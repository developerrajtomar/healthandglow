package com.rajtomar.healthandglow.interview.validation.validator;

import com.rajtomar.healthandglow.interview.validation.constraint.FieldMatch;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static lombok.AccessLevel.PRIVATE;

/**
 * Validator for {@link FieldMatch} annotation.
 *
 * @author Raj Tomar
 */
@Slf4j
@FieldDefaults(level = PRIVATE)
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    String firstFieldName;
    String secondFieldName;
    String message;

    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            final Object firstObj = BeanUtils.getProperty(value, this.firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, this.secondFieldName);

            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(
                    secondObj);
        } catch (final Exception ignore) {
            log.error("Field Validator Exception for firstFieldName::{}, secondFieldName::{}, " +
                      "Exception::", firstFieldName, secondFieldName, ignore);
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(this.message)
                   .addPropertyNode(this.firstFieldName)
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
