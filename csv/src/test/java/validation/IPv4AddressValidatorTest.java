package validation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import csv.ICSVEntity;

class IPv4AddressValidatorTest {
    private Validator validator = null;

    @BeforeEach
    public final void beforeAll() {
        this.validator = ValidationUtility.getValidator();
    }

    @Test
    @DisplayName("値がNullの場合にエラーとなること")
    public final void testNegativeWhenNull() {
        NotNullBean bean = new NotNullBean(null);
        Set<ConstraintViolation<ICSVEntity>> violations = this.validator.validate(bean);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "0", "0.0", "0.0.0", "0.0.0.", "a.0.0.0"})
    @DisplayName("値がIPアドレスの形式でない場合にエラーとなること")
    public final void testNegativeWhenInvalidFormat(String str) {
        NotNullBean bean = new NotNullBean(str);
        Set<ConstraintViolation<ICSVEntity>> violations = this.validator.validate(bean);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.0.0.0", "12.12.12.12", "255.255.255.255"})
    @DisplayName("値がIPアドレスの形式の場合にエラーにならないこと")
    public final void testPositive(String str) {
        NotNullBean bean = new NotNullBean(str);
        Set<ConstraintViolation<ICSVEntity>> violations = this.validator.validate(bean);
        assertTrue(violations.isEmpty());
    }

    private static class NotNullBean implements ICSVEntity {
        @IPv4Address(nullable = false)
        private String ipv4Address = null;

        NotNullBean(final String value) {
            this.ipv4Address = value;
        }

        @Override
        public void setData(final String[] data) {
            // pass
        }

        @Override
        public int getColumnSize() {
            return 0;
        }
    }
}
