package com.lizun;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TranslateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 读取用户输入的英文单词
        String english = req.getParameter("english");
        // 2. 根据我们的之前保存的字典数据，进行翻译
        String chinese = translate(english);
        // 3. 输出 HTML 内容
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.printf("<p>%s 的翻译结果是 %s</p>%n", english, chinese);
    }

    private String translate(String english) throws ServletException {
        try (Connection c = DBUtil.getConnection()) {
            String sql = "select chinese from dictionary where english = ?";
            try (PreparedStatement s = c.prepareStatement(sql)) {
                s.setString(1, english);
                try (ResultSet r = s.executeQuery()) {
                    if (r.next()) {
                        return r.getString("chinese");
                    } else {
                        return "不认识的单词";
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
