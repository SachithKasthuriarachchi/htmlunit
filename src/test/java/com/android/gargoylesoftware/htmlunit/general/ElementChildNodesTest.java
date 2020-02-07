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
package com.android.gargoylesoftware.htmlunit.general;

import com.android.gargoylesoftware.htmlunit.BrowserRunner;
import com.android.gargoylesoftware.htmlunit.WebDriverTestCase;
import com.android.gargoylesoftware.htmlunit.html.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests the result of {@code element.childNodes.length}.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 * @author Marc Guillemot
 * @author Frank Danek
 */
@RunWith(BrowserRunner.class)
public class ElementChildNodesTest extends WebDriverTestCase {

    private static String test(final String tagName) {
        return "<html><head><title>test_getChildNodes</title>\n"
                + "<script>\n"
                + "function test() {\n"
                + "  for (var i = 1; i <= 6; i++) {\n"
                + "    var element = document.getElementById('p' + i);\n"
                + "    if (element) {\n"
                + "      alert(element.childNodes.length);\n"
                + "    }\n"
                + "  }\n"
                + "}\n"
                + "</script>\n"
                + "</head><body onload='test()'>\n"
                + "<p id='p1'> <" + tagName + "></" + tagName + "> </p>\n"
                + "<p id='p2'><" + tagName + "></" + tagName + "> </p>\n"
                + "<p id='p3'> <" + tagName + "></" + tagName + "></p>\n"
                + "<p id='p4'> <" + tagName + ">var x</" + tagName + "> </p>\n"
                + "<p id='p5'><" + tagName + ">var x</" + tagName + "> </p>\n"
                + "<p id='p6'> <" + tagName + ">var x</" + tagName + "></p>\n"
                + "</body></html>";
    }

