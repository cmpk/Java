package csv;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CSVUtilityTest {
    @Test
    @DisplayName("引数がNullでない場合に期待する文字列を取得できること")
    public final void testPositive_notNull() {
        assertEquals("test", CSVUtility.toString("test"));
    }

    @Test
    @DisplayName("引数がNullの場合に空文字を取得できること")
    public final void testNegative_isNull() {
        assertEquals("", CSVUtility.toString(null));
    }

    @ParameterizedTest
    @CsvSource({"2016/02/29,yyyy/MM/dd,2016/02/29", "2016/02/29,,"})
    @DisplayName("引数の日付オブジェクトとフォーマットが想定どおりの場合に期待する文字列を取得できること")
    public final void testPositive_Date(String inputDateStr, String inputFormat, String expected) {
        inputFormat = (inputFormat == null) ? "" : inputFormat;
        expected = (expected == null) ? "" : expected;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse(inputDateStr);

            assertEquals(expected, CSVUtility.toString(date, inputFormat));
        } catch (ParseException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("引数の日付オブジェクトがnullの場合に空文字を取得できること")
    public final void testNegative_dateIsNull() {
        assertEquals("", CSVUtility.toString(null, "dummy"));
    }

    @Test
    @DisplayName("引数のフォーマットがnullの場合に空文字を取得できること")
    public final void testNegative_formatIsNull() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse("2016/02/29");

            assertEquals("", CSVUtility.toString(date, null));
        } catch (ParseException e) {
            fail(e);
        }
    }
}
