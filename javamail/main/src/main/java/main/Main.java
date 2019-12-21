package main;

import java.io.File;

/**
 * freemarker サンプルコード.
 */
public final class Main {

    public static void main(final String[] args) {
        // デバッグのため環境情報を出力する
        System.out.println(new File(".").getAbsolutePath());
        System.out.println(new File(".").getAbsoluteFile().getParent());
        System.out.println("Start");

        MyApplication app = new MyApplication();
        app.doBusinessLogic();

        System.out.println("End");
    }

    private Main() {
        // do nothing
    }

}
