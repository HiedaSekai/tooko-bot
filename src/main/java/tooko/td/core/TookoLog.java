package tooko.td.core;

import cn.hutool.core.date.*;
import cn.hutool.core.lang.*;
import cn.hutool.core.util.*;
import cn.hutool.http.cookie.*;
import cn.hutool.log.*;
import cn.hutool.log.dialect.console.*;
import cn.hutool.log.level.*;
import tooko.*;
import tooko.main.*;

public class TookoLog extends ConsoleLog {

    public TookoLog(String name) {

        super(name);
    }

    public TookoLog(Class<?> clazz) {

        super(clazz.getSimpleName());
    }

    @Override
    public void log(String fqcn, Level level, Throwable t, String format, Object... arguments) {

        if (!isEnabled(level)) return;

        final Dict dict = Dict.create().set("date", DateUtil.now()).set("level", level.toString()).set("name", this.getName()).set("msg", StrUtil.format(format, arguments));

        String logFormat = "[{level}] {name}: {msg}";
        String logMsg = StrUtil.format(logFormat, dict);

        if (level.ordinal() >= Level.WARN.ordinal()) {

            Console.error(t, logMsg);

            if (Launcher.INSTANCE != null && Launcher.INSTANCE.status.get() && Launcher.INSTANCE.hasAuthed()) {

                if (t != null) logMsg += "\n\n" + Fn.parseError(t);

                Launcher.INSTANCE.syncI(Fn.sendText(Env.LOG_CHANNEL, Fn.plainText(logMsg)));

            }

        } else {

            Console.log(t, logMsg);

        }


    }

    public static class Factory extends LogFactory {

        public Factory() {

            super("Tooko");

        }

        @Override
        public Log createLog(String name) {

            return new TookoLog(name);

        }

        @Override
        public Log createLog(Class<?> clazz) {

            if (clazz.equals(GlobalCookieManager.class)) {

                return new TookoLog(clazz) {

                    @Override
                    public boolean isDebugEnabled() {

                        return false;

                    }

                    @Override
                    public void debug(String fqcn, Throwable t, String format, Object... arguments) {

                    }

                };

            }

            return new TookoLog(clazz);

        }

    }

}

