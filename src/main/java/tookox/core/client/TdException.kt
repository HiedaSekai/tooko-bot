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