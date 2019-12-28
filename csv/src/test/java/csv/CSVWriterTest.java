package csv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CSVWriterTest {
    public static final String OUTPUT_CSV_FILE_PATH = "work/test.csv";

    @AfterEach
    public final void afterEach() {
        try {
            Files.delete(Paths.get(OUTPUT_CSV_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("ヘッダーを含む")
    public final void testIncludeHeader() {
        TestRecords records = createTestRecords();

        CSVWriter writer = new CSVWriter();
        try {
            writer.write(records, OUTPUT_CSV_FILE_PATH, ',', true);
        } catch (IOException e) {
            fail(e);
        }

        int cnt = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_CSV_FILE_PATH))) {
            while ((br.readLine()) != null) {
                cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(4, cnt);  //SUPPRESS CHECKSTYLE ignore magic number
    }

    @Test
    @DisplayName("ヘッダーを含まない")
    public final void testExcludeHeader() {
        TestRecords records = createTestRecords();

        CSVWriter writer = new CSVWriter();
        try {
            writer.write(records, OUTPUT_CSV_FILE_PATH, ',', false);
        } catch (IOException e) {
            fail(e);
        }

        int cnt = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_CSV_FILE_PATH))) {
            while ((br.readLine()) != null) {
                cnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(3, cnt);  //SUPPRESS CHECKSTYLE ignore magic number
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private TestRecords createTestRecords() {
        CSVReader reader = new CSVReader();
        ICSVRecords csvRecords = null;
        try {
            csvRecords = reader.read((Class) TestRecords.class, "./src/test/resources/with_header.csv", true);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            fail(e);
        }

        return (TestRecords) csvRecords;
    }

    public static class TestRecords implements ICSVRecords {
        private List<ICSVRecord> records = new ArrayList<ICSVRecord>();
        private int index = 0;

        @Override
        public final String[] getHeader() {
            return new String[] {"h1", "h2", "h3"};
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
