package common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyFTPClientCloseTest {
    @Test
    @DisplayName("未接続状態で切断処理を実行しても例外が発生ないこと。")
    public final void testNegative_NotOpend() {
        MyFTPClient client = new MyFTPClient();
        MyFTPClientException actual = null;
        try {
            client.close();
        } catch (MyFTPClientException e) {
            actual = e;
        }

        assertNull(actual);
    }

    @Test
    @DisplayName("正常系")
    public final void testPositive() {
        MyFTPClient client = new MyFTPClient();
        MyFTPClientException actual = null;
        try {
            client.open(TestConfig.FTP_SERVER, TestConfig.FTP_USER, TestConfig.FTP_PASSWORD);
        } catch (MyFTPClientException e) {
            e.printStackTrace();
        }
        try {
            client.close();
        } catch (MyFTPClientException e) {
            actual = e;
        }

        assertNull(actual);
    }
}
