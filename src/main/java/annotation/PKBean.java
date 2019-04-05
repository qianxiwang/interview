package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)  //表明该注解可以用在什么地方
@Retention(RetentionPolicy.RUNTIME) //表明该注解在运行的是欧被加载到
public @interface PKBean {

    //注解中的属性叫做成员变量
    String table();

    String from() default "pk";

}
