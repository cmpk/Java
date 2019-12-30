package db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import lombok.Getter;

/**
 * DBテーブル"DATA"を表現するクラス.
 */
public final class Data {
    public static final int COLUMN_NUMBER_VALUE = 1;
    public static final int COLUMN_STRING_VALUE = 2;
    public static final int COLUMN_DATE_VALUE = 3;

    @Getter
    private int numberValue;

    @Getter
    private String stringValue;

    @Getter
    private Date dateValue;

    public Data setNumberValue(final int value) {
        this.numberValue = value;
        return this;
    }

    public Data setNumberValue(final ResultSet rset) throws SQLException {
        this.numberValue = rset.getInt("number_value");
        return this;
    }

    public Data setStringValue(final String value) {
        this.stringValue = value;
        return this;
    }

    public Data setStringValue(final ResultSet rset) throws SQLException {
        this.stringValue = rset.getString("string_value");
        return this;
    }

    public Data setDateValue(final Date value) {
        this.dateValue = value;
        return this;
    }

    public Data setDateValue(final ResultSet rset) throws SQLException {
        this.dateValue = rset.getDate("date_value");
        return this;
    }
}
