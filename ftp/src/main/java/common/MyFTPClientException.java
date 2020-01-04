package common;

public class MyFTPClientException extends Exception {
    /**
     * コンストラクタ.
     *
     * @param message     エラーメッセージ.
     */
    public MyFTPClientException(final String message) {
        super(message);
    }

    /**
     * コンストラクタ.
     *
     * @param message     エラーメッセージ.
     * @param cause       本エラー発生より前に発生したエラーであり、本エラーの原因となるエラー.
     */
    public MyFTPClientException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
