package Utils;

import framework.Utils.DBUtils;
import org.junit.Test;

public class DBTest {

    @Test
    public void test01() {
        System.out.println(DBUtils.getConnection());
    }
}
