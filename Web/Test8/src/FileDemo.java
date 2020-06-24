import java.io.File;
import java.util.Scanner;

/**
 * @Author Li_ZW
 * @Date: 2020/06/24
 * @Time: 21:45
 * @Description
 */

public class FileDemo {
    public static void main(String[] args) {
        //File file = new File("测试目录\\a.txt");
        // 模拟文件重命名的操作
        /*
        File dest = new File("测试目录\\b.txt");
        boolean b = file.renameTo(dest);
        System.out.println(b);
        */

        // 模拟剪切-粘贴的操作
        /*
        File dest = new File("a.txt");
        boolean b = file.renameTo(dest);
        System.out.println(b);
        */

        /*
        Scanner scanner = new Scanner(System.in);
        {
            File file = new File("测试目录\\a.txt");
            file.delete();
            System.out.println("等待一会，先观察 a.txt 文件是否已经被删除！");
            scanner.nextLine();
        }

        {
            File file = new File("测试目录\\b.txt");
            file.deleteOnExit();
            System.out.println("等待一会，先观察 b.txt 文件是否已经被删除！");
            scanner.nextLine();
        }
        */

        File file = new File("测试目录\\非空目录\\a.txt");
        /*
        File[] files = file.listFiles();
        for (File f: files) {
            System.out.println(f.getAbsolutePath());
        }
        */

        String[] list = file.list();
        System.out.println(list);
        System.out.println(list.length);
        for (String s: list) {
            System.out.println(s);
        }
    }
}
