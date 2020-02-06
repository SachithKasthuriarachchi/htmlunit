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
package com.android.gargoylesoftware.htmlunit.javascript.host.css;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * Contains information about the style attribute defined for different browser
 * as well as their default values.
 *
 * @version $Revision$
 * @author Marc Guillemot
 */
final class StyleAttributes {
    private static final Map<String, Definition> styles_ = new HashMap<String, Definition>();

    static {
        // force loading of Definition to fill the styles_ map
        Definition.values();
    }

    private StyleAttributes() {
        // nothing
    }

    /**
     * Gets the style attributes definition with the given name for the specified browser version.
     * @param browserVersion the browser version
     * @return <code>null</code> if no definition exists for this browser version
     */
    public static Definition getDefinition(final String propertyName, final BrowserVersion browserVersion) {
        if (browserVersion == null) {
            return null;
        }
        final Definition definition = styles_.get(propertyName);
        if (definition == null) {
            return null;
        }
        if (!definition.isAvailable(browserVersion)) {
            return null;
        }
        return definition;
    }

    /**
     * Gets the style attributes definitions for the specified browser version.
     * @param browserVersion the browser version
     * @return the list
     */
    public static List<Definition> getDefinitions(final BrowserVersion browserVersion) {
        final List<Definition> list = new ArrayList<Definition>();
        for (final Definition definition : Definition.values()) {
            if (definition.isAvailable(browserVersion)) {
                list.add(definition);
            }
        }

        return list;
    }

    /**
     * Holds information about a style attribute (CSS name, property name, browser availability,
     * default computed value.
     * TODO: move all (?) style attribute definitions here.
     */
    public static enum Definition {
        /** The style property -moz-animation. */
        MOZ_ANIMATION("MozAnimation", "-moz-animation", BrowserConfiguration.ff17up("")),

        /** The style property -moz-animation-delay. */
        MOZ_ANIMATION_DELAY("MozAnimationDelay", "-moz-animation-delay",
                BrowserConfiguration.ff17up("0s")),

        /** The style property -moz-animation-direction. */
        MOZ_ANIMATION_DIRECTION("MozAnimationDirection",
                "-moz-animation-direction", BrowserConfiguration.ff17up("normal")),

        /** The style property -moz-animation-duration. */
        MOZ_ANIMATION_DURATION("MozAnimationDuration",
                "-moz-animation-duration", BrowserConfiguration.ff17up("0s")),

        /** The style property -moz-animation-fill-mode. */
        MOZ_ANIMATION_FILL_MODE("MozAnimationFillMode",
                "moz-annimation-fill-mode", BrowserConfiguration.ff17up("none")),

        /** The style property -moz-animation-iteration-count. */
        MOZ_ANIMATION_ITERATION_COUNT("MozAnimationIterationCount",
                "-moz-animation-iteration-count", BrowserConfiguration.ff17up("1")),

        /** The style property -moz-animation-name. */
        MOZ_ANIMATION_NAME("MozAnimationName", "moz-annimation-name",
                BrowserConfiguration.ff17up("none")),

        /** The style property -moz-animation-play-state. */
        MOZ_ANIMATION_PLAY_STATE("MozAnimationPlayState",
                "moz-annimation-play-state", BrowserConfiguration.ff17up("running")),

        /** The style property -moz-animation-timing-function. */
        MOZ_ANIMATION_TIMING_FUNCTION("MozAnimationTimingFunction",
                "moz-annimation-timing-function",
                BrowserConfiguration.ff17up("cubic-bezier(0.25, 0.1, 0.25, 1)")),

        /** The style property -moz-appearance. */
        MOZ_APPEARANCE("MozAppearance", "-moz-appearance", BrowserConfiguration.ff("none")),

        /** The style property -moz-backface-visibility. */
        MOZ_BACKFACE_VISIBILITY("MozBackfaceVisibility",
                "-moz-backface-visibility", BrowserConfiguration.ff17up("visible")),

        /** The style property -moz-background-clip. */
        MOZ_BACKGROUND_CLIP("MozBackgroundClip", "-moz-background-clip",
                BrowserConfiguration.ffBelow17("border")),

        /** The style property -moz-background-inline-policy. */
        MOZ_BACKGROUND_INLINE_POLICY("MozBackgroundInlinePolicy",
                "-moz-background-inline-policy", BrowserConfiguration.ff("continuous")),

        /** The style property -moz-background-origin. */
        MOZ_BACKGROUND_ORIGIN("MozBackgroundOrigin", "-moz-background-origin",
                BrowserConfiguration.ffBelow17("padding")),

