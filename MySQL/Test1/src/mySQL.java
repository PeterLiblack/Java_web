import java.sql.*;

/**
 * @Author Li_ZW
 * @Date: 2020/06/06
 * @Time: 17:00
 * @Description
 */
public class mySQL {
    public static void main(String[] args) throws SQLException {

        String defaultDatabaseName = "li_0603";
        String password = "hello";
        String user = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/" + defaultDatabaseName + "?useSSL=false&characterEncoding=utf8";

        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement = connection.createStatement();
        String sql = "select *from student_0604";
        ResultSet resultSet = statement.executeQuery(sql);

        int count = 0;
        System.out.println("+----+------+-----------+-----+");
        System.out.println("| id | sn   | name      | sex |");
        System.out.println("+----+------+-----------+-----+");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String sn = resultSet.getString(2);
            String name = resultSet.getString(3);
            String sex = resultSet.getString(4);

            System.out.format("| %-2s | %-4s | %-7s | %-3s |%n",id,sn,name,sex);
            count++;
        }
        System.out.println("+----+------+-----------+-----+");
        System.out.format(" %d rows in set",count);


    }
}
