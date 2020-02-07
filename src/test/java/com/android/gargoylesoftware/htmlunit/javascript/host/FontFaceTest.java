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
package com.android.gargoylesoftware.htmlunit.javascript.host;

import com.android.gargoylesoftware.htmlunit.BrowserRunner;
import com.android.gargoylesoftware.htmlunit.WebDriverTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for {@link FontFace}.
 *
 * @author Ronald Brill
 */
@RunWith(BrowserRunner.class)
public class FontFaceTest extends WebDriverTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function FontFace() {\n    [native code]\n}",
            CHROME = "function FontFace() { [native code] }",
            IE = "undefined")
    public void window() throws Exception {
        final String html
            = "<html>\n"
            + "<body>\n"
            + "<script>\n"
            + "  alert(window.FontFace);\n"
            + "</script>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }
}