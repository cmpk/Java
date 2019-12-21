package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.IDataSource;
import db.Oracle;
import db.dao.Data;
import db.dao.DataDao;

public class Main {

    public static void main(String[] args) {
        IDataSource dataSource = new Oracle();

        if (!dataSource.open()) {
            return;
        }

        Connection con = dataSource.getConnection();
        DataDao dataDao = new DataDao();
        try {
            List<Data> dataList = dataDao.getData(con);
            dataList.forEach(data -> {
                System.out.println(data.getNumberValue() + "," + data.getStringValue());
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dataSource.close();
    }

}
