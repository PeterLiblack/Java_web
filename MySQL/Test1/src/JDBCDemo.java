import java.sql.*;

/**
 * @Author Li_ZW
 * @Date: 2020/06/06
 * @Time: 16:48
 * @Description
 */
public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class<?> cls = Class.forName("com.mysql.jdbc.Driver");
        System.out.println(cls.getSimpleName());

        String defaultDatabaseName = "li_0603";
        String password = "hello";

        String user = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/" + defaultDatabaseName + "?useSSL=false&characterEncoding=utf8";

        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);

        Statement statement = connection.createStatement();
        String sql = "select *from student_0604";
        ResultSet resultSet = statement.executeQuery(sql);

    }
}
