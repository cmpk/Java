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

    private PropertyStorage properties;

    public GMailProperties(final String filepath, final String charsetName) throws FileNotFoundException, IOException {
        this.properties = new PropertyStorage(filepath, charsetName);
    }

    @Override
    public final String getUsername() {
        return this.properties.getPropertyString(USERNAME);
    }

    @Override
    public final String getPassword() {
        return this.properties.getPropertyString(PASSWORD);
    }

    @Override
    public final String getCharset() {
        return this.properties.getPropertyString(CHARSET);
    }

    @Override
    public final String getEncoding() {
        return this.properties.getPropertyString(ENCODING);
    }

    @Override
    public final String getHost() {
        return this.properties.getPropertyString(HOST);
    }

    @Override
    public final String getPort() {
        return this.properties.getPropertyString(PORT);
    }

    public final String getFrom() {
        return this.properties.getPropertyString(FROM);
    }

    public final String getReplyTo() {
        return this.properties.getPropertyString(REPLY_TO);
    }
}
