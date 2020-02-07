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
package com.android.gargoylesoftware.htmlunit.javascript.host.media;

import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser;

/**
 * A JavaScript object for {@code DynamicsCompressorNode}.
 *
 * @author Ahmed Ashour
 */
@JsxClass({SupportedBrowser.CHROME, SupportedBrowser.FF, SupportedBrowser.EDGE})
public class DynamicsCompressorNode extends AudioNode {

    /**
     * Creates an instance.
     */
    @JsxConstructor
    public DynamicsCompressorNode() {
    }
}