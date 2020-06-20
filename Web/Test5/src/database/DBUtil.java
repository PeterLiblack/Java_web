package database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @Author Li_ZW
 * @Date: 2020/06/20
 * @Time: 20:43
 * @Description
 */

public class DBUtil {
    // 这个应用期间，只需要一个 DataSource
    // DataSource 就是单例的应用

    // 饿汉模式的单例
    private static final DataSource dataSource; // dataSource 指向唯一的一个对象
    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");

        dataSource = mysqlDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

