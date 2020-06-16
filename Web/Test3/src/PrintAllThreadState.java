/**
 * @Author Li_ZW
 * @Date: 2020/06/16
 * @Time: 22:04
 * @Description
 */
public class PrintAllThreadState {
    public static void main(String[] args) {
        Thread.State[] values = Thread.State.values();
        for (Thread.State s : values) {
            System.out.println(s);
        }
    }
}
