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

import com.android.gargoylesoftware.htmlunit.javascript.configuration.*;
import org.apache.android.commons.lang3.ArrayUtils;

import com.android.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.android.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.android.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.android.gargoylesoftware.htmlunit.html.HtmlHeading4;
import com.android.gargoylesoftware.htmlunit.html.HtmlHeading5;
import com.android.gargoylesoftware.htmlunit.html.HtmlHeading6;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxGetter;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxSetter;

import net.sourceforge.htmlunit.corejs.javascript.Context;

/**
 * The JavaScript object {@code HTMLHeadingElement}.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@JsxClass(domClass = HtmlHeading1.class)
@JsxClass(domClass = HtmlHeading2.class)
@JsxClass(domClass = HtmlHeading3.class)
@JsxClass(domClass = HtmlHeading4.class)
@JsxClass(domClass = HtmlHeading5.class)
@JsxClass(domClass = HtmlHeading6.class)
public class HTMLHeadingElement extends HTMLElement {

    /** Valid values for the {@link #getClear() clear} property. */
    private static final String[] VALID_CLEAR_VALUES = new String[] {"left", "right", "all", "none"};

    /**
     * Creates an instance.
     */
    @JsxConstructor({SupportedBrowser.CHROME, SupportedBrowser.FF, SupportedBrowser.EDGE})
    public HTMLHeadingElement() {
    }

    /**
     * Returns the value of the {@code align} property.
     * @return the value of the {@code align} property
     */
    @JsxGetter
    public String getAlign() {
        return getAlign(false);
    }

    /**
     * Sets the value of the {@code align} property.
     * @param align the value of the {@code align} property
     */
    @JsxSetter
    public void setAlign(final String align) {
        setAlign(align, false);
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