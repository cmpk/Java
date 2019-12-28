package validation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import csv.ICSVRecord;

class ColumnSizeValidatorTest {
    private Validator validator = null;

    @BeforeEach
    public final void beforeAll() {
        this.validator = ValidationUtility.getValidator();
    }

    @Test
    @DisplayName("値がNullの場合にエラーとなること")
    public final void testNegativeWhenNull() {
        TestBean bean = new TestBean(null);
        Set<ConstraintViolation<ICSVRecord>> violations = this.validator.validate(bean);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    @DisplayName("値が1でない場合にエラーとなること")
    public final void testNegativeWhenInvalidNumber(final int value) {
        TestBean bean = new TestBean(value);
        Set<ConstraintViolation<ICSVRecord>> violations = this.validator.validate(bean);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("値が1の場合にエラーにならないこと")
    public final void testPositive() {
        TestBean bean = new TestBean(1);
        Set<ConstraintViolation<ICSVRecord>> violations = this.validator.validate(bean);
        assertTrue(violations.isEmpty());
    }

    private static class TestBean implements ICSVRecord {
        @ColumnSize(size = 1)
        private Integer columnSize = null;

        TestBean(final Integer value) {
            this.columnSize = value;
        }

        @Override
        public void setRecord(final CSVRecord record) {
            // pass
        }

        @Override
        public int getColumnSize() {
            return this.columnSize;
        }

        @Override
        public String[] getRecord() {
            return null;
        }
    }
}
