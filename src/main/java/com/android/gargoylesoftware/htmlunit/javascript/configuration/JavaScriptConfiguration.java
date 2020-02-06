/*
 * Copyright (c) 2002-2013 Gargoyle Software Inc.
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
package com.android.gargoylesoftware.htmlunit.javascript.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import com.android.gargoylesoftware.htmlunit.BrowserVersion;
import com.android.gargoylesoftware.htmlunit.javascript.NamedNodeMap;
import com.android.gargoylesoftware.htmlunit.javascript.SimpleScriptable;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.Coordinates;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.Geolocation;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.Position;
import org.apache.android.commons.logging.Log;
import org.apache.android.commons.logging.LogFactory;

import com.android.gargoylesoftware.htmlunit.javascript.host.ActiveXObject;
import com.android.gargoylesoftware.htmlunit.javascript.host.Attr;
import com.android.gargoylesoftware.htmlunit.javascript.host.BoxObject;
import com.android.gargoylesoftware.htmlunit.javascript.host.CDATASection;
import com.android.gargoylesoftware.htmlunit.javascript.host.CharacterDataImpl;
import com.android.gargoylesoftware.htmlunit.javascript.host.ClientRect;
import com.android.gargoylesoftware.htmlunit.javascript.host.ClipboardData;
import com.android.gargoylesoftware.htmlunit.javascript.host.Comment;
import com.android.gargoylesoftware.htmlunit.javascript.host.Console;
import com.android.gargoylesoftware.htmlunit.javascript.host.Document;
import com.android.gargoylesoftware.htmlunit.javascript.host.DocumentFragment;
import com.android.gargoylesoftware.htmlunit.javascript.host.DocumentType;
import com.android.gargoylesoftware.htmlunit.javascript.host.Element;
import com.android.gargoylesoftware.htmlunit.javascript.host.Enumerator;
import com.android.gargoylesoftware.htmlunit.javascript.host.Event;
import com.android.gargoylesoftware.htmlunit.javascript.host.EventNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.External;
import com.android.gargoylesoftware.htmlunit.javascript.host.FormChild;
import com.android.gargoylesoftware.htmlunit.javascript.host.FormField;
import com.android.gargoylesoftware.htmlunit.javascript.host.HashChangeEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.History;
import com.android.gargoylesoftware.htmlunit.javascript.host.KeyboardEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.Location;
import com.android.gargoylesoftware.htmlunit.javascript.host.MediaList;
import com.android.gargoylesoftware.htmlunit.javascript.host.MessageEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.MimeType;
import com.android.gargoylesoftware.htmlunit.javascript.host.MimeTypeArray;
import com.android.gargoylesoftware.htmlunit.javascript.host.MouseEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.MutationEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.Namespace;
import com.android.gargoylesoftware.htmlunit.javascript.host.NamespaceCollection;
import com.android.gargoylesoftware.htmlunit.javascript.host.Navigator;
import com.android.gargoylesoftware.htmlunit.javascript.host.Node;
import com.android.gargoylesoftware.htmlunit.javascript.host.NodeFilter;
import com.android.gargoylesoftware.htmlunit.javascript.host.NodeList;
import com.android.gargoylesoftware.htmlunit.javascript.host.OfflineResourceList;
import com.android.gargoylesoftware.htmlunit.javascript.host.Plugin;
import com.android.gargoylesoftware.htmlunit.javascript.host.PluginArray;
import com.android.gargoylesoftware.htmlunit.javascript.host.Popup;
import com.android.gargoylesoftware.htmlunit.javascript.host.ProcessingInstruction;
import com.android.gargoylesoftware.htmlunit.javascript.host.Range;
import com.android.gargoylesoftware.htmlunit.javascript.host.RowContainer;
import com.android.gargoylesoftware.htmlunit.javascript.host.Screen;
import com.android.gargoylesoftware.htmlunit.javascript.host.Selection;
import com.android.gargoylesoftware.htmlunit.javascript.host.SimpleArray;
import com.android.gargoylesoftware.htmlunit.javascript.host.StaticNodeList;
import com.android.gargoylesoftware.htmlunit.javascript.host.Storage;
import com.android.gargoylesoftware.htmlunit.javascript.host.Text;
import com.android.gargoylesoftware.htmlunit.javascript.host.TextRange;
import com.android.gargoylesoftware.htmlunit.javascript.host.TreeWalker;
import com.android.gargoylesoftware.htmlunit.javascript.host.UIEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.WebSocket;
import com.android.gargoylesoftware.htmlunit.javascript.host.Window;
import com.android.gargoylesoftware.htmlunit.javascript.host.XPathEvaluator;
import com.android.gargoylesoftware.htmlunit.javascript.host.XPathNSResolver;
import com.android.gargoylesoftware.htmlunit.javascript.host.XPathResult;
import com.android.gargoylesoftware.htmlunit.javascript.host.XSLTProcessor;
import com.android.gargoylesoftware.htmlunit.javascript.host.XSLTemplate;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.ArrayBuffer;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.ArrayBufferView;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.ArrayBufferViewBase;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.DataView;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.Float32Array;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.Float64Array;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.Int16Array;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.Int32Array;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.Int8Array;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.Uint16Array;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.Uint32Array;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.Uint8Array;
import com.android.gargoylesoftware.htmlunit.javascript.host.arrays.Uint8ClampedArray;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.CanvasRenderingContext2D;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSCharsetRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSFontFaceRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSImportRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSMediaRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSPrimitiveValue;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSRuleList;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSStyleDeclaration;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSStyleRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSStyleSheet;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSValue;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.ComputedCSSStyleDeclaration;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.StyleSheetList;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMException;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMImplementation;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMParser;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMStringMap;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMTokenList;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLAnchorElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLAppletElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLAreaElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLAudioElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLBGSoundElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLBRElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLBaseElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLBaseFontElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLBlockQuoteElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLBodyElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLButtonElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLCanvasElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLCollection;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLCollectionTags;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLDListElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLDelElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLDirectoryElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLDivElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLDocument;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLEmbedElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLFieldSetElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLFontElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLFormElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLFrameElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLFrameSetElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLHRElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLHeadElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLHeadingElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLHtmlElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLIFrameElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLImageElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLInlineQuotationElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLInputElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLInsElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLIsIndexElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLLIElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLLabelElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLLegendElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLLinkElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLListElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLMapElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLMediaElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLMenuElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLMetaElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLMeterElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLOListElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLObjectElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLOptGroupElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLOptionElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLOptionsCollection;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLParagraphElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLParamElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLPreElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLProgressElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLScriptElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLSelectElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLSourceElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLSpacerElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLSpanElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLStyleElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLTableCaptionElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLTableCellElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLTableColElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLTableComponent;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLTableElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLTableRowElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLTableSectionElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLTextAreaElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLTitleElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLUListElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLUnknownElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLVideoElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLWBRElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGAElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGAltGlyphElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGAngle;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGAnimateElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGAnimateMotionElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGAnimateTransformElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGCircleElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGClipPathElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGDefsElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGDescElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGEllipseElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEBlendElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEColorMatrixElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEComponentTransferElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFECompositeElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEConvolveMatrixElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEDiffuseLightingElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEDisplacementMapElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEDistantLightElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEFloodElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEFuncAElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEFuncBElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEFuncGElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEFuncRElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEGaussianBlurElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEImageElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEMergeElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEMergeNodeElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEMorphologyElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEOffsetElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFEPointLightElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFESpecularLightingElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFESpotLightElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFETileElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFETurbulenceElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGFilterElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGForeignObjectElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGGElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGImageElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGLineElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGLinearGradientElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGMarkerElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGMaskElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGMatrix;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGMetadataElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGMpathElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGPathElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGPatternElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGPolygonElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGPolylineElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGRadialGradientElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGRect;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGRectElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGSVGElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGScriptElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGSetElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGStopElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGStyleElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGSwitchElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGSymbolElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGTSpanElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGTextElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGTextPathElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGTitleElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.SVGUseElement;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLAttr;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLDOMParseError;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLDocument;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLHttpRequest;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLSerializer;

/**
 * A container for all the JavaScript configuration information.
 *
 * @version $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Chris Erskine
 * @author Ahmed Ashour
 */
