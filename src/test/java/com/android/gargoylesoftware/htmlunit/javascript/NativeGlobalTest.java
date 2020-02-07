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
package com.android.gargoylesoftware.htmlunit.javascript;

import com.android.gargoylesoftware.htmlunit.BrowserRunner;
import com.android.gargoylesoftware.htmlunit.WebDriverTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for setup done in {@link net.sourceforge.htmlunit.corejs.javascript.NativeGlobal}.
 *
 * @author Marc Guillemot
 * @author Frank Danek
 */
@RunWith(BrowserRunner.class)
public class NativeGlobalTest extends WebDriverTestCase {

    /**
     * Test assignment of constants.
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"undefined", "NaN", "Infinity"})
    public void assignConst() throws Exception {
        final String html
            = "<html><body><script>\n"
            + "undefined = 123;\n"
            + "alert(undefined);\n"
            + "NaN = 123;\n"
            + "alert(NaN);\n"
            + "Infinity = 123;\n"
            + "alert(Infinity);\n"
            + "</script></body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * Test for re-declaration of constants.
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"true", "undefined", "NaN", "Infinity"})
    public void redeclareConst() throws Exception {
        final String html
            = "<html><body><script>\n"
            + "var undefined;\n"
            + "var NaN;\n"
            + "var Infinity;\n"
            + "alert(window.foo == undefined);\n"
            + "undefined = 123;\n"
            + "alert(undefined);\n"
            + "alert(NaN);\n"
            + "alert(Infinity);\n"
            + "</script></body></html>";

        loadPageWithAlerts2(html);
    }
}
