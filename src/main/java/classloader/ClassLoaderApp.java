package classloader;

/**
 * ClassLoader的loadClass()和Class.forName()的区别？
 * <p>
 * 类的装载过程：
 * 1）加载：ClassLoader加载class字节码文件，生成Class对象
 * 2）链接：校验、准备、解析的过程
 * 3）初始化：执行类变量的赋值和静态代码块
 * <p>
 * <p>
 * loadClass：仅仅是完成了第一个操作，也就是所谓的加载。（Spring中的Bean就是使用这种lazy机制）
 * <p>
 * forName：已经是初始化完成了
 */
public class ClassLoaderApp {

    public static void main(String[] args) throws Exception {
        PKClassLoader classLoader = new PKClassLoader("/wangqingguo/bigdata/testdata/", "PKClassLoader");
        Class clazz = classLoader.loadClass("PK");

        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.getClassLoader().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent().getParent());

        clazz.newInstance();
    }
}
