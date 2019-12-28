package main;

import java.io.File;

/**
 * freemarker �T���v���R�[�h.
 */
public final class Main {

    public static void main(final String[] args) {
        // �f�o�b�O�̂��ߊ������o�͂���
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
