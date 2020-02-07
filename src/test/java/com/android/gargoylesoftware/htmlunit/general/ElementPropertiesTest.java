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

import static java.nio.charset.StandardCharsets.ISO_8859_1;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import com.android.gargoylesoftware.htmlunit.BrowserRunner;
import com.android.gargoylesoftware.htmlunit.BrowserVersion;
import com.android.gargoylesoftware.htmlunit.WebDriverTestCase;
import com.android.gargoylesoftware.htmlunit.html.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.Element;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLDocument;
import org.apache.android.commons.io.FileUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.chart.util.SortOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.android.gargoylesoftware.htmlunit.html.HtmlPageTest;

/**
 * Tests all properties of an object.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@RunWith(BrowserRunner.class)
public class ElementPropertiesTest extends WebDriverTestCase {

    private static DefaultCategoryDataset DATASET_;
    private static StringBuilder HTML_ = new StringBuilder();
    private static BrowserVersion BROWSER_VERSION_;
    private static int IMPLEMENTED_COUNT_;
    private static int TOTAL_COUNT_;

    private void test(final String tagName) throws Exception {
        testString("document.createElement('" + tagName + "'), unknown");
    }

    private void testString(final String string) throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
                + "<html><head><script>\n"
                + "  function test(event) {\n"
                + "    var xmlDocument = document.implementation.createDocument('', '', null);\n"
                + "    var element = xmlDocument.createElement('wakwak');\n"
                + "    var unknown = document.createElement('harhar');\n"
                + "    var div = document.createElement('div');\n"
                + "    var svg = document.getElementById('mySvg');\n"
                + "    try{\n"
                + "      process(" + string + ");\n"
                + "    } catch (e) {\n"
                + "      alert('exception');\n"
                + "      return;"
                + "    }\n"
                + "  }\n"
                + "\n"
                + "  /*\n"
                + "   * Alerts all properties (including functions) of the specified object.\n"
                + "   *\n"
                + "   * @param object the object to write the property of\n"
                + "   * @param parent the direct parent of the object (or child of that parent), can be null.\n"
                + "   *        The parent is used to exclude any inherited properties.\n"
                + "   */\n"
                + "  function process(object, parent) {\n"
                + "    var all = [];\n"
                + "    for (var property in object) {\n"
                + "      try {\n"
                + "        if (parent == null || !(property in parent)) {\n"
                + "          if (typeof object[property] == 'function')\n"
                + "            all.push(property + '()');\n"
                + "          else\n"
                + "            all.push(property);\n"
                + "        }\n"
                + "      } catch(e) {\n"
                + "        try{\n"
                + "          if (typeof object[property] == 'function')\n"
                + "            all.push(property + '()');\n"
                + "          else\n"
                + "            all.push(property);\n"
                + "        } catch (e) {\n"
                + "          all.push(property.toString());\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "    all.sort(sortFunction);\n"
                + "    if (all.length == 0) { all = '-' };\n"
                + "    alert(all);\n"
                + "  }\n"
                + "  function sortFunction(s1, s2) {\n"
                + "    return s1.toLowerCase() > s2.toLowerCase() ? 1 : -1;\n"
                + "  }\n"
                + "</script></head>\n"
                + "<body onload='test(event)'>\n"
                + "  <svg xmlns='http://www.w3.org/2000/svg' version='1.1'>\n"
                + "    <invalid id='mySvg'/>\n"
                + "  </svg>\n"
                + "</body></html>";

        if (BROWSER_VERSION_ == null) {
            BROWSER_VERSION_ = getBrowserVersion();
        }
        assertTrue(getExpectedAlerts().length == 1);

        final String expected = getExpectedAlerts()[0];
        String actual;

        ComparisonFailure failure = null;
        try {
            loadPageWithAlerts2(html);
            actual = expected;
        }
        catch (final ComparisonFailure c) {
            failure = c;
            actual = c.getActual();
            actual = actual.substring(1, actual.length() - 1);
        }

        final List<String> realProperties = stringAsArray(expected);
        final List<String> simulatedProperties = stringAsArray(actual);

        final List<String> erroredProperties = new ArrayList<>(simulatedProperties);
        erroredProperties.removeAll(realProperties);

        final List<String> implementedProperties = new ArrayList<>(simulatedProperties);
        implementedProperties.retainAll(realProperties);

        IMPLEMENTED_COUNT_ += implementedProperties.size();
        TOTAL_COUNT_ += realProperties.size();

        String methodName = null;
        for (final StackTraceElement e : new Exception().getStackTrace()) {
            if (e.getClassName().equals(getClass().getName())) {
                methodName = e.getMethodName();
            }
            else {
                break;
            }
        }

        htmlDetails(methodName, HTML_, realProperties, implementedProperties, erroredProperties);

        DATASET_.addValue(implementedProperties.size(), "Implemented", methodName);
        DATASET_.addValue(realProperties.size(),
            getBrowserVersion().getNickname().replace("FF", "Firefox ").replace("IE", "Internet Explorer "),
            methodName);
        DATASET_.addValue(erroredProperties.size(), "Should not be implemented", methodName);

        if (failure != null) {
            throw failure;
        }
    }

    private static List<String> stringAsArray(final String string) {
        if (string.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(string.split(","));
    }

    /**
     * Resets browser-specific values.
     */
    @BeforeClass
    public static void beforeClass() {
        DATASET_ = new DefaultCategoryDataset();
        HTML_.setLength(0);
        BROWSER_VERSION_ = null;
        IMPLEMENTED_COUNT_ = 0;
        TOTAL_COUNT_ = 0;
    }

    /**
     * Saves HTML and PNG files.
     *
     * @throws IOException if an error occurs
     */
    @AfterClass
    public static void saveAll() throws IOException {
        saveChart();

        FileUtils.writeStringToFile(new File(getTargetDirectory()
                + "/properties-" + BROWSER_VERSION_.getNickname() + ".html"),
                htmlHeader()
                    .append(overview())
                    .append(htmlDetailsHeader())
                    .append(HTML_)
                    .append(htmlDetailsFooter())
                    .append(htmlFooter()).toString(), ISO_8859_1);
    }

    private static void saveChart() throws IOException {
        final JFreeChart chart = ChartFactory.createBarChart(
            "HtmlUnit implemented properties and methods for " + BROWSER_VERSION_.getNickname(), "Objects",
            "Count", DATASET_, PlotOrientation.HORIZONTAL, true, true, false);
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        final NumberAxis axis = (NumberAxis) plot.getRangeAxis();
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final LayeredBarRenderer renderer = new LayeredBarRenderer();
        plot.setRenderer(renderer);
        plot.setRowRenderingOrder(SortOrder.DESCENDING);
        renderer.setSeriesPaint(0, new GradientPaint(0, 0, Color.green, 0, 0, new Color(0, 64, 0)));
        renderer.setSeriesPaint(1, new GradientPaint(0, 0, Color.blue, 0, 0, new Color(0, 0, 64)));
        renderer.setSeriesPaint(2, new GradientPaint(0, 0, Color.red, 0, 0, new Color(64, 0, 0)));
        ImageIO.write(chart.createBufferedImage(1200, 2400), "png",
            new File(getTargetDirectory() + "/properties-" + BROWSER_VERSION_.getNickname() + ".png"));
    }

    /**
     * Returns the 'target' directory.
     * @return the 'target' directory
     */
    public static String getTargetDirectory() {
        final String dirName = "./target";
        final File dir = new File(dirName);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new RuntimeException("Could not create artifacts directory");
            }
        }
        return dirName;
    }

    private static StringBuilder htmlHeader() {
        final StringBuilder html = new StringBuilder();
        html.append("<html><head>\n");
        html.append("<style type=\"text/css\">\n");
        html.append("table.bottomBorder { border-collapse:collapse; }\n");
        html.append("table.bottomBorder td, table.bottomBorder th { "
                            + "border-bottom:1px dotted black;padding:5px; }\n");
        html.append("table.bottomBorder td.numeric { text-align:right; }\n");
        html.append("</style>\n");
        html.append("</head><body>\n");

        html.append("<div align='center'>").append("<h2>")
        .append("HtmlUnit implemented properties and methods for " + BROWSER_VERSION_.getNickname())
        .append("</h2>").append("</div>\n");
        return html;
    }

    private static StringBuilder overview() {
        final StringBuilder html = new StringBuilder();
        html.append("<table class='bottomBorder'>");
        html.append("<tr>\n");

        html.append("<th>Total Implemented:</th>\n");
        html.append("<td>" + IMPLEMENTED_COUNT_)
            .append(" / " + TOTAL_COUNT_).append("</td>\n");

        html.append("</tr>\n");
        html.append("</table>\n");

        html.append("<p><br></p>\n");

        return html;
    }

    private static StringBuilder htmlFooter() {
        final StringBuilder html = new StringBuilder();

        html.append("<br>").append("Legend:").append("<br>")
        .append("<span style='color: blue'>").append("To be implemented").append("</span>").append("<br>")
        .append("<span style='color: green'>").append("Implemented").append("</span>").append("<br>")
        .append("<span style='color: red'>").append("Should not be implemented").append("</span>");
        html.append("\n");

        html.append("</body>\n");
        html.append("</html>\n");
        return html;
    }

    private static StringBuilder htmlDetailsHeader() {
        final StringBuilder html = new StringBuilder();

        html.append("<table class='bottomBorder' width='100%'>");
        html.append("<tr>\n");
        html.append("<th>Class</th><th>Methods/Properties</th><th>Counts</th>\n");
        html.append("</tr>");
        return html;
    }

    private static StringBuilder htmlDetails(final String name, final StringBuilder html,
            final List<String> realProperties,
            final List<String> implementedProperties, final List<String> erroredProperties) {
        html.append("<tr>").append('\n').append("<td rowspan='2'>").append("<a name='" + name + "'>").append(name)
            .append("</a>").append("</td>").append('\n').append("<td>");
        int implementedCount = 0;

        if (realProperties.isEmpty()) {
            html.append("&nbsp;");
        }
        else if (realProperties.size() == 1
                && realProperties.contains("exception")
                && implementedProperties.size() == 1
                && implementedProperties.contains("exception")
                && erroredProperties.size() == 0) {
            html.append("&nbsp;");
        }
        else {
            for (int i = 0; i < realProperties.size(); i++) {
                final String color;
                if (implementedProperties.contains(realProperties.get(i))) {
                    color = "green";
                    implementedCount++;
                }
                else {
                    color = "blue";
                }
                html.append("<span style='color: " + color + "'>").append(realProperties.get(i)).append("</span>");
                if (i < realProperties.size() - 1) {
                    html.append(',').append(' ');
                }
            }
        }

        html.append("</td>").append("<td>").append(implementedCount).append('/')
            .append(realProperties.size()).append("</td>").append("</tr>").append('\n');
        html.append("<tr>").append("<td>");
        for (int i = 0; i < erroredProperties.size(); i++) {
            html.append("<span style='color: red'>").append(erroredProperties.get(i)).append("</span>");
            if (i < erroredProperties.size() - 1) {
                html.append(',').append(' ');
            }
        }
        if (erroredProperties.isEmpty()) {
            html.append("&nbsp;");
        }
        html.append("</td>")
            .append("<td>").append(erroredProperties.size()).append("</td>").append("</tr>\n");

        return html;
    }

    private static StringBuilder htmlDetailsFooter() {
        final StringBuilder html = new StringBuilder();
        html.append("</table>");
        return html;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean ignoreExpectationsLength() {
        return true;
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "appendData(),data,deleteData(),insertData(),length,replaceData(),splitText(),substringData(),"
                + "wholeText",
            IE = "appendData(),data,deleteData(),insertData(),length,replaceData(),replaceWholeText(),splitText(),"
                + "substringData(),"
                + "wholeText",
            EDGE = "appendData(),data,deleteData(),insertData(),length,replaceData(),replaceWholeText(),splitText(),"
                + "substringData(),"
                + "wholeText")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.IE, BrowserRunner.TestedBrowser.EDGE})
    public void text() throws Exception {
        testString("document.createTextNode('some text'), unknown");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "name,ownerElement,specified,value",
            IE = "expando,name,ownerElement,specified,value")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void attr() throws Exception {
        testString("document.createAttribute('some_attrib'), unknown");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "appendData(),data,deleteData(),insertData(),length,replaceData(),substringData()",
            IE = "appendData(),data,deleteData(),insertData(),length,replaceData(),substringData(),text",
            EDGE = "appendData(),data,deleteData(),insertData(),length,replaceData(),substringData(),text")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void comment() throws Exception {
        testString("document.createComment('come_comment'), unknown");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "namedRecordset(),recordset")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void unknown() throws Exception {
        testString("unknown, div");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "accessKey,autocapitalize,"
                + "blur(),click(),contentEditable,dataset,dir,draggable,focus(),hidden,"
                + "innerText,inputMode,isContentEditable,lang,nonce,"
                + "offsetHeight,offsetLeft,offsetParent,offsetTop,offsetWidth,onabort,"
                + "onauxclick,onblur,oncancel,oncanplay,oncanplaythrough,onchange,onclick,"
                + "onclose,oncontextmenu,oncuechange,ondblclick,ondrag,ondragend,ondragenter,ondragleave,ondragover,"
                + "ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,onfocus,ongotpointercapture,"
                + "oninput,oninvalid,"
                + "onkeydown,onkeypress,onkeyup,onload,onloadeddata,onloadedmetadata,onloadstart,onlostpointercapture,"
                + "onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,onmousewheel,"
                + "onpause,onplay,onplaying,"
                + "onpointercancel,onpointerdown,onpointerenter,onpointerleave,onpointermove,onpointerout,"
                + "onpointerover,onpointerup,onprogress,"
                + "onratechange,onreset,onresize,onscroll,onseeked,onseeking,onselect,"
                + "onstalled,onsubmit,onsuspend,ontimeupdate,ontoggle,onvolumechange,onwaiting,onwheel,outerText,"
                + "spellcheck,style,tabIndex,title,translate",
            FF52 = "accessKey,accessKeyLabel,blur(),click(),contentEditable,contextMenu,dataset,dir,"
                + "draggable,focus(),hidden,innerText,isContentEditable,"
                + "lang,offsetHeight,offsetLeft,offsetParent,offsetTop,offsetWidth,onabort,"
                + "onanimationend,onanimationiteration,onanimationstart,onblur,oncanplay,"
                + "oncanplaythrough,onchange,onclick,oncontextmenu,oncopy,oncut,ondblclick,ondrag,ondragend,"
                + "ondragenter,ondragexit,ondragleave,ondragover,ondragstart,ondrop,ondurationchange,onemptied,"
                + "onended,onerror,"
                + "onfocus,oninput,oninvalid,onkeydown,onkeypress,onkeyup,onload,onloadeddata,onloadedmetadata,"
                + "onloadend,onloadstart,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,"
                + "onmouseover,onmouseup,"
                + "onmozfullscreenchange,onmozfullscreenerror,onpaste,"
                + "onpause,onplay,onplaying,onprogress,onratechange,onreset,onresize,onscroll,onseeked,onseeking,"
                + "onselect,onselectstart,onshow,onstalled,onsubmit,onsuspend,ontimeupdate,ontoggle,ontransitionend,"
                + "onvolumechange,onwaiting,onwebkitanimationend,onwebkitanimationiteration,onwebkitanimationstart,"
                + "onwebkittransitionend,spellcheck,style,tabIndex,title",
            FF60 = "accessKey,accessKeyLabel,blur(),click(),contentEditable,contextMenu,dataset,dir,"
                + "draggable,focus(),hidden,innerText,isContentEditable,"
                + "lang,offsetHeight,offsetLeft,offsetParent,offsetTop,offsetWidth,onabort,"
                + "onanimationcancel,onanimationend,onanimationiteration,onanimationstart,onauxclick,onblur,oncanplay,"
                + "oncanplaythrough,onchange,onclick,onclose,oncontextmenu,oncopy,oncut,ondblclick,ondrag,ondragend,"
                + "ondragenter,ondragexit,ondragleave,ondragover,ondragstart,ondrop,"
                + "ondurationchange,onemptied,onended,onerror,"
                + "onfocus,ongotpointercapture,oninput,oninvalid,onkeydown,onkeypress,onkeyup,"
                + "onload,onloadeddata,onloadedmetadata,onloadend,"
                + "onloadstart,onlostpointercapture,onmousedown,onmouseenter,onmouseleave,"
                + "onmousemove,onmouseout,onmouseover,onmouseup,"
                + "onmozfullscreenchange,onmozfullscreenerror,onpaste,"
                + "onpause,onplay,onplaying,onpointercancel,onpointerdown,onpointerenter,onpointerleave,"
                + "onpointermove,onpointerout,onpointerover,onpointerup,onprogress,onratechange,onreset,"
                + "onresize,onscroll,onseeked,onseeking,"
                + "onselect,onselectstart,onshow,onstalled,onsubmit,onsuspend,ontimeupdate,ontoggle,"
                + "ontransitioncancel,ontransitionend,ontransitionrun,ontransitionstart,onvolumechange,onwaiting,"
                + "onwebkitanimationend,onwebkitanimationiteration,onwebkitanimationstart,"
                + "onwebkittransitionend,onwheel,spellcheck,style,tabIndex,"
                + "title",
            IE = "accessKey,applyElement(),blur(),canHaveChildren,canHaveHTML,children,classList,className,"
                + "clearAttributes(),click(),componentFromPoint(),contains(),contentEditable,createControlRange(),"
                + "currentStyle,dataset,dir,disabled,dragDrop(),draggable,focus(),getAdjacentText(),"
                + "getElementsByClassName(),hidden,hideFocus,id,innerHTML,innerText,insertAdjacentElement(),"
                + "insertAdjacentHTML(),insertAdjacentText(),isContentEditable,isDisabled,isMultiLine,isTextEdit,"
                + "lang,language,mergeAttributes(),msGetInputContext(),namedRecordset(),offsetHeight,offsetLeft,"
                + "offsetParent,offsetTop,offsetWidth,onabort,onactivate,onbeforeactivate,onbeforecopy,onbeforecut,"
                + "onbeforedeactivate,onbeforepaste,onblur,oncanplay,oncanplaythrough,onchange,onclick,"
                + "oncontextmenu,oncopy,oncuechange,oncut,ondblclick,ondeactivate,ondrag,ondragend,ondragenter,"
                + "ondragleave,ondragover,ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,onfocus,"
                + "onfocusin,onfocusout,onhelp,oninput,onkeydown,onkeypress,onkeyup,onload,onloadeddata,"
                + "onloadedmetadata,onloadstart,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,"
                + "onmouseover,onmouseup,onmousewheel,onmscontentzoom,onmsmanipulationstatechanged,onpaste,onpause,"
                + "onplay,onplaying,onprogress,onratechange,onreset,onscroll,onseeked,onseeking,onselect,"
                + "onselectstart,onstalled,onsubmit,onsuspend,ontimeupdate,onvolumechange,onwaiting,outerHTML,"
                + "outerText,parentElement,parentTextEdit,recordNumber,recordset,releaseCapture(),removeNode(),"
                + "replaceAdjacentText(),replaceNode(),runtimeStyle,scrollIntoView(),setActive(),setCapture(),"
                + "sourceIndex,spellcheck,style,swapNode(),tabIndex,title,uniqueID,"
                + "uniqueNumber",
            EDGE = "accessKey,blur(),children,className,click(),contentEditable,dataset,dir,dragDrop(),draggable,"
                + "focus(),getElementsByClassName(),hidden,hideFocus,innerHTML,innerText,insertAdjacentElement(),"
                + "insertAdjacentHTML(),insertAdjacentText(),isContentEditable,lang,msGetInputContext(),"
                + "offsetHeight,offsetLeft,offsetParent,offsetTop,offsetWidth,onabort,onactivate,onbeforeactivate,"
                + "onbeforecopy,onbeforecut,onbeforedeactivate,onbeforepaste,onblur,oncanplay,oncanplaythrough,"
                + "onchange,onclick,oncontextmenu,oncopy,oncuechange,oncut,ondblclick,ondeactivate,ondrag,ondragend,"
                + "ondragenter,ondragleave,ondragover,ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,"
                + "onfocus,oninput,onkeydown,onkeypress,onkeyup,onload,onloadeddata,onloadedmetadata,onloadstart,"
                + "onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,onmousewheel,"
                + "onmscontentzoom,onmsmanipulationstatechanged,onpaste,onpause,onplay,onplaying,onprogress,"
                + "onratechange,onreset,onscroll,onseeked,onseeking,onselect,onselectstart,onstalled,onsubmit,"
                + "onsuspend,ontimeupdate,onvolumechange,onwaiting,outerHTML,outerText,scrollIntoView(),setActive(),"
                + "spellcheck,style,tabIndex,"
                + "title")
    @BrowserRunner.NotYetImplemented
    public void htmlElement() throws Exception {
        testString("unknown, element");
    }

    /**
     * Test {@link Element}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "animate(),append(),attachShadow(),attributes,attributeStyleMap,"
                + "childElementCount,children,classList,className,"
                + "clientHeight,clientLeft,clientTop,clientWidth,closest(),computedStyleMap(),createShadowRoot(),"
                + "firstElementChild,getAttribute(),getAttributeNames(),getAttributeNode(),"
                + "getAttributeNodeNS(),getAttributeNS(),getBoundingClientRect(),"
                + "getClientRects(),getElementsByClassName(),getElementsByTagName(),getElementsByTagNameNS(),"
                + "hasAttribute(),hasAttributeNS(),hasAttributes(),hasPointerCapture(),id,innerHTML,"
                + "insertAdjacentElement(),insertAdjacentHTML(),insertAdjacentText(),lastElementChild,localName,"
                + "matches(),namespaceURI,onbeforecopy,onbeforecut,onbeforepaste,oncopy,oncut,onpaste,onsearch,"
                + "onselectstart,onwebkitfullscreenchange,onwebkitfullscreenerror,outerHTML,prefix,prepend(),"
                + "querySelector(),querySelectorAll(),releasePointerCapture(),removeAttribute(),removeAttributeNode(),"
                + "removeAttributeNS(),requestPointerLock(),"
                + "scroll(),scrollBy(),scrollHeight,scrollIntoView(),scrollIntoViewIfNeeded(),"
                + "scrollLeft,scrollTo(),scrollTop,scrollWidth,setAttribute(),setAttributeNode(),setAttributeNodeNS(),"
                + "setAttributeNS(),setPointerCapture(),shadowRoot,slot,tagName,webkitMatchesSelector(),"
                + "webkitRequestFullScreen(),webkitRequestFullscreen()",
            FF52 = "animate(),append(),attributes,childElementCount,children,classList,className,clientHeight,"
                + "clientLeft,clientTop,clientWidth,closest(),firstElementChild,getAttribute(),getAttributeNames(),"
                + "getAttributeNode(),getAttributeNodeNS(),getAttributeNS(),getBoundingClientRect(),getClientRects(),"
                + "getElementsByClassName(),getElementsByTagName(),getElementsByTagNameNS(),hasAttribute(),"
                + "hasAttributeNS(),hasAttributes(),id,innerHTML,insertAdjacentElement(),insertAdjacentHTML(),"
                + "insertAdjacentText(),lastElementChild,localName,matches(),mozMatchesSelector(),"
                + "mozRequestFullScreen(),namespaceURI,onwheel,outerHTML,prefix,prepend(),querySelector(),"
                + "querySelectorAll(),releaseCapture(),removeAttribute(),removeAttributeNode(),removeAttributeNS(),"
                + "requestPointerLock(),scroll(),scrollBy(),scrollHeight,scrollIntoView(),scrollLeft,scrollLeftMax,"
                + "scrollTo(),scrollTop,scrollTopMax,scrollWidth,setAttribute(),setAttributeNode(),"
                + "setAttributeNodeNS(),setAttributeNS(),setCapture(),tagName,webkitMatchesSelector()",
            FF60 = "animate(),append(),attributes,childElementCount,children,classList,className,"
                + "clientHeight,clientLeft,clientTop,"
                + "clientWidth,closest(),firstElementChild,getAttribute(),getAttributeNames(),getAttributeNode(),"
                + "getAttributeNodeNS(),getAttributeNS(),getBoundingClientRect(),getClientRects(),"
                + "getElementsByClassName(),getElementsByTagName(),getElementsByTagNameNS(),hasAttribute(),"
                + "hasAttributeNS(),hasAttributes(),hasPointerCapture(),id,innerHTML,insertAdjacentElement(),"
                + "insertAdjacentHTML(),insertAdjacentText(),lastElementChild,localName,matches(),"
                + "mozMatchesSelector(),mozRequestFullScreen(),namespaceURI,outerHTML,prefix,prepend(),"
                + "querySelector(),querySelectorAll(),releaseCapture(),releasePointerCapture(),removeAttribute(),"
                + "removeAttributeNode(),"
                + "removeAttributeNS(),requestPointerLock(),scroll(),scrollBy(),scrollHeight,scrollIntoView(),"
                + "scrollLeft,scrollLeftMax,"
                + "scrollTo(),scrollTop,scrollTopMax,scrollWidth,setAttribute(),setAttributeNode(),"
                + "setAttributeNodeNS(),setAttributeNS(),setCapture(),setPointerCapture(),"
                + "tagName,webkitMatchesSelector()",
            IE = "childElementCount,clientHeight,clientLeft,clientTop,clientWidth,firstElementChild,getAttribute(),"
                + "getAttributeNode(),getAttributeNodeNS(),getAttributeNS(),getBoundingClientRect(),getClientRects(),"
                + "getElementsByTagName(),getElementsByTagNameNS(),hasAttribute(),hasAttributeNS(),lastElementChild,"
                + "msContentZoomFactor,msGetRegionContent(),msGetUntransformedBounds(),msMatchesSelector(),"
                + "msRegionOverflow,msReleasePointerCapture(),msRequestFullscreen(),msSetPointerCapture(),"
                + "nextElementSibling,ongotpointercapture,onlostpointercapture,onmsgesturechange,onmsgesturedoubletap,"
                + "onmsgestureend,onmsgesturehold,onmsgesturestart,onmsgesturetap,onmsgotpointercapture,"
                + "onmsinertiastart,onmslostpointercapture,onmspointercancel,onmspointerdown,onmspointerenter,"
                + "onmspointerleave,onmspointermove,onmspointerout,onmspointerover,onmspointerup,onpointercancel,"
                + "onpointerdown,onpointerenter,onpointerleave,onpointermove,onpointerout,onpointerover,onpointerup,"
                + "previousElementSibling,querySelector(),querySelectorAll(),releasePointerCapture(),removeAttribute(),"
                + "removeAttributeNode(),removeAttributeNS(),scrollHeight,scrollLeft,scrollTop,scrollWidth,"
                + "setAttribute(),setAttributeNode(),setAttributeNodeNS(),setAttributeNS(),setPointerCapture(),tagName")
    @BrowserRunner.NotYetImplemented
    public void element() throws Exception {
        testString("element, xmlDocument.createTextNode('abc')");
    }

    /**
     * Test {@link Element}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "after(),animate(),assignedSlot,attachShadow(),attributes,attributeStyleMap,"
                + "before(),classList,className,"
                + "clientHeight,clientLeft,clientTop,clientWidth,closest(),computedStyleMap(),createShadowRoot(),"
                + "getAttribute(),"
                + "getAttributeNames(),getAttributeNode(),getAttributeNodeNS(),getAttributeNS(),"
                + "getBoundingClientRect(),getClientRects(),"
                + "getDestinationInsertionPoints(),getElementsByClassName(),getElementsByTagName(),"
                + "getElementsByTagNameNS(),hasAttribute(),hasAttributeNS(),hasAttributes(),hasPointerCapture(),id,"
                + "innerHTML,insertAdjacentElement(),insertAdjacentHTML(),insertAdjacentText(),localName,matches(),"
                + "namespaceURI,nextElementSibling,onbeforecopy,onbeforecut,onbeforepaste,oncopy,oncut,onpaste,"
                + "onsearch,onselectstart,onwebkitfullscreenchange,onwebkitfullscreenerror,outerHTML,prefix,"
                + "previousElementSibling,releasePointerCapture(),remove(),removeAttribute(),removeAttributeNode(),"
                + "removeAttributeNS(),replaceWith(),requestPointerLock(),"
                + "scroll(),scrollBy(),scrollHeight,scrollIntoView(),"
                + "scrollIntoViewIfNeeded(),scrollLeft,scrollTo(),scrollTop,scrollWidth,setAttribute(),"
                + "setAttributeNode(),"
                + "setAttributeNodeNS(),setAttributeNS(),setPointerCapture(),shadowRoot,slot,tagName,"
                + "webkitMatchesSelector(),webkitRequestFullScreen(),webkitRequestFullscreen()",
            FF52 = "after(),animate(),attributes,before(),classList,className,clientHeight,clientLeft,clientTop,"
                + "clientWidth,closest(),getAttribute(),getAttributeNames(),getAttributeNode(),getAttributeNodeNS(),"
                + "getAttributeNS(),getBoundingClientRect(),getClientRects(),getElementsByClassName(),"
                + "getElementsByTagName(),getElementsByTagNameNS(),hasAttribute(),hasAttributeNS(),hasAttributes(),id,"
                + "innerHTML,insertAdjacentElement(),insertAdjacentHTML(),insertAdjacentText(),localName,matches(),"
                + "mozMatchesSelector(),mozRequestFullScreen(),namespaceURI,nextElementSibling,onwheel,outerHTML,"
                + "prefix,previousElementSibling,releaseCapture(),remove(),removeAttribute(),removeAttributeNode(),"
                + "removeAttributeNS(),replaceWith(),requestPointerLock(),scroll(),scrollBy(),scrollHeight,"
                + "scrollIntoView(),scrollLeft,scrollLeftMax,scrollTo(),scrollTop,scrollTopMax,scrollWidth,"
                + "setAttribute(),setAttributeNode(),setAttributeNodeNS(),setAttributeNS(),setCapture(),tagName,"
                + "webkitMatchesSelector()",
            FF60 = "after(),animate(),attributes,before(),classList,className,clientHeight,clientLeft,clientTop,"
                + "clientWidth,closest(),getAttribute(),getAttributeNames(),getAttributeNode(),getAttributeNodeNS(),"
                + "getAttributeNS(),getBoundingClientRect(),getClientRects(),getElementsByClassName(),"
                + "getElementsByTagName(),getElementsByTagNameNS(),hasAttribute(),hasAttributeNS(),hasAttributes(),"
                + "hasPointerCapture(),id,"
                + "innerHTML,insertAdjacentElement(),insertAdjacentHTML(),insertAdjacentText(),localName,matches(),"
                + "mozMatchesSelector(),mozRequestFullScreen(),namespaceURI,nextElementSibling,outerHTML,prefix,"
                + "previousElementSibling,releaseCapture(),releasePointerCapture(),remove(),removeAttribute(),"
                + "removeAttributeNode(),removeAttributeNS(),replaceWith(),requestPointerLock(),"
                + "scroll(),scrollBy(),scrollHeight,"
                + "scrollIntoView(),scrollLeft,scrollLeftMax,scrollTo(),scrollTop,scrollTopMax,scrollWidth,"
                + "setAttribute(),setAttributeNode(),setAttributeNodeNS(),setAttributeNS(),"
                + "setCapture(),setPointerCapture(),tagName,webkitMatchesSelector()",
            IE = "childElementCount,clientHeight,clientLeft,clientTop,clientWidth,firstElementChild,getAttribute(),"
                + "getAttributeNode(),getAttributeNodeNS(),getAttributeNS(),getBoundingClientRect(),getClientRects(),"
                + "getElementsByTagName(),getElementsByTagNameNS(),hasAttribute(),hasAttributeNS(),lastElementChild,"
                + "msContentZoomFactor,msGetRegionContent(),msGetUntransformedBounds(),msMatchesSelector(),"
                + "msRegionOverflow,msReleasePointerCapture(),msRequestFullscreen(),msSetPointerCapture(),"
                + "nextElementSibling,ongotpointercapture,onlostpointercapture,onmsgesturechange,onmsgesturedoubletap,"
                + "onmsgestureend,onmsgesturehold,onmsgesturestart,onmsgesturetap,onmsgotpointercapture,"
                + "onmsinertiastart,onmslostpointercapture,onmspointercancel,onmspointerdown,onmspointerenter,"
                + "onmspointerleave,onmspointermove,onmspointerout,onmspointerover,onmspointerup,onpointercancel,"
                + "onpointerdown,onpointerenter,onpointerleave,onpointermove,onpointerout,onpointerover,onpointerup,"
                + "previousElementSibling,releasePointerCapture(),removeAttribute(),removeAttributeNode(),"
                + "removeAttributeNS(),scrollHeight,scrollLeft,scrollTop,scrollWidth,setAttribute(),setAttributeNode(),"
                + "setAttributeNodeNS(),setAttributeNS(),setPointerCapture(),tagName")
    @BrowserRunner.NotYetImplemented
    public void element2() throws Exception {
        testString("element, document.createDocumentFragment()");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "blockDirection,clipBottom,clipLeft,clipRight,clipTop,hasLayout")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void currentStyle() throws Exception {
        testString("document.body.currentStyle, document.body.style");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "AT_TARGET,bubbles,BUBBLING_PHASE,cancelable,cancelBubble,CAPTURING_PHASE,"
                + "composed,composedPath(),currentTarget,defaultPrevented,eventPhase,initEvent(),isTrusted,"
                + "NONE,path,preventDefault(),returnValue,srcElement,stopImmediatePropagation(),stopPropagation(),"
                + "target,timeStamp,type",
            FF52 = "ALT_MASK,AT_TARGET,bubbles,BUBBLING_PHASE,cancelable,cancelBubble,CAPTURING_PHASE,composed,"
                + "CONTROL_MASK,currentTarget,"
                + "defaultPrevented,eventPhase,explicitOriginalTarget,getPreventDefault(),initEvent(),isTrusted,"
                + "META_MASK,NONE,originalTarget,preventDefault(),SHIFT_MASK,stopImmediatePropagation(),"
                + "stopPropagation(),target,timeStamp,"
                + "type",
            FF60 = "ALT_MASK,AT_TARGET,bubbles,BUBBLING_PHASE,cancelable,cancelBubble,"
                + "CAPTURING_PHASE,composed,composedPath(),CONTROL_MASK,currentTarget,"
                + "defaultPrevented,eventPhase,explicitOriginalTarget,initEvent(),isTrusted,"
                + "META_MASK,NONE,originalTarget,preventDefault(),SHIFT_MASK,stopImmediatePropagation(),"
                + "stopPropagation(),target,timeStamp,"
                + "type",
            IE = "AT_TARGET,bubbles,BUBBLING_PHASE,cancelable,cancelBubble,CAPTURING_PHASE,currentTarget,"
                + "defaultPrevented,eventPhase,initEvent(),isTrusted,preventDefault(),srcElement,"
                + "stopImmediatePropagation(),stopPropagation(),target,timeStamp,"
                + "type",
            EDGE = "AT_TARGET,bubbles,BUBBLING_PHASE,cancelable,cancelBubble,CAPTURING_PHASE,currentTarget,"
                + "defaultPrevented,eventPhase,initEvent(),isTrusted,preventDefault(),returnValue,srcElement,"
                + "stopImmediatePropagation(),stopPropagation(),target,timeStamp,"
                + "type")
    @BrowserRunner.NotYetImplemented
    public void event() throws Exception {
        testString("event ? event : window.event, null");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "addEventListener(),alert(),applicationCache,atob(),blur(),btoa(),caches,cancelAnimationFrame(),"
                + "cancelIdleCallback(),captureEvents(),chrome,clearInterval(),clearTimeout(),clientInformation,"
                + "close(),closed,confirm(),"
                + "createImageBitmap(),crypto,customElements,"
                + "defaultStatus,defaultstatus,devicePixelRatio,"
                + "dispatchEvent(),document,external,fetch(),find(),focus(),frameElement,frames,getComputedStyle(),"
                + "getSelection(),history,"
                + "indexedDB,innerHeight,innerWidth,isSecureContext,length,"
                + "localStorage,location,locationbar,matchMedia(),menubar,moveBy(),moveTo(),name,navigator,onabort,"
                + "onafterprint,onanimationend,onanimationiteration,onanimationstart,onappinstalled,"
                + "onauxclick,onbeforeinstallprompt,onbeforeprint,"
                + "onbeforeunload,onblur,oncancel,oncanplay,oncanplaythrough,"
                + "onchange,onclick,onclose,oncontextmenu,oncuechange,ondblclick,ondevicemotion,ondeviceorientation,"
                + "ondeviceorientationabsolute,ondrag,ondragend,ondragenter,ondragleave,ondragover,ondragstart,ondrop,"
                + "ondurationchange,onemptied,onended,onerror,onfocus,ongotpointercapture,"
                + "onhashchange,oninput,oninvalid,onkeydown,onkeypress,onkeyup,"
                + "onlanguagechange,onload(),onloadeddata,onloadedmetadata,onloadstart,onlostpointercapture,onmessage,"
                + "onmessageerror,onmousedown,"
                + "onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,onmousewheel,onoffline,"
                + "ononline,onpagehide,onpageshow,onpause,onplay,onplaying,"
                + "onpointercancel,onpointerdown,onpointerenter,onpointerleave,"
                + "onpointermove,onpointerout,onpointerover,onpointerup,"
                + "onpopstate,onprogress,onratechange,"
                + "onrejectionhandled,onreset,onresize,onscroll,onsearch,"
                + "onseeked,onseeking,onselect,onstalled,onstorage,"
                + "onsubmit,onsuspend,ontimeupdate,ontoggle,ontransitionend,onunhandledrejection,"
                + "onunload,onvolumechange,onwaiting,"
                + "onwebkitanimationend,onwebkitanimationiteration,onwebkitanimationstart,onwebkittransitionend,"
                + "onwheel,open(),openDatabase(),opener,origin,outerHeight,outerWidth,pageXOffset,pageYOffset,parent,"
                + "performance,PERSISTENT,personalbar,postMessage(),print(),process(),prompt(),releaseEvents(),"
                + "removeEventListener(),requestAnimationFrame(),requestIdleCallback(),"
                + "resizeBy(),resizeTo(),screen,screenLeft,screenTop,"
                + "screenX,screenY,scroll(),scrollbars,scrollBy(),scrollTo(),scrollX,scrollY,self,sessionStorage,"
                + "setInterval(),setTimeout(),sortFunction(),speechSynthesis,status,statusbar,stop(),styleMedia,"
                + "TEMPORARY,test(),toolbar,top,visualViewport,webkitCancelAnimationFrame(),"
                + "webkitRequestAnimationFrame(),webkitRequestFileSystem(),"
                + "webkitResolveLocalFileSystemURL(),webkitStorageInfo,"
                + "window",
            FF52 = "addEventListener(),alert(),applicationCache,atob(),blur(),btoa(),caches,cancelAnimationFrame(),"
                + "captureEvents(),clearInterval(),clearTimeout(),close(),closed,confirm(),content,"
                + "createImageBitmap(),crypto,devicePixelRatio,dispatchEvent(),document,dump(),"
                + "external,fetch(),find(),"
                + "focus(),frameElement,frames,fullScreen,getComputedStyle(),getDefaultComputedStyle(),"
                + "getSelection(),history,indexedDB,innerHeight,innerWidth,"
                + "InstallTrigger,isSecureContext,length,localStorage,location,locationbar,"
                + "matchMedia(),menubar,moveBy(),moveTo(),"
                + "mozInnerScreenX,mozInnerScreenY,mozPaintCount,name,navigator,onabort,"
                + "onabsolutedeviceorientation,onafterprint,onanimationend,onanimationiteration,"
                + "onanimationstart,onbeforeprint,onbeforeunload,onblur,"
                + "oncanplay,oncanplaythrough,onchange,onclick,oncontextmenu,ondblclick,ondevicelight,"
                + "ondevicemotion,ondeviceorientation,ondeviceproximity,ondrag,ondragend,ondragenter,"
                + "ondragexit,ondragleave,"
                + "ondragover,ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,onfocus,"
                + "onhashchange,oninput,oninvalid,onkeydown,onkeypress,onkeyup,onlanguagechange,onload(),"
                + "onloadeddata,onloadedmetadata,onloadend,onloadstart,onmessage,onmousedown,onmouseenter,"
                + "onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,onmozfullscreenchange,"
                + "onmozfullscreenerror,onoffline,ononline,onpagehide,onpageshow,onpause,onplay,onplaying,"
                + "onpopstate,onprogress,onratechange,onreset,onresize,onscroll,onseeked,onseeking,onselect,"
                + "onselectstart,onshow,onstalled,onstorage,onsubmit,onsuspend,ontimeupdate,ontoggle,"
                + "ontransitionend,onunload,onuserproximity,onvolumechange,onwaiting,onwebkitanimationend,"
                + "onwebkitanimationiteration,onwebkitanimationstart,onwebkittransitionend,onwheel,"
                + "open(),opener,outerHeight,outerWidth,pageXOffset,pageYOffset,parent,performance,"
                + "personalbar,postMessage(),print(),process(),prompt(),releaseEvents(),"
                + "removeEventListener(),requestAnimationFrame(),resizeBy(),resizeTo(),"
                + "screen,screenX,screenY,scroll(),scrollbars,scrollBy(),scrollByLines(),scrollByPages(),"
                + "scrollMaxX,scrollMaxY,scrollTo(),scrollX,scrollY,self,sessionStorage,setInterval(),"
                + "setResizable(),setTimeout(),showModalDialog(),sidebar,sizeToContent(),"
                + "sortFunction(),speechSynthesis,"
                + "status,statusbar,stop(),test(),toolbar,top,updateCommands(),window",
            FF60 = "addEventListener(),alert(),applicationCache,atob(),blur(),btoa(),caches,cancelAnimationFrame(),"
                + "cancelIdleCallback(),captureEvents(),clearInterval(),clearTimeout(),close(),closed,confirm(),"
                + "createImageBitmap(),crypto,devicePixelRatio,dispatchEvent(),document,dump(),external,fetch(),"
                + "find(),focus(),frameElement,frames,fullScreen,getComputedStyle(),getDefaultComputedStyle(),"
                + "getSelection(),history,indexedDB,innerHeight,innerWidth,isSecureContext,length,localStorage,"
                + "location,locationbar,matchMedia(),menubar,moveBy(),moveTo(),mozInnerScreenX,mozInnerScreenY,"
                + "mozPaintCount,name,navigator,onabort,onabsolutedeviceorientation,onafterprint,onanimationcancel,"
                + "onanimationend,onanimationiteration,onanimationstart,onauxclick,onbeforeprint,onbeforeunload,"
                + "onblur,oncanplay,oncanplaythrough,onchange,onclick,onclose,oncontextmenu,ondblclick,ondevicelight,"
                + "ondevicemotion,ondeviceorientation,ondeviceproximity,ondrag,ondragend,ondragenter,ondragexit,"
                + "ondragleave,ondragover,ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,onfocus,"
                + "ongotpointercapture,onhashchange,oninput,oninvalid,onkeydown,onkeypress,onkeyup,onlanguagechange,"
                + "onload(),onloadeddata,onloadedmetadata,onloadend,onloadstart,onlostpointercapture,onmessage,"
                + "onmessageerror,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,"
                + "onmouseup,onmozfullscreenchange,onmozfullscreenerror,onoffline,ononline,onpagehide,onpageshow,"
                + "onpause,onplay,onplaying,onpointercancel,onpointerdown,onpointerenter,onpointerleave,onpointermove,"
                + "onpointerout,onpointerover,onpointerup,onpopstate,onprogress,onratechange,onreset,onresize,"
                + "onscroll,onseeked,onseeking,onselect,onselectstart,onshow,onstalled,onstorage,onsubmit,onsuspend,"
                + "ontimeupdate,ontoggle,ontransitioncancel,ontransitionend,ontransitionrun,ontransitionstart,onunload,"
                + "onuserproximity,onvolumechange,onvrdisplayactivate,onvrdisplayconnect,onvrdisplaydeactivate,"
                + "onvrdisplaydisconnect,onvrdisplaypresentchange,onwaiting,onwebkitanimationend,"
                + "onwebkitanimationiteration,onwebkitanimationstart,onwebkittransitionend,onwheel,open(),opener,"
                + "origin,outerHeight,outerWidth,pageXOffset,pageYOffset,parent,performance,personalbar,postMessage(),"
                + "print(),process(),prompt(),releaseEvents(),removeEventListener(),requestAnimationFrame(),"
                + "requestIdleCallback(),resizeBy(),resizeTo(),screen,screenX,screenY,scroll(),scrollbars,"
                + "scrollBy(),scrollByLines(),scrollByPages(),scrollMaxX,scrollMaxY,scrollTo(),scrollX,scrollY,"
                + "self,sessionStorage,setInterval(),setResizable(),setTimeout(),sidebar,sizeToContent(),"
                + "sortFunction(),speechSynthesis,status,statusbar,stop(),test(),toolbar,top,updateCommands(),window",
            IE = "addEventListener(),alert(),animationStartTime,applicationCache,atob(),blur(),btoa(),"
                + "cancelAnimationFrame(),captureEvents(),clearImmediate(),clearInterval(),clearTimeout(),"
                + "clientInformation,clipboardData,close(),closed,confirm(),console,"
                + "defaultStatus,devicePixelRatio,dispatchEvent(),document,doNotTrack,event,external,focus(),"
                + "frameElement,frames,getComputedStyle(),getSelection(),history,ieMethods,indexedDB,innerHeight,"
                + "innerWidth,item(),length,localStorage,location,matchMedia(),maxConnectionsPerServer,moveBy(),"
                + "moveTo(),msAnimationStartTime,msCancelRequestAnimationFrame(),msClearImmediate(),msCrypto,"
                + "msIndexedDB,msIsStaticHTML(),msMatchMedia(),msRequestAnimationFrame(),msSetImmediate(),"
                + "msWriteProfilerMark(),name,navigate(),navigator,offscreenBuffering,onabort,onafterprint,"
                + "onbeforeprint,onbeforeunload,onblur,oncanplay,oncanplaythrough,onchange,onclick,oncontextmenu,"
                + "ondblclick,ondrag,ondragend,ondragenter,ondragleave,ondragover,ondragstart,ondrop,"
                + "ondurationchange,onemptied,onended,onerror,onfocus,onfocusin,onfocusout,onhashchange,onhelp,"
                + "oninput,onkeydown,onkeypress,onkeyup,onload(),onloadeddata,onloadedmetadata,onloadstart,"
                + "onmessage,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,"
                + "onmousewheel,onmsgesturechange,onmsgesturedoubletap,onmsgestureend,onmsgesturehold,"
                + "onmsgesturestart,onmsgesturetap,onmsinertiastart,onmspointercancel,onmspointerdown,"
                + "onmspointerenter,onmspointerleave,onmspointermove,onmspointerout,onmspointerover,onmspointerup,"
                + "onoffline,ononline,onpagehide,onpageshow,onpause,onplay,onplaying,onpointercancel,onpointerdown,"
                + "onpointerenter,onpointerleave,onpointermove,onpointerout,onpointerover,onpointerup,onpopstate,"
                + "onprogress,onratechange,onreadystatechange,onreset,onresize,onscroll,onseeked,onseeking,onselect,"
                + "onstalled,onstorage,onsubmit,onsuspend,ontimeupdate,onunload,onvolumechange,onwaiting,open(),"
                + "opener,outerHeight,outerWidth,pageXOffset,pageYOffset,parent,performance,postMessage(),print(),"
                + "process(),prompt(),releaseEvents(),removeEventListener(),requestAnimationFrame(),resizeBy(),"
                + "resizeTo(),screen,screenLeft,screenTop,screenX,screenY,scroll(),scrollBy(),scrollTo(),self,"
                + "sessionStorage,setImmediate(),setInterval(),setTimeout(),showHelp(),showModalDialog(),"
                + "showModelessDialog(),sortFunction(),status,styleMedia,test(),top,toStaticHTML(),toString(),"
                + "window",
            EDGE = "addEventListener(),alert(),applicationCache,atob(),blur(),btoa(),cancelAnimationFrame(),"
                + "captureEvents(),chrome,clearImmediate(),clearInterval(),clearTimeout(),clientInformation,close(),"
                + "closed,confirm(),console,crypto,defaultStatus,devicePixelRatio,"
                + "dispatchEvent(),document,doNotTrack,event,external,focus(),frameElement,frames,"
                + "getComputedStyle(),getMatchedCSSRules(),getSelection(),history,ieMethods,indexedDB,innerHeight,"
                + "innerWidth,length,localStorage,location,locationbar,matchMedia(),menubar,moveBy(),moveTo(),"
                + "msWriteProfilerMark(),name,navigator,offscreenBuffering,onabort,onafterprint,onbeforeprint,"
                + "onbeforeunload,onblur,oncanplay,oncanplaythrough,onchange,onclick,oncompassneedscalibration,"
                + "oncontextmenu,ondblclick,ondevicemotion,ondeviceorientation,ondrag,ondragend,ondragenter,"
                + "ondragleave,ondragover,ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,onfocus,"
                + "onhashchange,oninput,onkeydown,onkeypress,onkeyup,onload(),onloadeddata,onloadedmetadata,"
                + "onloadstart,onmessage,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,"
                + "onmouseup,onmousewheel,onmsgesturechange,onmsgesturedoubletap,onmsgestureend,onmsgesturehold,"
                + "onmsgesturestart,onmsgesturetap,onmsinertiastart,onoffline,ononline,onpagehide,onpageshow,"
                + "onpause,onplay,onplaying,onpointercancel,onpointerdown,onpointerenter,onpointerleave,"
                + "onpointermove,onpointerout,onpointerover,onpointerup,onpopstate,onprogress,onratechange,"
                + "onreadystatechange,onreset,onresize,onscroll,onseeked,onseeking,onselect,onstalled,onstorage,"
                + "onsubmit,onsuspend,ontimeupdate,onunload,onvolumechange,onwaiting,onwheel,open(),opener,"
                + "outerHeight,outerWidth,pageXOffset,pageYOffset,parent,performance,personalbar,postMessage(),"
                + "print(),process(),prompt(),releaseEvents(),removeEventListener(),requestAnimationFrame(),"
                + "resizeBy(),resizeTo(),screen,screenLeft,screenTop,screenX,screenY,scroll(),scrollbars,scrollBy(),"
                + "scrollTo(),scrollX,scrollY,self,sessionStorage,setImmediate(),setInterval(),setTimeout(),"
                + "sortFunction(),status,statusbar,styleMedia,test(),toolbar,top,webkitCancelAnimationFrame(),"
                + "webkitConvertPointFromNodeToPage(),webkitConvertPointFromPageToNode(),"
                + "webkitRequestAnimationFrame(),"
                + "window")
    @BrowserRunner.NotYetImplemented
    public void window() throws Exception {
        testString("window, null");
    }

    /**
     * Test {@link HtmlAbbreviated}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void abbr() throws Exception {
        test("abbr");
    }

    /**
     * Test {@link HtmlAcronym}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void acronym() throws Exception {
        test("acronym");
    }

    /**
     * Test {@link HtmlAnchor}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "charset,coords,download,hash,host,hostname,href,hreflang,name,origin,password,pathname,ping,"
                + "port,protocol,referrerPolicy,rel,relList,rev,search,shape,target,text,type,"
                + "username",
            IE = "charset,coords,hash,host,hostname,href,hreflang,Methods,mimeType,name,nameProp,pathname,port,"
                + "protocol,protocolLong,rel,rev,search,shape,target,text,type,"
                + "urn",
            EDGE = "charset,coords,hash,host,hostname,href,hreflang,Methods,mimeType,name,nameProp,pathname,port,"
                + "protocol,protocolLong,rel,rev,search,shape,target,text,type,"
                + "urn")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void a() throws Exception {
        test("a");
    }

    /**
     * Test {@link HtmlAddress}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,clear,width")
    public void address() throws Exception {
        test("address");
    }

    /**
     * Test {@link HtmlApplet}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align,alt,archive,code,codeBase,height,hspace,name,object,vspace,width",
            IE = "align,alt,altHtml,archive,BaseHref,border,classid,code,codeBase,codeType,contentDocument,data,"
                + "declare,form,height,hspace,name,object,standby,type,useMap,vspace,width",
            EDGE = "align,alt,altHtml,archive,BaseHref,border,code,codeBase,codeType,contentDocument,data,declare,"
                + "form,height,hspace,name,object,standby,type,useMap,vspace,"
                + "width",
            CHROME = "-",
            FF60 = "-")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.FF52, BrowserRunner.TestedBrowser.IE})
    public void applet() throws Exception {
        test("applet");
    }

    /**
     * Test {@link HtmlArea}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "alt,coords,download,hash,host,hostname,href,noHref,origin,password,pathname,ping,port,"
                + "protocol,referrerPolicy,rel,relList,search,shape,target,username",
            IE = "alt,coords,hash,host,hostname,href,noHref,pathname,port,protocol,rel,search,shape,target",
            EDGE = "alt,coords,hash,host,hostname,href,noHref,pathname,port,protocol,rel,search,shape,target")
    @BrowserRunner.NotYetImplemented
    public void area() throws Exception {
        test("area");
    }

    /**
     * Test {@link HtmlArticle}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void article() throws Exception {
        test("article");
    }

    /**
     * Test {@link HtmlAside}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void aside() throws Exception {
        test("aside");
    }

    /**
     * Test {@link HtmlAudio}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "addTextTrack(),autoplay,buffered,"
                + "canPlayType(),captureStream(),controls,controlsList,crossOrigin,currentSrc,currentTime,"
                + "defaultMuted,defaultPlaybackRate,disableRemotePlayback,duration,"
                + "ended,error,HAVE_CURRENT_DATA,HAVE_ENOUGH_DATA,"
                + "HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,load(),loop,mediaKeys,muted,NETWORK_EMPTY,NETWORK_IDLE,"
                + "NETWORK_LOADING,NETWORK_NO_SOURCE,networkState,onencrypted,"
                + "onwaitingforkey,pause(),paused,play(),playbackRate,played,preload,readyState,remote,"
                + "seekable,seeking,setMediaKeys(),setSinkId(),sinkId,src,srcObject,textTracks,"
                + "volume,webkitAudioDecodedByteCount,"
                + "webkitVideoDecodedByteCount",
            FF52 = "addTextTrack(),autoplay,buffered,canPlayType(),controls,crossOrigin,currentSrc,currentTime,"
                + "defaultMuted,defaultPlaybackRate,duration,ended,error,fastSeek(),HAVE_CURRENT_DATA,"
                + "HAVE_ENOUGH_DATA,HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,load(),loop,mediaKeys,mozAudioCaptured,"
                + "mozAutoplayEnabled,mozCaptureStream(),"
                + "mozCaptureStreamUntilEnded(),"
                + "mozFragmentEnd,mozGetMetadata(),mozPreservesPitch,mozSrcObject,muted,NETWORK_EMPTY,NETWORK_IDLE,"
                + "NETWORK_LOADING,NETWORK_NO_SOURCE,networkState,onencrypted,"
                + "onwaitingforkey,pause(),paused,play(),playbackRate,played,preload,"
                + "readyState,seekable,seeking,setMediaKeys(),src,srcObject,textTracks,volume",
            FF60 = "addTextTrack(),autoplay,buffered,canPlayType(),controls,crossOrigin,currentSrc,currentTime,"
                + "defaultMuted,defaultPlaybackRate,duration,ended,error,fastSeek(),HAVE_CURRENT_DATA,"
                + "HAVE_ENOUGH_DATA,HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,load(),loop,mediaKeys,mozAudioCaptured,"
                + "mozCaptureStream(),"
                + "mozCaptureStreamUntilEnded(),"
                + "mozFragmentEnd,mozGetMetadata(),mozPreservesPitch,muted,NETWORK_EMPTY,NETWORK_IDLE,"
                + "NETWORK_LOADING,NETWORK_NO_SOURCE,networkState,onencrypted,onwaitingforkey,"
                + "pause(),paused,play(),playbackRate,played,preload,"
                + "readyState,seekable,seeking,seekToNextFrame(),setMediaKeys(),src,srcObject,textTracks,volume",
            IE = "addTextTrack(),audioTracks,autobuffer,autoplay,buffered,canPlayType(),controls,currentSrc,"
                + "currentTime,defaultPlaybackRate,duration,ended,error,HAVE_CURRENT_DATA,HAVE_ENOUGH_DATA,"
                + "HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,initialTime,load(),loop,msPlayToDisabled,"
                + "msPlayToPreferredSourceUri,msPlayToPrimary,muted,NETWORK_EMPTY,NETWORK_IDLE,NETWORK_LOADING,"
                + "NETWORK_NO_SOURCE,networkState,pause(),paused,play(),playbackRate,played,preload,readyState,"
                + "seekable,seeking,src,textTracks,volume",
            EDGE = "addTextTrack(),audioTracks,autoplay,buffered,canPlayType(),controls,currentSrc,currentTime,"
                + "defaultMuted,defaultPlaybackRate,duration,ended,error,HAVE_CURRENT_DATA,HAVE_ENOUGH_DATA,"
                + "HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,load(),loop,msGraphicsTrustStatus,msKeys,"
                + "msPlayToDisabled,msPlayToPreferredSourceUri,msPlayToPrimary,msSetMediaKeys(),muted,NETWORK_EMPTY,"
                + "NETWORK_IDLE,NETWORK_LOADING,NETWORK_NO_SOURCE,networkState,onmsneedkey,pause(),paused,play(),"
                + "playbackRate,played,preload,readyState,seekable,seeking,src,srcObject,textTracks,videoTracks,"
                + "volume")
    @BrowserRunner.NotYetImplemented
    public void audio() throws Exception {
        test("audio");
    }

    /**
     * Test {@link HtmlBackgroundSound}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "balance,loop,src,volume")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void bgsound() throws Exception {
        test("bgsound");
    }

    /**
     * Test {@link HtmlBase}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("href,target")
    public void base() throws Exception {
        test("base");
    }

    /**
     * Test {@link HtmlBaseFont}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "color,face,size",
            EDGE = "color,face,size")
    public void basefont() throws Exception {
        test("basefont");
    }

    /**
     * Test {@link HtmlBidirectionalIsolation}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void bdi() throws Exception {
        test("bdi");
    }

    /**
     * Test {@link HtmlBidirectionalOverride}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void bdo() throws Exception {
        test("bdo");
    }

    /**
     * Test {@link HtmlBig}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void big() throws Exception {
        test("big");
    }

    /**
     * Test {@link HtmlBlink}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void blink() throws Exception {
        test("blink");
    }

    /**
     * Test {@link HtmlBlockQuote}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "cite",
            IE = "cite,clear,width")
    public void blockquote() throws Exception {
        test("blockquote");
    }

    /**
     * Test {@link HtmlBody}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "aLink,background,bgColor,link,onafterprint,onbeforeprint,"
                + "onbeforeunload,onhashchange,onlanguagechange,onmessage,"
                + "onmessageerror,onoffline,ononline,onpagehide,onpageshow,onpopstate,"
                + "onrejectionhandled,onstorage,onunhandledrejection,onunload,"
                + "text,vLink",
            FF52 = "aLink,background,bgColor,link,onafterprint,onbeforeprint,onbeforeunload,onhashchange,"
                + "onlanguagechange,onmessage,"
                + "onoffline,ononline,onpagehide,onpageshow,onpopstate,onstorage,onunload,text,"
                + "vLink",
            FF60 = "aLink,background,bgColor,link,onafterprint,onbeforeprint,onbeforeunload,onhashchange,"
                + "onlanguagechange,onmessage,onmessageerror,"
                + "onoffline,ononline,onpagehide,onpageshow,onpopstate,onstorage,onunload,text,"
                + "vLink",
            IE = "aLink,background,bgColor,bgProperties,bottomMargin,createTextRange(),leftMargin,link,noWrap,"
                + "onafterprint,onbeforeprint,onbeforeunload,onhashchange,onmessage,onoffline,ononline,onpagehide,"
                + "onpageshow,onpopstate,onresize,onstorage,onunload,rightMargin,scroll,text,topMargin,"
                + "vLink",
            EDGE = "aLink,background,bgColor,bgProperties,link,noWrap,onafterprint,onbeforeprint,onbeforeunload,"
                + "onhashchange,onmessage,onoffline,ononline,onpagehide,onpageshow,onpopstate,onresize,onstorage,"
                + "onunload,text,"
                + "vLink")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void body() throws Exception {
        test("body");
    }

    /**
     * Test {@link HtmlBold}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void b() throws Exception {
        test("b");
    }

    /**
     * Test {@link HtmlBreak}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("clear")
    public void br() throws Exception {
        test("br");
    }

    /**
     * Test {@link HtmlButton}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "autofocus,checkValidity(),disabled,form,formAction,formEnctype,formMethod,formNoValidate,"
                + "formTarget,labels,name,reportValidity(),setCustomValidity(),type,validationMessage,validity,"
                + "value,willValidate",
            FF52 = "autofocus,checkValidity(),disabled,form,formAction,formEnctype,formMethod,formNoValidate,"
                + "formTarget,name,reportValidity(),setCustomValidity(),type,validationMessage,validity,value,"
                + "willValidate",
            FF60 = "autofocus,checkValidity(),disabled,form,formAction,formEnctype,formMethod,formNoValidate,"
                + "formTarget,labels,name,reportValidity(),setCustomValidity(),type,validationMessage,validity,"
                + "value,willValidate",
            IE = "autofocus,checkValidity(),createTextRange(),form,formAction,formEnctype,formMethod,"
                + "formNoValidate,formTarget,name,setCustomValidity(),status,type,validationMessage,validity,value,"
                + "willValidate",
            EDGE = "autofocus,checkValidity(),disabled,form,formAction,formEnctype,formMethod,formNoValidate,"
                + "formTarget,name,setCustomValidity(),status,type,validationMessage,validity,value,"
                + "willValidate")
    @BrowserRunner.NotYetImplemented
    public void button() throws Exception {
        test("button");
    }

    /**
     * Test {@link HtmlCanvas}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "captureStream(),getContext(),height,toBlob(),toDataURL(),width",
            FF = "captureStream(),getContext(),height,"
                    + "mozGetAsFile(),mozOpaque,mozPrintCallback,toBlob(),toDataURL(),width",
            IE = "getContext(),height,msToBlob(),toDataURL(),width",
            EDGE = "getContext(),height,msToBlob(),toDataURL(),width")
    @BrowserRunner.NotYetImplemented
    public void canvas() throws Exception {
        test("canvas");
    }

    /**
     * Test {@link HtmlCaption}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align",
            IE = "align,vAlign",
            EDGE = "align,vAlign")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void caption() throws Exception {
        test("caption");
    }

    /**
     * Test {@link HtmlCenter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,clear,width")
    public void center() throws Exception {
        test("center");
    }

    /**
     * Test {@link HtmlCitation}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void cite() throws Exception {
        test("cite");
    }

    /**
     * Test {@link HtmlCode}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void code() throws Exception {
        test("code");
    }

    /**
     * Test {@link HtmlCommand}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void command() throws Exception {
        test("command");
    }

    /**
     * Test {@link HtmlDataList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("options")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void datalist() throws Exception {
        test("datalist");
    }

    /**
     * Test {@link HtmlDefinition}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void dfn() throws Exception {
        test("dfn");
    }

    /**
     * Test {@link HtmlDefinitionDescription}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "noWrap")
    public void dd() throws Exception {
        test("dd");
    }

    /**
     * Test {@link HtmlDeletedText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("cite,dateTime")
    public void del() throws Exception {
        test("del");
    }

    /**
     * Test {@link HtmlDetails}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "open",
            IE = "-")
    public void details() throws Exception {
        test("details");
    }

    /**
     * Test {@link HtmlDialog}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            CHROME = "close(),open,returnValue,show(),showModal()")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.CHROME)
    public void dialog() throws Exception {
        test("dialog");
    }

    /**
     * Test {@link HtmlDirectory}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "compact",
            IE = "compact,type")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void dir() throws Exception {
        test("dir");
    }

    /**
     * Test {@link HtmlDivision}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align",
            IE = "align,noWrap",
            EDGE = "align,noWrap")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void div() throws Exception {
        test("div");
    }

    /**
     * Test {@link HtmlDefinitionList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("compact")
    public void dl() throws Exception {
        test("dl");
    }

    /**
     * Test {@link HtmlDefinitionTerm}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "noWrap")
    public void dt() throws Exception {
        test("dt");
    }

    /**
     * Test {@link HtmlEmbed}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align,getSVGDocument(),height,name,src,type,width",
            IE = "getSVGDocument(),height,msPlayToDisabled,msPlayToPreferredSourceUri,msPlayToPrimary,name,palette,"
                + "pluginspage,readyState,src,units,"
                + "width",
            EDGE = "getSVGDocument(),height,msPlayToDisabled,msPlayToPreferredSourceUri,msPlayToPrimary,name,palette,"
                + "pluginspage,readyState,src,units,"
                + "width")
    @BrowserRunner.NotYetImplemented
    public void embed() throws Exception {
        test("embed");
    }

    /**
     * Test {@link HtmlEmphasis}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void em() throws Exception {
        test("em");
    }

    /**
     * Test {@link HtmlFieldSet}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "checkValidity(),disabled,elements,form,name,reportValidity(),setCustomValidity(),type,"
                + "validationMessage,validity,willValidate",
            IE = "align,checkValidity(),form,setCustomValidity(),validationMessage,validity,willValidate",
            EDGE = "align,checkValidity(),disabled,form,setCustomValidity(),validationMessage,validity,willValidate")
    @BrowserRunner.NotYetImplemented
    public void fieldset() throws Exception {
        test("fieldset");
    }

    /**
     * Test {@link HtmlFigureCaption}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void figcaption() throws Exception {
        test("figcaption");
    }

    /**
     * Test {@link HtmlFigure}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void figure() throws Exception {
        test("figure");
    }

    /**
     * Test {@link HtmlFont}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("color,face,size")
    public void font() throws Exception {
        test("font");
    }

    /**
     * Test {@link HtmlForm}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "acceptCharset,action,autocomplete,checkValidity(),elements,encoding,enctype,length,method,name,"
                + "noValidate,reportValidity(),reset(),submit(),"
                + "target",
            IE = "acceptCharset,action,autocomplete,checkValidity(),elements,encoding,enctype,item(),length,method,"
                + "name,namedItem(),noValidate,reset(),submit(),"
                + "target",
            EDGE = "acceptCharset,action,autocomplete,checkValidity(),elements,encoding,enctype,item(),length,method,"
                + "name,namedItem(),noValidate,reset(),submit(),"
                + "target")
    @BrowserRunner.NotYetImplemented
    public void form() throws Exception {
        test("form");
    }

    /**
     * Test {@link HtmlFooter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void footer() throws Exception {
        test("footer");
    }

    /**
     * Test {@link HtmlFrame}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "contentDocument,contentWindow,frameBorder,longDesc,marginHeight,marginWidth,"
                + "name,noResize,scrolling,"
                + "src",
            IE = "border,borderColor,contentDocument,contentWindow,frameBorder,frameSpacing,getSVGDocument(),"
                + "height,longDesc,marginHeight,marginWidth,name,noResize,scrolling,security,src,"
                + "width",
            EDGE = "border,borderColor,contentDocument,contentWindow,frameBorder,frameSpacing,getSVGDocument(),"
                + "height,longDesc,marginHeight,marginWidth,name,noResize,scrolling,src,"
                + "width")
    @BrowserRunner.NotYetImplemented
    public void frame() throws Exception {
        test("frame");
    }

    /**
     * Test {@link HtmlFrameSet}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "cols,onafterprint,onbeforeprint,onbeforeunload,onhashchange,onlanguagechange,"
                + "onmessage,onmessageerror,onoffline,ononline,onpagehide,"
                + "onpageshow,onpopstate,onrejectionhandled,onstorage,onunhandledrejection,onunload,"
                + "rows",
            FF52 = "cols,onafterprint,onbeforeprint,onbeforeunload,onhashchange,onlanguagechange,onmessage,"
                + "onoffline,ononline,"
                + "onpagehide,onpageshow,onpopstate,onstorage,onunload,"
                + "rows",
            FF60 = "cols,onafterprint,onbeforeprint,onbeforeunload,onhashchange,onlanguagechange,onmessage,"
                + "onmessageerror,onoffline,ononline,"
                + "onpagehide,onpageshow,onpopstate,onstorage,onunload,"
                + "rows",
            IE = "border,borderColor,cols,frameBorder,frameSpacing,name,onafterprint,onbeforeprint,onbeforeunload,"
                + "onhashchange,onmessage,onoffline,ononline,onpagehide,onpageshow,onresize,onstorage,onunload,"
                + "rows",
            EDGE = "border,borderColor,cols,frameBorder,frameSpacing,name,onafterprint,onbeforeprint,onbeforeunload,"
                + "onhashchange,onmessage,onoffline,ononline,onpagehide,onpageshow,onresize,onstorage,onunload,"
                + "rows")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void frameset() throws Exception {
        test("frameset");
    }

    /**
     * Test {@link HtmlHead}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "profile",
            EDGE = "profile")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.IE, BrowserRunner.TestedBrowser.EDGE})
    public void head() throws Exception {
        test("head");
    }

    /**
     * Test {@link HtmlHeader}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void header() throws Exception {
        test("header");
    }

    /**
     * Test {@link HtmlHeading1}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align",
            IE = "align,clear")
    public void h1() throws Exception {
        test("h1");
    }

    /**
     * Test {@link HtmlHeading2}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align",
            IE = "align,clear")
    public void h2() throws Exception {
        test("h2");
    }

    /**
     * Test {@link HtmlHeading3}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align",
            IE = "align,clear")
    public void h3() throws Exception {
        test("h3");
    }

    /**
     * Test {@link HtmlHeading4}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align",
            IE = "align,clear")
    public void h4() throws Exception {
        test("h4");
    }

    /**
     * Test {@link HtmlHeading5}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align",
            IE = "align,clear")
    public void h5() throws Exception {
        test("h5");
    }

    /**
     * Test {@link HtmlHeading6}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align",
            IE = "align,clear")
    public void h6() throws Exception {
        test("h6");
    }

    /**
     * Test {@link HtmlHorizontalRule}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("align,color,noShade,size,width")
    @BrowserRunner.NotYetImplemented
    public void hr() throws Exception {
        test("hr");
    }

    /**
     * Test {@link HtmlHtml}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("version")
    public void html() throws Exception {
        test("html");
    }

    /**
     * Test {@link HtmlInlineFrame}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(FF52 = "align,allowFullscreen,contentDocument,contentWindow,frameBorder,getSVGDocument(),height,"
                + "longDesc,marginHeight,marginWidth,name,referrerPolicy,sandbox,scrolling,src,srcdoc,"
                + "width",
            FF60 = "align,allowFullscreen,allowPaymentRequest,contentDocument,contentWindow,frameBorder,"
                + "getSVGDocument(),height,"
                + "longDesc,marginHeight,marginWidth,name,referrerPolicy,sandbox,scrolling,src,srcdoc,"
                + "width",
            CHROME = "align,allow,allowFullscreen,allowPaymentRequest,contentDocument,contentWindow,"
                + "csp,frameBorder,getSVGDocument(),height,"
                + "longDesc,marginHeight,marginWidth,name,referrerPolicy,sandbox,scrolling,src,srcdoc,"
                + "width",
            IE = "align,border,contentDocument,contentWindow,frameBorder,frameSpacing,getSVGDocument(),height,"
                + "hspace,longDesc,marginHeight,marginWidth,name,noResize,sandbox,scrolling,security,src,vspace,"
                + "width",
            EDGE = "align,allowFullscreen,border,contentDocument,contentWindow,frameBorder,frameSpacing,"
                + "getSVGDocument(),height,hspace,longDesc,marginHeight,marginWidth,name,noResize,sandbox,scrolling,"
                + "src,vspace,"
                + "width")
    @BrowserRunner.NotYetImplemented
    public void iframe() throws Exception {
        test("iframe");
    }

    /**
     * Test {@link HtmlInlineQuotation}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "cite",
            IE = "cite,dateTime")
    public void q() throws Exception {
        test("q");
    }

    /**
     * Test {@link HtmlImage}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(FF52 = "align,alt,border,complete,crossOrigin,currentSrc,height,hspace,isMap,longDesc,lowsrc,name,"
                + "naturalHeight,naturalWidth,referrerPolicy,sizes,src,srcset,useMap,vspace,width,x,"
                + "y",
            FF60 = "align,alt,border,complete,crossOrigin,currentSrc,height,hspace,isMap,longDesc,lowsrc,name,"
                + "naturalHeight,naturalWidth,referrerPolicy,sizes,src,srcset,useMap,vspace,width,x,"
                + "y",
            CHROME = "align,alt,border,complete,crossOrigin,currentSrc,decode(),decoding,"
                + "height,hspace,isMap,longDesc,lowsrc,name,"
                + "naturalHeight,naturalWidth,referrerPolicy,sizes,src,srcset,useMap,vspace,width,x,"
                + "y",
            IE = "align,alt,border,complete,crossOrigin,dynsrc,fileCreatedDate,fileModifiedDate,fileUpdatedDate,"
                + "height,href,hspace,isMap,longDesc,loop,lowsrc,mimeType,msPlayToDisabled,"
                + "msPlayToPreferredSourceUri,msPlayToPrimary,name,nameProp,naturalHeight,naturalWidth,protocol,src,"
                + "start,useMap,vrml,vspace,"
                + "width",
            EDGE = "align,alt,border,complete,crossOrigin,height,hspace,isMap,longDesc,msPlayToDisabled,"
                + "msPlayToPreferredSourceUri,msPlayToPrimary,name,naturalHeight,naturalWidth,src,srcset,useMap,"
                + "vspace,width,x,"
                + "y")
    @BrowserRunner.NotYetImplemented
    public void img() throws Exception {
        test("img");
    }

    /**
     * Test {@link HtmlImage}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "align,alt,border,complete,crossOrigin,dynsrc,fileCreatedDate,fileModifiedDate,fileUpdatedDate,"
                + "height,href,hspace,isMap,longDesc,loop,lowsrc,mimeType,msPlayToDisabled,"
                + "msPlayToPreferredSourceUri,msPlayToPrimary,name,nameProp,naturalHeight,naturalWidth,protocol,src,"
                + "start,useMap,vrml,vspace,"
                + "width",
            EDGE = "align,alt,border,complete,crossOrigin,height,hspace,isMap,longDesc,msPlayToDisabled,"
                + "msPlayToPreferredSourceUri,msPlayToPrimary,name,naturalHeight,naturalWidth,src,srcset,useMap,"
                + "vspace,width,x,"
                + "y")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.IE, BrowserRunner.TestedBrowser.EDGE})
    public void image() throws Exception {
        test("image");
    }

    /**
     * Test {@link HtmlInsertedText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("cite,dateTime")
    public void ins() throws Exception {
        test("ins");
    }

    /**
     * Test {@link HtmlIsIndex}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "action,form,prompt")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void isindex() throws Exception {
        test("isindex");
    }

    /**
     * Test {@link HtmlItalic}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void i() throws Exception {
        test("i");
    }

    /**
     * Test {@link HtmlKeyboard}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void kbd() throws Exception {
        test("kbd");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,clear,width")
    public void keygen() throws Exception {
        test("keygen");
    }

    /**
     * Test {@link HtmlLabel}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "control,form,htmlFor",
            IE = "form,htmlFor",
            EDGE = "form,htmlFor")
    public void label() throws Exception {
        test("label");
    }

    /**
     * Test {@link HtmlLayer}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void layer() throws Exception {
        test("layer");
    }

    /**
     * Test {@link HtmlLegend}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("align,form")
    public void legend() throws Exception {
        test("legend");
    }

    /**
     * Test {@link HtmlListing}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "width",
            IE = "cite,clear,width")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void listing() throws Exception {
        test("listing");
    }

    /**
     * Test {@link HtmlListItem}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("type,value")
    @BrowserRunner.NotYetImplemented
    public void li() throws Exception {
        test("li");
    }

    /**
     * Test {@link HtmlLink}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "as,charset,crossOrigin,disabled,href,hreflang,import,integrity,"
                + "media,referrerPolicy,rel,relList,rev,sheet,sizes,target,type",
            FF52 = "charset,crossOrigin,disabled,href,hreflang,integrity,"
                + "media,referrerPolicy,rel,relList,rev,sheet,sizes,target,type",
            FF60 = "as,charset,crossOrigin,disabled,href,hreflang,integrity,"
                + "media,referrerPolicy,rel,relList,rev,sheet,sizes,target,type",
            IE = "charset,href,hreflang,media,rel,rev,sheet,target,type",
            EDGE = "charset,disabled,href,hreflang,media,rel,rev,sheet,target,type")
    @BrowserRunner.NotYetImplemented
    public void link() throws Exception {
        test("link");
    }

    /**
     * Test {@link HtmlMain}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void main() throws Exception {
        test("main");
    }

    /**
     * Test {@link HtmlMap}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("areas,name")
    public void map() throws Exception {
        test("map");
    }

    /**
     * Test {@link HtmlMark}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void mark() throws Exception {
        test("mark");
    }

    /**
     * Test {@link HtmlMarquee}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "behavior,bgColor,direction,height,hspace,loop,scrollAmount,scrollDelay,start(),stop(),trueSpeed,"
                + "vspace,width",
            FF = "align",
            IE = "behavior,bgColor,direction,height,hspace,loop,onbounce,onfinish,onstart,scrollAmount,scrollDelay,"
                + "start(),stop(),trueSpeed,vspace,width",
            EDGE = "behavior,bgColor,direction,height,hspace,loop,onbounce,onfinish,onstart,scrollAmount,scrollDelay,"
                + "start(),stop(),trueSpeed,vspace,"
                + "width")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.IE, BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.EDGE})
    public void marquee() throws Exception {
        test("marquee");
    }

    /**
     * Test {@link HtmlMenu}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "compact",
            FF = "compact,label,type",
            IE = "compact,type",
            EDGE = "compact,type")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void menu() throws Exception {
        test("menu");
    }

    /**
     * Test {@link HtmlMenuItem}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            FF = "checked,defaultChecked,disabled,icon,label,radiogroup,type")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.FF)
    public void menuitem() throws Exception {
        test("menuitem");
    }

    /**
     * Test {@link HtmlMeta}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "content,httpEquiv,name,scheme",
            IE = "charset,content,httpEquiv,name,scheme,url",
            EDGE = "charset,content,httpEquiv,name,scheme,url")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void meta() throws Exception {
        test("meta");
    }

    /**
     * Test {@link HtmlMeter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "high,labels,low,max,min,optimum,value",
            FF52 = "high,low,max,min,optimum,value",
            IE = "-",
            EDGE = "-")
    public void meter() throws Exception {
        test("meter");
    }

    /**
     * Test {@link HtmlMultiColumn}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void multicol() throws Exception {
        test("multicol");
    }

    /**
     * Test {@link HtmlNav}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void nav() throws Exception {
        test("nav");
    }

    /**
     * Test {@link HtmlNextId}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "n")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void nextid() throws Exception {
        test("nextid");
    }

    /**
     * Test {@link HtmlNoBreak}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void nobr() throws Exception {
        test("nobr");
    }

    /**
     * Test {@link HtmlNoEmbed}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void noembed() throws Exception {
        test("noembed");
    }

    /**
     * Test {@link HtmlNoFrames}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void noframes() throws Exception {
        test("noframes");
    }

    /**
     * Test {@link HtmlNoLayer}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void nolayer() throws Exception {
        test("nolayer");
    }

    /**
     * Test {@link HtmlNoScript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void noscript() throws Exception {
        test("noscript");
    }

    /**
     * Test {@link HtmlObject}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "align,archive,border,checkValidity(),code,codeBase,codeType,contentDocument,contentWindow,"
                + "data,declare,form,"
                + "getSVGDocument(),height,hspace,name,reportValidity(),setCustomValidity(),standby,type,useMap,"
                + "validationMessage,validity,vspace,width,willValidate",
            FF52 = "align,archive,border,checkValidity(),code,codeBase,codeType,contentDocument,contentWindow,data,"
                + "declare,form,getSVGDocument(),height,hspace,name,reportValidity(),setCustomValidity(),standby,"
                + "type,typeMustMatch,useMap,validationMessage,validity,vspace,width,willValidate",
            FF60 = "align,archive,border,checkValidity(),code,codeBase,codeType,contentDocument,contentWindow,data,"
                + "declare,form,getSVGDocument(),height,hspace,name,reportValidity(),setCustomValidity(),"
                + "standby,type,typeMustMatch,useMap,validationMessage,validity,vspace,width,willValidate",
            IE = "align,alt,altHtml,archive,BaseHref,border,checkValidity(),classid,code,codeBase,codeType,"
                + "contentDocument,data,declare,form,getSVGDocument(),height,hspace,msPlayToDisabled,"
                + "msPlayToPreferredSourceUri,msPlayToPrimary,name,object,readyState,setCustomValidity(),standby,"
                + "type,useMap,validationMessage,validity,vspace,width,willValidate",
            EDGE = "align,alt,altHtml,archive,BaseHref,border,checkValidity(),code,codeBase,codeType,contentDocument,"
                + "data,declare,form,getSVGDocument(),height,hspace,msPlayToDisabled,msPlayToPreferredSourceUri,"
                + "msPlayToPrimary,name,object,readyState,setCustomValidity(),standby,type,useMap,validationMessage,"
                + "validity,vspace,width,"
                + "willValidate")
    @BrowserRunner.NotYetImplemented
    public void object() throws Exception {
        test("object");
    }

    /**
     * Test {@link HtmlOrderedList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "compact,reversed,start,type",
            IE = "compact,start,type",
            EDGE = "compact,start,type")
    @BrowserRunner.NotYetImplemented
    public void ol() throws Exception {
        test("ol");
    }

    /**
     * Test {@link HtmlOptionGroup}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "disabled,label",
            IE = "defaultSelected,form,index,label,selected,text,value",
            EDGE = "defaultSelected,disabled,form,index,label,selected,text,value")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.IE, BrowserRunner.TestedBrowser.EDGE})
    public void optgroup() throws Exception {
        test("optgroup");
    }

    /**
     * Test {@link HtmlOption}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "defaultSelected,disabled,form,index,label,selected,text,value",
            IE = "defaultSelected,form,index,label,selected,text,value")
    public void option() throws Exception {
        test("option");
    }

    /**
     * Test {@link HtmlOutput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "checkValidity(),defaultValue,form,htmlFor,labels,name,reportValidity(),setCustomValidity(),type,"
                + "validationMessage,validity,value,"
                + "willValidate",
            FF52 = "checkValidity(),defaultValue,form,htmlFor,name,reportValidity(),setCustomValidity(),"
                + "type,validationMessage,validity,value,"
                + "willValidate",
            IE = "-",
            EDGE = "-")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF})
    public void output() throws Exception {
        test("output");
    }

    /**
     * Test {@link HtmlParagraph}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align",
            IE = "align,clear",
            EDGE = "align,clear")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void p() throws Exception {
        test("p");
    }

    /**
     * Test {@link HtmlParameter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("name,type,value,valueType")
    public void param() throws Exception {
        test("param");
    }

    /**
     * Test {@link HtmlPlainText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,clear,width")
    public void plaintext() throws Exception {
        test("plaintext");
    }

    /**
     * Test {@link HtmlPreformattedText}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "width",
            IE = "cite,clear,width")
    public void pre() throws Exception {
        test("pre");
    }

    /**
     * Test {@link HtmlProgress}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "labels,max,position,value",
            FF52 = "max,position,value",
            IE = "form,max,position,value",
            EDGE = "form,max,position,value")
    @BrowserRunner.NotYetImplemented
    public void progress() throws Exception {
        test("progress");
    }

    /**
     * Test {@link HtmlRp}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void rp() throws Exception {
        test("rp");
    }

    /**
     * Test {@link HtmlRt}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void rt() throws Exception {
        test("rt");
    }

    /**
     * Test {@link HtmlRuby}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void ruby() throws Exception {
        test("ruby");
    }

    /**
     * Test {@link HtmlS}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void s() throws Exception {
        test("s");
    }

    /**
     * Test {@link HtmlSample}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void samp() throws Exception {
        test("samp");
    }

    /**
     * Test {@link HtmlScript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "async,charset,crossOrigin,defer,event,htmlFor,integrity,noModule,src,text,type",
            FF52 = "async,charset,crossOrigin,defer,event,htmlFor,integrity,src,text,type",
            IE = "async,charset,defer,event,htmlFor,src,text,type",
            EDGE = "async,charset,defer,event,htmlFor,src,text,type")
    @BrowserRunner.NotYetImplemented
    public void script() throws Exception {
        test("script");
    }

    /**
     * Test {@link HtmlSection}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void section() throws Exception {
        test("section");
    }

    /**
     * Test {@link HtmlSelect}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "add(),autocomplete,autofocus,checkValidity(),"
                + "disabled,form,item(),labels,length,multiple,name,namedItem(),"
                + "options,reportValidity(),required,selectedIndex,selectedOptions,setCustomValidity(),size,type,"
                + "validationMessage,validity,value,"
                + "willValidate",
            FF52 = "add(),autofocus,checkValidity(),disabled,form,item(),length,multiple,name,"
                + "namedItem(),options,reportValidity(),required,selectedIndex,selectedOptions,setCustomValidity(),"
                + "size,type,validationMessage,validity,value,"
                + "willValidate",
            FF60 = "add(),autocomplete,autofocus,checkValidity(),disabled,form,item(),labels,length,multiple,name,"
                + "namedItem(),options,reportValidity(),required,selectedIndex,selectedOptions,setCustomValidity(),"
                + "size,type,validationMessage,validity,value,"
                + "willValidate",
            IE = "add(),autofocus,checkValidity(),form,item(),length,multiple,name,namedItem(),options,remove(),"
                + "required,selectedIndex,setCustomValidity(),size,type,validationMessage,validity,value,"
                + "willValidate",
            EDGE = "add(),autofocus,checkValidity(),disabled,form,item(),length,multiple,name,namedItem(),options,"
                + "required,selectedIndex,selectedOptions,setCustomValidity(),size,type,validationMessage,validity,"
                + "value,"
                + "willValidate")
    @BrowserRunner.NotYetImplemented
    public void select() throws Exception {
        test("select");
    }

    /**
     * Test {@link HtmlSmall}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void small() throws Exception {
        test("small");
    }

    /**
     * Test {@link HtmlSource}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "media,sizes,src,srcset,type",
            IE = "media,src,type",
            EDGE = "media,msKeySystem,src,type")
    @BrowserRunner.NotYetImplemented
    public void source() throws Exception {
        test("source");
    }

    /**
     * Test {@link HtmlSpan}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void span() throws Exception {
        test("span");
    }

    /**
     * Test {@link HtmlStrike}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void strike() throws Exception {
        test("strike");
    }

    /**
     * Test {@link HtmlStrong}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void strong() throws Exception {
        test("strong");
    }

    /**
     * Test {@link HtmlStyle}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "disabled,media,sheet,type",
            FF52 = "disabled,media,scoped,sheet,type",
            IE = "media,sheet,type",
            EDGE = "media,sheet,type")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void style() throws Exception {
        test("style");
    }

    /**
     * Test {@link HtmlSubscript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void sub() throws Exception {
        test("sub");
    }

    /**
     * Test {@link HtmlSummary}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void summary() throws Exception {
        test("summary");
    }

    /**
     * Test {@link HtmlSuperscript}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void sup() throws Exception {
        test("sup");
    }

    /**
     * Test {@link HtmlSvg}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void svg() throws Exception {
        test("svg");
    }

    /**
     * Test {@link HtmlTable}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align,bgColor,border,caption,cellPadding,cellSpacing,createCaption(),createTBody(),"
                + "createTFoot(),createTHead(),deleteCaption(),deleteRow(),deleteTFoot(),deleteTHead(),frame,"
                + "insertRow(),rows,rules,summary,tBodies,tFoot,tHead,"
                + "width",
            IE = "align,background,bgColor,border,borderColor,borderColorDark,borderColorLight,caption,cellPadding,"
                + "cells,cellSpacing,cols,createCaption(),createTBody(),createTFoot(),createTHead(),deleteCaption(),"
                + "deleteRow(),deleteTFoot(),deleteTHead(),frame,height,insertRow(),moveRow(),rows,rules,summary,"
                + "tBodies,tFoot,tHead,"
                + "width",
            EDGE = "align,bgColor,border,borderColor,caption,cellPadding,cellSpacing,cols,createCaption(),"
                + "createTBody(),createTFoot(),createTHead(),deleteCaption(),deleteRow(),deleteTFoot(),"
                + "deleteTHead(),frame,height,insertRow(),rows,rules,summary,tBodies,tFoot,tHead,"
                + "width")
    @BrowserRunner.NotYetImplemented
    public void table() throws Exception {
        test("table");
    }

    /**
     * Test {@link HtmlTableColumn}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("align,ch,chOff,span,vAlign,width")
    public void col() throws Exception {
        test("col");
    }

    /**
     * Test {@link HtmlTableColumnGroup}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("align,ch,chOff,span,vAlign,width")
    public void colgroup() throws Exception {
        test("colgroup");
    }

    /**
     * Test {@link HtmlTableBody}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align,ch,chOff,deleteRow(),insertRow(),rows,vAlign",
            IE = "align,bgColor,ch,chOff,deleteRow(),insertRow(),moveRow(),rows,vAlign")
    public void tbody() throws Exception {
        test("tbody");
    }

    /**
     * Test {@link HtmlTableDataCell}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "abbr,align,axis,bgColor,cellIndex,ch,chOff,colSpan,headers,height,noWrap,rowSpan,scope,vAlign,"
                + "width",
            IE = "abbr,align,axis,background,bgColor,borderColor,borderColorDark,borderColorLight,cellIndex,ch,"
                + "chOff,colSpan,headers,height,noWrap,rowSpan,scope,vAlign,"
                + "width")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void td() throws Exception {
        test("td");
    }

    /**
     * Test {@link HtmlTableHeaderCell}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "abbr,align,axis,bgColor,cellIndex,ch,chOff,colSpan,headers,height,noWrap,rowSpan,scope,vAlign,"
                + "width",
            IE = "abbr,align,axis,background,bgColor,borderColor,borderColorDark,borderColorLight,cellIndex,ch,"
                + "chOff,colSpan,headers,height,noWrap,rowSpan,scope,vAlign,"
                + "width")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void th() throws Exception {
        test("th");
    }

    /**
     * Test {@link HtmlTableRow}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align,bgColor,cells,ch,chOff,deleteCell(),insertCell(),rowIndex,sectionRowIndex,vAlign",
            IE = "align,bgColor,borderColor,borderColorDark,borderColorLight,cells,ch,chOff,deleteCell(),height,"
                + "insertCell(),rowIndex,sectionRowIndex,"
                + "vAlign",
            EDGE = "align,bgColor,cells,ch,chOff,deleteCell(),height,insertCell(),rowIndex,sectionRowIndex,vAlign")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.IE, BrowserRunner.TestedBrowser.EDGE})
    public void tr() throws Exception {
        test("tr");
    }

    /**
     * Test {@link HtmlTextArea}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "autocomplete,autofocus,checkValidity(),cols,defaultValue,dirName,disabled,form,labels,"
                + "maxLength,minLength,name,placeholder,readOnly,reportValidity(),required,rows,select(),"
                + "selectionDirection,selectionEnd,selectionStart,setCustomValidity(),setRangeText(),"
                + "setSelectionRange(),textLength,type,validationMessage,validity,value,willValidate,"
                + "wrap",
            FF52 = "autofocus,checkValidity(),cols,defaultValue,disabled,form,maxLength,minLength,name,placeholder,"
                + "readOnly,reportValidity(),required,rows,select(),selectionDirection,selectionEnd,"
                + "selectionStart,setCustomValidity(),setRangeText(),setSelectionRange(),"
                + "textLength,type,validationMessage,validity,value,willValidate,wrap",
            FF60 = "autocomplete,autofocus,checkValidity(),cols,defaultValue,disabled,form,labels,maxLength,"
                + "minLength,name,placeholder,readOnly,reportValidity(),"
                + "required,rows,select(),selectionDirection,selectionEnd,selectionStart,setCustomValidity(),"
                + "setRangeText(),setSelectionRange(),textLength,type,validationMessage,validity,value,willValidate,"
                + "wrap",
            IE = "autofocus,checkValidity(),cols,createTextRange(),defaultValue,form,maxLength,name,placeholder,"
                + "readOnly,required,rows,select(),selectionEnd,selectionStart,setCustomValidity(),"
                + "setSelectionRange(),status,type,validationMessage,validity,value,willValidate,"
                + "wrap",
            EDGE = "autofocus,checkValidity(),cols,defaultValue,disabled,form,maxLength,name,placeholder,readOnly,"
                + "required,rows,select(),selectionEnd,selectionStart,setCustomValidity(),setSelectionRange(),"
                + "status,type,validationMessage,validity,value,willValidate,"
                + "wrap")
    @BrowserRunner.NotYetImplemented
    public void textarea() throws Exception {
        test("textarea");
    }

    /**
     * Test {@link HtmlTableFooter}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align,ch,chOff,deleteRow(),insertRow(),rows,vAlign",
            IE = "align,bgColor,ch,chOff,deleteRow(),insertRow(),moveRow(),rows,vAlign")
    public void tfoot() throws Exception {
        test("tfoot");
    }

    /**
     * Test {@link HtmlTableHeader}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "align,ch,chOff,deleteRow(),insertRow(),rows,vAlign",
            IE = "align,bgColor,ch,chOff,deleteRow(),insertRow(),moveRow(),rows,vAlign")
    public void thead() throws Exception {
        test("thead");
    }

    /**
     * Test {@link HtmlTeletype}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void tt() throws Exception {
        test("tt");
    }

    /**
     * Test {@link HtmlTime}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "dateTime",
            IE = "-")
    public void time() throws Exception {
        test("time");
    }

    /**
     * Test {@link HtmlTitle}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("text")
    public void title() throws Exception {
        test("title");
    }

    /**
     * Test {@link HtmlTrack}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("default,ERROR,kind,label,LOADED,LOADING,NONE,readyState,src,srclang,track")
    @BrowserRunner.NotYetImplemented
    public void track() throws Exception {
        test("track");
    }

    /**
     * Test {@link HtmlUnderlined}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void u() throws Exception {
        test("u");
    }

    /**
     * Test {@link HtmlUnorderedList}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("compact,type")
    public void ul() throws Exception {
        test("ul");
    }

    /**
     * Test {@link HtmlVariable}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            IE = "cite,dateTime")
    public void var() throws Exception {
        test("var");
    }

    /**
     * Test {@link HtmlVideo}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "addTextTrack(),autoplay,buffered,"
                + "canPlayType(),captureStream(),controls,controlsList,crossOrigin,currentSrc,currentTime,"
                + "defaultMuted,defaultPlaybackRate,disableRemotePlayback,duration,"
                + "ended,error,HAVE_CURRENT_DATA,HAVE_ENOUGH_DATA,"
                + "HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,height,load(),loop,mediaKeys,muted,NETWORK_EMPTY,"
                + "NETWORK_IDLE,NETWORK_LOADING,NETWORK_NO_SOURCE,networkState,onencrypted,"
                + "onwaitingforkey,pause(),paused,play(),playbackRate,played,"
                + "poster,preload,readyState,remote,"
                + "seekable,seeking,setMediaKeys(),setSinkId(),sinkId,src,srcObject,"
                + "textTracks,videoHeight,videoWidth,"
                + "volume,webkitAudioDecodedByteCount,webkitDecodedFrameCount,"
                + "webkitDisplayingFullscreen,webkitDroppedFrameCount,"
                + "webkitEnterFullscreen(),webkitEnterFullScreen(),"
                + "webkitExitFullscreen(),webkitExitFullScreen(),webkitSupportsFullscreen,webkitVideoDecodedByteCount,"
                + "width",
            FF52 = "addTextTrack(),autoplay,buffered,canPlayType(),controls,crossOrigin,currentSrc,currentTime,"
                + "defaultMuted,defaultPlaybackRate,duration,ended,error,fastSeek(),getVideoPlaybackQuality(),"
                + "HAVE_CURRENT_DATA,"
                + "HAVE_ENOUGH_DATA,HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,height,load(),loop,mediaKeys,"
                + "mozAudioCaptured,"
                + "mozAutoplayEnabled,mozCaptureStream(),mozCaptureStreamUntilEnded(),"
                + "mozDecodedFrames,mozFragmentEnd,mozFrameDelay,mozGetMetadata(),mozHasAudio,mozPaintedFrames,"
                + "mozParsedFrames,mozPresentedFrames,mozPreservesPitch,mozSrcObject,muted,NETWORK_EMPTY,"
                + "NETWORK_IDLE,NETWORK_LOADING,NETWORK_NO_SOURCE,networkState,onencrypted,"
                + "onwaitingforkey,pause(),paused,play(),playbackRate,"
                + "played,poster,preload,readyState,seekable,seeking,setMediaKeys(),"
                + "src,srcObject,textTracks,videoHeight,videoWidth,volume,"
                + "width",
            FF60 = "addTextTrack(),autoplay,buffered,canPlayType(),controls,crossOrigin,currentSrc,currentTime,"
                + "defaultMuted,defaultPlaybackRate,duration,ended,error,fastSeek(),getVideoPlaybackQuality(),"
                + "HAVE_CURRENT_DATA,"
                + "HAVE_ENOUGH_DATA,HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,height,load(),loop,mediaKeys,"
                + "mozAudioCaptured,"
                + "mozCaptureStream(),mozCaptureStreamUntilEnded(),"
                + "mozDecodedFrames,mozFragmentEnd,mozFrameDelay,mozGetMetadata(),mozHasAudio,mozPaintedFrames,"
                + "mozParsedFrames,mozPresentedFrames,mozPreservesPitch,muted,NETWORK_EMPTY,"
                + "NETWORK_IDLE,NETWORK_LOADING,NETWORK_NO_SOURCE,networkState,onencrypted,"
                + "onwaitingforkey,pause(),paused,play(),playbackRate,"
                + "played,poster,preload,readyState,seekable,seeking,seekToNextFrame(),setMediaKeys(),"
                + "src,srcObject,textTracks,videoHeight,videoWidth,volume,"
                + "width",
            IE = "addTextTrack(),audioTracks,autobuffer,autoplay,buffered,canPlayType(),controls,currentSrc,"
                + "currentTime,defaultPlaybackRate,duration,ended,error,HAVE_CURRENT_DATA,HAVE_ENOUGH_DATA,"
                + "HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,height,initialTime,load(),loop,msPlayToDisabled,"
                + "msPlayToPreferredSourceUri,msPlayToPrimary,msZoom,muted,NETWORK_EMPTY,NETWORK_IDLE,"
                + "NETWORK_LOADING,NETWORK_NO_SOURCE,networkState,pause(),paused,play(),playbackRate,played,poster,"
                + "preload,readyState,seekable,seeking,src,textTracks,videoHeight,videoWidth,volume,"
                + "width",
            EDGE = "addTextTrack(),audioTracks,autoplay,buffered,canPlayType(),controls,currentSrc,currentTime,"
                + "defaultMuted,defaultPlaybackRate,duration,ended,error,getVideoPlaybackQuality(),"
                + "HAVE_CURRENT_DATA,HAVE_ENOUGH_DATA,HAVE_FUTURE_DATA,HAVE_METADATA,HAVE_NOTHING,height,load(),"
                + "loop,msGraphicsTrustStatus,msKeys,msPlayToDisabled,msPlayToPreferredSourceUri,msPlayToPrimary,"
                + "msSetMediaKeys(),msZoom,muted,NETWORK_EMPTY,NETWORK_IDLE,NETWORK_LOADING,NETWORK_NO_SOURCE,"
                + "networkState,onmsneedkey,pause(),paused,play(),playbackRate,played,poster,preload,readyState,"
                + "seekable,seeking,src,srcObject,textTracks,videoHeight,videoTracks,videoWidth,volume,"
                + "webkitDisplayingFullscreen,webkitEnterFullscreen(),webkitEnterFullScreen(),"
                + "webkitExitFullscreen(),webkitExitFullScreen(),webkitSupportsFullscreen,"
                + "width")
    @BrowserRunner.NotYetImplemented
    public void video() throws Exception {
        test("video");
    }

    /**
     * Test {@link HtmlWordBreak}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void wbr() throws Exception {
        test("wbr");
    }

    /**
     * Test {@link HtmlExample}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "width",
            IE = "cite,clear,width")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.EDGE)
    public void xmp() throws Exception {
        test("xmp");
    }

    /**
     * Test {@link HtmlInput}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(FF52 = "accept,align,alt,autocomplete,autofocus,checked,checkValidity(),defaultChecked,defaultValue,"
                + "disabled,files,form,formAction,formEnctype,formMethod,formNoValidate,formTarget,height,"
                + "indeterminate,list,max,maxLength,min,minLength,mozIsTextField(),multiple,name,"
                + "pattern,placeholder,readOnly,reportValidity(),required,"
                + "select(),selectionDirection,selectionEnd,selectionStart,setCustomValidity(),"
                + "setRangeText(),setSelectionRange(),size,src,step,stepDown(),stepUp(),textLength,type,useMap,"
                + "validationMessage,validity,value,valueAsNumber,webkitdirectory,webkitEntries,width,willValidate",
            FF60 = "accept,align,alt,autocomplete,autofocus,checked,checkValidity(),defaultChecked,defaultValue,"
                + "disabled,files,form,formAction,formEnctype,formMethod,formNoValidate,formTarget,height,"
                + "indeterminate,labels,list,max,maxLength,min,minLength,mozIsTextField(),multiple,name,"
                + "pattern,placeholder,readOnly,reportValidity(),"
                + "required,select(),selectionDirection,selectionEnd,selectionStart,setCustomValidity(),"
                + "setRangeText(),setSelectionRange(),size,src,step,stepDown(),stepUp(),textLength,type,useMap,"
                + "validationMessage,validity,value,valueAsDate,valueAsNumber,"
                + "webkitdirectory,webkitEntries,width,willValidate",
            IE = "accept,align,alt,autocomplete,autofocus,border,checked,checkValidity(),complete,"
                + "createTextRange(),defaultChecked,defaultValue,dynsrc,files,form,formAction,formEnctype,"
                + "formMethod,formNoValidate,formTarget,height,hspace,indeterminate,list,loop,lowsrc,max,maxLength,"
                + "min,multiple,name,pattern,placeholder,readOnly,required,select(),selectionEnd,selectionStart,"
                + "setCustomValidity(),setSelectionRange(),size,src,start,status,step,stepDown(),stepUp(),type,"
                + "useMap,validationMessage,validity,value,valueAsNumber,vrml,vspace,width,willValidate",
            CHROME = "accept,align,alt,autocomplete,autofocus,checked,checkValidity(),"
                + "defaultChecked,defaultValue,"
                + "dirName,disabled,files,form,formAction,formEnctype,formMethod,formNoValidate,formTarget,height,"
                + "incremental,indeterminate,labels,list,max,maxLength,min,minLength,multiple,name,pattern,"
                + "placeholder,readOnly,reportValidity(),required,select(),selectionDirection,selectionEnd,"
                + "selectionStart,setCustomValidity(),setRangeText(),setSelectionRange(),size,src,step,stepDown(),"
                + "stepUp(),type,useMap,validationMessage,validity,value,valueAsDate,valueAsNumber,webkitdirectory,"
                + "webkitEntries,width,willValidate",
            EDGE = "accept,align,alt,autocomplete,autofocus,border,checked,checkValidity(),complete,defaultChecked,"
                + "defaultValue,disabled,files,form,formAction,formEnctype,formMethod,formNoValidate,formTarget,"
                + "height,hspace,indeterminate,list,max,maxLength,min,multiple,name,pattern,placeholder,readOnly,"
                + "required,select(),selectionEnd,selectionStart,setCustomValidity(),setSelectionRange(),size,src,"
                + "status,step,stepDown(),stepUp(),type,useMap,validationMessage,validity,value,valueAsDate,"
                + "valueAsNumber,vspace,width,"
                + "willValidate")
    @BrowserRunner.NotYetImplemented
    public void input() throws Exception {
        test("input");
    }

    /**
     * Test {@link HtmlData}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "value",
            IE = "-")
    public void data() throws Exception {
        test("data");
    }

    /**
     * Test {@link HtmlContent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            CHROME = "getDistributedNodes(),select")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.CHROME)
    public void content() throws Exception {
        test("content");
    }

    /**
     * Test {@link HtmlPicture}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("-")
    public void picutre() throws Exception {
        test("picture");
    }

    /**
     * Test {@link HtmlTemplate}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "content",
            IE = "-",
            EDGE = "-")
    public void template() throws Exception {
        test("template");
    }

    /**
     * Test {@link KeyboardEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "altKey,charCode,code,ctrlKey,DOM_KEY_LOCATION_LEFT,DOM_KEY_LOCATION_NUMPAD,"
                + "DOM_KEY_LOCATION_RIGHT,"
                + "DOM_KEY_LOCATION_STANDARD,getModifierState(),initKeyboardEvent(),isComposing,"
                + "key,keyCode,location,metaKey,repeat,"
                + "shiftKey",
            FF52 = "altKey,charCode,code,ctrlKey,DOM_KEY_LOCATION_LEFT,DOM_KEY_LOCATION_NUMPAD,"
                + "DOM_KEY_LOCATION_RIGHT,DOM_KEY_LOCATION_STANDARD,DOM_VK_0,DOM_VK_1,DOM_VK_2,DOM_VK_3,DOM_VK_4,"
                + "DOM_VK_5,DOM_VK_6,DOM_VK_7,DOM_VK_8,DOM_VK_9,DOM_VK_A,DOM_VK_ACCEPT,DOM_VK_ADD,DOM_VK_ALT,"
                + "DOM_VK_ALTGR,DOM_VK_AMPERSAND,DOM_VK_ASTERISK,DOM_VK_AT,DOM_VK_ATTN,DOM_VK_B,DOM_VK_BACK_QUOTE,"
                + "DOM_VK_BACK_SLASH,DOM_VK_BACK_SPACE,DOM_VK_C,DOM_VK_CANCEL,DOM_VK_CAPS_LOCK,DOM_VK_CIRCUMFLEX,"
                + "DOM_VK_CLEAR,DOM_VK_CLOSE_BRACKET,DOM_VK_CLOSE_CURLY_BRACKET,DOM_VK_CLOSE_PAREN,DOM_VK_COLON,"
                + "DOM_VK_COMMA,DOM_VK_CONTEXT_MENU,DOM_VK_CONTROL,DOM_VK_CONVERT,DOM_VK_CRSEL,DOM_VK_D,"
                + "DOM_VK_DECIMAL,DOM_VK_DELETE,DOM_VK_DIVIDE,DOM_VK_DOLLAR,DOM_VK_DOUBLE_QUOTE,DOM_VK_DOWN,"
                + "DOM_VK_E,DOM_VK_EISU,DOM_VK_END,DOM_VK_EQUALS,DOM_VK_EREOF,DOM_VK_ESCAPE,DOM_VK_EXCLAMATION,"
                + "DOM_VK_EXECUTE,DOM_VK_EXSEL,DOM_VK_F,DOM_VK_F1,DOM_VK_F10,DOM_VK_F11,DOM_VK_F12,DOM_VK_F13,"
                + "DOM_VK_F14,DOM_VK_F15,DOM_VK_F16,DOM_VK_F17,DOM_VK_F18,DOM_VK_F19,DOM_VK_F2,DOM_VK_F20,"
                + "DOM_VK_F21,DOM_VK_F22,DOM_VK_F23,DOM_VK_F24,DOM_VK_F3,DOM_VK_F4,DOM_VK_F5,DOM_VK_F6,DOM_VK_F7,"
                + "DOM_VK_F8,DOM_VK_F9,DOM_VK_FINAL,DOM_VK_G,DOM_VK_GREATER_THAN,DOM_VK_H,DOM_VK_HANGUL,"
                + "DOM_VK_HANJA,DOM_VK_HASH,DOM_VK_HELP,DOM_VK_HOME,DOM_VK_HYPHEN_MINUS,DOM_VK_I,DOM_VK_INSERT,"
                + "DOM_VK_J,DOM_VK_JUNJA,DOM_VK_K,DOM_VK_KANA,DOM_VK_KANJI,DOM_VK_L,DOM_VK_LEFT,DOM_VK_LESS_THAN,"
                + "DOM_VK_M,DOM_VK_META,DOM_VK_MODECHANGE,DOM_VK_MULTIPLY,DOM_VK_N,DOM_VK_NONCONVERT,"
                + "DOM_VK_NUM_LOCK,DOM_VK_NUMPAD0,DOM_VK_NUMPAD1,DOM_VK_NUMPAD2,DOM_VK_NUMPAD3,DOM_VK_NUMPAD4,"
                + "DOM_VK_NUMPAD5,DOM_VK_NUMPAD6,DOM_VK_NUMPAD7,DOM_VK_NUMPAD8,DOM_VK_NUMPAD9,DOM_VK_O,"
                + "DOM_VK_OPEN_BRACKET,DOM_VK_OPEN_CURLY_BRACKET,DOM_VK_OPEN_PAREN,DOM_VK_P,DOM_VK_PA1,"
                + "DOM_VK_PAGE_DOWN,DOM_VK_PAGE_UP,DOM_VK_PAUSE,DOM_VK_PERCENT,DOM_VK_PERIOD,DOM_VK_PIPE,"
                + "DOM_VK_PLAY,DOM_VK_PLUS,DOM_VK_PRINT,DOM_VK_PRINTSCREEN,DOM_VK_Q,DOM_VK_QUESTION_MARK,"
                + "DOM_VK_QUOTE,DOM_VK_R,DOM_VK_RETURN,DOM_VK_RIGHT,DOM_VK_S,DOM_VK_SCROLL_LOCK,DOM_VK_SELECT,"
                + "DOM_VK_SEMICOLON,DOM_VK_SEPARATOR,DOM_VK_SHIFT,DOM_VK_SLASH,DOM_VK_SLEEP,DOM_VK_SPACE,"
                + "DOM_VK_SUBTRACT,DOM_VK_T,DOM_VK_TAB,DOM_VK_TILDE,DOM_VK_U,DOM_VK_UNDERSCORE,DOM_VK_UP,DOM_VK_V,"
                + "DOM_VK_VOLUME_DOWN,DOM_VK_VOLUME_MUTE,DOM_VK_VOLUME_UP,DOM_VK_W,DOM_VK_WIN,DOM_VK_WIN_ICO_00,"
                + "DOM_VK_WIN_ICO_CLEAR,DOM_VK_WIN_ICO_HELP,DOM_VK_WIN_OEM_ATTN,DOM_VK_WIN_OEM_AUTO,"
                + "DOM_VK_WIN_OEM_BACKTAB,DOM_VK_WIN_OEM_CLEAR,DOM_VK_WIN_OEM_COPY,DOM_VK_WIN_OEM_CUSEL,"
                + "DOM_VK_WIN_OEM_ENLW,DOM_VK_WIN_OEM_FINISH,DOM_VK_WIN_OEM_FJ_JISHO,DOM_VK_WIN_OEM_FJ_LOYA,"
                + "DOM_VK_WIN_OEM_FJ_MASSHOU,DOM_VK_WIN_OEM_FJ_ROYA,DOM_VK_WIN_OEM_FJ_TOUROKU,DOM_VK_WIN_OEM_JUMP,"
                + "DOM_VK_WIN_OEM_PA1,DOM_VK_WIN_OEM_PA2,DOM_VK_WIN_OEM_PA3,DOM_VK_WIN_OEM_RESET,"
                + "DOM_VK_WIN_OEM_WSCTRL,DOM_VK_X,DOM_VK_Y,DOM_VK_Z,DOM_VK_ZOOM,getModifierState(),initKeyEvent(),"
                + "isComposing,key,keyCode,location,metaKey,repeat,shiftKey",
            FF60 = "altKey,charCode,code,ctrlKey,DOM_KEY_LOCATION_LEFT,DOM_KEY_LOCATION_NUMPAD,"
                + "DOM_KEY_LOCATION_RIGHT,DOM_KEY_LOCATION_STANDARD,DOM_VK_0,DOM_VK_1,DOM_VK_2,DOM_VK_3,DOM_VK_4,"
                + "DOM_VK_5,DOM_VK_6,DOM_VK_7,DOM_VK_8,DOM_VK_9,DOM_VK_A,DOM_VK_ACCEPT,DOM_VK_ADD,DOM_VK_ALT,"
                + "DOM_VK_ALTGR,DOM_VK_AMPERSAND,DOM_VK_ASTERISK,DOM_VK_AT,DOM_VK_ATTN,DOM_VK_B,DOM_VK_BACK_QUOTE,"
                + "DOM_VK_BACK_SLASH,DOM_VK_BACK_SPACE,DOM_VK_C,DOM_VK_CANCEL,DOM_VK_CAPS_LOCK,DOM_VK_CIRCUMFLEX,"
                + "DOM_VK_CLEAR,DOM_VK_CLOSE_BRACKET,DOM_VK_CLOSE_CURLY_BRACKET,DOM_VK_CLOSE_PAREN,DOM_VK_COLON,"
                + "DOM_VK_COMMA,DOM_VK_CONTEXT_MENU,DOM_VK_CONTROL,DOM_VK_CONVERT,DOM_VK_CRSEL,DOM_VK_D,"
                + "DOM_VK_DECIMAL,DOM_VK_DELETE,DOM_VK_DIVIDE,DOM_VK_DOLLAR,DOM_VK_DOUBLE_QUOTE,DOM_VK_DOWN,"
                + "DOM_VK_E,DOM_VK_EISU,DOM_VK_END,DOM_VK_EQUALS,DOM_VK_EREOF,DOM_VK_ESCAPE,DOM_VK_EXCLAMATION,"
                + "DOM_VK_EXECUTE,DOM_VK_EXSEL,DOM_VK_F,DOM_VK_F1,DOM_VK_F10,DOM_VK_F11,DOM_VK_F12,DOM_VK_F13,"
                + "DOM_VK_F14,DOM_VK_F15,DOM_VK_F16,DOM_VK_F17,DOM_VK_F18,DOM_VK_F19,DOM_VK_F2,DOM_VK_F20,"
                + "DOM_VK_F21,DOM_VK_F22,DOM_VK_F23,DOM_VK_F24,DOM_VK_F3,DOM_VK_F4,DOM_VK_F5,DOM_VK_F6,DOM_VK_F7,"
                + "DOM_VK_F8,DOM_VK_F9,DOM_VK_FINAL,DOM_VK_G,DOM_VK_GREATER_THAN,DOM_VK_H,DOM_VK_HANGUL,"
                + "DOM_VK_HANJA,DOM_VK_HASH,DOM_VK_HELP,DOM_VK_HOME,DOM_VK_HYPHEN_MINUS,DOM_VK_I,DOM_VK_INSERT,"
                + "DOM_VK_J,DOM_VK_JUNJA,DOM_VK_K,DOM_VK_KANA,DOM_VK_KANJI,DOM_VK_L,DOM_VK_LEFT,DOM_VK_LESS_THAN,"
                + "DOM_VK_M,DOM_VK_META,DOM_VK_MODECHANGE,DOM_VK_MULTIPLY,DOM_VK_N,DOM_VK_NONCONVERT,"
                + "DOM_VK_NUM_LOCK,DOM_VK_NUMPAD0,DOM_VK_NUMPAD1,DOM_VK_NUMPAD2,DOM_VK_NUMPAD3,DOM_VK_NUMPAD4,"
                + "DOM_VK_NUMPAD5,DOM_VK_NUMPAD6,DOM_VK_NUMPAD7,DOM_VK_NUMPAD8,DOM_VK_NUMPAD9,DOM_VK_O,"
                + "DOM_VK_OPEN_BRACKET,DOM_VK_OPEN_CURLY_BRACKET,DOM_VK_OPEN_PAREN,DOM_VK_P,DOM_VK_PA1,"
                + "DOM_VK_PAGE_DOWN,DOM_VK_PAGE_UP,DOM_VK_PAUSE,DOM_VK_PERCENT,DOM_VK_PERIOD,DOM_VK_PIPE,"
                + "DOM_VK_PLAY,DOM_VK_PLUS,DOM_VK_PRINT,DOM_VK_PRINTSCREEN,DOM_VK_Q,DOM_VK_QUESTION_MARK,"
                + "DOM_VK_QUOTE,DOM_VK_R,DOM_VK_RETURN,DOM_VK_RIGHT,DOM_VK_S,DOM_VK_SCROLL_LOCK,DOM_VK_SELECT,"
                + "DOM_VK_SEMICOLON,DOM_VK_SEPARATOR,DOM_VK_SHIFT,DOM_VK_SLASH,DOM_VK_SLEEP,DOM_VK_SPACE,"
                + "DOM_VK_SUBTRACT,DOM_VK_T,DOM_VK_TAB,DOM_VK_TILDE,DOM_VK_U,DOM_VK_UNDERSCORE,DOM_VK_UP,DOM_VK_V,"
                + "DOM_VK_VOLUME_DOWN,DOM_VK_VOLUME_MUTE,DOM_VK_VOLUME_UP,DOM_VK_W,DOM_VK_WIN,DOM_VK_WIN_ICO_00,"
                + "DOM_VK_WIN_ICO_CLEAR,DOM_VK_WIN_ICO_HELP,DOM_VK_WIN_OEM_ATTN,DOM_VK_WIN_OEM_AUTO,"
                + "DOM_VK_WIN_OEM_BACKTAB,DOM_VK_WIN_OEM_CLEAR,DOM_VK_WIN_OEM_COPY,DOM_VK_WIN_OEM_CUSEL,"
                + "DOM_VK_WIN_OEM_ENLW,DOM_VK_WIN_OEM_FINISH,DOM_VK_WIN_OEM_FJ_JISHO,DOM_VK_WIN_OEM_FJ_LOYA,"
                + "DOM_VK_WIN_OEM_FJ_MASSHOU,DOM_VK_WIN_OEM_FJ_ROYA,DOM_VK_WIN_OEM_FJ_TOUROKU,DOM_VK_WIN_OEM_JUMP,"
                + "DOM_VK_WIN_OEM_PA1,DOM_VK_WIN_OEM_PA2,DOM_VK_WIN_OEM_PA3,DOM_VK_WIN_OEM_RESET,"
                + "DOM_VK_WIN_OEM_WSCTRL,DOM_VK_X,DOM_VK_Y,DOM_VK_Z,DOM_VK_ZOOM,getModifierState(),"
                + "initKeyboardEvent(),initKeyEvent(),isComposing,key,keyCode,location,metaKey,repeat,shiftKey",
            IE = "altKey,char,charCode,ctrlKey,DOM_KEY_LOCATION_JOYSTICK,DOM_KEY_LOCATION_LEFT,"
                + "DOM_KEY_LOCATION_MOBILE,DOM_KEY_LOCATION_NUMPAD,DOM_KEY_LOCATION_RIGHT,DOM_KEY_LOCATION_STANDARD,"
                + "getModifierState(),initKeyboardEvent(),key,keyCode,locale,location,metaKey,repeat,shiftKey,"
                + "which",
            EDGE = "altKey,char,charCode,ctrlKey,DOM_KEY_LOCATION_JOYSTICK,DOM_KEY_LOCATION_LEFT,"
                + "DOM_KEY_LOCATION_MOBILE,DOM_KEY_LOCATION_NUMPAD,DOM_KEY_LOCATION_RIGHT,DOM_KEY_LOCATION_STANDARD,"
                + "getModifierState(),initKeyboardEvent(),key,keyCode,locale,location,metaKey,repeat,shiftKey,"
                + "which")
    @BrowserRunner.NotYetImplemented
    public void keyboardEvent() throws Exception {
        testString("document.createEvent('KeyboardEvent'), document.createEvent('UIEvent')");
    }

    /**
     * Test {@link UIEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "detail,initUIEvent(),sourceCapabilities,view,which",
            FF52 = "detail,initUIEvent(),isChar,layerX,layerY,pageX,pageY,rangeOffset,rangeParent,"
                + "SCROLL_PAGE_DOWN,SCROLL_PAGE_UP,view,which",
            FF60 = "detail,initUIEvent(),layerX,layerY,pageX,pageY,rangeOffset,rangeParent,"
                + "SCROLL_PAGE_DOWN,SCROLL_PAGE_UP,view,which",
            IE = "detail,initUIEvent(),view")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF})
    public void uiEvent() throws Exception {
        testString("document.createEvent('UIEvent'), document.createEvent('Event')");
    }

    /**
     * Test {@link DragEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "dataTransfer",
            FF52 = "dataTransfer,initDragEvent()",
            FF60 = "dataTransfer,initDragEvent()",
            IE = "dataTransfer,initDragEvent(),msConvertURL()")
    @BrowserRunner.NotYetImplemented
    public void dragEvent() throws Exception {
        testString("document.createEvent('DragEvent'), document.createEvent('MouseEvent')");
    }

    /**
     * Test {@link PointerEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "getCoalescedEvents(),height,isPrimary,pointerId,pointerType,pressure,"
                + "tangentialPressure,tiltX,tiltY,twist,width",
            FF52 = "exception",
            FF60 = "getCoalescedEvents(),height,isPrimary,pointerId,pointerType,pressure,"
                + "tangentialPressure,tiltX,tiltY,twist,width",
            IE = "exception")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF60})
    public void pointerEvent() throws Exception {
        testString("new PointerEvent('click'), document.createEvent('MouseEvent')");
    }

    /**
     * Test {@link PointerEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "exception",
            FF52 = "exception",
            FF60 = "exception",
            IE = "height,hwTimestamp,initPointerEvent(),isPrimary,pointerId,"
                + "pointerType,pressure,rotation,tiltX,tiltY,width")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void pointerEvent2() throws Exception {
        testString(" document.createEvent('PointerEvent'), document.createEvent('MouseEvent')");
    }

    /**
     * Test {@link WheelEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "deltaMode,deltaX,deltaY,deltaZ,DOM_DELTA_LINE,DOM_DELTA_PAGE,"
                + "DOM_DELTA_PIXEL,wheelDelta,wheelDeltaX,wheelDeltaY",
            FF52 = "exception",
            FF60 = "exception",
            IE = "deltaMode,deltaX,deltaY,deltaZ,DOM_DELTA_LINE,DOM_DELTA_PAGE,DOM_DELTA_PIXEL,initWheelEvent()")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.IE})
    public void wheelEvent() throws Exception {
        testString("document.createEvent('WheelEvent'), document.createEvent('MouseEvent')");
    }

    /**
     * Test {@link MouseEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "altKey,button,buttons,clientX,clientY,ctrlKey,fromElement,getModifierState(),"
                + "initMouseEvent(),layerX,layerY,metaKey,movementX,movementY,offsetX,offsetY,"
                + "pageX,pageY,relatedTarget,screenX,screenY,shiftKey,toElement,x,y",
            FF52 = "altKey,button,buttons,clientX,clientY,ctrlKey,getModifierState(),initMouseEvent(),"
                + "initNSMouseEvent(),metaKey,movementX,movementY,MOZ_SOURCE_CURSOR,MOZ_SOURCE_ERASER,"
                + "MOZ_SOURCE_KEYBOARD,MOZ_SOURCE_MOUSE,MOZ_SOURCE_PEN,MOZ_SOURCE_TOUCH,MOZ_SOURCE_UNKNOWN,"
                + "mozInputSource,mozPressure,offsetX,offsetY,region,relatedTarget,screenX,screenY,shiftKey",
            FF60 = "altKey,button,buttons,clientX,clientY,ctrlKey,getModifierState(),initMouseEvent(),"
                + "initNSMouseEvent(),metaKey,movementX,movementY,MOZ_SOURCE_CURSOR,MOZ_SOURCE_ERASER,"
                + "MOZ_SOURCE_KEYBOARD,MOZ_SOURCE_MOUSE,MOZ_SOURCE_PEN,MOZ_SOURCE_TOUCH,MOZ_SOURCE_UNKNOWN,"
                + "mozInputSource,mozPressure,offsetX,offsetY,region,relatedTarget,screenX,screenY,shiftKey,x,y",
            IE = "altKey,button,buttons,clientX,clientY,ctrlKey,fromElement,getModifierState(),initMouseEvent(),"
                + "layerX,layerY,metaKey,offsetX,offsetY,pageX,pageY,relatedTarget,screenX,screenY,shiftKey,"
                + "toElement,which,x,y")
    @BrowserRunner.NotYetImplemented
    public void mouseEvent() throws Exception {
        testString("document.createEvent('MouseEvent'), document.createEvent('UIEvent')");
    }

    /**
     * Test {@link CompositionEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "data,initCompositionEvent()",
            FF60 = "data,initCompositionEvent(),locale",
            FF52 = "data,initCompositionEvent(),locale",
            IE = "data,initCompositionEvent(),locale")
    @BrowserRunner.NotYetImplemented
    public void compositionEvent() throws Exception {
        testString("document.createEvent('CompositionEvent'), document.createEvent('UIEvent')");
    }

    /**
     * Test {@link FocusEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "relatedTarget",
            FF52 = "exception",
            FF60 = "relatedTarget",
            IE = "initFocusEvent(),relatedTarget")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF60, BrowserRunner.TestedBrowser.IE})
    public void focusEvent() throws Exception {
        testString("document.createEvent('FocusEvent'), document.createEvent('UIEvent')");
    }

    /**
     * Test {@link InputEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "data,dataTransfer,getTargetRanges(),inputType,isComposing",
            FF52 = "isComposing",
            FF60 = "isComposing",
            IE = "exception")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF})
    public void inputEvent() throws Exception {
        testString("new InputEvent('input'), document.createEvent('UIEvent')");
    }

    /**
     * Test {@link MouseWheelEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "exception",
            FF52 = "exception",
            FF60 = "exception",
            IE = "altKey,button,buttons,clientX,clientY,ctrlKey,fromElement,getModifierState(),initMouseEvent(),"
                + "initMouseWheelEvent(),layerX,layerY,metaKey,offsetX,offsetY,pageX,pageY,relatedTarget,"
                + "screenX,screenY,shiftKey,toElement,wheelDelta,which,x,y")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void mouseWheelEvent() throws Exception {
        testString("document.createEvent('MouseWheelEvent'), document.createEvent('UIEvent')");
    }

    /**
     * Test {@link SVGZoomEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "exception",
            FF52 = "newScale,newTranslate,previousScale,previousTranslate",
            FF60 = "exception",
            IE = "exception")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.FF52)
    public void svgZoomEvent() throws Exception {
        testString("document.createEvent('SVGZoomEvent'), document.createEvent('UIEvent')");
    }

    /**
     * Test {@link TextEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "data,initTextEvent()",
            FF52 = "data,initCompositionEvent(),locale",
            FF60 = "data,initCompositionEvent(),locale",
            IE = "data,DOM_INPUT_METHOD_DROP,DOM_INPUT_METHOD_HANDWRITING,DOM_INPUT_METHOD_IME,"
                + "DOM_INPUT_METHOD_KEYBOARD,DOM_INPUT_METHOD_MULTIMODAL,DOM_INPUT_METHOD_OPTION,"
                + "DOM_INPUT_METHOD_PASTE,DOM_INPUT_METHOD_SCRIPT,DOM_INPUT_METHOD_UNKNOWN,"
                + "DOM_INPUT_METHOD_VOICE,initTextEvent(),inputMethod,locale")
    @BrowserRunner.NotYetImplemented
    public void textEvent() throws Exception {
        testString("document.createEvent('TextEvent'), document.createEvent('UIEvent')");
    }

    /**
     * Test {@link TouchEvent}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "altKey,changedTouches,ctrlKey,metaKey,shiftKey,targetTouches,touches",
            FF52 = "exception",
            FF60 = "exception",
            IE = "exception")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.CHROME)
    public void touchEvent2() throws Exception {
        testString("new TouchEvent('touch'), document.createEvent('UIEvent')");
    }

    /**
     * Test {@link HtmlSlot}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            CHROME = "assignedElements(),assignedNodes(),name")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.CHROME)
    public void slot() throws Exception {
        test("slot");
    }

    /**
     * Test {@link HTMLDocument}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "-",
            FF52 = "alinkColor,all,anchors,applets,bgColor,body,captureEvents(),clear(),close(),cookie,designMode,"
                + "domain,"
                + "embeds,execCommand(),fgColor,forms,getElementsByName(),getSelection(),head,images,"
                + "linkColor,links,"
                + "open(),plugins,queryCommandEnabled(),queryCommandIndeterm(),queryCommandState(),"
                + "queryCommandSupported(),queryCommandValue(),releaseEvents(),scripts,vlinkColor,write(),writeln()",
            FF60 = "alinkColor,all,anchors,applets,bgColor,captureEvents(),clear(),close(),cookie,designMode,"
                + "domain,embeds,execCommand(),fgColor,forms,head,images,"
                + "linkColor,links,"
                + "open(),plugins,queryCommandEnabled(),queryCommandIndeterm(),queryCommandState(),"
                + "queryCommandSupported(),queryCommandValue(),releaseEvents(),scripts,vlinkColor,write(),writeln()")
    @BrowserRunner.NotYetImplemented
    public void htmlDocument() throws Exception {
        testString("document, xmlDocument");
    }

    /**
     * Test {@link Document}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "activeElement,adoptNode(),alinkColor,all,anchors,append(),applets,"
                + "bgColor,body,captureEvents(),caretRangeFromPoint(),characterSet,"
                + "charset,childElementCount,children,clear(),close(),compatMode,contentType,cookie,createAttribute(),"
                + "createAttributeNS(),createCDATASection(),createComment(),createDocumentFragment(),createElement(),"
                + "createElementNS(),createEvent(),createExpression(),createNodeIterator(),createNSResolver(),"
                + "createProcessingInstruction(),createRange(),createTextNode(),createTreeWalker(),currentScript,"
                + "defaultView,designMode,dir,doctype,documentElement,documentURI,domain,elementFromPoint(),"
                + "elementsFromPoint(),embeds,evaluate(),execCommand(),exitPointerLock(),"
                + "fgColor,firstElementChild,fonts,forms,"
                + "getElementById(),getElementsByClassName(),getElementsByName(),getElementsByTagName(),"
                + "getElementsByTagNameNS(),getSelection(),hasFocus(),head,hidden,images,implementation,importNode(),"
                + "inputEncoding,lastElementChild,lastModified,"
                + "linkColor,links,location,onabort,onauxclick,onbeforecopy,"
                + "onbeforecut,onbeforepaste,onblur,oncancel,oncanplay,oncanplaythrough,onchange,onclick,onclose,"
                + "oncontextmenu,oncopy,oncuechange,oncut,ondblclick,ondrag,ondragend,ondragenter,ondragleave,"
                + "ondragover,ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,onfocus,onfreeze,"
                + "ongotpointercapture,oninput,oninvalid,onkeydown,onkeypress,onkeyup,onload,onloadeddata,"
                + "onloadedmetadata,onloadstart,onlostpointercapture,onmousedown,onmouseenter,onmouseleave,onmousemove,"
                + "onmouseout,onmouseover,onmouseup,onmousewheel,onpaste,onpause,onplay,onplaying,onpointercancel,"
                + "onpointerdown,onpointerenter,onpointerleave,onpointerlockchange,onpointerlockerror,onpointermove,"
                + "onpointerout,onpointerover,onpointerup,onprogress,onratechange,onreadystatechange,onreset,onresize,"
                + "onresume,onscroll,onsearch,onseeked,onseeking,onselect,onselectionchange,onselectstart,onstalled,"
                + "onsubmit,onsuspend,ontimeupdate,ontoggle,onvisibilitychange,onvolumechange,onwaiting,"
                + "onwebkitfullscreenchange,"
                + "onwebkitfullscreenerror,onwheel,open(),origin,plugins,pointerLockElement,"
                + "prepend(),queryCommandEnabled(),queryCommandIndeterm(),queryCommandState(),queryCommandSupported(),"
                + "queryCommandValue(),querySelector(),querySelectorAll(),readyState,referrer,registerElement(),"
                + "releaseEvents(),rootElement,"
                + "scripts,scrollingElement,styleSheets,title,URL,"
                + "visibilityState,vlinkColor,wasDiscarded,"
                + "webkitCancelFullScreen(),webkitCurrentFullScreenElement,webkitExitFullscreen(),"
                + "webkitFullscreenElement,webkitFullscreenEnabled,webkitHidden,webkitIsFullScreen,"
                + "webkitVisibilityState,write(),writeln(),xmlEncoding,xmlStandalone,xmlVersion",
            FF52 = "activeElement,adoptNode(),append(),async,caretPositionFromPoint(),characterSet,charset,"
                + "childElementCount,children,compatMode,contentType,createAttribute(),createAttributeNS(),"
                + "createCDATASection(),createComment(),createDocumentFragment(),createElement(),"
                + "createElementNS(),createEvent(),createExpression(),createNodeIterator(),createNSResolver(),"
                + "createProcessingInstruction(),createRange(),createTextNode(),createTreeWalker(),"
                + "currentScript,defaultView,dir,doctype,documentElement,documentURI,elementFromPoint(),"
                + "elementsFromPoint(),enableStyleSheetsForSet(),evaluate(),exitPointerLock(),"
                + "firstElementChild,fonts,getElementById(),getElementsByClassName(),getElementsByTagName(),"
                + "getElementsByTagNameNS(),hasFocus(),hidden,implementation,importNode(),inputEncoding,"
                + "lastElementChild,lastModified,lastStyleSheetSet,load(),location,mozCancelFullScreen(),"
                + "mozFullScreen,mozFullScreenElement,mozFullScreenEnabled,mozSetImageElement(),onabort,"
                + "onafterscriptexecute,onanimationend,onanimationiteration,onanimationstart,"
                + "onbeforescriptexecute,onblur,oncanplay,oncanplaythrough,onchange,onclick,oncontextmenu,"
                + "oncopy,oncut,ondblclick,ondrag,ondragend,ondragenter,ondragexit,ondragleave,ondragover,"
                + "ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,onfocus,oninput,oninvalid,"
                + "onkeydown,onkeypress,onkeyup,onload,onloadeddata,onloadedmetadata,onloadend,onloadstart,"
                + "onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,"
                + "onmozfullscreenchange,onmozfullscreenerror,onpaste,onpause,onplay,onplaying,"
                + "onpointerlockchange,onpointerlockerror,onprogress,onratechange,onreadystatechange,"
                + "onreset,onresize,onscroll,onseeked,onseeking,onselect,onselectionchange,onselectstart,"
                + "onshow,onstalled,onsubmit,onsuspend,ontimeupdate,ontoggle,ontransitionend,"
                + "onvolumechange,onwaiting,onwebkitanimationend,onwebkitanimationiteration,onwebkitanimationstart,"
                + "onwebkittransitionend,onwheel,pointerLockElement,preferredStyleSheetSet,prepend(),"
                + "querySelector(),querySelectorAll(),readyState,referrer,releaseCapture(),rootElement,"
                + "scrollingElement,selectedStyleSheetSet,styleSheets,styleSheetSets,title,URL,visibilityState",
            FF60 = "activeElement,adoptNode(),append(),async,body,"
                + "caretPositionFromPoint(),characterSet,charset,childElementCount,"
                + "children,compatMode,contentType,createAttribute(),createAttributeNS(),createCDATASection(),"
                + "createComment(),createDocumentFragment(),createElement(),createElementNS(),createEvent(),"
                + "createExpression(),createNodeIterator(),createNSResolver(),createProcessingInstruction(),"
                + "createRange(),createTextNode(),createTreeWalker(),currentScript,defaultView,dir,doctype,"
                + "documentElement,documentURI,elementFromPoint(),elementsFromPoint(),"
                + "enableStyleSheetsForSet(),evaluate(),exitPointerLock(),"
                + "firstElementChild,fonts,getElementById(),getElementsByClassName(),getElementsByName(),"
                + "getElementsByTagName(),"
                + "getElementsByTagNameNS(),getSelection(),hasFocus(),hidden,implementation,importNode(),inputEncoding,"
                + "lastElementChild,lastModified,lastStyleSheetSet,load(),location,mozCancelFullScreen(),"
                + "mozFullScreen,mozFullScreenElement,mozFullScreenEnabled,"
                + "mozSetImageElement(),onabort,onafterscriptexecute,"
                + "onanimationcancel,onanimationend,onanimationiteration,onanimationstart,onauxclick,"
                + "onbeforescriptexecute,onblur,oncanplay,oncanplaythrough,onchange,onclick,onclose,"
                + "oncontextmenu,oncopy,"
                + "oncut,ondblclick,ondrag,ondragend,ondragenter,ondragexit,ondragleave,ondragover,ondragstart,ondrop,"
                + "ondurationchange,onemptied,onended,onerror,onfocus,ongotpointercapture,oninput,"
                + "oninvalid,onkeydown,onkeypress,"
                + "onkeyup,onload,onloadeddata,onloadedmetadata,onloadend,onloadstart,onlostpointercapture,"
                + "onmousedown,onmouseenter,onmouseleave,"
                + "onmousemove,onmouseout,onmouseover,onmouseup,onmozfullscreenchange,onmozfullscreenerror,"
                + "onpaste,onpause,onplay,onplaying,onpointercancel,onpointerdown,onpointerenter,onpointerleave,"
                + "onpointerlockchange,onpointerlockerror,onpointermove,onpointerout,onpointerover,onpointerup,"
                + "onprogress,"
                + "onratechange,onreadystatechange,onreset,onresize,onscroll,onseeked,onseeking,onselect,"
                + "onselectionchange,onselectstart,onshow,"
                + "onstalled,onsubmit,onsuspend,ontimeupdate,ontoggle,ontransitioncancel,ontransitionend,"
                + "ontransitionrun,ontransitionstart,onvisibilitychange,onvolumechange,onwaiting,"
                + "onwebkitanimationend,onwebkitanimationiteration,onwebkitanimationstart,onwebkittransitionend,"
                + "onwheel,pointerLockElement,preferredStyleSheetSet,"
                + "prepend(),querySelector(),querySelectorAll(),readyState,referrer,releaseCapture(),rootElement,"
                + "scrollingElement,"
                + "selectedStyleSheetSet,styleSheets,styleSheetSets,title,URL,visibilityState",
            IE = "activeElement,adoptNode(),alinkColor,all,anchors,applets,bgColor,body,captureEvents(),characterSet,"
                + "charset,clear(),close(),compatible,compatMode,cookie,createAttribute(),createAttributeNS(),"
                + "createCDATASection(),createComment(),createDocumentFragment(),createElement(),createElementNS(),"
                + "createEvent(),createNodeIterator(),createProcessingInstruction(),createRange(),createTextNode(),"
                + "createTreeWalker(),defaultCharset,defaultView,designMode,dir,doctype,documentElement,documentMode,"
                + "domain,elementFromPoint(),embeds,execCommand(),execCommandShowHelp(),fgColor,fileCreatedDate,"
                + "fileModifiedDate,fileUpdatedDate,focus(),forms,frames,getElementById(),getElementsByClassName(),"
                + "getElementsByName(),getElementsByTagName(),getElementsByTagNameNS(),getSelection(),hasFocus(),head,"
                + "hidden,images,implementation,importNode(),inputEncoding,lastModified,linkColor,links,location,media,"
                + "mimeType,msCapsLockWarningOff,msCSSOMElementFloatMetrics,msElementsFromPoint(),msElementsFromRect(),"
                + "msExitFullscreen(),msFullscreenElement,msFullscreenEnabled,msHidden,msVisibilityState,nameProp,"
                + "onabort,onactivate,onbeforeactivate,onbeforedeactivate,onblur,oncanplay,oncanplaythrough,onchange,"
                + "onclick,oncontextmenu,ondblclick,ondeactivate,ondrag,ondragend,ondragenter,ondragleave,ondragover,"
                + "ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,onfocus,onfocusin,onfocusout,onhelp,"
                + "oninput,onkeydown,onkeypress,onkeyup,onload,onloadeddata,onloadedmetadata,onloadstart,onmousedown,"
                + "onmousemove,onmouseout,onmouseover,onmouseup,onmousewheel,onmscontentzoom,onmsfullscreenchange,"
                + "onmsfullscreenerror,onmsgesturechange,onmsgesturedoubletap,onmsgestureend,onmsgesturehold,"
                + "onmsgesturestart,onmsgesturetap,onmsinertiastart,onmsmanipulationstatechanged,onmspointercancel,"
                + "onmspointerdown,onmspointerenter,onmspointerleave,onmspointermove,onmspointerout,onmspointerover,"
                + "onmspointerup,onmssitemodejumplistitemremoved,onmsthumbnailclick,onpause,onplay,onplaying,"
                + "onpointercancel,onpointerdown,onpointerenter,onpointerleave,onpointermove,onpointerout,"
                + "onpointerover,onpointerup,onprogress,onratechange,onreadystatechange,onreset,onscroll,onseeked,"
                + "onseeking,onselect,onselectionchange,onselectstart,onstalled,onstop,onstoragecommit,onsubmit,"
                + "onsuspend,ontimeupdate,onvolumechange,onwaiting,open(),parentWindow,plugins,protocol,"
                + "queryCommandEnabled(),queryCommandIndeterm(),queryCommandState(),queryCommandSupported(),"
                + "queryCommandText(),queryCommandValue(),querySelector(),querySelectorAll(),readyState,referrer,"
                + "releaseCapture(),releaseEvents(),rootElement,scripts,security,styleSheets,title,uniqueID,"
                + "updateSettings(),URL,URLUnencoded,visibilityState,vlinkColor,write(),writeln(),xmlEncoding,"
                + "xmlStandalone,xmlVersion")
    @BrowserRunner.NotYetImplemented
    //IE expectations are bigger than real IE alert length, test should be changed to store value in textarea
    public void document() throws Exception {
        testString("xmlDocument, document.createTextNode('some text')");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "blur(),dataset,focus(),nonce,"
                + "onabort,onauxclick,onblur,oncancel,oncanplay,oncanplaythrough,onchange,"
                + "onclick,onclose,oncontextmenu,oncuechange,ondblclick,ondrag,ondragend,ondragenter,ondragleave,"
                + "ondragover,ondragstart,ondrop,ondurationchange,onemptied,onended,onerror,onfocus,"
                + "ongotpointercapture,oninput,oninvalid,onkeydown,onkeypress,onkeyup,onload,onloadeddata,"
                + "onloadedmetadata,onloadstart,onlostpointercapture,onmousedown,onmouseenter,onmouseleave,onmousemove,"
                + "onmouseout,onmouseover,onmouseup,onmousewheel,onpause,onplay,onplaying,onpointercancel,"
                + "onpointerdown,onpointerenter,onpointerleave,onpointermove,onpointerout,onpointerover,onpointerup,"
                + "onprogress,onratechange,onreset,onresize,onscroll,onseeked,onseeking,onselect,onstalled,"
                + "onsubmit,onsuspend,ontimeupdate,ontoggle,onvolumechange,onwaiting,onwheel,"
                + "ownerSVGElement,style,tabIndex,"
                + "viewportElement",
            FF52 = "blur(),dataset,focus(),onabort,onanimationend,onanimationiteration,onanimationstart,onblur,"
                + "oncanplay,oncanplaythrough,onchange,onclick,oncontextmenu,oncopy,oncut,ondblclick,ondrag,"
                + "ondragend,ondragenter,ondragexit,ondragleave,ondragover,ondragstart,ondrop,ondurationchange,"
                + "onemptied,onended,onerror,onfocus,oninput,oninvalid,onkeydown,onkeypress,onkeyup,onload,"
                + "onloadeddata,onloadedmetadata,onloadend,onloadstart,onmousedown,onmouseenter,onmouseleave,"
                + "onmousemove,onmouseout,onmouseover,onmouseup,onmozfullscreenchange,onmozfullscreenerror,"
                + "onpaste,onpause,onplay,onplaying,onprogress,onratechange,onreset,onresize,onscroll,onseeked,"
                + "onseeking,onselect,onselectstart,onshow,onstalled,onsubmit,onsuspend,ontimeupdate,ontoggle,"
                + "ontransitionend,onvolumechange,onwaiting,onwebkitanimationend,onwebkitanimationiteration,"
                + "onwebkitanimationstart,onwebkittransitionend,ownerSVGElement,style,tabIndex,viewportElement",
            FF60 = "blur(),dataset,focus(),"
                + "onabort,onanimationcancel,onanimationend,onanimationiteration,onanimationstart,onauxclick,"
                + "onblur,oncanplay,oncanplaythrough,onchange,onclick,onclose,oncontextmenu,oncopy,oncut,ondblclick,"
                + "ondrag,ondragend,ondragenter,ondragexit,ondragleave,ondragover,ondragstart,ondrop,ondurationchange,"
                + "onemptied,onended,onerror,onfocus,ongotpointercapture,oninput,oninvalid,onkeydown,onkeypress,"
                + "onkeyup,onload,"
                + "onloadeddata,onloadedmetadata,onloadend,onloadstart,onlostpointercapture,onmousedown,"
                + "onmouseenter,onmouseleave,onmousemove,"
                + "onmouseout,onmouseover,onmouseup,onmozfullscreenchange,onmozfullscreenerror,"
                + "onpaste,onpause,onplay,onplaying,onpointercancel,onpointerdown,onpointerenter,onpointerleave,"
                + "onpointermove,onpointerout,onpointerover,onpointerup,onprogress,"
                + "onratechange,onreset,onresize,onscroll,onseeked,onseeking,onselect,onselectstart,onshow,onstalled,"
                + "onsubmit,onsuspend,ontimeupdate,ontoggle,ontransitioncancel,ontransitionend,ontransitionrun,"
                + "ontransitionstart,onvolumechange,onwaiting,onwebkitanimationend,onwebkitanimationiteration,"
                + "onwebkitanimationstart,onwebkittransitionend,onwheel,ownerSVGElement,style,tabIndex,viewportElement",
            IE = "-")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF})
    public void svgElement() throws Exception {
        testString("svg, element");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "appendChild(),ATTRIBUTE_NODE,baseURI,CDATA_SECTION_NODE,childNodes,cloneNode(),COMMENT_NODE,"
                + "compareDocumentPosition(),contains(),DOCUMENT_FRAGMENT_NODE,DOCUMENT_NODE,"
                + "DOCUMENT_POSITION_CONTAINED_BY,DOCUMENT_POSITION_CONTAINS,DOCUMENT_POSITION_DISCONNECTED,"
                + "DOCUMENT_POSITION_FOLLOWING,DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC,DOCUMENT_POSITION_PRECEDING,"
                + "DOCUMENT_TYPE_NODE,ELEMENT_NODE,ENTITY_NODE,ENTITY_REFERENCE_NODE,firstChild,getRootNode(),"
                + "hasChildNodes(),insertBefore(),isConnected,isDefaultNamespace(),isEqualNode(),isSameNode(),"
                + "lastChild,localName,lookupNamespaceURI(),lookupPrefix(),name,namespaceURI,nextSibling,nodeName,"
                + "nodeType,nodeValue,normalize(),NOTATION_NODE,ownerDocument,ownerElement,parentElement,parentNode,"
                + "prefix,previousSibling,PROCESSING_INSTRUCTION_NODE,removeChild(),replaceChild(),specified,TEXT_NODE,"
                + "textContent,value",
            FF52 = "addEventListener(),appendChild(),ATTRIBUTE_NODE,baseURI,CDATA_SECTION_NODE,childNodes,cloneNode(),"
                + "COMMENT_NODE,compareDocumentPosition(),contains(),dispatchEvent(),DOCUMENT_FRAGMENT_NODE,"
                + "DOCUMENT_NODE,DOCUMENT_POSITION_CONTAINED_BY,DOCUMENT_POSITION_CONTAINS,"
                + "DOCUMENT_POSITION_DISCONNECTED,DOCUMENT_POSITION_FOLLOWING,"
                + "DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC,DOCUMENT_POSITION_PRECEDING,DOCUMENT_TYPE_NODE,"
                + "ELEMENT_NODE,ENTITY_NODE,ENTITY_REFERENCE_NODE,firstChild,hasChildNodes(),"
                + "insertBefore(),isConnected,isDefaultNamespace(),isEqualNode(),isSameNode(),lastChild,localName,"
                + "lookupNamespaceURI(),lookupPrefix(),name,namespaceURI,nextSibling,nodeName,nodeType,nodeValue,"
                + "normalize(),NOTATION_NODE,ownerDocument,ownerElement,parentElement,parentNode,prefix,"
                + "previousSibling,PROCESSING_INSTRUCTION_NODE,removeChild(),removeEventListener(),replaceChild(),"
                + "specified,TEXT_NODE,textContent,value",
            FF60 = "appendChild(),ATTRIBUTE_NODE,baseURI,CDATA_SECTION_NODE,childNodes,cloneNode(),"
                + "COMMENT_NODE,compareDocumentPosition(),contains(),DOCUMENT_FRAGMENT_NODE,"
                + "DOCUMENT_NODE,DOCUMENT_POSITION_CONTAINED_BY,DOCUMENT_POSITION_CONTAINS,"
                + "DOCUMENT_POSITION_DISCONNECTED,DOCUMENT_POSITION_FOLLOWING,"
                + "DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC,DOCUMENT_POSITION_PRECEDING,DOCUMENT_TYPE_NODE,"
                + "ELEMENT_NODE,ENTITY_NODE,ENTITY_REFERENCE_NODE,firstChild,getRootNode(),hasChildNodes(),"
                + "insertBefore(),isConnected,isDefaultNamespace(),isEqualNode(),isSameNode(),lastChild,localName,"
                + "lookupNamespaceURI(),lookupPrefix(),name,namespaceURI,nextSibling,nodeName,nodeType,nodeValue,"
                + "normalize(),NOTATION_NODE,ownerDocument,ownerElement,parentElement,parentNode,prefix,"
                + "previousSibling,PROCESSING_INSTRUCTION_NODE,removeChild(),replaceChild(),"
                + "specified,TEXT_NODE,textContent,value",
            IE = "addEventListener(),appendChild(),ATTRIBUTE_NODE,attributes,CDATA_SECTION_NODE,childNodes,cloneNode(),"
                + "COMMENT_NODE,compareDocumentPosition(),dispatchEvent(),DOCUMENT_FRAGMENT_NODE,DOCUMENT_NODE,"
                + "DOCUMENT_POSITION_CONTAINED_BY,DOCUMENT_POSITION_CONTAINS,DOCUMENT_POSITION_DISCONNECTED,"
                + "DOCUMENT_POSITION_FOLLOWING,DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC,DOCUMENT_POSITION_PRECEDING,"
                + "DOCUMENT_TYPE_NODE,ELEMENT_NODE,ENTITY_NODE,ENTITY_REFERENCE_NODE,expando,firstChild,"
                + "hasAttributes(),hasChildNodes(),insertBefore(),isDefaultNamespace(),isEqualNode(),isSameNode(),"
                + "isSupported(),lastChild,localName,lookupNamespaceURI(),lookupPrefix(),name,namespaceURI,nextSibling,"
                + "nodeName,nodeType,nodeValue,normalize(),NOTATION_NODE,ownerDocument,ownerElement,parentNode,prefix,"
                + "previousSibling,PROCESSING_INSTRUCTION_NODE,removeChild(),removeEventListener(),replaceChild(),"
                + "specified,TEXT_NODE,textContent,value")
    @BrowserRunner.NotYetImplemented
    public void nodeAndAttr() throws Exception {
        testString("document.createAttribute('some_attrib'), window.performance");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "cloneContents(),cloneRange(),collapse(),collapsed,commonAncestorContainer,"
                + "compareBoundaryPoints(),comparePoint(),createContextualFragment(),deleteContents(),detach(),"
                + "END_TO_END,END_TO_START,endContainer,endOffset,expand(),extractContents(),getBoundingClientRect(),"
                + "getClientRects(),insertNode(),intersectsNode(),isPointInRange(),selectNode(),selectNodeContents(),"
                + "setEnd(),setEndAfter(),setEndBefore(),setStart(),setStartAfter(),setStartBefore(),START_TO_END,"
                + "START_TO_START,startContainer,startOffset,surroundContents()",
            IE = "cloneContents(),cloneRange(),collapse(),collapsed,commonAncestorContainer,compareBoundaryPoints(),"
                + "createContextualFragment(),deleteContents(),detach(),END_TO_END,END_TO_START,endContainer,endOffset,"
                + "extractContents(),getBoundingClientRect(),getClientRects(),insertNode(),selectNode(),"
                + "selectNodeContents(),setEnd(),setEndAfter(),setEndBefore(),setStart(),setStartAfter(),"
                + "setStartBefore(),START_TO_END,START_TO_START,startContainer,startOffset,surroundContents()",
            FF = "cloneContents(),cloneRange(),collapse(),collapsed,commonAncestorContainer,compareBoundaryPoints(),"
                + "comparePoint(),createContextualFragment(),deleteContents(),detach(),END_TO_END,END_TO_START,"
                + "endContainer,endOffset,extractContents(),getBoundingClientRect(),getClientRects(),insertNode(),"
                + "intersectsNode(),isPointInRange(),selectNode(),selectNodeContents(),setEnd(),setEndAfter(),"
                + "setEndBefore(),setStart(),setStartAfter(),setStartBefore(),START_TO_END,START_TO_START,"
                + "startContainer,startOffset,surroundContents()")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF})
    public void range() throws Exception {
        testString("document.createRange(), window.performance");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = "append(),appendChild(),ATTRIBUTE_NODE,baseURI,CDATA_SECTION_NODE,childElementCount,childNodes,"
                + "children,cloneNode(),COMMENT_NODE,compareDocumentPosition(),contains(),DOCUMENT_FRAGMENT_NODE,"
                + "DOCUMENT_NODE,DOCUMENT_POSITION_CONTAINED_BY,DOCUMENT_POSITION_CONTAINS,"
                + "DOCUMENT_POSITION_DISCONNECTED,DOCUMENT_POSITION_FOLLOWING,"
                + "DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC,DOCUMENT_POSITION_PRECEDING,DOCUMENT_TYPE_NODE,"
                + "ELEMENT_NODE,ENTITY_NODE,ENTITY_REFERENCE_NODE,firstChild,firstElementChild,getElementById(),"
                + "getRootNode(),hasChildNodes(),insertBefore(),isConnected,isDefaultNamespace(),isEqualNode(),"
                + "isSameNode(),lastChild,lastElementChild,lookupNamespaceURI(),lookupPrefix(),nextSibling,nodeName,"
                + "nodeType,nodeValue,normalize(),NOTATION_NODE,ownerDocument,parentElement,parentNode,prepend(),"
                + "previousSibling,PROCESSING_INSTRUCTION_NODE,querySelector(),querySelectorAll(),removeChild(),"
                + "replaceChild(),TEXT_NODE,textContent",
            FF52 = "addEventListener(),append(),appendChild(),ATTRIBUTE_NODE,baseURI,CDATA_SECTION_NODE,"
                + "childElementCount,childNodes,children,cloneNode(),COMMENT_NODE,compareDocumentPosition(),contains(),"
                + "dispatchEvent(),DOCUMENT_FRAGMENT_NODE,DOCUMENT_NODE,DOCUMENT_POSITION_CONTAINED_BY,"
                + "DOCUMENT_POSITION_CONTAINS,DOCUMENT_POSITION_DISCONNECTED,DOCUMENT_POSITION_FOLLOWING,"
                + "DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC,DOCUMENT_POSITION_PRECEDING,DOCUMENT_TYPE_NODE,"
                + "ELEMENT_NODE,ENTITY_NODE,ENTITY_REFERENCE_NODE,firstChild,firstElementChild,getElementById(),"
                + "hasChildNodes(),insertBefore(),isConnected,isDefaultNamespace(),isEqualNode(),isSameNode(),"
                + "lastChild,lastElementChild,lookupNamespaceURI(),lookupPrefix(),nextSibling,nodeName,nodeType,"
                + "nodeValue,normalize(),NOTATION_NODE,ownerDocument,parentElement,parentNode,prepend(),"
                + "previousSibling,PROCESSING_INSTRUCTION_NODE,querySelector(),querySelectorAll(),removeChild(),"
                + "removeEventListener(),replaceChild(),TEXT_NODE,textContent",
            FF60 = "append(),appendChild(),ATTRIBUTE_NODE,baseURI,CDATA_SECTION_NODE,"
                + "childElementCount,childNodes,children,cloneNode(),COMMENT_NODE,compareDocumentPosition(),contains(),"
                + "DOCUMENT_FRAGMENT_NODE,DOCUMENT_NODE,DOCUMENT_POSITION_CONTAINED_BY,"
                + "DOCUMENT_POSITION_CONTAINS,DOCUMENT_POSITION_DISCONNECTED,DOCUMENT_POSITION_FOLLOWING,"
                + "DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC,DOCUMENT_POSITION_PRECEDING,DOCUMENT_TYPE_NODE,"
                + "ELEMENT_NODE,ENTITY_NODE,ENTITY_REFERENCE_NODE,firstChild,firstElementChild,getElementById(),"
                + "getRootNode(),hasChildNodes(),insertBefore(),isConnected,isDefaultNamespace(),isEqualNode(),"
                + "isSameNode(),lastChild,lastElementChild,lookupNamespaceURI(),lookupPrefix(),"
                + "nextSibling,nodeName,nodeType,"
                + "nodeValue,normalize(),NOTATION_NODE,ownerDocument,parentElement,parentNode,prepend(),"
                + "previousSibling,PROCESSING_INSTRUCTION_NODE,querySelector(),querySelectorAll(),removeChild(),"
                + "replaceChild(),TEXT_NODE,textContent",
            IE = "addEventListener(),appendChild(),ATTRIBUTE_NODE,attributes,CDATA_SECTION_NODE,childNodes,cloneNode(),"
                + "COMMENT_NODE,compareDocumentPosition(),dispatchEvent(),DOCUMENT_FRAGMENT_NODE,DOCUMENT_NODE,"
                + "DOCUMENT_POSITION_CONTAINED_BY,DOCUMENT_POSITION_CONTAINS,DOCUMENT_POSITION_DISCONNECTED,"
                + "DOCUMENT_POSITION_FOLLOWING,DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC,DOCUMENT_POSITION_PRECEDING,"
                + "DOCUMENT_TYPE_NODE,ELEMENT_NODE,ENTITY_NODE,ENTITY_REFERENCE_NODE,firstChild,hasAttributes(),"
                + "hasChildNodes(),insertBefore(),isDefaultNamespace(),isEqualNode(),isSameNode(),isSupported(),"
                + "lastChild,localName,lookupNamespaceURI(),lookupPrefix(),namespaceURI,nextSibling,nodeName,nodeType,"
                + "nodeValue,normalize(),NOTATION_NODE,ownerDocument,parentNode,prefix,previousSibling,"
                + "PROCESSING_INSTRUCTION_NODE,querySelector(),querySelectorAll(),removeChild(),removeEventListener(),"
                + "removeNode(),replaceChild(),replaceNode(),swapNode(),TEXT_NODE,textContent")
    @BrowserRunner.NotYetImplemented
    public void documentFragment() throws Exception {
        testString("document.createDocumentFragment(), window.performance");
    }

}