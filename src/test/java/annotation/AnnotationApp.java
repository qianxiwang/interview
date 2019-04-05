package annotation;

import org.junit.Test;

import java.lang.reflect.Field;

public class AnnotationApp {
    @Test
    public void test01() throws Exception {

        Class<?> clazz = Class.forName("annotation.Animal");

        //判断该Class上是否有指定的注解
        Boolean isAnnotation = clazz.isAnnotationPresent(PKBean.class);
        System.out.println(isAnnotation);

        //通过反射拿到注解的信息
        if (isAnnotation) {
            PKBean pkBean = clazz.getAnnotation(PKBean.class);
            if (pkBean != null) {
                System.out.println(pkBean);
                System.out.println(pkBean.table() + " : " + pkBean.from());
            }
        }
    }

    @Test
    public void test02() throws Exception {

        Class<?> clazz = Class.forName("annotation.Animal");

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            PKField pkField = field.getAnnotation(PKField.class);

            if (pkField != null) {
                /**
                 * Bean id
                 * MySQL id
                 */
                System.out.println(field.getName() + "......." + pkField.value());
            }
        }

    }
}
