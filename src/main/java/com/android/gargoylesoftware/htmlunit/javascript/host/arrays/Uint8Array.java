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
package com.android.gargoylesoftware.htmlunit.javascript.host.arrays;

import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstant;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.host.Window;

/**
 * Represents an array of unsigned 16-bit integers.
 *
 * @author Ahmed Ashour
 * @author Frank Danek
 * @author Ronald Brill
 * @author Michael Rimov
 */
@JsxClass
public class Uint8Array extends ArrayBufferViewBase {

    /**
     * Ctor.
     */
    public Uint8Array() {
        super();
    }

    /**
     * Ctor.
     *
     * @param bytes the bytes to store
     * @param window the context
     */
    public Uint8Array(final byte[] bytes, final Window window) {
        this();
        setPrototype(window.getPrototype(getClass()));
        setParentScope(getParentScope());

        setByteLength(bytes.length);

        final ArrayBuffer buffer = new ArrayBuffer(bytes);
        buffer.setPrototype(window.getPrototype(buffer.getClass()));
        buffer.setParentScope(getParentScope());
        setBuffer(buffer);
    }

    /** The size, in bytes, of each array element. */
    @JsxConstant
    public static final int BYTES_PER_ELEMENT = 1;

    /**
     * {@inheritDoc}
     */
    @Override
    @JsxConstructor
    public void constructor(final Object object, final Object byteOffset, final Object length) {
        super.constructor(object, byteOffset, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] toByteArray(final Number number) {
        if (number == null || Double.isInfinite(number.doubleValue())) {
            return new byte[] {0};
        }
        return new byte[] {number.byteValue()};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object fromArray(final byte[] array, final int offset) {
        if (offset < 0 || offset >= array.length) {
            return NOT_FOUND;
        }
        return array[offset] & 0xFF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getBytesPerElement() {
        return BYTES_PER_ELEMENT;
    }
}