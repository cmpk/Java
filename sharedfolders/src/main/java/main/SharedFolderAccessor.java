package main;

import java.util.ArrayList;
import java.util.List;

public class SharedFolderAccessor {
    private final String stdoutCharset;

    /**
     *
     * @param stdoutCharset {@see Command#Command(String[], String)}
     */
    public SharedFolderAccessor(final String stdoutCharset) {
        this.stdoutCharset = stdoutCharset;
    }

    /**
     * 利用可能なドライブ文字列を検索する.
     *
     * Zから降順に検索し、最初に見つかった文字列を返す.
     *
     * @return 利用可能なドライブ文字列.
     */
    public String searchDriveLetter() {
        char c = 'Z';
        for (int i = 0; i < 26; i++) {
            String[] commandList = {"cmd", "/c", "if not exist " + c + ":\\ echo " + c};
            List<String> outList = new ArrayList<String>();
            try {
                Command.run("./", commandList, this.stdoutCharset, outList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (outList.size() == 1 && outList.get(0).length() == 1) {
                return outList.get(0);
            }
            c--;
        }
        return "";
    }

    public boolean assignNetworkDrive(final String driveLetter, final String sharedDirPath, final String userId, final String password) {
        String[] commandList = {"cmd", "/c", "net use", driveLetter + ":", sharedDirPath, password, "/USER:" + userId};
        List<String> outList = new ArrayList<String>();
        int exitCode = 1;
        try {
            exitCode = Command.run("./", commandList, this.stdoutCharset, outList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (exitCode != 0) {
            outList.forEach(s -> {System.err.println(s);});
            return false;
        }
        outList.forEach(s -> {System.out.println(s);});

        return true;
    }

    public boolean deleteNetworkDrive(final String driveLetter) {
        String[] commandList = {"cmd", "/c", "net use", driveLetter + ":", "/delete"};
        List<String> outList = new ArrayList<String>();
        int exitCode = 1;
        try {
            exitCode = Command.run("./", commandList, this.stdoutCharset, outList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (exitCode != 0) {
            outList.forEach(s -> {System.err.println(s);});
            return false;
        }
        outList.forEach(s -> {System.out.println(s);});

        return true;
    }
}
