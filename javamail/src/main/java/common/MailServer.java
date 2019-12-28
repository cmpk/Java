package common;

import lombok.Getter;

/**
 * メールサーバとメール送信の設定を保持する.
 *
 */
public abstract class MailServer {
    public static final int DEFAULT_CONNECTION_TIMEOUT = 10000;
    public static final int DEFAULT_TIMEOUT = 10000;

    @Getter
    private String username = null;

    @Getter
    private String password = null;

    @Getter
    private String charset = null;

    @Getter
    private String encoding = null;

    @Getter
    private String host = null;

    @Getter
    private String port = null;

    @Getter
    private int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;

    @Getter
    private int timeout = DEFAULT_TIMEOUT;
}
