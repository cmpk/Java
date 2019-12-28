package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import csv.ICSVRecord;
import csv.ICSVRecords;

public class DataRecords implements ICSVRecords {
    public static final int COLUMN_SIZE = 5;

    public static final String HEADER_DATE = "日付";
    public static final String HEADER_TITLE = "件名";
    public static final String HEADER_COUNT = "件数";
    public static final String HEADER_IP_ADDRESS = "IPアドレス";
    public static final String HEADER_NOTES = "備考";

    public static final String DATE_FORMAT = "yyyy/MM/dd";

    private List<DataRecord> dataRecordList = new ArrayList<DataRecord>();
    private int index = 0;

    @Override
    public final String[] getHeader() {
        return new String[] {HEADER_DATE, HEADER_TITLE, HEADER_COUNT, HEADER_IP_ADDRESS, HEADER_NOTES};
    }

    @Override
    public final void addRecords(final List<CSVRecord> records) {
        for (CSVRecord record : records) {
            DataRecord line = new DataRecord();
            line.setRecord(record);
            this.dataRecordList.add(line);
        }
    }

    @Override
    public final int size() {
        return this.dataRecordList.size();
    }

    @Override
    public final Iterator<ICSVRecord> iterator() {
        this.index = 0;
        return this;
    }

    @Override
    public final boolean hasNext() {
        return (this.index < this.dataRecordList.size());
    }

    @Override
    public final ICSVRecord next() {
        return this.dataRecordList.get(this.index++);
    }

}
