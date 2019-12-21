package mypackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class MyFTPClient implements AutoCloseable {
    public static final int DEFAULT_PORT = 21;

    private FTPClient client = null;

    public boolean open(String hostname, String username, String password) throws SocketException, IOException {
        return open(hostname, username, password, DEFAULT_PORT);
    }

    /**
     * FTPサーバに接続する.
     *
     * @param hostname
     * @param username
     * @param password
     * @param port
     * @return 接続に成功した場合は true
     * @throws SocketException
     * @throws IOException
     */
    public boolean open(String hostname, String username, String password, int port) throws SocketException, IOException {
        boolean success = false;
        this.client = new FTPClient();

        this.client.connect(hostname, port);
        if (!FTPReply.isPositiveCompletion(this.client.getReplyCode())) {

            System.err.println("Failed to connect");
            this.client.disconnect();
            return false;
        }

        this.client.login(username, password);
        if (!FTPReply.isPositiveCompletion(this.client.getReplyCode())) {
            System.err.println("Failed to login");
            this.client.disconnect();
            return false;
        }

        return success;
    }

    @Override
    public void close() throws Exception {
        if (this.client != null) {
            this.client.completePendingCommand();
            this.client.disconnect();
            this.client = null;
        }
    }

    public void get(String sourceFilePath, String destFilepath) throws FileNotFoundException, IOException {
        if (this.client == null) {
            //TODO to deal error
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(destFilepath)) {
            this.client.retrieveFile(sourceFilePath, fos);
        }
    }

    public InputStream get(String sourceFilePath) throws IOException {
        if (this.client == null) {
            //TODO to deal error
            return null;
        }

        InputStream is = this.client.retrieveFileStream(sourceFilePath);

        return is;
    }
}