package com.rajtomar.healthandglow.interview.validation.constraint;

import com.rajtomar.healthandglow.interview.validation.validator.FieldMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation validates where two fields of an object has same value or not.
 *
 * @author Raj Tomar
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

    String message() default "The fields must match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String first();

    String second();

    @Documented
    @Retention(RUNTIME)
    @Target({TYPE, ANNOTATION_TYPE})
    @interface List {
        FieldMatch[] value();
    }

}
