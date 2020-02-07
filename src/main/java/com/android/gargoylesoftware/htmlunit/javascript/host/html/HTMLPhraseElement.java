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
package com.android.gargoylesoftware.htmlunit.javascript.host.html;

import com.android.gargoylesoftware.htmlunit.BrowserVersionFeatures;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser;
import com.android.gargoylesoftware.htmlunit.javascript.host.ActiveXObject;
import com.android.gargoylesoftware.htmlunit.html.DomNode;
import com.android.gargoylesoftware.htmlunit.html.HtmlAbbreviated;
import com.android.gargoylesoftware.htmlunit.html.HtmlAcronym;
import com.android.gargoylesoftware.htmlunit.html.HtmlBidirectionalOverride;
import com.android.gargoylesoftware.htmlunit.html.HtmlBig;
import com.android.gargoylesoftware.htmlunit.html.HtmlBlink;
import com.android.gargoylesoftware.htmlunit.html.HtmlBold;
import com.android.gargoylesoftware.htmlunit.html.HtmlCitation;
import com.android.gargoylesoftware.htmlunit.html.HtmlCode;
import com.android.gargoylesoftware.htmlunit.html.HtmlDefinition;
import com.android.gargoylesoftware.htmlunit.html.HtmlEmphasis;
import com.android.gargoylesoftware.htmlunit.html.HtmlItalic;
import com.android.gargoylesoftware.htmlunit.html.HtmlKeyboard;
import com.android.gargoylesoftware.htmlunit.html.HtmlNoBreak;
import com.android.gargoylesoftware.htmlunit.html.HtmlRp;
import com.android.gargoylesoftware.htmlunit.html.HtmlRt;
import com.android.gargoylesoftware.htmlunit.html.HtmlRuby;
import com.android.gargoylesoftware.htmlunit.html.HtmlS;
import com.android.gargoylesoftware.htmlunit.html.HtmlSample;
import com.android.gargoylesoftware.htmlunit.html.HtmlSmall;
import com.android.gargoylesoftware.htmlunit.html.HtmlStrike;
import com.android.gargoylesoftware.htmlunit.html.HtmlStrong;
import com.android.gargoylesoftware.htmlunit.html.HtmlSubscript;
import com.android.gargoylesoftware.htmlunit.html.HtmlSuperscript;
import com.android.gargoylesoftware.htmlunit.html.HtmlTeletype;
import com.android.gargoylesoftware.htmlunit.html.HtmlUnderlined;
import com.android.gargoylesoftware.htmlunit.html.HtmlVariable;

/**
 * The JavaScript object {@code HTMLPhraseElement}.
 *
 * @author Ahmed Ashour
 * @author Daniel Gredler
 * @author Ronald Brill
 */
@JsxClass(domClass = HtmlAbbreviated.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlAcronym.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlBidirectionalOverride.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlBig.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlBlink.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlBold.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlCitation.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlCode.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlDefinition.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlEmphasis.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlItalic.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlKeyboard.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlNoBreak.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlRt.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlRp.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlRuby.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlS.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlSample.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlSmall.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlStrike.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlSubscript.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlSuperscript.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlStrong.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlTeletype.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlUnderlined.class, value = SupportedBrowser.IE)
@JsxClass(domClass = HtmlVariable.class, value = SupportedBrowser.IE)
public class HTMLPhraseElement extends HTMLElement {

    /**
     * Sets the DOM node that corresponds to this JavaScript object.
     * @param domNode the DOM node
     */
    @Override
    public void setDomNode(final DomNode domNode) {
        super.setDomNode(domNode);

        if (getBrowserVersion().hasFeature(BrowserVersionFeatures.JS_XML_SUPPORT_VIA_ACTIVEXOBJECT)) {
            if ((domNode instanceof HtmlAbbreviated && getBrowserVersion().hasFeature(BrowserVersionFeatures.HTMLABBREVIATED))
                || domNode instanceof HtmlAcronym
                || domNode instanceof HtmlBidirectionalOverride
                || domNode instanceof HtmlBig
                || domNode instanceof HtmlBlink
                || domNode instanceof HtmlBold
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
                || domNode instanceof HtmlStrong
                || domNode instanceof HtmlStrike
                || domNode instanceof HtmlSubscript
                || domNode instanceof HtmlSuperscript
                || domNode instanceof HtmlTeletype
                || domNode instanceof HtmlUnderlined
                || domNode instanceof HtmlVariable
                ) {
                ActiveXObject.addProperty(this, "cite", true, true);
                ActiveXObject.addProperty(this, "dateTime", true, true);
            }
        }
    }

    /**
     * Returns the value of the {@code cite} property.
     * @return the value of the {@code cite} property
     */
    public String getCite() {
        final String cite = getDomNodeOrDie().getAttributeDirect("cite");
        return cite;
    }

    /**
     * Returns the value of the {@code cite} property.
     * @param cite the value
     */
    public void setCite(final String cite) {
        getDomNodeOrDie().setAttribute("cite", cite);
    }

    /**
     * Returns the value of the {@code dateTime} property.
     * @return the value of the {@code dateTime} property
     */
    public String getDateTime() {
        final String dateTime = getDomNodeOrDie().getAttributeDirect("datetime");
        return dateTime;
    }

    /**
     * Returns the value of the {@code dateTime} property.
     * @param dateTime the value
     */
    public void setDateTime(final String dateTime) {
        getDomNodeOrDie().setAttribute("datetime", dateTime);
    }

    /**
     * Returns whether the end tag is forbidden or not.
     * @see <a href="http://www.w3.org/TR/html4/index/elements.html">HTML 4 specs</a>
     * @return whether the end tag is forbidden or not
     */
    @Override
    protected boolean isEndTagForbidden() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        if (getWindow().getWebWindow() != null && getBrowserVersion().hasFeature(BrowserVersionFeatures.JS_PHRASE_COMMON_CLASS_NAME)) {
            return "HTMLElement";
        }
        return super.getClassName();
    }
}
