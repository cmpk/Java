package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * お世話になったサイト: https://blog.y-yuki.net/entry/2018/10/13/213000
 *
 */
public class Command {
    public static final long DEFAULT_TIMEOUT_SECOND = 10L;

    /**
     * コマンドを実行する.
     *
     * すでに実行中のコマンドがある場合は、実行中のコマンドを強制終了してからコマンドを実行する.
     *
     * @param execDirPath 実行ディレクトリ.
     * @param command 実行するコマンド.
     * @param stdoutCharset 標準出力の文字コード. これを指定しないと受け取った文字列が文字化けする.
     * @param outputs 標準出力に出力された内容を格納するインスタンス.
     * @return 終了コード.
     * @throws IOException @see {@link ProcessBuilder#start()}, @see {@link InputStream#read()}
     * @throws InterruptedException @see {@link Process#waitFor(long, TimeUnit)}
     */
    public static int run(final String execDirPath, final String[] command, final String stdoutCharset, List<String> outputs) throws InterruptedException, IOException {
        System.out.println(String.join(" ", command));

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new File(execDirPath));
        builder.redirectErrorStream(true); // merge contents in stderr to stdout

        Process proc = null;
        try {
            proc = builder.start();
        } catch (IOException e) {
            close(proc);
            throw e;
        }

        // read stdout
        final Process p = proc; //TODO final付の変数を使わないとThread()内で怒られる
        new Thread(() -> {
            try (InputStream is = p.getInputStream()) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(is, stdoutCharset))) {
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break; // 全ての行を読み切ったら抜ける
                        } else {
                            outputs.add(line);
                        }
                    }
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }).start();

        boolean end = false;
        try {
            end = proc.waitFor(DEFAULT_TIMEOUT_SECOND, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            close(proc);
            throw e;
        }
        int exitCode = 1;
        if (end) {
            exitCode = proc.exitValue();
        }

        return exitCode;
    }

    private static void close(Process proc) {
        if (proc != null) {
            if (proc.isAlive()) {
                proc.destroy();
            }
        }
    }
}
