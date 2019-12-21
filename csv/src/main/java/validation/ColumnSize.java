package validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * CSVカラム数バリデートアノテーション用クラス
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ColumnSizeValidator.class)
public @interface ColumnSize {

    String message() default "{validation.ColumnSize.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int size();

    @Target({ ElementType.FIELD, ElementType.PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        ColumnSize[] value();
    }
}
