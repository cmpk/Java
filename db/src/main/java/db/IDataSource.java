package db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBとの通信を提供する.
 */
public interface IDataSource {
    /**
     * DBとの接続を確立する.
     * @param jdbcClassPath 利用するJDBCクラス
     * @param url DBの接続先URL
     * @param userId DB接続に利用するユーザID
     * @param password 接続パスワード
     * @return
     * @throws ClassNotFoundException クラスロードに失敗した場合. 対象クラスを含むライブラリが見つからない場合などが考えられる.
     * @throws SQLException データベース接続に失敗した場合. 原因として、接続先やログイン情報の不一致などが考えられる.
     */
    Connection open(String jdbcClassPath, String url, String userId, String password) throws ClassNotFoundException, SQLException;

    /**
     * DBとの接続を切断する.
     *
     * @throws SQLException 切断に失敗した場合. 本エラーが発生した場合は、接続情報が残ったままとなる可能性がある.
     */
    void close() throws SQLException;
}
