package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import common.conf.Log4j2Attributes;

/**
 * Apache log4j 2 サンプルコード.
 */
public final class Main {

    public static void main(final String[] args) {
        System.out.println("Start");

        // log4j 2 設定を確認してみる
        try {
            Log4j2Attributes logConf = Log4j2Attributes.newInstance("conf/log4j2.xml");
            logConf.getFileList().forEach(file -> {
                System.out.println(file.getName() + ": " + file.getFileName());
            });
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        // ログ出力
        MyApplication app = new MyApplication();
        app.doBusinessLogic();


        System.out.println("End");
    }

    private Main() {
        // 何もしない
    }
}
