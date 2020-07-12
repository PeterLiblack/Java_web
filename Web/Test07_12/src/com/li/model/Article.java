package com.li.model;

import com.li.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    /*
    这么用 DateFormat 是错误的，因为是
    1. DateFormat 不是线程安全的
    2. 我们的 publish 实际上是运行在多线程环境下的

    static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void publish(int userId, String title, String content) {
        Date date = new Date();
        String publishedAt = format.format(date); // 2020-07-12 11:42:38
    }
    */

    public static void publish(int userId, String title, String content) throws SQLException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String publishedAt = format.format(date);

        try (Connection c = DBUtil.getConnection()) {
            String sql = "INSERT INTO articles (user_id, title, content, published_at) VALUES (?, ?, ?, ?)";
            try (PreparedStatement s = c.prepareStatement(sql)) {
                s.setInt(1, userId);
                s.setString(2, title);
                s.setString(3, content);
                s.setString(4, publishedAt);

                s.executeUpdate();
            }
        }
    }
}