public final class JavaScriptConfiguration {

    private static final Log LOG = LogFactory.getLog(JavaScriptConfiguration.class);

    @SuppressWarnings("unchecked")
    static final Class<? extends SimpleScriptable>[] CLASSES_ = new Class[] {
        ArrayBuffer.class, ArrayBufferView.class, ArrayBufferViewBase.class,
        Attr.class, ActiveXObject.class, BoxObject.class, CDATASection.class, ClipboardData.class,
        CSSCharsetRule.class, CSSFontFaceRule.class, CSSImportRule.class, CSSMediaRule.class, CSSPrimitiveValue.class,
        CSSRule.class,
        CSSRuleList.class, CSSStyleDeclaration.class, CSSStyleRule.class, CSSStyleSheet.class, CSSValue.class,
        CanvasRenderingContext2D.class, CharacterDataImpl.class, ClientRect.class, Comment.class,
        ComputedCSSStyleDeclaration.class, Console.class, Coordinates.class, DataView.class, DOMException.class,
        DOMImplementation.class, DOMParser.class, DOMStringMap.class,
        DOMTokenList.class, Document.class, DocumentFragment.class,
        DocumentType.class, Element.class, Enumerator.class, Event.class, EventNode.class, External.class,
        Float32Array.class, Float64Array.class,
        FormChild.class, FormField.class, Geolocation.class, History.class,
        HTMLAnchorElement.class, HTMLAppletElement.class, HTMLAreaElement.class, HTMLAudioElement.class,
        HTMLBRElement.class, HTMLBaseElement.class, HTMLBaseFontElement.class, HTMLBGSoundElement.class,
        HTMLBlockQuoteElement.class, HTMLBodyElement.class, HTMLButtonElement.class, HTMLCanvasElement.class,
        HTMLCollection.class, HTMLCollectionTags.class, HTMLDListElement.class,
        HTMLDelElement.class, HTMLDirectoryElement.class,
        HTMLDivElement.class, HTMLDocument.class, HTMLElement.class, HTMLEmbedElement.class, HTMLFieldSetElement.class,
        HTMLFontElement.class, HTMLFormElement.class, HTMLFrameElement.class, HTMLFrameSetElement.class,
        HTMLHRElement.class, HTMLHeadElement.class, HTMLHeadingElement.class, HTMLHtmlElement.class,
        HTMLIFrameElement.class, HTMLImageElement.class, HTMLInlineQuotationElement.class, HTMLInputElement.class,
        HTMLInsElement.class, HTMLIsIndexElement.class, HTMLLIElement.class, HTMLLabelElement.class,
        HTMLLegendElement.class, HTMLLinkElement.class, HTMLListElement.class, HTMLMapElement.class,
        HTMLMediaElement.class, HTMLMenuElement.class, HTMLMetaElement.class, HTMLMeterElement.class,
        HTMLOListElement.class, HTMLObjectElement.class, HTMLOptGroupElement.class,
        HTMLOptionElement.class, HTMLOptionsCollection.class, HTMLParagraphElement.class, HTMLParamElement.class,
        HTMLPreElement.class, HTMLProgressElement.class, HTMLScriptElement.class,
        HTMLSelectElement.class, HTMLSourceElement.class, HTMLSpacerElement.class, HTMLSpanElement.class,
        HTMLStyleElement.class, HTMLTableCaptionElement.class, HTMLTableCellElement.class, HTMLTableColElement.class,
        HTMLTableComponent.class, HTMLTableElement.class, HTMLTableRowElement.class, HTMLTableSectionElement.class,
        HTMLTextAreaElement.class, HTMLTitleElement.class, HTMLUListElement.class, HTMLUnknownElement.class,
        HTMLVideoElement.class, HTMLWBRElement.class, HashChangeEvent.class, History.class,
        Int16Array.class, Int32Array.class, Int8Array.class,
        KeyboardEvent.class,
        Location.class, MediaList.class, MessageEvent.class, MimeType.class, MimeTypeArray.class, MouseEvent.class,
        MutationEvent.class, NamedNodeMap.class, Namespace.class, NamespaceCollection.class, Navigator.class,
        Node.class, NodeFilter.class, NodeList.class, OfflineResourceList.class,
        Plugin.class, PluginArray.class, Popup.class, Position.class, ProcessingInstruction.class,
        Range.class, RowContainer.class,
        SVGAElement.class, SVGAltGlyphElement.class, SVGAngle.class, SVGAnimateElement.class,
        SVGAnimateMotionElement.class, SVGAnimateTransformElement.class, SVGCircleElement.class,
        SVGClipPathElement.class, SVGDefsElement.class, SVGDescElement.class, SVGElement.class,
        SVGEllipseElement.class, SVGFEBlendElement.class, SVGFEColorMatrixElement.class,
        SVGFEComponentTransferElement.class, SVGFECompositeElement.class, SVGFEConvolveMatrixElement.class,
        SVGFEDiffuseLightingElement.class, SVGFEDisplacementMapElement.class, SVGFEDistantLightElement.class,
        SVGFEFloodElement.class, SVGFEFuncAElement.class, SVGFEFuncBElement.class, SVGFEFuncGElement.class,
        SVGFEFuncRElement.class, SVGFEGaussianBlurElement.class, SVGFEImageElement.class, SVGFEMergeElement.class,
        SVGFEMergeNodeElement.class, SVGFEMorphologyElement.class, SVGFEOffsetElement.class,
        SVGFEPointLightElement.class, SVGFESpecularLightingElement.class, SVGFESpotLightElement.class,
        SVGFETileElement.class, SVGFETurbulenceElement.class, SVGFilterElement.class, SVGForeignObjectElement.class,
        SVGGElement.class, SVGImageElement.class, SVGLineElement.class, SVGLinearGradientElement.class,
        SVGMarkerElement.class, SVGMaskElement.class, SVGMatrix.class, SVGMetadataElement.class, SVGMpathElement.class,
        SVGPathElement.class, SVGPatternElement.class, SVGPolygonElement.class, SVGPolylineElement.class,
        SVGRadialGradientElement.class,
        SVGRect.class, SVGRectElement.class,
        SVGSVGElement.class, SVGScriptElement.class,
        SVGSetElement.class, SVGStopElement.class, SVGStyleElement.class, SVGSwitchElement.class,
        SVGSymbolElement.class, SVGTSpanElement.class, SVGTextElement.class, SVGTextPathElement.class,
        SVGTitleElement.class, SVGUseElement.class, Screen.class, Selection.class, SimpleArray.class,
        StaticNodeList.class, Storage.class, StyleSheetList.class, Text.class, TextRange.class, TreeWalker.class,
        UIEvent.class, Uint16Array.class, Uint32Array.class, Uint8Array.class, Uint8ClampedArray.class,
        WebSocket.class, Window.class, XMLAttr.class, XMLDocument.class, XMLDOMParseError.class,
        XMLHttpRequest.class, XMLSerializer.class, XPathEvaluator.class, XPathNSResolver.class, XPathResult.class,
        XSLTProcessor.class, XSLTemplate.class};

