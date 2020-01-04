package common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class MyFTPClient implements AutoCloseable {
    public static final int DEFAULT_PORT = 21;

    private FTPClient client = null;

    public void open(String hostname, String username, String password) throws MyFTPClientException {
        open(hostname, username, password, DEFAULT_PORT);
    }

    /**
     * FTPサーバに接続する.
     *
     * @param hostname
     * @param username
     * @param password
     * @param port
     * @throws MyFTPClientException
     */
    public void open(String hostname, String username, String password, int port) throws MyFTPClientException {
        this.client = new FTPClient();

        try {
            this.client.connect(hostname, port);
        } catch (IOException e) {
            this.client = null;
            throw new MyFTPClientException("ソケット作成に失敗しました。クライアントの状態、接続先を確認してください。", e);
        }

        if (!FTPReply.isPositiveCompletion(this.client.getReplyCode())) {
            int code = this.client.getReplyCode();
            this.client = null;
            throw new MyFTPClientException("FTPサーバへの接続の応答コードが " + code + " です。接続先を確認してください。");
        }

        try {
            this.client.login(username, password);
        } catch (IOException e) {
            this.client = null;
            throw new MyFTPClientException("FTPサーバへのログイン中にエラーが発生しました。接続先、ユーザ名、パスワード、通信状態を確認してください。", e);
        }

        if (!FTPReply.isPositiveCompletion(this.client.getReplyCode())) {
            int code = this.client.getReplyCode();
            this.client = null;
            throw new MyFTPClientException("FTPサーバへのログインの応答コードが " + code + " です。接続先、ユーザ名、パスワード、通信状態を確認してください。");
        }
    }

    @Override
    public void close() throws MyFTPClientException {
        if (this.client == null) {
            return;
        }

        try {
            this.client.disconnect();
        } catch (IOException e) {
            throw new MyFTPClientException("FTPサーバとの切断処理に失敗しました。通信状態を確認してください。", e);
        }

        this.client = null;
    }

    /**
     * FTPサーバ上のファイルをローカルマシンにコピーする.
     *
     * @param sourceFilePath FTPサーバ上のコピー元ファイル
     * @param destFilepath ローカルマシン上のコピー先ファイル
     * @throws MyFTPClientException
     */
    public void get(String sourceFilePath, String destFilepath) throws MyFTPClientException {
        if (this.client == null) {
            throw new MyFTPClientException("FTPサーバに接続されていません。FTPサーバに接続後、GET処理を実行してください。");
        }

        try (FileOutputStream fos = new FileOutputStream(destFilepath)) {
            this.client.retrieveFile(sourceFilePath, fos);
        } catch (FileNotFoundException e) {
            throw new MyFTPClientException("ローカルマシン上のコピー先ファイル生成に失敗しました。コピー先の状態を確認してください。", e);
        } catch (IOException e) {
            throw new MyFTPClientException("FTPサーバ上のファイルをローカルマシンにコピーできませんでした。コピー元およびコピー先の状態を確認してください。", e);
        }
    }

    /**
     * ftpサーバ上のファイルの内容を{@link InputStream}として読み込む.
     *
     * @param sourceFilePath FTPサーバ上のファイル
     * @return {@link InputStream}
     * @throws MyFTPClientException
     */
    public InputStream get(String sourceFilePath) throws MyFTPClientException {
        if (this.client == null) {
            throw new MyFTPClientException("FTPサーバに接続されていません。FTPサーバに接続後、GET処理を実行してください。");
        }

        InputStream is = null;
        try {
            is = this.client.retrieveFileStream(sourceFilePath);
        } catch (IOException e) {
            throw new MyFTPClientException("FTPサーバ上のファイルの読み込み中にエラーが発生しました。ファイルおよびローカルマシンの状態を確認してください。", e);
        }

        return is;
    }
}