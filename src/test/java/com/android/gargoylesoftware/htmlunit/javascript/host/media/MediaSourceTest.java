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

import com.android.gargoylesoftware.htmlunit.BrowserRunner;
import com.android.gargoylesoftware.htmlunit.WebDriverTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for {@link MediaSource}.
 *
 * @author Ronald Brill
 */
@RunWith(BrowserRunner.class)
public class MediaSourceTest extends WebDriverTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "true",
            IE = "false")
    public void inWindow() throws Exception {
        final String html
            = "<html>\n"
            + "<head>\n"
            + "  <script>\n"
            + "    function test() {\n"
            + "      alert('MediaSource' in window);\n"
            + "    }\n"
            + "  </script>\n"
            + "</head>\n"
            + "<body onload='test()'>\n"
            + "</body>\n"
            + "</html>";

        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = { "true", "true", "false" },
            CHROME = { "true", "false", "false" },
            IE = "MediaSource not available")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF})
    public void isTypeSypported() throws Exception {
        final String html
            = "<html>\n"
            + "<head>\n"
            + "  <script>\n"
            + "    function test() {\n"
            + "      if (!('MediaSource' in window)) {\n"
            + "        alert('MediaSource not available');\n"
            + "        return;\n"
            + "      }\n"

            + "      supported('video/mp4; codecs=\"avc1.42E01E, mp4a.40.2\"');\n"
            + "      supported('video/mp4');\n"
            + "      supported('');\n"
            + "    }\n"
            + "    function supported(mime) {\n"
            + "      alert(MediaSource.isTypeSupported(mime));\n"
            + "    }\n"
            + "  </script>\n"
            + "</head>\n"
            + "<body onload='test()'>\n"
            + "</body>\n"
            + "</html>";

        loadPageWithAlerts2(html);
    }
}