import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author Li_ZW
 * @Date: 2020/06/22
 * @Time: 21:56
 * @Description
 */

public class MeiRiYiTi {
    static class Student {
        String name;
        int score;
        int number;

        public Student(String name, int score, int number) {
            this.name = name;
            this.score = score;
            this.number = number;
        }
    }

    // 传入两个 student，请告诉我谁应该靠前
    static class AscComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            /*
            if (o1.score != o2.score) {
                return o1.score - o2.score;
            } else {
                return o1.number - o1.number;
            }
            */

            if (o1.score < o2.score) {
                return -1;  // 只要是负数，就代表 o1 靠前
            } else if (o1.score > o2.score) {
                return 1;   // 返回整数，就代表 o2 靠前
            } else {
                // 代表成绩相同
                if (o1.number < o2.number) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }

    static class DescComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.score != o2.score) {
                return o2.score - o1.score;
            } else {
                return o1.number - o2.number;
            }
        }
    }

    public static void main(String[] args) {
        // 1. 处理输入
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        boolean ascOrDesc = true;   // true 代表升序；false 代表降序
        if (a == 0) {
            ascOrDesc = false;
        }
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int score = scanner.nextInt();
            students[i] = new Student(name, score, i);
        }

        // 因为我要调用的排序方法是 Arrays.sort
        if (ascOrDesc) {
            Arrays.sort(students, new AscComparator());
        } else {
            Arrays.sort(students, new DescComparator());
        }

        // 3. 处理输出
        for (int i = 0; i < n; i++) {
            System.out.println(students[i].name + " " + students[i].score);
        }
    }
}

