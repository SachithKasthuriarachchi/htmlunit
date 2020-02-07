/*
 * Copyright (c) 2002-2018 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.gargoylesoftware.htmlunit.html;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.android.gargoylesoftware.htmlunit.*;
import org.apache.android.commons.io.IOUtils;
import org.apache.android.commons.lang3.StringUtils;
import org.apache.android.commons.logging.Log;
import org.apache.android.commons.logging.LogFactory;
import org.apache.android.http.HttpStatus;

import com.android.gargoylesoftware.htmlunit.HttpHeader;
import com.android.gargoylesoftware.htmlunit.Page;
import com.android.gargoylesoftware.htmlunit.SgmlPage;
import com.android.gargoylesoftware.htmlunit.WebClient;
import com.android.gargoylesoftware.htmlunit.WebRequest;
import com.android.gargoylesoftware.htmlunit.WebResponse;
import com.android.gargoylesoftware.htmlunit.javascript.PostponedAction;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.Node;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.Event;

/**
 * Wrapper for the HTML element "img".
 *
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author David K. Taylor
 * @author <a href="mailto:cse@dynabean.de">Christian Sell</a>
 * @author Ahmed Ashour
 * @author <a href="mailto:knut.johannes.dahle@gmail.com">Knut Johannes Dahle</a>
 * @author Ronald Brill
 * @author Frank Danek
 * @author Carsten Steul
 */
public class HtmlImage extends HtmlElement {

    private static final Log LOG = LogFactory.getLog(HtmlImage.class);

    /** The HTML tag represented by this element. */
    public static final String TAG_NAME = "img";
    /** Another HTML tag represented by this element. */
    public static final String TAG_NAME2 = "image";

    private final String originalQualifiedName_;

    private int lastClickX_;
    private int lastClickY_;
    private WebResponse imageWebResponse_;
    private transient ImageData imageData_;
    private int width_ = -1;
    private int height_ = -1;
    private boolean downloaded_;
    private boolean isComplete_;
    private boolean onloadInvoked_;
    private boolean createdByJavascript_;

