package csv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CSVReaderTest {
    public enum Type {
        FILE, STREAM;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ParameterizedTest
    @EnumSource(Type.class)
    @DisplayName("区切り文字がカンマでない")
    public final void testDelimiterIsNotComma(Type type) {
        String filePath = "./src/test/resources/delimiter_is_tab.tsv";

        CSVReader reader = new CSVReader();
        ICSVRecords csvRecords = null;
        try {
            switch(type) {
            case FILE:
                csvRecords = reader.read((Class) TestRecords.class, filePath, StandardCharsets.UTF_8, '\t', false);
                break;
            case STREAM:
                FileInputStream inputStream = new FileInputStream(filePath);
                csvRecords = reader.read((Class) TestRecords.class, inputStream, StandardCharsets.UTF_8, '\t', false);
                break;
            default:
                fail("invalid input into this test");
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            fail(e);
        }

        assertEquals(3, csvRecords.size());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ParameterizedTest
    @EnumSource(Type.class)
    @DisplayName("ヘッダ付き")
    public final void testWithHeader(Type type) {
        String filePath = "./src/test/resources/with_header.csv";
        CSVReader reader = new CSVReader();
        ICSVRecords csvRecords = null;
        try {
            switch(type) {
            case FILE:
                csvRecords = reader.read((Class) TestRecords.class, filePath, StandardCharsets.UTF_8, true);
                break;
            case STREAM:
                FileInputStream inputStream = new FileInputStream(filePath);
                csvRecords = reader.read((Class) TestRecords.class, inputStream, StandardCharsets.UTF_8, true);
                break;
            default:
                fail("invalid input into this test");
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            fail(e);
        }

        assertEquals(3, csvRecords.size());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ParameterizedTest
    @EnumSource(Type.class)
    @DisplayName("文字コード(SJIS)の読込")
    public final void testInSJIS(Type type) {
        String filePath = "./src/test/resources/SJIS.csv";

        CSVReader reader = new CSVReader();
        ICSVRecords csvRecords = null;
        try {
            switch(type) {
            case FILE:
                csvRecords = reader.read((Class) TestRecords.class, filePath, Charset.forName("SJIS"), true);
                break;
            case STREAM:
                FileInputStream inputStream = new FileInputStream(filePath);
                csvRecords = reader.read((Class) TestRecords.class, inputStream, Charset.forName("SJIS"), true);
                break;
            default:
                fail("invalid input into this test");
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            fail(e);
        }

        assertEquals(1, csvRecords.size());
        for (ICSVRecord record : csvRecords) {
            assertEquals("拾壱,壱拾弐,壱拾参", String.join(",", record.getRecord()));
        }
    }

    public static class TestRecords implements ICSVRecords {
        private List<ICSVRecord> records = new ArrayList<ICSVRecord>();
        private int index = 0;

        @Override
        public final String[] getHeader() {
            return null;
        }

        @Override
        public final void addRecords(final List<CSVRecord> recordList) {
            for (CSVRecord record : recordList) {
                TestRecord line = new TestRecord();
                line.setRecord(record);
                this.records.add(line);
            }
        }

        @Override
        public final int size() {
            return this.records.size();
        }

        @Override
        public final Iterator<ICSVRecord> iterator() {
            this.index = 0;
            return this;
        }

        @Override
        public final boolean hasNext() {
            return (this.index < this.records.size());
        }

        @Override
        public final ICSVRecord next() {
            return (ICSVRecord) this.records.get(this.index++);
        }
    }

    public static class TestRecord implements ICSVRecord {
        private CSVRecord csvRecord = null;

        @Override
        public final void setRecord(final CSVRecord record) {
            this.csvRecord = record;
        }

        @Override
        public final String[] getRecord() {
            return new String[] {this.csvRecord.get(0), this.csvRecord.get(1), this.csvRecord.get(2)};
        }

        @Override
        public final int getColumnSize() {
            return this.csvRecord.size();
        }

    }
}
