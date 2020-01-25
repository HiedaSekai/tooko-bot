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

package tooko.twitter.login.test

import org.openqa.selenium.By
import org.openqa.selenium.Cookie
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import tooko.core.env.Fn
import tooko.lottie.mkDriver
import tooko.lottie.waitForId
import tooko.lottie.waitForTag
import java.util.*

object TokenAddTest {

    @JvmStatic
    fun main(args: Array<String>) {

        checkTwitterAuthToken("<hide>")

    }

    val userPage = "//*[@id=\"react-root\"]/div/div/div/header/div/div/div/div/div[2]/nav/a[7]"

    fun checkTwitterAuthToken(authToken: String) {

        val driver = mkDriver()

        driver.get("https://twitter.com")

        driver.manage().addCookie(Cookie("auth_token", authToken, ".twitter.com", "/",
                Date(System.currentTimeMillis() + 5 * 365 * 24 * 60 * 60 * 1000L)
                , true, true))

        driver.get("https://twitter.com/home")

        WebDriverWait(driver, 10).until(

                ExpectedConditions.or(
                        ExpectedConditions.urlContains("login"),
                        ExpectedConditions.elementToBeClickable(By.xpath(userPage))
                )

        )

        check(!driver.currentUrl.contains("login")) { "Invalid Token." }

        val screenName = driver.findElementByXPath(userPage).getAttribute("href").substringAfterLast("/")

        val apiToken = Fn.mkApi("z6FQzXfFNeiEr9XmYuJTxD9Qp", "ALzp3YJ2uPQHyxfrNxNGfQRQNKKtiQTYHvw30mwKe3NueO0DnY")

        val requestToken = apiToken.getOAuthRequestToken("oob")

        driver.get(requestToken.authenticationURL)

        driver.waitForId("allow").click()

        val code = driver.waitForTag("code").text

        val accessToken = apiToken.getOAuthAccessToken(code)

    }

}