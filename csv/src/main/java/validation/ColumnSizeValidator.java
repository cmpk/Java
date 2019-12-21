package validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class ColumnSizeValidator implements ConstraintValidator<ColumnSize, Integer> {
    private int size;

    @Override
    public final void initialize(final ColumnSize constraintAnnotation) {
        this.size = constraintAnnotation.size();
    }

    @Override
    public final boolean isValid(final Integer value, final ConstraintValidatorContext context) {
        boolean ret = (value != null && value == size);
        if (!ret) {
            HibernateConstraintValidatorContext hContext = context.unwrap(HibernateConstraintValidatorContext.class);
            hContext.addMessageParameter("value", value);
        }
        return ret;
    }
}
