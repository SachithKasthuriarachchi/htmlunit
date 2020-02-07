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
package com.android.gargoylesoftware.htmlunit.javascript.configuration;

import java.util.Map;
import java.util.WeakHashMap;

import com.android.gargoylesoftware.htmlunit.BrowserVersion;
import com.android.gargoylesoftware.htmlunit.javascript.NamedNodeMap;
import com.android.gargoylesoftware.htmlunit.javascript.SimpleScriptable;
import com.android.gargoylesoftware.htmlunit.javascript.host.ActiveXObject;
import com.android.gargoylesoftware.htmlunit.javascript.host.Animation;
import com.android.gargoylesoftware.htmlunit.javascript.host.ApplicationCache;
import com.android.gargoylesoftware.htmlunit.javascript.host.AudioScheduledSourceNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.BarProp;
import com.android.gargoylesoftware.htmlunit.javascript.host.BatteryManager;
import com.android.gargoylesoftware.htmlunit.javascript.host.BroadcastChannel;
import com.android.gargoylesoftware.htmlunit.javascript.host.Cache;
import com.android.gargoylesoftware.htmlunit.javascript.host.CacheStorage;
import com.android.gargoylesoftware.htmlunit.javascript.host.ClientRect;
import com.android.gargoylesoftware.htmlunit.javascript.host.ClientRectList;
import com.android.gargoylesoftware.htmlunit.javascript.host.Console;
import com.android.gargoylesoftware.htmlunit.javascript.host.Element;
import com.android.gargoylesoftware.htmlunit.javascript.host.External;
import com.android.gargoylesoftware.htmlunit.javascript.host.FontFace;
import com.android.gargoylesoftware.htmlunit.javascript.host.FontFaceSet;
import com.android.gargoylesoftware.htmlunit.javascript.host.Gamepad;
import com.android.gargoylesoftware.htmlunit.javascript.host.GamepadButton;
import com.android.gargoylesoftware.htmlunit.javascript.host.History;
import com.android.gargoylesoftware.htmlunit.javascript.host.ImageBitmap;
import com.android.gargoylesoftware.htmlunit.javascript.host.InstallTrigger;
import com.android.gargoylesoftware.htmlunit.javascript.host.Location;
import com.android.gargoylesoftware.htmlunit.javascript.host.MessageChannel;
import com.android.gargoylesoftware.htmlunit.javascript.host.MessagePort;
import com.android.gargoylesoftware.htmlunit.javascript.host.MimeType;
import com.android.gargoylesoftware.htmlunit.javascript.host.MimeTypeArray;
import com.android.gargoylesoftware.htmlunit.javascript.host.Namespace;
import com.android.gargoylesoftware.htmlunit.javascript.host.NamespaceCollection;
import com.android.gargoylesoftware.htmlunit.javascript.host.Navigator;
import com.android.gargoylesoftware.htmlunit.javascript.host.Notification;
import com.android.gargoylesoftware.htmlunit.javascript.host.PerformanceObserver;
import com.android.gargoylesoftware.htmlunit.javascript.host.PerformanceObserverEntryList;
import com.android.gargoylesoftware.htmlunit.javascript.host.PermissionStatus;
import com.android.gargoylesoftware.htmlunit.javascript.host.Permissions;
import com.android.gargoylesoftware.htmlunit.javascript.host.Plugin;
import com.android.gargoylesoftware.htmlunit.javascript.host.PluginArray;
import com.android.gargoylesoftware.htmlunit.javascript.host.Promise;
import com.android.gargoylesoftware.htmlunit.javascript.host.Proxy;
import com.android.gargoylesoftware.htmlunit.javascript.host.PushManager;
import com.android.gargoylesoftware.htmlunit.javascript.host.PushSubscription;
import com.android.gargoylesoftware.htmlunit.javascript.host.PushSubscriptionOptions;
import com.android.gargoylesoftware.htmlunit.javascript.host.ReadableStream;
import com.android.gargoylesoftware.htmlunit.javascript.host.Screen;
import com.android.gargoylesoftware.htmlunit.javascript.host.ScreenOrientation;
import com.android.gargoylesoftware.htmlunit.javascript.host.Set;
import com.android.gargoylesoftware.htmlunit.javascript.host.SharedWorker;
import com.android.gargoylesoftware.htmlunit.javascript.host.SimpleArray;
import com.android.gargoylesoftware.htmlunit.javascript.host.Storage;
import com.android.gargoylesoftware.htmlunit.javascript.host.StorageManager;
import com.android.gargoylesoftware.htmlunit.javascript.host.TextDecoder;
import com.android.gargoylesoftware.htmlunit.javascript.host.TextEncoder;
import com.android.gargoylesoftware.htmlunit.javascript.host.Touch;
import com.android.gargoylesoftware.htmlunit.javascript.host.TouchList;
import com.android.gargoylesoftware.htmlunit.javascript.host.URL;
import com.android.gargoylesoftware.htmlunit.javascript.host.URLSearchParams;
import com.android.gargoylesoftware.htmlunit.javascript.host.WeakMap;
import com.android.gargoylesoftware.htmlunit.javascript.host.WeakSet;
import com.android.gargoylesoftware.htmlunit.javascript.host.WebSocket;
import com.android.gargoylesoftware.htmlunit.javascript.host.Window;
import com.android.gargoylesoftware.htmlunit.javascript.host.XPathExpression;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.webkitURL;
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
import com.android.gargoylesoftware.htmlunit.javascript.host.budget.BudgetService;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.CanvasCaptureMediaStream;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.CanvasCaptureMediaStreamTrack;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.CanvasGradient;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.CanvasPattern;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.CanvasRenderingContext2D;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ImageBitmapRenderingContext;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ImageData;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.IntersectionObserver;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.IntersectionObserverEntry;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.Path2D;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.TextMetrics;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGL2RenderingContext;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLActiveInfo;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLBuffer;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLFramebuffer;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLProgram;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLQuery;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLRenderbuffer;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLRenderingContext;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLSampler;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLShader;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLShaderPrecisionFormat;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLSync;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLTexture;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLTransformFeedback;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLUniformLocation;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLVertexArrayObject;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ext.ANGLE_instanced_arrays;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ext.EXT_texture_filter_anisotropic;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ext.OES_element_index_uint;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ext.OES_standard_derivatives;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ext.OES_texture_float;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ext.OES_texture_float_linear;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ext.WEBGL_compressed_texture_s3tc;
import com.android.gargoylesoftware.htmlunit.javascript.host.canvas.ext.WEBGL_debug_renderer_info;
import com.android.gargoylesoftware.htmlunit.javascript.host.crypto.Crypto;
import com.android.gargoylesoftware.htmlunit.javascript.host.crypto.CryptoKey;
import com.android.gargoylesoftware.htmlunit.javascript.host.crypto.SubtleCrypto;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSS;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSS2Properties;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSConditionRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSCounterStyleRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSFontFaceRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSGroupingRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSImportRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSKeyframeRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSKeyframesRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSMediaRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSNamespaceRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSPageRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSPrimitiveValue;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSRuleList;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSStyleDeclaration;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSStyleRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSStyleSheet;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSSupportsRule;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSValue;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CSSValueList;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.CaretPosition;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.ComputedCSSStyleDeclaration;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.MediaQueryList;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.StyleMedia;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.StyleSheet;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.StyleSheetList;
import com.android.gargoylesoftware.htmlunit.javascript.host.css.WebKitCSSMatrix;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.AbstractList;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.Attr;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.CDATASection;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.CharacterData;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.Comment;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.CustomElementRegistry;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMCursor;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMError;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMException;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMImplementation;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMMatrix;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMMatrixReadOnly;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMParser;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMPoint;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMPointReadOnly;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMRectReadOnly;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMRequest;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMSettableTokenList;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMStringList;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMStringMap;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DOMTokenList;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DocumentFragment;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.DocumentType;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.IdleDeadline;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.MediaList;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.MutationObserver;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.MutationRecord;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.Node;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.NodeFilter;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.NodeIterator;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.NodeList;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.ProcessingInstruction;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.RadioNodeList;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.Range;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.Selection;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.ShadowRoot;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.Text;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.TextRange;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.TreeWalker;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.WebKitMutationObserver;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.XPathEvaluator;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.XPathNSResolver;
import com.android.gargoylesoftware.htmlunit.javascript.host.dom.XPathResult;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.AnimationEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.ApplicationCacheErrorEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.AudioProcessingEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.BeforeInstallPromptEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.BeforeUnloadEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.BlobEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.ClipboardEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.CloseEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.CompositionEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.CustomEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.DeviceLightEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.DeviceMotionEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.DeviceOrientationEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.DeviceProximityEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.DragEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.ErrorEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.Event;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.EventSource;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.EventTarget;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.FocusEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.GamepadEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.HashChangeEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.IDBVersionChangeEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.InputEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.KeyboardEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MIDIConnectionEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MIDIMessageEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MSGestureEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MediaEncryptedEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MediaKeyMessageEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MediaQueryListEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MediaStreamEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MediaStreamTrackEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MessageEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MouseEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MouseScrollEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MouseWheelEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.MutationEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.OfflineAudioCompletionEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.PageTransitionEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.PointerEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.PopStateEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.PresentationConnectionAvailableEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.PresentationConnectionCloseEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.ProgressEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.PromiseRejectionEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.RTCDataChannelEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.RTCPeerConnectionIceEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.SVGZoomEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.SecurityPolicyViolationEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.SpeechSynthesisEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.StorageEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.TextEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.TimeEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.TouchEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.TrackEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.TransitionEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.UIEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.UserProximityEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.WebGLContextEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.WebKitAnimationEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.WebKitTransitionEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.WheelEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.webkitSpeechRecognitionError;
import com.android.gargoylesoftware.htmlunit.javascript.host.event.webkitSpeechRecognitionEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.fetch.Headers;
import com.android.gargoylesoftware.htmlunit.javascript.host.fetch.Request;
import com.android.gargoylesoftware.htmlunit.javascript.host.fetch.Response;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.Blob;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.DataTransferItem;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.DataTransferItemList;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.File;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.FileList;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.FileReader;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.FileSystem;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.FileSystemDirectoryEntry;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.FileSystemDirectoryReader;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.FileSystemEntry;
import com.android.gargoylesoftware.htmlunit.javascript.host.file.FileSystemFileEntry;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.Coordinates;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.Geolocation;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.Position;
import com.android.gargoylesoftware.htmlunit.javascript.host.geo.PositionError;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBCursor;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBCursorWithValue;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBDatabase;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBFactory;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBIndex;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBKeyRange;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBMutableFile;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBObjectStore;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBOpenDBRequest;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBRequest;
import com.android.gargoylesoftware.htmlunit.javascript.host.idb.IDBTransaction;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AnalyserNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AudioBuffer;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AudioBufferSourceNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AudioContext;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AudioDestinationNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AudioListener;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AudioNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.AudioParam;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.BaseAudioContext;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.BiquadFilterNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.ChannelMergerNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.ChannelSplitterNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.ConstantSourceNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.ConvolverNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.DelayNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.DynamicsCompressorNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.GainNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.IIRFilterNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.InputDeviceCapabilities;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.LocalMediaStream;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaDeviceInfo;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaDevices;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaElementAudioSourceNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaError;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaKeyError;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaKeySession;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaKeyStatusMap;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaKeySystemAccess;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaKeys;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaRecorder;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaSource;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaStream;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaStreamAudioDestinationNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaStreamAudioSourceNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.MediaStreamTrack;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.OfflineAudioContext;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.OscillatorNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.PannerNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.PeriodicWave;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.RemotePlayback;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.ScriptProcessorNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.SourceBuffer;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.SourceBufferList;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.StereoPannerNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.TextTrack;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.TextTrackCue;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.TextTrackCueList;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.TextTrackList;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.TimeRanges;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.VTTCue;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.VideoPlaybackQuality;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.WaveShaperNode;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.midi.MIDIAccess;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.midi.MIDIInput;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.midi.MIDIInputMap;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.midi.MIDIOutput;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.midi.MIDIOutputMap;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.midi.MIDIPort;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.presentation.Presentation;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.presentation.PresentationAvailability;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.presentation.PresentationConnection;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.presentation.PresentationRequest;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.rtc.RTCCertificate;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.rtc.RTCIceCandidate;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.rtc.RTCPeerConnection;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.rtc.RTCSessionDescription;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.rtc.RTCStatsReport;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.rtc.mozRTCIceCandidate;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.rtc.mozRTCPeerConnection;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.rtc.mozRTCSessionDescription;
import com.android.gargoylesoftware.htmlunit.javascript.host.media.rtc.webkitRTCPeerConnection;
import com.android.gargoylesoftware.htmlunit.javascript.host.network.NetworkInformation;
import com.android.gargoylesoftware.htmlunit.javascript.host.payment.PaymentAddress;
import com.android.gargoylesoftware.htmlunit.javascript.host.payment.PaymentRequest;
import com.android.gargoylesoftware.htmlunit.javascript.host.payment.PaymentResponse;
import com.android.gargoylesoftware.htmlunit.javascript.host.performance.Performance;
import com.android.gargoylesoftware.htmlunit.javascript.host.performance.PerformanceEntry;
import com.android.gargoylesoftware.htmlunit.javascript.host.performance.PerformanceMark;
import com.android.gargoylesoftware.htmlunit.javascript.host.performance.PerformanceMeasure;
import com.android.gargoylesoftware.htmlunit.javascript.host.performance.PerformanceNavigation;
import com.android.gargoylesoftware.htmlunit.javascript.host.performance.PerformanceNavigationTiming;
import com.android.gargoylesoftware.htmlunit.javascript.host.performance.PerformanceResourceTiming;
import com.android.gargoylesoftware.htmlunit.javascript.host.performance.PerformanceTiming;
import com.android.gargoylesoftware.htmlunit.javascript.host.security.Credential;
import com.android.gargoylesoftware.htmlunit.javascript.host.security.CredentialsContainer;
import com.android.gargoylesoftware.htmlunit.javascript.host.security.FederatedCredential;
import com.android.gargoylesoftware.htmlunit.javascript.host.security.PasswordCredential;
import com.android.gargoylesoftware.htmlunit.javascript.host.speech.SpeechSynthesis;
import com.android.gargoylesoftware.htmlunit.javascript.host.speech.SpeechSynthesisErrorEvent;
import com.android.gargoylesoftware.htmlunit.javascript.host.speech.SpeechSynthesisUtterance;
import com.android.gargoylesoftware.htmlunit.javascript.host.speech.SpeechSynthesisVoice;
import com.android.gargoylesoftware.htmlunit.javascript.host.speech.webkitSpeechGrammar;
import com.android.gargoylesoftware.htmlunit.javascript.host.speech.webkitSpeechGrammarList;
import com.android.gargoylesoftware.htmlunit.javascript.host.speech.webkitSpeechRecognition;
import com.android.gargoylesoftware.htmlunit.javascript.host.svg.*;
import com.android.gargoylesoftware.htmlunit.javascript.host.worker.ServiceWorker;
import com.android.gargoylesoftware.htmlunit.javascript.host.worker.ServiceWorkerContainer;
import com.android.gargoylesoftware.htmlunit.javascript.host.worker.ServiceWorkerRegistration;
import com.android.gargoylesoftware.htmlunit.javascript.host.worker.SyncManager;
import com.android.gargoylesoftware.htmlunit.javascript.host.worker.Worker;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.FormData;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLDocument;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLHttpRequest;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLHttpRequestEventTarget;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLHttpRequestUpload;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XMLSerializer;
import com.android.gargoylesoftware.htmlunit.javascript.host.xml.XSLTProcessor;

