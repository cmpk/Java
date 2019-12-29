package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import common.CommandException;
import common.PropertyStorage;
import db.IDataSource;
import db.dao.Data;
import db.dao.DataDao;
import db.oracle.Oracle;

public final class Main {

    public static void main(final String[] args) {
        PropertyStorage prop;
        try {
            prop = new PropertyStorage("conf/database.properties", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        IDataSource dataSource = new Oracle();
        try (Connection con = dataSource.open(prop.getPropertyString("jdbc_class"), prop.getPropertyString("url"), prop.getPropertyString("userid"), prop.getPropertyString("password"))) {
            con.setAutoCommit(false); //自動コミットしない
            DataDao dataDao = new DataDao();

            if (!clearData(con, dataDao)) {
                return;
            }
            if (!storeData(con, dataDao)) {
                return;
            }
            if (!loadData(con, dataDao)) {
                return;
            }
            if (!storeData(prop.getPropertyString("userid"), prop.getPropertyString("password"), dataDao)) {
                return;
            }
            if (!loadData(con, dataDao)) {
                return;
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    private static boolean clearData(final Connection con, final DataDao dataDao) {
        try {
            int cnt = dataDao.clear(con);
            con.commit();

            System.out.println("全データを削除しました。件数：" + cnt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean storeData(final Connection con, final DataDao dataDao) {
        try {
            List<Data> dataList = new ArrayList<Data>();

            Data data = new Data();
            data.setNumberValue(1);
            data.setStringValue("(｀∀´)b");
            data.setDateValue(new SimpleDateFormat("yyyy/MM/dd").parse("2016/02/29"));
            dataList.add(data);

            int cnt = dataDao.store(con, dataList);
            con.commit();

            System.out.println("登録しました。件数：" + cnt);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean loadData(final Connection con, final DataDao dataDao) {
        try {
            List<Data> dataList = dataDao.load(con);
            dataList.forEach(data -> {
                System.out.println(data.getNumberValue() + ", " + data.getStringValue() + ", " + data.getDateValue());
            });
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean storeData(final String userId, final String password, final DataDao dataDao) {
        String logFilename = "";
        try {
            logFilename = dataDao.loadCSV2DB(userId, password, "work", "conf/DATA.ctl", "data/DATA.csv", true);

            System.out.println("登録しました。");
        } catch (CommandException e) {
            e.printStackTrace();
            return false;
        }

        try {
            Files.deleteIfExists(Paths.get("work/" + logFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private Main() {
    }
}
