/*
 * Copyright (c) 2002-2013 Gargoyle Software Inc.
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
package com.android.gargoylesoftware.htmlunit.javascript.host.html;

import static com.android.gargoylesoftware.htmlunit.BrowserVersionFeatures.GENERATED_90;
import static com.android.gargoylesoftware.htmlunit.BrowserVersionFeatures.HTMLABBREVIATED;

import com.android.gargoylesoftware.htmlunit.BrowserVersion;
import com.android.gargoylesoftware.htmlunit.html.DomNode;
import com.android.gargoylesoftware.htmlunit.html.HtmlAbbreviated;
import com.android.gargoylesoftware.htmlunit.html.HtmlAcronym;
import com.android.gargoylesoftware.htmlunit.html.HtmlAddress;
import com.android.gargoylesoftware.htmlunit.html.HtmlBidirectionalOverride;
import com.android.gargoylesoftware.htmlunit.html.HtmlBig;
import com.android.gargoylesoftware.htmlunit.html.HtmlBlink;
import com.android.gargoylesoftware.htmlunit.html.HtmlBold;
import com.android.gargoylesoftware.htmlunit.html.HtmlCenter;
import com.android.gargoylesoftware.htmlunit.html.HtmlCitation;
import com.android.gargoylesoftware.htmlunit.html.HtmlCode;
import com.android.gargoylesoftware.htmlunit.html.HtmlDefinition;
import com.android.gargoylesoftware.htmlunit.html.HtmlDefinitionDescription;
import com.android.gargoylesoftware.htmlunit.html.HtmlDefinitionTerm;
import com.android.gargoylesoftware.htmlunit.html.HtmlEmphasis;
import com.android.gargoylesoftware.htmlunit.html.HtmlExample;
import com.android.gargoylesoftware.htmlunit.html.HtmlItalic;
import com.android.gargoylesoftware.htmlunit.html.HtmlKeyboard;
import com.android.gargoylesoftware.htmlunit.html.HtmlListing;
import com.android.gargoylesoftware.htmlunit.html.HtmlMultiColumn;
import com.android.gargoylesoftware.htmlunit.html.HtmlNoBreak;
import com.android.gargoylesoftware.htmlunit.html.HtmlPlainText;
import com.android.gargoylesoftware.htmlunit.html.HtmlS;
import com.android.gargoylesoftware.htmlunit.html.HtmlSample;
import com.android.gargoylesoftware.htmlunit.html.HtmlSmall;
import com.android.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.android.gargoylesoftware.htmlunit.html.HtmlStrike;
import com.android.gargoylesoftware.htmlunit.html.HtmlStrong;
import com.android.gargoylesoftware.htmlunit.html.HtmlSubscript;
import com.android.gargoylesoftware.htmlunit.html.HtmlSuperscript;
import com.android.gargoylesoftware.htmlunit.html.HtmlTeletype;
import com.android.gargoylesoftware.htmlunit.html.HtmlUnderlined;
import com.android.gargoylesoftware.htmlunit.html.HtmlVariable;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.host.ActiveXObject;

/**
 * The JavaScript object "HTMLSpanElement".
 *
 * @version $Revision$
 * @author Ahmed Ashour
 * @author Daniel Gredler
 * @author Ronald Brill
 */
@JsxClass(domClasses = { HtmlAbbreviated.class, HtmlAcronym.class, HtmlAddress.class,
        HtmlBidirectionalOverride.class, HtmlBig.class, HtmlBold.class, HtmlBlink.class, HtmlCenter.class,
        HtmlCitation.class, HtmlCode.class, HtmlDefinition.class, HtmlDefinitionDescription.class,
        HtmlDefinitionTerm.class, HtmlEmphasis.class, HtmlItalic.class, HtmlKeyboard.class, HtmlListing.class,
        HtmlMultiColumn.class, HtmlNoBreak.class, HtmlPlainText.class, HtmlS.class, HtmlSample.class,
        HtmlSmall.class, HtmlSpan.class, HtmlStrike.class, HtmlStrong.class, HtmlSubscript.class,
        HtmlSuperscript.class, HtmlTeletype.class, HtmlUnderlined.class, HtmlVariable.class, HtmlExample.class })
