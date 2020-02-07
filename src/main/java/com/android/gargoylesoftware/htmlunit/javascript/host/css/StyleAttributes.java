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
package com.android.gargoylesoftware.htmlunit.javascript.host.css;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * Contains information about the style attribute defined for different browser as well as their default values.
 *
 * @author Marc Guillemot
 * @author Frank Danek
 * @author Ahmed Ashour
 */
public final class StyleAttributes {
    private static final Map<String, Definition> styles_ = new HashMap<>();

    static {
        for (final Definition definition : Definition.values()) {
            styles_.put(definition.getPropertyName(), definition);
        }
    }

    private StyleAttributes() {
        // nothing
    }

    /**
     * Gets the style attributes definition with the given name for the specified browser version.
     * @param propertyName the name of the property
     * @param browserVersion the browser version
     * @return {@code null} if no definition exists for this browser version
     */
    public static Definition getDefinition(final String propertyName, final BrowserVersion browserVersion) {
        if (browserVersion == null) {
            return null;
        }

        final Definition definition = styles_.get(propertyName);
        if (definition == null) {
            return null;
        }
        if (!definition.isAvailable(browserVersion, false)) {
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
        final List<Definition> list = new ArrayList<>();
        for (final Definition definition : Definition.values()) {
            if (definition.isAvailable(browserVersion, true)) {
                list.add(definition);
            }
        }

        return list;
    }

    /**
     * Holds information about a style attribute (CSS name, property name, browser availability, default computed value.
     * TODO: move all (?) style attribute definitions here.
     */
    public enum Definition {
        /** The style property {@code accelerator}. */
        ACCELERATOR("accelerator", "accelerator", BrowserConfiguration.ie("undefined")),

        /** The style property {@code alignContent}. */
        ALIGN_CONTENT("alignContent", "align-content", BrowserConfiguration.ie("stretch"),
                BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code align-content}. */
        ALIGN_CONTENT_("align-content", "align-content", BrowserConfiguration.ff("normal")),

        /** The style property {@code alignItems}. */
        ALIGN_ITEMS("alignItems", "align-items", BrowserConfiguration.ff("normal"),
                BrowserConfiguration.ie("stretch"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code align-items}. */
        ALIGN_ITEMS_("align-items", "align-items", BrowserConfiguration.ff("normal")),

        /** The style property {@code alignSelf}. */
        ALIGN_SELF("alignSelf", "align-self", BrowserConfiguration.ff("auto"),
                BrowserConfiguration.ie("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code align-self}. */
        ALIGN_SELF_("align-self", "align-self", BrowserConfiguration.ff("auto")),

        /** The style property {@code alignmentBaseline}. */
        ALIGNMENT_BASELINE("alignmentBaseline", "alignment-baseline", BrowserConfiguration.ie("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code all}. */
        ALL("all", "all", BrowserConfiguration.ff(""), BrowserConfiguration.chrome("")),

        /** The style property {@code animation}. */
        ANIMATION("animation", "animation", BrowserConfiguration.ff(""), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("none 0s ease 0s 1 normal none running")),

        /** The style property {@code animationDelay}. */
        ANIMATION_DELAY("animationDelay", "animation-delay", BrowserConfiguration.ff("0s"), BrowserConfiguration.ie("0s"), BrowserConfiguration.chrome("0s")),

        /** The style property {@code animation-delay}. */
        ANIMATION_DELAY_("animation-delay", "animation-delay", BrowserConfiguration.ff("0s")),

        /** The style property {@code animationDirection}. */
        ANIMATION_DIRECTION("animationDirection", "animation-direction",
                BrowserConfiguration.ff("normal"), BrowserConfiguration.ie("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code animation-direction}. */
        ANIMATION_DIRECTION_("animation-direction", "animation-direction", BrowserConfiguration.ff("normal")),

        /** The style property {@code animationDuration}. */
        ANIMATION_DURATION("animationDuration", "animation-duration", BrowserConfiguration.ff("0s"), BrowserConfiguration.ie("0s"), BrowserConfiguration.chrome("0s")),

        /** The style property {@code animation-duration}. */
        ANIMATION_DURATION_("animation-duration", "animation-duration", BrowserConfiguration.ff("0s")),

        /** The style property {@code animationFillMode}. */
        ANIMATION_FILL_MODE("animationFillMode", "animation-fill-mode", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code animation-fill-mode}. */
        ANIMATION_FILL_MODE_("animation-fill-mode", "animation-fill-mode", BrowserConfiguration.ff("none")),

        /** The style property {@code animationIterationCount}. */
        ANIMATION_ITERATION_COUNT("animationIterationCount", "animation-iteration-count",
                BrowserConfiguration.ff("1"), BrowserConfiguration.ie("1"), BrowserConfiguration.chrome("1")),

        /** The style property {@code animation-iteration-count}. */
        ANIMATION_ITERATION_COUNT_("animation-iteration-count", "animation-iteration-count", BrowserConfiguration.ff("1")),

        /** The style property {@code animationName}. */
        ANIMATION_NAME("animationName", "animation-name", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code animation-name}. */
        ANIMATION_NAME_("animation-name", "animation-name", BrowserConfiguration.ff("none")),

        /** The style property {@code animationPlayState}. */
        ANIMATION_PLAY_STATE("animationPlayState", "animation-play-state",
                BrowserConfiguration.ff("running"), BrowserConfiguration.ie("running"), BrowserConfiguration.chrome("running")),

        /** The style property {@code animation-play-state}. */
        ANIMATION_PLAY_STATE_("animation-play-state", "animation-play-state", BrowserConfiguration.ff("running")),

        /** The style property {@code animationTimingFunction}. */
        ANIMATION_TIMING_FUNCTION("animationTimingFunction",
                "animation-timing-function",
                BrowserConfiguration.ff("ease"),
                BrowserConfiguration.ie("cubic-bezier(0.25, 0.1, 0.25, 1)"), BrowserConfiguration.chrome("ease")),

        /** The style property {@code animation-timing-function}. */
        ANIMATION_TIMING_FUNCTION_("animation-timing-function", "animation-timing-function",
                BrowserConfiguration.ff("ease")),

        /** The style property {@code azimuth}. */
        AZIMUTH("azimuth", "azimuth"),

        /** The style property {@code backfaceVisibility}. */
        BACKFACE_VISIBILITY("backfaceVisibility", "backface-visibility",
                BrowserConfiguration.ff("visible"), BrowserConfiguration.ie("visible"), BrowserConfiguration.chrome("visible")),

        /** The style property {@code backface-visibility}. */
        BACKFACE_VISIBILITY_("backface-visibility", "backface-visibility", BrowserConfiguration.ff("visible")),

        /** The style property {@code background}. */
        BACKGROUND("background", "background", BrowserConfiguration.ff(""), BrowserConfiguration.ie(""),
                BrowserConfiguration.chrome("rgba(0, 0, 0, 0) none repeat scroll 0% 0% / auto padding-box border-box")),

        /** The style property {@code backgroundAttachment}. */
        BACKGROUND_ATTACHMENT("backgroundAttachment", "background-attachment", BrowserConfiguration.chrome("scroll"),
                BrowserConfiguration.ff("scroll"), BrowserConfiguration.ie("scroll")),

        /** The style property {@code background-attachment}. */
        BACKGROUND_ATTACHMENT_("background-attachment", "background-attachment", BrowserConfiguration.ff("scroll")),

        /** The style property {@code backgroundBlendMode}. */
        BACKGROUND_BLEND_MODE("backgroundBlendMode", "background-blend-mode", BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code background-blend-mode}. */
        BACKGROUND_BLEND_MODE_("background-blend-mode", "background-blend-mode", BrowserConfiguration.ff("normal")),

        /** The style property {@code backgroundClip}. */
        BACKGROUND_CLIP("backgroundClip", "background-clip",
                BrowserConfiguration.ff("border-box"), BrowserConfiguration.ie("border-box"), BrowserConfiguration.chrome("border-box")),

        /** The style property {@code background-clip}. */
        BACKGROUND_CLIP_("background-clip", "background-clip", BrowserConfiguration.ff("border-box")),

        /** The style property {@code backgroundColor}. */
        BACKGROUND_COLOR("backgroundColor", "background-color", BrowserConfiguration.chrome("rgba(0, 0, 0, 0)"),
                BrowserConfiguration.ffBelow60("transparent"), BrowserConfiguration.ff60up("rgba(0, 0, 0, 0)"),
                BrowserConfiguration.ie("transparent")),

        /** The style property {@code background-color}. */
        BACKGROUND_COLOR_("background-color", "background-color", BrowserConfiguration.ffBelow60("transparent"), BrowserConfiguration.ff60up("rgba(0, 0, 0, 0)")),

        /** The style property {@code backgroundImage}. */
        BACKGROUND_IMAGE("backgroundImage", "background-image", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code background-image}. */
        BACKGROUND_IMAGE_("background-image", "background-image", BrowserConfiguration.ff("none")),

        /** The style property {@code backgroundOrigin}. */
        BACKGROUND_ORIGIN("backgroundOrigin", "background-origin",
                BrowserConfiguration.ff("padding-box"), BrowserConfiguration.ie("padding-box"), BrowserConfiguration.chrome("padding-box")),

        /** The style property {@code background-origin}. */
        BACKGROUND_ORIGIN_("background-origin", "background-origin", BrowserConfiguration.ff("padding-box")),

        /** The style property {@code backgroundPosition}. */
        BACKGROUND_POSITION("backgroundPosition", "background-position", BrowserConfiguration.chrome("0% 0%"), BrowserConfiguration.ff("0% 0%"), BrowserConfiguration.ie("0% 0%")),

        /** The style property {@code background-position}. */
        BACKGROUND_POSITION_("background-position", "background-position", BrowserConfiguration.ff("0% 0%")),

        /** The style property {@code backgroundPositionX}. */
        BACKGROUND_POSITION_X("backgroundPositionX", "background-position-x",
                BrowserConfiguration.ie("undefined"), BrowserConfiguration.chrome("0%"), BrowserConfiguration.ff("0%")),

        /** The style property {@code background-position-x}. */
        BACKGROUND_POSITION_X_("background-position-x", "background-position-x", BrowserConfiguration.ff("0%")),

        /** The style property {@code backgroundPositionY}. */
        BACKGROUND_POSITION_Y("backgroundPositionY", "background-position-y",
                BrowserConfiguration.ie("undefined"), BrowserConfiguration.chrome("0%"), BrowserConfiguration.ff("0%")),

        /** The style property {@code background-position-y}. */
        BACKGROUND_POSITION_Y_("background-position-y", "background-position-y", BrowserConfiguration.ff("0%")),

        /** The style property {@code backgroundRepeat}. */
        BACKGROUND_REPEAT("backgroundRepeat", "background-repeat", BrowserConfiguration.chrome("repeat"), BrowserConfiguration.ff("repeat"), BrowserConfiguration.ie("repeat")),

        /** The style property {@code background-repeat}. */
        BACKGROUND_REPEAT_("background-repeat", "background-repeat", BrowserConfiguration.ff("repeat")),

        /** The style property {@code backgroundRepeatX}. */
        BACKGROUND_REPEAT_X("backgroundRepeatX", "background-repeat-x", BrowserConfiguration.chrome("")),

        /** The style property {@code backgroundRepeatY}. */
        BACKGROUND_REPEAT_Y("backgroundRepeatY", "background-repeat-y", BrowserConfiguration.chrome("")),

        /** The style property {@code backgroundSize}. */
        BACKGROUND_SIZE("backgroundSize", "background-size", BrowserConfiguration.ff("auto auto"), BrowserConfiguration.ie("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code background-size}. */
        BACKGROUND_SIZE_("background-size", "background-size", BrowserConfiguration.ff("auto auto")),

        /** The style property {@code baselineShift}. */
        BASELINE_SHIFT("baselineShift", "baseline-shift", BrowserConfiguration.ie("baseline"), BrowserConfiguration.chrome("0px")),

        /** The style property {@code behavior}. */
        BEHAVIOR("behavior", "behavior"),

        /** The style property {@code blockSize}. */
        BLOCK_SIZE("blockSize", "block-size", BrowserConfiguration.ff(""), BrowserConfiguration.chrome("0px")),

        /** The style property {@code block-size}. */
        BLOCK_SIZE_("block-size", "block-size", BrowserConfiguration.ff("")),

        /** The style property {@code border}. */
        BORDER("border", "border", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code borderBlockEnd}. */
        BORDER_BLOCK_END("borderBlockEnd", "border-block-end", BrowserConfiguration.ff("")),

        /** The style property {@code border-block-end}. */
        BORDER_BLOCK_END_("border-block-end", "border-block-end", BrowserConfiguration.ff("")),

        /** The style property {@code borderBlockEndColor}. */
        BORDER_BLOCK_END_COLOR("borderBlockEndColor", "border-block-end-color", BrowserConfiguration.ff("")),

        /** The style property {@code border-block-end-color}. */
        BORDER_BLOCK_END_COLOR_("border-block-end-color", "border-block-end-color", BrowserConfiguration.ff("")),

        /** The style property {@code borderBlockEndStyle}. */
        BORDER_BLOCK_END_STYLE("borderBlockEndStyle", "border-block-end-style", BrowserConfiguration.ff("")),

        /** The style property {@code border-block-end-style}. */
        BORDER_BLOCK_END_STYLE_("border-block-end-style", "border-block-end-style", BrowserConfiguration.ff("")),

        /** The style property {@code borderBlockEndWidth}. */
        BORDER_BLOCK_END_WIDTH("borderBlockEndWidth", "border-block-end-width", BrowserConfiguration.ff("")),

        /** The style property {@code border-block-end-width}. */
        BORDER_BLOCK_END_WIDTH_("border-block-end-width", "border-block-end-width", BrowserConfiguration.ff("")),

        /** The style property {@code borderBlockStart}. */
        BORDER_BLOCK_START("borderBlockStart", "border-block-start", BrowserConfiguration.ff("")),

        /** The style property {@code border-block-start}. */
        BORDER_BLOCK_START_("border-block-start", "border-block-start", BrowserConfiguration.ff("")),

        /** The style property {@code borderBlockStartColor}. */
        BORDER_BLOCK_START_COLOR("borderBlockStartColor", "border-block-start-color", BrowserConfiguration.ff("")),

        /** The style property {@code border-block-start-color}. */
        BORDER_BLOCK_START_COLOR_("border-block-start-color", "border-block-start-color", BrowserConfiguration.ff("")),

        /** The style property {@code borderBlockStartStyle}. */
        BORDER_BLOCK_START_STYLE("borderBlockStartStyle", "border-block-start-style", BrowserConfiguration.ff("")),

        /** The style property {@code border-block-start-style}. */
        BORDER_BLOCK_START_STYLE_("border-block-start-style", "border-block-start-style", BrowserConfiguration.ff("")),

        /** The style property {@code borderBlockStartWidth}. */
        BORDER_BLOCK_START_WIDTH("borderBlockStartWidth", "border-block-start-width", BrowserConfiguration.ff("")),

        /** The style property {@code border-block-start-width}. */
        BORDER_BLOCK_START_WIDTH_("border-block-start-width", "border-block-start-width", BrowserConfiguration.ff("")),

        /** The style property {@code borderBottom}. */
        BORDER_BOTTOM("borderBottom", "border-bottom", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-bottom}. */
        BORDER_BOTTOM_("border-bottom", "border-bottom", BrowserConfiguration.ff("")),

        /** The style property {@code borderBottomColor}. */
        BORDER_BOTTOM_COLOR("borderBottomColor", "border-bottom-color", BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff("rgb(0, 0, 0)"),
                BrowserConfiguration.ie("rgb(0, 0, 0)")),

        /** The style property {@code border-bottom-color}. */
        BORDER_BOTTOM_COLOR_("border-bottom-color", "border-bottom-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code borderBottomLeftRadius}. */
        BORDER_BOTTOM_LEFT_RADIUS("borderBottomLeftRadius", "border-bottom-left-radius",
                BrowserConfiguration.ff("0px"), BrowserConfiguration.ie("0px"), BrowserConfiguration.chrome("0px")),

        /** The style property {@code border-bottom-left-radius}. */
        BORDER_BOTTOM_LEFT_RADIUS_("border-bottom-left-radius", "border-bottom-left-radius", BrowserConfiguration.ff("0px")),

        /** The style property {@code borderBottomRightRadius}. */
        BORDER_BOTTOM_RIGHT_RADIUS("borderBottomRightRadius", "border-bottom-right-radius",
                BrowserConfiguration.ff("0px"), BrowserConfiguration.ie("0px"), BrowserConfiguration.chrome("0px")),

        /** The style property {@code border-bottom-right-radius}. */
        BORDER_BOTTOM_RIGHT_RADIUS_("border-bottom-right-radius", "border-bottom-right-radius", BrowserConfiguration.ff("0px")),

        /** The style property {@code borderBottomStyle}. */
        BORDER_BOTTOM_STYLE("borderBottomStyle", "border-bottom-style", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code border-bottom-style}. */
        BORDER_BOTTOM_STYLE_("border-bottom-style", "border-bottom-style", BrowserConfiguration.ff("none")),

        /** The style property {@code borderBottomWidth}. */
        BORDER_BOTTOM_WIDTH("borderBottomWidth", "border-bottom-width", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("0px"), BrowserConfiguration.ie("0px")),

        /** The style property {@code border-bottom-width}. */
        BORDER_BOTTOM_WIDTH_("border-bottom-width", "border-bottom-width", BrowserConfiguration.ff("0px")),

        /** The style property {@code borderCollapse}. */
        BORDER_COLLAPSE("borderCollapse", "border-collapse", BrowserConfiguration.chrome("separate"), BrowserConfiguration.ff("separate"), BrowserConfiguration.ie("separate")),

        /** The style property {@code border-collapse}. */
        BORDER_COLLAPSE_("border-collapse", "border-collapse", BrowserConfiguration.ff("separate")),

        /** The style property {@code borderColor}. */
        BORDER_COLOR("borderColor", "border-color", BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-color}. */
        BORDER_COLOR_("border-color", "border-color", BrowserConfiguration.ff("")),

        /** The style property {@code borderImage}. */
        BORDER_IMAGE("borderImage", "border-image", BrowserConfiguration.ff(""), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("none")),

        /** The style property {@code border-image}. */
        BORDER_IMAGE_("border-image", "border-image", BrowserConfiguration.ff("")),

        /** The style property {@code borderImageOutset}. */
        BORDER_IMAGE_OUTSET("borderImageOutset", "border-image-outset", BrowserConfiguration.ie("0"), BrowserConfiguration.chrome("0px"),
                BrowserConfiguration.ffBelow60("0 0 0 0"), BrowserConfiguration.ff60up("0")),

        /** The style property {@code border-image-outset}. */
        BORDER_IMAGE_OUTSET_("border-image-outset", "border-image-outset", BrowserConfiguration.ffBelow60("0 0 0 0"), BrowserConfiguration.ff60up("0")),

        /** The style property {@code borderImageRepeat}. */
        BORDER_IMAGE_REPEAT("borderImageRepeat", "border-image-repeat",
                BrowserConfiguration.ff("stretch stretch"), BrowserConfiguration.ie("stretch"), BrowserConfiguration.chrome("stretch")),

        /** The style property {@code border-image-repeat}. */
        BORDER_IMAGE_REPEAT_("border-image-repeat", "border-image-repeat", BrowserConfiguration.ff("stretch stretch")),

        /** The style property {@code borderImageSlice}. */
        BORDER_IMAGE_SLICE("borderImageSlice", "border-image-slice", BrowserConfiguration.ie("100%"), BrowserConfiguration.chrome("100%"),
                BrowserConfiguration.ffBelow60("100% 100% 100% 100%"), BrowserConfiguration.ff60up("100%")),

        /** The style property {@code border-image-slice}. */
        BORDER_IMAGE_SLICE_("border-image-slice", "border-image-slice",
                BrowserConfiguration.ffBelow60("100% 100% 100% 100%"), BrowserConfiguration.ff60up("100%")),

        /** The style property {@code borderImageSource}. */
        BORDER_IMAGE_SOURCE("borderImageSource", "border-image-source", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code border-image-source}. */
        BORDER_IMAGE_SOURCE_("border-image-source", "border-image-source", BrowserConfiguration.ff("none")),

        /** The style property {@code borderImageWidth}. */
        BORDER_IMAGE_WIDTH("borderImageWidth", "border-image-width", BrowserConfiguration.ie("1"), BrowserConfiguration.chrome("1"),
                BrowserConfiguration.ffBelow60("1 1 1 1"), BrowserConfiguration.ff60up("1")),

        /** The style property {@code border-image-width}. */
        BORDER_IMAGE_WIDTH_("border-image-width", "border-image-width", BrowserConfiguration.ffBelow60("1 1 1 1"), BrowserConfiguration.ff60up("1")),

        /** The style property {@code borderInlineEnd}. */
        BORDER_INLINE_END("borderInlineEnd", "border-inline-end", BrowserConfiguration.ff("")),

        /** The style property {@code border-inline-end}. */
        BORDER_INLINE_END_("border-inline-end", "border-inline-end", BrowserConfiguration.ff("")),

        /** The style property {@code borderInlineEndColor}. */
        BORDER_INLINE_END_COLOR("borderInlineEndColor", "border-inline-end-color", BrowserConfiguration.ff("")),

        /** The style property {@code border-inline-end-color}. */
        BORDER_INLINE_END_COLOR_("border-inline-end-color", "border-inline-end-color", BrowserConfiguration.ff("")),

        /** The style property {@code borderInlineEndStyle}. */
        BORDER_INLINE_END_STYLE("borderInlineEndStyle", "border-inline-end-style", BrowserConfiguration.ff("")),

        /** The style property {@code border-inline-end-style}. */
        BORDER_INLINE_END_STYLE_("border-inline-end-style", "border-inline-end-style", BrowserConfiguration.ff("")),

        /** The style property {@code borderInlineEndWidth}. */
        BORDER_INLINE_END_WIDTH("borderInlineEndWidth", "border-inline-end-width", BrowserConfiguration.ff("")),

        /** The style property {@code border-inline-end-width}. */
        BORDER_INLINE_END_WIDTH_("border-inline-end-width", "border-inline-end-width", BrowserConfiguration.ff("")),

        /** The style property {@code borderInlineStart}. */
        BORDER_INLINE_START("borderInlineStart", "border-inline-start", BrowserConfiguration.ff("")),

        /** The style property {@code border-inline-start}. */
        BORDER_INLINE_START_("border-inline-start", "border-inline-start", BrowserConfiguration.ff("")),

        /** The style property {@code borderInlineStartColor}. */
        BORDER_INLINE_START_COLOR("borderInlineStartColor", "border-inline-start-color", BrowserConfiguration.ff("")),

        /** The style property {@code border-inline-start-color}. */
        BORDER_INLINE_START_COLOR_("border-inline-start-color", "border-inline-start-color", BrowserConfiguration.ff("")),

        /** The style property {@code borderInlineStartStyle}. */
        BORDER_INLINE_START_STYLE("borderInlineStartStyle", "border-inline-start-style", BrowserConfiguration.ff("")),

        /** The style property {@code border-inline-start-style}. */
        BORDER_INLINE_START_STYLE_("border-inline-start-style", "border-inline-start-style", BrowserConfiguration.ff("")),

        /** The style property {@code borderInlineStartWidth}. */
        BORDER_INLINE_START_WIDTH("borderInlineStartWidth", "border-inline-start-width", BrowserConfiguration.ff("")),

        /** The style property {@code border-inline-start-width}. */
        BORDER_INLINE_START_WIDTH_("border-inline-start-width", "border-inline-start-width", BrowserConfiguration.ff("")),

        /** The style property {@code borderLeft}. */
        BORDER_LEFT("borderLeft", "border-left", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-left}. */
        BORDER_LEFT_("border-left", "border-left", BrowserConfiguration.ff("")),

        /** The style property {@code borderLeftColor}. */
        BORDER_LEFT_COLOR("borderLeftColor", "border-left-color", BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff("rgb(0, 0, 0)"),
                BrowserConfiguration.ie("rgb(0, 0, 0)")),

        /** The style property {@code border-left-color}. */
        BORDER_LEFT_COLOR_("border-left-color", "border-left-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code borderLeftStyle}. */
        BORDER_LEFT_STYLE("borderLeftStyle", "border-left-style", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code border-left-style}. */
        BORDER_LEFT_STYLE_("border-left-style", "border-left-style", BrowserConfiguration.ff("none")),

        /** The style property {@code borderLeftWidth}. */
        BORDER_LEFT_WIDTH("borderLeftWidth", "border-left-width", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-left-width}. */
        BORDER_LEFT_WIDTH_("border-left-width", "border-left-width", BrowserConfiguration.ff("0px")),

        /** The style property {@code borderRadius}. */
        BORDER_RADIUS("borderRadius", "border-radius", BrowserConfiguration.ff(""), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("0px")),

        /** The style property {@code border-radius}. */
        BORDER_RADIUS_("border-radius", "border-radius", BrowserConfiguration.ff("")),

        /** The style property {@code borderRight}. */
        BORDER_RIGHT("borderRight", "border-right", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-right}. */
        BORDER_RIGHT_("border-right", "border-right", BrowserConfiguration.ff("")),

        /** The style property {@code borderRightColor}. */
        BORDER_RIGHT_COLOR("borderRightColor", "border-right-color", BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-right-color}. */
        BORDER_RIGHT_COLOR_("border-right-color", "border-right-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code borderRightStyle}. */
        BORDER_RIGHT_STYLE("borderRightStyle", "border-right-style", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-right-style}. */
        BORDER_RIGHT_STYLE_("border-right-style", "border-right-style", BrowserConfiguration.ff("none")),

        /** The style property {@code borderRightWidth}. */
        BORDER_RIGHT_WIDTH("borderRightWidth", "border-right-width", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-right-width}. */
        BORDER_RIGHT_WIDTH_("border-right-width", "border-right-width", BrowserConfiguration.ff("0px")),

        /** The style property {@code borderSpacing}. */
        BORDER_SPACING("borderSpacing", "border-spacing", BrowserConfiguration.chrome("0px 0px"), BrowserConfiguration.ff("0px 0px"), BrowserConfiguration.ie("0px 0px")),

        /** The style property {@code border-spacing}. */
        BORDER_SPACING_("border-spacing", "border-spacing", BrowserConfiguration.ff("0px 0px")),

        /** The style property {@code borderStyle}. */
        BORDER_STYLE("borderStyle", "border-style", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-style}. */
        BORDER_STYLE_("border-style", "border-style", BrowserConfiguration.ff("")),

        /** The style property {@code borderTop}. */
        BORDER_TOP("borderTop", "border-top", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-top}. */
        BORDER_TOP_("border-top", "border-top", BrowserConfiguration.ff("")),

        /** The style property {@code borderTopColor}. */
        BORDER_TOP_COLOR("borderTopColor", "border-top-color", BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-top-color}. */
        BORDER_TOP_COLOR_("border-top-color", "border-top-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code borderTopLeftRadius}. */
        BORDER_TOP_LEFT_RADIUS("borderTopLeftRadius", "border-top-left-radius",
                BrowserConfiguration.ff("0px"), BrowserConfiguration.ie("0px"), BrowserConfiguration.chrome("0px")),

        /** The style property {@code border-top-left-radius}. */
        BORDER_TOP_LEFT_RADIUS_("border-top-left-radius", "border-top-left-radius", BrowserConfiguration.ff("0px")),

        /** The style property {@code borderTopRightRadius}. */
        BORDER_TOP_RIGHT_RADIUS("borderTopRightRadius", "border-top-right-radius",
                BrowserConfiguration.ff("0px"), BrowserConfiguration.ie("0px"), BrowserConfiguration.chrome("0px")),

        /** The style property {@code border-top-right-radius}. */
        BORDER_TOP_RIGHT_RADIUS_("border-top-right-radius", "border-top-right-radius", BrowserConfiguration.ff("0px")),

        /** The style property {@code borderTopStyle}. */
        BORDER_TOP_STYLE("borderTopStyle", "border-top-style", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-top-style}. */
        BORDER_TOP_STYLE_("border-top-style", "border-top-style", BrowserConfiguration.ff("none")),

        /** The style property {@code borderTopWidth}. */
        BORDER_TOP_WIDTH("borderTopWidth", "border-top-width", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-top-width}. */
        BORDER_TOP_WIDTH_("border-top-width", "border-top-width", BrowserConfiguration.ff("0px")),

        /** The style property {@code borderWidth}. */
        BORDER_WIDTH("borderWidth", "border-width", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code border-width}. */
        BORDER_WIDTH_("border-width", "border-width", BrowserConfiguration.ff("")),

        /** The style property {@code bottom}. */
        BOTTOM("bottom", "bottom", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code boxDecorationBreak}. */
        BOX_DECORATION_BREAK("boxDecorationBreak", "box-decoration-break", BrowserConfiguration.ff("slice")),

        /** The style property {@code box-decoration-break}. */
        BOX_DECORATION_BREAK_("box-decoration-break", "box-decoration-break", BrowserConfiguration.ff("slice")),

        /** The style property {@code boxShadow}. */
        BOX_SHADOW("boxShadow", "box-shadow", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code box-shadow}. */
        BOX_SHADOW_("box-shadow", "box-shadow", BrowserConfiguration.ff("none")),

        /** The style property {@code boxSizing}. */
        BOX_SIZING("boxSizing", "box-sizing", BrowserConfiguration.ff("content-box"), BrowserConfiguration.ie("content-box"), BrowserConfiguration.chrome("content-box")),

        /** The style property {@code box-sizing}. */
        BOX_SIZING_("box-sizing", "box-sizing", BrowserConfiguration.ff("content-box")),

        /** The style property {@code breakAfter}. */
        BREAK_AFTER("breakAfter", "break-after", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code breakBefore}. */
        BREAK_BEFORE("breakBefore", "break-before", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code breakInside}. */
        BREAK_INSIDE("breakInside", "break-inside", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code bufferedRendering}. */
        BUFFERED_RENDERING("bufferedRendering", "buffered-rendering", BrowserConfiguration.chrome("auto")),

        /** The style property {@code captionSide}. */
        CAPTION_SIDE("captionSide", "caption-side", BrowserConfiguration.chrome("top"), BrowserConfiguration.ff("top"), BrowserConfiguration.ie("top")),

        /** The style property {@code caption-side}. */
        CAPTION_SIDE_("caption-side", "caption-side", BrowserConfiguration.ff("top")),

        /** The style property {@code caretColor}. */
        CARET_COLOR("caretColor", "caret-color", BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff60up("rgb(0, 0, 0)")),

        /** The style property {@code caret-color}. */
        CARET_COLOR_("caret-color", "caret-color", BrowserConfiguration.ff60up("rgb(0, 0, 0)")),

        /** The style property {@code clear}. */
        CLEAR("clear", "clear", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code clip}. */
        CLIP("clip", "clip", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code clipPath}. */
        CLIP_PATH("clipPath", "clip-path", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code clip-path}. */
        CLIP_PATH_("clip-path", "clip-path", BrowserConfiguration.ff("none")),

        /** The style property {@code clipRule}. */
        CLIP_RULE("clipRule", "clip-rule", BrowserConfiguration.ff("nonzero"), BrowserConfiguration.ie("nonzero"), BrowserConfiguration.chrome("nonzero")),

        /** The style property {@code clip-rule}. */
        CLIP_RULE_("clip-rule", "clip-rule", BrowserConfiguration.ff("nonzero")),

        /** The style property {@code color}. */
        COLOR("color", "color", BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code colorAdjust}. */
        COLOR_ADJUST("colorAdjust", "color-adjust", BrowserConfiguration.ff("economy")),

        /** The style property {@code color-adjust}. */
        COLOR_ADJUST_("color-adjust", "color-adjust", BrowserConfiguration.ff("economy")),

        /** The style property {@code colorInterpolation}. */
        COLOR_INTERPOLATION("colorInterpolation", "color-interpolation", BrowserConfiguration.ff("srgb"), BrowserConfiguration.chrome("sRGB")),

        /** The style property {@code color-interpolation}. */
        COLOR_INTERPOLATION_("color-interpolation", "color-interpolation", BrowserConfiguration.ff("srgb")),

        /** The style property {@code colorInterpolationFilters}. */
        COLOR_INTERPOLATION_FILTERS("colorInterpolationFilters",
                "color-interpolation-filters", BrowserConfiguration.ff("linearrgb"), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("linearRGB")),

        /** The style property {@code color-interpolation-filters}. */
        COLOR_INTERPOLATION_FILTERS_("color-interpolation-filters", "color-interpolation-filters", BrowserConfiguration.ff("linearrgb")),

        /** The style property {@code colorRendering}. */
        COLOR_RENDERING("colorRendering", "color-rendering", BrowserConfiguration.chrome("auto")),

        /** The style property {@code columnCount}. */
        COLUMN_COUNT("columnCount", "column-count", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code column-count}. */
        COLUMN_COUNT_("column-count", "column-count", BrowserConfiguration.ff("auto")),

        /** The style property {@code columnFill}. */
        COLUMN_FILL("columnFill", "column-fill", BrowserConfiguration.chrome("balance"), BrowserConfiguration.ff("balance"), BrowserConfiguration.ie("balance")),

        /** The style property {@code column-fill}. */
        COLUMN_FILL_("column-fill", "column-fill", BrowserConfiguration.ff("balance")),

        /** The style property {@code columnGap}. */
        COLUMN_GAP("columnGap", "column-gap", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ie("normal"),
                BrowserConfiguration.ffBelow60("16px"), BrowserConfiguration.ff60up("normal")),

        /** The style property {@code column-gap}. */
        COLUMN_GAP_("column-gap", "column-gap", BrowserConfiguration.ffBelow60("16px"), BrowserConfiguration.ff60up("normal")),

        /** The style property {@code columnRule}. */
        COLUMN_RULE("columnRule", "column-rule", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code column-rule}. */
        COLUMN_RULE_("column-rule", "column-rule", BrowserConfiguration.ff("")),

        /** The style property {@code columnRuleColor}. */
        COLUMN_RULE_COLOR("columnRuleColor", "column-rule-color",
                BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff("rgb(0, 0, 0)"), BrowserConfiguration.ie("rgb(0, 0, 0)")),

        /** The style property {@code column-rule-color}. */
        COLUMN_RULE_COLOR_("column-rule-color", "column-rule-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code columnRuleStyle}. */
        COLUMN_RULE_STYLE("columnRuleStyle", "column-rule-style", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code column-rule-style}. */
        COLUMN_RULE_STYLE_("column-rule-style", "column-rule-style", BrowserConfiguration.ff("none")),

        /** The style property {@code columnRuleWidth}. */
        COLUMN_RULE_WIDTH("columnRuleWidth", "column-rule-width", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("0px"), BrowserConfiguration.ie("medium")),

        /** The style property {@code column-rule-width}. */
        COLUMN_RULE_WIDTH_("column-rule-width", "column-rule-width", BrowserConfiguration.ff("0px")),

        /** The style property {@code columnSpan}. */
        COLUMN_SPAN("columnSpan", "column-span", BrowserConfiguration.chrome("none"), BrowserConfiguration.ie("1")),

        /** The style property {@code columnWidth}. */
        COLUMN_WIDTH("columnWidth", "column-width", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code column-width}. */
        COLUMN_WIDTH_("column-width", "column-width", BrowserConfiguration.ff("auto")),

        /** The style property {@code columns}. */
        COLUMNS("columns", "columns", BrowserConfiguration.chrome("auto auto"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code contain}. */
        CONTAIN("contain", "contain", BrowserConfiguration.chrome("none")),

        /** The style property {@code content}. */
        CONTENT("content", "content", BrowserConfiguration.ie("normal"), BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("none")),

        /** The style property {@code counterIncrement}. */
        COUNTER_INCREMENT("counterIncrement", "counter-increment", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code counter-increment}. */
        COUNTER_INCREMENT_("counter-increment", "counter-increment", BrowserConfiguration.ff("none")),

        /** The style property {@code counterReset}. */
        COUNTER_RESET("counterReset", "counter-reset", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code counter-reset}. */
        COUNTER_RESET_("counter-reset", "counter-reset", BrowserConfiguration.ff("none")),

        /** The style property {@code cssFloat}. */
        CSS_FLOAT("cssFloat", "css-float", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        //TODO: seems to be a combination of all other properties.
        /** The style property {@code cssText}. */
        CSS_TEXT("cssText", "css-text", BrowserConfiguration.chrome(""), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code cue}. */
        CUE("cue", "cue"),

        /** The style property {@code cueAfter}. */
        CUE_AFTER("cueAfter", "cue-after"),

        /** The style property {@code cueBefore}. */
        CUE_BEFORE("cueBefore", "cue-before"),

        /** The style property {@code cursor}. */
        CURSOR("cursor", "cursor", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code cx}. */
        CX("cx", "cx", BrowserConfiguration.chrome("0px")),

        /** The style property {@code cy}. */
        CY("cy", "cy", BrowserConfiguration.chrome("0px")),

        /** The style property {@code d}. */
        D("d", "d", BrowserConfiguration.chrome("none")),

        /** The style property {@code direction}. */
        DIRECTION("direction", "direction", BrowserConfiguration.chrome("ltr"), BrowserConfiguration.ff("ltr"), BrowserConfiguration.ie("ltr")),

        /** The style property {@code display}. */
        DISPLAY("display", "display", BrowserConfiguration.chrome("block"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code dominantBaseline}. */
        DOMINANT_BASELINE("dominantBaseline", "dominant-baseline", BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code dominant-baseline}. */
        DOMINANT_BASELINE_("dominant-baseline", "dominant-baseline", BrowserConfiguration.ff("auto")),

        /** The style property {@code elevation}. */
        ELEVATION("elevation", "elevation"),

        /** The style property {@code emptyCells}. */
        EMPTY_CELLS("emptyCells", "empty-cells", BrowserConfiguration.ie("show"), BrowserConfiguration.ff("show"),
                BrowserConfiguration.chrome("show")),

        /** The style property {@code empty-cells}. */
        EMPTY_CELLS_("empty-cells", "empty-cells", BrowserConfiguration.ff("show")),

        /** The style property {@code enableBackground}. */
        ENABLE_BACKGROUND("enableBackground", "enable-background", BrowserConfiguration.ie("accumulate")),

        /** The style property {@code fill}. */
        FILL("fill", "fill", BrowserConfiguration.ff("rgb(0, 0, 0)"), BrowserConfiguration.ie("black"), BrowserConfiguration.chrome("rgb(0, 0, 0)")),

        /** The style property {@code fillOpacity}. */
        FILL_OPACITY("fillOpacity", "fill-opacity", BrowserConfiguration.ff("1"), BrowserConfiguration.ie("1"), BrowserConfiguration.chrome("1")),

        /** The style property {@code fill-opacity}. */
        FILL_OPACITY_("fill-opacity", "fill-opacity", BrowserConfiguration.ff("1")),

        /** The style property {@code fillRule}. */
        FILL_RULE("fillRule", "fill-rule", BrowserConfiguration.ff("nonzero"), BrowserConfiguration.ie("nonzero"), BrowserConfiguration.chrome("nonzero")),

        /** The style property {@code fill-rule}. */
        FILL_RULE_("fill-rule", "fill-rule", BrowserConfiguration.ff("nonzero")),

        /** The style property {@code filter}. */
        FILTER("filter", "filter", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code flex}. */
        FLEX("flex", "flex", BrowserConfiguration.ff(""), BrowserConfiguration.ie("0 1 auto"), BrowserConfiguration.chrome("0 1 auto")),

        /** The style property {@code flexBasis}. */
        FLEX_BASIS("flexBasis", "flex-basis", BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code flex-basis}. */
        FLEX_BASIS_("flex-basis", "flex-basis", BrowserConfiguration.ff("auto")),

        /** The style property {@code flexDirection}. */
        FLEX_DIRECTION("flexDirection", "flex-direction", BrowserConfiguration.ff("row"), BrowserConfiguration.ie("row"), BrowserConfiguration.chrome("row")),

        /** The style property {@code flex-direction}. */
        FLEX_DIRECTION_("flex-direction", "flex-direction", BrowserConfiguration.ff("row")),

        /** The style property {@code flexFlow}. */
        FLEX_FLOW("flexFlow", "flex-flow", BrowserConfiguration.ff(""), BrowserConfiguration.ie("row nowrap"), BrowserConfiguration.chrome("row nowrap")),

        /** The style property {@code flex-flow}. */
        FLEX_FLOW_("flex-flow", "flex-flow", BrowserConfiguration.ff("")),

        /** The style property {@code flexGrow}. */
        FLEX_GROW("flexGrow", "flex-grow", BrowserConfiguration.ff("0"), BrowserConfiguration.ie("0"), BrowserConfiguration.chrome("0")),

        /** The style property {@code flex-grow}. */
        FLEX_GROW_("flex-grow", "flex-grow", BrowserConfiguration.ff("0")),

        /** The style property {@code flexShrink}. */
        FLEX_SHRINK("flexShrink", "flex-shrink", BrowserConfiguration.ff("1"), BrowserConfiguration.ie("1"), BrowserConfiguration.chrome("1")),

        /** The style property {@code flex-shrink}. */
        FLEX_SHRINK_("flex-shrink", "flex-shrink", BrowserConfiguration.ff("1")),

        /** The style property {@code flexWrap}. */
        FLEX_WRAP("flexWrap", "flex-wrap", BrowserConfiguration.ff("nowrap"), BrowserConfiguration.ie("nowrap"), BrowserConfiguration.chrome("nowrap")),

        /** The style property {@code flex-wrap}. */
        FLEX_WRAP_("flex-wrap", "flex-wrap", BrowserConfiguration.ff("nowrap")),

        /** The style property {@code float}. */
        FLOAT("float", "float", BrowserConfiguration.ff("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code floodColor}. */
        FLOOD_COLOR("floodColor", "flood-color", BrowserConfiguration.ff("rgb(0, 0, 0)"), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("rgb(0, 0, 0)")),

        /** The style property {@code flood-color}. */
        FLOOD_COLOR_("flood-color", "flood-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code floodOpacity}. */
        FLOOD_OPACITY("floodOpacity", "flood-opacity", BrowserConfiguration.ff("1"), BrowserConfiguration.ie("1"), BrowserConfiguration.chrome("1")),

        /** The style property {@code flood-opacity}. */
        FLOOD_OPACITY_("flood-opacity", "flood-opacity", BrowserConfiguration.ff("1")),

        /** The style property {@code font}. */
        FONT("font", "font", BrowserConfiguration.chrome("normal normal 400 normal 16px / normal \"Times New Roman\""), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code fontDisplay}. */
        FONT_DISPLAY("fontDisplay", "font-display", BrowserConfiguration.chrome("")),

        /** The style property {@code fontFamily}. */
        FONT_FAMILY("fontFamily", "font-family", BrowserConfiguration.chrome("\"Times New Roman\""), BrowserConfiguration.ie("Times New Roman"), BrowserConfiguration.ff("serif")),

        /** The style property {@code font-family}. */
        FONT_FAMILY_("font-family", "font-family", BrowserConfiguration.ff("serif")),

        /** The style property {@code fontFeatureSettings}. */
        FONT_FEATURE_SETTINGS("fontFeatureSettings", "font-feature-settings",
                BrowserConfiguration.ie("normal"), BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code font-feature-settings}. */
        FONT_FEATURE_SETTINGS_("font-feature-settings", "font-feature-settings", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontKerning}. */
        FONT_KERNING("fontKerning", "font-kerning", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code font-kerning}. */
        FONT_KERNING_("font-kerning", "font-kerning", BrowserConfiguration.ff("auto")),

        /** The style property {@code fontLanguageOverride}. */
        FONT_LANGUAGE_OVERRIDE("fontLanguageOverride", "font-language-override", BrowserConfiguration.ff("normal")),

        /** The style property {@code font-language-override}. */
        FONT_LANGUAGE_OVERRIDE_("font-language-override", "font-language-override", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontSize}. */
        FONT_SIZE("fontSize", "font-size", BrowserConfiguration.chrome("16px"), BrowserConfiguration.ff("16px"), BrowserConfiguration.ie("16px")),

        /** The style property {@code font-size}. */
        FONT_SIZE_("font-size", "font-size", BrowserConfiguration.ff("16px")),

        /** The style property {@code fontSizeAdjust}. */
        FONT_SIZE_ADJUST("fontSizeAdjust", "font-size-adjust", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code font-size-adjust}. */
        FONT_SIZE_ADJUST_("font-size-adjust", "font-size-adjust", BrowserConfiguration.ff("none")),

        /** The style property {@code fontStretch}. */
        FONT_STRETCH("fontStretch", "font-stretch", BrowserConfiguration.chrome("100%"), BrowserConfiguration.ff("normal"), BrowserConfiguration.ie("normal")),

        /** The style property {@code font-stretch}. */
        FONT_STRETCH_("font-stretch", "font-stretch", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontStyle}. */
        FONT_STYLE("fontStyle", "font-style", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal"), BrowserConfiguration.ie("normal")),

        /** The style property {@code font-style}. */
        FONT_STYLE_("font-style", "font-style", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontSynthesis}. */
        FONT_SYNTHESIS("fontSynthesis", "font-synthesis", BrowserConfiguration.ff("weight style")),

        /** The style property {@code font-synthesis}. */
        FONT_SYNTHESIS_("font-synthesis", "font-synthesis", BrowserConfiguration.ff("weight style")),

        /** The style property {@code fontVariant}. */
        FONT_VARIANT("fontVariant", "font-variant", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal"), BrowserConfiguration.ie("normal")),

        /** The style property {@code font-variant}. */
        FONT_VARIANT_("font-variant", "font-variant", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontVariantAlternates}. */
        FONT_VARIANT_ALTERNATES("fontVariantAlternates", "font-variant-alternates", BrowserConfiguration.ff("normal")),

        /** The style property {@code font-variant-alternates}. */
        FONT_VARIANT_ALTERNATES_("font-variant-alternates", "font-variant-alternates", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontVariantCaps}. */
        FONT_VARIANT_CAPS("fontVariantCaps", "font-variant-caps", BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code font-variant-caps}. */
        FONT_VARIANT_CAPS_("font-variant-caps", "font-variant-caps", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontVariantEastAsian}. */
        FONT_VARIANT_EAST_ASIAN("fontVariantEastAsian", "font-variant-east-asian", BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code font-variant-east-asian}. */
        FONT_VARIANT_EAST_ASIAN_("font-variant-east-asian", "font-variant-east-asian", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontVariantLigatures}. */
        FONT_VARIANT_LIGATURES("fontVariantLigatures", "font-variant-ligatures", BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code font-variant-ligatures}. */
        FONT_VARIANT_LIGATURES_("font-variant-ligatures", "font-variant-ligatures", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontVariantNumeric}. */
        FONT_VARIANT_NUMERIC("fontVariantNumeric", "font-variant-numeric", BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code font-variant-numeric}. */
        FONT_VARIANT_NUMERIC_("font-variant-numeric", "font-variant-numeric", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontVariantPosition}. */
        FONT_VARIANT_POSITION("fontVariantPosition", "font-variant-position", BrowserConfiguration.ff("normal")),

        /** The style property {@code font-variant-position}. */
        FONT_VARIANT_POSITION_("font-variant-position", "font-variant-position", BrowserConfiguration.ff("normal")),

        /** The style property {@code fontVariationSettings}. */
        FONT_VARIATION_SETTING("fontVariationSettings", "font-variation-settings", BrowserConfiguration.chrome("normal")),

        /** The style property {@code fontWeight}. */
        FONT_WEIGHT("fontWeight", "font-weight", BrowserConfiguration.chrome("400"), BrowserConfiguration.ff("400"), BrowserConfiguration.ie("400")),

        /** The style property {@code font-weight}. */
        FONT_WEIGHT_("font-weight", "font-weight", BrowserConfiguration.ff("400")),

        /** The style property {@code gap}. */
        GAP("gap", "gap", BrowserConfiguration.chrome("normal normal")),

        /** The style property {@code glyphOrientationHorizontal}. */
        GLYPH_ORIENTATION_HORIZONTAL("glyphOrientationHorizontal", "glyph-orientation-horizontal",
                BrowserConfiguration.ie("0deg")),

        /** The style property {@code glyphOrientationVertical}. */
        GLYPH_ORIENTATION_VERTICAL("glyphOrientationVertical", "glyph-orientation-vertical",
                BrowserConfiguration.ie("auto")),

        /** The style property {@code grid}. */
        GRID("grid", "grid", BrowserConfiguration.chrome("none / none / none / row / auto / auto"),
                BrowserConfiguration.ff("")),

        /** The style property {@code gridArea}. */
        GRID_AREA("gridArea", "grid-area", BrowserConfiguration.chrome("auto / auto / auto / auto"), BrowserConfiguration.ff("")),

        /** The style property {@code grid-area}. */
        GRID_AREA_("grid-area", "grid-area", BrowserConfiguration.ff("")),

        /** The style property {@code gridAutoColumns}. */
        GRID_AUTO_COLUMNS("gridAutoColumns", "grid-auto-columns", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code grid-auto-columns}. */
        GRID_AUTO_COLUMNS_("grid-auto-columns", "grid-auto-columns", BrowserConfiguration.ff("auto")),

        /** The style property {@code gridAutoFlow}. */
        GRID_AUTO_FLOW("gridAutoFlow", "grid-auto-flow", BrowserConfiguration.chrome("row"), BrowserConfiguration.ff("row")),

        /** The style property {@code grid-auto-flow}. */
        GRID_AUTO_FLOW_("grid-auto-flow", "grid-auto-flow", BrowserConfiguration.ff("row")),

        /** The style property {@code gridAutoRows}. */
        GRID_AUTO_ROWS("gridAutoRows", "grid-auto-rows", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code grid-auto-rows}. */
        GRID_AUTO_ROWS_("grid-auto-rows", "grid-auto-rows", BrowserConfiguration.ff("auto")),

        /** The style property {@code gridColumn}. */
        GRID_COLUMN("gridColumn", "grid-column", BrowserConfiguration.chrome("auto / auto"), BrowserConfiguration.ff("")),

        /** The style property {@code grid-column}. */
        GRID_COLUMN_("grid-column", "grid-column", BrowserConfiguration.ff("")),

        /** The style property {@code gridColumnEnd}. */
        GRID_COLUMN_END("gridColumnEnd", "grid-column-end", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code grid-column-end}. */
        GRID_COLUMN_END_("grid-column-end", "grid-column-end", BrowserConfiguration.ff("auto")),

        /** The style property {@code gridColumnGap}. */
        GRID_COLUMN_GAP("gridColumnGap", "grid-column-gap", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("0px")),

        /** The style property {@code grid-column-gap}. */
        GRID_COLUMN_GAP_("grid-column-gap", "grid-column-gap", BrowserConfiguration.ff("0px")),

        /** The style property {@code gridColumnStart}. */
        GRID_COLUMN_START("gridColumnStart", "grid-column-start", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code grid-column-start}. */
        GRID_COLUMN_START_("grid-column-start", "grid-column-start", BrowserConfiguration.ff("auto")),

        /** The style property {@code gridGap}. */
        GRID_GAP("gridGap", "grid-gap", BrowserConfiguration.chrome("normal normal"), BrowserConfiguration.ff("")),

        /** The style property {@code grid-gap}. */
        GRID_GAP_("grid-gap", "grid-gap", BrowserConfiguration.ff("")),

        /** The style property {@code gridRow}. */
        GRID_ROW("gridRow", "grid-row", BrowserConfiguration.chrome("auto / auto"), BrowserConfiguration.ff("")),

        /** The style property {@code grid-row}. */
        GRID_ROW_("grid-row", "grid-row", BrowserConfiguration.ff("")),

        /** The style property {@code gridRowEnd}. */
        GRID_ROW_END("gridRowEnd", "grid-row-end", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code grid-row-end}. */
        GRID_ROW_END_("grid-row-end", "grid-row-end", BrowserConfiguration.ff("auto")),

        /** The style property {@code gridRowGap}. */
        GRID_ROW_GAP("gridRowGap", "grid-row-gap", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("0px")),

        /** The style property {@code grid-row-gap}. */
        GRID_ROW_GAP_("grid-row-gap", "grid-row-gap", BrowserConfiguration.ff("0px")),

        /** The style property {@code gridRowStart}. */
        GRID_ROW_START("gridRowStart", "grid-row-start", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code grid-row-start}. */
        GRID_ROW_START_("grid-row-start", "grid-row-start", BrowserConfiguration.ff("auto")),

        /** The style property {@code gridTemplate}. */
        GRID_TEMPLATE("gridTemplate", "grid-template", BrowserConfiguration.chrome("none / none / none"), BrowserConfiguration.ff("")),

        /** The style property {@code grid-template}. */
        GRID_TEMPLATE_("grid-template", "grid-template", BrowserConfiguration.ff("")),

        /** The style property {@code gridTemplateAreas}. */
        GRID_TEMPLATE_AREAS("gridTemplateAreas", "grid-template-areas", BrowserConfiguration.chrome("none"),
                BrowserConfiguration.ff("none")),

        /** The style property {@code grid-template-areas}. */
        GRID_TEMPLATE_AREAS_("grid-template-areas", "grid-template-areas",
                BrowserConfiguration.ff("none")),

        /** The style property {@code gridTemplateColumns}. */
        GRID_TEMPLATE_COLUMNS("gridTemplateColumns", "grid-template-columns", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code grid-template-columns}. */
        GRID_TEMPLATE_COLUMNS_("grid-template-columns", "grid-template-columns", BrowserConfiguration.ff("none")),

        /** The style property {@code gridTemplateRows}. */
        GRID_TEMPLATE_ROWS("gridTemplateRows", "grid-template-rows", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code grid-template-rows}. */
        GRID_TEMPLATE_ROWS_("grid-template-rows", "grid-template-rows", BrowserConfiguration.ff("none")),

        /** The style property {@code height}. */
        HEIGHT("height", "height", BrowserConfiguration.chrome(""), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code hyphens}. */
        HYPHENS("hyphens", "hyphens", BrowserConfiguration.ff("manual"), BrowserConfiguration.chrome("manual")),

        /** The style property {@code imageOrientation}. */
        IMAGE_ORIENTATION("imageOrientation", "image-orientation", BrowserConfiguration.ff("0deg")),

        /** The style property {@code image-orientation}. */
        IMAGE_ORIENTATION_("image-orientation", "image-orientation", BrowserConfiguration.ff("0deg")),

        /** The style property {@code imageRendering}. */
        IMAGE_RENDERING("imageRendering", "image-rendering", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code image-rendering}. */
        IMAGE_RENDERING_("image-rendering", "image-rendering", BrowserConfiguration.ff("auto")),

        /** The style property {@code imeMode}. */
        IME_MODE("imeMode", "ime-mode", BrowserConfiguration.ie("undefined"), BrowserConfiguration.ff("auto")),

        /** The style property {@code ime-mode}. */
        IME_MODE_("ime-mode", "ime-mode", BrowserConfiguration.ff("auto")),

        /** The style property {@code inlineSize}. */
        INLINE_SIZE("inlineSize", "inline-size", BrowserConfiguration.ff(""), BrowserConfiguration.chrome("913px")),

        /** The style property {@code inline-size}. */
        INLINE_SIZE_("inline-size", "inline-size", BrowserConfiguration.ff("")),

        /** The style property {@code isolation}. */
        ISOLATION("isolation", "isolation", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code justifyContent}. */
        JUSTIFY_CONTENT("justifyContent", "justify-content",
                BrowserConfiguration.ff("normal"),
                BrowserConfiguration.ie("flex-start"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code justify-content}. */
        JUSTIFY_CONTENT_("justify-content", "justify-content", BrowserConfiguration.ff("normal")),

        /** The style property {@code justifyItems}. */
        JUSTIFY_ITEMS("justifyItems", "justify-items", BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code justify-items}. */
        JUSTIFY_ITEMS_("justify-items", "justify-items", BrowserConfiguration.ff("normal")),

        /** The style property {@code justifySelf}. */
        JUSTIFY_SELF("justifySelf", "justify-self", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code justify-self}. */
        JUSTIFY_SELF_("justify-self", "justify-self", BrowserConfiguration.ff("auto")),

        /** The style property {@code kerning}. */
        KERNING("kerning", "kerning", BrowserConfiguration.ie("auto")),

        /** The style property {@code layoutFlow}. */
        LAYOUT_FLOW("layoutFlow", "layout-flow", BrowserConfiguration.ie("undefined")),

        /** The style property {@code layoutGrid}. */
        LAYOUT_GRID("layoutGrid", "layout-grid", BrowserConfiguration.ie("undefined")),

        /** The style property {@code layoutGridChar}. */
        LAYOUT_GRID_CHAR("layoutGridChar", "layout-grid-char", BrowserConfiguration.ie("undefined")),

        /** The style property {@code layoutGridLine}. */
        LAYOUT_GRID_LINE("layoutGridLine", "layout-grid-line", BrowserConfiguration.ie("undefined")),

        /** The style property {@code layoutGridMode}. */
        LAYOUT_GRID_MODE("layoutGridMode", "layout-grid-mode", BrowserConfiguration.ie("undefined")),

        /** The style property {@code layoutGridType}. */
        LAYOUT_GRID_TYPE("layoutGridType", "layout-grid-type", BrowserConfiguration.ie("undefined")),

        /** The style property {@code left}. */
        LEFT("left", "left", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code letterSpacing}. */
        LETTER_SPACING("letterSpacing", "letter-spacing", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code letter-spacing}. */
        LETTER_SPACING_("letter-spacing", "letter-spacing", BrowserConfiguration.ff("normal")),

        /** The style property {@code lightingColor}. */
        LIGHTING_COLOR("lightingColor", "lighting-color",
                BrowserConfiguration.ff("rgb(255, 255, 255)"), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("rgb(255, 255, 255)")),

        /** The style property {@code lighting-color}. */
        LIGHTING_COLOR_("lighting-color", "lighting-color", BrowserConfiguration.ff("rgb(255, 255, 255)")),

        /** The style property {@code lineBreak}. */
        LINE_BREAK("lineBreak", "line-break", BrowserConfiguration.ie("undefined"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code lineHeight}. */
        LINE_HEIGHT("lineHeight", "line-height", BrowserConfiguration.ff("20px"), BrowserConfiguration.ie("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code line-height}. */
        LINE_HEIGHT_("line-height", "line-height", BrowserConfiguration.ff("20px")),

        /** The style property {@code listStyle}. */
        LIST_STYLE("listStyle", "list-style", BrowserConfiguration.chrome("disc outside none"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code list-style}. */
        LIST_STYLE_("list-style", "list-style", BrowserConfiguration.ff("")),

        /** The style property {@code listStyleImage}. */
        LIST_STYLE_IMAGE("listStyleImage", "list-style-image", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code list-style-image}. */
        LIST_STYLE_IMAGE_("list-style-image", "list-style-image", BrowserConfiguration.ff("none")),

        /** The style property {@code listStylePosition}. */
        LIST_STYLE_POSITION("listStylePosition", "list-style-position",
                BrowserConfiguration.chrome("outside"), BrowserConfiguration.ff("outside"), BrowserConfiguration.ie("outside")),

        /** The style property {@code list-style-position}. */
        LIST_STYLE_POSITION_("list-style-position", "list-style-position", BrowserConfiguration.ff("outside")),

        /** The style property {@code listStyleType}. */
        LIST_STYLE_TYPE("listStyleType", "list-style-type", BrowserConfiguration.chrome("disc"), BrowserConfiguration.ff("disc"), BrowserConfiguration.ie("disc")),

        /** The style property {@code list-style-type}. */
        LIST_STYLE_TYPE_("list-style-type", "list-style-type", BrowserConfiguration.ff("disc")),

        /** The style property {@code margin}. */
        MARGIN("margin", "margin", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code marginBlockEnd}. */
        MARGIN_BLOCK_END("marginBlockEnd", "margin-block-end", BrowserConfiguration.ff("")),

        /** The style property {@code margin-block-end}. */
        MARGIN_BLOCK_END_("margin-block-end", "margin-block-end", BrowserConfiguration.ff("")),

        /** The style property {@code marginBlockStart}. */
        MARGIN_BLOCK_START("marginBlockStart", "margin-block-start", BrowserConfiguration.ff("")),

        /** The style property {@code margin-block-start}. */
        MARGIN_BLOCK_START_("margin-block-start", "margin-block-start", BrowserConfiguration.ff("")),

        /** The style property {@code marginBottom}. */
        MARGIN_BOTTOM("marginBottom", "margin-bottom", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code margin-bottom}. */
        MARGIN_BOTTOM_("margin-bottom", "margin-bottom", BrowserConfiguration.ff("0px")),

        /** The style property {@code marginInlineEnd}. */
        MARGIN_INLINE_END("marginInlineEnd", "margin-inline-end", BrowserConfiguration.ff("")),

        /** The style property {@code margin-inline-end}. */
        MARGIN_INLINE_END_("margin-inline-end", "margin-inline-end", BrowserConfiguration.ff("")),

        /** The style property {@code marginInlineStart}. */
        MARGIN_INLINE_START("marginInlineStart", "margin-inline-start", BrowserConfiguration.ff("")),

        /** The style property {@code margin-inline-start}. */
        MARGIN_INLINE_START_("margin-inline-start", "margin-inline-start", BrowserConfiguration.ff("")),

        /** The style property {@code marginLeft}. */
        MARGIN_LEFT("marginLeft", "margin-left", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code margin-left}. */
        MARGIN_LEFT_("margin-left", "margin-left", BrowserConfiguration.ff("0px")),

        /** The style property {@code marginRight}. */
        MARGIN_RIGHT("marginRight", "margin-right", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code margin-right}. */
        MARGIN_RIGHT_("margin-right", "margin-right", BrowserConfiguration.ff("0px")),

        /** The style property {@code marginTop}. */
        MARGIN_TOP("marginTop", "margin-top", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code margin-top}. */
        MARGIN_TOP_("margin-top", "margin-top", BrowserConfiguration.ff("0px")),

        /** The style property {@code marker}. */
        MARKER("marker", "marker", BrowserConfiguration.ff(""), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("")),

        /** The style property {@code markerEnd}. */
        MARKER_END("markerEnd", "marker-end", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code marker-end}. */
        MARKER_END_("marker-end", "marker-end", BrowserConfiguration.ff("none")),

        /** The style property {@code markerMid}. */
        MARKER_MID("markerMid", "marker-mid", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code marker-mid}. */
        MARKER_MID_("marker-mid", "marker-mid", BrowserConfiguration.ff("none")),

        /** The style property {@code markerOffset}. */
        MARKER_OFFSET("markerOffset", "marker-offset"),

        /** The style property {@code marker-offset}. */
        MARKER_OFFSET_("marker-offset", "marker-offset"),

        /** The style property {@code markerStart}. */
        MARKER_START("markerStart", "marker-start", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code marker-start}. */
        MARKER_START_("marker-start", "marker-start", BrowserConfiguration.ff("none")),

        /** The style property {@code mask}. */
        MASK("mask", "mask", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code maskClip}. */
        MASK_CLIP("maskClip", "mask-clip", BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code mask-clip}. */
        MASK_CLIP_("mask-clip", "mask-clip", BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code maskComposite}. */
        MASK_COMPOSITE("maskComposite", "mask-composite", BrowserConfiguration.ff60up("add")),

        /** The style property {@code mask-composite}. */
        MASK_COMPOSITE_("mask-composite", "mask-composite", BrowserConfiguration.ff60up("add")),

        /** The style property {@code maskImage}. */
        MASK_IMAGE("maskImage", "mask-image", BrowserConfiguration.ff60up("none")),

        /** The style property {@code mask-image}. */
        MASK_IMAGE_("mask-image", "mask-image", BrowserConfiguration.ff60up("none")),

        /** The style property {@code maskMode}. */
        MASK_MODE("maskMode", "mask-mode", BrowserConfiguration.ff60up("match-source")),

        /** The style property {@code mask-mode}. */
        MASK_MODE_("mask-mode", "mask-mode", BrowserConfiguration.ff60up("match-source")),

        /** The style property {@code maskOrigin}. */
        MASK_ORIGIN("maskOrigin", "mask-origin", BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code mask-origin}. */
        MASK_ORIGIN_("mask-origin", "mask-origin", BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code maskPosition}. */
        MASK_POSITION("maskPosition", "mask-position", BrowserConfiguration.ff60up("0% 0%")),

        /** The style property {@code mask-position}. */
        MASK_POSITION_("mask-position", "mask-position", BrowserConfiguration.ff60up("0% 0%")),

        /** The style property {@code maskPositionX}. */
        MASK_POSITION_X("maskPositionX", "mask-position-x", BrowserConfiguration.ff60up("0%")),

        /** The style property {@code mask-position-x}. */
        MASK_POSITION_X_("mask-position-x", "mask-position-x", BrowserConfiguration.ff60up("0%")),

        /** The style property {@code maskPositionY}. */
        MASK_POSITION_Y("maskPositionY", "mask-position-y", BrowserConfiguration.ff60up("0%")),

        /** The style property {@code mask-position-y}. */
        MASK_POSITION_Y_("mask-position-y", "mask-position-y", BrowserConfiguration.ff60up("0%")),

        /** The style property {@code maskRepeat}. */
        MASK_REPEAT("maskRepeat", "mask-repeat", BrowserConfiguration.ff60up("repeat")),

        /** The style property {@code mask-repeat}. */
        MASK_REPEAT_("mask-repeat", "mask-repeat", BrowserConfiguration.ff60up("repeat")),

        /** The style property {@code maskSize}. */
        MASK_SIZE("maskSize", "mask-size", BrowserConfiguration.ff60up("auto auto")),

        /** The style property {@code mask-size}. */
        MASK_SIZE_("mask-size", "mask-size", BrowserConfiguration.ff60up("auto auto")),

        /** The style property {@code maskType}. */
        MASK_TYPE("maskType", "mask-type", BrowserConfiguration.ff("luminance"), BrowserConfiguration.chrome("luminance")),

        /** The style property {@code mask-type}. */
        MASK_TYPE_("mask-type", "mask-type", BrowserConfiguration.ff("luminance")),

        /** The style property {@code maxBlockSize}. */
        MAX_BLOCK_SIZE("maxBlockSize", "max-block-size", BrowserConfiguration.ff(""), BrowserConfiguration.chrome("none")),

        /** The style property {@code max-block-size}. */
        MAX_BLOCK_SIZE_("max-block-size", "max-block-size", BrowserConfiguration.ff("")),

        /** The style property {@code maxHeight}. */
        MAX_HEIGHT("maxHeight", "max-height", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code max-height}. */
        MAX_HEIGHT_("max-height", "max-height", BrowserConfiguration.ff("none")),

        /** The style property {@code maxInlineSize}. */
        MAX_INLINE_SIZE("maxInlineSize", "max-inline-size", BrowserConfiguration.ff(""), BrowserConfiguration.chrome("none")),

        /** The style property {@code max-inline-size}. */
        MAX_INLINE_SIZE_("max-inline-size", "max-inline-size", BrowserConfiguration.ff("")),

        /** The style property {@code maxWidth}. */
        MAX_WIDTH("maxWidth", "max-width", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code max-width}. */
        MAX_WIDTH_("max-width", "max-width", BrowserConfiguration.ff("none")),

        /** The style property {@code maxZoom}. */
        MAX_ZOOM("maxZoom", "max-zoom", BrowserConfiguration.chrome("")),

        /** The style property {@code minBlockSize}. */
        MIN_BLOCK_SIZE("minBlockSize", "min-block-size", BrowserConfiguration.ff(""), BrowserConfiguration.chrome("0px")),

        /** The style property {@code min-block-size}. */
        MIN_BLOCK_SIZE_("min-block-size", "min-block-size", BrowserConfiguration.ff("")),

        /** The style property {@code minHeight}. */
        MIN_HEIGHT("minHeight", "min-height", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code min-height}. */
        MIN_HEIGHT_("min-height", "min-height", BrowserConfiguration.ff("0px")),

        /** The style property {@code minInlineSize}. */
        MIN_INLINE_SIZE("minInlineSize", "min-inline-size", BrowserConfiguration.ff(""), BrowserConfiguration.chrome("0px")),

        /** The style property {@code min-inline-size}. */
        MIN_INLINE_SIZE_("min-inline-size", "min-inline-size", BrowserConfiguration.ff("")),

        /** The style property {@code minWidth}. */
        MIN_WIDTH("minWidth", "min-width", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code min-width}. */
        MIN_WIDTH_("min-width", "min-width", BrowserConfiguration.ff("0px")),

        /** The style property {@code minZoom}. */
        MIN_ZOOM("minZoom", "min-zoom", BrowserConfiguration.chrome("")),

        /** The style property {@code mixBlendMode}. */
        MIX_BLEND_MODE("mixBlendMode", "mix-blend-mode", BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code mix-blend-mode}. */
        MIX_BLEND_MODE_("mix-blend-mode", "mix-blend-mode", BrowserConfiguration.ff("normal")),

        /** The style property {@code MozAnimation}. */
        MOZ_ANIMATION("MozAnimation", "-moz-animation", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-animation}. */
        MOZ_ANIMATION__("-moz-animation", "-moz-animation", BrowserConfiguration.ff("")),

        /** The style property {@code MozAnimationDelay}. */
        MOZ_ANIMATION_DELAY("MozAnimationDelay", "-moz-animation-delay", BrowserConfiguration.ff("0s")),

        /** The style property {@code -moz-animation-delay}. */
        MOZ_ANIMATION_DELAY__("-moz-animation-delay", "-moz-animation-delay", BrowserConfiguration.ff("0s")),

        /** The style property {@code MozAnimationDirection}. */
        MOZ_ANIMATION_DIRECTION("MozAnimationDirection", "-moz-animation-direction", BrowserConfiguration.ff("normal")),

        /** The style property {@code -moz-animation-direction}. */
        MOZ_ANIMATION_DIRECTION__("-moz-animation-direction", "-moz-animation-direction", BrowserConfiguration.ff("normal")),

        /** The style property {@code MozAnimationDuration}. */
        MOZ_ANIMATION_DURATION("MozAnimationDuration", "-moz-animation-duration", BrowserConfiguration.ff("0s")),

        /** The style property {@code -moz-animation-duration}. */
        MOZ_ANIMATION_DURATION__("-moz-animation-duration", "-moz-animation-duration", BrowserConfiguration.ff("0s")),

        /** The style property {@code MozAnimationFillMode}. */
        MOZ_ANIMATION_FILL_MODE("MozAnimationFillMode", "-moz-animation-fill-mode", BrowserConfiguration.ff("none")),

        /** The style property {@code -moz-animation-fill-mode}. */
        MOZ_ANIMATION_FILL_MODE__("-moz-animation-fill-mode", "-moz-animation-fill-mode", BrowserConfiguration.ff("none")),

        /** The style property {@code MozAnimationIterationCount}. */
        MOZ_ANIMATION_ITERATION_COUNT("MozAnimationIterationCount", "-moz-animation-iteration-count", BrowserConfiguration.ff("1")),

        /** The style property {@code -moz-animation-iteration-count}. */
        MOZ_ANIMATION_ITERATION_COUNT__("-moz-animation-iteration-count", "-moz-animation-iteration-count",
                BrowserConfiguration.ff("1")),

        /** The style property {@code MozAnimationName}. */
        MOZ_ANIMATION_NAME("MozAnimationName", "-moz-animation-name", BrowserConfiguration.ff("none")),

        /** The style property {@code -moz-animation-name}. */
        MOZ_ANIMATION_NAME__("-moz-animation-name", "-moz-animation-name", BrowserConfiguration.ff("none")),

        /** The style property {@code MozAnimationPlayState}. */
        MOZ_ANIMATION_PLAY_STATE("MozAnimationPlayState", "-moz-animation-play-state", BrowserConfiguration.ff("running")),

        /** The style property {@code -moz-animation-play-state}. */
        MOZ_ANIMATION_PLAY_STATE__("-moz-animation-play-state", "-moz-animation-play-state", BrowserConfiguration.ff("running")),

        /** The style property {@code MozAnimationTimingFunction}. */
        MOZ_ANIMATION_TIMING_FUNCTION("MozAnimationTimingFunction", "-moz-animation-timing-function",
                BrowserConfiguration.ff("ease")),

        /** The style property {@code -moz-animation-timing-function}. */
        MOZ_ANIMATION_TIMING_FUNCTION__("-moz-animation-timing-function", "-moz-animation-timing-function",
                BrowserConfiguration.ff("ease")),

        /** The style property {@code MozAppearance}. */
        MOZ_APPEARANCE("MozAppearance", "-moz-appearance", BrowserConfiguration.ff("none")),

        /** The style property {@code -moz-appearance}. */
        MOZ_APPEARANCE__("-moz-appearance", "-moz-appearance", BrowserConfiguration.ff("none")),

        /** The style property {@code MozBackfaceVisibility}. */
        MOZ_BACKFACE_VISIBILITY("MozBackfaceVisibility", "-moz-backface-visibility", BrowserConfiguration.ff("visible")),

        /** The style property {@code -moz-backface-visibility}. */
        MOZ_BACKFACE_VISIBILITY__("-moz-backface-visibility", "-moz-backface-visibility", BrowserConfiguration.ff("visible")),

        /** The style property {@code MozBackgroundClip}. */
        MOZ_BACKGROUND_CLIP("MozBackgroundClip", "-moz-background-clip"),

        /** The style property {@code MozBackgroundOrigin}. */
        MOZ_BACKGROUND_ORIGIN("MozBackgroundOrigin", "-moz-background-origin"),

        /** The style property {@code MozBackgroundSize}. */
        MOZ_BACKGROUND_SIZE("MozBackgroundSize", "-moz-background-size"),

        /** The style property {@code MozBinding}. */
        MOZ_BINDING("MozBinding", "-moz-binding", BrowserConfiguration.ff("none")),

        /** The style property {@code -moz-binding}. */
        MOZ_BINDING__("-moz-binding", "-moz-binding", BrowserConfiguration.ff("none")),

        /** The style property {@code MozBorderBottomColors}. */
        MOZ_BORDER_BOTTOM_COLORS("MozBorderBottomColors", "-moz-border-bottom-colors",
                BrowserConfiguration.ffBelow60("none"), BrowserConfiguration.ff60up("none").setIteratable(false)),

        /** The style property {@code -moz-border-bottom-colors}. */
        MOZ_BORDER_BOTTOM_COLORS__("-moz-border-bottom-colors", "-moz-border-bottom-colors",
                BrowserConfiguration.ffBelow60("none"), BrowserConfiguration.ff60up("none").setIteratable(false)),

        /** The style property {@code MozBorderEnd}. */
        MOZ_BORDER_END("MozBorderEnd", "-moz-border-end", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-border-end}. */
        MOZ_BORDER_END__("-moz-border-end", "-moz-border-end", BrowserConfiguration.ff("")),

        /** The style property {@code MozBorderEndColor}. */
        MOZ_BORDER_END_COLOR("MozBorderEndColor", "-moz-border-end-color", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-border-end-color}. */
        MOZ_BORDER_END_COLOR__("-moz-border-end-color", "-moz-border-end-color", BrowserConfiguration.ff("")),

        /** The style property {@code MozBorderEndStyle}. */
        MOZ_BORDER_END_STYLE("MozBorderEndStyle", "-moz-border-end-style", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-border-end-style}. */
        MOZ_BORDER_END_STYLE__("-moz-border-end-style", "-moz-border-end-style", BrowserConfiguration.ff("")),

        /** The style property {@code MozBorderEndWidth}. */
        MOZ_BORDER_END_WIDTH("MozBorderEndWidth", "-moz-border-end-width", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-border-end-width}. */
        MOZ_BORDER_END_WIDTH__("-moz-border-end-width", "-moz-border-end-width", BrowserConfiguration.ff("")),

        /** The style property {@code MozBorderImage}. */
        MOZ_BORDER_IMAGE("MozBorderImage", "-moz-border-image", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-border-image}. */
        MOZ_BORDER_IMAGE__("-moz-border-image", "-moz-border-image", BrowserConfiguration.ff("")),

        /** The style property {@code MozBorderLeftColors}. */
        MOZ_BORDER_LEFT_COLORS("MozBorderLeftColors", "-moz-border-left-colors",
                BrowserConfiguration.ffBelow60("none"), BrowserConfiguration.ff60up("none").setIteratable(false)),

        /** The style property {@code -moz-border-left-colors}. */
        MOZ_BORDER_LEFT_COLORS__("-moz-border-left-colors", "-moz-border-left-colors",
                BrowserConfiguration.ffBelow60("none"), BrowserConfiguration.ff60up("none").setIteratable(false)),

        /** The style property {@code MozBorderRadius}. */
        MOZ_BORDER_RADIUS("MozBorderRadius", "-moz-border-radius"),

        /** The style property {@code MozBorderRadiusBottomleft}. */
        MOZ_BORDER_RADIUS_BOTTOMLEFT("MozBorderRadiusBottomleft", "-moz-border-radius-bottomleft"),

        /** The style property {@code MozBorderRadiusBottomright}. */
        MOZ_BORDER_RADIUS_BOTTOMRIGHT("MozBorderRadiusBottomright", "-moz-border-radius-bottomright"),

        /** The style property {@code MozBorderRadiusTopleft}. */
        MOZ_BORDER_RADIUS_TOPLEFT("MozBorderRadiusTopleft", "-moz-border-radius-topleft"),

        /** The style property {@code MozBorderRadiusTopright}. */
        MOZ_BORDER_RADIUS_TOPRIGHT("MozBorderRadiusTopright", "-moz-border-radius-topright"),

        /** The style property {@code MozBorderRightColors}. */
        MOZ_BORDER_RIGHT_COLORS("MozBorderRightColors", "-moz-border-right-colors",
                BrowserConfiguration.ffBelow60("none"), BrowserConfiguration.ff60up("none").setIteratable(false)),

        /** The style property {@code -moz-border-right-colors}. */
        MOZ_BORDER_RIGHT_COLORS__("-moz-border-right-colors", "-moz-border-right-colors",
                BrowserConfiguration.ffBelow60("none"), BrowserConfiguration.ff60up("none").setIteratable(false)),

        /** The style property {@code MozBorderStart}. */
        MOZ_BORDER_START("MozBorderStart", "-moz-border-start", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-border-start}. */
        MOZ_BORDER_START__("-moz-border-start", "-moz-border-start", BrowserConfiguration.ff("")),

        /** The style property {@code MozBorderStartColor}. */
        MOZ_BORDER_START_COLOR("MozBorderStartColor", "-moz-border-start-color", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-border-start-color}. */
        MOZ_BORDER_START_COLOR__("-moz-border-start-color", "-moz-border-start-color", BrowserConfiguration.ff("")),

        /** The style property {@code MozBorderStartStyle}. */
        MOZ_BORDER_START_STYLE("MozBorderStartStyle", "-moz-border-start-style", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-border-start-style}. */
        MOZ_BORDER_START_STYLE__("-moz-border-start-style", "-moz-border-start-style", BrowserConfiguration.ff("")),

        /** The style property {@code MozBorderStartWidth}. */
        MOZ_BORDER_START_WIDTH("MozBorderStartWidth", "-moz-border-start-width", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-border-start-width}. */
        MOZ_BORDER_START_WIDTH__("-moz-border-start-width", "-moz-border-start-width", BrowserConfiguration.ff("")),

        /** The style property {@code MozBorderTopColors}. */
        MOZ_BORDER_TOP_COLORS("MozBorderTopColors", "-moz-border-top-colors",
                BrowserConfiguration.ffBelow60("none"), BrowserConfiguration.ff60up("none").setIteratable(false)),

        /** The style property {@code -moz-border-top-colors}. */
        MOZ_BORDER_TOP_COLORS__("-moz-border-top-colors", "-moz-border-top-colors",
                BrowserConfiguration.ffBelow60("none"), BrowserConfiguration.ff60up("none").setIteratable(false)),

        /** The style property {@code MozBoxAlign}. */
        MOZ_BOX_ALIGN("MozBoxAlign", "-moz-box-align", BrowserConfiguration.ff("stretch")),

        /** The style property {@code -moz-box-align}. */
        MOZ_BOX_ALIGN__("-moz-box-align", "-moz-box-align", BrowserConfiguration.ff("stretch")),

        /** The style property {@code MozBoxDirection}. */
        MOZ_BOX_DIRECTION("MozBoxDirection", "-moz-box-direction", BrowserConfiguration.ff("normal")),

        /** The style property {@code -moz-box-direction}. */
        MOZ_BOX_DIRECTION__("-moz-box-direction", "-moz-box-direction", BrowserConfiguration.ff("normal")),

        /** The style property {@code MozBoxFlex}. */
        MOZ_BOX_FLEX("MozBoxFlex", "-moz-box-flex", BrowserConfiguration.ff("0")),

        /** The style property {@code -moz-box-flex}. */
        MOZ_BOX_FLEX__("-moz-box-flex", "-moz-box-flex", BrowserConfiguration.ff("0")),

        /** The style property {@code MozBoxOrdinalGroup}. */
        MOZ_BOX_ORDINAL_GROUP("MozBoxOrdinalGroup", "-moz-box-ordinal-group", BrowserConfiguration.ff("1")),

        /** The style property {@code -moz-box-ordinal-group}. */
        MOZ_BOX_ORDINAL_GROUP__("-moz-box-ordinal-group", "-moz-box-ordinal-group", BrowserConfiguration.ff("1")),

        /** The style property {@code MozBoxOrient}. */
        MOZ_BOX_ORIENT("MozBoxOrient", "-moz-box-orient", BrowserConfiguration.ff("horizontal")),

        /** The style property {@code -moz-box-orient}. */
        MOZ_BOX_ORIENT__("-moz-box-orient", "-moz-box-orient", BrowserConfiguration.ff("horizontal")),

        /** The style property {@code MozBoxPack}. */
        MOZ_BOX_PACK("MozBoxPack", "-moz-box-pack", BrowserConfiguration.ff("start")),

        /** The style property {@code -moz-box-pack}. */
        MOZ_BOX_PACK__("-moz-box-pack", "-moz-box-pack", BrowserConfiguration.ff("start")),

        /** The style property {@code MozBoxShadow}. */
        MOZ_BOX_SHADOW("MozBoxShadow", "-moz-box-shadow"),

        /** The style property {@code MozBoxSizing}. */
        MOZ_BOX_SIZING("MozBoxSizing", "-moz-box-sizing", BrowserConfiguration.ff("content-box")),

        /** The style property {@code -moz-box-sizing}. */
        MOZ_BOX_SIZING__("-moz-box-sizing", "-moz-box-sizing", BrowserConfiguration.ff("content-box")),

        /** The style property {@code MozColumnCount}. */
        MOZ_COLUMN_COUNT("MozColumnCount", "-moz-column-count", BrowserConfiguration.ff("auto")),

        /** The style property {@code -moz-column-count}. */
        MOZ_COLUMN_COUNT__("-moz-column-count", "-moz-column-count", BrowserConfiguration.ff("auto")),

        /** The style property {@code MozColumnFill}. */
        MOZ_COLUMN_FILL("MozColumnFill", "-moz-column-fill", BrowserConfiguration.ff("balance")),

        /** The style property {@code -moz-column-fill}. */
        MOZ_COLUMN_FILL__("-moz-column-fill", "-moz-column-fill", BrowserConfiguration.ff("balance")),

        /** The style property {@code MozColumnGap}. */
        MOZ_COLUMN_GAP("MozColumnGap", "-moz-column-gap", BrowserConfiguration.ffBelow60("16px"), BrowserConfiguration.ff60up("normal")),

        /** The style property {@code -moz-column-gap}. */
        MOZ_COLUMN_GAP__("-moz-column-gap", "-moz-column-gap", BrowserConfiguration.ffBelow60("16px"), BrowserConfiguration.ff60up("normal")),

        /** The style property {@code MozColumnRule}. */
        MOZ_COLUMN_RULE("MozColumnRule", "-moz-column-rule", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-column-rule}. */
        MOZ_COLUMN_RULE__("-moz-column-rule", "-moz-column-rule", BrowserConfiguration.ff("")),

        /** The style property {@code MozColumnRuleColor}. */
        MOZ_COLUMN_RULE_COLOR("MozColumnRuleColor", "-moz-column-rule-color",
                BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code -moz-column-rule-color}. */
        MOZ_COLUMN_RULE_COLOR__("-moz-column-rule-color", "-moz-column-rule-color",
                BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code MozColumnRuleStyle}. */
        MOZ_COLUMN_RULE_STYLE("MozColumnRuleStyle", "-moz-column-rule-style",
                BrowserConfiguration.ff("none")),

        /** The style property {@code -moz-column-rule-style}. */
        MOZ_COLUMN_RULE_STYLE__("-moz-column-rule-style", "-moz-column-rule-style",
                BrowserConfiguration.ff("none")),

        /** The style property {@code MozColumnRuleWidth}. */
        MOZ_COLUMN_RULE_WIDTH("MozColumnRuleWidth", "-moz-column-rule-width",
                BrowserConfiguration.ff("0px")),

        /** The style property {@code -moz-column-rule-width}. */
        MOZ_COLUMN_RULE_WIDTH__("-moz-column-rule-width", "-moz-column-rule-width",
                BrowserConfiguration.ff("0px")),

        /** The style property {@code MozColumnWidth}. */
        MOZ_COLUMN_WIDTH("MozColumnWidth", "-moz-column-width", BrowserConfiguration.ff("auto")),

        /** The style property {@code -moz-column-width}. */
        MOZ_COLUMN_WIDTH__("-moz-column-width", "-moz-column-width", BrowserConfiguration.ff("auto")),

        /** The style property {@code MozColumns}. */
        MOZ_COLUMNS("MozColumns", "-moz-columns", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-columns}. */
        MOZ_COLUMNS__("-moz-columns", "-moz-columns", BrowserConfiguration.ff("")),

        /** The style property {@code MozFloatEdge}. */
        MOZ_FLOAT_EDGE("MozFloatEdge", "-moz-float-edge", BrowserConfiguration.ff("content-box")),

        /** The style property {@code -moz-float-edge}. */
        MOZ_FLOAT_EDGE__("-moz-float-edge", "-moz-float-edge", BrowserConfiguration.ff("content-box")),

        /** The style property {@code MozFontFeatureSettings}. */
        MOZ_FONT_FEATURE_SETTINGS("MozFontFeatureSettings", "-moz-font-feature-settings",
                BrowserConfiguration.ff("normal")),

        /** The style property {@code -moz-font-feature-settings}. */
        MOZ_FONT_FEATURE_SETTINGS__("-moz-font-feature-settings", "-moz-font-feature-settings",
                BrowserConfiguration.ff("normal")),

        /** The style property {@code MozFontLanguageOverride}. */
        MOZ_FONT_LANGUAGE_OVERRIDE("MozFontLanguageOverride", "-moz-font-language-override",
                BrowserConfiguration.ff("normal")),

        /** The style property {@code -moz-font-language-override}. */
        MOZ_FONT_LANGUAGE_OVERRIDE__("-moz-font-language-override", "-moz-font-language-override",
                BrowserConfiguration.ff("normal")),

        /** The style property {@code MozForceBrokenImageIcon}. */
        MOZ_FORCE_BROKEN_IMAGE_ICON("MozForceBrokenImageIcon", "-moz-force-broken-image-icon",
                BrowserConfiguration.ff("0")),

        /** The style property {@code -moz-force-broken-image-icon}. */
        MOZ_FORCE_BROKEN_IMAGE_ICON__("-moz-force-broken-image-icon", "-moz-force-broken-image-icon",
                BrowserConfiguration.ff("0")),

        /** The style property {@code MozHyphens}. */
        MOZ_HYPHENS("MozHyphens", "-moz-hyphens", BrowserConfiguration.ff("manual")),

        /** The style property {@code -moz-hyphens}. */
        MOZ_HYPHENS__("-moz-hyphens", "-moz-hyphens", BrowserConfiguration.ff("manual")),

        /** The style property {@code MozImageRegion}. */
        MOZ_IMAGE_REGION("MozImageRegion", "-moz-image-region", BrowserConfiguration.ff("auto")),

        /** The style property {@code -moz-image-region}. */
        MOZ_IMAGE_REGION__("-moz-image-region", "-moz-image-region", BrowserConfiguration.ff("auto")),

        /** The style property {@code MozMarginEnd}. */
        MOZ_MARGIN_END("MozMarginEnd", "-moz-margin-end", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-margin-end}. */
        MOZ_MARGIN_END__("-moz-margin-end", "-moz-margin-end", BrowserConfiguration.ff("")),

        /** The style property {@code MozMarginStart}. */
        MOZ_MARGIN_START("MozMarginStart", "-moz-margin-start", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-margin-start}. */
        MOZ_MARGIN_START__("-moz-margin-start", "-moz-margin-start", BrowserConfiguration.ff("")),

        /** The style property {@code MozOpacity}. */
        MOZ_OPACITY("MozOpacity", "-moz-opacity"),

        /** The style property {@code MozOrient}. */
        MOZ_ORIENT("MozOrient", "-moz-orient", BrowserConfiguration.ff("inline")),

        /** The style property {@code -moz-orient}. */
        MOZ_ORIENT__("-moz-orient", "-moz-orient", BrowserConfiguration.ff("inline")),

        /** The style property {@code MozOutline}. */
        MOZ_OUTLINE("MozOutline", "-moz-outline"),

        /** The style property {@code MozOutlineColor}. */
        MOZ_OUTLINE_COLOR("MozOutlineColor", "-moz-outline-color"),

        /** The style property {@code MozOutlineOffset}. */
        MOZ_OUTLINE_OFFSET("MozOutlineOffset", "-moz-outline-offset"),

        /** The style property {@code MozOutlineRadius}. */
        MOZ_OUTLINE_RADIUS("MozOutlineRadius", "-moz-outline-radius", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-outline-radius}. */
        MOZ_OUTLINE_RADIUS__("-moz-outline-radius", "-moz-outline-radius", BrowserConfiguration.ff("")),

        /** The style property {@code MozOutlineRadiusBottomleft}. */
        MOZ_OUTLINE_RADIUS_BOTTOMLEFT("MozOutlineRadiusBottomleft",
                "-moz-outline-radius-bottomleft", BrowserConfiguration.ff("0px")),

        /** The style property {@code -moz-outline-radius-bottomleft}. */
        MOZ_OUTLINE_RADIUS_BOTTOMLEFT__("-moz-outline-radius-bottomleft",
                "-moz-outline-radius-bottomleft", BrowserConfiguration.ff("0px")),

        /** The style property {@code MozOutlineRadiusBottomright}. */
        MOZ_OUTLINE_RADIUS_BOTTOMRIGHT("MozOutlineRadiusBottomright",
                "-moz-outline-radius-bottomright", BrowserConfiguration.ff("0px")),

        /** The style property {@code -moz-outline-radius-bottomright}. */
        MOZ_OUTLINE_RADIUS_BOTTOMRIGHT__("-moz-outline-radius-bottomright",
                "-moz-outline-radius-bottomright", BrowserConfiguration.ff("0px")),

        /** The style property {@code MozOutlineRadiusTopleft}. */
        MOZ_OUTLINE_RADIUS_TOPLEFT("MozOutlineRadiusTopleft",
                "-moz-outline-radius-topleft", BrowserConfiguration.ff("0px")),

        /** The style property {@code -moz-outline-radius-topleft}. */
        MOZ_OUTLINE_RADIUS_TOPLEFT__("-moz-outline-radius-topleft",
                "-moz-outline-radius-topleft", BrowserConfiguration.ff("0px")),

        /** The style property {@code MozOutlineRadiusTopright}. */
        MOZ_OUTLINE_RADIUS_TOPRIGHT("MozOutlineRadiusTopright",
                "-moz-outline-radius-topright", BrowserConfiguration.ff("0px")),

        /** The style property {@code -moz-outline-radius-topright}. */
        MOZ_OUTLINE_RADIUS_TOPRIGHT__("-moz-outline-radius-topright",
                "-moz-outline-radius-topright", BrowserConfiguration.ff("0px")),

        /** The style property {@code MozOutlineStyle}. */
        MOZ_OUTLINE_STYLE("MozOutlineStyle", "-moz-outline-style"),

        /** The style property {@code MozOutlineWidth}. */
        MOZ_OUTLINE_WIDTH("MozOutlineWidth", "-moz-outline-width"),

        /** The style property {@code MozPaddingEnd}. */
        MOZ_PADDING_END("MozPaddingEnd", "-moz-padding-end", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-padding-end}. */
        MOZ_PADDING_END__("-moz-padding-end", "-moz-padding-end", BrowserConfiguration.ff("")),

        /** The style property {@code MozPaddingStart}. */
        MOZ_PADDING_START("MozPaddingStart", "-moz-padding-start", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-padding-start}. */
        MOZ_PADDING_START__("-moz-padding-start", "-moz-padding-start", BrowserConfiguration.ff("")),

        /** The style property {@code MozPerspective}. */
        MOZ_PERSPECTIVE("MozPerspective", "-moz-perspective", BrowserConfiguration.ff("none")),

        /** The style property {@code -moz-perspective}. */
        MOZ_PERSPECTIVE__("-moz-perspective", "-moz-perspective", BrowserConfiguration.ff("none")),

        /** The style property {@code MozPerspectiveOrigin}. */
        MOZ_PERSPECTIVE_ORIGIN("MozPerspectiveOrigin",
                "-moz-perspective-origin", BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code -moz-perspective-origin}. */
        MOZ_PERSPECTIVE_ORIGIN__("-moz-perspective-origin",
                "-moz-perspective-origin", BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code MozStackSizing}. */
        MOZ_STACK_SIZING("MozStackSizing", "-moz-stack-sizing", BrowserConfiguration.ff("stretch-to-fit")),

        /** The style property {@code -moz-stack-sizing}. */
        MOZ_STACK_SIZING__("-moz-stack-sizing", "-moz-stack-sizing", BrowserConfiguration.ff("stretch-to-fit")),

        /** The style property {@code MozTabSize}. */
        MOZ_TAB_SIZE("MozTabSize", "-moz-tab-size", BrowserConfiguration.ff("8")),

        /** The style property {@code -moz-tab-size}. */
        MOZ_TAB_SIZE__("-moz-tab-size", "-moz-tab-size", BrowserConfiguration.ff("8")),

        /** The style property {@code MozTextAlignLast}. */
        MOZ_TEXT_ALIGN_LAST("MozTextAlignLast", "-moz-text-align-last",
                BrowserConfiguration.ffBelow60("auto"), BrowserConfiguration.ff60up("auto").setIteratable(false)),

        /** The style property {@code -moz-text-align-last}. */
        MOZ_TEXT_ALIGN_LAST__("-moz-text-align-last", "-moz-text-align-last",
                BrowserConfiguration.ffBelow60("auto"), BrowserConfiguration.ff60up("auto").setIteratable(false)),

        /** The style property {@code MozTextSizeAdjust}. */
        MOZ_TEXT_SIZE_ADJUST("MozTextSizeAdjust", "-moz-text-size-adjust",
                BrowserConfiguration.ff("auto")),

        /** The style property {@code -moz-text-size-adjust}. */
        MOZ_TEXT_SIZE_ADJUST__("-moz-text-size-adjust", "-moz-text-size-adjust",
                BrowserConfiguration.ff("auto")),

        /** The style property {@code MozTransform}. */
        MOZ_TRANSFORM("MozTransform", "-moz-transform", BrowserConfiguration.ff("none")),

        /** The style property {@code -moz-transform}. */
        MOZ_TRANSFORM__("-moz-transform", "-moz-transform", BrowserConfiguration.ff("none")),

        /** The style property {@code MozTransformOrigin}. */
        MOZ_TRANSFORM_ORIGIN("MozTransformOrigin", "-moz-transform-origin",
                BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code -moz-transform-origin}. */
        MOZ_TRANSFORM_ORIGIN__("-moz-transform-origin", "-moz-transform-origin",
                BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code MozTransformStyle}. */
        MOZ_TRANSFORM_STYLE("MozTransformStyle", "-moz-transform-style",
                BrowserConfiguration.ff("flat")),

        /** The style property {@code -moz-transform-style}. */
        MOZ_TRANSFORM_STYLE__("-moz-transform-style", "-moz-transform-style",
                BrowserConfiguration.ff("flat")),

        /** The style property {@code MozTransition}. */
        MOZ_TRANSITION("MozTransition", "-moz-transition", BrowserConfiguration.ff("")),

        /** The style property {@code -moz-transition}. */
        MOZ_TRANSITION__("-moz-transition", "-moz-transition", BrowserConfiguration.ff("")),

        /** The style property {@code MozTransitionDelay}. */
        MOZ_TRANSITION_DELAY("MozTransitionDelay", "-moz-transition-delay",
                BrowserConfiguration.ff("0s")),

        /** The style property {@code -moz-transition-delay}. */
        MOZ_TRANSITION_DELAY__("-moz-transition-delay", "-moz-transition-delay",
                BrowserConfiguration.ff("0s")),

        /** The style property {@code MozTransitionDuration}. */
        MOZ_TRANSITION_DURATION("MozTransitionDuration", "-moz-transition-duration",
                BrowserConfiguration.ff("0s")),

        /** The style property {@code -moz-transition-duration}. */
        MOZ_TRANSITION_DURATION__("-moz-transition-duration", "-moz-transition-duration",
                BrowserConfiguration.ff("0s")),

        /** The style property {@code MozTransitionProperty}. */
        MOZ_TRANSITION_PROPERTY("MozTransitionProperty", "-moz-transition-property",
                BrowserConfiguration.ff("all")),

        /** The style property {@code -moz-transition-property}. */
        MOZ_TRANSITION_PROPERTY__("-moz-transition-property", "-moz-transition-property",
                BrowserConfiguration.ff("all")),

        /** The style property {@code MozTransitionTimingFunction}. */
        MOZ_TRANSITION_TIMING_FUNCTION("MozTransitionTimingFunction",
                "-moz-transition-timing-function", BrowserConfiguration.ff("ease")),

        /** The style property {@code -moz-transition-timing-function}. */
        MOZ_TRANSITION_TIMING_FUNCTION__("-moz-transition-timing-function",
                "-moz-transition-timing-function", BrowserConfiguration.ff("ease")),

        /** The style property {@code MozUserFocus}. */
        MOZ_USER_FOCUS("MozUserFocus", "-moz-user-focus", BrowserConfiguration.ff("none")),

        /** The style property {@code -moz-user-focus}. */
        MOZ_USER_FOCUS__("-moz-user-focus", "-moz-user-focus", BrowserConfiguration.ff("none")),

        /** The style property {@code MozUserInput}. */
        MOZ_USER_INPUT("MozUserInput", "-moz-user-input", BrowserConfiguration.ff("auto")),

        /** The style property {@code -moz-user-input}. */
        MOZ_USER_INPUT__("-moz-user-input", "-moz-user-input", BrowserConfiguration.ff("auto")),

        /** The style property {@code MozUserModify}. */
        MOZ_USER_MODIFY("MozUserModify", "-moz-user-modify", BrowserConfiguration.ff("read-only")),

        /** The style property {@code -moz-user-modify}. */
        MOZ_USER_MODIFY__("-moz-user-modify", "-moz-user-modify", BrowserConfiguration.ff("read-only")),

        /** The style property {@code MozUserSelect}. */
        MOZ_USER_SELECT("MozUserSelect", "-moz-user-select", BrowserConfiguration.ff("auto")),

        /** The style property {@code -moz-user-select}. */
        MOZ_USER_SELECT__("-moz-user-select", "-moz-user-select", BrowserConfiguration.ff("auto")),

        /** The style property {@code MozWindowDragging}. */
        MOZ_WINDOW_DRAGGING("MozWindowDragging", "-moz-window-dragging", BrowserConfiguration.ff("default")),

        /** The style property {@code -moz-window-dragging}. */
        MOZ_WINDOW_DRAGGING__("-moz-window-dragging", "-moz-window-dragging", BrowserConfiguration.ff("default")),

        /** The style property {@code msAnimation}. */
        MS_ANIMATION("msAnimation", "-ms-animation", BrowserConfiguration.ie("")),

        /** The style property {@code msAnimationDelay}. */
        MS_ANIMATION_DELAY("msAnimationDelay", "-ms-animation-delay",
                BrowserConfiguration.ie("0s")),

        /** The style property {@code msAnimationDirection}. */
        MS_ANIMATION_DIRECTION("msAnimationDirection",
                "-ms-animation-direction", BrowserConfiguration.ie("normal")),

        /** The style property {@code msAnimationDuration}. */
        MS_ANIMATION_DURATION("msAnimationDuration",
                "-ms-animation-duration", BrowserConfiguration.ie("0s")),

        /** The style property {@code msAnimationFillMode}. */
        MS_ANIMATION_FILL_MODE("msAnimationFillMode",
                "-ms-animation-fill-mode", BrowserConfiguration.ie("none")),

        /** The style property {@code msAnimationIterationCount}. */
        MS_ANIMATION_ITERATION_COUNT("msAnimationIterationCount",
                "-ms-animation-iteration-count", BrowserConfiguration.ie("1")),

        /** The style property {@code msAnimationName}. */
        MS_ANIMATION_NAME("msAnimationName", "-ms-annimation-name",
                BrowserConfiguration.ie("none")),

        /** The style property {@code msAnimationPlayState}. */
        MS_ANIMATION_PLAY_STATE("msAnimationPlayState",
                "-ms-animation-play-state", BrowserConfiguration.ie("running")),

        /** The style property {@code msAnimationTimingFunction}. */
        MS_ANIMATION_TIMING_FUNCTION("msAnimationTimingFunction",
                "-ms-animation-timing-function",
                BrowserConfiguration.ie("cubic-bezier(0.25, 0.1, 0.25, 1)")),

        /** The style property {@code msBackfaceVisibility}. */
        MS_BACKFACE_VISIBILITY("msBackfaceVisibility",
                "-ms-backface-visibility", BrowserConfiguration.ie("visible")),

        /** The style property {@code msBlockProgression}. */
        MS_BLOCK_PROGRESSION("msBlockProgression", "-ms-block-progression",
                BrowserConfiguration.ie("undefined")),

        /** The style property {@code msContentZoomChaining}. */
        MS_CONTENT_ZOOM_CHAINING("msContentZoomChaining",
                "-ms-content-zoom-chaining", BrowserConfiguration.ie("none")),

        /** The style property {@code msContentZoomLimit}. */
        MS_CONTENT_ZOOM_LIMIT("msContentZoomLimit", "-ms-content-zoom-limit",
                BrowserConfiguration.ie("")),

        /** The style property {@code msContentZoomLimitMax}. */
        MS_CONTENT_ZOOM_LIMIT_MAX("msContentZoomLimitMax", "-ms-content-zoom-limit-max",
                BrowserConfiguration.ie("400%")),

        /** The style property {@code msContentZoomLimitMin}. */
        MS_CONTENT_ZOOM_LIMIT_MIN("msContentZoomLimitMin", "-ms-content-zoom-limit-min",
                BrowserConfiguration.ie("100%")),

        /** The style property {@code msContentZoomSnap}. */
        MS_CONTENT_ZOOM_SNAP("msContentZoomSnap",
                "-ms-content-zoom-snap", BrowserConfiguration.ie("none snapInterval(0%, 100%)")),

        /** The style property {@code msContentZoomSnapPoints}. */
        MS_CONTENT_ZOOM_SNAP_POINTS("msContentZoomSnapPoints",
                "-ms-content-zoom-snap-points", BrowserConfiguration.ie("snapInterval(0%, 100%)")),

        /** The style property {@code msContentZoomSnapType}. */
        MS_CONTENT_ZOOM_SNAP_TYPE("msContentZoomSnapType", "-ms-content-zoom-snap-type", BrowserConfiguration.ie("none")),

        /** The style property {@code msContentZooming}. */
        MS_CONTENT_ZOOMING("msContentZooming", "-ms-content-zooming", BrowserConfiguration.ie("none")),

        /** The style property {@code msFlex}. */
        MS_FLEX("msFlex", "-ms-flex", BrowserConfiguration.ie("0 1 auto")),

        /** The style property {@code msFlexAlign}. */
        MS_FLEX_ALIGN("msFlexAlign", "-ms-flex-align", BrowserConfiguration.ie("stretch")),

        /** The style property {@code msFlexDirection}. */
        MS_FLEX_DIRECTION("msFlexDirection", "-ms-flex-direction", BrowserConfiguration.ie("row")),

        /** The style property {@code msFlexFlow}. */
        MS_FLEX_FLOW("msFlexFlow", "-ms-flex-flow", BrowserConfiguration.ie("row nowrap")),

        /** The style property {@code msFlexItemAlign}. */
        MS_FLEX_ITEM_ALIGN("msFlexItemAlign", "-ms-flex-item-align", BrowserConfiguration.ie("auto")),

        /** The style property {@code msFlexLinePack}. */
        MS_FLEX_LINE_PACK("msFlexLinePack", "-ms-flex-line-pack", BrowserConfiguration.ie("stretch")),

        /** The style property {@code msFlexNegative}. */
        MS_FLEX_NEGATIVE("msFlexNegative", "-ms-flex-negative", BrowserConfiguration.ie("1")),

        /** The style property {@code msFlexOrder}. */
        MS_FLEX_ORDER("msFlexOrder", "-ms-flex-order", BrowserConfiguration.ie("0")),

        /** The style property {@code msFlexPack}. */
        MS_FLEX_PACK("msFlexPack", "-ms-flex-pack", BrowserConfiguration.ie("start")),

        /** The style property {@code msFlexPositive}. */
        MS_FLEX_POSITIVE("msFlexPositive", "-ms-flex-positive", BrowserConfiguration.ie("0")),

        /** The style property {@code msFlexPreferredSize}. */
        MS_FLEX_PREFERRED_SIZE("msFlexPreferredSize", "-ms-flex-preferred-size", BrowserConfiguration.ie("auto")),

        /** The style property {@code msFlexWrap}. */
        MS_FLEX_WRAP("msFlexWrap", "-ms-flex-wrap", BrowserConfiguration.ie("nowrap")),

        /** The style property {@code msFlowFrom}. */
        MS_FLOW_FROM("msFlowFrom", "-ms-flow-from", BrowserConfiguration.ie("none")),

        /** The style property {@code msFlowInto}. */
        MS_FLOW_INTO("msFlowInto", "-ms-flow-into", BrowserConfiguration.ie("none")),

        /** The style property {@code msFontFeatureSettings}. */
        MS_FONT_FEATURE_SETTINGS("msFontFeatureSettings", "-ms-font-feature-settings", BrowserConfiguration.ie("normal")),

        /** The style property {@code msGridColumn}. */
        MS_GRID_COLUMN("msGridColumn", "-ms-grid-column", BrowserConfiguration.ie("1")),

        /** The style property {@code msGridColumnAlign}. */
        MS_GRID_COLUMN_ALIGN("msGridColumnAlign", "-ms-grid-column-align", BrowserConfiguration.ie("stretch")),

        /** The style property {@code msGridColumnSpan}. */
        MS_GRID_COLUMN_SPAN("msGridColumnSpan", "-ms-grid-column-span", BrowserConfiguration.ie("1")),

        /** The style property {@code msGridColumns}. */
        MS_GRID_COLUMNS("msGridColumns", "-ms-grid-columns", BrowserConfiguration.ie("none")),

        /** The style property {@code msGridRow}. */
        MS_GRID_ROW("msGridRow", "-ms-grid-row", BrowserConfiguration.ie("1")),

        /** The style property {@code msGridRowAlign}. */
        MS_GRID_ROW_ALIGN("msGridRowAlign", "-ms-grid-row-align", BrowserConfiguration.ie("stretch")),

        /** The style property {@code msGridRowSpan}. */
        MS_GRID_ROW_SPAN("msGridRowSpan", "-ms-grid-row-span", BrowserConfiguration.ie("1")),

        /** The style property {@code msGridRows}. */
        MS_GRID_ROWS("msGridRows", "-ms-grid-rows", BrowserConfiguration.ie("none")),

        /** The style property {@code msHighContrastAdjust}. */
        MS_HIGH_CONTRAST_ADJUST("msHighContrastAdjust", "-ms-high-contrast-adjust", BrowserConfiguration.ie("auto")),

        /** The style property {@code msHyphenateLimitChars}. */
        MS_HYPHENATE_LIMIT_CHARS("msHyphenateLimitChars", "-ms-hyphenate-limit-chars", BrowserConfiguration.ie("5 2 2")),

        /** The style property {@code msHyphenateLimitLines}. */
        MS_HYPHENATE_LIMIT_LINES("msHyphenateLimitLines", "-ms-hyphenate-limit-lines", BrowserConfiguration.ie("no-limit")),

        /** The style property {@code msHyphenateLimitZone}. */
        MS_HYPHENATE_LIMIT_ZONE("msHyphenateLimitZone", "-ms-hyphenate-limit-zone", BrowserConfiguration.ie("0px")),

        /** The style property {@code msHyphens}. */
        MS_HYPHENS("msHyphens", "-ms-hyphens", BrowserConfiguration.ie("manual")),

        /** The style property {@code msImeAlign}. */
        MS_IME_ALIGN("msImeAlign", "-ms-ime-align", BrowserConfiguration.ie("")),

        /** The style property {@code msInterpolationMode}. */
        MS_INTERPOLATION_MODE("msInterpolationMode", "-ms-interpolation-mode", BrowserConfiguration.ie("undefined")),

        /** The style property {@code msOverflowStyle}. */
        MS_OVERFLOW_STYLE("msOverflowStyle", "-ms-overflow-style", BrowserConfiguration.ie("scrollbar")),

        /** The style property {@code msPerspective}. */
        MS_PERSPECTIVE("msPerspective", "-ms-perspective", BrowserConfiguration.ie("none")),

        /** The style property {@code msPerspectiveOrigin}. */
        MS_PERSPECTIVE_ORIGIN("msPerspectiveOrigin", "-ms-perspective-origin", BrowserConfiguration.ie("620px 163.2px")),

        /** The style property {@code msScrollChaining}. */
        MS_SCROLL_CHAINING("msScrollChaining", "-ms-scroll-chaining", BrowserConfiguration.ie("chained")),

        /** The style property {@code msScrollLimit}. */
        MS_SCROLL_LIMIT("msScrollLimit", "-ms-scroll-limit", BrowserConfiguration.ie("")),

        /** The style property {@code msScrollLimitXMax}. */
        MS_SCROLL_LIMIT_X_MAX("msScrollLimitXMax", "-ms-scroll-limit-x-max", BrowserConfiguration.ie("0px")),

        /** The style property {@code msScrollLimitXMin}. */
        MS_SCROLL_LIMIT_X_MIN("msScrollLimitXMin", "-ms-scroll-limit-x-min", BrowserConfiguration.ie("0px")),

        /** The style property {@code msScrollLimitYMax}. */
        MS_SCROLL_LIMIT_Y_MAX("msScrollLimitYMax", "-ms-scroll-limit-y-max", BrowserConfiguration.ie("0px")),

        /** The style property {@code msScrollLimitYMin}. */
        MS_SCROLL_LIMIT_Y_MIN("msScrollLimitYMin", "-ms-scroll-limit-y-min", BrowserConfiguration.ie("0px")),

        /** The style property {@code msScrollRails}. */
        MS_SCROLL_RAILS("msScrollRails", "-ms-scroll-rails", BrowserConfiguration.ie("railed")),

        /** The style property {@code msScrollSnapPointsX}. */
        MS_SCROLL_SNAP_POINTS_X("msScrollSnapPointsX", "-ms-scroll-snap-points-x", BrowserConfiguration.ie("snapInterval(0%, 100%)")),

        /** The style property {@code msScrollSnapPointsY}. */
        MS_SCROLL_SNAP_POINTS_Y("msScrollSnapPointsY", "-ms-scroll-snap-points-y", BrowserConfiguration.ie("snapInterval(0%, 100%)")),

        /** The style property {@code msScrollSnapType}. */
        MS_SCROLL_SNAP_TYPE("msScrollSnapType", "-ms-scroll-snap-type", BrowserConfiguration.ie("none")),

        /** The style property {@code msScrollSnapX}. */
        MS_SCROLL_SNAP_X("msScrollSnapX", "-ms-scroll-snap-x", BrowserConfiguration.ie("none snapInterval(0%, 100%)")),

        /** The style property {@code msScrollSnapY}. */
        MS_SCROLL_SNAP_Y("msScrollSnapY", "-ms-scroll-snap-y", BrowserConfiguration.ie("none snapInterval(0%, 100%)")),

        /** The style property {@code msScrollTranslation}. */
        MS_SCROLL_TRANSLATION("msScrollTranslation", "-ms-scroll-translation", BrowserConfiguration.ie("none")),

        /** The style property {@code msTextCombineHorizontal}. */
        MS_TEXT_COMBINE_HORIZONTAL("msTextCombineHorizontal", "-ms-text-combine-horizontal", BrowserConfiguration.ie("none")),

        /** The style property {@code msTouchAction}. */
        MS_TOUCH_ACTION("msTouchAction", "-ms-touch-action", BrowserConfiguration.ie("auto")),

        /** The style property {@code msTouchSelect}. */
        MS_TOUCH_SELECT("msTouchSelect", "-ms-touch-select", BrowserConfiguration.ie("")),

        /** The style property {@code msTransform}. */
        MS_TRANSFORM("msTransform", "-ms-transform", BrowserConfiguration.ie("none")),

        /** The style property {@code msTransformOrigin}. */
        MS_TRANSFORM_ORIGIN("msTransformOrigin", "-ms-transform-origin", BrowserConfiguration.ie("620px 163.2px")),

        /** The style property {@code msTransformStyle}. */
        MS_TRANSFORM_STYLE("msTransformStyle", "-ms-transform-style", BrowserConfiguration.ie("flat")),

        /** The style property {@code msTransition}. */
        MS_TRANSITION("msTransition", "-ms-transition", BrowserConfiguration.ie("")),

        /** The style property {@code msTransitionDelay}. */
        MS_TRANSITION_DELAY("msTransitionDelay", "-ms-transition-delay", BrowserConfiguration.ie("0s")),

        /** The style property {@code msTransitionDuration}. */
        MS_TRANSITION_DURATION("msTransitionDuration",
                "-ms-transition-duration", BrowserConfiguration.ie("0s")),

        /** The style property {@code msTransitionProperty}. */
        MS_TRANSITION_PROPERTY("msTransitionProperty",
                "-ms-transition-property", BrowserConfiguration.ie("all")),

        /** The style property {@code msTransitionTimingFunction}. */
        MS_TRANSITION_TIMING_FUNCTION("msTransitionTimingFunction",
                "-ms-transition-timing-function",
                BrowserConfiguration.ie("cubic-bezier(0.25, 0.1, 0.25, 1)")),

        /** The style property {@code msUserSelect}. */
        MS_USER_SELECT("msUserSelect", "-ms-user-select", BrowserConfiguration.ie("text")),

        /** The style property {@code msWrapFlow}. */
        MS_WRAP_FLOW("msWrapFlow", "-ms-wrap-flow", BrowserConfiguration.ie("auto")),

        /** The style property {@code msWrapMargin}. */
        MS_WRAP_MARGIN("msWrapMargin", "-ms-wrap-margin", BrowserConfiguration.ie("auto")),

        /** The style property {@code msWrapThrough}. */
        MS_WRAP_THROUGH("msWrapThrough", "-ms-wrap-through", BrowserConfiguration.ie("wrap")),

        /** The style property {@code objectFit}. */
        OBJECT_FIT("objectFit", "object-fit", BrowserConfiguration.ff("fill"), BrowserConfiguration.chrome("fill")),

        /** The style property {@code object-fit}. */
        OBJECT_FIT_("object-fit", "object-fit", BrowserConfiguration.ff("fill")),

        /** The style property {@code objectPosition}. */
        OBJECT_POSITION("objectPosition", "object-position", BrowserConfiguration.ff("50% 50%"), BrowserConfiguration.chrome("50% 50%")),

        /** The style property {@code object-position}. */
        OBJECT_POSITION_("object-position", "object-position", BrowserConfiguration.ff("50% 50%")),

        /** The style property {@code offset}. */
        OFFSET("offset", "offset", BrowserConfiguration.chrome("none 0px auto 0deg")),

        /** The style property {@code offsetBlockEnd}. */
        OFFSET_BLOCK_END("offsetBlockEnd", "offset-block-end", BrowserConfiguration.ff("")),

        /** The style property {@code offset-block-end}. */
        OFFSET_BLOCK_END_("offset-block-end", "offset-block-end", BrowserConfiguration.ff("")),

        /** The style property {@code offsetBlockStart}. */
        OFFSET_BLOCK_START("offsetBlockStart", "offset-block-start", BrowserConfiguration.ff("")),

        /** The style property {@code offset-block-start}. */
        OFFSET_BLOCK_START_("offset-block-start", "offset-block-start", BrowserConfiguration.ff("")),

        /** The style property {@code offsetDistance}. */
        OFFSET_DISTANCE("offsetDistance", "offsetDistance", BrowserConfiguration.chrome("0px")),

        /** The style property {@code offsetInlineEnd}. */
        OFFSET_INLINE_END("offsetInlineEnd", "offset-inline-end", BrowserConfiguration.ff("")),

        /** The style property {@code offset-inline-end}. */
        OFFSET_INLINE_END_("offset-inline-end", "offset-inline-end", BrowserConfiguration.ff("")),

        /** The style property {@code offsetInlineStart}. */
        OFFSET_INLINE_START("offsetInlineStart", "offset-inline-start", BrowserConfiguration.ff("")),

        /** The style property {@code offset-inline-start}. */
        OFFSET_INLINE_START_("offset-inline-start", "offset-inline-start", BrowserConfiguration.ff("")),

        /** The style property {@code offsetPath}. */
        OFFSET_PATH("offsetPath", "offsetPath", BrowserConfiguration.chrome("none")),

        /** The style property {@code offsetRotate}. */
        OFFSET_ROTATE("offsetRotate", "offsetRotate", BrowserConfiguration.chrome("auto 0deg")),

        /** The style property {@code opacity}. */
        OPACITY("opacity", "opacity", BrowserConfiguration.chrome("1"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code order}. */
        ORDER("order", "order", BrowserConfiguration.ff("0"), BrowserConfiguration.ie("0"), BrowserConfiguration.chrome("0")),

        /** The style property {@code orientation}. */
        ORIENTATION("orientation", "orientation", BrowserConfiguration.chrome("")),

        /** The style property {@code orphans}. */
        ORPHANS("orphans", "orphans", BrowserConfiguration.ie("2"), BrowserConfiguration.chrome("2")),

        /** The style property {@code outline}. */
        OUTLINE("outline", "outline", BrowserConfiguration.chrome("rgb(0, 0, 0) none 0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code outlineColor}. */
        OUTLINE_COLOR("outlineColor", "outline-color", BrowserConfiguration.ie("transparent"), BrowserConfiguration.chrome("rgb(0, 0, 0)"),
                BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code outline-color}. */
        OUTLINE_COLOR_("outline-color", "outline-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code outlineOffset}. */
        OUTLINE_OFFSET("outlineOffset", "outline-offset", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("0px")),

        /** The style property {@code outline-offset}. */
        OUTLINE_OFFSET_("outline-offset", "outline-offset", BrowserConfiguration.ff("0px")),

        /** The style property {@code outlineStyle}. */
        OUTLINE_STYLE("outlineStyle", "outline-style", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code outline-style}. */
        OUTLINE_STYLE_("outline-style", "outline-style", BrowserConfiguration.ff("none")),

        /** The style property {@code outlineWidth}. */
        OUTLINE_WIDTH("outlineWidth", "outline-width", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code outline-width}. */
        OUTLINE_WIDTH_("outline-width", "outline-width", BrowserConfiguration.ff("0px")),

        /** The style property {@code overflow}. */
        OVERFLOW("overflow", "overflow", BrowserConfiguration.chrome("visible"), BrowserConfiguration.ff("visible"), BrowserConfiguration.ie("visible")),

        /** The style property {@code overflowAnchor}. */
        OVERFLOW_ANCHOR("overflowAnchor", "overflow-anchor", BrowserConfiguration.chrome("auto")),

        /** The style property {@code overflowWrap}. */
        OVERFLOW_WRAP("overflowWrap", "overflow-wrap", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal")),

        /** The style property {@code overflow-wrap}. */
        OVERFLOW_WRAP_("overflow-wrap", "overflow-wrap", BrowserConfiguration.ff("normal")),

        /** The style property {@code overflowX}. */
        OVERFLOW_X("overflowX", "overflow-x", BrowserConfiguration.chrome("visible"), BrowserConfiguration.ff("visible"), BrowserConfiguration.ie("visible")),

        /** The style property {@code overflow-x}. */
        OVERFLOW_X_("overflow-x", "overflow-x", BrowserConfiguration.ff("visible")),

        /** The style property {@code overflowY}. */
        OVERFLOW_Y("overflowY", "overflow-y", BrowserConfiguration.chrome("visible"), BrowserConfiguration.ff("visible"), BrowserConfiguration.ie("visible")),

        /** The style property {@code overflow-y}. */
        OVERFLOW_Y_("overflow-y", "overflow-y", BrowserConfiguration.ff("visible")),

        /** The style property {@code overscrollBehavior}. */
        OVERSCROLL_BEHAVIOR("overscrollBehavior", "overscroll-behavior", BrowserConfiguration.chrome("auto auto"), BrowserConfiguration.ff60up("")),

        /** The style property {@code overscroll-behavior}. */
        OVERSCROLL_BEHAVIOR_("overscroll-behavior", "overscroll-behavior", BrowserConfiguration.ff60up("")),

        /** The style property {@code overscrollBehaviorX}. */
        OVERSCROLL_BEHAVIOR_X("overscrollBehaviorX", "overscroll-behavior-x", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff60up("auto")),

        /** The style property {@code overscroll-behavior-x}. */
        OVERSCROLL_BEHAVIOR_X_("overscroll-behavior-x", "overscroll-behavior-x", BrowserConfiguration.ff60up("auto")),

        /** The style property {@code overscrollBehaviorY}. */
        OVERSCROLL_BEHAVIOR_Y("overscrollBehaviorY", "overscroll-behavior-y", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff60up("auto")),

        /** The style property {@code overscroll-behavior-y}. */
        OVERSCROLL_BEHAVIOR_Y_("overscroll-behavior-y", "overscroll-behavior-y", BrowserConfiguration.ff60up("auto")),

        /** The style property {@code padding}. */
        PADDING("padding", "padding", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code paddingBlockEnd}. */
        PADDING_BLOCK_END("paddingBlockEnd", "padding-block-end", BrowserConfiguration.ff("")),

        /** The style property {@code padding-block-end}. */
        PADDING_BLOCK_END_("padding-block-end", "padding-block-end", BrowserConfiguration.ff("")),

        /** The style property {@code paddingBlockStart}. */
        PADDING_BLOCK_START("paddingBlockStart", "padding-block-start", BrowserConfiguration.ff("")),

        /** The style property {@code padding-block-start}. */
        PADDING_BLOCK_START_("padding-block-start", "padding-block-start", BrowserConfiguration.ff("")),

        /** The style property {@code paddingBottom}. */
        PADDING_BOTTOM("paddingBottom", "padding-bottom", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code padding-bottom}. */
        PADDING_BOTTOM_("padding-bottom", "padding-bottom", BrowserConfiguration.ff("0px")),

        /** The style property {@code paddingInlineEnd}. */
        PADDING_INLINE_END("paddingInlineEnd", "padding-inline-end", BrowserConfiguration.ff("")),

        /** The style property {@code padding-inline-end}. */
        PADDING_INLINE_END_("padding-inline-end", "padding-inline-end", BrowserConfiguration.ff("")),

        /** The style property {@code paddingInlineStart}. */
        PADDING_INLINE_START("paddingInlineStart", "padding-inline-start", BrowserConfiguration.ff("")),

        /** The style property {@code padding-inline-start}. */
        PADDING_INLINE_START_("padding-inline-start", "padding-inline-start", BrowserConfiguration.ff("")),

        /** The style property {@code paddingLeft}. */
        PADDING_LEFT("paddingLeft", "padding-left", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code padding-left}. */
        PADDING_LEFT_("padding-left", "padding-left", BrowserConfiguration.ff("0px")),

        /** The style property {@code paddingRight}. */
        PADDING_RIGHT("paddingRight", "padding-right", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code padding-right}. */
        PADDING_RIGHT_("padding-right", "padding-right", BrowserConfiguration.ff("0px")),

        /** The style property {@code paddingTop}. */
        PADDING_TOP("paddingTop", "padding-top", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code padding-top}. */
        PADDING_TOP_("padding-top", "padding-top", BrowserConfiguration.ff("0px")),

        /** The style property {@code page}. */
        PAGE("page", "page", BrowserConfiguration.chrome("")),

        /** The style property {@code pageBreakAfter}. */
        PAGE_BREAK_AFTER("pageBreakAfter", "page-break-after", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code page-break-after}. */
        PAGE_BREAK_AFTER_("page-break-after", "page-break-after", BrowserConfiguration.ff("auto")),

        /** The style property {@code pageBreakBefore}. */
        PAGE_BREAK_BEFORE("pageBreakBefore", "page-break-before", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code page-break-before}. */
        PAGE_BREAK_BEFORE_("page-break-before", "page-break-before", BrowserConfiguration.ff("auto")),

        /** The style property {@code pageBreakInside}. */
        PAGE_BREAK_INSIDE("pageBreakInside", "page-break-inside", BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code page-break-inside}. */
        PAGE_BREAK_INSIDE_("page-break-inside", "page-break-inside", BrowserConfiguration.ff("auto")),

        /** The style property {@code paintOrder}. */
        PAINT_ORDER("paintOrder", "paint-order", BrowserConfiguration.ff("normal"), BrowserConfiguration.chrome("fill stroke markers")),

        /** The style property {@code paint-order}. */
        PAINT_ORDER_("paint-order", "paint-order", BrowserConfiguration.ff("normal")),

        /** The style property {@code pause}. */
        PAUSE("pause", "pause"),

        /** The style property {@code pauseAfter}. */
        PAUSE_AFTER("pauseAfter", "pause-after"),

        /** The style property {@code pauseBefore}. */
        PAUSE_BEFORE("pauseBefore", "pause-before"),

        /** The style property {@code perspective}. */
        PERSPECTIVE("perspective", "perspective", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code perspectiveOrigin}. */
        PERSPECTIVE_ORIGIN("perspectiveOrigin", "perspective-origin",
                BrowserConfiguration.ff("625px 172.5px"), BrowserConfiguration.ie("620px 163.2px"), BrowserConfiguration.chrome("456.5px 161px")),

        /** The style property {@code perspective-origin}. */
        PERSPECTIVE_ORIGIN_("perspective-origin", "perspective-origin",
                BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code pitch}. */
        PITCH("pitch", "pitch"),

        /** The style property {@code pitchRange}. */
        PITCH_RANGE("pitchRange", "pitch-range"),

        /** The style property {@code pixelBottom}. */
        PIXEL_BOTTOM("pixelBottom", "pixel-bottom", BrowserConfiguration.ie("")),

        /** The style property {@code pixelHeight}. */
        PIXEL_HEIGHT("pixelHeight", "pixel-height", BrowserConfiguration.ie("")),

        /** The style property {@code pixelLeft}. */
        PIXEL_LEFT("pixelLeft", "pixel-left", BrowserConfiguration.ie("")),

        /** The style property {@code pixelRight}. */
        PIXEL_RIGHT("pixelRight", "pixel-right", BrowserConfiguration.ie("")),

        /** The style property {@code pixelTop}. */
        PIXEL_TOP("pixelTop", "pixel-top", BrowserConfiguration.ie("")),

        /** The style property {@code pixelWidth}. */
        PIXEL_WIDTH("pixelWidth", "pixel-width", BrowserConfiguration.ie("")),

        /** The style property {@code placeContent}. */
        PLACE_CONTENT("placeContent", "place-content", BrowserConfiguration.chrome("normal normal"), BrowserConfiguration.ff("")),

        /** The style property {@code place-content}. */
        PLACE_CONTENT_("place-content", "place-content", BrowserConfiguration.ff("")),

        /** The style property {@code placeItems}. */
        PLACE_ITEMS("placeItems", "place-items", BrowserConfiguration.chrome("normal normal"), BrowserConfiguration.ff("")),

        /** The style property {@code place-items}. */
        PLACE_ITEMS_("place-items", "place-items", BrowserConfiguration.ff("")),

        /** The style property {@code placeSelf}. */
        PLACE_SELF("placeSelf", "place-self", BrowserConfiguration.chrome("auto auto"), BrowserConfiguration.ff("")),

        /** The style property {@code place-self}. */
        PLACE_SELF_("place-self", "place-self", BrowserConfiguration.ff("")),

        /** The style property {@code pointerEvents}. */
        POINTER_EVENTS("pointerEvents", "pointer-events", BrowserConfiguration.ie("visiblePainted"), BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code pointer-events}. */
        POINTER_EVENTS_("pointer-events", "pointer-events", BrowserConfiguration.ff("auto")),

        /** The style property {@code posBottom}. */
        POS_BOTTOM("posBottom", "pos-bottom", BrowserConfiguration.ie("")),

        /** The style property {@code posHeight}. */
        POS_HEIGHT("posHeight", "pos-height", BrowserConfiguration.ie("")),

        /** The style property {@code posLeft}. */
        POS_LEFT("posLeft", "pos-left", BrowserConfiguration.ie("")),

        /** The style property {@code posRight}. */
        POS_RIGHT("posRight", "pos-right", BrowserConfiguration.ie("")),

        /** The style property {@code posTop}. */
        POS_TOP("posTop", "pos-top", BrowserConfiguration.ie("")),

        /** The style property {@code posWidth}. */
        POS_WIDTH("posWidth", "pos-width", BrowserConfiguration.ie("")),

        /** The style property {@code position}. */
        POSITION("position", "position", BrowserConfiguration.chrome("static"), BrowserConfiguration.ff("static"), BrowserConfiguration.ie("static")),

        /** The style property {@code quotes}. */
        QUOTES("quotes", "quotes", BrowserConfiguration.ff("\"“\" \"”\" \"‘\" \"’\""), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("")),

        /** The style property {@code r}. */
        R("r", "r", BrowserConfiguration.chrome("0px")),

        /** The style property {@code resize}. */
        RESIZE("resize", "resize", BrowserConfiguration.ff("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code richness}. */
        RICHNESS("richness", "richness"),

        /** The style property {@code right}. */
        RIGHT("right", "right", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code rowGap}. */
        ROW_GAP("rowGap", "rowGap", BrowserConfiguration.chrome("normal")),

        /** The style property {@code rubyAlign}. */
        RUBY_ALIGN("rubyAlign", "ruby-align", BrowserConfiguration.ff("space-around"), BrowserConfiguration.ie("")),

        /** The style property {@code ruby-align}. */
        RUBY_ALIGN_("ruby-align", "ruby-align", BrowserConfiguration.ff("space-around")),

        /** The style property {@code rubyOverhang}. */
        RUBY_OVERHANG("rubyOverhang", "ruby-overhang", BrowserConfiguration.ie("auto")),

        /** The style property {@code rubyPosition}. */
        RUBY_POSITION("rubyPosition", "ruby-position", BrowserConfiguration.ie("above"), BrowserConfiguration.ff("over")),

        /** The style property {@code ruby-position}. */
        RUBY_POSITION_("ruby-position", "ruby-position", BrowserConfiguration.ff("over")),

        /** The style property {@code rx}. */
        RX("rx", "rx", BrowserConfiguration.chrome("auto")),

        /** The style property {@code ry}. */
        RY("ry", "ry", BrowserConfiguration.chrome("auto")),

        /** The style property {@code scrollBehavior}. */
        SCROLL_BEHAVIOR("scrollBehavior", "scroll-behavior", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code scroll-behavior}. */
        SCROLL_BEHAVIOR_("scroll-behavior", "scroll-behavior", BrowserConfiguration.ff("auto")),

        /** The style property {@code scrollSnapCoordinate}. */
        SCROLL_SNAP_COORDINATE("scrollSnapCoordinate", "scroll-snap-coordinate", BrowserConfiguration.ff("none")),

        /** The style property {@code scroll-snap-coordinate}. */
        SCROLL_SNAP_COORDINATE_("scroll-snap-coordinate", "scroll-snap-coordinate", BrowserConfiguration.ff("none")),

        /** The style property {@code scrollSnapDestination}. */
        SCROLL_SNAP_DESTINATION("scrollSnapDestination", "scroll-snap-destination", BrowserConfiguration.ff("0px 0px")),

        /** The style property {@code scroll-snap-destination}. */
        SCROLL_SNAP_DESTINATION_("scroll-snap-destination", "scroll-snap-destination", BrowserConfiguration.ff("0px 0px")),

        /** The style property {@code scrollSnapPointsX}. */
        SCROLL_SNAP_POINTS_X("scrollSnapPointsX", "scroll-snap-points-x", BrowserConfiguration.ff("none")),

        /** The style property {@code scroll-snap-points-x}. */
        SCROLL_SNAP_POINTS_X_("scroll-snap-points-x", "scroll-snap-points-x", BrowserConfiguration.ff("none")),

        /** The style property {@code scrollSnapPointsY}. */
        SCROLL_SNAP_POINTS_Y("scrollSnapPointsY", "scroll-snap-points-y", BrowserConfiguration.ff("none")),

        /** The style property {@code scroll-snap-points-y}. */
        SCROLL_SNAP_POINTS_Y_("scroll-snap-points-y", "scroll-snap-points-y", BrowserConfiguration.ff("none")),

        /** The style property {@code scrollSnapType}. */
        SCROLL_SNAP_TYPE("scrollSnapType", "scroll-snap-type", BrowserConfiguration.ff("")),

        /** The style property {@code scroll-snap-type}. */
        SCROLL_SNAP_TYPE_("scroll-snap-type", "scroll-snap-type", BrowserConfiguration.ff("")),

        /** The style property {@code scrollSnapTypeX}. */
        SCROLL_SNAP_TYPE_X("scrollSnapTypeX", "scroll-snap-type-x", BrowserConfiguration.ff("none")),

        /** The style property {@code scroll-snap-type-x}. */
        SCROLL_SNAP_TYPE_X_("scroll-snap-type-x", "scroll-snap-type-x", BrowserConfiguration.ff("none")),

        /** The style property {@code scrollSnapTypeY}. */
        SCROLL_SNAP_TYPE_Y("scrollSnapTypeY", "scroll-snap-type-y", BrowserConfiguration.ff("none")),

        /** The style property {@code scroll-snap-type-y}. */
        SCROLL_SNAP_TYPE_Y_("scroll-snap-type-y", "scroll-snap-type-y", BrowserConfiguration.ff("none")),

        /** The style property {@code scrollbar3dLightColor}. */
        SCROLLBAR_3DLIGHT_COLOR("scrollbar3dLightColor", "scrollbar-3dlight-color", BrowserConfiguration.ie("undefined")),

        /** The style property {@code scrollbarArrowColor}. */
        SCROLLBAR_ARROW_COLOR("scrollbarArrowColor", "scrollbar-arrow-color", BrowserConfiguration.ie("undefined")),

        /** The style property {@code scrollbarBaseColor}. */
        SCROLLBAR_BASE_COLOR("scrollbarBaseColor", "scrollbar-base-color", BrowserConfiguration.ie("undefined")),

        /** The style property {@code scrollbarDarkShadowColor}. */
        SCROLLBAR_DARKSHADOW_COLOR("scrollbarDarkShadowColor", "scrollbar-darkshadow-color", BrowserConfiguration.ie("undefined")),

        /** The style property {@code scrollbarFaceColor}. */
        SCROLLBAR_FACE_COLOR("scrollbarFaceColor", "scrollbar-face-color", BrowserConfiguration.ie("undefined")),

        /** The style property {@code scrollbarHighlightColor}. */
        SCROLLBAR_HIGHLIGHT_COLOR("scrollbarHighlightColor", "scrollbar-highlight-color", BrowserConfiguration.ie("undefined")),

        /** The style property {@code scrollbarShadowColor}. */
        SCROLLBAR_SHADOW_COLOR("scrollbarShadowColor", "scrollbar-shadow-color", BrowserConfiguration.ie("undefined")),

        /** The style property {@code scrollbarTrackColor}. */
        SCROLLBAR_TRACK_COLOR("scrollbarTrackColor", "scrollbar-track-color", BrowserConfiguration.ie("undefined")),

        /** The style property {@code shapeImageThreshold}. */
        SHAPE_IMAGE_THRESHOLD("shapeImageThreshold", "shape-image-threshold", BrowserConfiguration.chrome("0")),

        /** The style property {@code shapeMargin}. */
        SHAPE_MARGIN("shapeMargin", "shape-margin", BrowserConfiguration.chrome("0px")),

        /** The style property {@code shapeOutside}. */
        SHAPE_OUTSIDE("shapeOutside", "shape-outside", BrowserConfiguration.chrome("none")),

        /** The style property {@code shapeRendering}. */
        SHAPE_RENDERING("shapeRendering", "shape-rendering", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code shape-rendering}. */
        SHAPE_RENDERING_("shape-rendering", "shape-rendering", BrowserConfiguration.ff("auto")),

        /** The style property {@code size}. */
        SIZE("size", "size", BrowserConfiguration.chrome("")),

        /** The style property {@code speak}. */
        SPEAK("speak", "speak", BrowserConfiguration.chrome("normal")),

        /** The style property {@code speakHeader}. */
        SPEAK_HEADER("speakHeader", "speak-header"),

        /** The style property {@code speakNumeral}. */
        SPEAK_NUMERAL("speakNumeral", "speak-numeral"),

        /** The style property {@code speakPunctuation}. */
        SPEAK_PUNCTUATION("speakPunctuation", "speak-punctuation"),

        /** The style property {@code speechRate}. */
        SPEECH_RATE("speechRate", "speech-rate"),

        /** The style property {@code src}. */
        SRC("src", "src", BrowserConfiguration.chrome("")),

        /** The style property {@code stopColor}. */
        STOP_COLOR("stopColor", "stop-color", BrowserConfiguration.ff("rgb(0, 0, 0)"), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("rgb(0, 0, 0)")),

        /** The style property {@code stop-color}. */
        STOP_COLOR_("stop-color", "stop-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code stopOpacity}. */
        STOP_OPACITY("stopOpacity", "stop-opacity", BrowserConfiguration.ff("1"), BrowserConfiguration.ie("1"), BrowserConfiguration.chrome("1")),

        /** The style property {@code stop-opacity}. */
        STOP_OPACITY_("stop-opacity", "stop-opacity", BrowserConfiguration.ff("1")),

        /** The style property {@code stress}. */
        STRESS("stress", "stress"),

        /** The style property {@code stroke}. */
        STROKE("stroke", "stroke", BrowserConfiguration.ff("none"), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("none")),

        /** The style property {@code strokeDasharray}. */
        STROKE_DASHARRAY("strokeDasharray", "stroke-dasharray", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code stroke-dasharray}. */
        STROKE_DASHARRAY_("stroke-dasharray", "stroke-dasharray", BrowserConfiguration.ff("none")),

        /** The style property {@code strokeDashoffset}. */
        STROKE_DASHOFFSET("strokeDashoffset", "stroke-dashoffset", BrowserConfiguration.ff("0px"), BrowserConfiguration.ie("0px"), BrowserConfiguration.chrome("0px")),

        /** The style property {@code stroke-dashoffset}. */
        STROKE_DASHOFFSET_("stroke-dashoffset", "stroke-dashoffset", BrowserConfiguration.ff("0px")),

        /** The style property {@code strokeLinecap}. */
        STROKE_LINECAP("strokeLinecap", "stroke-linecap", BrowserConfiguration.ff("butt"), BrowserConfiguration.ie("butt"), BrowserConfiguration.chrome("butt")),

        /** The style property {@code stroke-linecap}. */
        STROKE_LINECAP_("stroke-linecap", "stroke-linecap", BrowserConfiguration.ff("butt")),

        /** The style property {@code strokeLinejoin}. */
        STROKE_LINEJOIN("strokeLinejoin", "stroke-linejoin", BrowserConfiguration.ff("miter"), BrowserConfiguration.ie("miter"), BrowserConfiguration.chrome("miter")),

        /** The style property {@code stroke-linejoin}. */
        STROKE_LINEJOIN_("stroke-linejoin", "stroke-linejoin", BrowserConfiguration.ff("miter")),

        /** The style property {@code strokeMiterlimit}. */
        STROKE_MITERLIMIT("strokeMiterlimit", "stroke-miterlimit", BrowserConfiguration.ff("4"), BrowserConfiguration.ie("4"), BrowserConfiguration.chrome("4")),

        /** The style property {@code stroke-miterlimit}. */
        STROKE_MITERLIMIT_("stroke-miterlimit", "stroke-miterlimit", BrowserConfiguration.ff("4")),

        /** The style property {@code strokeOpacity}. */
        STROKE_OPACITY("strokeOpacity", "stroke-opacity", BrowserConfiguration.ff("1"), BrowserConfiguration.ie("1"), BrowserConfiguration.chrome("1")),

        /** The style property {@code stroke-opacity}. */
        STROKE_OPACITY_("stroke-opacity", "stroke-opacity", BrowserConfiguration.ff("1")),

        /** The style property {@code strokeWidth}. */
        STROKE_WIDTH("strokeWidth", "stroke-width", BrowserConfiguration.ff("1px"), BrowserConfiguration.ie("0.01px"), BrowserConfiguration.chrome("1px")),

        /** The style property {@code stroke-width}. */
        STROKE_WIDTH_("stroke-width", "stroke-width", BrowserConfiguration.ff("1px")),

        /** The style property {@code styleFloat}. */
        STYLE_FLOAT("styleFloat", "style-float", BrowserConfiguration.ie("undefined")),

        /** The style property {@code tabSize}. */
        TAB_SIZE("tabSize", "tab-size", BrowserConfiguration.chrome("8")),

        /** The style property {@code tableLayout}. */
        TABLE_LAYOUT("tableLayout", "table-layout", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code table-layout}. */
        TABLE_LAYOUT_("table-layout", "table-layout", BrowserConfiguration.ff("auto")),

        /** The style property {@code textAlign}. */
        TEXT_ALIGN("textAlign", "text-align", BrowserConfiguration.ie("left"), BrowserConfiguration.chrome("start"), BrowserConfiguration.ff("start")),

        /** The style property {@code text-align}. */
        TEXT_ALIGN_("text-align", "text-align", BrowserConfiguration.ff("start")),

        /** The style property {@code textAlignLast}. */
        TEXT_ALIGN_LAST("textAlignLast", "text-align-last", BrowserConfiguration.ie("auto"), BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code text-align-last}. */
        TEXT_ALIGN_LAST_("text-align-last", "text-align-last", BrowserConfiguration.ff("auto")),

        /** The style property {@code textAnchor}. */
        TEXT_ANCHOR("textAnchor", "text-anchor", BrowserConfiguration.ff("start"), BrowserConfiguration.ie("start"), BrowserConfiguration.chrome("start")),

        /** The style property {@code text-anchor}. */
        TEXT_ANCHOR_("text-anchor", "text-anchor", BrowserConfiguration.ff("start")),

        /** The style property {@code textAutospace}. */
        TEXT_AUTOSPACE("textAutospace", "text-autospace", BrowserConfiguration.ie("undefined")),

        /** The style property {@code textCombineUpright}. */
        TEXT_COMBINE_UPRIGHT("textCombineUpright", "text-combine-upright", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code text-combine-upright}. */
        TEXT_COMBINE_UPRIGHT_("text-combine-upright", "text-combine-upright", BrowserConfiguration.ff("none")),

        /** The style property {@code textDecoration}. */
        TEXT_DECORATION("textDecoration", "text-decoration", BrowserConfiguration.chrome("none solid rgb(0, 0, 0)"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code text-decoration}. */
        TEXT_DECORATION_("text-decoration", "text-decoration", BrowserConfiguration.ff("none")),

        /** The style property {@code textDecorationBlink}. */
        TEXT_DECORATION_BLINK("textDecorationBlink", "text-decoration-blink", BrowserConfiguration.ie("false")),

        /** The style property {@code textDecorationColor}. */
        TEXT_DECORATION_COLOR("textDecorationColor", "text-decoration-color",
                BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code text-decoration-color}. */
        TEXT_DECORATION_COLOR_("text-decoration-color", "text-decoration-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code textDecorationLine}. */
        TEXT_DECORATION_LINE("textDecorationLine", "text-decoration-line", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code text-decoration-line}. */
        TEXT_DECORATION_LINE_("text-decoration-line", "text-decoration-line", BrowserConfiguration.ff("none")),

        /** The style property {@code textDecorationLineThrough}. */
        TEXT_DECORATION_LINE_THROUGH("textDecorationLineThrough", "text-decoration-line-through",
                BrowserConfiguration.ie("false")),

        /** The style property {@code textDecorationNone}. */
        TEXT_DECORATION_NONE("textDecorationNone", "text-decoration-none", BrowserConfiguration.ie("false")),

        /** The style property {@code textDecorationOverline}. */
        TEXT_DECORATION_OVERLINE("textDecorationOverline", "text-decoration-overline",
                BrowserConfiguration.ie("false")),

        /** The style property {@code textDecorationSkipInk}. */
        TEXT_DECORATION_SKIP("textDecorationSkipInk", "text-decoration-skip-ink", BrowserConfiguration.chrome("auto")),

        /** The style property {@code textDecorationStyle}. */
        TEXT_DECORATION_STYLE("textDecorationStyle", "text-decoration-style", BrowserConfiguration.chrome("solid"), BrowserConfiguration.ff("solid")),

        /** The style property {@code text-decoration-style}. */
        TEXT_DECORATION_STYLE_("text-decoration-style", "text-decoration-style", BrowserConfiguration.ff("solid")),

        /** The style property {@code textDecorationUnderline}. */
        TEXT_DECORATION_UNDERLINE("textDecorationUnderline", "text-decoration-underline",
                BrowserConfiguration.ie("false")),

        /** The style property {@code textEmphasis}. */
        TEXT_EMPHASIS("textEmphasis", "text-emphasis", BrowserConfiguration.ff("")),

        /** The style property {@code text-emphasis}. */
        TEXT_EMPHASIS_("text-emphasis", "text-emphasis", BrowserConfiguration.ff("")),

        /** The style property {@code textEmphasisColor}. */
        TEXT_EMPHASIS_COLOR("textEmphasisColor", "text-emphasis-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code text-emphasis-color}. */
        TEXT_EMPHASIS_COLOR_("text-emphasis-color", "text-emphasis-color", BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code textEmphasisPosition}. */
        TEXT_EMPHASIS_POSITION("textEmphasisPosition", "text-emphasis-position", BrowserConfiguration.ff("over right")),

        /** The style property {@code text-emphasis-position}. */
        TEXT_EMPHASIS_POSITION_("text-emphasis-position", "text-emphasis-position", BrowserConfiguration.ff("over right")),

        /** The style property {@code textEmphasisStyle}. */
        TEXT_EMPHASIS_STYLE("textEmphasisStyle", "text-emphasis-style", BrowserConfiguration.ff("none")),

        /** The style property {@code text-emphasis-style}. */
        TEXT_EMPHASIS_STYLE_("text-emphasis-style", "text-emphasis-style", BrowserConfiguration.ff("none")),

        /** The style property {@code textIndent}. */
        TEXT_INDENT("textIndent", "text-indent", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code text-indent}. */
        TEXT_INDENT_("text-indent", "text-indent", BrowserConfiguration.ff("0px")),

        /** The style property {@code textJustify}. */
        TEXT_JUSTIFY("textJustify", "text-justify", BrowserConfiguration.ie("auto"), BrowserConfiguration.ff60up("auto")),

        /** The style property {@code text-justify}. */
        TEXT_JUSTIFY_("text-justify", "text-justify", BrowserConfiguration.ff60up("auto")),

        /** The style property {@code textJustifyTrim}. */
        TEXT_JUSTIFY_TRIM("textJustifyTrim", "text-justify-trim", BrowserConfiguration.ie("undefined")),

        /** The style property {@code textKashida}. */
        TEXT_KASHIDA("textKashida", "text-kashida", BrowserConfiguration.ie("undefined")),

        /** The style property {@code textKashidaSpace}. */
        TEXT_KASHIDA_SPACE("textKashidaSpace", "text-kashida-space", BrowserConfiguration.ie("undefined")),

        /** The style property {@code textOrientation}. */
        TEXT_ORIENTATION("textOrientation", "text-orientation", BrowserConfiguration.chrome("mixed"), BrowserConfiguration.ff("mixed")),

        /** The style property {@code text-orientation}. */
        TEXT_ORIENTATION_("text-orientation", "text-orientation", BrowserConfiguration.ff("mixed")),

        /** The style property {@code textOverflow}. */
        TEXT_OVERFLOW("textOverflow", "text-overflow", BrowserConfiguration.ff("clip"), BrowserConfiguration.ie("clip"), BrowserConfiguration.chrome("clip")),

        /** The style property {@code text-overflow}. */
        TEXT_OVERFLOW_("text-overflow", "text-overflow", BrowserConfiguration.ff("clip")),

        /** The style property {@code textRendering}. */
        TEXT_RENDERING("textRendering", "text-rendering", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code text-rendering}. */
        TEXT_RENDERING_("text-rendering", "text-rendering", BrowserConfiguration.ff("auto")),

        /** The style property {@code textShadow}. */
        TEXT_SHADOW("textShadow", "text-shadow", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code text-shadow}. */
        TEXT_SHADOW_("text-shadow", "text-shadow", BrowserConfiguration.ff("none")),

        /** The style property {@code textSizeAdjust}. */
        TEXT_SIZE_ADJUST("textSizeAdjust", "text-size-adjust", BrowserConfiguration.chrome("auto")),

        /** The style property {@code textTransform}. */
        TEXT_TRANSFORM("textTransform", "text-transform", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none")),

        /** The style property {@code text-transform}. */
        TEXT_TRANSFORM_("text-transform", "text-transform", BrowserConfiguration.ff("none")),

        /** The style property {@code textUnderlinePosition}. */
        TEXT_UNDERLINE_POSITION("textUnderlinePosition", "text-underline-position", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code top}. */
        TOP("top", "top", BrowserConfiguration.ff("auto"), BrowserConfiguration.ie("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code touchAction}. */
        TOUCH_ACTION("touchAction", "touch-action", BrowserConfiguration.ie("auto"), BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code touch-action}. */
        TOUCH_ACTION_("touch-action", "touch-action", BrowserConfiguration.ff("auto")),

        /** The style property {@code transform}. */
        TRANSFORM("transform", "transform", BrowserConfiguration.ff("none"), BrowserConfiguration.ie("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code transformBox}. */
        TRANSFORM_BOX("transformBox", "transform-box", BrowserConfiguration.chrome("view-box"), BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code transform-box}. */
        TRANSFORM_BOX_("transform-box", "transform-box", BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code transformOrigin}. */
        TRANSFORM_ORIGIN("transformOrigin", "transform-origin",
                BrowserConfiguration.ff("625px 172.5px"), BrowserConfiguration.ie("620px 163.2px"), BrowserConfiguration.chrome("456.5px 161px")),

        /** The style property {@code transform-origin}. */
        TRANSFORM_ORIGIN_("transform-origin", "transform-origin", BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code transformStyle}. */
        TRANSFORM_STYLE("transformStyle", "transform-style", BrowserConfiguration.ff("flat"), BrowserConfiguration.ie("flat"), BrowserConfiguration.chrome("flat")),

        /** The style property {@code transform-style}. */
        TRANSFORM_STYLE_("transform-style", "transform-style", BrowserConfiguration.ff("flat")),

        /** The style property {@code transition}. */
        TRANSITION("transition", "transition", BrowserConfiguration.ff(""), BrowserConfiguration.ie(""), BrowserConfiguration.chrome("all 0s ease 0s")),

        /** The style property {@code transitionDelay}. */
        TRANSITION_DELAY("transitionDelay", "transition-delay", BrowserConfiguration.ff("0s"), BrowserConfiguration.ie("0s"), BrowserConfiguration.chrome("0s")),

        /** The style property {@code transition-delay}. */
        TRANSITION_DELAY_("transition-delay", "transition-delay", BrowserConfiguration.ff("0s")),

        /** The style property {@code transitionDuration}. */
        TRANSITION_DURATION("transitionDuration", "transition-duration", BrowserConfiguration.ff("0s"), BrowserConfiguration.ie("0s"), BrowserConfiguration.chrome("0s")),

        /** The style property {@code transition-duration}. */
        TRANSITION_DURATION_("transition-duration", "transition-duration", BrowserConfiguration.ff("0s")),

        /** The style property {@code transitionProperty}. */
        TRANSITION_PROPERTY("transitionProperty", "transition-property", BrowserConfiguration.ff("all"), BrowserConfiguration.ie("all"), BrowserConfiguration.chrome("all")),

        /** The style property {@code transition-property}. */
        TRANSITION_PROPERTY_("transition-property", "transition-property", BrowserConfiguration.ff("all")),

        /** The style property {@code transitionTimingFunction}. */
        TRANSITION_TIMING_FUNCTION("transitionTimingFunction",
                "transition-timing-function",
                BrowserConfiguration.ff("ease"),
                BrowserConfiguration.ie("cubic-bezier(0.25, 0.1, 0.25, 1)"),
                BrowserConfiguration.chrome("ease")),

        /** The style property {@code transition-timing-function}. */
        TRANSITION_TIMING_FUNCTION_("transition-timing-function", "transition-timing-function",
                BrowserConfiguration.ff("ease")),

        /** The style property {@code unicodeBidi}. */
        UNICODE_BIDI("unicodeBidi", "unicode-bidi",
                BrowserConfiguration.ff("isolate"), BrowserConfiguration.ie("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code unicode-bidi}. */
        UNICODE_BIDI_("unicode-bidi", "unicode-bidi", BrowserConfiguration.ff("isolate")),

        /** The style property {@code unicodeRange}. */
        UNICODE_RANGE("unicodeRange", "unicode-range", BrowserConfiguration.chrome("")),

        /** The style property {@code userSelect}. */
        USER_SELECT("userSelect", "user-select", BrowserConfiguration.chrome("auto")),

        /** The style property {@code userZoom}. */
        USER_ZOOM("userZoom", "user-zoom", BrowserConfiguration.chrome("")),

        /** The style property {@code vectorEffect}. */
        VECTOR_EFFECT("vectorEffect", "vector-effect", BrowserConfiguration.ff("none"), BrowserConfiguration.chrome("none")),

        /** The style property {@code vector-effect}. */
        VECTOR_EFFECT_("vector-effect", "vector-effect", BrowserConfiguration.ff("none")),

        /** The style property {@code verticalAlign}. */
        VERTICAL_ALIGN("verticalAlign", "vertical-align", BrowserConfiguration.chrome("baseline"), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code vertical-align}. */
        VERTICAL_ALIGN_("vertical-align", "vertical-align", BrowserConfiguration.ff("baseline")),

        /** The style property {@code visibility}. */
        VISIBILITY("visibility", "visibility", BrowserConfiguration.chrome("visible"), BrowserConfiguration.ff("visible"), BrowserConfiguration.ie("visible")),

        /** The style property {@code voiceFamily}. */
        VOICE_FAMILY("voiceFamily", "voice-family"),

        /** The style property {@code volume}. */
        VOLUME("volume", "volume"),

        /** The style property {@code webkitAlignContent}. */
        WEBKIT_ALIGN_CONTENT("webkitAlignContent", "webkit-align-content", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal")),

        /** The style property {@code WebkitAlignContent}. */
        WEBKIT_ALIGN_CONTENT_("WebkitAlignContent", "webkit-align-content", BrowserConfiguration.ff("normal")),

        /** The style property {@code -webkit-align-content}. */
        WEBKIT_ALIGN_CONTENT__("-webkit-align-content", "webkit-align-content", BrowserConfiguration.ff("normal")),

        /** The style property {@code webkitAlignItems}. */
        WEBKIT_ALIGN_ITEMS("webkitAlignItems", "webkit-align-items", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal")),

        /** The style property {@code WebkitAlignItems}. */
        WEBKIT_ALIGN_ITEMS_("WebkitAlignItems", "webkit-align-items", BrowserConfiguration.ff("normal")),

        /** The style property {@code -webkit-align-items}. */
        WEBKIT_ALIGN_ITEMS__("-webkit-align-items", "webkit-align-items", BrowserConfiguration.ff("normal")),

        /** The style property {@code webkitAlignSelf}. */
        WEBKIT_ALIGN_SELF("webkitAlignSelf", "webkit-align-self", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code WebkitAlignSelf}. */
        WEBKIT_ALIGN_SELF_("WebkitAlignSelf", "webkit-align-self", BrowserConfiguration.ff("auto")),

        /** The style property {@code -webkit-align-self}. */
        WEBKIT_ALIGN_SELF__("-webkit-align-self", "webkit-align-self", BrowserConfiguration.ff("auto")),

        /** The style property {@code webkitAnimation}. */
        WEBKIT_ANIMATION("webkitAnimation", "webkit-animation",
                BrowserConfiguration.chrome("none 0s ease 0s 1 normal none running"), BrowserConfiguration.ff("")),

        /** The style property {@code WebkitAnimation}. */
        WEBKIT_ANIMATION_("WebkitAnimation", "webkit-animation", BrowserConfiguration.ff("")),

        /** The style property {@code -webkit-animation}. */
        WEBKIT_ANIMATION__("-webkit-animation", "webkit-animation", BrowserConfiguration.ff("")),

        /** The style property {@code webkitAnimationDelay}. */
        WEBKIT_ANIMATION_DELAY("webkitAnimationDelay", "webkit-animation-delay", BrowserConfiguration.chrome("0s"), BrowserConfiguration.ff("0s")),

        /** The style property {@code WebkitAnimationDelay}. */
        WEBKIT_ANIMATION_DELAY_("WebkitAnimationDelay", "webkit-animation-delay", BrowserConfiguration.ff("0s")),

        /** The style property {@code -webkit-animation-delay}. */
        WEBKIT_ANIMATION_DELAY__("-webkit-animation-delay", "webkit-animation-delay", BrowserConfiguration.ff("0s")),

        /** The style property {@code webkitAnimationDirection}. */
        WEBKIT_ANIMATION_DIRECTION("webkitAnimationDirection", "webkit-animation-direction",
                BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal")),

        /** The style property {@code WebkitAnimationDirection}. */
        WEBKIT_ANIMATION_DIRECTION_("WebkitAnimationDirection", "webkit-animation-direction", BrowserConfiguration.ff("normal")),

        /** The style property {@code -webkit-animation-direction}. */
        WEBKIT_ANIMATION_DIRECTION__("-webkit-animation-direction", "webkit-animation-direction", BrowserConfiguration.ff("normal")),

        /** The style property {@code webkitAnimationDuration}. */
        WEBKIT_ANIMATION_DURATION("webkitAnimationDuration", "webkit-animation-duration", BrowserConfiguration.chrome("0s"), BrowserConfiguration.ff("0s")),

        /** The style property {@code WebkitAnimationDuration}. */
        WEBKIT_ANIMATION_DURATION_("WebkitAnimationDuration", "webkit-animation-duration", BrowserConfiguration.ff("0s")),

        /** The style property {@code -webkit-animation-duration}. */
        WEBKIT_ANIMATION_DURATION__("-webkit-animation-duration", "webkit-animation-duration", BrowserConfiguration.ff("0s")),

        /** The style property {@code webkitAnimationFillMode}. */
        WEBKIT_ANIMATION_FILL_MODE("webkitAnimationFillMode", "webkit-animation-fill-mode", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code WebkitAnimationFillMode}. */
        WEBKIT_ANIMATION_FILL_MODE_("WebkitAnimationFillMode", "webkit-animation-fill-mode", BrowserConfiguration.ff("none")),

        /** The style property {@code -webkit-animation-fill-mode}. */
        WEBKIT_ANIMATION_FILL_MODE__("-webkit-animation-fill-mode", "webkit-animation-fill-mode", BrowserConfiguration.ff("none")),

        /** The style property {@code webkitAnimationIterationCount}. */
        WEBKIT_ANIMATION_ITERATION_COUNT("webkitAnimationIterationCount", "webkit-animation-iteration-count",
                BrowserConfiguration.chrome("1"), BrowserConfiguration.ff("1")),

        /** The style property {@code WebkitAnimationIterationCount}. */
        WEBKIT_ANIMATION_ITERATION_COUNT_("WebkitAnimationIterationCount", "webkit-animation-iteration-count",
                BrowserConfiguration.ff("1")),

        /** The style property {@code -webkit-animation-iteration-count}. */
        WEBKIT_ANIMATION_ITERATION_COUNT__("-webkit-animation-iteration-count", "webkit-animation-iteration-count",
                BrowserConfiguration.ff("1")),

        /** The style property {@code webkitAnimationName}. */
        WEBKIT_ANIMATION_NAME("webkitAnimationName", "webkit-animation-name", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code WebkitAnimationName}. */
        WEBKIT_ANIMATION_NAME_("WebkitAnimationName", "webkit-animation-name", BrowserConfiguration.ff("none")),

        /** The style property {@code -webkit-animation-name}. */
        WEBKIT_ANIMATION_NAME__("-webkit-animation-name", "webkit-animation-name", BrowserConfiguration.ff("none")),

        /** The style property {@code webkitAnimationPlayState}. */
        WEBKIT_ANIMATION_PLAY_STATE("webkitAnimationPlayState", "webkit-animation-play-state",
                BrowserConfiguration.chrome("running"), BrowserConfiguration.ff("running")),

        /** The style property {@code WebkitAnimationPlayState}. */
        WEBKIT_ANIMATION_PLAY_STATE_("WebkitAnimationPlayState", "webkit-animation-play-state", BrowserConfiguration.ff("running")),

        /** The style property {@code -webkit-animation-play-state}. */
        WEBKIT_ANIMATION_PLAY_STATE__("-webkit-animation-play-state", "webkit-animation-play-state", BrowserConfiguration.ff("running")),

        /** The style property {@code webkitAnimationTimingFunction}. */
        WEBKIT_ANIMATION_TIMING_FUNCTION("webkitAnimationTimingFunction", "webkit-animation-timing-function",
                BrowserConfiguration.chrome("ease"), BrowserConfiguration.ff("ease")),

        /** The style property {@code WebkitAnimationTimingFunction}. */
        WEBKIT_ANIMATION_TIMING_FUNCTION_("WebkitAnimationTimingFunction", "webkit-animation-timing-function",
                BrowserConfiguration.ff("ease")),

        /** The style property {@code -webkit-animation-timing-function}. */
        WEBKIT_ANIMATION_TIMING_FUNCTION__("-webkit-animation-timing-function", "webkit-animation-timing-function",
                BrowserConfiguration.ff("ease")),

        /** The style property {@code webkitAppRegion}. */
        WEBKIT_APP_REGION("webkitAppRegion", "webkit-app-region", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitAppearance}. */
        WEBKIT_APPEARANCE("webkitAppearance", "webkit-appearance", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitBackfaceVisibility}. */
        WEBKIT_BACKFACE_VISIBILITY("webkitBackfaceVisibility", "webkit-backface-visibility",
                BrowserConfiguration.chrome("visible"), BrowserConfiguration.ff("visible")),

        /** The style property {@code WebkitBackfaceVisibility}. */
        WEBKIT_BACKFACE_VISIBILITY_("WebkitBackfaceVisibility", "webkit-backface-visibility",
                BrowserConfiguration.ff("visible")),

        /** The style property {@code -webkit-backface-visibility}. */
        WEBKIT_BACKFACE_VISIBILITY__("-webkit-backface-visibility", "webkit-backface-visibility",
                BrowserConfiguration.ff("visible")),

        /** The style property {@code webkitBackgroundClip}. */
        WEBKIT_BACKGROUND_CLIP("webkitBackgroundClip", "webkit-background-clip",
                BrowserConfiguration.chrome("border-box"), BrowserConfiguration.ff("border-box")),

        /** The style property {@code WebkitBackgroundClip}. */
        WEBKIT_BACKGROUND_CLIP_("WebkitBackgroundClip", "webkit-background-clip", BrowserConfiguration.ff("border-box")),

        /** The style property {@code -webkit-background-clip}. */
        WEBKIT_BACKGROUND_CLIP__("-webkit-background-clip", "webkit-background-clip", BrowserConfiguration.ff("border-box")),

        /** The style property {@code webkitBackgroundOrigin}. */
        WEBKIT_BACKGROUND_ORIGIN("webkitBackgroundOrigin", "webkit-background-origin",
                BrowserConfiguration.chrome("padding-box"), BrowserConfiguration.ff("padding-box")),

        /** The style property {@code WebkitBackgroundOrigin}. */
        WEBKIT_BACKGROUND_ORIGIN_("WebkitBackgroundOrigin", "webkit-background-origin", BrowserConfiguration.ff("padding-box")),

        /** The style property {@code -webkit-background-origin}. */
        WEBKIT_BACKGROUND_ORIGIN__("-webkit-background-origin", "webkit-background-origin", BrowserConfiguration.ff("padding-box")),

        /** The style property {@code webkitBackgroundSize}. */
        WEBKIT_BACKGROUND_SIZE("webkitBackgroundSize", "webkit-background-size", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto auto")),

        /** The style property {@code WebkitBackgroundSize}. */
        WEBKIT_BACKGROUND_SIZE_("WebkitBackgroundSize", "webkit-background-size", BrowserConfiguration.ff("auto auto")),

        /** The style property {@code -webkit-background-size}. */
        WEBKIT_BACKGROUND_SIZE__("-webkit-background-size", "webkit-background-size", BrowserConfiguration.ff("auto auto")),

        /** The style property {@code webkitBorderAfter}. */
        WEBKIT_BORDER_AFTER("webkitBorderAfter", "webkit-border-after", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)")),

        /** The style property {@code webkitBorderAfterColor}. */
        WEBKIT_BORDER_AFTER_COLOR("webkitBorderAfterColor", "webkit-border-after-color", BrowserConfiguration.chrome("rgb(0, 0, 0)")),

        /** The style property {@code webkitBorderAfterStyle}. */
        WEBKIT_BORDER_AFTER_STYLE("webkitBorderAfterStyle", "webkit-border-after-style", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitBorderAfterWidth}. */
        WEBKIT_BORDER_AFTER_WIDTH("webkitBorderAfterWidth", "webkit-border-after-width", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitBorderBefore}. */
        WEBKIT_BORDER_BEFORE("webkitBorderBefore", "webkit-border-before", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)")),

        /** The style property {@code webkitBorderBeforeColor}. */
        WEBKIT_BORDER_BEFORE_COLOR("webkitBorderBeforeColor", "webkit-border-before-color", BrowserConfiguration.chrome("rgb(0, 0, 0)")),

        /** The style property {@code webkitBorderBeforeStyle}. */
        WEBKIT_BORDER_BEFORE_STYLE("webkitBorderBeforeStyle", "webkit-border-before-style", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitBorderBeforeWidth}. */
        WEBKIT_BORDER_BEFORE_WIDTH("webkitBorderBeforeWidth", "webkit-border-before-width", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitBorderBottomLeftRadius}. */
        WEBKIT_BORDER_BOTTOM_LEFT_RADIUS("webkitBorderBottomLeftRadius", "webkit-border-bottom-left-radius",
                BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("0px")),

        /** The style property {@code WebkitBorderBottomLeftRadius}. */
        WEBKIT_BORDER_BOTTOM_LEFT_RADIUS_("WebkitBorderBottomLeftRadius", "webkit-border-bottom-left-radius",
                BrowserConfiguration.ff("0px")),

        /** The style property {@code -webkit-border-bottom-left-radius}. */
        WEBKIT_BORDER_BOTTOM_LEFT_RADIUS__("-webkit-border-bottom-left-radius", "webkit-border-bottom-left-radius",
                BrowserConfiguration.ff("0px")),

        /** The style property {@code webkitBorderBottomRightRadius}. */
        WEBKIT_BORDER_BOTTOM_RIGHT_RADIUS("webkitBorderBottomRightRadius", "webkit-border-bottom-right-radius",
                BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("0px")),

        /** The style property {@code WebkitBorderBottomRightRadius}. */
        WEBKIT_BORDER_BOTTOM_RIGHT_RADIUS_("WebkitBorderBottomRightRadius", "webkit-border-bottom-right-radius",
                BrowserConfiguration.ff("0px")),

        /** The style property {@code -webkit-border-bottom-right-radius}. */
        WEBKIT_BORDER_BOTTOM_RIGHT_RADIUS__("-webkit-border-bottom-right-radius", "webkit-border-bottom-right-radius",
                BrowserConfiguration.ff("0px")),

        /** The style property {@code webkitBorderEnd}. */
        WEBKIT_BORDER_END("webkitBorderEnd", "webkit-border-end", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)")),

        /** The style property {@code webkitBorderEndColor}. */
        WEBKIT_BORDER_END_COLOR("webkitBorderEndColor", "webkit-border-end-color", BrowserConfiguration.chrome("rgb(0, 0, 0)")),

        /** The style property {@code webkitBorderEndStyle}. */
        WEBKIT_BORDER_END_STYLE("webkitBorderEndStyle", "webkit-border-end-style", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitBorderEndWidth}. */
        WEBKIT_BORDER_END_WIDTH("webkitBorderEndWidth", "webkit-border-end-width", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitBorderHorizontalSpacing}. */
        WEBKIT_BORDER_HORIZONTAL_SPACING("webkitBorderHorizontalSpacing", "webkit-border-horizontal-spacing",
                BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitBorderImage}. */
        WEBKIT_BORDER_IMAGE("webkitBorderImage", "webkit-border-image", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("")),

        /** The style property {@code WebkitBorderImage}. */
        WEBKIT_BORDER_IMAGE_("WebkitBorderImage", "webkit-border-image", BrowserConfiguration.ff("")),

        /** The style property {@code -webkit-border-image}. */
        WEBKIT_BORDER_IMAGE__("-webkit-border-image", "webkit-border-image", BrowserConfiguration.ff("")),

        /** The style property {@code webkitBorderRadius}. */
        WEBKIT_BORDER_RADIUS("webkitBorderRadius", "webkit-border-radius", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("")),

        /** The style property {@code WebkitBorderRadius}. */
        WEBKIT_BORDER_RADIUS_("WebkitBorderRadius", "webkit-border-radius", BrowserConfiguration.ff("")),

        /** The style property {@code -webkit-border-radius}. */
        WEBKIT_BORDER_RADIUS__("-webkit-border-radius", "webkit-border-radius", BrowserConfiguration.ff("")),

        /** The style property {@code webkitBorderStart}. */
        WEBKIT_BORDER_START("webkitBorderStart", "webkit-border-start", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)")),

        /** The style property {@code webkitBorderStartColor}. */
        WEBKIT_BORDER_START_COLOR("webkitBorderStartColor", "webkit-border-start-color", BrowserConfiguration.chrome("rgb(0, 0, 0)")),

        /** The style property {@code webkitBorderStartStyle}. */
        WEBKIT_BORDER_START_STYLE("webkitBorderStartStyle", "webkit-border-start-style", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitBorderStartWidth}. */
        WEBKIT_BORDER_START_WIDTH("webkitBorderStartWidth", "webkit-border-start-width", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitBorderTopLeftRadius}. */
        WEBKIT_BORDER_TOP_LEFT_RADIUS("webkitBorderTopLeftRadius", "webkit-border-top-left-radius",
                BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("0px")),

        /** The style property {@code WebkitBorderTopLeftRadius}. */
        WEBKIT_BORDER_TOP_LEFT_RADIUS_("WebkitBorderTopLeftRadius", "webkit-border-top-left-radius", BrowserConfiguration.ff("0px")),

        /** The style property {@code -webkit-border-top-left-radius}. */
        WEBKIT_BORDER_TOP_LEFT_RADIUS__("-webkit-border-top-left-radius", "webkit-border-top-left-radius",
                BrowserConfiguration.ff("0px")),

        /** The style property {@code webkitBorderTopRightRadius}. */
        WEBKIT_BORDER_TOP_RIGHT_RADIUS("webkitBorderTopRightRadius", "webkit-border-top-right-radius",
                BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("0px")),

        /** The style property {@code WebkitBorderTopRightRadius}. */
        WEBKIT_BORDER_TOP_RIGHT_RADIUS_("WebkitBorderTopRightRadius", "webkit-border-top-right-radius", BrowserConfiguration.ff("0px")),

        /** The style property {@code -webkit-border-top-right-radius}. */
        WEBKIT_BORDER_TOP_RIGHT_RADIUS__("-webkit-border-top-right-radius", "webkit-border-top-right-radius",
                BrowserConfiguration.ff("0px")),

        /** The style property {@code webkitBorderVerticalSpacing}. */
        WEBKIT_BORDER_VERTICAL_SPACING("webkitBorderVerticalSpacing", "webkit-border-vertical-spacing", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitBoxAlign}. */
        WEBKIT_BOX_ALIGN("webkitBoxAlign", "webkit-box-align", BrowserConfiguration.chrome("stretch"), BrowserConfiguration.ff("stretch")),

        /** The style property {@code WebkitBoxAlign}. */
        WEBKIT_BOX_ALIGN_("WebkitBoxAlign", "webkit-box-align", BrowserConfiguration.ff("stretch")),

        /** The style property {@code -webkit-box-align}. */
        WEBKIT_BOX_ALIGN__("-webkit-box-align", "webkit-box-align", BrowserConfiguration.ff("stretch")),

        /** The style property {@code webkitBoxDecorationBreak}. */
        WEBKIT_BOX_DECORATION_BREAK("webkitBoxDecorationBreak", "webkit-box-decoration-break", BrowserConfiguration.chrome("slice")),

        /** The style property {@code webkitBoxDirection}. */
        WEBKIT_BOX_DIRECTION("webkitBoxDirection", "webkit-box-direction", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal")),

        /** The style property {@code WebkitBoxDirection}. */
        WEBKIT_BOX_DIRECTION_("WebkitBoxDirection", "webkit-box-direction", BrowserConfiguration.ff("normal")),

        /** The style property {@code -webkit-box-direction}. */
        WEBKIT_BOX_DIRECTION__("-webkit-box-direction", "webkit-box-direction", BrowserConfiguration.ff("normal")),

        /** The style property {@code webkitBoxFlex}. */
        WEBKIT_BOX_FLEX("webkitBoxFlex", "webkit-box-flex", BrowserConfiguration.chrome("0"), BrowserConfiguration.ff("0")),

        /** The style property {@code WebkitBoxFlex}. */
        WEBKIT_BOX_FLEX_("WebkitBoxFlex", "webkit-box-flex", BrowserConfiguration.ff("0")),

        /** The style property {@code -webkit-box-flex}. */
        WEBKIT_BOX_FLEX__("-webkit-box-flex", "webkit-box-flex", BrowserConfiguration.ff("0")),

        /** The style property {@code webkitBoxFlexGroup}. */
        WEBKIT_BOX_FLEX_GROUP("webkitBoxFlexGroup", "webkit-box-flex-group", BrowserConfiguration.chrome("1").setIteratable(false)),

        /** The style property {@code webkitBoxLines}. */
        WEBKIT_BOX_LINES("webkitBoxLines", "webkit-box-lines", BrowserConfiguration.chrome("single").setIteratable(false)),

        /** The style property {@code webkitBoxOrdinalGroup}. */
        WEBKIT_BOX_ORDINAL_GROUP("webkitBoxOrdinalGroup", "webkit-box-ordinal-group", BrowserConfiguration.chrome("1"), BrowserConfiguration.ff("1")),

        /** The style property {@code WebkitBoxOrdinalGroup}. */
        WEBKIT_BOX_ORDINAL_GROUP_("WebkitBoxOrdinalGroup", "webkit-box-ordinal-group", BrowserConfiguration.ff("1")),

        /** The style property {@code -webkit-box-ordinal-group}. */
        WEBKIT_BOX_ORDINAL_GROUP__("-webkit-box-ordinal-group", "webkit-box-ordinal-group", BrowserConfiguration.ff("1")),

        /** The style property {@code webkitBoxOrient}. */
        WEBKIT_BOX_ORIENT("webkitBoxOrient", "webkit-box-orient", BrowserConfiguration.chrome("horizontal"), BrowserConfiguration.ff("horizontal")),

        /** The style property {@code WebkitBoxOrient}. */
        WEBKIT_BOX_ORIENT_("WebkitBoxOrient", "webkit-box-orient", BrowserConfiguration.ff("horizontal")),

        /** The style property {@code -webkit-box-orient}. */
        WEBKIT_BOX_ORIENT__("-webkit-box-orient", "webkit-box-orient", BrowserConfiguration.ff("horizontal")),

        /** The style property {@code webkitBoxPack}. */
        WEBKIT_BOX_PACK("webkitBoxPack", "webkit-box-pack", BrowserConfiguration.chrome("start"), BrowserConfiguration.ff("start")),

        /** The style property {@code WebkitBoxPack}. */
        WEBKIT_BOX_PACK_("WebkitBoxPack", "webkit-box-pack", BrowserConfiguration.ff("start")),

        /** The style property {@code -webkit-box-pack}. */
        WEBKIT_BOX_PACK__("-webkit-box-pack", "webkit-box-pack", BrowserConfiguration.ff("start")),

        /** The style property {@code webkitBoxReflect}. */
        WEBKIT_BOX_REFLECT("webkitBoxReflect", "webkit-box-reflect", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitBoxShadow}. */
        WEBKIT_BOX_SHADOW("webkitBoxShadow", "webkit-box-shadow", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code WebkitBoxShadow}. */
        WEBKIT_BOX_SHADOW_("WebkitBoxShadow", "webkit-box-shadow", BrowserConfiguration.ff("none")),

        /** The style property {@code -webkit-box-shadow}. */
        WEBKIT_BOX_SHADOW__("-webkit-box-shadow", "webkit-box-shadow", BrowserConfiguration.ff("none")),

        /** The style property {@code webkitBoxSizing}. */
        WEBKIT_BOX_SIZING("webkitBoxSizing", "webkit-box-sizing", BrowserConfiguration.chrome("content-box"), BrowserConfiguration.ff("content-box")),

        /** The style property {@code WebkitBoxSizing}. */
        WEBKIT_BOX_SIZING_("WebkitBoxSizing", "webkit-box-sizing", BrowserConfiguration.ff("content-box")),

        /** The style property {@code -webkit-box-sizing}. */
        WEBKIT_BOX_SIZING__("-webkit-box-sizing", "webkit-box-sizing", BrowserConfiguration.ff("content-box")),

        /** The style property {@code webkitClipPath}. */
        WEBKIT_CLIP_PATH("webkitClipPath", "webkit-clip-path", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitColumnBreakAfter}. */
        WEBKIT_COLUMN_BREAK_AFTER("webkitColumnBreakAfter", "webkit-column-break-after", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitColumnBreakBefore}. */
        WEBKIT_COLUMN_BREAK_BEFORE("webkitColumnBreakBefore", "webkit-column-break-before", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitColumnBreakInside}. */
        WEBKIT_COLUMN_BREAK_INSIDE("webkitColumnBreakInside", "webkit-column-break-inside", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitColumnCount}. */
        WEBKIT_COLUMN_COUNT("webkitColumnCount", "webkit-column-count", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitColumnGap}. */
        WEBKIT_COLUMN_GAP("webkitColumnGap", "webkit-column-gap", BrowserConfiguration.chrome("normal")),

        /** The style property {@code webkitColumnRule}. */
        WEBKIT_COLUMN_RULE("webkitColumnRule", "webkit-column-rule", BrowserConfiguration.chrome("0px none rgb(0, 0, 0)")),

        /** The style property {@code webkitColumnRuleColor}. */
        WEBKIT_COLUMN_RULE_COLOR("webkitColumnRuleColor", "webkit-column-rule-color", BrowserConfiguration.chrome("rgb(0, 0, 0)")),

        /** The style property {@code webkitColumnRuleStyle}. */
        WEBKIT_COLUMN_RULE_STYLE("webkitColumnRuleStyle", "webkit-column-rule-style", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitColumnRuleWidth}. */
        WEBKIT_COLUMN_RULE_WIDTH("webkitColumnRuleWidth", "webkit-column-rule-width", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitColumnSpan}. */
        WEBKIT_COLUMN_SPAN("webkitColumnSpan", "webkit-column-rule-span", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitColumnWidth}. */
        WEBKIT_COLUMN_WIDTH("webkitColumnWidth", "webkit-column-width", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitColumns}. */
        WEBKIT_COLUMNS("webkitColumns", "webkit-columns", BrowserConfiguration.chrome("auto auto")),

        /** The style property {@code webkitFilter}. */
        WEBKIT_FILTER("webkitFilter", "webkit-filter", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code WebkitFilter}. */
        WEBKIT_FILTER_("WebkitFilter", "webkit-filter", BrowserConfiguration.ff("none")),

        /** The style property {@code -webkit-filter}. */
        WEBKIT_FILTER__("-webkit-filter", "webkit-filter", BrowserConfiguration.ff("none")),

        /** The style property {@code webkitFlex}. */
        WEBKIT_FLEX("webkitFlex", "webkit-flex", BrowserConfiguration.chrome("0 1 auto"), BrowserConfiguration.ff("")),

        /** The style property {@code WebkitFlex}. */
        WEBKIT_FLEX_("WebkitFlex", "webkit-flex", BrowserConfiguration.ff("")),

        /** The style property {@code -webkit-flex}. */
        WEBKIT_FLEX__("-webkit-flex", "webkit-flex", BrowserConfiguration.ff("")),

        /** The style property {@code webkitFlexBasis}. */
        WEBKIT_FLEX_BASIS("webkitFlexBasis", "webkit-flex-basis", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code WebkitFlexBasis}. */
        WEBKIT_FLEX_BASIS_("WebkitFlexBasis", "webkit-flex-basis", BrowserConfiguration.ff("auto")),

        /** The style property {@code -webkit-flex-basis}. */
        WEBKIT_FLEX_BASIS__("-webkit-flex-basis", "webkit-flex-basis", BrowserConfiguration.ff("auto")),

        /** The style property {@code webkitFlexDirection}. */
        WEBKIT_FLEX_DIRECTION("webkitFlexDirection", "webkit-flex-direction", BrowserConfiguration.chrome("row"), BrowserConfiguration.ff("row")),

        /** The style property {@code WebkitFlexDirection}. */
        WEBKIT_FLEX_DIRECTION_("WebkitFlexDirection", "webkit-flex-direction", BrowserConfiguration.ff("row")),

        /** The style property {@code -webkit-flex-direction}. */
        WEBKIT_FLEX_DIRECTION__("-webkit-flex-direction", "webkit-flex-direction", BrowserConfiguration.ff("row")),

        /** The style property {@code webkitFlexFlow}. */
        WEBKIT_FLEX_FLOW("webkitFlexFlow", "webkit-flex-flow", BrowserConfiguration.chrome("row nowrap"), BrowserConfiguration.ff("")),

        /** The style property {@code WebkitFlexFlow}. */
        WEBKIT_FLEX_FLOW_("WebkitFlexFlow", "webkit-flex-flow", BrowserConfiguration.ff("")),

        /** The style property {@code -webkit-flex-flow}. */
        WEBKIT_FLEX_FLOW__("-webkit-flex-flow", "webkit-flex-flow", BrowserConfiguration.ff("")),

        /** The style property {@code webkitFlexGrow}. */
        WEBKIT_FLEX_GROW("webkitFlexGrow", "webkit-flex-grow", BrowserConfiguration.chrome("0"), BrowserConfiguration.ff("0")),

        /** The style property {@code WebkitFlexGrow}. */
        WEBKIT_FLEX_GROW_("WebkitFlexGrow", "webkit-flex-grow", BrowserConfiguration.ff("0")),

        /** The style property {@code -webkit-flex-grow}. */
        WEBKIT_FLEX_GROW__("-webkit-flex-grow", "webkit-flex-grow", BrowserConfiguration.ff("0")),

        /** The style property {@code webkitFlexShrink}. */
        WEBKIT_FLEX_SHRINK("webkitFlexShrink", "webkit-flex-shrink", BrowserConfiguration.chrome("1"), BrowserConfiguration.ff("1")),

        /** The style property {@code WebkitFlexShrink}. */
        WEBKIT_FLEX_SHRINK_("WebkitFlexShrink", "webkit-flex-shrink", BrowserConfiguration.ff("1")),

        /** The style property {@code -webkit-flex-shrink}. */
        WEBKIT_FLEX_SHRINK__("-webkit-flex-shrink", "webkit-flex-shrink", BrowserConfiguration.ff("1")),

        /** The style property {@code webkitFlexWrap}. */
        WEBKIT_FLEX_WRAP("webkitFlexWrap", "webkit-flex-wrap", BrowserConfiguration.chrome("nowrap"), BrowserConfiguration.ff("nowrap")),

        /** The style property {@code WebkitFlexWrap}. */
        WEBKIT_FLEX_WRAP_("WebkitFlexWrap", "webkit-flex-wrap", BrowserConfiguration.ff("nowrap")),

        /** The style property {@code -webkit-flex-wrap}. */
        WEBKIT_FLEX_WRAP__("-webkit-flex-wrap", "webkit-flex-wrap", BrowserConfiguration.ff("nowrap")),

        /** The style property {@code webkitFontFeatureSettings}. */
        WEBKIT_FONT_FEATURE_SETTINGS("webkitFontFeatureSettings", "webkit-font-feature-settings", BrowserConfiguration.chrome("normal")),

        /** The style property {@code webkitFontSizeDelta}. */
        WEBKIT_FONT_SIZE_DELTA("webkitFontSizeDelta", "webkit-font-size-delta", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitFontSmoothing}. */
        WEBKIT_FONT_SMOOTHING("webkitFontSmoothing", "webkit-font-smoothing", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitHighlight}. */
        WEBKIT_HIGHLIGHT("webkitHighlight", "webkit-highlight", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitHyphenateCharacter}. */
        WEBKIT_HYPHENATE_CHARACTER("webkitHyphenateCharacter", "webkit-hyphenate-character", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitJustifyContent}. */
        WEBKIT_JUSTIFY_CONTENT("webkitJustifyContent", "webkit-justify-content", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal")),

        /** The style property {@code WebkitJustifyContent}. */
        WEBKIT_JUSTIFY_CONTENT_("WebkitJustifyContent", "webkit-justify-content", BrowserConfiguration.ff("normal")),

        /** The style property {@code -webkit-justify-content}. */
        WEBKIT_JUSTIFY_CONTENT__("-webkit-justify-content", "webkit-justify-content", BrowserConfiguration.ff("normal")),

        /** The style property {@code webkitLineBreak}. */
        WEBKIT_LINE_BREAK("webkitLineBreak", "webkit-line-break", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitLineClamp}. */
        WEBKIT_LINE_CLAMP("webkitLineClamp", "webkit-line-clamp", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitLocale}. */
        WEBKIT_LOCALE("webkitLocale", "webkit-locale", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitLogicalHeight}. */
        WEBKIT_LOGICAL_HEIGHT("webkitLogicalHeight", "webkit-logical-height", BrowserConfiguration.chrome("322px")),

        /** The style property {@code webkitLogicalWidth}. */
        WEBKIT_LOGICAL_WIDTH("webkitLogicalWidth", "webkit-logical-width", BrowserConfiguration.chrome("913px")),

        /** The style property {@code webkitMarginAfter}. */
        WEBKIT_MARGIN_AFTER("webkitMarginAfter", "webkit-margin-after", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitMarginAfterCollapse}. */
        WEBKIT_MARGIN_AFTER_COLLAPSE("webkitMarginAfterCollapse", "webkit-margin-after-collapse", BrowserConfiguration.chrome("collapse")),

        /** The style property {@code webkitMarginBefore}. */
        WEBKIT_MARGIN_BEFORE("webkitMarginBefore", "webkit-margin-before", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitMarginBeforeCollapse}. */
        WEBKIT_MARGIN_BEFORE_COLLAPSE("webkitMarginBeforeCollapse", "webkit-margin-before-collapse",
                BrowserConfiguration.chrome("collapse")),

        /** The style property {@code webkitMarginBottomCollapse}. */
        WEBKIT_MARGIN_BOTTOM_COLLAPSE("webkitMarginBottomCollapse", "webkit-margin-bottom-collapse",
                BrowserConfiguration.chrome("collapse")),

        /** The style property {@code webkitMarginCollapse}. */
        WEBKIT_MARGIN_COLLAPSE("webkitMarginCollapse", "webkit-margin-collapse", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitMarginEnd}. */
        WEBKIT_MARGIN_END("webkitMarginEnd", "webkit-margin-end", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitMarginStart}. */
        WEBKIT_MARGIN_START("webkitMarginStart", "webkit-margin-start", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitMarginTopCollapse}. */
        WEBKIT_MARGIN_TOP_COLLAPSE("webkitMarginTopCollapse", "webkit-margin-top-collapse", BrowserConfiguration.chrome("collapse")),

        /** The style property {@code webkitMask}. */
        WEBKIT_MASK("webkitMask", "webkit-mask", BrowserConfiguration.chrome(""), BrowserConfiguration.ff60up("none")),

        /** The style property {@code WebkitMask}. */
        WEBKIT_MASK_("WebkitMask", "webkit-mask", BrowserConfiguration.ff60up("none")),

        /** The style property {@code -webkit-mask}. */
        WEBKIT_MASK__("-webkit-mask", "webkit-mask", BrowserConfiguration.ff60up("none")),

        /** The style property {@code webkitMaskBoxImage}. */
        WEBKIT_MASK_BOX_IMAGE("webkitMaskBoxImage", "webkit-mask-box-image", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitMaskBoxImageOutset}. */
        WEBKIT_MASK_BOX_IMAGE_OUTSET("webkitMaskBoxImageOutset", "webkit-mask-box-image-outset", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitMaskBoxImageRepeat}. */
        WEBKIT_MASK_BOX_IMAGE_REPEAT("webkitMaskBoxImageRepeat", "webkit-mask-box-image-repeat", BrowserConfiguration.chrome("stretch")),

        /** The style property {@code webkitMaskBoxImageSlice}. */
        WEBKIT_MASK_BOX_IMAGE_SLICE("webkitMaskBoxImageSlice", "webkit-mask-box-image-slice", BrowserConfiguration.chrome("0 fill")),

        /** The style property {@code webkitMaskBoxImageSource}. */
        WEBKIT_MASK_BOX_IMAGE_SOURCE("webkitMaskBoxImageSource", "webkit-mask-box-image-source", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitMaskBoxImageWidth}. */
        WEBKIT_MASK_BOX_IMAGE_WIDTH("webkitMaskBoxImageWidth", "webkit-mask-box-image-width", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitMaskClip}. */
        WEBKIT_MASK_CLIP("webkitMaskClip", "webkit-mask-clip", BrowserConfiguration.chrome("border-box"), BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code WebkitMaskClip}. */
        WEBKIT_MASK_CLIP_("WebkitMaskClip", "webkit-mask-clip", BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code -webkit-mask-clip}. */
        WEBKIT_MASK_CLIP__("-webkit-mask-clip", "webkit-mask-clip", BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code webkitMaskComposite}. */
        WEBKIT_MASK_COMPOSITE("webkitMaskComposite", "webkit-mask-composite", BrowserConfiguration.chrome("source-over"), BrowserConfiguration.ff60up("add")),

        /** The style property {@code WebkitMaskComposite}. */
        WEBKIT_MASK_COMPOSITE_("WebkitMaskComposite", "webkit-mask-composite", BrowserConfiguration.ff60up("add")),

        /** The style property {@code -webkit-mask-composite}. */
        WEBKIT_MASK_COMPOSITE__("-webkit-mask-composite", "webkit-mask-composite", BrowserConfiguration.ff60up("add")),

        /** The style property {@code webkitMaskImage}. */
        WEBKIT_MASK_IMAGE("webkitMaskImage", "webkit-mask-image", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff60up("none")),

        /** The style property {@code WebkitMaskImage}. */
        WEBKIT_MASK_IMAGE_("WebkitMaskImage", "webkit-mask-image", BrowserConfiguration.ff60up("none")),

        /** The style property {@code -webkit-mask-image}. */
        WEBKIT_MASK_IMAGE__("-webkit-mask-image", "webkit-mask-image", BrowserConfiguration.ff60up("none")),

        /** The style property {@code webkitMaskOrigin}. */
        WEBKIT_MASK_ORIGIN("webkitMaskOrigin", "webkit-mask-origin", BrowserConfiguration.chrome("border-box"), BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code WebkitMaskOrigin}. */
        WEBKIT_MASK_ORIGIN_("WebkitMaskOrigin", "webkit-mask-origin", BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code -webkit-mask-origin}. */
        WEBKIT_MASK_ORIGIN__("-webkit-mask-origin", "webkit-mask-origin", BrowserConfiguration.ff60up("border-box")),

        /** The style property {@code webkitMaskPosition}. */
        WEBKIT_MASK_POSITION("webkitMaskPosition", "webkit-mask-position", BrowserConfiguration.chrome("0% 0%"), BrowserConfiguration.ff60up("0% 0%")),

        /** The style property {@code WebkitMaskPosition}. */
        WEBKIT_MASK_POSITION_("WebkitMaskPosition", "webkit-mask-position", BrowserConfiguration.ff60up("0% 0%")),

        /** The style property {@code -webkit-mask-position}. */
        WEBKIT_MASK_POSITION__("-webkit-mask-position", "webkit-mask-position", BrowserConfiguration.ff60up("0% 0%")),

        /** The style property {@code webkitMaskPositionX}. */
        WEBKIT_MASK_POSITION_X("webkitMaskPositionX", "webkit-mask-position-x", BrowserConfiguration.chrome("0%"), BrowserConfiguration.ff60up("0%")),

        /** The style property {@code WebkitMaskPositionX}. */
        WEBKIT_MASK_POSITION_X_("WebkitMaskPositionX", "webkit-mask-position-x", BrowserConfiguration.ff60up("0%")),

        /** The style property {@code -webkit-mask-position-x}. */
        WEBKIT_MASK_POSITION_X__("-webkit-mask-position-x", "webkit-mask-position-x", BrowserConfiguration.ff60up("0%")),

        /** The style property {@code webkitMaskPositionY}. */
        WEBKIT_MASK_POSITION_Y("webkitMaskPositionY", "webkit-mask-position-y", BrowserConfiguration.chrome("0%"), BrowserConfiguration.ff60up("0%")),

        /** The style property {@code WebkitMaskPositionY}. */
        WEBKIT_MASK_POSITION_Y_("WebkitMaskPositionY", "webkit-mask-position-y", BrowserConfiguration.ff60up("0%")),

        /** The style property {@code -webkit-mask-position-y}. */
        WEBKIT_MASK_POSITION_Y__("-webkit-mask-position-y", "webkit-mask-position-y", BrowserConfiguration.ff60up("0%")),

        /** The style property {@code webkitMaskRepeat}. */
        WEBKIT_MASK_REPEAT("webkitMaskRepeat", "webkit-mask-repeat", BrowserConfiguration.chrome("repeat"), BrowserConfiguration.ff60up("repeat")),

        /** The style property {@code WebkitMaskRepeat}. */
        WEBKIT_MASK_REPEAT_("WebkitMaskRepeat", "webkit-mask-repeat", BrowserConfiguration.ff60up("repeat")),

        /** The style property {@code -webkit-mask-repeat}. */
        WEBKIT_MASK_REPEAT__("-webkit-mask-repeat", "webkit-mask-repeat", BrowserConfiguration.ff60up("repeat")),

        /** The style property {@code webkitMaskRepeatX}. */
        WEBKIT_MASK_REPEAT_X("webkitMaskRepeatX", "webkit-mask-repeat-x", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitMaskRepeatY}. */
        WEBKIT_MASK_REPEAT_Y("webkitMaskRepeatY", "webkit-mask-repeat-y", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitMaskSize}. */
        WEBKIT_MASK_SIZE("webkitMaskSize", "webkit-mask-size", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff60up("auto auto")),

        /** The style property {@code WebkitMaskSize}. */
        WEBKIT_MASK_SIZE_("WebkitMaskSize", "webkit-mask-size", BrowserConfiguration.ff60up("auto auto")),

        /** The style property {@code -webkit-mask-size}. */
        WEBKIT_MASK_SIZE__("-webkit-mask-size", "webkit-mask-size", BrowserConfiguration.ff60up("auto auto")),

        /** The style property {@code webkitMaxLogicalHeight}. */
        WEBKIT_MAX_LOGICAL_HEIGHT("webkitMaxLogicalHeight", "webkit-max-logical-height", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitMaxLogicalWidth}. */
        WEBKIT_MAX_LOGICAL_WIDTH("webkitMaxLogicalWidth", "webkit-max-logical-width", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitMinLogicalHeight}. */
        WEBKIT_MIN_LOGICAL_HEIGHT("webkitMinLogicalHeight", "webkit-min-logical-height", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitMinLogicalWidth}. */
        WEBKIT_MIN_LOGICAL_WIDTH("webkitMinLogicalWidth", "webkit-min-logical-width", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitOpacity}. */
        WEBKIT_OPACITY("webkitOpacity", "webkit-opacity", BrowserConfiguration.chrome("1")),

        /** The style property {@code webkitOrder}. */
        WEBKIT_ORDER("webkitOrder", "webkit-order", BrowserConfiguration.chrome("0"), BrowserConfiguration.ff("0")),

        /** The style property {@code WebkitOrder}. */
        WEBKIT_ORDER_("WebkitOrder", "webkit-order", BrowserConfiguration.ff("0")),

        /** The style property {@code -webkit-order}. */
        WEBKIT_ORDER__("-webkit-order", "webkit-order", BrowserConfiguration.ff("0")),

        /** The style property {@code webkitPaddingAfter}. */
        WEBKIT_PADDING_AFTER("webkitPaddingAfter", "webkit-padding-after", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitPaddingBefore}. */
        WEBKIT_PADDING_BEFORE("webkitPaddingBefore", "webkit-padding-before", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitPaddingEnd}. */
        WEBKIT_PADDING_END("webkitPaddingEnd", "webkit-padding-end", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitPaddingStart}. */
        WEBKIT_PADDING_START("webkitPaddingStart", "webkit-padding-start", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitPerspective}. */
        WEBKIT_PERSPECTIVE("webkitPerspective", "webkit-perspective", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code WebkitPerspective}. */
        WEBKIT_PERSPECTIVE_("WebkitPerspective", "webkit-perspective", BrowserConfiguration.ff("none")),

        /** The style property {@code -webkit-perspective}. */
        WEBKIT_PERSPECTIVE__("-webkit-perspective", "webkit-perspective", BrowserConfiguration.ff("none")),

        /** The style property {@code webkitPerspectiveOrigin}. */
        WEBKIT_PERSPECTIVE_ORIGIN("webkitPerspectiveOrigin", "webkit-perspective-origin",
                BrowserConfiguration.chrome("456.5px 161px"), BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code WebkitPerspectiveOrigin}. */
        WEBKIT_PERSPECTIVE_ORIGIN_("WebkitPerspectiveOrigin", "webkit-perspective-origin", BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code -webkit-perspective-origin}. */
        WEBKIT_PERSPECTIVE_ORIGIN__("-webkit-perspective-origin", "webkit-perspective-origin", BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code webkitPerspectiveOriginX}. */
        WEBKIT_PERSPECTIVE_ORIGIN_X("webkitPerspectiveOriginX", "webkit-perspective-origin-x", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitPerspectiveOriginY}. */
        WEBKIT_PERSPECTIVE_ORIGIN_Y("webkitPerspectiveOriginY", "webkit-perspective-origin-y", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitPrintColorAdjust}. */
        WEBKIT_PRINT_COLOR_ADJUST("webkitPrintColorAdjust", "webkit-print-color-adjust", BrowserConfiguration.chrome("economy")),

        /** The style property {@code webkitRtlOrdering}. */
        WEBKIT_RTL_ORDERING("webkitRtlOrdering", "webkit-rtl-ordering", BrowserConfiguration.chrome("logical")),

        /** The style property {@code webkitRubyPosition}. */
        WEBKIT_RUBY_POSITION("webkitRubyPosition", "webkit-ruby-position", BrowserConfiguration.chrome("before")),

        /** The style property {@code webkitShapeImageThreshold}. */
        WEBKIT_SHAPE_IMAGE_THRESHOLD("webkitShapeImageThreshold", "webkit-shape-image-threshold", BrowserConfiguration.chrome("0")),

        /** The style property {@code webkitShapeMargin}. */
        WEBKIT_SHAPE_MARGIN("webkitShapeMargin", "webkit-shape-margin", BrowserConfiguration.chrome("0px")),

        /** The style property {@code webkitShapeOutside}. */
        WEBKIT_SHAPE_OUTSIDE("webkitShapeOutside", "webkit-shape-outside", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitTapHighlightColor}. */
        WEBKIT_TAP_HIGHLIGHT_COLOR("webkitTapHighlightColor", "webkit-tap-highlight-color",
                BrowserConfiguration.chrome("rgba(0, 0, 0, 0.18)")),

        /** The style property {@code webkitTextCombine}. */
        WEBKIT_TEXT_COMBINE("webkitTextCombine", "webkit-text-combine", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitTextDecorationsInEffect}. */
        WEBKIT_TEXT_DECORATIONS_IN_EFFECT("webkitTextDecorationsInEffect", "webkit-text-decorations-in-effect",
                BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitTextEmphasis}. */
        WEBKIT_TEXT_EMPHASIS("webkitTextEmphasis", "webkit-text-emphasis", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitTextEmphasisColor}. */
        WEBKIT_TEXT_EMPHASIS_COLOR("webkitTextEmphasisColor", "webkit-text-emphasis-color",
                BrowserConfiguration.chrome("rgb(0, 0, 0)")),

        /** The style property {@code webkitTextEmphasisPosition}. */
        WEBKIT_TEXT_EMPHASIS_POSITION("webkitTextEmphasisPosition", "webkit-text-emphasis-position",
                BrowserConfiguration.chrome("over right")),

        /** The style property {@code webkitTextEmphasisStyle}. */
        WEBKIT_TEXT_EMPHASIS_STYLE("webkitTextEmphasisStyle", "webkit-text-emphasis-style",
                BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitTextFillColor}. */
        WEBKIT_TEXT_FILL_COLOR("webkitTextFillColor", "webkit-text-fill-color",
                BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code WebkitTextFillColor}. */
        WEBKIT_TEXT_FILL_COLOR_("WebkitTextFillColor", "webkit-text-fill-color",
                BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code -webkit-text-fill-color}. */
        WEBKIT_TEXT_FILL_COLOR__("-webkit-text-fill-color", "webkit-text-fill-color",
                BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code webkitTextOrientation}. */
        WEBKIT_TEXT_ORIENTATION("webkitTextOrientation", "webkit-text-orientation", BrowserConfiguration.chrome("vertical-right")),

        /** The style property {@code webkitTextSecurity}. */
        WEBKIT_TEXT_SECURITY("webkitTextSecurity", "webkit-text-security", BrowserConfiguration.chrome("none")),

        /** The style property {@code webkitTextSizeAdjust}. */
        WEBKIT_TEXT_SIZE_ADJUST("webkitTextSizeAdjust", "webkit-text-size-adjust", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code WebkitTextSizeAdjust}. */
        WEBKIT_TEXT_SIZE_ADJUST_("WebkitTextSizeAdjust", "webkit-text-size-adjust", BrowserConfiguration.ff("auto")),

        /** The style property {@code -webkit-text-size-adjust}. */
        WEBKIT_TEXT_SIZE_ADJUST__("-webkit-text-size-adjust", "webkit-text-size-adjust", BrowserConfiguration.ff("auto")),

        /** The style property {@code webkitTextStroke}. */
        WEBKIT_TEXT_STROKE("webkitTextStroke", "webkit-text-stroke", BrowserConfiguration.chrome(""), BrowserConfiguration.ff("")),

        /** The style property {@code WebkitTextStroke}. */
        WEBKIT_TEXT_STROKE_("WebkitTextStroke", "webkit-text-stroke", BrowserConfiguration.ff("")),

        /** The style property {@code -webkit-text-stroke}. */
        WEBKIT_TEXT_STROKE__("-webkit-text-stroke", "webkit-text-stroke", BrowserConfiguration.ff("")),

        /** The style property {@code webkitTextStrokeColor}. */
        WEBKIT_TEXT_STROKE_COLOR("webkitTextStrokeColor", "webkit-text-stroke-color",
                BrowserConfiguration.chrome("rgb(0, 0, 0)"), BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code WebkitTextStrokeColor}. */
        WEBKIT_TEXT_STROKE_COLOR_("WebkitTextStrokeColor", "webkit-text-stroke-color",
                BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code -webkit-text-stroke-color}. */
        WEBKIT_TEXT_STROKE_COLOR__("-webkit-text-stroke-color", "webkit-text-stroke-color",
                BrowserConfiguration.ff("rgb(0, 0, 0)")),

        /** The style property {@code webkitTextStrokeWidth}. */
        WEBKIT_TEXT_STROKE_WIDTH("webkitTextStrokeWidth", "webkit-text-stroke-width", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("0px")),

        /** The style property {@code WebkitTextStrokeWidth}. */
        WEBKIT_TEXT_STROKE_WIDTH_("WebkitTextStrokeWidth", "webkit-text-stroke-width", BrowserConfiguration.ff("0px")),

        /** The style property {@code -webkit-text-stroke-width}. */
        WEBKIT_TEXT_STROKE_WIDTH__("-webkit-text-stroke-width", "webkit-text-stroke-width", BrowserConfiguration.ff("0px")),

        /** The style property {@code webkitTransform}. */
        WEBKIT_TRANSFORM("webkitTransform", "webkit-transform", BrowserConfiguration.chrome("none"), BrowserConfiguration.ff("none")),

        /** The style property {@code WebkitTransform}. */
        WEBKIT_TRANSFORM_("WebkitTransform", "webkit-transform", BrowserConfiguration.ff("none")),

        /** The style property {@code -webkit-transform}. */
        WEBKIT_TRANSFORM__("-webkit-transform", "webkit-transform", BrowserConfiguration.ff("none")),

        /** The style property {@code webkitTransformOrigin}. */
        WEBKIT_TRANSFORM_ORIGIN("webkitTransformOrigin", "webkit-transform-origin",
                BrowserConfiguration.chrome("620px 161px"), BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code WebkitTransformOrigin}. */
        WEBKIT_TRANSFORM_ORIGIN_("WebkitTransformOrigin", "webkit-transform-origin", BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code -webkit-transform-origin}. */
        WEBKIT_TRANSFORM_ORIGIN__("-webkit-transform-origin", "webkit-transform-origin", BrowserConfiguration.ff("625px 172.5px")),

        /** The style property {@code webkitTransformOriginX}. */
        WEBKIT_TRANSFORM_ORIGIN_X("webkitTransformOriginX", "webkit-transform-origin-x", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitTransformOriginY}. */
        WEBKIT_TRANSFORM_ORIGIN_Y("webkitTransformOriginY", "webkit-transform-origin-y", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitTransformOriginZ}. */
        WEBKIT_TRANSFORM_ORIGIN_Z("webkitTransformOriginZ", "webkit-transform-origin-z", BrowserConfiguration.chrome("")),

        /** The style property {@code webkitTransformStyle}. */
        WEBKIT_TRANSFORM_STYLE("webkitTransformStyle", "webkit-transform-style", BrowserConfiguration.chrome("flat"), BrowserConfiguration.ff("flat")),

        /** The style property {@code WebkitTransformStyle}. */
        WEBKIT_TRANSFORM_STYLE_("WebkitTransformStyle", "webkit-transform-style", BrowserConfiguration.ff("flat")),

        /** The style property {@code -webkit-transform-style}. */
        WEBKIT_TRANSFORM_STYLE__("-webkit-transform-style", "webkit-transform-style", BrowserConfiguration.ff("flat")),

        /** The style property {@code webkitTransition}. */
        WEBKIT_TRANSITION("webkitTransition", "webkit-transition", BrowserConfiguration.chrome("all 0s ease 0s"), BrowserConfiguration.ff("")),

        /** The style property {@code WebkitTransition}. */
        WEBKIT_TRANSITION_("WebkitTransition", "webkit-transition", BrowserConfiguration.ff("")),

        /** The style property {@code -webkit-transition}. */
        WEBKIT_TRANSITION__("-webkit-transition", "webkit-transition", BrowserConfiguration.ff("")),

        /** The style property {@code webkitTransitionDelay}. */
        WEBKIT_TRANSITION_DELAY("webkitTransitionDelay", "webkit-transition-delay", BrowserConfiguration.chrome("0s"), BrowserConfiguration.ff("0s")),

        /** The style property {@code WebkitTransitionDelay}. */
        WEBKIT_TRANSITION_DELAY_("WebkitTransitionDelay", "webkit-transition-delay", BrowserConfiguration.ff("0s")),

        /** The style property {@code -webkit-transition-delay}. */
        WEBKIT_TRANSITION_DELAY__("-webkit-transition-delay", "webkit-transition-delay", BrowserConfiguration.ff("0s")),

        /** The style property {@code webkitTransitionDuration}. */
        WEBKIT_TRANSITION_DURATION("webkitTransitionDuration", "webkit-transition-duration", BrowserConfiguration.chrome("0s"), BrowserConfiguration.ff("0s")),

        /** The style property {@code WebkitTransitionDuration}. */
        WEBKIT_TRANSITION_DURATION_("WebkitTransitionDuration", "webkit-transition-duration", BrowserConfiguration.ff("0s")),

        /** The style property {@code -webkit-transition-duration}. */
        WEBKIT_TRANSITION_DURATION__("-webkit-transition-duration", "webkit-transition-duration", BrowserConfiguration.ff("0s")),

        /** The style property {@code webkitTransitionProperty}. */
        WEBKIT_TRANSITION_PROPERTY("webkitTransitionProperty", "webkit-transition-property", BrowserConfiguration.chrome("all"), BrowserConfiguration.ff("all")),

        /** The style property {@code WebkitTransitionProperty}. */
        WEBKIT_TRANSITION_PROPERTY_("WebkitTransitionProperty", "webkit-transition-property", BrowserConfiguration.ff("all")),

        /** The style property {@code -webkit-transition-property}. */
        WEBKIT_TRANSITION_PROPERTY__("-webkit-transition-property", "webkit-transition-property", BrowserConfiguration.ff("all")),

        /** The style property {@code webkitTransitionTimingFunction}. */
        WEBKIT_TRANSITION_TIMING_FUNCTION("webkitTransitionTimingFunction", "webkit-transition-timing-function",
                BrowserConfiguration.chrome("ease"), BrowserConfiguration.ff("ease")),

        /** The style property {@code WebkitTransitionTimingFunction}. */
        WEBKIT_TRANSITION_TIMING_FUNCTION_("WebkitTransitionTimingFunction", "webkit-transition-timing-function",
                BrowserConfiguration.ff("ease")),

        /** The style property {@code -webkit-transition-timing-function}. */
        WEBKIT_TRANSITION_TIMING_FUNCTION__("-webkit-transition-timing-function", "webkit-transition-timing-function",
                BrowserConfiguration.ff("ease")),

        /** The style property {@code webkitUserDrag}. */
        WEBKIT_USER_DRAG("webkitUserDrag", "webkit-user-drag", BrowserConfiguration.chrome("auto")),

        /** The style property {@code webkitUserModify}. */
        WEBKIT_USER_MODIFY("webkitUserModify", "webkit-user-modify", BrowserConfiguration.chrome("read-only")),

        /** The style property {@code webkitUserSelect}. */
        WEBKIT_USER_SELECT("webkitUserSelect", "webkit-user-select", BrowserConfiguration.chrome("auto"), BrowserConfiguration.ff("auto")),

        /** The style property {@code WebkitUserSelect}. */
        WEBKIT_USER_SELECT_("WebkitUserSelect", "webkit-user-select", BrowserConfiguration.ff("auto")),

        /** The style property {@code -webkit-user-select}. */
        WEBKIT_USER_SELECT__("-webkit-user-select", "webkit-user-select", BrowserConfiguration.ff("auto")),

        /** The style property {@code webkitWritingMode}. */
        WEBKIT_WRITING_MODE("webkitWritingMode", "webkit-writing-mode", BrowserConfiguration.chrome("horizontal-tb")),

        /** The style property {@code whiteSpace}. */
        WHITE_SPACE("whiteSpace", "white-space", BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal"), BrowserConfiguration.ie("normal")),

        /** The style property {@code white-space}. */
        WHITE_SPACE_("white-space", "white-space", BrowserConfiguration.ff("normal")),

        /** The style property {@code widows}. */
        WIDOWS("widows", "widows", BrowserConfiguration.ie("2"), BrowserConfiguration.chrome("2")),

        /** The style property {@code width}. */
        WIDTH("width", "width", BrowserConfiguration.chrome(""), BrowserConfiguration.ff(""), BrowserConfiguration.ie("")),

        /** The style property {@code willChange}. */
        WILL_CHANGE("willChange", "will-change", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto")),

        /** The style property {@code will-change}. */
        WILL_CHANGE_("will-change", "will-change", BrowserConfiguration.ff("auto")),

        /** The style property {@code wordBreak}. */
        WORD_BREAK("wordBreak", "word-break", BrowserConfiguration.ff("normal"), BrowserConfiguration.ie("normal"), BrowserConfiguration.chrome("normal")),

        /** The style property {@code word-break}. */
        WORD_BREAK_("word-break", "word-break", BrowserConfiguration.ff("normal")),

        /** The style property {@code wordSpacing}. */
        WORD_SPACING("wordSpacing", "word-spacing", BrowserConfiguration.chrome("0px"), BrowserConfiguration.ff("0px"), BrowserConfiguration.ie("0px")),

        /** The style property {@code word-spacing}. */
        WORD_SPACING_("word-spacing", "word-spacing", BrowserConfiguration.ff("0px")),

        /** The style property {@code wordWrap}. */
        WORD_WRAP("wordWrap", "word-wrap", BrowserConfiguration.ie(""), BrowserConfiguration.chrome("normal"), BrowserConfiguration.ff("normal")),

        /** The style property {@code word-wrap}. */
        WORD_WRAP_("word-wrap", "word-wrap", BrowserConfiguration.ff("normal")),

        /** The style property {@code writingMode}. */
        WRITING_MODE("writingMode", "writing-mode", BrowserConfiguration.ie("undefined"), BrowserConfiguration.chrome("horizontal-tb"), BrowserConfiguration.ff("horizontal-tb")),

        /** The style property {@code writing-mode}. */
        WRITING_MODE_("writing-mode", "writing-mode", BrowserConfiguration.ff("horizontal-tb")),

        /** The style property {@code x}. */
        X("x", "x", BrowserConfiguration.chrome("0px")),

        /** The style property {@code y}. */
        Y("y", "y", BrowserConfiguration.chrome("0px")),

        /** The style property {@code zIndex}. */
        Z_INDEX("zIndex", "z-index", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto"), BrowserConfiguration.ie("auto")),

        /** The style property {@code z-index}. */
        Z_INDEX_("z-index", "z-index", BrowserConfiguration.ff("auto"), BrowserConfiguration.chrome("auto").setIteratable(false),
                BrowserConfiguration.ie("auto").setIteratable(false)),

        /** The style property {@code zoom}. */
        ZOOM("zoom", "zoom", BrowserConfiguration.ie("undefined"), BrowserConfiguration.chrome("1"));

        private final String propertyName_;
        private final String attributeName_;
        private final BrowserConfiguration[] browserConfigurations_;

        Definition(final String propertyName, final String attributeName,
                final BrowserConfiguration... browserConfigurations) {
            propertyName_ = propertyName;
            attributeName_ = attributeName;
            browserConfigurations_ = browserConfigurations;
        }

        boolean isAvailable(final BrowserVersion browserVersion, final boolean onlyIfIteratable) {
            if (browserConfigurations_ == null) {
                return true; // defined for all browsers
            }

            final BrowserConfiguration config
                = BrowserConfiguration.getMatchingConfiguration(browserVersion, browserConfigurations_);
            return config != null && (!onlyIfIteratable || config.isIteratable());
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

        /**
         * @param browserVersion the browser version
         * @return the default value for this attribute
         */
        public String getDefaultComputedValue(final BrowserVersion browserVersion) {
            final BrowserConfiguration config
                = BrowserConfiguration.getMatchingConfiguration(browserVersion, browserConfigurations_);
            if (config == null) {
                return "";
            }
            return config.getDefaultValue();
        }
    }
}
