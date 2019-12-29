package common;

/**
 * 外部コマンド実行時に発生するエラーのラッパー.
 * エラー発生個所の詳細を提供する.
 */
public class CommandException extends Exception {
    public CommandException(final String message) {
        super(message);
    }

    public CommandException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
