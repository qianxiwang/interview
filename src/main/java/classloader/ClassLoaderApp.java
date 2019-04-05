package classloader;

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
