package common;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyFTPClientGetTest {
    @BeforeAll
    public static void beforeAll() {
        File[] files = new File(".\\work").listFiles();

        for (File file : files) {
            file.delete();
        }
    }

    @Test
    @DisplayName("GET先のディレクトリがない場合に、falseが返る")
    public final void testPositive_NotFoundDestDirDuringGettingAsFile() {
        MyFTPClient client = new MyFTPClient();
        try {
            client.open(TestConfig.FTP_SERVER, TestConfig.FTP_USER, TestConfig.FTP_PASSWORD);
        } catch (MyFTPClientException e) {
            fail(e);
        }

        MyFTPClientException actual = null;
        try {
            client.get("file.txt", ".\\invalid\\invalid.txt");
        } catch (MyFTPClientException e) {
            actual = e;
        }

        try {
            client.close();
        } catch (MyFTPClientException e) {
            e.printStackTrace();
        }

        assertNotNull(actual);
    }

    @Test
    @DisplayName("GET元のファイルがない場合に、falseが返る")
    public final void testPositive_NotFoundSourceFileDuringGettingAsFile() {
        String destFilepath = ".\\work\\" + getMethodName() + ".txt";
        MyFTPClient client = new MyFTPClient();
        try {
            client.open(TestConfig.FTP_SERVER, TestConfig.FTP_USER, TestConfig.FTP_PASSWORD);
        } catch (MyFTPClientException e) {
            fail(e);
        }

        boolean actual = true;
        try {
            actual = client.get("invalid", destFilepath);
        } catch (MyFTPClientException e) {
            e.printStackTrace();
        }

        try {
            client.close();
        } catch (MyFTPClientException e) {
            e.printStackTrace();
        }

        assertFalse(actual);
    }

    @Test
    @DisplayName("GET元のファイルがない場合に、falseが返る")
    public final void testPositive_NotFoundSourceFileDuringGettingAsInputStream() {
        InputStream actual = null;
        try (MyFTPClient client = new MyFTPClient()) {
            client.open(TestConfig.FTP_SERVER, TestConfig.FTP_USER, TestConfig.FTP_PASSWORD);
            try (InputStream is = client.get("invalid")) {
                actual = is;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MyFTPClientException ex) {
            fail(ex);
        }

        assertNull(actual);
    }

    @Test
    @DisplayName("未接続状態でGETを試みた場合に、Exceptionが発生する")
    public final void testPositive_WithoutOpenDuringGettingAsFile() {
        String destFilepath = ".\\work\\" + getMethodName() + ".txt";
        MyFTPClient client = new MyFTPClient();

        try {
            client.get("file.txt", destFilepath);
            fail("no exception");
        } catch (MyFTPClientException e) {
        }

        try {
            client.close();
        } catch (MyFTPClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("未接続状態でGETを試みた場合に、Exceptionが発生する")
    public final void testPositive_WithoutOpenDuringGettingAsInputStream() {
        MyFTPClient client = new MyFTPClient();

        try {
            client.get("file.txt");
            fail("no exception");
        } catch (MyFTPClientException e) {
        }

        try {
            client.close();
        } catch (MyFTPClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("ファイルとしてGETする")
    public final void testPositive_AsFile() {
        String destFilepath = ".\\work\\" + getMethodName() + ".txt";
        boolean actual = false;
        try (MyFTPClient client = new MyFTPClient()) {
            client.open(TestConfig.FTP_SERVER, TestConfig.FTP_USER, TestConfig.FTP_PASSWORD);
            try {
                actual = client.get("file.txt", destFilepath);
            } catch (MyFTPClientException e) {
                fail(e);
            }
        } catch (MyFTPClientException ex) {
            fail(ex);
        }

        assertTrue(actual);
        try {
            assertTrue(Arrays.equals(Files.readAllBytes(Paths.get(".\\src\\test\\resources\\file.txt")), Files.readAllBytes(Paths.get(destFilepath))));
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("InputStreamとしてGETする")
    public final void testPositive_AsInputStream() {
        List<String> actual = new ArrayList<String>();
        try (MyFTPClient client = new MyFTPClient()) {
            client.open(TestConfig.FTP_SERVER, TestConfig.FTP_USER, TestConfig.FTP_PASSWORD);
            try (InputStream is = client.get("file.txt")) {
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(is, "SJIS"))) {
                    String line = null;
                    while((line = reader.readLine()) != null){
                        actual.add(line);
                    }
                }
            } catch (IOException e) {
                fail(e);
            } catch (MyFTPClientException e) {
                fail(e);
            }
        } catch (MyFTPClientException ex) {
            fail(ex);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(".\\src\\test\\resources\\file.txt")), "SJIS"))) {
            String expected = null;
            int i = 0;
            while((expected = reader.readLine()) != null){
                assertEquals(expected, actual.get(i));
                i++;
            }
        } catch (IOException e) {
            fail(e);
        }
    }

    /**
     * 実行メソッド名を取得する.
     *
     * @return
     */
    private String getMethodName() {
        // スタックトレースから本メソッドを呼び出しているメソッド名を取得する
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

}
