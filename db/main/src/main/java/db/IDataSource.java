package db;

import java.sql.Connection;

public interface IDataSource {
    boolean open();
    void close();
    Connection getConnection();
}
