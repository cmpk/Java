package validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IPv4AddressValidator.class)
/**
 * Validate that the annotated string is in IPv4 address
 */
public @interface IPv4Address {

    String message() default "{validation.IPv4Address.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean nullable() default false;

    @Target({ ElementType.FIELD, ElementType.PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        IPv4Address[] value();
    }
}
