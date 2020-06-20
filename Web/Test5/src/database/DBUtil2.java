package database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @Author Li_ZW
 * @Date: 2020/06/20
 * @Time: 20:44
 * @Description
 */

/*
懒汉模式的单例
 */
public class DBUtil2 {
    private static volatile DataSource dataSource = null;

    private static DataSource initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");

        return mysqlDataSource;
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            synchronized (DBUtil2.class) {
                if (dataSource == null) {
                    // volatile 可见性进一步限制了重排序的效果
                    dataSource = initDataSource();
                }
            }
        }

        return dataSource.getConnection();
    }
}
