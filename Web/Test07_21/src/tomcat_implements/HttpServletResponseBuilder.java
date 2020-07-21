package tomcat_implements;

import servlet_standard.HttpServletResponse;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class HttpServletResponseBuilder {
    public static HttpServletResponse build() throws UnsupportedEncodingException {
        HttpServletResponseImpl resp = new HttpServletResponseImpl();
        return resp;
    }
}
