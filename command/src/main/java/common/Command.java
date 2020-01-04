package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;

/**
 * お世話になったサイト: https://blog.y-yuki.net/entry/2018/10/13/213000
 *
 */
public final class Command {
    public static final int EXIT_CODE_TIMEOUT = -1;
    /**
     * コマンドを実行する. コマンド実行後はコマンドが終了するまで待機する.
     * すでに実行中のコマンドがある場合は、実行中のコマンドを強制終了してからコマンドを実行する.
     *
     * @param execDirPath
     * @param command
     * @param stdoutCharset
     * @param outputs
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public static int run(final String execDirPath, final String[] command, final String stdoutCharset, final List<String> outputs) throws CommandException {
        return Command.run(execDirPath, command, stdoutCharset, outputs, 0, null);
    }

    /**
     * コマンドを実行する. 指定されたタイムアウト時間を過ぎてもコマンドが終了しない場合は、終了コードとして-1を返す.
     * すでに実行中のコマンドがある場合は、実行中のコマンドを強制終了してからコマンドを実行する.
     *
     * @param execDirPath   実行ディレクトリ.
     * @param command       実行するコマンド.
     * @param stdoutCharset 標準出力の文字コード. これを指定しないと受け取った文字列が文字化けする.
     * @param outputs       標準出力に出力された内容を格納するインスタンス.
     * @param timeout       タイムアウト. 0を指定した場合は、コマンドが終了するまで待機する.
     * @param timeUnit      タイムアウトの単位
     * @return 終了コード. タイムアウトの場合は {@link Command#EXIT_CODE_TIMEOUT} を返すが、このコードが必ずタイムアウトを示すわけではない.
     * @throws CommandException コマンド実行準備、標準入出力の読み取り、コマンド実行に失敗した場合
     */
    public static int run(final String execDirPath, final String[] command, final String stdoutCharset, final List<String> outputs, final long timeout, final TimeUnit timeUnit) throws CommandException {
        System.out.println(String.join(" ", command)); //TODO 削除

        String[] cmd = ArrayUtils.addAll(new String[] {"cmd", "/c"}, command);
        ProcessBuilder builder = new ProcessBuilder(cmd);
        builder.directory(new File(execDirPath));
        builder.redirectErrorStream(true); // merge contents in stderr to stdout

        Process proc = null;
        try {
            proc = builder.start();
        } catch (IOException e) {
            close(proc);
            throw new CommandException("プロセスの起動に失敗しました。コマンドが見つからない、コマンドファイルや作業ディレクトリの状態を確認してください。", e);
        }

        // read stdout
        final Process p = proc; // final付の変数を使わないとThread()内で怒られる
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

        int exitCode = EXIT_CODE_TIMEOUT;
        try {
            if (timeout == 0) {
                exitCode = proc.waitFor(); // コマンドが終了するまで待機する
            } else {
                if (proc.waitFor(timeout, timeUnit)) {
                    exitCode = proc.exitValue();
                }
            }
        } catch (InterruptedException e) {
            close(proc);
            throw new CommandException("コマンド実行完了の待機中にエラーが発生しました。割込みが発生した可能性があります。", e);
        }

        return exitCode;
    }

    private static void close(final Process proc) {
        if (proc != null) {
            if (proc.isAlive()) {
                proc.destroy();
            }
        }
    }

    private Command() {
    }
}
