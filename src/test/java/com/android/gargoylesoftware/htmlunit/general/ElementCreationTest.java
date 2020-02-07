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

import com.android.gargoylesoftware.htmlunit.html.HtmlPageTest;

/**
 * Tests the result of <code>document.createElement()</code>.
 *
 * @author Ahmed Ashour
 */
@RunWith(BrowserRunner.class)
public class ElementCreationTest extends WebDriverTestCase {

    private void test(final String tagName) throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><script>\n"
            + "  function test() {\n"
            + "    try {\n"
            + "      alert(document.createElement('" + tagName + "'));\n"
            + "    } catch(e) {alert('exception')}\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * Test {@link HtmlAbbreviated}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void abbr() throws Exception {
        test("abbr");
    }

    /**
     * Test {@link HtmlAcronym}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void acronym() throws Exception {
        test("acronym");
    }

    /**
     * Test {@link HtmlAddress}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLBlockElement]")
    public void address() throws Exception {
        test("address");
    }

    /**
     * Test {@link HtmlAnchor}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("")
    public void a() throws Exception {
        test("a");
    }

    /**
     * Test {@link HtmlApplet}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLAppletElement]",
            CHROME = "[object HTMLUnknownElement]",
            FF60 = "[object HTMLUnknownElement]")
    public void applet() throws Exception {
        test("applet");
    }

    /**
     * Test {@link HtmlArea}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("")
    public void area() throws Exception {
        test("area");
    }

    /**
     * Test {@link HtmlArticle}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void article() throws Exception {
        test("article");
    }

    /**
     * Test {@link HtmlAside}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void aside() throws Exception {
        test("aside");
    }

    /**
     * Test {@link HtmlAudio}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLAudioElement]")
    public void audio() throws Exception {
        test("audio");
    }

    /**
     * Test {@link HtmlBackgroundSound}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            IE = "[object HTMLBGSoundElement]")
    public void bgsound() throws Exception {
        test("bgsound");
    }

    /**
     * Test {@link HtmlBase}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLBaseElement]")
    public void base() throws Exception {
        test("base");
    }

    /**
     * Test {@link HtmlBaseFont}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLBaseFontElement]")
    public void basefont() throws Exception {
        test("basefont");
    }

    /**
     * Test {@link HtmlBidirectionalIsolation}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            CHROME = "[object HTMLElement]")
    public void bdi() throws Exception {
        test("bdi");
    }

    /**
     * Test {@link HtmlBidirectionalOverride}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void bdo() throws Exception {
        test("bdo");
    }

    /**
     * Test {@link HtmlBig}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void big() throws Exception {
        test("big");
    }

    /**
     * Test {@link HtmlBlink}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            IE = "[object HTMLPhraseElement]")
    public void blink() throws Exception {
        test("blink");
    }

    /**
     * Test {@link HtmlBlockQuote}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLQuoteElement]",
            IE = "[object HTMLBlockElement]")
    public void blockquote() throws Exception {
        test("blockquote");
    }

    /**
     * Test {@link HtmlBody}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLBodyElement]")
    public void body() throws Exception {
        test("body");
    }

    /**
     * Test {@link HtmlBold}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void b() throws Exception {
        test("b");
    }

    /**
     * Test {@link HtmlBreak}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLBRElement]")
    public void br() throws Exception {
        test("br");
    }

    /**
     * Test {@link HtmlButton}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLButtonElement]")
    public void button() throws Exception {
        test("button");
    }

    /**
     * Test {@link HtmlCanvas}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLCanvasElement]")
    public void canvas() throws Exception {
        test("canvas");
    }

    /**
     * Test {@link HtmlCaption}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTableCaptionElement]")
    public void caption() throws Exception {
        test("caption");
    }

    /**
     * Test {@link HtmlCenter}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLBlockElement]")
    public void center() throws Exception {
        test("center");
    }

    /**
     * Test {@link HtmlCitation}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void cite() throws Exception {
        test("cite");
    }

    /**
     * Test {@link HtmlCode}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void code() throws Exception {
        test("code");
    }

    /**
     * Test {@link HtmlDataList}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLDataListElement]")
    public void datalist() throws Exception {
        test("datalist");
    }

    /**
     * Test {@link HtmlDefinition}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void dfn() throws Exception {
        test("dfn");
    }

    /**
     * Test {@link HtmlDefinitionDescription}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLDDElement]")
    public void dd() throws Exception {
        test("dd");
    }

    /**
     * Test {@link HtmlDefinitionList}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLDListElement]")
    public void dl() throws Exception {
        test("dl");
    }

    /**
     * Test {@link HtmlDefinitionTerm}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLDTElement]")
    public void dt() throws Exception {
        test("dt");
    }

    /**
     * Test {@link HtmlDeletedText}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLModElement]")
    public void del() throws Exception {
        test("del");
    }

    /**
     * Test {@link HtmlDirectory}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLDirectoryElement]")
    public void dir() throws Exception {
        test("dir");
    }

    /**
     * Test {@link HtmlDivision}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLDivElement]")
    public void div() throws Exception {
        test("div");
    }

    /**
     * Test {@link HtmlEmbed}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLEmbedElement]")
    public void embed() throws Exception {
        test("embed");
    }

    /**
     * Test {@link HtmlEmphasis}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void em() throws Exception {
        test("em");
    }

    /**
     * Test {@link HtmlExample}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLPreElement]",
            IE = "[object HTMLBlockElement]")
    public void xmp() throws Exception {
        test("xmp");
    }

    /**
     * Test {@link HtmlFieldSet}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLFieldSetElement]")
    public void fieldset() throws Exception {
        test("fieldset");
    }

    /**
     * Test {@link HtmlFigure}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void figure() throws Exception {
        test("figure");
    }

    /**
     * Test {@link HtmlFigureCaption}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void figcaption() throws Exception {
        test("figcaption");
    }

    /**
     * Test {@link HtmlFont}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLFontElement]")
    public void font() throws Exception {
        test("font");
    }

    /**
     * Test {@link HtmlFooter}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void footer() throws Exception {
        test("footer");
    }

    /**
     * Test {@link HtmlForm}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLFormElement]")
    public void form() throws Exception {
        test("form");
    }

    /**
     * Test {@link HtmlFrame}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLFrameElement]")
    public void frame() throws Exception {
        test("frame");
    }

    /**
     * Test {@link HtmlFrameSet}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLFrameSetElement]")
    public void frameset() throws Exception {
        test("frameset");
    }

    /**
     * Test {@link HtmlHead}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLHeadElement]")
    public void head() throws Exception {
        test("head");
    }

    /**
     * Test {@link HtmlHeader}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void header() throws Exception {
        test("header");
    }

    /**
     * Test {@link HtmlHeading1}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLHeadingElement]")
    public void h1() throws Exception {
        test("h1");
    }

    /**
     * Test {@link HtmlHeading2}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLHeadingElement]")
    public void h2() throws Exception {
        test("h2");
    }

    /**
     * Test {@link HtmlHeading3}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLHeadingElement]")
    public void h3() throws Exception {
        test("h3");
    }

    /**
     * Test {@link HtmlHeading4}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLHeadingElement]")
    public void h4() throws Exception {
        test("h4");
    }

    /**
     * Test {@link HtmlHeading5}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLHeadingElement]")
    public void h5() throws Exception {
        test("h5");
    }

    /**
     * Test {@link HtmlHeading6}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLHeadingElement]")
    public void h6() throws Exception {
        test("h6");
    }

    /**
     * Test {@link HtmlHorizontalRule}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLHRElement]")
    public void hr() throws Exception {
        test("hr");
    }

    /**
     * Test {@link HtmlHtml}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLHtmlElement]")
    public void html() throws Exception {
        test("html");
    }

    /**
     * Test {@link HtmlImage}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLImageElement]")
    public void img() throws Exception {
        test("img");
    }

    /**
     * Test {@link HtmlImage}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLImageElement]",
            CHROME = "[object HTMLUnknownElement]",
            FF = "[object HTMLElement]")
    public void image() throws Exception {
        test("image");
    }

    /**
     * Test {@link HtmlInlineFrame}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLIFrameElement]")
    public void iframe() throws Exception {
        test("iframe");
    }

    /**
     * Test {@link HtmlInlineQuotation}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLQuoteElement]")
    public void q() throws Exception {
        test("q");
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLInputElement]")
    public void input() throws Exception {
        test("input");
    }

    /**
     * Test {@link HtmlInsertedText}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLModElement]")
    public void ins() throws Exception {
        test("ins");
    }

    /**
     * Test {@link HtmlIsIndex}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            IE = "[object HTMLIsIndexElement]")
    public void isindex() throws Exception {
        test("isindex");
    }

    /**
     * Test {@link HtmlItalic}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void i() throws Exception {
        test("i");
    }

    /**
     * Test {@link HtmlKeyboard}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void kbd() throws Exception {
        test("kbd");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLSpanElement]",
            CHROME = "[object HTMLUnknownElement]",
            IE = "[object HTMLBlockElement]")
    public void keygen() throws Exception {
        test("keygen");
    }

    /**
     * Test {@link HtmlLabel}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLLabelElement]")
    public void label() throws Exception {
        test("label");
    }

    /**
     * Test {@link HtmlLegend}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLLegendElement]")
    public void legend() throws Exception {
        test("legend");
    }

    /**
     * Test {@link HtmlLink}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLLinkElement]")
    public void link() throws Exception {
        test("link");
    }

    /**
     * Test {@link HtmlListing}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLPreElement]",
            IE = "[object HTMLBlockElement]")
    public void listing() throws Exception {
        test("listing");
    }

    /**
     * Test {@link HtmlListItem}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLLIElement]")
    public void li() throws Exception {
        test("li");
    }

    /**
     * Test {@link HtmlMain}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLUnknownElement]")
    public void main() throws Exception {
        test("main");
    }

    /**
     * Test {@link HtmlMap}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLMapElement]")
    public void map() throws Exception {
        test("map");
    }

    /**
     * Test {@link HtmlMark}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void mark() throws Exception {
        test("mark");
    }

    /**
     * Test {@link HtmlMarquee}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLMarqueeElement]",
            FF = "[object HTMLDivElement]")
    public void marquee() throws Exception {
        test("marquee");
    }

    /**
     * Test {@link HtmlMenu}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLMenuElement]")
    public void menu() throws Exception {
        test("menu");
    }

    /**
     * Test {@link HtmlMenuItem}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            FF = "[object HTMLMenuItemElement]")
    public void menuitem() throws Exception {
        test("menuitem");
    }

    /**
     * Test {@link HtmlMeta}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLMetaElement]")
    public void meta() throws Exception {
        test("meta");
    }

    /**
     * Test {@link HtmlMeter}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLMeterElement]",
            IE = "[object HTMLUnknownElement]")
    public void meter() throws Exception {
        test("meter");
    }

    /**
     * Test {@link HtmlMultiColumn}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void multicol() throws Exception {
        test("multicol");
    }

    /**
     * Test {@link HtmlNav}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void nav() throws Exception {
        test("nav");
    }

    /**
     * Test {@link HtmlNextId}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            IE = "[object HTMLNextIdElement]")
    public void nextid() throws Exception {
        test("nextid");
    }

    /**
     * Test {@link HtmlNoBreak}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void nobr() throws Exception {
        test("nobr");
    }

    /**
     * Test {@link HtmlNoEmbed}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void noembed() throws Exception {
        test("noembed");
    }

    /**
     * Test {@link HtmlNoFrames}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void noframes() throws Exception {
        test("noframes");
    }

    /**
     * Test {@link HtmlNoScript}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void noscript() throws Exception {
        test("noscript");
    }

    /**
     * Test {@link HtmlObject}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLObjectElement]")
    public void object() throws Exception {
        test("object");
    }

    /**
     * Test {@link HtmlOption}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLOptionElement]")
    public void option() throws Exception {
        test("option");
    }

    /**
     * Test {@link HtmlOptionGroup}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLOptGroupElement]")
    public void optgroup() throws Exception {
        test("optgroup");
    }

    /**
     * Test {@link HtmlOrderedList}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLOListElement]")
    public void ol() throws Exception {
        test("ol");
    }

    /**
     * Test {@link HtmlOutput}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLOutputElement]",
            IE = "[object HTMLUnknownElement]")
    public void output() throws Exception {
        test("output");
    }

    /**
     * Test {@link HtmlParagraph}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLParagraphElement]")
    public void p() throws Exception {
        test("p");
    }

    /**
     * Test {@link HtmlParameter}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLParamElement]")
    public void param() throws Exception {
        test("param");
    }

    /**
     * Test {@link HtmlPlainText}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLBlockElement]")
    public void plaintext() throws Exception {
        test("plaintext");
    }

    /**
     * Test {@link HtmlPreformattedText}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLPreElement]")
    public void pre() throws Exception {
        test("pre");
    }

    /**
     * Test {@link HtmlProgress}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLProgressElement]")
    public void progress() throws Exception {
        test("progress");
    }

    /**
     * Test {@link HtmlRp}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void rp() throws Exception {
        test("rp");
    }

    /**
     * Test {@link HtmlRt}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void rt() throws Exception {
        test("rt");
    }

    /**
     * Test {@link HtmlRuby}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void ruby() throws Exception {
        test("ruby");
    }

    /**
     * Test {@link HtmlS}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void s() throws Exception {
        test("s");
    }

    /**
     * Test {@link HtmlSample}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void samp() throws Exception {
        test("samp");
    }

    /**
     * Test {@link HtmlScript}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLScriptElement]")
    public void script() throws Exception {
        test("script");
    }

    /**
     * Test {@link HtmlSection}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void section() throws Exception {
        test("section");
    }

    /**
     * Test {@link HtmlSelect}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLSelectElement]")
    public void select() throws Exception {
        test("select");
    }

    /**
     * Test {@link HtmlSmall}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void small() throws Exception {
        test("small");
    }

    /**
     * Test {@link HtmlSource}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLSourceElement]")
    public void source() throws Exception {
        test("source");
    }

    /**
     * Test {@link HtmlSpan}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLSpanElement]")
    public void span() throws Exception {
        test("span");
    }

    /**
     * Test {@link HtmlStrike}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void strike() throws Exception {
        test("strike");
    }

    /**
     * Test {@link HtmlStrong}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void strong() throws Exception {
        test("strong");
    }

    /**
     * Test {@link HtmlStyle}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLStyleElement]")
    public void style() throws Exception {
        test("style");
    }

    /**
     * Test {@link HtmlSubscript}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void sub() throws Exception {
        test("sub");
    }

    /**
     * Test {@link HtmlSummary}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLUnknownElement]")
    public void summary() throws Exception {
        test("summary");
    }

    /**
     * Test {@link HtmlSuperscript}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void sup() throws Exception {
        test("sup");
    }

    /**
     * Test {@link HtmlSuperscript}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void svg() throws Exception {
        test("svg");
    }

    /**
     * Test {@link HtmlTable}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTableElement]")
    public void table() throws Exception {
        test("table");
    }

    /**
     * Test {@link HtmlTableBody}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTableSectionElement]")
    public void tbody() throws Exception {
        test("tbody");
    }

    /**
     * Test {@link HtmlTableColumn}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTableColElement]")
    public void col() throws Exception {
        test("col");
    }

    /**
     * Test {@link HtmlTableColumnGroup}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTableColElement]")
    public void colgroup() throws Exception {
        test("colgroup");
    }

    /**
     * Test {@link HtmlTableDataCell}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLTableCellElement]",
            IE = "[object HTMLTableDataCellElement]")
    public void td() throws Exception {
        test("td");
    }

    /**
     * Test {@link HtmlTableFooter}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTableSectionElement]")
    public void tfoot() throws Exception {
        test("tfoot");
    }

    /**
     * Test {@link HtmlTableHeader}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTableSectionElement]")
    public void thead() throws Exception {
        test("thead");
    }

    /**
     * Test {@link HtmlTableHeaderCell}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLTableCellElement]",
            IE = "[object HTMLTableHeaderCellElement]")
    public void th() throws Exception {
        test("th");
    }

    /**
     * Test {@link HtmlTableRow}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTableRowElement]")
    public void tr() throws Exception {
        test("tr");
    }

    /**
     * Test {@link HtmlTrack}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTrackElement]")
    public void track() throws Exception {
        test("track");
    }

    /**
     * Test {@link HtmlTeletype}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void tt() throws Exception {
        test("tt");
    }

    /**
     * Test {@link HtmlTextArea}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTextAreaElement]")
    public void textarea() throws Exception {
        test("textarea");
    }

    /**
     * Test {@link HtmlTime}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLTimeElement]",
            IE = "[object HTMLUnknownElement]")
    public void time() throws Exception {
        test("time");
    }

    /**
     * Test {@link HtmlTitle}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLTitleElement]")
    public void title() throws Exception {
        test("title");
    }

    /**
     * Test {@link HtmlUnderlined}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void u() throws Exception {
        test("u");
    }

    /**
     * Test {@link HtmlUnorderedList}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUListElement]")
    public void ul() throws Exception {
        test("ul");
    }

    /**
     * Test {@link HtmlVariable}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLPhraseElement]")
    public void var() throws Exception {
        test("var");
    }

    /**
     * Test {@link HtmlVideo}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLVideoElement]")
    public void video() throws Exception {
        test("video");
    }

    /**
     * Test {@link HtmlWordBreak}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLElement]")
    public void wbr() throws Exception {
        test("wbr");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void attribute() throws Exception {
        test("attribute");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void clientInformation() throws Exception {
        test("clientInformation");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void clipboardData() throws Exception {
        test("clipboardData");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void command() throws Exception {
        test("command");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void comment() throws Exception {
        test("comment");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void currentStyle() throws Exception {
        test("currentStyle");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void custom() throws Exception {
        test("custom");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void datagrid() throws Exception {
        test("datagrid");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void datatemplate() throws Exception {
        test("datatemplate");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void dataTransfer() throws Exception {
        test("dataTransfer");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void defaults() throws Exception {
        test("defaults");
    }

    /**
     * Test {@link HtmlDetails}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLDetailsElement]",
            IE = "[object HTMLUnknownElement]")
    public void details() throws Exception {
        test("details");
    }

    /**
     * Test {@link HtmlDialog}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            CHROME = "[object HTMLDialogElement]")
    public void dialog() throws Exception {
        test("dialog");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void document() throws Exception {
        test("document");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void DocumentCompatibleInfo() throws Exception {
        test("DocumentCompatibleInfo");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void event() throws Exception {
        test("event");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLUnknownElement]")
    public void event_source() throws Exception {
        test("event-source");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void external() throws Exception {
        test("external");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void history() throws Exception {
        test("history");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void hn() throws Exception {
        test("hn");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void ilayer() throws Exception {
        test("ilayer");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void implementation() throws Exception {
        test("implementation");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void IMPORT() throws Exception {
        test("IMPORT");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            CHROME = "[object HTMLElement]")
    public void layer() throws Exception {
        test("layer");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void location() throws Exception {
        test("location");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void namespace() throws Exception {
        test("namespace");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void navigator() throws Exception {
        test("navigator");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void nest() throws Exception {
        test("nest");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            CHROME = "[object HTMLElement]")
    public void noLayer() throws Exception {
        test("nolayer");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void page() throws Exception {
        test("page");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void popup() throws Exception {
        test("popup");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLUnknownElement]")
    public void rb() throws Exception {
        test("rb");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void rbc() throws Exception {
        test("rbc");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLElement]",
            IE = "[object HTMLUnknownElement]")
    public void rtc() throws Exception {
        test("rtc");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void rule() throws Exception {
        test("rule");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void runtimeStyle() throws Exception {
        test("runtimeStyle");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void screen() throws Exception {
        test("screen");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void selection() throws Exception {
        test("selection");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void spacer() throws Exception {
        test("spacer");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void Storage() throws Exception {
        test("Storage");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void styleSheet() throws Exception {
        test("styleSheet");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void TextNode() throws Exception {
        test("TextNode");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void TextRange() throws Exception {
        test("TextRange");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void TextRectangle() throws Exception {
        test("TextRectangle");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void userProfile() throws Exception {
        test("userProfile");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void window() throws Exception {
        test("window");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void XDomainRequest() throws Exception {
        test("XDomainRequest");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void xml() throws Exception {
        test("xml");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("[object HTMLUnknownElement]")
    public void XMLHttpRequest() throws Exception {
        test("XMLHttpRequest");
    }

    /**
     * Test {@link HtmlData}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLDataElement]",
            IE = "[object HTMLUnknownElement]")
    public void data() throws Exception {
        test("data");
    }

    /**
     * Test {@link HtmlContent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            CHROME = "[object HTMLContentElement]")
    public void content() throws Exception {
        test("content");
    }

    /**
     * Test {@link HtmlPicture}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLPictureElement]",
            IE = "[object HTMLUnknownElement]")
    public void picture() throws Exception {
        test("picture");
    }

    /**
     * Test {@link HtmlPicture}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLTemplateElement]",
            IE = "[object HTMLUnknownElement]")
    public void template() throws Exception {
        test("template");
    }

    /**
     * Test {@link HtmlSlot}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "[object HTMLUnknownElement]",
            CHROME = "[object HTMLSlotElement]")
    public void slot() throws Exception {
        test("slot");
    }
}
