package db.oracle;

import lombok.Getter;

/**
 * SQL*Loader 利用時に発生したエラー.
 */
public class SQLLoaderException extends Exception {
    /**
     * SQL*Loader から出力されたログファイルのパス
     */
    @Getter
    private final String logFilepath;

    /**
     * SQL*Loader コマンドから標準出力および標準エラーに出力された内容
     */
    @Getter
    private final String output;

    public SQLLoaderException(final String message, final Throwable cause) {
        super(message, cause);
        this.logFilepath = "";
        this.output = "";
    }

    /**
     * コンストラクタ.
     *
     * @param message     エラーメッセージ.
     * @param logFilepath SQL*Loader から出力されたログファイルのパス.
     * @param output      SQ+*Loader から標準出力および標準エラーに出力された内容.
     */
    public SQLLoaderException(final String message, final String logFilepath, final String output) {
        super(message);
        this.logFilepath = logFilepath;
        this.output = output;
    }

    /**
     * コンストラクタ.
     *
     * @param message     エラーメッセージ.
     * @param cause       本エラー発生より前に発生したエラーであり、本エラーの原因となるエラー.
     * @param logFilepath SQL*Loader から出力されたログファイルのパス.
     * @param output      SQ+*Loader から標準出力および標準エラーに出力された内容.
     */
    public SQLLoaderException(final String message, final Throwable cause, final String logFilepath, final String output) {
        super(message, cause);
        this.logFilepath = logFilepath;
        this.output = output;
    }

}
