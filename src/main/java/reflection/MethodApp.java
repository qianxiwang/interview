package reflection;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 1）如何获取到执行的Method     getDeclaredMethod
 * 2）如何动态调用Method   invoke
 */
public class MethodApp {


    @Test
    public void test01() throws Exception {

        Class<?> clazz = Class.forName("reflection.Student");

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Class<?>[] parameterTypes = method.getParameterTypes();

            for (Class<?> parameterType : parameterTypes) {
                System.out.println(method.getName() + "..." + parameterType);
            }
        }
    }

    @Test
    public void test02() throws Exception {

        Class<?> clazz = Class.forName("reflection.Student");
        Object object = clazz.newInstance();

        Method setSexMethod = clazz.getDeclaredMethod("setSex", String.class);
        setSexMethod.invoke(object, "男");

//        System.out.println(object);

        Method getSexMethod = clazz.getDeclaredMethod("getSex");
        Object sex = getSexMethod.invoke(object);

        System.out.println(sex);
    }
}
