package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import common.MyFTPClient;
import common.MyFTPClientException;

public final class Main {
    public static final String REMOTE_FILEPATH = "file.txt";
    public static final String LOCAL_FILEPATH = ".\\work\\out.txt";

    public static void main(final String[] args) {
        try (MyFTPClient client = new MyFTPClient()) {
            client.open("localhost", "user", "password");
            client.get(REMOTE_FILEPATH, LOCAL_FILEPATH);
            try (InputStream is = client.get(REMOTE_FILEPATH)) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "SJIS"))) {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MyFTPClientException e) {
            e.printStackTrace();
        }
    }

    private Main() {
    }

}
