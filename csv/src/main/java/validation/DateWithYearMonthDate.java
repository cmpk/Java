package validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateWithYearMonthDateValidator.class)
/**
 * Validate that the annotated string is in YYYY/MM/DD Date format
 */
public @interface DateWithYearMonthDate {

    String message() default "{validation.DateWithYearMonthDate.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean nullable() default false;

    @Target({ ElementType.FIELD, ElementType.PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        DateWithYearMonthDate[] value();
    }
}
