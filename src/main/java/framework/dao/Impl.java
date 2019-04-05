package framework.dao;

import framework.Utils.DBUtils;
import framework.Utils.Tools;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 反射/注解  能够讲解清楚
 * 拓展： 代码生成器
 */
public class Impl implements BaseDao {

    /**
     * 入参是T:User
     * insert into ruozedata_user(name,age,birth_day) values(?,?,?);
     */
    public <T> Serializable save(T t) {

        StringBuilder builder = new StringBuilder("insert into ");
        String table = Tools.getTable(t.getClass());
        builder.append(table).append(" (");

        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                String column = Tools.getColumn(field);
                builder.append(column).append(",");
            }
        }

        builder.deleteCharAt(builder.toString().length() - 1).append(") values (");

        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                builder.append("?,");
            }
        }
        builder.deleteCharAt(builder.toString().length() - 1).append(")");

        System.out.println(builder.toString());

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int index = 1;

        try {
            conn = DBUtils.getConnection();
//            pstmt = conn.prepareStatement(builder.toString());
            pstmt = conn.prepareStatement(builder.toString(), new String[]{"id"});

            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    String getMethod = Tools.getMethod(field);
                    Method method = clazz.getDeclaredMethod(getMethod);
                    Object obj = method.invoke(t);

                    pstmt.setObject(index++, obj);
                }

            }

            int rowcount = pstmt.executeUpdate();
            System.out.println("rowcount: " + rowcount);

            if (rowcount > 0) {
                rs = pstmt.getGeneratedKeys();
                rs.next();
                return (Serializable) rs.getObject(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