/**
 * A container for all the JavaScript configuration information.
 *
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Chris Erskine
 * @author Ahmed Ashour
 * @author Ronald Brill
 * @author Frank Danek
 */
public final class JavaScriptConfiguration extends AbstractJavaScriptConfiguration {

    @SuppressWarnings("unchecked")
    static final Class<? extends SimpleScriptable>[] CLASSES_ = new Class[] {
        AbstractList.class, ActiveXObject.class, AnalyserNode.class, ANGLE_instanced_arrays.class,
        Animation.class, AnimationEvent.class,
        ApplicationCache.class, ApplicationCacheErrorEvent.class,

        // disabled because of Spectre:
        // Atomics.class,

        Attr.class, Audio.class, AudioBuffer.class,
        AudioBufferSourceNode.class, AudioContext.class, AudioDestinationNode.class, AudioListener.class,
        AudioNode.class, AudioParam.class, AudioProcessingEvent.class, AudioScheduledSourceNode.class,
        BarProp.class, BaseAudioContext.class,
        BatteryManager.class, BeforeInstallPromptEvent.class, BeforeUnloadEvent.class, BiquadFilterNode.class,
        Blob.class, BlobEvent.class, BroadcastChannel.class,
        BudgetService.class,
        Cache.class, CacheStorage.class,
        CanvasCaptureMediaStream.class, CanvasCaptureMediaStreamTrack.class,
        CanvasGradient.class, CanvasPattern.class, CanvasRenderingContext2D.class, CaretPosition.class,
        CDATASection.class, ChannelMergerNode.class, ChannelSplitterNode.class, CharacterData.class, ClientRect.class,
        ClientRectList.class, ClipboardEvent.class,
        CloseEvent.class, Comment.class, CompositionEvent.class, ComputedCSSStyleDeclaration.class, Console.class,
        ConstantSourceNode.class,
        ConvolverNode.class, Coordinates.class, Credential.class, CredentialsContainer.class, Crypto.class,
        CryptoKey.class, CSS.class, CSS2Properties.class, CSSConditionRule.class,
        CSSCounterStyleRule.class, CSSFontFaceRule.class, CSSGroupingRule.class, CSSImportRule.class,
        CSSKeyframeRule.class, CSSKeyframesRule.class, CSSMediaRule.class, CSSNamespaceRule.class, CSSPageRule.class,
        CSSPrimitiveValue.class, CSSRule.class, CSSRuleList.class, CSSStyleDeclaration.class, CSSStyleRule.class,
        CSSStyleSheet.class,
        CSSSupportsRule.class, CSSValue.class, CSSValueList.class, CustomElementRegistry.class,
        CustomEvent.class, DataTransfer.class,
        DataTransferItem.class, DataTransferItemList.class,
        DelayNode.class, DeviceLightEvent.class,
        DeviceMotionEvent.class, DeviceOrientationEvent.class, DeviceProximityEvent.class,
        Document.class, DocumentFragment.class, DocumentType.class, DOMCursor.class, DOMError.class, DOMException.class,
        DOMImplementation.class, DOMMatrix.class, DOMMatrixReadOnly.class, DOMParser.class, DOMPoint.class,
        DOMPointReadOnly.class, DOMRectReadOnly.class, DOMRequest.class,
        DOMSettableTokenList.class, DOMStringList.class, DOMStringMap.class, DOMTokenList.class,
        DragEvent.class, DynamicsCompressorNode.class,
        Element.class, Enumerator.class, ErrorEvent.class, Event.class, EventSource.class,
        EventTarget.class, EXT_texture_filter_anisotropic.class, External.class, FederatedCredential.class,
        File.class, FileList.class, FileReader.class,
        FileSystem.class, FileSystemDirectoryEntry.class, FileSystemDirectoryReader.class,
        FileSystemEntry.class, FileSystemFileEntry.class,
        FocusEvent.class, FontFace.class,
        FontFaceSet.class, FormData.class, FormField.class, GainNode.class, Gamepad.class,
        GamepadButton.class, GamepadEvent.class, Geolocation.class, HashChangeEvent.class, Headers.class, History.class,
        HTMLAllCollection.class,
        HTMLAnchorElement.class, HTMLAppletElement.class, HTMLAreaElement.class, HTMLAudioElement.class,
        HTMLBaseElement.class, HTMLBaseFontElement.class, HTMLBGSoundElement.class, HTMLBlockElement.class,
        HTMLBodyElement.class, HTMLBRElement.class, HTMLButtonElement.class,
        HTMLCanvasElement.class, HTMLCollection.class, HTMLContentElement.class,
        HTMLDataElement.class, HTMLDataListElement.class,
        HTMLDDElement.class, HTMLDetailsElement.class, HTMLDialogElement.class, HTMLDirectoryElement.class,
        HTMLDivElement.class, HTMLDListElement.class, HTMLDocument.class, HTMLDTElement.class, HTMLElement.class,
        HTMLEmbedElement.class, HTMLFieldSetElement.class,
        HTMLFontElement.class, HTMLFormControlsCollection.class, HTMLFormElement.class, HTMLFrameElement.class,
        HTMLFrameSetElement.class,
        HTMLHeadElement.class, HTMLHeadingElement.class, HTMLHRElement.class, HTMLHtmlElement.class,
        HTMLIFrameElement.class, HTMLImageElement.class, HTMLInlineQuotationElement.class, HTMLInputElement.class,
        HTMLIsIndexElement.class, HTMLLabelElement.class,
        HTMLLegendElement.class, HTMLLIElement.class, HTMLLinkElement.class, HTMLListElement.class,
        HTMLMapElement.class, HTMLMarqueeElement.class,
        HTMLMediaElement.class, HTMLMenuElement.class, HTMLMenuItemElement.class, HTMLMetaElement.class,
        HTMLMeterElement.class, HTMLModElement.class, HTMLNextIdElement.class,
        HTMLObjectElement.class, HTMLOListElement.class, HTMLOptGroupElement.class,
        HTMLOptionElement.class, HTMLOptionsCollection.class, HTMLOutputElement.class,
        HTMLParagraphElement.class, HTMLParamElement.class, HTMLPhraseElement.class, HTMLPictureElement.class,
        HTMLPreElement.class, HTMLProgressElement.class, HTMLQuoteElement.class, HTMLScriptElement.class,
        HTMLSelectElement.class, HTMLShadowElement.class, HTMLSlotElement.class, HTMLSourceElement.class,
        HTMLSpanElement.class,
        HTMLStyleElement.class, HTMLTableCaptionElement.class, HTMLTableCellElement.class, HTMLTableColElement.class,
        HTMLTableComponent.class, HTMLTableDataCellElement.class, HTMLTableElement.class,
        HTMLTableHeaderCellElement.class, HTMLTableRowElement.class, HTMLTableSectionElement.class,
        HTMLTemplateElement.class, HTMLTextAreaElement.class, HTMLTimeElement.class,
        HTMLTitleElement.class, HTMLTrackElement.class, HTMLUListElement.class, HTMLUnknownElement.class,
        HTMLVideoElement.class,
        IDBCursor.class, IDBCursorWithValue.class, IDBDatabase.class, IDBFactory.class, IDBIndex.class,
        IDBKeyRange.class, IDBMutableFile.class, IDBObjectStore.class, IDBOpenDBRequest.class, IDBRequest.class,
        IDBTransaction.class, IDBVersionChangeEvent.class, IdleDeadline.class, IIRFilterNode.class,
        Image.class, ImageBitmap.class, ImageBitmapRenderingContext.class, ImageData.class,
        InputDeviceCapabilities.class, InputEvent.class,
        InstallTrigger.class,
        IntersectionObserver.class, IntersectionObserverEntry.class,
        KeyboardEvent.class, LocalMediaStream.class,
        Location.class, com.android.gargoylesoftware.htmlunit.javascript.host.Map.class,
        MediaDeviceInfo.class,
        MediaDevices.class, MediaElementAudioSourceNode.class, MediaEncryptedEvent.class, MediaError.class,
        MediaKeyError.class, MediaKeyMessageEvent.class, MediaKeys.class, MediaKeySession.class,
        MediaKeyStatusMap.class, MediaKeySystemAccess.class, MediaList.class, MediaQueryList.class,
        MediaQueryListEvent.class, MediaRecorder.class,
        MediaSource.class, MediaStream.class, MediaStreamAudioDestinationNode.class, MediaStreamAudioSourceNode.class,
        MediaStreamEvent.class, MediaStreamTrack.class, MediaStreamTrackEvent.class, MessageChannel.class,
        MessageEvent.class, MessagePort.class, MIDIAccess.class, MIDIConnectionEvent.class, MIDIInput.class,
        MIDIInputMap.class, MIDIMessageEvent.class, MIDIOutput.class, MIDIOutputMap.class, MIDIPort.class,
        MimeType.class, MimeTypeArray.class, MouseEvent.class, MouseScrollEvent.class,
        MouseWheelEvent.class, mozRTCIceCandidate.class, mozRTCPeerConnection.class, mozRTCSessionDescription.class,
        MSGestureEvent.class,
        MutationEvent.class, MutationObserver.class, MutationRecord.class, NamedNodeMap.class, Namespace.class,
        NamespaceCollection.class,
        Navigator.class, NetworkInformation.class, Node.class, NodeFilter.class, NodeIterator.class,
        NodeList.class, Notification.class, OES_element_index_uint.class, OES_standard_derivatives.class,
        OES_texture_float.class, OES_texture_float_linear.class, OfflineAudioCompletionEvent.class,
        OfflineAudioContext.class, Option.class, OscillatorNode.class, PageTransitionEvent.class, PannerNode.class,
        PasswordCredential.class,
        Path2D.class,
        PaymentAddress.class, PaymentRequest.class, PaymentResponse.class,
        Performance.class, PerformanceEntry.class, PerformanceMark.class,
        PerformanceMeasure.class, PerformanceNavigation.class, PerformanceNavigationTiming.class,
        PerformanceObserver.class, PerformanceObserverEntryList.class,
        PerformanceResourceTiming.class, PerformanceTiming.class,
        PeriodicWave.class, Permissions.class, PermissionStatus.class, Plugin.class, PluginArray.class,
        PointerEvent.class, PopStateEvent.class, Position.class, PositionError.class, Presentation.class,
        PresentationAvailability.class, PresentationConnection.class, PresentationConnectionAvailableEvent.class,
        PresentationConnectionCloseEvent.class, PresentationRequest.class,
        ProcessingInstruction.class, ProgressEvent.class, Promise.class, PromiseRejectionEvent.class,
        Proxy.class, PushManager.class,
        PushSubscription.class, PushSubscriptionOptions.class, RadioNodeList.class, Range.class, ReadableStream.class,
        RemotePlayback.class, Request.class, Response.class, RowContainer.class, RTCCertificate.class,
        RTCDataChannelEvent.class, RTCIceCandidate.class, RTCPeerConnection.class, RTCPeerConnectionIceEvent.class,
        RTCSessionDescription.class, RTCStatsReport.class, Screen.class, ScreenOrientation.class,
        ScriptProcessorNode.class,
        SecurityPolicyViolationEvent.class, Selection.class, ServiceWorker.class, ServiceWorkerContainer.class,
        ServiceWorkerRegistration.class, Set.class, ShadowRoot.class,

        // disabled because of Spectre:
        // SharedArrayBuffer.class,

        SharedWorker.class, SimpleArray.class, SourceBuffer.class, SourceBufferList.class,
        SpeechSynthesis.class, SpeechSynthesisErrorEvent.class, SpeechSynthesisEvent.class,
        SpeechSynthesisUtterance.class, SpeechSynthesisVoice.class,
        StereoPannerNode.class, Storage.class, StorageEvent.class, StorageManager.class,
        StyleMedia.class, StyleSheet.class, StyleSheetList.class, SubtleCrypto.class,
        SVGAElement.class, SVGAngle.class, SVGAnimatedAngle.class,
        SVGAnimatedBoolean.class, SVGAnimatedEnumeration.class, SVGAnimatedInteger.class,
        SVGAnimatedLength.class, SVGAnimatedLengthList.class, SVGAnimatedNumber.class, SVGAnimatedNumberList.class,
        SVGAnimatedPreserveAspectRatio.class, SVGAnimatedRect.class, SVGAnimatedString.class,
        SVGAnimatedTransformList.class, SVGAnimateElement.class,
        SVGAnimateMotionElement.class, SVGAnimateTransformElement.class, SVGAnimationElement.class,
        SVGCircleElement.class,
        SVGClipPathElement.class, SVGComponentTransferFunctionElement.class,
        SVGDefsElement.class, SVGDescElement.class, SVGDiscardElement.class, SVGElement.class,
        SVGEllipseElement.class, SVGFEBlendElement.class, SVGFEColorMatrixElement.class,
        SVGFEComponentTransferElement.class, SVGFECompositeElement.class, SVGFEConvolveMatrixElement.class,
        SVGFEDiffuseLightingElement.class, SVGFEDisplacementMapElement.class, SVGFEDistantLightElement.class,
        SVGFEDropShadowElement.class,
        SVGFEFloodElement.class, SVGFEFuncAElement.class, SVGFEFuncBElement.class, SVGFEFuncGElement.class,
        SVGFEFuncRElement.class, SVGFEGaussianBlurElement.class, SVGFEImageElement.class, SVGFEMergeElement.class,
        SVGFEMergeNodeElement.class, SVGFEMorphologyElement.class, SVGFEOffsetElement.class,
        SVGFEPointLightElement.class, SVGFESpecularLightingElement.class, SVGFESpotLightElement.class,
        SVGFETileElement.class, SVGFETurbulenceElement.class, SVGFilterElement.class, SVGForeignObjectElement.class,
        SVGGElement.class, SVGGeometryElement.class, SVGGradientElement.class, SVGGraphicsElement.class,
        SVGImageElement.class, SVGLength.class, SVGLengthList.class, SVGLinearGradientElement.class,
        SVGLineElement.class, SVGMarkerElement.class, SVGMaskElement.class, SVGMatrix.class,
        SVGMetadataElement.class, SVGMPathElement.class, SVGNumber.class, SVGNumberList.class,
        SVGPathElement.class, SVGPathSeg.class, SVGPathSegArcAbs.class,
        SVGPathSegArcRel.class, SVGPathSegClosePath.class, SVGPathSegCurvetoCubicAbs.class,
        SVGPathSegCurvetoCubicRel.class, SVGPathSegCurvetoCubicSmoothAbs.class, SVGPathSegCurvetoCubicSmoothRel.class,
        SVGPathSegCurvetoQuadraticAbs.class, SVGPathSegCurvetoQuadraticRel.class,
        SVGPathSegCurvetoQuadraticSmoothAbs.class, SVGPathSegCurvetoQuadraticSmoothRel.class,
        SVGPathSegLinetoAbs.class, SVGPathSegLinetoHorizontalAbs.class, SVGPathSegLinetoHorizontalRel.class,
        SVGPathSegLinetoRel.class, SVGPathSegLinetoVerticalAbs.class, SVGPathSegLinetoVerticalRel.class,
        SVGPathSegList.class, SVGPathSegMovetoAbs.class, SVGPathSegMovetoRel.class, SVGPatternElement.class,
        SVGPoint.class, SVGPointList.class, SVGPolygonElement.class, SVGPolylineElement.class,
        SVGPreserveAspectRatio.class, SVGRadialGradientElement.class, SVGRect.class, SVGRectElement.class,
        SVGScriptElement.class, SVGSetElement.class, SVGStopElement.class,
        SVGStringList.class, SVGStyleElement.class, SVGSVGElement.class, SVGSwitchElement.class,
        SVGSymbolElement.class, SVGTextContentElement.class, SVGTextElement.class,
        SVGTextPathElement.class, SVGTextPositioningElement.class, SVGTitleElement.class, SVGTransform.class,
        SVGTransformList.class, SVGTSpanElement.class, SVGUnitTypes.class, SVGUseElement.class, SVGViewElement.class,
        SVGZoomEvent.class, SyncManager.class, Text.class, TextDecoder.class,
        TextEncoder.class, TextEvent.class, TextMetrics.class, TextRange.class, TextTrack.class, TextTrackCue.class,
        TextTrackCueList.class, TextTrackList.class, TimeEvent.class, TimeRanges.class,
        Touch.class, TouchEvent.class, TouchList.class, TrackEvent.class, TransitionEvent.class, TreeWalker.class,
        UIEvent.class,
        URL.class,
        URLSearchParams.class, UserProximityEvent.class, ValidityState.class, VideoPlaybackQuality.class,
        VTTCue.class, WaveShaperNode.class, WeakMap.class, WeakSet.class, WebGL2RenderingContext.class,
        WEBGL_compressed_texture_s3tc.class, WEBGL_debug_renderer_info.class, WebGLActiveInfo.class, WebGLBuffer.class,
        WebGLContextEvent.class, WebGLFramebuffer.class, WebGLProgram.class,
        WebGLQuery.class,
        WebGLRenderbuffer.class,
        WebGLRenderingContext.class,
        WebGLSampler.class, WebGLShader.class, WebGLShaderPrecisionFormat.class, WebGLSync.class,
        WebGLTexture.class, WebGLTransformFeedback.class,
        WebGLUniformLocation.class, WebGLVertexArrayObject.class,
        WebKitAnimationEvent.class,
        WebKitCSSMatrix.class, webkitMediaStream.class, WebKitMutationObserver.class,
        webkitRTCPeerConnection.class, webkitSpeechGrammar.class,
        webkitSpeechGrammarList.class, webkitSpeechRecognition.class, webkitSpeechRecognitionError.class,
        webkitSpeechRecognitionEvent.class, WebKitTransitionEvent.class, webkitURL.class,
        WebSocket.class, WheelEvent.class, Window.class, Worker.class, XMLDocument.class,
        XMLHttpRequest.class, XMLHttpRequestEventTarget.class, XMLHttpRequestUpload.class, XMLSerializer.class,
        XPathEvaluator.class, XPathExpression.class,
        XPathNSResolver.class, XPathResult.class, XSLTProcessor.class,

        // we will use the Rhino stuff as soon as possible
        ArrayBuffer.class, ArrayBufferView.class, ArrayBufferViewBase.class,
        DataView.class,
        Float32Array.class, Float64Array.class,
        Int16Array.class, Int32Array.class, Int8Array.class,
        Uint16Array.class, Uint32Array.class, Uint8Array.class, Uint8ClampedArray.class
    };

