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

package tookox.core.client

import td.TdApi.Error

class TdException(val error: Error) : RuntimeException() {

    constructor(code: Int, message: String) : this(Error(code, message))

    constructor(message: String) : this(-1, message)

    val code: Int
        get() = error.code

    override val message: String
        get() = error.message

    override fun toString(): String {
        return "$code : $message"
    }

}