package com.li;

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
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Li_ZW
 * @Date: 2020/07/05
 * @Time: 18:50
 * @Description
 */
public class TranslateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String english = req.getParameter("english");
        // TODO: 没有任何的错误处理

        String chinese;
        // select chinese from dictionary where english = ?
        try (Connection c = DBUtil.getConnection()) {
            String sql = "select chinese from dictionary where english = ?";
            try (PreparedStatement s = c.prepareStatement(sql)) {
                s.setString(1, english);
                try (ResultSet r = s.executeQuery()) {
                    // 因为创建表的时候，english 字段设定是 unique
                    // 所以，上述 SQL，要不返回 1 行数据，要不返回 0 行数据
                    if (r.next()) {
                        // 返回了一行
                        chinese = r.getString("chinese");
                    } else {
                        // 没有结果
                        chinese = "不认识的单词";
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>翻译结果</h1>");
        writer.println("<p>" + english + " 的意思是 " + chinese + "</p>");
    }
}
