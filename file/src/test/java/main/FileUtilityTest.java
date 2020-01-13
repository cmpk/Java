package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class FileUtilityTest {
    public static final String WORKING_DIR = "work";
    @BeforeAll
    public static void beforeAll() throws IOException {
        File dir = new File(WORKING_DIR);
        FileUtils.deleteDirectory(dir);
        dir.mkdir();
    }

    @Test
    @DisplayName("リネーム対象ファイルがnull")
    public final void testNegative_renameFileWithDateExpression_nullSourceFilepath(TestInfo testInfo) {
        try {
            FileUtility.renameFileWithDateExpression(null, WORKING_DIR);
            fail(testInfo.getDisplayName() + " が正常終了した");
        } catch (FileNotFoundException e) {
            System.out.println("テスト「" + testInfo.getDisplayName() + "」メッセージ：" +  e.getMessage());
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("リネーム対象ファイルが存在しない")
    public final void testNegative_renameFileWithDateExpression_notExistSourceFilepath(TestInfo testInfo) {
        try {
            FileUtility.renameFileWithDateExpression("invalid.txt", WORKING_DIR);
            fail(testInfo.getDisplayName() + " が正常終了した");
        } catch (FileNotFoundException e) {
            System.out.println("テスト「" + testInfo.getDisplayName() + "」メッセージ：" +  e.getMessage());
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("ファイル配置先ディレクトリがnull")
    public final void testNegative_renameFileWithDateExpression_nullDestDirpath(TestInfo testInfo) throws IOException {
        String sourceFilepath = WORKING_DIR + "/test.txt";
        FileUtils.touch(new File(sourceFilepath));

        try {
            FileUtility.renameFileWithDateExpression(sourceFilepath, null);
            fail(testInfo.getDisplayName() + " が正常終了した");
        } catch (FileNotFoundException e) {
            System.out.println("テスト「" + testInfo.getDisplayName() + "」メッセージ：" +  e.getMessage());
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("ファイル配置先ディレクトリの階層が深い")
    public final void testPositive_renameFileWithDateExpression_deepDestDirpath(TestInfo testInfo) throws IOException {
        String sourceFilepath = WORKING_DIR + "/test.txt";
        FileUtils.touch(new File(sourceFilepath));
        String destDirpath = WORKING_DIR + "/dir1/dir2/dir3";

        try {
            FileUtility.renameFileWithDateExpression(sourceFilepath, destDirpath);
        } catch (FileNotFoundException e) {
            fail(e);
        } catch (IOException e) {
            fail(e);
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(FileUtility.DATE_FORMAT);
        String expected = destDirpath + "/test" + sdf.format(cal.getTime()) + ".txt";
        assertTrue(new File(expected).exists());
    }

    @Test
    @DisplayName("任意の日付フォーマット")
    public final void testPositive_renameFileWithDateExpression_withSpecifiedDateFormat(TestInfo testInfo) throws IOException {
        String sourceFilepath = WORKING_DIR + "/test.txt";
        FileUtils.touch(new File(sourceFilepath));
        String dateFormat = "-yyyy";

        try {
            FileUtility.renameFileWithDateExpression(sourceFilepath, WORKING_DIR, dateFormat);
        } catch (FileNotFoundException e) {
            fail(e);
        } catch (IOException e) {
            fail(e);
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String expected = WORKING_DIR + "/test" + sdf.format(cal.getTime()) + ".txt";
        assertTrue(new File(expected).exists());
    }
}
