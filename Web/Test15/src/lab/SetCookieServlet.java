package lab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/set-cookie")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setHeader("Set-Cookie", "today=2020-07-09");   // 这句是关键

        // 以下是 Servlet 提供的方便的类和方法种 cookie
        // 本质上都是在设置响应头: Set-Cookie
        Cookie cookie = new Cookie("today", "2020-07-09");
        cookie.setDomain("baidu.com");  // 我们的域假如是 www.baidu.com，最多可以设置到 baidu.com
                                         // tieba.baidu.com
                                         // leyan.baidu.com
                                         // 实际上，我们设置不了，因为我们不是 baidu.com 的子域
        resp.addCookie(cookie);

        resp.setContentType("text/plain; charset=utf-8");
        resp.getWriter().println("种下cookie: today=2020-07-09");
    }
}
