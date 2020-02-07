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
package com.android.gargoylesoftware.htmlunit.svg;

import com.android.gargoylesoftware.htmlunit.BrowserRunner;
import com.android.gargoylesoftware.htmlunit.WebDriverTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.android.gargoylesoftware.htmlunit.html.HtmlPage;
import com.android.gargoylesoftware.htmlunit.html.HtmlPageTest;

/**
 * Tests for {@link SvgTitle}.
 *
 * @author Ahmed Ashour
 * @author Frank Danek
 */
@RunWith(BrowserRunner.class)
public class SvgTitleTest extends WebDriverTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("[object SVGTitleElement]")
    public void simpleScriptable() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head>\n"
            + "<script>\n"
            + "  function test() {\n"
            + "    alert(document.getElementById('myId'));\n"
            + "  }\n"
            + "</script>\n"
            + "</head><body onload='test()'>\n"
            + "  <svg xmlns='http://www.w3.org/2000/svg' version='1.1'>\n"
            + "    <title id='myId'/>\n"
            + "  </svg>\n"
            + "</body></html>";

        final WebDriver driver = loadPageWithAlerts2(html);
        if (driver instanceof HtmlUnitDriver) {
            final HtmlPage page = (HtmlPage) getWebWindowOf((HtmlUnitDriver) driver).getEnclosedPage();
            assertTrue(SvgTitle.class.isInstance(page.getElementById("myId")));
        }
    }
}