package com.lizun;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ReqServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printInfo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printInfo(req, resp);
    }

    private void printInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        // 方法(Method)
        writer.println("请求的方式是: " + req.getMethod());
        // URL or 路径(Path)
        writer.println("URL中的完整路径: " + req.getRequestURI());
        writer.println("URL中的Context路径: " + req.getContextPath());
        writer.println("URL中的 web.xml 中的路径: " + req.getServletPath());
        writer.println("完整的 QueryString: " + req.getQueryString());
        // 根据 query string 的 key 获取对应的 value
        writer.println("QueryString 中 name 为 key1 的值: " + req.getParameter("key1"));
        writer.println("QueryString 中 name 为 key2 的值: " + req.getParameter("key2"));
        // Enumeration 是一个用来做遍历的对象
        writer.println("QueryString 中有如下 name: ");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {  // 只要还有更多的元素，就循环
            String name = parameterNames.nextElement();  // 挨个取 name
            writer.println(name);
        }
        // 获取协议版本信息
        writer.println("请求协议版本: " + req.getProtocol());

        // 获取头信息
        // 根据 Key 获取对应的 Value
        writer.println("请求头: User-Agent = " + req.getHeader("User-Agent"));
        // req.getIntHeader("key");  相当于 Integer.parseInt(req.getHeader("key"))
        // 获取所有的请求头信息
        writer.println("所有的请求头信息如下: ");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            writer.println(name + " = " + value);
        }

        // 如果有请求体，如何获取
        // 重要信息：请求体的格式HTTP并没有完全规定死
        InputStream inputStream = req.getInputStream(); // 通过这个输入流，读取请求体中的内容（了解）
        // 如果是在 form 表单中提交，并且请求的 Content-Type 是 application/x-www-form-urlencoded 的话，通过 req.getParameter 获取
        String username = req.getParameter("username");
        writer.println("用户输入的 username 是: " + username);
        String password = req.getParameter("password");
        writer.println("用户输入的 password 是: " + password);
    }
}
