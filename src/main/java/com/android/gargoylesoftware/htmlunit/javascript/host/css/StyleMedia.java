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
package com.android.gargoylesoftware.htmlunit.javascript.host.css;

import com.android.gargoylesoftware.htmlunit.javascript.SimpleScriptable;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.*;
import org.w3c.dom.stylesheets.MediaList;

import com.gargoylesoftware.css.parser.CSSErrorHandler;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxFunction;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxGetter;

/**
 * A JavaScript object for {@code StyleMedia}.
 *
 * @author Ahmed Ashour
 */
@JsxClass(isJSObject = false, value = SupportedBrowser.CHROME)
@JsxClass({SupportedBrowser.IE, SupportedBrowser.EDGE})
public class StyleMedia extends SimpleScriptable {

    /**
     * Default constructor.
     */
    @JsxConstructor(SupportedBrowser.EDGE)
    public StyleMedia() {
    }

    /**
     * Returns the {@code type} property.
     * @return the {@code type} property
     */
    @JsxGetter
    public String getType() {
        return "screen";
    }

    /**
     * Returns whether the specified media is supported by the object that displays the document object.
     * @param media the media query
     * @return whether the specified media is supported or not
     */
    @JsxFunction
    public boolean matchMedium(final String media) {
        final CSSErrorHandler errorHandler = getWindow().getWebWindow().getWebClient().getCssErrorHandler();
        final MediaList mediaList = CSSStyleSheet.parseMedia(errorHandler, media);
        return CSSStyleSheet.isActive(this, mediaList);
    }

}
