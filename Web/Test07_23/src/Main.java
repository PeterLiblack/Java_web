import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Comparator;

public class Main {

    /**
     * 给定一个数组，取四条边组成最大平行四边形的面积
     * 输入数组，输出最大面积
     * @param args
     */
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] lines = new long[n];
        int i = 0;
        while (i < n) {
            lines[i++] = scanner.nextInt();
        }
        Arrays.sort(lines);
        List<Long> side = new ArrayList<>();
        int count = 1;
        for (i = n-1; i >= 0 ; i--) {
            if (side.size() >= 2) {
                break;
            }
            if (i > 0 && lines[i] = lines[i - 1]) {
                count++;
            } else {
                if (count >= 4) {
                    side.add(lines[i]);
                    side.add(lines[i]);
                } else if (count >= 2) {
                    side.add(lines[i]);
                }
                count = 1;
            }
        }
        if (side.size() < 2) {
            System.out.println((-1));
        } else {
            System.out.println(side.get(0) * side.get(1));
        }
    }


    //有一个穿着可以把数组的一个元素提到0号位置
    //给定一个数组，输出至少多少次这样的操作使数组变得有序
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        int[] array = new int[n];
        int[] sortArray = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
            sortArray[i] = array[i];
        }
        Arrays.sort(sortArray);
        int c = 0;
        int j = n-1;
        for (int i = n-1; i >= 0; i--) {
            if (array[i] == sortArray[j]) {
                ++c;
                --j;
            }
        }
        System.out.println(n - c);
    }


    //怪兽在一个一维的数轴上，他们有属性位置和血量，玩家可以定点攻击，攻击范围为【x-range，x+range】
    //求最少攻击次数能消灭所有的怪物

    public static void main3(String[] args) {
        int time = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int range = sc.nextInt();
        int[][] position = new int[n][2];
        for (int i = 0; i < n; i++) {
            position[i][0] = sc.nextInt();
            position[i][1] = sc.nextInt();
        }
        Arrays.sort(position, Comparator.comparingInt(p -> p[0]));
        for (int i = 0; i < n; i++) {
            while (position[i][1] > 0) {
                int tmp = position[i][1];
                for (int j = i; j < n; j++) {
                    if (position[j][0] - position[i][0] <= range * 2) {
                        position[j][1] -= tmp;
                    }
                }
                time += tmp;
            }
        }
        System.out.println(time);
    }
}
