package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//该注解只能用于field上
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PKField {

    String value();

}
