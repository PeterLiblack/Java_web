package boke;

/**
 * @Author Li_ZW
 * @Date: 2020/06/13
 * @Time: 17:49
 * @Description
 */
public class User {
    int id;
    String username;
    String nickname;

    private static User currentUser = null;

    public static void login(User user) {
        currentUser = user;
        System.out.println("刚刚登陆的用户信息是：" + currentUser);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLogined() {
        return currentUser != null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