    /** Cache of browser versions and their corresponding JavaScript configurations. */
    private static final Map<BrowserVersion, JavaScriptConfiguration> ConfigurationMap_ =
        new WeakHashMap<BrowserVersion, JavaScriptConfiguration>();

    private static final Map<String, String> ClassnameMap_ = new HashMap<String, String>();

    private Map<Class<?>, Class<? extends SimpleScriptable>> domJavaScriptMap_;

    private final Map<String, ClassConfiguration> configuration_;

    /**
     * Constructor is only called from {@link #getInstance(BrowserVersion)} which is synchronized.
     * @param browser the browser version to use
     */
    private JavaScriptConfiguration(final BrowserVersion browser) {
        configuration_ = buildUsageMap(browser);
    }

    /**
     * Returns the instance that represents the configuration for the specified {@link BrowserVersion}.
     * This method is synchronized to allow multi-threaded access to the JavaScript configuration.
     * @param browserVersion the {@link BrowserVersion}
     * @return the instance for the specified {@link BrowserVersion}
     */
    public static synchronized JavaScriptConfiguration getInstance(final BrowserVersion browserVersion) {
        if (browserVersion == null) {
            throw new IllegalStateException("BrowserVersion must be defined");
        }
        JavaScriptConfiguration configuration = ConfigurationMap_.get(browserVersion);

        if (configuration == null) {
            configuration = new JavaScriptConfiguration(browserVersion);
            ConfigurationMap_.put(browserVersion, configuration);
        }
        return configuration;
    }

