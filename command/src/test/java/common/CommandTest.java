package common;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    @DisplayName("正常系")
    public final void testPositive() {
        String execDirPath = ".\\";
        String[] command = new String[] {"dir", "/B", "/N", "src"}; //ファイル名のみをアルファベット順で表示
        String stdoutCharset = "SJIS";
        List<String> outputs = new ArrayList<String> ();
        long timeout = 10L;
        TimeUnit timeUnit = TimeUnit.SECONDS;

        int actualExitCode = -1;
        try {
            actualExitCode = Command.run(execDirPath, command, stdoutCharset, outputs, timeout, timeUnit);
        } catch (InterruptedException | IOException e) {
            fail(e);
        }
        assertEquals(0, actualExitCode);

        assertEquals("main", outputs.get(0));
        assertEquals("test", outputs.get(1));
    }

    @Test
    @DisplayName("コマンド失敗")
    public final void testCommandFail() {
        String execDirPath = ".\\";
        String[] command = new String[] {"dir", "/B", "hoge"};
        String stdoutCharset = "SJIS";
        List<String> outputs = new ArrayList<String> ();
        long timeout = 10L;
        TimeUnit timeUnit = TimeUnit.SECONDS;

        int actualExitCode = -1;
        try {
            actualExitCode = Command.run(execDirPath, command, stdoutCharset, outputs, timeout, timeUnit);
        } catch (InterruptedException | IOException e) {
            fail(e);
        }
        assertTrue(0 != actualExitCode && -1 != actualExitCode);

        assertTrue(outputs.size() > 0);
    }

    @Test
    @DisplayName("タイムアウト")
    public final void testTimeout() {
        String execDirPath = ".\\";
        String[] command = new String[] {"ping", "192.168.1.1"}; //ファイル名のみをアルファベット順で表示
        String stdoutCharset = "SJIS";
        List<String> outputs = new ArrayList<String> ();
        long timeout = 1L;
        TimeUnit timeUnit = TimeUnit.SECONDS;

        int actualExitCode = -1;
        try {
            actualExitCode = Command.run(execDirPath, command, stdoutCharset, outputs, timeout, timeUnit);
        } catch (InterruptedException | IOException e) {
            fail(e);
        }
        assertEquals(Command.EXIT_CODE_TIMEOUT, actualExitCode);
    }
}