        /** The style property -moz-background-size. */
        MOZ_BACKGROUND_SIZE("MozBackgroundSize", "-moz-background-size",
                BrowserConfiguration.ffBelow17("auto auto")),

        /** The style property -moz-binding. */
        MOZ_BINDING("MozBinding", "-moz-binding", BrowserConfiguration.ff("none")),

        /** The style property -moz-border-bottom-colors. */
        MOZ_BORDER_BOTTOM_COLORS("MozBorderBottomColors",
                "-moz-border-bottom-colors", BrowserConfiguration.ff("none")),

        /** The style property -moz-border-end. */
        MOZ_BORDER_END("MozBorderEnd", "-moz-border-end", BrowserConfiguration.ff("")),

        /** The style property -moz-border-end-color. */
        MOZ_BORDER_END_COLOR("MozBorderEndColor", "-moz-border-end-color",
                BrowserConfiguration.ff("")),

        /** The style property -moz-border-end-style. */
        MOZ_BORDER_END_STYLE("MozBorderEndStyle", "-moz-border-end-style",
                BrowserConfiguration.ff("")),

        /** The style property -moz-border-end-width. */
        MOZ_BORDER_END_WIDTH("MozBorderEndWidth", "-moz-border-end-width",
                BrowserConfiguration.ff("")),

        /** The style property -moz-border-image. */
        MOZ_BORDER_IMAGE("MozBorderImage", "-moz-border-image",
                BrowserConfiguration.ffBelow17("none"), BrowserConfiguration.ff("")),

        /** The style property -moz-border-left-colors. */
        MOZ_BORDER_LEFT_COLORS("MozBorderLeftColors",
                "-moz-border-left-colors", BrowserConfiguration.ff("none")),

        /** The style property -moz-border-radius. */
        MOZ_BORDER_RADIUS("MozBorderRadius", "-moz-border-radius",
                BrowserConfiguration.ffBelow17("")),

        /** The style property -moz-border-radius-bottomleft. */
        MOZ_BORDER_RADIUS_BOTTOMLEFT("MozBorderRadiusBottomleft",
                "-moz-border-radius-bottomleft", BrowserConfiguration.ffBelow17("0px")),

        /** The style property -moz-border-radius-bottomright. */
        MOZ_BORDER_RADIUS_BOTTOMRIGHT("MozBorderRadiusBottomright",
                "-moz-border-radius-bottomright", BrowserConfiguration.ffBelow17("0px")),

        /** The style property -moz-border-radius-topleft. */
        MOZ_BORDER_RADIUS_TOPLEFT("MozBorderRadiusTopleft",
                "-moz-border-radius-topleft", BrowserConfiguration.ffBelow17("0px")),

        /** The style property -moz-border-radius-topright. */
        MOZ_BORDER_RADIUS_TOPRIGHT("MozBorderRadiusTopright",
                "-moz-border-radius-topright", BrowserConfiguration.ffBelow17("0px")),

        /** The style property -moz-border-right-colors. */
        MOZ_BORDER_RIGHT_COLORS("MozBorderRightColors",
                "-moz-border-right-colors", BrowserConfiguration.ff("none")),

        /** The style property -moz-border-start. */
        MOZ_BORDER_START("MozBorderStart", "-moz-border-start", BrowserConfiguration.ff("")),

        /** The style property -moz-border-start-color. */
        MOZ_BORDER_START_COLOR("MozBorderStartColor",
                "-moz-border-start-color", BrowserConfiguration.ff("")),

        /** The style property -moz-border-start-style. */
        MOZ_BORDER_START_STYLE("MozBorderStartStyle",
                "-moz-border-start-style", BrowserConfiguration.ff("")),

        /** The style property -moz-border-start-width. */
        MOZ_BORDER_START_WIDTH("MozBorderStartWidth",
                "-moz-border-start-width", BrowserConfiguration.ff("")),

        /** The style property -moz-border-top-colors. */
        MOZ_BORDER_TOP_COLORS("MozBorderTopColors", "-moz-border-top-colors",
                BrowserConfiguration.ff("none")),

        /** The style property -moz-box-align. */
        MOZ_BOX_ALIGN("MozBoxAlign", "-moz-box-align", BrowserConfiguration.ff("stretch")),

        /** The style property -moz-box-direction. */
        MOZ_BOX_DIRECTION("MozBoxDirection", "-moz-box-direction", BrowserConfiguration.ff("normal")),

