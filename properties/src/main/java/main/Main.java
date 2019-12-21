package main;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            PropertyStorage p = new PropertyStorage("sample.properties", "UTF-8");
            System.out.println(p.getPropertyString("sample_string"));
            System.out.println(p.getPropertyInt("sample_int"));
        } catch (FileNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

}
