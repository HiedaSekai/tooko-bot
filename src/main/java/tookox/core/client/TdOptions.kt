package tookox.core.client

import tooko.main.Env
import tooko.td.TdApi.TdlibParameters

class TdOptions {

    private var useTestDc = false
    private var databaseDirectory = Env.getPath("data")
    private var filesDirectory = Env.getPath("cache/files")
    private var useFileDatabase = true
    private var useChatInfoDatabase = true
    private var useMessageDatabase = true
    private var useSecretChats = true
    private var apiId = 971882
    private var apiHash = "1232533dd027dc2ec952ba91fc8e3f27"
    private var systemLanguageCode = "en"
    private var deviceModel = "Tooko"
    private var systemVersion = "/"
    private var applicationVersion = "1.0"
    private var enableStorageOptimizer = false
    private var ignoreFileNames = false

    fun build(): TdlibParameters {

        return TdlibParameters().also {

            it.useTestDc = useTestDc
            it.databaseDirectory = databaseDirectory
            it.filesDirectory = filesDirectory
            it.useFileDatabase = useFileDatabase
            it.useChatInfoDatabase = useChatInfoDatabase
            it.useMessageDatabase = useMessageDatabase
            it.useSecretChats = useSecretChats
            it.apiId = apiId
            it.apiHash = apiHash
            it.systemLanguageCode = systemLanguageCode
            it.deviceModel = deviceModel
            it.systemVersion = systemVersion
            it.applicationVersion = applicationVersion
            it.enableStorageOptimizer = enableStorageOptimizer
            it.ignoreFileNames = ignoreFileNames

        }
    }

    fun useTestDc(useTestDc: Boolean): TdOptions {
        this.useTestDc = useTestDc
        return this
    }

    fun databaseDirectory(databaseDirectory: String): TdOptions {
        this.databaseDirectory = databaseDirectory
        return this
    }

    fun useFileDatabase(useFileDatabase: Boolean): TdOptions {
        this.useFileDatabase = useFileDatabase
        return this
    }

    fun filesDirectory(filesDirectory: String): TdOptions {
        this.filesDirectory = filesDirectory
        return this
    }

    fun useChatInfoDatabase(useChatInfoDatabase: Boolean): TdOptions {
        this.useChatInfoDatabase = useChatInfoDatabase
        return this
    }

    fun useMessageDatabase(useMessageDatabase: Boolean): TdOptions {
        this.useMessageDatabase = useMessageDatabase
        return this
    }

    fun useSecretChats(useSecretChats: Boolean): TdOptions {
        this.useSecretChats = useSecretChats
        return this
    }

    fun apiId(apiId: Int): TdOptions {
        this.apiId = apiId
        return this
    }

    fun apiHash(apiHash: String): TdOptions {
        this.apiHash = apiHash
        return this
    }

    fun systemLanguageCode(systemLanguageCode: String): TdOptions {
        this.systemLanguageCode = systemLanguageCode
        return this
    }

    fun deviceModel(deviceModel: String): TdOptions {
        this.deviceModel = deviceModel
        return this
    }

    fun systemVersion(systemVersion: String): TdOptions {
        this.systemVersion = systemVersion
        return this
    }

    fun applicationVersion(applicationVersion: String): TdOptions {
        this.applicationVersion = applicationVersion
        return this
    }

    fun enableStorageOptimizer(enableStorageOptimizer: Boolean): TdOptions {
        this.enableStorageOptimizer = enableStorageOptimizer
        return this
    }

    fun ignoreFileNames(ignoreFileNames: Boolean): TdOptions {
        this.ignoreFileNames = ignoreFileNames
        return this
    }
}