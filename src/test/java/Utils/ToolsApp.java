package Utils;

import framework.Utils.Tools;
import framework.domain.User;
import org.junit.Test;

import java.lang.reflect.Field;

public class ToolsApp {

    @Test
    public void test01() {

        System.out.println(Tools.getTable(User.class));
    }

    @Test
    public void test02() throws Exception {

        Class<?> clazz = Class.forName("framework.domain.User");

        Field field = clazz.getDeclaredField("birthday");

        System.out.println(Tools.getColumn(field));

    }
}
