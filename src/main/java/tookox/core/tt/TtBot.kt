package tookox.core.tt

import chat.tamtam.botapi.TamTamBotAPI
import chat.tamtam.botapi.model.*
import cn.hutool.core.thread.ThreadUtil
import tooko.main.Env
import tookox.core.applyIf
import tookox.core.createLog
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

open class TtBot(val botToken: String) : TtHandler() {

    override var client: TtBot = this

    val api = TamTamBotAPI.create(botToken)

    val status = AtomicBoolean(false)

    private val handlers = LinkedList<TtHandler>()

    fun addHandler(handler: TtHandler) {

        handler.client = this

        handlers.add(handler)

        handler.onLoad()

    }

    fun clearHandlers() {

        handlers.clear()

    }

    fun start() {

        if (status.get()) return

        clearHandlers()

        handlers.add(this)

        onLoad()

        synchronized(postAdd) {

            postAdd.add(this)

        }

    }

    fun stop() {

        if (!status.get()) return

        status.set(false)

        handlers.forEach { it.onDestroy() }

        synchronized(postDestroy) { postDestroy.add(this) }

    }

    companion object {

        private val clients = LinkedList<TtBot>()

        val log = createLog("TT")

        val postAdd = LinkedList<TtBot>()
        val postDestroy = LinkedList<TtBot>()

        val updateThread = Thread {

            val markers = hashMapOf<TtBot, Long>()

            while (!Env.STOP.get()) {

                synchronized(postDestroy) {

                    val iter = postDestroy.iterator()

                    while (iter.hasNext()) {

                        val toDestroy = iter.next()

                        clients.remove(toDestroy)

                        iter.remove()

                    }

                }

                synchronized(postAdd) {

                    val iter = postAdd.iterator()

                    while (iter.hasNext()) {

                        val toAdd = iter.next()

                        clients.add(toAdd)

                        iter.remove()

                    }

                }

                if (clients.isEmpty()) {

                    ThreadUtil.safeSleep(2333L)

                    continue

                }

                val start = System.currentTimeMillis()

                clients.forEach { tt: TtBot ->

                    tt.api.updates.applyIf(markers.containsKey(tt)) {

                        marker(markers[tt])

                    }.execute().apply {

                        val marker = marker

                        if (marker != null) {

                            markers[tt] = marker

                        }

                    }.updates.forEach {

                        tt.handlers.forEach { handler: TtHandler ->

                            handler.apply {

                                when (it) {

                                    is MessageCreatedUpdate -> onMessageCreated(it.message, it.timestamp)

                                    is MessageCallbackUpdate -> onMessageCallback(it.callback, it.message, it.userLocale, it.timestamp)

                                    is MessageEditedUpdate -> onMessageEdited(it.message, it.timestamp)

                                    is MessageRemovedUpdate -> onMessageRemoved(it.messageId, it.chatId, it.userId, it.timestamp)

                                    is BotAddedToChatUpdate -> onBotAddedToChat(it.chatId, it.user, it.timestamp)

                                    is BotRemovedFromChatUpdate -> onBotRemovedFromChat(it.chatId, it.user, it.timestamp)

                                    is UserAddedToChatUpdate -> onUserAddedToChatUpdate(it.chatId, it.user, it.inviterId, it.timestamp)

                                    is BotStartedUpdate -> onBotStarted(it.chatId, it.user, it.payload, it.userLocale, it.timestamp)

                                    is ChatTitleChangedUpdate -> onChatTitleChanged(it.chatId, it.user, it.title, it.timestamp)

                                    is MessageConstructionRequest -> onConstructionRequest(it.user, it.userLocale, it.sessionId, it.userLocale, it.input, it.timestamp)

                                    is MessageConstructedUpdate -> onConstructed(it.sessionId, it.message, it.timestamp)

                                }

                            }

                        }

                    }

                }

                if (System.currentTimeMillis() - start < 1000L) {

                    ThreadUtil.safeSleep(1000L - System.currentTimeMillis() - start)

                }

            }

        }

    }

}