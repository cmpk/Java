package db.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import common.Command;
import common.CommandException;
import db.IDataSource;

/**
 * Oracle Database への接続と Oracle が用意する機能の一部を提供する.
 *
 */
public final class Oracle implements IDataSource {
    public static final String SQLLOADER_FILE_PREFIX = "sqlloader_";

    private Connection conn = null;

    @Override
    public Connection open(final String jdbcClassPath, final String url, final String userId, final String password) throws ClassNotFoundException, SQLException {
        // JDBCロード
        Class.forName(jdbcClassPath);

        // DB接続の確立
        this.conn = DriverManager.getConnection(url, userId, password);

        return this.conn;
    }

    @Override
    public void close() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }

    /**
     * SQL*Loader を使用して DB テーブルのレコードを入れ替える.
     *
     * @param userId DB接続に利用するユーザID.
     * @param password パスワード.
     * @param logDir SQL*Loader のログファイル、不良ファイル、廃棄ファイルの出力先ディレクトリ.
     * @param controlFile 制御ファイルのパス.
     * @param dataFile データファイルのパス.
     * @param includeHeader データファイルにヘッダが含まれるか否か.
     * @return SQL*Loader から出力されたファイル名.<br>ファイル名は実行日時（yyyyMMddHHmmssSSS）と乱数から生成する.
     * @throws CommandException SQL*Loader コマンド実行時にエラーが発生した場合
     * @trhwos SQLLoaderException SQL*Loader の終了コードが 0 でない場合
     */
    public String loadCSV2DB(final String userId, final String password, final String logDir, final String controlFile, final String dataFile, final boolean includeHeader) throws CommandException, SQLLoaderException {
        String basename = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()) + "_" + (int) (Math.random() * 100);
        String badFilepath = logDir + "/" + SQLLOADER_FILE_PREFIX + basename + ".bad";
        String discardFilepath = logDir + "/" + SQLLOADER_FILE_PREFIX + basename + ".dsc";
        String logFilepath = logDir + "/" + SQLLOADER_FILE_PREFIX + basename + ".log";

        int skipLineCnt = (includeHeader ? 1 : 0);
        String[] command = {
            "sqlldr",
            "userid=" + userId + "/" + password,
            "control='" + controlFile + "'",
            "data='" + dataFile + "'",
            "bad='" + badFilepath + "'",
            "discard='" + discardFilepath + "'",
            "log='" + logFilepath + "'",
            "skip=" + skipLineCnt};
        List<String> outputs = new ArrayList<String>();
        int exitCodeValue = Command.run("./", command, "SJIS", outputs);

        SQLLoaderExitCode exitCode = SQLLoaderExitCode.getExitCode(exitCodeValue);
        if (exitCode != SQLLoaderExitCode.SUCCESS) {
            String output = String.join("\r\n", outputs);
            throw new SQLLoaderException(
                    "コマンドの終了コードが " + exitCodeValue + " です。" + exitCode.getMessage() + "\r\n" +
                    "ログファイル：" + logFilepath + "\r\n" +
                    "コマンドからの出力：" + output,
                    logFilepath,
                    output);
        }

        return logFilepath;
    }
}
