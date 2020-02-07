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
package com.android.gargoylesoftware.htmlunit.javascript.host.performance;

import com.android.gargoylesoftware.htmlunit.javascript.SimpleScriptable;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.EventTarget;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxFunction;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxGetter;

/**
 * A JavaScript object for {@code Performance}.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@JsxClass({SupportedBrowser.CHROME, SupportedBrowser.FF})
@JsxClass(value = SupportedBrowser.IE, extendedClass = SimpleScriptable.class)
public class Performance extends EventTarget {
    private PerformanceTiming timing_;

    /**
     * Creates an instance.
     */
    @JsxConstructor({SupportedBrowser.CHROME, SupportedBrowser.FF, SupportedBrowser.EDGE})
    public Performance() {
    }

    /**
     * Returns the {@code navigation} property.
     * @return the {@code navigation} property
     */
    @JsxGetter
    public PerformanceNavigation getNavigation() {
        final PerformanceNavigation navigation = new PerformanceNavigation();
        navigation.setParentScope(getParentScope());
        navigation.setPrototype(getPrototype(navigation.getClass()));
        return navigation;
    }

    /**
     * Returns the {@code timing} property.
     * @return the {@code timing} property
     */
    @JsxGetter
    public PerformanceTiming getTiming() {
        if (timing_ == null) {
            final PerformanceTiming timing = new PerformanceTiming();
            timing.setParentScope(getParentScope());
            timing.setPrototype(getPrototype(timing.getClass()));
            timing_ = timing;
        }

        return timing_;
    }

    /**
     * @return a timestamp
     */
    @JsxFunction
    public double now() {
        return System.nanoTime() / 1_000_000d;
    }
}