package tooko.td;

/**
 * Interface for managing the internal logging of TDLib. By default TDLib writes logs to stderr or an OS specific log
 * and uses a verbosity level of 5.
 */
public class Log {

    /**
     * Sets the path to the file to where the internal TDLib log will be written. By default TDLib writes logs to
     * stderr or an OS specific log. Use this method to write the log to a file instead.
     *
     * @param filePath Path to a file where the internal TDLib log will be written. Use an empty path to switch back
     *                 to the default logging behaviour.
     * @return True on success, or false otherwise, i.e. if the file can't be opened for writing.
     */
    public static native boolean setFilePath(String filePath);

    /**
     * Sets maximum size of the file to where the internal TDLib log is written before the file will be auto-rotated.
     * Unused if log is not written to a file. Defaults to 10 MB.
     *
     * @param maxFileSize Maximum size of the file to where the internal TDLib log is written before the file will be
     *                    auto-rotated. Should be positive.
     */
    public static native void setMaxFileSize(long maxFileSize);

    /**
     * Sets the verbosity level of the internal logging of TDLib. By default the TDLib uses a verbosity level of 5
     * for logging.
     *
     * @param verbosityLevel New value of the verbosity level for logging. Value 0 corresponds to fatal errors, value
     *                       1 corresponds to errors, value 2 corresponds to warnings and debug warnings, value 3
     *                       corresponds to informational, value 4 corresponds to debug, value 5 corresponds to
     *                       verbose debug, value greater than 5 and up to 1024 can be used to enable even more logging.
     */
    public static native void setVerbosityLevel(int verbosityLevel);

    private static void onFatalError(final String errorMessage) {

        new Thread(() -> System.out.println(errorMessage)).start();


    }

}
