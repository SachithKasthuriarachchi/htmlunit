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
package com.android.gargoylesoftware.htmlunit.javascript.host.canvas;

import com.android.gargoylesoftware.htmlunit.javascript.SimpleScriptable;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxFunction;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser;
import com.android.gargoylesoftware.htmlunit.javascript.host.Element;

/**
 * A JavaScript object for {@code IntersectionObserver}.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@JsxClass({SupportedBrowser.CHROME, SupportedBrowser.FF60})
public class IntersectionObserver extends SimpleScriptable {

    /**
     * Default constructor.
     */
    @JsxConstructor
    public IntersectionObserver() {
    }

    /**
     * Observes the target.
     * @param target the target
     */
    @JsxFunction
    public void observe(final Element target) {
        //nothing
    }

    /**
     * Removes the target from observation.
     *
     * @param target the target
     */
    @JsxFunction
    public void unobserve(final Element target) {
        //nothing
    }

    /**
     * Disconnects.
     */
    @JsxFunction
    public void disconnect() {
        //nothing
    }

    /**
     * Takes records.
     * @return the records
     */
    @JsxFunction
    public Object takeRecords() {
        return null;
    }
}