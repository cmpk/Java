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

import csv.ICSVLine;

class DateWithYearMonthDateTest {
    private Validator validator = null;

    @BeforeEach
    public final void beforeAll() {
        this.validator = ValidationUtility.getValidator();
    }

    @Test
    @DisplayName("値がNullの場合にエラーとなること")
    public final void testNegativeWhenNull() {
        NotNullBean bean = new NotNullBean(null);
        Set<ConstraintViolation<ICSVLine>> violations = this.validator.validate(bean);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "2003/02/29", "2004/2/29", "2004/13/01", "2004/04/31", "2004/1/1"})
    @DisplayName("値がYYYY/MM/DDの形式でない場合にエラーとなること")
    public final void testNegativeWhenInvalidFormat(String str) {
        NotNullBean bean = new NotNullBean(str);
        Set<ConstraintViolation<ICSVLine>> violations = this.validator.validate(bean);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2004/02/29", "2004/01/01"})
    @DisplayName("値がYYYY/MM/DDの形式の場合にエラーにならないこと")
    public final void testPositive(String str) {
        NotNullBean bean = new NotNullBean(str);
        Set<ConstraintViolation<ICSVLine>> violations = this.validator.validate(bean);
        assertTrue(violations.isEmpty());
    }

    private static class NotNullBean implements ICSVLine {
        @DateWithYearMonthDate(nullable = false)
        private String dateStr = null;

        NotNullBean(final String value) {
            this.dateStr = value;
        }

        @Override
        public void setLine(final String[] data) {
            // pass
        }

        @Override
        public int getColumnSize() {
            return 0;
        }

        @Override
        public String[] getLine() {
            // TODO 自動生成されたメソッド・スタブ
            return null;
        }
    }
}