    /**
     * Returns the configuration that has all entries. No constraints are put on the returned entries.
     *
     * @return the instance containing all entries from the configuration file
     */
    static JavaScriptConfiguration getAllEntries() {
        final JavaScriptConfiguration configuration = new JavaScriptConfiguration(null);
        return configuration;
    }

    /**
     * Gets all the configurations.
     * @return the class configurations
     */
    public Iterable<ClassConfiguration> getAll() {
        return configuration_.values();
    }

    private Map<String, ClassConfiguration> buildUsageMap(final BrowserVersion browser) {
        final Map<String, ClassConfiguration> classMap = new HashMap<String, ClassConfiguration>(CLASSES_.length);

        for (final Class<? extends SimpleScriptable> klass : CLASSES_) {
            final ClassConfiguration config = processClass(klass, browser);
            if (config != null) {
                classMap.put(config.getHostClass().getSimpleName(), config);
            }
        }
        return Collections.unmodifiableMap(classMap);
    }

    private ClassConfiguration processClass(final Class<? extends SimpleScriptable> klass,
            final BrowserVersion browser) {
        if (browser != null) {
            final JsxClass jsxClass = klass.getAnnotation(JsxClass.class);
            final String expectedBrowserName;
            if (browser.isIE()) {
                expectedBrowserName = "IE";
            }
            else if (browser.isFirefox()) {
                expectedBrowserName = "FF";
            }
            else {
                expectedBrowserName = "CHROME";
            }
            final float browserVersionNumeric = browser.getBrowserVersionNumeric();
            if (jsxClass != null && isSupported(jsxClass.browsers(), expectedBrowserName, browserVersionNumeric)) {
                final String hostClassName = klass.getName();

                final Class<?>[] domClasses = jsxClass.domClasses();

                final boolean isJsObject = jsxClass.isJSObject();
                final ClassConfiguration classConfiguration = new ClassConfiguration(klass, domClasses, isJsObject);

                final String simpleClassName = hostClassName.substring(hostClassName.lastIndexOf('.') + 1);
                ClassnameMap_.put(hostClassName, simpleClassName);
                final Map<String, Method> allGetters = new HashMap<String, Method>();
                final Map<String, Method> allSetters = new HashMap<String, Method>();
                for (final Method method : classConfiguration.getHostClass().getDeclaredMethods()) {
                    for (final Annotation annotation : method.getAnnotations()) {
                        if (annotation instanceof JsxGetter) {
                            final JsxGetter jsxGetter = (JsxGetter) annotation;
                            if (isSupported(jsxGetter.value(), expectedBrowserName, browserVersionNumeric)) {
                                String property;
                                if (jsxGetter.propertyName().isEmpty()) {
                                    property = method.getName().substring(3);
                                    property = Character.toLowerCase(property.charAt(0)) + property.substring(1);
                                }
                                else {
                                    property = jsxGetter.propertyName();
                                }
                                allGetters.put(property, method);
                            }
                        }
                        else if (annotation instanceof JsxSetter) {
                            final JsxSetter jsxSetter = (JsxSetter) annotation;
                            if (isSupported(jsxSetter.value(), expectedBrowserName, browserVersionNumeric)) {
                                String property;
                                if (jsxSetter.propertyName().isEmpty()) {
                                    property = method.getName().substring(3);
                                    property = Character.toLowerCase(property.charAt(0)) + property.substring(1);
                                }
                                else {
                                    property = jsxSetter.propertyName();
                                }
                                allSetters.put(property, method);
                            }
                        }
                        else if (annotation instanceof JsxFunction) {
                            if (isSupported(((JsxFunction) annotation).value(),
                                    expectedBrowserName, browserVersionNumeric)) {
                                classConfiguration.addFunction(method);
                            }
                        }
                        else if (annotation instanceof JsxConstructor) {
                            classConfiguration.setJSConstructor(method);
                        }
                    }
                }
                for (final Field field : classConfiguration.getHostClass().getDeclaredFields()) {
                    final JsxConstant jsxConstant = field.getAnnotation(JsxConstant.class);
                    if (jsxConstant != null
                            && isSupported(jsxConstant.value(), expectedBrowserName, browserVersionNumeric)) {
                        classConfiguration.addConstant(field.getName());
                    }
                }
                for (final Entry<String, Method> getterEntry : allGetters.entrySet()) {
                    final String property = getterEntry.getKey();
                    classConfiguration.addProperty(property,
                            getterEntry.getValue(), allSetters.get(property));
                }
                return classConfiguration;
            }
        }
        return null;
    }

