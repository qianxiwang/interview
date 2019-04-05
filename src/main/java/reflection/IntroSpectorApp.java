package reflection;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 利用java的内省机制
 */
public class IntroSpectorApp {

    @Test
    public void test01() throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class, Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor.getName());
        }

    }

    @Test
    public void test02() throws Exception {

        Student student = new Student();
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("age", Student.class);

        Method setMethod = propertyDescriptor.getWriteMethod();//setAge
        setMethod.invoke(student, 30);

        System.out.println(student.getAge());

        Method getMethod = propertyDescriptor.getReadMethod();//getAge

//        System.out.println(getMethod.invoke(student));

        getMethod.invoke(student, null);

        System.out.println(propertyDescriptor.getPropertyType());//得到这个属性的类型
    }
}
