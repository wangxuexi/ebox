package com.fijo.ebox.base.annotation;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApproveAuthorityAnnotation {
    String tenant() default "tenant";

    String entityTypeCode() default "entityTypeCode";

    String dbTableName() default "dbTableName";

    String uId() default "uId";

    String orgCode() default "orgCode";

    String headerTableReName() default "headerTableReName";

    String headerTableReNameValue() default "a";

    String states() default "states";

    String sqlArgName() default "sql";
}
