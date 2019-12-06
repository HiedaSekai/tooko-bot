package tooko.main.manage;

import tooko.main.update.UpdateTask;
import tooko.td.core.CacheTable;

public class SysConfig {

    public static CacheTable<String, DB> DATA = new CacheTable<>("sys", DB.class);
    private static String KEY_AUTO_UPDATE = "auto_update";
    private static String KEY_HOOK_UPDATE = "hook_update";

    public static boolean isAutoUpdateEnable() {

        return DATA.containsId(KEY_AUTO_UPDATE);

    }

    public static boolean isHookUpdateEnable() {

        return DATA.containsId(KEY_HOOK_UPDATE);

    }

    public static void setAutoUpdate(boolean enable) {

        if (enable) {

            UpdateTask.start();

            DATA.setById(KEY_AUTO_UPDATE, new DB(KEY_AUTO_UPDATE, true));

        } else {

            UpdateTask.stop();

            DATA.deleteById(KEY_AUTO_UPDATE);

        }

    }

    public static void setHookUpdate(boolean enable) {

        if (enable) {

            DATA.setById(KEY_HOOK_UPDATE, new DB(KEY_HOOK_UPDATE, true));

        } else {

            DATA.deleteById(KEY_HOOK_UPDATE);

        }

    }

    public static class DB {

        public String id;

        public Long long_value;
        public Boolean bool_value;
        public String str_value;

        public DB(String id, long longValue) {
            this.id = id;
            this.long_value = longValue;
        }

        public DB(String id, boolean boolValue) {
            this.id = id;
            this.bool_value = boolValue;
        }

        public DB(String id, String strValue) {
            this.id = id;
            this.str_value = strValue;
        }


    }

}
