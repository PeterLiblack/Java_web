package boke;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author Li_ZW
 * @Date: 2020/06/13
 * @Time: 17:48
 * @Description
 */
public class ArticleListAction implements Action {
    @Override
    public void run() {
        try (Connection connection = DBUtil.getConnection()) {
            List<String[]> articleList = new ArrayList<>();

            String sql = "select id, author_id, title, published_at from articles order by published_at desc";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        String[] article = new String[4];
                        String id = rs.getString("id");
                        String authorId = rs.getString("author_id");
                        String title = rs.getString("title");
                        String publishedAt = rs.getString("published_at");
                        article[0] = id;
                        article[1] = authorId;
                        article[2] = title;
                        article[3] = publishedAt;

                        articleList.add(article);
                    }
                }
            }
            Set<String> authorIdSet = new HashSet<>();
            for (String[] article: articleList) {
                String authorId = article[1];
                authorIdSet.add(authorId);
            }

            StringBuilder querySql = new StringBuilder("select id, nickname from users where id in (");
            for (int i = 1; i < authorIdSet.size(); i++) {
                querySql.append("?,");
            }
            querySql.append("?)");
            System.out.println("DEBUG: " + querySql.toString());

            Map<String, String> userIdToNicknameMap = new HashMap<>();
            try (PreparedStatement statement = connection.prepareStatement(querySql.toString())) {
                int i = 1;
                for (String id : authorIdSet) {
                    statement.setString(i++, id);
                }

                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String nickname = rs.getString("nickname");
                        userIdToNicknameMap.put(id, nickname);
                    }
                }
            }

            System.out.printf("#ID  | 标题                  | 作者    | 发表时间%n");
            for (String[] article : articleList) {
                String id = article[0];
                String authorId = article[1];
                String authorNickname = userIdToNicknameMap.get(authorId);
                String title = article[2];
                String publishedAt = article[3];

                System.out.printf("%-4s | %-40s | %-10s | %s%n", id, title, authorNickname, publishedAt);
            }
        }catch (SQLException e) {
            System.out.println("错误：" + e.getMessage());
        }
    }
}