    /**
     * Test {@link HtmlAbbreviated}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void abbr() throws Exception {
        loadPageWithAlerts2(test("abbr"));
    }

    /**
     * Test {@link HtmlAcronym}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void acronym() throws Exception {
        loadPageWithAlerts2(test("acronym"));
    }

    /**
     * Test {@link HtmlAnchor}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void a() throws Exception {
        loadPageWithAlerts2(test("a"));
    }

    /**
     * Test {@link HtmlAddress}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void address() throws Exception {
        loadPageWithAlerts2(test("address"));
    }

    /**
     * Test {@link HtmlApplet}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void applet() throws Exception {
        loadPageWithAlerts2(test("applet"));
    }

    /**
     * Test {@link HtmlArea}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void area() throws Exception {
        loadPageWithAlerts2(test("area"));
    }

    /**
     * Test {@link HtmlArticle}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void article() throws Exception {
        loadPageWithAlerts2(test("article"));
    }

    /**
     * Test {@link HtmlAside}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void aside() throws Exception {
        loadPageWithAlerts2(test("aside"));
    }

    /**
     * Test {@link HtmlAudio}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void audio() throws Exception {
        loadPageWithAlerts2(test("audio"));
    }

    /**
     * Test {@link HtmlBackgroundSound}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void bgsound() throws Exception {
        loadPageWithAlerts2(test("bgsound"));
    }

    /**
     * Test {@link HtmlBase}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void base() throws Exception {
        loadPageWithAlerts2(test("base"));
    }

    /**
     * Test {@link HtmlBaseFont}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void basefont() throws Exception {
        loadPageWithAlerts2(test("basefont"));
    }

    /**
     * Test {@link HtmlBidirectionalIsolation}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void bdi() throws Exception {
        loadPageWithAlerts2(test("bdi"));
    }

    /**
     * Test {@link HtmlBidirectionalOverride}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void bdo() throws Exception {
        loadPageWithAlerts2(test("bdo"));
    }

    /**
     * Test {@link HtmlBig}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void big() throws Exception {
        loadPageWithAlerts2(test("big"));
    }

    /**
     * Test {@link HtmlBlink}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void blink() throws Exception {
        loadPageWithAlerts2(test("blink"));
    }

    /**
     * Test {@link HtmlBlockQuote}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void blockquote() throws Exception {
        loadPageWithAlerts2(test("blockquote"));
    }

    /**
     * Test {@link HtmlBody}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void body() throws Exception {
        loadPageWithAlerts2(test("body"));
    }

    /**
     * Test {@link HtmlBold}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void b() throws Exception {
        loadPageWithAlerts2(test("b"));
    }

    /**
     * Test {@link HtmlBreak}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"4", "3", "3", "5", "4", "4"})
    public void br() throws Exception {
        loadPageWithAlerts2(test("br"));
    }

    /**
     * Test {@link HtmlButton}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void button() throws Exception {
        loadPageWithAlerts2(test("button"));
    }

    /**
     * Test {@link HtmlCanvas}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void canvas() throws Exception {
        loadPageWithAlerts2(test("canvas"));
    }

    /**
     * Test {@link HtmlCaption}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void caption() throws Exception {
        loadPageWithAlerts2(test("caption"));
    }

    /**
     * Test {@link HtmlCenter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void center() throws Exception {
        loadPageWithAlerts2(test("center"));
    }

    /**
     * Test {@link HtmlCitation}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void cite() throws Exception {
        loadPageWithAlerts2(test("cite"));
    }

    /**
     * Test {@link HtmlCode}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void code() throws Exception {
        loadPageWithAlerts2(test("code"));
    }

    /**
     * Test {@link HtmlCommand}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = {"3", "2", "2", "3", "2", "3"},
            FF = {"3", "2", "2", "3", "2", "2"})
    public void command() throws Exception {
        loadPageWithAlerts2(test("command"));
    }

    /**
     * Test {@link HtmlDataList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void datalist() throws Exception {
        loadPageWithAlerts2(test("datalist"));
    }

    /**
     * Test {@link HtmlDefinition}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void dfn() throws Exception {
        loadPageWithAlerts2(test("dfn"));
    }

    /**
     * Test {@link HtmlDefinitionDescription}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void dd() throws Exception {
        loadPageWithAlerts2(test("dd"));
    }

    /**
     * Test {@link HtmlDeletedText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void del() throws Exception {
        loadPageWithAlerts2(test("del"));
    }

    /**
     * Test {@link HtmlDetails}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void details() throws Exception {
        loadPageWithAlerts2(test("details"));
    }

    /**
     * Test {@link HtmlDialog}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = {"3", "2", "2", "3", "2", "2"},
            FF60 = {"1", "0", "1", "1", "0", "1"})
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.FF60)
    public void dialog() throws Exception {
        loadPageWithAlerts2(test("dialog"));
    }

    /**
     * Test {@link HtmlDirectory}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void dir() throws Exception {
        loadPageWithAlerts2(test("dir"));
    }

    /**
     * Test {@link HtmlDivision}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void div() throws Exception {
        loadPageWithAlerts2(test("div"));
    }

    /**
     * Test {@link HtmlDefinitionList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void dl() throws Exception {
        loadPageWithAlerts2(test("dl"));
    }

    /**
     * Test {@link HtmlDefinitionTerm}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void dt() throws Exception {
        loadPageWithAlerts2(test("dt"));
    }

    /**
     * Test {@link HtmlEmbed}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void embed() throws Exception {
        loadPageWithAlerts2(test("embed"));
    }

    /**
     * Test {@link HtmlEmphasis}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void em() throws Exception {
        loadPageWithAlerts2(test("em"));
    }

    /**
     * Test {@link HtmlFieldSet}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void fieldset() throws Exception {
        loadPageWithAlerts2(test("fieldset"));
    }

    /**
     * Test {@link HtmlFigureCaption}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void figcaption() throws Exception {
        loadPageWithAlerts2(test("figcaption"));
    }

    /**
     * Test {@link HtmlFigure}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void figure() throws Exception {
        loadPageWithAlerts2(test("figure"));
    }

    /**
     * Test {@link HtmlFont}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void font() throws Exception {
        loadPageWithAlerts2(test("font"));
    }

    /**
     * Test {@link HtmlForm}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void form() throws Exception {
        loadPageWithAlerts2(test("form"));
    }

    /**
     * Test {@link HtmlFooter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void footer() throws Exception {
        loadPageWithAlerts2(test("footer"));
    }

    /**
     * Test {@link HtmlFrame}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void frame() throws Exception {
        loadPageWithAlerts2(test("frame"));
    }

    /**
     * Test {@link HtmlFrameSet}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void frameset() throws Exception {
        loadPageWithAlerts2(test("frameset"));
    }

    /**
     * Test {@link HtmlHeading1}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void h1() throws Exception {
        loadPageWithAlerts2(test("h1"));
    }

    /**
     * Test {@link HtmlHeading2}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void h2() throws Exception {
        loadPageWithAlerts2(test("h2"));
    }

    /**
     * Test {@link HtmlHeading3}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void h3() throws Exception {
        loadPageWithAlerts2(test("h3"));
    }

    /**
     * Test {@link HtmlHeading4}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void h4() throws Exception {
        loadPageWithAlerts2(test("h4"));
    }

    /**
     * Test {@link HtmlHeading5}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void h5() throws Exception {
        loadPageWithAlerts2(test("h5"));
    }

    /**
     * Test {@link HtmlHeading6}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void h6() throws Exception {
        loadPageWithAlerts2(test("h6"));
    }

    /**
     * Test {@link HtmlHead}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void head() throws Exception {
        loadPageWithAlerts2(test("head"));
    }

    /**
     * Test {@link HtmlHeader}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void header() throws Exception {
        loadPageWithAlerts2(test("header"));
    }

    /**
     * Test {@link HtmlHorizontalRule}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void hr() throws Exception {
        loadPageWithAlerts2(test("hr"));
    }

    /**
     * Test {@link HtmlHtml}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void html() throws Exception {
        loadPageWithAlerts2(test("html"));
    }

    /**
     * Test {@link HtmlInlineFrame}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void iframe() throws Exception {
        loadPageWithAlerts2(test("iframe"));
    }

    /**
     * Test {@link HtmlImage}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void image() throws Exception {
        loadPageWithAlerts2(test("image"));
    }

    /**
     * Test {@link HtmlImage}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void img() throws Exception {
        loadPageWithAlerts2(test("img"));
    }

    /**
     * Test {@link HtmlInsertedText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void ins() throws Exception {
        loadPageWithAlerts2(test("ins"));
    }

    /**
     * Test {@link HtmlIsIndex}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = {"1", "0", "1", "1", "0", "1"},
            CHROME = {"3", "2", "2", "3", "2", "2"},
            FF60 = {"3", "2", "2", "3", "2", "2"})
    public void isindex() throws Exception {
        loadPageWithAlerts2(test("isindex"));
    }

    /**
     * Test {@link HtmlItalic}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void i() throws Exception {
        loadPageWithAlerts2(test("i"));
    }

    /**
     * Test {@link HtmlKeyboard}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void kbd() throws Exception {
        loadPageWithAlerts2(test("kbd"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void keygen() throws Exception {
        loadPageWithAlerts2(test("keygen"));
    }

    /**
     * Test {@link HtmlLabel}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void label() throws Exception {
        loadPageWithAlerts2(test("label"));
    }

    /**
     * Test {@link HtmlLayer}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void layer() throws Exception {
        loadPageWithAlerts2(test("layer"));
    }

    /**
     * Test {@link HtmlLegend}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void legend() throws Exception {
        loadPageWithAlerts2(test("legend"));
    }

    /**
     * Test {@link HtmlListing}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void listing() throws Exception {
        loadPageWithAlerts2(test("listing"));
    }

    /**
     * Test {@link HtmlListItem}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void li() throws Exception {
        loadPageWithAlerts2(test("li"));
    }

    /**
     * Test {@link HtmlLink}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void link() throws Exception {
        loadPageWithAlerts2(test("link"));
    }

    /**
     * Test {@link HtmlMain}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = {"1", "0", "1", "1", "0", "1"},
            IE = {"3", "2", "2", "3", "2", "2"})
    public void main() throws Exception {
        loadPageWithAlerts2(test("main"));
    }

    /**
     * Test {@link HtmlMap}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void map() throws Exception {
        loadPageWithAlerts2(test("map"));
    }

    /**
     * Test {@link HtmlMarquee}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void marquee() throws Exception {
        loadPageWithAlerts2(test("marquee"));
    }

    /**
     * Test {@link HtmlMark}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void mark() throws Exception {
        loadPageWithAlerts2(test("mark"));
    }

    /**
     * Test {@link HtmlMenu}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void menu() throws Exception {
        loadPageWithAlerts2(test("menu"));
    }

    /**
     * Test {@link HtmlMenuItem}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void menuitem() throws Exception {
        loadPageWithAlerts2(test("menuitem"));
    }

    /**
     * Test {@link HtmlMeta}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void meta() throws Exception {
        loadPageWithAlerts2(test("meta"));
    }

    /**
     * Test {@link HtmlMeter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void meter() throws Exception {
        loadPageWithAlerts2(test("meter"));
    }

    /**
     * Test {@link HtmlMultiColumn}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void multicol() throws Exception {
        loadPageWithAlerts2(test("multicol"));
    }

    /**
     * Test {@link HtmlNav}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void nav() throws Exception {
        loadPageWithAlerts2(test("nav"));
    }

    /**
     * Test {@link HtmlNextId}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void nextid() throws Exception {
        loadPageWithAlerts2(test("nextid"));
    }

    /**
     * Test {@link HtmlNoBreak}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void nobr() throws Exception {
        loadPageWithAlerts2(test("nobr"));
    }

    /**
     * Test {@link HtmlNoEmbed}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void noembed() throws Exception {
        loadPageWithAlerts2(test("noembed"));
    }

    /**
     * Test {@link HtmlNoFrames}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void noframes() throws Exception {
        loadPageWithAlerts2(test("noframes"));
    }

    /**
     * Test {@link HtmlNoLayer}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void nolayer() throws Exception {
        loadPageWithAlerts2(test("nolayer"));
    }

    /**
     * Test {@link HtmlNoScript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void noscript() throws Exception {
        loadPageWithAlerts2(test("noscript"));
    }

    /**
     * Test {@link HtmlObject}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void object() throws Exception {
        loadPageWithAlerts2(test("object"));
    }

    /**
     * Test {@link HtmlOrderedList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void ol() throws Exception {
        loadPageWithAlerts2(test("ol"));
    }

    /**
     * Test {@link HtmlOptionGroup}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void optgroup() throws Exception {
        loadPageWithAlerts2(test("optgroup"));
    }

    /**
     * Test {@link HtmlOption}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void option() throws Exception {
        loadPageWithAlerts2(test("option"));
    }

    /**
     * Test {@link HtmlOutput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void output() throws Exception {
        loadPageWithAlerts2(test("output"));
    }

    /**
     * Test {@link HtmlParagraph}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void p() throws Exception {
        loadPageWithAlerts2(test("p"));
    }

    /**
     * Test {@link HtmlParameter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void param() throws Exception {
        loadPageWithAlerts2(test("param"));
    }

    /**
     * Test {@link HtmlPlainText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("1")
    public void plaintext() throws Exception {
        loadPageWithAlerts2(test("plaintext"));
    }

    /**
     * Test {@link HtmlPreformattedText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void pre() throws Exception {
        loadPageWithAlerts2(test("pre"));
    }

    /**
     * Test {@link HtmlProgress}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void progress() throws Exception {
        loadPageWithAlerts2(test("progress"));
    }

    /**
     * Test {@link HtmlInlineQuotation}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void q() throws Exception {
        loadPageWithAlerts2(test("q"));
    }

    /**
     * Test {@link HtmlRuby}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void ruby() throws Exception {
        loadPageWithAlerts2(test("ruby"));
    }

    /**
     * Test {@link HtmlRt}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void rt() throws Exception {
        loadPageWithAlerts2(test("rt"));
    }

    /**
     * Test {@link HtmlRp}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void rp() throws Exception {
        loadPageWithAlerts2(test("rp"));
    }

    /**
     * Test {@link HtmlS}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void s() throws Exception {
        loadPageWithAlerts2(test("s"));
    }

    /**
     * Test {@link HtmlSample}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void samp() throws Exception {
        loadPageWithAlerts2(test("samp"));
    }

    /**
     * Test {@link HtmlScript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void script() throws Exception {
        loadPageWithAlerts2(test("script"));
    }

    /**
     * Test {@link HtmlSection}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void section() throws Exception {
        loadPageWithAlerts2(test("section"));
    }

    /**
     * Test {@link HtmlSelect}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void select() throws Exception {
        loadPageWithAlerts2(test("select"));
    }

    /**
     * Test {@link HtmlSmall}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void small() throws Exception {
        loadPageWithAlerts2(test("small"));
    }

    /**
     * Test {@link HtmlSource}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void source() throws Exception {
        loadPageWithAlerts2(test("source"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void spacer() throws Exception {
        loadPageWithAlerts2(test("spacer"));
    }

    /**
     * Test {@link HtmlSpan}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void span() throws Exception {
        loadPageWithAlerts2(test("span"));
    }

    /**
     * Test {@link HtmlStrike}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void strike() throws Exception {
        loadPageWithAlerts2(test("strike"));
    }

    /**
     * Test {@link HtmlStrong}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void strong() throws Exception {
        loadPageWithAlerts2(test("strong"));
    }

    /**
     * Test {@link HtmlStyle}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void style() throws Exception {
        loadPageWithAlerts2(test("style"));
    }

    /**
     * Test {@link HtmlSubscript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void sub() throws Exception {
        loadPageWithAlerts2(test("sub"));
    }

    /**
     * Test {@link HtmlSummary}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void summary() throws Exception {
        loadPageWithAlerts2(test("summary"));
    }

    /**
     * Test {@link HtmlSuperscript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void sup() throws Exception {
        loadPageWithAlerts2(test("sup"));
    }

    /**
     * Test {@link HtmlSuperscript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void svg() throws Exception {
        loadPageWithAlerts2(test("svg"));
    }

    /**
     * Test {@link HtmlTable}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "3", "2"})
    public void table() throws Exception {
        loadPageWithAlerts2(test("table"));
    }

    /**
     * Test {@link HtmlTableColumn}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void col() throws Exception {
        loadPageWithAlerts2(test("col"));
    }

    /**
     * Test {@link HtmlTableColumnGroup}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void colgroup() throws Exception {
        loadPageWithAlerts2(test("colgroup"));
    }

    /**
     * Test {@link HtmlTableBody}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void tbody() throws Exception {
        loadPageWithAlerts2(test("tbody"));
    }

    /**
     * Test {@link HtmlTableDataCell}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void td() throws Exception {
        loadPageWithAlerts2(test("td"));
    }

    /**
     * Test {@link HtmlTableHeaderCell}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void th() throws Exception {
        loadPageWithAlerts2(test("th"));
    }

    /**
     * Test {@link HtmlTableRow}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void tr() throws Exception {
        loadPageWithAlerts2(test("tr"));
    }

    /**
     * Test {@link HtmlTrack}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void track() throws Exception {
        loadPageWithAlerts2(test("track"));
    }

    /**
     * Test {@link HtmlTextArea}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void textarea() throws Exception {
        loadPageWithAlerts2(test("textarea"));
    }

    /**
     * Test {@link HtmlTableFooter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void tfoot() throws Exception {
        loadPageWithAlerts2(test("tfoot"));
    }

    /**
     * Test {@link HtmlTableHeader}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "1", "1", "1", "1", "1"})
    public void thead() throws Exception {
        loadPageWithAlerts2(test("thead"));
    }

    /**
     * Test {@link HtmlTeletype}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void tt() throws Exception {
        loadPageWithAlerts2(test("tt"));
    }

    /**
     * Test {@link HtmlTime}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void time() throws Exception {
        loadPageWithAlerts2(test("time"));
    }

    /**
     * Test {@link HtmlTitle}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void title() throws Exception {
        loadPageWithAlerts2(test("title"));
    }

    /**
     * Test {@link HtmlUnderlined}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void u() throws Exception {
        loadPageWithAlerts2(test("u"));
    }

    /**
     * Test {@link HtmlUnorderedList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void ul() throws Exception {
        loadPageWithAlerts2(test("ul"));
    }

    /**
     * Test {@link HtmlVariable}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void var() throws Exception {
        loadPageWithAlerts2(test("var"));
    }

    /**
     * Test {@link HtmlVideo}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void video() throws Exception {
        loadPageWithAlerts2(test("video"));
    }

    /**
     * Test {@link HtmlWordBreak}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void wbr() throws Exception {
        loadPageWithAlerts2(test("wbr"));
    }

    /**
     * Test {@link HtmlExample}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"1", "0", "1", "1", "0", "1"})
    public void xmp() throws Exception {
        loadPageWithAlerts2(test("xmp"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "3"})
    public void input() throws Exception {
        loadPageWithAlerts2(test("input"));
    }

    /**
     * Test {@link HtmlData}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void data() throws Exception {
        loadPageWithAlerts2(test("data"));
    }

    /**
     * Test {@link HtmlContent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void content() throws Exception {
        loadPageWithAlerts2(test("content"));
    }

    /**
     * Test {@link HtmlPicture}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void picture() throws Exception {
        loadPageWithAlerts2(test("picture"));
    }

    /**
     * Test {@link HtmlTemplate}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void template() throws Exception {
        loadPageWithAlerts2(test("template"));
    }

    /**
     * Test {@link HtmlSlot}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts({"3", "2", "2", "3", "2", "2"})
    public void slot() throws Exception {
        loadPageWithAlerts2(test("slot"));
    }
}
