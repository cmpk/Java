package validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class IPv4AddressValidator implements ConstraintValidator<IPv4Address, String> {
    public static final String IPV4_FORMAT = "\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}";
    private Boolean isNullable;

    @Override
    public final void initialize(final IPv4Address constraintAnnotation) {
        this.isNullable = constraintAnnotation.nullable();
    }

    @Override
    public final boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return this.isNullable ? true : false;
        }
        boolean ret = value.matches(IPV4_FORMAT);
        if (!ret) {
            HibernateConstraintValidatorContext hContext = context.unwrap(HibernateConstraintValidatorContext.class);
            hContext.addMessageParameter("value", value);
        }
        return ret;
    }
}
