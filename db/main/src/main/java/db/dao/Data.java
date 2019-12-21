package db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;

public class Data {

    @Getter
    private Long numberValue;

    @Getter
    private String stringValue;

    public void setNumberValue(ResultSet rset) throws SQLException {
        this.numberValue = rset.getLong("number_value");
    }

    public void setStringValue(ResultSet rset) throws SQLException {
        this.stringValue = rset.getString("string_value");
    }
}