    /**
     * Creates a new instance.
     *
     * @param qualifiedName the qualified name of the element type to instantiate
     * @param page the page that contains this element
     * @param attributes the initial attributes
     */
    HtmlImage(final String qualifiedName, final SgmlPage page, final Map<String, DomAttr> attributes) {
        super(unifyLocalName(qualifiedName), page, attributes);
        originalQualifiedName_ = qualifiedName;
        if (page.getWebClient().getOptions().isDownloadImages()) {
            try {
                downloadImageIfNeeded();
            }
            catch (final IOException e) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Unable to download image for element " + this);
                }
            }
        }
    }

    private static String unifyLocalName(final String qualifiedName) {
        if (qualifiedName != null && qualifiedName.endsWith(TAG_NAME2)) {
            final int pos = qualifiedName.lastIndexOf(TAG_NAME2);
            return qualifiedName.substring(0, pos) + TAG_NAME;
        }
        return qualifiedName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onAddedToPage() {
        doOnLoad();
        super.onAddedToPage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setAttributeNS(final String namespaceURI, final String qualifiedName, final String value,
            final boolean notifyAttributeChangeListeners, final boolean notifyMutationObservers) {

        final HtmlPage htmlPage = getHtmlPageOrNull();
        if ("src".equals(qualifiedName) && value != ATTRIBUTE_NOT_DEFINED && htmlPage != null) {
            final String oldValue = getAttributeNS(namespaceURI, qualifiedName);
            if (!oldValue.equals(value)) {
                super.setAttributeNS(namespaceURI, qualifiedName, value, notifyAttributeChangeListeners,
                        notifyMutationObservers);

                // onload handlers may need to be invoked again, and a new image may need to be downloaded
                onloadInvoked_ = false;
                downloaded_ = false;
                isComplete_ = false;
                width_ = -1;
                height_ = -1;
                if (imageData_ != null) {
                    imageData_.close();
                    imageData_ = null;
                }

                final String readyState = htmlPage.getReadyState();
                if (READY_STATE_LOADING.equals(readyState)) {
                    final PostponedAction action = new PostponedAction(getPage()) {
                        @Override
                        public void execute() throws Exception {
                            doOnLoad();
                        }
                    };
                    htmlPage.addAfterLoadAction(action);
                    return;
                }
                doOnLoad();
                return;
            }
        }

        super.setAttributeNS(namespaceURI, qualifiedName, value, notifyAttributeChangeListeners,
                notifyMutationObservers);
    }

    /**
     * <p><span style="color:red">INTERNAL API - SUBJECT TO CHANGE AT ANY TIME - USE AT YOUR OWN RISK.</span></p>
     *
     * <p>Executes this element's <tt>onload</tt> handler if it has one. This method also downloads the image
     * if this element has an <tt>onload</tt> handler (prior to invoking said handler), because applications
     * sometimes use images to send information to the server and use the <tt>onload</tt> handler to get notified
     * when the information has been received by the server.</p>
     *
     * <p>See <a href="http://www.nabble.com/How-should-we-handle-image.onload--tt9850876.html">here</a> and
     * <a href="http://www.nabble.com/Image-Onload-Support-td18895781.html">here</a> for the discussion which
     * lead up to this method.</p>
     *
     * <p>This method may be called multiple times, but will only attempt to execute the <tt>onload</tt>
     * handler the first time it is invoked.</p>
     */
    public void doOnLoad() {
        if (onloadInvoked_) {
            return;
        }

        final HtmlPage htmlPage = getHtmlPageOrNull();
        if (htmlPage == null) {
            return; // nothing to do if embedded in XML code
        }

        final WebClient client = htmlPage.getWebClient();
        if (!client.getOptions().isJavaScriptEnabled()) {
            onloadInvoked_ = true;
            return;
        }

        if (hasEventHandlers("onload") && !getSrcAttribute().isEmpty()) {
            // An onload handler and source are defined; we need to download the image and then call the onload handler.
            onloadInvoked_ = true;
            try {
                downloadImageIfNeeded();
                final int i = imageWebResponse_.getStatusCode();
                if ((i >= HttpStatus.SC_OK && i < HttpStatus.SC_MULTIPLE_CHOICES)
                        || i == HttpStatus.SC_USE_PROXY) {

                    // If the download was a success, trigger the onload handler.
                    final Event event = new Event(this, Event.TYPE_LOAD);
                    final Node scriptObject = getScriptableObject();

                    final String readyState = htmlPage.getReadyState();
                    if (READY_STATE_LOADING.equals(readyState)) {
                        final PostponedAction action = new PostponedAction(getPage()) {
                            @Override
                            public void execute() throws Exception {
                                scriptObject.executeEventLocally(event);
                            }
                        };
                        htmlPage.addAfterLoadAction(action);
                    }
                    else {
                        scriptObject.executeEventLocally(event);
                    }
                }
                return;
            }
            catch (final IOException e) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("IOException while downloading image for '" + this + "' : " + e.getMessage());
                }
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to download image for '" + this + "'; not firing onload event.");
            }
        }
    }

    /**
     * Returns the value of the attribute {@code src}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code src} or an empty string if that attribute isn't defined
     */
    public final String getSrcAttribute() {
        return getSrcAttributeNormalized();
    }

    /**
     * Returns the value of the attribute {@code alt}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code alt} or an empty string if that attribute isn't defined
     */
    public final String getAltAttribute() {
        return getAttributeDirect("alt");
    }

    /**
     * Returns the value of the attribute {@code name}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code name} or an empty string if that attribute isn't defined
     */
    public final String getNameAttribute() {
        return getAttributeDirect("name");
    }

    /**
     * Returns the value of the attribute {@code longdesc}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code longdesc} or an empty string if that attribute isn't defined
     */
    public final String getLongDescAttribute() {
        return getAttributeDirect("longdesc");
    }

    /**
     * Returns the value of the attribute {@code height}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code height} or an empty string if that attribute isn't defined
     */
    public final String getHeightAttribute() {
        return getAttributeDirect("height");
    }

    /**
     * Returns the value of the attribute {@code width}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code width} or an empty string if that attribute isn't defined
     */
    public final String getWidthAttribute() {
        return getAttributeDirect("width");
    }

    /**
     * Returns the value of the attribute {@code usemap}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code usemap} or an empty string if that attribute isn't defined
     */
    public final String getUseMapAttribute() {
        return getAttributeDirect("usemap");
    }

    /**
     * Returns the value of the attribute {@code ismap}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code ismap} or an empty string if that attribute isn't defined
     */
    public final String getIsmapAttribute() {
        return getAttributeDirect("ismap");
    }

    /**
     * Returns the value of the attribute {@code align}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code align} or an empty string if that attribute isn't defined
     */
    public final String getAlignAttribute() {
        return getAttributeDirect("align");
    }

    /**
     * Returns the value of the attribute {@code border}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code border} or an empty string if that attribute isn't defined
     */
    public final String getBorderAttribute() {
        return getAttributeDirect("border");
    }

    /**
     * Returns the value of the attribute {@code hspace}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code hspace} or an empty string if that attribute isn't defined
     */
    public final String getHspaceAttribute() {
        return getAttributeDirect("hspace");
    }

    /**
     * Returns the value of the attribute {@code vspace}. Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code vspace} or an empty string if that attribute isn't defined
     */
    public final String getVspaceAttribute() {
        return getAttributeDirect("vspace");
    }

    /**
     * <p>Returns the image's actual height (<b>not</b> the image's {@link #getHeightAttribute() height attribute}).</p>
     * <p><span style="color:red">POTENTIAL PERFORMANCE KILLER - DOWNLOADS THE IMAGE - USE AT YOUR OWN RISK</span></p>
     * <p>If the image has not already been downloaded, this method triggers a download and caches the image.</p>
     *
     * @return the image's actual height
     * @throws IOException if an error occurs while downloading or reading the image
     */
    public int getHeight() throws IOException {
        if (height_ < 0) {
            determineWidthAndHeight();
        }
        return height_;
    }

    /**
     * <p>Returns the image's actual width (<b>not</b> the image's {@link #getWidthAttribute() width attribute}).</p>
     * <p><span style="color:red">POTENTIAL PERFORMANCE KILLER - DOWNLOADS THE IMAGE - USE AT YOUR OWN RISK</span></p>
     * <p>If the image has not already been downloaded, this method triggers a download and caches the image.</p>
     *
     * @return the image's actual width
     * @throws IOException if an error occurs while downloading or reading the image
     */
    public int getWidth() throws IOException {
        if (width_ < 0) {
            determineWidthAndHeight();
        }
        return width_;
    }

    /**
     * <p>Returns the <tt>ImageReader</tt> which can be used to read the image contained by this image element.</p>
     * <p><span style="color:red">POTENTIAL PERFORMANCE KILLER - DOWNLOADS THE IMAGE - USE AT YOUR OWN RISK</span></p>
     * <p>If the image has not already been downloaded, this method triggers a download and caches the image.</p>
     *
     * @return the <tt>ImageReader</tt> which can be used to read the image contained by this image element
     * @throws IOException if an error occurs while downloading or reading the image
     */
    public ImageReader getImageReader() throws IOException {
        readImageIfNeeded();
        return imageData_.getImageReader();
    }

    private void determineWidthAndHeight() throws IOException {
        final ImageReader imgReader = getImageReader();
        width_ = imgReader.getWidth(0);
        height_ = imgReader.getHeight(0);

        // ImageIO creates temp files; to save file handles
        // we will cache the values and close this directly to free the resources
        if (imageData_ != null) {
            imageData_.close();
            imageData_ = null;
        }
    }

    /**
     * <p>Returns the <tt>WebResponse</tt> for the image contained by this image element.</p>
     * <p><span style="color:red">POTENTIAL PERFORMANCE KILLER - DOWNLOADS THE IMAGE - USE AT YOUR OWN RISK</span></p>
     * <p>If the image has not already been downloaded and <tt>downloadIfNeeded</tt> is {@code true}, this method
     * triggers a download and caches the image.</p>
     *
     * @param downloadIfNeeded whether or not the image should be downloaded (if it hasn't already been downloaded)
     * @return {@code null} if no download should be performed and one hasn't already been triggered; otherwise,
     *         the response received when performing a request for the image referenced by this element
     * @throws IOException if an error occurs while downloading the image
     */
    public WebResponse getWebResponse(final boolean downloadIfNeeded) throws IOException {
        if (downloadIfNeeded) {
            downloadImageIfNeeded();
        }
        return imageWebResponse_;
    }

    /**
     * <p>Downloads the image contained by this image element.</p>
     * <p><span style="color:red">POTENTIAL PERFORMANCE KILLER - DOWNLOADS THE IMAGE - USE AT YOUR OWN RISK</span></p>
     * <p>If the image has not already been downloaded, this method triggers a download and caches the image.</p>
     *
     * @throws IOException if an error occurs while downloading the image
     */
    private void downloadImageIfNeeded() throws IOException {
        if (!downloaded_) {
            // HTMLIMAGE_BLANK_SRC_AS_EMPTY
            final String src = getSrcAttribute();
            if (!"".equals(src)
                    && !(hasFeature(BrowserVersionFeatures.HTMLIMAGE_BLANK_SRC_AS_EMPTY) && StringUtils.isBlank(src))) {
                final HtmlPage page = (HtmlPage) getPage();
                final WebClient webclient = page.getWebClient();

                final URL url = page.getFullyQualifiedUrl(src);
                final String accept = webclient.getBrowserVersion().getImgAcceptHeader();
                final WebRequest request = new WebRequest(url, accept);
                request.setCharset(page.getCharset());
                request.setAdditionalHeader(HttpHeader.REFERER, page.getUrl().toExternalForm());
                imageWebResponse_ = webclient.loadWebResponse(request);
            }

            if (imageData_ != null) {
                imageData_.close();
                imageData_ = null;
            }
            downloaded_ = true;
            isComplete_ = hasFeature(BrowserVersionFeatures.JS_IMAGE_COMPLETE_RETURNS_TRUE_FOR_NO_REQUEST)
                    || (imageWebResponse_ != null && imageWebResponse_.getContentType().contains("image"));

            width_ = -1;
            height_ = -1;
        }
    }

    private void readImageIfNeeded() throws IOException {
        downloadImageIfNeeded();
        if (imageData_ == null) {
            if (null == imageWebResponse_) {
                throw new IOException("No image response available (src=" + getSrcAttribute() + ")");
            }
            @SuppressWarnings("resource")
            final ImageInputStream iis = ImageIO.createImageInputStream(imageWebResponse_.getContentAsStream());
            final Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                iis.close();
                throw new IOException("No image detected in response");
            }
            final ImageReader imageReader = iter.next();
            imageReader.setInput(iis);
            imageData_ = new ImageData(imageReader);

            // dispose all others
            while (iter.hasNext()) {
                iter.next().dispose();
            }
        }
    }

    /**
     * Simulates clicking this element at the specified position. This only makes sense for
     * an image map (currently only server side), where the position matters. This method
     * returns the page contained by this image's window after the click, which may or may not
     * be the same as the original page, depending on JavaScript event handlers, etc.
     *
     * @param x the x position of the click
     * @param y the y position of the click
     * @return the page contained by this image's window after the click
     * @exception IOException if an IO error occurs
     */
    public Page click(final int x, final int y) throws IOException {
        lastClickX_ = x;
        lastClickY_ = y;
        return super.click();
    }

    /**
     * Simulates clicking this element at the position <tt>(0, 0)</tt>. This method returns
     * the page contained by this image's window after the click, which may or may not be the
     * same as the original page, depending on JavaScript event handlers, etc.
     *
     * @return the page contained by this image's window after the click
     * @exception IOException if an IO error occurs
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page click() throws IOException {
        return click(0, 0);
    }

    /**
     * Performs the click action on the enclosing A tag (if any).
     * {@inheritDoc}
     * @throws IOException if an IO error occurred
     */
    @Override
    protected boolean doClickStateUpdate(final boolean shiftKey, final boolean ctrlKey) throws IOException {
        if (getUseMapAttribute() != ATTRIBUTE_NOT_DEFINED) {
            // remove initial '#'
            final String mapName = getUseMapAttribute().substring(1);
            final HtmlElement doc = ((HtmlPage) getPage()).getDocumentElement();
            final HtmlMap map = doc.getOneHtmlElementByAttribute("map", "name", mapName);
            for (final DomElement element : map.getChildElements()) {
                if (element instanceof HtmlArea) {
                    final HtmlArea area = (HtmlArea) element;
                    if (area.containsPoint(lastClickX_, lastClickY_)) {
                        area.doClickStateUpdate(shiftKey, ctrlKey);
                        return false;
                    }
                }
            }
        }
        final HtmlAnchor anchor = (HtmlAnchor) getEnclosingElement("a");
        if (anchor == null) {
            return false;
        }
        if (getIsmapAttribute() != ATTRIBUTE_NOT_DEFINED) {
            final String suffix = "?" + lastClickX_ + "," + lastClickY_;
            anchor.doClickStateUpdate(false, false, suffix);
            return false;
        }
        anchor.doClickStateUpdate(shiftKey, ctrlKey);
        return false;
    }

    /**
     * Saves this image as the specified file.
     * @param file the file to save to
     * @throws IOException if an IO error occurs
     */
    public void saveAs(final File file) throws IOException {
        downloadImageIfNeeded();
        if (null != imageWebResponse_) {
            try (FileOutputStream fileOut = new FileOutputStream(file);
                    InputStream inputStream = imageWebResponse_.getContentAsStream()) {
                IOUtils.copy(inputStream, fileOut);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DisplayStyle getDefaultStyleDisplay() {
        return DisplayStyle.INLINE;
    }

    /**
     * Wraps the ImageReader for an HtmlImage. This is necessary because an object with a finalize()
     * method is only garbage collected after the method has been run. Which causes all referenced
     * objects to also not be garbage collected until this happens. Because a HtmlImage references a lot
     * of objects which could all be garbage collected without impacting the ImageReader it is better to
     * wrap it in another class.
     */
    static final class ImageData implements AutoCloseable {

        private final ImageReader imageReader_;

        ImageData(final ImageReader imageReader) {
            imageReader_ = imageReader;
        }

        public ImageReader getImageReader() {
            return imageReader_;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void finalize() throws Throwable {
            close();
            super.finalize();
        }

        @Override
        public void close() {
            if (imageReader_ != null) {
                try {
                    try (ImageInputStream stream = (ImageInputStream) imageReader_.getInput()) {
                        // nothing
                    }
                }
                catch (final IOException e) {
                    LOG.error(e.getMessage(), e);
                }
                finally {
                    imageReader_.setInput(null);
                    imageReader_.dispose();
                }
            }
        }
    }

    /**
     * @return true if the image was successfully downloaded
     */
    public boolean isComplete() {
        return isComplete_ || (hasFeature(BrowserVersionFeatures.JS_IMAGE_COMPLETE_RETURNS_TRUE_FOR_NO_REQUEST)
                                ? ATTRIBUTE_NOT_DEFINED == getSrcAttribute()
                                : imageData_ != null);
    }

    /**
     * @return true if the image was successfully downloaded
     * @deprecated as of 2.26, please use {@link #isComplete()} instead
     */
    @Deprecated
    public boolean getComplete() {
        return isComplete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisplayed() {
        final String src = getSrcAttribute();
        if (hasFeature(BrowserVersionFeatures.HTMLIMAGE_INVISIBLE_NO_SRC)) {
            if (ATTRIBUTE_NOT_DEFINED == src) {
                return false;
            }
            if (hasFeature(BrowserVersionFeatures.HTMLIMAGE_BLANK_SRC_AS_EMPTY) && StringUtils.isBlank(src)) {
                return false;
            }
            if (hasFeature(BrowserVersionFeatures.HTMLIMAGE_EMPTY_SRC_DISPLAY_FALSE) && StringUtils.isEmpty(src)) {
                return false;
            }
        }

        return super.isDisplayed();
    }

    /**
     * <span style="color:red">INTERNAL API - SUBJECT TO CHANGE AT ANY TIME - USE AT YOUR OWN RISK.</span><br>
     *
     * Marks this frame as created by javascript. This is needed to handle
     * some special IE behavior.
     */
    public void markAsCreatedByJavascript() {
        createdByJavascript_ = true;
    }

    /**
     * <span style="color:red">INTERNAL API - SUBJECT TO CHANGE AT ANY TIME - USE AT YOUR OWN RISK.</span><br>
     *
     * Returns true if this frame was created by javascript. This is needed to handle
     * some special IE behavior.
     * @return true or false
     */
    public boolean wasCreatedByJavascript() {
        return createdByJavascript_;
    }

    /**
     * Returns the original element qualified name,
     * this is needed to differentiate between <tt>img</tt> and <tt>image</tt>.
     * @return the original element qualified name
     */
    public String getOriginalQualifiedName() {
        return originalQualifiedName_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocalName() {
        if (wasCreatedByJavascript()
                && (hasFeature(BrowserVersionFeatures.HTMLIMAGE_HTMLELEMENT) || hasFeature(BrowserVersionFeatures.HTMLIMAGE_HTMLUNKNOWNELEMENT))) {
            return originalQualifiedName_;
        }
        return super.getLocalName();
    }
}