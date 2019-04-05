package framework.Utils;

import framework.annotation.PKBean;
import framework.annotation.PKField;

import java.lang.reflect.Field;

public class Tools {

    /**
     * 根据注解获取表名
     *
     * @param clazz
     * @return
     */
    public static String getTable(Class<?> clazz) {

        String tableName = "";

        PKBean pkBean = clazz.getAnnotation(PKBean.class);

        if (pkBean != null) {
            tableName = pkBean.value();
        } else {
            tableName = clazz.getSimpleName();
        }

        return tableName;
    }

    /**
     * 根据注解获取属性名称
     *
     * @param field
     * @return
     */
    public static String getColumn(Field field) {

        String column = "";

        PKField pkField = field.getAnnotation(PKField.class);

        if (pkField != null) {
            column = pkField.value();
        } else {
            column = field.getName();
        }

        return column;
    }

    public static String getMethod(Field field) {

        String fieldName = field.getName();
        //id ==> getId   name ==> getName
        String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        return "get" + name;
    }
}
