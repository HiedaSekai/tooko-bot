/*
 * Copyright (c) 2019 - 2020 KazamaWataru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tookox.lottie

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

fun mkDriver() = ChromeDriver(ChromeOptions().addArguments(
        "--no-sandbox",
        // "--headless",
        "user-agent=\"Mozilla/5.0 (Linux; Android 9; KazamaWataru) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.116 Mobile Safari/537.36\"",
        "--hide-scrollbars",
        "--mute-audio",
        "--disable-background-networking",
        "--enable-features=NetworkService,NetworkServiceInProcess",
        "--disable-background-timer-throttling",
        "--disable-backgrounding-occluded-windows",
        "--disable-breakpad",
        "--disable-client-side-phishing-detection",
        "--disable-component-extensions-with-background-pages",
        "--disable-default-apps",
        "--disable-dev-shm-usage",
        "--disable-extensions",
        "--disable-features=TranslateUI",
        "--disable-hang-monitor",
        "--disable-ipc-flooding-protection",
        "--disable-popup-blocking",
        "--disable-prompt-on-repost",
        "--disable-renderer-backgrounding",
        "--disable-sync",
        "--force-color-profile=srgb",
        "--metrics-recording-only",
        "--no-first-run",
        "--enable-automation",
        "--password-store=basic",
        "--use-mock-keychain"))

fun WebDriver.waitForId(id: String): WebElement {

    return WebDriverWait(this, 10)
            .until(ExpectedConditions.elementToBeClickable(By.id(id)))

}

fun WebDriver.waitForClass(id: String): WebElement {

    return WebDriverWait(this, 10)
            .until(ExpectedConditions.elementToBeClickable(By.className(id)))

}

fun WebDriver.waitForSelector(id: String): WebElement {

    return WebDriverWait(this, 10)
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(id)))

}

fun WebDriver.execute(script: String, vararg args: Any?) = (this as JavascriptExecutor).executeScript(script, args)
fun WebDriver.executeAsync(script: String, vararg args: Any?) = (this as JavascriptExecutor).executeAsyncScript(script, args)

fun WebDriver.getVar(name: String) = execute("return $name")
fun WebDriver.getNum(name: String) = getVar(name) as Long