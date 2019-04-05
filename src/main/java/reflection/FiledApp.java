package reflection;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 类里边的私有属性能不能设置值？？？？必然是可以的
 */
public class FiledApp {

    @Test
    public void test01() throws Exception {

        Class<?> clazz = Class.forName("reflection.Student");

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            System.out.println(field.getType() + " : " + field.getName());
        }

        Field sex = clazz.getDeclaredField("sex");
        System.out.println(sex);
    }


    @Test
    public void test02() throws Exception {

        Class<?> clazz = Class.forName("reflection.Student");

        Student student = (Student) clazz.newInstance();

        Field field = clazz.getDeclaredField("sex");

        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        field.set(student, "男");
        System.out.println(student);

    }
}