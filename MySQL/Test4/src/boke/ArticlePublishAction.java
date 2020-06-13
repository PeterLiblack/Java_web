package boke;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @Author Li_ZW
 * @Date: 2020/06/13
 * @Time: 17:49
 * @Description
 */
public class ArticlePublishAction implements Action {
    @Override
    public void run() {
        if (!User.isLogined()) {
            System.out.println("** 需要先登录，才能操作该功能！！");
            return;
        }

        System.out.println("发表文章中 ...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入文章标题> ");
        String title = scanner.nextLine();
        System.out.println("请输入文章正文> ");
        String content = scanner.nextLine();
        int authorId = User.getCurrentUser().id;
        Date publishedAt = new Date();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishedAtStr = format.format(publishedAt);

        try (Connection c = DBUtil.getConnection()) {
            String sql = "insert into articles (author_id, title, published_at, content) values (?, ?, ?, ?)";
            try (PreparedStatement s = c.prepareStatement(sql)) {
                s.setInt(1, authorId);
                s.setString(2, title);
                s.setString(3, publishedAtStr); // "2020-06-08 20:10:38"
                s.setString(4, content);

                s.executeUpdate();

                System.out.println("《" + title + "》 文章发表成功！");
            }
        } catch (SQLException e) {
            System.out.println("错误：" + e.getMessage());
        }
    }
}
