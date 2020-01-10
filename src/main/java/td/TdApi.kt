@file:Suppress(
    "unused"
)

package td

import tookox.core.*
import org.bson.codecs.pojo.annotations.*

class TdApi {

    abstract class Object {
        external override fun toString(): String
        abstract val constructor: Int
    }

    abstract class Function : Object() {
        external override fun toString(): String
    }

    /**
     * An object of this type can be returned on every function call, in case of an error
     *
     * @code - Error code
     *         Subject to future changes
     *         If the error code is 406, the error message must not be processed in any way and must not be displayed to the user
     * @message - Error message
     *            Subject to future changes
     */
    class Error : Object {

        var code: Int by WeakField()
        var message: String by WeakField()

        constructor()

        constructor(code: Int, message: String) {

            this.code = code
            this.message = message

        }

        override val constructor: Int @BsonIgnore get() = -1679978726

    }


    /**
     * An object of this type is returned on a successful function call for certain functions
     */
    class Ok : Object() {

        override val constructor: Int @BsonIgnore get() = -722616727

    }


    /**
     * Contains parameters for TDLib initialization
     *
     * @useTestDc - If set to true, the Telegram test environment will be used instead of the production environment
     * @databaseDirectory - The path to the directory for the persistent database
     *                      If empty, the current working directory will be used
     * @filesDirectory - The path to the directory for storing files
     *                   If empty, database_directory will be used
     * @useFileDatabase - If set to true, information about downloaded and uploaded files will be saved between application restarts
     * @useChatInfoDatabase - If set to true, the library will maintain a cache of users, basic groups, supergroups, channels and secret chats
     *                        Implies use_file_database
     * @useMessageDatabase - If set to true, the library will maintain a cache of chats and messages
     *                       Implies use_chat_info_database
     * @useSecretChats - If set to true, support for secret chats will be enabled
     * @apiId - Application identifier for Telegram API access, which can be obtained at https://my.telegram.org
     * @apiHash - Application identifier hash for Telegram API access, which can be obtained at https://my.telegram.org
     * @systemLanguageCode - IETF language tag of the user's operating system language
     * @deviceModel - Model of the device the application is being run on
     * @systemVersion - Version of the operating system the application is being run on
     * @applicationVersion - Application version
     * @enableStorageOptimizer - If set to true, old files will automatically be deleted
     * @ignoreFileNames - If set to true, original file names will be ignored
     *                    Otherwise, downloaded files will be saved under names as close as possible to the original name
     */
    class TdlibParameters : Object {

        var useTestDc: Boolean by WeakField()
        var databaseDirectory: String by WeakField()
        var filesDirectory: String by WeakField()
        var useFileDatabase: Boolean by WeakField()
        var useChatInfoDatabase: Boolean by WeakField()
        var useMessageDatabase: Boolean by WeakField()
        var useSecretChats: Boolean by WeakField()
        var apiId: Int by WeakField()
        var apiHash: String by WeakField()
        var systemLanguageCode: String by WeakField()
        var deviceModel: String by WeakField()
        var systemVersion: String by WeakField()
        var applicationVersion: String by WeakField()
        var enableStorageOptimizer: Boolean by WeakField()
        var ignoreFileNames: Boolean by WeakField()

        constructor()

        constructor(useTestDc: Boolean, databaseDirectory: String, filesDirectory: String, useFileDatabase: Boolean, useChatInfoDatabase: Boolean, useMessageDatabase: Boolean, useSecretChats: Boolean, apiId: Int, apiHash: String, systemLanguageCode: String, deviceModel: String, systemVersion: String, applicationVersion: String, enableStorageOptimizer: Boolean, ignoreFileNames: Boolean) {

            this.useTestDc = useTestDc
            this.databaseDirectory = databaseDirectory
            this.filesDirectory = filesDirectory
            this.useFileDatabase = useFileDatabase
            this.useChatInfoDatabase = useChatInfoDatabase
            this.useMessageDatabase = useMessageDatabase
            this.useSecretChats = useSecretChats
            this.apiId = apiId
            this.apiHash = apiHash
            this.systemLanguageCode = systemLanguageCode
            this.deviceModel = deviceModel
            this.systemVersion = systemVersion
            this.applicationVersion = applicationVersion
            this.enableStorageOptimizer = enableStorageOptimizer
            this.ignoreFileNames = ignoreFileNames

        }

        override val constructor: Int @BsonIgnore get() = -761520773

    }


    /**
     * Provides information about the method by which an authentication code is delivered to the user
     */
    abstract class AuthenticationCodeType : Object()

    /**
     * An authentication code is delivered via a private Telegram message, which can be viewed in another client
     *
     * @length - Length of the code
     */
    class AuthenticationCodeTypeTelegramMessage : AuthenticationCodeType {

        var length: Int by WeakField()

        constructor()

        constructor(length: Int) {

            this.length = length

        }

        override val constructor: Int @BsonIgnore get() = 2079628074

    }


    /**
     * An authentication code is delivered via an SMS message to the specified phone number
     *
     * @length - Length of the code
     */
    class AuthenticationCodeTypeSms : AuthenticationCodeType {

        var length: Int by WeakField()

        constructor()

        constructor(length: Int) {

            this.length = length

        }

        override val constructor: Int @BsonIgnore get() = 962650760

    }


    /**
     * An authentication code is delivered via a phone call to the specified phone number
     *
     * @length - Length of the code
     */
    class AuthenticationCodeTypeCall : AuthenticationCodeType {

        var length: Int by WeakField()

        constructor()

        constructor(length: Int) {

            this.length = length

        }

        override val constructor: Int @BsonIgnore get() = 1636265063

    }


    /**
     * An authentication code is delivered by an immediately cancelled call to the specified phone number
     * The number from which the call was made is the code
     *
     * @pattern - Pattern of the phone number from which the call will be made
     */
    class AuthenticationCodeTypeFlashCall : AuthenticationCodeType {

        var pattern: String by WeakField()

        constructor()

        constructor(pattern: String) {

            this.pattern = pattern

        }

        override val constructor: Int @BsonIgnore get() = 1395882402

    }


    /**
     * Information about the authentication code that was sent
     *
     * @phoneNumber - A phone number that is being authenticated
     * @type - Describes the way the code was sent to the user
     * @nextType - Describes the way the next code will be sent to the user
     * @timeout - Timeout before the code should be re-sent, in seconds
     */
    class AuthenticationCodeInfo : Object {

        var phoneNumber: String by WeakField()
        var type: AuthenticationCodeType by WeakField()
        var nextType: AuthenticationCodeType? = null
        var timeout: Int by WeakField()

        constructor()

        constructor(phoneNumber: String, type: AuthenticationCodeType, nextType: AuthenticationCodeType? = null, timeout: Int) {

            this.phoneNumber = phoneNumber
            this.type = type
            this.nextType = nextType
            this.timeout = timeout

        }

        override val constructor: Int @BsonIgnore get() = -860345416

    }


    /**
     * Information about the email address authentication code that was sent
     *
     * @emailAddressPattern - Pattern of the email address to which an authentication code was sent
     * @length - Length of the code
     *           0 if unknown
     */
    class EmailAddressAuthenticationCodeInfo : Object {

        var emailAddressPattern: String by WeakField()
        var length: Int by WeakField()

        constructor()

        constructor(emailAddressPattern: String, length: Int) {

            this.emailAddressPattern = emailAddressPattern
            this.length = length

        }

        override val constructor: Int @BsonIgnore get() = 1151066659

    }


    /**
     * Represents a part of the text that needs to be formatted in some unusual way
     *
     * @offset - Offset of the entity in UTF-16 code units
     * @length - Length of the entity, in UTF-16 code units
     * @type - Type of the entity
     */
    class TextEntity : Object {

        var offset: Int by WeakField()
        var length: Int by WeakField()
        var type: TextEntityType by WeakField()

        constructor()

        constructor(offset: Int, length: Int, type: TextEntityType) {

            this.offset = offset
            this.length = length
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -1951688280

    }


    /**
     * Contains a list of text entities
     *
     * @entities - List of text entities
     */
    class TextEntities : Object {

        var entities: Array<TextEntity> by WeakField()

        constructor()

        constructor(entities: Array<TextEntity>) {

            this.entities = entities

        }

        override val constructor: Int @BsonIgnore get() = -933199172

    }


    /**
     * A text with some entities
     *
     * @text - The text
     * @entities - Entities contained in the text
     *             Entities can be nested, but must not mutually intersect with each other
     *             Pre, Code and PreCode entities can't contain other entities
     *             Bold, Italic, Underline and Strikethrough entities can contain and to be contained in all other entities
     *             All other entities can't contain each other
     */
    class FormattedText : Object {

        var text: String by WeakField()
        var entities: Array<TextEntity> by WeakField()

        constructor()

        constructor(text: String, entities: Array<TextEntity>) {

            this.text = text
            this.entities = entities

        }

        override val constructor: Int @BsonIgnore get() = -252624564

    }


    /**
     * Contains Telegram terms of service
     *
     * @text - Text of the terms of service
     * @minUserAge - The minimum age of a user to be able to accept the terms
     *               0 if any
     * @showPopup - True, if a blocking popup with terms of service must be shown to the user
     */
    class TermsOfService : Object {

        var text: FormattedText by WeakField()
        var minUserAge: Int by WeakField()
        var showPopup: Boolean by WeakField()

        constructor()

        constructor(text: FormattedText, minUserAge: Int, showPopup: Boolean) {

            this.text = text
            this.minUserAge = minUserAge
            this.showPopup = showPopup

        }

        override val constructor: Int @BsonIgnore get() = 739422597

    }


    /**
     * Represents the current authorization state of the client
     */
    abstract class AuthorizationState : Object()

    /**
     * TDLib needs TdlibParameters for initialization
     */
    class AuthorizationStateWaitTdlibParameters : AuthorizationState() {

        override val constructor: Int @BsonIgnore get() = 904720988

    }


    /**
     * TDLib needs an encryption key to decrypt the local database
     *
     * @isEncrypted - True, if the database is currently encrypted
     */
    class AuthorizationStateWaitEncryptionKey : AuthorizationState {

        var isEncrypted: Boolean by WeakField()

        constructor()

        constructor(isEncrypted: Boolean) {

            this.isEncrypted = isEncrypted

        }

        override val constructor: Int @BsonIgnore get() = 612103496

    }


    /**
     * TDLib needs the user's phone number to authorize
     * Call `setAuthenticationPhoneNumber` to provide the phone number, or use `requestQrCodeAuthentication`, or `checkAuthenticationBotToken` for other authentication options
     */
    class AuthorizationStateWaitPhoneNumber : AuthorizationState() {

        override val constructor: Int @BsonIgnore get() = 306402531

    }


    /**
     * TDLib needs the user's authentication code to authorize
     *
     * @codeInfo - Information about the authorization code that was sent
     */
    class AuthorizationStateWaitCode : AuthorizationState {

        var codeInfo: AuthenticationCodeInfo by WeakField()

        constructor()

        constructor(codeInfo: AuthenticationCodeInfo) {

            this.codeInfo = codeInfo

        }

        override val constructor: Int @BsonIgnore get() = 52643073

    }


    /**
     * The user needs to confirm authorization on another logged in device by scanning a QR code with the provided link
     *
     * @link - A tg:// URL for the QR code
     *         The link will be updated frequently
     */
    class AuthorizationStateWaitOtherDeviceConfirmation : AuthorizationState {

        var link: String by WeakField()

        constructor()

        constructor(link: String) {

            this.link = link

        }

        override val constructor: Int @BsonIgnore get() = 860166378

    }


    /**
     * The user is unregistered and need to accept terms of service and enter their first name and last name to finish registration
     *
     * @termsOfService - Telegram terms of service
     */
    class AuthorizationStateWaitRegistration : AuthorizationState {

        var termsOfService: TermsOfService by WeakField()

        constructor()

        constructor(termsOfService: TermsOfService) {

            this.termsOfService = termsOfService

        }

        override val constructor: Int @BsonIgnore get() = 550350511

    }


    /**
     * The user has been authorized, but needs to enter a password to start using the application
     *
     * @passwordHint - Hint for the password
     * @hasRecoveryEmailAddress - True, if a recovery email address has been set up
     * @recoveryEmailAddressPattern - Pattern of the email address to which the recovery email was sent
     *                                Empty until a recovery email has been sent
     */
    class AuthorizationStateWaitPassword : AuthorizationState {

        var passwordHint: String? = null
        var hasRecoveryEmailAddress: Boolean by WeakField()
        var recoveryEmailAddressPattern: String by WeakField()

        constructor()

        constructor(passwordHint: String? = null, hasRecoveryEmailAddress: Boolean, recoveryEmailAddressPattern: String) {

            this.passwordHint = passwordHint
            this.hasRecoveryEmailAddress = hasRecoveryEmailAddress
            this.recoveryEmailAddressPattern = recoveryEmailAddressPattern

        }

        override val constructor: Int @BsonIgnore get() = 187548796

    }


    /**
     * The user has been successfully authorized
     * TDLib is now ready to answer queries
     */
    class AuthorizationStateReady : AuthorizationState() {

        override val constructor: Int @BsonIgnore get() = -1834871737

    }


    /**
     * The user is currently logging out
     */
    class AuthorizationStateLoggingOut : AuthorizationState() {

        override val constructor: Int @BsonIgnore get() = 154449270

    }


    /**
     * TDLib is closing, all subsequent queries will be answered with the error 500
     * Note that closing TDLib can take a while
     * All resources will be freed only after authorizationStateClosed has been received
     */
    class AuthorizationStateClosing : AuthorizationState() {

        override val constructor: Int @BsonIgnore get() = 445855311

    }


    /**
     * TDLib client is in its final state
     * All databases are closed and all resources are released
     * No other updates will be received after this
     * All queries will be responded to with error code 500
     * To continue working, one should create a new instance of the TDLib client
     */
    class AuthorizationStateClosed : AuthorizationState() {

        override val constructor: Int @BsonIgnore get() = 1526047584

    }


    /**
     * Represents the current state of 2-step verification
     *
     * @hasPassword - True, if a 2-step verification password is set
     * @passwordHint - Hint for the password
     * @hasRecoveryEmailAddress - True, if a recovery email is set
     * @hasPassportData - True, if some Telegram Passport elements were saved
     * @recoveryEmailAddressCodeInfo - Information about the recovery email address to which the confirmation email was sent
     */
    class PasswordState : Object {

        var hasPassword: Boolean by WeakField()
        var passwordHint: String? = null
        var hasRecoveryEmailAddress: Boolean by WeakField()
        var hasPassportData: Boolean by WeakField()
        var recoveryEmailAddressCodeInfo: EmailAddressAuthenticationCodeInfo? = null

        constructor()

        constructor(hasPassword: Boolean, passwordHint: String? = null, hasRecoveryEmailAddress: Boolean, hasPassportData: Boolean, recoveryEmailAddressCodeInfo: EmailAddressAuthenticationCodeInfo? = null) {

            this.hasPassword = hasPassword
            this.passwordHint = passwordHint
            this.hasRecoveryEmailAddress = hasRecoveryEmailAddress
            this.hasPassportData = hasPassportData
            this.recoveryEmailAddressCodeInfo = recoveryEmailAddressCodeInfo

        }

        override val constructor: Int @BsonIgnore get() = -1154797731

    }


    /**
     * Contains information about the current recovery email address
     *
     * @recoveryEmailAddress - Recovery email address
     */
    class RecoveryEmailAddress : Object {

        var recoveryEmailAddress: String by WeakField()

        constructor()

        constructor(recoveryEmailAddress: String) {

            this.recoveryEmailAddress = recoveryEmailAddress

        }

        override val constructor: Int @BsonIgnore get() = 1290526187

    }


    /**
     * Returns information about the availability of a temporary password, which can be used for payments
     *
     * @hasPassword - True, if a temporary password is available
     * @validFor - Time left before the temporary password expires, in seconds
     */
    class TemporaryPasswordState : Object {

        var hasPassword: Boolean by WeakField()
        var validFor: Int by WeakField()

        constructor()

        constructor(hasPassword: Boolean, validFor: Int) {

            this.hasPassword = hasPassword
            this.validFor = validFor

        }

        override val constructor: Int @BsonIgnore get() = 939837410

    }


    /**
     * Represents a local file
     *
     * @path - Local path to the locally available file part
     * @canBeDownloaded - True, if it is possible to try to download or generate the file
     * @canBeDeleted - True, if the file can be deleted
     * @isDownloadingActive - True, if the file is currently being downloaded (or a local copy is being generated by some other means)
     * @isDownloadingCompleted - True, if the local copy is fully available
     * @downloadOffset - Download will be started from this offset
     *                   Downloaded_prefix_size is calculated from this offset
     * @downloadedPrefixSize - If is_downloading_completed is false, then only some prefix of the file starting from download_offset is ready to be read
     *                         Downloaded_prefix_size is the size of that prefix
     * @downloadedSize - Total downloaded file bytes
     *                   Should be used only for calculating download progress
     *                   The actual file size may be bigger, and some parts of it may contain garbage
     */
    class LocalFile : Object {

        var path: String? = null
        var canBeDownloaded: Boolean by WeakField()
        var canBeDeleted: Boolean by WeakField()
        var isDownloadingActive: Boolean by WeakField()
        var isDownloadingCompleted: Boolean by WeakField()
        var downloadOffset: Int by WeakField()
        var downloadedPrefixSize: Int by WeakField()
        var downloadedSize: Int by WeakField()

        constructor()

        constructor(path: String? = null, canBeDownloaded: Boolean, canBeDeleted: Boolean, isDownloadingActive: Boolean, isDownloadingCompleted: Boolean, downloadOffset: Int, downloadedPrefixSize: Int, downloadedSize: Int) {

            this.path = path
            this.canBeDownloaded = canBeDownloaded
            this.canBeDeleted = canBeDeleted
            this.isDownloadingActive = isDownloadingActive
            this.isDownloadingCompleted = isDownloadingCompleted
            this.downloadOffset = downloadOffset
            this.downloadedPrefixSize = downloadedPrefixSize
            this.downloadedSize = downloadedSize

        }

        override val constructor: Int @BsonIgnore get() = -1166400317

    }


    /**
     * Represents a remote file
     *
     * @id - Remote file identifier
     *       Can be used across application restarts or even from other devices for the current user
     *       Uniquely identifies a file, but a file can have a lot of different valid identifiers
     *       If the ID starts with "http://" or "https://", it represents the HTTP URL of the file
     *       TDLib is currently unable to download files if only their URL is known
     *       If downloadFile is called on such a file or if it is sent to a secret chat, TDLib starts a file generation process by sending updateFileGenerationStart to the client with the HTTP URL in the original_path and "#url#" as the conversion string
     *       Clients should generate the file by downloading it to the specified location
     * @uniqueId - Unique file identifier
     *             May be empty if unknown
     *             The unique file identifier which is the same for the same file even for different users and is persistent over time
     * @isUploadingActive - True, if the file is currently being uploaded (or a remote copy is being generated by some other means)
     * @isUploadingCompleted - True, if a remote copy is fully available
     * @uploadedSize - Size of the remote available part of the file
     *                 0 if unknown
     */
    class RemoteFile : Object {

        var id: String? = null
        var uniqueId: String by WeakField()
        var isUploadingActive: Boolean by WeakField()
        var isUploadingCompleted: Boolean by WeakField()
        var uploadedSize: Int by WeakField()

        constructor()

        constructor(id: String? = null, uniqueId: String, isUploadingActive: Boolean, isUploadingCompleted: Boolean, uploadedSize: Int) {

            this.id = id
            this.uniqueId = uniqueId
            this.isUploadingActive = isUploadingActive
            this.isUploadingCompleted = isUploadingCompleted
            this.uploadedSize = uploadedSize

        }

        override val constructor: Int @BsonIgnore get() = -1822143022

    }


    /**
     * Represents a file
     *
     * @id - Unique file identifier
     * @size - File size
     *         0 if unknown
     * @expectedSize - Expected file size in case the exact file size is unknown, but an approximate size is known
     *                 Can be used to show download/upload progress
     * @local - Information about the local copy of the file
     * @remote - Information about the remote copy of the file
     */
    class File : Object {

        var id: Int by WeakField()
        var size: Int by WeakField()
        var expectedSize: Int by WeakField()
        var local: LocalFile by WeakField()
        var remote: RemoteFile by WeakField()

        constructor()

        constructor(id: Int, size: Int, expectedSize: Int, local: LocalFile, remote: RemoteFile) {

            this.id = id
            this.size = size
            this.expectedSize = expectedSize
            this.local = local
            this.remote = remote

        }

        override val constructor: Int @BsonIgnore get() = 766337656

    }


    /**
     * Points to a file
     */
    abstract class InputFile : Object()

    /**
     * A file defined by its unique ID
     *
     * @id - Unique file identifier
     */
    class InputFileId : InputFile {

        var id: Int? = null

        constructor()

        constructor(id: Int? = null) {

            this.id = id

        }

        override val constructor: Int @BsonIgnore get() = 1788906253

    }


    /**
     * A file defined by its remote ID
     * The remote ID is guaranteed to be usable only if the corresponding file is still accessible to the user and known to TDLib
     * For example, if the file is from a message, then the message must be not deleted and accessible to the user
     * If the file database is disabled, then the corresponding object with the file must be preloaded by the client
     *
     * @id - Remote file identifier
     */
    class InputFileRemote : InputFile {

        var id: String? = null

        constructor()

        constructor(id: String? = null) {

            this.id = id

        }

        override val constructor: Int @BsonIgnore get() = -107574466

    }


    /**
     * A file defined by a local path
     *
     * @path - Local path to the file
     */
    class InputFileLocal : InputFile {

        var path: String? = null

        constructor()

        constructor(path: String? = null) {

            this.path = path

        }

        override val constructor: Int @BsonIgnore get() = 2056030919

    }


    /**
     * A file generated by the client
     *
     * @originalPath - Local path to a file from which the file is generated
     *                 May be empty if there is no such file
     * @conversion - String specifying the conversion applied to the original file
     *               Should be persistent across application restarts
     *               Conversions beginning with '#' are reserved for internal TDLib usage
     * @expectedSize - Expected size of the generated file
     *                 0 if unknown
     */
    class InputFileGenerated : InputFile {

        var originalPath: String? = null
        var conversion: String? = null
        var expectedSize: Int? = null

        constructor()

        constructor(originalPath: String? = null, conversion: String? = null, expectedSize: Int? = null) {

            this.originalPath = originalPath
            this.conversion = conversion
            this.expectedSize = expectedSize

        }

        override val constructor: Int @BsonIgnore get() = -1781351885

    }


    /**
     * Photo description
     *
     * @type - Thumbnail type (see https://core.telegram.org/constructor/photoSize)
     * @photo - Information about the photo file
     * @width - Photo width
     * @height - Photo height
     */
    class PhotoSize : Object {

        var type: String by WeakField()
        var photo: File by WeakField()
        var width: Int by WeakField()
        var height: Int by WeakField()

        constructor()

        constructor(type: String, photo: File, width: Int, height: Int) {

            this.type = type
            this.photo = photo
            this.width = width
            this.height = height

        }

        override val constructor: Int @BsonIgnore get() = 421980227

    }


    /**
     * Thumbnail image of a very poor quality and low resolution
     *
     * @width - Thumbnail width, usually doesn't exceed 40
     * @height - Thumbnail height, usually doesn't exceed 40
     * @data - The thumbnail in JPEG format
     */
    class Minithumbnail : Object {

        var width: Int by WeakField()
        var height: Int by WeakField()
        var data: ByteArray by WeakField()

        constructor()

        constructor(width: Int, height: Int, data: ByteArray) {

            this.width = width
            this.height = height
            this.data = data

        }

        override val constructor: Int @BsonIgnore get() = -328540758

    }


    /**
     * Part of the face, relative to which a mask should be placed
     */
    abstract class MaskPoint : Object()

    /**
     * A mask should be placed relatively to the forehead
     */
    class MaskPointForehead : MaskPoint() {

        override val constructor: Int @BsonIgnore get() = 1027512005

    }


    /**
     * A mask should be placed relatively to the eyes
     */
    class MaskPointEyes : MaskPoint() {

        override val constructor: Int @BsonIgnore get() = 1748310861

    }


    /**
     * A mask should be placed relatively to the mouth
     */
    class MaskPointMouth : MaskPoint() {

        override val constructor: Int @BsonIgnore get() = 411773406

    }


    /**
     * A mask should be placed relatively to the chin
     */
    class MaskPointChin : MaskPoint() {

        override val constructor: Int @BsonIgnore get() = 534995335

    }


    /**
     * Position on a photo where a mask should be placed
     *
     * @point - Part of the face, relative to which the mask should be placed
     * @xShift - Shift by X-axis measured in widths of the mask scaled to the face size, from left to right
     *           (For example, -1.0 will place the mask just to the left of the default mask position)
     * @yShift - Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom
     *           (For example, 1.0 will place the mask just below the default mask position)
     * @scale - Mask scaling coefficient
     *          (For example, 2.0 means a doubled size)
     */
    class MaskPosition : Object {

        var point: MaskPoint by WeakField()
        var xShift: Double by WeakField()
        var yShift: Double by WeakField()
        var scale: Double by WeakField()

        constructor()

        constructor(point: MaskPoint, xShift: Double, yShift: Double, scale: Double) {

            this.point = point
            this.xShift = xShift
            this.yShift = yShift
            this.scale = scale

        }

        override val constructor: Int @BsonIgnore get() = -2097433026

    }


    /**
     * Describes one answer option of a poll
     *
     * @text - Option text, 1-100 characters
     * @voterCount - Number of voters for this option, available only for closed or voted polls
     * @votePercentage - The percentage of votes for this option, 0-100
     * @isChosen - True, if the option was chosen by the user
     * @isBeingChosen - True, if the option is being chosen by a pending setPollAnswer request
     */
    class PollOption : Object {

        var text: String by WeakField()
        var voterCount: Int by WeakField()
        var votePercentage: Int by WeakField()
        var isChosen: Boolean by WeakField()
        var isBeingChosen: Boolean by WeakField()

        constructor()

        constructor(text: String, voterCount: Int, votePercentage: Int, isChosen: Boolean, isBeingChosen: Boolean) {

            this.text = text
            this.voterCount = voterCount
            this.votePercentage = votePercentage
            this.isChosen = isChosen
            this.isBeingChosen = isBeingChosen

        }

        override val constructor: Int @BsonIgnore get() = 1473893797

    }


    /**
     * Describes an animation file
     * The animation must be encoded in GIF or MPEG4 format
     *
     * @duration - Duration of the animation, in seconds
     *             As defined by the sender
     * @width - Width of the animation
     * @height - Height of the animation
     * @fileName - Original name of the file
     *             As defined by the sender
     * @mimeType - MIME type of the file, usually "image/gif" or "video/mp4"
     * @minithumbnail - Animation minithumbnail
     * @thumbnail - Animation thumbnail
     * @animation - File containing the animation
     */
    class Animation : Object {

        var duration: Int by WeakField()
        var width: Int by WeakField()
        var height: Int by WeakField()
        var fileName: String by WeakField()
        var mimeType: String by WeakField()
        var minithumbnail: Minithumbnail? = null
        var thumbnail: PhotoSize? = null
        var animation: File by WeakField()

        constructor()

        constructor(duration: Int, width: Int, height: Int, fileName: String, mimeType: String, minithumbnail: Minithumbnail? = null, thumbnail: PhotoSize? = null, animation: File) {

            this.duration = duration
            this.width = width
            this.height = height
            this.fileName = fileName
            this.mimeType = mimeType
            this.minithumbnail = minithumbnail
            this.thumbnail = thumbnail
            this.animation = animation

        }

        override val constructor: Int @BsonIgnore get() = -1629245379

    }


    /**
     * Describes an audio file
     * Audio is usually in MP3 or M4A format
     *
     * @duration - Duration of the audio, in seconds
     *             As defined by the sender
     * @title - Title of the audio
     *          As defined by the sender
     * @performer - Performer of the audio
     *              As defined by the sender
     * @fileName - Original name of the file
     *             As defined by the sender
     * @mimeType - The MIME type of the file
     *             As defined by the sender
     * @albumCoverMinithumbnail - The minithumbnail of the album cover
     * @albumCoverThumbnail - The thumbnail of the album cover
     *                        As defined by the sender
     *                        The full size thumbnail should be extracted from the downloaded file
     * @audio - File containing the audio
     */
    class Audio : Object {

        var duration: Int by WeakField()
        var title: String by WeakField()
        var performer: String by WeakField()
        var fileName: String by WeakField()
        var mimeType: String by WeakField()
        var albumCoverMinithumbnail: Minithumbnail? = null
        var albumCoverThumbnail: PhotoSize? = null
        var audio: File by WeakField()

        constructor()

        constructor(duration: Int, title: String, performer: String, fileName: String, mimeType: String, albumCoverMinithumbnail: Minithumbnail? = null, albumCoverThumbnail: PhotoSize? = null, audio: File) {

            this.duration = duration
            this.title = title
            this.performer = performer
            this.fileName = fileName
            this.mimeType = mimeType
            this.albumCoverMinithumbnail = albumCoverMinithumbnail
            this.albumCoverThumbnail = albumCoverThumbnail
            this.audio = audio

        }

        override val constructor: Int @BsonIgnore get() = 1475294302

    }


    /**
     * Describes a document of any type
     *
     * @fileName - Original name of the file
     *             As defined by the sender
     * @mimeType - MIME type of the file
     *             As defined by the sender
     * @minithumbnail - Document minithumbnail
     * @thumbnail - Document thumbnail in JPEG or PNG format (PNG will be used only for background patterns)
     *              As defined by the sender
     * @document - File containing the document
     */
    class Document : Object {

        var fileName: String by WeakField()
        var mimeType: String by WeakField()
        var minithumbnail: Minithumbnail? = null
        var thumbnail: PhotoSize? = null
        var document: File by WeakField()

        constructor()

        constructor(fileName: String, mimeType: String, minithumbnail: Minithumbnail? = null, thumbnail: PhotoSize? = null, document: File) {

            this.fileName = fileName
            this.mimeType = mimeType
            this.minithumbnail = minithumbnail
            this.thumbnail = thumbnail
            this.document = document

        }

        override val constructor: Int @BsonIgnore get() = 21881988

    }


    /**
     * Describes a photo
     *
     * @hasStickers - True, if stickers were added to the photo
     * @minithumbnail - Photo minithumbnail
     * @sizes - Available variants of the photo, in different sizes
     */
    class Photo : Object {

        var hasStickers: Boolean by WeakField()
        var minithumbnail: Minithumbnail? = null
        var sizes: Array<PhotoSize> by WeakField()

        constructor()

        constructor(hasStickers: Boolean, minithumbnail: Minithumbnail? = null, sizes: Array<PhotoSize>) {

            this.hasStickers = hasStickers
            this.minithumbnail = minithumbnail
            this.sizes = sizes

        }

        override val constructor: Int @BsonIgnore get() = -2022871583

    }


    /**
     * Describes a sticker
     *
     * @setId - The identifier of the sticker set to which the sticker belongs
     *          0 if none
     * @width - Sticker width
     *          As defined by the sender
     * @height - Sticker height
     *           As defined by the sender
     * @emoji - Emoji corresponding to the sticker
     * @isAnimated - True, if the sticker is an animated sticker in TGS format
     * @isMask - True, if the sticker is a mask
     * @maskPosition - Position where the mask should be placed
     * @thumbnail - Sticker thumbnail in WEBP or JPEG format
     * @sticker - File containing the sticker
     */
    class Sticker : Object {

        var setId: Long by WeakField()
        var width: Int by WeakField()
        var height: Int by WeakField()
        var emoji: String by WeakField()
        var isAnimated: Boolean by WeakField()
        var isMask: Boolean by WeakField()
        var maskPosition: MaskPosition? = null
        var thumbnail: PhotoSize? = null
        var sticker: File by WeakField()

        constructor()

        constructor(setId: Long, width: Int, height: Int, emoji: String, isAnimated: Boolean, isMask: Boolean, maskPosition: MaskPosition? = null, thumbnail: PhotoSize? = null, sticker: File) {

            this.setId = setId
            this.width = width
            this.height = height
            this.emoji = emoji
            this.isAnimated = isAnimated
            this.isMask = isMask
            this.maskPosition = maskPosition
            this.thumbnail = thumbnail
            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = -1835470627

    }


    /**
     * Describes a video file
     *
     * @duration - Duration of the video, in seconds
     *             As defined by the sender
     * @width - Video width
     *          As defined by the sender
     * @height - Video height
     *           As defined by the sender
     * @fileName - Original name of the file
     *             As defined by the sender
     * @mimeType - MIME type of the file
     *             As defined by the sender
     * @hasStickers - True, if stickers were added to the video
     * @supportsStreaming - True, if the video should be tried to be streamed
     * @minithumbnail - Video minithumbnail
     * @thumbnail - Video thumbnail
     *              As defined by the sender
     * @video - File containing the video
     */
    class Video : Object {

        var duration: Int by WeakField()
        var width: Int by WeakField()
        var height: Int by WeakField()
        var fileName: String by WeakField()
        var mimeType: String by WeakField()
        var hasStickers: Boolean by WeakField()
        var supportsStreaming: Boolean by WeakField()
        var minithumbnail: Minithumbnail? = null
        var thumbnail: PhotoSize? = null
        var video: File by WeakField()

        constructor()

        constructor(duration: Int, width: Int, height: Int, fileName: String, mimeType: String, hasStickers: Boolean, supportsStreaming: Boolean, minithumbnail: Minithumbnail? = null, thumbnail: PhotoSize? = null, video: File) {

            this.duration = duration
            this.width = width
            this.height = height
            this.fileName = fileName
            this.mimeType = mimeType
            this.hasStickers = hasStickers
            this.supportsStreaming = supportsStreaming
            this.minithumbnail = minithumbnail
            this.thumbnail = thumbnail
            this.video = video

        }

        override val constructor: Int @BsonIgnore get() = -536898740

    }


    /**
     * Describes a video note
     * The video must be equal in width and height, cropped to a circle, and stored in MPEG4 format
     *
     * @duration - Duration of the video, in seconds
     *             As defined by the sender
     * @length - Video width and height
     *           As defined by the sender
     * @minithumbnail - Video minithumbnail
     * @thumbnail - Video thumbnail
     *              As defined by the sender
     * @video - File containing the video
     */
    class VideoNote : Object {

        var duration: Int by WeakField()
        var length: Int by WeakField()
        var minithumbnail: Minithumbnail? = null
        var thumbnail: PhotoSize? = null
        var video: File by WeakField()

        constructor()

        constructor(duration: Int, length: Int, minithumbnail: Minithumbnail? = null, thumbnail: PhotoSize? = null, video: File) {

            this.duration = duration
            this.length = length
            this.minithumbnail = minithumbnail
            this.thumbnail = thumbnail
            this.video = video

        }

        override val constructor: Int @BsonIgnore get() = -1080075672

    }


    /**
     * Describes a voice note
     * The voice note must be encoded with the Opus codec, and stored inside an OGG container
     * Voice notes can have only a single audio channel
     *
     * @duration - Duration of the voice note, in seconds
     *             As defined by the sender
     * @waveform - A waveform representation of the voice note in 5-bit format
     * @mimeType - MIME type of the file
     *             As defined by the sender
     * @voice - File containing the voice note
     */
    class VoiceNote : Object {

        var duration: Int by WeakField()
        var waveform: ByteArray by WeakField()
        var mimeType: String by WeakField()
        var voice: File by WeakField()

        constructor()

        constructor(duration: Int, waveform: ByteArray, mimeType: String, voice: File) {

            this.duration = duration
            this.waveform = waveform
            this.mimeType = mimeType
            this.voice = voice

        }

        override val constructor: Int @BsonIgnore get() = -2066012058

    }


    /**
     * Describes a user contact
     *
     * @phoneNumber - Phone number of the user
     * @firstName - First name of the user
     * @lastName - Last name of the user
     * @vcard - Additional data about the user in a form of vCard
     * @userId - Identifier of the user, if known
     *           Otherwise 0
     */
    class Contact : Object {

        var phoneNumber: String by WeakField()
        var firstName: String by WeakField()
        var lastName: String by WeakField()
        var vcard: String by WeakField()
        var userId: Int by WeakField()

        constructor()

        constructor(phoneNumber: String, firstName: String, lastName: String, vcard: String, userId: Int) {

            this.phoneNumber = phoneNumber
            this.firstName = firstName
            this.lastName = lastName
            this.vcard = vcard
            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -1483002540

    }


    /**
     * Describes a location on planet Earth
     *
     * @latitude - Latitude of the location in degrees
     *             As defined by the sender
     * @longitude - Longitude of the location, in degrees
     *              As defined by the sender
     */
    class Location : Object {

        var latitude: Double by WeakField()
        var longitude: Double by WeakField()

        constructor()

        constructor(latitude: Double, longitude: Double) {

            this.latitude = latitude
            this.longitude = longitude

        }

        override val constructor: Int @BsonIgnore get() = 749028016

    }


    /**
     * Describes a venue
     *
     * @location - Venue location
     *             As defined by the sender
     * @title - Venue name
     *          As defined by the sender
     * @address - Venue address
     *            As defined by the sender
     * @provider - Provider of the venue database
     *             As defined by the sender
     *             Currently only "foursquare" needs to be supported
     * @id - Identifier of the venue in the provider database
     *       As defined by the sender
     * @type - Type of the venue in the provider database
     *         As defined by the sender
     */
    class Venue : Object {

        var location: Location by WeakField()
        var title: String by WeakField()
        var address: String by WeakField()
        var provider: String by WeakField()
        var id: String by WeakField()
        var type: String by WeakField()

        constructor()

        constructor(location: Location, title: String, address: String, provider: String, id: String, type: String) {

            this.location = location
            this.title = title
            this.address = address
            this.provider = provider
            this.id = id
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = 1070406393

    }


    /**
     * Describes a game
     *
     * @id - Game ID
     * @shortName - Game short name
     *              To share a game use the URL https://t.me/{bot_username}?game={game_short_name}
     * @title - Game title
     * @text - Game text, usually containing scoreboards for a game
     * @description - Game description
     * @photo - Game photo
     * @animation - Game animation
     */
    class Game : Object {

        var id: Long by WeakField()
        var shortName: String by WeakField()
        var title: String by WeakField()
        var text: FormattedText by WeakField()
        var description: String by WeakField()
        var photo: Photo by WeakField()
        var animation: Animation? = null

        constructor()

        constructor(id: Long, shortName: String, title: String, text: FormattedText, description: String, photo: Photo, animation: Animation? = null) {

            this.id = id
            this.shortName = shortName
            this.title = title
            this.text = text
            this.description = description
            this.photo = photo
            this.animation = animation

        }

        override val constructor: Int @BsonIgnore get() = -1565597752

    }


    /**
     * Describes a poll
     *
     * @id - Unique poll identifier
     * @question - Poll question, 1-255 characters
     * @options - List of poll answer options
     * @totalVoterCount - Total number of voters, participating in the poll
     * @isClosed - True, if the poll is closed
     */
    class Poll : Object {

        var id: Long by WeakField()
        var question: String by WeakField()
        var options: Array<PollOption> by WeakField()
        var totalVoterCount: Int by WeakField()
        var isClosed: Boolean by WeakField()

        constructor()

        constructor(id: Long, question: String, options: Array<PollOption>, totalVoterCount: Int, isClosed: Boolean) {

            this.id = id
            this.question = question
            this.options = options
            this.totalVoterCount = totalVoterCount
            this.isClosed = isClosed

        }

        override val constructor: Int @BsonIgnore get() = -959396214

    }


    /**
     * Describes a user profile photo
     *
     * @id - Photo identifier
     *       0 for an empty photo
     *       Can be used to find a photo in a list of userProfilePhotos
     * @small - A small (160x160) user profile photo
     *          The file can be downloaded only before the photo is changed
     * @big - A big (640x640) user profile photo
     *        The file can be downloaded only before the photo is changed
     */
    class ProfilePhoto : Object {

        var id: Long by WeakField()
        var small: File by WeakField()
        var big: File by WeakField()

        constructor()

        constructor(id: Long, small: File, big: File) {

            this.id = id
            this.small = small
            this.big = big

        }

        override val constructor: Int @BsonIgnore get() = 978085937

    }


    /**
     * Describes the photo of a chat
     *
     * @small - A small (160x160) chat photo
     *          The file can be downloaded only before the photo is changed
     * @big - A big (640x640) chat photo
     *        The file can be downloaded only before the photo is changed
     */
    class ChatPhoto : Object {

        var small: File by WeakField()
        var big: File by WeakField()

        constructor()

        constructor(small: File, big: File) {

            this.small = small
            this.big = big

        }

        override val constructor: Int @BsonIgnore get() = -217062456

    }


    /**
     * Represents the type of the user
     * The following types are possible: regular users, deleted users and bots
     */
    abstract class UserType : Object()

    /**
     * A regular user
     */
    class UserTypeRegular : UserType() {

        override val constructor: Int @BsonIgnore get() = -598644325

    }


    /**
     * A deleted user or deleted bot
     * No information on the user besides the user identifier is available
     * It is not possible to perform any active actions on this type of user
     */
    class UserTypeDeleted : UserType() {

        override val constructor: Int @BsonIgnore get() = -1807729372

    }


    /**
     * A bot (see https://core.telegram.org/bots)
     *
     * @canJoinGroups - True, if the bot can be invited to basic group and supergroup chats
     * @canReadAllGroupMessages - True, if the bot can read all messages in basic group or supergroup chats and not just those addressed to the bot
     *                            In private and channel chats a bot can always read all messages
     * @isInline - True, if the bot supports inline queries
     * @inlineQueryPlaceholder - Placeholder for inline queries (displayed on the client input field)
     * @needLocation - True, if the location of the user should be sent with every inline query to this bot
     */
    class UserTypeBot : UserType {

        var canJoinGroups: Boolean by WeakField()
        var canReadAllGroupMessages: Boolean by WeakField()
        var isInline: Boolean by WeakField()
        var inlineQueryPlaceholder: String by WeakField()
        var needLocation: Boolean by WeakField()

        constructor()

        constructor(canJoinGroups: Boolean, canReadAllGroupMessages: Boolean, isInline: Boolean, inlineQueryPlaceholder: String, needLocation: Boolean) {

            this.canJoinGroups = canJoinGroups
            this.canReadAllGroupMessages = canReadAllGroupMessages
            this.isInline = isInline
            this.inlineQueryPlaceholder = inlineQueryPlaceholder
            this.needLocation = needLocation

        }

        override val constructor: Int @BsonIgnore get() = 1262387765

    }


    /**
     * No information on the user besides the user identifier is available, yet this user has not been deleted
     * This object is extremely rare and must be handled like a deleted user
     * It is not possible to perform any actions on users of this type
     */
    class UserTypeUnknown : UserType() {

        override val constructor: Int @BsonIgnore get() = -724541123

    }


    /**
     * Represents commands supported by a bot
     *
     * @command - Text of the bot command
     * @description - Description of the bot command
     */
    class BotCommand : Object {

        var command: String by WeakField()
        var description: String by WeakField()

        constructor()

        constructor(command: String, description: String) {

            this.command = command
            this.description = description

        }

        override val constructor: Int @BsonIgnore get() = -1032140601

    }


    /**
     * Provides information about a bot and its supported commands
     *
     * @description - Long description shown on the user info page
     * @commands - A list of commands supported by the bot
     */
    class BotInfo : Object {

        var description: String by WeakField()
        var commands: Array<BotCommand> by WeakField()

        constructor()

        constructor(description: String, commands: Array<BotCommand>) {

            this.description = description
            this.commands = commands

        }

        override val constructor: Int @BsonIgnore get() = 1296528907

    }


    /**
     * Represents a location to which a chat is connected
     *
     * @location - The location
     * @address - Location address
     */
    class ChatLocation : Object {

        var location: Location by WeakField()
        var address: String by WeakField()

        constructor()

        constructor(location: Location, address: String) {

            this.location = location
            this.address = address

        }

        override val constructor: Int @BsonIgnore get() = -1566863583

    }


    /**
     * Represents a user
     *
     * @id - User identifier
     * @firstName - First name of the user
     * @lastName - Last name of the user
     * @username - Username of the user
     * @phoneNumber - Phone number of the user
     * @status - Current online status of the user
     * @profilePhoto - Profile photo of the user
     * @isContact - The user is a contact of the current user
     * @isMutualContact - The user is a contact of the current user and the current user is a contact of the user
     * @isVerified - True, if the user is verified
     * @isSupport - True, if the user is Telegram support account
     * @restrictionReason - If non-empty, it contains a human-readable description of the reason why access to this user must be restricted
     * @isScam - True, if many users reported this user as a scam
     * @haveAccess - If false, the user is inaccessible, and the only information known about the user is inside this class
     *               It can't be passed to any method except GetUser
     * @type - Type of the user
     * @languageCode - IETF language tag of the user's language
     *                 Only available to bots
     */
    class User : Object {

        var id: Int by WeakField()
        var firstName: String by WeakField()
        var lastName: String by WeakField()
        var username: String by WeakField()
        var phoneNumber: String by WeakField()
        var status: UserStatus by WeakField()
        var profilePhoto: ProfilePhoto? = null
        var isContact: Boolean by WeakField()
        var isMutualContact: Boolean by WeakField()
        var isVerified: Boolean by WeakField()
        var isSupport: Boolean by WeakField()
        var restrictionReason: String by WeakField()
        var isScam: Boolean by WeakField()
        var haveAccess: Boolean by WeakField()
        var type: UserType by WeakField()
        var languageCode: String by WeakField()

        constructor()

        constructor(id: Int, firstName: String, lastName: String, username: String, phoneNumber: String, status: UserStatus, profilePhoto: ProfilePhoto? = null, isContact: Boolean, isMutualContact: Boolean, isVerified: Boolean, isSupport: Boolean, restrictionReason: String, isScam: Boolean, haveAccess: Boolean, type: UserType, languageCode: String) {

            this.id = id
            this.firstName = firstName
            this.lastName = lastName
            this.username = username
            this.phoneNumber = phoneNumber
            this.status = status
            this.profilePhoto = profilePhoto
            this.isContact = isContact
            this.isMutualContact = isMutualContact
            this.isVerified = isVerified
            this.isSupport = isSupport
            this.restrictionReason = restrictionReason
            this.isScam = isScam
            this.haveAccess = haveAccess
            this.type = type
            this.languageCode = languageCode

        }

        override val constructor: Int @BsonIgnore get() = -824771497

    }


    /**
     * Contains full information about a user (except the full list of profile photos)
     *
     * @isBlocked - True, if the user is blacklisted by the current user
     * @canBeCalled - True, if the user can be called
     * @hasPrivateCalls - True, if the user can't be called due to their privacy settings
     * @needPhoneNumberPrivacyException - True, if the current user needs to explicitly allow to share their phone number with the user when the method addContact is used
     * @bio - A short user bio
     * @shareText - For bots, the text that is included with the link when users share the bot
     * @groupInCommonCount - Number of group chats where both the other user and the current user are a member
     *                       0 for the current user
     * @botInfo - If the user is a bot, information about the bot
     */
    class UserFullInfo : Object {

        var isBlocked: Boolean by WeakField()
        var canBeCalled: Boolean by WeakField()
        var hasPrivateCalls: Boolean by WeakField()
        var needPhoneNumberPrivacyException: Boolean by WeakField()
        var bio: String by WeakField()
        var shareText: String by WeakField()
        var groupInCommonCount: Int by WeakField()
        var botInfo: BotInfo? = null

        constructor()

        constructor(isBlocked: Boolean, canBeCalled: Boolean, hasPrivateCalls: Boolean, needPhoneNumberPrivacyException: Boolean, bio: String, shareText: String, groupInCommonCount: Int, botInfo: BotInfo? = null) {

            this.isBlocked = isBlocked
            this.canBeCalled = canBeCalled
            this.hasPrivateCalls = hasPrivateCalls
            this.needPhoneNumberPrivacyException = needPhoneNumberPrivacyException
            this.bio = bio
            this.shareText = shareText
            this.groupInCommonCount = groupInCommonCount
            this.botInfo = botInfo

        }

        override val constructor: Int @BsonIgnore get() = 333888500

    }


    /**
     * Contains full information about a user profile photo
     *
     * @id - Unique user profile photo identifier
     * @addedDate - Point in time (Unix timestamp) when the photo has been added
     * @sizes - Available variants of the user photo, in different sizes
     */
    class UserProfilePhoto : Object {

        var id: Long by WeakField()
        var addedDate: Int by WeakField()
        var sizes: Array<PhotoSize> by WeakField()

        constructor()

        constructor(id: Long, addedDate: Int, sizes: Array<PhotoSize>) {

            this.id = id
            this.addedDate = addedDate
            this.sizes = sizes

        }

        override val constructor: Int @BsonIgnore get() = -1882596466

    }


    /**
     * Contains part of the list of user photos
     *
     * @totalCount - Total number of user profile photos
     * @photos - A list of photos
     */
    class UserProfilePhotos : Object {

        var totalCount: Int by WeakField()
        var photos: Array<UserProfilePhoto> by WeakField()

        constructor()

        constructor(totalCount: Int, photos: Array<UserProfilePhoto>) {

            this.totalCount = totalCount
            this.photos = photos

        }

        override val constructor: Int @BsonIgnore get() = 1512709690

    }


    /**
     * Represents a list of users
     *
     * @totalCount - Approximate total count of users found
     * @userIds - A list of user identifiers
     */
    class Users : Object {

        var totalCount: Int by WeakField()
        var userIds: IntArray by WeakField()

        constructor()

        constructor(totalCount: Int, userIds: IntArray) {

            this.totalCount = totalCount
            this.userIds = userIds

        }

        override val constructor: Int @BsonIgnore get() = 273760088

    }


    /**
     * Contains information about a chat administrator
     *
     * @userId - User identifier of the administrator
     * @customTitle - Custom title of the administrator
     * @isOwner - True, if the user is the owner of the chat
     */
    class ChatAdministrator : Object {

        var userId: Int by WeakField()
        var customTitle: String by WeakField()
        var isOwner: Boolean by WeakField()

        constructor()

        constructor(userId: Int, customTitle: String, isOwner: Boolean) {

            this.userId = userId
            this.customTitle = customTitle
            this.isOwner = isOwner

        }

        override val constructor: Int @BsonIgnore get() = 487220942

    }


    /**
     * Represents a list of chat administrators
     *
     * @administrators - A list of chat administrators
     */
    class ChatAdministrators : Object {

        var administrators: Array<ChatAdministrator> by WeakField()

        constructor()

        constructor(administrators: Array<ChatAdministrator>) {

            this.administrators = administrators

        }

        override val constructor: Int @BsonIgnore get() = -2126186435

    }


    /**
     * Describes actions that a user is allowed to take in a chat
     *
     * @canSendMessages - True, if the user can send text messages, contacts, locations, and venues
     * @canSendMediaMessages - True, if the user can send audio files, documents, photos, videos, video notes, and voice notes
     *                         Implies can_send_messages permissions
     * @canSendPolls - True, if the user can send polls
     *                 Implies can_send_messages permissions
     * @canSendOtherMessages - True, if the user can send animations, games, and stickers and use inline bots
     *                         Implies can_send_messages permissions
     * @canAddWebPagePreviews - True, if the user may add a web page preview to their messages
     *                          Implies can_send_messages permissions
     * @canChangeInfo - True, if the user can change the chat title, photo, and other settings
     * @canInviteUsers - True, if the user can invite new users to the chat
     * @canPinMessages - True, if the user can pin messages
     */
    class ChatPermissions : Object {

        var canSendMessages: Boolean by WeakField()
        var canSendMediaMessages: Boolean by WeakField()
        var canSendPolls: Boolean by WeakField()
        var canSendOtherMessages: Boolean by WeakField()
        var canAddWebPagePreviews: Boolean by WeakField()
        var canChangeInfo: Boolean by WeakField()
        var canInviteUsers: Boolean by WeakField()
        var canPinMessages: Boolean by WeakField()

        constructor()

        constructor(canSendMessages: Boolean, canSendMediaMessages: Boolean, canSendPolls: Boolean, canSendOtherMessages: Boolean, canAddWebPagePreviews: Boolean, canChangeInfo: Boolean, canInviteUsers: Boolean, canPinMessages: Boolean) {

            this.canSendMessages = canSendMessages
            this.canSendMediaMessages = canSendMediaMessages
            this.canSendPolls = canSendPolls
            this.canSendOtherMessages = canSendOtherMessages
            this.canAddWebPagePreviews = canAddWebPagePreviews
            this.canChangeInfo = canChangeInfo
            this.canInviteUsers = canInviteUsers
            this.canPinMessages = canPinMessages

        }

        override val constructor: Int @BsonIgnore get() = 1584650463

    }


    /**
     * Provides information about the status of a member in a chat
     */
    abstract class ChatMemberStatus : Object()

    /**
     * The user is the owner of a chat and has all the administrator privileges
     *
     * @customTitle - A custom title of the owner
     *                Applicable to supergroups only
     * @isMember - True, if the user is a member of the chat
     */
    class ChatMemberStatusCreator : ChatMemberStatus {

        var customTitle: String by WeakField()
        var isMember: Boolean by WeakField()

        constructor()

        constructor(customTitle: String, isMember: Boolean) {

            this.customTitle = customTitle
            this.isMember = isMember

        }

        override val constructor: Int @BsonIgnore get() = 2038475849

    }


    /**
     * The user is a member of a chat and has some additional privileges
     * In basic groups, administrators can edit and delete messages sent by others, add new members, and ban unprivileged members
     * In supergroups and channels, there are more detailed options for administrator privileges
     *
     * @customTitle - A custom title of the administrator
     *                Applicable to supergroups only
     * @canBeEdited - True, if the current user can edit the administrator privileges for the called user
     * @canChangeInfo - True, if the administrator can change the chat title, photo, and other settings
     * @canPostMessages - True, if the administrator can create channel posts
     *                    Applicable to channels only
     * @canEditMessages - True, if the administrator can edit messages of other users and pin messages
     *                    Applicable to channels only
     * @canDeleteMessages - True, if the administrator can delete messages of other users
     * @canInviteUsers - True, if the administrator can invite new users to the chat
     * @canRestrictMembers - True, if the administrator can restrict, ban, or unban chat members
     * @canPinMessages - True, if the administrator can pin messages
     *                   Applicable to groups only
     * @canPromoteMembers - True, if the administrator can add new administrators with a subset of their own privileges or demote administrators that were directly or indirectly promoted by them
     */
    class ChatMemberStatusAdministrator : ChatMemberStatus {

        var customTitle: String by WeakField()
        var canBeEdited: Boolean by WeakField()
        var canChangeInfo: Boolean by WeakField()
        var canPostMessages: Boolean by WeakField()
        var canEditMessages: Boolean by WeakField()
        var canDeleteMessages: Boolean by WeakField()
        var canInviteUsers: Boolean by WeakField()
        var canRestrictMembers: Boolean by WeakField()
        var canPinMessages: Boolean by WeakField()
        var canPromoteMembers: Boolean by WeakField()

        constructor()

        constructor(customTitle: String, canBeEdited: Boolean, canChangeInfo: Boolean, canPostMessages: Boolean, canEditMessages: Boolean, canDeleteMessages: Boolean, canInviteUsers: Boolean, canRestrictMembers: Boolean, canPinMessages: Boolean, canPromoteMembers: Boolean) {

            this.customTitle = customTitle
            this.canBeEdited = canBeEdited
            this.canChangeInfo = canChangeInfo
            this.canPostMessages = canPostMessages
            this.canEditMessages = canEditMessages
            this.canDeleteMessages = canDeleteMessages
            this.canInviteUsers = canInviteUsers
            this.canRestrictMembers = canRestrictMembers
            this.canPinMessages = canPinMessages
            this.canPromoteMembers = canPromoteMembers

        }

        override val constructor: Int @BsonIgnore get() = 1800612058

    }


    /**
     * The user is a member of a chat, without any additional privileges or restrictions
     */
    class ChatMemberStatusMember : ChatMemberStatus() {

        override val constructor: Int @BsonIgnore get() = 844723285

    }


    /**
     * The user is under certain restrictions in the chat
     * Not supported in basic groups and channels
     *
     * @isMember - True, if the user is a member of the chat
     * @restrictedUntilDate - Point in time (Unix timestamp) when restrictions will be lifted from the user
     *                        0 if never
     *                        If the user is restricted for more than 366 days or for less than 30 seconds from the current time, the user is considered to be restricted forever
     * @permissions - User permissions in the chat
     */
    class ChatMemberStatusRestricted : ChatMemberStatus {

        var isMember: Boolean by WeakField()
        var restrictedUntilDate: Int by WeakField()
        var permissions: ChatPermissions by WeakField()

        constructor()

        constructor(isMember: Boolean, restrictedUntilDate: Int, permissions: ChatPermissions) {

            this.isMember = isMember
            this.restrictedUntilDate = restrictedUntilDate
            this.permissions = permissions

        }

        override val constructor: Int @BsonIgnore get() = 1661432998

    }


    /**
     * The user is not a chat member
     */
    class ChatMemberStatusLeft : ChatMemberStatus() {

        override val constructor: Int @BsonIgnore get() = -5815259

    }


    /**
     * The user was banned (and hence is not a member of the chat)
     * Implies the user can't return to the chat or view messages
     *
     * @bannedUntilDate - Point in time (Unix timestamp) when the user will be unbanned
     *                    0 if never
     *                    If the user is banned for more than 366 days or for less than 30 seconds from the current time, the user is considered to be banned forever
     */
    class ChatMemberStatusBanned : ChatMemberStatus {

        var bannedUntilDate: Int by WeakField()

        constructor()

        constructor(bannedUntilDate: Int) {

            this.bannedUntilDate = bannedUntilDate

        }

        override val constructor: Int @BsonIgnore get() = -1653518666

    }


    /**
     * A user with information about joining/leaving a chat
     *
     * @userId - User identifier of the chat member
     * @inviterUserId - Identifier of a user that invited/promoted/banned this member in the chat
     *                  0 if unknown
     * @joinedChatDate - Point in time (Unix timestamp) when the user joined a chat
     * @status - Status of the member in the chat
     * @botInfo - If the user is a bot, information about the bot
     *            Can be null even for a bot if the bot is not a chat member
     */
    class ChatMember : Object {

        var userId: Int by WeakField()
        var inviterUserId: Int by WeakField()
        var joinedChatDate: Int by WeakField()
        var status: ChatMemberStatus by WeakField()
        var botInfo: BotInfo? = null

        constructor()

        constructor(userId: Int, inviterUserId: Int, joinedChatDate: Int, status: ChatMemberStatus, botInfo: BotInfo? = null) {

            this.userId = userId
            this.inviterUserId = inviterUserId
            this.joinedChatDate = joinedChatDate
            this.status = status
            this.botInfo = botInfo

        }

        override val constructor: Int @BsonIgnore get() = -806137076

    }


    /**
     * Contains a list of chat members
     *
     * @totalCount - Approximate total count of chat members found
     * @members - A list of chat members
     */
    class ChatMembers : Object {

        var totalCount: Int by WeakField()
        var members: Array<ChatMember> by WeakField()

        constructor()

        constructor(totalCount: Int, members: Array<ChatMember>) {

            this.totalCount = totalCount
            this.members = members

        }

        override val constructor: Int @BsonIgnore get() = -497558622

    }


    /**
     * Specifies the kind of chat members to return in searchChatMembers
     */
    abstract class ChatMembersFilter : Object()

    /**
     * Returns contacts of the user
     */
    class ChatMembersFilterContacts : ChatMembersFilter() {

        override val constructor: Int @BsonIgnore get() = 1774485671

    }


    /**
     * Returns the owner and administrators
     */
    class ChatMembersFilterAdministrators : ChatMembersFilter() {

        override val constructor: Int @BsonIgnore get() = -1266893796

    }


    /**
     * Returns all chat members, including restricted chat members
     */
    class ChatMembersFilterMembers : ChatMembersFilter() {

        override val constructor: Int @BsonIgnore get() = 670504342

    }


    /**
     * Returns users under certain restrictions in the chat
     * Can be used only by administrators in a supergroup
     */
    class ChatMembersFilterRestricted : ChatMembersFilter() {

        override val constructor: Int @BsonIgnore get() = 1256282813

    }


    /**
     * Returns users banned from the chat
     * Can be used only by administrators in a supergroup or in a channel
     */
    class ChatMembersFilterBanned : ChatMembersFilter() {

        override val constructor: Int @BsonIgnore get() = -1863102648

    }


    /**
     * Returns bot members of the chat
     */
    class ChatMembersFilterBots : ChatMembersFilter() {

        override val constructor: Int @BsonIgnore get() = -1422567288

    }


    /**
     * Specifies the kind of chat members to return in getSupergroupMembers
     */
    abstract class SupergroupMembersFilter : Object()

    /**
     * Returns recently active users in reverse chronological order
     */
    class SupergroupMembersFilterRecent : SupergroupMembersFilter() {

        override val constructor: Int @BsonIgnore get() = 1178199509

    }


    /**
     * Returns contacts of the user, which are members of the supergroup or channel
     *
     * @query - Query to search for
     */
    class SupergroupMembersFilterContacts : SupergroupMembersFilter {

        var query: String by WeakField()

        constructor()

        constructor(query: String) {

            this.query = query

        }

        override val constructor: Int @BsonIgnore get() = -1282910856

    }


    /**
     * Returns the owner and administrators
     */
    class SupergroupMembersFilterAdministrators : SupergroupMembersFilter() {

        override val constructor: Int @BsonIgnore get() = -2097380265

    }


    /**
     * Used to search for supergroup or channel members via a (string) query
     *
     * @query - Query to search for
     */
    class SupergroupMembersFilterSearch : SupergroupMembersFilter {

        var query: String by WeakField()

        constructor()

        constructor(query: String) {

            this.query = query

        }

        override val constructor: Int @BsonIgnore get() = -1696358469

    }


    /**
     * Returns restricted supergroup members
     * Can be used only by administrators
     *
     * @query - Query to search for
     */
    class SupergroupMembersFilterRestricted : SupergroupMembersFilter {

        var query: String by WeakField()

        constructor()

        constructor(query: String) {

            this.query = query

        }

        override val constructor: Int @BsonIgnore get() = -1107800034

    }


    /**
     * Returns users banned from the supergroup or channel
     * Can be used only by administrators
     *
     * @query - Query to search for
     */
    class SupergroupMembersFilterBanned : SupergroupMembersFilter {

        var query: String by WeakField()

        constructor()

        constructor(query: String) {

            this.query = query

        }

        override val constructor: Int @BsonIgnore get() = -1210621683

    }


    /**
     * Returns bot members of the supergroup or channel
     */
    class SupergroupMembersFilterBots : SupergroupMembersFilter() {

        override val constructor: Int @BsonIgnore get() = 492138918

    }


    /**
     * Represents a basic group of 0-200 users (must be upgraded to a supergroup to accommodate more than 200 users)
     *
     * @id - Group identifier
     * @memberCount - Number of members in the group
     * @status - Status of the current user in the group
     * @isActive - True, if the group is active
     * @upgradedToSupergroupId - Identifier of the supergroup to which this group was upgraded
     *                           0 if none
     */
    class BasicGroup : Object {

        var id: Int by WeakField()
        var memberCount: Int by WeakField()
        var status: ChatMemberStatus by WeakField()
        var isActive: Boolean by WeakField()
        var upgradedToSupergroupId: Int by WeakField()

        constructor()

        constructor(id: Int, memberCount: Int, status: ChatMemberStatus, isActive: Boolean, upgradedToSupergroupId: Int) {

            this.id = id
            this.memberCount = memberCount
            this.status = status
            this.isActive = isActive
            this.upgradedToSupergroupId = upgradedToSupergroupId

        }

        override val constructor: Int @BsonIgnore get() = -317839045

    }


    /**
     * Contains full information about a basic group
     *
     * @description - Group description
     * @creatorUserId - User identifier of the creator of the group
     *                  0 if unknown
     * @members - Group members
     * @inviteLink - Invite link for this group
     *               Available only after it has been generated at least once and only for the group creator
     */
    class BasicGroupFullInfo : Object {

        var description: String by WeakField()
        var creatorUserId: Int by WeakField()
        var members: Array<ChatMember> by WeakField()
        var inviteLink: String by WeakField()

        constructor()

        constructor(description: String, creatorUserId: Int, members: Array<ChatMember>, inviteLink: String) {

            this.description = description
            this.creatorUserId = creatorUserId
            this.members = members
            this.inviteLink = inviteLink

        }

        override val constructor: Int @BsonIgnore get() = 161500149

    }


    /**
     * Represents a supergroup or channel with zero or more members (subscribers in the case of channels)
     * From the point of view of the system, a channel is a special kind of a supergroup: only administrators can post and see the list of members, and posts from all administrators use the name and photo of the channel instead of individual names and profile photos
     * Unlike supergroups, channels can have an unlimited number of subscribers
     *
     * @id - Supergroup or channel identifier
     * @username - Username of the supergroup or channel
     *             Empty for private supergroups or channels
     * @date - Point in time (Unix timestamp) when the current user joined, or the point in time when the supergroup or channel was created, in case the user is not a member
     * @status - Status of the current user in the supergroup or channel
     *           Custom title will be always empty
     * @memberCount - Member count
     *                0 if unknown
     *                Currently it is guaranteed to be known only if the supergroup or channel was found through SearchPublicChats
     * @hasLinkedChat - True, if the channel has a discussion group, or the supergroup is the designated discussion group for a channel
     * @hasLocation - True, if the supergroup is connected to a location, i.e
     *                The supergroup is a location-based supergroup
     * @signMessages - True, if messages sent to the channel should contain information about the sender
     *                 This field is only applicable to channels
     * @isSlowModeEnabled - True, if the slow mode is enabled in the supergroup
     * @isChannel - True, if the supergroup is a channel
     * @isVerified - True, if the supergroup or channel is verified
     * @restrictionReason - If non-empty, contains a human-readable description of the reason why access to this supergroup or channel must be restricted
     * @isScam - True, if many users reported this supergroup as a scam
     */
    class Supergroup : Object {

        var id: Int by WeakField()
        var username: String by WeakField()
        var date: Int by WeakField()
        var status: ChatMemberStatus by WeakField()
        var memberCount: Int by WeakField()
        var hasLinkedChat: Boolean by WeakField()
        var hasLocation: Boolean by WeakField()
        var signMessages: Boolean by WeakField()
        var isSlowModeEnabled: Boolean by WeakField()
        var isChannel: Boolean by WeakField()
        var isVerified: Boolean by WeakField()
        var restrictionReason: String by WeakField()
        var isScam: Boolean by WeakField()

        constructor()

        constructor(id: Int, username: String, date: Int, status: ChatMemberStatus, memberCount: Int, hasLinkedChat: Boolean, hasLocation: Boolean, signMessages: Boolean, isSlowModeEnabled: Boolean, isChannel: Boolean, isVerified: Boolean, restrictionReason: String, isScam: Boolean) {

            this.id = id
            this.username = username
            this.date = date
            this.status = status
            this.memberCount = memberCount
            this.hasLinkedChat = hasLinkedChat
            this.hasLocation = hasLocation
            this.signMessages = signMessages
            this.isSlowModeEnabled = isSlowModeEnabled
            this.isChannel = isChannel
            this.isVerified = isVerified
            this.restrictionReason = restrictionReason
            this.isScam = isScam

        }

        override val constructor: Int @BsonIgnore get() = -103091

    }


    /**
     * Contains full information about a supergroup or channel
     *
     * @description - Supergroup or channel description
     * @memberCount - Number of members in the supergroup or channel
     *                0 if unknown
     * @administratorCount - Number of privileged users in the supergroup or channel
     *                       0 if unknown
     * @restrictedCount - Number of restricted users in the supergroup
     *                    0 if unknown
     * @bannedCount - Number of users banned from chat
     *                0 if unknown
     * @linkedChatId - Chat identifier of a discussion group for the channel, or a channel, for which the supergroup is the designated discussion group
     *                 0 if none or unknown
     * @slowModeDelay - Delay between consecutive sent messages for non-administrator supergroup members, in seconds
     * @slowModeDelayExpiresIn - Time left before next message can be sent in the supergroup, in seconds
     *                           An updateSupergroupFullInfo update is not triggered when value of this field changes, but both new and old values are non-zero
     * @canGetMembers - True, if members of the chat can be retrieved
     * @canSetUsername - True, if the chat username can be changed
     * @canSetStickerSet - True, if the supergroup sticker set can be changed
     * @canSetLocation - True, if the supergroup location can be changed
     * @canViewStatistics - True, if the channel statistics is available through getChatStatisticsUrl
     * @isAllHistoryAvailable - True, if new chat members will have access to old messages
     *                          In public or discussion groups and both public and private channels, old messages are always available, so this option affects only private supergroups without a linked chat
     *                          The value of this field is only available for chat administrators
     * @stickerSetId - Identifier of the supergroup sticker set
     *                 0 if none
     * @location - Location to which the supergroup is connected
     * @inviteLink - Invite link for this chat
     * @upgradedFromBasicGroupId - Identifier of the basic group from which supergroup was upgraded
     *                             0 if none
     * @upgradedFromMaxMessageId - Identifier of the last message in the basic group from which supergroup was upgraded
     *                             0 if none
     */
    class SupergroupFullInfo : Object {

        var description: String by WeakField()
        var memberCount: Int by WeakField()
        var administratorCount: Int by WeakField()
        var restrictedCount: Int by WeakField()
        var bannedCount: Int by WeakField()
        var linkedChatId: Long by WeakField()
        var slowModeDelay: Int by WeakField()
        var slowModeDelayExpiresIn: Double by WeakField()
        var canGetMembers: Boolean by WeakField()
        var canSetUsername: Boolean by WeakField()
        var canSetStickerSet: Boolean by WeakField()
        var canSetLocation: Boolean by WeakField()
        var canViewStatistics: Boolean by WeakField()
        var isAllHistoryAvailable: Boolean by WeakField()
        var stickerSetId: Long by WeakField()
        var location: ChatLocation? = null
        var inviteLink: String by WeakField()
        var upgradedFromBasicGroupId: Int by WeakField()
        var upgradedFromMaxMessageId: Long by WeakField()

        constructor()

        constructor(description: String, memberCount: Int, administratorCount: Int, restrictedCount: Int, bannedCount: Int, linkedChatId: Long, slowModeDelay: Int, slowModeDelayExpiresIn: Double, canGetMembers: Boolean, canSetUsername: Boolean, canSetStickerSet: Boolean, canSetLocation: Boolean, canViewStatistics: Boolean, isAllHistoryAvailable: Boolean, stickerSetId: Long, location: ChatLocation? = null, inviteLink: String, upgradedFromBasicGroupId: Int, upgradedFromMaxMessageId: Long) {

            this.description = description
            this.memberCount = memberCount
            this.administratorCount = administratorCount
            this.restrictedCount = restrictedCount
            this.bannedCount = bannedCount
            this.linkedChatId = linkedChatId
            this.slowModeDelay = slowModeDelay
            this.slowModeDelayExpiresIn = slowModeDelayExpiresIn
            this.canGetMembers = canGetMembers
            this.canSetUsername = canSetUsername
            this.canSetStickerSet = canSetStickerSet
            this.canSetLocation = canSetLocation
            this.canViewStatistics = canViewStatistics
            this.isAllHistoryAvailable = isAllHistoryAvailable
            this.stickerSetId = stickerSetId
            this.location = location
            this.inviteLink = inviteLink
            this.upgradedFromBasicGroupId = upgradedFromBasicGroupId
            this.upgradedFromMaxMessageId = upgradedFromMaxMessageId

        }

        override val constructor: Int @BsonIgnore get() = -1562832718

    }


    /**
     * Describes the current secret chat state
     */
    abstract class SecretChatState : Object()

    /**
     * The secret chat is not yet created
     * Waiting for the other user to get online
     */
    class SecretChatStatePending : SecretChatState() {

        override val constructor: Int @BsonIgnore get() = -1637050756

    }


    /**
     * The secret chat is ready to use
     */
    class SecretChatStateReady : SecretChatState() {

        override val constructor: Int @BsonIgnore get() = -1611352087

    }


    /**
     * The secret chat is closed
     */
    class SecretChatStateClosed : SecretChatState() {

        override val constructor: Int @BsonIgnore get() = -1945106707

    }


    /**
     * Represents a secret chat
     *
     * @id - Secret chat identifier
     * @userId - Identifier of the chat partner
     * @state - State of the secret chat
     * @isOutbound - True, if the chat was created by the current user
     *               Otherwise false
     * @ttl - Current message Time To Live setting (self-destruct timer) for the chat, in seconds
     * @keyHash - Hash of the currently used key for comparison with the hash of the chat partner's key
     *            This is a string of 36 little-endian bytes, which must be split into groups of 2 bits, each denoting a pixel of one of 4 colors
     *            The pixels must be used to make a 12x12 square image filled from left to right, top to bottom
     *            Alternatively, the first 32 bytes of the hash can be converted to the hexadecimal format and printed as 32 2-digit hex numbers
     * @layer - Secret chat layer
     *          Determines features supported by the other client
     *          Video notes are supported if the layer >= 66
     *          Nested text entities and underline and strikethrough entities are supported if the layer >= 101
     */
    class SecretChat : Object {

        var id: Int by WeakField()
        var userId: Int by WeakField()
        var state: SecretChatState by WeakField()
        var isOutbound: Boolean by WeakField()
        var ttl: Int by WeakField()
        var keyHash: ByteArray by WeakField()
        var layer: Int by WeakField()

        constructor()

        constructor(id: Int, userId: Int, state: SecretChatState, isOutbound: Boolean, ttl: Int, keyHash: ByteArray, layer: Int) {

            this.id = id
            this.userId = userId
            this.state = state
            this.isOutbound = isOutbound
            this.ttl = ttl
            this.keyHash = keyHash
            this.layer = layer

        }

        override val constructor: Int @BsonIgnore get() = 1279231629

    }


    /**
     * Contains information about the origin of a forwarded message
     */
    abstract class MessageForwardOrigin : Object()

    /**
     * The message was originally written by a known user
     *
     * @senderUserId - Identifier of the user that originally sent the message
     */
    class MessageForwardOriginUser : MessageForwardOrigin {

        var senderUserId: Int by WeakField()

        constructor()

        constructor(senderUserId: Int) {

            this.senderUserId = senderUserId

        }

        override val constructor: Int @BsonIgnore get() = 2781520

    }


    /**
     * The message was originally written by a user, which is hidden by their privacy settings
     *
     * @senderName - Name of the sender
     */
    class MessageForwardOriginHiddenUser : MessageForwardOrigin {

        var senderName: String by WeakField()

        constructor()

        constructor(senderName: String) {

            this.senderName = senderName

        }

        override val constructor: Int @BsonIgnore get() = -271257885

    }


    /**
     * The message was originally a post in a channel
     *
     * @chatId - Identifier of the chat from which the message was originally forwarded
     * @messageId - Message identifier of the original message
     *              0 if unknown
     * @authorSignature - Original post author signature
     */
    class MessageForwardOriginChannel : MessageForwardOrigin {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var authorSignature: String by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, authorSignature: String) {

            this.chatId = chatId
            this.messageId = messageId
            this.authorSignature = authorSignature

        }

        override val constructor: Int @BsonIgnore get() = 1490730723

    }


    /**
     * Contains information about a forwarded message
     *
     * @origin - Origin of a forwarded message
     * @date - Point in time (Unix timestamp) when the message was originally sent
     * @fromChatId - For messages forwarded to the chat with the current user (Saved Messages) or to the channel's discussion group, the identifier of the chat from which the message was forwarded last time
     *               0 if unknown
     * @fromMessageId - For messages forwarded to the chat with the current user (Saved Messages) or to the channel's discussion group, the identifier of the original message from which the new message was forwarded last time
     *                  0 if unknown
     */
    class MessageForwardInfo : Object {

        var origin: MessageForwardOrigin by WeakField()
        var date: Int by WeakField()
        var fromChatId: Long by WeakField()
        var fromMessageId: Long by WeakField()

        constructor()

        constructor(origin: MessageForwardOrigin, date: Int, fromChatId: Long, fromMessageId: Long) {

            this.origin = origin
            this.date = date
            this.fromChatId = fromChatId
            this.fromMessageId = fromMessageId

        }

        override val constructor: Int @BsonIgnore get() = -1622371186

    }


    /**
     * Contains information about the sending state of the message
     */
    abstract class MessageSendingState : Object()

    /**
     * The message is being sent now, but has not yet been delivered to the server
     */
    class MessageSendingStatePending : MessageSendingState() {

        override val constructor: Int @BsonIgnore get() = -1381803582

    }


    /**
     * The message failed to be sent
     *
     * @errorCode - An error code
     *              0 if unknown
     * @errorMessage - Error message
     * @canRetry - True, if the message can be re-sent
     * @retryAfter - Time left before the message can be re-sent, in seconds
     *               No update is sent when this field changes
     */
    class MessageSendingStateFailed : MessageSendingState {

        var errorCode: Int by WeakField()
        var errorMessage: String by WeakField()
        var canRetry: Boolean by WeakField()
        var retryAfter: Double by WeakField()

        constructor()

        constructor(errorCode: Int, errorMessage: String, canRetry: Boolean, retryAfter: Double) {

            this.errorCode = errorCode
            this.errorMessage = errorMessage
            this.canRetry = canRetry
            this.retryAfter = retryAfter

        }

        override val constructor: Int @BsonIgnore get() = 2054476087

    }


    /**
     * Describes a message
     *
     * @id - Message identifier, unique for the chat to which the message belongs
     * @senderUserId - Identifier of the user who sent the message
     *                 0 if unknown
     *                 Currently, it is unknown for channel posts and for channel posts automatically forwarded to discussion group
     * @chatId - Chat identifier
     * @sendingState - Information about the sending state of the message
     * @schedulingState - Information about the scheduling state of the message
     * @isOutgoing - True, if the message is outgoing
     * @canBeEdited - True, if the message can be edited
     *                For live location and poll messages this fields shows whether editMessageLiveLocation or stopPoll can be used with this message by the client
     * @canBeForwarded - True, if the message can be forwarded
     * @canBeDeletedOnlyForSelf - True, if the message can be deleted only for the current user while other users will continue to see it
     * @canBeDeletedForAllUsers - True, if the message can be deleted for all users
     * @isChannelPost - True, if the message is a channel post
     *                  All messages to channels are channel posts, all other messages are not channel posts
     * @containsUnreadMention - True, if the message contains an unread mention for the current user
     * @date - Point in time (Unix timestamp) when the message was sent
     * @editDate - Point in time (Unix timestamp) when the message was last edited
     * @forwardInfo - Information about the initial message sender
     * @replyToMessageId - If non-zero, the identifier of the message this message is replying to
     *                     Can be the identifier of a deleted message
     * @ttl - For self-destructing messages, the message's TTL (Time To Live), in seconds
     *        0 if none
     *        TDLib will send updateDeleteMessages or updateMessageContent once the TTL expires
     * @ttlExpiresIn - Time left before the message expires, in seconds
     * @viaBotUserId - If non-zero, the user identifier of the bot through which this message was sent
     * @authorSignature - For channel posts, optional author signature
     * @views - Number of times this message was viewed
     * @mediaAlbumId - Unique identifier of an album this message belongs to
     *                 Only photos and videos can be grouped together in albums
     * @restrictionReason - If non-empty, contains a human-readable description of the reason why access to this message must be restricted
     * @content - Content of the message
     * @replyMarkup - Reply markup for the message
     */
    class Message : Object {

        var id: Long by WeakField()
        var senderUserId: Int by WeakField()
        var chatId: Long by WeakField()
        var sendingState: MessageSendingState? = null
        var schedulingState: MessageSchedulingState? = null
        var isOutgoing: Boolean by WeakField()
        var canBeEdited: Boolean by WeakField()
        var canBeForwarded: Boolean by WeakField()
        var canBeDeletedOnlyForSelf: Boolean by WeakField()
        var canBeDeletedForAllUsers: Boolean by WeakField()
        var isChannelPost: Boolean by WeakField()
        var containsUnreadMention: Boolean by WeakField()
        var date: Int by WeakField()
        var editDate: Int by WeakField()
        var forwardInfo: MessageForwardInfo? = null
        var replyToMessageId: Long by WeakField()
        var ttl: Int by WeakField()
        var ttlExpiresIn: Double by WeakField()
        var viaBotUserId: Int by WeakField()
        var authorSignature: String by WeakField()
        var views: Int by WeakField()
        var mediaAlbumId: Long by WeakField()
        var restrictionReason: String by WeakField()
        var content: MessageContent by WeakField()
        var replyMarkup: ReplyMarkup? = null

        constructor()

        constructor(id: Long, senderUserId: Int, chatId: Long, sendingState: MessageSendingState? = null, schedulingState: MessageSchedulingState? = null, isOutgoing: Boolean, canBeEdited: Boolean, canBeForwarded: Boolean, canBeDeletedOnlyForSelf: Boolean, canBeDeletedForAllUsers: Boolean, isChannelPost: Boolean, containsUnreadMention: Boolean, date: Int, editDate: Int, forwardInfo: MessageForwardInfo? = null, replyToMessageId: Long, ttl: Int, ttlExpiresIn: Double, viaBotUserId: Int, authorSignature: String, views: Int, mediaAlbumId: Long, restrictionReason: String, content: MessageContent, replyMarkup: ReplyMarkup? = null) {

            this.id = id
            this.senderUserId = senderUserId
            this.chatId = chatId
            this.sendingState = sendingState
            this.schedulingState = schedulingState
            this.isOutgoing = isOutgoing
            this.canBeEdited = canBeEdited
            this.canBeForwarded = canBeForwarded
            this.canBeDeletedOnlyForSelf = canBeDeletedOnlyForSelf
            this.canBeDeletedForAllUsers = canBeDeletedForAllUsers
            this.isChannelPost = isChannelPost
            this.containsUnreadMention = containsUnreadMention
            this.date = date
            this.editDate = editDate
            this.forwardInfo = forwardInfo
            this.replyToMessageId = replyToMessageId
            this.ttl = ttl
            this.ttlExpiresIn = ttlExpiresIn
            this.viaBotUserId = viaBotUserId
            this.authorSignature = authorSignature
            this.views = views
            this.mediaAlbumId = mediaAlbumId
            this.restrictionReason = restrictionReason
            this.content = content
            this.replyMarkup = replyMarkup

        }

        override val constructor: Int @BsonIgnore get() = 1169109781

    }


    /**
     * Contains a list of messages
     *
     * @totalCount - Approximate total count of messages found
     * @messages - List of messages
     *             Messages may be null
     */
    class Messages : Object {

        var totalCount: Int by WeakField()
        var messages: Array<Message> by WeakField()

        constructor()

        constructor(totalCount: Int, messages: Array<Message>) {

            this.totalCount = totalCount
            this.messages = messages

        }

        override val constructor: Int @BsonIgnore get() = -16498159

    }


    /**
     * Contains a list of messages found by a search
     *
     * @messages - List of messages
     * @nextFromSearchId - Value to pass as from_search_id to get more results
     */
    class FoundMessages : Object {

        var messages: Array<Message> by WeakField()
        var nextFromSearchId: Long by WeakField()

        constructor()

        constructor(messages: Array<Message>, nextFromSearchId: Long) {

            this.messages = messages
            this.nextFromSearchId = nextFromSearchId

        }

        override val constructor: Int @BsonIgnore get() = 2135623881

    }


    /**
     * Describes the types of chats to which notification settings are applied
     */
    abstract class NotificationSettingsScope : Object()

    /**
     * Notification settings applied to all private and secret chats when the corresponding chat setting has a default value
     */
    class NotificationSettingsScopePrivateChats : NotificationSettingsScope() {

        override val constructor: Int @BsonIgnore get() = 937446759

    }


    /**
     * Notification settings applied to all basic groups and supergroups when the corresponding chat setting has a default value
     */
    class NotificationSettingsScopeGroupChats : NotificationSettingsScope() {

        override val constructor: Int @BsonIgnore get() = 1212142067

    }


    /**
     * Notification settings applied to all channels when the corresponding chat setting has a default value
     */
    class NotificationSettingsScopeChannelChats : NotificationSettingsScope() {

        override val constructor: Int @BsonIgnore get() = 548013448

    }


    /**
     * Contains information about notification settings for a chat
     *
     * @useDefaultMuteFor - If true, mute_for is ignored and the value for the relevant type of chat is used instead
     * @muteFor - Time left before notifications will be unmuted, in seconds
     * @useDefaultSound - If true, sound is ignored and the value for the relevant type of chat is used instead
     * @sound - The name of an audio file to be used for notification sounds
     *          Only applies to iOS applications
     * @useDefaultShowPreview - If true, show_preview is ignored and the value for the relevant type of chat is used instead
     * @showPreview - True, if message content should be displayed in notifications
     * @useDefaultDisablePinnedMessageNotifications - If true, disable_pinned_message_notifications is ignored and the value for the relevant type of chat is used instead
     * @disablePinnedMessageNotifications - If true, notifications for incoming pinned messages will be created as for an ordinary unread message
     * @useDefaultDisableMentionNotifications - If true, disable_mention_notifications is ignored and the value for the relevant type of chat is used instead
     * @disableMentionNotifications - If true, notifications for messages with mentions will be created as for an ordinary unread message
     */
    class ChatNotificationSettings : Object {

        var useDefaultMuteFor: Boolean by WeakField()
        var muteFor: Int by WeakField()
        var useDefaultSound: Boolean by WeakField()
        var sound: String by WeakField()
        var useDefaultShowPreview: Boolean by WeakField()
        var showPreview: Boolean by WeakField()
        var useDefaultDisablePinnedMessageNotifications: Boolean by WeakField()
        var disablePinnedMessageNotifications: Boolean by WeakField()
        var useDefaultDisableMentionNotifications: Boolean by WeakField()
        var disableMentionNotifications: Boolean by WeakField()

        constructor()

        constructor(useDefaultMuteFor: Boolean, muteFor: Int, useDefaultSound: Boolean, sound: String, useDefaultShowPreview: Boolean, showPreview: Boolean, useDefaultDisablePinnedMessageNotifications: Boolean, disablePinnedMessageNotifications: Boolean, useDefaultDisableMentionNotifications: Boolean, disableMentionNotifications: Boolean) {

            this.useDefaultMuteFor = useDefaultMuteFor
            this.muteFor = muteFor
            this.useDefaultSound = useDefaultSound
            this.sound = sound
            this.useDefaultShowPreview = useDefaultShowPreview
            this.showPreview = showPreview
            this.useDefaultDisablePinnedMessageNotifications = useDefaultDisablePinnedMessageNotifications
            this.disablePinnedMessageNotifications = disablePinnedMessageNotifications
            this.useDefaultDisableMentionNotifications = useDefaultDisableMentionNotifications
            this.disableMentionNotifications = disableMentionNotifications

        }

        override val constructor: Int @BsonIgnore get() = 1503183218

    }


    /**
     * Contains information about notification settings for several chats
     *
     * @muteFor - Time left before notifications will be unmuted, in seconds
     * @sound - The name of an audio file to be used for notification sounds
     *          Only applies to iOS applications
     * @showPreview - True, if message content should be displayed in notifications
     * @disablePinnedMessageNotifications - True, if notifications for incoming pinned messages will be created as for an ordinary unread message
     * @disableMentionNotifications - True, if notifications for messages with mentions will be created as for an ordinary unread message
     */
    class ScopeNotificationSettings : Object {

        var muteFor: Int by WeakField()
        var sound: String by WeakField()
        var showPreview: Boolean by WeakField()
        var disablePinnedMessageNotifications: Boolean by WeakField()
        var disableMentionNotifications: Boolean by WeakField()

        constructor()

        constructor(muteFor: Int, sound: String, showPreview: Boolean, disablePinnedMessageNotifications: Boolean, disableMentionNotifications: Boolean) {

            this.muteFor = muteFor
            this.sound = sound
            this.showPreview = showPreview
            this.disablePinnedMessageNotifications = disablePinnedMessageNotifications
            this.disableMentionNotifications = disableMentionNotifications

        }

        override val constructor: Int @BsonIgnore get() = -426103745

    }


    /**
     * Contains information about a message draft
     *
     * @replyToMessageId - Identifier of the message to reply to
     *                     0 if none
     * @inputMessageText - Content of the message draft
     *                     This should always be of type inputMessageText
     */
    class DraftMessage : Object {

        var replyToMessageId: Long by WeakField()
        var inputMessageText: InputMessageContent by WeakField()

        constructor()

        constructor(replyToMessageId: Long, inputMessageText: InputMessageContent) {

            this.replyToMessageId = replyToMessageId
            this.inputMessageText = inputMessageText

        }

        override val constructor: Int @BsonIgnore get() = 1902914742

    }


    /**
     * Describes the type of a chat
     */
    abstract class ChatType : Object()

    /**
     * An ordinary chat with a user
     *
     * @userId - User identifier
     */
    class ChatTypePrivate : ChatType {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = 1700720838

    }


    /**
     * A basic group (i.e., a chat with 0-200 other users)
     *
     * @basicGroupId - Basic group identifier
     */
    class ChatTypeBasicGroup : ChatType {

        var basicGroupId: Int by WeakField()

        constructor()

        constructor(basicGroupId: Int) {

            this.basicGroupId = basicGroupId

        }

        override val constructor: Int @BsonIgnore get() = 21815278

    }


    /**
     * A supergroup (i.e
     * A chat with up to GetOption("supergroup_max_size") other users), or channel (with unlimited members)
     *
     * @supergroupId - Supergroup or channel identifier
     * @isChannel - True, if the supergroup is a channel
     */
    class ChatTypeSupergroup : ChatType {

        var supergroupId: Int by WeakField()
        var isChannel: Boolean by WeakField()

        constructor()

        constructor(supergroupId: Int, isChannel: Boolean) {

            this.supergroupId = supergroupId
            this.isChannel = isChannel

        }

        override val constructor: Int @BsonIgnore get() = 955152366

    }


    /**
     * A secret chat with a user
     *
     * @secretChatId - Secret chat identifier
     * @userId - User identifier of the secret chat peer
     */
    class ChatTypeSecret : ChatType {

        var secretChatId: Int by WeakField()
        var userId: Int by WeakField()

        constructor()

        constructor(secretChatId: Int, userId: Int) {

            this.secretChatId = secretChatId
            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = 136722563

    }


    /**
     * Describes a list of chats
     */
    abstract class ChatList : Object()

    /**
     * A main list of chats
     */
    class ChatListMain : ChatList() {

        override val constructor: Int @BsonIgnore get() = -400991316

    }


    /**
     * A list of chats usually located at the top of the main chat list
     * Unmuted chats are automatically moved from the Archive to the Main chat list when a new message arrives
     */
    class ChatListArchive : ChatList() {

        override val constructor: Int @BsonIgnore get() = 362770115

    }


    /**
     * A chat
     * (Can be a private chat, basic group, supergroup, or secret chat)
     *
     * @id - Chat unique identifier
     * @type - Type of the chat
     * @chatList - A chat list to which the chat belongs
     * @title - Chat title
     * @photo - Chat photo
     * @permissions - Actions that non-administrator chat members are allowed to take in the chat
     * @lastMessage - Last message in the chat
     * @order - Descending parameter by which chats are sorted in the main chat list
     *          If the order number of two chats is the same, they must be sorted in descending order by ID
     *          If 0, the position of the chat in the list is undetermined
     * @isPinned - True, if the chat is pinned
     * @isMarkedAsUnread - True, if the chat is marked as unread
     * @isSponsored - True, if the chat is sponsored by the user's MTProxy server
     * @hasScheduledMessages - True, if the chat has scheduled messages
     * @canBeDeletedOnlyForSelf - True, if the chat messages can be deleted only for the current user while other users will continue to see the messages
     * @canBeDeletedForAllUsers - True, if the chat messages can be deleted for all users
     * @canBeReported - True, if the chat can be reported to Telegram moderators through reportChat
     * @defaultDisableNotification - Default value of the disable_notification parameter, used when a message is sent to the chat
     * @unreadCount - Number of unread messages in the chat
     * @lastReadInboxMessageId - Identifier of the last read incoming message
     * @lastReadOutboxMessageId - Identifier of the last read outgoing message
     * @unreadMentionCount - Number of unread messages with a mention/reply in the chat
     * @notificationSettings - Notification settings for this chat
     * @actionBar - Describes actions which should be possible to do through a chat action bar
     * @pinnedMessageId - Identifier of the pinned message in the chat
     *                    0 if none
     * @replyMarkupMessageId - Identifier of the message from which reply markup needs to be used
     *                         0 if there is no default custom reply markup in the chat
     * @draftMessage - A draft of a message in the chat
     * @clientData - Contains client-specific data associated with the chat
     *               (For example, the chat position or local chat notification settings can be stored here.) Persistent if the message database is used
     */
    class Chat : Object {

        var id: Long by WeakField()
        var type: ChatType by WeakField()
        var chatList: ChatList? = null
        var title: String by WeakField()
        var photo: ChatPhoto? = null
        var permissions: ChatPermissions by WeakField()
        var lastMessage: Message? = null
        var order: Long by WeakField()
        var isPinned: Boolean by WeakField()
        var isMarkedAsUnread: Boolean by WeakField()
        var isSponsored: Boolean by WeakField()
        var hasScheduledMessages: Boolean by WeakField()
        var canBeDeletedOnlyForSelf: Boolean by WeakField()
        var canBeDeletedForAllUsers: Boolean by WeakField()
        var canBeReported: Boolean by WeakField()
        var defaultDisableNotification: Boolean by WeakField()
        var unreadCount: Int by WeakField()
        var lastReadInboxMessageId: Long by WeakField()
        var lastReadOutboxMessageId: Long by WeakField()
        var unreadMentionCount: Int by WeakField()
        var notificationSettings: ChatNotificationSettings by WeakField()
        var actionBar: ChatActionBar? = null
        var pinnedMessageId: Long by WeakField()
        var replyMarkupMessageId: Long by WeakField()
        var draftMessage: DraftMessage? = null
        var clientData: String by WeakField()

        constructor()

        constructor(id: Long, type: ChatType, chatList: ChatList? = null, title: String, photo: ChatPhoto? = null, permissions: ChatPermissions, lastMessage: Message? = null, order: Long, isPinned: Boolean, isMarkedAsUnread: Boolean, isSponsored: Boolean, hasScheduledMessages: Boolean, canBeDeletedOnlyForSelf: Boolean, canBeDeletedForAllUsers: Boolean, canBeReported: Boolean, defaultDisableNotification: Boolean, unreadCount: Int, lastReadInboxMessageId: Long, lastReadOutboxMessageId: Long, unreadMentionCount: Int, notificationSettings: ChatNotificationSettings, actionBar: ChatActionBar? = null, pinnedMessageId: Long, replyMarkupMessageId: Long, draftMessage: DraftMessage? = null, clientData: String) {

            this.id = id
            this.type = type
            this.chatList = chatList
            this.title = title
            this.photo = photo
            this.permissions = permissions
            this.lastMessage = lastMessage
            this.order = order
            this.isPinned = isPinned
            this.isMarkedAsUnread = isMarkedAsUnread
            this.isSponsored = isSponsored
            this.hasScheduledMessages = hasScheduledMessages
            this.canBeDeletedOnlyForSelf = canBeDeletedOnlyForSelf
            this.canBeDeletedForAllUsers = canBeDeletedForAllUsers
            this.canBeReported = canBeReported
            this.defaultDisableNotification = defaultDisableNotification
            this.unreadCount = unreadCount
            this.lastReadInboxMessageId = lastReadInboxMessageId
            this.lastReadOutboxMessageId = lastReadOutboxMessageId
            this.unreadMentionCount = unreadMentionCount
            this.notificationSettings = notificationSettings
            this.actionBar = actionBar
            this.pinnedMessageId = pinnedMessageId
            this.replyMarkupMessageId = replyMarkupMessageId
            this.draftMessage = draftMessage
            this.clientData = clientData

        }

        override val constructor: Int @BsonIgnore get() = -861487386

    }


    /**
     * Represents a list of chats
     *
     * @chatIds - List of chat identifiers
     */
    class Chats : Object {

        var chatIds: LongArray by WeakField()

        constructor()

        constructor(chatIds: LongArray) {

            this.chatIds = chatIds

        }

        override val constructor: Int @BsonIgnore get() = -1687756019

    }


    /**
     * Describes a chat located nearby
     *
     * @chatId - Chat identifier
     * @distance - Distance to the chat location in meters
     */
    class ChatNearby : Object {

        var chatId: Long by WeakField()
        var distance: Int by WeakField()

        constructor()

        constructor(chatId: Long, distance: Int) {

            this.chatId = chatId
            this.distance = distance

        }

        override val constructor: Int @BsonIgnore get() = 48120405

    }


    /**
     * Represents a list of chats located nearby
     *
     * @usersNearby - List of users nearby
     * @supergroupsNearby - List of location-based supergroups nearby
     */
    class ChatsNearby : Object {

        var usersNearby: Array<ChatNearby> by WeakField()
        var supergroupsNearby: Array<ChatNearby> by WeakField()

        constructor()

        constructor(usersNearby: Array<ChatNearby>, supergroupsNearby: Array<ChatNearby>) {

            this.usersNearby = usersNearby
            this.supergroupsNearby = supergroupsNearby

        }

        override val constructor: Int @BsonIgnore get() = 187746081

    }


    /**
     * Contains a chat invite link
     *
     * @inviteLink - Chat invite link
     */
    class ChatInviteLink : Object {

        var inviteLink: String by WeakField()

        constructor()

        constructor(inviteLink: String) {

            this.inviteLink = inviteLink

        }

        override val constructor: Int @BsonIgnore get() = -882072492

    }


    /**
     * Contains information about a chat invite link
     *
     * @chatId - Chat identifier of the invite link
     *           0 if the user is not a member of this chat
     * @type - Contains information about the type of the chat
     * @title - Title of the chat
     * @photo - Chat photo
     * @memberCount - Number of members
     * @memberUserIds - User identifiers of some chat members that may be known to the current user
     * @isPublic - True, if the chat is a public supergroup or channel, i.e
     *             It has a username or it is a location-based supergroup
     */
    class ChatInviteLinkInfo : Object {

        var chatId: Long by WeakField()
        var type: ChatType by WeakField()
        var title: String by WeakField()
        var photo: ChatPhoto? = null
        var memberCount: Int by WeakField()
        var memberUserIds: IntArray by WeakField()
        var isPublic: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, type: ChatType, title: String, photo: ChatPhoto? = null, memberCount: Int, memberUserIds: IntArray, isPublic: Boolean) {

            this.chatId = chatId
            this.type = type
            this.title = title
            this.photo = photo
            this.memberCount = memberCount
            this.memberUserIds = memberUserIds
            this.isPublic = isPublic

        }

        override val constructor: Int @BsonIgnore get() = -323394424

    }


    /**
     * Describes a type of public chats
     */
    abstract class PublicChatType : Object()

    /**
     * The chat is public, because it has username
     */
    class PublicChatTypeHasUsername : PublicChatType() {

        override val constructor: Int @BsonIgnore get() = 350789758

    }


    /**
     * The chat is public, because it is a location-based supergroup
     */
    class PublicChatTypeIsLocationBased : PublicChatType() {

        override val constructor: Int @BsonIgnore get() = 1183735952

    }


    /**
     * Describes actions which should be possible to do through a chat action bar
     */
    abstract class ChatActionBar : Object()

    /**
     * The chat can be reported as spam using the method reportChat with the reason chatReportReasonSpam
     */
    class ChatActionBarReportSpam : ChatActionBar() {

        override val constructor: Int @BsonIgnore get() = -1603417249

    }


    /**
     * The chat is a location-based supergroup, which can be reported as having unrelated location using the method reportChat with the reason chatReportReasonUnrelatedLocation
     */
    class ChatActionBarReportUnrelatedLocation : ChatActionBar() {

        override val constructor: Int @BsonIgnore get() = 758175489

    }


    /**
     * The chat is a private or secret chat, which can be reported using the method reportChat, or the other user can be added to the contact list using the method addContact, or the other user can be blocked using the method blockUser
     */
    class ChatActionBarReportAddBlock : ChatActionBar() {

        override val constructor: Int @BsonIgnore get() = -87894249

    }


    /**
     * The chat is a private or secret chat and the other user can be added to the contact list using the method addContact
     */
    class ChatActionBarAddContact : ChatActionBar() {

        override val constructor: Int @BsonIgnore get() = -733325295

    }


    /**
     * The chat is a private or secret chat with a mutual contact and the user's phone number can be shared with the other user using the method sharePhoneNumber
     */
    class ChatActionBarSharePhoneNumber : ChatActionBar() {

        override val constructor: Int @BsonIgnore get() = 35188697

    }


    /**
     * Describes a keyboard button type
     */
    abstract class KeyboardButtonType : Object()

    /**
     * A simple button, with text that should be sent when the button is pressed
     */
    class KeyboardButtonTypeText : KeyboardButtonType() {

        override val constructor: Int @BsonIgnore get() = -1773037256

    }


    /**
     * A button that sends the user's phone number when pressed
     * Available only in private chats
     */
    class KeyboardButtonTypeRequestPhoneNumber : KeyboardButtonType() {

        override val constructor: Int @BsonIgnore get() = -1529235527

    }


    /**
     * A button that sends the user's location when pressed
     * Available only in private chats
     */
    class KeyboardButtonTypeRequestLocation : KeyboardButtonType() {

        override val constructor: Int @BsonIgnore get() = -125661955

    }


    /**
     * Represents a single button in a bot keyboard
     *
     * @text - Text of the button
     * @type - Type of the button
     */
    class KeyboardButton : Object {

        var text: String by WeakField()
        var type: KeyboardButtonType by WeakField()

        constructor()

        constructor(text: String, type: KeyboardButtonType) {

            this.text = text
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -2069836172

    }


    /**
     * Describes the type of an inline keyboard button
     */
    abstract class InlineKeyboardButtonType : Object()

    /**
     * A button that opens a specified URL
     *
     * @url - HTTP or tg:// URL to open
     */
    class InlineKeyboardButtonTypeUrl : InlineKeyboardButtonType {

        var url: String by WeakField()

        constructor()

        constructor(url: String) {

            this.url = url

        }

        override val constructor: Int @BsonIgnore get() = 1130741420

    }


    /**
     * A button that opens a specified URL and automatically logs in in current user if they allowed to do that
     *
     * @url - An HTTP URL to open
     * @id - Unique button identifier
     * @forwardText - If non-empty, new text of the button in forwarded messages
     */
    class InlineKeyboardButtonTypeLoginUrl : InlineKeyboardButtonType {

        var url: String by WeakField()
        var id: Int by WeakField()
        var forwardText: String by WeakField()

        constructor()

        constructor(url: String, id: Int, forwardText: String) {

            this.url = url
            this.id = id
            this.forwardText = forwardText

        }

        override val constructor: Int @BsonIgnore get() = 281435539

    }


    /**
     * A button that sends a special callback query to a bot
     *
     * @data - Data to be sent to the bot via a callback query
     */
    class InlineKeyboardButtonTypeCallback : InlineKeyboardButtonType {

        var data: ByteArray by WeakField()

        constructor()

        constructor(data: ByteArray) {

            this.data = data

        }

        override val constructor: Int @BsonIgnore get() = -1127515139

    }


    /**
     * A button with a game that sends a special callback query to a bot
     * This button must be in the first column and row of the keyboard and can be attached only to a message with content of the type messageGame
     */
    class InlineKeyboardButtonTypeCallbackGame : InlineKeyboardButtonType() {

        override val constructor: Int @BsonIgnore get() = -383429528

    }


    /**
     * A button that forces an inline query to the bot to be inserted in the input field
     *
     * @query - Inline query to be sent to the bot
     * @inCurrentChat - True, if the inline query should be sent from the current chat
     */
    class InlineKeyboardButtonTypeSwitchInline : InlineKeyboardButtonType {

        var query: String by WeakField()
        var inCurrentChat: Boolean by WeakField()

        constructor()

        constructor(query: String, inCurrentChat: Boolean) {

            this.query = query
            this.inCurrentChat = inCurrentChat

        }

        override val constructor: Int @BsonIgnore get() = -2035563307

    }


    /**
     * A button to buy something
     * This button must be in the first column and row of the keyboard and can be attached only to a message with content of the type messageInvoice
     */
    class InlineKeyboardButtonTypeBuy : InlineKeyboardButtonType() {

        override val constructor: Int @BsonIgnore get() = 1360739440

    }


    /**
     * Represents a single button in an inline keyboard
     *
     * @text - Text of the button
     * @type - Type of the button
     */
    class InlineKeyboardButton : Object {

        var text: String by WeakField()
        var type: InlineKeyboardButtonType by WeakField()

        constructor()

        constructor(text: String, type: InlineKeyboardButtonType) {

            this.text = text
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -372105704

    }


    /**
     * Contains a description of a custom keyboard and actions that can be done with it to quickly reply to bots
     */
    abstract class ReplyMarkup : Object()

    /**
     * Instructs clients to remove the keyboard once this message has been received
     * This kind of keyboard can't be received in an incoming message
     * Instead, UpdateChatReplyMarkup with message_id == 0 will be sent
     *
     * @isPersonal - True, if the keyboard is removed only for the mentioned users or the target user of a reply
     */
    class ReplyMarkupRemoveKeyboard : ReplyMarkup {

        var isPersonal: Boolean by WeakField()

        constructor()

        constructor(isPersonal: Boolean) {

            this.isPersonal = isPersonal

        }

        override val constructor: Int @BsonIgnore get() = -691252879

    }


    /**
     * Instructs clients to force a reply to this message
     *
     * @isPersonal - True, if a forced reply must automatically be shown to the current user
     *               For outgoing messages, specify true to show the forced reply only for the mentioned users and for the target user of a reply
     */
    class ReplyMarkupForceReply : ReplyMarkup {

        var isPersonal: Boolean by WeakField()

        constructor()

        constructor(isPersonal: Boolean) {

            this.isPersonal = isPersonal

        }

        override val constructor: Int @BsonIgnore get() = 1039104593

    }


    /**
     * Contains a custom keyboard layout to quickly reply to bots
     *
     * @rows - A list of rows of bot keyboard buttons
     * @resizeKeyboard - True, if the client needs to resize the keyboard vertically
     * @oneTime - True, if the client needs to hide the keyboard after use
     * @isPersonal - True, if the keyboard must automatically be shown to the current user
     *               For outgoing messages, specify true to show the keyboard only for the mentioned users and for the target user of a reply
     */
    class ReplyMarkupShowKeyboard : ReplyMarkup {

        var rows: Array<Array<KeyboardButton>> by WeakField()
        var resizeKeyboard: Boolean by WeakField()
        var oneTime: Boolean by WeakField()
        var isPersonal: Boolean by WeakField()

        constructor()

        constructor(rows: Array<Array<KeyboardButton>>, resizeKeyboard: Boolean, oneTime: Boolean, isPersonal: Boolean) {

            this.rows = rows
            this.resizeKeyboard = resizeKeyboard
            this.oneTime = oneTime
            this.isPersonal = isPersonal

        }

        override val constructor: Int @BsonIgnore get() = -992627133

    }


    /**
     * Contains an inline keyboard layout
     *
     * @rows - A list of rows of inline keyboard buttons
     */
    class ReplyMarkupInlineKeyboard : ReplyMarkup {

        var rows: Array<Array<InlineKeyboardButton>> by WeakField()

        constructor()

        constructor(rows: Array<Array<InlineKeyboardButton>>) {

            this.rows = rows

        }

        override val constructor: Int @BsonIgnore get() = -619317658

    }


    /**
     * Contains information about an inline button of type inlineKeyboardButtonTypeLoginUrl
     */
    abstract class LoginUrlInfo : Object()

    /**
     * An HTTP url needs to be open
     *
     * @url - The URL to open
     * @skipConfirm - True, if there is no need to show an ordinary open URL confirm
     */
    class LoginUrlInfoOpen : LoginUrlInfo {

        var url: String by WeakField()
        var skipConfirm: Boolean by WeakField()

        constructor()

        constructor(url: String, skipConfirm: Boolean) {

            this.url = url
            this.skipConfirm = skipConfirm

        }

        override val constructor: Int @BsonIgnore get() = -1079045420

    }


    /**
     * An authorization confirmation dialog needs to be shown to the user
     *
     * @url - An HTTP URL to be opened
     * @domain - A domain of the URL
     * @botUserId - User identifier of a bot linked with the website
     * @requestWriteAccess - True, if the user needs to be requested to give the permission to the bot to send them messages
     */
    class LoginUrlInfoRequestConfirmation : LoginUrlInfo {

        var url: String by WeakField()
        var domain: String by WeakField()
        var botUserId: Int by WeakField()
        var requestWriteAccess: Boolean by WeakField()

        constructor()

        constructor(url: String, domain: String, botUserId: Int, requestWriteAccess: Boolean) {

            this.url = url
            this.domain = domain
            this.botUserId = botUserId
            this.requestWriteAccess = requestWriteAccess

        }

        override val constructor: Int @BsonIgnore get() = -1761898342

    }


    /**
     * Describes a text object inside an instant-view web page
     */
    abstract class RichText : Object()

    /**
     * A plain text
     *
     * @text - Text
     */
    class RichTextPlain : RichText {

        var text: String by WeakField()

        constructor()

        constructor(text: String) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 482617702

    }


    /**
     * A bold rich text
     *
     * @text - Text
     */
    class RichTextBold : RichText {

        var text: RichText by WeakField()

        constructor()

        constructor(text: RichText) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 1670844268

    }


    /**
     * An italicized rich text
     *
     * @text - Text
     */
    class RichTextItalic : RichText {

        var text: RichText by WeakField()

        constructor()

        constructor(text: RichText) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 1853354047

    }


    /**
     * An underlined rich text
     *
     * @text - Text
     */
    class RichTextUnderline : RichText {

        var text: RichText by WeakField()

        constructor()

        constructor(text: RichText) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = -536019572

    }


    /**
     * A strikethrough rich text
     *
     * @text - Text
     */
    class RichTextStrikethrough : RichText {

        var text: RichText by WeakField()

        constructor()

        constructor(text: RichText) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 723413585

    }


    /**
     * A fixed-width rich text
     *
     * @text - Text
     */
    class RichTextFixed : RichText {

        var text: RichText by WeakField()

        constructor()

        constructor(text: RichText) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = -1271496249

    }


    /**
     * A rich text URL link
     *
     * @text - Text
     * @url - URL
     * @isCached - True, if the URL has cached instant view server-side
     */
    class RichTextUrl : RichText {

        var text: RichText by WeakField()
        var url: String by WeakField()
        var isCached: Boolean by WeakField()

        constructor()

        constructor(text: RichText, url: String, isCached: Boolean) {

            this.text = text
            this.url = url
            this.isCached = isCached

        }

        override val constructor: Int @BsonIgnore get() = 83939092

    }


    /**
     * A rich text email link
     *
     * @text - Text
     * @emailAddress - Email address
     */
    class RichTextEmailAddress : RichText {

        var text: RichText by WeakField()
        var emailAddress: String by WeakField()

        constructor()

        constructor(text: RichText, emailAddress: String) {

            this.text = text
            this.emailAddress = emailAddress

        }

        override val constructor: Int @BsonIgnore get() = 40018679

    }


    /**
     * A subscript rich text
     *
     * @text - Text
     */
    class RichTextSubscript : RichText {

        var text: RichText by WeakField()

        constructor()

        constructor(text: RichText) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = -868197812

    }


    /**
     * A superscript rich text
     *
     * @text - Text
     */
    class RichTextSuperscript : RichText {

        var text: RichText by WeakField()

        constructor()

        constructor(text: RichText) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = -382241437

    }


    /**
     * A marked rich text
     *
     * @text - Text
     */
    class RichTextMarked : RichText {

        var text: RichText by WeakField()

        constructor()

        constructor(text: RichText) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = -1271999614

    }


    /**
     * A rich text phone number
     *
     * @text - Text
     * @phoneNumber - Phone number
     */
    class RichTextPhoneNumber : RichText {

        var text: RichText by WeakField()
        var phoneNumber: String by WeakField()

        constructor()

        constructor(text: RichText, phoneNumber: String) {

            this.text = text
            this.phoneNumber = phoneNumber

        }

        override val constructor: Int @BsonIgnore get() = 128521539

    }


    /**
     * A small image inside the text
     *
     * @document - The image represented as a document
     *             The image can be in GIF, JPEG or PNG format
     * @width - Width of a bounding box in which the image should be shown
     *          0 if unknown
     * @height - Height of a bounding box in which the image should be shown
     *           0 if unknown
     */
    class RichTextIcon : RichText {

        var document: Document by WeakField()
        var width: Int by WeakField()
        var height: Int by WeakField()

        constructor()

        constructor(document: Document, width: Int, height: Int) {

            this.document = document
            this.width = width
            this.height = height

        }

        override val constructor: Int @BsonIgnore get() = -1480316158

    }


    /**
     * A rich text anchor
     *
     * @text - Text
     * @name - Anchor name
     */
    class RichTextAnchor : RichText {

        var text: RichText by WeakField()
        var name: String by WeakField()

        constructor()

        constructor(text: RichText, name: String) {

            this.text = text
            this.name = name

        }

        override val constructor: Int @BsonIgnore get() = 673137292

    }


    /**
     * A concatenation of rich texts
     *
     * @texts - Texts
     */
    class RichTexts : RichText {

        var texts: Array<RichText> by WeakField()

        constructor()

        constructor(texts: Array<RichText>) {

            this.texts = texts

        }

        override val constructor: Int @BsonIgnore get() = 1647457821

    }


    /**
     * Contains a caption of an instant view web page block, consisting of a text and a trailing credit
     *
     * @text - Content of the caption
     * @credit - Block credit (like HTML tag <cite>)
     */
    class PageBlockCaption : Object {

        var text: RichText by WeakField()
        var credit: RichText by WeakField()

        constructor()

        constructor(text: RichText, credit: RichText) {

            this.text = text
            this.credit = credit

        }

        override val constructor: Int @BsonIgnore get() = -1180064650

    }


    /**
     * Describes an item of a list page block
     *
     * @label - Item label
     * @pageBlocks - Item blocks
     */
    class PageBlockListItem : Object {

        var label: String by WeakField()
        var pageBlocks: Array<PageBlock> by WeakField()

        constructor()

        constructor(label: String, pageBlocks: Array<PageBlock>) {

            this.label = label
            this.pageBlocks = pageBlocks

        }

        override val constructor: Int @BsonIgnore get() = 323186259

    }


    /**
     * Describes a horizontal alignment of a table cell content
     */
    abstract class PageBlockHorizontalAlignment : Object()

    /**
     * The content should be left-aligned
     */
    class PageBlockHorizontalAlignmentLeft : PageBlockHorizontalAlignment() {

        override val constructor: Int @BsonIgnore get() = 848701417

    }


    /**
     * The content should be center-aligned
     */
    class PageBlockHorizontalAlignmentCenter : PageBlockHorizontalAlignment() {

        override val constructor: Int @BsonIgnore get() = -1009203990

    }


    /**
     * The content should be right-aligned
     */
    class PageBlockHorizontalAlignmentRight : PageBlockHorizontalAlignment() {

        override val constructor: Int @BsonIgnore get() = 1371369214

    }


    /**
     * Describes a Vertical alignment of a table cell content
     */
    abstract class PageBlockVerticalAlignment : Object()

    /**
     * The content should be top-aligned
     */
    class PageBlockVerticalAlignmentTop : PageBlockVerticalAlignment() {

        override val constructor: Int @BsonIgnore get() = 195500454

    }


    /**
     * The content should be middle-aligned
     */
    class PageBlockVerticalAlignmentMiddle : PageBlockVerticalAlignment() {

        override val constructor: Int @BsonIgnore get() = -2123096587

    }


    /**
     * The content should be bottom-aligned
     */
    class PageBlockVerticalAlignmentBottom : PageBlockVerticalAlignment() {

        override val constructor: Int @BsonIgnore get() = 2092531158

    }


    /**
     * Represents a cell of a table
     *
     * @text - Cell text
     *         If the text is null, then the cell should be invisible
     * @isHeader - True, if it is a header cell
     * @colspan - The number of columns the cell should span
     * @rowspan - The number of rows the cell should span
     * @align - Horizontal cell content alignment
     * @valign - Vertical cell content alignment
     */
    class PageBlockTableCell : Object {

        var text: RichText? = null
        var isHeader: Boolean by WeakField()
        var colspan: Int by WeakField()
        var rowspan: Int by WeakField()
        var align: PageBlockHorizontalAlignment by WeakField()
        var valign: PageBlockVerticalAlignment by WeakField()

        constructor()

        constructor(text: RichText? = null, isHeader: Boolean, colspan: Int, rowspan: Int, align: PageBlockHorizontalAlignment, valign: PageBlockVerticalAlignment) {

            this.text = text
            this.isHeader = isHeader
            this.colspan = colspan
            this.rowspan = rowspan
            this.align = align
            this.valign = valign

        }

        override val constructor: Int @BsonIgnore get() = 1417658214

    }


    /**
     * Contains information about a related article
     *
     * @url - Related article URL
     * @title - Article title
     * @description - Article description
     * @photo - Article photo
     * @author - Article author
     * @publishDate - Point in time (Unix timestamp) when the article was published
     *                0 if unknown
     */
    class PageBlockRelatedArticle : Object {

        var url: String by WeakField()
        var title: String? = null
        var description: String? = null
        var photo: Photo? = null
        var author: String? = null
        var publishDate: Int by WeakField()

        constructor()

        constructor(url: String, title: String? = null, description: String? = null, photo: Photo? = null, author: String? = null, publishDate: Int) {

            this.url = url
            this.title = title
            this.description = description
            this.photo = photo
            this.author = author
            this.publishDate = publishDate

        }

        override val constructor: Int @BsonIgnore get() = 481199251

    }


    /**
     * Describes a block of an instant view web page
     */
    abstract class PageBlock : Object()

    /**
     * The title of a page
     *
     * @title - Title
     */
    class PageBlockTitle : PageBlock {

        var title: RichText by WeakField()

        constructor()

        constructor(title: RichText) {

            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = 1629664784

    }


    /**
     * The subtitle of a page
     *
     * @subtitle - Subtitle
     */
    class PageBlockSubtitle : PageBlock {

        var subtitle: RichText by WeakField()

        constructor()

        constructor(subtitle: RichText) {

            this.subtitle = subtitle

        }

        override val constructor: Int @BsonIgnore get() = 264524263

    }


    /**
     * The author and publishing date of a page
     *
     * @author - Author
     * @publishDate - Point in time (Unix timestamp) when the article was published
     *                0 if unknown
     */
    class PageBlockAuthorDate : PageBlock {

        var author: RichText by WeakField()
        var publishDate: Int by WeakField()

        constructor()

        constructor(author: RichText, publishDate: Int) {

            this.author = author
            this.publishDate = publishDate

        }

        override val constructor: Int @BsonIgnore get() = 1300231184

    }


    /**
     * A header
     *
     * @header - Header
     */
    class PageBlockHeader : PageBlock {

        var header: RichText by WeakField()

        constructor()

        constructor(header: RichText) {

            this.header = header

        }

        override val constructor: Int @BsonIgnore get() = 1402854811

    }


    /**
     * A subheader
     *
     * @subheader - Subheader
     */
    class PageBlockSubheader : PageBlock {

        var subheader: RichText by WeakField()

        constructor()

        constructor(subheader: RichText) {

            this.subheader = subheader

        }

        override val constructor: Int @BsonIgnore get() = 1263956774

    }


    /**
     * A kicker
     *
     * @kicker - Kicker
     */
    class PageBlockKicker : PageBlock {

        var kicker: RichText by WeakField()

        constructor()

        constructor(kicker: RichText) {

            this.kicker = kicker

        }

        override val constructor: Int @BsonIgnore get() = 1361282635

    }


    /**
     * A text paragraph
     *
     * @text - Paragraph text
     */
    class PageBlockParagraph : PageBlock {

        var text: RichText by WeakField()

        constructor()

        constructor(text: RichText) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 1182402406

    }


    /**
     * A preformatted text paragraph
     *
     * @text - Paragraph text
     * @language - Programming language for which the text should be formatted
     */
    class PageBlockPreformatted : PageBlock {

        var text: RichText by WeakField()
        var language: String by WeakField()

        constructor()

        constructor(text: RichText, language: String) {

            this.text = text
            this.language = language

        }

        override val constructor: Int @BsonIgnore get() = -1066346178

    }


    /**
     * The footer of a page
     *
     * @footer - Footer
     */
    class PageBlockFooter : PageBlock {

        var footer: RichText by WeakField()

        constructor()

        constructor(footer: RichText) {

            this.footer = footer

        }

        override val constructor: Int @BsonIgnore get() = 886429480

    }


    /**
     * An empty block separating a page
     */
    class PageBlockDivider : PageBlock() {

        override val constructor: Int @BsonIgnore get() = -618614392

    }


    /**
     * An invisible anchor on a page, which can be used in a URL to open the page from the specified anchor
     *
     * @name - Name of the anchor
     */
    class PageBlockAnchor : PageBlock {

        var name: String by WeakField()

        constructor()

        constructor(name: String) {

            this.name = name

        }

        override val constructor: Int @BsonIgnore get() = -837994576

    }


    /**
     * A list of data blocks
     *
     * @items - The items of the list
     */
    class PageBlockList : PageBlock {

        var items: Array<PageBlockListItem> by WeakField()

        constructor()

        constructor(items: Array<PageBlockListItem>) {

            this.items = items

        }

        override val constructor: Int @BsonIgnore get() = -1037074852

    }


    /**
     * A block quote
     *
     * @text - Quote text
     * @credit - Quote credit
     */
    class PageBlockBlockQuote : PageBlock {

        var text: RichText by WeakField()
        var credit: RichText by WeakField()

        constructor()

        constructor(text: RichText, credit: RichText) {

            this.text = text
            this.credit = credit

        }

        override val constructor: Int @BsonIgnore get() = 1657834142

    }


    /**
     * A pull quote
     *
     * @text - Quote text
     * @credit - Quote credit
     */
    class PageBlockPullQuote : PageBlock {

        var text: RichText by WeakField()
        var credit: RichText by WeakField()

        constructor()

        constructor(text: RichText, credit: RichText) {

            this.text = text
            this.credit = credit

        }

        override val constructor: Int @BsonIgnore get() = 490242317

    }


    /**
     * An animation
     *
     * @animation - Animation file
     * @caption - Animation caption
     * @needAutoplay - True, if the animation should be played automatically
     */
    class PageBlockAnimation : PageBlock {

        var animation: Animation? = null
        var caption: PageBlockCaption by WeakField()
        var needAutoplay: Boolean by WeakField()

        constructor()

        constructor(animation: Animation? = null, caption: PageBlockCaption, needAutoplay: Boolean) {

            this.animation = animation
            this.caption = caption
            this.needAutoplay = needAutoplay

        }

        override val constructor: Int @BsonIgnore get() = 1355669513

    }


    /**
     * An audio file
     *
     * @audio - Audio file
     * @caption - Audio file caption
     */
    class PageBlockAudio : PageBlock {

        var audio: Audio? = null
        var caption: PageBlockCaption by WeakField()

        constructor()

        constructor(audio: Audio? = null, caption: PageBlockCaption) {

            this.audio = audio
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = -63371245

    }


    /**
     * A photo
     *
     * @photo - Photo file
     * @caption - Photo caption
     * @url - URL that needs to be opened when the photo is clicked
     */
    class PageBlockPhoto : PageBlock {

        var photo: Photo? = null
        var caption: PageBlockCaption by WeakField()
        var url: String by WeakField()

        constructor()

        constructor(photo: Photo? = null, caption: PageBlockCaption, url: String) {

            this.photo = photo
            this.caption = caption
            this.url = url

        }

        override val constructor: Int @BsonIgnore get() = 417601156

    }


    /**
     * A video
     *
     * @video - Video file
     * @caption - Video caption
     * @needAutoplay - True, if the video should be played automatically
     * @isLooped - True, if the video should be looped
     */
    class PageBlockVideo : PageBlock {

        var video: Video? = null
        var caption: PageBlockCaption by WeakField()
        var needAutoplay: Boolean by WeakField()
        var isLooped: Boolean by WeakField()

        constructor()

        constructor(video: Video? = null, caption: PageBlockCaption, needAutoplay: Boolean, isLooped: Boolean) {

            this.video = video
            this.caption = caption
            this.needAutoplay = needAutoplay
            this.isLooped = isLooped

        }

        override val constructor: Int @BsonIgnore get() = 510041394

    }


    /**
     * A voice note
     *
     * @voiceNote - Voice note
     * @caption - Voice note caption
     */
    class PageBlockVoiceNote : PageBlock {

        var voiceNote: VoiceNote? = null
        var caption: PageBlockCaption by WeakField()

        constructor()

        constructor(voiceNote: VoiceNote? = null, caption: PageBlockCaption) {

            this.voiceNote = voiceNote
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 1823310463

    }


    /**
     * A page cover
     *
     * @cover - Cover
     */
    class PageBlockCover : PageBlock {

        var cover: PageBlock by WeakField()

        constructor()

        constructor(cover: PageBlock) {

            this.cover = cover

        }

        override val constructor: Int @BsonIgnore get() = 972174080

    }


    /**
     * An embedded web page
     *
     * @url - Web page URL, if available
     * @html - HTML-markup of the embedded page
     * @posterPhoto - Poster photo, if available
     * @width - Block width
     *          0 if unknown
     * @height - Block height
     *           0 if unknown
     * @caption - Block caption
     * @isFullWidth - True, if the block should be full width
     * @allowScrolling - True, if scrolling should be allowed
     */
    class PageBlockEmbedded : PageBlock {

        var url: String by WeakField()
        var html: String by WeakField()
        var posterPhoto: Photo? = null
        var width: Int by WeakField()
        var height: Int by WeakField()
        var caption: PageBlockCaption by WeakField()
        var isFullWidth: Boolean by WeakField()
        var allowScrolling: Boolean by WeakField()

        constructor()

        constructor(url: String, html: String, posterPhoto: Photo? = null, width: Int, height: Int, caption: PageBlockCaption, isFullWidth: Boolean, allowScrolling: Boolean) {

            this.url = url
            this.html = html
            this.posterPhoto = posterPhoto
            this.width = width
            this.height = height
            this.caption = caption
            this.isFullWidth = isFullWidth
            this.allowScrolling = allowScrolling

        }

        override val constructor: Int @BsonIgnore get() = -1942577763

    }


    /**
     * An embedded post
     *
     * @url - Web page URL
     * @author - Post author
     * @authorPhoto - Post author photo
     * @date - Point in time (Unix timestamp) when the post was created
     *         0 if unknown
     * @pageBlocks - Post content
     * @caption - Post caption
     */
    class PageBlockEmbeddedPost : PageBlock {

        var url: String by WeakField()
        var author: String by WeakField()
        var authorPhoto: Photo? = null
        var date: Int by WeakField()
        var pageBlocks: Array<PageBlock> by WeakField()
        var caption: PageBlockCaption by WeakField()

        constructor()

        constructor(url: String, author: String, authorPhoto: Photo? = null, date: Int, pageBlocks: Array<PageBlock>, caption: PageBlockCaption) {

            this.url = url
            this.author = author
            this.authorPhoto = authorPhoto
            this.date = date
            this.pageBlocks = pageBlocks
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 397600949

    }


    /**
     * A collage
     *
     * @pageBlocks - Collage item contents
     * @caption - Block caption
     */
    class PageBlockCollage : PageBlock {

        var pageBlocks: Array<PageBlock> by WeakField()
        var caption: PageBlockCaption by WeakField()

        constructor()

        constructor(pageBlocks: Array<PageBlock>, caption: PageBlockCaption) {

            this.pageBlocks = pageBlocks
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 1163760110

    }


    /**
     * A slideshow
     *
     * @pageBlocks - Slideshow item contents
     * @caption - Block caption
     */
    class PageBlockSlideshow : PageBlock {

        var pageBlocks: Array<PageBlock> by WeakField()
        var caption: PageBlockCaption by WeakField()

        constructor()

        constructor(pageBlocks: Array<PageBlock>, caption: PageBlockCaption) {

            this.pageBlocks = pageBlocks
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 539217375

    }


    /**
     * A link to a chat
     *
     * @title - Chat title
     * @photo - Chat photo
     * @username - Chat username, by which all other information about the chat should be resolved
     */
    class PageBlockChatLink : PageBlock {

        var title: String by WeakField()
        var photo: ChatPhoto? = null
        var username: String by WeakField()

        constructor()

        constructor(title: String, photo: ChatPhoto? = null, username: String) {

            this.title = title
            this.photo = photo
            this.username = username

        }

        override val constructor: Int @BsonIgnore get() = 214606645

    }


    /**
     * A table
     *
     * @caption - Table caption
     * @cells - Table cells
     * @isBordered - True, if the table is bordered
     * @isStriped - True, if the table is striped
     */
    class PageBlockTable : PageBlock {

        var caption: RichText by WeakField()
        var cells: Array<Array<PageBlockTableCell>> by WeakField()
        var isBordered: Boolean by WeakField()
        var isStriped: Boolean by WeakField()

        constructor()

        constructor(caption: RichText, cells: Array<Array<PageBlockTableCell>>, isBordered: Boolean, isStriped: Boolean) {

            this.caption = caption
            this.cells = cells
            this.isBordered = isBordered
            this.isStriped = isStriped

        }

        override val constructor: Int @BsonIgnore get() = -942649288

    }


    /**
     * A collapsible block
     *
     * @header - Always visible heading for the block
     * @pageBlocks - Block contents
     * @isOpen - True, if the block is open by default
     */
    class PageBlockDetails : PageBlock {

        var header: RichText by WeakField()
        var pageBlocks: Array<PageBlock> by WeakField()
        var isOpen: Boolean by WeakField()

        constructor()

        constructor(header: RichText, pageBlocks: Array<PageBlock>, isOpen: Boolean) {

            this.header = header
            this.pageBlocks = pageBlocks
            this.isOpen = isOpen

        }

        override val constructor: Int @BsonIgnore get() = -1599869809

    }


    /**
     * Related articles
     *
     * @header - Block header
     * @articles - List of related articles
     */
    class PageBlockRelatedArticles : PageBlock {

        var header: RichText by WeakField()
        var articles: Array<PageBlockRelatedArticle> by WeakField()

        constructor()

        constructor(header: RichText, articles: Array<PageBlockRelatedArticle>) {

            this.header = header
            this.articles = articles

        }

        override val constructor: Int @BsonIgnore get() = -1807324374

    }


    /**
     * A map
     *
     * @location - Location of the map center
     * @zoom - Map zoom level
     * @width - Map width
     * @height - Map height
     * @caption - Block caption
     */
    class PageBlockMap : PageBlock {

        var location: Location by WeakField()
        var zoom: Int by WeakField()
        var width: Int by WeakField()
        var height: Int by WeakField()
        var caption: PageBlockCaption by WeakField()

        constructor()

        constructor(location: Location, zoom: Int, width: Int, height: Int, caption: PageBlockCaption) {

            this.location = location
            this.zoom = zoom
            this.width = width
            this.height = height
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 1510961171

    }


    /**
     * Describes an instant view page for a web page
     *
     * @pageBlocks - Content of the web page
     * @version - Version of the instant view, currently can be 1 or 2
     * @url - Instant view URL
     *        May be different from WebPage.url and must be used for the correct anchors handling
     * @isRtl - True, if the instant view must be shown from right to left
     * @isFull - True, if the instant view contains the full page
     *           A network request might be needed to get the full web page instant view
     */
    class WebPageInstantView : Object {

        var pageBlocks: Array<PageBlock> by WeakField()
        var version: Int by WeakField()
        var url: String by WeakField()
        var isRtl: Boolean by WeakField()
        var isFull: Boolean by WeakField()

        constructor()

        constructor(pageBlocks: Array<PageBlock>, version: Int, url: String, isRtl: Boolean, isFull: Boolean) {

            this.pageBlocks = pageBlocks
            this.version = version
            this.url = url
            this.isRtl = isRtl
            this.isFull = isFull

        }

        override val constructor: Int @BsonIgnore get() = 957287214

    }


    /**
     * Describes a web page preview
     *
     * @url - Original URL of the link
     * @displayUrl - URL to display
     * @type - Type of the web page
     *         Can be: article, photo, audio, video, document, profile, app, or something else
     * @siteName - Short name of the site (e.g., Google Docs, App Store)
     * @title - Title of the content
     * @description - Description of the content
     * @photo - Image representing the content
     * @embedUrl - URL to show in the embedded preview
     * @embedType - MIME type of the embedded preview, (e.g., text/html or video/mp4)
     * @embedWidth - Width of the embedded preview
     * @embedHeight - Height of the embedded preview
     * @duration - Duration of the content, in seconds
     * @author - Author of the content
     * @animation - Preview of the content as an animation, if available
     * @audio - Preview of the content as an audio file, if available
     * @document - Preview of the content as a document, if available (currently only available for small PDF files and ZIP archives)
     * @sticker - Preview of the content as a sticker for small WEBP files, if available
     * @video - Preview of the content as a video, if available
     * @videoNote - Preview of the content as a video note, if available
     * @voiceNote - Preview of the content as a voice note, if available
     * @instantViewVersion - Version of instant view, available for the web page (currently can be 1 or 2), 0 if none
     */
    class WebPage : Object {

        var url: String by WeakField()
        var displayUrl: String by WeakField()
        var type: String by WeakField()
        var siteName: String by WeakField()
        var title: String by WeakField()
        var description: String by WeakField()
        var photo: Photo? = null
        var embedUrl: String by WeakField()
        var embedType: String by WeakField()
        var embedWidth: Int by WeakField()
        var embedHeight: Int by WeakField()
        var duration: Int by WeakField()
        var author: String by WeakField()
        var animation: Animation? = null
        var audio: Audio? = null
        var document: Document? = null
        var sticker: Sticker? = null
        var video: Video? = null
        var videoNote: VideoNote? = null
        var voiceNote: VoiceNote? = null
        var instantViewVersion: Int by WeakField()

        constructor()

        constructor(url: String, displayUrl: String, type: String, siteName: String, title: String, description: String, photo: Photo? = null, embedUrl: String, embedType: String, embedWidth: Int, embedHeight: Int, duration: Int, author: String, animation: Animation? = null, audio: Audio? = null, document: Document? = null, sticker: Sticker? = null, video: Video? = null, videoNote: VideoNote? = null, voiceNote: VoiceNote? = null, instantViewVersion: Int) {

            this.url = url
            this.displayUrl = displayUrl
            this.type = type
            this.siteName = siteName
            this.title = title
            this.description = description
            this.photo = photo
            this.embedUrl = embedUrl
            this.embedType = embedType
            this.embedWidth = embedWidth
            this.embedHeight = embedHeight
            this.duration = duration
            this.author = author
            this.animation = animation
            this.audio = audio
            this.document = document
            this.sticker = sticker
            this.video = video
            this.videoNote = videoNote
            this.voiceNote = voiceNote
            this.instantViewVersion = instantViewVersion

        }

        override val constructor: Int @BsonIgnore get() = 1092898169

    }


    /**
     * Describes an address
     *
     * @countryCode - A two-letter ISO 3166-1 alpha-2 country code
     * @state - State, if applicable
     * @city - City
     * @streetLine1 - First line of the address
     * @streetLine2 - Second line of the address
     * @postalCode - Address postal code
     */
    class Address : Object {

        var countryCode: String by WeakField()
        var state: String by WeakField()
        var city: String by WeakField()
        var streetLine1: String by WeakField()
        var streetLine2: String by WeakField()
        var postalCode: String by WeakField()

        constructor()

        constructor(countryCode: String, state: String, city: String, streetLine1: String, streetLine2: String, postalCode: String) {

            this.countryCode = countryCode
            this.state = state
            this.city = city
            this.streetLine1 = streetLine1
            this.streetLine2 = streetLine2
            this.postalCode = postalCode

        }

        override val constructor: Int @BsonIgnore get() = -2043654342

    }


    /**
     * Portion of the price of a product (e.g., "delivery cost", "tax amount")
     *
     * @label - Label for this portion of the product price
     * @amount - Currency amount in minimal quantity of the currency
     */
    class LabeledPricePart : Object {

        var label: String by WeakField()
        var amount: Long by WeakField()

        constructor()

        constructor(label: String, amount: Long) {

            this.label = label
            this.amount = amount

        }

        override val constructor: Int @BsonIgnore get() = 552789798

    }


    /**
     * Product invoice
     *
     * @currency - ISO 4217 currency code
     * @priceParts - A list of objects used to calculate the total price of the product
     * @isTest - True, if the payment is a test payment
     * @needName - True, if the user's name is needed for payment
     * @needPhoneNumber - True, if the user's phone number is needed for payment
     * @needEmailAddress - True, if the user's email address is needed for payment
     * @needShippingAddress - True, if the user's shipping address is needed for payment
     * @sendPhoneNumberToProvider - True, if the user's phone number will be sent to the provider
     * @sendEmailAddressToProvider - True, if the user's email address will be sent to the provider
     * @isFlexible - True, if the total price depends on the shipping method
     */
    class Invoice : Object {

        var currency: String by WeakField()
        var priceParts: Array<LabeledPricePart> by WeakField()
        var isTest: Boolean by WeakField()
        var needName: Boolean by WeakField()
        var needPhoneNumber: Boolean by WeakField()
        var needEmailAddress: Boolean by WeakField()
        var needShippingAddress: Boolean by WeakField()
        var sendPhoneNumberToProvider: Boolean by WeakField()
        var sendEmailAddressToProvider: Boolean by WeakField()
        var isFlexible: Boolean by WeakField()

        constructor()

        constructor(currency: String, priceParts: Array<LabeledPricePart>, isTest: Boolean, needName: Boolean, needPhoneNumber: Boolean, needEmailAddress: Boolean, needShippingAddress: Boolean, sendPhoneNumberToProvider: Boolean, sendEmailAddressToProvider: Boolean, isFlexible: Boolean) {

            this.currency = currency
            this.priceParts = priceParts
            this.isTest = isTest
            this.needName = needName
            this.needPhoneNumber = needPhoneNumber
            this.needEmailAddress = needEmailAddress
            this.needShippingAddress = needShippingAddress
            this.sendPhoneNumberToProvider = sendPhoneNumberToProvider
            this.sendEmailAddressToProvider = sendEmailAddressToProvider
            this.isFlexible = isFlexible

        }

        override val constructor: Int @BsonIgnore get() = -368451690

    }


    /**
     * Order information
     *
     * @name - Name of the user
     * @phoneNumber - Phone number of the user
     * @emailAddress - Email address of the user
     * @shippingAddress - Shipping address for this order
     */
    class OrderInfo : Object {

        var name: String by WeakField()
        var phoneNumber: String by WeakField()
        var emailAddress: String by WeakField()
        var shippingAddress: Address? = null

        constructor()

        constructor(name: String, phoneNumber: String, emailAddress: String, shippingAddress: Address? = null) {

            this.name = name
            this.phoneNumber = phoneNumber
            this.emailAddress = emailAddress
            this.shippingAddress = shippingAddress

        }

        override val constructor: Int @BsonIgnore get() = 783997294

    }


    /**
     * One shipping option
     *
     * @id - Shipping option identifier
     * @title - Option title
     * @priceParts - A list of objects used to calculate the total shipping costs
     */
    class ShippingOption : Object {

        var id: String by WeakField()
        var title: String by WeakField()
        var priceParts: Array<LabeledPricePart> by WeakField()

        constructor()

        constructor(id: String, title: String, priceParts: Array<LabeledPricePart>) {

            this.id = id
            this.title = title
            this.priceParts = priceParts

        }

        override val constructor: Int @BsonIgnore get() = 1425690001

    }


    /**
     * Contains information about saved card credentials
     *
     * @id - Unique identifier of the saved credentials
     * @title - Title of the saved credentials
     */
    class SavedCredentials : Object {

        var id: String by WeakField()
        var title: String by WeakField()

        constructor()

        constructor(id: String, title: String) {

            this.id = id
            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = -370273060

    }


    /**
     * Contains information about the payment method chosen by the user
     */
    abstract class InputCredentials : Object()

    /**
     * Applies if a user chooses some previously saved payment credentials
     * To use their previously saved credentials, the user must have a valid temporary password
     *
     * @savedCredentialsId - Identifier of the saved credentials
     */
    class InputCredentialsSaved : InputCredentials {

        var savedCredentialsId: String? = null

        constructor()

        constructor(savedCredentialsId: String? = null) {

            this.savedCredentialsId = savedCredentialsId

        }

        override val constructor: Int @BsonIgnore get() = -2034385364

    }


    /**
     * Applies if a user enters new credentials on a payment provider website
     *
     * @data - Contains JSON-encoded data with a credential identifier from the payment provider
     * @allowSave - True, if the credential identifier can be saved on the server side
     */
    class InputCredentialsNew : InputCredentials {

        var data: String? = null
        var allowSave: Boolean? = null

        constructor()

        constructor(data: String? = null, allowSave: Boolean? = null) {

            this.data = data
            this.allowSave = allowSave

        }

        override val constructor: Int @BsonIgnore get() = -829689558

    }


    /**
     * Applies if a user enters new credentials using Android Pay
     *
     * @data - JSON-encoded data with the credential identifier
     */
    class InputCredentialsAndroidPay : InputCredentials {

        var data: String? = null

        constructor()

        constructor(data: String? = null) {

            this.data = data

        }

        override val constructor: Int @BsonIgnore get() = 1979566832

    }


    /**
     * Applies if a user enters new credentials using Apple Pay
     *
     * @data - JSON-encoded data with the credential identifier
     */
    class InputCredentialsApplePay : InputCredentials {

        var data: String? = null

        constructor()

        constructor(data: String? = null) {

            this.data = data

        }

        override val constructor: Int @BsonIgnore get() = -1246570799

    }


    /**
     * Stripe payment provider
     *
     * @publishableKey - Stripe API publishable key
     * @needCountry - True, if the user country must be provided
     * @needPostalCode - True, if the user ZIP/postal code must be provided
     * @needCardholderName - True, if the cardholder name must be provided
     */
    class PaymentsProviderStripe : Object {

        var publishableKey: String by WeakField()
        var needCountry: Boolean by WeakField()
        var needPostalCode: Boolean by WeakField()
        var needCardholderName: Boolean by WeakField()

        constructor()

        constructor(publishableKey: String, needCountry: Boolean, needPostalCode: Boolean, needCardholderName: Boolean) {

            this.publishableKey = publishableKey
            this.needCountry = needCountry
            this.needPostalCode = needPostalCode
            this.needCardholderName = needCardholderName

        }

        override val constructor: Int @BsonIgnore get() = 1090791032

    }


    /**
     * Contains information about an invoice payment form
     *
     * @invoice - Full information of the invoice
     * @url - Payment form URL
     * @paymentsProvider - Contains information about the payment provider, if available, to support it natively without the need for opening the URL
     * @savedOrderInfo - Saved server-side order information
     * @savedCredentials - Contains information about saved card credentials
     * @canSaveCredentials - True, if the user can choose to save credentials
     * @needPassword - True, if the user will be able to save credentials protected by a password they set up
     */
    class PaymentForm : Object {

        var invoice: Invoice by WeakField()
        var url: String by WeakField()
        var paymentsProvider: PaymentsProviderStripe? = null
        var savedOrderInfo: OrderInfo? = null
        var savedCredentials: SavedCredentials? = null
        var canSaveCredentials: Boolean by WeakField()
        var needPassword: Boolean by WeakField()

        constructor()

        constructor(invoice: Invoice, url: String, paymentsProvider: PaymentsProviderStripe? = null, savedOrderInfo: OrderInfo? = null, savedCredentials: SavedCredentials? = null, canSaveCredentials: Boolean, needPassword: Boolean) {

            this.invoice = invoice
            this.url = url
            this.paymentsProvider = paymentsProvider
            this.savedOrderInfo = savedOrderInfo
            this.savedCredentials = savedCredentials
            this.canSaveCredentials = canSaveCredentials
            this.needPassword = needPassword

        }

        override val constructor: Int @BsonIgnore get() = -200418230

    }


    /**
     * Contains a temporary identifier of validated order information, which is stored for one hour
     * Also contains the available shipping options
     *
     * @orderInfoId - Temporary identifier of the order information
     * @shippingOptions - Available shipping options
     */
    class ValidatedOrderInfo : Object {

        var orderInfoId: String by WeakField()
        var shippingOptions: Array<ShippingOption> by WeakField()

        constructor()

        constructor(orderInfoId: String, shippingOptions: Array<ShippingOption>) {

            this.orderInfoId = orderInfoId
            this.shippingOptions = shippingOptions

        }

        override val constructor: Int @BsonIgnore get() = 1511451484

    }


    /**
     * Contains the result of a payment request
     *
     * @success - True, if the payment request was successful
     *            Otherwise the verification_url will be not empty
     * @verificationUrl - URL for additional payment credentials verification
     */
    class PaymentResult : Object {

        var success: Boolean by WeakField()
        var verificationUrl: String by WeakField()

        constructor()

        constructor(success: Boolean, verificationUrl: String) {

            this.success = success
            this.verificationUrl = verificationUrl

        }

        override val constructor: Int @BsonIgnore get() = -804263843

    }


    /**
     * Contains information about a successful payment
     *
     * @date - Point in time (Unix timestamp) when the payment was made
     * @paymentsProviderUserId - User identifier of the payment provider bot
     * @invoice - Contains information about the invoice
     * @orderInfo - Contains order information
     * @shippingOption - Chosen shipping option
     * @credentialsTitle - Title of the saved credentials
     */
    class PaymentReceipt : Object {

        var date: Int by WeakField()
        var paymentsProviderUserId: Int by WeakField()
        var invoice: Invoice by WeakField()
        var orderInfo: OrderInfo? = null
        var shippingOption: ShippingOption? = null
        var credentialsTitle: String by WeakField()

        constructor()

        constructor(date: Int, paymentsProviderUserId: Int, invoice: Invoice, orderInfo: OrderInfo? = null, shippingOption: ShippingOption? = null, credentialsTitle: String) {

            this.date = date
            this.paymentsProviderUserId = paymentsProviderUserId
            this.invoice = invoice
            this.orderInfo = orderInfo
            this.shippingOption = shippingOption
            this.credentialsTitle = credentialsTitle

        }

        override val constructor: Int @BsonIgnore get() = -1171223545

    }


    /**
     * File with the date it was uploaded
     *
     * @file - The file
     * @date - Point in time (Unix timestamp) when the file was uploaded
     */
    class DatedFile : Object {

        var file: File by WeakField()
        var date: Int by WeakField()

        constructor()

        constructor(file: File, date: Int) {

            this.file = file
            this.date = date

        }

        override val constructor: Int @BsonIgnore get() = -1840795491

    }


    /**
     * Contains the type of a Telegram Passport element
     */
    abstract class PassportElementType : Object()

    /**
     * A Telegram Passport element containing the user's personal details
     */
    class PassportElementTypePersonalDetails : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = -1032136365

    }


    /**
     * A Telegram Passport element containing the user's passport
     */
    class PassportElementTypePassport : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = -436360376

    }


    /**
     * A Telegram Passport element containing the user's driver license
     */
    class PassportElementTypeDriverLicense : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = 1827298379

    }


    /**
     * A Telegram Passport element containing the user's identity card
     */
    class PassportElementTypeIdentityCard : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = -502356132

    }


    /**
     * A Telegram Passport element containing the user's internal passport
     */
    class PassportElementTypeInternalPassport : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = -793781959

    }


    /**
     * A Telegram Passport element containing the user's address
     */
    class PassportElementTypeAddress : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = 496327874

    }


    /**
     * A Telegram Passport element containing the user's utility bill
     */
    class PassportElementTypeUtilityBill : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = 627084906

    }


    /**
     * A Telegram Passport element containing the user's bank statement
     */
    class PassportElementTypeBankStatement : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = 574095667

    }


    /**
     * A Telegram Passport element containing the user's rental agreement
     */
    class PassportElementTypeRentalAgreement : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = -2060583280

    }


    /**
     * A Telegram Passport element containing the registration page of the user's passport
     */
    class PassportElementTypePassportRegistration : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = -159478209

    }


    /**
     * A Telegram Passport element containing the user's temporary registration
     */
    class PassportElementTypeTemporaryRegistration : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = 1092498527

    }


    /**
     * A Telegram Passport element containing the user's phone number
     */
    class PassportElementTypePhoneNumber : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = -995361172

    }


    /**
     * A Telegram Passport element containing the user's email address
     */
    class PassportElementTypeEmailAddress : PassportElementType() {

        override val constructor: Int @BsonIgnore get() = -79321405

    }


    /**
     * Represents a date according to the Gregorian calendar
     *
     * @day - Day of the month, 1-31
     * @month - Month, 1-12
     * @year - Year, 1-9999
     */
    class Date : Object {

        var day: Int by WeakField()
        var month: Int by WeakField()
        var year: Int by WeakField()

        constructor()

        constructor(day: Int, month: Int, year: Int) {

            this.day = day
            this.month = month
            this.year = year

        }

        override val constructor: Int @BsonIgnore get() = -277956960

    }


    /**
     * Contains the user's personal details
     *
     * @firstName - First name of the user written in English
     * @middleName - Middle name of the user written in English
     * @lastName - Last name of the user written in English
     * @nativeFirstName - Native first name of the user
     * @nativeMiddleName - Native middle name of the user
     * @nativeLastName - Native last name of the user
     * @birthdate - Birthdate of the user
     * @gender - Gender of the user, "male" or "female"
     * @countryCode - A two-letter ISO 3166-1 alpha-2 country code of the user's country
     * @residenceCountryCode - A two-letter ISO 3166-1 alpha-2 country code of the user's residence country
     */
    class PersonalDetails : Object {

        var firstName: String by WeakField()
        var middleName: String by WeakField()
        var lastName: String by WeakField()
        var nativeFirstName: String by WeakField()
        var nativeMiddleName: String by WeakField()
        var nativeLastName: String by WeakField()
        var birthdate: Date by WeakField()
        var gender: String by WeakField()
        var countryCode: String by WeakField()
        var residenceCountryCode: String by WeakField()

        constructor()

        constructor(firstName: String, middleName: String, lastName: String, nativeFirstName: String, nativeMiddleName: String, nativeLastName: String, birthdate: Date, gender: String, countryCode: String, residenceCountryCode: String) {

            this.firstName = firstName
            this.middleName = middleName
            this.lastName = lastName
            this.nativeFirstName = nativeFirstName
            this.nativeMiddleName = nativeMiddleName
            this.nativeLastName = nativeLastName
            this.birthdate = birthdate
            this.gender = gender
            this.countryCode = countryCode
            this.residenceCountryCode = residenceCountryCode

        }

        override val constructor: Int @BsonIgnore get() = -1061656137

    }


    /**
     * An identity document
     *
     * @number - Document number
     * @expiryDate - Document expiry date
     * @frontSide - Front side of the document
     * @reverseSide - Reverse side of the document
     *                Only for driver license and identity card
     * @selfie - Selfie with the document
     * @translation - List of files containing a certified English translation of the document
     */
    class IdentityDocument : Object {

        var number: String by WeakField()
        var expiryDate: Date? = null
        var frontSide: DatedFile by WeakField()
        var reverseSide: DatedFile by WeakField()
        var selfie: DatedFile? = null
        var translation: Array<DatedFile> by WeakField()

        constructor()

        constructor(number: String, expiryDate: Date? = null, frontSide: DatedFile, reverseSide: DatedFile, selfie: DatedFile? = null, translation: Array<DatedFile>) {

            this.number = number
            this.expiryDate = expiryDate
            this.frontSide = frontSide
            this.reverseSide = reverseSide
            this.selfie = selfie
            this.translation = translation

        }

        override val constructor: Int @BsonIgnore get() = 445952972

    }


    /**
     * An identity document to be saved to Telegram Passport
     *
     * @number - Document number
     * @expiryDate - Document expiry date, if available
     * @frontSide - Front side of the document
     * @reverseSide - Reverse side of the document
     *                Only for driver license and identity card
     * @selfie - Selfie with the document, if available
     * @translation - List of files containing a certified English translation of the document
     */
    class InputIdentityDocument : Object {

        var number: String? = null
        var expiryDate: Date? = null
        var frontSide: InputFile? = null
        var reverseSide: InputFile? = null
        var selfie: InputFile? = null
        var translation: Array<InputFile>? = null

        constructor()

        constructor(number: String? = null, expiryDate: Date? = null, frontSide: InputFile? = null, reverseSide: InputFile? = null, selfie: InputFile? = null, translation: Array<InputFile>? = null) {

            this.number = number
            this.expiryDate = expiryDate
            this.frontSide = frontSide
            this.reverseSide = reverseSide
            this.selfie = selfie
            this.translation = translation

        }

        override val constructor: Int @BsonIgnore get() = -381776063

    }


    /**
     * A personal document, containing some information about a user
     *
     * @files - List of files containing the pages of the document
     * @translation - List of files containing a certified English translation of the document
     */
    class PersonalDocument : Object {

        var files: Array<DatedFile> by WeakField()
        var translation: Array<DatedFile> by WeakField()

        constructor()

        constructor(files: Array<DatedFile>, translation: Array<DatedFile>) {

            this.files = files
            this.translation = translation

        }

        override val constructor: Int @BsonIgnore get() = -1011634661

    }


    /**
     * A personal document to be saved to Telegram Passport
     *
     * @files - List of files containing the pages of the document
     * @translation - List of files containing a certified English translation of the document
     */
    class InputPersonalDocument : Object {

        var files: Array<InputFile>? = null
        var translation: Array<InputFile>? = null

        constructor()

        constructor(files: Array<InputFile>? = null, translation: Array<InputFile>? = null) {

            this.files = files
            this.translation = translation

        }

        override val constructor: Int @BsonIgnore get() = 1676966826

    }


    /**
     * Contains information about a Telegram Passport element
     */
    abstract class PassportElement : Object()

    /**
     * A Telegram Passport element containing the user's personal details
     *
     * @personalDetails - Personal details of the user
     */
    class PassportElementPersonalDetails : PassportElement {

        var personalDetails: PersonalDetails by WeakField()

        constructor()

        constructor(personalDetails: PersonalDetails) {

            this.personalDetails = personalDetails

        }

        override val constructor: Int @BsonIgnore get() = 1217724035

    }


    /**
     * A Telegram Passport element containing the user's passport
     *
     * @passport - Passport
     */
    class PassportElementPassport : PassportElement {

        var passport: IdentityDocument by WeakField()

        constructor()

        constructor(passport: IdentityDocument) {

            this.passport = passport

        }

        override val constructor: Int @BsonIgnore get() = -263985373

    }


    /**
     * A Telegram Passport element containing the user's driver license
     *
     * @driverLicense - Driver license
     */
    class PassportElementDriverLicense : PassportElement {

        var driverLicense: IdentityDocument by WeakField()

        constructor()

        constructor(driverLicense: IdentityDocument) {

            this.driverLicense = driverLicense

        }

        override val constructor: Int @BsonIgnore get() = 1643580589

    }


    /**
     * A Telegram Passport element containing the user's identity card
     *
     * @identityCard - Identity card
     */
    class PassportElementIdentityCard : PassportElement {

        var identityCard: IdentityDocument by WeakField()

        constructor()

        constructor(identityCard: IdentityDocument) {

            this.identityCard = identityCard

        }

        override val constructor: Int @BsonIgnore get() = 2083775797

    }


    /**
     * A Telegram Passport element containing the user's internal passport
     *
     * @internalPassport - Internal passport
     */
    class PassportElementInternalPassport : PassportElement {

        var internalPassport: IdentityDocument by WeakField()

        constructor()

        constructor(internalPassport: IdentityDocument) {

            this.internalPassport = internalPassport

        }

        override val constructor: Int @BsonIgnore get() = 36220295

    }


    /**
     * A Telegram Passport element containing the user's address
     *
     * @address - Address
     */
    class PassportElementAddress : PassportElement {

        var address: Address by WeakField()

        constructor()

        constructor(address: Address) {

            this.address = address

        }

        override val constructor: Int @BsonIgnore get() = -782625232

    }


    /**
     * A Telegram Passport element containing the user's utility bill
     *
     * @utilityBill - Utility bill
     */
    class PassportElementUtilityBill : PassportElement {

        var utilityBill: PersonalDocument by WeakField()

        constructor()

        constructor(utilityBill: PersonalDocument) {

            this.utilityBill = utilityBill

        }

        override val constructor: Int @BsonIgnore get() = -234611246

    }


    /**
     * A Telegram Passport element containing the user's bank statement
     *
     * @bankStatement - Bank statement
     */
    class PassportElementBankStatement : PassportElement {

        var bankStatement: PersonalDocument by WeakField()

        constructor()

        constructor(bankStatement: PersonalDocument) {

            this.bankStatement = bankStatement

        }

        override val constructor: Int @BsonIgnore get() = -366464408

    }


    /**
     * A Telegram Passport element containing the user's rental agreement
     *
     * @rentalAgreement - Rental agreement
     */
    class PassportElementRentalAgreement : PassportElement {

        var rentalAgreement: PersonalDocument by WeakField()

        constructor()

        constructor(rentalAgreement: PersonalDocument) {

            this.rentalAgreement = rentalAgreement

        }

        override val constructor: Int @BsonIgnore get() = -290141400

    }


    /**
     * A Telegram Passport element containing the user's passport registration pages
     *
     * @passportRegistration - Passport registration pages
     */
    class PassportElementPassportRegistration : PassportElement {

        var passportRegistration: PersonalDocument by WeakField()

        constructor()

        constructor(passportRegistration: PersonalDocument) {

            this.passportRegistration = passportRegistration

        }

        override val constructor: Int @BsonIgnore get() = 618323071

    }


    /**
     * A Telegram Passport element containing the user's temporary registration
     *
     * @temporaryRegistration - Temporary registration
     */
    class PassportElementTemporaryRegistration : PassportElement {

        var temporaryRegistration: PersonalDocument by WeakField()

        constructor()

        constructor(temporaryRegistration: PersonalDocument) {

            this.temporaryRegistration = temporaryRegistration

        }

        override val constructor: Int @BsonIgnore get() = 1237626864

    }


    /**
     * A Telegram Passport element containing the user's phone number
     *
     * @phoneNumber - Phone number
     */
    class PassportElementPhoneNumber : PassportElement {

        var phoneNumber: String by WeakField()

        constructor()

        constructor(phoneNumber: String) {

            this.phoneNumber = phoneNumber

        }

        override val constructor: Int @BsonIgnore get() = -1320118375

    }


    /**
     * A Telegram Passport element containing the user's email address
     *
     * @emailAddress - Email address
     */
    class PassportElementEmailAddress : PassportElement {

        var emailAddress: String by WeakField()

        constructor()

        constructor(emailAddress: String) {

            this.emailAddress = emailAddress

        }

        override val constructor: Int @BsonIgnore get() = -1528129531

    }


    /**
     * Contains information about a Telegram Passport element to be saved
     */
    abstract class InputPassportElement : Object()

    /**
     * A Telegram Passport element to be saved containing the user's personal details
     *
     * @personalDetails - Personal details of the user
     */
    class InputPassportElementPersonalDetails : InputPassportElement {

        var personalDetails: PersonalDetails? = null

        constructor()

        constructor(personalDetails: PersonalDetails? = null) {

            this.personalDetails = personalDetails

        }

        override val constructor: Int @BsonIgnore get() = 164791359

    }


    /**
     * A Telegram Passport element to be saved containing the user's passport
     *
     * @passport - The passport to be saved
     */
    class InputPassportElementPassport : InputPassportElement {

        var passport: InputIdentityDocument? = null

        constructor()

        constructor(passport: InputIdentityDocument? = null) {

            this.passport = passport

        }

        override val constructor: Int @BsonIgnore get() = -497011356

    }


    /**
     * A Telegram Passport element to be saved containing the user's driver license
     *
     * @driverLicense - The driver license to be saved
     */
    class InputPassportElementDriverLicense : InputPassportElement {

        var driverLicense: InputIdentityDocument? = null

        constructor()

        constructor(driverLicense: InputIdentityDocument? = null) {

            this.driverLicense = driverLicense

        }

        override val constructor: Int @BsonIgnore get() = 304813264

    }


    /**
     * A Telegram Passport element to be saved containing the user's identity card
     *
     * @identityCard - The identity card to be saved
     */
    class InputPassportElementIdentityCard : InputPassportElement {

        var identityCard: InputIdentityDocument? = null

        constructor()

        constructor(identityCard: InputIdentityDocument? = null) {

            this.identityCard = identityCard

        }

        override val constructor: Int @BsonIgnore get() = -9963390

    }


    /**
     * A Telegram Passport element to be saved containing the user's internal passport
     *
     * @internalPassport - The internal passport to be saved
     */
    class InputPassportElementInternalPassport : InputPassportElement {

        var internalPassport: InputIdentityDocument? = null

        constructor()

        constructor(internalPassport: InputIdentityDocument? = null) {

            this.internalPassport = internalPassport

        }

        override val constructor: Int @BsonIgnore get() = 715360043

    }


    /**
     * A Telegram Passport element to be saved containing the user's address
     *
     * @address - The address to be saved
     */
    class InputPassportElementAddress : InputPassportElement {

        var address: Address? = null

        constructor()

        constructor(address: Address? = null) {

            this.address = address

        }

        override val constructor: Int @BsonIgnore get() = 461630480

    }


    /**
     * A Telegram Passport element to be saved containing the user's utility bill
     *
     * @utilityBill - The utility bill to be saved
     */
    class InputPassportElementUtilityBill : InputPassportElement {

        var utilityBill: InputPersonalDocument? = null

        constructor()

        constructor(utilityBill: InputPersonalDocument? = null) {

            this.utilityBill = utilityBill

        }

        override val constructor: Int @BsonIgnore get() = 1389203841

    }


    /**
     * A Telegram Passport element to be saved containing the user's bank statement
     *
     * @bankStatement - The bank statement to be saved
     */
    class InputPassportElementBankStatement : InputPassportElement {

        var bankStatement: InputPersonalDocument? = null

        constructor()

        constructor(bankStatement: InputPersonalDocument? = null) {

            this.bankStatement = bankStatement

        }

        override val constructor: Int @BsonIgnore get() = -26585208

    }


    /**
     * A Telegram Passport element to be saved containing the user's rental agreement
     *
     * @rentalAgreement - The rental agreement to be saved
     */
    class InputPassportElementRentalAgreement : InputPassportElement {

        var rentalAgreement: InputPersonalDocument? = null

        constructor()

        constructor(rentalAgreement: InputPersonalDocument? = null) {

            this.rentalAgreement = rentalAgreement

        }

        override val constructor: Int @BsonIgnore get() = 1736154155

    }


    /**
     * A Telegram Passport element to be saved containing the user's passport registration
     *
     * @passportRegistration - The passport registration page to be saved
     */
    class InputPassportElementPassportRegistration : InputPassportElement {

        var passportRegistration: InputPersonalDocument? = null

        constructor()

        constructor(passportRegistration: InputPersonalDocument? = null) {

            this.passportRegistration = passportRegistration

        }

        override val constructor: Int @BsonIgnore get() = 1314562128

    }


    /**
     * A Telegram Passport element to be saved containing the user's temporary registration
     *
     * @temporaryRegistration - The temporary registration document to be saved
     */
    class InputPassportElementTemporaryRegistration : InputPassportElement {

        var temporaryRegistration: InputPersonalDocument? = null

        constructor()

        constructor(temporaryRegistration: InputPersonalDocument? = null) {

            this.temporaryRegistration = temporaryRegistration

        }

        override val constructor: Int @BsonIgnore get() = -1913238047

    }


    /**
     * A Telegram Passport element to be saved containing the user's phone number
     *
     * @phoneNumber - The phone number to be saved
     */
    class InputPassportElementPhoneNumber : InputPassportElement {

        var phoneNumber: String? = null

        constructor()

        constructor(phoneNumber: String? = null) {

            this.phoneNumber = phoneNumber

        }

        override val constructor: Int @BsonIgnore get() = 1319357497

    }


    /**
     * A Telegram Passport element to be saved containing the user's email address
     *
     * @emailAddress - The email address to be saved
     */
    class InputPassportElementEmailAddress : InputPassportElement {

        var emailAddress: String? = null

        constructor()

        constructor(emailAddress: String? = null) {

            this.emailAddress = emailAddress

        }

        override val constructor: Int @BsonIgnore get() = -248605659

    }


    /**
     * Contains information about saved Telegram Passport elements
     *
     * @elements - Telegram Passport elements
     */
    class PassportElements : Object {

        var elements: Array<PassportElement> by WeakField()

        constructor()

        constructor(elements: Array<PassportElement>) {

            this.elements = elements

        }

        override val constructor: Int @BsonIgnore get() = 1264617556

    }


    /**
     * Contains the description of an error in a Telegram Passport element
     */
    abstract class PassportElementErrorSource : Object()

    /**
     * The element contains an error in an unspecified place
     * The error will be considered resolved when new data is added
     */
    class PassportElementErrorSourceUnspecified : PassportElementErrorSource() {

        override val constructor: Int @BsonIgnore get() = -378320830

    }


    /**
     * One of the data fields contains an error
     * The error will be considered resolved when the value of the field changes
     *
     * @fieldName - Field name
     */
    class PassportElementErrorSourceDataField : PassportElementErrorSource {

        var fieldName: String by WeakField()

        constructor()

        constructor(fieldName: String) {

            this.fieldName = fieldName

        }

        override val constructor: Int @BsonIgnore get() = -308650776

    }


    /**
     * The front side of the document contains an error
     * The error will be considered resolved when the file with the front side changes
     */
    class PassportElementErrorSourceFrontSide : PassportElementErrorSource() {

        override val constructor: Int @BsonIgnore get() = 1895658292

    }


    /**
     * The reverse side of the document contains an error
     * The error will be considered resolved when the file with the reverse side changes
     */
    class PassportElementErrorSourceReverseSide : PassportElementErrorSource() {

        override val constructor: Int @BsonIgnore get() = 1918630391

    }


    /**
     * The selfie with the document contains an error
     * The error will be considered resolved when the file with the selfie changes
     */
    class PassportElementErrorSourceSelfie : PassportElementErrorSource() {

        override val constructor: Int @BsonIgnore get() = -797043672

    }


    /**
     * One of files with the translation of the document contains an error
     * The error will be considered resolved when the file changes
     *
     * @fileIndex - Index of a file with the error
     */
    class PassportElementErrorSourceTranslationFile : PassportElementErrorSource {

        var fileIndex: Int by WeakField()

        constructor()

        constructor(fileIndex: Int) {

            this.fileIndex = fileIndex

        }

        override val constructor: Int @BsonIgnore get() = -689621228

    }


    /**
     * The translation of the document contains an error
     * The error will be considered resolved when the list of translation files changes
     */
    class PassportElementErrorSourceTranslationFiles : PassportElementErrorSource() {

        override val constructor: Int @BsonIgnore get() = 581280796

    }


    /**
     * The file contains an error
     * The error will be considered resolved when the file changes
     *
     * @fileIndex - Index of a file with the error
     */
    class PassportElementErrorSourceFile : PassportElementErrorSource {

        var fileIndex: Int by WeakField()

        constructor()

        constructor(fileIndex: Int) {

            this.fileIndex = fileIndex

        }

        override val constructor: Int @BsonIgnore get() = 2020358960

    }


    /**
     * The list of attached files contains an error
     * The error will be considered resolved when the list of files changes
     */
    class PassportElementErrorSourceFiles : PassportElementErrorSource() {

        override val constructor: Int @BsonIgnore get() = 1894164178

    }


    /**
     * Contains the description of an error in a Telegram Passport element
     *
     * @type - Type of the Telegram Passport element which has the error
     * @message - Error message
     * @source - Error source
     */
    class PassportElementError : Object {

        var type: PassportElementType by WeakField()
        var message: String by WeakField()
        var source: PassportElementErrorSource by WeakField()

        constructor()

        constructor(type: PassportElementType, message: String, source: PassportElementErrorSource) {

            this.type = type
            this.message = message
            this.source = source

        }

        override val constructor: Int @BsonIgnore get() = -1861902395

    }


    /**
     * Contains information about a Telegram Passport element that was requested by a service
     *
     * @type - Type of the element
     * @isSelfieRequired - True, if a selfie is required with the identity document
     * @isTranslationRequired - True, if a certified English translation is required with the document
     * @isNativeNameRequired - True, if personal details must include the user's name in the language of their country of residence
     */
    class PassportSuitableElement : Object {

        var type: PassportElementType by WeakField()
        var isSelfieRequired: Boolean by WeakField()
        var isTranslationRequired: Boolean by WeakField()
        var isNativeNameRequired: Boolean by WeakField()

        constructor()

        constructor(type: PassportElementType, isSelfieRequired: Boolean, isTranslationRequired: Boolean, isNativeNameRequired: Boolean) {

            this.type = type
            this.isSelfieRequired = isSelfieRequired
            this.isTranslationRequired = isTranslationRequired
            this.isNativeNameRequired = isNativeNameRequired

        }

        override val constructor: Int @BsonIgnore get() = -789019876

    }


    /**
     * Contains a description of the required Telegram Passport element that was requested by a service
     *
     * @suitableElements - List of Telegram Passport elements any of which is enough to provide
     */
    class PassportRequiredElement : Object {

        var suitableElements: Array<PassportSuitableElement> by WeakField()

        constructor()

        constructor(suitableElements: Array<PassportSuitableElement>) {

            this.suitableElements = suitableElements

        }

        override val constructor: Int @BsonIgnore get() = -1983641651

    }


    /**
     * Contains information about a Telegram Passport authorization form that was requested
     *
     * @id - Unique identifier of the authorization form
     * @requiredElements - Information about the Telegram Passport elements that need to be provided to complete the form
     * @privacyPolicyUrl - URL for the privacy policy of the service
     */
    class PassportAuthorizationForm : Object {

        var id: Int by WeakField()
        var requiredElements: Array<PassportRequiredElement> by WeakField()
        var privacyPolicyUrl: String? = null

        constructor()

        constructor(id: Int, requiredElements: Array<PassportRequiredElement>, privacyPolicyUrl: String? = null) {

            this.id = id
            this.requiredElements = requiredElements
            this.privacyPolicyUrl = privacyPolicyUrl

        }

        override val constructor: Int @BsonIgnore get() = -1070673218

    }


    /**
     * Contains information about a Telegram Passport elements and corresponding errors
     *
     * @elements - Telegram Passport elements
     * @errors - Errors in the elements that are already available
     */
    class PassportElementsWithErrors : Object {

        var elements: Array<PassportElement> by WeakField()
        var errors: Array<PassportElementError> by WeakField()

        constructor()

        constructor(elements: Array<PassportElement>, errors: Array<PassportElementError>) {

            this.elements = elements
            this.errors = errors

        }

        override val constructor: Int @BsonIgnore get() = 1308923044

    }


    /**
     * Contains encrypted Telegram Passport data credentials
     *
     * @data - The encrypted credentials
     * @hash - The decrypted data hash
     * @secret - Secret for data decryption, encrypted with the service's public key
     */
    class EncryptedCredentials : Object {

        var data: ByteArray by WeakField()
        var hash: ByteArray by WeakField()
        var secret: ByteArray by WeakField()

        constructor()

        constructor(data: ByteArray, hash: ByteArray, secret: ByteArray) {

            this.data = data
            this.hash = hash
            this.secret = secret

        }

        override val constructor: Int @BsonIgnore get() = 1331106766

    }


    /**
     * Contains information about an encrypted Telegram Passport element
     * For bots only
     *
     * @type - Type of Telegram Passport element
     * @data - Encrypted JSON-encoded data about the user
     * @frontSide - The front side of an identity document
     * @reverseSide - The reverse side of an identity document
     * @selfie - Selfie with the document
     * @translation - List of files containing a certified English translation of the document
     * @files - List of attached files
     * @value - Unencrypted data, phone number or email address
     * @hash - Hash of the entire element
     */
    class EncryptedPassportElement : Object {

        var type: PassportElementType by WeakField()
        var data: ByteArray by WeakField()
        var frontSide: DatedFile by WeakField()
        var reverseSide: DatedFile? = null
        var selfie: DatedFile? = null
        var translation: Array<DatedFile> by WeakField()
        var files: Array<DatedFile> by WeakField()
        var value: String by WeakField()
        var hash: String by WeakField()

        constructor()

        constructor(type: PassportElementType, data: ByteArray, frontSide: DatedFile, reverseSide: DatedFile? = null, selfie: DatedFile? = null, translation: Array<DatedFile>, files: Array<DatedFile>, value: String, hash: String) {

            this.type = type
            this.data = data
            this.frontSide = frontSide
            this.reverseSide = reverseSide
            this.selfie = selfie
            this.translation = translation
            this.files = files
            this.value = value
            this.hash = hash

        }

        override val constructor: Int @BsonIgnore get() = 2002386193

    }


    /**
     * Contains the description of an error in a Telegram Passport element
     * For bots only
     */
    abstract class InputPassportElementErrorSource : Object()

    /**
     * The element contains an error in an unspecified place
     * The error will be considered resolved when new data is added
     *
     * @elementHash - Current hash of the entire element
     */
    class InputPassportElementErrorSourceUnspecified : InputPassportElementErrorSource {

        var elementHash: ByteArray? = null

        constructor()

        constructor(elementHash: ByteArray? = null) {

            this.elementHash = elementHash

        }

        override val constructor: Int @BsonIgnore get() = 267230319

    }


    /**
     * A data field contains an error
     * The error is considered resolved when the field's value changes
     *
     * @fieldName - Field name
     * @dataHash - Current data hash
     */
    class InputPassportElementErrorSourceDataField : InputPassportElementErrorSource {

        var fieldName: String? = null
        var dataHash: ByteArray? = null

        constructor()

        constructor(fieldName: String? = null, dataHash: ByteArray? = null) {

            this.fieldName = fieldName
            this.dataHash = dataHash

        }

        override val constructor: Int @BsonIgnore get() = -426795002

    }


    /**
     * The front side of the document contains an error
     * The error is considered resolved when the file with the front side of the document changes
     *
     * @fileHash - Current hash of the file containing the front side
     */
    class InputPassportElementErrorSourceFrontSide : InputPassportElementErrorSource {

        var fileHash: ByteArray? = null

        constructor()

        constructor(fileHash: ByteArray? = null) {

            this.fileHash = fileHash

        }

        override val constructor: Int @BsonIgnore get() = 588023741

    }


    /**
     * The reverse side of the document contains an error
     * The error is considered resolved when the file with the reverse side of the document changes
     *
     * @fileHash - Current hash of the file containing the reverse side
     */
    class InputPassportElementErrorSourceReverseSide : InputPassportElementErrorSource {

        var fileHash: ByteArray? = null

        constructor()

        constructor(fileHash: ByteArray? = null) {

            this.fileHash = fileHash

        }

        override val constructor: Int @BsonIgnore get() = 413072891

    }


    /**
     * The selfie contains an error
     * The error is considered resolved when the file with the selfie changes
     *
     * @fileHash - Current hash of the file containing the selfie
     */
    class InputPassportElementErrorSourceSelfie : InputPassportElementErrorSource {

        var fileHash: ByteArray? = null

        constructor()

        constructor(fileHash: ByteArray? = null) {

            this.fileHash = fileHash

        }

        override val constructor: Int @BsonIgnore get() = -773575528

    }


    /**
     * One of the files containing the translation of the document contains an error
     * The error is considered resolved when the file with the translation changes
     *
     * @fileHash - Current hash of the file containing the translation
     */
    class InputPassportElementErrorSourceTranslationFile : InputPassportElementErrorSource {

        var fileHash: ByteArray? = null

        constructor()

        constructor(fileHash: ByteArray? = null) {

            this.fileHash = fileHash

        }

        override val constructor: Int @BsonIgnore get() = 505842299

    }


    /**
     * The translation of the document contains an error
     * The error is considered resolved when the list of files changes
     *
     * @fileHashes - Current hashes of all files with the translation
     */
    class InputPassportElementErrorSourceTranslationFiles : InputPassportElementErrorSource {

        var fileHashes: Array<ByteArray>? = null

        constructor()

        constructor(fileHashes: Array<ByteArray>? = null) {

            this.fileHashes = fileHashes

        }

        override val constructor: Int @BsonIgnore get() = -527254048

    }


    /**
     * The file contains an error
     * The error is considered resolved when the file changes
     *
     * @fileHash - Current hash of the file which has the error
     */
    class InputPassportElementErrorSourceFile : InputPassportElementErrorSource {

        var fileHash: ByteArray? = null

        constructor()

        constructor(fileHash: ByteArray? = null) {

            this.fileHash = fileHash

        }

        override val constructor: Int @BsonIgnore get() = -298492469

    }


    /**
     * The list of attached files contains an error
     * The error is considered resolved when the file list changes
     *
     * @fileHashes - Current hashes of all attached files
     */
    class InputPassportElementErrorSourceFiles : InputPassportElementErrorSource {

        var fileHashes: Array<ByteArray>? = null

        constructor()

        constructor(fileHashes: Array<ByteArray>? = null) {

            this.fileHashes = fileHashes

        }

        override val constructor: Int @BsonIgnore get() = -2008541640

    }


    /**
     * Contains the description of an error in a Telegram Passport element
     * For bots only
     *
     * @type - Type of Telegram Passport element that has the error
     * @message - Error message
     * @source - Error source
     */
    class InputPassportElementError : Object {

        var type: PassportElementType? = null
        var message: String? = null
        var source: InputPassportElementErrorSource? = null

        constructor()

        constructor(type: PassportElementType? = null, message: String? = null, source: InputPassportElementErrorSource? = null) {

            this.type = type
            this.message = message
            this.source = source

        }

        override val constructor: Int @BsonIgnore get() = 285756898

    }


    /**
     * Contains the content of a message
     */
    abstract class MessageContent : Object()

    /**
     * A text message
     *
     * @text - Text of the message
     * @webPage - A preview of the web page that's mentioned in the text
     */
    class MessageText : MessageContent {

        var text: FormattedText by WeakField()
        var webPage: WebPage? = null

        constructor()

        constructor(text: FormattedText, webPage: WebPage? = null) {

            this.text = text
            this.webPage = webPage

        }

        override val constructor: Int @BsonIgnore get() = 1989037971

    }


    /**
     * An animation message (GIF-style).
     *
     * @animation - Message content
     * @caption - Animation caption
     * @isSecret - True, if the animation thumbnail must be blurred and the animation must be shown only while tapped
     */
    class MessageAnimation : MessageContent {

        var animation: Animation by WeakField()
        var caption: FormattedText by WeakField()
        var isSecret: Boolean by WeakField()

        constructor()

        constructor(animation: Animation, caption: FormattedText, isSecret: Boolean) {

            this.animation = animation
            this.caption = caption
            this.isSecret = isSecret

        }

        override val constructor: Int @BsonIgnore get() = 1306939396

    }


    /**
     * An audio message
     *
     * @audio - Message content
     * @caption - Audio caption
     */
    class MessageAudio : MessageContent {

        var audio: Audio by WeakField()
        var caption: FormattedText by WeakField()

        constructor()

        constructor(audio: Audio, caption: FormattedText) {

            this.audio = audio
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 276722716

    }


    /**
     * A document message (general file)
     *
     * @document - Message content
     * @caption - Document caption
     */
    class MessageDocument : MessageContent {

        var document: Document by WeakField()
        var caption: FormattedText by WeakField()

        constructor()

        constructor(document: Document, caption: FormattedText) {

            this.document = document
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 596945783

    }


    /**
     * A photo message
     *
     * @photo - Message content
     * @caption - Photo caption
     * @isSecret - True, if the photo must be blurred and must be shown only while tapped
     */
    class MessagePhoto : MessageContent {

        var photo: Photo by WeakField()
        var caption: FormattedText by WeakField()
        var isSecret: Boolean by WeakField()

        constructor()

        constructor(photo: Photo, caption: FormattedText, isSecret: Boolean) {

            this.photo = photo
            this.caption = caption
            this.isSecret = isSecret

        }

        override val constructor: Int @BsonIgnore get() = -1851395174

    }


    /**
     * An expired photo message (self-destructed after TTL has elapsed)
     */
    class MessageExpiredPhoto : MessageContent() {

        override val constructor: Int @BsonIgnore get() = -1404641801

    }


    /**
     * A sticker message
     *
     * @sticker - Message content
     */
    class MessageSticker : MessageContent {

        var sticker: Sticker by WeakField()

        constructor()

        constructor(sticker: Sticker) {

            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = 1779022878

    }


    /**
     * A video message
     *
     * @video - Message content
     * @caption - Video caption
     * @isSecret - True, if the video thumbnail must be blurred and the video must be shown only while tapped
     */
    class MessageVideo : MessageContent {

        var video: Video by WeakField()
        var caption: FormattedText by WeakField()
        var isSecret: Boolean by WeakField()

        constructor()

        constructor(video: Video, caption: FormattedText, isSecret: Boolean) {

            this.video = video
            this.caption = caption
            this.isSecret = isSecret

        }

        override val constructor: Int @BsonIgnore get() = 2021281344

    }


    /**
     * An expired video message (self-destructed after TTL has elapsed)
     */
    class MessageExpiredVideo : MessageContent() {

        override val constructor: Int @BsonIgnore get() = -1212209981

    }


    /**
     * A video note message
     *
     * @videoNote - Message content
     * @isViewed - True, if at least one of the recipients has viewed the video note
     * @isSecret - True, if the video note thumbnail must be blurred and the video note must be shown only while tapped
     */
    class MessageVideoNote : MessageContent {

        var videoNote: VideoNote by WeakField()
        var isViewed: Boolean by WeakField()
        var isSecret: Boolean by WeakField()

        constructor()

        constructor(videoNote: VideoNote, isViewed: Boolean, isSecret: Boolean) {

            this.videoNote = videoNote
            this.isViewed = isViewed
            this.isSecret = isSecret

        }

        override val constructor: Int @BsonIgnore get() = 963323014

    }


    /**
     * A voice note message
     *
     * @voiceNote - Message content
     * @caption - Voice note caption
     * @isListened - True, if at least one of the recipients has listened to the voice note
     */
    class MessageVoiceNote : MessageContent {

        var voiceNote: VoiceNote by WeakField()
        var caption: FormattedText by WeakField()
        var isListened: Boolean by WeakField()

        constructor()

        constructor(voiceNote: VoiceNote, caption: FormattedText, isListened: Boolean) {

            this.voiceNote = voiceNote
            this.caption = caption
            this.isListened = isListened

        }

        override val constructor: Int @BsonIgnore get() = 527777781

    }


    /**
     * A message with a location
     *
     * @location - Message content
     * @livePeriod - Time relative to the message sent date until which the location can be updated, in seconds
     * @expiresIn - Left time for which the location can be updated, in seconds
     *              UpdateMessageContent is not sent when this field changes
     */
    class MessageLocation : MessageContent {

        var location: Location by WeakField()
        var livePeriod: Int by WeakField()
        var expiresIn: Int by WeakField()

        constructor()

        constructor(location: Location, livePeriod: Int, expiresIn: Int) {

            this.location = location
            this.livePeriod = livePeriod
            this.expiresIn = expiresIn

        }

        override val constructor: Int @BsonIgnore get() = -1301887786

    }


    /**
     * A message with information about a venue
     *
     * @venue - Message content
     */
    class MessageVenue : MessageContent {

        var venue: Venue by WeakField()

        constructor()

        constructor(venue: Venue) {

            this.venue = venue

        }

        override val constructor: Int @BsonIgnore get() = -2146492043

    }


    /**
     * A message with a user contact
     *
     * @contact - Message content
     */
    class MessageContact : MessageContent {

        var contact: Contact by WeakField()

        constructor()

        constructor(contact: Contact) {

            this.contact = contact

        }

        override val constructor: Int @BsonIgnore get() = -512684966

    }


    /**
     * A message with a game
     *
     * @game - Game
     */
    class MessageGame : MessageContent {

        var game: Game by WeakField()

        constructor()

        constructor(game: Game) {

            this.game = game

        }

        override val constructor: Int @BsonIgnore get() = -69441162

    }


    /**
     * A message with a poll
     *
     * @poll - Poll
     */
    class MessagePoll : MessageContent {

        var poll: Poll by WeakField()

        constructor()

        constructor(poll: Poll) {

            this.poll = poll

        }

        override val constructor: Int @BsonIgnore get() = -662130099

    }


    /**
     * A message with an invoice from a bot
     *
     * @title - Product title
     * @description - Product description
     * @photo - Product photo
     * @currency - Currency for the product price
     * @totalAmount - Product total price in the minimal quantity of the currency
     * @startParameter - Unique invoice bot start_parameter
     *                   To share an invoice use the URL https://t.me/{bot_username}?start={start_parameter}
     * @isTest - True, if the invoice is a test invoice
     * @needShippingAddress - True, if the shipping address should be specified
     * @receiptMessageId - The identifier of the message with the receipt, after the product has been purchased
     */
    class MessageInvoice : MessageContent {

        var title: String by WeakField()
        var description: String by WeakField()
        var photo: Photo? = null
        var currency: String by WeakField()
        var totalAmount: Long by WeakField()
        var startParameter: String by WeakField()
        var isTest: Boolean by WeakField()
        var needShippingAddress: Boolean by WeakField()
        var receiptMessageId: Long by WeakField()

        constructor()

        constructor(title: String, description: String, photo: Photo? = null, currency: String, totalAmount: Long, startParameter: String, isTest: Boolean, needShippingAddress: Boolean, receiptMessageId: Long) {

            this.title = title
            this.description = description
            this.photo = photo
            this.currency = currency
            this.totalAmount = totalAmount
            this.startParameter = startParameter
            this.isTest = isTest
            this.needShippingAddress = needShippingAddress
            this.receiptMessageId = receiptMessageId

        }

        override val constructor: Int @BsonIgnore get() = -1916671476

    }


    /**
     * A message with information about an ended call
     *
     * @discardReason - Reason why the call was discarded
     * @duration - Call duration, in seconds
     */
    class MessageCall : MessageContent {

        var discardReason: CallDiscardReason by WeakField()
        var duration: Int by WeakField()

        constructor()

        constructor(discardReason: CallDiscardReason, duration: Int) {

            this.discardReason = discardReason
            this.duration = duration

        }

        override val constructor: Int @BsonIgnore get() = 366512596

    }


    /**
     * A newly created basic group
     *
     * @title - Title of the basic group
     * @memberUserIds - User identifiers of members in the basic group
     */
    class MessageBasicGroupChatCreate : MessageContent {

        var title: String by WeakField()
        var memberUserIds: IntArray by WeakField()

        constructor()

        constructor(title: String, memberUserIds: IntArray) {

            this.title = title
            this.memberUserIds = memberUserIds

        }

        override val constructor: Int @BsonIgnore get() = 1575377646

    }


    /**
     * A newly created supergroup or channel
     *
     * @title - Title of the supergroup or channel
     */
    class MessageSupergroupChatCreate : MessageContent {

        var title: String by WeakField()

        constructor()

        constructor(title: String) {

            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = -434325733

    }


    /**
     * An updated chat title
     *
     * @title - New chat title
     */
    class MessageChatChangeTitle : MessageContent {

        var title: String by WeakField()

        constructor()

        constructor(title: String) {

            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = 748272449

    }


    /**
     * An updated chat photo
     *
     * @photo - New chat photo
     */
    class MessageChatChangePhoto : MessageContent {

        var photo: Photo by WeakField()

        constructor()

        constructor(photo: Photo) {

            this.photo = photo

        }

        override val constructor: Int @BsonIgnore get() = 319630249

    }


    /**
     * A deleted chat photo
     */
    class MessageChatDeletePhoto : MessageContent() {

        override val constructor: Int @BsonIgnore get() = -184374809

    }


    /**
     * New chat members were added
     *
     * @memberUserIds - User identifiers of the new members
     */
    class MessageChatAddMembers : MessageContent {

        var memberUserIds: IntArray by WeakField()

        constructor()

        constructor(memberUserIds: IntArray) {

            this.memberUserIds = memberUserIds

        }

        override val constructor: Int @BsonIgnore get() = 401228326

    }


    /**
     * A new member joined the chat by invite link
     */
    class MessageChatJoinByLink : MessageContent() {

        override val constructor: Int @BsonIgnore get() = 1846493311

    }


    /**
     * A chat member was deleted
     *
     * @userId - User identifier of the deleted chat member
     */
    class MessageChatDeleteMember : MessageContent {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = 1164414043

    }


    /**
     * A basic group was upgraded to a supergroup and was deactivated as the result
     *
     * @supergroupId - Identifier of the supergroup to which the basic group was upgraded
     */
    class MessageChatUpgradeTo : MessageContent {

        var supergroupId: Int by WeakField()

        constructor()

        constructor(supergroupId: Int) {

            this.supergroupId = supergroupId

        }

        override val constructor: Int @BsonIgnore get() = 1957816681

    }


    /**
     * A supergroup has been created from a basic group
     *
     * @title - Title of the newly created supergroup
     * @basicGroupId - The identifier of the original basic group
     */
    class MessageChatUpgradeFrom : MessageContent {

        var title: String by WeakField()
        var basicGroupId: Int by WeakField()

        constructor()

        constructor(title: String, basicGroupId: Int) {

            this.title = title
            this.basicGroupId = basicGroupId

        }

        override val constructor: Int @BsonIgnore get() = 1642272558

    }


    /**
     * A message has been pinned
     *
     * @messageId - Identifier of the pinned message, can be an identifier of a deleted message or 0
     */
    class MessagePinMessage : MessageContent {

        var messageId: Long by WeakField()

        constructor()

        constructor(messageId: Long) {

            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = 953503801

    }


    /**
     * A screenshot of a message in the chat has been taken
     */
    class MessageScreenshotTaken : MessageContent() {

        override val constructor: Int @BsonIgnore get() = -1564971605

    }


    /**
     * The TTL (Time To Live) setting messages in a secret chat has been changed
     *
     * @ttl - New TTL
     */
    class MessageChatSetTtl : MessageContent {

        var ttl: Int by WeakField()

        constructor()

        constructor(ttl: Int) {

            this.ttl = ttl

        }

        override val constructor: Int @BsonIgnore get() = 1810060209

    }


    /**
     * A non-standard action has happened in the chat
     *
     * @text - Message text to be shown in the chat
     */
    class MessageCustomServiceAction : MessageContent {

        var text: String by WeakField()

        constructor()

        constructor(text: String) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 1435879282

    }


    /**
     * A new high score was achieved in a game
     *
     * @gameMessageId - Identifier of the message with the game, can be an identifier of a deleted message
     * @gameId - Identifier of the game
     *           May be different from the games presented in the message with the game
     * @score - New score
     */
    class MessageGameScore : MessageContent {

        var gameMessageId: Long by WeakField()
        var gameId: Long by WeakField()
        var score: Int by WeakField()

        constructor()

        constructor(gameMessageId: Long, gameId: Long, score: Int) {

            this.gameMessageId = gameMessageId
            this.gameId = gameId
            this.score = score

        }

        override val constructor: Int @BsonIgnore get() = 1344904575

    }


    /**
     * A payment has been completed
     *
     * @invoiceMessageId - Identifier of the message with the corresponding invoice
     *                     Can be an identifier of a deleted message
     * @currency - Currency for the price of the product
     * @totalAmount - Total price for the product, in the minimal quantity of the currency
     */
    class MessagePaymentSuccessful : MessageContent {

        var invoiceMessageId: Long by WeakField()
        var currency: String by WeakField()
        var totalAmount: Long by WeakField()

        constructor()

        constructor(invoiceMessageId: Long, currency: String, totalAmount: Long) {

            this.invoiceMessageId = invoiceMessageId
            this.currency = currency
            this.totalAmount = totalAmount

        }

        override val constructor: Int @BsonIgnore get() = -595962993

    }


    /**
     * A payment has been completed
     * For bots only
     *
     * @invoiceMessageId - Identifier of the message with the corresponding invoice
     *                     Can be an identifier of a deleted message
     * @currency - Currency for price of the product
     * @totalAmount - Total price for the product, in the minimal quantity of the currency
     * @invoicePayload - Invoice payload
     * @shippingOptionId - Identifier of the shipping option chosen by the user
     *                     May be empty if not applicable
     * @orderInfo - Information about the order
     * @telegramPaymentChargeId - Telegram payment identifier
     * @providerPaymentChargeId - Provider payment identifier
     */
    class MessagePaymentSuccessfulBot : MessageContent {

        var invoiceMessageId: Long by WeakField()
        var currency: String by WeakField()
        var totalAmount: Long by WeakField()
        var invoicePayload: ByteArray by WeakField()
        var shippingOptionId: String? = null
        var orderInfo: OrderInfo? = null
        var telegramPaymentChargeId: String by WeakField()
        var providerPaymentChargeId: String by WeakField()

        constructor()

        constructor(invoiceMessageId: Long, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String? = null, orderInfo: OrderInfo? = null, telegramPaymentChargeId: String, providerPaymentChargeId: String) {

            this.invoiceMessageId = invoiceMessageId
            this.currency = currency
            this.totalAmount = totalAmount
            this.invoicePayload = invoicePayload
            this.shippingOptionId = shippingOptionId
            this.orderInfo = orderInfo
            this.telegramPaymentChargeId = telegramPaymentChargeId
            this.providerPaymentChargeId = providerPaymentChargeId

        }

        override val constructor: Int @BsonIgnore get() = -412310696

    }


    /**
     * A contact has registered with Telegram
     */
    class MessageContactRegistered : MessageContent() {

        override val constructor: Int @BsonIgnore get() = -1502020353

    }


    /**
     * The current user has connected a website by logging in using Telegram Login Widget on it
     *
     * @domainName - Domain name of the connected website
     */
    class MessageWebsiteConnected : MessageContent {

        var domainName: String by WeakField()

        constructor()

        constructor(domainName: String) {

            this.domainName = domainName

        }

        override val constructor: Int @BsonIgnore get() = -1074551800

    }


    /**
     * Telegram Passport data has been sent
     *
     * @types - List of Telegram Passport element types sent
     */
    class MessagePassportDataSent : MessageContent {

        var types: Array<PassportElementType> by WeakField()

        constructor()

        constructor(types: Array<PassportElementType>) {

            this.types = types

        }

        override val constructor: Int @BsonIgnore get() = 1017405171

    }


    /**
     * Telegram Passport data has been received
     * For bots only
     *
     * @elements - List of received Telegram Passport elements
     * @credentials - Encrypted data credentials
     */
    class MessagePassportDataReceived : MessageContent {

        var elements: Array<EncryptedPassportElement> by WeakField()
        var credentials: EncryptedCredentials by WeakField()

        constructor()

        constructor(elements: Array<EncryptedPassportElement>, credentials: EncryptedCredentials) {

            this.elements = elements
            this.credentials = credentials

        }

        override val constructor: Int @BsonIgnore get() = -1367863624

    }


    /**
     * Message content that is not supported by the client
     */
    class MessageUnsupported : MessageContent() {

        override val constructor: Int @BsonIgnore get() = -1816726139

    }


    /**
     * Represents a part of the text which must be formatted differently
     */
    abstract class TextEntityType : Object()

    /**
     * A mention of a user by their username
     */
    class TextEntityTypeMention : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = 934535013

    }


    /**
     * A hashtag text, beginning with "#"
     */
    class TextEntityTypeHashtag : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = -1023958307

    }


    /**
     * A cashtag text, beginning with "$" and consisting of capital english letters (i.e
     * "$USD")
     */
    class TextEntityTypeCashtag : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = 1222915915

    }


    /**
     * A bot command, beginning with "/"
     * This shouldn't be highlighted if there are no bots in the chat
     */
    class TextEntityTypeBotCommand : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = -1150997581

    }


    /**
     * An HTTP URL
     */
    class TextEntityTypeUrl : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = -1312762756

    }


    /**
     * An email address
     */
    class TextEntityTypeEmailAddress : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = 1425545249

    }


    /**
     * A phone number
     */
    class TextEntityTypePhoneNumber : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = -1160140246

    }


    /**
     * A bold text
     */
    class TextEntityTypeBold : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = -1128210000

    }


    /**
     * An italic text
     */
    class TextEntityTypeItalic : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = -118253987

    }


    /**
     * An underlined text
     */
    class TextEntityTypeUnderline : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = 792317842

    }


    /**
     * A strikethrough text
     */
    class TextEntityTypeStrikethrough : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = 961529082

    }


    /**
     * Text that must be formatted as if inside a code HTML tag
     */
    class TextEntityTypeCode : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = -974534326

    }


    /**
     * Text that must be formatted as if inside a pre HTML tag
     */
    class TextEntityTypePre : TextEntityType() {

        override val constructor: Int @BsonIgnore get() = 1648958606

    }


    /**
     * Text that must be formatted as if inside pre, and code HTML tags
     *
     * @language - Programming language of the code
     *             As defined by the sender
     */
    class TextEntityTypePreCode : TextEntityType {

        var language: String by WeakField()

        constructor()

        constructor(language: String) {

            this.language = language

        }

        override val constructor: Int @BsonIgnore get() = -945325397

    }


    /**
     * A text description shown instead of a raw URL
     *
     * @url - HTTP or tg:// URL to be opened when the link is clicked
     */
    class TextEntityTypeTextUrl : TextEntityType {

        var url: String by WeakField()

        constructor()

        constructor(url: String) {

            this.url = url

        }

        override val constructor: Int @BsonIgnore get() = 445719651

    }


    /**
     * A text shows instead of a raw mention of the user (e.g., when the user has no username)
     *
     * @userId - Identifier of the mentioned user
     */
    class TextEntityTypeMentionName : TextEntityType {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -791517091

    }


    /**
     * A thumbnail to be sent along with a file
     * Should be in JPEG or WEBP format for stickers, and less than 200 kB in size
     *
     * @thumbnail - Thumbnail file to send
     *              Sending thumbnails by file_id is currently not supported
     * @width - Thumbnail width, usually shouldn't exceed 320
     *          Use 0 if unknown
     * @height - Thumbnail height, usually shouldn't exceed 320
     *           Use 0 if unknown
     */
    class InputThumbnail : Object {

        var thumbnail: InputFile? = null
        var width: Int? = null
        var height: Int? = null

        constructor()

        constructor(thumbnail: InputFile? = null, width: Int? = null, height: Int? = null) {

            this.thumbnail = thumbnail
            this.width = width
            this.height = height

        }

        override val constructor: Int @BsonIgnore get() = 1582387236

    }


    /**
     * Contains information about the time when a scheduled message will be sent
     */
    abstract class MessageSchedulingState : Object()

    /**
     * The message will be sent at the specified date
     *
     * @sendDate - Date the message will be sent
     *             The date must be within 367 days in the future
     */
    class MessageSchedulingStateSendAtDate : MessageSchedulingState {

        var sendDate: Int by WeakField()

        constructor()

        constructor(sendDate: Int) {

            this.sendDate = sendDate

        }

        override val constructor: Int @BsonIgnore get() = -1485570073

    }


    /**
     * The message will be sent when the peer will be online
     * Applicable to private chats only and when the exact online status of the peer is known
     */
    class MessageSchedulingStateSendWhenOnline : MessageSchedulingState() {

        override val constructor: Int @BsonIgnore get() = 2092947464

    }


    /**
     * Options to be used when a message is send
     *
     * @disableNotification - Pass true to disable notification for the message
     *                        Must be false if the message is sent to a secret chat
     * @fromBackground - Pass true if the message is sent from the background
     * @schedulingState - Message scheduling state
     *                    Messages sent to a secret chat, live location messages and self-destructing messages can't be scheduled
     */
    class SendMessageOptions : Object {

        var disableNotification: Boolean? = null
        var fromBackground: Boolean? = null
        var schedulingState: MessageSchedulingState? = null

        constructor()

        constructor(disableNotification: Boolean? = null, fromBackground: Boolean? = null, schedulingState: MessageSchedulingState? = null) {

            this.disableNotification = disableNotification
            this.fromBackground = fromBackground
            this.schedulingState = schedulingState

        }

        override val constructor: Int @BsonIgnore get() = 669760254

    }


    /**
     * The content of a message to send
     */
    abstract class InputMessageContent : Object()

    /**
     * A text message
     *
     * @text - Formatted text to be sent
     *         1-GetOption("message_text_length_max") characters
     *         Only Bold, Italic, Underline, Strikethrough, Code, Pre, PreCode, TextUrl and MentionName entities are allowed to be specified manually
     * @disableWebPagePreview - True, if rich web page previews for URLs in the message text should be disabled
     * @clearDraft - True, if a chat message draft should be deleted
     */
    class InputMessageText : InputMessageContent {

        var text: FormattedText? = null
        var disableWebPagePreview: Boolean? = null
        var clearDraft: Boolean? = null

        constructor()

        constructor(text: FormattedText? = null, disableWebPagePreview: Boolean? = null, clearDraft: Boolean? = null) {

            this.text = text
            this.disableWebPagePreview = disableWebPagePreview
            this.clearDraft = clearDraft

        }

        override val constructor: Int @BsonIgnore get() = 247050392

    }


    /**
     * An animation message (GIF-style).
     *
     * @animation - Animation file to be sent
     * @thumbnail - Animation thumbnail, if available
     * @duration - Duration of the animation, in seconds
     * @width - Width of the animation
     *          May be replaced by the server
     * @height - Height of the animation
     *           May be replaced by the server
     * @caption - Animation caption
     *            0-GetOption("message_caption_length_max") characters
     */
    class InputMessageAnimation : InputMessageContent {

        var animation: InputFile? = null
        var thumbnail: InputThumbnail? = null
        var duration: Int? = null
        var width: Int? = null
        var height: Int? = null
        var caption: FormattedText? = null

        constructor()

        constructor(animation: InputFile? = null, thumbnail: InputThumbnail? = null, duration: Int? = null, width: Int? = null, height: Int? = null, caption: FormattedText? = null) {

            this.animation = animation
            this.thumbnail = thumbnail
            this.duration = duration
            this.width = width
            this.height = height
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 926542724

    }


    /**
     * An audio message
     *
     * @audio - Audio file to be sent
     * @albumCoverThumbnail - Thumbnail of the cover for the album, if available
     * @duration - Duration of the audio, in seconds
     *             May be replaced by the server
     * @title - Title of the audio
     *          May be replaced by the server
     * @performer - Performer of the audio
     * @caption - Audio caption
     *            0-GetOption("message_caption_length_max") characters
     */
    class InputMessageAudio : InputMessageContent {

        var audio: InputFile? = null
        var albumCoverThumbnail: InputThumbnail? = null
        var duration: Int? = null
        var title: String? = null
        var performer: String? = null
        var caption: FormattedText? = null

        constructor()

        constructor(audio: InputFile? = null, albumCoverThumbnail: InputThumbnail? = null, duration: Int? = null, title: String? = null, performer: String? = null, caption: FormattedText? = null) {

            this.audio = audio
            this.albumCoverThumbnail = albumCoverThumbnail
            this.duration = duration
            this.title = title
            this.performer = performer
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = -626786126

    }


    /**
     * A document message (general file)
     *
     * @document - Document to be sent
     * @thumbnail - Document thumbnail, if available
     * @caption - Document caption
     *            0-GetOption("message_caption_length_max") characters
     */
    class InputMessageDocument : InputMessageContent {

        var document: InputFile? = null
        var thumbnail: InputThumbnail? = null
        var caption: FormattedText? = null

        constructor()

        constructor(document: InputFile? = null, thumbnail: InputThumbnail? = null, caption: FormattedText? = null) {

            this.document = document
            this.thumbnail = thumbnail
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 937970604

    }


    /**
     * A photo message
     *
     * @photo - Photo to send
     * @thumbnail - Photo thumbnail to be sent, this is sent to the other party in secret chats only
     * @addedStickerFileIds - File identifiers of the stickers added to the photo, if applicable
     * @width - Photo width
     * @height - Photo height
     * @caption - Photo caption
     *            0-GetOption("message_caption_length_max") characters
     * @ttl - Photo TTL (Time To Live), in seconds (0-60)
     *        A non-zero TTL can be specified only in private chats
     */
    class InputMessagePhoto : InputMessageContent {

        var photo: InputFile? = null
        var thumbnail: InputThumbnail? = null
        var addedStickerFileIds: IntArray? = null
        var width: Int? = null
        var height: Int? = null
        var caption: FormattedText? = null
        var ttl: Int? = null

        constructor()

        constructor(photo: InputFile? = null, thumbnail: InputThumbnail? = null, addedStickerFileIds: IntArray? = null, width: Int? = null, height: Int? = null, caption: FormattedText? = null, ttl: Int? = null) {

            this.photo = photo
            this.thumbnail = thumbnail
            this.addedStickerFileIds = addedStickerFileIds
            this.width = width
            this.height = height
            this.caption = caption
            this.ttl = ttl

        }

        override val constructor: Int @BsonIgnore get() = 1648801584

    }


    /**
     * A sticker message
     *
     * @sticker - Sticker to be sent
     * @thumbnail - Sticker thumbnail, if available
     * @width - Sticker width
     * @height - Sticker height
     */
    class InputMessageSticker : InputMessageContent {

        var sticker: InputFile? = null
        var thumbnail: InputThumbnail? = null
        var width: Int? = null
        var height: Int? = null

        constructor()

        constructor(sticker: InputFile? = null, thumbnail: InputThumbnail? = null, width: Int? = null, height: Int? = null) {

            this.sticker = sticker
            this.thumbnail = thumbnail
            this.width = width
            this.height = height

        }

        override val constructor: Int @BsonIgnore get() = 740776325

    }


    /**
     * A video message
     *
     * @video - Video to be sent
     * @thumbnail - Video thumbnail, if available
     * @addedStickerFileIds - File identifiers of the stickers added to the video, if applicable
     * @duration - Duration of the video, in seconds
     * @width - Video width
     * @height - Video height
     * @supportsStreaming - True, if the video should be tried to be streamed
     * @caption - Video caption
     *            0-GetOption("message_caption_length_max") characters
     * @ttl - Video TTL (Time To Live), in seconds (0-60)
     *        A non-zero TTL can be specified only in private chats
     */
    class InputMessageVideo : InputMessageContent {

        var video: InputFile? = null
        var thumbnail: InputThumbnail? = null
        var addedStickerFileIds: IntArray? = null
        var duration: Int? = null
        var width: Int? = null
        var height: Int? = null
        var supportsStreaming: Boolean? = null
        var caption: FormattedText? = null
        var ttl: Int? = null

        constructor()

        constructor(video: InputFile? = null, thumbnail: InputThumbnail? = null, addedStickerFileIds: IntArray? = null, duration: Int? = null, width: Int? = null, height: Int? = null, supportsStreaming: Boolean? = null, caption: FormattedText? = null, ttl: Int? = null) {

            this.video = video
            this.thumbnail = thumbnail
            this.addedStickerFileIds = addedStickerFileIds
            this.duration = duration
            this.width = width
            this.height = height
            this.supportsStreaming = supportsStreaming
            this.caption = caption
            this.ttl = ttl

        }

        override val constructor: Int @BsonIgnore get() = -2108486755

    }


    /**
     * A video note message
     *
     * @videoNote - Video note to be sent
     * @thumbnail - Video thumbnail, if available
     * @duration - Duration of the video, in seconds
     * @length - Video width and height
     *           Must be positive and not greater than 640
     */
    class InputMessageVideoNote : InputMessageContent {

        var videoNote: InputFile? = null
        var thumbnail: InputThumbnail? = null
        var duration: Int? = null
        var length: Int? = null

        constructor()

        constructor(videoNote: InputFile? = null, thumbnail: InputThumbnail? = null, duration: Int? = null, length: Int? = null) {

            this.videoNote = videoNote
            this.thumbnail = thumbnail
            this.duration = duration
            this.length = length

        }

        override val constructor: Int @BsonIgnore get() = 279108859

    }


    /**
     * A voice note message
     *
     * @voiceNote - Voice note to be sent
     * @duration - Duration of the voice note, in seconds
     * @waveform - Waveform representation of the voice note, in 5-bit format
     * @caption - Voice note caption
     *            0-GetOption("message_caption_length_max") characters
     */
    class InputMessageVoiceNote : InputMessageContent {

        var voiceNote: InputFile? = null
        var duration: Int? = null
        var waveform: ByteArray? = null
        var caption: FormattedText? = null

        constructor()

        constructor(voiceNote: InputFile? = null, duration: Int? = null, waveform: ByteArray? = null, caption: FormattedText? = null) {

            this.voiceNote = voiceNote
            this.duration = duration
            this.waveform = waveform
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 2136519657

    }


    /**
     * A message with a location
     *
     * @location - Location to be sent
     * @livePeriod - Period for which the location can be updated, in seconds
     *               Should bebetween 60 and 86400 for a live location and 0 otherwise
     */
    class InputMessageLocation : InputMessageContent {

        var location: Location? = null
        var livePeriod: Int? = null

        constructor()

        constructor(location: Location? = null, livePeriod: Int? = null) {

            this.location = location
            this.livePeriod = livePeriod

        }

        override val constructor: Int @BsonIgnore get() = -1624179655

    }


    /**
     * A message with information about a venue
     *
     * @venue - Venue to send
     */
    class InputMessageVenue : InputMessageContent {

        var venue: Venue? = null

        constructor()

        constructor(venue: Venue? = null) {

            this.venue = venue

        }

        override val constructor: Int @BsonIgnore get() = 1447926269

    }


    /**
     * A message containing a user contact
     *
     * @contact - Contact to send
     */
    class InputMessageContact : InputMessageContent {

        var contact: Contact? = null

        constructor()

        constructor(contact: Contact? = null) {

            this.contact = contact

        }

        override val constructor: Int @BsonIgnore get() = -982446849

    }


    /**
     * A message with a game
     * Not supported for channels or secret chats
     *
     * @botUserId - User identifier of the bot that owns the game
     * @gameShortName - Short name of the game
     */
    class InputMessageGame : InputMessageContent {

        var botUserId: Int? = null
        var gameShortName: String? = null

        constructor()

        constructor(botUserId: Int? = null, gameShortName: String? = null) {

            this.botUserId = botUserId
            this.gameShortName = gameShortName

        }

        override val constructor: Int @BsonIgnore get() = -1728000914

    }


    /**
     * A message with an invoice
     * Can be used only by bots and only in private chats
     *
     * @invoice - Invoice
     * @title - Product title
     * @description - Product description
     * @photoUrl - Product photo URL
     * @photoSize - Product photo size
     * @photoWidth - Product photo width
     * @photoHeight - Product photo height
     * @payload - The invoice payload
     * @providerToken - Payment provider token
     * @providerData - JSON-encoded data about the invoice, which will be shared with the payment provider
     * @startParameter - Unique invoice bot start_parameter for the generation of this invoice
     */
    class InputMessageInvoice : InputMessageContent {

        var invoice: Invoice? = null
        var title: String? = null
        var description: String? = null
        var photoUrl: String? = null
        var photoSize: Int? = null
        var photoWidth: Int? = null
        var photoHeight: Int? = null
        var payload: ByteArray? = null
        var providerToken: String? = null
        var providerData: String? = null
        var startParameter: String? = null

        constructor()

        constructor(invoice: Invoice? = null, title: String? = null, description: String? = null, photoUrl: String? = null, photoSize: Int? = null, photoWidth: Int? = null, photoHeight: Int? = null, payload: ByteArray? = null, providerToken: String? = null, providerData: String? = null, startParameter: String? = null) {

            this.invoice = invoice
            this.title = title
            this.description = description
            this.photoUrl = photoUrl
            this.photoSize = photoSize
            this.photoWidth = photoWidth
            this.photoHeight = photoHeight
            this.payload = payload
            this.providerToken = providerToken
            this.providerData = providerData
            this.startParameter = startParameter

        }

        override val constructor: Int @BsonIgnore get() = 1038812175

    }


    /**
     * A message with a poll
     * Polls can't be sent to private or secret chats
     *
     * @question - Poll question, 1-255 characters
     * @options - List of poll answer options, 2-10 strings 1-100 characters each
     */
    class InputMessagePoll : InputMessageContent {

        var question: String? = null
        var options: Array<String>? = null

        constructor()

        constructor(question: String? = null, options: Array<String>? = null) {

            this.question = question
            this.options = options

        }

        override val constructor: Int @BsonIgnore get() = -1791140518

    }


    /**
     * A forwarded message
     *
     * @fromChatId - Identifier for the chat this forwarded message came from
     * @messageId - Identifier of the message to forward
     * @inGameShare - True, if a game message should be shared within a launched game
     *                Applies only to game messages
     * @sendCopy - True, if content of the message needs to be copied without a link to the original message
     *             Always true if the message is forwarded to a secret chat
     * @removeCaption - True, if media caption of the message copy needs to be removed
     *                  Ignored if send_copy is false
     */
    class InputMessageForwarded : InputMessageContent {

        var fromChatId: Long? = null
        var messageId: Long? = null
        var inGameShare: Boolean? = null
        var sendCopy: Boolean? = null
        var removeCaption: Boolean? = null

        constructor()

        constructor(fromChatId: Long? = null, messageId: Long? = null, inGameShare: Boolean? = null, sendCopy: Boolean? = null, removeCaption: Boolean? = null) {

            this.fromChatId = fromChatId
            this.messageId = messageId
            this.inGameShare = inGameShare
            this.sendCopy = sendCopy
            this.removeCaption = removeCaption

        }

        override val constructor: Int @BsonIgnore get() = 1503132333

    }


    /**
     * Represents a filter for message search results
     */
    abstract class SearchMessagesFilter : Object()

    /**
     * Returns all found messages, no filter is applied
     */
    class SearchMessagesFilterEmpty : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = -869395657

    }


    /**
     * Returns only animation messages
     */
    class SearchMessagesFilterAnimation : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = -155713339

    }


    /**
     * Returns only audio messages
     */
    class SearchMessagesFilterAudio : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 867505275

    }


    /**
     * Returns only document messages
     */
    class SearchMessagesFilterDocument : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 1526331215

    }


    /**
     * Returns only photo messages
     */
    class SearchMessagesFilterPhoto : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 925932293

    }


    /**
     * Returns only video messages
     */
    class SearchMessagesFilterVideo : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 115538222

    }


    /**
     * Returns only voice note messages
     */
    class SearchMessagesFilterVoiceNote : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 1841439357

    }


    /**
     * Returns only photo and video messages
     */
    class SearchMessagesFilterPhotoAndVideo : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 1352130963

    }


    /**
     * Returns only messages containing URLs
     */
    class SearchMessagesFilterUrl : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = -1828724341

    }


    /**
     * Returns only messages containing chat photos
     */
    class SearchMessagesFilterChatPhoto : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = -1247751329

    }


    /**
     * Returns only call messages
     */
    class SearchMessagesFilterCall : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 1305231012

    }


    /**
     * Returns only incoming call messages with missed/declined discard reasons
     */
    class SearchMessagesFilterMissedCall : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 970663098

    }


    /**
     * Returns only video note messages
     */
    class SearchMessagesFilterVideoNote : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 564323321

    }


    /**
     * Returns only voice and video note messages
     */
    class SearchMessagesFilterVoiceAndVideoNote : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 664174819

    }


    /**
     * Returns only messages with mentions of the current user, or messages that are replies to their messages
     */
    class SearchMessagesFilterMention : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = 2001258652

    }


    /**
     * Returns only messages with unread mentions of the current user, or messages that are replies to their messages
     * When using this filter the results can't be additionally filtered by a query or by the sending user
     */
    class SearchMessagesFilterUnreadMention : SearchMessagesFilter() {

        override val constructor: Int @BsonIgnore get() = -95769149

    }


    /**
     * Describes the different types of activity in a chat
     */
    abstract class ChatAction : Object()

    /**
     * The user is typing a message
     */
    class ChatActionTyping : ChatAction() {

        override val constructor: Int @BsonIgnore get() = 380122167

    }


    /**
     * The user is recording a video
     */
    class ChatActionRecordingVideo : ChatAction() {

        override val constructor: Int @BsonIgnore get() = 216553362

    }


    /**
     * The user is uploading a video
     *
     * @progress - Upload progress, as a percentage
     */
    class ChatActionUploadingVideo : ChatAction {

        var progress: Int by WeakField()

        constructor()

        constructor(progress: Int) {

            this.progress = progress

        }

        override val constructor: Int @BsonIgnore get() = 1234185270

    }


    /**
     * The user is recording a voice note
     */
    class ChatActionRecordingVoiceNote : ChatAction() {

        override val constructor: Int @BsonIgnore get() = -808850058

    }


    /**
     * The user is uploading a voice note
     *
     * @progress - Upload progress, as a percentage
     */
    class ChatActionUploadingVoiceNote : ChatAction {

        var progress: Int by WeakField()

        constructor()

        constructor(progress: Int) {

            this.progress = progress

        }

        override val constructor: Int @BsonIgnore get() = -613643666

    }


    /**
     * The user is uploading a photo
     *
     * @progress - Upload progress, as a percentage
     */
    class ChatActionUploadingPhoto : ChatAction {

        var progress: Int by WeakField()

        constructor()

        constructor(progress: Int) {

            this.progress = progress

        }

        override val constructor: Int @BsonIgnore get() = 654240583

    }


    /**
     * The user is uploading a document
     *
     * @progress - Upload progress, as a percentage
     */
    class ChatActionUploadingDocument : ChatAction {

        var progress: Int by WeakField()

        constructor()

        constructor(progress: Int) {

            this.progress = progress

        }

        override val constructor: Int @BsonIgnore get() = 167884362

    }


    /**
     * The user is picking a location or venue to send
     */
    class ChatActionChoosingLocation : ChatAction() {

        override val constructor: Int @BsonIgnore get() = -2017893596

    }


    /**
     * The user is picking a contact to send
     */
    class ChatActionChoosingContact : ChatAction() {

        override val constructor: Int @BsonIgnore get() = -1222507496

    }


    /**
     * The user has started to play a game
     */
    class ChatActionStartPlayingGame : ChatAction() {

        override val constructor: Int @BsonIgnore get() = -865884164

    }


    /**
     * The user is recording a video note
     */
    class ChatActionRecordingVideoNote : ChatAction() {

        override val constructor: Int @BsonIgnore get() = 16523393

    }


    /**
     * The user is uploading a video note
     *
     * @progress - Upload progress, as a percentage
     */
    class ChatActionUploadingVideoNote : ChatAction {

        var progress: Int by WeakField()

        constructor()

        constructor(progress: Int) {

            this.progress = progress

        }

        override val constructor: Int @BsonIgnore get() = 1172364918

    }


    /**
     * The user has cancelled the previous action
     */
    class ChatActionCancel : ChatAction() {

        override val constructor: Int @BsonIgnore get() = 1160523958

    }


    /**
     * Describes the last time the user was online
     */
    abstract class UserStatus : Object()

    /**
     * The user status was never changed
     */
    class UserStatusEmpty : UserStatus() {

        override val constructor: Int @BsonIgnore get() = 164646985

    }


    /**
     * The user is online
     *
     * @expires - Point in time (Unix timestamp) when the user's online status will expire
     */
    class UserStatusOnline : UserStatus {

        var expires: Int by WeakField()

        constructor()

        constructor(expires: Int) {

            this.expires = expires

        }

        override val constructor: Int @BsonIgnore get() = -1529460876

    }


    /**
     * The user is offline
     *
     * @wasOnline - Point in time (Unix timestamp) when the user was last online
     */
    class UserStatusOffline : UserStatus {

        var wasOnline: Int by WeakField()

        constructor()

        constructor(wasOnline: Int) {

            this.wasOnline = wasOnline

        }

        override val constructor: Int @BsonIgnore get() = -759984891

    }


    /**
     * The user was online recently
     */
    class UserStatusRecently : UserStatus() {

        override val constructor: Int @BsonIgnore get() = -496024847

    }


    /**
     * The user is offline, but was online last week
     */
    class UserStatusLastWeek : UserStatus() {

        override val constructor: Int @BsonIgnore get() = 129960444

    }


    /**
     * The user is offline, but was online last month
     */
    class UserStatusLastMonth : UserStatus() {

        override val constructor: Int @BsonIgnore get() = 2011940674

    }


    /**
     * Represents a list of stickers
     *
     * @stickers - List of stickers
     */
    class Stickers : Object {

        var stickers: Array<Sticker> by WeakField()

        constructor()

        constructor(stickers: Array<Sticker>) {

            this.stickers = stickers

        }

        override val constructor: Int @BsonIgnore get() = 1974859260

    }


    /**
     * Represents a list of emoji
     *
     * @emojis - List of emojis
     */
    class Emojis : Object {

        var emojis: Array<String> by WeakField()

        constructor()

        constructor(emojis: Array<String>) {

            this.emojis = emojis

        }

        override val constructor: Int @BsonIgnore get() = 950339552

    }


    /**
     * Represents a sticker set
     *
     * @id - Identifier of the sticker set
     * @title - Title of the sticker set
     * @name - Name of the sticker set
     * @thumbnail - Sticker set thumbnail in WEBP format with width and height 100
     *              The file can be downloaded only before the thumbnail is changed
     * @isInstalled - True, if the sticker set has been installed by the current user
     * @isArchived - True, if the sticker set has been archived
     *               A sticker set can't be installed and archived simultaneously
     * @isOfficial - True, if the sticker set is official
     * @isAnimated - True, is the stickers in the set are animated
     * @isMasks - True, if the stickers in the set are masks
     * @isViewed - True for already viewed trending sticker sets
     * @stickers - List of stickers in this set
     * @emojis - A list of emoji corresponding to the stickers in the same order
     *           The list is only for informational purposes, because a sticker is always sent with a fixed emoji from the corresponding Sticker object
     */
    class StickerSet : Object {

        var id: Long by WeakField()
        var title: String by WeakField()
        var name: String by WeakField()
        var thumbnail: PhotoSize? = null
        var isInstalled: Boolean by WeakField()
        var isArchived: Boolean by WeakField()
        var isOfficial: Boolean by WeakField()
        var isAnimated: Boolean by WeakField()
        var isMasks: Boolean by WeakField()
        var isViewed: Boolean by WeakField()
        var stickers: Array<Sticker> by WeakField()
        var emojis: Array<Emojis> by WeakField()

        constructor()

        constructor(id: Long, title: String, name: String, thumbnail: PhotoSize? = null, isInstalled: Boolean, isArchived: Boolean, isOfficial: Boolean, isAnimated: Boolean, isMasks: Boolean, isViewed: Boolean, stickers: Array<Sticker>, emojis: Array<Emojis>) {

            this.id = id
            this.title = title
            this.name = name
            this.thumbnail = thumbnail
            this.isInstalled = isInstalled
            this.isArchived = isArchived
            this.isOfficial = isOfficial
            this.isAnimated = isAnimated
            this.isMasks = isMasks
            this.isViewed = isViewed
            this.stickers = stickers
            this.emojis = emojis

        }

        override val constructor: Int @BsonIgnore get() = 734588298

    }


    /**
     * Represents short information about a sticker set
     *
     * @id - Identifier of the sticker set
     * @title - Title of the sticker set
     * @name - Name of the sticker set
     * @thumbnail - Sticker set thumbnail in WEBP format with width and height 100
     * @isInstalled - True, if the sticker set has been installed by current user
     * @isArchived - True, if the sticker set has been archived
     *               A sticker set can't be installed and archived simultaneously
     * @isOfficial - True, if the sticker set is official
     * @isAnimated - True, is the stickers in the set are animated
     * @isMasks - True, if the stickers in the set are masks
     * @isViewed - True for already viewed trending sticker sets
     * @size - Total number of stickers in the set
     * @covers - Contains up to the first 5 stickers from the set, depending on the context
     *           If the client needs more stickers the full set should be requested
     */
    class StickerSetInfo : Object {

        var id: Long by WeakField()
        var title: String by WeakField()
        var name: String by WeakField()
        var thumbnail: PhotoSize? = null
        var isInstalled: Boolean by WeakField()
        var isArchived: Boolean by WeakField()
        var isOfficial: Boolean by WeakField()
        var isAnimated: Boolean by WeakField()
        var isMasks: Boolean by WeakField()
        var isViewed: Boolean by WeakField()
        var size: Int by WeakField()
        var covers: Array<Sticker> by WeakField()

        constructor()

        constructor(id: Long, title: String, name: String, thumbnail: PhotoSize? = null, isInstalled: Boolean, isArchived: Boolean, isOfficial: Boolean, isAnimated: Boolean, isMasks: Boolean, isViewed: Boolean, size: Int, covers: Array<Sticker>) {

            this.id = id
            this.title = title
            this.name = name
            this.thumbnail = thumbnail
            this.isInstalled = isInstalled
            this.isArchived = isArchived
            this.isOfficial = isOfficial
            this.isAnimated = isAnimated
            this.isMasks = isMasks
            this.isViewed = isViewed
            this.size = size
            this.covers = covers

        }

        override val constructor: Int @BsonIgnore get() = 228054782

    }


    /**
     * Represents a list of sticker sets
     *
     * @totalCount - Approximate total number of sticker sets found
     * @sets - List of sticker sets
     */
    class StickerSets : Object {

        var totalCount: Int by WeakField()
        var sets: Array<StickerSetInfo> by WeakField()

        constructor()

        constructor(totalCount: Int, sets: Array<StickerSetInfo>) {

            this.totalCount = totalCount
            this.sets = sets

        }

        override val constructor: Int @BsonIgnore get() = -1883828812

    }


    /**
     * Describes the reason why a call was discarded
     */
    abstract class CallDiscardReason : Object()

    /**
     * The call wasn't discarded, or the reason is unknown
     */
    class CallDiscardReasonEmpty : CallDiscardReason() {

        override val constructor: Int @BsonIgnore get() = -1258917949

    }


    /**
     * The call was ended before the conversation started
     * It was cancelled by the caller or missed by the other party
     */
    class CallDiscardReasonMissed : CallDiscardReason() {

        override val constructor: Int @BsonIgnore get() = 1680358012

    }


    /**
     * The call was ended before the conversation started
     * It was declined by the other party
     */
    class CallDiscardReasonDeclined : CallDiscardReason() {

        override val constructor: Int @BsonIgnore get() = -1729926094

    }


    /**
     * The call was ended during the conversation because the users were disconnected
     */
    class CallDiscardReasonDisconnected : CallDiscardReason() {

        override val constructor: Int @BsonIgnore get() = -1342872670

    }


    /**
     * The call was ended because one of the parties hung up
     */
    class CallDiscardReasonHungUp : CallDiscardReason() {

        override val constructor: Int @BsonIgnore get() = 438216166

    }


    /**
     * Specifies the supported call protocols
     *
     * @udpP2p - True, if UDP peer-to-peer connections are supported
     * @udpReflector - True, if connection through UDP reflectors is supported
     * @minLayer - The minimum supported API layer
     * @maxLayer - The maximum supported API layer
     */
    class CallProtocol : Object {

        var udpP2p: Boolean by WeakField()
        var udpReflector: Boolean by WeakField()
        var minLayer: Int by WeakField()
        var maxLayer: Int by WeakField()

        constructor()

        constructor(udpP2p: Boolean, udpReflector: Boolean, minLayer: Int, maxLayer: Int) {

            this.udpP2p = udpP2p
            this.udpReflector = udpReflector
            this.minLayer = minLayer
            this.maxLayer = maxLayer

        }

        override val constructor: Int @BsonIgnore get() = -1042830667

    }


    /**
     * Describes the address of UDP reflectors
     *
     * @id - Reflector identifier
     * @ip - IPv4 reflector address
     * @ipv6 - IPv6 reflector address
     * @port - Reflector port number
     * @peerTag - Connection peer tag
     */
    class CallConnection : Object {

        var id: Long by WeakField()
        var ip: String by WeakField()
        var ipv6: String by WeakField()
        var port: Int by WeakField()
        var peerTag: ByteArray by WeakField()

        constructor()

        constructor(id: Long, ip: String, ipv6: String, port: Int, peerTag: ByteArray) {

            this.id = id
            this.ip = ip
            this.ipv6 = ipv6
            this.port = port
            this.peerTag = peerTag

        }

        override val constructor: Int @BsonIgnore get() = 1318542714

    }


    /**
     * Contains the call identifier
     *
     * @id - Call identifier
     */
    class CallId : Object {

        var id: Int by WeakField()

        constructor()

        constructor(id: Int) {

            this.id = id

        }

        override val constructor: Int @BsonIgnore get() = 65717769

    }


    /**
     * Describes the current call state
     */
    abstract class CallState : Object()

    /**
     * The call is pending, waiting to be accepted by a user
     *
     * @isCreated - True, if the call has already been created by the server
     * @isReceived - True, if the call has already been received by the other party
     */
    class CallStatePending : CallState {

        var isCreated: Boolean by WeakField()
        var isReceived: Boolean by WeakField()

        constructor()

        constructor(isCreated: Boolean, isReceived: Boolean) {

            this.isCreated = isCreated
            this.isReceived = isReceived

        }

        override val constructor: Int @BsonIgnore get() = 1073048620

    }


    /**
     * The call has been answered and encryption keys are being exchanged
     */
    class CallStateExchangingKeys : CallState() {

        override val constructor: Int @BsonIgnore get() = -1848149403

    }


    /**
     * The call is ready to use
     *
     * @protocol - Call protocols supported by the peer
     * @connections - Available UDP reflectors
     * @config - A JSON-encoded call config
     * @encryptionKey - Call encryption key
     * @emojis - Encryption key emojis fingerprint
     * @allowP2p - True, if peer-to-peer connection is allowed by users privacy settings
     */
    class CallStateReady : CallState {

        var protocol: CallProtocol by WeakField()
        var connections: Array<CallConnection> by WeakField()
        var config: String by WeakField()
        var encryptionKey: ByteArray by WeakField()
        var emojis: Array<String> by WeakField()
        var allowP2p: Boolean by WeakField()

        constructor()

        constructor(protocol: CallProtocol, connections: Array<CallConnection>, config: String, encryptionKey: ByteArray, emojis: Array<String>, allowP2p: Boolean) {

            this.protocol = protocol
            this.connections = connections
            this.config = config
            this.encryptionKey = encryptionKey
            this.emojis = emojis
            this.allowP2p = allowP2p

        }

        override val constructor: Int @BsonIgnore get() = 1848397705

    }


    /**
     * The call is hanging up after discardCall has been called
     */
    class CallStateHangingUp : CallState() {

        override val constructor: Int @BsonIgnore get() = -2133790038

    }


    /**
     * The call has ended successfully
     *
     * @reason - The reason, why the call has ended
     * @needRating - True, if the call rating should be sent to the server
     * @needDebugInformation - True, if the call debug information should be sent to the server
     */
    class CallStateDiscarded : CallState {

        var reason: CallDiscardReason by WeakField()
        var needRating: Boolean by WeakField()
        var needDebugInformation: Boolean by WeakField()

        constructor()

        constructor(reason: CallDiscardReason, needRating: Boolean, needDebugInformation: Boolean) {

            this.reason = reason
            this.needRating = needRating
            this.needDebugInformation = needDebugInformation

        }

        override val constructor: Int @BsonIgnore get() = -190853167

    }


    /**
     * The call has ended with an error
     *
     * @error - Error
     *          An error with the code 4005000 will be returned if an outgoing call is missed because of an expired timeout
     */
    class CallStateError : CallState {

        var error: Error by WeakField()

        constructor()

        constructor(error: Error) {

            this.error = error

        }

        override val constructor: Int @BsonIgnore get() = -975215467

    }


    /**
     * Describes the exact type of a problem with a call
     */
    abstract class CallProblem : Object()

    /**
     * The user heard their own voice
     */
    class CallProblemEcho : CallProblem() {

        override val constructor: Int @BsonIgnore get() = 801116548

    }


    /**
     * The user heard background noise
     */
    class CallProblemNoise : CallProblem() {

        override val constructor: Int @BsonIgnore get() = 1053065359

    }


    /**
     * The other side kept disappearing
     */
    class CallProblemInterruptions : CallProblem() {

        override val constructor: Int @BsonIgnore get() = 1119493218

    }


    /**
     * The speech was distorted
     */
    class CallProblemDistortedSpeech : CallProblem() {

        override val constructor: Int @BsonIgnore get() = 379960581

    }


    /**
     * The user couldn't hear the other side
     */
    class CallProblemSilentLocal : CallProblem() {

        override val constructor: Int @BsonIgnore get() = 253652790

    }


    /**
     * The other side couldn't hear the user
     */
    class CallProblemSilentRemote : CallProblem() {

        override val constructor: Int @BsonIgnore get() = 573634714

    }


    /**
     * The call ended unexpectedly
     */
    class CallProblemDropped : CallProblem() {

        override val constructor: Int @BsonIgnore get() = -1207311487

    }


    /**
     * Describes a call
     *
     * @id - Call identifier, not persistent
     * @userId - Peer user identifier
     * @isOutgoing - True, if the call is outgoing
     * @state - Call state
     */
    class Call : Object {

        var id: Int by WeakField()
        var userId: Int by WeakField()
        var isOutgoing: Boolean by WeakField()
        var state: CallState by WeakField()

        constructor()

        constructor(id: Int, userId: Int, isOutgoing: Boolean, state: CallState) {

            this.id = id
            this.userId = userId
            this.isOutgoing = isOutgoing
            this.state = state

        }

        override val constructor: Int @BsonIgnore get() = -1837599107

    }


    /**
     * Contains settings for the authentication of the user's phone number
     *
     * @allowFlashCall - Pass true if the authentication code may be sent via flash call to the specified phone number
     * @isCurrentPhoneNumber - Pass true if the authenticated phone number is used on the current device
     * @allowSmsRetrieverApi - For official applications only
     *                         True, if the app can use Android SMS Retriever API (requires Google Play Services >= 10.2) to automatically receive the authentication code from the SMS
     *                         See https://developers.google.com/identity/sms-retriever/ for more details
     */
    class PhoneNumberAuthenticationSettings : Object {

        var allowFlashCall: Boolean by WeakField()
        var isCurrentPhoneNumber: Boolean by WeakField()
        var allowSmsRetrieverApi: Boolean by WeakField()

        constructor()

        constructor(allowFlashCall: Boolean, isCurrentPhoneNumber: Boolean, allowSmsRetrieverApi: Boolean) {

            this.allowFlashCall = allowFlashCall
            this.isCurrentPhoneNumber = isCurrentPhoneNumber
            this.allowSmsRetrieverApi = allowSmsRetrieverApi

        }

        override val constructor: Int @BsonIgnore get() = -859198743

    }


    /**
     * Represents a list of animations
     *
     * @animations - List of animations
     */
    class Animations : Object {

        var animations: Array<Animation> by WeakField()

        constructor()

        constructor(animations: Array<Animation>) {

            this.animations = animations

        }

        override val constructor: Int @BsonIgnore get() = 344216945

    }


    /**
     * Represents the result of an ImportContacts request
     *
     * @userIds - User identifiers of the imported contacts in the same order as they were specified in the request
     *            0 if the contact is not yet a registered user
     * @importerCount - The number of users that imported the corresponding contact
     *                  0 for already registered users or if unavailable
     */
    class ImportedContacts : Object {

        var userIds: IntArray by WeakField()
        var importerCount: IntArray by WeakField()

        constructor()

        constructor(userIds: IntArray, importerCount: IntArray) {

            this.userIds = userIds
            this.importerCount = importerCount

        }

        override val constructor: Int @BsonIgnore get() = -741685354

    }


    /**
     * Contains an HTTP URL
     *
     * @url - The URL
     */
    class HttpUrl : Object {

        var url: String by WeakField()

        constructor()

        constructor(url: String) {

            this.url = url

        }

        override val constructor: Int @BsonIgnore get() = -2018019930

    }


    /**
     * Represents a single result of an inline query
     * For bots only
     */
    abstract class InputInlineQueryResult : Object()

    /**
     * Represents a link to an animated GIF
     *
     * @id - Unique identifier of the query result
     * @title - Title of the query result
     * @thumbnailUrl - URL of the static result thumbnail (JPEG or GIF), if it exists
     * @gifUrl - The URL of the GIF-file (file size must not exceed 1MB)
     * @gifDuration - Duration of the GIF, in seconds
     * @gifWidth - Width of the GIF
     * @gifHeight - Height of the GIF
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageAnimation, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultAnimatedGif : InputInlineQueryResult {

        var id: String? = null
        var title: String? = null
        var thumbnailUrl: String? = null
        var gifUrl: String? = null
        var gifDuration: Int? = null
        var gifWidth: Int? = null
        var gifHeight: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, title: String? = null, thumbnailUrl: String? = null, gifUrl: String? = null, gifDuration: Int? = null, gifWidth: Int? = null, gifHeight: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.title = title
            this.thumbnailUrl = thumbnailUrl
            this.gifUrl = gifUrl
            this.gifDuration = gifDuration
            this.gifWidth = gifWidth
            this.gifHeight = gifHeight
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = -891474894

    }


    /**
     * Represents a link to an animated (i.e
     * Without sound) H.264/MPEG-4 AVC video
     *
     * @id - Unique identifier of the query result
     * @title - Title of the result
     * @thumbnailUrl - URL of the static result thumbnail (JPEG or GIF), if it exists
     * @mpeg4Url - The URL of the MPEG4-file (file size must not exceed 1MB)
     * @mpeg4Duration - Duration of the video, in seconds
     * @mpeg4Width - Width of the video
     * @mpeg4Height - Height of the video
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageAnimation, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultAnimatedMpeg4 : InputInlineQueryResult {

        var id: String? = null
        var title: String? = null
        var thumbnailUrl: String? = null
        var mpeg4Url: String? = null
        var mpeg4Duration: Int? = null
        var mpeg4Width: Int? = null
        var mpeg4Height: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, title: String? = null, thumbnailUrl: String? = null, mpeg4Url: String? = null, mpeg4Duration: Int? = null, mpeg4Width: Int? = null, mpeg4Height: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.title = title
            this.thumbnailUrl = thumbnailUrl
            this.mpeg4Url = mpeg4Url
            this.mpeg4Duration = mpeg4Duration
            this.mpeg4Width = mpeg4Width
            this.mpeg4Height = mpeg4Height
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = -1629529888

    }


    /**
     * Represents a link to an article or web page
     *
     * @id - Unique identifier of the query result
     * @url - URL of the result, if it exists
     * @hideUrl - True, if the URL must be not shown
     * @title - Title of the result
     * @description - A short description of the result
     * @thumbnailUrl - URL of the result thumbnail, if it exists
     * @thumbnailWidth - Thumbnail width, if known
     * @thumbnailHeight - Thumbnail height, if known
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultArticle : InputInlineQueryResult {

        var id: String? = null
        var url: String? = null
        var hideUrl: Boolean? = null
        var title: String? = null
        var description: String? = null
        var thumbnailUrl: String? = null
        var thumbnailWidth: Int? = null
        var thumbnailHeight: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, url: String? = null, hideUrl: Boolean? = null, title: String? = null, description: String? = null, thumbnailUrl: String? = null, thumbnailWidth: Int? = null, thumbnailHeight: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.url = url
            this.hideUrl = hideUrl
            this.title = title
            this.description = description
            this.thumbnailUrl = thumbnailUrl
            this.thumbnailWidth = thumbnailWidth
            this.thumbnailHeight = thumbnailHeight
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = 1973670156

    }


    /**
     * Represents a link to an MP3 audio file
     *
     * @id - Unique identifier of the query result
     * @title - Title of the audio file
     * @performer - Performer of the audio file
     * @audioUrl - The URL of the audio file
     * @audioDuration - Audio file duration, in seconds
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageAudio, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultAudio : InputInlineQueryResult {

        var id: String? = null
        var title: String? = null
        var performer: String? = null
        var audioUrl: String? = null
        var audioDuration: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, title: String? = null, performer: String? = null, audioUrl: String? = null, audioDuration: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.title = title
            this.performer = performer
            this.audioUrl = audioUrl
            this.audioDuration = audioDuration
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = 1260139988

    }


    /**
     * Represents a user contact
     *
     * @id - Unique identifier of the query result
     * @contact - User contact
     * @thumbnailUrl - URL of the result thumbnail, if it exists
     * @thumbnailWidth - Thumbnail width, if known
     * @thumbnailHeight - Thumbnail height, if known
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultContact : InputInlineQueryResult {

        var id: String? = null
        var contact: Contact? = null
        var thumbnailUrl: String? = null
        var thumbnailWidth: Int? = null
        var thumbnailHeight: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, contact: Contact? = null, thumbnailUrl: String? = null, thumbnailWidth: Int? = null, thumbnailHeight: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.contact = contact
            this.thumbnailUrl = thumbnailUrl
            this.thumbnailWidth = thumbnailWidth
            this.thumbnailHeight = thumbnailHeight
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = 1846064594

    }


    /**
     * Represents a link to a file
     *
     * @id - Unique identifier of the query result
     * @title - Title of the resulting file
     * @description - Short description of the result, if known
     * @documentUrl - URL of the file
     * @mimeType - MIME type of the file content
     *             Only "application/pdf" and "application/zip" are currently allowed
     * @thumbnailUrl - The URL of the file thumbnail, if it exists
     * @thumbnailWidth - Width of the thumbnail
     * @thumbnailHeight - Height of the thumbnail
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageDocument, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultDocument : InputInlineQueryResult {

        var id: String? = null
        var title: String? = null
        var description: String? = null
        var documentUrl: String? = null
        var mimeType: String? = null
        var thumbnailUrl: String? = null
        var thumbnailWidth: Int? = null
        var thumbnailHeight: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, title: String? = null, description: String? = null, documentUrl: String? = null, mimeType: String? = null, thumbnailUrl: String? = null, thumbnailWidth: Int? = null, thumbnailHeight: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.title = title
            this.description = description
            this.documentUrl = documentUrl
            this.mimeType = mimeType
            this.thumbnailUrl = thumbnailUrl
            this.thumbnailWidth = thumbnailWidth
            this.thumbnailHeight = thumbnailHeight
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = 578801869

    }


    /**
     * Represents a game
     *
     * @id - Unique identifier of the query result
     * @gameShortName - Short name of the game
     * @replyMarkup - Message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     */
    class InputInlineQueryResultGame : InputInlineQueryResult {

        var id: String? = null
        var gameShortName: String? = null
        var replyMarkup: ReplyMarkup? = null

        constructor()

        constructor(id: String? = null, gameShortName: String? = null, replyMarkup: ReplyMarkup? = null) {

            this.id = id
            this.gameShortName = gameShortName
            this.replyMarkup = replyMarkup

        }

        override val constructor: Int @BsonIgnore get() = 966074327

    }


    /**
     * Represents a point on the map
     *
     * @id - Unique identifier of the query result
     * @location - Location result
     * @livePeriod - Amount of time relative to the message sent time until the location can be updated, in seconds
     * @title - Title of the result
     * @thumbnailUrl - URL of the result thumbnail, if it exists
     * @thumbnailWidth - Thumbnail width, if known
     * @thumbnailHeight - Thumbnail height, if known
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultLocation : InputInlineQueryResult {

        var id: String? = null
        var location: Location? = null
        var livePeriod: Int? = null
        var title: String? = null
        var thumbnailUrl: String? = null
        var thumbnailWidth: Int? = null
        var thumbnailHeight: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, location: Location? = null, livePeriod: Int? = null, title: String? = null, thumbnailUrl: String? = null, thumbnailWidth: Int? = null, thumbnailHeight: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.location = location
            this.livePeriod = livePeriod
            this.title = title
            this.thumbnailUrl = thumbnailUrl
            this.thumbnailWidth = thumbnailWidth
            this.thumbnailHeight = thumbnailHeight
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = -1887650218

    }


    /**
     * Represents link to a JPEG image
     *
     * @id - Unique identifier of the query result
     * @title - Title of the result, if known
     * @description - A short description of the result, if known
     * @thumbnailUrl - URL of the photo thumbnail, if it exists
     * @photoUrl - The URL of the JPEG photo (photo size must not exceed 5MB)
     * @photoWidth - Width of the photo
     * @photoHeight - Height of the photo
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessagePhoto, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultPhoto : InputInlineQueryResult {

        var id: String? = null
        var title: String? = null
        var description: String? = null
        var thumbnailUrl: String? = null
        var photoUrl: String? = null
        var photoWidth: Int? = null
        var photoHeight: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, title: String? = null, description: String? = null, thumbnailUrl: String? = null, photoUrl: String? = null, photoWidth: Int? = null, photoHeight: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.title = title
            this.description = description
            this.thumbnailUrl = thumbnailUrl
            this.photoUrl = photoUrl
            this.photoWidth = photoWidth
            this.photoHeight = photoHeight
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = -1123338721

    }


    /**
     * Represents a link to a WEBP or TGS sticker
     *
     * @id - Unique identifier of the query result
     * @thumbnailUrl - URL of the sticker thumbnail, if it exists
     * @stickerUrl - The URL of the WEBP or TGS sticker (sticker file size must not exceed 5MB)
     * @stickerWidth - Width of the sticker
     * @stickerHeight - Height of the sticker
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, inputMessageSticker, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultSticker : InputInlineQueryResult {

        var id: String? = null
        var thumbnailUrl: String? = null
        var stickerUrl: String? = null
        var stickerWidth: Int? = null
        var stickerHeight: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, thumbnailUrl: String? = null, stickerUrl: String? = null, stickerWidth: Int? = null, stickerHeight: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.thumbnailUrl = thumbnailUrl
            this.stickerUrl = stickerUrl
            this.stickerWidth = stickerWidth
            this.stickerHeight = stickerHeight
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = 274007129

    }


    /**
     * Represents information about a venue
     *
     * @id - Unique identifier of the query result
     * @venue - Venue result
     * @thumbnailUrl - URL of the result thumbnail, if it exists
     * @thumbnailWidth - Thumbnail width, if known
     * @thumbnailHeight - Thumbnail height, if known
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultVenue : InputInlineQueryResult {

        var id: String? = null
        var venue: Venue? = null
        var thumbnailUrl: String? = null
        var thumbnailWidth: Int? = null
        var thumbnailHeight: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, venue: Venue? = null, thumbnailUrl: String? = null, thumbnailWidth: Int? = null, thumbnailHeight: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.venue = venue
            this.thumbnailUrl = thumbnailUrl
            this.thumbnailWidth = thumbnailWidth
            this.thumbnailHeight = thumbnailHeight
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = 541704509

    }


    /**
     * Represents a link to a page containing an embedded video player or a video file
     *
     * @id - Unique identifier of the query result
     * @title - Title of the result
     * @description - A short description of the result, if known
     * @thumbnailUrl - The URL of the video thumbnail (JPEG), if it exists
     * @videoUrl - URL of the embedded video player or video file
     * @mimeType - MIME type of the content of the video URL, only "text/html" or "video/mp4" are currently supported
     * @videoWidth - Width of the video
     * @videoHeight - Height of the video
     * @videoDuration - Video duration, in seconds
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageVideo, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultVideo : InputInlineQueryResult {

        var id: String? = null
        var title: String? = null
        var description: String? = null
        var thumbnailUrl: String? = null
        var videoUrl: String? = null
        var mimeType: String? = null
        var videoWidth: Int? = null
        var videoHeight: Int? = null
        var videoDuration: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, title: String? = null, description: String? = null, thumbnailUrl: String? = null, videoUrl: String? = null, mimeType: String? = null, videoWidth: Int? = null, videoHeight: Int? = null, videoDuration: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.title = title
            this.description = description
            this.thumbnailUrl = thumbnailUrl
            this.videoUrl = videoUrl
            this.mimeType = mimeType
            this.videoWidth = videoWidth
            this.videoHeight = videoHeight
            this.videoDuration = videoDuration
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = 1724073191

    }


    /**
     * Represents a link to an opus-encoded audio file within an OGG container, single channel audio
     *
     * @id - Unique identifier of the query result
     * @title - Title of the voice note
     * @voiceNoteUrl - The URL of the voice note file
     * @voiceNoteDuration - Duration of the voice note, in seconds
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageVoiceNote, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    class InputInlineQueryResultVoiceNote : InputInlineQueryResult {

        var id: String? = null
        var title: String? = null
        var voiceNoteUrl: String? = null
        var voiceNoteDuration: Int? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(id: String? = null, title: String? = null, voiceNoteUrl: String? = null, voiceNoteDuration: Int? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.id = id
            this.title = title
            this.voiceNoteUrl = voiceNoteUrl
            this.voiceNoteDuration = voiceNoteDuration
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = -1790072503

    }


    /**
     * Represents a single result of an inline query
     */
    abstract class InlineQueryResult : Object()

    /**
     * Represents a link to an article or web page
     *
     * @id - Unique identifier of the query result
     * @url - URL of the result, if it exists
     * @hideUrl - True, if the URL must be not shown
     * @title - Title of the result
     * @description - A short description of the result
     * @thumbnail - Result thumbnail
     */
    class InlineQueryResultArticle : InlineQueryResult {

        var id: String by WeakField()
        var url: String by WeakField()
        var hideUrl: Boolean by WeakField()
        var title: String by WeakField()
        var description: String by WeakField()
        var thumbnail: PhotoSize? = null

        constructor()

        constructor(id: String, url: String, hideUrl: Boolean, title: String, description: String, thumbnail: PhotoSize? = null) {

            this.id = id
            this.url = url
            this.hideUrl = hideUrl
            this.title = title
            this.description = description
            this.thumbnail = thumbnail

        }

        override val constructor: Int @BsonIgnore get() = -518366710

    }


    /**
     * Represents a user contact
     *
     * @id - Unique identifier of the query result
     * @contact - A user contact
     * @thumbnail - Result thumbnail
     */
    class InlineQueryResultContact : InlineQueryResult {

        var id: String by WeakField()
        var contact: Contact by WeakField()
        var thumbnail: PhotoSize? = null

        constructor()

        constructor(id: String, contact: Contact, thumbnail: PhotoSize? = null) {

            this.id = id
            this.contact = contact
            this.thumbnail = thumbnail

        }

        override val constructor: Int @BsonIgnore get() = 410081985

    }


    /**
     * Represents a point on the map
     *
     * @id - Unique identifier of the query result
     * @location - Location result
     * @title - Title of the result
     * @thumbnail - Result thumbnail
     */
    class InlineQueryResultLocation : InlineQueryResult {

        var id: String by WeakField()
        var location: Location by WeakField()
        var title: String by WeakField()
        var thumbnail: PhotoSize? = null

        constructor()

        constructor(id: String, location: Location, title: String, thumbnail: PhotoSize? = null) {

            this.id = id
            this.location = location
            this.title = title
            this.thumbnail = thumbnail

        }

        override val constructor: Int @BsonIgnore get() = -158305341

    }


    /**
     * Represents information about a venue
     *
     * @id - Unique identifier of the query result
     * @venue - Venue result
     * @thumbnail - Result thumbnail
     */
    class InlineQueryResultVenue : InlineQueryResult {

        var id: String by WeakField()
        var venue: Venue by WeakField()
        var thumbnail: PhotoSize? = null

        constructor()

        constructor(id: String, venue: Venue, thumbnail: PhotoSize? = null) {

            this.id = id
            this.venue = venue
            this.thumbnail = thumbnail

        }

        override val constructor: Int @BsonIgnore get() = -1592932211

    }


    /**
     * Represents information about a game
     *
     * @id - Unique identifier of the query result
     * @game - Game result
     */
    class InlineQueryResultGame : InlineQueryResult {

        var id: String by WeakField()
        var game: Game by WeakField()

        constructor()

        constructor(id: String, game: Game) {

            this.id = id
            this.game = game

        }

        override val constructor: Int @BsonIgnore get() = 1706916987

    }


    /**
     * Represents an animation file
     *
     * @id - Unique identifier of the query result
     * @animation - Animation file
     * @title - Animation title
     */
    class InlineQueryResultAnimation : InlineQueryResult {

        var id: String by WeakField()
        var animation: Animation by WeakField()
        var title: String by WeakField()

        constructor()

        constructor(id: String, animation: Animation, title: String) {

            this.id = id
            this.animation = animation
            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = 2009984267

    }


    /**
     * Represents an audio file
     *
     * @id - Unique identifier of the query result
     * @audio - Audio file
     */
    class InlineQueryResultAudio : InlineQueryResult {

        var id: String by WeakField()
        var audio: Audio by WeakField()

        constructor()

        constructor(id: String, audio: Audio) {

            this.id = id
            this.audio = audio

        }

        override val constructor: Int @BsonIgnore get() = 842650360

    }


    /**
     * Represents a document
     *
     * @id - Unique identifier of the query result
     * @document - Document
     * @title - Document title
     * @description - Document description
     */
    class InlineQueryResultDocument : InlineQueryResult {

        var id: String by WeakField()
        var document: Document by WeakField()
        var title: String by WeakField()
        var description: String by WeakField()

        constructor()

        constructor(id: String, document: Document, title: String, description: String) {

            this.id = id
            this.document = document
            this.title = title
            this.description = description

        }

        override val constructor: Int @BsonIgnore get() = -1491268539

    }


    /**
     * Represents a photo
     *
     * @id - Unique identifier of the query result
     * @photo - Photo
     * @title - Title of the result, if known
     * @description - A short description of the result, if known
     */
    class InlineQueryResultPhoto : InlineQueryResult {

        var id: String by WeakField()
        var photo: Photo by WeakField()
        var title: String by WeakField()
        var description: String by WeakField()

        constructor()

        constructor(id: String, photo: Photo, title: String, description: String) {

            this.id = id
            this.photo = photo
            this.title = title
            this.description = description

        }

        override val constructor: Int @BsonIgnore get() = 1848319440

    }


    /**
     * Represents a sticker
     *
     * @id - Unique identifier of the query result
     * @sticker - Sticker
     */
    class InlineQueryResultSticker : InlineQueryResult {

        var id: String by WeakField()
        var sticker: Sticker by WeakField()

        constructor()

        constructor(id: String, sticker: Sticker) {

            this.id = id
            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = -1848224245

    }


    /**
     * Represents a video
     *
     * @id - Unique identifier of the query result
     * @video - Video
     * @title - Title of the video
     * @description - Description of the video
     */
    class InlineQueryResultVideo : InlineQueryResult {

        var id: String by WeakField()
        var video: Video by WeakField()
        var title: String by WeakField()
        var description: String by WeakField()

        constructor()

        constructor(id: String, video: Video, title: String, description: String) {

            this.id = id
            this.video = video
            this.title = title
            this.description = description

        }

        override val constructor: Int @BsonIgnore get() = -1373158683

    }


    /**
     * Represents a voice note
     *
     * @id - Unique identifier of the query result
     * @voiceNote - Voice note
     * @title - Title of the voice note
     */
    class InlineQueryResultVoiceNote : InlineQueryResult {

        var id: String by WeakField()
        var voiceNote: VoiceNote by WeakField()
        var title: String by WeakField()

        constructor()

        constructor(id: String, voiceNote: VoiceNote, title: String) {

            this.id = id
            this.voiceNote = voiceNote
            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = -1897393105

    }


    /**
     * Represents the results of the inline query
     * Use sendInlineQueryResultMessage to send the result of the query
     *
     * @inlineQueryId - Unique identifier of the inline query
     * @nextOffset - The offset for the next request
     *               If empty, there are no more results
     * @results - Results of the query
     * @switchPmText - If non-empty, this text should be shown on the button, which opens a private chat with the bot and sends the bot a start message with the switch_pm_parameter
     * @switchPmParameter - Parameter for the bot start message
     */
    class InlineQueryResults : Object {

        var inlineQueryId: Long by WeakField()
        var nextOffset: String by WeakField()
        var results: Array<InlineQueryResult> by WeakField()
        var switchPmText: String by WeakField()
        var switchPmParameter: String by WeakField()

        constructor()

        constructor(inlineQueryId: Long, nextOffset: String, results: Array<InlineQueryResult>, switchPmText: String, switchPmParameter: String) {

            this.inlineQueryId = inlineQueryId
            this.nextOffset = nextOffset
            this.results = results
            this.switchPmText = switchPmText
            this.switchPmParameter = switchPmParameter

        }

        override val constructor: Int @BsonIgnore get() = 1000709656

    }


    /**
     * Represents a payload of a callback query
     */
    abstract class CallbackQueryPayload : Object()

    /**
     * The payload from a general callback button
     *
     * @data - Data that was attached to the callback button
     */
    class CallbackQueryPayloadData : CallbackQueryPayload {

        var data: ByteArray by WeakField()

        constructor()

        constructor(data: ByteArray) {

            this.data = data

        }

        override val constructor: Int @BsonIgnore get() = -1977729946

    }


    /**
     * The payload from a game callback button
     *
     * @gameShortName - A short name of the game that was attached to the callback button
     */
    class CallbackQueryPayloadGame : CallbackQueryPayload {

        var gameShortName: String by WeakField()

        constructor()

        constructor(gameShortName: String) {

            this.gameShortName = gameShortName

        }

        override val constructor: Int @BsonIgnore get() = 1303571512

    }


    /**
     * Contains a bot's answer to a callback query
     *
     * @text - Text of the answer
     * @showAlert - True, if an alert should be shown to the user instead of a toast notification
     * @url - URL to be opened
     */
    class CallbackQueryAnswer : Object {

        var text: String by WeakField()
        var showAlert: Boolean by WeakField()
        var url: String by WeakField()

        constructor()

        constructor(text: String, showAlert: Boolean, url: String) {

            this.text = text
            this.showAlert = showAlert
            this.url = url

        }

        override val constructor: Int @BsonIgnore get() = 360867933

    }


    /**
     * Contains the result of a custom request
     *
     * @result - A JSON-serialized result
     */
    class CustomRequestResult : Object {

        var result: String by WeakField()

        constructor()

        constructor(result: String) {

            this.result = result

        }

        override val constructor: Int @BsonIgnore get() = -2009960452

    }


    /**
     * Contains one row of the game high score table
     *
     * @position - Position in the high score table
     * @userId - User identifier
     * @score - User score
     */
    class GameHighScore : Object {

        var position: Int by WeakField()
        var userId: Int by WeakField()
        var score: Int by WeakField()

        constructor()

        constructor(position: Int, userId: Int, score: Int) {

            this.position = position
            this.userId = userId
            this.score = score

        }

        override val constructor: Int @BsonIgnore get() = -30778358

    }


    /**
     * Contains a list of game high scores
     *
     * @scores - A list of game high scores
     */
    class GameHighScores : Object {

        var scores: Array<GameHighScore> by WeakField()

        constructor()

        constructor(scores: Array<GameHighScore>) {

            this.scores = scores

        }

        override val constructor: Int @BsonIgnore get() = -725770727

    }


    /**
     * Contains the response of a request to TON lite server
     *
     * @response - The response
     */
    class TonLiteServerResponse : Object {

        var response: ByteArray by WeakField()

        constructor()

        constructor(response: ByteArray) {

            this.response = response

        }

        override val constructor: Int @BsonIgnore get() = 928306959

    }


    /**
     * Contains the salt to be used with locally stored password to access a local TON-based wallet
     *
     * @salt - The salt
     */
    class TonWalletPasswordSalt : Object {

        var salt: ByteArray by WeakField()

        constructor()

        constructor(salt: ByteArray) {

            this.salt = salt

        }

        override val constructor: Int @BsonIgnore get() = -1406795233

    }


    /**
     * Represents a chat event
     */
    abstract class ChatEventAction : Object()

    /**
     * A message was edited
     *
     * @oldMessage - The original message before the edit
     * @newMessage - The message after it was edited
     */
    class ChatEventMessageEdited : ChatEventAction {

        var oldMessage: Message by WeakField()
        var newMessage: Message by WeakField()

        constructor()

        constructor(oldMessage: Message, newMessage: Message) {

            this.oldMessage = oldMessage
            this.newMessage = newMessage

        }

        override val constructor: Int @BsonIgnore get() = -430967304

    }


    /**
     * A message was deleted
     *
     * @message - Deleted message
     */
    class ChatEventMessageDeleted : ChatEventAction {

        var message: Message by WeakField()

        constructor()

        constructor(message: Message) {

            this.message = message

        }

        override val constructor: Int @BsonIgnore get() = -892974601

    }


    /**
     * A poll in a message was stopped
     *
     * @message - The message with the poll
     */
    class ChatEventPollStopped : ChatEventAction {

        var message: Message by WeakField()

        constructor()

        constructor(message: Message) {

            this.message = message

        }

        override val constructor: Int @BsonIgnore get() = 2009893861

    }


    /**
     * A message was pinned
     *
     * @message - Pinned message
     */
    class ChatEventMessagePinned : ChatEventAction {

        var message: Message by WeakField()

        constructor()

        constructor(message: Message) {

            this.message = message

        }

        override val constructor: Int @BsonIgnore get() = 438742298

    }


    /**
     * A message was unpinned
     */
    class ChatEventMessageUnpinned : ChatEventAction() {

        override val constructor: Int @BsonIgnore get() = 2002594849

    }


    /**
     * A new member joined the chat
     */
    class ChatEventMemberJoined : ChatEventAction() {

        override val constructor: Int @BsonIgnore get() = -235468508

    }


    /**
     * A member left the chat
     */
    class ChatEventMemberLeft : ChatEventAction() {

        override val constructor: Int @BsonIgnore get() = -948420593

    }


    /**
     * A new chat member was invited
     *
     * @userId - New member user identifier
     * @status - New member status
     */
    class ChatEventMemberInvited : ChatEventAction {

        var userId: Int by WeakField()
        var status: ChatMemberStatus by WeakField()

        constructor()

        constructor(userId: Int, status: ChatMemberStatus) {

            this.userId = userId
            this.status = status

        }

        override val constructor: Int @BsonIgnore get() = -2093688706

    }


    /**
     * A chat member has gained/lost administrator status, or the list of their administrator privileges has changed
     *
     * @userId - Chat member user identifier
     * @oldStatus - Previous status of the chat member
     * @newStatus - New status of the chat member
     */
    class ChatEventMemberPromoted : ChatEventAction {

        var userId: Int by WeakField()
        var oldStatus: ChatMemberStatus by WeakField()
        var newStatus: ChatMemberStatus by WeakField()

        constructor()

        constructor(userId: Int, oldStatus: ChatMemberStatus, newStatus: ChatMemberStatus) {

            this.userId = userId
            this.oldStatus = oldStatus
            this.newStatus = newStatus

        }

        override val constructor: Int @BsonIgnore get() = 1887176186

    }


    /**
     * A chat member was restricted/unrestricted or banned/unbanned, or the list of their restrictions has changed
     *
     * @userId - Chat member user identifier
     * @oldStatus - Previous status of the chat member
     * @newStatus - New status of the chat member
     */
    class ChatEventMemberRestricted : ChatEventAction {

        var userId: Int by WeakField()
        var oldStatus: ChatMemberStatus by WeakField()
        var newStatus: ChatMemberStatus by WeakField()

        constructor()

        constructor(userId: Int, oldStatus: ChatMemberStatus, newStatus: ChatMemberStatus) {

            this.userId = userId
            this.oldStatus = oldStatus
            this.newStatus = newStatus

        }

        override val constructor: Int @BsonIgnore get() = 584946294

    }


    /**
     * The chat title was changed
     *
     * @oldTitle - Previous chat title
     * @newTitle - New chat title
     */
    class ChatEventTitleChanged : ChatEventAction {

        var oldTitle: String by WeakField()
        var newTitle: String by WeakField()

        constructor()

        constructor(oldTitle: String, newTitle: String) {

            this.oldTitle = oldTitle
            this.newTitle = newTitle

        }

        override val constructor: Int @BsonIgnore get() = 1134103250

    }


    /**
     * The chat permissions was changed
     *
     * @oldPermissions - Previous chat permissions
     * @newPermissions - New chat permissions
     */
    class ChatEventPermissionsChanged : ChatEventAction {

        var oldPermissions: ChatPermissions by WeakField()
        var newPermissions: ChatPermissions by WeakField()

        constructor()

        constructor(oldPermissions: ChatPermissions, newPermissions: ChatPermissions) {

            this.oldPermissions = oldPermissions
            this.newPermissions = newPermissions

        }

        override val constructor: Int @BsonIgnore get() = -1311557720

    }


    /**
     * The chat description was changed
     *
     * @oldDescription - Previous chat description
     * @newDescription - New chat description
     */
    class ChatEventDescriptionChanged : ChatEventAction {

        var oldDescription: String by WeakField()
        var newDescription: String by WeakField()

        constructor()

        constructor(oldDescription: String, newDescription: String) {

            this.oldDescription = oldDescription
            this.newDescription = newDescription

        }

        override val constructor: Int @BsonIgnore get() = 39112478

    }


    /**
     * The chat username was changed
     *
     * @oldUsername - Previous chat username
     * @newUsername - New chat username
     */
    class ChatEventUsernameChanged : ChatEventAction {

        var oldUsername: String by WeakField()
        var newUsername: String by WeakField()

        constructor()

        constructor(oldUsername: String, newUsername: String) {

            this.oldUsername = oldUsername
            this.newUsername = newUsername

        }

        override val constructor: Int @BsonIgnore get() = 1728558443

    }


    /**
     * The chat photo was changed
     *
     * @oldPhoto - Previous chat photo value
     * @newPhoto - New chat photo value
     */
    class ChatEventPhotoChanged : ChatEventAction {

        var oldPhoto: Photo? = null
        var newPhoto: Photo? = null

        constructor()

        constructor(oldPhoto: Photo? = null, newPhoto: Photo? = null) {

            this.oldPhoto = oldPhoto
            this.newPhoto = newPhoto

        }

        override val constructor: Int @BsonIgnore get() = 1037662734

    }


    /**
     * The can_invite_users permission of a supergroup chat was toggled
     *
     * @canInviteUsers - New value of can_invite_users permission
     */
    class ChatEventInvitesToggled : ChatEventAction {

        var canInviteUsers: Boolean by WeakField()

        constructor()

        constructor(canInviteUsers: Boolean) {

            this.canInviteUsers = canInviteUsers

        }

        override val constructor: Int @BsonIgnore get() = -62548373

    }


    /**
     * The linked chat of a supergroup was changed
     *
     * @oldLinkedChatId - Previous supergroup linked chat identifier
     * @newLinkedChatId - New supergroup linked chat identifier
     */
    class ChatEventLinkedChatChanged : ChatEventAction {

        var oldLinkedChatId: Long by WeakField()
        var newLinkedChatId: Long by WeakField()

        constructor()

        constructor(oldLinkedChatId: Long, newLinkedChatId: Long) {

            this.oldLinkedChatId = oldLinkedChatId
            this.newLinkedChatId = newLinkedChatId

        }

        override val constructor: Int @BsonIgnore get() = 1797419439

    }


    /**
     * The slow_mode_delay setting of a supergroup was changed
     *
     * @oldSlowModeDelay - Previous value of slow_mode_delay
     * @newSlowModeDelay - New value of slow_mode_delay
     */
    class ChatEventSlowModeDelayChanged : ChatEventAction {

        var oldSlowModeDelay: Int by WeakField()
        var newSlowModeDelay: Int by WeakField()

        constructor()

        constructor(oldSlowModeDelay: Int, newSlowModeDelay: Int) {

            this.oldSlowModeDelay = oldSlowModeDelay
            this.newSlowModeDelay = newSlowModeDelay

        }

        override val constructor: Int @BsonIgnore get() = -1653195765

    }


    /**
     * The sign_messages setting of a channel was toggled
     *
     * @signMessages - New value of sign_messages
     */
    class ChatEventSignMessagesToggled : ChatEventAction {

        var signMessages: Boolean by WeakField()

        constructor()

        constructor(signMessages: Boolean) {

            this.signMessages = signMessages

        }

        override val constructor: Int @BsonIgnore get() = -1313265634

    }


    /**
     * The supergroup sticker set was changed
     *
     * @oldStickerSetId - Previous identifier of the chat sticker set
     *                    0 if none
     * @newStickerSetId - New identifier of the chat sticker set
     *                    0 if none
     */
    class ChatEventStickerSetChanged : ChatEventAction {

        var oldStickerSetId: Long by WeakField()
        var newStickerSetId: Long by WeakField()

        constructor()

        constructor(oldStickerSetId: Long, newStickerSetId: Long) {

            this.oldStickerSetId = oldStickerSetId
            this.newStickerSetId = newStickerSetId

        }

        override val constructor: Int @BsonIgnore get() = -1243130481

    }


    /**
     * The supergroup location was changed
     *
     * @oldLocation - Previous location
     * @newLocation - New location
     */
    class ChatEventLocationChanged : ChatEventAction {

        var oldLocation: ChatLocation? = null
        var newLocation: ChatLocation? = null

        constructor()

        constructor(oldLocation: ChatLocation? = null, newLocation: ChatLocation? = null) {

            this.oldLocation = oldLocation
            this.newLocation = newLocation

        }

        override val constructor: Int @BsonIgnore get() = -405930674

    }


    /**
     * The is_all_history_available setting of a supergroup was toggled
     *
     * @isAllHistoryAvailable - New value of is_all_history_available
     */
    class ChatEventIsAllHistoryAvailableToggled : ChatEventAction {

        var isAllHistoryAvailable: Boolean by WeakField()

        constructor()

        constructor(isAllHistoryAvailable: Boolean) {

            this.isAllHistoryAvailable = isAllHistoryAvailable

        }

        override val constructor: Int @BsonIgnore get() = -1599063019

    }


    /**
     * Represents a chat event
     *
     * @id - Chat event identifier
     * @date - Point in time (Unix timestamp) when the event happened
     * @userId - Identifier of the user who performed the action that triggered the event
     * @action - Action performed by the user
     */
    class ChatEvent : Object {

        var id: Long by WeakField()
        var date: Int by WeakField()
        var userId: Int by WeakField()
        var action: ChatEventAction by WeakField()

        constructor()

        constructor(id: Long, date: Int, userId: Int, action: ChatEventAction) {

            this.id = id
            this.date = date
            this.userId = userId
            this.action = action

        }

        override val constructor: Int @BsonIgnore get() = -609912404

    }


    /**
     * Contains a list of chat events
     *
     * @events - List of events
     */
    class ChatEvents : Object {

        var events: Array<ChatEvent> by WeakField()

        constructor()

        constructor(events: Array<ChatEvent>) {

            this.events = events

        }

        override val constructor: Int @BsonIgnore get() = -585329664

    }


    /**
     * Represents a set of filters used to obtain a chat event log
     *
     * @messageEdits - True, if message edits should be returned
     * @messageDeletions - True, if message deletions should be returned
     * @messagePins - True, if pin/unpin events should be returned
     * @memberJoins - True, if members joining events should be returned
     * @memberLeaves - True, if members leaving events should be returned
     * @memberInvites - True, if invited member events should be returned
     * @memberPromotions - True, if member promotion/demotion events should be returned
     * @memberRestrictions - True, if member restricted/unrestricted/banned/unbanned events should be returned
     * @infoChanges - True, if changes in chat information should be returned
     * @settingChanges - True, if changes in chat settings should be returned
     */
    class ChatEventLogFilters : Object {

        var messageEdits: Boolean by WeakField()
        var messageDeletions: Boolean by WeakField()
        var messagePins: Boolean by WeakField()
        var memberJoins: Boolean by WeakField()
        var memberLeaves: Boolean by WeakField()
        var memberInvites: Boolean by WeakField()
        var memberPromotions: Boolean by WeakField()
        var memberRestrictions: Boolean by WeakField()
        var infoChanges: Boolean by WeakField()
        var settingChanges: Boolean by WeakField()

        constructor()

        constructor(messageEdits: Boolean, messageDeletions: Boolean, messagePins: Boolean, memberJoins: Boolean, memberLeaves: Boolean, memberInvites: Boolean, memberPromotions: Boolean, memberRestrictions: Boolean, infoChanges: Boolean, settingChanges: Boolean) {

            this.messageEdits = messageEdits
            this.messageDeletions = messageDeletions
            this.messagePins = messagePins
            this.memberJoins = memberJoins
            this.memberLeaves = memberLeaves
            this.memberInvites = memberInvites
            this.memberPromotions = memberPromotions
            this.memberRestrictions = memberRestrictions
            this.infoChanges = infoChanges
            this.settingChanges = settingChanges

        }

        override val constructor: Int @BsonIgnore get() = 941939684

    }


    /**
     * Represents the value of a string in a language pack
     */
    abstract class LanguagePackStringValue : Object()

    /**
     * An ordinary language pack string
     *
     * @value - String value
     */
    class LanguagePackStringValueOrdinary : LanguagePackStringValue {

        var value: String by WeakField()

        constructor()

        constructor(value: String) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = -249256352

    }


    /**
     * A language pack string which has different forms based on the number of some object it mentions
     * See https://www.unicode.org/cldr/charts/latest/supplemental/language_plural_rules.html for more info
     *
     * @zeroValue - Value for zero objects
     * @oneValue - Value for one object
     * @twoValue - Value for two objects
     * @fewValue - Value for few objects
     * @manyValue - Value for many objects
     * @otherValue - Default value
     */
    class LanguagePackStringValuePluralized : LanguagePackStringValue {

        var zeroValue: String by WeakField()
        var oneValue: String by WeakField()
        var twoValue: String by WeakField()
        var fewValue: String by WeakField()
        var manyValue: String by WeakField()
        var otherValue: String by WeakField()

        constructor()

        constructor(zeroValue: String, oneValue: String, twoValue: String, fewValue: String, manyValue: String, otherValue: String) {

            this.zeroValue = zeroValue
            this.oneValue = oneValue
            this.twoValue = twoValue
            this.fewValue = fewValue
            this.manyValue = manyValue
            this.otherValue = otherValue

        }

        override val constructor: Int @BsonIgnore get() = 1906840261

    }


    /**
     * A deleted language pack string, the value should be taken from the built-in english language pack
     */
    class LanguagePackStringValueDeleted : LanguagePackStringValue() {

        override val constructor: Int @BsonIgnore get() = 1834792698

    }


    /**
     * Represents one language pack string
     *
     * @key - String key
     * @value - String value
     */
    class LanguagePackString : Object {

        var key: String by WeakField()
        var value: LanguagePackStringValue by WeakField()

        constructor()

        constructor(key: String, value: LanguagePackStringValue) {

            this.key = key
            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 1307632736

    }


    /**
     * Contains a list of language pack strings
     *
     * @strings - A list of language pack strings
     */
    class LanguagePackStrings : Object {

        var strings: Array<LanguagePackString> by WeakField()

        constructor()

        constructor(strings: Array<LanguagePackString>) {

            this.strings = strings

        }

        override val constructor: Int @BsonIgnore get() = 1172082922

    }


    /**
     * Contains information about a language pack
     *
     * @id - Unique language pack identifier
     * @baseLanguagePackId - Identifier of a base language pack
     *                       If a string is missed in the language pack, then it should be fetched from base language pack
     *                       Unsupported in custom language packs
     * @name - Language name
     * @nativeName - Name of the language in that language
     * @pluralCode - A language code to be used to apply plural forms
     *               See https://www.unicode.org/cldr/charts/latest/supplemental/language_plural_rules.html for more info
     * @isOfficial - True, if the language pack is official
     * @isRtl - True, if the language pack strings are RTL
     * @isBeta - True, if the language pack is a beta language pack
     * @isInstalled - True, if the language pack is installed by the current user
     * @totalStringCount - Total number of non-deleted strings from the language pack
     * @translatedStringCount - Total number of translated strings from the language pack
     * @localStringCount - Total number of non-deleted strings from the language pack available locally
     * @translationUrl - Link to language translation interface
     *                   Empty for custom local language packs
     */
    class LanguagePackInfo : Object {

        var id: String by WeakField()
        var baseLanguagePackId: String? = null
        var name: String by WeakField()
        var nativeName: String by WeakField()
        var pluralCode: String by WeakField()
        var isOfficial: Boolean by WeakField()
        var isRtl: Boolean by WeakField()
        var isBeta: Boolean by WeakField()
        var isInstalled: Boolean by WeakField()
        var totalStringCount: Int by WeakField()
        var translatedStringCount: Int by WeakField()
        var localStringCount: Int by WeakField()
        var translationUrl: String by WeakField()

        constructor()

        constructor(id: String, baseLanguagePackId: String? = null, name: String, nativeName: String, pluralCode: String, isOfficial: Boolean, isRtl: Boolean, isBeta: Boolean, isInstalled: Boolean, totalStringCount: Int, translatedStringCount: Int, localStringCount: Int, translationUrl: String) {

            this.id = id
            this.baseLanguagePackId = baseLanguagePackId
            this.name = name
            this.nativeName = nativeName
            this.pluralCode = pluralCode
            this.isOfficial = isOfficial
            this.isRtl = isRtl
            this.isBeta = isBeta
            this.isInstalled = isInstalled
            this.totalStringCount = totalStringCount
            this.translatedStringCount = translatedStringCount
            this.localStringCount = localStringCount
            this.translationUrl = translationUrl

        }

        override val constructor: Int @BsonIgnore get() = 542199642

    }


    /**
     * Contains information about the current localization target
     *
     * @languagePacks - List of available language packs for this application
     */
    class LocalizationTargetInfo : Object {

        var languagePacks: Array<LanguagePackInfo> by WeakField()

        constructor()

        constructor(languagePacks: Array<LanguagePackInfo>) {

            this.languagePacks = languagePacks

        }

        override val constructor: Int @BsonIgnore get() = -2048670809

    }


    /**
     * Represents a data needed to subscribe for push notifications through registerDevice method
     * To use specific push notification service, you must specify the correct application platform and upload valid server authentication data at https://my.telegram.org
     */
    abstract class DeviceToken : Object()

    /**
     * A token for Firebase Cloud Messaging
     *
     * @token - Device registration token
     *          May be empty to de-register a device
     * @encrypt - True, if push notifications should be additionally encrypted
     */
    class DeviceTokenFirebaseCloudMessaging : DeviceToken {

        var token: String? = null
        var encrypt: Boolean by WeakField()

        constructor()

        constructor(token: String? = null, encrypt: Boolean) {

            this.token = token
            this.encrypt = encrypt

        }

        override val constructor: Int @BsonIgnore get() = -797881849

    }


    /**
     * A token for Apple Push Notification service
     *
     * @deviceToken - Device token
     *                May be empty to de-register a device
     * @isAppSandbox - True, if App Sandbox is enabled
     */
    class DeviceTokenApplePush : DeviceToken {

        var deviceToken: String? = null
        var isAppSandbox: Boolean by WeakField()

        constructor()

        constructor(deviceToken: String? = null, isAppSandbox: Boolean) {

            this.deviceToken = deviceToken
            this.isAppSandbox = isAppSandbox

        }

        override val constructor: Int @BsonIgnore get() = 387541955

    }


    /**
     * A token for Apple Push Notification service VoIP notifications
     *
     * @deviceToken - Device token
     *                May be empty to de-register a device
     * @isAppSandbox - True, if App Sandbox is enabled
     * @encrypt - True, if push notifications should be additionally encrypted
     */
    class DeviceTokenApplePushVoIP : DeviceToken {

        var deviceToken: String? = null
        var isAppSandbox: Boolean by WeakField()
        var encrypt: Boolean by WeakField()

        constructor()

        constructor(deviceToken: String? = null, isAppSandbox: Boolean, encrypt: Boolean) {

            this.deviceToken = deviceToken
            this.isAppSandbox = isAppSandbox
            this.encrypt = encrypt

        }

        override val constructor: Int @BsonIgnore get() = 804275689

    }


    /**
     * A token for Windows Push Notification Services
     *
     * @accessToken - The access token that will be used to send notifications
     *                May be empty to de-register a device
     */
    class DeviceTokenWindowsPush : DeviceToken {

        var accessToken: String? = null

        constructor()

        constructor(accessToken: String? = null) {

            this.accessToken = accessToken

        }

        override val constructor: Int @BsonIgnore get() = -1410514289

    }


    /**
     * A token for Microsoft Push Notification Service
     *
     * @channelUri - Push notification channel URI
     *               May be empty to de-register a device
     */
    class DeviceTokenMicrosoftPush : DeviceToken {

        var channelUri: String? = null

        constructor()

        constructor(channelUri: String? = null) {

            this.channelUri = channelUri

        }

        override val constructor: Int @BsonIgnore get() = 1224269900

    }


    /**
     * A token for Microsoft Push Notification Service VoIP channel
     *
     * @channelUri - Push notification channel URI
     *               May be empty to de-register a device
     */
    class DeviceTokenMicrosoftPushVoIP : DeviceToken {

        var channelUri: String? = null

        constructor()

        constructor(channelUri: String? = null) {

            this.channelUri = channelUri

        }

        override val constructor: Int @BsonIgnore get() = -785603759

    }


    /**
     * A token for web Push API
     *
     * @endpoint - Absolute URL exposed by the push service where the application server can send push messages
     *             May be empty to de-register a device
     * @p256dhBase64url - Base64url-encoded P-256 elliptic curve Diffie-Hellman public key
     * @authBase64url - Base64url-encoded authentication secret
     */
    class DeviceTokenWebPush : DeviceToken {

        var endpoint: String? = null
        var p256dhBase64url: String by WeakField()
        var authBase64url: String by WeakField()

        constructor()

        constructor(endpoint: String? = null, p256dhBase64url: String, authBase64url: String) {

            this.endpoint = endpoint
            this.p256dhBase64url = p256dhBase64url
            this.authBase64url = authBase64url

        }

        override val constructor: Int @BsonIgnore get() = -1694507273

    }


    /**
     * A token for Simple Push API for Firefox OS
     *
     * @endpoint - Absolute URL exposed by the push service where the application server can send push messages
     *             May be empty to de-register a device
     */
    class DeviceTokenSimplePush : DeviceToken {

        var endpoint: String? = null

        constructor()

        constructor(endpoint: String? = null) {

            this.endpoint = endpoint

        }

        override val constructor: Int @BsonIgnore get() = 49584736

    }


    /**
     * A token for Ubuntu Push Client service
     *
     * @token - Token
     *          May be empty to de-register a device
     */
    class DeviceTokenUbuntuPush : DeviceToken {

        var token: String? = null

        constructor()

        constructor(token: String? = null) {

            this.token = token

        }

        override val constructor: Int @BsonIgnore get() = 1782320422

    }


    /**
     * A token for BlackBerry Push Service
     *
     * @token - Token
     *          May be empty to de-register a device
     */
    class DeviceTokenBlackBerryPush : DeviceToken {

        var token: String? = null

        constructor()

        constructor(token: String? = null) {

            this.token = token

        }

        override val constructor: Int @BsonIgnore get() = 1559167234

    }


    /**
     * A token for Tizen Push Service
     *
     * @regId - Push service registration identifier
     *          May be empty to de-register a device
     */
    class DeviceTokenTizenPush : DeviceToken {

        var regId: String? = null

        constructor()

        constructor(regId: String? = null) {

            this.regId = regId

        }

        override val constructor: Int @BsonIgnore get() = -1359947213

    }


    /**
     * Contains a globally unique push receiver identifier, which can be used to identify which account has received a push notification
     *
     * @id - The globally unique identifier of push notification subscription
     */
    class PushReceiverId : Object {

        var id: Long by WeakField()

        constructor()

        constructor(id: Long) {

            this.id = id

        }

        override val constructor: Int @BsonIgnore get() = 371056428

    }


    /**
     * Describes a fill of a background
     */
    abstract class BackgroundFill : Object()

    /**
     * Describes a solid fill of a background
     *
     * @color - A color of the background in the RGB24 format
     */
    class BackgroundFillSolid : BackgroundFill {

        var color: Int by WeakField()

        constructor()

        constructor(color: Int) {

            this.color = color

        }

        override val constructor: Int @BsonIgnore get() = 1010678813

    }


    /**
     * Describes a gradient fill of a background
     *
     * @topColor - A top color of the background in the RGB24 format
     * @bottomColor - A bottom color of the background in the RGB24 format
     * @rotationAngle - Clockwise rotation angle of the gradient, in degrees
     *                  Should be always divisible by 45
     */
    class BackgroundFillGradient : BackgroundFill {

        var topColor: Int by WeakField()
        var bottomColor: Int by WeakField()
        var rotationAngle: Int by WeakField()

        constructor()

        constructor(topColor: Int, bottomColor: Int, rotationAngle: Int) {

            this.topColor = topColor
            this.bottomColor = bottomColor
            this.rotationAngle = rotationAngle

        }

        override val constructor: Int @BsonIgnore get() = -1839206017

    }


    /**
     * Describes a type of a background
     */
    abstract class BackgroundType : Object()

    /**
     * A wallpaper in JPEG format
     *
     * @isBlurred - True, if the wallpaper must be downscaled to fit in 450x450 square and then box-blurred with radius 12
     * @isMoving - True, if the background needs to be slightly moved when device is tilted
     */
    class BackgroundTypeWallpaper : BackgroundType {

        var isBlurred: Boolean by WeakField()
        var isMoving: Boolean by WeakField()

        constructor()

        constructor(isBlurred: Boolean, isMoving: Boolean) {

            this.isBlurred = isBlurred
            this.isMoving = isMoving

        }

        override val constructor: Int @BsonIgnore get() = 1972128891

    }


    /**
     * A PNG or TGV (gzipped subset of SVG with MIME type "application/x-tgwallpattern") pattern to be combined with the background fill chosen by the user
     *
     * @fill - Description of the background fill
     * @intensity - Intensity of the pattern when it is shown above the filled background, 0-100
     * @isMoving - True, if the background needs to be slightly moved when device is tilted
     */
    class BackgroundTypePattern : BackgroundType {

        var fill: BackgroundFill by WeakField()
        var intensity: Int by WeakField()
        var isMoving: Boolean by WeakField()

        constructor()

        constructor(fill: BackgroundFill, intensity: Int, isMoving: Boolean) {

            this.fill = fill
            this.intensity = intensity
            this.isMoving = isMoving

        }

        override val constructor: Int @BsonIgnore get() = 649993914

    }


    /**
     * A filled background
     *
     * @fill - Description of the background fill
     */
    class BackgroundTypeFill : BackgroundType {

        var fill: BackgroundFill by WeakField()

        constructor()

        constructor(fill: BackgroundFill) {

            this.fill = fill

        }

        override val constructor: Int @BsonIgnore get() = 993008684

    }


    /**
     * Describes a chat background
     *
     * @id - Unique background identifier
     * @isDefault - True, if this is one of default backgrounds
     * @isDark - True, if the background is dark and is recommended to be used with dark theme
     * @name - Unique background name
     * @document - Document with the background
     *             Null only for filled backgrounds
     * @type - Type of the background
     */
    class Background : Object {

        var id: Long by WeakField()
        var isDefault: Boolean by WeakField()
        var isDark: Boolean by WeakField()
        var name: String by WeakField()
        var document: Document? = null
        var type: BackgroundType by WeakField()

        constructor()

        constructor(id: Long, isDefault: Boolean, isDark: Boolean, name: String, document: Document? = null, type: BackgroundType) {

            this.id = id
            this.isDefault = isDefault
            this.isDark = isDark
            this.name = name
            this.document = document
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -429971172

    }


    /**
     * Contains a list of backgrounds
     *
     * @backgrounds - A list of backgrounds
     */
    class Backgrounds : Object {

        var backgrounds: Array<Background> by WeakField()

        constructor()

        constructor(backgrounds: Array<Background>) {

            this.backgrounds = backgrounds

        }

        override val constructor: Int @BsonIgnore get() = 724728704

    }


    /**
     * Contains information about background to set
     */
    abstract class InputBackground : Object()

    /**
     * A background from a local file
     *
     * @background - Background file to use
     *               Only inputFileLocal and inputFileGenerated are supported
     *               The file nust be in JPEG format for wallpapers and in PNG format for patterns
     */
    class InputBackgroundLocal : InputBackground {

        var background: InputFile? = null

        constructor()

        constructor(background: InputFile? = null) {

            this.background = background

        }

        override val constructor: Int @BsonIgnore get() = -1747094364

    }


    /**
     * A background from the server
     *
     * @backgroundId - The background identifier
     */
    class InputBackgroundRemote : InputBackground {

        var backgroundId: Long? = null

        constructor()

        constructor(backgroundId: Long? = null) {

            this.backgroundId = backgroundId

        }

        override val constructor: Int @BsonIgnore get() = -274976231

    }


    /**
     * Contains a list of hashtags
     *
     * @hashtags - A list of hashtags
     */
    class Hashtags : Object {

        var hashtags: Array<String> by WeakField()

        constructor()

        constructor(hashtags: Array<String>) {

            this.hashtags = hashtags

        }

        override val constructor: Int @BsonIgnore get() = 676798885

    }


    /**
     * Represents result of checking whether the current session can be used to transfer a chat ownership to another user
     */
    abstract class CanTransferOwnershipResult : Object()

    /**
     * The session can be used
     */
    class CanTransferOwnershipResultOk : CanTransferOwnershipResult() {

        override val constructor: Int @BsonIgnore get() = -89881021

    }


    /**
     * The 2-step verification needs to be enabled first
     */
    class CanTransferOwnershipResultPasswordNeeded : CanTransferOwnershipResult() {

        override val constructor: Int @BsonIgnore get() = 1548372703

    }


    /**
     * The 2-step verification was enabled recently, user needs to wait
     *
     * @retryAfter - Time left before the session can be used to transfer ownership of a chat, in seconds
     */
    class CanTransferOwnershipResultPasswordTooFresh : CanTransferOwnershipResult {

        var retryAfter: Int by WeakField()

        constructor()

        constructor(retryAfter: Int) {

            this.retryAfter = retryAfter

        }

        override val constructor: Int @BsonIgnore get() = 811440913

    }


    /**
     * The session was created recently, user needs to wait
     *
     * @retryAfter - Time left before the session can be used to transfer ownership of a chat, in seconds
     */
    class CanTransferOwnershipResultSessionTooFresh : CanTransferOwnershipResult {

        var retryAfter: Int by WeakField()

        constructor()

        constructor(retryAfter: Int) {

            this.retryAfter = retryAfter

        }

        override val constructor: Int @BsonIgnore get() = 984664289

    }


    /**
     * Represents result of checking whether a username can be set for a chat
     */
    abstract class CheckChatUsernameResult : Object()

    /**
     * The username can be set
     */
    class CheckChatUsernameResultOk : CheckChatUsernameResult() {

        override val constructor: Int @BsonIgnore get() = -1498956964

    }


    /**
     * The username is invalid
     */
    class CheckChatUsernameResultUsernameInvalid : CheckChatUsernameResult() {

        override val constructor: Int @BsonIgnore get() = -636979370

    }


    /**
     * The username is occupied
     */
    class CheckChatUsernameResultUsernameOccupied : CheckChatUsernameResult() {

        override val constructor: Int @BsonIgnore get() = 1320892201

    }


    /**
     * The user has too much chats with username, one of them should be made private first
     */
    class CheckChatUsernameResultPublicChatsTooMuch : CheckChatUsernameResult() {

        override val constructor: Int @BsonIgnore get() = 858247741

    }


    /**
     * The user can't be a member of a public supergroup
     */
    class CheckChatUsernameResultPublicGroupsUnavailable : CheckChatUsernameResult() {

        override val constructor: Int @BsonIgnore get() = -51833641

    }


    /**
     * Contains content of a push message notification
     */
    abstract class PushMessageContent : Object()

    /**
     * A general message with hidden content
     *
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentHidden : PushMessageContent {

        var isPinned: Boolean by WeakField()

        constructor()

        constructor(isPinned: Boolean) {

            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = -316950436

    }


    /**
     * An animation message (GIF-style).
     *
     * @animation - Message content
     * @caption - Animation caption
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentAnimation : PushMessageContent {

        var animation: Animation? = null
        var caption: String by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(animation: Animation? = null, caption: String, isPinned: Boolean) {

            this.animation = animation
            this.caption = caption
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = 1034215396

    }


    /**
     * An audio message
     *
     * @audio - Message content
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentAudio : PushMessageContent {

        var audio: Audio? = null
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(audio: Audio? = null, isPinned: Boolean) {

            this.audio = audio
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = 381581426

    }


    /**
     * A message with a user contact
     *
     * @name - Contact's name
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentContact : PushMessageContent {

        var name: String by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(name: String, isPinned: Boolean) {

            this.name = name
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = -12219820

    }


    /**
     * A contact has registered with Telegram
     */
    class PushMessageContentContactRegistered : PushMessageContent() {

        override val constructor: Int @BsonIgnore get() = -303962720

    }


    /**
     * A document message (a general file)
     *
     * @document - Message content
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentDocument : PushMessageContent {

        var document: Document? = null
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(document: Document? = null, isPinned: Boolean) {

            this.document = document
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = -458379775

    }


    /**
     * A message with a game
     *
     * @title - Game title, empty for pinned game message
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentGame : PushMessageContent {

        var title: String by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(title: String, isPinned: Boolean) {

            this.title = title
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = -515131109

    }


    /**
     * A new high score was achieved in a game
     *
     * @title - Game title, empty for pinned message
     * @score - New score, 0 for pinned message
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentGameScore : PushMessageContent {

        var title: String by WeakField()
        var score: Int by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(title: String, score: Int, isPinned: Boolean) {

            this.title = title
            this.score = score
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = 901303688

    }


    /**
     * A message with an invoice from a bot
     *
     * @price - Product price
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentInvoice : PushMessageContent {

        var price: String by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(price: String, isPinned: Boolean) {

            this.price = price
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = -1731687492

    }


    /**
     * A message with a location
     *
     * @isLive - True, if the location is live
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentLocation : PushMessageContent {

        var isLive: Boolean by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(isLive: Boolean, isPinned: Boolean) {

            this.isLive = isLive
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = -1288005709

    }


    /**
     * A photo message
     *
     * @photo - Message content
     * @caption - Photo caption
     * @isSecret - True, if the photo is secret
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentPhoto : PushMessageContent {

        var photo: Photo? = null
        var caption: String by WeakField()
        var isSecret: Boolean by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(photo: Photo? = null, caption: String, isSecret: Boolean, isPinned: Boolean) {

            this.photo = photo
            this.caption = caption
            this.isSecret = isSecret
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = 140631122

    }


    /**
     * A message with a poll
     *
     * @question - Poll question
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentPoll : PushMessageContent {

        var question: String by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(question: String, isPinned: Boolean) {

            this.question = question
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = -1545438580

    }


    /**
     * A screenshot of a message in the chat has been taken
     */
    class PushMessageContentScreenshotTaken : PushMessageContent() {

        override val constructor: Int @BsonIgnore get() = 214245369

    }


    /**
     * A message with a sticker
     *
     * @sticker - Message content
     * @emoji - Emoji corresponding to the sticker
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentSticker : PushMessageContent {

        var sticker: Sticker? = null
        var emoji: String? = null
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(sticker: Sticker? = null, emoji: String? = null, isPinned: Boolean) {

            this.sticker = sticker
            this.emoji = emoji
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = 1553513939

    }


    /**
     * A text message
     *
     * @text - Message text
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentText : PushMessageContent {

        var text: String by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(text: String, isPinned: Boolean) {

            this.text = text
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = 274587305

    }


    /**
     * A video message
     *
     * @video - Message content
     * @caption - Video caption
     * @isSecret - True, if the video is secret
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentVideo : PushMessageContent {

        var video: Video? = null
        var caption: String by WeakField()
        var isSecret: Boolean by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(video: Video? = null, caption: String, isSecret: Boolean, isPinned: Boolean) {

            this.video = video
            this.caption = caption
            this.isSecret = isSecret
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = 310038831

    }


    /**
     * A video note message
     *
     * @videoNote - Message content
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentVideoNote : PushMessageContent {

        var videoNote: VideoNote? = null
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(videoNote: VideoNote? = null, isPinned: Boolean) {

            this.videoNote = videoNote
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = -1122764417

    }


    /**
     * A voice note message
     *
     * @voiceNote - Message content
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    class PushMessageContentVoiceNote : PushMessageContent {

        var voiceNote: VoiceNote? = null
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(voiceNote: VoiceNote? = null, isPinned: Boolean) {

            this.voiceNote = voiceNote
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = 88910987

    }


    /**
     * A newly created basic group
     */
    class PushMessageContentBasicGroupChatCreate : PushMessageContent() {

        override val constructor: Int @BsonIgnore get() = -2114855172

    }


    /**
     * New chat members were invited to a group
     *
     * @memberName - Name of the added member
     * @isCurrentUser - True, if the current user was added to the group
     * @isReturned - True, if the user has returned to the group themself
     */
    class PushMessageContentChatAddMembers : PushMessageContent {

        var memberName: String by WeakField()
        var isCurrentUser: Boolean by WeakField()
        var isReturned: Boolean by WeakField()

        constructor()

        constructor(memberName: String, isCurrentUser: Boolean, isReturned: Boolean) {

            this.memberName = memberName
            this.isCurrentUser = isCurrentUser
            this.isReturned = isReturned

        }

        override val constructor: Int @BsonIgnore get() = -1087145158

    }


    /**
     * A chat photo was edited
     */
    class PushMessageContentChatChangePhoto : PushMessageContent() {

        override val constructor: Int @BsonIgnore get() = -1114222051

    }


    /**
     * A chat title was edited
     *
     * @title - New chat title
     */
    class PushMessageContentChatChangeTitle : PushMessageContent {

        var title: String by WeakField()

        constructor()

        constructor(title: String) {

            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = -1964902749

    }


    /**
     * A chat member was deleted
     *
     * @memberName - Name of the deleted member
     * @isCurrentUser - True, if the current user was deleted from the group
     * @isLeft - True, if the user has left the group themself
     */
    class PushMessageContentChatDeleteMember : PushMessageContent {

        var memberName: String by WeakField()
        var isCurrentUser: Boolean by WeakField()
        var isLeft: Boolean by WeakField()

        constructor()

        constructor(memberName: String, isCurrentUser: Boolean, isLeft: Boolean) {

            this.memberName = memberName
            this.isCurrentUser = isCurrentUser
            this.isLeft = isLeft

        }

        override val constructor: Int @BsonIgnore get() = 598714783

    }


    /**
     * A new member joined the chat by invite link
     */
    class PushMessageContentChatJoinByLink : PushMessageContent() {

        override val constructor: Int @BsonIgnore get() = 1553719113

    }


    /**
     * A forwarded messages
     *
     * @totalCount - Number of forwarded messages
     */
    class PushMessageContentMessageForwards : PushMessageContent {

        var totalCount: Int by WeakField()

        constructor()

        constructor(totalCount: Int) {

            this.totalCount = totalCount

        }

        override val constructor: Int @BsonIgnore get() = -1913083876

    }


    /**
     * A media album
     *
     * @totalCount - Number of messages in the album
     * @hasPhotos - True, if the album has at least one photo
     * @hasVideos - True, if the album has at least one video
     */
    class PushMessageContentMediaAlbum : PushMessageContent {

        var totalCount: Int by WeakField()
        var hasPhotos: Boolean by WeakField()
        var hasVideos: Boolean by WeakField()

        constructor()

        constructor(totalCount: Int, hasPhotos: Boolean, hasVideos: Boolean) {

            this.totalCount = totalCount
            this.hasPhotos = hasPhotos
            this.hasVideos = hasVideos

        }

        override val constructor: Int @BsonIgnore get() = -874278109

    }


    /**
     * Contains detailed information about a notification
     */
    abstract class NotificationType : Object()

    /**
     * New message was received
     *
     * @message - The message
     */
    class NotificationTypeNewMessage : NotificationType {

        var message: Message by WeakField()

        constructor()

        constructor(message: Message) {

            this.message = message

        }

        override val constructor: Int @BsonIgnore get() = 1885935159

    }


    /**
     * New secret chat was created
     */
    class NotificationTypeNewSecretChat : NotificationType() {

        override val constructor: Int @BsonIgnore get() = 1198638768

    }


    /**
     * New call was received
     *
     * @callId - Call identifier
     */
    class NotificationTypeNewCall : NotificationType {

        var callId: Int by WeakField()

        constructor()

        constructor(callId: Int) {

            this.callId = callId

        }

        override val constructor: Int @BsonIgnore get() = 1712734585

    }


    /**
     * New message was received through a push notification
     *
     * @messageId - The message identifier
     *              The message will not be available in the chat history, but the ID can be used in viewMessages and as reply_to_message_id
     * @senderUserId - Sender of the message
     *                 Corresponding user may be inaccessible
     * @content - Push message content
     */
    class NotificationTypeNewPushMessage : NotificationType {

        var messageId: Long by WeakField()
        var senderUserId: Int by WeakField()
        var content: PushMessageContent by WeakField()

        constructor()

        constructor(messageId: Long, senderUserId: Int, content: PushMessageContent) {

            this.messageId = messageId
            this.senderUserId = senderUserId
            this.content = content

        }

        override val constructor: Int @BsonIgnore get() = 1167232404

    }


    /**
     * Describes type of notifications in the group
     */
    abstract class NotificationGroupType : Object()

    /**
     * A group containing notifications of type notificationTypeNewMessage and notificationTypeNewPushMessage with ordinary unread messages
     */
    class NotificationGroupTypeMessages : NotificationGroupType() {

        override val constructor: Int @BsonIgnore get() = -1702481123

    }


    /**
     * A group containing notifications of type notificationTypeNewMessage and notificationTypeNewPushMessage with unread mentions of the current user, replies to their messages, or a pinned message
     */
    class NotificationGroupTypeMentions : NotificationGroupType() {

        override val constructor: Int @BsonIgnore get() = -2050324051

    }


    /**
     * A group containing a notification of type notificationTypeNewSecretChat
     */
    class NotificationGroupTypeSecretChat : NotificationGroupType() {

        override val constructor: Int @BsonIgnore get() = 1390759476

    }


    /**
     * A group containing notifications of type notificationTypeNewCall
     */
    class NotificationGroupTypeCalls : NotificationGroupType() {

        override val constructor: Int @BsonIgnore get() = 1379123538

    }


    /**
     * Contains information about a notification
     *
     * @id - Unique persistent identifier of this notification
     * @date - Notification date
     * @isSilent - True, if the notification was initially silent
     * @type - Notification type
     */
    class Notification : Object {

        var id: Int by WeakField()
        var date: Int by WeakField()
        var isSilent: Boolean by WeakField()
        var type: NotificationType by WeakField()

        constructor()

        constructor(id: Int, date: Int, isSilent: Boolean, type: NotificationType) {

            this.id = id
            this.date = date
            this.isSilent = isSilent
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = 788743120

    }


    /**
     * Describes a group of notifications
     *
     * @id - Unique persistent auto-incremented from 1 identifier of the notification group
     * @type - Type of the group
     * @chatId - Identifier of a chat to which all notifications in the group belong
     * @totalCount - Total number of active notifications in the group
     * @notifications - The list of active notifications
     */
    class NotificationGroup : Object {

        var id: Int by WeakField()
        var type: NotificationGroupType by WeakField()
        var chatId: Long by WeakField()
        var totalCount: Int by WeakField()
        var notifications: Array<Notification> by WeakField()

        constructor()

        constructor(id: Int, type: NotificationGroupType, chatId: Long, totalCount: Int, notifications: Array<Notification>) {

            this.id = id
            this.type = type
            this.chatId = chatId
            this.totalCount = totalCount
            this.notifications = notifications

        }

        override val constructor: Int @BsonIgnore get() = 780691541

    }


    /**
     * Represents the value of an option
     */
    abstract class OptionValue : Object()

    /**
     * Represents a boolean option
     *
     * @value - The value of the option
     */
    class OptionValueBoolean : OptionValue {

        var value: Boolean by WeakField()

        constructor()

        constructor(value: Boolean) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 63135518

    }


    /**
     * Represents an unknown option or an option which has a default value
     */
    class OptionValueEmpty : OptionValue() {

        override val constructor: Int @BsonIgnore get() = 918955155

    }


    /**
     * Represents an integer option
     *
     * @value - The value of the option
     */
    class OptionValueInteger : OptionValue {

        var value: Int by WeakField()

        constructor()

        constructor(value: Int) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = -1400911104

    }


    /**
     * Represents a string option
     *
     * @value - The value of the option
     */
    class OptionValueString : OptionValue {

        var value: String by WeakField()

        constructor()

        constructor(value: String) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 756248212

    }


    /**
     * Represents one member of a JSON object
     *
     * @key - Member's key
     * @value - Member's value
     */
    class JsonObjectMember : Object {

        var key: String by WeakField()
        var value: JsonValue by WeakField()

        constructor()

        constructor(key: String, value: JsonValue) {

            this.key = key
            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = -1803309418

    }


    /**
     * Represents a JSON value
     */
    abstract class JsonValue : Object()

    /**
     * Represents a null JSON value
     */
    class JsonValueNull : JsonValue() {

        override val constructor: Int @BsonIgnore get() = -92872499

    }


    /**
     * Represents a boolean JSON value
     *
     * @value - The value
     */
    class JsonValueBoolean : JsonValue {

        var value: Boolean by WeakField()

        constructor()

        constructor(value: Boolean) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = -2142186576

    }


    /**
     * Represents a numeric JSON value
     *
     * @value - The value
     */
    class JsonValueNumber : JsonValue {

        var value: Double by WeakField()

        constructor()

        constructor(value: Double) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = -1010822033

    }


    /**
     * Represents a string JSON value
     *
     * @value - The value
     */
    class JsonValueString : JsonValue {

        var value: String by WeakField()

        constructor()

        constructor(value: String) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 1597947313

    }


    /**
     * Represents a JSON array
     *
     * @values - The list of array elements
     */
    class JsonValueArray : JsonValue {

        var values: Array<JsonValue> by WeakField()

        constructor()

        constructor(values: Array<JsonValue>) {

            this.values = values

        }

        override val constructor: Int @BsonIgnore get() = -183913546

    }


    /**
     * Represents a JSON object
     *
     * @members - The list of object members
     */
    class JsonValueObject : JsonValue {

        var members: Array<JsonObjectMember> by WeakField()

        constructor()

        constructor(members: Array<JsonObjectMember>) {

            this.members = members

        }

        override val constructor: Int @BsonIgnore get() = 520252026

    }


    /**
     * Represents a single rule for managing privacy settings
     */
    abstract class UserPrivacySettingRule : Object()

    /**
     * A rule to allow all users to do something
     */
    class UserPrivacySettingRuleAllowAll : UserPrivacySettingRule() {

        override val constructor: Int @BsonIgnore get() = -1967186881

    }


    /**
     * A rule to allow all of a user's contacts to do something
     */
    class UserPrivacySettingRuleAllowContacts : UserPrivacySettingRule() {

        override val constructor: Int @BsonIgnore get() = -1892733680

    }


    /**
     * A rule to allow certain specified users to do something
     *
     * @userIds - The user identifiers, total number of users in all rules must not exceed 1000
     */
    class UserPrivacySettingRuleAllowUsers : UserPrivacySettingRule {

        var userIds: IntArray by WeakField()

        constructor()

        constructor(userIds: IntArray) {

            this.userIds = userIds

        }

        override val constructor: Int @BsonIgnore get() = 427601278

    }


    /**
     * A rule to allow all members of certain specified basic groups and supergroups to doing something
     *
     * @chatIds - The chat identifiers, total number of chats in all rules must not exceed 20
     */
    class UserPrivacySettingRuleAllowChatMembers : UserPrivacySettingRule {

        var chatIds: LongArray by WeakField()

        constructor()

        constructor(chatIds: LongArray) {

            this.chatIds = chatIds

        }

        override val constructor: Int @BsonIgnore get() = -2048749863

    }


    /**
     * A rule to restrict all users from doing something
     */
    class UserPrivacySettingRuleRestrictAll : UserPrivacySettingRule() {

        override val constructor: Int @BsonIgnore get() = -1406495408

    }


    /**
     * A rule to restrict all contacts of a user from doing something
     */
    class UserPrivacySettingRuleRestrictContacts : UserPrivacySettingRule() {

        override val constructor: Int @BsonIgnore get() = 1008389378

    }


    /**
     * A rule to restrict all specified users from doing something
     *
     * @userIds - The user identifiers, total number of users in all rules must not exceed 1000
     */
    class UserPrivacySettingRuleRestrictUsers : UserPrivacySettingRule {

        var userIds: IntArray by WeakField()

        constructor()

        constructor(userIds: IntArray) {

            this.userIds = userIds

        }

        override val constructor: Int @BsonIgnore get() = 2119951802

    }


    /**
     * A rule to restrict all members of specified basic groups and supergroups from doing something
     *
     * @chatIds - The chat identifiers, total number of chats in all rules must not exceed 20
     */
    class UserPrivacySettingRuleRestrictChatMembers : UserPrivacySettingRule {

        var chatIds: LongArray by WeakField()

        constructor()

        constructor(chatIds: LongArray) {

            this.chatIds = chatIds

        }

        override val constructor: Int @BsonIgnore get() = 392530897

    }


    /**
     * A list of privacy rules
     * Rules are matched in the specified order
     * The first matched rule defines the privacy setting for a given user
     * If no rule matches, the action is not allowed
     *
     * @rules - A list of rules
     */
    class UserPrivacySettingRules : Object {

        var rules: Array<UserPrivacySettingRule> by WeakField()

        constructor()

        constructor(rules: Array<UserPrivacySettingRule>) {

            this.rules = rules

        }

        override val constructor: Int @BsonIgnore get() = 322477541

    }


    /**
     * Describes available user privacy settings
     */
    abstract class UserPrivacySetting : Object()

    /**
     * A privacy setting for managing whether the user's online status is visible
     */
    class UserPrivacySettingShowStatus : UserPrivacySetting() {

        override val constructor: Int @BsonIgnore get() = 1862829310

    }


    /**
     * A privacy setting for managing whether the user's profile photo is visible
     */
    class UserPrivacySettingShowProfilePhoto : UserPrivacySetting() {

        override val constructor: Int @BsonIgnore get() = 1408485877

    }


    /**
     * A privacy setting for managing whether a link to the user's account is included in forwarded messages
     */
    class UserPrivacySettingShowLinkInForwardedMessages : UserPrivacySetting() {

        override val constructor: Int @BsonIgnore get() = 592688870

    }


    /**
     * A privacy setting for managing whether the user's phone number is visible
     */
    class UserPrivacySettingShowPhoneNumber : UserPrivacySetting() {

        override val constructor: Int @BsonIgnore get() = -791567831

    }


    /**
     * A privacy setting for managing whether the user can be invited to chats
     */
    class UserPrivacySettingAllowChatInvites : UserPrivacySetting() {

        override val constructor: Int @BsonIgnore get() = 1271668007

    }


    /**
     * A privacy setting for managing whether the user can be called
     */
    class UserPrivacySettingAllowCalls : UserPrivacySetting() {

        override val constructor: Int @BsonIgnore get() = -906967291

    }


    /**
     * A privacy setting for managing whether peer-to-peer connections can be used for calls
     */
    class UserPrivacySettingAllowPeerToPeerCalls : UserPrivacySetting() {

        override val constructor: Int @BsonIgnore get() = 352500032

    }


    /**
     * A privacy setting for managing whether the user can be found by their phone number
     * Checked only if the phone number is not known to the other user
     * Can be set only to "Allow contacts" or "Allow all"
     */
    class UserPrivacySettingAllowFindingByPhoneNumber : UserPrivacySetting() {

        override val constructor: Int @BsonIgnore get() = -1846645423

    }


    /**
     * Contains information about the period of inactivity after which the current user's account will automatically be deleted
     *
     * @days - Number of days of inactivity before the account will be flagged for deletion
     *         Should range from 30-366 days
     */
    class AccountTtl : Object {

        var days: Int by WeakField()

        constructor()

        constructor(days: Int) {

            this.days = days

        }

        override val constructor: Int @BsonIgnore get() = 1324495492

    }


    /**
     * Contains information about one session in a Telegram application used by the current user
     * Sessions should be shown to the user in the returned order
     *
     * @id - Session identifier
     * @isCurrent - True, if this session is the current session
     * @isPasswordPending - True, if a password is needed to complete authorization of the session
     * @apiId - Telegram API identifier, as provided by the application
     * @applicationName - Name of the application, as provided by the application
     * @applicationVersion - The version of the application, as provided by the application
     * @isOfficialApplication - True, if the application is an official application or uses the api_id of an official application
     * @deviceModel - Model of the device the application has been run or is running on, as provided by the application
     * @platform - Operating system the application has been run or is running on, as provided by the application
     * @systemVersion - Version of the operating system the application has been run or is running on, as provided by the application
     * @logInDate - Point in time (Unix timestamp) when the user has logged in
     * @lastActiveDate - Point in time (Unix timestamp) when the session was last used
     * @ip - IP address from which the session was created, in human-readable format
     * @country - A two-letter country code for the country from which the session was created, based on the IP address
     * @region - Region code from which the session was created, based on the IP address
     */
    class Session : Object {

        var id: Long by WeakField()
        var isCurrent: Boolean by WeakField()
        var isPasswordPending: Boolean by WeakField()
        var apiId: Int by WeakField()
        var applicationName: String by WeakField()
        var applicationVersion: String by WeakField()
        var isOfficialApplication: Boolean by WeakField()
        var deviceModel: String by WeakField()
        var platform: String by WeakField()
        var systemVersion: String by WeakField()
        var logInDate: Int by WeakField()
        var lastActiveDate: Int by WeakField()
        var ip: String by WeakField()
        var country: String by WeakField()
        var region: String by WeakField()

        constructor()

        constructor(id: Long, isCurrent: Boolean, isPasswordPending: Boolean, apiId: Int, applicationName: String, applicationVersion: String, isOfficialApplication: Boolean, deviceModel: String, platform: String, systemVersion: String, logInDate: Int, lastActiveDate: Int, ip: String, country: String, region: String) {

            this.id = id
            this.isCurrent = isCurrent
            this.isPasswordPending = isPasswordPending
            this.apiId = apiId
            this.applicationName = applicationName
            this.applicationVersion = applicationVersion
            this.isOfficialApplication = isOfficialApplication
            this.deviceModel = deviceModel
            this.platform = platform
            this.systemVersion = systemVersion
            this.logInDate = logInDate
            this.lastActiveDate = lastActiveDate
            this.ip = ip
            this.country = country
            this.region = region

        }

        override val constructor: Int @BsonIgnore get() = 1920553176

    }


    /**
     * Contains a list of sessions
     *
     * @sessions - List of sessions
     */
    class Sessions : Object {

        var sessions: Array<Session> by WeakField()

        constructor()

        constructor(sessions: Array<Session>) {

            this.sessions = sessions

        }

        override val constructor: Int @BsonIgnore get() = -463118121

    }


    /**
     * Contains information about one website the current user is logged in with Telegram
     *
     * @id - Website identifier
     * @domainName - The domain name of the website
     * @botUserId - User identifier of a bot linked with the website
     * @browser - The version of a browser used to log in
     * @platform - Operating system the browser is running on
     * @logInDate - Point in time (Unix timestamp) when the user was logged in
     * @lastActiveDate - Point in time (Unix timestamp) when obtained authorization was last used
     * @ip - IP address from which the user was logged in, in human-readable format
     * @location - Human-readable description of a country and a region, from which the user was logged in, based on the IP address
     */
    class ConnectedWebsite : Object {

        var id: Long by WeakField()
        var domainName: String by WeakField()
        var botUserId: Int by WeakField()
        var browser: String by WeakField()
        var platform: String by WeakField()
        var logInDate: Int by WeakField()
        var lastActiveDate: Int by WeakField()
        var ip: String by WeakField()
        var location: String by WeakField()

        constructor()

        constructor(id: Long, domainName: String, botUserId: Int, browser: String, platform: String, logInDate: Int, lastActiveDate: Int, ip: String, location: String) {

            this.id = id
            this.domainName = domainName
            this.botUserId = botUserId
            this.browser = browser
            this.platform = platform
            this.logInDate = logInDate
            this.lastActiveDate = lastActiveDate
            this.ip = ip
            this.location = location

        }

        override val constructor: Int @BsonIgnore get() = -1538986855

    }


    /**
     * Contains a list of websites the current user is logged in with Telegram
     *
     * @websites - List of connected websites
     */
    class ConnectedWebsites : Object {

        var websites: Array<ConnectedWebsite> by WeakField()

        constructor()

        constructor(websites: Array<ConnectedWebsite>) {

            this.websites = websites

        }

        override val constructor: Int @BsonIgnore get() = -1727949694

    }


    /**
     * Describes the reason why a chat is reported
     */
    abstract class ChatReportReason : Object()

    /**
     * The chat contains spam messages
     */
    class ChatReportReasonSpam : ChatReportReason() {

        override val constructor: Int @BsonIgnore get() = -510848863

    }


    /**
     * The chat promotes violence
     */
    class ChatReportReasonViolence : ChatReportReason() {

        override val constructor: Int @BsonIgnore get() = -1330235395

    }


    /**
     * The chat contains pornographic messages
     */
    class ChatReportReasonPornography : ChatReportReason() {

        override val constructor: Int @BsonIgnore get() = 722614385

    }


    /**
     * The chat has child abuse related content
     */
    class ChatReportReasonChildAbuse : ChatReportReason() {

        override val constructor: Int @BsonIgnore get() = -1070686531

    }


    /**
     * The chat contains copyrighted content
     */
    class ChatReportReasonCopyright : ChatReportReason() {

        override val constructor: Int @BsonIgnore get() = 986898080

    }


    /**
     * The location-based chat is unrelated to its stated location
     */
    class ChatReportReasonUnrelatedLocation : ChatReportReason() {

        override val constructor: Int @BsonIgnore get() = 2632403

    }


    /**
     * A custom reason provided by the user
     *
     * @text - Report text
     */
    class ChatReportReasonCustom : ChatReportReason {

        var text: String by WeakField()

        constructor()

        constructor(text: String) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 544575454

    }


    /**
     * Contains a public HTTPS link to a message in a supergroup or channel with a username
     *
     * @link - Message link
     * @html - HTML-code for embedding the message
     */
    class PublicMessageLink : Object {

        var link: String by WeakField()
        var html: String by WeakField()

        constructor()

        constructor(link: String, html: String) {

            this.link = link
            this.html = html

        }

        override val constructor: Int @BsonIgnore get() = -679603433

    }


    /**
     * Contains information about a link to a message in a chat
     *
     * @isPublic - True, if the link is a public link for a message in a chat
     * @chatId - If found, identifier of the chat to which the message belongs, 0 otherwise
     * @message - If found, the linked message
     * @forAlbum - True, if the whole media album to which the message belongs is linked
     */
    class MessageLinkInfo : Object {

        var isPublic: Boolean by WeakField()
        var chatId: Long by WeakField()
        var message: Message? = null
        var forAlbum: Boolean by WeakField()

        constructor()

        constructor(isPublic: Boolean, chatId: Long, message: Message? = null, forAlbum: Boolean) {

            this.isPublic = isPublic
            this.chatId = chatId
            this.message = message
            this.forAlbum = forAlbum

        }

        override val constructor: Int @BsonIgnore get() = 657372995

    }


    /**
     * Contains a part of a file
     *
     * @data - File bytes
     */
    class FilePart : Object {

        var data: ByteArray by WeakField()

        constructor()

        constructor(data: ByteArray) {

            this.data = data

        }

        override val constructor: Int @BsonIgnore get() = 911821878

    }


    /**
     * Represents the type of a file
     */
    abstract class FileType : Object()

    /**
     * The data is not a file
     */
    class FileTypeNone : FileType() {

        override val constructor: Int @BsonIgnore get() = 2003009189

    }


    /**
     * The file is an animation
     */
    class FileTypeAnimation : FileType() {

        override val constructor: Int @BsonIgnore get() = -290816582

    }


    /**
     * The file is an audio file
     */
    class FileTypeAudio : FileType() {

        override val constructor: Int @BsonIgnore get() = -709112160

    }


    /**
     * The file is a document
     */
    class FileTypeDocument : FileType() {

        override val constructor: Int @BsonIgnore get() = -564722929

    }


    /**
     * The file is a photo
     */
    class FileTypePhoto : FileType() {

        override val constructor: Int @BsonIgnore get() = -1718914651

    }


    /**
     * The file is a profile photo
     */
    class FileTypeProfilePhoto : FileType() {

        override val constructor: Int @BsonIgnore get() = 1795089315

    }


    /**
     * The file was sent to a secret chat (the file type is not known to the server)
     */
    class FileTypeSecret : FileType() {

        override val constructor: Int @BsonIgnore get() = -1871899401

    }


    /**
     * The file is a thumbnail of a file from a secret chat
     */
    class FileTypeSecretThumbnail : FileType() {

        override val constructor: Int @BsonIgnore get() = -1401326026

    }


    /**
     * The file is a file from Secure storage used for storing Telegram Passport files
     */
    class FileTypeSecure : FileType() {

        override val constructor: Int @BsonIgnore get() = -1419133146

    }


    /**
     * The file is a sticker
     */
    class FileTypeSticker : FileType() {

        override val constructor: Int @BsonIgnore get() = 475233385

    }


    /**
     * The file is a thumbnail of another file
     */
    class FileTypeThumbnail : FileType() {

        override val constructor: Int @BsonIgnore get() = -12443298

    }


    /**
     * The file type is not yet known
     */
    class FileTypeUnknown : FileType() {

        override val constructor: Int @BsonIgnore get() = -2011566768

    }


    /**
     * The file is a video
     */
    class FileTypeVideo : FileType() {

        override val constructor: Int @BsonIgnore get() = 1430816539

    }


    /**
     * The file is a video note
     */
    class FileTypeVideoNote : FileType() {

        override val constructor: Int @BsonIgnore get() = -518412385

    }


    /**
     * The file is a voice note
     */
    class FileTypeVoiceNote : FileType() {

        override val constructor: Int @BsonIgnore get() = -588681661

    }


    /**
     * The file is a wallpaper or a background pattern
     */
    class FileTypeWallpaper : FileType() {

        override val constructor: Int @BsonIgnore get() = 1854930076

    }


    /**
     * Contains the storage usage statistics for a specific file type
     *
     * @fileType - File type
     * @size - Total size of the files
     * @count - Total number of files
     */
    class StorageStatisticsByFileType : Object {

        var fileType: FileType by WeakField()
        var size: Long by WeakField()
        var count: Int by WeakField()

        constructor()

        constructor(fileType: FileType, size: Long, count: Int) {

            this.fileType = fileType
            this.size = size
            this.count = count

        }

        override val constructor: Int @BsonIgnore get() = 714012840

    }


    /**
     * Contains the storage usage statistics for a specific chat
     *
     * @chatId - Chat identifier
     *           0 if none
     * @size - Total size of the files in the chat
     * @count - Total number of files in the chat
     * @byFileType - Statistics split by file types
     */
    class StorageStatisticsByChat : Object {

        var chatId: Long by WeakField()
        var size: Long by WeakField()
        var count: Int by WeakField()
        var byFileType: Array<StorageStatisticsByFileType> by WeakField()

        constructor()

        constructor(chatId: Long, size: Long, count: Int, byFileType: Array<StorageStatisticsByFileType>) {

            this.chatId = chatId
            this.size = size
            this.count = count
            this.byFileType = byFileType

        }

        override val constructor: Int @BsonIgnore get() = 635434531

    }


    /**
     * Contains the exact storage usage statistics split by chats and file type
     *
     * @size - Total size of files
     * @count - Total number of files
     * @byChat - Statistics split by chats
     */
    class StorageStatistics : Object {

        var size: Long by WeakField()
        var count: Int by WeakField()
        var byChat: Array<StorageStatisticsByChat> by WeakField()

        constructor()

        constructor(size: Long, count: Int, byChat: Array<StorageStatisticsByChat>) {

            this.size = size
            this.count = count
            this.byChat = byChat

        }

        override val constructor: Int @BsonIgnore get() = 217237013

    }


    /**
     * Contains approximate storage usage statistics, excluding files of unknown file type
     *
     * @filesSize - Approximate total size of files
     * @fileCount - Approximate number of files
     * @databaseSize - Size of the database
     * @languagePackDatabaseSize - Size of the language pack database
     * @logSize - Size of the TDLib internal log
     */
    class StorageStatisticsFast : Object {

        var filesSize: Long by WeakField()
        var fileCount: Int by WeakField()
        var databaseSize: Long by WeakField()
        var languagePackDatabaseSize: Long by WeakField()
        var logSize: Long by WeakField()

        constructor()

        constructor(filesSize: Long, fileCount: Int, databaseSize: Long, languagePackDatabaseSize: Long, logSize: Long) {

            this.filesSize = filesSize
            this.fileCount = fileCount
            this.databaseSize = databaseSize
            this.languagePackDatabaseSize = languagePackDatabaseSize
            this.logSize = logSize

        }

        override val constructor: Int @BsonIgnore get() = -884922271

    }


    /**
     * Contains database statistics
     *
     * @statistics - Database statistics in an unspecified human-readable format
     */
    class DatabaseStatistics : Object {

        var statistics: String by WeakField()

        constructor()

        constructor(statistics: String) {

            this.statistics = statistics

        }

        override val constructor: Int @BsonIgnore get() = -1123912880

    }


    /**
     * Represents the type of a network
     */
    abstract class NetworkType : Object()

    /**
     * The network is not available
     */
    class NetworkTypeNone : NetworkType() {

        override val constructor: Int @BsonIgnore get() = -1971691759

    }


    /**
     * A mobile network
     */
    class NetworkTypeMobile : NetworkType() {

        override val constructor: Int @BsonIgnore get() = 819228239

    }


    /**
     * A mobile roaming network
     */
    class NetworkTypeMobileRoaming : NetworkType() {

        override val constructor: Int @BsonIgnore get() = -1435199760

    }


    /**
     * A Wi-Fi network
     */
    class NetworkTypeWiFi : NetworkType() {

        override val constructor: Int @BsonIgnore get() = -633872070

    }


    /**
     * A different network type (e.g., Ethernet network)
     */
    class NetworkTypeOther : NetworkType() {

        override val constructor: Int @BsonIgnore get() = 1942128539

    }


    /**
     * Contains statistics about network usage
     */
    abstract class NetworkStatisticsEntry : Object()

    /**
     * Contains information about the total amount of data that was used to send and receive files
     *
     * @fileType - Type of the file the data is part of
     * @networkType - Type of the network the data was sent through
     *                Call setNetworkType to maintain the actual network type
     * @sentBytes - Total number of bytes sent
     * @receivedBytes - Total number of bytes received
     */
    class NetworkStatisticsEntryFile : NetworkStatisticsEntry {

        var fileType: FileType by WeakField()
        var networkType: NetworkType by WeakField()
        var sentBytes: Long by WeakField()
        var receivedBytes: Long by WeakField()

        constructor()

        constructor(fileType: FileType, networkType: NetworkType, sentBytes: Long, receivedBytes: Long) {

            this.fileType = fileType
            this.networkType = networkType
            this.sentBytes = sentBytes
            this.receivedBytes = receivedBytes

        }

        override val constructor: Int @BsonIgnore get() = 188452706

    }


    /**
     * Contains information about the total amount of data that was used for calls
     *
     * @networkType - Type of the network the data was sent through
     *                Call setNetworkType to maintain the actual network type
     * @sentBytes - Total number of bytes sent
     * @receivedBytes - Total number of bytes received
     * @duration - Total call duration, in seconds
     */
    class NetworkStatisticsEntryCall : NetworkStatisticsEntry {

        var networkType: NetworkType by WeakField()
        var sentBytes: Long by WeakField()
        var receivedBytes: Long by WeakField()
        var duration: Double by WeakField()

        constructor()

        constructor(networkType: NetworkType, sentBytes: Long, receivedBytes: Long, duration: Double) {

            this.networkType = networkType
            this.sentBytes = sentBytes
            this.receivedBytes = receivedBytes
            this.duration = duration

        }

        override val constructor: Int @BsonIgnore get() = 737000365

    }


    /**
     * A full list of available network statistic entries
     *
     * @sinceDate - Point in time (Unix timestamp) when the app began collecting statistics
     * @entries - Network statistics entries
     */
    class NetworkStatistics : Object {

        var sinceDate: Int by WeakField()
        var entries: Array<NetworkStatisticsEntry> by WeakField()

        constructor()

        constructor(sinceDate: Int, entries: Array<NetworkStatisticsEntry>) {

            this.sinceDate = sinceDate
            this.entries = entries

        }

        override val constructor: Int @BsonIgnore get() = 1615554212

    }


    /**
     * Contains auto-download settings
     *
     * @isAutoDownloadEnabled - True, if the auto-download is enabled
     * @maxPhotoFileSize - The maximum size of a photo file to be auto-downloaded
     * @maxVideoFileSize - The maximum size of a video file to be auto-downloaded
     * @maxOtherFileSize - The maximum size of other file types to be auto-downloaded
     * @videoUploadBitrate - The maximum suggested bitrate for uploaded videos
     * @preloadLargeVideos - True, if the beginning of videos needs to be preloaded for instant playback
     * @preloadNextAudio - True, if the next audio track needs to be preloaded while the user is listening to an audio file
     * @useLessDataForCalls - True, if "use less data for calls" option needs to be enabled
     */
    class AutoDownloadSettings : Object {

        var isAutoDownloadEnabled: Boolean by WeakField()
        var maxPhotoFileSize: Int by WeakField()
        var maxVideoFileSize: Int by WeakField()
        var maxOtherFileSize: Int by WeakField()
        var videoUploadBitrate: Int by WeakField()
        var preloadLargeVideos: Boolean by WeakField()
        var preloadNextAudio: Boolean by WeakField()
        var useLessDataForCalls: Boolean by WeakField()

        constructor()

        constructor(isAutoDownloadEnabled: Boolean, maxPhotoFileSize: Int, maxVideoFileSize: Int, maxOtherFileSize: Int, videoUploadBitrate: Int, preloadLargeVideos: Boolean, preloadNextAudio: Boolean, useLessDataForCalls: Boolean) {

            this.isAutoDownloadEnabled = isAutoDownloadEnabled
            this.maxPhotoFileSize = maxPhotoFileSize
            this.maxVideoFileSize = maxVideoFileSize
            this.maxOtherFileSize = maxOtherFileSize
            this.videoUploadBitrate = videoUploadBitrate
            this.preloadLargeVideos = preloadLargeVideos
            this.preloadNextAudio = preloadNextAudio
            this.useLessDataForCalls = useLessDataForCalls

        }

        override val constructor: Int @BsonIgnore get() = -2144418333

    }


    /**
     * Contains auto-download settings presets for the user
     *
     * @low - Preset with lowest settings
     *        Supposed to be used by default when roaming
     * @medium - Preset with medium settings
     *           Supposed to be used by default when using mobile data
     * @high - Preset with highest settings
     *         Supposed to be used by default when connected on Wi-Fi
     */
    class AutoDownloadSettingsPresets : Object {

        var low: AutoDownloadSettings by WeakField()
        var medium: AutoDownloadSettings by WeakField()
        var high: AutoDownloadSettings by WeakField()

        constructor()

        constructor(low: AutoDownloadSettings, medium: AutoDownloadSettings, high: AutoDownloadSettings) {

            this.low = low
            this.medium = medium
            this.high = high

        }

        override val constructor: Int @BsonIgnore get() = -782099166

    }


    /**
     * Describes the current state of the connection to Telegram servers
     */
    abstract class ConnectionState : Object()

    /**
     * Currently waiting for the network to become available
     * Use setNetworkType to change the available network type
     */
    class ConnectionStateWaitingForNetwork : ConnectionState() {

        override val constructor: Int @BsonIgnore get() = 1695405912

    }


    /**
     * Currently establishing a connection with a proxy server
     */
    class ConnectionStateConnectingToProxy : ConnectionState() {

        override val constructor: Int @BsonIgnore get() = -93187239

    }


    /**
     * Currently establishing a connection to the Telegram servers
     */
    class ConnectionStateConnecting : ConnectionState() {

        override val constructor: Int @BsonIgnore get() = -1298400670

    }


    /**
     * Downloading data received while the client was offline
     */
    class ConnectionStateUpdating : ConnectionState() {

        override val constructor: Int @BsonIgnore get() = -188104009

    }


    /**
     * There is a working connection to the Telegram servers
     */
    class ConnectionStateReady : ConnectionState() {

        override val constructor: Int @BsonIgnore get() = 48608492

    }


    /**
     * Represents the categories of chats for which a list of frequently used chats can be retrieved
     */
    abstract class TopChatCategory : Object()

    /**
     * A category containing frequently used private chats with non-bot users
     */
    class TopChatCategoryUsers : TopChatCategory() {

        override val constructor: Int @BsonIgnore get() = 1026706816

    }


    /**
     * A category containing frequently used private chats with bot users
     */
    class TopChatCategoryBots : TopChatCategory() {

        override val constructor: Int @BsonIgnore get() = -1577129195

    }


    /**
     * A category containing frequently used basic groups and supergroups
     */
    class TopChatCategoryGroups : TopChatCategory() {

        override val constructor: Int @BsonIgnore get() = 1530056846

    }


    /**
     * A category containing frequently used channels
     */
    class TopChatCategoryChannels : TopChatCategory() {

        override val constructor: Int @BsonIgnore get() = -500825885

    }


    /**
     * A category containing frequently used chats with inline bots sorted by their usage in inline mode
     */
    class TopChatCategoryInlineBots : TopChatCategory() {

        override val constructor: Int @BsonIgnore get() = 377023356

    }


    /**
     * A category containing frequently used chats used for calls
     */
    class TopChatCategoryCalls : TopChatCategory() {

        override val constructor: Int @BsonIgnore get() = 356208861

    }


    /**
     * A category containing frequently used chats used to forward messages
     */
    class TopChatCategoryForwardChats : TopChatCategory() {

        override val constructor: Int @BsonIgnore get() = 1695922133

    }


    /**
     * Describes the type of a URL linking to an internal Telegram entity
     */
    abstract class TMeUrlType : Object()

    /**
     * A URL linking to a user
     *
     * @userId - Identifier of the user
     */
    class TMeUrlTypeUser : TMeUrlType {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -1198700130

    }


    /**
     * A URL linking to a public supergroup or channel
     *
     * @supergroupId - Identifier of the supergroup or channel
     */
    class TMeUrlTypeSupergroup : TMeUrlType {

        var supergroupId: Long by WeakField()

        constructor()

        constructor(supergroupId: Long) {

            this.supergroupId = supergroupId

        }

        override val constructor: Int @BsonIgnore get() = -1353369944

    }


    /**
     * A chat invite link
     *
     * @info - Chat invite link info
     */
    class TMeUrlTypeChatInvite : TMeUrlType {

        var info: ChatInviteLinkInfo by WeakField()

        constructor()

        constructor(info: ChatInviteLinkInfo) {

            this.info = info

        }

        override val constructor: Int @BsonIgnore get() = 313907785

    }


    /**
     * A URL linking to a sticker set
     *
     * @stickerSetId - Identifier of the sticker set
     */
    class TMeUrlTypeStickerSet : TMeUrlType {

        var stickerSetId: Long by WeakField()

        constructor()

        constructor(stickerSetId: Long) {

            this.stickerSetId = stickerSetId

        }

        override val constructor: Int @BsonIgnore get() = 1602473196

    }


    /**
     * Represents a URL linking to an internal Telegram entity
     *
     * @url - URL
     * @type - Type of the URL
     */
    class TMeUrl : Object {

        var url: String by WeakField()
        var type: TMeUrlType by WeakField()

        constructor()

        constructor(url: String, type: TMeUrlType) {

            this.url = url
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -1140786622

    }


    /**
     * Contains a list of t.me URLs
     *
     * @urls - List of URLs
     */
    class TMeUrls : Object {

        var urls: Array<TMeUrl> by WeakField()

        constructor()

        constructor(urls: Array<TMeUrl>) {

            this.urls = urls

        }

        override val constructor: Int @BsonIgnore get() = -1130595098

    }


    /**
     * Contains a counter
     *
     * @count - Count
     */
    class Count : Object {

        var count: Int by WeakField()

        constructor()

        constructor(count: Int) {

            this.count = count

        }

        override val constructor: Int @BsonIgnore get() = 1295577348

    }


    /**
     * Contains some text
     *
     * @text - Text
     */
    class Text : Object {

        var text: String by WeakField()

        constructor()

        constructor(text: String) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 578181272

    }


    /**
     * Contains a value representing a number of seconds
     *
     * @seconds - Number of seconds
     */
    class Seconds : Object {

        var seconds: Double by WeakField()

        constructor()

        constructor(seconds: Double) {

            this.seconds = seconds

        }

        override val constructor: Int @BsonIgnore get() = 959899022

    }


    /**
     * Contains information about a tg:// deep link
     *
     * @text - Text to be shown to the user
     * @needUpdateApplication - True, if user should be asked to update the application
     */
    class DeepLinkInfo : Object {

        var text: FormattedText by WeakField()
        var needUpdateApplication: Boolean by WeakField()

        constructor()

        constructor(text: FormattedText, needUpdateApplication: Boolean) {

            this.text = text
            this.needUpdateApplication = needUpdateApplication

        }

        override val constructor: Int @BsonIgnore get() = 1864081662

    }


    /**
     * Describes the way the text should be parsed for TextEntities
     */
    abstract class TextParseMode : Object()

    /**
     * The text should be parsed in markdown-style
     *
     * @version - Version of the parser: 0 or 1 - Bot API Markdown parse mode, 2 - Bot API MarkdownV2 parse mode
     */
    class TextParseModeMarkdown : TextParseMode {

        var version: Int by WeakField()

        constructor()

        constructor(version: Int) {

            this.version = version

        }

        override val constructor: Int @BsonIgnore get() = 360073407

    }


    /**
     * The text should be parsed in HTML-style
     */
    class TextParseModeHTML : TextParseMode() {

        override val constructor: Int @BsonIgnore get() = 1660208627

    }


    /**
     * Describes the type of the proxy server
     */
    abstract class ProxyType : Object()

    /**
     * A SOCKS5 proxy server
     *
     * @username - Username for logging in
     * @password - Password for logging in
     */
    class ProxyTypeSocks5 : ProxyType {

        var username: String? = null
        var password: String? = null

        constructor()

        constructor(username: String? = null, password: String? = null) {

            this.username = username
            this.password = password

        }

        override val constructor: Int @BsonIgnore get() = -890027341

    }


    /**
     * A HTTP transparent proxy server
     *
     * @username - Username for logging in
     * @password - Password for logging in
     * @httpOnly - Pass true, if the proxy supports only HTTP requests and doesn't support transparent TCP connections via HTTP CONNECT method
     */
    class ProxyTypeHttp : ProxyType {

        var username: String? = null
        var password: String? = null
        var httpOnly: Boolean by WeakField()

        constructor()

        constructor(username: String? = null, password: String? = null, httpOnly: Boolean) {

            this.username = username
            this.password = password
            this.httpOnly = httpOnly

        }

        override val constructor: Int @BsonIgnore get() = -1547188361

    }


    /**
     * An MTProto proxy server
     *
     * @secret - The proxy's secret in hexadecimal encoding
     */
    class ProxyTypeMtproto : ProxyType {

        var secret: String by WeakField()

        constructor()

        constructor(secret: String) {

            this.secret = secret

        }

        override val constructor: Int @BsonIgnore get() = -1964826627

    }


    /**
     * Contains information about a proxy server
     *
     * @id - Unique identifier of the proxy
     * @server - Proxy server IP address
     * @port - Proxy server port
     * @lastUsedDate - Point in time (Unix timestamp) when the proxy was last used
     *                 0 if never
     * @isEnabled - True, if the proxy is enabled now
     * @type - Type of the proxy
     */
    class Proxy : Object {

        var id: Int by WeakField()
        var server: String by WeakField()
        var port: Int by WeakField()
        var lastUsedDate: Int by WeakField()
        var isEnabled: Boolean by WeakField()
        var type: ProxyType by WeakField()

        constructor()

        constructor(id: Int, server: String, port: Int, lastUsedDate: Int, isEnabled: Boolean, type: ProxyType) {

            this.id = id
            this.server = server
            this.port = port
            this.lastUsedDate = lastUsedDate
            this.isEnabled = isEnabled
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = 196049779

    }


    /**
     * Represents a list of proxy servers
     *
     * @proxies - List of proxy servers
     */
    class Proxies : Object {

        var proxies: Array<Proxy> by WeakField()

        constructor()

        constructor(proxies: Array<Proxy>) {

            this.proxies = proxies

        }

        override val constructor: Int @BsonIgnore get() = 1200447205

    }


    /**
     * Describes a sticker that should be added to a sticker set
     *
     * @pngSticker - PNG image with the sticker
     *               Must be up to 512 kB in size and fit in a 512x512 square
     * @emojis - Emoji corresponding to the sticker
     * @maskPosition - For masks, position where the mask should be placed
     */
    class InputSticker : Object {

        var pngSticker: InputFile? = null
        var emojis: String? = null
        var maskPosition: MaskPosition? = null

        constructor()

        constructor(pngSticker: InputFile? = null, emojis: String? = null, maskPosition: MaskPosition? = null) {

            this.pngSticker = pngSticker
            this.emojis = emojis
            this.maskPosition = maskPosition

        }

        override val constructor: Int @BsonIgnore get() = -1998602205

    }


    /**
     * Contains notifications about data changes
     */
    abstract class Update : Object()

    /**
     * The user authorization state has changed
     *
     * @authorizationState - New authorization state
     */
    class UpdateAuthorizationState : Update {

        var authorizationState: AuthorizationState by WeakField()

        constructor()

        constructor(authorizationState: AuthorizationState) {

            this.authorizationState = authorizationState

        }

        override val constructor: Int @BsonIgnore get() = 1622347490

    }


    /**
     * A new message was received
     * Can also be an outgoing message
     *
     * @message - The new message
     */
    class UpdateNewMessage : Update {

        var message: Message by WeakField()

        constructor()

        constructor(message: Message) {

            this.message = message

        }

        override val constructor: Int @BsonIgnore get() = -563105266

    }


    /**
     * A request to send a message has reached the Telegram server
     * This doesn't mean that the message will be sent successfully or even that the send message request will be processed
     * This update will be sent only if the option "use_quick_ack" is set to true
     * This update may be sent multiple times for the same message
     *
     * @chatId - The chat identifier of the sent message
     * @messageId - A temporary message identifier
     */
    class UpdateMessageSendAcknowledged : Update {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = 1302843961

    }


    /**
     * A message has been successfully sent
     *
     * @message - Information about the sent message
     *            Usually only the message identifier, date, and content are changed, but almost all other fields can also change
     * @oldMessageId - The previous temporary message identifier
     */
    class UpdateMessageSendSucceeded : Update {

        var message: Message by WeakField()
        var oldMessageId: Long by WeakField()

        constructor()

        constructor(message: Message, oldMessageId: Long) {

            this.message = message
            this.oldMessageId = oldMessageId

        }

        override val constructor: Int @BsonIgnore get() = 1815715197

    }


    /**
     * A message failed to send
     * Be aware that some messages being sent can be irrecoverably deleted, in which case updateDeleteMessages will be received instead of this update
     *
     * @message - Contains information about the message which failed to send
     * @oldMessageId - The previous temporary message identifier
     * @errorCode - An error code
     * @errorMessage - Error message
     */
    class UpdateMessageSendFailed : Update {

        var message: Message by WeakField()
        var oldMessageId: Long by WeakField()
        var errorCode: Int by WeakField()
        var errorMessage: String by WeakField()

        constructor()

        constructor(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String) {

            this.message = message
            this.oldMessageId = oldMessageId
            this.errorCode = errorCode
            this.errorMessage = errorMessage

        }

        override val constructor: Int @BsonIgnore get() = -1032335779

    }


    /**
     * The message content has changed
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @newContent - New message content
     */
    class UpdateMessageContent : Update {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var newContent: MessageContent by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, newContent: MessageContent) {

            this.chatId = chatId
            this.messageId = messageId
            this.newContent = newContent

        }

        override val constructor: Int @BsonIgnore get() = 506903332

    }


    /**
     * A message was edited
     * Changes in the message content will come in a separate updateMessageContent
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @editDate - Point in time (Unix timestamp) when the message was edited
     * @replyMarkup - New message reply markup
     */
    class UpdateMessageEdited : Update {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var editDate: Int by WeakField()
        var replyMarkup: ReplyMarkup? = null

        constructor()

        constructor(chatId: Long, messageId: Long, editDate: Int, replyMarkup: ReplyMarkup? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.editDate = editDate
            this.replyMarkup = replyMarkup

        }

        override val constructor: Int @BsonIgnore get() = -559545626

    }


    /**
     * The view count of the message has changed
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @views - New value of the view count
     */
    class UpdateMessageViews : Update {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var views: Int by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, views: Int) {

            this.chatId = chatId
            this.messageId = messageId
            this.views = views

        }

        override val constructor: Int @BsonIgnore get() = -1854131125

    }


    /**
     * The message content was opened
     * Updates voice note messages to "listened", video note messages to "viewed" and starts the TTL timer for self-destructing messages
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     */
    class UpdateMessageContentOpened : Update {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = -1520523131

    }


    /**
     * A message with an unread mention was read
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @unreadMentionCount - The new number of unread mention messages left in the chat
     */
    class UpdateMessageMentionRead : Update {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var unreadMentionCount: Int by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, unreadMentionCount: Int) {

            this.chatId = chatId
            this.messageId = messageId
            this.unreadMentionCount = unreadMentionCount

        }

        override val constructor: Int @BsonIgnore get() = -252228282

    }


    /**
     * A message with a live location was viewed
     * When the update is received, the client is supposed to update the live location
     *
     * @chatId - Identifier of the chat with the live location message
     * @messageId - Identifier of the message with live location
     */
    class UpdateMessageLiveLocationViewed : Update {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = -1308260971

    }


    /**
     * A new chat has been loaded/created
     * This update is guaranteed to come before the chat identifier is returned to the client
     * The chat field changes will be reported through separate updates
     *
     * @chat - The chat
     */
    class UpdateNewChat : Update {

        var chat: Chat by WeakField()

        constructor()

        constructor(chat: Chat) {

            this.chat = chat

        }

        override val constructor: Int @BsonIgnore get() = 2075757773

    }


    /**
     * The list to which the chat belongs was changed
     * This update is guaranteed to be sent only when chat.order == 0 and the current or the new chat list is null
     *
     * @chatId - Chat identifier
     * @chatList - The new chat's chat list
     */
    class UpdateChatChatList : Update {

        var chatId: Long by WeakField()
        var chatList: ChatList? = null

        constructor()

        constructor(chatId: Long, chatList: ChatList? = null) {

            this.chatId = chatId
            this.chatList = chatList

        }

        override val constructor: Int @BsonIgnore get() = -170455894

    }


    /**
     * The title of a chat was changed
     *
     * @chatId - Chat identifier
     * @title - The new chat title
     */
    class UpdateChatTitle : Update {

        var chatId: Long by WeakField()
        var title: String by WeakField()

        constructor()

        constructor(chatId: Long, title: String) {

            this.chatId = chatId
            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = -175405660

    }


    /**
     * A chat photo was changed
     *
     * @chatId - Chat identifier
     * @photo - The new chat photo
     */
    class UpdateChatPhoto : Update {

        var chatId: Long by WeakField()
        var photo: ChatPhoto? = null

        constructor()

        constructor(chatId: Long, photo: ChatPhoto? = null) {

            this.chatId = chatId
            this.photo = photo

        }

        override val constructor: Int @BsonIgnore get() = -209353966

    }


    /**
     * Chat permissions was changed
     *
     * @chatId - Chat identifier
     * @permissions - The new chat permissions
     */
    class UpdateChatPermissions : Update {

        var chatId: Long by WeakField()
        var permissions: ChatPermissions by WeakField()

        constructor()

        constructor(chatId: Long, permissions: ChatPermissions) {

            this.chatId = chatId
            this.permissions = permissions

        }

        override val constructor: Int @BsonIgnore get() = -1622010003

    }


    /**
     * The last message of a chat was changed
     * If last_message is null, then the last message in the chat became unknown
     * Some new unknown messages might be added to the chat in this case
     *
     * @chatId - Chat identifier
     * @lastMessage - The new last message in the chat
     * @order - New value of the chat order
     */
    class UpdateChatLastMessage : Update {

        var chatId: Long by WeakField()
        var lastMessage: Message? = null
        var order: Long by WeakField()

        constructor()

        constructor(chatId: Long, lastMessage: Message? = null, order: Long) {

            this.chatId = chatId
            this.lastMessage = lastMessage
            this.order = order

        }

        override val constructor: Int @BsonIgnore get() = 580348828

    }


    /**
     * The order of the chat in the chat list has changed
     * Instead of this update updateChatLastMessage, updateChatIsPinned, updateChatDraftMessage, or updateChatIsSponsored might be sent
     *
     * @chatId - Chat identifier
     * @order - New value of the order
     */
    class UpdateChatOrder : Update {

        var chatId: Long by WeakField()
        var order: Long by WeakField()

        constructor()

        constructor(chatId: Long, order: Long) {

            this.chatId = chatId
            this.order = order

        }

        override val constructor: Int @BsonIgnore get() = -1601888026

    }


    /**
     * A chat was pinned or unpinned
     *
     * @chatId - Chat identifier
     * @isPinned - New value of is_pinned
     * @order - New value of the chat order
     */
    class UpdateChatIsPinned : Update {

        var chatId: Long by WeakField()
        var isPinned: Boolean by WeakField()
        var order: Long by WeakField()

        constructor()

        constructor(chatId: Long, isPinned: Boolean, order: Long) {

            this.chatId = chatId
            this.isPinned = isPinned
            this.order = order

        }

        override val constructor: Int @BsonIgnore get() = 488876260

    }


    /**
     * A chat was marked as unread or was read
     *
     * @chatId - Chat identifier
     * @isMarkedAsUnread - New value of is_marked_as_unread
     */
    class UpdateChatIsMarkedAsUnread : Update {

        var chatId: Long by WeakField()
        var isMarkedAsUnread: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, isMarkedAsUnread: Boolean) {

            this.chatId = chatId
            this.isMarkedAsUnread = isMarkedAsUnread

        }

        override val constructor: Int @BsonIgnore get() = 1468347188

    }


    /**
     * A chat's is_sponsored field has changed
     *
     * @chatId - Chat identifier
     * @isSponsored - New value of is_sponsored
     * @order - New value of chat order
     */
    class UpdateChatIsSponsored : Update {

        var chatId: Long by WeakField()
        var isSponsored: Boolean by WeakField()
        var order: Long by WeakField()

        constructor()

        constructor(chatId: Long, isSponsored: Boolean, order: Long) {

            this.chatId = chatId
            this.isSponsored = isSponsored
            this.order = order

        }

        override val constructor: Int @BsonIgnore get() = -1196180070

    }


    /**
     * A chat's has_scheduled_messages field has changed
     *
     * @chatId - Chat identifier
     * @hasScheduledMessages - New value of has_scheduled_messages
     */
    class UpdateChatHasScheduledMessages : Update {

        var chatId: Long by WeakField()
        var hasScheduledMessages: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, hasScheduledMessages: Boolean) {

            this.chatId = chatId
            this.hasScheduledMessages = hasScheduledMessages

        }

        override val constructor: Int @BsonIgnore get() = 2064958167

    }


    /**
     * The value of the default disable_notification parameter, used when a message is sent to the chat, was changed
     *
     * @chatId - Chat identifier
     * @defaultDisableNotification - The new default_disable_notification value
     */
    class UpdateChatDefaultDisableNotification : Update {

        var chatId: Long by WeakField()
        var defaultDisableNotification: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, defaultDisableNotification: Boolean) {

            this.chatId = chatId
            this.defaultDisableNotification = defaultDisableNotification

        }

        override val constructor: Int @BsonIgnore get() = 464087707

    }


    /**
     * Incoming messages were read or number of unread messages has been changed
     *
     * @chatId - Chat identifier
     * @lastReadInboxMessageId - Identifier of the last read incoming message
     * @unreadCount - The number of unread messages left in the chat
     */
    class UpdateChatReadInbox : Update {

        var chatId: Long by WeakField()
        var lastReadInboxMessageId: Long by WeakField()
        var unreadCount: Int by WeakField()

        constructor()

        constructor(chatId: Long, lastReadInboxMessageId: Long, unreadCount: Int) {

            this.chatId = chatId
            this.lastReadInboxMessageId = lastReadInboxMessageId
            this.unreadCount = unreadCount

        }

        override val constructor: Int @BsonIgnore get() = -797952281

    }


    /**
     * Outgoing messages were read
     *
     * @chatId - Chat identifier
     * @lastReadOutboxMessageId - Identifier of last read outgoing message
     */
    class UpdateChatReadOutbox : Update {

        var chatId: Long by WeakField()
        var lastReadOutboxMessageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, lastReadOutboxMessageId: Long) {

            this.chatId = chatId
            this.lastReadOutboxMessageId = lastReadOutboxMessageId

        }

        override val constructor: Int @BsonIgnore get() = 708334213

    }


    /**
     * The chat unread_mention_count has changed
     *
     * @chatId - Chat identifier
     * @unreadMentionCount - The number of unread mention messages left in the chat
     */
    class UpdateChatUnreadMentionCount : Update {

        var chatId: Long by WeakField()
        var unreadMentionCount: Int by WeakField()

        constructor()

        constructor(chatId: Long, unreadMentionCount: Int) {

            this.chatId = chatId
            this.unreadMentionCount = unreadMentionCount

        }

        override val constructor: Int @BsonIgnore get() = -2131461348

    }


    /**
     * Notification settings for a chat were changed
     *
     * @chatId - Chat identifier
     * @notificationSettings - The new notification settings
     */
    class UpdateChatNotificationSettings : Update {

        var chatId: Long by WeakField()
        var notificationSettings: ChatNotificationSettings by WeakField()

        constructor()

        constructor(chatId: Long, notificationSettings: ChatNotificationSettings) {

            this.chatId = chatId
            this.notificationSettings = notificationSettings

        }

        override val constructor: Int @BsonIgnore get() = -803163050

    }


    /**
     * Notification settings for some type of chats were updated
     *
     * @scope - Types of chats for which notification settings were updated
     * @notificationSettings - The new notification settings
     */
    class UpdateScopeNotificationSettings : Update {

        var scope: NotificationSettingsScope by WeakField()
        var notificationSettings: ScopeNotificationSettings by WeakField()

        constructor()

        constructor(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings) {

            this.scope = scope
            this.notificationSettings = notificationSettings

        }

        override val constructor: Int @BsonIgnore get() = -1203975309

    }


    /**
     * The chat action bar was changed
     *
     * @chatId - Chat identifier
     * @actionBar - The new value of the action bar
     */
    class UpdateChatActionBar : Update {

        var chatId: Long by WeakField()
        var actionBar: ChatActionBar? = null

        constructor()

        constructor(chatId: Long, actionBar: ChatActionBar? = null) {

            this.chatId = chatId
            this.actionBar = actionBar

        }

        override val constructor: Int @BsonIgnore get() = -643671870

    }


    /**
     * The chat pinned message was changed
     *
     * @chatId - Chat identifier
     * @pinnedMessageId - The new identifier of the pinned message
     *                    0 if there is no pinned message in the chat
     */
    class UpdateChatPinnedMessage : Update {

        var chatId: Long by WeakField()
        var pinnedMessageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, pinnedMessageId: Long) {

            this.chatId = chatId
            this.pinnedMessageId = pinnedMessageId

        }

        override val constructor: Int @BsonIgnore get() = 802160507

    }


    /**
     * The default chat reply markup was changed
     * Can occur because new messages with reply markup were received or because an old reply markup was hidden by the user
     *
     * @chatId - Chat identifier
     * @replyMarkupMessageId - Identifier of the message from which reply markup needs to be used
     *                         0 if there is no default custom reply markup in the chat
     */
    class UpdateChatReplyMarkup : Update {

        var chatId: Long by WeakField()
        var replyMarkupMessageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, replyMarkupMessageId: Long) {

            this.chatId = chatId
            this.replyMarkupMessageId = replyMarkupMessageId

        }

        override val constructor: Int @BsonIgnore get() = 1309386144

    }


    /**
     * A chat draft has changed
     * Be aware that the update may come in the currently opened chat but with old content of the draft
     * If the user has changed the content of the draft, this update shouldn't be applied
     *
     * @chatId - Chat identifier
     * @draftMessage - The new draft message
     * @order - New value of the chat order
     */
    class UpdateChatDraftMessage : Update {

        var chatId: Long by WeakField()
        var draftMessage: DraftMessage? = null
        var order: Long by WeakField()

        constructor()

        constructor(chatId: Long, draftMessage: DraftMessage? = null, order: Long) {

            this.chatId = chatId
            this.draftMessage = draftMessage
            this.order = order

        }

        override val constructor: Int @BsonIgnore get() = -1436617498

    }


    /**
     * The number of online group members has changed
     * This update with non-zero count is sent only for currently opened chats
     * There is no guarantee that it will be sent just after the count has changed
     *
     * @chatId - Identifier of the chat
     * @onlineMemberCount - New number of online members in the chat, or 0 if unknown
     */
    class UpdateChatOnlineMemberCount : Update {

        var chatId: Long by WeakField()
        var onlineMemberCount: Int by WeakField()

        constructor()

        constructor(chatId: Long, onlineMemberCount: Int) {

            this.chatId = chatId
            this.onlineMemberCount = onlineMemberCount

        }

        override val constructor: Int @BsonIgnore get() = 487369373

    }


    /**
     * A notification was changed
     *
     * @notificationGroupId - Unique notification group identifier
     * @notification - Changed notification
     */
    class UpdateNotification : Update {

        var notificationGroupId: Int by WeakField()
        var notification: Notification by WeakField()

        constructor()

        constructor(notificationGroupId: Int, notification: Notification) {

            this.notificationGroupId = notificationGroupId
            this.notification = notification

        }

        override val constructor: Int @BsonIgnore get() = -1897496876

    }


    /**
     * A list of active notifications in a notification group has changed
     *
     * @notificationGroupId - Unique notification group identifier
     * @type - New type of the notification group
     * @chatId - Identifier of a chat to which all notifications in the group belong
     * @notificationSettingsChatId - Chat identifier, which notification settings must be applied to the added notifications
     * @isSilent - True, if the notifications should be shown without sound
     * @totalCount - Total number of unread notifications in the group, can be bigger than number of active notifications
     * @addedNotifications - List of added group notifications, sorted by notification ID
     * @removedNotificationIds - Identifiers of removed group notifications, sorted by notification ID
     */
    class UpdateNotificationGroup : Update {

        var notificationGroupId: Int by WeakField()
        var type: NotificationGroupType by WeakField()
        var chatId: Long by WeakField()
        var notificationSettingsChatId: Long by WeakField()
        var isSilent: Boolean by WeakField()
        var totalCount: Int by WeakField()
        var addedNotifications: Array<Notification> by WeakField()
        var removedNotificationIds: IntArray by WeakField()

        constructor()

        constructor(notificationGroupId: Int, type: NotificationGroupType, chatId: Long, notificationSettingsChatId: Long, isSilent: Boolean, totalCount: Int, addedNotifications: Array<Notification>, removedNotificationIds: IntArray) {

            this.notificationGroupId = notificationGroupId
            this.type = type
            this.chatId = chatId
            this.notificationSettingsChatId = notificationSettingsChatId
            this.isSilent = isSilent
            this.totalCount = totalCount
            this.addedNotifications = addedNotifications
            this.removedNotificationIds = removedNotificationIds

        }

        override val constructor: Int @BsonIgnore get() = -2049005665

    }


    /**
     * Contains active notifications that was shown on previous application launches
     * This update is sent only if the message database is used
     * In that case it comes once before any updateNotification and updateNotificationGroup update
     *
     * @groups - Lists of active notification groups
     */
    class UpdateActiveNotifications : Update {

        var groups: Array<NotificationGroup> by WeakField()

        constructor()

        constructor(groups: Array<NotificationGroup>) {

            this.groups = groups

        }

        override val constructor: Int @BsonIgnore get() = -1306672221

    }


    /**
     * Describes whether there are some pending notification updates
     * Can be used to prevent application from killing, while there are some pending notifications
     *
     * @haveDelayedNotifications - True, if there are some delayed notification updates, which will be sent soon
     * @haveUnreceivedNotifications - True, if there can be some yet unreceived notifications, which are being fetched from the server
     */
    class UpdateHavePendingNotifications : Update {

        var haveDelayedNotifications: Boolean by WeakField()
        var haveUnreceivedNotifications: Boolean by WeakField()

        constructor()

        constructor(haveDelayedNotifications: Boolean, haveUnreceivedNotifications: Boolean) {

            this.haveDelayedNotifications = haveDelayedNotifications
            this.haveUnreceivedNotifications = haveUnreceivedNotifications

        }

        override val constructor: Int @BsonIgnore get() = 179233243

    }


    /**
     * Some messages were deleted
     *
     * @chatId - Chat identifier
     * @messageIds - Identifiers of the deleted messages
     * @isPermanent - True, if the messages are permanently deleted by a user (as opposed to just becoming inaccessible)
     * @fromCache - True, if the messages are deleted only from the cache and can possibly be retrieved again in the future
     */
    class UpdateDeleteMessages : Update {

        var chatId: Long by WeakField()
        var messageIds: LongArray by WeakField()
        var isPermanent: Boolean by WeakField()
        var fromCache: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean) {

            this.chatId = chatId
            this.messageIds = messageIds
            this.isPermanent = isPermanent
            this.fromCache = fromCache

        }

        override val constructor: Int @BsonIgnore get() = 1669252686

    }


    /**
     * User activity in the chat has changed
     *
     * @chatId - Chat identifier
     * @userId - Identifier of a user performing an action
     * @action - The action description
     */
    class UpdateUserChatAction : Update {

        var chatId: Long by WeakField()
        var userId: Int by WeakField()
        var action: ChatAction by WeakField()

        constructor()

        constructor(chatId: Long, userId: Int, action: ChatAction) {

            this.chatId = chatId
            this.userId = userId
            this.action = action

        }

        override val constructor: Int @BsonIgnore get() = 1444133514

    }


    /**
     * The user went online or offline
     *
     * @userId - User identifier
     * @status - New status of the user
     */
    class UpdateUserStatus : Update {

        var userId: Int by WeakField()
        var status: UserStatus by WeakField()

        constructor()

        constructor(userId: Int, status: UserStatus) {

            this.userId = userId
            this.status = status

        }

        override val constructor: Int @BsonIgnore get() = -1443545195

    }


    /**
     * Some data of a user has changed
     * This update is guaranteed to come before the user identifier is returned to the client
     *
     * @user - New data about the user
     */
    class UpdateUser : Update {

        var user: User by WeakField()

        constructor()

        constructor(user: User) {

            this.user = user

        }

        override val constructor: Int @BsonIgnore get() = 1183394041

    }


    /**
     * Some data of a basic group has changed
     * This update is guaranteed to come before the basic group identifier is returned to the client
     *
     * @basicGroup - New data about the group
     */
    class UpdateBasicGroup : Update {

        var basicGroup: BasicGroup by WeakField()

        constructor()

        constructor(basicGroup: BasicGroup) {

            this.basicGroup = basicGroup

        }

        override val constructor: Int @BsonIgnore get() = -1003239581

    }


    /**
     * Some data of a supergroup or a channel has changed
     * This update is guaranteed to come before the supergroup identifier is returned to the client
     *
     * @supergroup - New data about the supergroup
     */
    class UpdateSupergroup : Update {

        var supergroup: Supergroup by WeakField()

        constructor()

        constructor(supergroup: Supergroup) {

            this.supergroup = supergroup

        }

        override val constructor: Int @BsonIgnore get() = -76782300

    }


    /**
     * Some data of a secret chat has changed
     * This update is guaranteed to come before the secret chat identifier is returned to the client
     *
     * @secretChat - New data about the secret chat
     */
    class UpdateSecretChat : Update {

        var secretChat: SecretChat by WeakField()

        constructor()

        constructor(secretChat: SecretChat) {

            this.secretChat = secretChat

        }

        override val constructor: Int @BsonIgnore get() = -1666903253

    }


    /**
     * Some data from userFullInfo has been changed
     *
     * @userId - User identifier
     * @userFullInfo - New full information about the user
     */
    class UpdateUserFullInfo : Update {

        var userId: Int by WeakField()
        var userFullInfo: UserFullInfo by WeakField()

        constructor()

        constructor(userId: Int, userFullInfo: UserFullInfo) {

            this.userId = userId
            this.userFullInfo = userFullInfo

        }

        override val constructor: Int @BsonIgnore get() = 222103874

    }


    /**
     * Some data from basicGroupFullInfo has been changed
     *
     * @basicGroupId - Identifier of a basic group
     * @basicGroupFullInfo - New full information about the group
     */
    class UpdateBasicGroupFullInfo : Update {

        var basicGroupId: Int by WeakField()
        var basicGroupFullInfo: BasicGroupFullInfo by WeakField()

        constructor()

        constructor(basicGroupId: Int, basicGroupFullInfo: BasicGroupFullInfo) {

            this.basicGroupId = basicGroupId
            this.basicGroupFullInfo = basicGroupFullInfo

        }

        override val constructor: Int @BsonIgnore get() = 924030531

    }


    /**
     * Some data from supergroupFullInfo has been changed
     *
     * @supergroupId - Identifier of the supergroup or channel
     * @supergroupFullInfo - New full information about the supergroup
     */
    class UpdateSupergroupFullInfo : Update {

        var supergroupId: Int by WeakField()
        var supergroupFullInfo: SupergroupFullInfo by WeakField()

        constructor()

        constructor(supergroupId: Int, supergroupFullInfo: SupergroupFullInfo) {

            this.supergroupId = supergroupId
            this.supergroupFullInfo = supergroupFullInfo

        }

        override val constructor: Int @BsonIgnore get() = 1288828758

    }


    /**
     * Service notification from the server
     * Upon receiving this the client must show a popup with the content of the notification
     *
     * @type - Notification type
     *         If type begins with "AUTH_KEY_DROP_", then two buttons "Cancel" and "Log out" should be shown under notification
     *         If user presses the second, all local data should be destroyed using Destroy method
     * @content - Notification content
     */
    class UpdateServiceNotification : Update {

        var type: String by WeakField()
        var content: MessageContent by WeakField()

        constructor()

        constructor(type: String, content: MessageContent) {

            this.type = type
            this.content = content

        }

        override val constructor: Int @BsonIgnore get() = 1318622637

    }


    /**
     * Information about a file was updated
     *
     * @file - New data about the file
     */
    class UpdateFile : Update {

        var file: File by WeakField()

        constructor()

        constructor(file: File) {

            this.file = file

        }

        override val constructor: Int @BsonIgnore get() = 114132831

    }


    /**
     * The file generation process needs to be started by the client
     *
     * @generationId - Unique identifier for the generation process
     * @originalPath - The path to a file from which a new file is generated
     * @destinationPath - The path to a file that should be created and where the new file should be generated
     * @conversion - String specifying the conversion applied to the original file
     *               If conversion is "#url#" than original_path contains an HTTP/HTTPS URL of a file, which should be downloaded by the client
     */
    class UpdateFileGenerationStart : Update {

        var generationId: Long by WeakField()
        var originalPath: String? = null
        var destinationPath: String by WeakField()
        var conversion: String by WeakField()

        constructor()

        constructor(generationId: Long, originalPath: String? = null, destinationPath: String, conversion: String) {

            this.generationId = generationId
            this.originalPath = originalPath
            this.destinationPath = destinationPath
            this.conversion = conversion

        }

        override val constructor: Int @BsonIgnore get() = 216817388

    }


    /**
     * File generation is no longer needed
     *
     * @generationId - Unique identifier for the generation process
     */
    class UpdateFileGenerationStop : Update {

        var generationId: Long by WeakField()

        constructor()

        constructor(generationId: Long) {

            this.generationId = generationId

        }

        override val constructor: Int @BsonIgnore get() = -1894449685

    }


    /**
     * New call was created or information about a call was updated
     *
     * @call - New data about a call
     */
    class UpdateCall : Update {

        var call: Call by WeakField()

        constructor()

        constructor(call: Call) {

            this.call = call

        }

        override val constructor: Int @BsonIgnore get() = 1337184477

    }


    /**
     * Some privacy setting rules have been changed
     *
     * @setting - The privacy setting
     * @rules - New privacy rules
     */
    class UpdateUserPrivacySettingRules : Update {

        var setting: UserPrivacySetting by WeakField()
        var rules: UserPrivacySettingRules by WeakField()

        constructor()

        constructor(setting: UserPrivacySetting, rules: UserPrivacySettingRules) {

            this.setting = setting
            this.rules = rules

        }

        override val constructor: Int @BsonIgnore get() = -912960778

    }


    /**
     * Number of unread messages in a chat list has changed
     * This update is sent only if the message database is used
     *
     * @chatList - The chat list with changed number of unread messages
     * @unreadCount - Total number of unread messages
     * @unreadUnmutedCount - Total number of unread messages in unmuted chats
     */
    class UpdateUnreadMessageCount : Update {

        var chatList: ChatList by WeakField()
        var unreadCount: Int by WeakField()
        var unreadUnmutedCount: Int by WeakField()

        constructor()

        constructor(chatList: ChatList, unreadCount: Int, unreadUnmutedCount: Int) {

            this.chatList = chatList
            this.unreadCount = unreadCount
            this.unreadUnmutedCount = unreadUnmutedCount

        }

        override val constructor: Int @BsonIgnore get() = 78987721

    }


    /**
     * Number of unread chats, i.e
     * With unread messages or marked as unread, has changed
     * This update is sent only if the message database is used
     *
     * @chatList - The chat list with changed number of unread messages
     * @totalCount - Approximate total number of chats in the chat list
     * @unreadCount - Total number of unread chats
     * @unreadUnmutedCount - Total number of unread unmuted chats
     * @markedAsUnreadCount - Total number of chats marked as unread
     * @markedAsUnreadUnmutedCount - Total number of unmuted chats marked as unread
     */
    class UpdateUnreadChatCount : Update {

        var chatList: ChatList by WeakField()
        var totalCount: Int by WeakField()
        var unreadCount: Int by WeakField()
        var unreadUnmutedCount: Int by WeakField()
        var markedAsUnreadCount: Int by WeakField()
        var markedAsUnreadUnmutedCount: Int by WeakField()

        constructor()

        constructor(chatList: ChatList, totalCount: Int, unreadCount: Int, unreadUnmutedCount: Int, markedAsUnreadCount: Int, markedAsUnreadUnmutedCount: Int) {

            this.chatList = chatList
            this.totalCount = totalCount
            this.unreadCount = unreadCount
            this.unreadUnmutedCount = unreadUnmutedCount
            this.markedAsUnreadCount = markedAsUnreadCount
            this.markedAsUnreadUnmutedCount = markedAsUnreadUnmutedCount

        }

        override val constructor: Int @BsonIgnore get() = 1994494530

    }


    /**
     * An option changed its value
     *
     * @name - The option name
     * @value - The new option value
     */
    class UpdateOption : Update {

        var name: String by WeakField()
        var value: OptionValue by WeakField()

        constructor()

        constructor(name: String, value: OptionValue) {

            this.name = name
            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 900822020

    }


    /**
     * The list of installed sticker sets was updated
     *
     * @isMasks - True, if the list of installed mask sticker sets was updated
     * @stickerSetIds - The new list of installed ordinary sticker sets
     */
    class UpdateInstalledStickerSets : Update {

        var isMasks: Boolean by WeakField()
        var stickerSetIds: LongArray by WeakField()

        constructor()

        constructor(isMasks: Boolean, stickerSetIds: LongArray) {

            this.isMasks = isMasks
            this.stickerSetIds = stickerSetIds

        }

        override val constructor: Int @BsonIgnore get() = 1125575977

    }


    /**
     * The list of trending sticker sets was updated or some of them were viewed
     *
     * @stickerSets - The new list of trending sticker sets
     */
    class UpdateTrendingStickerSets : Update {

        var stickerSets: StickerSets by WeakField()

        constructor()

        constructor(stickerSets: StickerSets) {

            this.stickerSets = stickerSets

        }

        override val constructor: Int @BsonIgnore get() = 450714593

    }


    /**
     * The list of recently used stickers was updated
     *
     * @isAttached - True, if the list of stickers attached to photo or video files was updated, otherwise the list of sent stickers is updated
     * @stickerIds - The new list of file identifiers of recently used stickers
     */
    class UpdateRecentStickers : Update {

        var isAttached: Boolean by WeakField()
        var stickerIds: IntArray by WeakField()

        constructor()

        constructor(isAttached: Boolean, stickerIds: IntArray) {

            this.isAttached = isAttached
            this.stickerIds = stickerIds

        }

        override val constructor: Int @BsonIgnore get() = 1906403540

    }


    /**
     * The list of favorite stickers was updated
     *
     * @stickerIds - The new list of file identifiers of favorite stickers
     */
    class UpdateFavoriteStickers : Update {

        var stickerIds: IntArray by WeakField()

        constructor()

        constructor(stickerIds: IntArray) {

            this.stickerIds = stickerIds

        }

        override val constructor: Int @BsonIgnore get() = 1662240999

    }


    /**
     * The list of saved animations was updated
     *
     * @animationIds - The new list of file identifiers of saved animations
     */
    class UpdateSavedAnimations : Update {

        var animationIds: IntArray by WeakField()

        constructor()

        constructor(animationIds: IntArray) {

            this.animationIds = animationIds

        }

        override val constructor: Int @BsonIgnore get() = 65563814

    }


    /**
     * The selected background has changed
     *
     * @forDarkTheme - True, if background for dark theme has changed
     * @background - The new selected background
     */
    class UpdateSelectedBackground : Update {

        var forDarkTheme: Boolean by WeakField()
        var background: Background? = null

        constructor()

        constructor(forDarkTheme: Boolean, background: Background? = null) {

            this.forDarkTheme = forDarkTheme
            this.background = background

        }

        override val constructor: Int @BsonIgnore get() = -1715658659

    }


    /**
     * Some language pack strings have been updated
     *
     * @localizationTarget - Localization target to which the language pack belongs
     * @languagePackId - Identifier of the updated language pack
     * @strings - List of changed language pack strings
     */
    class UpdateLanguagePackStrings : Update {

        var localizationTarget: String by WeakField()
        var languagePackId: String by WeakField()
        var strings: Array<LanguagePackString> by WeakField()

        constructor()

        constructor(localizationTarget: String, languagePackId: String, strings: Array<LanguagePackString>) {

            this.localizationTarget = localizationTarget
            this.languagePackId = languagePackId
            this.strings = strings

        }

        override val constructor: Int @BsonIgnore get() = -1056319886

    }


    /**
     * The connection state has changed
     *
     * @state - The new connection state
     */
    class UpdateConnectionState : Update {

        var state: ConnectionState by WeakField()

        constructor()

        constructor(state: ConnectionState) {

            this.state = state

        }

        override val constructor: Int @BsonIgnore get() = 1469292078

    }


    /**
     * New terms of service must be accepted by the user
     * If the terms of service are declined, then the deleteAccount method should be called with the reason "Decline ToS update"
     *
     * @termsOfServiceId - Identifier of the terms of service
     * @termsOfService - The new terms of service
     */
    class UpdateTermsOfService : Update {

        var termsOfServiceId: String by WeakField()
        var termsOfService: TermsOfService by WeakField()

        constructor()

        constructor(termsOfServiceId: String, termsOfService: TermsOfService) {

            this.termsOfServiceId = termsOfServiceId
            this.termsOfService = termsOfService

        }

        override val constructor: Int @BsonIgnore get() = -1304640162

    }


    /**
     * List of users nearby has changed
     * The update is sent only 60 seconds after a successful searchChatsNearby request
     *
     * @usersNearby - The new list of users nearby
     */
    class UpdateUsersNearby : Update {

        var usersNearby: Array<ChatNearby> by WeakField()

        constructor()

        constructor(usersNearby: Array<ChatNearby>) {

            this.usersNearby = usersNearby

        }

        override val constructor: Int @BsonIgnore get() = -1517109163

    }


    /**
     * A new incoming inline query
     * For bots only
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @userLocation - User location, provided by the client
     * @query - Text of the query
     * @offset - Offset of the first entry to return
     */
    class UpdateNewInlineQuery : Update {

        var id: Long by WeakField()
        var senderUserId: Int by WeakField()
        var userLocation: Location? = null
        var query: String by WeakField()
        var offset: String by WeakField()

        constructor()

        constructor(id: Long, senderUserId: Int, userLocation: Location? = null, query: String, offset: String) {

            this.id = id
            this.senderUserId = senderUserId
            this.userLocation = userLocation
            this.query = query
            this.offset = offset

        }

        override val constructor: Int @BsonIgnore get() = 2064730634

    }


    /**
     * The user has chosen a result of an inline query
     * For bots only
     *
     * @senderUserId - Identifier of the user who sent the query
     * @userLocation - User location, provided by the client
     * @query - Text of the query
     * @resultId - Identifier of the chosen result
     * @inlineMessageId - Identifier of the sent inline message, if known
     */
    class UpdateNewChosenInlineResult : Update {

        var senderUserId: Int by WeakField()
        var userLocation: Location? = null
        var query: String by WeakField()
        var resultId: String by WeakField()
        var inlineMessageId: String by WeakField()

        constructor()

        constructor(senderUserId: Int, userLocation: Location? = null, query: String, resultId: String, inlineMessageId: String) {

            this.senderUserId = senderUserId
            this.userLocation = userLocation
            this.query = query
            this.resultId = resultId
            this.inlineMessageId = inlineMessageId

        }

        override val constructor: Int @BsonIgnore get() = 527526965

    }


    /**
     * A new incoming callback query
     * For bots only
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @chatId - Identifier of the chat where the query was sent
     * @messageId - Identifier of the message, from which the query originated
     * @chatInstance - Identifier that uniquely corresponds to the chat to which the message was sent
     * @payload - Query payload
     */
    class UpdateNewCallbackQuery : Update {

        var id: Long by WeakField()
        var senderUserId: Int by WeakField()
        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var chatInstance: Long by WeakField()
        var payload: CallbackQueryPayload by WeakField()

        constructor()

        constructor(id: Long, senderUserId: Int, chatId: Long, messageId: Long, chatInstance: Long, payload: CallbackQueryPayload) {

            this.id = id
            this.senderUserId = senderUserId
            this.chatId = chatId
            this.messageId = messageId
            this.chatInstance = chatInstance
            this.payload = payload

        }

        override val constructor: Int @BsonIgnore get() = -2044226370

    }


    /**
     * A new incoming callback query from a message sent via a bot
     * For bots only
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @inlineMessageId - Identifier of the inline message, from which the query originated
     * @chatInstance - An identifier uniquely corresponding to the chat a message was sent to
     * @payload - Query payload
     */
    class UpdateNewInlineCallbackQuery : Update {

        var id: Long by WeakField()
        var senderUserId: Int by WeakField()
        var inlineMessageId: String by WeakField()
        var chatInstance: Long by WeakField()
        var payload: CallbackQueryPayload by WeakField()

        constructor()

        constructor(id: Long, senderUserId: Int, inlineMessageId: String, chatInstance: Long, payload: CallbackQueryPayload) {

            this.id = id
            this.senderUserId = senderUserId
            this.inlineMessageId = inlineMessageId
            this.chatInstance = chatInstance
            this.payload = payload

        }

        override val constructor: Int @BsonIgnore get() = -1879154829

    }


    /**
     * A new incoming shipping query
     * For bots only
     * Only for invoices with flexible price
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @invoicePayload - Invoice payload
     * @shippingAddress - User shipping address
     */
    class UpdateNewShippingQuery : Update {

        var id: Long by WeakField()
        var senderUserId: Int by WeakField()
        var invoicePayload: String by WeakField()
        var shippingAddress: Address by WeakField()

        constructor()

        constructor(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: Address) {

            this.id = id
            this.senderUserId = senderUserId
            this.invoicePayload = invoicePayload
            this.shippingAddress = shippingAddress

        }

        override val constructor: Int @BsonIgnore get() = -817474682

    }


    /**
     * A new incoming pre-checkout query
     * For bots only
     * Contains full information about a checkout
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @currency - Currency for the product price
     * @totalAmount - Total price for the product, in the minimal quantity of the currency
     * @invoicePayload - Invoice payload
     * @shippingOptionId - Identifier of a shipping option chosen by the user
     *                     May be empty if not applicable
     * @orderInfo - Information about the order
     */
    class UpdateNewPreCheckoutQuery : Update {

        var id: Long by WeakField()
        var senderUserId: Int by WeakField()
        var currency: String by WeakField()
        var totalAmount: Long by WeakField()
        var invoicePayload: ByteArray by WeakField()
        var shippingOptionId: String? = null
        var orderInfo: OrderInfo? = null

        constructor()

        constructor(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String? = null, orderInfo: OrderInfo? = null) {

            this.id = id
            this.senderUserId = senderUserId
            this.currency = currency
            this.totalAmount = totalAmount
            this.invoicePayload = invoicePayload
            this.shippingOptionId = shippingOptionId
            this.orderInfo = orderInfo

        }

        override val constructor: Int @BsonIgnore get() = 87964006

    }


    /**
     * A new incoming event
     * For bots only
     *
     * @event - A JSON-serialized event
     */
    class UpdateNewCustomEvent : Update {

        var event: String by WeakField()

        constructor()

        constructor(event: String) {

            this.event = event

        }

        override val constructor: Int @BsonIgnore get() = 1994222092

    }


    /**
     * A new incoming query
     * For bots only
     *
     * @id - The query identifier
     * @data - JSON-serialized query data
     * @timeout - Query timeout
     */
    class UpdateNewCustomQuery : Update {

        var id: Long by WeakField()
        var data: String by WeakField()
        var timeout: Int by WeakField()

        constructor()

        constructor(id: Long, data: String, timeout: Int) {

            this.id = id
            this.data = data
            this.timeout = timeout

        }

        override val constructor: Int @BsonIgnore get() = -687670874

    }


    /**
     * Information about a poll was updated
     * For bots only
     *
     * @poll - New data about the poll
     */
    class UpdatePoll : Update {

        var poll: Poll by WeakField()

        constructor()

        constructor(poll: Poll) {

            this.poll = poll

        }

        override val constructor: Int @BsonIgnore get() = -1771342902

    }


    /**
     * Contains a list of updates
     *
     * @updates - List of updates
     */
    class Updates : Object {

        var updates: Array<Update> by WeakField()

        constructor()

        constructor(updates: Array<Update>) {

            this.updates = updates

        }

        override val constructor: Int @BsonIgnore get() = 475842347

    }


    /**
     * Describes a stream to which TDLib internal log is written
     */
    abstract class LogStream : Object()

    /**
     * The log is written to stderr or an OS specific log
     */
    class LogStreamDefault : LogStream() {

        override val constructor: Int @BsonIgnore get() = 1390581436

    }


    /**
     * The log is written to a file
     *
     * @path - Path to the file to where the internal TDLib log will be written
     * @maxFileSize - The maximum size of the file to where the internal TDLib log is written before the file will be auto-rotated
     */
    class LogStreamFile : LogStream {

        var path: String by WeakField()
        var maxFileSize: Long by WeakField()

        constructor()

        constructor(path: String, maxFileSize: Long) {

            this.path = path
            this.maxFileSize = maxFileSize

        }

        override val constructor: Int @BsonIgnore get() = -1880085930

    }


    /**
     * The log is written nowhere
     */
    class LogStreamEmpty : LogStream() {

        override val constructor: Int @BsonIgnore get() = -499912244

    }


    /**
     * Contains a TDLib internal log verbosity level
     *
     * @verbosityLevel - Log verbosity level
     */
    class LogVerbosityLevel : Object {

        var verbosityLevel: Int by WeakField()

        constructor()

        constructor(verbosityLevel: Int) {

            this.verbosityLevel = verbosityLevel

        }

        override val constructor: Int @BsonIgnore get() = 1734624234

    }


    /**
     * Contains a list of available TDLib internal log tags
     *
     * @tags - List of log tags
     */
    class LogTags : Object {

        var tags: Array<String> by WeakField()

        constructor()

        constructor(tags: Array<String>) {

            this.tags = tags

        }

        override val constructor: Int @BsonIgnore get() = -1604930601

    }


    /**
     * A simple object containing a number
     * For testing only
     *
     * @value - Number
     */
    class TestInt : Object {

        var value: Int by WeakField()

        constructor()

        constructor(value: Int) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = -574804983

    }


    /**
     * A simple object containing a string
     * For testing only
     *
     * @value - String
     */
    class TestString : Object {

        var value: String by WeakField()

        constructor()

        constructor(value: String) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = -27891572

    }


    /**
     * A simple object containing a sequence of bytes
     * For testing only
     *
     * @value - Bytes
     */
    class TestBytes : Object {

        var value: ByteArray by WeakField()

        constructor()

        constructor(value: ByteArray) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = -1541225250

    }


    /**
     * A simple object containing a vector of numbers
     * For testing only
     *
     * @value - Vector of numbers
     */
    class TestVectorInt : Object {

        var value: IntArray by WeakField()

        constructor()

        constructor(value: IntArray) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 593682027

    }


    /**
     * A simple object containing a vector of objects that hold a number
     * For testing only
     *
     * @value - Vector of objects
     */
    class TestVectorIntObject : Object {

        var value: Array<TestInt> by WeakField()

        constructor()

        constructor(value: Array<TestInt>) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 125891546

    }


    /**
     * A simple object containing a vector of strings
     * For testing only
     *
     * @value - Vector of strings
     */
    class TestVectorString : Object {

        var value: Array<String> by WeakField()

        constructor()

        constructor(value: Array<String>) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 79339995

    }


    /**
     * A simple object containing a vector of objects that hold a string
     * For testing only
     *
     * @value - Vector of objects
     */
    class TestVectorStringObject : Object {

        var value: Array<TestString> by WeakField()

        constructor()

        constructor(value: Array<TestString>) {

            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 80780537

    }


    /**
     * Returns the current authorization state
     * This is an offline request
     * For informational purposes only
     * Use updateAuthorizationState instead to maintain the current authorization state
     */
    class GetAuthorizationState : Function() {

        override val constructor: Int @BsonIgnore get() = 1949154877

    }


    /**
     * Sets the parameters for TDLib initialization
     * Works only when the current authorization state is authorizationStateWaitTdlibParameters
     *
     * @parameters - Parameters
     */
    class SetTdlibParameters : Function {

        var parameters: TdlibParameters? = null

        constructor()

        constructor(parameters: TdlibParameters? = null) {

            this.parameters = parameters

        }

        override val constructor: Int @BsonIgnore get() = -1912557997

    }


    /**
     * Checks the database encryption key for correctness
     * Works only when the current authorization state is authorizationStateWaitEncryptionKey
     *
     * @encryptionKey - Encryption key to check or set up
     */
    class CheckDatabaseEncryptionKey : Function {

        var encryptionKey: ByteArray by WeakField()

        constructor()

        constructor(encryptionKey: ByteArray) {

            this.encryptionKey = encryptionKey

        }

        override val constructor: Int @BsonIgnore get() = 1018769307

    }


    /**
     * Sets the phone number of the user and sends an authentication code to the user
     * Works only when the current authorization state is authorizationStateWaitPhoneNumber, or if there is no pending authentication query and the current authorization state is authorizationStateWaitCode, authorizationStateWaitRegistration, or authorizationStateWaitPassword
     *
     * @phoneNumber - The phone number of the user, in international format
     * @settings - Settings for the authentication of the user's phone number
     */
    class SetAuthenticationPhoneNumber : Function {

        var phoneNumber: String? = null
        var settings: PhoneNumberAuthenticationSettings? = null

        constructor()

        constructor(phoneNumber: String? = null, settings: PhoneNumberAuthenticationSettings? = null) {

            this.phoneNumber = phoneNumber
            this.settings = settings

        }

        override val constructor: Int @BsonIgnore get() = 868276259

    }


    /**
     * Re-sends an authentication code to the user
     * Works only when the current authorization state is authorizationStateWaitCode and the next_code_type of the result is not null
     */
    class ResendAuthenticationCode : Function() {

        override val constructor: Int @BsonIgnore get() = -814377191

    }


    /**
     * Checks the authentication code
     * Works only when the current authorization state is authorizationStateWaitCode
     *
     * @code - The verification code received via SMS, Telegram message, phone call, or flash call
     */
    class CheckAuthenticationCode : Function {

        var code: String? = null

        constructor()

        constructor(code: String? = null) {

            this.code = code

        }

        override val constructor: Int @BsonIgnore get() = -302103382

    }


    /**
     * Requests QR code authentication by scanning a QR code on another logged in device
     * Works only when the current authorization state is authorizationStateWaitPhoneNumber
     *
     * @otherUserIds - List of user identifiers of other users currently using the client
     */
    class RequestQrCodeAuthentication : Function {

        var otherUserIds: IntArray by WeakField()

        constructor()

        constructor(otherUserIds: IntArray) {

            this.otherUserIds = otherUserIds

        }

        override val constructor: Int @BsonIgnore get() = -104224560

    }


    /**
     * Finishes user registration
     * Works only when the current authorization state is authorizationStateWaitRegistration
     *
     * @firstName - The first name of the user
     * @lastName - The last name of the user
     */
    class RegisterUser : Function {

        var firstName: String? = null
        var lastName: String? = null

        constructor()

        constructor(firstName: String? = null, lastName: String? = null) {

            this.firstName = firstName
            this.lastName = lastName

        }

        override val constructor: Int @BsonIgnore get() = -109994467

    }


    /**
     * Checks the authentication password for correctness
     * Works only when the current authorization state is authorizationStateWaitPassword
     *
     * @password - The password to check
     */
    class CheckAuthenticationPassword : Function {

        var password: String? = null

        constructor()

        constructor(password: String? = null) {

            this.password = password

        }

        override val constructor: Int @BsonIgnore get() = -2025698400

    }


    /**
     * Requests to send a password recovery code to an email address that was previously set up
     * Works only when the current authorization state is authorizationStateWaitPassword
     */
    class RequestAuthenticationPasswordRecovery : Function() {

        override val constructor: Int @BsonIgnore get() = 1393896118

    }


    /**
     * Recovers the password with a password recovery code sent to an email address that was previously set up
     * Works only when the current authorization state is authorizationStateWaitPassword
     *
     * @recoveryCode - Recovery code to check
     */
    class RecoverAuthenticationPassword : Function {

        var recoveryCode: String? = null

        constructor()

        constructor(recoveryCode: String? = null) {

            this.recoveryCode = recoveryCode

        }

        override val constructor: Int @BsonIgnore get() = 787436412

    }


    /**
     * Checks the authentication token of a bot
     * To log in as a bot
     * Works only when the current authorization state is authorizationStateWaitPhoneNumber
     * Can be used instead of setAuthenticationPhoneNumber and checkAuthenticationCode to log in
     *
     * @token - The bot token
     */
    class CheckAuthenticationBotToken : Function {

        var token: String? = null

        constructor()

        constructor(token: String? = null) {

            this.token = token

        }

        override val constructor: Int @BsonIgnore get() = 639321206

    }


    /**
     * Closes the TDLib instance after a proper logout
     * Requires an available network connection
     * All local data will be destroyed
     * After the logout completes, updateAuthorizationState with authorizationStateClosed will be sent
     */
    class LogOut : Function() {

        override val constructor: Int @BsonIgnore get() = -1581923301

    }


    /**
     * Closes the TDLib instance
     * All databases will be flushed to disk and properly closed
     * After the close completes, updateAuthorizationState with authorizationStateClosed will be sent
     */
    class Close : Function() {

        override val constructor: Int @BsonIgnore get() = -1187782273

    }


    /**
     * Closes the TDLib instance, destroying all local data without a proper logout
     * The current user session will remain in the list of all active sessions
     * All local data will be destroyed
     * After the destruction completes updateAuthorizationState with authorizationStateClosed will be sent
     */
    class Destroy : Function() {

        override val constructor: Int @BsonIgnore get() = 685331274

    }


    /**
     * Confirms QR code authentication on another device
     * Returns created session on success
     *
     * @link - A link from a QR code
     *         The link must be scanned by the in-app camera
     */
    class ConfirmQrCodeAuthentication : Function {

        var link: String? = null

        constructor()

        constructor(link: String? = null) {

            this.link = link

        }

        override val constructor: Int @BsonIgnore get() = -376199379

    }


    /**
     * Returns all updates needed to restore current TDLib state, i.e
     * All actual UpdateAuthorizationState/UpdateUser/UpdateNewChat and others
     * This is especially usefull if TDLib is run in a separate process
     * This is an offline method
     * Can be called before authorization
     */
    class GetCurrentState : Function() {

        override val constructor: Int @BsonIgnore get() = -1191417719

    }


    /**
     * Changes the database encryption key
     * Usually the encryption key is never changed and is stored in some OS keychain
     *
     * @newEncryptionKey - New encryption key
     */
    class SetDatabaseEncryptionKey : Function {

        var newEncryptionKey: ByteArray by WeakField()

        constructor()

        constructor(newEncryptionKey: ByteArray) {

            this.newEncryptionKey = newEncryptionKey

        }

        override val constructor: Int @BsonIgnore get() = -1204599371

    }


    /**
     * Returns the current state of 2-step verification
     */
    class GetPasswordState : Function() {

        override val constructor: Int @BsonIgnore get() = -174752904

    }


    /**
     * Changes the password for the user
     * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
     *
     * @oldPassword - Previous password of the user
     * @newPassword - New password of the user
     *                May be empty to remove the password
     * @newHint - New password hint
     * @setRecoveryEmailAddress - Pass true if the recovery email address should be changed
     * @newRecoveryEmailAddress - New recovery email address
     */
    class SetPassword : Function {

        var oldPassword: String? = null
        var newPassword: String? = null
        var newHint: String? = null
        var setRecoveryEmailAddress: Boolean by WeakField()
        var newRecoveryEmailAddress: String? = null

        constructor()

        constructor(oldPassword: String? = null, newPassword: String? = null, newHint: String? = null, setRecoveryEmailAddress: Boolean, newRecoveryEmailAddress: String? = null) {

            this.oldPassword = oldPassword
            this.newPassword = newPassword
            this.newHint = newHint
            this.setRecoveryEmailAddress = setRecoveryEmailAddress
            this.newRecoveryEmailAddress = newRecoveryEmailAddress

        }

        override val constructor: Int @BsonIgnore get() = -1193589027

    }


    /**
     * Returns a 2-step verification recovery email address that was previously set up
     * This method can be used to verify a password provided by the user
     *
     * @password - The password for the current user
     */
    class GetRecoveryEmailAddress : Function {

        var password: String? = null

        constructor()

        constructor(password: String? = null) {

            this.password = password

        }

        override val constructor: Int @BsonIgnore get() = -1594770947

    }


    /**
     * Changes the 2-step verification recovery email address of the user
     * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
     * If new_recovery_email_address is the same as the email address that is currently set up, this call succeeds immediately and aborts all other requests waiting for an email confirmation
     *
     * @password - Password of the current user
     * @newRecoveryEmailAddress - New recovery email address
     */
    class SetRecoveryEmailAddress : Function {

        var password: String? = null
        var newRecoveryEmailAddress: String? = null

        constructor()

        constructor(password: String? = null, newRecoveryEmailAddress: String? = null) {

            this.password = password
            this.newRecoveryEmailAddress = newRecoveryEmailAddress

        }

        override val constructor: Int @BsonIgnore get() = -1981836385

    }


    /**
     * Checks the 2-step verification recovery email address verification code
     *
     * @code - Verification code
     */
    class CheckRecoveryEmailAddressCode : Function {

        var code: String? = null

        constructor()

        constructor(code: String? = null) {

            this.code = code

        }

        override val constructor: Int @BsonIgnore get() = -1997039589

    }


    /**
     * Resends the 2-step verification recovery email address verification code
     */
    class ResendRecoveryEmailAddressCode : Function() {

        override val constructor: Int @BsonIgnore get() = 433483548

    }


    /**
     * Requests to send a password recovery code to an email address that was previously set up
     */
    class RequestPasswordRecovery : Function() {

        override val constructor: Int @BsonIgnore get() = -13777582

    }


    /**
     * Recovers the password using a recovery code sent to an email address that was previously set up
     *
     * @recoveryCode - Recovery code to check
     */
    class RecoverPassword : Function {

        var recoveryCode: String? = null

        constructor()

        constructor(recoveryCode: String? = null) {

            this.recoveryCode = recoveryCode

        }

        override val constructor: Int @BsonIgnore get() = 1660185903

    }


    /**
     * Creates a new temporary password for processing payments
     *
     * @password - Persistent user password
     * @validFor - Time during which the temporary password will be valid, in seconds
     *             Should be between 60 and 86400
     */
    class CreateTemporaryPassword : Function {

        var password: String? = null
        var validFor: Int by WeakField()

        constructor()

        constructor(password: String? = null, validFor: Int) {

            this.password = password
            this.validFor = validFor

        }

        override val constructor: Int @BsonIgnore get() = -1626509434

    }


    /**
     * Returns information about the current temporary password
     */
    class GetTemporaryPasswordState : Function() {

        override val constructor: Int @BsonIgnore get() = -12670830

    }


    /**
     * Returns the current user
     */
    class GetMe : Function() {

        override val constructor: Int @BsonIgnore get() = -191516033

    }


    /**
     * Returns information about a user by their identifier
     * This is an offline request if the current user is not a bot
     *
     * @userId - User identifier
     */
    class GetUser : Function {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -47586017

    }


    /**
     * Returns full information about a user by their identifier
     *
     * @userId - User identifier
     */
    class GetUserFullInfo : Function {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -655443263

    }


    /**
     * Returns information about a basic group by its identifier
     * This is an offline request if the current user is not a bot
     *
     * @basicGroupId - Basic group identifier
     */
    class GetBasicGroup : Function {

        var basicGroupId: Int by WeakField()

        constructor()

        constructor(basicGroupId: Int) {

            this.basicGroupId = basicGroupId

        }

        override val constructor: Int @BsonIgnore get() = 561775568

    }


    /**
     * Returns full information about a basic group by its identifier
     *
     * @basicGroupId - Basic group identifier
     */
    class GetBasicGroupFullInfo : Function {

        var basicGroupId: Int by WeakField()

        constructor()

        constructor(basicGroupId: Int) {

            this.basicGroupId = basicGroupId

        }

        override val constructor: Int @BsonIgnore get() = 1770517905

    }


    /**
     * Returns information about a supergroup or a channel by its identifier
     * This is an offline request if the current user is not a bot
     *
     * @supergroupId - Supergroup or channel identifier
     */
    class GetSupergroup : Function {

        var supergroupId: Int by WeakField()

        constructor()

        constructor(supergroupId: Int) {

            this.supergroupId = supergroupId

        }

        override val constructor: Int @BsonIgnore get() = -2063063706

    }


    /**
     * Returns full information about a supergroup or a channel by its identifier, cached for up to 1 minute
     *
     * @supergroupId - Supergroup or channel identifier
     */
    class GetSupergroupFullInfo : Function {

        var supergroupId: Int by WeakField()

        constructor()

        constructor(supergroupId: Int) {

            this.supergroupId = supergroupId

        }

        override val constructor: Int @BsonIgnore get() = -1150331262

    }


    /**
     * Returns information about a secret chat by its identifier
     * This is an offline request
     *
     * @secretChatId - Secret chat identifier
     */
    class GetSecretChat : Function {

        var secretChatId: Int by WeakField()

        constructor()

        constructor(secretChatId: Int) {

            this.secretChatId = secretChatId

        }

        override val constructor: Int @BsonIgnore get() = 40599169

    }


    /**
     * Returns information about a chat by its identifier, this is an offline request if the current user is not a bot
     *
     * @chatId - Chat identifier
     */
    class GetChat : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 1866601536

    }


    /**
     * Returns information about a message
     *
     * @chatId - Identifier of the chat the message belongs to
     * @messageId - Identifier of the message to get
     */
    class GetMessage : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = -1821196160

    }


    /**
     * Returns information about a message, if it is available locally without sending network request
     * This is an offline request
     *
     * @chatId - Identifier of the chat the message belongs to
     * @messageId - Identifier of the message to get
     */
    class GetMessageLocally : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = -603575444

    }


    /**
     * Returns information about a message that is replied by given message
     *
     * @chatId - Identifier of the chat the message belongs to
     * @messageId - Identifier of the message reply to which get
     */
    class GetRepliedMessage : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = -641918531

    }


    /**
     * Returns information about a pinned chat message
     *
     * @chatId - Identifier of the chat the message belongs to
     */
    class GetChatPinnedMessage : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 359865008

    }


    /**
     * Returns information about messages
     * If a message is not found, returns null on the corresponding position of the result
     *
     * @chatId - Identifier of the chat the messages belong to
     * @messageIds - Identifiers of the messages to get
     */
    class GetMessages : Function {

        var chatId: Long by WeakField()
        var messageIds: LongArray by WeakField()

        constructor()

        constructor(chatId: Long, messageIds: LongArray) {

            this.chatId = chatId
            this.messageIds = messageIds

        }

        override val constructor: Int @BsonIgnore get() = 425299338

    }


    /**
     * Returns information about a file
     * This is an offline request
     *
     * @fileId - Identifier of the file to get
     */
    class GetFile : Function {

        var fileId: Int by WeakField()

        constructor()

        constructor(fileId: Int) {

            this.fileId = fileId

        }

        override val constructor: Int @BsonIgnore get() = 1553923406

    }


    /**
     * Returns information about a file by its remote ID
     * This is an offline request
     * Can be used to register a URL as a file for further uploading, or sending as a message
     * Even the request succeeds, the file can be used only if it is still accessible to the user
     * For example, if the file is from a message, then the message must be not deleted and accessible to the user
     * If the file database is disabled, then the corresponding object with the file must be preloaded by the client
     *
     * @remoteFileId - Remote identifier of the file to get
     * @fileType - File type, if known
     */
    class GetRemoteFile : Function {

        var remoteFileId: String? = null
        var fileType: FileType? = null

        constructor()

        constructor(remoteFileId: String? = null, fileType: FileType? = null) {

            this.remoteFileId = remoteFileId
            this.fileType = fileType

        }

        override val constructor: Int @BsonIgnore get() = 2137204530

    }


    /**
     * Returns an ordered list of chats in a chat list
     * Chats are sorted by the pair (order, chat_id) in decreasing order
     * (For example, to get a list of chats from the beginning, the offset_order should be equal to a biggest signed 64-bit number 9223372036854775807 == 2^63 - 1)
     * For optimal performance the number of returned chats is chosen by the library
     *
     * @chatList - The chat list in which to return chats
     * @offsetOrder - Chat order to return chats from
     * @offsetChatId - Chat identifier to return chats from
     * @limit - The maximum number of chats to be returned
     *          It is possible that fewer chats than the limit are returned even if the end of the list is not reached
     */
    class GetChats : Function {

        var chatList: ChatList? = null
        var offsetOrder: Long by WeakField()
        var offsetChatId: Long by WeakField()
        var limit: Int by WeakField()

        constructor()

        constructor(chatList: ChatList? = null, offsetOrder: Long, offsetChatId: Long, limit: Int) {

            this.chatList = chatList
            this.offsetOrder = offsetOrder
            this.offsetChatId = offsetChatId
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = 1847129537

    }


    /**
     * Searches a public chat by its username
     * Currently only private chats, supergroups and channels can be public
     * Returns the chat if found
     * Otherwise an error is returned
     *
     * @username - Username to be resolved
     */
    class SearchPublicChat : Function {

        var username: String? = null

        constructor()

        constructor(username: String? = null) {

            this.username = username

        }

        override val constructor: Int @BsonIgnore get() = 857135533

    }


    /**
     * Searches public chats by looking for specified query in their username and title
     * Currently only private chats, supergroups and channels can be public
     * Returns a meaningful number of results
     * Returns nothing if the length of the searched username prefix is less than 5
     * Excludes private chats with contacts and chats from the chat list from the results
     *
     * @query - Query to search for
     */
    class SearchPublicChats : Function {

        var query: String? = null

        constructor()

        constructor(query: String? = null) {

            this.query = query

        }

        override val constructor: Int @BsonIgnore get() = 970385337

    }


    /**
     * Searches for the specified query in the title and username of already known chats, this is an offline request
     * Returns chats in the order seen in the chat list
     *
     * @query - Query to search for
     *          If the query is empty, returns up to 20 recently found chats
     * @limit - The maximum number of chats to be returned
     */
    class SearchChats : Function {

        var query: String? = null
        var limit: Int by WeakField()

        constructor()

        constructor(query: String? = null, limit: Int) {

            this.query = query
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = -1879787060

    }


    /**
     * Searches for the specified query in the title and username of already known chats via request to the server
     * Returns chats in the order seen in the chat list
     *
     * @query - Query to search for
     * @limit - The maximum number of chats to be returned
     */
    class SearchChatsOnServer : Function {

        var query: String? = null
        var limit: Int by WeakField()

        constructor()

        constructor(query: String? = null, limit: Int) {

            this.query = query
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = -1158402188

    }


    /**
     * Returns a list of users and location-based supergroups nearby
     * The list of users nearby will be updated for 60 seconds after the request by the updates updateUsersNearby
     * The request should be sent again every 25 seconds with adjusted location to not miss new chats
     *
     * @location - Current user location
     */
    class SearchChatsNearby : Function {

        var location: Location? = null

        constructor()

        constructor(location: Location? = null) {

            this.location = location

        }

        override val constructor: Int @BsonIgnore get() = -196753377

    }


    /**
     * Returns a list of frequently used chats
     * Supported only if the chat info database is enabled
     *
     * @category - Category of chats to be returned
     * @limit - The maximum number of chats to be returned
     *          Up to 30
     */
    class GetTopChats : Function {

        var category: TopChatCategory? = null
        var limit: Int by WeakField()

        constructor()

        constructor(category: TopChatCategory? = null, limit: Int) {

            this.category = category
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = -388410847

    }


    /**
     * Removes a chat from the list of frequently used chats
     * Supported only if the chat info database is enabled
     *
     * @category - Category of frequently used chats
     * @chatId - Chat identifier
     */
    class RemoveTopChat : Function {

        var category: TopChatCategory? = null
        var chatId: Long by WeakField()

        constructor()

        constructor(category: TopChatCategory? = null, chatId: Long) {

            this.category = category
            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = -1907876267

    }


    /**
     * Adds a chat to the list of recently found chats
     * The chat is added to the beginning of the list
     * If the chat is already in the list, it will be removed from the list first
     *
     * @chatId - Identifier of the chat to add
     */
    class AddRecentlyFoundChat : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = -1746396787

    }


    /**
     * Removes a chat from the list of recently found chats
     *
     * @chatId - Identifier of the chat to be removed
     */
    class RemoveRecentlyFoundChat : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 717340444

    }


    /**
     * Clears the list of recently found chats
     */
    class ClearRecentlyFoundChats : Function() {

        override val constructor: Int @BsonIgnore get() = -285582542

    }


    /**
     * Checks whether a username can be set for a chat
     *
     * @chatId - Chat identifier
     *           Should be identifier of a supergroup chat, or a channel chat, or a private chat with self, or zero if chat is being created
     * @username - Username to be checked
     */
    class CheckChatUsername : Function {

        var chatId: Long by WeakField()
        var username: String? = null

        constructor()

        constructor(chatId: Long, username: String? = null) {

            this.chatId = chatId
            this.username = username

        }

        override val constructor: Int @BsonIgnore get() = -119119344

    }


    /**
     * Returns a list of public chats of the specified type, owned by the user
     *
     * @type - Type of the public chats to return
     */
    class GetCreatedPublicChats : Function {

        var type: PublicChatType? = null

        constructor()

        constructor(type: PublicChatType? = null) {

            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = 710354415

    }


    /**
     * Checks whether the maximum number of owned public chats has been reached
     * Returns corresponding error if the limit was reached
     *
     * @type - Type of the public chats, for which to check the limit
     */
    class CheckCreatedPublicChatsLimit : Function {

        var type: PublicChatType? = null

        constructor()

        constructor(type: PublicChatType? = null) {

            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -445546591

    }


    /**
     * Returns a list of basic group and supergroup chats, which can be used as a discussion group for a channel
     * Basic group chats need to be first upgraded to supergroups before they can be set as a discussion group
     */
    class GetSuitableDiscussionChats : Function() {

        override val constructor: Int @BsonIgnore get() = 49044982

    }


    /**
     * Returns a list of recently inactive supergroups and channels
     * Can be used when user reaches limit on the number of joined supergroups and channels and receives CHANNELS_TOO_MUCH error
     */
    class GetInactiveSupergroupChats : Function() {

        override val constructor: Int @BsonIgnore get() = -657720907

    }


    /**
     * Returns a list of common group chats with a given user
     * Chats are sorted by their type and creation date
     *
     * @userId - User identifier
     * @offsetChatId - Chat identifier starting from which to return chats
     *                 Use 0 for the first request
     * @limit - The maximum number of chats to be returned
     */
    class GetGroupsInCommon : Function {

        var userId: Int by WeakField()
        var offsetChatId: Long by WeakField()
        var limit: Int by WeakField()

        constructor()

        constructor(userId: Int, offsetChatId: Long, limit: Int) {

            this.userId = userId
            this.offsetChatId = offsetChatId
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = -23238689

    }


    /**
     * Returns messages in a chat
     * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
     * For optimal performance the number of returned messages is chosen by the library
     * This is an offline request if only_local is true
     *
     * @chatId - Chat identifier
     * @fromMessageId - Identifier of the message starting from which history must be fetched
     *                  Use 0 to get results from the last message
     * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset up to 99 to get additionally some newer messages
     * @limit - The maximum number of messages to be returned
     *          Must be positive and can't be greater than 100
     *          If the offset is negative, the limit must be greater or equal to -offset
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     * @onlyLocal - If true, returns only messages that are available locally without sending network requests
     */
    class GetChatHistory : Function {

        var chatId: Long by WeakField()
        var fromMessageId: Long by WeakField()
        var offset: Int by WeakField()
        var limit: Int by WeakField()
        var onlyLocal: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, fromMessageId: Long, offset: Int, limit: Int, onlyLocal: Boolean) {

            this.chatId = chatId
            this.fromMessageId = fromMessageId
            this.offset = offset
            this.limit = limit
            this.onlyLocal = onlyLocal

        }

        override val constructor: Int @BsonIgnore get() = -799960451

    }


    /**
     * Deletes all messages in the chat
     * Use Chat.can_be_deleted_only_for_self and Chat.can_be_deleted_for_all_users fields to find whether and how the method can be applied to the chat
     *
     * @chatId - Chat identifier
     * @removeFromChatList - Pass true if the chat should be removed from the chat list
     * @revoke - Pass true to try to delete chat history for all users
     */
    class DeleteChatHistory : Function {

        var chatId: Long by WeakField()
        var removeFromChatList: Boolean by WeakField()
        var revoke: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, removeFromChatList: Boolean, revoke: Boolean) {

            this.chatId = chatId
            this.removeFromChatList = removeFromChatList
            this.revoke = revoke

        }

        override val constructor: Int @BsonIgnore get() = -1472081761

    }


    /**
     * Searches for messages with given words in the chat
     * Returns the results in reverse chronological order, i.e
     * In order of decreasing message_id
     * Cannot be used in secret chats with a non-empty query (searchSecretMessages should be used instead), or without an enabled message database
     * For optimal performance the number of returned messages is chosen by the library
     *
     * @chatId - Identifier of the chat in which to search messages
     * @query - Query to search for
     * @senderUserId - If not 0, only messages sent by the specified user will be returned
     *                 Not supported in secret chats
     * @fromMessageId - Identifier of the message starting from which history must be fetched
     *                  Use 0 to get results from the last message
     * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset to get the specified message and some newer messages
     * @limit - The maximum number of messages to be returned
     *          Must be positive and can't be greater than 100
     *          If the offset is negative, the limit must be greater than -offset
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     * @filter - Filter for message content in the search results
     */
    class SearchChatMessages : Function {

        var chatId: Long by WeakField()
        var query: String? = null
        var senderUserId: Int by WeakField()
        var fromMessageId: Long by WeakField()
        var offset: Int by WeakField()
        var limit: Int by WeakField()
        var filter: SearchMessagesFilter? = null

        constructor()

        constructor(chatId: Long, query: String? = null, senderUserId: Int, fromMessageId: Long, offset: Int, limit: Int, filter: SearchMessagesFilter? = null) {

            this.chatId = chatId
            this.query = query
            this.senderUserId = senderUserId
            this.fromMessageId = fromMessageId
            this.offset = offset
            this.limit = limit
            this.filter = filter

        }

        override val constructor: Int @BsonIgnore get() = -1528846671

    }


    /**
     * Searches for messages in all chats except secret chats
     * Returns the results in reverse chronological order (i.e., in order of decreasing (date, chat_id, message_id))
     * For optimal performance the number of returned messages is chosen by the library
     *
     * @chatList - Chat list in which to search messages
     *             Pass null to search in all chats regardless of their chat list
     * @query - Query to search for
     * @offsetDate - The date of the message starting from which the results should be fetched
     *               Use 0 or any date in the future to get results from the last message
     * @offsetChatId - The chat identifier of the last found message, or 0 for the first request
     * @offsetMessageId - The message identifier of the last found message, or 0 for the first request
     * @limit - The maximum number of messages to be returned, up to 100
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     */
    class SearchMessages : Function {

        var chatList: ChatList? = null
        var query: String? = null
        var offsetDate: Int by WeakField()
        var offsetChatId: Long by WeakField()
        var offsetMessageId: Long by WeakField()
        var limit: Int by WeakField()

        constructor()

        constructor(chatList: ChatList? = null, query: String? = null, offsetDate: Int, offsetChatId: Long, offsetMessageId: Long, limit: Int) {

            this.chatList = chatList
            this.query = query
            this.offsetDate = offsetDate
            this.offsetChatId = offsetChatId
            this.offsetMessageId = offsetMessageId
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = -455843835

    }


    /**
     * Searches for messages in secret chats
     * Returns the results in reverse chronological order
     * For optimal performance the number of returned messages is chosen by the library
     *
     * @chatId - Identifier of the chat in which to search
     *           Specify 0 to search in all secret chats
     * @query - Query to search for
     *          If empty, searchChatMessages should be used instead
     * @fromSearchId - The identifier from the result of a previous request, use 0 to get results from the last message
     * @limit - The maximum number of messages to be returned
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     * @filter - A filter for the content of messages in the search results
     */
    class SearchSecretMessages : Function {

        var chatId: Long by WeakField()
        var query: String? = null
        var fromSearchId: Long by WeakField()
        var limit: Int by WeakField()
        var filter: SearchMessagesFilter? = null

        constructor()

        constructor(chatId: Long, query: String? = null, fromSearchId: Long, limit: Int, filter: SearchMessagesFilter? = null) {

            this.chatId = chatId
            this.query = query
            this.fromSearchId = fromSearchId
            this.limit = limit
            this.filter = filter

        }

        override val constructor: Int @BsonIgnore get() = -1670627915

    }


    /**
     * Searches for call messages
     * Returns the results in reverse chronological order (i
     * E., in order of decreasing message_id)
     * For optimal performance the number of returned messages is chosen by the library
     *
     * @fromMessageId - Identifier of the message from which to search
     *                  Use 0 to get results from the last message
     * @limit - The maximum number of messages to be returned
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     * @onlyMissed - If true, returns only messages with missed calls
     */
    class SearchCallMessages : Function {

        var fromMessageId: Long by WeakField()
        var limit: Int by WeakField()
        var onlyMissed: Boolean by WeakField()

        constructor()

        constructor(fromMessageId: Long, limit: Int, onlyMissed: Boolean) {

            this.fromMessageId = fromMessageId
            this.limit = limit
            this.onlyMissed = onlyMissed

        }

        override val constructor: Int @BsonIgnore get() = -1077230820

    }


    /**
     * Returns information about the recent locations of chat members that were sent to the chat
     * Returns up to 1 location message per user
     *
     * @chatId - Chat identifier
     * @limit - The maximum number of messages to be returned
     */
    class SearchChatRecentLocationMessages : Function {

        var chatId: Long by WeakField()
        var limit: Int by WeakField()

        constructor()

        constructor(chatId: Long, limit: Int) {

            this.chatId = chatId
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = 950238950

    }


    /**
     * Returns all active live locations that should be updated by the client
     * The list is persistent across application restarts only if the message database is used
     */
    class GetActiveLiveLocationMessages : Function() {

        override val constructor: Int @BsonIgnore get() = -1425459567

    }


    /**
     * Returns the last message sent in a chat no later than the specified date
     *
     * @chatId - Chat identifier
     * @date - Point in time (Unix timestamp) relative to which to search for messages
     */
    class GetChatMessageByDate : Function {

        var chatId: Long by WeakField()
        var date: Int by WeakField()

        constructor()

        constructor(chatId: Long, date: Int) {

            this.chatId = chatId
            this.date = date

        }

        override val constructor: Int @BsonIgnore get() = 1062564150

    }


    /**
     * Returns approximate number of messages of the specified type in the chat
     *
     * @chatId - Identifier of the chat in which to count messages
     * @filter - Filter for message content
     *           SearchMessagesFilterEmpty is unsupported in this function
     * @returnLocal - If true, returns count that is available locally without sending network requests, returning -1 if the number of messages is unknown
     */
    class GetChatMessageCount : Function {

        var chatId: Long by WeakField()
        var filter: SearchMessagesFilter? = null
        var returnLocal: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, filter: SearchMessagesFilter? = null, returnLocal: Boolean) {

            this.chatId = chatId
            this.filter = filter
            this.returnLocal = returnLocal

        }

        override val constructor: Int @BsonIgnore get() = 205435308

    }


    /**
     * Returns all scheduled messages in a chat
     * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
     *
     * @chatId - Chat identifier
     */
    class GetChatScheduledMessages : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = -549638149

    }


    /**
     * Removes an active notification from notification list
     * Needs to be called only if the notification is removed by the current user
     *
     * @notificationGroupId - Identifier of notification group to which the notification belongs
     * @notificationId - Identifier of removed notification
     */
    class RemoveNotification : Function {

        var notificationGroupId: Int by WeakField()
        var notificationId: Int by WeakField()

        constructor()

        constructor(notificationGroupId: Int, notificationId: Int) {

            this.notificationGroupId = notificationGroupId
            this.notificationId = notificationId

        }

        override val constructor: Int @BsonIgnore get() = 862630734

    }


    /**
     * Removes a group of active notifications
     * Needs to be called only if the notification group is removed by the current user
     *
     * @notificationGroupId - Notification group identifier
     * @maxNotificationId - The maximum identifier of removed notifications
     */
    class RemoveNotificationGroup : Function {

        var notificationGroupId: Int by WeakField()
        var maxNotificationId: Int by WeakField()

        constructor()

        constructor(notificationGroupId: Int, maxNotificationId: Int) {

            this.notificationGroupId = notificationGroupId
            this.maxNotificationId = maxNotificationId

        }

        override val constructor: Int @BsonIgnore get() = 1713005454

    }


    /**
     * Returns a public HTTPS link to a message
     * Available only for messages in supergroups and channels with a username
     *
     * @chatId - Identifier of the chat to which the message belongs
     * @messageId - Identifier of the message
     * @forAlbum - Pass true if a link for a whole media album should be returned
     */
    class GetPublicMessageLink : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var forAlbum: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, forAlbum: Boolean) {

            this.chatId = chatId
            this.messageId = messageId
            this.forAlbum = forAlbum

        }

        override val constructor: Int @BsonIgnore get() = -374642839

    }


    /**
     * Returns a private HTTPS link to a message in a chat
     * Available only for already sent messages in supergroups and channels
     * The link will work only for members of the chat
     *
     * @chatId - Identifier of the chat to which the message belongs
     * @messageId - Identifier of the message
     */
    class GetMessageLink : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = 1362732326

    }


    /**
     * Returns information about a public or private message link
     *
     * @url - The message link in the format "https://t.me/c/...", or "tg://privatepost?...", or "https://t.me/username/...", or "tg://resolve?..."
     */
    class GetMessageLinkInfo : Function {

        var url: String? = null

        constructor()

        constructor(url: String? = null) {

            this.url = url

        }

        override val constructor: Int @BsonIgnore get() = -700533672

    }


    /**
     * Sends a message
     * Returns the sent message
     *
     * @chatId - Target chat
     * @replyToMessageId - Identifier of the message to reply to or 0
     * @options - Options to be used to send the message
     * @replyMarkup - Markup for replying to the message
     *                For bots only
     * @inputMessageContent - The content of the message to be sent
     */
    class SendMessage : Function {

        var chatId: Long by WeakField()
        var replyToMessageId: Long by WeakField()
        var options: SendMessageOptions? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(chatId: Long, replyToMessageId: Long, options: SendMessageOptions? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.chatId = chatId
            this.replyToMessageId = replyToMessageId
            this.options = options
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = -1314396596

    }


    /**
     * Sends messages grouped together into an album
     * Currently only photo and video messages can be grouped into an album
     * Returns sent messages
     *
     * @chatId - Target chat
     * @replyToMessageId - Identifier of a message to reply to or 0
     * @options - Options to be used to send the messages
     * @inputMessageContents - Contents of messages to be sent
     */
    class SendMessageAlbum : Function {

        var chatId: Long by WeakField()
        var replyToMessageId: Long by WeakField()
        var options: SendMessageOptions? = null
        var inputMessageContents: Array<InputMessageContent> by WeakField()

        constructor()

        constructor(chatId: Long, replyToMessageId: Long, options: SendMessageOptions? = null, inputMessageContents: Array<InputMessageContent>) {

            this.chatId = chatId
            this.replyToMessageId = replyToMessageId
            this.options = options
            this.inputMessageContents = inputMessageContents

        }

        override val constructor: Int @BsonIgnore get() = -818794592

    }


    /**
     * Invites a bot to a chat (if it is not yet a member) and sends it the /start command
     * Bots can't be invited to a private chat other than the chat with the bot
     * Bots can't be invited to channels (although they can be added as admins) and secret chats
     * Returns the sent message
     *
     * @botUserId - Identifier of the bot
     * @chatId - Identifier of the target chat
     * @parameter - A hidden parameter sent to the bot for deep linking purposes (https://core.telegram.org/bots#deep-linking)
     */
    class SendBotStartMessage : Function {

        var botUserId: Int by WeakField()
        var chatId: Long by WeakField()
        var parameter: String? = null

        constructor()

        constructor(botUserId: Int, chatId: Long, parameter: String? = null) {

            this.botUserId = botUserId
            this.chatId = chatId
            this.parameter = parameter

        }

        override val constructor: Int @BsonIgnore get() = 1112181339

    }


    /**
     * Sends the result of an inline query as a message
     * Returns the sent message
     * Always clears a chat draft message
     *
     * @chatId - Target chat
     * @replyToMessageId - Identifier of a message to reply to or 0
     * @options - Options to be used to send the message
     * @queryId - Identifier of the inline query
     * @resultId - Identifier of the inline result
     * @hideViaBot - If true, there will be no mention of a bot, via which the message is sent
     *               Can be used only for bots GetOption("animation_search_bot_username"), GetOption("photo_search_bot_username") and GetOption("venue_search_bot_username")
     */
    class SendInlineQueryResultMessage : Function {

        var chatId: Long by WeakField()
        var replyToMessageId: Long by WeakField()
        var options: SendMessageOptions? = null
        var queryId: Long by WeakField()
        var resultId: String? = null
        var hideViaBot: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, replyToMessageId: Long, options: SendMessageOptions? = null, queryId: Long, resultId: String? = null, hideViaBot: Boolean) {

            this.chatId = chatId
            this.replyToMessageId = replyToMessageId
            this.options = options
            this.queryId = queryId
            this.resultId = resultId
            this.hideViaBot = hideViaBot

        }

        override val constructor: Int @BsonIgnore get() = 729880339

    }


    /**
     * Forwards previously sent messages
     * Returns the forwarded messages in the same order as the message identifiers passed in message_ids
     * If a message can't be forwarded, null will be returned instead of the message
     *
     * @chatId - Identifier of the chat to which to forward messages
     * @fromChatId - Identifier of the chat from which to forward messages
     * @messageIds - Identifiers of the messages to forward
     * @options - Options to be used to send the messages
     * @asAlbum - True, if the messages should be grouped into an album after forwarding
     *            For this to work, no more than 10 messages may be forwarded, and all of them must be photo or video messages
     * @sendCopy - True, if content of the messages needs to be copied without links to the original messages
     *             Always true if the messages are forwarded to a secret chat
     * @removeCaption - True, if media captions of message copies needs to be removed
     *                  Ignored if send_copy is false
     */
    class ForwardMessages : Function {

        var chatId: Long by WeakField()
        var fromChatId: Long by WeakField()
        var messageIds: LongArray by WeakField()
        var options: SendMessageOptions? = null
        var asAlbum: Boolean by WeakField()
        var sendCopy: Boolean by WeakField()
        var removeCaption: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, fromChatId: Long, messageIds: LongArray, options: SendMessageOptions? = null, asAlbum: Boolean, sendCopy: Boolean, removeCaption: Boolean) {

            this.chatId = chatId
            this.fromChatId = fromChatId
            this.messageIds = messageIds
            this.options = options
            this.asAlbum = asAlbum
            this.sendCopy = sendCopy
            this.removeCaption = removeCaption

        }

        override val constructor: Int @BsonIgnore get() = -1633531094

    }


    /**
     * Resends messages which failed to send
     * Can be called only for messages for which messageSendingStateFailed.can_retry is true and after specified in messageSendingStateFailed.retry_after time passed
     * If a message is re-sent, the corresponding failed to send message is deleted
     * Returns the sent messages in the same order as the message identifiers passed in message_ids
     * If a message can't be re-sent, null will be returned instead of the message
     *
     * @chatId - Identifier of the chat to send messages
     * @messageIds - Identifiers of the messages to resend
     *               Message identifiers must be in a strictly increasing order
     */
    class ResendMessages : Function {

        var chatId: Long by WeakField()
        var messageIds: LongArray by WeakField()

        constructor()

        constructor(chatId: Long, messageIds: LongArray) {

            this.chatId = chatId
            this.messageIds = messageIds

        }

        override val constructor: Int @BsonIgnore get() = -940655817

    }


    /**
     * Changes the current TTL setting (sets a new self-destruct timer) in a secret chat and sends the corresponding message
     *
     * @chatId - Chat identifier
     * @ttl - New TTL value, in seconds
     */
    class SendChatSetTtlMessage : Function {

        var chatId: Long by WeakField()
        var ttl: Int by WeakField()

        constructor()

        constructor(chatId: Long, ttl: Int) {

            this.chatId = chatId
            this.ttl = ttl

        }

        override val constructor: Int @BsonIgnore get() = 1432535564

    }


    /**
     * Sends a notification about a screenshot taken in a chat
     * Supported only in private and secret chats
     *
     * @chatId - Chat identifier
     */
    class SendChatScreenshotTakenNotification : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 448399457

    }


    /**
     * Adds a local message to a chat
     * The message is persistent across application restarts only if the message database is used
     * Returns the added message
     *
     * @chatId - Target chat
     * @senderUserId - Identifier of the user who will be shown as the sender of the message
     *                 May be 0 for channel posts
     * @replyToMessageId - Identifier of the message to reply to or 0
     * @disableNotification - Pass true to disable notification for the message
     * @inputMessageContent - The content of the message to be added
     */
    class AddLocalMessage : Function {

        var chatId: Long by WeakField()
        var senderUserId: Int by WeakField()
        var replyToMessageId: Long by WeakField()
        var disableNotification: Boolean by WeakField()
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(chatId: Long, senderUserId: Int, replyToMessageId: Long, disableNotification: Boolean, inputMessageContent: InputMessageContent? = null) {

            this.chatId = chatId
            this.senderUserId = senderUserId
            this.replyToMessageId = replyToMessageId
            this.disableNotification = disableNotification
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = -348943149

    }


    /**
     * Deletes messages
     *
     * @chatId - Chat identifier
     * @messageIds - Identifiers of the messages to be deleted
     * @revoke - Pass true to try to delete messages for all chat members
     *           Always true for supergroups, channels and secret chats
     */
    class DeleteMessages : Function {

        var chatId: Long by WeakField()
        var messageIds: LongArray by WeakField()
        var revoke: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, messageIds: LongArray, revoke: Boolean) {

            this.chatId = chatId
            this.messageIds = messageIds
            this.revoke = revoke

        }

        override val constructor: Int @BsonIgnore get() = 1130090173

    }


    /**
     * Deletes all messages sent by the specified user to a chat
     * Supported only for supergroups
     * Requires can_delete_messages administrator privileges
     *
     * @chatId - Chat identifier
     * @userId - User identifier
     */
    class DeleteChatMessagesFromUser : Function {

        var chatId: Long by WeakField()
        var userId: Int by WeakField()

        constructor()

        constructor(chatId: Long, userId: Int) {

            this.chatId = chatId
            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -1599689199

    }


    /**
     * Edits the text of a message (or a text of a game message)
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @inputMessageContent - New text content of the message
     *                        Should be of type InputMessageText
     */
    class EditMessageText : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(chatId: Long, messageId: Long, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = 196272567

    }


    /**
     * Edits the message content of a live location
     * Messages can be edited for a limited period of time specified in the live location
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @location - New location content of the message
     *             Pass null to stop sharing the live location
     */
    class EditMessageLiveLocation : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var replyMarkup: ReplyMarkup? = null
        var location: Location? = null

        constructor()

        constructor(chatId: Long, messageId: Long, replyMarkup: ReplyMarkup? = null, location: Location? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.replyMarkup = replyMarkup
            this.location = location

        }

        override val constructor: Int @BsonIgnore get() = -1146772745

    }


    /**
     * Edits the content of a message with an animation, an audio, a document, a photo or a video
     * The media in the message can't be replaced if the message was set to self-destruct
     * Media can't be replaced by self-destructing media
     * Media in an album can be edited only to contain a photo or a video
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @inputMessageContent - New content of the message
     *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
     */
    class EditMessageMedia : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(chatId: Long, messageId: Long, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = -1152678125

    }


    /**
     * Edits the message content caption
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @caption - New message content caption
     *            0-GetOption("message_caption_length_max") characters
     */
    class EditMessageCaption : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var replyMarkup: ReplyMarkup? = null
        var caption: FormattedText? = null

        constructor()

        constructor(chatId: Long, messageId: Long, replyMarkup: ReplyMarkup? = null, caption: FormattedText? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.replyMarkup = replyMarkup
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = 1154677038

    }


    /**
     * Edits the message reply markup
     * For bots only
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     */
    class EditMessageReplyMarkup : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var replyMarkup: ReplyMarkup? = null

        constructor()

        constructor(chatId: Long, messageId: Long, replyMarkup: ReplyMarkup? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.replyMarkup = replyMarkup

        }

        override val constructor: Int @BsonIgnore get() = 332127881

    }


    /**
     * Edits the text of an inline text or game message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     * @inputMessageContent - New text content of the message
     *                        Should be of type InputMessageText
     */
    class EditInlineMessageText : Function {

        var inlineMessageId: String? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(inlineMessageId: String? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.inlineMessageId = inlineMessageId
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = -855457307

    }


    /**
     * Edits the content of a live location in an inline message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     * @location - New location content of the message
     *             Pass null to stop sharing the live location
     */
    class EditInlineMessageLiveLocation : Function {

        var inlineMessageId: String? = null
        var replyMarkup: ReplyMarkup? = null
        var location: Location? = null

        constructor()

        constructor(inlineMessageId: String? = null, replyMarkup: ReplyMarkup? = null, location: Location? = null) {

            this.inlineMessageId = inlineMessageId
            this.replyMarkup = replyMarkup
            this.location = location

        }

        override val constructor: Int @BsonIgnore get() = 655046316

    }


    /**
     * Edits the content of a message with an animation, an audio, a document, a photo or a video in an inline message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @inputMessageContent - New content of the message
     *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
     */
    class EditInlineMessageMedia : Function {

        var inlineMessageId: String? = null
        var replyMarkup: ReplyMarkup? = null
        var inputMessageContent: InputMessageContent? = null

        constructor()

        constructor(inlineMessageId: String? = null, replyMarkup: ReplyMarkup? = null, inputMessageContent: InputMessageContent? = null) {

            this.inlineMessageId = inlineMessageId
            this.replyMarkup = replyMarkup
            this.inputMessageContent = inputMessageContent

        }

        override val constructor: Int @BsonIgnore get() = 23553921

    }


    /**
     * Edits the caption of an inline message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     * @caption - New message content caption
     *            0-GetOption("message_caption_length_max") characters
     */
    class EditInlineMessageCaption : Function {

        var inlineMessageId: String? = null
        var replyMarkup: ReplyMarkup? = null
        var caption: FormattedText? = null

        constructor()

        constructor(inlineMessageId: String? = null, replyMarkup: ReplyMarkup? = null, caption: FormattedText? = null) {

            this.inlineMessageId = inlineMessageId
            this.replyMarkup = replyMarkup
            this.caption = caption

        }

        override val constructor: Int @BsonIgnore get() = -760985929

    }


    /**
     * Edits the reply markup of an inline message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     */
    class EditInlineMessageReplyMarkup : Function {

        var inlineMessageId: String? = null
        var replyMarkup: ReplyMarkup? = null

        constructor()

        constructor(inlineMessageId: String? = null, replyMarkup: ReplyMarkup? = null) {

            this.inlineMessageId = inlineMessageId
            this.replyMarkup = replyMarkup

        }

        override val constructor: Int @BsonIgnore get() = -67565858

    }


    /**
     * Edits the time when a scheduled message will be sent
     * Scheduling state of all messages in the same album or forwarded together with the message will be also changed
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @schedulingState - The new message scheduling state
     *                    Pass null to send the message immediately
     */
    class EditMessageSchedulingState : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var schedulingState: MessageSchedulingState? = null

        constructor()

        constructor(chatId: Long, messageId: Long, schedulingState: MessageSchedulingState? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.schedulingState = schedulingState

        }

        override val constructor: Int @BsonIgnore get() = -1372976192

    }


    /**
     * Returns all entities (mentions, hashtags, cashtags, bot commands, URLs, and email addresses) contained in the text
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @text - The text in which to look for entites
     */
    class GetTextEntities : Function {

        var text: String? = null

        constructor()

        constructor(text: String? = null) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = -341490693

    }


    /**
     * Parses Bold, Italic, Underline, Strikethrough, Code, Pre, PreCode, TextUrl and MentionName entities contained in the text
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @text - The text which should be parsed
     * @parseMode - Text parse mode
     */
    class ParseTextEntities : Function {

        var text: String? = null
        var parseMode: TextParseMode? = null

        constructor()

        constructor(text: String? = null, parseMode: TextParseMode? = null) {

            this.text = text
            this.parseMode = parseMode

        }

        override val constructor: Int @BsonIgnore get() = -1709194593

    }


    /**
     * Returns the MIME type of a file, guessed by its extension
     * Returns an empty string on failure
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @fileName - The name of the file or path to the file
     */
    class GetFileMimeType : Function {

        var fileName: String? = null

        constructor()

        constructor(fileName: String? = null) {

            this.fileName = fileName

        }

        override val constructor: Int @BsonIgnore get() = -2073879671

    }


    /**
     * Returns the extension of a file, guessed by its MIME type
     * Returns an empty string on failure
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @mimeType - The MIME type of the file
     */
    class GetFileExtension : Function {

        var mimeType: String? = null

        constructor()

        constructor(mimeType: String? = null) {

            this.mimeType = mimeType

        }

        override val constructor: Int @BsonIgnore get() = -106055372

    }


    /**
     * Removes potentially dangerous characters from the name of a file
     * The encoding of the file name is supposed to be UTF-8
     * Returns an empty string on failure
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @fileName - File name or path to the file
     */
    class CleanFileName : Function {

        var fileName: String? = null

        constructor()

        constructor(fileName: String? = null) {

            this.fileName = fileName

        }

        override val constructor: Int @BsonIgnore get() = 967964667

    }


    /**
     * Returns a string stored in the local database from the specified localization target and language pack by its key
     * Returns a 404 error if the string is not found
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @languagePackDatabasePath - Path to the language pack database in which strings are stored
     * @localizationTarget - Localization target to which the language pack belongs
     * @languagePackId - Language pack identifier
     * @key - Language pack key of the string to be returned
     */
    class GetLanguagePackString : Function {

        var languagePackDatabasePath: String? = null
        var localizationTarget: String? = null
        var languagePackId: String? = null
        var key: String? = null

        constructor()

        constructor(languagePackDatabasePath: String? = null, localizationTarget: String? = null, languagePackId: String? = null, key: String? = null) {

            this.languagePackDatabasePath = languagePackDatabasePath
            this.localizationTarget = localizationTarget
            this.languagePackId = languagePackId
            this.key = key

        }

        override val constructor: Int @BsonIgnore get() = 150789747

    }


    /**
     * Converts a JSON-serialized string to corresponding JsonValue object
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @json - The JSON-serialized string
     */
    class GetJsonValue : Function {

        var json: String? = null

        constructor()

        constructor(json: String? = null) {

            this.json = json

        }

        override val constructor: Int @BsonIgnore get() = -1829086715

    }


    /**
     * Converts a JsonValue object to corresponding JSON-serialized string
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @jsonValue - The JsonValue object
     */
    class GetJsonString : Function {

        var jsonValue: JsonValue? = null

        constructor()

        constructor(jsonValue: JsonValue? = null) {

            this.jsonValue = jsonValue

        }

        override val constructor: Int @BsonIgnore get() = 663458849

    }


    /**
     * Changes user answer to a poll
     *
     * @chatId - Identifier of the chat to which the poll belongs
     * @messageId - Identifier of the message containing the poll
     * @optionIds - 0-based identifiers of options, chosen by the user
     *              Currently user can't choose more than 1 option
     */
    class SetPollAnswer : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var optionIds: IntArray by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, optionIds: IntArray) {

            this.chatId = chatId
            this.messageId = messageId
            this.optionIds = optionIds

        }

        override val constructor: Int @BsonIgnore get() = -1399388792

    }


    /**
     * Stops a poll
     * A poll in a message can be stopped when the message has can_be_edited flag set
     *
     * @chatId - Identifier of the chat to which the poll belongs
     * @messageId - Identifier of the message containing the poll
     * @replyMarkup - The new message reply markup
     *                For bots only
     */
    class StopPoll : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var replyMarkup: ReplyMarkup? = null

        constructor()

        constructor(chatId: Long, messageId: Long, replyMarkup: ReplyMarkup? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.replyMarkup = replyMarkup

        }

        override val constructor: Int @BsonIgnore get() = 1659374253

    }


    /**
     * Returns information about a button of type inlineKeyboardButtonTypeLoginUrl
     * The method needs to be called when the user presses the button
     *
     * @chatId - Chat identifier of the message with the button
     * @messageId - Message identifier of the message with the button
     * @buttonId - Button identifier
     */
    class GetLoginUrlInfo : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var buttonId: Int by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, buttonId: Int) {

            this.chatId = chatId
            this.messageId = messageId
            this.buttonId = buttonId

        }

        override val constructor: Int @BsonIgnore get() = -980042966

    }


    /**
     * Returns an HTTP URL which can be used to automatically authorize the user on a website after clicking an inline button of type inlineKeyboardButtonTypeLoginUrl
     * Use the method getLoginUrlInfo to find whether a prior user confirmation is needed
     * If an error is returned, then the button must be handled as an ordinary URL button
     *
     * @chatId - Chat identifier of the message with the button
     * @messageId - Message identifier of the message with the button
     * @buttonId - Button identifier
     * @allowWriteAccess - True, if the user allowed the bot to send them messages
     */
    class GetLoginUrl : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var buttonId: Int by WeakField()
        var allowWriteAccess: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, buttonId: Int, allowWriteAccess: Boolean) {

            this.chatId = chatId
            this.messageId = messageId
            this.buttonId = buttonId
            this.allowWriteAccess = allowWriteAccess

        }

        override val constructor: Int @BsonIgnore get() = 694973925

    }


    /**
     * Sends an inline query to a bot and returns its results
     * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
     *
     * @botUserId - The identifier of the target bot
     * @chatId - Identifier of the chat where the query was sent
     * @userLocation - Location of the user, only if needed
     * @query - Text of the query
     * @offset - Offset of the first entry to return
     */
    class GetInlineQueryResults : Function {

        var botUserId: Int by WeakField()
        var chatId: Long by WeakField()
        var userLocation: Location? = null
        var query: String? = null
        var offset: String? = null

        constructor()

        constructor(botUserId: Int, chatId: Long, userLocation: Location? = null, query: String? = null, offset: String? = null) {

            this.botUserId = botUserId
            this.chatId = chatId
            this.userLocation = userLocation
            this.query = query
            this.offset = offset

        }

        override val constructor: Int @BsonIgnore get() = -1182511172

    }


    /**
     * Sets the result of an inline query
     * For bots only
     *
     * @inlineQueryId - Identifier of the inline query
     * @isPersonal - True, if the result of the query can be cached for the specified user
     * @results - The results of the query
     * @cacheTime - Allowed time to cache the results of the query, in seconds
     * @nextOffset - Offset for the next inline query
     *               Pass an empty string if there are no more results
     * @switchPmText - If non-empty, this text should be shown on the button that opens a private chat with the bot and sends a start message to the bot with the parameter switch_pm_parameter
     * @switchPmParameter - The parameter for the bot start message
     */
    class AnswerInlineQuery : Function {

        var inlineQueryId: Long by WeakField()
        var isPersonal: Boolean by WeakField()
        var results: Array<InputInlineQueryResult> by WeakField()
        var cacheTime: Int by WeakField()
        var nextOffset: String? = null
        var switchPmText: String? = null
        var switchPmParameter: String? = null

        constructor()

        constructor(inlineQueryId: Long, isPersonal: Boolean, results: Array<InputInlineQueryResult>, cacheTime: Int, nextOffset: String? = null, switchPmText: String? = null, switchPmParameter: String? = null) {

            this.inlineQueryId = inlineQueryId
            this.isPersonal = isPersonal
            this.results = results
            this.cacheTime = cacheTime
            this.nextOffset = nextOffset
            this.switchPmText = switchPmText
            this.switchPmParameter = switchPmParameter

        }

        override val constructor: Int @BsonIgnore get() = 485879477

    }


    /**
     * Sends a callback query to a bot and returns an answer
     * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
     *
     * @chatId - Identifier of the chat with the message
     * @messageId - Identifier of the message from which the query originated
     * @payload - Query payload
     */
    class GetCallbackQueryAnswer : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var payload: CallbackQueryPayload? = null

        constructor()

        constructor(chatId: Long, messageId: Long, payload: CallbackQueryPayload? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.payload = payload

        }

        override val constructor: Int @BsonIgnore get() = 116357727

    }


    /**
     * Sets the result of a callback query
     * For bots only
     *
     * @callbackQueryId - Identifier of the callback query
     * @text - Text of the answer
     * @showAlert - If true, an alert should be shown to the user instead of a toast notification
     * @url - URL to be opened
     * @cacheTime - Time during which the result of the query can be cached, in seconds
     */
    class AnswerCallbackQuery : Function {

        var callbackQueryId: Long by WeakField()
        var text: String? = null
        var showAlert: Boolean by WeakField()
        var url: String? = null
        var cacheTime: Int by WeakField()

        constructor()

        constructor(callbackQueryId: Long, text: String? = null, showAlert: Boolean, url: String? = null, cacheTime: Int) {

            this.callbackQueryId = callbackQueryId
            this.text = text
            this.showAlert = showAlert
            this.url = url
            this.cacheTime = cacheTime

        }

        override val constructor: Int @BsonIgnore get() = -1153028490

    }


    /**
     * Sets the result of a shipping query
     * For bots only
     *
     * @shippingQueryId - Identifier of the shipping query
     * @shippingOptions - Available shipping options
     * @errorMessage - An error message, empty on success
     */
    class AnswerShippingQuery : Function {

        var shippingQueryId: Long by WeakField()
        var shippingOptions: Array<ShippingOption> by WeakField()
        var errorMessage: String? = null

        constructor()

        constructor(shippingQueryId: Long, shippingOptions: Array<ShippingOption>, errorMessage: String? = null) {

            this.shippingQueryId = shippingQueryId
            this.shippingOptions = shippingOptions
            this.errorMessage = errorMessage

        }

        override val constructor: Int @BsonIgnore get() = -434601324

    }


    /**
     * Sets the result of a pre-checkout query
     * For bots only
     *
     * @preCheckoutQueryId - Identifier of the pre-checkout query
     * @errorMessage - An error message, empty on success
     */
    class AnswerPreCheckoutQuery : Function {

        var preCheckoutQueryId: Long by WeakField()
        var errorMessage: String? = null

        constructor()

        constructor(preCheckoutQueryId: Long, errorMessage: String? = null) {

            this.preCheckoutQueryId = preCheckoutQueryId
            this.errorMessage = errorMessage

        }

        override val constructor: Int @BsonIgnore get() = -1486789653

    }


    /**
     * Updates the game score of the specified user in the game
     * For bots only
     *
     * @chatId - The chat to which the message with the game belongs
     * @messageId - Identifier of the message
     * @editMessage - True, if the message should be edited
     * @userId - User identifier
     * @score - The new score
     * @force - Pass true to update the score even if it decreases
     *          If the score is 0, the user will be deleted from the high score table
     */
    class SetGameScore : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var editMessage: Boolean by WeakField()
        var userId: Int by WeakField()
        var score: Int by WeakField()
        var force: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, editMessage: Boolean, userId: Int, score: Int, force: Boolean) {

            this.chatId = chatId
            this.messageId = messageId
            this.editMessage = editMessage
            this.userId = userId
            this.score = score
            this.force = force

        }

        override val constructor: Int @BsonIgnore get() = -1768307069

    }


    /**
     * Updates the game score of the specified user in a game
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @editMessage - True, if the message should be edited
     * @userId - User identifier
     * @score - The new score
     * @force - Pass true to update the score even if it decreases
     *          If the score is 0, the user will be deleted from the high score table
     */
    class SetInlineGameScore : Function {

        var inlineMessageId: String? = null
        var editMessage: Boolean by WeakField()
        var userId: Int by WeakField()
        var score: Int by WeakField()
        var force: Boolean by WeakField()

        constructor()

        constructor(inlineMessageId: String? = null, editMessage: Boolean, userId: Int, score: Int, force: Boolean) {

            this.inlineMessageId = inlineMessageId
            this.editMessage = editMessage
            this.userId = userId
            this.score = score
            this.force = force

        }

        override val constructor: Int @BsonIgnore get() = 758435487

    }


    /**
     * Returns the high scores for a game and some part of the high score table in the range of the specified user
     * For bots only
     *
     * @chatId - The chat that contains the message with the game
     * @messageId - Identifier of the message
     * @userId - User identifier
     */
    class GetGameHighScores : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var userId: Int by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, userId: Int) {

            this.chatId = chatId
            this.messageId = messageId
            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = 1920923753

    }


    /**
     * Returns game high scores and some part of the high score table in the range of the specified user
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @userId - User identifier
     */
    class GetInlineGameHighScores : Function {

        var inlineMessageId: String? = null
        var userId: Int by WeakField()

        constructor()

        constructor(inlineMessageId: String? = null, userId: Int) {

            this.inlineMessageId = inlineMessageId
            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -1833445800

    }


    /**
     * Deletes the default reply markup from a chat
     * Must be called after a one-time keyboard or a ForceReply reply markup has been used
     * UpdateChatReplyMarkup will be sent if the reply markup will be changed
     *
     * @chatId - Chat identifier
     * @messageId - The message identifier of the used keyboard
     */
    class DeleteChatReplyMarkup : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = 100637531

    }


    /**
     * Sends a notification about user activity in a chat
     *
     * @chatId - Chat identifier
     * @action - The action description
     */
    class SendChatAction : Function {

        var chatId: Long by WeakField()
        var action: ChatAction? = null

        constructor()

        constructor(chatId: Long, action: ChatAction? = null) {

            this.chatId = chatId
            this.action = action

        }

        override val constructor: Int @BsonIgnore get() = -841357536

    }


    /**
     * Informs TDLib that the chat is opened by the user
     * Many useful activities depend on the chat being opened or closed (e.g., in supergroups and channels all updates are received only for opened chats)
     *
     * @chatId - Chat identifier
     */
    class OpenChat : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = -323371509

    }


    /**
     * Informs TDLib that the chat is closed by the user
     * Many useful activities depend on the chat being opened or closed
     *
     * @chatId - Chat identifier
     */
    class CloseChat : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 39749353

    }


    /**
     * Informs TDLib that messages are being viewed by the user
     * Many useful activities depend on whether the messages are currently being viewed or not (e.g., marking messages as read, incrementing a view counter, updating a view counter, removing deleted messages in supergroups and channels)
     *
     * @chatId - Chat identifier
     * @messageIds - The identifiers of the messages being viewed
     * @forceRead - True, if messages in closed chats should be marked as read
     */
    class ViewMessages : Function {

        var chatId: Long by WeakField()
        var messageIds: LongArray by WeakField()
        var forceRead: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, messageIds: LongArray, forceRead: Boolean) {

            this.chatId = chatId
            this.messageIds = messageIds
            this.forceRead = forceRead

        }

        override val constructor: Int @BsonIgnore get() = -1925784915

    }


    /**
     * Informs TDLib that the message content has been opened (e.g., the user has opened a photo, video, document, location or venue, or has listened to an audio file or voice note message)
     * An updateMessageContentOpened update will be generated if something has changed
     *
     * @chatId - Chat identifier of the message
     * @messageId - Identifier of the message with the opened content
     */
    class OpenMessageContent : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = -739088005

    }


    /**
     * Marks all mentions in a chat as read
     *
     * @chatId - Chat identifier
     */
    class ReadAllChatMentions : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 1357558453

    }


    /**
     * Returns an existing chat corresponding to a given user
     *
     * @userId - User identifier
     * @force - If true, the chat will be created without network request
     *          In this case all information about the chat except its type, title and photo can be incorrect
     */
    class CreatePrivateChat : Function {

        var userId: Int by WeakField()
        var force: Boolean by WeakField()

        constructor()

        constructor(userId: Int, force: Boolean) {

            this.userId = userId
            this.force = force

        }

        override val constructor: Int @BsonIgnore get() = -1807530364

    }


    /**
     * Returns an existing chat corresponding to a known basic group
     *
     * @basicGroupId - Basic group identifier
     * @force - If true, the chat will be created without network request
     *          In this case all information about the chat except its type, title and photo can be incorrect
     */
    class CreateBasicGroupChat : Function {

        var basicGroupId: Int by WeakField()
        var force: Boolean by WeakField()

        constructor()

        constructor(basicGroupId: Int, force: Boolean) {

            this.basicGroupId = basicGroupId
            this.force = force

        }

        override val constructor: Int @BsonIgnore get() = 642492777

    }


    /**
     * Returns an existing chat corresponding to a known supergroup or channel
     *
     * @supergroupId - Supergroup or channel identifier
     * @force - If true, the chat will be created without network request
     *          In this case all information about the chat except its type, title and photo can be incorrect
     */
    class CreateSupergroupChat : Function {

        var supergroupId: Int by WeakField()
        var force: Boolean by WeakField()

        constructor()

        constructor(supergroupId: Int, force: Boolean) {

            this.supergroupId = supergroupId
            this.force = force

        }

        override val constructor: Int @BsonIgnore get() = 352742758

    }


    /**
     * Returns an existing chat corresponding to a known secret chat
     *
     * @secretChatId - Secret chat identifier
     */
    class CreateSecretChat : Function {

        var secretChatId: Int by WeakField()

        constructor()

        constructor(secretChatId: Int) {

            this.secretChatId = secretChatId

        }

        override val constructor: Int @BsonIgnore get() = 1930285615

    }


    /**
     * Creates a new basic group and sends a corresponding messageBasicGroupChatCreate
     * Returns the newly created chat
     *
     * @userIds - Identifiers of users to be added to the basic group
     * @title - Title of the new basic group
     */
    class CreateNewBasicGroupChat : Function {

        var userIds: IntArray by WeakField()
        var title: String? = null

        constructor()

        constructor(userIds: IntArray, title: String? = null) {

            this.userIds = userIds
            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = 1874532069

    }


    /**
     * Creates a new supergroup or channel and sends a corresponding messageSupergroupChatCreate
     * Returns the newly created chat
     *
     * @title - Title of the new chat
     * @isChannel - True, if a channel chat should be created
     * @description - Chat description
     * @location - Chat location if a location-based supergroup is being created
     */
    class CreateNewSupergroupChat : Function {

        var title: String? = null
        var isChannel: Boolean by WeakField()
        var description: String? = null
        var location: ChatLocation? = null

        constructor()

        constructor(title: String? = null, isChannel: Boolean, description: String? = null, location: ChatLocation? = null) {

            this.title = title
            this.isChannel = isChannel
            this.description = description
            this.location = location

        }

        override val constructor: Int @BsonIgnore get() = -8999251

    }


    /**
     * Creates a new secret chat
     * Returns the newly created chat
     *
     * @userId - Identifier of the target user
     */
    class CreateNewSecretChat : Function {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = 1689344881

    }


    /**
     * Creates a new supergroup from an existing basic group and sends a corresponding messageChatUpgradeTo and messageChatUpgradeFrom
     * Requires creator privileges
     * Deactivates the original basic group
     *
     * @chatId - Identifier of the chat to upgrade
     */
    class UpgradeBasicGroupChatToSupergroupChat : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 300488122

    }


    /**
     * Moves a chat to a different chat list
     * Current chat list of the chat must ne non-null
     *
     * @chatId - Chat identifier
     * @chatList - New chat list of the chat
     */
    class SetChatChatList : Function {

        var chatId: Long by WeakField()
        var chatList: ChatList? = null

        constructor()

        constructor(chatId: Long, chatList: ChatList? = null) {

            this.chatId = chatId
            this.chatList = chatList

        }

        override val constructor: Int @BsonIgnore get() = -1396517321

    }


    /**
     * Changes the chat title
     * Supported only for basic groups, supergroups and channels
     * Requires can_change_info rights
     * The title will not be changed until the request to the server has been completed
     *
     * @chatId - Chat identifier
     * @title - New title of the chat
     */
    class SetChatTitle : Function {

        var chatId: Long by WeakField()
        var title: String? = null

        constructor()

        constructor(chatId: Long, title: String? = null) {

            this.chatId = chatId
            this.title = title

        }

        override val constructor: Int @BsonIgnore get() = 164282047

    }


    /**
     * Changes the photo of a chat
     * Supported only for basic groups, supergroups and channels
     * Requires can_change_info rights
     * The photo will not be changed before request to the server has been completed
     *
     * @chatId - Chat identifier
     * @photo - New chat photo
     *          You can use a zero InputFileId to delete the chat photo
     *          Files that are accessible only by HTTP URL are not acceptable
     */
    class SetChatPhoto : Function {

        var chatId: Long by WeakField()
        var photo: InputFile? = null

        constructor()

        constructor(chatId: Long, photo: InputFile? = null) {

            this.chatId = chatId
            this.photo = photo

        }

        override val constructor: Int @BsonIgnore get() = 132244217

    }


    /**
     * Changes the chat members permissions
     * Supported only for basic groups and supergroups
     * Requires can_restrict_members administrator right
     *
     * @chatId - Chat identifier
     * @permissions - New non-administrator members permissions in the chat
     */
    class SetChatPermissions : Function {

        var chatId: Long by WeakField()
        var permissions: ChatPermissions? = null

        constructor()

        constructor(chatId: Long, permissions: ChatPermissions? = null) {

            this.chatId = chatId
            this.permissions = permissions

        }

        override val constructor: Int @BsonIgnore get() = 2138507006

    }


    /**
     * Changes the draft message in a chat
     *
     * @chatId - Chat identifier
     * @draftMessage - New draft message
     */
    class SetChatDraftMessage : Function {

        var chatId: Long by WeakField()
        var draftMessage: DraftMessage? = null

        constructor()

        constructor(chatId: Long, draftMessage: DraftMessage? = null) {

            this.chatId = chatId
            this.draftMessage = draftMessage

        }

        override val constructor: Int @BsonIgnore get() = -588175579

    }


    /**
     * Changes the notification settings of a chat
     * Notification settings of a chat with the current user (Saved Messages) can't be changed
     *
     * @chatId - Chat identifier
     * @notificationSettings - New notification settings for the chat
     *                         If the chat is muted for more than 1 week, it is considered to be muted forever
     */
    class SetChatNotificationSettings : Function {

        var chatId: Long by WeakField()
        var notificationSettings: ChatNotificationSettings? = null

        constructor()

        constructor(chatId: Long, notificationSettings: ChatNotificationSettings? = null) {

            this.chatId = chatId
            this.notificationSettings = notificationSettings

        }

        override val constructor: Int @BsonIgnore get() = 777199614

    }


    /**
     * Changes the pinned state of a chat
     * You can pin up to GetOption("pinned_chat_count_max")/GetOption("pinned_archived_chat_count_max") non-secret chats and the same number of secret chats in the main/archive chat list
     *
     * @chatId - Chat identifier
     * @isPinned - New value of is_pinned
     */
    class ToggleChatIsPinned : Function {

        var chatId: Long by WeakField()
        var isPinned: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, isPinned: Boolean) {

            this.chatId = chatId
            this.isPinned = isPinned

        }

        override val constructor: Int @BsonIgnore get() = -1166802621

    }


    /**
     * Changes the marked as unread state of a chat
     *
     * @chatId - Chat identifier
     * @isMarkedAsUnread - New value of is_marked_as_unread
     */
    class ToggleChatIsMarkedAsUnread : Function {

        var chatId: Long by WeakField()
        var isMarkedAsUnread: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, isMarkedAsUnread: Boolean) {

            this.chatId = chatId
            this.isMarkedAsUnread = isMarkedAsUnread

        }

        override val constructor: Int @BsonIgnore get() = -986129697

    }


    /**
     * Changes the value of the default disable_notification parameter, used when a message is sent to a chat
     *
     * @chatId - Chat identifier
     * @defaultDisableNotification - New value of default_disable_notification
     */
    class ToggleChatDefaultDisableNotification : Function {

        var chatId: Long by WeakField()
        var defaultDisableNotification: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, defaultDisableNotification: Boolean) {

            this.chatId = chatId
            this.defaultDisableNotification = defaultDisableNotification

        }

        override val constructor: Int @BsonIgnore get() = 314794002

    }


    /**
     * Changes client data associated with a chat
     *
     * @chatId - Chat identifier
     * @clientData - New value of client_data
     */
    class SetChatClientData : Function {

        var chatId: Long by WeakField()
        var clientData: String? = null

        constructor()

        constructor(chatId: Long, clientData: String? = null) {

            this.chatId = chatId
            this.clientData = clientData

        }

        override val constructor: Int @BsonIgnore get() = -827119811

    }


    /**
     * Changes information about a chat
     * Available for basic groups, supergroups, and channels
     * Requires can_change_info rights
     *
     * @chatId - Identifier of the chat
     * @description - New chat description
     */
    class SetChatDescription : Function {

        var chatId: Long by WeakField()
        var description: String? = null

        constructor()

        constructor(chatId: Long, description: String? = null) {

            this.chatId = chatId
            this.description = description

        }

        override val constructor: Int @BsonIgnore get() = 1957213277

    }


    /**
     * Changes the discussion group of a channel chat
     * Requires can_change_info rights in the channel if it is specified
     *
     * @chatId - Identifier of the channel chat
     *           Pass 0 to remove a link from the supergroup passed in the second argument to a linked channel chat (requires can_pin_messages rights in the supergroup)
     * @discussionChatId - Identifier of a new channel's discussion group
     *                     Use 0 to remove the discussion group
     *                     Use the method getSuitableDiscussionChats to find all suitable groups
     *                     Basic group chats needs to be first upgraded to supergroup chats
     *                     If new chat members don't have access to old messages in the supergroup, then toggleSupergroupIsAllHistoryAvailable needs to be used first to change that
     */
    class SetChatDiscussionGroup : Function {

        var chatId: Long by WeakField()
        var discussionChatId: Long by WeakField()

        constructor()

        constructor(chatId: Long, discussionChatId: Long) {

            this.chatId = chatId
            this.discussionChatId = discussionChatId

        }

        override val constructor: Int @BsonIgnore get() = -918801736

    }


    /**
     * Changes the location of a chat
     * Available only for some location-based supergroups, use supergroupFullInfo.can_set_location to check whether the method is allowed to use
     *
     * @chatId - Chat identifier
     * @location - New location for the chat
     *             Must be valid and not null
     */
    class SetChatLocation : Function {

        var chatId: Long by WeakField()
        var location: ChatLocation? = null

        constructor()

        constructor(chatId: Long, location: ChatLocation? = null) {

            this.chatId = chatId
            this.location = location

        }

        override val constructor: Int @BsonIgnore get() = -767091286

    }


    /**
     * Changes the slow mode delay of a chat
     * Available only for supergroups
     * Requires can_restrict_members rights
     *
     * @chatId - Chat identifier
     * @slowModeDelay - New slow mode delay for the chat
     *                  Must be one of 0, 10, 30, 60, 300, 900, 3600
     */
    class SetChatSlowModeDelay : Function {

        var chatId: Long by WeakField()
        var slowModeDelay: Int by WeakField()

        constructor()

        constructor(chatId: Long, slowModeDelay: Int) {

            this.chatId = chatId
            this.slowModeDelay = slowModeDelay

        }

        override val constructor: Int @BsonIgnore get() = -540350914

    }


    /**
     * Pins a message in a chat
     * Requires can_pin_messages rights
     *
     * @chatId - Identifier of the chat
     * @messageId - Identifier of the new pinned message
     * @disableNotification - True, if there should be no notification about the pinned message
     */
    class PinChatMessage : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var disableNotification: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, disableNotification: Boolean) {

            this.chatId = chatId
            this.messageId = messageId
            this.disableNotification = disableNotification

        }

        override val constructor: Int @BsonIgnore get() = -554712351

    }


    /**
     * Removes the pinned message from a chat
     * Requires can_pin_messages rights in the group or channel
     *
     * @chatId - Identifier of the chat
     */
    class UnpinChatMessage : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 277557690

    }


    /**
     * Adds current user as a new member to a chat
     * Private and secret chats can't be joined using this method
     *
     * @chatId - Chat identifier
     */
    class JoinChat : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 326769313

    }


    /**
     * Removes current user from chat members
     * Private and secret chats can't be left using this method
     *
     * @chatId - Chat identifier
     */
    class LeaveChat : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = -1825080735

    }


    /**
     * Adds a new member to a chat
     * Members can't be added to private or secret chats
     * Members will not be added until the chat state has been synchronized with the server
     *
     * @chatId - Chat identifier
     * @userId - Identifier of the user
     * @forwardLimit - The number of earlier messages from the chat to be forwarded to the new member
     *                 Ignored for supergroups and channels
     */
    class AddChatMember : Function {

        var chatId: Long by WeakField()
        var userId: Int by WeakField()
        var forwardLimit: Int by WeakField()

        constructor()

        constructor(chatId: Long, userId: Int, forwardLimit: Int) {

            this.chatId = chatId
            this.userId = userId
            this.forwardLimit = forwardLimit

        }

        override val constructor: Int @BsonIgnore get() = 1182817962

    }


    /**
     * Adds multiple new members to a chat
     * Currently this option is only available for supergroups and channels
     * This option can't be used to join a chat
     * Members can't be added to a channel if it has more than 200 members
     * Members will not be added until the chat state has been synchronized with the server
     *
     * @chatId - Chat identifier
     * @userIds - Identifiers of the users to be added to the chat
     */
    class AddChatMembers : Function {

        var chatId: Long by WeakField()
        var userIds: IntArray by WeakField()

        constructor()

        constructor(chatId: Long, userIds: IntArray) {

            this.chatId = chatId
            this.userIds = userIds

        }

        override val constructor: Int @BsonIgnore get() = 1234094617

    }


    /**
     * Changes the status of a chat member, needs appropriate privileges
     * This function is currently not suitable for adding new members to the chat and transferring chat ownership
     * Instead, use addChatMember or transferChatOwnership
     * The chat member status will not be changed until it has been synchronized with the server
     *
     * @chatId - Chat identifier
     * @userId - User identifier
     * @status - The new status of the member in the chat
     */
    class SetChatMemberStatus : Function {

        var chatId: Long by WeakField()
        var userId: Int by WeakField()
        var status: ChatMemberStatus? = null

        constructor()

        constructor(chatId: Long, userId: Int, status: ChatMemberStatus? = null) {

            this.chatId = chatId
            this.userId = userId
            this.status = status

        }

        override val constructor: Int @BsonIgnore get() = -1754439241

    }


    /**
     * Checks whether the current session can be used to transfer a chat ownership to another user
     */
    class CanTransferOwnership : Function() {

        override val constructor: Int @BsonIgnore get() = 634602508

    }


    /**
     * Changes the owner of a chat
     * The current user must be a current owner of the chat
     * Use the method canTransferOwnership to check whether the ownership can be transferred from the current session
     * Available only for supergroups and channel chats
     *
     * @chatId - Chat identifier
     * @userId - Identifier of the user to which transfer the ownership
     *           The ownership can't be transferred to a bot or to a deleted user
     * @password - The password of the current user
     */
    class TransferChatOwnership : Function {

        var chatId: Long by WeakField()
        var userId: Int by WeakField()
        var password: String? = null

        constructor()

        constructor(chatId: Long, userId: Int, password: String? = null) {

            this.chatId = chatId
            this.userId = userId
            this.password = password

        }

        override val constructor: Int @BsonIgnore get() = -1925047127

    }


    /**
     * Returns information about a single member of a chat
     *
     * @chatId - Chat identifier
     * @userId - User identifier
     */
    class GetChatMember : Function {

        var chatId: Long by WeakField()
        var userId: Int by WeakField()

        constructor()

        constructor(chatId: Long, userId: Int) {

            this.chatId = chatId
            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = 677085892

    }


    /**
     * Searches for a specified query in the first name, last name and username of the members of a specified chat
     * Requires administrator rights in channels
     *
     * @chatId - Chat identifier
     * @query - Query to search for
     * @limit - The maximum number of users to be returned
     * @filter - The type of users to return
     *           By default, chatMembersFilterMembers
     */
    class SearchChatMembers : Function {

        var chatId: Long by WeakField()
        var query: String? = null
        var limit: Int by WeakField()
        var filter: ChatMembersFilter? = null

        constructor()

        constructor(chatId: Long, query: String? = null, limit: Int, filter: ChatMembersFilter? = null) {

            this.chatId = chatId
            this.query = query
            this.limit = limit
            this.filter = filter

        }

        override val constructor: Int @BsonIgnore get() = -445823291

    }


    /**
     * Returns a list of administrators of the chat with their custom titles
     *
     * @chatId - Chat identifier
     */
    class GetChatAdministrators : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 1544468155

    }


    /**
     * Clears draft messages in all chats
     *
     * @excludeSecretChats - If true, local draft messages in secret chats will not be cleared
     */
    class ClearAllDraftMessages : Function {

        var excludeSecretChats: Boolean by WeakField()

        constructor()

        constructor(excludeSecretChats: Boolean) {

            this.excludeSecretChats = excludeSecretChats

        }

        override val constructor: Int @BsonIgnore get() = -46369573

    }


    /**
     * Returns list of chats with non-default notification settings
     *
     * @scope - If specified, only chats from the specified scope will be returned
     * @compareSound - If true, also chats with non-default sound will be returned
     */
    class GetChatNotificationSettingsExceptions : Function {

        var scope: NotificationSettingsScope? = null
        var compareSound: Boolean by WeakField()

        constructor()

        constructor(scope: NotificationSettingsScope? = null, compareSound: Boolean) {

            this.scope = scope
            this.compareSound = compareSound

        }

        override val constructor: Int @BsonIgnore get() = 201199121

    }


    /**
     * Returns the notification settings for chats of a given type
     *
     * @scope - Types of chats for which to return the notification settings information
     */
    class GetScopeNotificationSettings : Function {

        var scope: NotificationSettingsScope? = null

        constructor()

        constructor(scope: NotificationSettingsScope? = null) {

            this.scope = scope

        }

        override val constructor: Int @BsonIgnore get() = -995613361

    }


    /**
     * Changes notification settings for chats of a given type
     *
     * @scope - Types of chats for which to change the notification settings
     * @notificationSettings - The new notification settings for the given scope
     */
    class SetScopeNotificationSettings : Function {

        var scope: NotificationSettingsScope? = null
        var notificationSettings: ScopeNotificationSettings? = null

        constructor()

        constructor(scope: NotificationSettingsScope? = null, notificationSettings: ScopeNotificationSettings? = null) {

            this.scope = scope
            this.notificationSettings = notificationSettings

        }

        override val constructor: Int @BsonIgnore get() = -2049984966

    }


    /**
     * Resets all notification settings to their default values
     * By default, all chats are unmuted, the sound is set to "default" and message previews are shown
     */
    class ResetAllNotificationSettings : Function() {

        override val constructor: Int @BsonIgnore get() = -174020359

    }


    /**
     * Changes the order of pinned chats
     *
     * @chatList - Chat list in which to change the order of pinned chats
     * @chatIds - The new list of pinned chats
     */
    class SetPinnedChats : Function {

        var chatList: ChatList? = null
        var chatIds: LongArray by WeakField()

        constructor()

        constructor(chatList: ChatList? = null, chatIds: LongArray) {

            this.chatList = chatList
            this.chatIds = chatIds

        }

        override val constructor: Int @BsonIgnore get() = -695640000

    }


    /**
     * Downloads a file from the cloud
     * Download progress and completion of the download will be notified through updateFile updates
     *
     * @fileId - Identifier of the file to download
     * @priority - Priority of the download (1-32)
     *             The higher the priority, the earlier the file will be downloaded
     *             If the priorities of two files are equal, then the last one for which downloadFile was called will be downloaded first
     * @offset - The starting position from which the file should be downloaded
     * @limit - Number of bytes which should be downloaded starting from the "offset" position before the download will be automatically cancelled
     *          Use 0 to download without a limit
     * @synchronous - If false, this request returns file state just after the download has been started
     *                If true, this request returns file state only after the download has succeeded, has failed, has been cancelled or a new downloadFile request with different offset/limit parameters was sent
     */
    class DownloadFile : Function {

        var fileId: Int by WeakField()
        var priority: Int by WeakField()
        var offset: Int by WeakField()
        var limit: Int by WeakField()
        var synchronous: Boolean by WeakField()

        constructor()

        constructor(fileId: Int, priority: Int, offset: Int, limit: Int, synchronous: Boolean) {

            this.fileId = fileId
            this.priority = priority
            this.offset = offset
            this.limit = limit
            this.synchronous = synchronous

        }

        override val constructor: Int @BsonIgnore get() = -1102026662

    }


    /**
     * Returns file downloaded prefix size from a given offset
     *
     * @fileId - Identifier of the file
     * @offset - Offset from which downloaded prefix size should be calculated
     */
    class GetFileDownloadedPrefixSize : Function {

        var fileId: Int by WeakField()
        var offset: Int by WeakField()

        constructor()

        constructor(fileId: Int, offset: Int) {

            this.fileId = fileId
            this.offset = offset

        }

        override val constructor: Int @BsonIgnore get() = -1668864864

    }


    /**
     * Stops the downloading of a file
     * If a file has already been downloaded, does nothing
     *
     * @fileId - Identifier of a file to stop downloading
     * @onlyIfPending - Pass true to stop downloading only if it hasn't been started, i.e
     *                  Request hasn't been sent to server
     */
    class CancelDownloadFile : Function {

        var fileId: Int by WeakField()
        var onlyIfPending: Boolean by WeakField()

        constructor()

        constructor(fileId: Int, onlyIfPending: Boolean) {

            this.fileId = fileId
            this.onlyIfPending = onlyIfPending

        }

        override val constructor: Int @BsonIgnore get() = -1954524450

    }


    /**
     * Asynchronously uploads a file to the cloud without sending it in a message
     * UpdateFile will be used to notify about upload progress and successful completion of the upload
     * The file will not have a persistent remote identifier until it will be sent in a message
     *
     * @file - File to upload
     * @fileType - File type
     * @priority - Priority of the upload (1-32)
     *             The higher the priority, the earlier the file will be uploaded
     *             If the priorities of two files are equal, then the first one for which uploadFile was called will be uploaded first
     */
    class UploadFile : Function {

        var file: InputFile? = null
        var fileType: FileType? = null
        var priority: Int by WeakField()

        constructor()

        constructor(file: InputFile? = null, fileType: FileType? = null, priority: Int) {

            this.file = file
            this.fileType = fileType
            this.priority = priority

        }

        override val constructor: Int @BsonIgnore get() = -745597786

    }


    /**
     * Stops the uploading of a file
     * Supported only for files uploaded by using uploadFile
     * For other files the behavior is undefined
     *
     * @fileId - Identifier of the file to stop uploading
     */
    class CancelUploadFile : Function {

        var fileId: Int by WeakField()

        constructor()

        constructor(fileId: Int) {

            this.fileId = fileId

        }

        override val constructor: Int @BsonIgnore get() = 1623539600

    }


    /**
     * Writes a part of a generated file
     * This method is intended to be used only if the client has no direct access to TDLib's file system, because it is usually slower than a direct write to the destination file
     *
     * @generationId - The identifier of the generation process
     * @offset - The offset from which to write the data to the file
     * @data - The data to write
     */
    class WriteGeneratedFilePart : Function {

        var generationId: Long by WeakField()
        var offset: Int by WeakField()
        var data: ByteArray by WeakField()

        constructor()

        constructor(generationId: Long, offset: Int, data: ByteArray) {

            this.generationId = generationId
            this.offset = offset
            this.data = data

        }

        override val constructor: Int @BsonIgnore get() = -2062358189

    }


    /**
     * Informs TDLib on a file generation prograss
     *
     * @generationId - The identifier of the generation process
     * @expectedSize - Expected size of the generated file, in bytes
     *                 0 if unknown
     * @localPrefixSize - The number of bytes already generated
     */
    class SetFileGenerationProgress : Function {

        var generationId: Long by WeakField()
        var expectedSize: Int by WeakField()
        var localPrefixSize: Int by WeakField()

        constructor()

        constructor(generationId: Long, expectedSize: Int, localPrefixSize: Int) {

            this.generationId = generationId
            this.expectedSize = expectedSize
            this.localPrefixSize = localPrefixSize

        }

        override val constructor: Int @BsonIgnore get() = -540459953

    }


    /**
     * Finishes the file generation
     *
     * @generationId - The identifier of the generation process
     * @error - If set, means that file generation has failed and should be terminated
     */
    class FinishFileGeneration : Function {

        var generationId: Long by WeakField()
        var error: Error? = null

        constructor()

        constructor(generationId: Long, error: Error? = null) {

            this.generationId = generationId
            this.error = error

        }

        override val constructor: Int @BsonIgnore get() = -1055060835

    }


    /**
     * Reads a part of a file from the TDLib file cache and returns read bytes
     * This method is intended to be used only if the client has no direct access to TDLib's file system, because it is usually slower than a direct read from the file
     *
     * @fileId - Identifier of the file
     *           The file must be located in the TDLib file cache
     * @offset - The offset from which to read the file
     * @count - Number of bytes to read
     *          An error will be returned if there are not enough bytes available in the file from the specified position
     *          Pass 0 to read all available data from the specified position
     */
    class ReadFilePart : Function {

        var fileId: Int by WeakField()
        var offset: Int by WeakField()
        var count: Int by WeakField()

        constructor()

        constructor(fileId: Int, offset: Int, count: Int) {

            this.fileId = fileId
            this.offset = offset
            this.count = count

        }

        override val constructor: Int @BsonIgnore get() = -407749314

    }


    /**
     * Deletes a file from the TDLib file cache
     *
     * @fileId - Identifier of the file to delete
     */
    class DeleteFile : Function {

        var fileId: Int by WeakField()

        constructor()

        constructor(fileId: Int) {

            this.fileId = fileId

        }

        override val constructor: Int @BsonIgnore get() = 1807653676

    }


    /**
     * Generates a new invite link for a chat
     * The previously generated link is revoked
     * Available for basic groups, supergroups, and channels
     * Requires administrator privileges and can_invite_users right
     *
     * @chatId - Chat identifier
     */
    class GenerateChatInviteLink : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = 1945532500

    }


    /**
     * Checks the validity of an invite link for a chat and returns information about the corresponding chat
     *
     * @inviteLink - Invite link to be checked
     */
    class CheckChatInviteLink : Function {

        var inviteLink: String? = null

        constructor()

        constructor(inviteLink: String? = null) {

            this.inviteLink = inviteLink

        }

        override val constructor: Int @BsonIgnore get() = -496940997

    }


    /**
     * Uses an invite link to add the current user to the chat if possible
     * The new member will not be added until the chat state has been synchronized with the server
     *
     * @inviteLink - Invite link to import
     */
    class JoinChatByInviteLink : Function {

        var inviteLink: String? = null

        constructor()

        constructor(inviteLink: String? = null) {

            this.inviteLink = inviteLink

        }

        override val constructor: Int @BsonIgnore get() = -1049973882

    }


    /**
     * Creates a new call
     *
     * @userId - Identifier of the user to be called
     * @protocol - Description of the call protocols supported by the client
     */
    class CreateCall : Function {

        var userId: Int by WeakField()
        var protocol: CallProtocol? = null

        constructor()

        constructor(userId: Int, protocol: CallProtocol? = null) {

            this.userId = userId
            this.protocol = protocol

        }

        override val constructor: Int @BsonIgnore get() = -1742408159

    }


    /**
     * Accepts an incoming call
     *
     * @callId - Call identifier
     * @protocol - Description of the call protocols supported by the client
     */
    class AcceptCall : Function {

        var callId: Int by WeakField()
        var protocol: CallProtocol? = null

        constructor()

        constructor(callId: Int, protocol: CallProtocol? = null) {

            this.callId = callId
            this.protocol = protocol

        }

        override val constructor: Int @BsonIgnore get() = -646618416

    }


    /**
     * Discards a call
     *
     * @callId - Call identifier
     * @isDisconnected - True, if the user was disconnected
     * @duration - The call duration, in seconds
     * @connectionId - Identifier of the connection used during the call
     */
    class DiscardCall : Function {

        var callId: Int by WeakField()
        var isDisconnected: Boolean by WeakField()
        var duration: Int by WeakField()
        var connectionId: Long by WeakField()

        constructor()

        constructor(callId: Int, isDisconnected: Boolean, duration: Int, connectionId: Long) {

            this.callId = callId
            this.isDisconnected = isDisconnected
            this.duration = duration
            this.connectionId = connectionId

        }

        override val constructor: Int @BsonIgnore get() = -923187372

    }


    /**
     * Sends a call rating
     *
     * @callId - Call identifier
     * @rating - Call rating
     * @comment - An optional user comment if the rating is less than 5
     * @problems - List of the exact types of problems with the call, specified by the user
     */
    class SendCallRating : Function {

        var callId: Int by WeakField()
        var rating: Int by WeakField()
        var comment: String? = null
        var problems: Array<CallProblem> by WeakField()

        constructor()

        constructor(callId: Int, rating: Int, comment: String? = null, problems: Array<CallProblem>) {

            this.callId = callId
            this.rating = rating
            this.comment = comment
            this.problems = problems

        }

        override val constructor: Int @BsonIgnore get() = -1402719502

    }


    /**
     * Sends debug information for a call
     *
     * @callId - Call identifier
     * @debugInformation - Debug information in application-specific format
     */
    class SendCallDebugInformation : Function {

        var callId: Int by WeakField()
        var debugInformation: String? = null

        constructor()

        constructor(callId: Int, debugInformation: String? = null) {

            this.callId = callId
            this.debugInformation = debugInformation

        }

        override val constructor: Int @BsonIgnore get() = 2019243839

    }


    /**
     * Adds a user to the blacklist
     *
     * @userId - User identifier
     */
    class BlockUser : Function {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -1239315139

    }


    /**
     * Removes a user from the blacklist
     *
     * @userId - User identifier
     */
    class UnblockUser : Function {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -307286367

    }


    /**
     * Returns users that were blocked by the current user
     *
     * @offset - Number of users to skip in the result
     * @limit - The maximum number of users to return
     */
    class GetBlockedUsers : Function {

        var offset: Int by WeakField()
        var limit: Int by WeakField()

        constructor()

        constructor(offset: Int, limit: Int) {

            this.offset = offset
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = -742912777

    }


    /**
     * Adds a user to the contact list or edits an existing contact by their user identifier
     *
     * @contact - The contact to add or edit
     *            Phone number can be empty and needs to be specified only if known, vCard is ignored
     * @sharePhoneNumber - True, if the new contact needs to be allowed to see current user's phone number
     *                     A corresponding rule to userPrivacySettingShowPhoneNumber will be added if needed
     *                     Use the field UserFullInfo.need_phone_number_privacy_exception to check whether the current user needs to be asked to share their phone number
     */
    class AddContact : Function {

        var contact: Contact? = null
        var sharePhoneNumber: Boolean by WeakField()

        constructor()

        constructor(contact: Contact? = null, sharePhoneNumber: Boolean) {

            this.contact = contact
            this.sharePhoneNumber = sharePhoneNumber

        }

        override val constructor: Int @BsonIgnore get() = 1869640000

    }


    /**
     * Adds new contacts or edits existing contacts by their phone numbers
     * Contacts' user identifiers are ignored
     *
     * @contacts - The list of contacts to import or edit
     *             Contacts' vCard are ignored and are not imported
     */
    class ImportContacts : Function {

        var contacts: Array<Contact> by WeakField()

        constructor()

        constructor(contacts: Array<Contact>) {

            this.contacts = contacts

        }

        override val constructor: Int @BsonIgnore get() = -215132767

    }


    /**
     * Returns all user contacts
     */
    class GetContacts : Function() {

        override val constructor: Int @BsonIgnore get() = -1417722768

    }


    /**
     * Searches for the specified query in the first names, last names and usernames of the known user contacts
     *
     * @query - Query to search for
     *          May be empty to return all contacts
     * @limit - The maximum number of users to be returned
     */
    class SearchContacts : Function {

        var query: String? = null
        var limit: Int by WeakField()

        constructor()

        constructor(query: String? = null, limit: Int) {

            this.query = query
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = -1794690715

    }


    /**
     * Removes users from the contact list
     *
     * @userIds - Identifiers of users to be deleted
     */
    class RemoveContacts : Function {

        var userIds: IntArray by WeakField()

        constructor()

        constructor(userIds: IntArray) {

            this.userIds = userIds

        }

        override val constructor: Int @BsonIgnore get() = -615510759

    }


    /**
     * Returns the total number of imported contacts
     */
    class GetImportedContactCount : Function() {

        override val constructor: Int @BsonIgnore get() = -656336346

    }


    /**
     * Changes imported contacts using the list of current user contacts saved on the device
     * Imports newly added contacts and, if at least the file database is enabled, deletes recently deleted contacts
     * Query result depends on the result of the previous query, so only one query is possible at the same time
     *
     * @contacts - The new list of contacts, contact's vCard are ignored and are not imported
     */
    class ChangeImportedContacts : Function {

        var contacts: Array<Contact> by WeakField()

        constructor()

        constructor(contacts: Array<Contact>) {

            this.contacts = contacts

        }

        override val constructor: Int @BsonIgnore get() = 1968207955

    }


    /**
     * Clears all imported contacts, contact list remains unchanged
     */
    class ClearImportedContacts : Function() {

        override val constructor: Int @BsonIgnore get() = 869503298

    }


    /**
     * Shares the phone number of the current user with a mutual contact
     * Supposed to be called when the user clicks on chatActionBarSharePhoneNumber
     *
     * @userId - Identifier of the user with whom to share the phone number
     *           The user must be a mutual contact
     */
    class SharePhoneNumber : Function {

        var userId: Int by WeakField()

        constructor()

        constructor(userId: Int) {

            this.userId = userId

        }

        override val constructor: Int @BsonIgnore get() = -370669878

    }


    /**
     * Returns the profile photos of a user
     * The result of this query may be outdated: some photos might have been deleted already
     *
     * @userId - User identifier
     * @offset - The number of photos to skip
     * @limit - The maximum number of photos to be returned
     */
    class GetUserProfilePhotos : Function {

        var userId: Int by WeakField()
        var offset: Int by WeakField()
        var limit: Int by WeakField()

        constructor()

        constructor(userId: Int, offset: Int, limit: Int) {

            this.userId = userId
            this.offset = offset
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = -2062927433

    }


    /**
     * Returns stickers from the installed sticker sets that correspond to a given emoji
     * If the emoji is not empty, favorite and recently used stickers may also be returned
     *
     * @emoji - String representation of emoji
     *          If empty, returns all known installed stickers
     * @limit - The maximum number of stickers to be returned
     */
    class GetStickers : Function {

        var emoji: String? = null
        var limit: Int by WeakField()

        constructor()

        constructor(emoji: String? = null, limit: Int) {

            this.emoji = emoji
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = -1594919556

    }


    /**
     * Searches for stickers from public sticker sets that correspond to a given emoji
     *
     * @emoji - String representation of emoji
     * @limit - The maximum number of stickers to be returned
     */
    class SearchStickers : Function {

        var emoji: String? = null
        var limit: Int by WeakField()

        constructor()

        constructor(emoji: String? = null, limit: Int) {

            this.emoji = emoji
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = 1555771203

    }


    /**
     * Returns a list of installed sticker sets
     *
     * @isMasks - Pass true to return mask sticker sets
     *            Pass false to return ordinary sticker sets
     */
    class GetInstalledStickerSets : Function {

        var isMasks: Boolean by WeakField()

        constructor()

        constructor(isMasks: Boolean) {

            this.isMasks = isMasks

        }

        override val constructor: Int @BsonIgnore get() = 1214523749

    }


    /**
     * Returns a list of archived sticker sets
     *
     * @isMasks - Pass true to return mask stickers sets
     *            Pass false to return ordinary sticker sets
     * @offsetStickerSetId - Identifier of the sticker set from which to return the result
     * @limit - The maximum number of sticker sets to return
     */
    class GetArchivedStickerSets : Function {

        var isMasks: Boolean by WeakField()
        var offsetStickerSetId: Long by WeakField()
        var limit: Int by WeakField()

        constructor()

        constructor(isMasks: Boolean, offsetStickerSetId: Long, limit: Int) {

            this.isMasks = isMasks
            this.offsetStickerSetId = offsetStickerSetId
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = 1996943238

    }


    /**
     * Returns a list of trending sticker sets
     */
    class GetTrendingStickerSets : Function() {

        override val constructor: Int @BsonIgnore get() = -1729129957

    }


    /**
     * Returns a list of sticker sets attached to a file
     * Currently only photos and videos can have attached sticker sets
     *
     * @fileId - File identifier
     */
    class GetAttachedStickerSets : Function {

        var fileId: Int by WeakField()

        constructor()

        constructor(fileId: Int) {

            this.fileId = fileId

        }

        override val constructor: Int @BsonIgnore get() = 1302172429

    }


    /**
     * Returns information about a sticker set by its identifier
     *
     * @setId - Identifier of the sticker set
     */
    class GetStickerSet : Function {

        var setId: Long by WeakField()

        constructor()

        constructor(setId: Long) {

            this.setId = setId

        }

        override val constructor: Int @BsonIgnore get() = 1052318659

    }


    /**
     * Searches for a sticker set by its name
     *
     * @name - Name of the sticker set
     */
    class SearchStickerSet : Function {

        var name: String? = null

        constructor()

        constructor(name: String? = null) {

            this.name = name

        }

        override val constructor: Int @BsonIgnore get() = 1157930222

    }


    /**
     * Searches for installed sticker sets by looking for specified query in their title and name
     *
     * @isMasks - Pass true to return mask sticker sets
     *            Pass false to return ordinary sticker sets
     * @query - Query to search for
     * @limit - The maximum number of sticker sets to return
     */
    class SearchInstalledStickerSets : Function {

        var isMasks: Boolean by WeakField()
        var query: String? = null
        var limit: Int by WeakField()

        constructor()

        constructor(isMasks: Boolean, query: String? = null, limit: Int) {

            this.isMasks = isMasks
            this.query = query
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = 681171344

    }


    /**
     * Searches for ordinary sticker sets by looking for specified query in their title and name
     * Excludes installed sticker sets from the results
     *
     * @query - Query to search for
     */
    class SearchStickerSets : Function {

        var query: String? = null

        constructor()

        constructor(query: String? = null) {

            this.query = query

        }

        override val constructor: Int @BsonIgnore get() = -1082314629

    }


    /**
     * Installs/uninstalls or activates/archives a sticker set
     *
     * @setId - Identifier of the sticker set
     * @isInstalled - The new value of is_installed
     * @isArchived - The new value of is_archived
     *               A sticker set can't be installed and archived simultaneously
     */
    class ChangeStickerSet : Function {

        var setId: Long by WeakField()
        var isInstalled: Boolean by WeakField()
        var isArchived: Boolean by WeakField()

        constructor()

        constructor(setId: Long, isInstalled: Boolean, isArchived: Boolean) {

            this.setId = setId
            this.isInstalled = isInstalled
            this.isArchived = isArchived

        }

        override val constructor: Int @BsonIgnore get() = 449357293

    }


    /**
     * Informs the server that some trending sticker sets have been viewed by the user
     *
     * @stickerSetIds - Identifiers of viewed trending sticker sets
     */
    class ViewTrendingStickerSets : Function {

        var stickerSetIds: LongArray by WeakField()

        constructor()

        constructor(stickerSetIds: LongArray) {

            this.stickerSetIds = stickerSetIds

        }

        override val constructor: Int @BsonIgnore get() = -952416520

    }


    /**
     * Changes the order of installed sticker sets
     *
     * @isMasks - Pass true to change the order of mask sticker sets
     *            Pass false to change the order of ordinary sticker sets
     * @stickerSetIds - Identifiers of installed sticker sets in the new correct order
     */
    class ReorderInstalledStickerSets : Function {

        var isMasks: Boolean by WeakField()
        var stickerSetIds: LongArray by WeakField()

        constructor()

        constructor(isMasks: Boolean, stickerSetIds: LongArray) {

            this.isMasks = isMasks
            this.stickerSetIds = stickerSetIds

        }

        override val constructor: Int @BsonIgnore get() = 1114537563

    }


    /**
     * Returns a list of recently used stickers
     *
     * @isAttached - Pass true to return stickers and masks that were recently attached to photos or video files
     *               Pass false to return recently sent stickers
     */
    class GetRecentStickers : Function {

        var isAttached: Boolean by WeakField()

        constructor()

        constructor(isAttached: Boolean) {

            this.isAttached = isAttached

        }

        override val constructor: Int @BsonIgnore get() = -579622241

    }


    /**
     * Manually adds a new sticker to the list of recently used stickers
     * The new sticker is added to the top of the list
     * If the sticker was already in the list, it is removed from the list first
     * Only stickers belonging to a sticker set can be added to this list
     *
     * @isAttached - Pass true to add the sticker to the list of stickers recently attached to photo or video files
     *               Pass false to add the sticker to the list of recently sent stickers
     * @sticker - Sticker file to add
     */
    class AddRecentSticker : Function {

        var isAttached: Boolean by WeakField()
        var sticker: InputFile? = null

        constructor()

        constructor(isAttached: Boolean, sticker: InputFile? = null) {

            this.isAttached = isAttached
            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = -1478109026

    }


    /**
     * Removes a sticker from the list of recently used stickers
     *
     * @isAttached - Pass true to remove the sticker from the list of stickers recently attached to photo or video files
     *               Pass false to remove the sticker from the list of recently sent stickers
     * @sticker - Sticker file to delete
     */
    class RemoveRecentSticker : Function {

        var isAttached: Boolean by WeakField()
        var sticker: InputFile? = null

        constructor()

        constructor(isAttached: Boolean, sticker: InputFile? = null) {

            this.isAttached = isAttached
            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = 1246577677

    }


    /**
     * Clears the list of recently used stickers
     *
     * @isAttached - Pass true to clear the list of stickers recently attached to photo or video files
     *               Pass false to clear the list of recently sent stickers
     */
    class ClearRecentStickers : Function {

        var isAttached: Boolean by WeakField()

        constructor()

        constructor(isAttached: Boolean) {

            this.isAttached = isAttached

        }

        override val constructor: Int @BsonIgnore get() = -321242684

    }


    /**
     * Returns favorite stickers
     */
    class GetFavoriteStickers : Function() {

        override val constructor: Int @BsonIgnore get() = -338964672

    }


    /**
     * Adds a new sticker to the list of favorite stickers
     * The new sticker is added to the top of the list
     * If the sticker was already in the list, it is removed from the list first
     * Only stickers belonging to a sticker set can be added to this list
     *
     * @sticker - Sticker file to add
     */
    class AddFavoriteSticker : Function {

        var sticker: InputFile? = null

        constructor()

        constructor(sticker: InputFile? = null) {

            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = 324504799

    }


    /**
     * Removes a sticker from the list of favorite stickers
     *
     * @sticker - Sticker file to delete from the list
     */
    class RemoveFavoriteSticker : Function {

        var sticker: InputFile? = null

        constructor()

        constructor(sticker: InputFile? = null) {

            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = 1152945264

    }


    /**
     * Returns emoji corresponding to a sticker
     * The list is only for informational purposes, because a sticker is always sent with a fixed emoji from the corresponding Sticker object
     *
     * @sticker - Sticker file identifier
     */
    class GetStickerEmojis : Function {

        var sticker: InputFile? = null

        constructor()

        constructor(sticker: InputFile? = null) {

            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = -1895508665

    }


    /**
     * Searches for emojis by keywords
     * Supported only if the file database is enabled
     *
     * @text - Text to search for
     * @exactMatch - True, if only emojis, which exactly match text needs to be returned
     * @inputLanguageCode - IETF language tag of the user's input language
     *                      Can be empty if unknown
     */
    class SearchEmojis : Function {

        var text: String? = null
        var exactMatch: Boolean by WeakField()
        var inputLanguageCode: String? = null

        constructor()

        constructor(text: String? = null, exactMatch: Boolean, inputLanguageCode: String? = null) {

            this.text = text
            this.exactMatch = exactMatch
            this.inputLanguageCode = inputLanguageCode

        }

        override val constructor: Int @BsonIgnore get() = 453292808

    }


    /**
     * Returns an HTTP URL which can be used to automatically log in to the translation platform and suggest new emoji replacements
     * The URL will be valid for 30 seconds after generation
     *
     * @languageCode - Language code for which the emoji replacements will be suggested
     */
    class GetEmojiSuggestionsUrl : Function {

        var languageCode: String? = null

        constructor()

        constructor(languageCode: String? = null) {

            this.languageCode = languageCode

        }

        override val constructor: Int @BsonIgnore get() = -1404101841

    }


    /**
     * Returns saved animations
     */
    class GetSavedAnimations : Function() {

        override val constructor: Int @BsonIgnore get() = 7051032

    }


    /**
     * Manually adds a new animation to the list of saved animations
     * The new animation is added to the beginning of the list
     * If the animation was already in the list, it is removed first
     * Only non-secret video animations with MIME type "video/mp4" can be added to the list
     *
     * @animation - The animation file to be added
     *              Only animations known to the server (i.e
     *              Successfully sent via a message) can be added to the list
     */
    class AddSavedAnimation : Function {

        var animation: InputFile? = null

        constructor()

        constructor(animation: InputFile? = null) {

            this.animation = animation

        }

        override val constructor: Int @BsonIgnore get() = -1538525088

    }


    /**
     * Removes an animation from the list of saved animations
     *
     * @animation - Animation file to be removed
     */
    class RemoveSavedAnimation : Function {

        var animation: InputFile? = null

        constructor()

        constructor(animation: InputFile? = null) {

            this.animation = animation

        }

        override val constructor: Int @BsonIgnore get() = -495605479

    }


    /**
     * Returns up to 20 recently used inline bots in the order of their last usage
     */
    class GetRecentInlineBots : Function() {

        override val constructor: Int @BsonIgnore get() = 1437823548

    }


    /**
     * Searches for recently used hashtags by their prefix
     *
     * @prefix - Hashtag prefix to search for
     * @limit - The maximum number of hashtags to be returned
     */
    class SearchHashtags : Function {

        var prefix: String? = null
        var limit: Int by WeakField()

        constructor()

        constructor(prefix: String? = null, limit: Int) {

            this.prefix = prefix
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = 1043637617

    }


    /**
     * Removes a hashtag from the list of recently used hashtags
     *
     * @hashtag - Hashtag to delete
     */
    class RemoveRecentHashtag : Function {

        var hashtag: String? = null

        constructor()

        constructor(hashtag: String? = null) {

            this.hashtag = hashtag

        }

        override val constructor: Int @BsonIgnore get() = -1013735260

    }


    /**
     * Returns a web page preview by the text of the message
     * Do not call this function too often
     * Returns a 404 error if the web page has no preview
     *
     * @text - Message text with formatting
     */
    class GetWebPagePreview : Function {

        var text: FormattedText? = null

        constructor()

        constructor(text: FormattedText? = null) {

            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 573441580

    }


    /**
     * Returns an instant view version of a web page if available
     * Returns a 404 error if the web page has no instant view page
     *
     * @url - The web page URL
     * @forceFull - If true, the full instant view for the web page will be returned
     */
    class GetWebPageInstantView : Function {

        var url: String? = null
        var forceFull: Boolean by WeakField()

        constructor()

        constructor(url: String? = null, forceFull: Boolean) {

            this.url = url
            this.forceFull = forceFull

        }

        override val constructor: Int @BsonIgnore get() = -1962649975

    }


    /**
     * Uploads a new profile photo for the current user
     * If something changes, updateUser will be sent
     *
     * @photo - Profile photo to set
     *          InputFileId and inputFileRemote may still be unsupported
     */
    class SetProfilePhoto : Function {

        var photo: InputFile? = null

        constructor()

        constructor(photo: InputFile? = null) {

            this.photo = photo

        }

        override val constructor: Int @BsonIgnore get() = 1594734550

    }


    /**
     * Deletes a profile photo
     * If something changes, updateUser will be sent
     *
     * @profilePhotoId - Identifier of the profile photo to delete
     */
    class DeleteProfilePhoto : Function {

        var profilePhotoId: Long by WeakField()

        constructor()

        constructor(profilePhotoId: Long) {

            this.profilePhotoId = profilePhotoId

        }

        override val constructor: Int @BsonIgnore get() = 1319794625

    }


    /**
     * Changes the first and last name of the current user
     * If something changes, updateUser will be sent
     *
     * @firstName - The new value of the first name for the user
     * @lastName - The new value of the optional last name for the user
     */
    class SetName : Function {

        var firstName: String? = null
        var lastName: String? = null

        constructor()

        constructor(firstName: String? = null, lastName: String? = null) {

            this.firstName = firstName
            this.lastName = lastName

        }

        override val constructor: Int @BsonIgnore get() = 1711693584

    }


    /**
     * Changes the bio of the current user
     *
     * @bio - The new value of the user bio
     */
    class SetBio : Function {

        var bio: String? = null

        constructor()

        constructor(bio: String? = null) {

            this.bio = bio

        }

        override val constructor: Int @BsonIgnore get() = -1619582124

    }


    /**
     * Changes the username of the current user
     * If something changes, updateUser will be sent
     *
     * @username - The new value of the username
     *             Use an empty string to remove the username
     */
    class SetUsername : Function {

        var username: String? = null

        constructor()

        constructor(username: String? = null) {

            this.username = username

        }

        override val constructor: Int @BsonIgnore get() = 439901214

    }


    /**
     * Changes the phone number of the user and sends an authentication code to the user's new phone number
     * On success, returns information about the sent code
     *
     * @phoneNumber - The new phone number of the user in international format
     * @settings - Settings for the authentication of the user's phone number
     */
    class ChangePhoneNumber : Function {

        var phoneNumber: String? = null
        var settings: PhoneNumberAuthenticationSettings? = null

        constructor()

        constructor(phoneNumber: String? = null, settings: PhoneNumberAuthenticationSettings? = null) {

            this.phoneNumber = phoneNumber
            this.settings = settings

        }

        override val constructor: Int @BsonIgnore get() = -124666973

    }


    /**
     * Re-sends the authentication code sent to confirm a new phone number for the user
     * Works only if the previously received authenticationCodeInfo next_code_type was not null
     */
    class ResendChangePhoneNumberCode : Function() {

        override val constructor: Int @BsonIgnore get() = -786772060

    }


    /**
     * Checks the authentication code sent to confirm a new phone number of the user
     *
     * @code - Verification code received by SMS, phone call or flash call
     */
    class CheckChangePhoneNumberCode : Function {

        var code: String? = null

        constructor()

        constructor(code: String? = null) {

            this.code = code

        }

        override val constructor: Int @BsonIgnore get() = -1720278429

    }


    /**
     * Returns all active sessions of the current user
     */
    class GetActiveSessions : Function() {

        override val constructor: Int @BsonIgnore get() = 1119710526

    }


    /**
     * Terminates a session of the current user
     *
     * @sessionId - Session identifier
     */
    class TerminateSession : Function {

        var sessionId: Long by WeakField()

        constructor()

        constructor(sessionId: Long) {

            this.sessionId = sessionId

        }

        override val constructor: Int @BsonIgnore get() = -407385812

    }


    /**
     * Terminates all other sessions of the current user
     */
    class TerminateAllOtherSessions : Function() {

        override val constructor: Int @BsonIgnore get() = 1874485523

    }


    /**
     * Returns all website where the current user used Telegram to log in
     */
    class GetConnectedWebsites : Function() {

        override val constructor: Int @BsonIgnore get() = -170536110

    }


    /**
     * Disconnects website from the current user's Telegram account
     *
     * @websiteId - Website identifier
     */
    class DisconnectWebsite : Function {

        var websiteId: Long by WeakField()

        constructor()

        constructor(websiteId: Long) {

            this.websiteId = websiteId

        }

        override val constructor: Int @BsonIgnore get() = -778767395

    }


    /**
     * Disconnects all websites from the current user's Telegram account
     */
    class DisconnectAllWebsites : Function() {

        override val constructor: Int @BsonIgnore get() = -1082985981

    }


    /**
     * Changes the username of a supergroup or channel, requires owner privileges in the supergroup or channel
     *
     * @supergroupId - Identifier of the supergroup or channel
     * @username - New value of the username
     *             Use an empty string to remove the username
     */
    class SetSupergroupUsername : Function {

        var supergroupId: Int by WeakField()
        var username: String? = null

        constructor()

        constructor(supergroupId: Int, username: String? = null) {

            this.supergroupId = supergroupId
            this.username = username

        }

        override val constructor: Int @BsonIgnore get() = -1428333122

    }


    /**
     * Changes the sticker set of a supergroup
     * Requires can_change_info rights
     *
     * @supergroupId - Identifier of the supergroup
     * @stickerSetId - New value of the supergroup sticker set identifier
     *                 Use 0 to remove the supergroup sticker set
     */
    class SetSupergroupStickerSet : Function {

        var supergroupId: Int by WeakField()
        var stickerSetId: Long by WeakField()

        constructor()

        constructor(supergroupId: Int, stickerSetId: Long) {

            this.supergroupId = supergroupId
            this.stickerSetId = stickerSetId

        }

        override val constructor: Int @BsonIgnore get() = -295782298

    }


    /**
     * Toggles sender signatures messages sent in a channel
     * Requires can_change_info rights
     *
     * @supergroupId - Identifier of the channel
     * @signMessages - New value of sign_messages
     */
    class ToggleSupergroupSignMessages : Function {

        var supergroupId: Int by WeakField()
        var signMessages: Boolean by WeakField()

        constructor()

        constructor(supergroupId: Int, signMessages: Boolean) {

            this.supergroupId = supergroupId
            this.signMessages = signMessages

        }

        override val constructor: Int @BsonIgnore get() = -558196581

    }


    /**
     * Toggles whether the message history of a supergroup is available to new members
     * Requires can_change_info rights
     *
     * @supergroupId - The identifier of the supergroup
     * @isAllHistoryAvailable - The new value of is_all_history_available
     */
    class ToggleSupergroupIsAllHistoryAvailable : Function {

        var supergroupId: Int by WeakField()
        var isAllHistoryAvailable: Boolean by WeakField()

        constructor()

        constructor(supergroupId: Int, isAllHistoryAvailable: Boolean) {

            this.supergroupId = supergroupId
            this.isAllHistoryAvailable = isAllHistoryAvailable

        }

        override val constructor: Int @BsonIgnore get() = 1701526555

    }


    /**
     * Reports some messages from a user in a supergroup as spam
     * Requires administrator rights in the supergroup
     *
     * @supergroupId - Supergroup identifier
     * @userId - User identifier
     * @messageIds - Identifiers of messages sent in the supergroup by the user
     *               This list must be non-empty
     */
    class ReportSupergroupSpam : Function {

        var supergroupId: Int by WeakField()
        var userId: Int by WeakField()
        var messageIds: LongArray by WeakField()

        constructor()

        constructor(supergroupId: Int, userId: Int, messageIds: LongArray) {

            this.supergroupId = supergroupId
            this.userId = userId
            this.messageIds = messageIds

        }

        override val constructor: Int @BsonIgnore get() = -2125451498

    }


    /**
     * Returns information about members or banned users in a supergroup or channel
     * Can be used only if SupergroupFullInfo.can_get_members == true
     * Additionally, administrator privileges may be required for some filters
     *
     * @supergroupId - Identifier of the supergroup or channel
     * @filter - The type of users to return
     *           By default, supergroupMembersRecent
     * @offset - Number of users to skip
     * @limit - The maximum number of users be returned
     *          Up to 200
     */
    class GetSupergroupMembers : Function {

        var supergroupId: Int by WeakField()
        var filter: SupergroupMembersFilter? = null
        var offset: Int by WeakField()
        var limit: Int by WeakField()

        constructor()

        constructor(supergroupId: Int, filter: SupergroupMembersFilter? = null, offset: Int, limit: Int) {

            this.supergroupId = supergroupId
            this.filter = filter
            this.offset = offset
            this.limit = limit

        }

        override val constructor: Int @BsonIgnore get() = 1427643098

    }


    /**
     * Deletes a supergroup or channel along with all messages in the corresponding chat
     * This will release the supergroup or channel username and remove all members
     * Requires owner privileges in the supergroup or channel
     * Chats with more than 1000 members can't be deleted using this method
     *
     * @supergroupId - Identifier of the supergroup or channel
     */
    class DeleteSupergroup : Function {

        var supergroupId: Int by WeakField()

        constructor()

        constructor(supergroupId: Int) {

            this.supergroupId = supergroupId

        }

        override val constructor: Int @BsonIgnore get() = -1999855965

    }


    /**
     * Closes a secret chat, effectively transfering its state to secretChatStateClosed
     *
     * @secretChatId - Secret chat identifier
     */
    class CloseSecretChat : Function {

        var secretChatId: Int by WeakField()

        constructor()

        constructor(secretChatId: Int) {

            this.secretChatId = secretChatId

        }

        override val constructor: Int @BsonIgnore get() = -471006133

    }


    /**
     * Returns a list of service actions taken by chat members and administrators in the last 48 hours
     * Available only for supergroups and channels
     * Requires administrator rights
     * Returns results in reverse chronological order (i
     * E., in order of decreasing event_id)
     *
     * @chatId - Chat identifier
     * @query - Search query by which to filter events
     * @fromEventId - Identifier of an event from which to return results
     *                Use 0 to get results from the latest events
     * @limit - The maximum number of events to return
     * @filters - The types of events to return
     *            By default, all types will be returned
     * @userIds - User identifiers by which to filter events
     *            By default, events relating to all users will be returned
     */
    class GetChatEventLog : Function {

        var chatId: Long by WeakField()
        var query: String? = null
        var fromEventId: Long by WeakField()
        var limit: Int by WeakField()
        var filters: ChatEventLogFilters? = null
        var userIds: IntArray by WeakField()

        constructor()

        constructor(chatId: Long, query: String? = null, fromEventId: Long, limit: Int, filters: ChatEventLogFilters? = null, userIds: IntArray) {

            this.chatId = chatId
            this.query = query
            this.fromEventId = fromEventId
            this.limit = limit
            this.filters = filters
            this.userIds = userIds

        }

        override val constructor: Int @BsonIgnore get() = 206900967

    }


    /**
     * Returns an invoice payment form
     * This method should be called when the user presses inlineKeyboardButtonBuy
     *
     * @chatId - Chat identifier of the Invoice message
     * @messageId - Message identifier
     */
    class GetPaymentForm : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = -2146950882

    }


    /**
     * Validates the order information provided by a user and returns the available shipping options for a flexible invoice
     *
     * @chatId - Chat identifier of the Invoice message
     * @messageId - Message identifier
     * @orderInfo - The order information, provided by the user
     * @allowSave - True, if the order information can be saved
     */
    class ValidateOrderInfo : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var orderInfo: OrderInfo? = null
        var allowSave: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long, orderInfo: OrderInfo? = null, allowSave: Boolean) {

            this.chatId = chatId
            this.messageId = messageId
            this.orderInfo = orderInfo
            this.allowSave = allowSave

        }

        override val constructor: Int @BsonIgnore get() = 9480644

    }


    /**
     * Sends a filled-out payment form to the bot for final verification
     *
     * @chatId - Chat identifier of the Invoice message
     * @messageId - Message identifier
     * @orderInfoId - Identifier returned by ValidateOrderInfo, or an empty string
     * @shippingOptionId - Identifier of a chosen shipping option, if applicable
     * @credentials - The credentials chosen by user for payment
     */
    class SendPaymentForm : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()
        var orderInfoId: String? = null
        var shippingOptionId: String? = null
        var credentials: InputCredentials? = null

        constructor()

        constructor(chatId: Long, messageId: Long, orderInfoId: String? = null, shippingOptionId: String? = null, credentials: InputCredentials? = null) {

            this.chatId = chatId
            this.messageId = messageId
            this.orderInfoId = orderInfoId
            this.shippingOptionId = shippingOptionId
            this.credentials = credentials

        }

        override val constructor: Int @BsonIgnore get() = 591581572

    }


    /**
     * Returns information about a successful payment
     *
     * @chatId - Chat identifier of the PaymentSuccessful message
     * @messageId - Message identifier
     */
    class GetPaymentReceipt : Function {

        var chatId: Long by WeakField()
        var messageId: Long by WeakField()

        constructor()

        constructor(chatId: Long, messageId: Long) {

            this.chatId = chatId
            this.messageId = messageId

        }

        override val constructor: Int @BsonIgnore get() = 1013758294

    }


    /**
     * Returns saved order info, if any
     */
    class GetSavedOrderInfo : Function() {

        override val constructor: Int @BsonIgnore get() = -1152016675

    }


    /**
     * Deletes saved order info
     */
    class DeleteSavedOrderInfo : Function() {

        override val constructor: Int @BsonIgnore get() = 1629058164

    }


    /**
     * Deletes saved credentials for all payment provider bots
     */
    class DeleteSavedCredentials : Function() {

        override val constructor: Int @BsonIgnore get() = 826300114

    }


    /**
     * Returns a user that can be contacted to get support
     */
    class GetSupportUser : Function() {

        override val constructor: Int @BsonIgnore get() = -1733497700

    }


    /**
     * Returns backgrounds installed by the user
     *
     * @forDarkTheme - True, if the backgrounds needs to be ordered for dark theme
     */
    class GetBackgrounds : Function {

        var forDarkTheme: Boolean by WeakField()

        constructor()

        constructor(forDarkTheme: Boolean) {

            this.forDarkTheme = forDarkTheme

        }

        override val constructor: Int @BsonIgnore get() = 249072633

    }


    /**
     * Constructs a persistent HTTP URL for a background
     *
     * @name - Background name
     * @type - Background type
     */
    class GetBackgroundUrl : Function {

        var name: String? = null
        var type: BackgroundType? = null

        constructor()

        constructor(name: String? = null, type: BackgroundType? = null) {

            this.name = name
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = 733769682

    }


    /**
     * Searches for a background by its name
     *
     * @name - The name of the background
     */
    class SearchBackground : Function {

        var name: String? = null

        constructor()

        constructor(name: String? = null) {

            this.name = name

        }

        override val constructor: Int @BsonIgnore get() = -2130996959

    }


    /**
     * Changes the background selected by the user
     * Adds background to the list of installed backgrounds
     *
     * @background - The input background to use, null for filled backgrounds
     * @type - Background type
     *         Null for default background
     *         The method will return error 404 if type is null
     * @forDarkTheme - True, if the background is chosen for dark theme
     */
    class SetBackground : Function {

        var background: InputBackground? = null
        var type: BackgroundType? = null
        var forDarkTheme: Boolean by WeakField()

        constructor()

        constructor(background: InputBackground? = null, type: BackgroundType? = null, forDarkTheme: Boolean) {

            this.background = background
            this.type = type
            this.forDarkTheme = forDarkTheme

        }

        override val constructor: Int @BsonIgnore get() = -1035439225

    }


    /**
     * Removes background from the list of installed backgrounds
     *
     * @backgroundId - The background indentifier
     */
    class RemoveBackground : Function {

        var backgroundId: Long by WeakField()

        constructor()

        constructor(backgroundId: Long) {

            this.backgroundId = backgroundId

        }

        override val constructor: Int @BsonIgnore get() = -1484545642

    }


    /**
     * Resets list of installed backgrounds to its default value
     */
    class ResetBackgrounds : Function() {

        override val constructor: Int @BsonIgnore get() = 204852088

    }


    /**
     * Returns information about the current localization target
     * This is an offline request if only_local is true
     * Can be called before authorization
     *
     * @onlyLocal - If true, returns only locally available information without sending network requests
     */
    class GetLocalizationTargetInfo : Function {

        var onlyLocal: Boolean by WeakField()

        constructor()

        constructor(onlyLocal: Boolean) {

            this.onlyLocal = onlyLocal

        }

        override val constructor: Int @BsonIgnore get() = 1849499526

    }


    /**
     * Returns information about a language pack
     * Returned language pack identifier may be different from a provided one
     * Can be called before authorization
     *
     * @languagePackId - Language pack identifier
     */
    class GetLanguagePackInfo : Function {

        var languagePackId: String? = null

        constructor()

        constructor(languagePackId: String? = null) {

            this.languagePackId = languagePackId

        }

        override val constructor: Int @BsonIgnore get() = 2077809320

    }


    /**
     * Returns strings from a language pack in the current localization target by their keys
     * Can be called before authorization
     *
     * @languagePackId - Language pack identifier of the strings to be returned
     * @keys - Language pack keys of the strings to be returned
     *         Leave empty to request all available strings
     */
    class GetLanguagePackStrings : Function {

        var languagePackId: String? = null
        var keys: Array<String> by WeakField()

        constructor()

        constructor(languagePackId: String? = null, keys: Array<String>) {

            this.languagePackId = languagePackId
            this.keys = keys

        }

        override val constructor: Int @BsonIgnore get() = 1246259088

    }


    /**
     * Fetches the latest versions of all strings from a language pack in the current localization target from the server
     * This method doesn't need to be called explicitly for the current used/base language packs
     * Can be called before authorization
     *
     * @languagePackId - Language pack identifier
     */
    class SynchronizeLanguagePack : Function {

        var languagePackId: String? = null

        constructor()

        constructor(languagePackId: String? = null) {

            this.languagePackId = languagePackId

        }

        override val constructor: Int @BsonIgnore get() = -2065307858

    }


    /**
     * Adds a custom server language pack to the list of installed language packs in current localization target
     * Can be called before authorization
     *
     * @languagePackId - Identifier of a language pack to be added
     *                   May be different from a name that is used in an "https://t.me/setlanguage/" link
     */
    class AddCustomServerLanguagePack : Function {

        var languagePackId: String? = null

        constructor()

        constructor(languagePackId: String? = null) {

            this.languagePackId = languagePackId

        }

        override val constructor: Int @BsonIgnore get() = 4492771

    }


    /**
     * Adds or changes a custom local language pack to the current localization target
     *
     * @info - Information about the language pack
     *         Language pack ID must start with 'X', consist only of English letters, digits and hyphens, and must not exceed 64 characters
     *         Can be called before authorization
     * @strings - Strings of the new language pack
     */
    class SetCustomLanguagePack : Function {

        var info: LanguagePackInfo? = null
        var strings: Array<LanguagePackString> by WeakField()

        constructor()

        constructor(info: LanguagePackInfo? = null, strings: Array<LanguagePackString>) {

            this.info = info
            this.strings = strings

        }

        override val constructor: Int @BsonIgnore get() = -296742819

    }


    /**
     * Edits information about a custom local language pack in the current localization target
     * Can be called before authorization
     *
     * @info - New information about the custom local language pack
     */
    class EditCustomLanguagePackInfo : Function {

        var info: LanguagePackInfo? = null

        constructor()

        constructor(info: LanguagePackInfo? = null) {

            this.info = info

        }

        override val constructor: Int @BsonIgnore get() = 1320751257

    }


    /**
     * Adds, edits or deletes a string in a custom local language pack
     * Can be called before authorization
     *
     * @languagePackId - Identifier of a previously added custom local language pack in the current localization target
     * @newString - New language pack string
     */
    class SetCustomLanguagePackString : Function {

        var languagePackId: String? = null
        var newString: LanguagePackString? = null

        constructor()

        constructor(languagePackId: String? = null, newString: LanguagePackString? = null) {

            this.languagePackId = languagePackId
            this.newString = newString

        }

        override val constructor: Int @BsonIgnore get() = 1316365592

    }


    /**
     * Deletes all information about a language pack in the current localization target
     * The language pack which is currently in use (including base language pack) or is being synchronized can't be deleted
     * Can be called before authorization
     *
     * @languagePackId - Identifier of the language pack to delete
     */
    class DeleteLanguagePack : Function {

        var languagePackId: String? = null

        constructor()

        constructor(languagePackId: String? = null) {

            this.languagePackId = languagePackId

        }

        override val constructor: Int @BsonIgnore get() = -2108761026

    }


    /**
     * Registers the currently used device for receiving push notifications
     * Returns a globally unique identifier of the push notification subscription
     *
     * @deviceToken - Device token
     * @otherUserIds - List of user identifiers of other users currently using the client
     */
    class RegisterDevice : Function {

        var deviceToken: DeviceToken? = null
        var otherUserIds: IntArray by WeakField()

        constructor()

        constructor(deviceToken: DeviceToken? = null, otherUserIds: IntArray) {

            this.deviceToken = deviceToken
            this.otherUserIds = otherUserIds

        }

        override val constructor: Int @BsonIgnore get() = 1734127493

    }


    /**
     * Handles a push notification
     * Returns error with code 406 if the push notification is not supported and connection to the server is required to fetch new data
     * Can be called before authorization
     *
     * @payload - JSON-encoded push notification payload with all fields sent by the server, and "google.sent_time" and "google.notification.sound" fields added
     */
    class ProcessPushNotification : Function {

        var payload: String? = null

        constructor()

        constructor(payload: String? = null) {

            this.payload = payload

        }

        override val constructor: Int @BsonIgnore get() = 786679952

    }


    /**
     * Returns a globally unique push notification subscription identifier for identification of an account, which has received a push notification
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @payload - JSON-encoded push notification payload
     */
    class GetPushReceiverId : Function {

        var payload: String? = null

        constructor()

        constructor(payload: String? = null) {

            this.payload = payload

        }

        override val constructor: Int @BsonIgnore get() = -286505294

    }


    /**
     * Returns t.me URLs recently visited by a newly registered user
     *
     * @referrer - Google Play referrer to identify the user
     */
    class GetRecentlyVisitedTMeUrls : Function {

        var referrer: String? = null

        constructor()

        constructor(referrer: String? = null) {

            this.referrer = referrer

        }

        override val constructor: Int @BsonIgnore get() = 806754961

    }


    /**
     * Changes user privacy settings
     *
     * @setting - The privacy setting
     * @rules - The new privacy rules
     */
    class SetUserPrivacySettingRules : Function {

        var setting: UserPrivacySetting? = null
        var rules: UserPrivacySettingRules? = null

        constructor()

        constructor(setting: UserPrivacySetting? = null, rules: UserPrivacySettingRules? = null) {

            this.setting = setting
            this.rules = rules

        }

        override val constructor: Int @BsonIgnore get() = -473812741

    }


    /**
     * Returns the current privacy settings
     *
     * @setting - The privacy setting
     */
    class GetUserPrivacySettingRules : Function {

        var setting: UserPrivacySetting? = null

        constructor()

        constructor(setting: UserPrivacySetting? = null) {

            this.setting = setting

        }

        override val constructor: Int @BsonIgnore get() = -2077223311

    }


    /**
     * Returns the value of an option by its name
     * (Check the list of available options on https://core.telegram.org/tdlib/options.) Can be called before authorization
     *
     * @name - The name of the option
     */
    class GetOption : Function {

        var name: String? = null

        constructor()

        constructor(name: String? = null) {

            this.name = name

        }

        override val constructor: Int @BsonIgnore get() = -1572495746

    }


    /**
     * Sets the value of an option
     * (Check the list of available options on https://core.telegram.org/tdlib/options.) Only writable options can be set
     * Can be called before authorization
     *
     * @name - The name of the option
     * @value - The new value of the option
     */
    class SetOption : Function {

        var name: String? = null
        var value: OptionValue? = null

        constructor()

        constructor(name: String? = null, value: OptionValue? = null) {

            this.name = name
            this.value = value

        }

        override val constructor: Int @BsonIgnore get() = 2114670322

    }


    /**
     * Changes the period of inactivity after which the account of the current user will automatically be deleted
     *
     * @ttl - New account TTL
     */
    class SetAccountTtl : Function {

        var ttl: AccountTtl? = null

        constructor()

        constructor(ttl: AccountTtl? = null) {

            this.ttl = ttl

        }

        override val constructor: Int @BsonIgnore get() = 701389032

    }


    /**
     * Returns the period of inactivity after which the account of the current user will automatically be deleted
     */
    class GetAccountTtl : Function() {

        override val constructor: Int @BsonIgnore get() = -443905161

    }


    /**
     * Deletes the account of the current user, deleting all information associated with the user from the server
     * The phone number of the account can be used to create a new account
     * Can be called before authorization when the current authorization state is authorizationStateWaitPassword
     *
     * @reason - The reason why the account was deleted
     */
    class DeleteAccount : Function {

        var reason: String? = null

        constructor()

        constructor(reason: String? = null) {

            this.reason = reason

        }

        override val constructor: Int @BsonIgnore get() = -1203056508

    }


    /**
     * Removes a chat action bar without any other action
     *
     * @chatId - Chat identifier
     */
    class RemoveChatActionBar : Function {

        var chatId: Long by WeakField()

        constructor()

        constructor(chatId: Long) {

            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = -1650968070

    }


    /**
     * Reports a chat to the Telegram moderators
     * Supported only for supergroups, channels, or private chats with bots, since other chats can't be checked by moderators, or when the report is done from the chat action bar
     *
     * @chatId - Chat identifier
     * @reason - The reason for reporting the chat
     * @messageIds - Identifiers of reported messages, if any
     */
    class ReportChat : Function {

        var chatId: Long by WeakField()
        var reason: ChatReportReason? = null
        var messageIds: LongArray by WeakField()

        constructor()

        constructor(chatId: Long, reason: ChatReportReason? = null, messageIds: LongArray) {

            this.chatId = chatId
            this.reason = reason
            this.messageIds = messageIds

        }

        override val constructor: Int @BsonIgnore get() = -312579772

    }


    /**
     * Returns an HTTP URL with the chat statistics
     * Currently this method can be used only for channels
     * Can be used only if SupergroupFullInfo.can_view_statistics == true
     *
     * @chatId - Chat identifier
     * @parameters - Parameters from "tg://statsrefresh?params=******" link
     * @isDark - Pass true if a URL with the dark theme must be returned
     */
    class GetChatStatisticsUrl : Function {

        var chatId: Long by WeakField()
        var parameters: String? = null
        var isDark: Boolean by WeakField()

        constructor()

        constructor(chatId: Long, parameters: String? = null, isDark: Boolean) {

            this.chatId = chatId
            this.parameters = parameters
            this.isDark = isDark

        }

        override val constructor: Int @BsonIgnore get() = 1114621183

    }


    /**
     * Returns storage usage statistics
     * Can be called before authorization
     *
     * @chatLimit - The maximum number of chats with the largest storage usage for which separate statistics should be returned
     *              All other chats will be grouped in entries with chat_id == 0
     *              If the chat info database is not used, the chat_limit is ignored and is always set to 0
     */
    class GetStorageStatistics : Function {

        var chatLimit: Int by WeakField()

        constructor()

        constructor(chatLimit: Int) {

            this.chatLimit = chatLimit

        }

        override val constructor: Int @BsonIgnore get() = -853193929

    }


    /**
     * Quickly returns approximate storage usage statistics
     * Can be called before authorization
     */
    class GetStorageStatisticsFast : Function() {

        override val constructor: Int @BsonIgnore get() = 61368066

    }


    /**
     * Returns database statistics
     */
    class GetDatabaseStatistics : Function() {

        override val constructor: Int @BsonIgnore get() = -1942760263

    }


    /**
     * Optimizes storage usage, i.e
     * Deletes some files and returns new storage usage statistics
     * Secret thumbnails can't be deleted
     *
     * @size - Limit on the total size of files after deletion
     *         Pass -1 to use the default limit
     * @ttl - Limit on the time that has passed since the last time a file was accessed (or creation time for some filesystems)
     *        Pass -1 to use the default limit
     * @count - Limit on the total count of files after deletion
     *          Pass -1 to use the default limit
     * @immunityDelay - The amount of time after the creation of a file during which it can't be deleted, in seconds
     *                  Pass -1 to use the default value
     * @fileTypes - If not empty, only files with the given type(s) are considered
     *              By default, all types except thumbnails, profile photos, stickers and wallpapers are deleted
     * @chatIds - If not empty, only files from the given chats are considered
     *            Use 0 as chat identifier to delete files not belonging to any chat (e.g., profile photos)
     * @excludeChatIds - If not empty, files from the given chats are excluded
     *                   Use 0 as chat identifier to exclude all files not belonging to any chat (e.g., profile photos)
     * @chatLimit - Same as in getStorageStatistics
     *              Affects only returned statistics
     */
    class OptimizeStorage : Function {

        var size: Long by WeakField()
        var ttl: Int by WeakField()
        var count: Int by WeakField()
        var immunityDelay: Int by WeakField()
        var fileTypes: Array<FileType> by WeakField()
        var chatIds: LongArray by WeakField()
        var excludeChatIds: LongArray by WeakField()
        var chatLimit: Int by WeakField()

        constructor()

        constructor(size: Long, ttl: Int, count: Int, immunityDelay: Int, fileTypes: Array<FileType>, chatIds: LongArray, excludeChatIds: LongArray, chatLimit: Int) {

            this.size = size
            this.ttl = ttl
            this.count = count
            this.immunityDelay = immunityDelay
            this.fileTypes = fileTypes
            this.chatIds = chatIds
            this.excludeChatIds = excludeChatIds
            this.chatLimit = chatLimit

        }

        override val constructor: Int @BsonIgnore get() = 980397489

    }


    /**
     * Sets the current network type
     * Can be called before authorization
     * Calling this method forces all network connections to reopen, mitigating the delay in switching between different networks, so it should be called whenever the network is changed, even if the network type remains the same
     * Network type is used to check whether the library can use the network at all and also for collecting detailed network data usage statistics
     *
     * @type - The new network type
     *         By default, networkTypeOther
     */
    class SetNetworkType : Function {

        var type: NetworkType? = null

        constructor()

        constructor(type: NetworkType? = null) {

            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -701635234

    }


    /**
     * Returns network data usage statistics
     * Can be called before authorization
     *
     * @onlyCurrent - If true, returns only data for the current library launch
     */
    class GetNetworkStatistics : Function {

        var onlyCurrent: Boolean by WeakField()

        constructor()

        constructor(onlyCurrent: Boolean) {

            this.onlyCurrent = onlyCurrent

        }

        override val constructor: Int @BsonIgnore get() = -986228706

    }


    /**
     * Adds the specified data to data usage statistics
     * Can be called before authorization
     *
     * @entry - The network statistics entry with the data to be added to statistics
     */
    class AddNetworkStatistics : Function {

        var entry: NetworkStatisticsEntry? = null

        constructor()

        constructor(entry: NetworkStatisticsEntry? = null) {

            this.entry = entry

        }

        override val constructor: Int @BsonIgnore get() = 1264825305

    }


    /**
     * Resets all network data usage statistics to zero
     * Can be called before authorization
     */
    class ResetNetworkStatistics : Function() {

        override val constructor: Int @BsonIgnore get() = 1646452102

    }


    /**
     * Returns auto-download settings presets for the currently logged in user
     */
    class GetAutoDownloadSettingsPresets : Function() {

        override val constructor: Int @BsonIgnore get() = -1721088201

    }


    /**
     * Sets auto-download settings
     *
     * @settings - New user auto-download settings
     * @type - Type of the network for which the new settings are applied
     */
    class SetAutoDownloadSettings : Function {

        var settings: AutoDownloadSettings? = null
        var type: NetworkType? = null

        constructor()

        constructor(settings: AutoDownloadSettings? = null, type: NetworkType? = null) {

            this.settings = settings
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -353671948

    }


    /**
     * Returns one of the available Telegram Passport elements
     *
     * @type - Telegram Passport element type
     * @password - Password of the current user
     */
    class GetPassportElement : Function {

        var type: PassportElementType? = null
        var password: String? = null

        constructor()

        constructor(type: PassportElementType? = null, password: String? = null) {

            this.type = type
            this.password = password

        }

        override val constructor: Int @BsonIgnore get() = -1882398342

    }


    /**
     * Returns all available Telegram Passport elements
     *
     * @password - Password of the current user
     */
    class GetAllPassportElements : Function {

        var password: String? = null

        constructor()

        constructor(password: String? = null) {

            this.password = password

        }

        override val constructor: Int @BsonIgnore get() = -2038945045

    }


    /**
     * Adds an element to the user's Telegram Passport
     * May return an error with a message "PHONE_VERIFICATION_NEEDED" or "EMAIL_VERIFICATION_NEEDED" if the chosen phone number or the chosen email address must be verified first
     *
     * @element - Input Telegram Passport element
     * @password - Password of the current user
     */
    class SetPassportElement : Function {

        var element: InputPassportElement? = null
        var password: String? = null

        constructor()

        constructor(element: InputPassportElement? = null, password: String? = null) {

            this.element = element
            this.password = password

        }

        override val constructor: Int @BsonIgnore get() = 2068173212

    }


    /**
     * Deletes a Telegram Passport element
     *
     * @type - Element type
     */
    class DeletePassportElement : Function {

        var type: PassportElementType? = null

        constructor()

        constructor(type: PassportElementType? = null) {

            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -1719555468

    }


    /**
     * Informs the user that some of the elements in their Telegram Passport contain errors
     * For bots only
     * The user will not be able to resend the elements, until the errors are fixed
     *
     * @userId - User identifier
     * @errors - The errors
     */
    class SetPassportElementErrors : Function {

        var userId: Int by WeakField()
        var errors: Array<InputPassportElementError> by WeakField()

        constructor()

        constructor(userId: Int, errors: Array<InputPassportElementError>) {

            this.userId = userId
            this.errors = errors

        }

        override val constructor: Int @BsonIgnore get() = 1455869875

    }


    /**
     * Returns an IETF language tag of the language preferred in the country, which should be used to fill native fields in Telegram Passport personal details
     * Returns a 404 error if unknown
     *
     * @countryCode - A two-letter ISO 3166-1 alpha-2 country code
     */
    class GetPreferredCountryLanguage : Function {

        var countryCode: String? = null

        constructor()

        constructor(countryCode: String? = null) {

            this.countryCode = countryCode

        }

        override val constructor: Int @BsonIgnore get() = -933049386

    }


    /**
     * Sends a code to verify a phone number to be added to a user's Telegram Passport
     *
     * @phoneNumber - The phone number of the user, in international format
     * @settings - Settings for the authentication of the user's phone number
     */
    class SendPhoneNumberVerificationCode : Function {

        var phoneNumber: String? = null
        var settings: PhoneNumberAuthenticationSettings? = null

        constructor()

        constructor(phoneNumber: String? = null, settings: PhoneNumberAuthenticationSettings? = null) {

            this.phoneNumber = phoneNumber
            this.settings = settings

        }

        override val constructor: Int @BsonIgnore get() = 2081689035

    }


    /**
     * Re-sends the code to verify a phone number to be added to a user's Telegram Passport
     */
    class ResendPhoneNumberVerificationCode : Function() {

        override val constructor: Int @BsonIgnore get() = 1367629820

    }


    /**
     * Checks the phone number verification code for Telegram Passport
     *
     * @code - Verification code
     */
    class CheckPhoneNumberVerificationCode : Function {

        var code: String? = null

        constructor()

        constructor(code: String? = null) {

            this.code = code

        }

        override val constructor: Int @BsonIgnore get() = 1497462718

    }


    /**
     * Sends a code to verify an email address to be added to a user's Telegram Passport
     *
     * @emailAddress - Email address
     */
    class SendEmailAddressVerificationCode : Function {

        var emailAddress: String? = null

        constructor()

        constructor(emailAddress: String? = null) {

            this.emailAddress = emailAddress

        }

        override val constructor: Int @BsonIgnore get() = -221621379

    }


    /**
     * Re-sends the code to verify an email address to be added to a user's Telegram Passport
     */
    class ResendEmailAddressVerificationCode : Function() {

        override val constructor: Int @BsonIgnore get() = -1872416732

    }


    /**
     * Checks the email address verification code for Telegram Passport
     *
     * @code - Verification code
     */
    class CheckEmailAddressVerificationCode : Function {

        var code: String? = null

        constructor()

        constructor(code: String? = null) {

            this.code = code

        }

        override val constructor: Int @BsonIgnore get() = -426386685

    }


    /**
     * Returns a Telegram Passport authorization form for sharing data with a service
     *
     * @botUserId - User identifier of the service's bot
     * @scope - Telegram Passport element types requested by the service
     * @publicKey - Service's public_key
     * @nonce - Authorization form nonce provided by the service
     */
    class GetPassportAuthorizationForm : Function {

        var botUserId: Int by WeakField()
        var scope: String? = null
        var publicKey: String? = null
        var nonce: String? = null

        constructor()

        constructor(botUserId: Int, scope: String? = null, publicKey: String? = null, nonce: String? = null) {

            this.botUserId = botUserId
            this.scope = scope
            this.publicKey = publicKey
            this.nonce = nonce

        }

        override val constructor: Int @BsonIgnore get() = -1468394095

    }


    /**
     * Returns already available Telegram Passport elements suitable for completing a Telegram Passport authorization form
     * Result can be received only once for each authorization form
     *
     * @autorizationFormId - Authorization form identifier
     * @password - Password of the current user
     */
    class GetPassportAuthorizationFormAvailableElements : Function {

        var autorizationFormId: Int by WeakField()
        var password: String? = null

        constructor()

        constructor(autorizationFormId: Int, password: String? = null) {

            this.autorizationFormId = autorizationFormId
            this.password = password

        }

        override val constructor: Int @BsonIgnore get() = 1738134754

    }


    /**
     * Sends a Telegram Passport authorization form, effectively sharing data with the service
     * This method must be called after getPassportAuthorizationFormAvailableElements if some previously available elements need to be used
     *
     * @autorizationFormId - Authorization form identifier
     * @types - Types of Telegram Passport elements chosen by user to complete the authorization form
     */
    class SendPassportAuthorizationForm : Function {

        var autorizationFormId: Int by WeakField()
        var types: Array<PassportElementType> by WeakField()

        constructor()

        constructor(autorizationFormId: Int, types: Array<PassportElementType>) {

            this.autorizationFormId = autorizationFormId
            this.types = types

        }

        override val constructor: Int @BsonIgnore get() = -602402218

    }


    /**
     * Sends phone number confirmation code
     * Should be called when user presses "https://t.me/confirmphone?phone=*******&hash=**********" or "tg://confirmphone?phone=*******&hash=**********" link
     *
     * @hash - Value of the "hash" parameter from the link
     * @phoneNumber - Value of the "phone" parameter from the link
     * @settings - Settings for the authentication of the user's phone number
     */
    class SendPhoneNumberConfirmationCode : Function {

        var hash: String? = null
        var phoneNumber: String? = null
        var settings: PhoneNumberAuthenticationSettings? = null

        constructor()

        constructor(hash: String? = null, phoneNumber: String? = null, settings: PhoneNumberAuthenticationSettings? = null) {

            this.hash = hash
            this.phoneNumber = phoneNumber
            this.settings = settings

        }

        override val constructor: Int @BsonIgnore get() = -1901171495

    }


    /**
     * Resends phone number confirmation code
     */
    class ResendPhoneNumberConfirmationCode : Function() {

        override val constructor: Int @BsonIgnore get() = 2069068522

    }


    /**
     * Checks phone number confirmation code
     *
     * @code - The phone number confirmation code
     */
    class CheckPhoneNumberConfirmationCode : Function {

        var code: String? = null

        constructor()

        constructor(code: String? = null) {

            this.code = code

        }

        override val constructor: Int @BsonIgnore get() = -1348060966

    }


    /**
     * Informs the server about the number of pending bot updates if they haven't been processed for a long time
     * For bots only
     *
     * @pendingUpdateCount - The number of pending updates
     * @errorMessage - The last error message
     */
    class SetBotUpdatesStatus : Function {

        var pendingUpdateCount: Int by WeakField()
        var errorMessage: String? = null

        constructor()

        constructor(pendingUpdateCount: Int, errorMessage: String? = null) {

            this.pendingUpdateCount = pendingUpdateCount
            this.errorMessage = errorMessage

        }

        override val constructor: Int @BsonIgnore get() = -1154926191

    }


    /**
     * Uploads a PNG image with a sticker
     * For bots only
     * Returns the uploaded file
     *
     * @userId - Sticker file owner
     * @pngSticker - PNG image with the sticker
     *               Must be up to 512 kB in size and fit in 512x512 square
     */
    class UploadStickerFile : Function {

        var userId: Int by WeakField()
        var pngSticker: InputFile? = null

        constructor()

        constructor(userId: Int, pngSticker: InputFile? = null) {

            this.userId = userId
            this.pngSticker = pngSticker

        }

        override val constructor: Int @BsonIgnore get() = 1134087551

    }


    /**
     * Creates a new sticker set
     * For bots only
     * Returns the newly created sticker set
     *
     * @userId - Sticker set owner
     * @title - Sticker set title
     * @name - Sticker set name
     *         Can contain only English letters, digits and underscores
     *         Must end with *"_by_<bot username>"* (*<bot_username>* is case insensitive)
     * @isMasks - True, if stickers are masks
     * @stickers - List of stickers to be added to the set
     */
    class CreateNewStickerSet : Function {

        var userId: Int by WeakField()
        var title: String? = null
        var name: String? = null
        var isMasks: Boolean by WeakField()
        var stickers: Array<InputSticker> by WeakField()

        constructor()

        constructor(userId: Int, title: String? = null, name: String? = null, isMasks: Boolean, stickers: Array<InputSticker>) {

            this.userId = userId
            this.title = title
            this.name = name
            this.isMasks = isMasks
            this.stickers = stickers

        }

        override val constructor: Int @BsonIgnore get() = 205093058

    }


    /**
     * Adds a new sticker to a set
     * For bots only
     * Returns the sticker set
     *
     * @userId - Sticker set owner
     * @name - Sticker set name
     * @sticker - Sticker to add to the set
     */
    class AddStickerToSet : Function {

        var userId: Int by WeakField()
        var name: String? = null
        var sticker: InputSticker? = null

        constructor()

        constructor(userId: Int, name: String? = null, sticker: InputSticker? = null) {

            this.userId = userId
            this.name = name
            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = 1422402800

    }


    /**
     * Changes the position of a sticker in the set to which it belongs
     * For bots only
     * The sticker set must have been created by the bot
     *
     * @sticker - Sticker
     * @position - New position of the sticker in the set, zero-based
     */
    class SetStickerPositionInSet : Function {

        var sticker: InputFile? = null
        var position: Int by WeakField()

        constructor()

        constructor(sticker: InputFile? = null, position: Int) {

            this.sticker = sticker
            this.position = position

        }

        override val constructor: Int @BsonIgnore get() = 2075281185

    }


    /**
     * Removes a sticker from the set to which it belongs
     * For bots only
     * The sticker set must have been created by the bot
     *
     * @sticker - Sticker
     */
    class RemoveStickerFromSet : Function {

        var sticker: InputFile? = null

        constructor()

        constructor(sticker: InputFile? = null) {

            this.sticker = sticker

        }

        override val constructor: Int @BsonIgnore get() = 1642196644

    }


    /**
     * Returns information about a file with a map thumbnail in PNG format
     * Only map thumbnail files with size less than 1MB can be downloaded
     *
     * @location - Location of the map center
     * @zoom - Map zoom level
     * @width - Map width in pixels before applying scale
     * @height - Map height in pixels before applying scale
     * @scale - Map scale
     * @chatId - Identifier of a chat, in which the thumbnail will be shown
     *           Use 0 if unknown
     */
    class GetMapThumbnailFile : Function {

        var location: Location? = null
        var zoom: Int by WeakField()
        var width: Int by WeakField()
        var height: Int by WeakField()
        var scale: Int by WeakField()
        var chatId: Long by WeakField()

        constructor()

        constructor(location: Location? = null, zoom: Int, width: Int, height: Int, scale: Int, chatId: Long) {

            this.location = location
            this.zoom = zoom
            this.width = width
            this.height = height
            this.scale = scale
            this.chatId = chatId

        }

        override val constructor: Int @BsonIgnore get() = -152660070

    }


    /**
     * Accepts Telegram terms of services
     *
     * @termsOfServiceId - Terms of service identifier
     */
    class AcceptTermsOfService : Function {

        var termsOfServiceId: String? = null

        constructor()

        constructor(termsOfServiceId: String? = null) {

            this.termsOfServiceId = termsOfServiceId

        }

        override val constructor: Int @BsonIgnore get() = 2130576356

    }


    /**
     * Sends a custom request
     * For bots only
     *
     * @method - The method name
     * @parameters - JSON-serialized method parameters
     */
    class SendCustomRequest : Function {

        var method: String? = null
        var parameters: String? = null

        constructor()

        constructor(method: String? = null, parameters: String? = null) {

            this.method = method
            this.parameters = parameters

        }

        override val constructor: Int @BsonIgnore get() = 285045153

    }


    /**
     * Answers a custom query
     * For bots only
     *
     * @customQueryId - Identifier of a custom query
     * @data - JSON-serialized answer to the query
     */
    class AnswerCustomQuery : Function {

        var customQueryId: Long by WeakField()
        var data: String? = null

        constructor()

        constructor(customQueryId: Long, data: String? = null) {

            this.customQueryId = customQueryId
            this.data = data

        }

        override val constructor: Int @BsonIgnore get() = -1293603521

    }


    /**
     * Sends a request to TON lite server through Telegram servers
     * Can be called before authorization
     *
     * @request - The request
     */
    class SendTonLiteServerRequest : Function {

        var request: ByteArray by WeakField()

        constructor()

        constructor(request: ByteArray) {

            this.request = request

        }

        override val constructor: Int @BsonIgnore get() = 964887713

    }


    /**
     * Returns a salt to be used with locally stored password to access a local TON-based wallet
     */
    class GetTonWalletPasswordSalt : Function() {

        override val constructor: Int @BsonIgnore get() = -642507025

    }


    /**
     * Succeeds after a specified amount of time has passed
     * Can be called before authorization
     * Can be called before initialization
     *
     * @seconds - Number of seconds before the function returns
     */
    class SetAlarm : Function {

        var seconds: Double by WeakField()

        constructor()

        constructor(seconds: Double) {

            this.seconds = seconds

        }

        override val constructor: Int @BsonIgnore get() = -873497067

    }


    /**
     * Uses current user IP to found their country
     * Returns two-letter ISO 3166-1 alpha-2 country code
     * Can be called before authorization
     */
    class GetCountryCode : Function() {

        override val constructor: Int @BsonIgnore get() = 1540593906

    }


    /**
     * Returns the default text for invitation messages to be used as a placeholder when the current user invites friends to Telegram
     */
    class GetInviteText : Function() {

        override val constructor: Int @BsonIgnore get() = 794573512

    }


    /**
     * Returns information about a tg:// deep link
     * Use "tg://need_update_for_some_feature" or "tg:some_unsupported_feature" for testing
     * Returns a 404 error for unknown links
     * Can be called before authorization
     *
     * @link - The link
     */
    class GetDeepLinkInfo : Function {

        var link: String? = null

        constructor()

        constructor(link: String? = null) {

            this.link = link

        }

        override val constructor: Int @BsonIgnore get() = 680673150

    }


    /**
     * Returns application config, provided by the server
     * Can be called before authorization
     */
    class GetApplicationConfig : Function() {

        override val constructor: Int @BsonIgnore get() = -1823144318

    }


    /**
     * Saves application log event on the server
     * Can be called before authorization
     *
     * @type - Event type
     * @chatId - Optional chat identifier, associated with the event
     * @data - The log event data
     */
    class SaveApplicationLogEvent : Function {

        var type: String? = null
        var chatId: Long by WeakField()
        var data: JsonValue? = null

        constructor()

        constructor(type: String? = null, chatId: Long, data: JsonValue? = null) {

            this.type = type
            this.chatId = chatId
            this.data = data

        }

        override val constructor: Int @BsonIgnore get() = -811154930

    }


    /**
     * Adds a proxy server for network requests
     * Can be called before authorization
     *
     * @server - Proxy server IP address
     * @port - Proxy server port
     * @enable - True, if the proxy should be enabled
     * @type - Proxy type
     */
    class AddProxy : Function {

        var server: String? = null
        var port: Int by WeakField()
        var enable: Boolean by WeakField()
        var type: ProxyType? = null

        constructor()

        constructor(server: String? = null, port: Int, enable: Boolean, type: ProxyType? = null) {

            this.server = server
            this.port = port
            this.enable = enable
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = 331529432

    }


    /**
     * Edits an existing proxy server for network requests
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     * @server - Proxy server IP address
     * @port - Proxy server port
     * @enable - True, if the proxy should be enabled
     * @type - Proxy type
     */
    class EditProxy : Function {

        var proxyId: Int by WeakField()
        var server: String? = null
        var port: Int by WeakField()
        var enable: Boolean by WeakField()
        var type: ProxyType? = null

        constructor()

        constructor(proxyId: Int, server: String? = null, port: Int, enable: Boolean, type: ProxyType? = null) {

            this.proxyId = proxyId
            this.server = server
            this.port = port
            this.enable = enable
            this.type = type

        }

        override val constructor: Int @BsonIgnore get() = -1605883821

    }


    /**
     * Enables a proxy
     * Only one proxy can be enabled at a time
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     */
    class EnableProxy : Function {

        var proxyId: Int by WeakField()

        constructor()

        constructor(proxyId: Int) {

            this.proxyId = proxyId

        }

        override val constructor: Int @BsonIgnore get() = 1494450838

    }


    /**
     * Disables the currently enabled proxy
     * Can be called before authorization
     */
    class DisableProxy : Function() {

        override val constructor: Int @BsonIgnore get() = -2100095102

    }


    /**
     * Removes a proxy server
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     */
    class RemoveProxy : Function {

        var proxyId: Int by WeakField()

        constructor()

        constructor(proxyId: Int) {

            this.proxyId = proxyId

        }

        override val constructor: Int @BsonIgnore get() = 1369219847

    }


    /**
     * Returns list of proxies that are currently set up
     * Can be called before authorization
     */
    class GetProxies : Function() {

        override val constructor: Int @BsonIgnore get() = -95026381

    }


    /**
     * Returns an HTTPS link, which can be used to add a proxy
     * Available only for SOCKS5 and MTProto proxies
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     */
    class GetProxyLink : Function {

        var proxyId: Int by WeakField()

        constructor()

        constructor(proxyId: Int) {

            this.proxyId = proxyId

        }

        override val constructor: Int @BsonIgnore get() = -1285597664

    }


    /**
     * Computes time needed to receive a response from a Telegram server through a proxy
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     *            Use 0 to ping a Telegram server without a proxy
     */
    class PingProxy : Function {

        var proxyId: Int by WeakField()

        constructor()

        constructor(proxyId: Int) {

            this.proxyId = proxyId

        }

        override val constructor: Int @BsonIgnore get() = -979681103

    }


    /**
     * Sets new log stream for internal logging of TDLib
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @logStream - New log stream
     */
    class SetLogStream : Function {

        var logStream: LogStream? = null

        constructor()

        constructor(logStream: LogStream? = null) {

            this.logStream = logStream

        }

        override val constructor: Int @BsonIgnore get() = -1364199535

    }


    /**
     * Returns information about currently used log stream for internal logging of TDLib
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     */
    class GetLogStream : Function() {

        override val constructor: Int @BsonIgnore get() = 1167608667

    }


    /**
     * Sets the verbosity level of the internal logging of TDLib
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @newVerbosityLevel - New value of the verbosity level for logging
     *                      Value 0 corresponds to fatal errors, value 1 corresponds to errors, value 2 corresponds to warnings and debug warnings, value 3 corresponds to informational, value 4 corresponds to debug, value 5 corresponds to verbose debug, value greater than 5 and up to 1023 can be used to enable even more logging
     */
    class SetLogVerbosityLevel : Function {

        var newVerbosityLevel: Int by WeakField()

        constructor()

        constructor(newVerbosityLevel: Int) {

            this.newVerbosityLevel = newVerbosityLevel

        }

        override val constructor: Int @BsonIgnore get() = -303429678

    }


    /**
     * Returns current verbosity level of the internal logging of TDLib
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     */
    class GetLogVerbosityLevel : Function() {

        override val constructor: Int @BsonIgnore get() = 594057956

    }


    /**
     * Returns list of available TDLib internal log tags, for example, ["actor", "binlog", "connections", "notifications", "proxy"]
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     */
    class GetLogTags : Function() {

        override val constructor: Int @BsonIgnore get() = -254449190

    }


    /**
     * Sets the verbosity level for a specified TDLib internal log tag
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @tag - Logging tag to change verbosity level
     * @newVerbosityLevel - New verbosity level
     */
    class SetLogTagVerbosityLevel : Function {

        var tag: String? = null
        var newVerbosityLevel: Int by WeakField()

        constructor()

        constructor(tag: String? = null, newVerbosityLevel: Int) {

            this.tag = tag
            this.newVerbosityLevel = newVerbosityLevel

        }

        override val constructor: Int @BsonIgnore get() = -2095589738

    }


    /**
     * Returns current verbosity level for a specified TDLib internal log tag
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @tag - Logging tag to change verbosity level
     */
    class GetLogTagVerbosityLevel : Function {

        var tag: String? = null

        constructor()

        constructor(tag: String? = null) {

            this.tag = tag

        }

        override val constructor: Int @BsonIgnore get() = 951004547

    }


    /**
     * Adds a message to TDLib internal log
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @verbosityLevel - The minimum verbosity level needed for the message to be logged, 0-1023
     * @text - Text of a message to log
     */
    class AddLogMessage : Function {

        var verbosityLevel: Int by WeakField()
        var text: String? = null

        constructor()

        constructor(verbosityLevel: Int, text: String? = null) {

            this.verbosityLevel = verbosityLevel
            this.text = text

        }

        override val constructor: Int @BsonIgnore get() = 1597427692

    }


    /**
     * Does nothing
     * For testing only
     * This is an offline method
     * Can be called before authorization
     */
    class TestCallEmpty : Function() {

        override val constructor: Int @BsonIgnore get() = -627291626

    }


    /**
     * Returns the received string
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - String to return
     */
    class TestCallString : Function {

        var x: String? = null

        constructor()

        constructor(x: String? = null) {

            this.x = x

        }

        override val constructor: Int @BsonIgnore get() = -1732818385

    }


    /**
     * Returns the received bytes
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Bytes to return
     */
    class TestCallBytes : Function {

        var x: ByteArray by WeakField()

        constructor()

        constructor(x: ByteArray) {

            this.x = x

        }

        override val constructor: Int @BsonIgnore get() = -736011607

    }


    /**
     * Returns the received vector of numbers
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Vector of numbers to return
     */
    class TestCallVectorInt : Function {

        var x: IntArray by WeakField()

        constructor()

        constructor(x: IntArray) {

            this.x = x

        }

        override val constructor: Int @BsonIgnore get() = -2137277793

    }


    /**
     * Returns the received vector of objects containing a number
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Vector of objects to return
     */
    class TestCallVectorIntObject : Function {

        var x: Array<TestInt> by WeakField()

        constructor()

        constructor(x: Array<TestInt>) {

            this.x = x

        }

        override val constructor: Int @BsonIgnore get() = 1825428218

    }


    /**
     * Returns the received vector of strings
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Vector of strings to return
     */
    class TestCallVectorString : Function {

        var x: Array<String> by WeakField()

        constructor()

        constructor(x: Array<String>) {

            this.x = x

        }

        override val constructor: Int @BsonIgnore get() = -408600900

    }


    /**
     * Returns the received vector of objects containing a string
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Vector of objects to return
     */
    class TestCallVectorStringObject : Function {

        var x: Array<TestString> by WeakField()

        constructor()

        constructor(x: Array<TestString>) {

            this.x = x

        }

        override val constructor: Int @BsonIgnore get() = 1527666429

    }


    /**
     * Returns the squared received number
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Number to square
     */
    class TestSquareInt : Function {

        var x: Int by WeakField()

        constructor()

        constructor(x: Int) {

            this.x = x

        }

        override val constructor: Int @BsonIgnore get() = -60135024

    }


    /**
     * Sends a simple network request to the Telegram servers
     * For testing only
     * Can be called before authorization
     */
    class TestNetwork : Function() {

        override val constructor: Int @BsonIgnore get() = -1343998901

    }


    /**
     * Sends a simple network request to the Telegram servers via proxy
     * For testing only
     * Can be called before authorization
     *
     * @server - Proxy server IP address
     * @port - Proxy server port
     * @type - Proxy type
     * @dcId - Identifier of a datacenter, with which to test connection
     * @timeout - The maximum overall timeout for the request
     */
    class TestProxy : Function {

        var server: String? = null
        var port: Int by WeakField()
        var type: ProxyType? = null
        var dcId: Int by WeakField()
        var timeout: Double by WeakField()

        constructor()

        constructor(server: String? = null, port: Int, type: ProxyType? = null, dcId: Int, timeout: Double) {

            this.server = server
            this.port = port
            this.type = type
            this.dcId = dcId
            this.timeout = timeout

        }

        override val constructor: Int @BsonIgnore get() = -1197366626

    }


    /**
     * Forces an updates.getDifference call to the Telegram servers
     * For testing only
     */
    class TestGetDifference : Function() {

        override val constructor: Int @BsonIgnore get() = 1747084069

    }


    /**
     * Does nothing and ensures that the Update object is used
     * For testing only
     * This is an offline method
     * Can be called before authorization
     */
    class TestUseUpdate : Function() {

        override val constructor: Int @BsonIgnore get() = 717094686

    }


    /**
     * Returns the specified error and ensures that the Error object is used
     * For testing only
     * This is an offline method
     * Can be called before authorization
     * Can be called synchronously
     *
     * @error - The error to be returned
     */
    class TestReturnError : Function {

        var error: Error? = null

        constructor()

        constructor(error: Error? = null) {

            this.error = error

        }

        override val constructor: Int @BsonIgnore get() = 455179506

    }


}
