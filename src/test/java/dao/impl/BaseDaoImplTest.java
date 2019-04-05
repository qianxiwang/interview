package dao.impl;

import framework.dao.BaseDao;
import framework.dao.Impl;
import framework.domain.User;
import org.junit.Test;

import java.io.Serializable;
import java.util.Date;

public class BaseDaoImplTest {

    @Test
    public void test01() {

        BaseDao baseDao = new Impl();

        User user = new User("pk", 18, new Date());

        Serializable id = baseDao.save(user);
        System.out.println(id);
    }
}
