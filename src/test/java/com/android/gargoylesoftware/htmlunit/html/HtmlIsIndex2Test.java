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
package com.android.gargoylesoftware.htmlunit.html;

import com.android.gargoylesoftware.htmlunit.BrowserRunner;
import com.android.gargoylesoftware.htmlunit.WebDriverTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Tests for {@link HtmlIsIndex}.
 *
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Marc Guillemot
 * @author Ahmed Ashour
 * @author Frank Danek
 * @author Ronald Brill
 */
@RunWith(BrowserRunner.class)
public class HtmlIsIndex2Test extends WebDriverTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "null",
            CHROME = "[object HTMLUnknownElement]",
            FF60 = "[object HTMLUnknownElement]")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.IE, BrowserRunner.TestedBrowser.FF52})
    public void simpleScriptable() throws Exception {
        final String html = "<html><head>\n"
            + "<script>\n"
            + "  function test() {\n"
            + "    alert(document.getElementById('myId'));\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='test()'>\n"
            + "  <form id='form1' method='post'>\n"
            + "    <isindex id='myId' prompt='enterSomeText'></isindex>\n"
            + "  </form>\n"
            + "</body></html>";

        final WebDriver driver = loadPageWithAlerts2(html);
        if (driver instanceof HtmlUnitDriver) {
            final HtmlPage page = (HtmlPage) getWebWindowOf((HtmlUnitDriver) driver).getEnclosedPage();
            assertTrue(HtmlUnknownElement.class.isInstance(page.getHtmlElementById("myId")));
        }
    }
}