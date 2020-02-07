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
package com.android.gargoylesoftware.htmlunit.javascript.host.css.property;

import com.android.gargoylesoftware.htmlunit.BrowserRunner;
import com.android.gargoylesoftware.htmlunit.WebDriverTestCase;
import com.android.gargoylesoftware.htmlunit.html.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit tests for {@code offsetWidth} of an element.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@RunWith(BrowserRunner.class)
public class ElementOffsetWidthTest extends WebDriverTestCase {

    private static final String VALUE_ = "e == null ? e : (e.offsetWidth < 1000 ? e.offsetWidth :"
            + "e.offsetWidth - document.documentElement.offsetWidth)";

    private static String test(final String tagName) {
        if ("basefont".equals(tagName) || "isindex".equals(tagName)) {
            return headElementClosesItself(tagName);
        }

        if ("title".equals(tagName)) {
            // title is a bit special, we have to provide at least
            // one closing tab otherwise title spans to the end of the file
            return "<html><head>\n"
                    + "<script>\n"
                    + "function test() {\n"
                    + "  var e = document.getElementById('outer');\n"
                    + "  alert(" + VALUE_ + ");\n"
                    + "}\n"
                    + "</script>\n"
                    + "<title id='outer'><title></title>\n"
                    + "</head><body onload='test()'>\n"
                    + "</body></html>";
        }

        if ("frame".equals(tagName)) {
            return "<html><head>\n"
                    + "<script>\n"
                    + "function test() {\n"
                    + "  var e = document.getElementById('outer');\n"
                    + "  alert(" + VALUE_ + ");\n"
                    + "}\n"
                    + "</script>\n"
                    + "</head>\n"
                    + "<frameset onload='test()'>\n"
                    + "<frame id='outer'><frame>\n"
                    + "</frameset></html>";
        }
        if ("script".equals(tagName)) {
            return "<html><head>\n"
                    + "<script>\n"
                    + "function test() {\n"
                    + "  var e = document.getElementById('outer');\n"
                    + "  alert(" + VALUE_ + ");\n"
                    + "}\n"
                    + "</script>\n"
                    + "</head><body onload='test()'>\n"
                    + "<script id='outer'>//<script>\n"
                    + "</script>\n"
                    + "</body></html>";
        }
        if ("frameset".equals(tagName)) {
            return "<html><head>\n"
                    + "<script>\n"
                    + "function test() {\n"
                    + "  var e = document.getElementById('outer');\n"
                    + "  alert(" + VALUE_ + ");\n"
                    + "}\n"
                    + "</script>\n"
                    + "</head>\n"
                    + "<frameset onload='test()' id='outer'>\n"
                    + "<frameset>\n"
                    + "</frameset></html>";
        }

        return "<html><head>\n"
                + "<script>\n"
                + "function test() {\n"
                + "  var e = document.getElementById('outer');\n"
                + "  alert(" + VALUE_ + ");\n"
                + "}\n"
                + "</script>\n"
                + "</head><body onload='test()'>\n"
                + "<" + tagName + " id='outer'><" + tagName + "></" + tagName + "></" + tagName + ">\n"
                + "</body></html>";
    }

    private static String testInput(final String type) {
        return "<html><head>\n"
                + "<script>\n"
                + "function test() {\n"
                + "  var e = document.getElementById('outer');\n"
                + "  alert(" + VALUE_ + ");\n"
                + "}\n"
                + "</script>\n"
                + "</head><body onload='test()'>\n"
                + "<input type='" + type + "' id='outer'>\n"
                + "</body></html>";
    }

    private static String headElementClosesItself(final String tagName) {
        return "<html><head>\n"
                + "<" + tagName + " id='outer'><" + tagName + ">\n"
                + "<script>\n"
                + "function test() {\n"
                + "  var e = document.getElementById('outer');\n"
                + "  alert(" + VALUE_ + ");\n"
                + "}\n"
                + "</script>\n"
                + "</head><body onload='test()'>\n"
                + "</body></html>";
    }

