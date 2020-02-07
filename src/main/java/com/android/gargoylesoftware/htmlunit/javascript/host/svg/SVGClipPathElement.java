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
package com.android.gargoylesoftware.htmlunit.javascript.host.svg;

import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstant;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser;
import com.android.gargoylesoftware.htmlunit.svg.SvgClipPath;

/**
 * A JavaScript object for {@code SVGClipPathElement}.
 *
 * @author Ahmed Ashour
 */
@JsxClass(domClass = SvgClipPath.class)
public class SVGClipPathElement extends SVGElement {

    /** Constant for {@code SVG_UNIT_TYPE_UNKNOWN}. */
    @JsxConstant({SupportedBrowser.FF52, SupportedBrowser.IE})
    public static final int SVG_UNIT_TYPE_UNKNOWN = 0;
    /** Constant for {@code SVG_UNIT_TYPE_USERSPACEONUSE}. */
    @JsxConstant({SupportedBrowser.FF52, SupportedBrowser.IE})
    public static final int SVG_UNIT_TYPE_USERSPACEONUSE = 1;
    /** Constant for {@code SVG_UNIT_TYPE_OBJECTBOUNDINGBOX}. */
    @JsxConstant({SupportedBrowser.FF52, SupportedBrowser.IE})
    public static final int SVG_UNIT_TYPE_OBJECTBOUNDINGBOX = 2;

    /**
     * Creates an instance.
     */
    @JsxConstructor({SupportedBrowser.CHROME, SupportedBrowser.FF, SupportedBrowser.EDGE})
    public SVGClipPathElement() {
    }
}
