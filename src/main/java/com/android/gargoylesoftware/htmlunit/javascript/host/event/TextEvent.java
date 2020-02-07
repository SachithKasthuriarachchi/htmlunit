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
package com.android.gargoylesoftware.htmlunit.javascript.host.event;

import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstant;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser;

/**
 * A JavaScript object for {@code TextEvent}.
 *
 * @author Ahmed Ashour
 */
@JsxClass({SupportedBrowser.CHROME, SupportedBrowser.IE, SupportedBrowser.EDGE})
public class TextEvent extends UIEvent {

    /** Constant for {@code DOM_INPUT_METHOD_UNKNOWN}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_UNKNOWN = 0;
    /** Constant for {@code DOM_INPUT_METHOD_KEYBOARD}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_KEYBOARD = 1;
    /** Constant for {@code DOM_INPUT_METHOD_PASTE}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_PASTE = 2;
    /** Constant for {@code DOM_INPUT_METHOD_DROP}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_DROP = 3;
    /** Constant for {@code DOM_INPUT_METHOD_IME}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_IME = 4;
    /** Constant for {@code DOM_INPUT_METHOD_OPTION}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_OPTION = 5;
    /** Constant for {@code DOM_INPUT_METHOD_HANDWRITING}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_HANDWRITING = 6;
    /** Constant for {@code DOM_INPUT_METHOD_VOICE}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_VOICE = 7;
    /** Constant for {@code DOM_INPUT_METHOD_MULTIMODAL}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_MULTIMODAL = 8;
    /** Constant for {@code DOM_INPUT_METHOD_SCRIPT}. */
    @JsxConstant(SupportedBrowser.IE)
    public static final int DOM_INPUT_METHOD_SCRIPT = 9;

    /**
     * Default constructor.
     */
    @JsxConstructor({SupportedBrowser.CHROME, SupportedBrowser.EDGE})
    public TextEvent() {
    }
}
