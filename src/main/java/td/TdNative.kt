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