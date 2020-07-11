import java.lang.Math;
import java.util.Scanner;

/**
 * @Author Li_ZW
 * @Date: 2020/07/11
 * @Time: 20:18
 * @Description
 */
public class demo {

    /**
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            String str=sc.next();
            System.out.println(Integer.decode(str));
        }
    }


    /**
     * 输入一行字符串，计算其中A-Z大写字母出现的次数
     * @param args
     */
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] array = scanner.nextLine().toCharArray();
        int[] record = new int[256];
        for (char c : array) {
            record[c]++;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            System.out.println((char) i+":"+record[i]);
        }
    }


    /**
     * 一个百万富翁遇到一个陌生人，陌生人找他谈了一个换钱的计划。该计划如下：
     * 我每天给你10 万元，你第一天给我1 分钱，第二天2 分钱，第三天4 分钱……
     * 这样交换 30 天后，百万富翁交出了多少钱？陌生人交出了多少钱？（注意一个是万元，一个是分）
     * @param args
     */
    public static void main1(String[] args){
        long sumRich=0;
        long sumStranger=0;
        for(int i=1;i<=30;i++){
            sumRich+=10;
            sumStranger=sumStranger+(long)Math.pow(2,i-1);
        }
        System.out.print(sumRich+" "+sumStranger);
    }


    /**风口之下，猪都能飞。当今中国股市牛市，真可谓“错过等七年”。
     * 给你一个回顾历史的机会，已知一支股票连续n天的价格走势，以长度为n的整数数组表示，
     * 数组中第i个元素（prices[i]）代表该股票第i天的股价。
     * 假设你一开始没有股票，但有至多两次买入1股而后卖出1股的机会，并且买入前一定要先保证手上没有股票。
     * 若两次交易机会都放弃，收益为0。 设计算法，计算你能获得的最大收益。
     * 输入数值范围：2<=n<=100,0<=prices[i]<=100
     * 计算你能获得的最大收益
     *
     * @param prices Prices[i]即第i天的股价
     * @return 整型
     */
    public int calculateMax(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int curPrice : prices) {
            firstBuy = Math.max(firstBuy, -curPrice);
            firstSell = Math.max(firstSell, firstBuy + curPrice);
            secondBuy = Math.max(secondBuy,firstSell - curPrice);
            secondSell = Math.max(secondSell, secondBuy + curPrice);
        }
        return secondSell;
    }
}
