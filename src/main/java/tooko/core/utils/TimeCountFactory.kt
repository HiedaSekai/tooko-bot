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

@file:Suppress("unused")

package tooko.core.utils

import tooko.core.defaultLog

fun mkTimeCount() = TimeCountFactory()

class TimeCountFactory {

    var last = System.currentTimeMillis()

    fun printTime(prefix: String = "Time: ") {

        val now = System.currentTimeMillis()

        defaultLog.debug("$prefix${(now - last) / (1000f)}s")

        last = now

    }

}