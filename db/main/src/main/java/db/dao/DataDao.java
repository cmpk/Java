package db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataDao {

    public List<Data> getData(Connection con) throws SQLException {
        List<Data> dataList = new ArrayList<Data>();

        try(Statement stmt = con.createStatement()) {
            try(ResultSet rset = stmt.executeQuery("select * from DATA")) {
                while(rset.next()) {
                    Data data = new Data();
                    data.setNumberValue(rset);
                    data.setStringValue(rset);
                    dataList.add(data);
                }
            }
        }

        return dataList;
    }

}
