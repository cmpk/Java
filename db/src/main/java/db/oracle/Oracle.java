package db.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.IDataSource;

public final class Oracle implements IDataSource {
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
}
