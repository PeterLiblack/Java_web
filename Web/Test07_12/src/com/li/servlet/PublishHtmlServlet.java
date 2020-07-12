package com.li.servlet;

import com.li.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/publish.html")
public class PublishHtmlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.html");
            return;
        }

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>老陈说 | 发表文章</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form method=\"post\" action=\"publish\">\n" +
                "        <div>\n" +
                "            <label for=\"title\">标题</label>\n" +
                "            <input type=\"text\" id=\"title\" name=\"title\">\n" +
                "        </div>\n" +
                "        \n" +
                "        <div>\n" +
                "            <label for=\"content\">正文</label>\n" +
                "            <textarea id=\"content\" name=\"content\"></textarea>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div>\n" +
                "            <button type=\"submit\">发表</button>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>";

        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println(htmlContent);
    }
}