        /** The style property -moz-box-flex. */
        MOZ_BOX_FLEX("MozBoxFlex", "-moz-box-flex", BrowserConfiguration.ff("0")),

        /** The style property -moz-box-ordinal-group. */
        MOZ_BOX_ORDINAL_GROUP("MozBoxOrdinalGroup", "-moz-box-ordinal-group",
                BrowserConfiguration.ff("1")),

        /** The style property -moz-box-orient. */
        MOZ_BOX_ORIENT("MozBoxOrient", "-moz-box-orient", BrowserConfiguration.ff("horizontal")),

        /** The style property -moz-box-pack. */
        MOZ_BOX_PACK("MozBoxPack", "-moz-box-pack", BrowserConfiguration.ff("start")),

        /** The style property -moz-box-shadow. */
        MOZ_BOX_SHADOW("MozBoxShadow", "-moz-box-shadow", BrowserConfiguration.ffBelow17("none")),

        /** The style property -moz-box-sizing. */
        MOZ_BOX_SIZING("MozBoxSizing", "-moz-box-sizing", BrowserConfiguration.ff("content-box")),

        /** The style property -moz-column-count. */
        MOZ_COLUMN_COUNT("MozColumnCount", "-moz-column-count", BrowserConfiguration.ff("auto")),

        /** The style property -moz-column-gap. */
        MOZ_COLUMN_GAP("MozColumnGap", "-moz-column-gap", BrowserConfiguration.ff("16px")),

        /** The style property -moz-column-rule. */
        MOZ_COLUMN_RULE("MozColumnRule", "-moz-column-rule", BrowserConfiguration.ff("")),

