package com.gsonkeno.interview.jvm.understandJvm.chapter7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author gaosong
 * @since 2019-03-26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Factory {

    /**
     * The name of the factory
     */
    Class<?> type();

    /**
     * The identifier for determining which item should be instantiated
     */
    String id();
}
