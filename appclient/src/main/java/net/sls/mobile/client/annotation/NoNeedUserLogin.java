package net.sls.mobile.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，方法，定义了该注解的方法不会被拦截
 * @author huzeyu 2015-01-15
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)  
public @interface NoNeedUserLogin {

}
