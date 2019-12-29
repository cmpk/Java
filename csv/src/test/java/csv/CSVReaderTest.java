package csv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CSVReaderTest {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    @DisplayName("区切り文字がカンマでない")
    public final void testDelimiterIsNotComma() {
        CSVReader reader = new CSVReader();
        ICSVRecords csvRecords = null;
        try {
            csvRecords = reader.read((Class) TestRecords.class, "./src/test/resources/delimiter_is_tab.tsv", StandardCharsets.UTF_8, '\t', false);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            fail(e);
        }

        assertEquals(3, csvRecords.size());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    @DisplayName("ヘッダ付き")
    public final void testWithHeader() {
        CSVReader reader = new CSVReader();
        ICSVRecords csvRecords = null;
        try {
            csvRecords = reader.read((Class) TestRecords.class, "./src/test/resources/with_header.csv", StandardCharsets.UTF_8, true);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            fail(e);
        }

        assertEquals(3, csvRecords.size());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    @DisplayName("文字コード(SJIS)の読込")
    public final void testInSJIS() {
        CSVReader reader = new CSVReader();
        ICSVRecords csvRecords = null;
        try {
            csvRecords = reader.read((Class) TestRecords.class, "./src/test/resources/SJIS.csv", Charset.forName("SJIS"), true);
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
