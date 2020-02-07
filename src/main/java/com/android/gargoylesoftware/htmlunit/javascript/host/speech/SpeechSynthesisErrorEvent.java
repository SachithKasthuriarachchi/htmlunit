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
package com.android.gargoylesoftware.htmlunit.javascript.host.speech;

import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.SpeechSynthesisEvent;

/**
 * A JavaScript object for {@code SpeechSynthesisErrorEvent}.
 *
 * @author Ronald Brill
 */
@JsxClass(SupportedBrowser.FF)
public class SpeechSynthesisErrorEvent extends SpeechSynthesisEvent {

    /**
     * Creates a new instance.
     */
    @JsxConstructor
    public SpeechSynthesisErrorEvent() {
    }
}
