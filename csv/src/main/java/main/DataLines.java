package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import csv.ICSVLine;
import csv.ICSVLines;

public class DataLines implements ICSVLines {
    public static final int COLUMN_SIZE = 5;

    public static final String HEADER_DATE = "日付";
    public static final String HEADER_TITLE = "件名";
    public static final String HEADER_COUNT = "件数";
    public static final String HEADER_IP_ADDRESS = "IPアドレス";
    public static final String HEADER_NOTES = "備考";

    public static final String DATE_FORMAT = "yyyy/MM/dd";

    private List<DataLine> dataLineList = new ArrayList<DataLine>();
    private int index = 0;

    @Override
    public String[] getHeader() {
        return new String[] { HEADER_DATE, HEADER_TITLE, HEADER_COUNT, HEADER_IP_ADDRESS, HEADER_NOTES };
    }

    @Override
    public void addLine(String[] values) {
        DataLine line = new DataLine();
        line.setLine(values);
        this.dataLineList.add(line);
    }

    @Override
    public int size() {
        return this.dataLineList.size();
    }

    @Override
    public Iterator<ICSVLine> iterator() {
        this.index = 0;
        return this;
    }

    @Override
    public boolean hasNext() {
        return (this.index < this.dataLineList.size());
    }

    @Override
    public ICSVLine next() {
        return this.dataLineList.get(this.index++);
    }

}
