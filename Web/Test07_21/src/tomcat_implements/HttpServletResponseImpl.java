package tomcat_implements;

import servlet_standard.HttpServletResponse;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HttpServletResponseImpl implements HttpServletResponse {
    private int status = 200;
    private Map<String, String> headerMap = new HashMap<>();
    private ByteArrayOutputStream bodyOutputStream = new ByteArrayOutputStream(4196);
    private PrintWriter bodyPrintWriter;

    public HttpServletResponseImpl() throws UnsupportedEncodingException {
        bodyPrintWriter = new PrintWriter(
                new OutputStreamWriter(bodyOutputStream, "utf-8")
        );
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    public void setHeader(String name, String value) {
        this.headerMap.put(name, value);
    }

    @Override
    public void setContentType(String contentType) {
        setHeader("Content-Type", contentType);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return bodyPrintWriter;
    }

    @Override
    public OutputStream getOutputStream() {
        return bodyOutputStream;
    }

    public void send(OutputStream socketOutputStream) throws IOException {
        bodyPrintWriter.flush();
        this.setHeader("Content-Length", String.valueOf(bodyOutputStream.size()));

        // 把 this 指向的 response 对象，按照HTTP响应格式，组装起来，然后发送到 socketOutputStream 中
        byte[] responseLine = buildResponseLine();
        socketOutputStream.write(responseLine);

        byte[] responseHeaders = buildResponseHeaders();
        socketOutputStream.write(responseHeaders);

        byte[] responseBody = buildResponseBody();
        socketOutputStream.write(responseBody);

        socketOutputStream.flush();
    }

    private byte[] buildResponseBody() {
        return bodyOutputStream.toByteArray();
    }

    private byte[] buildResponseHeaders() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            sb.append(String.format("%s: %s\r\n", name, value));
        }
        sb.append("\r\n");

        return sb.toString().getBytes();
    }

    private byte[] buildResponseLine() {
        String line = "HTTP/1.0 " + status + "\r\n";
        return line.getBytes();
    }

    @Override
    public String toString() {
        return "HttpServletResponseImpl{" +
                "status=" + status +
                ", headerMap=" + headerMap +
                ", bodyOutputStream=" + bodyOutputStream +
                ", bodyPrintWriter=" + bodyPrintWriter +
                '}';
    }
}
