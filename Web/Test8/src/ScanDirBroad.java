import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Li_ZW
 * @Date: 2020/06/24
 * @Time: 21:46
 * @Description
 */

public class ScanDirBroad {
    public static void main(String[] args) {
        File root = new File("测试目录\\非空目录");
        //  广度优先一定用到队列
        Queue<File> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            File node = queue.poll();
            if (node.isDirectory()) {
                System.out.println(node.getAbsolutePath() + "\\");
            } else if (node.isFile()) {
                System.out.println(node.getAbsolutePath());
            }
            // 找到 node 的所有孩子
            if (node.isDirectory()) {
                File[] children = node.listFiles();
                if (children != null) {
                    for (File child : children) {
                        queue.offer(child);
                    }
                }
            }
        }
    }
}

