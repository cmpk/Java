package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class DateWithYearMonthDateValidator implements ConstraintValidator<DateWithYearMonthDate, String> {
    public static final String DEFAULT_FORMAT = "yyyy/MM/dd";

    private Boolean isNullable;

    @Override
    public final void initialize(final DateWithYearMonthDate constraintAnnotation) {
        this.isNullable = constraintAnnotation.nullable();
    }

    @Override
    public final boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return this.isNullable ? true : false;
        }
        boolean ret = isValidFormat(DEFAULT_FORMAT, value);
        if (!ret) {
            HibernateConstraintValidatorContext hContext = context.unwrap(HibernateConstraintValidatorContext.class);
            hContext.addMessageParameter("value", value);
        }
        return ret;
    }

    private static boolean isValidFormat(final String format, final String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (value != null) {
                date = sdf.parse(value);
                if (!value.equals(sdf.format(date))) {
                    date = null;
                }
            }

        } catch (ParseException ex) {
            // pass
        }
        return date != null;
    }
}
