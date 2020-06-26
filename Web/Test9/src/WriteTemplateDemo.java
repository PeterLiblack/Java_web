import java.io.*;

/**
 * @Author Li_ZW
 * @Date: 2020/06/26
 * @Time: 20:22
 * @Description
 */

public class WriteTemplateDemo {
    public static void 字节数据() throws IOException {
        byte[] buffer = new byte[8192];
        // 把 buffer 填充了有效数据

        try (OutputStream os = new FileOutputStream("测试目录\\out.png")) {
            for (int i = 0; i < 8; i++) {
                os.write(buffer, i * 1024, 1024);
            }

            os.flush();
        }
    }

    public static void 字符数据() throws IOException {
        char[] buffer = new char[8192];
        String s = "...";
        // buffer 中假设是有效数据

        try (OutputStream os = new FileOutputStream("测试目录\\输出.txt")) {
            try (Writer writer = new OutputStreamWriter(os, "UTF-8")) {
                /*
                for (int i = 0; i < 8; i++) {
                    writer.write(buffer, i * 1024, 1024);
                }
                writer.flush();
                */
                try (PrintWriter printWriter = new PrintWriter(writer)) {
                    printWriter.println(s);
                    printWriter.printf("想写什么写什么");

                    printWriter.flush();        // 刷新操作不要忘记
                }
            }
        }
    }
}

