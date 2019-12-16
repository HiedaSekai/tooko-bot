package tooko.td.client;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.log.Log;
import tooko.Launcher;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.td.Client;
import tooko.td.TdApi;
import tooko.td.TdApi.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("EmptyMethod")
public class TdClient extends TdHandler {

    public static Timer mainTimer = new Timer("Mian Timer");
    public static LinkedList<TdClient> clients = new LinkedList<>();
    public static final LinkedList<TdClient> postAdd = new LinkedList<>();
    public static final LinkedList<TdClient> postDestroy = new LinkedList<>();

    public static ThreadPoolExecutor publicPool = new ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    public static ThreadPoolExecutor asyncPool = new ThreadPoolExecutor(8, 8, 15, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public Log log = Launcher.log;
    public Client client = new Client();
    public AtomicBoolean status;
    public User me;

    public LinkedList<TdHandler> handlers = new LinkedList<>();

    private AtomicLong requestId = new AtomicLong(1);
    private ReentrantLock executionLock = new ReentrantLock();
    private ConcurrentHashMap<Long, TdCallback<?>> callbacks = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, TdCallback<?>> messages = new ConcurrentHashMap<>();
    private AtomicBoolean hasAuth = new AtomicBoolean(false);
    private SetTdlibParameters params;

    public TdClient(TdOptions options) {

        params = new SetTdlibParameters(options.build());

    }

    public <T extends TdHandler> T findHandler(Class<T> clazz) {

        for (TdHandler handler : handlers) {

            if (clazz.isInstance(handler))
                return (T) handler;

        }

        throw new IllegalStateException("Hanlder " + clazz.getName() + " not found !");

    }

    public void addHandler(TdHandler handler) {

        handlers.add(handler);

        handler.client = this;

        handler.onLoad();

    }

    public void clearListeners() {

        handlers.clear();

    }

    public boolean hasAuthed() {

        return hasAuth.get();

    }

    @Override
    public void onAuthorizationState(AuthorizationState authorizationState) {

        if (authorizationState instanceof AuthorizationStateWaitTdlibParameters) {

            send(params);

        } else if (authorizationState instanceof AuthorizationStateWaitEncryptionKey) {

            send(new CheckDatabaseEncryptionKey());

        } else if (authorizationState instanceof AuthorizationStateReady) {

            try {

                me = execute(new GetMe());

            } catch (TdException e) {

                try {

                    me = execute(new GetMe());

                } catch (TdException ex) {

                    log.error(e);

                    Fn.finishEvent();

                }

            }

            log.debug("认证完成 : {}", Fn.displayName(me));

            hasAuth.set(true);

            for (TdHandler handler : handlers) handler.onLogin();

        } else if (authorizationState instanceof AuthorizationStateLoggingOut) {

            onLoggingOut();

        }


    }

    public void onLoggingOut() {
    }


    public <T extends TdApi.Object> T E(Function function) {

        try {

            return execute(function);

        } catch (TdException e) {

            return null;

        }

    }

    @Override
    public <T extends TdApi.Object> T execute(Function function) throws TdException {

        if (this.executionLock.isLocked()) {

            throw new IllegalStateException("ClientActor is destroyed");

        }

        final AtomicReference<TdApi.Object> responseAtomicReference = new AtomicReference<>();
        final AtomicBoolean executedAtomicBoolean = new AtomicBoolean(false);

        send(function, new ReturnBack<T>() {

            @Override
            public void onCallback(boolean isOk, T result, TdApi.Error error) {

                if (isOk) {

                    responseAtomicReference.set(result);

                } else {

                    responseAtomicReference.set(error);

                }

                executedAtomicBoolean.set(true);

            }

        });

        while (!executedAtomicBoolean.get()) {

            if (Env.STOP.get()) {

                throw new TdException(new TdApi.Error(-1, "Server Stopped"));

            }

        }

        TdApi.Object response = responseAtomicReference.get();

        if (response instanceof TdApi.Error) {

            throw new TdException((TdApi.Error) response);

        }

        return (T) response;

    }

    @Override
    public void send(Function function, TdCallback<?> callback) {

        if (this.executionLock.isLocked()) {

            throw new IllegalStateException("ClientActor is destroyed");

        }

        long requestId = this.requestId.getAndIncrement();

        callbacks.put(requestId, callback);

        send(requestId, function);

    }

    @Override
    public long send(Function function) {

        if (this.executionLock.isLocked()) {

            throw new IllegalStateException("Client is destroyed");

        }

        long requestId = this.requestId.getAndIncrement();

        callbacks.put(requestId, new LogCallback());

        send(requestId, function);

        return requestId;

    }

    public void send(long requestId, Function function) {

        if (this.executionLock.isLocked()) {

            throw new IllegalStateException("Client is destroyed");

        }

        client.send(requestId, function);

    }

    @Override
    public void send(SendMessage function, final TdCallback<Message> callback) {

        send((Function) function, new ReturnBack<Message>() {

            @Override
            public void onCallback(boolean isOk, Message result, TdApi.Error error) {

                if (isOk) messages.put(result.id, callback);

            }

        });

    }

    @Override
    public void sync(SendMessage function, final TdCallback<Message> callback) {

        try {

            callback.onCallback(true, sync(function), null);

        } catch (TdException e) {

            callback.onCallback(false, null, e.getError());

        }

    }

    @Override
    public Message sync(SendMessage function) throws TdException {

        final AtomicReference<TdApi.Object> responseAtomicReference = new AtomicReference<>();
        final AtomicBoolean executedAtomicBoolean = new AtomicBoolean(false);

        send(function, new ReturnBack<Message>() {

            @Override
            public void onCallback(boolean isOk, Message result, TdApi.Error error) {

                if (isOk) {

                    responseAtomicReference.set(result);

                } else {

                    responseAtomicReference.set(error);

                }

                executedAtomicBoolean.set(true);

            }

        });

        while (!executedAtomicBoolean.get()) {
        }

        TdApi.Object response = responseAtomicReference.get();

        if (response instanceof TdApi.Error) {

            throw new TdException((TdApi.Error) response);

        }

        return (Message) response;

    }

    @Override
    public void onMessageSendSucceeded(Message message, long oldMessageId) {

        if (!messages.containsKey(oldMessageId)) return;

        try {

            TdCallback<?> callback = messages.remove(oldMessageId);

            callR(message, callback, !(callback instanceof ReturnBack));

        } catch (Exception e) {

            log.error(e, "TdError - Sync");

        }

    }

    @Override
    public void onMessageSendFailed(Message message, long oldMessageId, int errorCode, String errorMessage) {

        if (!messages.containsKey(oldMessageId)) return;

        try {

            TdCallback<?> callback = messages.remove(oldMessageId);

            callR(new TdApi.Error(errorCode, errorMessage), callback, !(callback instanceof ReturnBack));

        } catch (Exception e) {

            log.error(e, "TdError - Sync");

        }

    }

    public void start() {

        stop();

        clearListeners();

        this.status = new AtomicBoolean(true);

        addHandler(this);

        synchronized (postAdd) {

            postAdd.add(this);

        }

    }

    public void processEvent(final Client.Event event) {

        // log.debug("Event : {}",event.object.getClass().getSimpleName());

        if (event.requestId != 0L) {

            if (!callbacks.containsKey(event.requestId)) {

                if (event.object instanceof TdApi.Error) {

                    TdApi.Error err = (TdApi.Error) event.object;

                    log.warn(err.code + " " + err.message);

                }

                return;

            }

            try {

                TdCallback<?> callback = callbacks.remove(event.requestId);

                callR(event.object, callback, !(callback instanceof ReturnBack));

            } catch (Exception e) {

                log.error(e, "TdError - Sync");

            }

            return;

        } else if (event.object instanceof UpdateMessageSendSucceeded || event.object instanceof UpdateMessageSendFailed) {

            callU((Update) event.object);

            return;

        }

        long chatId = -1;

        if (event.object instanceof UpdateNewMessage) {

            Message message = ((UpdateNewMessage) event.object).message;

            chatId = message.chatId;

        } else if (event.object instanceof UpdateNewCallbackQuery) {

            UpdateNewCallbackQuery query = (UpdateNewCallbackQuery) event.object;

            chatId = query.chatId;

        } else if (event.object instanceof UpdateNewInlineQuery) {

            UpdateNewInlineQuery query = (UpdateNewInlineQuery) event.object;

            chatId = query.senderUserId;

        }

        (chatId == -1 ? asyncPool : publicPool).execute(new Runnable() {

            @Override
            public void run() {

                callU((Update) event.object);

            }

        });

    }

    private void callU(Update update) {

        for (TdHandler handler : handlers) {

            try {

                handler.processEvent(update);

            } catch (Break ignored) {

                break;

            } catch (Exception e) {

                log.error(e, "TdError - Sync");

            }

        }

    }

    void callR(final TdApi.Object object, final TdCallback<?> callback, boolean async) {

        if (async) {

            asyncPool.execute(new Runnable() {

                ;

                @Override
                public void run() {

                    callR(object, callback, false);

                }

            });

        } else {

            if (object instanceof TdApi.Error) {

                callback.onCallback(false, null, (TdApi.Error) object);

            } else {

                ((TdCallback<TdApi.Object>) callback).onCallback(true, object, null);

            }

        }

    }

    public void stop() {

        if (status == null) return;

        for (TdHandler hanlder : handlers) {

            hanlder.onDestroy();

        }

        status.set(false);

        synchronized (postDestroy) {

            postDestroy.add(this);

        }

        while (!status.get()) ThreadUtil.sleep(10);

        status = null;

    }

    public void destroy() {

        if (status != null) {

            stop();

        }

        executionLock.lock();

        client.destroyClient();

    }

    interface ReturnBack<T extends TdApi.Object> extends TdCallback<T> {
    }

    public static class EventTask extends Thread {

        @Override
        public void run() {

            while (true) {

                synchronized (postAdd) {

                    Iterator<TdClient> iter = postAdd.iterator();

                    while (iter.hasNext()) {

                        TdClient toAdd = iter.next();

                        clients.add(toAdd);

                        iter.remove();

                    }

                }

                synchronized (postDestroy) {

                    Iterator<TdClient> iter = postDestroy.iterator();

                    while (iter.hasNext()) {

                        TdClient toDestroy = iter.next();

                        clients.remove(toDestroy);

                        toDestroy.status.set(true);

                        iter.remove();

                    }

                }

                if (clients.isEmpty()) break;

                for (TdClient client : clients) {

                    LinkedList<Client.Event> responseList = client.client.receive(0, 4);

                    for (Client.Event event : responseList) client.processEvent(event);

                }

            }

        }
    }

    public class LogCallback implements TdCallback<TdApi.Object> {

        public StackTraceElement[] stackTrace;

        {

            stackTrace = ThreadUtil.getStackTrace();

            stackTrace = ArrayUtil.sub(stackTrace, 3, stackTrace.length);


        }

        @Override
        public void onCallback(boolean isOk, TdApi.Object result, TdApi.Error error) {

            if (!isOk) {

                TdException exception = new TdException(error);

                exception.setStackTrace(stackTrace);

                log.error(exception);

            }

        }

    }

}
