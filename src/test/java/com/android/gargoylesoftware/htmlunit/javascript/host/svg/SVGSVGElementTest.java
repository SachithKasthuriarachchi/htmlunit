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
package com.android.gargoylesoftware.htmlunit.javascript.host.svg;

import com.android.gargoylesoftware.htmlunit.BrowserRunner;
import com.android.gargoylesoftware.htmlunit.WebDriverTestCase;
import com.android.gargoylesoftware.htmlunit.html.HtmlPageTest;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for {@link SVGSVGElement}.
 *
 * @author Ahmed Ashour
 * @author Frank Danek
 * @author Ronald Brill
 * @author Natasha Lazarova
 */
@RunWith(BrowserRunner.class)
public class SVGSVGElementTest extends WebDriverTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("[object SVGRect]")
    public void createSVGRect() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head>\n"
            + "<script>\n"
            + "  function test() {\n"
            + "    alert(document.createElementNS('http://www.w3.org/2000/svg', 'svg').createSVGRect());\n"
            + "  }\n"
            + "</script>\n"
            + "</head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void getInnerTextOfSvg() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
                + "<html><body>\n"
                + "  <svg xmlns='http://www.w3.org/2000/svg' id='myId' version='1.1'></svg>\n"
                + "  <script>\n"
                + "    var svg =  document.getElementById('myId');\n"
                + "    alert(svg.innerText);\n"
                + "  </script>\n"
                + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("")
    public void getInnerTextOfElementContainingSvg() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
                + "<html><body>\n"
                + "  <div id='myDivId'><svg xmlns='http://www.w3.org/2000/svg' version='1.1'></svg></div>\n"
                + "  <script>\n"
                + "    var div = document.getElementById('myDivId');\n"
                + "    alert(div.innerText);\n"
                + "  </script>\n"
                + "</body></html>";

        loadPageWithAlerts2(html);
    }
}