    /**
     * Test {@link HtmlAbbreviated}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void abbr() throws Exception {
        loadPageWithAlerts2(test("abbr"));
    }

    /**
     * Test {@link HtmlAcronym}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void acronym() throws Exception {
        loadPageWithAlerts2(test("acronym"));
    }

    /**
     * Test {@link HtmlAnchor}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void a() throws Exception {
        loadPageWithAlerts2(test("a"));
    }

    /**
     * Test {@link HtmlAddress}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void address() throws Exception {
        loadPageWithAlerts2(test("address"));
    }

    /**
     * Test {@link HtmlApplet}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void applet() throws Exception {
        loadPageWithAlerts2(test("applet"));
    }

    /**
     * Test {@link HtmlArea}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void area() throws Exception {
        loadPageWithAlerts2(test("area"));
    }

    /**
     * Test {@link HtmlArticle}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void article() throws Exception {
        loadPageWithAlerts2(test("article"));
    }

    /**
     * Test {@link HtmlAside}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void aside() throws Exception {
        loadPageWithAlerts2(test("aside"));
    }

    /**
     * Test {@link HtmlAudio}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void audio() throws Exception {
        loadPageWithAlerts2(test("audio"));
    }

    /**
     * Test {@link HtmlBackgroundSound}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void bgsound() throws Exception {
        loadPageWithAlerts2(test("bgsound"));
    }

    /**
     * Test {@link HtmlBase}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void base() throws Exception {
        loadPageWithAlerts2(test("base"));
    }

    /**
     * Test {@link HtmlBaseFont}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void basefont() throws Exception {
        loadPageWithAlerts2(test("basefont"));
    }

    /**
     * Test {@link HtmlBidirectionalIsolation}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void bdi() throws Exception {
        loadPageWithAlerts2(test("bdi"));
    }

    /**
     * Test {@link HtmlBidirectionalOverride}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void bdo() throws Exception {
        loadPageWithAlerts2(test("bdo"));
    }

    /**
     * Test {@link HtmlBig}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void big() throws Exception {
        loadPageWithAlerts2(test("big"));
    }

    /**
     * Test {@link HtmlBlink}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void blink() throws Exception {
        loadPageWithAlerts2(test("blink"));
    }

    /**
     * Test {@link HtmlBlockQuote}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-96")
    @BrowserRunner.NotYetImplemented
    public void blockquote() throws Exception {
        loadPageWithAlerts2(test("blockquote"));
    }

    /**
     * Test {@link HtmlBody}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void body() throws Exception {
        loadPageWithAlerts2(test("body"));
    }

    /**
     * Test {@link HtmlBold}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void b() throws Exception {
        loadPageWithAlerts2(test("b"));
    }

    /**
     * Test {@link HtmlBreak}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void br() throws Exception {
        loadPageWithAlerts2(test("br"));
    }

    /**
     * Test {@link HtmlButton}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "22",
            CHROME = "16",
            FF52 = "24")
    @BrowserRunner.NotYetImplemented
    public void button() throws Exception {
        loadPageWithAlerts2(test("button"));
    }

    /**
     * Test {@link HtmlCanvas}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("300")
    public void canvas() throws Exception {
        loadPageWithAlerts2(test("canvas"));
    }

    /**
     * Test {@link HtmlCaption}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void caption() throws Exception {
        loadPageWithAlerts2(test("caption"));
    }

    /**
     * Test {@link HtmlCenter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void center() throws Exception {
        loadPageWithAlerts2(test("center"));
    }

    /**
     * Test {@link HtmlCitation}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void cite() throws Exception {
        loadPageWithAlerts2(test("cite"));
    }

    /**
     * Test {@link HtmlCode}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void code() throws Exception {
        loadPageWithAlerts2(test("code"));
    }

    /**
     * Test {@link HtmlCommand}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void command() throws Exception {
        loadPageWithAlerts2(test("command"));
    }

    /**
     * Test {@link HtmlDataList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void datalist() throws Exception {
        loadPageWithAlerts2(test("datalist"));
    }

    /**
     * Test {@link HtmlDetails}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-16",
            IE = "0")
    public void details() throws Exception {
        loadPageWithAlerts2(test("details"));
    }

    /**
     * Test {@link HtmlDefinition}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void dfn() throws Exception {
        loadPageWithAlerts2(test("dfn"));
    }

    /**
     * Test {@link HtmlDefinitionDescription}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-56",
            FF = "0")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.IE})
    public void dd() throws Exception {
        loadPageWithAlerts2(test("dd"));
    }

    /**
     * Test {@link HtmlDeletedText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void del() throws Exception {
        loadPageWithAlerts2(test("del"));
    }

    /**
     * Test {@link HtmlDialog}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void dialog() throws Exception {
        loadPageWithAlerts2(test("dialog"));
    }

    /**
     * Test {@link HtmlDirectory}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void dir() throws Exception {
        loadPageWithAlerts2(test("dir"));
    }

    /**
     * Test {@link HtmlDivision}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void div() throws Exception {
        loadPageWithAlerts2(test("div"));
    }

    /**
     * Test {@link HtmlDefinitionList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void dl() throws Exception {
        loadPageWithAlerts2(test("dl"));
    }

    /**
     * Test {@link HtmlDefinitionTerm}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void dt() throws Exception {
        loadPageWithAlerts2(test("dt"));
    }

    /**
     * Test {@link HtmlEmbed}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "0",
            IE = "16")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void embed() throws Exception {
        loadPageWithAlerts2(test("embed"));
    }

    /**
     * Test {@link HtmlEmphasis}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void em() throws Exception {
        loadPageWithAlerts2(test("em"));
    }

    /**
     * Test {@link HtmlFieldSet}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-20")
    @BrowserRunner.NotYetImplemented
    public void fieldset() throws Exception {
        loadPageWithAlerts2(test("fieldset"));
    }

    /**
     * Test {@link HtmlFigureCaption}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void figcaption() throws Exception {
        loadPageWithAlerts2(test("figcaption"));
    }

    /**
     * Test {@link HtmlFigure}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-96")
    @BrowserRunner.NotYetImplemented
    public void figure() throws Exception {
        loadPageWithAlerts2(test("figure"));
    }

    /**
     * Test {@link HtmlFont}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void font() throws Exception {
        loadPageWithAlerts2(test("font"));
    }

    /**
     * Test {@link HtmlFooter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void footer() throws Exception {
        loadPageWithAlerts2(test("footer"));
    }

    /**
     * Test {@link HtmlForm}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void form() throws Exception {
        loadPageWithAlerts2(test("form"));
    }

    /**
     * Test {@link HtmlFrame}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void frame() throws Exception {
        loadPageWithAlerts2(test("frame"));
    }

    /**
     * Test {@link HtmlFrameSet}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void frameset() throws Exception {
        loadPageWithAlerts2(test("frameset"));
    }

    /**
     * Test {@link HtmlHeading1}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void h1() throws Exception {
        loadPageWithAlerts2(test("h1"));
    }

    /**
     * Test {@link HtmlHeading2}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void h2() throws Exception {
        loadPageWithAlerts2(test("h2"));
    }

    /**
     * Test {@link HtmlHeading3}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void h3() throws Exception {
        loadPageWithAlerts2(test("h3"));
    }

    /**
     * Test {@link HtmlHeading4}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void h4() throws Exception {
        loadPageWithAlerts2(test("h4"));
    }

    /**
     * Test {@link HtmlHeading5}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void h5() throws Exception {
        loadPageWithAlerts2(test("h5"));
    }

    /**
     * Test {@link HtmlHeading6}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void h6() throws Exception {
        loadPageWithAlerts2(test("h6"));
    }

    /**
     * Test {@link HtmlHead}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void head() throws Exception {
        loadPageWithAlerts2(test("head"));
    }

    /**
     * Test {@link HtmlHeader}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void header() throws Exception {
        loadPageWithAlerts2(test("header"));
    }

    /**
     * Test {@link HtmlHorizontalRule}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void hr() throws Exception {
        loadPageWithAlerts2(test("hr"));
    }

    /**
     * Test {@link HtmlHtml}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void html() throws Exception {
        loadPageWithAlerts2(test("html"));
    }

    /**
     * Test {@link HtmlInlineFrame}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("304")
    @BrowserRunner.NotYetImplemented
    public void iframe() throws Exception {
        loadPageWithAlerts2(test("iframe"));
    }

    /**
     * Test {@link HtmlInlineQuotation}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "25",
            CHROME = "19")
    @BrowserRunner.NotYetImplemented
    public void q() throws Exception {
        loadPageWithAlerts2(test("q"));
    }

    /**
     * Test {@link HtmlImage}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "0",
            IE = "28")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void image() throws Exception {
        loadPageWithAlerts2(test("image"));
    }

    /**
     * Test {@link HtmlImage}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "0",
            IE = "28")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void img() throws Exception {
        loadPageWithAlerts2(test("img"));
    }

    /**
     * Test {@link HtmlInsertedText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void ins() throws Exception {
        loadPageWithAlerts2(test("ins"));
    }

    /**
     * Test {@link HtmlIsIndex}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "0",
            FF52 = "143",
            IE = "145")
    @BrowserRunner.NotYetImplemented
    public void isindex() throws Exception {
        loadPageWithAlerts2(test("isindex"));
    }

    /**
     * Test {@link HtmlItalic}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void i() throws Exception {
        loadPageWithAlerts2(test("i"));
    }

    /**
     * Test {@link HtmlKeyboard}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void kbd() throws Exception {
        loadPageWithAlerts2(test("kbd"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "0",
            FF = "110")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.FF)
    public void keygen() throws Exception {
        loadPageWithAlerts2(test("keygen"));
    }

    /**
     * Test {@link HtmlLabel}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void label() throws Exception {
        loadPageWithAlerts2(test("label"));
    }

    /**
     * Test {@link HtmlLayer}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "0",
            CHROME = "-16")
    public void layer() throws Exception {
        loadPageWithAlerts2(test("layer"));
    }

    /**
     * Test {@link HtmlLegend}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-16",
            IE = "8")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void legend() throws Exception {
        loadPageWithAlerts2(test("legend"));
    }

    /**
     * Test {@link HtmlListing}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-16",
            IE = "0")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void listing() throws Exception {
        loadPageWithAlerts2(test("listing"));
    }

    /**
     * Test {@link HtmlListItem}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    @BrowserRunner.NotYetImplemented
    public void li() throws Exception {
        loadPageWithAlerts2(test("li"));
    }

    /**
     * Test {@link HtmlLink}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void link() throws Exception {
        loadPageWithAlerts2(test("link"));
    }

    /**
     * Test {@link HtmlMain}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-16",
            IE = "0")
    public void main() throws Exception {
        loadPageWithAlerts2(test("main"));
    }

    /**
     * Test {@link HtmlMap}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void map() throws Exception {
        loadPageWithAlerts2(test("map"));
    }

    /**
     * Test {@link HtmlMarquee}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF})
    public void marquee() throws Exception {
        loadPageWithAlerts2(test("marquee"));
    }

    /**
     * Test {@link HtmlMark}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void mark() throws Exception {
        loadPageWithAlerts2(test("mark"));
    }

    /**
     * Test {@link HtmlMenu}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void menu() throws Exception {
        loadPageWithAlerts2(test("menu"));
    }

    /**
     * Test {@link HtmlMenuItem}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void menuitem() throws Exception {
        loadPageWithAlerts2(test("menuitem"));
    }

    /**
     * Test {@link HtmlMeta}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void meta() throws Exception {
        loadPageWithAlerts2(test("meta"));
    }

    /**
     * Test {@link HtmlMeter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "80",
            IE = "0")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF})
    public void meter() throws Exception {
        loadPageWithAlerts2(test("meter"));
    }

    /**
     * Test {@link HtmlMultiColumn}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "0",
            FF = "-16")
    public void multicol() throws Exception {
        loadPageWithAlerts2(test("multicol"));
    }

    /**
     * Test {@link HtmlNoBreak}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void nobr() throws Exception {
        loadPageWithAlerts2(test("nobr"));
    }

    /**
     * Test {@link HtmlNav}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void nav() throws Exception {
        loadPageWithAlerts2(test("nav"));
    }

    /**
     * Test {@link HtmlNextId}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void nextid() throws Exception {
        loadPageWithAlerts2(test("nextid"));
    }

    /**
     * Test {@link HtmlNoEmbed}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void noembed() throws Exception {
        loadPageWithAlerts2(test("noembed"));
    }

    /**
     * Test {@link HtmlNoFrames}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void noframes() throws Exception {
        loadPageWithAlerts2(test("noframes"));
    }

    /**
     * Test {@link HtmlNoLayer}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void nolayer() throws Exception {
        loadPageWithAlerts2(test("nolayer"));
    }

    /**
     * Test {@link HtmlNoScript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.CHROME)
    public void noscript() throws Exception {
        loadPageWithAlerts2(test("noscript"));
    }

    /**
     * Test {@link HtmlObject}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "0",
            CHROME = "300")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.CHROME)
    public void object() throws Exception {
        loadPageWithAlerts2(test("object"));
    }

    /**
     * Test {@link HtmlOrderedList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void ol() throws Exception {
        loadPageWithAlerts2(test("ol"));
    }

    /**
     * Test {@link HtmlOptionGroup}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-16",
            IE = "0")
    public void optgroup() throws Exception {
        loadPageWithAlerts2(test("optgroup"));
    }

    /**
     * Test {@link HtmlOption}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-16",
            IE = "0")
    public void option() throws Exception {
        loadPageWithAlerts2(test("option"));
    }

    /**
     * Test {@link HtmlOutput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void output() throws Exception {
        loadPageWithAlerts2(test("output"));
    }

    /**
     * Test {@link HtmlParagraph}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void p() throws Exception {
        loadPageWithAlerts2(test("p"));
    }

    /**
     * Test {@link HtmlParameter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void param() throws Exception {
        loadPageWithAlerts2(test("param"));
    }

    /**
     * Test {@link HtmlPlainText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void plaintext() throws Exception {
        loadPageWithAlerts2(test("plaintext"));
    }

    /**
     * Test {@link HtmlPreformattedText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void pre() throws Exception {
        loadPageWithAlerts2(test("pre"));
    }

    /**
     * Test {@link HtmlProgress}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "160",
            FF = "162",
            IE = "280")
    @BrowserRunner.NotYetImplemented
    public void progress() throws Exception {
        loadPageWithAlerts2(test("progress"));
    }

    /**
     * Test {@link HtmlRuby}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void ruby() throws Exception {
        loadPageWithAlerts2(test("ruby"));
    }

    /**
     * Test {@link HtmlRp}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void rp() throws Exception {
        loadPageWithAlerts2(test("rp"));
    }

    /**
     * Test {@link HtmlRt}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.CHROME)
    public void rt() throws Exception {
        loadPageWithAlerts2(test("rt"));
    }

    /**
     * Test {@link HtmlS}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void s() throws Exception {
        loadPageWithAlerts2(test("s"));
    }

    /**
     * Test {@link HtmlSample}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void samp() throws Exception {
        loadPageWithAlerts2(test("samp"));
    }

    /**
     * Test {@link HtmlScript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void script() throws Exception {
        loadPageWithAlerts2(test("script"));
    }

    /**
     * Test {@link HtmlSection}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void section() throws Exception {
        loadPageWithAlerts2(test("section"));
    }

    /**
     * Test {@link HtmlSelect}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "24",
            FF = "33",
            IE = "25")
    @BrowserRunner.NotYetImplemented
    public void select() throws Exception {
        loadPageWithAlerts2(test("select"));
    }

    /**
     * Test {@link HtmlSmall}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void small() throws Exception {
        loadPageWithAlerts2(test("small"));
    }

    /**
     * Test {@link HtmlSource}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void source() throws Exception {
        loadPageWithAlerts2(test("source"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void spacer() throws Exception {
        loadPageWithAlerts2(test("spacer"));
    }

    /**
     * Test {@link HtmlSpan}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void span() throws Exception {
        loadPageWithAlerts2(test("span"));
    }

    /**
     * Test {@link HtmlStrike}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void strike() throws Exception {
        loadPageWithAlerts2(test("strike"));
    }

    /**
     * Test {@link HtmlStrong}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void strong() throws Exception {
        loadPageWithAlerts2(test("strong"));
    }

    /**
     * Test {@link HtmlStyle}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void style() throws Exception {
        loadPageWithAlerts2(test("style"));
    }

    /**
     * Test {@link HtmlSubscript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void sub() throws Exception {
        loadPageWithAlerts2(test("sub"));
    }

    /**
     * Test {@link HtmlSummary}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-16",
            IE = "0")
    public void summary() throws Exception {
        loadPageWithAlerts2(test("summary"));
    }

    /**
     * Test {@link HtmlSuperscript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void sup() throws Exception {
        loadPageWithAlerts2(test("sup"));
    }

    /**
     * Test {@link HtmlSvg}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("NaN")
    public void svg() throws Exception {
        loadPageWithAlerts2(test("svg"));
    }

    /**
     * Test {@link HtmlTable}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "0",
            IE = "2")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void table() throws Exception {
        loadPageWithAlerts2(test("table"));
    }

    /**
     * Test {@link HtmlTableColumn}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void col() throws Exception {
        loadPageWithAlerts2(test("col"));
    }

    /**
     * Test {@link HtmlTableColumnGroup}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void colgroup() throws Exception {
        loadPageWithAlerts2(test("colgroup"));
    }

    /**
     * Test {@link HtmlTableBody}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void tbody() throws Exception {
        loadPageWithAlerts2(test("tbody"));
    }

    /**
     * Test {@link HtmlTableDataCell}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void td() throws Exception {
        loadPageWithAlerts2(test("td"));
    }

    /**
     * Test {@link HtmlTableHeaderCell}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void th() throws Exception {
        loadPageWithAlerts2(test("th"));
    }

    /**
     * Test {@link HtmlTableRow}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void tr() throws Exception {
        loadPageWithAlerts2(test("tr"));
    }

    /**
     * Test {@link HtmlTrack}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void track() throws Exception {
        loadPageWithAlerts2(test("track"));
    }

    /**
     * Test {@link HtmlTextArea}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "183",
            CHROME = "161")
    @BrowserRunner.NotYetImplemented
    public void textarea() throws Exception {
        loadPageWithAlerts2(test("textarea"));
    }

    /**
     * Test {@link HtmlTableFooter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void tfoot() throws Exception {
        loadPageWithAlerts2(test("tfoot"));
    }

    /**
     * Test {@link HtmlTableHeader}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("null")
    public void thead() throws Exception {
        loadPageWithAlerts2(test("thead"));
    }

    /**
     * Test {@link HtmlTeletype}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void tt() throws Exception {
        loadPageWithAlerts2(test("tt"));
    }

    /**
     * Test {@link HtmlTime}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void time() throws Exception {
        loadPageWithAlerts2(test("time"));
    }

    /**
     * Test {@link HtmlTitle}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void title() throws Exception {
        loadPageWithAlerts2(test("title"));
    }

    /**
     * Test {@link HtmlUnderlined}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void u() throws Exception {
        loadPageWithAlerts2(test("u"));
    }

    /**
     * Test {@link HtmlUnorderedList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-16")
    public void ul() throws Exception {
        loadPageWithAlerts2(test("ul"));
    }

    /**
     * Test {@link HtmlVariable}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void var() throws Exception {
        loadPageWithAlerts2(test("var"));
    }

    /**
     * Test {@link HtmlVideo}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("300")
    @BrowserRunner.NotYetImplemented
    public void video() throws Exception {
        loadPageWithAlerts2(test("video"));
    }

    /**
     * Test {@link HtmlWordBreak}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void wbr() throws Exception {
        loadPageWithAlerts2(test("wbr"));
    }

    /**
     * Test {@link HtmlExample}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-16",
            IE = "40")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void xmp() throws Exception {
        loadPageWithAlerts2(test("xmp"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "173",
            FF = "143",
            IE = "145")
    @BrowserRunner.NotYetImplemented
    public void input() throws Exception {
        loadPageWithAlerts2(test("input"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "22",
            CHROME = "16",
            FF52 = "24")
    @BrowserRunner.NotYetImplemented
    public void inputButton() throws Exception {
        loadPageWithAlerts2(testInput("button"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("13")
    public void inputCheckbox() throws Exception {
        loadPageWithAlerts2(testInput("checkbox"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "253",
            FF52 = "237",
            FF60 = "235",
            IE = "262")
    @BrowserRunner.NotYetImplemented
    public void inputFile() throws Exception {
        loadPageWithAlerts2(testInput("file"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void inputHidden() throws Exception {
        loadPageWithAlerts2(testInput("hidden"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "173",
            FF = "143",
            IE = "147")
    @BrowserRunner.NotYetImplemented
    public void inputPassword() throws Exception {
        loadPageWithAlerts2(testInput("password"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("13")
    public void inputRadio() throws Exception {
        loadPageWithAlerts2(testInput("radio"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "51",
            FF52 = "56",
            FF60 = "54",
            IE = "102")
    @BrowserRunner.NotYetImplemented
    public void inputReset() throws Exception {
        loadPageWithAlerts2(testInput("reset"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "175",
            FF = "143",
            IE = "145")
    @BrowserRunner.NotYetImplemented
    public void inputSelect() throws Exception {
        loadPageWithAlerts2(testInput("select"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "58",
            FF52 = "102",
            FF60 = "100",
            IE = "116")
    @BrowserRunner.NotYetImplemented
    public void inputSubmit() throws Exception {
        loadPageWithAlerts2(testInput("submit"));
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "173",
            FF = "143",
            IE = "145")
    @BrowserRunner.NotYetImplemented
    public void inputText() throws Exception {
        loadPageWithAlerts2(testInput("text"));
    }

    /**
     * Test {@link HtmlData}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void data() throws Exception {
        loadPageWithAlerts2(test("data"));
    }

    /**
     * Test {@link HtmlContent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void content() throws Exception {
        loadPageWithAlerts2(test("content"));
    }

    /**
     * Test {@link HtmlPicture}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void picture() throws Exception {
        loadPageWithAlerts2(test("picture"));
    }

    /**
     * Test {@link HtmlTemplate}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void template() throws Exception {
        loadPageWithAlerts2(test("template"));
    }

    /**
     * Test {@link HtmlSlot}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("0")
    public void slot() throws Exception {
        loadPageWithAlerts2(test("slot"));
    }

}
