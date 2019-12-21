package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Oracle implements IDataSource {
    public static final String JDBC_CLASS = "oracle.jdbc.driver.OracleDriver";
    public static final String CONNECTION_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    public static final String CONNECTION_ID = "scott";
    public static final String CONNECTION_PASSWORD = "tiger";

    private Connection conn = null;

    @Override
    public boolean open() {
        try {
            Class.forName(JDBC_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("クラスをロードできませんでした。クラスパスにライブラリの配置先が指定されていない可能性があります。ロード対象：" + JDBC_CLASS);
            e.printStackTrace();
            return false;
        }

        try {
            this.conn = DriverManager.getConnection(CONNECTION_URL, CONNECTION_ID, CONNECTION_PASSWORD);
        } catch (SQLException e) {
            System.err.println("データベースに接続できませんでした。接続先、接続ID、パスワードが間違っている可能性があります。");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                System.err.println("データベースとの切断に失敗しました。本アプリケーションを終了しても接続が残る可能性があります。");
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        return this.conn;
    }
}