    /** Cache of browser versions and their corresponding JavaScript configurations. */
    private static final Map<String, JavaScriptConfiguration> CONFIGURATION_MAP_ = new WeakHashMap<>();

    /**
     * Constructor is only called from {@link #getInstance(BrowserVersion)} which is synchronized.
     * @param browser the browser version to use
     */
    protected JavaScriptConfiguration(final BrowserVersion browser) {
        super(browser);
    }

    /**
     * Returns the instance that represents the configuration for the specified {@link BrowserVersion}.
     * This method is synchronized to allow multi-threaded access to the JavaScript configuration.
     * @param browserVersion the {@link BrowserVersion}
     * @return the instance for the specified {@link BrowserVersion}
     */
    public static synchronized JavaScriptConfiguration getInstance(final BrowserVersion browserVersion) {
        if (browserVersion == null) {
            throw new IllegalArgumentException("BrowserVersion must be provided");
        }
        JavaScriptConfiguration configuration = CONFIGURATION_MAP_.get(browserVersion.getNickname());

        if (configuration == null) {
            configuration = new JavaScriptConfiguration(browserVersion);
            CONFIGURATION_MAP_.put(browserVersion.getNickname(), configuration);
        }
        return configuration;
    }

    @Override
    protected Class<? extends SimpleScriptable>[] getClasses() {
        return CLASSES_;
    }
}
