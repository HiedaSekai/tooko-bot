package tooko.main.lang;

import tooko.main.*;

public class ENG extends Lang {

    public static ENG INSTANCE = new ENG();

    {

        CHOOSE_LANG = "Please select a language :";

        LANG_SELECTED = "English language is <b>set</b>.\nPlease note that translators are volunteers, and this " + "localization <i>may be incomplete</i>.You can contact the " + Fn.mention("developer", Env.DEVELOPER) + " to help improve the translations.";

        FN_PING_RESULT = "pong！";

        STICKER_CAPTION = "StickerId : <code>{}</code>\nEmoji : <code>{}</code>\nSetId : <code>{}</code>";

        STICKER_EXPORT = "Download Sticker Set";
        STICKER_EXPORT_DL = "Downloading <b>{}</b> {} / {}";
        STICKER_EXPORT_PACK = "Converting images...";
        STICKER_EXPORT_SEND = "Sending the .zip file, may take some time. Check back later!";
        STICKER_EXPORT_OTHER = "Someone is downloading this sticker set, please try again later.";
        STICKER_EXPORT_WAIT = "You currently have a task running, please wait until the task is completed.";
        STICKER_EXPORT_FLOOD = "There are too many people downloading at the same time. Please try again later.";

        PERMISSION_DENIED = "Permissions denied.";

        ACTIVE_THREADS = "Active Threads";
        MEM_TOTAL = "Total Memory";
        MEM_USED = "Used Memory";
        MEM_CACHED = "Cached Memory";
        MEM_FREE = "Free Memory";

        LOCAL_VERSION = "Local Version";
        REMOTE_VERSION = "Remote Version";
        VER_UPDATE = "Auto Update";
        AUTO_UPDATE = "Pull Update";
        HOOK_UPDATE = "Hook";

        ENABLE = "Enable";
        DISABLE = "Disable";

        REFRESH = "Refresh";
        STOP = "Stop";
        RESTART = "Restart";

        CHECK_UPDATE = "Check Update";
        UPDATE_NOW = "Update";

        ENABLE_AUTO_UPDATE = ENABLE + " " + AUTO_UPDATE;
        DISABLE_AUTO_UPDATE = DISABLE + " " + AUTO_UPDATE;

        ENABLE_AUTO_UPDATE = ENABLE + " " + HOOK_UPDATE;
        DISABLE_AUTO_UPDATE = DISABLE + " " + HOOK_UPDATE;

        REFRESHED = "Refreshed";

        NEW_UPDATE_AVILABLE = "New Update Avilable";
        NO_UPDATE = "No Avilable Update";

        ENABLED = "Enabled";
        DISABLED = "Disabled";

        STATUS_ENABLE = ENABLED;
        STATUS_DISABLE = DISABLED;

        RESTARTING = "Restarting...";
        UPDATING = "Updating...";

        SERVER_CLOSING = "Err : server us closing.";

        UPDATE_FAILED = "Update Failed :\n\n{}";

        PM_WELCOME = "Hello!\n\nYou can contact us using this bot.";

    }

    public ENG() {

        super("English");
    }

}