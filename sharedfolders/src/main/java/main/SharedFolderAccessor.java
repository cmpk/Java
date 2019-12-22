package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Windows 共有フォルダにアクセスするための操作を提供する.
 */
public class SharedFolderAccessor {
    private final String stdoutCharset;

    /**
     * コンストラクタ.
     * @param stdoutCharset {@see Command#Command(String[], String)}
     */
    public SharedFolderAccessor(final String stdoutCharset) {
        this.stdoutCharset = stdoutCharset;
    }

    /**
     * 利用可能なドライブ文字列を検索する.
     * Zから降順に検索し、最初に見つかった文字列を返す.
     *
     * @param outputs 標準出力に出力された内容を格納するインスタンス.
     * @return 利用可能なドライブ文字列.
     */
    public String searchDriveLetter(List<String> outputs) {
        char c = 'Z';
        for (int i = 0; i < 26; i++) {
            String[] commandList = {"cmd", "/c", "if not exist " + c + ":\\ echo " + c};
            outputs = (outputs == null) ? new ArrayList<String>() : outputs;
            try {
                Command.run("./", commandList, this.stdoutCharset, outputs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (outputs.size() == 1 && outputs.get(0).length() == 1) {
                return outputs.get(0);
            }
            c--;
        }
        return "";
    }

    /**
     * 共有フォルダをネットワークドライブに割り当てる.
     * これにより、共有フォルダ上のファイルを操作可能になる.
     * @param driveLetter 割り当てるドライブ文字列
     * @param sharedDirPath 共有フォルダのパス
     * @param userId 共有フォルダを利用する際のユーザID
     * @param password パスワード
     * @param outputs 標準出力に出力された内容を格納するインスタンス.
     * @return 成功した場合は true
     * @throws IOException {@see Command#run(String, String[], String, List)}
     * @throws InterruptedException  {@see Command#run(String, String[], String, List)}
     */
    public boolean assignNetworkDrive(final String driveLetter, final String sharedDirPath, final String userId, final String password, List<String> outputs) throws InterruptedException, IOException {
        String[] commandList = {"cmd", "/c", "net use", driveLetter + ":", sharedDirPath, password, "/USER:" + userId};
        outputs = (outputs == null) ? new ArrayList<String>() : outputs;
        int exitCode = Command.run("./", commandList, this.stdoutCharset, outputs);

        return (exitCode == 0);
    }

    /**
     * ネットワークドライブを切断する.
     * @param driveLetter 切断対象のドライブ文字列
     * @param outputs 標準出力に出力された内容を格納するインスタンス.
     * @return 成功した場合は true
     * @throws IOException {@see Command#run(String, String[], String, List)}
     * @throws InterruptedException {@see Command#run(String, String[], String, List)}
     */
    public boolean deleteNetworkDrive(final String driveLetter, List<String> outputs) throws InterruptedException, IOException {
        String[] commandList = {"cmd", "/c", "net use", driveLetter + ":", "/delete"};
        outputs = (outputs == null) ? new ArrayList<String>() : outputs;
        int exitCode = Command.run("./", commandList, this.stdoutCharset, outputs);

        return (exitCode == 0);
    }
}
