package tomcat_implements;

import servlet_standard.HttpServlet;
import servlet_standard.HttpServletRequest;
import servlet_standard.HttpServletResponse;
import servlet_standard.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

public class NotFoundServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setStatus(404);
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<style>body { background-color: skyblue; }</style>");
        writer.println("<h1>404 没有这个页面</h1>");
    }
}
