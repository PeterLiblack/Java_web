package tomcat_implements;

import servlet_standard.HttpServlet;
import servlet_standard.HttpServletRequest;
import servlet_standard.HttpServletResponse;
import servlet_standard.ServletException;

import java.io.*;

public class StaticResourceHttpServlet extends HttpServlet {
    private String docRoot = "E:\\比特科技\\课程\\JavaWeb\\2020年-火箭班2\\2020-07-20-网络-自己实现Tomcat\\static";

    public boolean exists(String requestURI) {
        String path = docRoot + "/" + requestURI;
        File file = new File(path);

        return file.exists() && file.isFile();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = docRoot + "/" + req.getRequestURI();
        File file = new File(path);

        // 根据后缀名判断
        int i = req.getRequestURI().lastIndexOf(".");
        String suffix = req.getRequestURI().substring(i + 1);
        String contentType = "text/plain; charset=utf8";
        if (suffix.equals("html")) {
            contentType = "text/html; charset=utf-8";
        } else if (suffix.equals("jpg")) {
            contentType = "image/jpeg";
        } else if (suffix.equals("png")) {
            contentType = "image/png";
        }

        resp.setContentType(contentType);

        OutputStream os = resp.getOutputStream();
        try (InputStream is = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int len;

            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        }
    }
}
