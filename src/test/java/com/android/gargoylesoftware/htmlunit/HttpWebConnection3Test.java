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
package com.android.gargoylesoftware.htmlunit;

import java.net.URLEncoder;
import java.util.Arrays;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Tests using the {@link PrimitiveWebServer}.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@RunWith(BrowserRunner.class)
public class HttpWebConnection3Test extends WebDriverTestCase {

    private PrimitiveWebServer primitiveWebServer_;

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = {HttpHeader.HOST, HttpHeader.CONNECTION, HttpHeader.UPGRADE_INSECURE_REQUESTS,
                        HttpHeader.USER_AGENT,
                        HttpHeader.ACCEPT, HttpHeader.ACCEPT_ENCODING, HttpHeader.ACCEPT_LANGUAGE},
            FF = {HttpHeader.HOST, HttpHeader.USER_AGENT, HttpHeader.ACCEPT, HttpHeader.ACCEPT_LANGUAGE,
                        HttpHeader.ACCEPT_ENCODING, HttpHeader.CONNECTION, HttpHeader.UPGRADE_INSECURE_REQUESTS},
            IE = {HttpHeader.ACCEPT, HttpHeader.ACCEPT_LANGUAGE, HttpHeader.USER_AGENT,
                        HttpHeader.ACCEPT_ENCODING, HttpHeader.HOST, HttpHeader.CONNECTION})
    public void headers() throws Exception {
        final String response = "HTTP/1.1 200 OK\r\n"
            + "Content-Length: 2\r\n"
            + "Content-Type: text/plain\r\n"
            + "\r\n"
            + "Hi";

        primitiveWebServer_ = new PrimitiveWebServer(PORT, response);
        primitiveWebServer_.start();
        final WebDriver driver = getWebDriver();

        driver.get("http://localhost:" + PORT);
        final String request = primitiveWebServer_.getRequests().get(0);
        final String[] headers = request.split("\\r\\n");
        final String[] result = new String[headers.length - 1];
        for (int i = 0; i < result.length; i++) {
            final String header = headers[i + 1];
            result[i] = header.substring(0, header.indexOf(':'));
        }
        assertEquals(Arrays.asList(getExpectedAlerts()).toString(), Arrays.asList(result).toString());
    }

    /**
     * @throws Exception if an error occurs
     */
    @After
    public void stopServer() throws Exception {
        if (primitiveWebServer_ != null) {
            primitiveWebServer_.stop();
        }
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.Alerts(CHROME = {HttpHeader.HOST, HttpHeader.CONNECTION, HttpHeader.UPGRADE_INSECURE_REQUESTS,
                        HttpHeader.USER_AGENT,
                        HttpHeader.ACCEPT, HttpHeader.REFERER, HttpHeader.ACCEPT_ENCODING, HttpHeader.ACCEPT_LANGUAGE,
                        HttpHeader.COOKIE},
            FF = {HttpHeader.HOST, HttpHeader.USER_AGENT, HttpHeader.ACCEPT, HttpHeader.ACCEPT_LANGUAGE,
                        HttpHeader.ACCEPT_ENCODING, HttpHeader.REFERER, HttpHeader.COOKIE, HttpHeader.CONNECTION,
                        HttpHeader.UPGRADE_INSECURE_REQUESTS},
            IE = {HttpHeader.ACCEPT, HttpHeader.REFERER, HttpHeader.ACCEPT_LANGUAGE, HttpHeader.USER_AGENT,
                        HttpHeader.ACCEPT_ENCODING, HttpHeader.HOST, HttpHeader.CONNECTION,
                        HttpHeader.COOKIE})
    public void headers_cookie_referer() throws Exception {
        final String htmlResponse = "<a href='2.html'>Click me</a>";
        final String response = "HTTP/1.1 200 OK\r\n"
            + "Content-Length: " + htmlResponse.length() + "\r\n"
            + "Content-Type: text/html\r\n"
            + "Set-Cookie: name=value\r\n"
            + "\r\n"
            + htmlResponse;

        primitiveWebServer_ = new PrimitiveWebServer(PORT, response);
        primitiveWebServer_.start();
        final WebDriver driver = getWebDriver();

        driver.get("http://localhost:" + PORT);
        driver.findElement(By.linkText("Click me")).click();

        final Wait<WebDriver> wait = new WebDriverWait(driver, 5);
        wait.until(currentUrlContains("2.html"));

        int index = 1;
        String request;
        do {
            request = primitiveWebServer_.getRequests().get(index++);
        }
        while (request.contains("/favicon.ico"));

        final String[] headers = request.split("\\r\\n");
        final String[] result = new String[headers.length - 1];
        for (int i = 0; i < result.length; i++) {
            final String header = headers[i + 1];
            result[i] = header.substring(0, header.indexOf(':'));
        }
        assertEquals(Arrays.asList(getExpectedAlerts()).toString(), Arrays.asList(result).toString());
    }

    /**
     * An expectation for checking that the current url contains a case-sensitive substring.
     *
     * @param url the fragment of url expected
     * @return true when the url matches, false otherwise
     */
    public static ExpectedCondition<Boolean> currentUrlContains(final String url) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver driver) {
                final String currentUrl = driver.getCurrentUrl();
                return currentUrl != null && currentUrl.contains(url);
            }
        };
    }

    /**
     * Test for bug #1898.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("§§URL§§?????")
    // seems to work only when running alone
    public void locationUTF() throws Exception {
        final String response = "HTTP/1.1 302 Found\r\n"
                + "Content-Length: 0\r\n"
                + "Location: " +  URL_FIRST + "\u0623\u0647\u0644\u0627\u064b" + "\r\n"
                + "\r\n";

        final String response2 = "HTTP/1.1 200 OK\r\n"
                + "Content-Length: 2\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "Hi";

        expandExpectedAlertsVariables(URL_FIRST);

        primitiveWebServer_ = new PrimitiveWebServer(PORT, response, response2);
        primitiveWebServer_.start();

        final WebDriver driver = getWebDriver();
        driver.get(URL_FIRST.toExternalForm());
        assertEquals(getExpectedAlerts()[0], driver.getCurrentUrl());
        assertTrue(driver.getPageSource().contains("Hi"));

        assertEquals(2, primitiveWebServer_.getRequests().size());
    }

    /**
     * Test for bug #1898.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("§§URL§§test?%D8%A3%D9%87%D9%84%D8%A7%D9%8B")
    public void locationQueryUTF8Encoded() throws Exception {
        final String response = "HTTP/1.1 302 Found\r\n"
                + "Content-Length: 0\r\n"
                + "Location: "
                    +  URL_FIRST
                    + "test?"
                    + URLEncoder.encode("\u0623\u0647\u0644\u0627\u064b", "UTF-8")
                    + "\r\n"
                + "\r\n";

        final String response2 = "HTTP/1.1 200 OK\r\n"
                + "Content-Length: 2\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "Hi";

        expandExpectedAlertsVariables(URL_FIRST);

        primitiveWebServer_ = new PrimitiveWebServer(PORT, response, response2);
        primitiveWebServer_.start();

        final WebDriver driver = getWebDriver();
        driver.get(URL_FIRST.toExternalForm());
        assertEquals(getExpectedAlerts()[0], driver.getCurrentUrl());
        assertTrue(driver.getPageSource().contains("Hi"));

        assertEquals(2, primitiveWebServer_.getRequests().size());
    }

    /**
     * Test for bug #1898.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @BrowserRunner.Alerts("§§URL§§%D8%A3%D9%87%D9%84%D8%A7%D9%8B")
    public void locationUTF8Encoded() throws Exception {
        final String response = "HTTP/1.1 302 Found\r\n"
                + "Content-Length: 0\r\n"
                + "Location: "
                    +  URL_FIRST
                    + URLEncoder.encode("\u0623\u0647\u0644\u0627\u064b", "UTF-8")
                    + "\r\n"
                + "\r\n";

        final String response2 = "HTTP/1.1 200 OK\r\n"
                + "Content-Length: 2\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "Hi";

        expandExpectedAlertsVariables(URL_FIRST);

        primitiveWebServer_ = new PrimitiveWebServer(PORT, response, response2);
        primitiveWebServer_.start();

        final WebDriver driver = getWebDriver();
        driver.get(URL_FIRST.toExternalForm());
        assertEquals(getExpectedAlerts()[0], driver.getCurrentUrl());
        assertTrue(driver.getPageSource().contains("Hi"));

        assertEquals(2, primitiveWebServer_.getRequests().size());
    }

    /**
     * Test case for Bug #1882.
     *
     * @throws Exception if the test fails
     */
    @Test
    @BrowserRunner.NotYetImplemented
    public void queryString() throws Exception {
        final String response = "HTTP/1.1 302 Found\r\n"
                + "Content-Length: 0\r\n"
                + "\r\n";

        primitiveWebServer_ = new PrimitiveWebServer(PORT, response);
        primitiveWebServer_.start();

        final WebDriver driver = getWebDriver();

        driver.get("http://localhost:" + PORT + "?para=%u65E5");
        assertTrue(primitiveWebServer_.getRequests().get(0),
                    primitiveWebServer_.getRequests().get(0).contains("para=%u65E5"));
    }
}