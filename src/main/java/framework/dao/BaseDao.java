package framework.dao;

import java.io.Serializable;

public interface BaseDao {

    /**
     * insert into xxx(....) values(?,?,?);
     * @param t
     * @param <T>
     * @return
     */
    <T> Serializable save(T t);
}
