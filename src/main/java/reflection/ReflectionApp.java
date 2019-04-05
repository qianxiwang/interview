package reflection;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 如何获取class对象
 * 1)Class.forName("")
 * 2)class.class/Object.getClass()
 */
public class ReflectionApp<test02> {

    @Test
    public void test01() throws Exception {

        Class<?> clazz = Class.forName("java.lang.Object");

        System.out.println(clazz);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");//所有的方法
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");//所有public的方法
        methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

    }

    @Test
    public void test02() {

        String name = "pk";
        Class<?> clazz = name.getClass();

        System.out.println(clazz);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        clazz = String.class;
        System.out.println(clazz);

    }

    @Test
    public void test03() {

        System.out.println(Integer.class);//返回包名+类名
        System.out.println(Integer.TYPE);//返回基本类型

    }

    @Test
    public void test04() {

        Class<?> clazzType = Child.class;
        System.out.println(clazzType);//Child

        clazzType = clazzType.getSuperclass();//Parent
        System.out.println(clazzType);

        clazzType = clazzType.getSuperclass();//Object
        System.out.println(clazzType);

        clazzType = clazzType.getSuperclass();//null
        System.out.println(clazzType);

    }

    @Test
    public void test05() throws Exception {

        List<String> teachers = new ArrayList<String>();
        teachers.add("苍老师");
        teachers.add("龙老师");

//        teachers.add(1000);//能不能放不同类型的数据呢？？？？

        Class<?> clazz = teachers.getClass();
        System.out.println(clazz);

        Method method = clazz.getDeclaredMethod("add", Object.class);
        method.invoke(teachers, 10000);
        method.invoke(teachers, "波老师");

        for (Object object : teachers) {
            System.out.println(object);
        }
    }


}


class Parent {

}

class Child extends Parent {

}