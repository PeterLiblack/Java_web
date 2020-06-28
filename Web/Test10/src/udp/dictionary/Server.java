package udp.dictionary;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;

/**
 * @Author Li_ZW
 * @Date: 2020/06/28
 * @Time: 21:43
 * @Description
 */

public class Server {
    // 作为服务端，需要公开自己的地址
    // ip 地址，交给 OS 选，在所有的 ip 地址上都公开
    // port 端口，需要我们自己明确，随便选
    // 1. [0 - 65535] 一般建议选 1024 以后的
    // 2. port 只能属于唯一的一个进程，所以不能选择已经被使用的端口
    public static final int PORT = 8260;

    public static void main(String[] args) {
        // 1. 作为服务端，需要先开张
        //    这里也就是创建一个 socket 出来
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.printf("%s: DEBUG: 服务端 Socket 已创建，UDP 端口是: %d%n", new Date(), PORT);

            // 2. 在一个循环中，不停的接收请求
            // 每次接收到的请求，可以来自完全不同的用户
            // 接收请求 -> 处理请求得到响应 -> 发送响应
            while (true) {
                // 2.1 通过 socket 等待，客户端发来的请求 —— 被动的等待

                byte[] receiveBuffer = new byte[1024];
                // 需要应用的开发人员 和 用户保证请求不会超过 1024 字节

                // 创建一个 DatagramPacket 对象，并且把 接收缓冲区（receive buffer）关联上去
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, 0, receiveBuffer.length);

                // 通过 socket 等待客户端发送数据，会把接收数据放入 receivePacket 对象（关联的 receiveBuffer 中）
                // 会调用一个阻塞的方法，不知道什么时候才能返回
                System.out.printf("%s: DEBUG: 等待客户端发送请求过来 ...%n", new Date());
                serverSocket.receive(receivePacket);
                System.out.printf("%s: DEBUG: 收到了客户端发送来的请求%n", new Date());

                // 数据接收成功，需要明确
                // 2.1.1 对方是谁（对方的 ip + port)
                String host = receivePacket.getAddress().getHostName();
                System.out.printf("%s: DEBUG: 对方的 host 是: %s%n", new Date(), host);
                int port = receivePacket.getPort();
                System.out.printf("%s: DEBUG: 对方的 port 是: %d%n", new Date(), port);
                // 2.1.2 我接收了多少的数据
                int length = receivePacket.getLength();
                System.out.printf("%s: DEBUG: 接收到 %d 字节的数据%n", new Date(), length);

                // 2.1.3 还有一个任务：目前接收到的数据是字节形式的，我们转换成字符形式，便于理解
                //       字节 -> 字符 涉及到字符集编解码的问题
                //       假设双方都使用 utf-8 的编码（需要我们应用保证）
                String request = new String(receiveBuffer, 0, length, "UTF-8");
                System.out.printf("%s: DEBUG: 收到的请求是: %s%n", new Date(), request);

                // 2.2 进行业务处理，并得到准备发送回客户端的响应
                String response = service(host, port, request);
                System.out.printf("%s: DEBUG: 准备发送给对方的响应是: %s%n", new Date(), response);

                // 2.3 通过 socket 发送响应
                // 2.3.1 需要把 目前 字符数据 转化为 字节数据
                byte[] sendBuffer = response.getBytes("UTF-8");
                // 2.3.2 创建发送用的 DatagramPacket
                //       同时，关联上 byte[] 发送的数据
                //       和接收的时候不同的是：需要关联上对方是谁，便于经过 socket，让 OS 发送给网上的正确进程
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                        receivePacket.getAddress(), port);
                // 2.3.3 经过 socket 正式发送了
                System.out.printf("%s: DEBUG: 准备发送数据 ...%n", new Date());
                serverSocket.send(sendPacket);
                System.out.printf("%s: DEBUG: 发送数据成功%n", new Date());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Translation {
        private String 中文含义;
        private List<String> 例句 = new ArrayList<>();
    }

    private static final Map<String, Translation> 字典 = new HashMap<>();
    static {
        {
            Translation translation = new Translation();
            translation.中文含义 = "n. 苹果，苹果树，苹果似的东西；[美俚]炸弹，手榴弹，（棒球的）球；[美俚]人，家伙。";
            translation.例句.add("He took another bite of apple. \r\n\r\n他又咬了一口苹果。");
            translation.例句.add("She crunched her apple noisily. \r\n\r\n她吃苹果发出嘎嚓嘎嚓的声音。");
            字典.put("apple", translation);
        }

        {
            Translation translation = new Translation();
            translation.中文含义 = "n. [园艺] 梨树；梨子";
            translation.例句.add("She bit into a ripe juicy pear. \r\n\r\n她咬了一口熟透了的多汁的梨。");
            translation.例句.add("Pear trees are grafted on quince rootstocks. \r\n\r\n梨树被嫁接到榅桲的根茎上。");
            字典.put("pear", translation);
        }
    }

    /**
     * 实现字典翻译服务（translate service）
     * @param host
     * @param port
     * @param request
     * @return
     */
    private static String service(String host, int port, String request) {
        Translation translation = 字典.get(request);
        if (translation == null) {
            return "不认识的单词！";
        }

        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("含义:\r\n");
        responseBuilder.append(translation.中文含义);
        responseBuilder.append("\r\n\r\n例句:\r\n");
        for (String 例句 : translation.例句) {
            responseBuilder.append(例句);
            responseBuilder.append("\r\n");
        }

        return responseBuilder.toString();
    }
}


