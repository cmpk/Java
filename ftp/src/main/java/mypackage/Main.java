package mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;

/**
 * 参考: https://web.plus-idea.net/2011/06/javaftp/
 *
 */
public class Main {
    public static void main(String[] args) {
        try (MyFTPClient client = new MyFTPClient()) {
            client.open("localhost", "user", "password");
            client.get("test.txt", "C:\\app\\test.log");
            try (InputStream is = client.get("test.txt")) {
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    String line = null;
                    while((line = reader.readLine()) != null){
                        System.out.println(line);
                    }
                }
            }
        } catch (SocketException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

}