        /** The style property -moz-column-rule-color. */
        MOZ_COLUMN_RULE_COLOR("MozColumnRuleColor", "-moz-column-rule-color",
                BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property -moz-column-rule-style. */
        MOZ_COLUMN_RULE_STYLE("MozColumnRuleStyle", "-moz-column-rule-style",
                BrowserConfiguration.ff("none")),

        /** The style property -moz-column-rule-width. */
        MOZ_COLUMN_RULE_WIDTH("MozColumnRuleWidth", "-moz-column-rule-width",
                BrowserConfiguration.ff("0px")),

        /** The style property -moz-column-width. */
        MOZ_COLUMN_WIDTH("MozColumnWidth", "-moz-column-width", BrowserConfiguration.ff("auto")),

        /** The style property -moz-columns. */
        MOZ_COLUMNS("MozColumns", "-moz-columns", BrowserConfiguration.ff17up("")),

        /** The style property -moz-float-edge. */
        MOZ_FLOAT_EDGE("MozFloatEdge", "-moz-float-edge", BrowserConfiguration.ff("content-box")),

        /** The style property -moz-font-feature-settings. */
        MOZ_FONT_FEATURE_SETTINGS("MozFontFeatureSettings",
                "-moz-font-feature-settings", BrowserConfiguration.ff17up("normal")),

        /** The style property -moz-font-language-override. */
        MOZ_FONT_LANGUAGE_OVERRIDE("MozFontLanguageOverride",
                "-moz-font-language-override", BrowserConfiguration.ff17up("normal")),

        /** The style property -moz-force-broken-image-icon. */
        MOZ_FORCE_BROKEN_IMAGE_ICON("MozForceBrokenImageIcon",
                "-moz-force-broken-image-icon", BrowserConfiguration.ff("0")),

        /** The style property -moz-hyphens. */
        MOZ_HYPHENS("MozHyphens", "-moz-hyphens", BrowserConfiguration.ff17up("manual")),

        /** The style property -moz-image-region. */
        MOZ_IMAGE_REGION("MozImageRegion", "-moz-image-region", BrowserConfiguration.ff("auto")),

        /** The style property -moz-margin-end. */
        MOZ_MARGIN_END("MozMarginEnd", "-moz-margin-end", BrowserConfiguration.ff("")),

        /** The style property -moz-margin-start. */
        MOZ_MARGIN_START("MozMarginStart", "-moz-margin-start", BrowserConfiguration.ff("")),

        /** The style property -moz-opacity. */
        MOZ_OPACITY("MozOpacity", "-moz-opacity", BrowserConfiguration.ffBelow17("1")),

        /** The style property -moz-orient. */
        MOZ_ORIENT("MozOrient", "-moz-orient", BrowserConfiguration.ff17up("horizontal")),

        /** The style property -moz-outline. */
        MOZ_OUTLINE("MozOutline", "-moz-outline", BrowserConfiguration.ffBelow17("")),

        /** The style property -moz-outline-color. */
        MOZ_OUTLINE_COLOR("MozOutlineColor", "-moz-outline-color",
                BrowserConfiguration.ffBelow17("rgb(0, 0, 0)")),

        /** The style property -moz-outline-offset. */
        MOZ_OUTLINE_OFFSET("MozOutlineOffset", "-moz-outline-offset",
                BrowserConfiguration.ffBelow17("0px")),

        /** The style property -mz-outline-radius. */
        MOZ_OUTLINE_RADIUS("MozOutlineRadius", "-mz-outline-radius", BrowserConfiguration.ff("")),

        /** The style property -moz-outline-radius-bottomleft. */
        MOZ_OUTLINE_RADIUS_BOTTOMLEFT("MozOutlineRadiusBottomleft",
                "-moz-outline-radius-bottomleft", BrowserConfiguration.ff("0px")),

        /** The style property -moz-outline-radius-bottomright. */
        MOZ_OUTLINE_RADIUS_BOTTOMRIGHT("MozOutlineRadiusBottomright",
                "-moz-outline-radius-bottomright", BrowserConfiguration.ff("0px")),

        /** The style property -moz-outline-radius-topleft. */
        MOZ_OUTLINE_RADIUS_TOPLEFT("MozOutlineRadiusTopleft",
                "-moz-outline-radius-topleft", BrowserConfiguration.ff("0px")),

        /** The style property -moz-outline-radius-topright. */
        MOZ_OUTLINE_RADIUS_TOPRIGHT("MozOutlineRadiusTopright",
                "-moz-outline-radius-topright", BrowserConfiguration.ff("0px")),

        /** The style property -moz-outline-style. */
        MOZ_OUTLINE_STYLE("MozOutlineStyle", "-moz-outline-style",
                BrowserConfiguration.ffBelow17("none")),

        /** The style property -moz-outline-width. */
        MOZ_OUTLINE_WIDTH("MozOutlineWidth", "-moz-outline-width",
                BrowserConfiguration.ffBelow17("0px")),

        /** The style property -moz-padding-end. */
        MOZ_PADDING_END("MozPaddingEnd", "-moz-padding-end", BrowserConfiguration.ff("")),

        /** The style property -moz-padding-start. */
        MOZ_PADDING_START("MozPaddingStart", "-moz-padding-start", BrowserConfiguration.ff("")),

        /** The style property -moz-perspective. */
        MOZ_PERSPECTIVE("MozPerspective", "-moz-perspective", BrowserConfiguration.ff17up("none")),

        /** The style property -moz-perspective-origin. */
        MOZ_PERSPECTIVE_ORIGIN("MozPerspectiveOrigin",
                "-moz-perspective-origin", BrowserConfiguration.ff17up("628px 170px")),

        /** The style property -moz-stack-sizing. */
        MOZ_STACK_SIZING("MozStackSizing", "-moz-stack-sizing",
                BrowserConfiguration.ff("stretch-to-fit")),

        /** The style property -moz-tab-size. */
        MOZ_TAB_SIZE("MozTabSize", "-moz-tab-size", BrowserConfiguration.ff17up("8")),

        /** The style property -moz-text-align-last. */
        MOZ_TEXT_ALIGN_LAST("MozTextAlignLast", "-moz-text-align-last",
                BrowserConfiguration.ff17up("auto")),

        /** The style property -moz-text-blink. */
        MOZ_TEXT_BLINK("MozTextBlink", "-moz-text-blink", BrowserConfiguration.ff17up("none")),

        /** The style property -moz-text-decoration-color. */
        MOZ_TEXT_DECORATION_COLOR("MozTextDecorationColor",
                "-moz-text-decoration-color", BrowserConfiguration.ff17up("rgb(0, 0, 0)")),

        /** The style property -moz-text-decoration-line. */
        MOZ_TEXT_DECORATION_LINE("MozTextDecorationLine",
                "-moz-text-decoration-line", BrowserConfiguration.ff17up("none")),

        /** The style property -moz-text-decoration-style. */
        MOZ_TEXT_DECORATION_STYLE("MozTextDecorationStyle",
                "-moz-text-decoration-style", BrowserConfiguration.ff17up("solid")),

        /** The style property -moz-text-size-adjust. */
        MOZ_TEXT_SIZE_ADJUST("MozTextSizeAdjust", "-moz-text-size-adjust",
                BrowserConfiguration.ff17up("auto")),

        /** The style property -moz-transform. */
        MOZ_TRANSFORM("MozTransform", "-moz-transform", BrowserConfiguration.ff("none")),

        /** The style property -moz-transform-origin. */
        MOZ_TRANSFORM_ORIGIN("MozTransformOrigin", "-moz-transform-origin",
                BrowserConfiguration.ffBelow17("50% 50%"), BrowserConfiguration.ff("628px 170px")),

        /** The style property -moz-transform-style. */
        MOZ_TRANSFORM_STYLE("MozTransformStyle", "-moz-transform-style",
                BrowserConfiguration.ff17up("flat")),

        /** The style property -moz-transition. */
        MOZ_TRANSITION("MozTransition", "-moz-transition", BrowserConfiguration.ff17up("")),

        /** The style property -moz-transition-delay. */
        MOZ_TRANSITION_DELAY("MozTransitionDelay", "-moz-transition-delay",
                BrowserConfiguration.ff17up("0s")),

        /** The style property -moz-transition-duration. */
        MOZ_TRANSITION_DURATION("MozTransitionDuration",
                "-moz-transition-duration", BrowserConfiguration.ff17up("0s")),

        /** The style property -moz-transition-property. */
        MOZ_TRANSITION_PROPERTY("MozTransitionProperty",
                "-moz-transition-property", BrowserConfiguration.ff17up("all")),

        /** The style property -moz-transition-timing-function. */
        MOZ_TRANSITION_TIMING_FUNCTION("MozTransitionTimingFunction",
                "-moz-transition-timing-function",
                BrowserConfiguration.ff17up("cubic-bezier(0.25, 0.1, 0.25, 1)")),

        /** The style property -moz-user-focus. */
        MOZ_USER_FOCUS("MozUserFocus", "-moz-user-focus", BrowserConfiguration.ff("none")),

        /** The style property -moz-user-input. */
        MOZ_USER_INPUT("MozUserInput", "-moz-user-input", BrowserConfiguration.ff("auto")),

        /** The style property -moz-user-modify. */
        MOZ_USER_MODIFY("MozUserModify", "-moz-user-modify", BrowserConfiguration.ff("read-only")),

        /** The style property -moz-user-select. */
        MOZ_USER_SELECT("MozUserSelect", "-moz-user-select", BrowserConfiguration.ff("auto")),

        /** The style property -moz-window-shadow. */
        MOZ_WINDOW_SHADOW("MozWindowShadow", "-moz-window-shadow",
                BrowserConfiguration.ff("default")),

        /** The style property animation. */
        ANIMATION("animation", "animation", BrowserConfiguration.ff17up("")),

        /** The style property animation-delay. */
        ANIMATION_DELAY("animationDelay", "animation-delay", BrowserConfiguration.ff17up("0s")),

        /** The style property animation-direction. */
        ANIMATION_DIRECTION("animationDirection", "animation-direction",
                BrowserConfiguration.ff17up("normal")),

        /** The style property animation-duration. */
        ANIMATION_DURATION("animationDuration", "animation-duration",
                BrowserConfiguration.ff17up("0s")),

        /** The style property animation-fill-mode. */
        ANIMATION_FILL_MODE("animationFillMode", "animation-fill-mode",
                BrowserConfiguration.ff17up("none")),

        /** The style property animation-iteration-count. */
        ANIMATION_ITERATION_COUNT("animationIterationCount",
                "animation-iteration-count", BrowserConfiguration.ff17up("1")),

        /** The style property animation-name. */
        ANIMATION_NAME("animationName", "animation-name", BrowserConfiguration.ff17up("none")),

        /** The style property animation-play-state. */
        ANIMATION_PLAY_STATE("animationPlayState", "animation-play-state",
                BrowserConfiguration.ff17up("running")),

        /** The style property animation-timing-function. */
        ANIMATION_TIMING_FUNCTION("animationTimingFunction",
                "animation-timing-function",
                BrowserConfiguration.ff17up("cubic-bezier(0.25, 0.1, 0.25, 1)")),

        /** The style property azimuth. */
        AZIMUTH("azimuth", "azimuth", BrowserConfiguration.ffBelow17("")),

        /** The style property backface-visibility. */
        BACKFACE_VISIBILITY("backfaceVisibility", "backface-visibility",
                BrowserConfiguration.ff17up("visible")),

        /** The style property background-clip. */
        BACKGROUND_CLIP("backgroundClip", "background-clip",
                BrowserConfiguration.ff17up("border-box")),

        /** The style property background-origin. */
        BACKGROUND_ORIGIN("backgroundOrigin", "background-origin",
                BrowserConfiguration.ff17up("padding-box")),

        /** The style property background-size. */
        BACKGROUND_SIZE("backgroundSize", "background-size",
                BrowserConfiguration.ff17up("auto auto")),

        /** The style property border-bottom-left-radius. */
        BORDER_BOTTOM_LEFT_RADIUS("borderBottomLeftRadius",
                "border-bottom-left-radius", BrowserConfiguration.ff17up("0px")),

        /** The style property border-bottom-right-radius. */
        BORDER_BOTTOM_RIGHT_RADIUS("borderBottomRightRadius",
                "border-bottom-right-radius", BrowserConfiguration.ff17up("0px")),

        /** The style property border-image. */
        BORDER_IMAGE("borderImage", "border-image", BrowserConfiguration.ff17up("")),

        /** The style property border-image-outset. */
        BORDER_IMAGE_OUTSET("borderImageOutset", "border-image-outset",
                BrowserConfiguration.ff17up("0 0 0 0")),

        /** The style property border-image-repeat. */
        BORDER_IMAGE_REPEAT("borderImageRepeat", "border-image-repeat",
                BrowserConfiguration.ff17up("stretch stretch")),

        /** The style property border-image-slice. */
        BORDER_IMAGE_SLICE("borderImageSlice", "border-image-slice",
                BrowserConfiguration.ff17up("100% 100% 100% 100%")),

        /** The style property border-image-source. */
        BORDER_IMAGE_SOURCE("borderImageSource", "border-image-source",
                BrowserConfiguration.ff17up("none")),

        /** The style property border-image-width. */
        BORDER_IMAGE_WIDTH("borderImageWidth", "border-image-width",
                BrowserConfiguration.ff17up("1 1 1 1")),

        /** The style property border-radius. */
        BORDER_RADIUS("borderRadius", "border-radius", BrowserConfiguration.ff17up("")),

        /** The style property border-top-left-radius. */
        BORDER_TOP_LEFT_RADIUS("borderTopLeftRadius", "border-top-left-radius",
                BrowserConfiguration.ff17up("0px")),

        /** The style property border-top-right-radius. */
        BORDER_TOP_RIGHT_RADIUS("borderTopRightRadius",
                "border-top-right-radius", BrowserConfiguration.ff17up("0px")),

        /** The style property box-shadow. */
        BOX_SHADOW("boxShadow", "box-shadow", BrowserConfiguration.ff17up("none")),

        /** The style property clip-path. */
        CLIP_PATH("clipPath", "clip-path", BrowserConfiguration.ff17up("none")),

        /** The style property clip-rule. */
        CLIP_RULE("clipRule", "clip-rule", BrowserConfiguration.ff17up("nonzero")),

        /** The style property color-interpolation. */
        COLOR_INTERPOLATION("colorInterpolation", "color-interpolation",
                BrowserConfiguration.ff17up("srgb")),

        /** The style property color-interpolation-filters. */
        COLOR_INTERPOLATION_FILTERS("colorInterpolationFilters",
                "color-interpolation-filters", BrowserConfiguration.ff17up("linearrgb")),

        /** The style property cue. */
        CUE("cue", "cue", BrowserConfiguration.ffBelow17("")),

        /** The style property cue-after. */
        CUE_AFTER("cueAfter", "cue-after", BrowserConfiguration.ffBelow17("")),

        /** The style property cue-before. */
        CUE_BEFORE("cueBefore", "cue-before", BrowserConfiguration.ffBelow17("")),

        /** The style property dominant-baseline. */
        DOMINANT_BASELINE("dominantBaseline", "dominant-baseline",
                BrowserConfiguration.ff17up("auto")),

        /** The style property elevation. */
        ELEVATION("elevation", "elevation", BrowserConfiguration.ffBelow17("")),

        /** The style property fill. */
        FILL("fill", "fill", BrowserConfiguration.ff17up("rgb(0, 0, 0)")),

        /** The style property fill-opacity. */
        FILL_OPACITY("fillOpacity", "fill-opacity", BrowserConfiguration.ff17up("1")),

        /** The style property fill-rule. */
        FILL_RULE("fillRule", "fill-rule", BrowserConfiguration.ff17up("nonzero")),

        /** The style property filter. */
        FILTER("filter", "filter", BrowserConfiguration.ff17up("none"), BrowserConfiguration.ie("")),

        /** The style property flood-color. */
        FLOOD_COLOR("floodColor", "flood-color", BrowserConfiguration.ff17up("rgb(0, 0, 0)")),

        /** The style property flood-opacity. */
        FLOOD_OPACITY("floodOpacity", "flood-opacity", BrowserConfiguration.ff17up("1")),

        /** The style property image-rendering. */
        IMAGE_RENDERING("imageRendering", "image-rendering", BrowserConfiguration.ff17up("auto")),

        /** The style property lighting-color. */
        LIGHTING_COLOR("lightingColor", "lighting-color",
                BrowserConfiguration.ff17up("rgb(255, 255, 255)")),

        /** The style property line-height. */
        LINE_HEIGHT("lineHeight", "line-height", BrowserConfiguration.ff17up("19px"),
                BrowserConfiguration.ffBelow17("20px"), BrowserConfiguration.ie("20px")),

        /** The style property marker. */
        MARKER("marker", "marker", BrowserConfiguration.ff17up("")),

        /** The style property marker-end. */
        MARKER_END("markerEnd", "marker-end", BrowserConfiguration.ff17up("none")),

        /** The style property marker-mid. */
        MARKER_MID("markerMid", "marker-mid", BrowserConfiguration.ff17up("none")),

        /** The style property marker-start. */
        MARKER_START("markerStart", "marker-start", BrowserConfiguration.ff17up("none")),

        /** The style property mask. */
        MASK("mask", "mask", BrowserConfiguration.ff17up("none")),

        /** The style property pause. */
        PAUSE("pause", "pause", BrowserConfiguration.ffBelow17("")),

        /** The style property pause-after. */
        PAUSE_AFTER("pauseAfter", "pause-after", BrowserConfiguration.ffBelow17("")),

        /** The style property pause-before. */
        PAUSE_BEFORE("pauseBefore", "pause-before", BrowserConfiguration.ffBelow17("")),

        /** The style property pitch. */
        PITCH("pitch", "pitch", BrowserConfiguration.ffBelow17("")),

        /** The style property pitch-range. */
        PITCH_RANGE("pitchRange", "pitch-range", BrowserConfiguration.ffBelow17("")),

        /** The style property perspective. */
        PERSPECTIVE("perspective", "perspective", BrowserConfiguration.ff17up("none")),

        /** The style property perspective-origin. */
        PERSPECTIVE_ORIGIN("perspectiveOrigin", "perspective-origin",
                BrowserConfiguration.ff17up("628px 170px")),

        /** The style property quotes. */
        QUOTES("quotes", "quotes", BrowserConfiguration.ffBelow17(""),
                BrowserConfiguration.ff17up("\"“\" \"”\" \"‘\" \"’\""), BrowserConfiguration.ie8up("")),

        /** The style property resize. */
        RESIZE("resize", "resize", BrowserConfiguration.ff17up("none")),

        /** The style property richness. */
        RICHNESS("richness", "richness", BrowserConfiguration.ffBelow17("")),

        /** The style property shape-rendering. */
        SHAPE_RENDERING("shapeRendering", "shape-rendering", BrowserConfiguration.ff17up("auto")),

        /** The style property speak. */
        SPEAK("speak", "speak", BrowserConfiguration.ffBelow17("")),

        /** The style property speak. */
        SPEAK_HEADER("speakHeader", "speak-header", BrowserConfiguration.ffBelow17("")),

        /** The style property speak-numeral. */
        SPEAK_NUMERAL("speakNumeral", "speak-numeral", BrowserConfiguration.ffBelow17("")),

        /** The style property speak-punktuation. */
        SPEAK_PUNCTUATION("speakPunctuation", "speak-punctuation",
                BrowserConfiguration.ffBelow17("")),

        /** The style property speech-rate. */
        SPEECH_RATE("speechRate", "speech-rate", BrowserConfiguration.ffBelow17("")),

        /** The style property stop-color. */
        STOP_COLOR("stopColor", "stop-color", BrowserConfiguration.ff17up("rgb(0, 0, 0)")),

        /** The style property stop-opacity. */
        STOP_OPACITY("stopOpacity", "stop-opacity", BrowserConfiguration.ff17up("1")),

        /** The style property stroke. */
        STROKE("stroke", "stroke", BrowserConfiguration.ff17up("none")),

        /** The style property stroke-dasharray. */
        STROKE_DASHARRAY("strokeDasharray", "stroke-dasharray", BrowserConfiguration.ff17up("none")),

        /** The style property stroke-dashoffset. */
        STROKE_DASHOFFSET("strokeDashoffset", "stroke-dashoffset",
                BrowserConfiguration.ff17up("0px")),

        /** The style property stroke-linecap. */
        STROKE_LINECAP("strokeLinecap", "stroke-linecap", BrowserConfiguration.ff17up("butt")),

        /** The style property stroke-linejoin. */
        STROKE_LINEJOIN("strokeLinejoin", "stroke-linejoin", BrowserConfiguration.ff17up("miter")),

        /** The style property stroke-miterlimit. */
        STROKE_MITERLIMIT("strokeMiterlimit", "stroke-miterlimit", BrowserConfiguration.ff17up("4")),

        /** The style property stroke-opacity. */
        STROKE_OPACITY("strokeOpacity", "stroke-opacity", BrowserConfiguration.ff17up("1")),

        /** The style property stroke-width. */
        STROKE_WIDTH("strokeWidth", "stroke-width", BrowserConfiguration.ff17up("1px")),

        /** The style property stress. */
        STRESS("stress", "stress", BrowserConfiguration.ffBelow17("")),

        /** The style property text-anchor. */
        TEXT_ANCHOR("textAnchor", "text-anchor", BrowserConfiguration.ff17up("start")),

        /** The style property text-overflow. */
        TEXT_OVERFLOW("textOverflow", "text-overflow", BrowserConfiguration.ff17up("clip")),

        /** The style property text-rendering. */
        TEXT_RENDERING("textRendering", "text-rendering", BrowserConfiguration.ff17up("auto")),

        /** The style property transform. */
        TRANSFORM("transform", "transform", BrowserConfiguration.ff17up("none")),

        /** The style property transform-origin. */
        TRANSFORM_ORIGIN("transformOrigin", "transform-origin",
                BrowserConfiguration.ff17up("628px 170px")),

        /** The style property transform-style. */
        TRANSFORM_STYLE("transformStyle", "transform-style", BrowserConfiguration.ff17up("flat")),

        /** The style property transition. */
        TRANSITION("transition", "transition", BrowserConfiguration.ff17up("")),

        /** The style property transition-delay. */
        TRANSITION_DELAY("transitionDelay", "transition-delay", BrowserConfiguration.ff17up("0s")),

        /** The style property transition-duration. */
        TRANSITION_DURATION("transitionDuration", "transition-duration",
                BrowserConfiguration.ff17up("0s")),

        /** The style property transition-property. */
        TRANSITION_PROPERTY("transitionProperty", "transition-property",
                BrowserConfiguration.ff17up("all")),

        /** The style property transition-timing-function. */
        TRANSITION_TIMING_FUNCTION("transitionTimingFunction",
                "transition-timing-function",
                BrowserConfiguration.ff17up("cubic-bezier(0.25, 0.1, 0.25, 1)")),

        /** The style property unicode-bidi. */
        UNICODE_BIDI("unicodeBidi", "unicode-bidi", BrowserConfiguration.ffBelow17("embed"),
                BrowserConfiguration.ff17up("-moz-isolate"), BrowserConfiguration.ie("embed")),

        /** The style property vector-effect. */
        VECTOR_EFFECT("vectorEffect", "vector-effect", BrowserConfiguration.ff17up("none")),

        /** The style property "voice-family". */
        VOICE_FAMILY("voiceFamily", "voice-family", BrowserConfiguration.ffBelow17("")),

        /** The style property volume. */
        VOLUME("volume", "volume", BrowserConfiguration.ffBelow17("")),

        /** The style property word-break. */
        WORD_BREAK("wordBreak", "word-break", BrowserConfiguration.ff17up("normal"), BrowserConfiguration.ie(""));

        private final String propertyName_;
        private final String attributeName_;
        private final BrowserConfiguration[] browserConfigurations_;

        private Definition(final String propertyName,
                final String styleAttributeName,
                final BrowserConfiguration... browserConfigurations) {
            attributeName_ = styleAttributeName;
            propertyName_ = propertyName;
            browserConfigurations_ = browserConfigurations;
            styles_.put(propertyName_, this);
        }

        private boolean isAvailable(final BrowserVersion browserVersion) {
            return BrowserConfiguration.isDefined(browserVersion,
                    browserConfigurations_);
        }

        /**
         * Gets the name of the JavaScript property for this attribute.
         * @return the name of the JavaScript property
         */
        public String getPropertyName() {
            return propertyName_;
        }

        /**
         * Gets the name of the style attribute.
         * @return the name of the style attribute
         */
        public String getAttributeName() {
            return attributeName_;
        }

        public String getDefaultComputedValue(
                final BrowserVersion browserVersion) {
            return BrowserConfiguration.getMatchingConfiguration(
                    browserVersion, browserConfigurations_).getDefaultValue();
        }
    }
}
