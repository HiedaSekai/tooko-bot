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

package td

@Suppress("unused")
object TdNative {

    @JvmStatic
    external fun createNativeClient(): Long

    @JvmStatic
    external fun nativeClientSend(nativeClientId: Long, eventId: Long, function: TdApi.Function)

    @JvmStatic
    external fun nativeClientReceive(nativeClientId: Long, eventIds: LongArray, events: Array<TdApi.Object?>, timeout: Double): Int

    @JvmStatic
    external fun nativeClientExecute(function: TdApi.Function): TdApi.Object

    @JvmStatic
    external fun destroyNativeClient(nativeClientId: Long)

}