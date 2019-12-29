package csv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
            writer.write(records, OUTPUT_CSV_FILE_PATH, StandardCharsets.UTF_8, ',', true);
        } catch (IOException e) {
            fail(e);
        }

        int cnt = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_CSV_FILE_PATH))) {
            while ((br.readLine()) != null) {
                cnt++;
            }
        } catch (IOException e) {
            fail(e);
        }

        assertEquals(4, cnt);
    }

    @Test
    @DisplayName("ヘッダーを含まない")
    public final void testExcludeHeader() {
        TestRecords records = createTestRecords();

        CSVWriter writer = new CSVWriter();
        try {
            writer.write(records, OUTPUT_CSV_FILE_PATH, StandardCharsets.UTF_8, ',', false);
        } catch (IOException e) {
            fail(e);
        }

        int cnt = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_CSV_FILE_PATH))) {
            while ((br.readLine()) != null) {
                cnt++;
            }
        } catch (IOException e) {
            fail(e);
        }

        assertEquals(3, cnt);
    }

    @Test
    @DisplayName("文字コード(SJIS)")
    public final void testInSJIS() {
        TestRecords records = createTestRecords();

        CSVWriter writer = new CSVWriter();
        try {
            writer.write(records, OUTPUT_CSV_FILE_PATH, Charset.forName("SJIS"), ',', true);
        } catch (IOException e) {
            fail(e);
        }

        String line = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(OUTPUT_CSV_FILE_PATH), Charset.forName("SJIS")))) {
            line = br.readLine();
        } catch (IOException e) {
            fail(e);
        }

        try {
            String expected = String.join(",", records.getHeader()).getBytes("SJIS").toString();
            String actual = line.getBytes("SJIS").toString();
            assertEquals(expected, actual);
        } catch (UnsupportedEncodingException e) {
            fail(e);
        }

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private TestRecords createTestRecords() {
        CSVReader reader = new CSVReader();
        ICSVRecords csvRecords = null;
        try {
            csvRecords = reader.read((Class) TestRecords.class, "./src/test/resources/with_header.csv", StandardCharsets.UTF_8, true);
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
            return new String[] {"ヘッダ１", "ヘッダ２", "ヘッダ３"};
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
