package reflection;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanUtils的使用
 */
public class BeanUtilsApp {

    @Test
    public void test01() throws Exception {

        Student student = new Student();

        BeanUtils.setProperty(student, "name", "pk");

        System.out.println(student);
    }

    @Test
    public void test02() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "pk");
        map.put("age", 18);

        Student student = new Student();

        //底层就是调用：BeanUtils.setProperty(student, "name", "若泽");
        BeanUtils.populate(student, map);//使用集合中的属性来填充JavaBean里面的属性

        System.out.println(student);
    }
}
