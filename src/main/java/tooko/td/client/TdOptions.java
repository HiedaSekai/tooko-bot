package tooko.td.client;

import tooko.main.Env;
import tooko.td.TdApi;

public class TdOptions {

    private TdApi.TdlibParameters parameters;
    private boolean useTestDc = false;
    private String databaseDirectory = Env.getPath("data");
    private String filesDirectory = Env.getPath("cache/files");
    private boolean useFileDatabase = true;
    private boolean useChatInfoDatabase = true;
    private boolean useMessageDatabase = true;
    private boolean useSecretChats = true;
    private int apiId = 971882;
    private String apiHash = "1232533dd027dc2ec952ba91fc8e3f27";
    private String systemLanguageCode = "en";
    private String deviceModel = "Tooko";
    private String systemVersion = "/";
    private String applicationVersion = "1.0";
    private boolean enableStorageOptimizer = false;
    private boolean ignoreFileNames = false;

    TdApi.TdlibParameters build() {

        if (parameters == null) {

            parameters = new TdApi.TdlibParameters();
            parameters.useTestDc = this.useTestDc;
            parameters.databaseDirectory = this.databaseDirectory;
            parameters.filesDirectory = this.filesDirectory;
            parameters.useFileDatabase = this.useFileDatabase;
            parameters.useChatInfoDatabase = this.useChatInfoDatabase;
            parameters.useMessageDatabase = this.useMessageDatabase;
            parameters.useSecretChats = useSecretChats;
            parameters.apiId = this.apiId;
            parameters.apiHash = this.apiHash;
            parameters.systemLanguageCode = this.systemLanguageCode;
            parameters.deviceModel = this.deviceModel;
            parameters.systemVersion = this.systemVersion;
            parameters.applicationVersion = this.applicationVersion;
            parameters.enableStorageOptimizer = this.enableStorageOptimizer;
            parameters.ignoreFileNames = this.ignoreFileNames;

        }

        return parameters;

    }

    public TdOptions useTestDc(boolean useTestDc) {

        this.useTestDc = useTestDc;

        return this;

    }

    public TdOptions databaseDirectory(String databaseDirectory) {

        this.databaseDirectory = databaseDirectory;

        return this;

    }

    public TdOptions useFileDatabase(boolean useFileDatabase) {

        this.useFileDatabase = useFileDatabase;

        return this;

    }

    public TdOptions filesDirectory(String filesDirectory) {

        this.filesDirectory = filesDirectory;

        return this;

    }

    public TdOptions useChatInfoDatabase(boolean useChatInfoDatabase) {

        this.useChatInfoDatabase = useChatInfoDatabase;

        return this;

    }

    public TdOptions useMessageDatabase(boolean useMessageDatabase) {

        this.useMessageDatabase = useMessageDatabase;

        return this;

    }

    public TdOptions useSecretChats(boolean useSecretChats) {

        this.useSecretChats = useSecretChats;

        return this;

    }

    public TdOptions apiId(int apiId) {

        this.apiId = apiId;

        return this;

    }

    public TdOptions apiHash(String apiHash) {

        this.apiHash = apiHash;

        return this;

    }

    public TdOptions systemLanguageCode(String systemLanguageCode) {

        this.systemLanguageCode = systemLanguageCode;

        return this;

    }

    public TdOptions deviceModel(String deviceModel) {

        this.deviceModel = deviceModel;

        return this;

    }

    public TdOptions systemVersion(String systemVersion) {

        this.systemVersion = systemVersion;

        return this;

    }

    public TdOptions applicationVersion(String applicationVersion) {

        this.applicationVersion = applicationVersion;

        return this;

    }

    public TdOptions enableStorageOptimizer(boolean enableStorageOptimizer) {

        this.enableStorageOptimizer = enableStorageOptimizer;

        return this;

    }

    public TdOptions ignoreFileNames(boolean ignoreFileNames) {

        this.ignoreFileNames = ignoreFileNames;

        return this;

    }

}
