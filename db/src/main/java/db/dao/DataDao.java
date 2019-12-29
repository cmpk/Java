package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import common.Command;
import common.CommandException;
import db.Utility;
import db.oracle.SQLLoaderExitCodeWindows;

public final class DataDao {
    public static final String SQLLOADER_FILE_PREFIX = "sqlloader_";

    /**
     * DBから該当テーブルの全てのレコードを削除する.
     * @param con
     * @return
     * @throws SQLException
     */
    public int clear(final Connection con) throws SQLException {
        String sql = "delete from DATA";
        int deletedCnt = 0;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            deletedCnt += ps.executeUpdate();
        }

        return deletedCnt;
    }

    /**
     * DBにレコードを挿入する.
     *
     * @param con
     * @param dataList
     * @return
     * @throws SQLException
     */
    public int store(final Connection con, final List<Data> dataList) throws SQLException {
        String sql = "insert into DATA values(?, ?, ?)";
        int insertedCnt = 0;
        for (Data data : dataList) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(Data.COLUMN_NUMBER_VALUE, data.getNumberValue());
                ps.setString(Data.COLUMN_STRING_VALUE, data.getStringValue());
                ps.setDate(Data.COLUMN_DATE_VALUE, Utility.toSQLDate(data.getDateValue()));

                insertedCnt += ps.executeUpdate();
            }
        }

        return insertedCnt;
    }

    /**
     * DBから該当テーブルの全てのレコードを取得する.
     *
     * @param con
     * @return
     * @throws SQLException
     */
    public List<Data> load(final Connection con) throws SQLException {
        List<Data> dataList = new ArrayList<Data>();

        try (Statement stmt = con.createStatement()) {
            try (ResultSet rset = stmt.executeQuery("select * from DATA")) {
                while (rset.next()) {
                    Data data = new Data();
                    data.setNumberValue(rset);
                    data.setStringValue(rset);
                    data.setDateValue(rset);
                    dataList.add(data);
                }
            }
        }

        return dataList;
    }

    /**
     * SQL*Loader を使用して DB テーブルのレコードを入れ替える.
     *
     * @param userId DB接続に利用するユーザID.
     * @param password パスワード.
     * @param workingDir SQL*Loader のログファイル、不良ファイル、廃棄ファイルの出力先ディレクトリ.
     * @param controlFile 制御ファイルのパス.
     * @param dataFile データファイルのパス.
     * @param includeHeader データファイルにヘッダが含まれるか否か.
     * @return SQL*Loader から出力されたファイル名.<br>ファイル名は実行日時（yyyyMMddHHmmssSSS）と乱数から生成する.
     * @throws CommandException
     */
    public String loadCSV2DB(final String userId, final String password, final String workingDir, final String controlFile, final String dataFile, final boolean includeHeader) throws CommandException {
        String basename = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()) + "_" + (int)(Math.random() * 100);
        String badFilepath = SQLLOADER_FILE_PREFIX + basename + ".bad";
        String discardFilepath = SQLLOADER_FILE_PREFIX + basename + ".dsc";
        String logFilepath = SQLLOADER_FILE_PREFIX + basename + ".log";

        int skipLineCnt = (includeHeader ? 1 : 0);
        String[] command = {
            "sqlldr",
            "userid=" + userId + "/" + password,
            "control='" + controlFile + "'",
            "data='" + dataFile + "'",
            "bad='" + workingDir + "/" + badFilepath + "'",
            "discard='" + workingDir + "/" + discardFilepath + "'",
            "log='" + workingDir + "/" + logFilepath + "'",
            "skip=" + skipLineCnt};
        List<String> outputs = new ArrayList<String>();
        int exitCodeValue = Command.run("./", command, "SJIS", outputs);

        SQLLoaderExitCodeWindows exitCode = SQLLoaderExitCodeWindows.getExitCode(exitCodeValue);
        if (exitCode != SQLLoaderExitCodeWindows.SUCCESS) {
            throw new CommandException(
                    "コマンドの終了コードが " + exitCode.getExitCode() + " です。" + exitCode.getMessage() + "\r\n" +
                    "ログファイル：" + logFilepath + "\r\n" +
                    "コマンドからの出力：" + String.join("\r\n", outputs));
        }

        return logFilepath;
    }
}
