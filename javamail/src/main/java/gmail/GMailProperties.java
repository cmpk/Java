package gmail;

import java.io.FileNotFoundException;
import java.io.IOException;

import common.MailServer;
import common.PropertyStorage;

public class GMailProperties extends MailServer {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CHARSET = "charset";
    public static final String ENCODING = "encoding";
    public static final String HOST = "host";
    public static final String PORT = "port";

    public static final String FROM = "from";
    public static final String REPLY_TO = "reply_to";

    protected PropertyStorage properties; // SUPPRESS CHECKSTYLE define as protected in order to allow extended class to use it without any restrictions

    public GMailProperties(final String filepath, final String charsetName) throws FileNotFoundException, IOException {
        this.properties = new PropertyStorage(filepath, charsetName);
    }

    /**
     * 設定ファイルに記載されたユーザ名（username）を取得する
     */
    @Override
    public String getUsername() {
        return this.properties.getPropertyString(USERNAME);
    }

    /**
     * 設定ファイルに記載されたパスワード（password）を取得する
     */
    @Override
    public String getPassword() {
        return this.properties.getPropertyString(PASSWORD);
    }

    /**
     * 設定ファイルに記載された文字コード（charset）を取得する
     */
    @Override
    public String getCharset() {
        return this.properties.getPropertyString(CHARSET);
    }

    /**
     * 設定ファイルに記載されたエンコード方式（encoding）を取得する
     */
    @Override
    public String getEncoding() {
        return this.properties.getPropertyString(ENCODING);
    }

    /**
     * 設定ファイルに記載されたメールホスト名（host）を取得する
     */
    @Override
    public String getHost() {
        return this.properties.getPropertyString(HOST);
    }

    /**
     * 設定ファイルに記載されたポート番号（port）を取得する
     */
    @Override
    public String getPort() {
        return this.properties.getPropertyString(PORT);
    }

    /**
     * 設定ファイルに記載された送信者（from）を取得する
     */
    public String getFrom() {
        return this.properties.getPropertyString(FROM);
    }

    /**
     * 設定ファイルに記載された返信先（reply_to）を取得する
     */
    public String getReplyTo() {
        return this.properties.getPropertyString(REPLY_TO);
    }
}
