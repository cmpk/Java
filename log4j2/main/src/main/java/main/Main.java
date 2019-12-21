package main;

/**
 * Apache log4j 2 サンプルコード.
 */
public final class Main {

    public static void main(final String[] args) {
        System.out.println("Start");
        MyApplication app = new MyApplication();
        app.doBusinessLogic();
        System.out.println("End");
    }

    private Main() {
        // 何もしない
    }

}
