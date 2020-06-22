import java.io.File;
import java.io.IOException;

/**
 * @Author Li_ZW
 * @Date: 2020/06/22
 * @Time: 21:56
 * @Description
 */

public class FileDemo {
    public static void main(String[] args) {
        // 先使用绝对路径
        // 1. 对应一个实际存在的文件
        // 2. 对应一个实际不存在的文件
        // 3. 对应一个实际存在的目录
        // 4. 修改下文件的属性，观察下代码的打印有什么不同
        //String path = "E:\\比特科技\\课程\\JavaWeb\\2020年-火箭班2\\2020-06-22-IO-1\\测试目录\\a.txt";

        String path = "a\\b\\c\\d\\e\\hello.txt";

        // 构建文件对象
        File file = new File(path);     // 路径对应的文件，但文件可能实际上不存在

        // 常见属性
        //System.out.println(file.isFile());
        //System.out.println(file.isDirectory());
        //System.out.println(file.isAbsolute());
        //System.out.println(file.isHidden());
        System.out.println(file.exists());
        //System.out.println(file.getAbsolutePath());
        //System.out.println(file.getPath());
        //System.out.println(file.getName());
        //System.out.println(file.getParent());
        //System.out.println(file.canRead());
        //System.out.println(file.canWrite());
        //System.out.println(file.canExecute());

        // 测试目录2\hello.txt
        // 要创建 hello.txt 文件，要求测试目录2首先存在
        // 但这里，测试目录2不存在，所以会出错
        // 1. 演示创建成功
        // 2. 演示文件已存在
        // 3. 上一级目录都不存在
//        try {
//            boolean newFile = file.createNewFile();     // 普通文件
//            System.out.println(newFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        boolean mkdir = file.mkdir();
//        System.out.println(mkdir);

        System.out.println("会把中间没有的目录，循环创建创建出来");
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);
    }
}
