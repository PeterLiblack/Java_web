package boke;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.sql.*;
import java.util.Scanner;

/**
 * @Author Li_ZW
 * @Date: 2020/06/13
 * @Time: 17:50
 * @Description
 */
public class UserRegisterAction implements Action{
    @Override
    public void run() {
        System.out.println("开始用户注册...");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户姓名> ");
        String username = scanner.nextLine();
        System.out.print("请输入用户昵称> ");
        String nickname = scanner.nextLine();
        System.out.print("请输入用户密码> ");
        String password = scanner.nextLine();

        String sql = "insert into users (username,nickname,password) values (?,?,?)";
        try (Connection connection = DBUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement. RETURN_GENERATED_KEYS)){
                statement.setString(1,username);
                statement.setString(2,nickname);
                statement.setString(3,password);

                statement.executeUpdate();

                int id;
                try (ResultSet r = statement.getGeneratedKeys()){
                    r.next();
                    id = r.getInt(1);
                }
                System.out.println("注册成功，欢迎您 " + nickname);

                User user = new User();
                user.id = id;
                user.nickname = nickname;
                user.username = username;
                User.login(user);
            }

        }catch (SQLException e) {
            System.out.println("错误： " + e.getMessage());
        }
    }
}
