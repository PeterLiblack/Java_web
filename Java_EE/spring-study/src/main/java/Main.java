import frank.Duck;
import frank.Duck2;
import frank.Duck3;
import frank.DuckShop;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        /**
         * Spring开启容器的方式：ApplicationContext 应用上下文（可以配置并管理Bean对象，及其他的工作）
         * ClassPathXmlApplicationContext根据classpath路径，指定一个xml文件（配置文件），
         * 并根据配置文件完成配置工作（如Bean的实例化）
         */
        ApplicationContext context = new
                ClassPathXmlApplicationContext("applications.xml");

        //通过bean的名称获取bean对象，bean名称就是xml中bean的id
        String bit = (String) context.getBean("bit");
//        String bit2 = (String) context.getBean("java.lang.String#0");
        System.out.println(bit);
//        System.out.println(bit2);

        //通过类型获取bean对象，如果该类型有多个对象，就会报错：只支持一个该类型的对象
//        String bit2 = context.getBean(String.class);
//        System.out.println(bit2);

        Duck d1 = (Duck) context.getBean("d1");
        System.out.println(d1);

        Duck2 d2 = (Duck2) context.getBean("d2");
        System.out.println(d2);

        Duck3 dk1 = (Duck3) context.getBean("dk1");
        System.out.println(dk1);

        DuckShop shop = (DuckShop) context.getBean("shop");
        System.out.println(shop);
    }
}