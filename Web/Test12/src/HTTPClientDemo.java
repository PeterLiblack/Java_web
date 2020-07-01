import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author Li_ZW
 * @Date: 2020/07/01
 * @Time: 21:41
 * @Description
 */

public class HTTPClientDemo {
    public static void main(String[] args) {
        // URL: http://www.baidu.com/s?wd=java
        String SERVER_HOST = "www.baidu.com";
        int SERVER_PORT = 80;

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            // GET 方法的请求，不包含请求体
            String request = "GET /s?wd=java HTTP/1.0\r\nHost: www.baidu.com\r\n\r\n";
            // 成功的发送请求
            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream(), "utf-8")
            );
            writer.print(request);
            writer.flush();

            // 等待响应
            Scanner scanner = new Scanner(socket.getInputStream(), "utf-8");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

