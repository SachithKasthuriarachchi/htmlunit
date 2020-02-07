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
import com.android.gargoylesoftware.htmlunit.javascript.NamedNodeMap;
import com.android.gargoylesoftware.htmlunit.javascript.host.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.CanvasRenderingContext2D;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.Path2D;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.Coordinates;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.Geolocation;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.Position;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.intl.Intl;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AnalyserNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AudioParam;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.ChannelMergerNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.LocalMediaStream;
import com.android.gargoylesoftware.htmlunit.javascript.host.network.NetworkInformation;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.worker.Worker;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests that {@code typeof} host class is correct.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@RunWith(BrowserRunner.class)
public class HostTypeOfTest extends WebDriverTestCase {

    private void test(final String className) throws Exception {
        final String html =
            "<html><head><script>\n"
            + "  function test() {\n"
            + "    alert(typeof " + className + ");\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void abstractList() throws Exception {
        test("AbstractList");
    }

    /**
     * Test {@link ActiveXObject}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void activeXObject() throws Exception {
        test("ActiveXObject");
    }

    /**
     * Test {@link AnalyserNode}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void analyserNode() throws Exception {
        test("AnalyserNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void angle_instanced_arrays() throws Exception {
        test("ANGLE_instanced_arrays");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void animation() throws Exception {
        test("Animation");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void animationEvent() throws Exception {
        test("AnimationEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void appBannerPromptResult() throws Exception {
        test("AppBannerPromptResult");
    }

    /**
     * Test {@link ApplicationCache}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            CHROME = "function",
            FF = "undefined")
    public void applicationCache() throws Exception {
        test("ApplicationCache");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void applicationCacheErrorEvent() throws Exception {
        test("ApplicationCacheErrorEvent");
    }

    /**
     * Test {@code net.sourceforge.htmlunit.corejs.javascript.Arguments}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("object")
    public void arguments() throws Exception {
        test("arguments");
    }

    /**
     * Test {@link ArrayBuffer}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void arrayBuffer() throws Exception {
        test("ArrayBuffer");
    }

    /**
     * Test {@link ArrayBufferView}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void arrayBufferView() throws Exception {
        test("ArrayBufferView");
    }

    /**
     * Test {@link ArrayBufferViewBase}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void arrayBufferViewBase() throws Exception {
        test("ArrayBufferViewBase");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void atomics() throws Exception {
        test("Atomics");
    }

    /**
     * Test {@link Attr}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void attr() throws Exception {
        test("Attr");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void audio() throws Exception {
        test("Audio");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void audioBuffer() throws Exception {
        test("AudioBuffer");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void audioBufferSourceNode() throws Exception {
        test("AudioBufferSourceNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void audioContext() throws Exception {
        test("AudioContext");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void audioDestinationNode() throws Exception {
        test("AudioDestinationNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void audioListener() throws Exception {
        test("AudioListener");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void audioNode() throws Exception {
        test("AudioNode");
    }

    /**
     * Test {@link AudioParam}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void audioParam() throws Exception {
        test("AudioParam");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void audioProcessingEvent() throws Exception {
        test("AudioProcessingEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void audioScheduledSourceNode() throws Exception {
        test("AudioScheduledSourceNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void autocompleteErrorEvent() throws Exception {
        test("AutocompleteErrorEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void barProp() throws Exception {
        test("BarProp");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void baseAudioContext() throws Exception {
        test("BaseAudioContext");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void batteryManager() throws Exception {
        test("BatteryManager");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void beforeInstallPromptEvent() throws Exception {
        test("BeforeInstallPromptEvent");
    }

    /**
     * Test {@link BeforeUnloadEvent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void beforeUnloadEvent() throws Exception {
        test("BeforeUnloadEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void biquadFilterNode() throws Exception {
        test("BiquadFilterNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void blob() throws Exception {
        test("Blob");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void blobEvent() throws Exception {
        test("BlobEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void bluetooth() throws Exception {
        test("Bluetooth");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void bluetoothRemoteGATTCharacteristic() throws Exception {
        test("BluetoothRemoteGATTCharacteristic");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void bluetoothRemoteGATTServer() throws Exception {
        test("BluetoothRemoteGATTServer");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void boxObject() throws Exception {
        test("BoxObject");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void broadcastChannel() throws Exception {
        test("BroadcastChannel");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void budgetService() throws Exception {
        test("BudgetService");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void cache() throws Exception {
        test("Cache");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void cacheStorage() throws Exception {
        test("CacheStorage");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void canvasCaptureMediaStream() throws Exception {
        test("CanvasCaptureMediaStream");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void canvasCaptureMediaStreamTrack() throws Exception {
        test("CanvasCaptureMediaStreamTrack");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void canvasGradient() throws Exception {
        test("CanvasGradient");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void canvasPattern() throws Exception {
        test("CanvasPattern");
    }

    /**
     * Test {@link CanvasRenderingContext2D}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void canvasRenderingContext2D() throws Exception {
        test("CanvasRenderingContext2D");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void caretPosition() throws Exception {
        test("CaretPosition");
    }

    /**
     * Test {@link CDATASection}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void cdataSection() throws Exception {
        test("CDATASection");
    }

    /**
     * Test {@link ChannelMergerNode}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void channelMergerNode() throws Exception {
        test("ChannelMergerNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void channelSplitterNode() throws Exception {
        test("ChannelSplitterNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void characterData() throws Exception {
        test("CharacterData");
    }

    /**
     * Test {@link CharacterData}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void characterDataImpl() throws Exception {
        test("CharacterDataImpl");
    }

    /**
     * Test {@link ClientRect}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void clientRect() throws Exception {
        test("ClientRect");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void clientRectList() throws Exception {
        test("ClientRectList");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void clipboardData() throws Exception {
        test("ClipboardData");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void clipboardEvent() throws Exception {
        test("ClipboardEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void closeEvent() throws Exception {
        test("CloseEvent");
    }

    /**
     * Test {@link Comment}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void comment() throws Exception {
        test("Comment");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void compositionEvent() throws Exception {
        test("CompositionEvent");
    }

    /**
     * Test {@link ComputedCSSStyleDeclaration}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void computedCSSStyleDeclaration() throws Exception {
        test("ComputedCSSStyleDeclaration");
    }

    /**
     * Test {@link Console}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void console() throws Exception {
        test("Console");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void constantSourceNode() throws Exception {
        test("ConstantSourceNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void convolverNode() throws Exception {
        test("ConvolverNode");
    }

    /**
     * Test {@link Coordinates}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void coordinates() throws Exception {
        test("Coordinates");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void credential() throws Exception {
        test("Credential");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void credentialsContainer() throws Exception {
        test("CredentialsContainer");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void crypto() throws Exception {
        test("Crypto");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void cryptoKey() throws Exception {
        test("CryptoKey");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void css() throws Exception {
        test("CSS");
    }

    /**
     * Test {@link CSSStyleDeclaration}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void css2Properties() throws Exception {
        test("CSS2Properties");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void cssCharsetRule() throws Exception {
        test("CSSCharsetRule");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            FF52 = "object",
            IE = "undefined")
    public void cssConditionRule() throws Exception {
        test("CSSConditionRule");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "object",
            FF60 = "function")
    public void cssCounterStyleRule() throws Exception {
        test("CSSCounterStyleRule");
    }

    /**
     * Test {@link CSSFontFaceRule}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            CHROME = "function",
            FF60 = "function")
    public void cssFontFaceRule() throws Exception {
        test("CSSFontFaceRule");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            FF52 = "object",
            IE = "undefined")
    public void cssGroupingRule() throws Exception {
        test("CSSGroupingRule");
    }

    /**
     * Test {@link CSSImportRule}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            CHROME = "function",
            FF60 = "function")
    public void cssImportRule() throws Exception {
        test("CSSImportRule");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            FF52 = "object",
            IE = "object")
    public void cssKeyframeRule() throws Exception {
        test("CSSKeyframeRule");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            FF52 = "object",
            IE = "object")
    public void cssKeyframesRule() throws Exception {
        test("CSSKeyframesRule");
    }

    /**
     * Test {@link CSSMediaRule}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            CHROME = "function",
            FF60 = "function")
    public void cssMediaRule() throws Exception {
        test("CSSMediaRule");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object",
            FF52 = "undefined")
    public void cssNamespaceRule() throws Exception {
        test("CSSNamespaceRule");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            CHROME = "function",
            FF60 = "function")
    public void cssPageRule() throws Exception {
        test("CSSPageRule");
    }

    /**
     * Test {@link CSSPrimitiveValue}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void cssPrimitiveValue() throws Exception {
        test("CSSPrimitiveValue");
    }

    /**
     * Test {@link CSSRule}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            CHROME = "function",
            FF60 = "function")
    public void cssRule() throws Exception {
        test("CSSRule");
    }

    /**
     * Test {@link CSSRuleList}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void cssRuleList() throws Exception {
        test("CSSRuleList");
    }

    /**
     * Test {@link CSSStyleDeclaration}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void cssStyleDeclaration() throws Exception {
        test("CSSStyleDeclaration");
    }

    /**
     * Test {@link CSSStyleRule}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            CHROME = "function",
            FF60 = "function")
    public void cssStyleRule() throws Exception {
        test("CSSStyleRule");
    }

    /**
     * Test {@link CSSStyleSheet}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void cssStyleSheet() throws Exception {
        test("CSSStyleSheet");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            FF52 = "object",
            IE = "undefined")
    public void cssSupportsRule() throws Exception {
        test("CSSSupportsRule");
    }

    /**
     * Test {@link CSSValue}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void cssValue() throws Exception {
        test("CSSValue");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void cssValueList() throws Exception {
        test("CSSValueList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void cssViewportRule() throws Exception {
        test("CSSViewportRule");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void customElementRegistry() throws Exception {
        test("CustomElementRegistry");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void customEvent() throws Exception {
        test("CustomEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void dataTransfer() throws Exception {
        test("DataTransfer");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void dataTransferItem() throws Exception {
        test("DataTransferItem");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void dataTransferItemList() throws Exception {
        test("DataTransferItemList");
    }

    /**
     * Test {@link DataView}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void dataView() throws Exception {
        test("DataView");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void delayNode() throws Exception {
        test("DelayNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void deviceLightEvent() throws Exception {
        test("DeviceLightEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void deviceMotionEvent() throws Exception {
        test("DeviceMotionEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void deviceOrientationEvent() throws Exception {
        test("DeviceOrientationEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void deviceProximityEvent() throws Exception {
        test("DeviceProximityEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void deviceStorage() throws Exception {
        test("DeviceStorage");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void deviceStorageChangeEvent() throws Exception {
        test("DeviceStorageChangeEvent");
    }

    /**
     * Test {@link Document}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void document() throws Exception {
        test("Document");
    }

    /**
     * Test {@link DocumentFragment}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void documentFragment() throws Exception {
        test("DocumentFragment");
    }

    /**
     * Test {@link DocumentType}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void documentType() throws Exception {
        test("DocumentType");
    }

    /**
     * Test {@link DOMCursor}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void domCursor() throws Exception {
        test("DOMCursor");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void domError() throws Exception {
        test("DOMError");
    }

    /**
     * Test {@link DOMException}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void domException() throws Exception {
        test("DOMException");
    }

    /**
     * Test {@link DOMImplementation}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void domImplementation() throws Exception {
        test("DOMImplementation");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void domMatrix() throws Exception {
        test("DOMMatrix");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void domMatrixReadOnly() throws Exception {
        test("DOMMatrixReadOnly");
    }

    /**
     * Test {@link DOMParser}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void domParser() throws Exception {
        test("DOMParser");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void domPoint() throws Exception {
        test("DOMPoint");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void domPointReadOnly() throws Exception {
        test("DOMPointReadOnly");
    }

    /**
     * Test {@link ClientRect}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void domRect() throws Exception {
        test("DOMRect");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    @BrowserRunner.NotYetImplemented({BrowserRunner.TestedBrowser.CHROME, BrowserRunner.TestedBrowser.FF})
    public void domRectList() throws Exception {
        test("DOMRectList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void domRectReadOnly() throws Exception {
        test("DOMRectReadOnly");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void domRequest() throws Exception {
        test("DOMRequest");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void domSettableTokenList() throws Exception {
        test("DOMSettableTokenList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void domStringList() throws Exception {
        test("DOMStringList");
    }

    /**
     * Test {@link DOMStringMap}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void domStringMap() throws Exception {
        test("DOMStringMap");
    }

    /**
     * Test {@link DOMTokenList}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void domTokenList() throws Exception {
        test("DOMTokenList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void dragEvent() throws Exception {
        test("DragEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void dynamicsCompressorNode() throws Exception {
        test("DynamicsCompressorNode");
    }

    /**
     * Test {@link Element}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void element() throws Exception {
        test("Element");
    }

    /**
     * Test {@link Enumerator}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "function")
    public void enumerator() throws Exception {
        test("Enumerator");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void error() throws Exception {
        test("Error");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void errorEvent() throws Exception {
        test("ErrorEvent");
    }

    /**
     * Test {@link Event}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void event() throws Exception {
        test("Event");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void eventNode() throws Exception {
        test("EventNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void eventSource() throws Exception {
        test("EventSource");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void eventTarget() throws Exception {
        test("EventTarget");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void ext_color_buffer_half_float() throws Exception {
        test("EXT_color_buffer_half_float");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void ext_texture_filter_anisotropic() throws Exception {
        test("EXT_texture_filter_anisotropic");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void extendableMessageEvent() throws Exception {
        test("ExtendableMessageEvent");
    }

    /**
     * Test {@link External}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function")
    public void external() throws Exception {
        test("External");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void federatedCredential() throws Exception {
        test("FederatedCredential");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void file() throws Exception {
        test("File");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void fileError() throws Exception {
        test("FileError");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void fileHandle() throws Exception {
        test("FileHandle");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void fileList() throws Exception {
        test("FileList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void fileReader() throws Exception {
        test("FileReader");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void fileRequest() throws Exception {
        test("FileRequest");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void fileSystem() throws Exception {
        test("FileSystem");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void fileSystemDirectoryEntry() throws Exception {
        test("FileSystemDirectoryEntry");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void fileSystemDirectoryReader() throws Exception {
        test("FileSystemDirectoryReader");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void fileSystemEntry() throws Exception {
        test("FileSystemEntry");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void fileSystemFileEntry() throws Exception {
        test("FileSystemFileEntry");
    }

    /**
     * Test {@link Float32Array}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void float32Array() throws Exception {
        test("Float32Array");
    }

    /**
     * Test {@link Float64Array}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void float64Array() throws Exception {
        test("Float64Array");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void focusEvent() throws Exception {
        test("FocusEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void fontFace() throws Exception {
        test("FontFace");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void fontFaceSet() throws Exception {
        test("FontFaceSet");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void formChild() throws Exception {
        test("FormChild");
    }

    /**
     * Test {@link FormData}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void formData() throws Exception {
        test("FormData");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void formField() throws Exception {
        test("FormField");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void gainNode() throws Exception {
        test("GainNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void gamepad() throws Exception {
        test("Gamepad");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void gamepadButton() throws Exception {
        test("GamepadButton");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void gamepadEvent() throws Exception {
        test("GamepadEvent");
    }

    /**
     * Test {@link Geolocation}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void geolocation() throws Exception {
        test("Geolocation");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void gestureEvent() throws Exception {
        test("GestureEvent");
    }

    /**
     * Test {@link HashChangeEvent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void hashChangeEvent() throws Exception {
        test("HashChangeEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void headers() throws Exception {
        test("Headers");
    }

    /**
     * Test {@link History}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void history() throws Exception {
        test("History");
    }

    /**
     * Test {@link HTMLAllCollection}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlAllCollection() throws Exception {
        test("HTMLAllCollection");
    }

    /**
     * Test {@link HTMLAnchorElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlAnchorElement() throws Exception {
        test("HTMLAnchorElement");
    }

    /**
     * Test {@link HTMLAppletElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void htmlAppletElement() throws Exception {
        test("HTMLAppletElement");
    }

    /**
     * Test {@link HTMLAreaElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlAreaElement() throws Exception {
        test("HTMLAreaElement");
    }

    /**
     * Test {@link HTMLAudioElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlAudioElement() throws Exception {
        test("HTMLAudioElement");
    }

    /**
     * Test {@link HTMLBaseElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlBaseElement() throws Exception {
        test("HTMLBaseElement");
    }

    /**
     * Test {@link HTMLBaseFontElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlBaseFontElement() throws Exception {
        test("HTMLBaseFontElement");
    }

    /**
     * Test {@link HTMLBGSoundElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlBGSoundElement() throws Exception {
        test("HTMLBGSoundElement");
    }

    /**
     * Test {@link HTMLBlockElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlBlockElement() throws Exception {
        test("HTMLBlockElement");
    }

    /**
     * Test {@link HTMLQuoteElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlBlockQuoteElement() throws Exception {
        test("HTMLBlockQuoteElement");
    }

    /**
     * Test {@link HTMLQuoteElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlBodyElement() throws Exception {
        test("HTMLBodyElement");
    }

    /**
     * Test {@link HTMLBRElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlBRElement() throws Exception {
        test("HTMLBRElement");
    }

    /**
     * Test {@link HTMLButtonElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlButtonElement() throws Exception {
        test("HTMLButtonElement");
    }

    /**
     * Test {@link HTMLCanvasElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlCanvasElement() throws Exception {
        test("HTMLCanvasElement");
    }

    /**
     * Test {@link HTMLCollection}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlCollection() throws Exception {
        test("HTMLCollection");
    }

    /**
     * Test {@link Comment}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlCommentElement() throws Exception {
        test("HTMLCommentElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void htmlContentElement() throws Exception {
        test("HTMLContentElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void htmlDataElement() throws Exception {
        test("HTMLDataElement");
    }

    /**
     * Test {@link HTMLDataListElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlDataListElement() throws Exception {
        test("HTMLDataListElement");
    }

    /**
     * Test {@link HTMLDDElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlDDElement() throws Exception {
        test("HTMLDDElement");
    }

    /**
     * Test {@link HTMLDDElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlDefinitionDescriptionElement() throws Exception {
        test("HTMLDefinitionDescriptionElement");
    }

    /**
     * Test {@link HTMLDTElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlDefinitionTermElement() throws Exception {
        test("HTMLDefinitionTermElement");
    }

    /**
     * Test {@link HTMLDetailsElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void htmlDetailsElement() throws Exception {
        test("HTMLDetailsElement");
    }

    /**
     * Test {@link HTMLDialogElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void htmlDialogElement() throws Exception {
        test("HTMLDialogElement");
    }

    /**
     * Test {@link HTMLDirectoryElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlDirectoryElement() throws Exception {
        test("HTMLDirectoryElement");
    }

    /**
     * Test {@link HTMLDivElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlDivElement() throws Exception {
        test("HTMLDivElement");
    }

    /**
     * Test {@link HTMLDListElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlDListElement() throws Exception {
        test("HTMLDListElement");
    }

    /**
     * Test {@link HTMLDocument}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlDocument() throws Exception {
        test("HTMLDocument");
    }

    /**
     * Test {@link HTMLDTElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlDTElement() throws Exception {
        test("HTMLDTElement");
    }

    /**
     * Test {@link HTMLElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlElement() throws Exception {
        test("HTMLElement");
    }

    /**
     * Test {@link HTMLEmbedElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlEmbedElement() throws Exception {
        test("HTMLEmbedElement");
    }

    /**
     * Test {@link HTMLFieldSetElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlFieldSetElement() throws Exception {
        test("HTMLFieldSetElement");
    }

    /**
     * Test {@link HTMLFontElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlFontElement() throws Exception {
        test("HTMLFontElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void htmlFormControlsCollection() throws Exception {
        test("HTMLFormControlsCollection");
    }

    /**
     * Test {@link HTMLFormElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlFormElement() throws Exception {
        test("HTMLFormElement");
    }

    /**
     * Test {@link HTMLFrameElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlFrameElement() throws Exception {
        test("HTMLFrameElement");
    }

    /**
     * Test {@link HTMLFrameSetElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlFrameSetElement() throws Exception {
        test("HTMLFrameSetElement");
    }

    /**
     * Test {@link HTMLUnknownElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlGenericElement() throws Exception {
        test("HTMLGenericElement");
    }

    /**
     * Test {@link HTMLHeadElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlHeadElement() throws Exception {
        test("HTMLHeadElement");
    }

    /**
     * Test {@link HTMLHeadingElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlHeadingElement() throws Exception {
        test("HTMLHeadingElement");
    }

    /**
     * Test {@link HTMLHRElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlHRElement() throws Exception {
        test("HTMLHRElement");
    }

    /**
     * Test {@link HTMLHtmlElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlHtmlElement() throws Exception {
        test("HTMLHtmlElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlHyperlinkElementUtils() throws Exception {
        test("HTMLHyperlinkElementUtils");
    }

    /**
     * Test {@link HTMLIFrameElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlIFrameElement() throws Exception {
        test("HTMLIFrameElement");
    }

    /**
     * Test {@link HTMLImageElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlImageElement() throws Exception {
        test("HTMLImageElement");
    }

    /**
     * Test {@link HTMLInlineQuotationElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlInlineQuotationElement() throws Exception {
        test("HTMLInlineQuotationElement");
    }

    /**
     * Test {@link HTMLInputElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlInputElement() throws Exception {
        test("HTMLInputElement");
    }

    /**
     * Test {@link HTMLIsIndexElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlIsIndexElement() throws Exception {
        test("HTMLIsIndexElement");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlKeygenElement() throws Exception {
        test("HTMLKeygenElement");
    }

    /**
     * Test {@link HTMLLabelElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlLabelElement() throws Exception {
        test("HTMLLabelElement");
    }

    /**
     * Test {@link HTMLLegendElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlLegendElement() throws Exception {
        test("HTMLLegendElement");
    }

    /**
     * Test {@link HTMLLIElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlLIElement() throws Exception {
        test("HTMLLIElement");
    }

    /**
     * Test {@link HTMLLinkElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlLinkElement() throws Exception {
        test("HTMLLinkElement");
    }

    /**
     * Test {@link HTMLListElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlListElement() throws Exception {
        test("HTMLListElement");
    }

    /**
     * Test {@link HTMLMapElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlMapElement() throws Exception {
        test("HTMLMapElement");
    }

    /**
     * Test {@link HTMLMarqueeElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            CHROME = "function",
            FF = "undefined")
    public void htmlMarqueeElement() throws Exception {
        test("HTMLMarqueeElement");
    }

    /**
     * Test {@link HTMLMediaElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlMediaElement() throws Exception {
        test("HTMLMediaElement");
    }

    /**
     * Test {@link HTMLMenuElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlMenuElement() throws Exception {
        test("HTMLMenuElement");
    }

    /**
     * Test {@link HTMLMenuItemElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void htmlMenuItemElement() throws Exception {
        test("HTMLMenuItemElement");
    }

    /**
     * Test {@link HTMLMetaElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlMetaElement() throws Exception {
        test("HTMLMetaElement");
    }

    /**
     * Test {@link HTMLMeterElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void htmlMeterElement() throws Exception {
        test("HTMLMeterElement");
    }

    /**
     * Test {@link HTMLModElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlModElement() throws Exception {
        test("HTMLModElement");
    }

    /**
     * Test {@link HTMLNextIdElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlNextIdElement() throws Exception {
        test("HTMLNextIdElement");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlNoShowElement() throws Exception {
        test("HTMLNoShowElement");
    }

    /**
     * Test {@link HTMLObjectElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlObjectElement() throws Exception {
        test("HTMLObjectElement");
    }

    /**
     * Test {@link HTMLOListElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlOListElement() throws Exception {
        test("HTMLOListElement");
    }

    /**
     * Test {@link HTMLOptGroupElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlOptGroupElement() throws Exception {
        test("HTMLOptGroupElement");
    }

    /**
     * Test {@link HTMLOptionElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlOptionElement() throws Exception {
        test("HTMLOptionElement");
    }

    /**
     * Test {@link HTMLOptionsCollection}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void htmlOptionsCollection() throws Exception {
        test("HTMLOptionsCollection");
    }

    /**
     * Test {@link HTMLOutputElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void htmlOutputElement() throws Exception {
        test("HTMLOutputElement");
    }

    /**
     * Test {@link HTMLParagraphElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlParagraphElement() throws Exception {
        test("HTMLParagraphElement");
    }

    /**
     * Test {@link HTMLParamElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlParamElement() throws Exception {
        test("HTMLParamElement");
    }

    /**
     * Test {@link HTMLPhraseElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlPhraseElement() throws Exception {
        test("HTMLPhraseElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void htmlPictureElement() throws Exception {
        test("HTMLPictureElement");
    }

    /**
     * Test {@link HTMLPreElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlPreElement() throws Exception {
        test("HTMLPreElement");
    }

    /**
     * Test {@link HTMLProgressElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlProgressElement() throws Exception {
        test("HTMLProgressElement");
    }

    /**
     * Test {@link HTMLQuoteElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlQuoteElement() throws Exception {
        test("HTMLQuoteElement");
    }

    /**
     * Test {@link HTMLScriptElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlScriptElement() throws Exception {
        test("HTMLScriptElement");
    }

    /**
     * Test {@link HTMLSelectElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlSelectElement() throws Exception {
        test("HTMLSelectElement");
    }

    /**
     * Test {@link HTMLShadowElement}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void htmlShadowElement() throws Exception {
        test("HTMLShadowElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void htmlSlotElement() throws Exception {
        test("HTMLSlotElement");
    }

    /**
     * Test {@link HTMLSourceElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlSourceElement() throws Exception {
        test("HTMLSourceElement");
    }

    /**
     * Test {@link HTMLSpanElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlSpanElement() throws Exception {
        test("HTMLSpanElement");
    }

    /**
     * Test {@link HTMLStyleElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlStyleElement() throws Exception {
        test("HTMLStyleElement");
    }

    /**
     * Test {@link HTMLTableCaptionElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlTableCaptionElement() throws Exception {
        test("HTMLTableCaptionElement");
    }

    /**
     * Test {@link HTMLTableCellElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlTableCellElement() throws Exception {
        test("HTMLTableCellElement");
    }

    /**
     * Test {@link HTMLTableColElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlTableColElement() throws Exception {
        test("HTMLTableColElement");
    }

    /**
     * Test {@link HTMLTableComponent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlTableComponent() throws Exception {
        test("HTMLTableComponent");
    }

    /**
     * Test {@link HTMLTableDataCellElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlTableDataCellElement() throws Exception {
        test("HTMLTableDataCellElement");
    }

    /**
     * Test {@link HTMLTableElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlTableElement() throws Exception {
        test("HTMLTableElement");
    }

    /**
     * Test {@link HTMLTableHeaderCellElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void htmlTableHeaderCellElement() throws Exception {
        test("HTMLTableHeaderCellElement");
    }

    /**
     * Test {@link HTMLTableRowElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlTableRowElement() throws Exception {
        test("HTMLTableRowElement");
    }

    /**
     * Test {@link HTMLTableSectionElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlTableSectionElement() throws Exception {
        test("HTMLTableSectionElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void htmlTemplateElement() throws Exception {
        test("HTMLTemplateElement");
    }

    /**
     * Test {@link HTMLTextAreaElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlTextAreaElement() throws Exception {
        test("HTMLTextAreaElement");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlTextElement() throws Exception {
        test("HTMLTextElement");
    }

    /**
     * Test {@link HTMLTimeElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void htmlTimeElement() throws Exception {
        test("HTMLTimeElement");
    }

    /**
     * Test {@link HTMLTitleElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlTitleElement() throws Exception {
        test("HTMLTitleElement");
    }

    /**
     * Test {@link HTMLTrackElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlTrackElement() throws Exception {
        test("HTMLTrackElement");
    }

    /**
     * Test {@link HTMLUListElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlUListElement() throws Exception {
        test("HTMLUListElement");
    }

    /**
     * Test {@link HTMLUnknownElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlUnknownElement() throws Exception {
        test("HTMLUnknownElement");
    }

    /**
     * Test {@link HTMLVideoElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void htmlVideoElement() throws Exception {
        test("HTMLVideoElement");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void htmlWBRElement() throws Exception {
        test("HTMLWBRElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbCursor() throws Exception {
        test("IDBCursor");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbCursorWithValue() throws Exception {
        test("IDBCursorWithValue");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbDatabase() throws Exception {
        test("IDBDatabase");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbFactory() throws Exception {
        test("IDBFactory");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbIndex() throws Exception {
        test("IDBIndex");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbKeyRange() throws Exception {
        test("IDBKeyRange");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void idbMutableFile() throws Exception {
        test("IDBMutableFile");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbObjectStore() throws Exception {
        test("IDBObjectStore");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbOpenDBRequest() throws Exception {
        test("IDBOpenDBRequest");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbRequest() throws Exception {
        test("IDBRequest");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbTransaction() throws Exception {
        test("IDBTransaction");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void idbVersionChangeEvent() throws Exception {
        test("IDBVersionChangeEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void idleDeadline() throws Exception {
        test("IdleDeadline");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void iirFilterNode() throws Exception {
        test("IIRFilterNode");
    }

    /**
     * Test {@link HTMLImageElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void image() throws Exception {
        test("Image");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void imageBitmap() throws Exception {
        test("ImageBitmap");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void imageBitmapRenderingContext() throws Exception {
        test("ImageBitmapRenderingContext");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void imageData() throws Exception {
        test("ImageData");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void inputDeviceCapabilities() throws Exception {
        test("InputDeviceCapabilities");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void inputEvent() throws Exception {
        test("InputEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "object")
    public void installTrigger() throws Exception {
        test("InstallTrigger");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void installTriggerImpl() throws Exception {
        test("InstallTriggerImpl");
    }

    /**
     * Test {@link Int16Array}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void int16Array() throws Exception {
        test("Int16Array");
    }

    /**
     * Test {@link Int32Array}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void int32Array() throws Exception {
        test("Int32Array");
    }

    /**
     * Test {@link Int8Array}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void int8Array() throws Exception {
        test("Int8Array");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void intersectionObserver() throws Exception {
        test("IntersectionObserver");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void intersectionObserverEntry() throws Exception {
        test("IntersectionObserverEntry");
    }

    /**
     * Test {@link Intl}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("object")
    public void intl() throws Exception {
        test("Intl");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("object")
    public void json() throws Exception {
        test("JSON");
    }

    /**
     * Test {@link KeyboardEvent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void keyboardEvent() throws Exception {
        test("KeyboardEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void keyframeEffect() throws Exception {
        test("KeyframeEffect");
    }

    /**
     * Test {@link LocalMediaStream}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void localMediaStream() throws Exception {
        test("LocalMediaStream");
    }

    /**
     * Test {@link Location}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void location() throws Exception {
        test("Location");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void lockedFile() throws Exception {
        test("LockedFile");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void map() throws Exception {
        test("Map");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("object")
    public void math() throws Exception {
        test("Math");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaDeviceInfo() throws Exception {
        test("MediaDeviceInfo");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaDevices() throws Exception {
        test("MediaDevices");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaElementAudioSourceNode() throws Exception {
        test("MediaElementAudioSourceNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaEncryptedEvent() throws Exception {
        test("MediaEncryptedEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void mediaError() throws Exception {
        test("MediaError");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void mediaKeyError() throws Exception {
        test("MediaKeyError");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mediaKeyEvent() throws Exception {
        test("MediaKeyEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaKeyMessageEvent() throws Exception {
        test("MediaKeyMessageEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaKeys() throws Exception {
        test("MediaKeys");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaKeySession() throws Exception {
        test("MediaKeySession");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaKeyStatusMap() throws Exception {
        test("MediaKeyStatusMap");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaKeySystemAccess() throws Exception {
        test("MediaKeySystemAccess");
    }

    /**
     * Test {@link MediaList}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void mediaList() throws Exception {
        test("MediaList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void mediaQueryList() throws Exception {
        test("MediaQueryList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void mediaQueryListEvent() throws Exception {
        test("MediaQueryListEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaRecorder() throws Exception {
        test("MediaRecorder");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaSource() throws Exception {
        test("MediaSource");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaStream() throws Exception {
        test("MediaStream");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaStreamAudioDestinationNode() throws Exception {
        test("MediaStreamAudioDestinationNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaStreamAudioSourceNode() throws Exception {
        test("MediaStreamAudioSourceNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaStreamEvent() throws Exception {
        test("MediaStreamEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaStreamTrack() throws Exception {
        test("MediaStreamTrack");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void mediaStreamTrackEvent() throws Exception {
        test("MediaStreamTrackEvent");
    }

    /**
     * Test {@link MessageChannel}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void messageChannel() throws Exception {
        test("MessageChannel");
    }

    /**
     * Test {@link MessageEvent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void messageEvent() throws Exception {
        test("MessageEvent");
    }

    /**
     * Test {@link MessagePort}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void messagePort() throws Exception {
        test("MessagePort");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void midiAccess() throws Exception {
        test("MIDIAccess");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void midiConnectionEvent() throws Exception {
        test("MIDIConnectionEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void midiInput() throws Exception {
        test("MIDIInput");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void midiInputMap() throws Exception {
        test("MIDIInputMap");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void midiMessageEvent() throws Exception {
        test("MIDIMessageEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void midiOutput() throws Exception {
        test("MIDIOutput");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void midiOutputMap() throws Exception {
        test("MIDIOutputMap");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void midiPort() throws Exception {
        test("MIDIPort");
    }

    /**
     * Test {@link MimeType}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void mimeType() throws Exception {
        test("MimeType");
    }

    /**
     * Test {@link MimeTypeArray}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void mimeTypeArray() throws Exception {
        test("MimeTypeArray");
    }

    /**
     * Test {@link MouseEvent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void mouseEvent() throws Exception {
        test("MouseEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void mouseScrollEvent() throws Exception {
        test("MouseScrollEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void mouseWheelEvent() throws Exception {
        test("MouseWheelEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozContactChangeEvent() throws Exception {
        test("MozContactChangeEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozCSSKeyframesRule() throws Exception {
        test("MozCSSKeyframesRule");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozMmsEvent() throws Exception {
        test("MozMmsEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozMmsMessage() throws Exception {
        test("MozMmsMessage");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozMobileMessageManager() throws Exception {
        test("MozMobileMessageManager");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozMobileMessageThread() throws Exception {
        test("MozMobileMessageThread");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozPowerManager() throws Exception {
        test("MozPowerManager");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void mozRTCIceCandidate() throws Exception {
        test("mozRTCIceCandidate");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void mozRTCPeerConnection() throws Exception {
        test("mozRTCPeerConnection");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void mozRTCSessionDescription() throws Exception {
        test("mozRTCSessionDescription");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozSettingsEvent() throws Exception {
        test("MozSettingsEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozSmsEvent() throws Exception {
        test("MozSmsEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozSmsFilter() throws Exception {
        test("MozSmsFilter");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozSmsMessage() throws Exception {
        test("MozSmsMessage");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void mozSmsSegmentInfo() throws Exception {
        test("MozSmsSegmentInfo");
    }

    /**
     * Test {@link ComputedCSSStyleDeclaration}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void msCurrentStyleCSSProperties() throws Exception {
        test("MSCurrentStyleCSSProperties");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void mSGestureEvent() throws Exception {
        test("MSGestureEvent");
    }

    /**
     * Test {@link CSSStyleDeclaration}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    @BrowserRunner.NotYetImplemented(BrowserRunner.TestedBrowser.IE)
    public void msStyleCSSProperties() throws Exception {
        test("MSStyleCSSProperties");
    }

    /**
     * Test {@link MutationEvent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void mutationEvent() throws Exception {
        test("MutationEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void mutationObserver() throws Exception {
        test("MutationObserver");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void mutationRecord() throws Exception {
        test("MutationRecord");
    }

    /**
     * Test {@link NamedNodeMap}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void namedNodeMap() throws Exception {
        test("NamedNodeMap");
    }

    /**
     * Test {@link Namespace}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void namespace() throws Exception {
        test("Namespace");
    }

    /**
     * Test {@link NamespaceCollection}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void namespaceCollection() throws Exception {
        test("NamespaceCollection");
    }

    /**
     * Test {@link Navigator}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void navigator() throws Exception {
        test("Navigator");
    }

    /**
     * Test {@link NetworkInformation}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void networkInformation() throws Exception {
        test("NetworkInformation");
    }

    /**
     * Test {@link Node}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void node() throws Exception {
        test("Node");
    }

    /**
     * Test {@link NodeFilter}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void nodeFilter() throws Exception {
        test("NodeFilter");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void nodeIterator() throws Exception {
        test("NodeIterator");
    }

    /**
     * Test {@link NodeList}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void nodeList() throws Exception {
        test("NodeList");
    }

    /**
     * Test {@link Notification}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void notification() throws Exception {
        test("Notification");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void object() throws Exception {
        test("Object");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void oes_element_index_uint() throws Exception {
        test("OES_element_index_uint");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void oes_standard_derivatives() throws Exception {
        test("OES_standard_derivatives");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void oes_texture_float() throws Exception {
        test("OES_texture_float");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void oes_texture_float_linear() throws Exception {
        test("OES_texture_float_linear");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void oes_texture_half_float() throws Exception {
        test("OES_texture_half_float");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void oes_texture_half_float_linear() throws Exception {
        test("OES_texture_half_float_linear");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void offlineAudioCompletionEvent() throws Exception {
        test("OfflineAudioCompletionEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void offlineAudioContext() throws Exception {
        test("OfflineAudioContext");
    }

    /**
     * Test {@link ApplicationCache}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void offlineResourceList() throws Exception {
        test("OfflineResourceList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void offscreenCanvas() throws Exception {
        test("OffscreenCanvas");
    }

    /**
     * Test {@link HTMLOptionElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void option() throws Exception {
        test("Option");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void oscillatorNode() throws Exception {
        test("OscillatorNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void pageTransitionEvent() throws Exception {
        test("PageTransitionEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void pannerNode() throws Exception {
        test("PannerNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void passwordCredential() throws Exception {
        test("PasswordCredential");
    }

    /**
     * Test {@link Path2D}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void path2D() throws Exception {
        test("Path2D");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void paymentAddress() throws Exception {
        test("PaymentAddress");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void paymentRequest() throws Exception {
        test("PaymentRequest");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void paymentResponse() throws Exception {
        test("PaymentResponse");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void performance() throws Exception {
        test("Performance");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void performanceEntry() throws Exception {
        test("PerformanceEntry");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void performanceFrameTiming() throws Exception {
        test("PerformanceFrameTiming");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void performanceMark() throws Exception {
        test("PerformanceMark");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void performanceMeasure() throws Exception {
        test("PerformanceMeasure");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void performanceNavigation() throws Exception {
        test("PerformanceNavigation");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function",
            IE = "object")
    public void performanceNavigationTiming() throws Exception {
        test("PerformanceNavigationTiming");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void performanceObserver() throws Exception {
        test("PerformanceObserver");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void performanceObserverEntryList() throws Exception {
        test("PerformanceObserverEntryList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void performanceResourceTiming() throws Exception {
        test("PerformanceResourceTiming");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void performanceTiming() throws Exception {
        test("PerformanceTiming");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void periodicWave() throws Exception {
        test("PeriodicWave");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void permissions() throws Exception {
        test("Permissions");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void permissionStatus() throws Exception {
        test("PermissionStatus");
    }

    /**
     * Test {@link Plugin}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void plugin() throws Exception {
        test("Plugin");
    }

    /**
     * Test {@link PluginArray}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void pluginArray() throws Exception {
        test("PluginArray");
    }

    /**
     * Test {@link PointerEvent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            CHROME = "function",
            FF60 = "function",
            FF52 = "undefined")
    public void pointerEvent() throws Exception {
        test("PointerEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void popStateEvent() throws Exception {
        test("PopStateEvent");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void popup() throws Exception {
        test("Popup");
    }

    /**
     * Test {@link Position}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void position() throws Exception {
        test("Position");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void positionError() throws Exception {
        test("PositionError");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void presentation() throws Exception {
        test("Presentation");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void presentationAvailability() throws Exception {
        test("PresentationAvailability");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void presentationConnection() throws Exception {
        test("PresentationConnection");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void presentationConnectionAvailableEvent() throws Exception {
        test("PresentationConnectionAvailableEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void presentationConnectionCloseEvent() throws Exception {
        test("PresentationConnectionCloseEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void presentationRequest() throws Exception {
        test("PresentationRequest");
    }

    /**
     * Test {@link ProcessingInstruction}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void processingInstruction() throws Exception {
        test("ProcessingInstruction");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void progressEvent() throws Exception {
        test("ProgressEvent");
    }

    /**
     * Test {@link Promise}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void promise() throws Exception {
        test("Promise");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void promiseRejectionEvent() throws Exception {
        test("PromiseRejectionEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void proxy() throws Exception {
        test("Proxy");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void pushManager() throws Exception {
        test("PushManager");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void pushSubscription() throws Exception {
        test("PushSubscription");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void pushSubscriptionOptions() throws Exception {
        test("PushSubscriptionOptions");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void radioNodeList() throws Exception {
        test("RadioNodeList");
    }

    /**
     * Test {@link Range}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void range() throws Exception {
        test("Range");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void readableByteStream() throws Exception {
        test("ReadableByteStream");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void readableStream() throws Exception {
        test("ReadableStream");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "object",
            IE = "undefined")
    public void reflect() throws Exception {
        test("Reflect");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void remotePlayback() throws Exception {
        test("RemotePlayback");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void request() throws Exception {
        test("Request");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void response() throws Exception {
        test("Response");
    }

    /**
     * Test {@link RowContainer}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void rowContainer() throws Exception {
        test("RowContainer");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void rtcCertificate() throws Exception {
        test("RTCCertificate");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void rtcDataChannelEvent() throws Exception {
        test("RTCDataChannelEvent");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void rtcIceCandidate() throws Exception {
        test("RTCIceCandidate");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void rtcIdentityAssertion() throws Exception {
        test("RTCIdentityAssertion");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void rtcPeerConnection() throws Exception {
        test("RTCPeerConnection");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void rtcPeerConnectionIceEvent() throws Exception {
        test("RTCPeerConnectionIceEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void rtcSessionDescription() throws Exception {
        test("RTCSessionDescription");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void rtcStatsReport() throws Exception {
        test("RTCStatsReport");
    }

    /**
     * Test {@link Screen}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void screen() throws Exception {
        test("Screen");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void screenOrientation() throws Exception {
        test("ScreenOrientation");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void scriptProcessorNode() throws Exception {
        test("ScriptProcessorNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void securityPolicyViolationEvent() throws Exception {
        test("SecurityPolicyViolationEvent");
    }

    /**
     * Test {@link Selection}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void selection() throws Exception {
        test("Selection");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void serviceWorker() throws Exception {
        test("ServiceWorker");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void serviceWorkerContainer() throws Exception {
        test("ServiceWorkerContainer");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void serviceWorkerMessageEvent() throws Exception {
        test("ServiceWorkerMessageEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void serviceWorkerRegistration() throws Exception {
        test("ServiceWorkerRegistration");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void serviceWorkerState() throws Exception {
        test("ServiceWorkerState");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void set() throws Exception {
        test("Set");
    }

    /**
     * Test {@link ShadowRoot}.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void shadowRoot() throws Exception {
        test("ShadowRoot");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void sharedArrayBuffer() throws Exception {
        test("SharedArrayBuffer");
    }

    /**
     * Test {@link SharedWorker}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void sharedWorker() throws Exception {
        test("SharedWorker");
    }

    /**
     * Test {@link SimpleArray}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void simpleArray() throws Exception {
        test("SimpleArray");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void siteBoundCredential() throws Exception {
        test("SiteBoundCredential");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void sourceBuffer() throws Exception {
        test("SourceBuffer");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void sourceBufferList() throws Exception {
        test("SourceBufferList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void speechGrammar() throws Exception {
        test("SpeechGrammar");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void speechGrammarList() throws Exception {
        test("SpeechGrammarList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void speechRecognitionAlternative() throws Exception {
        test("SpeechRecognitionAlternative");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void speechRecognitionError() throws Exception {
        test("SpeechRecognitionError");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void speechRecognitionEvent() throws Exception {
        test("SpeechRecognitionEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void speechRecognitionResult() throws Exception {
        test("SpeechRecognitionResult");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void speechRecognitionResultList() throws Exception {
        test("SpeechRecognitionResultList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void speechSynthesis() throws Exception {
        test("SpeechSynthesis");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void speechSynthesisErrorEvent() throws Exception {
        test("SpeechSynthesisErrorEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void speechSynthesisEvent() throws Exception {
        test("SpeechSynthesisEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void speechSynthesisUtterance() throws Exception {
        test("SpeechSynthesisUtterance");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void speechSynthesisVoice() throws Exception {
        test("SpeechSynthesisVoice");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void staticNodeList() throws Exception {
        test("StaticNodeList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void stereoPannerNode() throws Exception {
        test("StereoPannerNode");
    }

    /**
     * Test {@code net.sourceforge.htmlunit.corejs.javascript.NativeIterator#StopIteration}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "object")
    public void stopIteration() throws Exception {
        test("StopIteration");
    }

    /**
     * Test {@link Storage}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void storage() throws Exception {
        test("Storage");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void storageEvent() throws Exception {
        test("StorageEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void storageManager() throws Exception {
        test("StorageManager");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void styleMedia() throws Exception {
        test("StyleMedia");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void styleSheet() throws Exception {
        test("StyleSheet");
    }

    /**
     * Test {@link StyleSheetList}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void styleSheetList() throws Exception {
        test("StyleSheetList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void subtleCrypto() throws Exception {
        test("SubtleCrypto");
    }

    /**
     * Test {@link SVGAElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAElement() throws Exception {
        test("SVGAElement");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void svgAltGlyphElement() throws Exception {
        test("SVGAltGlyphElement");
    }

    /**
     * Test {@link SVGAngle}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAngle() throws Exception {
        test("SVGAngle");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedAngle() throws Exception {
        test("SVGAnimatedAngle");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedBoolean() throws Exception {
        test("SVGAnimatedBoolean");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedEnumeration() throws Exception {
        test("SVGAnimatedEnumeration");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedInteger() throws Exception {
        test("SVGAnimatedInteger");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedLength() throws Exception {
        test("SVGAnimatedLength");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedLengthList() throws Exception {
        test("SVGAnimatedLengthList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedNumber() throws Exception {
        test("SVGAnimatedNumber");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedNumberList() throws Exception {
        test("SVGAnimatedNumberList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedPreserveAspectRatio() throws Exception {
        test("SVGAnimatedPreserveAspectRatio");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedRect() throws Exception {
        test("SVGAnimatedRect");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedString() throws Exception {
        test("SVGAnimatedString");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgAnimatedTransformList() throws Exception {
        test("SVGAnimatedTransformList");
    }

    /**
     * Test {@link SVGAnimateElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void svgAnimateElement() throws Exception {
        test("SVGAnimateElement");
    }

    /**
     * Test {@link SVGAnimateMotionElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void svgAnimateMotionElement() throws Exception {
        test("SVGAnimateMotionElement");
    }

    /**
     * Test {@link SVGAnimateTransformElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void svgAnimateTransformElement() throws Exception {
        test("SVGAnimateTransformElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void svgAnimationElement() throws Exception {
        test("SVGAnimationElement");
    }

    /**
     * Test {@link SVGCircleElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgCircleElement() throws Exception {
        test("SVGCircleElement");
    }

    /**
     * Test {@link SVGClipPathElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgClipPathElement() throws Exception {
        test("SVGClipPathElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgComponentTransferFunctionElement() throws Exception {
        test("SVGComponentTransferFunctionElement");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void svgCursorElement() throws Exception {
        test("SVGCursorElement");
    }

    /**
     * Test {@link SVGDefsElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgDefsElement() throws Exception {
        test("SVGDefsElement");
    }

    /**
     * Test {@link SVGDescElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgDescElement() throws Exception {
        test("SVGDescElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void svgDiscardElement() throws Exception {
        test("SVGDiscardElement");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void svgDocument() throws Exception {
        test("SVGDocument");
    }

    /**
     * Test {@link SVGElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgElement() throws Exception {
        test("SVGElement");
    }

    /**
     * Test {@link SVGEllipseElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgEllipseElement() throws Exception {
        test("SVGEllipseElement");
    }

    /**
     * Test {@link SVGFEBlendElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEBlendElement() throws Exception {
        test("SVGFEBlendElement");
    }

    /**
     * Test {@link SVGFEColorMatrixElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEColorMatrixElement() throws Exception {
        test("SVGFEColorMatrixElement");
    }

    /**
     * Test {@link SVGFEComponentTransferElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEComponentTransferElement() throws Exception {
        test("SVGFEComponentTransferElement");
    }

    /**
     * Test {@link SVGFECompositeElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFECompositeElement() throws Exception {
        test("SVGFECompositeElement");
    }

    /**
     * Test {@link SVGFEConvolveMatrixElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEConvolveMatrixElement() throws Exception {
        test("SVGFEConvolveMatrixElement");
    }

    /**
     * Test {@link SVGFEDiffuseLightingElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEDiffuseLightingElement() throws Exception {
        test("SVGFEDiffuseLightingElement");
    }

    /**
     * Test {@link SVGFEDisplacementMapElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEDisplacementMapElement() throws Exception {
        test("SVGFEDisplacementMapElement");
    }

    /**
     * Test {@link SVGFEDistantLightElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEDistantLightElement() throws Exception {
        test("SVGFEDistantLightElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void svgFEDropShadowElement() throws Exception {
        test("SVGFEDropShadowElement");
    }

    /**
     * Test {@link SVGFEFloodElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEFloodElement() throws Exception {
        test("SVGFEFloodElement");
    }

    /**
     * Test {@link SVGFEFuncAElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEFuncAElement() throws Exception {
        test("SVGFEFuncAElement");
    }

    /**
     * Test {@link SVGFEFuncBElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEFuncBElement() throws Exception {
        test("SVGFEFuncBElement");
    }

    /**
     * Test {@link SVGFEFuncGElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEFuncGElement() throws Exception {
        test("SVGFEFuncGElement");
    }

    /**
     * Test {@link SVGFEFuncRElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEFuncRElement() throws Exception {
        test("SVGFEFuncRElement");
    }

    /**
     * Test {@link SVGFEGaussianBlurElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEGaussianBlurElement() throws Exception {
        test("SVGFEGaussianBlurElement");
    }

    /**
     * Test {@link SVGFEImageElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEImageElement() throws Exception {
        test("SVGFEImageElement");
    }

    /**
     * Test {@link SVGFEMergeElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEMergeElement() throws Exception {
        test("SVGFEMergeElement");
    }

    /**
     * Test {@link SVGFEMergeNodeElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEMergeNodeElement() throws Exception {
        test("SVGFEMergeNodeElement");
    }

    /**
     * Test {@link SVGFEMorphologyElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEMorphologyElement() throws Exception {
        test("SVGFEMorphologyElement");
    }

    /**
     * Test {@link SVGFEOffsetElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEOffsetElement() throws Exception {
        test("SVGFEOffsetElement");
    }

    /**
     * Test {@link SVGFEPointLightElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFEPointLightElement() throws Exception {
        test("SVGFEPointLightElement");
    }

    /**
     * Test {@link SVGFESpecularLightingElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFESpecularLightingElement() throws Exception {
        test("SVGFESpecularLightingElement");
    }

    /**
     * Test {@link SVGFESpotLightElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFESpotLightElement() throws Exception {
        test("SVGFESpotLightElement");
    }

    /**
     * Test {@link SVGFETileElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFETileElement() throws Exception {
        test("SVGFETileElement");
    }

    /**
     * Test {@link SVGFETurbulenceElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFETurbulenceElement() throws Exception {
        test("SVGFETurbulenceElement");
    }

    /**
     * Test {@link SVGFilterElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgFilterElement() throws Exception {
        test("SVGFilterElement");
    }

    /**
     * Test {@link SVGForeignObjectElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void svgForeignObjectElement() throws Exception {
        test("SVGForeignObjectElement");
    }

    /**
     * Test {@link SVGGElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgGElement() throws Exception {
        test("SVGGElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            FF60 = "function")
    public void svgGeometryElement() throws Exception {
        test("SVGGeometryElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgGradientElement() throws Exception {
        test("SVGGradientElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void svgGraphicsElement() throws Exception {
        test("SVGGraphicsElement");
    }

    /**
     * Test {@link SVGImageElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgImageElement() throws Exception {
        test("SVGImageElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgLength() throws Exception {
        test("SVGLength");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgLengthList() throws Exception {
        test("SVGLengthList");
    }

    /**
     * Test {@link SVGLinearGradientElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgLinearGradientElement() throws Exception {
        test("SVGLinearGradientElement");
    }

    /**
     * Test {@link SVGLineElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgLineElement() throws Exception {
        test("SVGLineElement");
    }

    /**
     * Test {@link SVGMarkerElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgMarkerElement() throws Exception {
        test("SVGMarkerElement");
    }

    /**
     * Test {@link SVGMaskElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgMaskElement() throws Exception {
        test("SVGMaskElement");
    }

    /**
     * Test {@link SVGMatrix}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgMatrix() throws Exception {
        test("SVGMatrix");
    }

    /**
     * Test {@link SVGMetadataElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgMetadataElement() throws Exception {
        test("SVGMetadataElement");
    }

    /**
     * Test {@link SVGMPathElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void svgMPathElement() throws Exception {
        test("SVGMPathElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgNumber() throws Exception {
        test("SVGNumber");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgNumberList() throws Exception {
        test("SVGNumberList");
    }

    /**
     * Test {@link SVGPathElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgPathElement() throws Exception {
        test("SVGPathElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSeg() throws Exception {
        test("SVGPathSeg");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegArcAbs() throws Exception {
        test("SVGPathSegArcAbs");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegArcRel() throws Exception {
        test("SVGPathSegArcRel");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegClosePath() throws Exception {
        test("SVGPathSegClosePath");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegCurvetoCubicAbs() throws Exception {
        test("SVGPathSegCurvetoCubicAbs");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegCurvetoCubicRel() throws Exception {
        test("SVGPathSegCurvetoCubicRel");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegCurvetoCubicSmoothAbs() throws Exception {
        test("SVGPathSegCurvetoCubicSmoothAbs");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegCurvetoCubicSmoothRel() throws Exception {
        test("SVGPathSegCurvetoCubicSmoothRel");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegCurvetoQuadraticAbs() throws Exception {
        test("SVGPathSegCurvetoQuadraticAbs");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegCurvetoQuadraticRel() throws Exception {
        test("SVGPathSegCurvetoQuadraticRel");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegCurvetoQuadraticSmoothAbs() throws Exception {
        test("SVGPathSegCurvetoQuadraticSmoothAbs");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegCurvetoQuadraticSmoothRel() throws Exception {
        test("SVGPathSegCurvetoQuadraticSmoothRel");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegLinetoAbs() throws Exception {
        test("SVGPathSegLinetoAbs");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegLinetoHorizontalAbs() throws Exception {
        test("SVGPathSegLinetoHorizontalAbs");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegLinetoHorizontalRel() throws Exception {
        test("SVGPathSegLinetoHorizontalRel");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegLinetoRel() throws Exception {
        test("SVGPathSegLinetoRel");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegLinetoVerticalAbs() throws Exception {
        test("SVGPathSegLinetoVerticalAbs");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegLinetoVerticalRel() throws Exception {
        test("SVGPathSegLinetoVerticalRel");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function",
            IE = "object")
    public void svgPathSegList() throws Exception {
        test("SVGPathSegList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegMovetoAbs() throws Exception {
        test("SVGPathSegMovetoAbs");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgPathSegMovetoRel() throws Exception {
        test("SVGPathSegMovetoRel");
    }

    /**
     * Test {@link SVGPatternElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgPatternElement() throws Exception {
        test("SVGPatternElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgPoint() throws Exception {
        test("SVGPoint");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgPointList() throws Exception {
        test("SVGPointList");
    }

    /**
     * Test {@link SVGPolygonElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgPolygonElement() throws Exception {
        test("SVGPolygonElement");
    }

    /**
     * Test {@link SVGPolylineElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgPolylineElement() throws Exception {
        test("SVGPolylineElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgPreserveAspectRatio() throws Exception {
        test("SVGPreserveAspectRatio");
    }

    /**
     * Test {@link SVGRadialGradientElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgRadialGradientElement() throws Exception {
        test("SVGRadialGradientElement");
    }

    /**
     * Test {@link SVGRect}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgRect() throws Exception {
        test("SVGRect");
    }

    /**
     * Test {@link SVGRectElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgRectElement() throws Exception {
        test("SVGRectElement");
    }

    /**
     * Test {@link SVGScriptElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgScriptElement() throws Exception {
        test("SVGScriptElement");
    }

    /**
     * Test {@link SVGSetElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void svgSetElement() throws Exception {
        test("SVGSetElement");
    }

    /**
     * Test {@link SVGStopElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgStopElement() throws Exception {
        test("SVGStopElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgStringList() throws Exception {
        test("SVGStringList");
    }

    /**
     * Test {@link SVGStyleElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgStyleElement() throws Exception {
        test("SVGStyleElement");
    }

    /**
     * Test {@link SVGSVGElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgSVGElement() throws Exception {
        test("SVGSVGElement");
    }

    /**
     * Test {@link SVGSwitchElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgSwitchElement() throws Exception {
        test("SVGSwitchElement");
    }

    /**
     * Test {@link SVGSymbolElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgSymbolElement() throws Exception {
        test("SVGSymbolElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgTextContentElement() throws Exception {
        test("SVGTextContentElement");
    }

    /**
     * Test {@link SVGTextElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgTextElement() throws Exception {
        test("SVGTextElement");
    }

    /**
     * Test {@link SVGTextPathElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgTextPathElement() throws Exception {
        test("SVGTextPathElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgTextPositioningElement() throws Exception {
        test("SVGTextPositioningElement");
    }

    /**
     * Test {@link SVGTitleElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgTitleElement() throws Exception {
        test("SVGTitleElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgTransform() throws Exception {
        test("SVGTransform");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgTransformList() throws Exception {
        test("SVGTransformList");
    }

    /**
     * Test {@link SVGTSpanElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgTSpanElement() throws Exception {
        test("SVGTSpanElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgUnitTypes() throws Exception {
        test("SVGUnitTypes");
    }

    /**
     * Test {@link SVGUseElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgUseElement() throws Exception {
        test("SVGUseElement");
    }

    /**
     * Test {@link SVGViewElement}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void svgViewElement() throws Exception {
        test("SVGViewElement");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void svgViewSpec() throws Exception {
        test("SVGViewSpec");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF52 = "function",
            IE = "object")
    public void svgZoomEvent() throws Exception {
        test("SVGZoomEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void symbol() throws Exception {
        test("Symbol");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void syncManager() throws Exception {
        test("SyncManager");
    }

    /**
     * Test {@link Text}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void text() throws Exception {
        test("Text");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void textDecoder() throws Exception {
        test("TextDecoder");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void textEncoder() throws Exception {
        test("TextEncoder");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function",
            IE = "object")
    public void textEvent() throws Exception {
        test("TextEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void textMetrics() throws Exception {
        test("TextMetrics");
    }

    /**
     * Test {@link TextRange}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void textRange() throws Exception {
        test("TextRange");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void textTrack() throws Exception {
        test("TextTrack");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void textTrackCue() throws Exception {
        test("TextTrackCue");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void textTrackCueList() throws Exception {
        test("TextTrackCueList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void textTrackList() throws Exception {
        test("TextTrackList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void timeEvent() throws Exception {
        test("TimeEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void timeRanges() throws Exception {
        test("TimeRanges");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void touch() throws Exception {
        test("Touch");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void touchEvent() throws Exception {
        test("TouchEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void touchList() throws Exception {
        test("TouchList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void trackDefault() throws Exception {
        test("TrackDefault");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void trackDefaultList() throws Exception {
        test("TrackDefaultList");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void trackEvent() throws Exception {
        test("TrackEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void transitionEvent() throws Exception {
        test("TransitionEvent");
    }

    /**
     * Test {@link TreeWalker}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void treeWalker() throws Exception {
        test("TreeWalker");
    }

    /**
     * Test {@link UIEvent}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void uIEvent() throws Exception {
        test("UIEvent");
    }

    /**
     * Test {@link Uint16Array}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void uint16Array() throws Exception {
        test("Uint16Array");
    }

    /**
     * Test {@link Uint32Array}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void uint32Array() throws Exception {
        test("Uint32Array");
    }

    /**
     * Test {@link Uint8Array}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void uint8Array() throws Exception {
        test("Uint8Array");
    }

    /**
     * Test {@link Uint8ClampedArray}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void uint8ClampedArray() throws Exception {
        test("Uint8ClampedArray");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void uriError() throws Exception {
        test("URIError");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void url() throws Exception {
        test("URL");
    }

    /**
     * Test {@link URLSearchParams}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void urlSearchParams() throws Exception {
        test("URLSearchParams");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function")
    public void userProximityEvent() throws Exception {
        test("UserProximityEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void validityState() throws Exception {
        test("ValidityState");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            FF = "function",
            EDGE = "function")
    public void videoPlaybackQuality() throws Exception {
        test("VideoPlaybackQuality");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void vTTCue() throws Exception {
        test("VTTCue");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void waveShaperNode() throws Exception {
        test("WaveShaperNode");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void weakMap() throws Exception {
        test("WeakMap");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void weakSet() throws Exception {
        test("WeakSet");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webGL_color_buffer_float() throws Exception {
        test("WEBGL_color_buffer_float");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webGL_compressed_texture_atc() throws Exception {
        test("WEBGL_compressed_texture_atc");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webGL_compressed_texture_es3() throws Exception {
        test("WEBGL_compressed_texture_es3");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webGL_compressed_texture_etc1() throws Exception {
        test("WEBGL_compressed_texture_etc1");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webGL_compressed_texture_pvrtc() throws Exception {
        test("WEBGL_compressed_texture_pvrtc");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void webGL_compressed_texture_s3tc() throws Exception {
        test("WEBGL_compressed_texture_s3tc");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            IE = "object")
    public void webGL_debug_renderer_info() throws Exception {
        test("WEBGL_debug_renderer_info");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webGL_debug_shaders() throws Exception {
        test("WEBGL_debug_shaders");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webGL_depth_texture() throws Exception {
        test("WEBGL_depth_texture");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void webGL2RenderingContext() throws Exception {
        test("WebGL2RenderingContext");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLActiveInfo() throws Exception {
        test("WebGLActiveInfo");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLBuffer() throws Exception {
        test("WebGLBuffer");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void webGLContextEvent() throws Exception {
        test("WebGLContextEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLFramebuffer() throws Exception {
        test("WebGLFramebuffer");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLProgram() throws Exception {
        test("WebGLProgram");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void webGLQuery() throws Exception {
        test("WebGLQuery");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLRenderbuffer() throws Exception {
        test("WebGLRenderbuffer");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLRenderingContext() throws Exception {
        test("WebGLRenderingContext");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void WebGLSampler() throws Exception {
        test("WebGLSampler");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLShader() throws Exception {
        test("WebGLShader");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLShaderPrecisionFormat() throws Exception {
        test("WebGLShaderPrecisionFormat");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void webGLSync() throws Exception {
        test("WebGLSync");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLTexture() throws Exception {
        test("WebGLTexture");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void webGLTransformFeedback() throws Exception {
        test("WebGLTransformFeedback");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void webGLUniformLocation() throws Exception {
        test("WebGLUniformLocation");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void webGLVertexArrayObject() throws Exception {
        test("WebGLVertexArrayObject");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webKitAnimationEvent() throws Exception {
        test("WebKitAnimationEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitAudioContext() throws Exception {
        test("webkitAudioContext");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void webKitCSSMatrix() throws Exception {
        test("WebKitCSSMatrix");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitIDBCursor() throws Exception {
        test("webkitIDBCursor");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitIDBDatabase() throws Exception {
        test("webkitIDBDatabase");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitIDBFactory() throws Exception {
        test("webkitIDBFactory");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitIDBIndex() throws Exception {
        test("webkitIDBIndex");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitIDBKeyRange() throws Exception {
        test("webkitIDBKeyRange");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitIDBObjectStore() throws Exception {
        test("webkitIDBObjectStore");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitIDBRequest() throws Exception {
        test("webkitIDBRequest");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitIDBTransaction() throws Exception {
        test("webkitIDBTransaction");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webkitMediaStream() throws Exception {
        test("webkitMediaStream");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webKitMutationObserver() throws Exception {
        test("WebKitMutationObserver");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void webkitOfflineAudioContext() throws Exception {
        test("webkitOfflineAudioContext");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webkitRTCPeerConnection() throws Exception {
        test("webkitRTCPeerConnection");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webkitSpeechGrammar() throws Exception {
        test("webkitSpeechGrammar");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webkitSpeechGrammarList() throws Exception {
        test("webkitSpeechGrammarList");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webkitSpeechRecognition() throws Exception {
        test("webkitSpeechRecognition");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webkitSpeechRecognitionError() throws Exception {
        test("webkitSpeechRecognitionError");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webkitSpeechRecognitionEvent() throws Exception {
        test("webkitSpeechRecognitionEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webKitTransitionEvent() throws Exception {
        test("WebKitTransitionEvent");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "undefined",
            CHROME = "function")
    public void webkitURL() throws Exception {
        test("webkitURL");
    }

    /**
     * Test {@link WebSocket}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void webSocket() throws Exception {
        test("WebSocket");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void wheelEvent() throws Exception {
        test("WheelEvent");
    }

    /**
     * Test {@link Window}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void window() throws Exception {
        test("Window");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void windowProperties() throws Exception {
        test("WindowProperties");
    }

    /**
     * Test {@link Worker}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void worker() throws Exception {
        test("Worker");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void xDomainRequest() throws Exception {
        test("XDomainRequest");
    }

    /**
     * Test {@link XMLDocument}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void xmlDocument() throws Exception {
        test("XMLDocument");
    }

    /**
     * Test {@link XMLHttpRequest}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void xmlHttpRequest() throws Exception {
        test("XMLHttpRequest");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "object")
    public void xmlHttpRequestEventTarget() throws Exception {
        test("XMLHttpRequestEventTarget");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void xmlHttpRequestUpload() throws Exception {
        test("XMLHttpRequestUpload");
    }

    /**
     * Test {@link XMLSerializer}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("function")
    public void xmlSerializer() throws Exception {
        test("XMLSerializer");
    }

    /**
     * Test {@link XPathEvaluator}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void xPathEvaluator() throws Exception {
        test("XPathEvaluator");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void xPathExpression() throws Exception {
        test("XPathExpression");
    }

    /**
     * Test {@link XPathNSResolver}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void xPathNSResolver() throws Exception {
        test("XPathNSResolver");
    }

    /**
     * Test {@link XPathResult}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void xPathResult() throws Exception {
        test("XPathResult");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("undefined")
    public void xsltemplate() throws Exception {
        test("XSLTemplate");
    }

    /**
     * Test {@link XSLTProcessor}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts(DEFAULT = "function",
            IE = "undefined")
    public void xsltProcessor() throws Exception {
        test("XSLTProcessor");
    }
}
