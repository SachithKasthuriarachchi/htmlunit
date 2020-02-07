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

import com.android.gargoylesoftware.htmlunit.html.HtmlMarquee;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.*;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxGetter;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxSetter;

/**
 * The JavaScript object {@code HTMLMarqueeElement}.
 *
 * @author Ronald Brill
 * @author Ahmed Ashour
 */
@JsxClass(domClass = HtmlMarquee.class, value = {SupportedBrowser.CHROME, SupportedBrowser.IE, SupportedBrowser.EDGE})
public class HTMLMarqueeElement extends HTMLElement {

    /**
     * Creates an instance.
     */
    @JsxConstructor({SupportedBrowser.CHROME, SupportedBrowser.EDGE})
    public HTMLMarqueeElement() {
    }

    /**
     * Returns the {@code width} property.
     * @return the {@code width} property
     */
    @Override
    @JsxGetter({SupportedBrowser.CHROME, SupportedBrowser.IE})
    public int getWidth() {
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
    @JsxSetter({SupportedBrowser.CHROME, SupportedBrowser.IE})
    public void setWidth(final int width) {
        getDomNodeOrDie().setAttribute("width", Integer.toString(width));
    }

    /**
     * Returns the {@code height} property.
     * @return the {@code height} property
     */
    @Override
    @JsxGetter({SupportedBrowser.CHROME, SupportedBrowser.IE})
    public int getHeight() {
        final String value = getDomNodeOrDie().getAttributeDirect("height");
        final Integer intValue = HTMLCanvasElement.getValue(value);
        if (intValue != null) {
            return intValue;
        }
        return 0;
    }

    /**
     * Sets the {@code height} property.
     * @param height the {@code height} property
     */
    @JsxSetter({SupportedBrowser.CHROME, SupportedBrowser.IE})
    public void setHeight(final int height) {
        getDomNodeOrDie().setAttribute("height", Integer.toString(height));
    }

    /**
     * Returns the value of the {@code bgColor} property.
     * @return the value of the {@code bgColor} property
     */
    @JsxGetter({SupportedBrowser.CHROME, SupportedBrowser.IE})
    public String getBgColor() {
        return getDomNodeOrDie().getAttribute("bgColor");
    }

    /**
     * Sets the value of the {@code bgColor} property.
     * @param bgColor the value of the {@code bgColor} property
     */
    @JsxSetter({SupportedBrowser.CHROME, SupportedBrowser.IE})
    public void setBgColor(final String bgColor) {
        setColorAttribute("bgColor", bgColor);
    }

}