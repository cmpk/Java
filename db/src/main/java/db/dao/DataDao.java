package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Utility;

/**
 * DBテーブル"DATA"に対する操作を提供する.
 */
public final class DataDao {
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
}
