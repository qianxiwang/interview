package reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * 如何使用反射来调用构造器
 * 1)如何获取构造器    getConstructor/getConstructors
 * 2）如何使用指定的构造器是实例化对象   newInstance
 */
public class ConstructorApp {

    @Test
    public void test01() throws Exception {

        Class<?> clazz = Class.forName("reflection.Student");

        Constructor[] constructors = clazz.getDeclaredConstructors();

        for (Constructor constructor : constructors) {

            Class<?>[] parameterTypes = constructor.getParameterTypes();
            System.out.println(constructor.getName());

            for (Class<?> parameterType : parameterTypes) {
                System.out.println("..." + parameterType);
            }
        }

    }

    @Test
    public void test02() throws Exception {

        Class<?> clazz = Class.forName("reflection.Student");

        Constructor constructor = clazz.getConstructor();
        Object object = constructor.newInstance();

        System.out.println(object);

        constructor = clazz.getConstructor(String.class, String.class, Integer.class);

        object = constructor.newInstance("qianxi", "nan", 18);
        System.out.println(object);
    }
}
