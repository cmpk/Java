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
     * �ݒ�t�@�C���ɋL�ڂ��ꂽ���[�U���iusername�j���擾����
     */
    @Override
    public String getUsername() {
        return this.properties.getPropertyString(USERNAME);
    }

    /**
     * �ݒ�t�@�C���ɋL�ڂ��ꂽ�p�X���[�h�ipassword�j���擾����
     */
    @Override
    public String getPassword() {
        return this.properties.getPropertyString(PASSWORD);
    }

    /**
     * �ݒ�t�@�C���ɋL�ڂ��ꂽ�����R�[�h�icharset�j���擾����
     */
    @Override
    public String getCharset() {
        return this.properties.getPropertyString(CHARSET);
    }

    /**
     * �ݒ�t�@�C���ɋL�ڂ��ꂽ�G���R�[�h�����iencoding�j���擾����
     */
    @Override
    public String getEncoding() {
        return this.properties.getPropertyString(ENCODING);
    }

    /**
     * �ݒ�t�@�C���ɋL�ڂ��ꂽ���[���z�X�g���ihost�j���擾����
     */
    @Override
    public String getHost() {
        return this.properties.getPropertyString(HOST);
    }

    /**
     * �ݒ�t�@�C���ɋL�ڂ��ꂽ�|�[�g�ԍ��iport�j���擾����
     */
    @Override
    public String getPort() {
        return this.properties.getPropertyString(PORT);
    }

    /**
     * �ݒ�t�@�C���ɋL�ڂ��ꂽ���M�ҁifrom�j���擾����
     */
    public String getFrom() {
        return this.properties.getPropertyString(FROM);
    }

    /**
     * �ݒ�t�@�C���ɋL�ڂ��ꂽ�ԐM��ireply_to�j���擾����
     */
    public String getReplyTo() {
        return this.properties.getPropertyString(REPLY_TO);
    }
}
