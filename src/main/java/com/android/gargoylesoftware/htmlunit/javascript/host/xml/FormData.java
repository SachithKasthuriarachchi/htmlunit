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
package com.android.gargoylesoftware.htmlunit.javascript.host.xml;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.android.gargoylesoftware.htmlunit.FormEncodingType;
import com.android.gargoylesoftware.htmlunit.WebRequest;
import com.android.gargoylesoftware.htmlunit.javascript.SimpleScriptable;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.JsxFunction;
import com.android.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser;
import com.android.gargoylesoftware.htmlunit.util.KeyDataPair;
import com.android.gargoylesoftware.htmlunit.util.NameValuePair;
import org.apache.android.commons.lang3.StringUtils;

import com.android.gargoylesoftware.htmlunit.javascript.host.file.File;
import com.android.gargoylesoftware.htmlunit.javascript.host.html.HTMLFormElement;

import net.sourceforge.htmlunit.corejs.javascript.Context;
import net.sourceforge.htmlunit.corejs.javascript.Scriptable;

/**
 * A JavaScript object for {@code FormData}.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@JsxClass
public class FormData extends SimpleScriptable {

    private final List<NameValuePair> requestParameters_ = new ArrayList<>();

    /**
     * Default constructor.
     */
    public FormData() {
    }

    /**
     * Constructor.
     * @param formObj a form
     */
    @JsxConstructor
    public FormData(final Object formObj) {
        if (formObj instanceof HTMLFormElement) {
            final HTMLFormElement form = (HTMLFormElement) formObj;
            requestParameters_.addAll(form.getHtmlForm().getParameterListForSubmit(null));
        }
    }

    /**
     * Appends a new value onto an existing key inside a {@code FormData} object,
     * or adds the key if it does not already exist.
     * @param name the name of the field whose data is contained in {@code value}
     * @param value the field's value
     * @param filename the filename reported to the server (optional)
     */
    @JsxFunction
    public void append(final String name, final Object value, final Object filename) {
        if (value instanceof File) {
            final File file = (File) value;
            String fileName = null;
            String contentType = null;
            if (filename instanceof String) {
                fileName = (String) filename;
            }
            contentType = file.getType();
            if (StringUtils.isEmpty(contentType)) {
                contentType = "application/octet-stream";
            }
            requestParameters_.add(new KeyDataPair(name, file.getFile(), fileName, contentType, (Charset) null));
        }
        else {
            requestParameters_.add(new NameValuePair(name, Context.toString(value)));
        }
    }

    /**
     * Removes the entry (if exists).
     * @param name the name of the field to remove
     */
    @JsxFunction(functionName = "delete", value = {SupportedBrowser.FF, SupportedBrowser.CHROME})
    public void delete_js(final String name) {
        if (StringUtils.isEmpty(name)) {
            return;
        }

        final Iterator<NameValuePair> iter = requestParameters_.iterator();
        while (iter.hasNext()) {
            final NameValuePair pair = iter.next();
            if (name.equals(pair.getName())) {
                iter.remove();
            }
        }
    }

    /**
     * @param name the name of the field to check
     * @return the first value found for the give name
     */
    @JsxFunction({SupportedBrowser.CHROME, SupportedBrowser.FF})
    public String get(final String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        final Iterator<NameValuePair> iter = requestParameters_.iterator();
        while (iter.hasNext()) {
            final NameValuePair pair = iter.next();
            if (name.equals(pair.getName())) {
                return pair.getValue();
            }
        }
        return null;
    }

    /**
     * @param name the name of the field to check
     * @return the first value found for the give name
     */
    @JsxFunction({SupportedBrowser.CHROME, SupportedBrowser.FF})
    public Scriptable getAll(final String name) {
        if (StringUtils.isEmpty(name)) {
            return Context.getCurrentContext().newArray(this, 0);
        }

        final List<Object> values = new ArrayList<>();
        final Iterator<NameValuePair> iter = requestParameters_.iterator();
        while (iter.hasNext()) {
            final NameValuePair pair = iter.next();
            if (name.equals(pair.getName())) {
                values.add(pair.getValue());
            }
        }

        final Object[] stringValues = values.toArray(new Object[values.size()]);
        return Context.getCurrentContext().newArray(this, stringValues);
    }

    /**
     * @param name the name of the field to check
     * @return true if the name exists
     */
    @JsxFunction({SupportedBrowser.CHROME, SupportedBrowser.FF})
    public boolean has(final String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }

        final Iterator<NameValuePair> iter = requestParameters_.iterator();
        while (iter.hasNext()) {
            final NameValuePair pair = iter.next();
            if (name.equals(pair.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets a new value for an existing key inside a {@code FormData} object,
     * or adds the key if it does not already exist.
     * @param name the name of the field whose data is contained in {@code value}
     * @param value the field's value
     * @param filename the filename reported to the server (optional)
     */
    @JsxFunction({SupportedBrowser.CHROME, SupportedBrowser.FF})
    public void set(final String name, final Object value, final Object filename) {
        if (StringUtils.isEmpty(name)) {
            return;
        }

        int pos = -1;

        final Iterator<NameValuePair> iter = requestParameters_.iterator();
        int idx = 0;
        while (iter.hasNext()) {
            final NameValuePair pair = iter.next();
            if (name.equals(pair.getName())) {
                iter.remove();
                if (pos < 0) {
                    pos = idx;
                }
            }
            idx++;
        }

        if (pos < 0) {
            pos = requestParameters_.size();
        }

        if (value instanceof File) {
            final File file = (File) value;
            String fileName = null;
            if (filename instanceof String) {
                fileName = (String) filename;
            }
            requestParameters_.add(pos,
                    new KeyDataPair(name, file.getFile(), fileName, file.getType(), (Charset) null));
        }
        else {
            requestParameters_.add(pos, new NameValuePair(name, Context.toString(value)));
        }
    }

    /**
     * Sets the specified request with the parameters in this {@code FormData}.
     * @param webRequest the web request to fill
     */
    public void fillRequest(final WebRequest webRequest) {
        webRequest.setEncodingType(FormEncodingType.MULTIPART);
        webRequest.setRequestParameters(requestParameters_);
    }
}
