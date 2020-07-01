import java.util.HashMap;
import java.util.Map;

/**
 * @Author Li_ZW
 * @Date: 2020/07/01
 * @Time: 21:42
 * @Description
 */

public class URLParser {
    public static void main(String[] args) {
        String[] urls = {
                "https://www.baidu.com/s?wd=java&rsv_spt=1&rsv_iqid=0xb86707600004351f&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_sug3=8&rsv_sug1=4&rsv_sug7=100&rsv_sug2=0&rsv_btype=i&inputT=2099&rsv_sug4=2305",
                "https://www.sogou.com/web?query=java&_asf=www.sogou.com&_ast=&w=01019900&p=40040100&ie=utf8&from=index-nologin&s_from=index&sut=871&sst0=1593516407073&lkt=4%2C1593516406203%2C1593516406827&sugsuv=00033F407B8B8B625E1ECF5D4D743627&sugtime=1593516407073",
                "http://www.qq.com/",
                "http://localhost:9939/hello"
        };

        for (String url : urls) {
            parseUrl(url);
            System.out.println("==============================");
        }
    }

    private static Map<String, Integer> schemaDefaultPortMap = new HashMap<>();
    static {
        schemaDefaultPortMap.put("http", 80);
        schemaDefaultPortMap.put("https", 443);
    }

    private static void parseUrl(String url) {
        // 1. 在 url 中定位 协议部分 —— 通过第一个 ://
        int pos;
        pos = url.indexOf("://");
        String schema = url.substring(0, pos);
        System.out.println("协议部分: " + schema);

        String remain = url.substring(pos + 3); // 3 是跳过 ://

        pos = remain.indexOf("/");
        String service = remain.substring(0, pos);
        System.out.println("服务端: " + service);

        // 通过 service，找到 host 和 port 部分
        int i = service.indexOf(":");
        String host = null;
        int port = -1;
        if (i == -1) {
            // 没有明确 port，则 port 信息使用协议的默认信息
            // service 本身就是一个 host
            host = service;
            port = schemaDefaultPortMap.get(schema);
        } else {
            // 代表 service 由 host 和 port 组成
            host = service.substring(0, i);
            port = Integer.parseInt(service.substring(i + 1));
        }
        System.out.println("服务端主机信息: " + host);
        System.out.println("服务端端口信息: " + port);

        remain = remain.substring(pos);     // 包含最开始的 /
        pos = remain.indexOf("?");// ? 代表查询字符串的开始
        if (pos == -1) {
            System.out.println("路径部分: " + remain);
        } else {
            String path = remain.substring(0, pos);
            String queryString = remain.substring(pos + 1);
            System.out.println("路径部分: "+ path);
            System.out.println("查询字符串部分: " + queryString);
        }
    }
}

