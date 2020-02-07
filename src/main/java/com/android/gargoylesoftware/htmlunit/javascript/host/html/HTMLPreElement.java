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
import com.android.gargoylesoftware.htmlunit.html.HtmlExample;
import com.android.gargoylesoftware.htmlunit.html.HtmlListing;
import com.android.gargoylesoftware.htmlunit.html.HtmlPreformattedText;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.*;
import org.apache.android.commons.lang3.ArrayUtils;

import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxGetter;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxSetter;

import net.sourceforge.htmlunit.corejs.javascript.Context;

/**
 * The JavaScript object {@code HTMLPreElement}.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@JsxClass(domClass = HtmlExample.class, value = {SupportedBrowser.CHROME, SupportedBrowser.FF})
@JsxClass(domClass = HtmlPreformattedText.class)
@JsxClass(domClass = HtmlListing.class, value = {SupportedBrowser.CHROME, SupportedBrowser.FF})
public class HTMLPreElement extends HTMLElement {

    /** Valid values for the {@link #getClear() clear} property. */
    private static final String[] VALID_CLEAR_VALUES = new String[] {"left", "right", "all", "none"};

    /**
     * Creates an instance.
     */
    @JsxConstructor({SupportedBrowser.CHROME, SupportedBrowser.FF, SupportedBrowser.EDGE})
    public HTMLPreElement() {
    }

    /**
     * Returns the value of the {@code cite} property.
     * @return the value of the {@code cite} property
     */
    @JsxGetter(SupportedBrowser.IE)
    public String getCite() {
        final String cite = getDomNodeOrDie().getAttributeDirect("cite");
        return cite;
    }

    /**
     * Returns the value of the {@code cite} property.
     * @param cite the value
     */
    @JsxSetter(SupportedBrowser.IE)
    public void setCite(final String cite) {
        getDomNodeOrDie().setAttribute("cite", cite);
    }

    /**
     * Returns the {@code width} property.
     * @return the {@code width} property
     */
    @JsxGetter(propertyName = "width")
    public Object getWidth_js() {
        if (getBrowserVersion().hasFeature(BrowserVersionFeatures.JS_PRE_WIDTH_STRING)) {
            return getWidthOrHeight("width", Boolean.TRUE);
        }
        final String value = getDomNodeOrDie().getAttributeDirect("width");
        final Integer intValue = HTMLCanvasElement.getValue(value);
        if (intValue != null) {
            return intValue;
        }
        return 0;
    }

    /**
     * Sets the {@code width} property.
     * @param width the {@code width} property
     */
    @JsxSetter
    public void setWidth(final String width) {
        if (getBrowserVersion().hasFeature(BrowserVersionFeatures.JS_PRE_WIDTH_STRING)) {
            setWidthOrHeight("width", width, true);
        }
        else {
            getDomNodeOrDie().setAttribute("width", width);
        }
    }

    /**
     * Returns the value of the {@code clear} property.
     * @return the value of the {@code clear} property
     */
    @JsxGetter(SupportedBrowser.IE)
    public String getClear() {
        final String clear = getDomNodeOrDie().getAttributeDirect("clear");
        if (!ArrayUtils.contains(VALID_CLEAR_VALUES, clear)) {
            return "";
        }
        return clear;
    }

    /**
     * Sets the value of the {@code clear} property.
     * @param clear the value of the {@code clear} property
     */
    @JsxSetter(SupportedBrowser.IE)
    public void setClear(final String clear) {
        if (!ArrayUtils.contains(VALID_CLEAR_VALUES, clear)) {
            throw Context.reportRuntimeError("Invalid clear property value: '" + clear + "'.");
        }
        getDomNodeOrDie().setAttribute("clear", clear);
    }
}