public class HTMLSpanElement extends HTMLElement {
    private boolean endTagForbidden_;

    /**
     * Sets the DOM node that corresponds to this JavaScript object.
     * @param domNode the DOM node
     */
    @Override
    public void setDomNode(final DomNode domNode) {
        super.setDomNode(domNode);
        final BrowserVersion browser = getBrowserVersion();
        if (!browser.hasFeature(GENERATED_90)) {
            if ("basefont".equalsIgnoreCase(domNode.getLocalName())) {
                endTagForbidden_ = true;
            }

            return;
        }

        if ((domNode instanceof HtmlAbbreviated && browser.hasFeature(HTMLABBREVIATED))
            || domNode instanceof HtmlAcronym
            || domNode instanceof HtmlBidirectionalOverride
            || domNode instanceof HtmlBig
            || domNode instanceof HtmlBold
            || domNode instanceof HtmlBlink
            || domNode instanceof HtmlCitation
            || domNode instanceof HtmlCode
            || domNode instanceof HtmlDefinition
            || domNode instanceof HtmlEmphasis
            || domNode instanceof HtmlItalic
            || domNode instanceof HtmlKeyboard
            || domNode instanceof HtmlNoBreak
            || domNode instanceof HtmlS
            || domNode instanceof HtmlSample
            || domNode instanceof HtmlSmall
            || domNode instanceof HtmlStrike
            || domNode instanceof HtmlStrong
            || domNode instanceof HtmlSubscript
            || domNode instanceof HtmlSuperscript
            || domNode instanceof HtmlTeletype
            || domNode instanceof HtmlUnderlined
            || domNode instanceof HtmlVariable) {
            ActiveXObject.addProperty(this, "cite", true, true);
            ActiveXObject.addProperty(this, "dateTime", true, true);
            return;
        }

        if (domNode instanceof HtmlAddress
            || domNode instanceof HtmlCenter
            || domNode instanceof HtmlExample
            || domNode instanceof HtmlListing
            || domNode instanceof HtmlPlainText) {
            ActiveXObject.addProperty(this, "cite", true, true);
        }
    }

    /**
     * Returns the value of the "cite" property.
     * @return the value of the "cite" property
     */
    public String getCite() {
        final String cite = getDomNodeOrDie().getAttribute("cite");
        return cite;
    }

    /**
     * Returns the value of the "cite" property.
     * @param cite the value
     */
    public void setCite(final String cite) {
        getDomNodeOrDie().setAttribute("cite", cite);
    }

    /**
     * Returns the value of the "dateTime" property.
     * @return the value of the "dateTime" property
     */
    public String getDateTime() {
        final String dateTime = getDomNodeOrDie().getAttribute("datetime");
        return dateTime;
    }

    /**
     * Returns the value of the "dateTime" property.
     * @param dateTime the value
     */
    public void setDateTime(final String dateTime) {
        getDomNodeOrDie().setAttribute("datetime", dateTime);
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isLowerCaseInOuterHtml() {
        if (getDomNodeOrDie() instanceof HtmlMultiColumn) {
            return true;
        }
        return super.isLowerCaseInOuterHtml();
    }

    /**
     * Returns whether the end tag is forbidden or not.
     * @see <a href="http://www.w3.org/TR/html4/index/elements.html">HTML 4 specs</a>
     * @return whether the end tag is forbidden or not
     */
    protected boolean isEndTagForbidden() {
        return endTagForbidden_;
    }

    /**
     * <span style="color:red">INTERNAL API - SUBJECT TO CHANGE AT ANY TIME - USE AT YOUR OWN RISK.</span><br/>
     * {@inheritDoc}
    */
    @Override
    public String getDefaultStyleDisplay() {
        final String tagName = getTagName();
        if ("ADDRESS".equals(tagName)
                || "CENTER".equals(tagName)
                || "DD".equals(tagName)
                || "DT".equals(tagName)) {
            return super.getDefaultStyleDisplay();
        }
        return "inline";
    }
}
