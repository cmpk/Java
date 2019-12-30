package db.oracle;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import common.CommandException;
import common.PropertyStorage;

class OracleTest {
    public static final String LOG_DIR = "work";
    public static final String TEST_DATA_DIR = "src/test/resources";
    private Oracle oracle;
    private String userId;
    private String password;

    @BeforeEach
    public final void beforeEach() {
        PropertyStorage prop = null;
        try {
            prop = new PropertyStorage("conf/database.properties", "UTF-8");
        } catch (IOException e) {
            fail(e);
        }
        this.oracle = new Oracle();
        this.userId = prop.getPropertyString("userid");
        this.password = prop.getPropertyString("password");
    }

    @ParameterizedTest
    @CsvSource({"with_header.csv,true", "without_header.csv,false"})
    @DisplayName("正常系")
    public final void testPositive_WithHeader(final String dataFilename, final boolean includeHeader) {
        try {
            this.oracle.loadCSV2DB(this.userId, this.password, LOG_DIR, TEST_DATA_DIR + "/DATA.ctl", TEST_DATA_DIR + "/" + dataFilename, includeHeader);
        } catch (CommandException | SQLLoaderException e) {
            fail(e);
        }
    }

    @ParameterizedTest
    @CsvSource({"DATA.ctl,no_exist_file.csv", "too_many_columns.ctl,with_header.csv"})
    @DisplayName("コマンドラインエラー")
    public final void testNegative_CommandError(final String controlFile, final String dataFile) {
        try {
            this.oracle.loadCSV2DB(this.userId, this.password, LOG_DIR, TEST_DATA_DIR + "/" + controlFile, TEST_DATA_DIR + "/" + dataFile, true);
            fail("SQLLoaderException does not occur.");
        } catch (SQLLoaderException e) {
            String expected = SQLLoaderExitCode.FAIL.getMessage();
            String actual = e.getMessage();
            assertTrue(actual.contains(expected));
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("ロードできない")
    public final void testPositive_CommandError_TooManyColumns() {
        try {
            this.oracle.loadCSV2DB(this.userId, this.password, LOG_DIR, TEST_DATA_DIR + "/DATA.ctl", TEST_DATA_DIR + "/invalid_data.csv", true);
            fail("SQLLoaderException does not occur.");
        } catch (SQLLoaderException e) {
            String expected = SQLLoaderExitCode.WARN.getMessage();
            String actual = e.getMessage();
            assertTrue(actual.contains(expected));
        } catch (Exception e) {
            fail(e);
        }
    }
}
