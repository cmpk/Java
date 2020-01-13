package main;

import java.io.IOException;

public final class Main {
    public static final String ORIGINAL_FILEPATH = "src/main/resources/test.txt";
    public static final String DIR = "src/main/resources";

    public static void main(final String[] args) {
        try {
            FileUtility.renameFileWithDateExpression(ORIGINAL_FILEPATH, DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Main() {
    }
}