    private static boolean isSupported(final WebBrowser[] browsers, final String expectedBrowserName,
            final float expectedVersionNumeric) {
        for (final WebBrowser browser : browsers) {
            if (browser.value().name().equals(expectedBrowserName)
                    && browser.minVersion() <= expectedVersionNumeric
                    && browser.maxVersion() >= expectedVersionNumeric) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the class configuration for the supplied JavaScript class name.
     * @param hostClassName the js class name
     * @return the class configuration for the supplied JavaScript class name
     */
    public ClassConfiguration getClassConfiguration(final String hostClassName) {
        return configuration_.get(hostClassName);
    }

    /**
     * Returns the classname that the given class implements. If the class is
     * the input class, then the name is extracted from the type that the Input class
     * is masquerading as.
     * FIXME - Implement the Input class processing
     * @param clazz
     * @return the classname
     */
    String getClassnameForClass(final Class<?> clazz) {
        final String name = ClassnameMap_.get(clazz.getName());
        if (name == null) {
            throw new IllegalStateException("Did not find the mapping of the class to the classname for "
                + clazz.getName());
        }
        return name;
    }

    /**
     * Returns an immutable map containing the DOM to JavaScript mappings. Keys are
     * java classes for the various DOM classes (e.g. HtmlInput.class) and the values
     * are the JavaScript class names (e.g. "HTMLAnchorElement").
     * @return the mappings
     */
    public Map<Class<?>, Class<? extends SimpleScriptable>>
    getDomJavaScriptMapping() {
        if (domJavaScriptMap_ != null) {
            return domJavaScriptMap_;
        }

        final Map<Class<?>, Class<? extends SimpleScriptable>> map =
            new HashMap<Class<?>, Class<? extends SimpleScriptable>>();

        for (String hostClassName : configuration_.keySet()) {
            ClassConfiguration classConfig = getClassConfiguration(hostClassName);
            for (final Class<?> domClass : classConfig.getDomClasses()) {
                // preload and validate that the class exists
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Mapping " + domClass.getName() + " to " + hostClassName);
                }
                while (!classConfig.isJsObject()) {
                    hostClassName = classConfig.getExtendedClassName();
                    classConfig = getClassConfiguration(hostClassName);
                }
                map.put(domClass, classConfig.getHostClass());
            }
        }

        domJavaScriptMap_ = Collections.unmodifiableMap(map);

        return domJavaScriptMap_;
    }
}
