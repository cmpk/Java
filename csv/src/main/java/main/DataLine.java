package main;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import csv.CSVUtility;
import csv.ICSVLine;
import lombok.Getter;
import validation.ColumnSize;
import validation.DateWithYearMonthDate;
import validation.IPv4Address;
import validation.seq.FirstCheck;
import validation.seq.SecondCheck;

public class DataLine implements ICSVLine {
    private static final long serialVersionUID = 1L;

    @ColumnSize(size = DataLines.COLUMN_SIZE, groups = FirstCheck.class)
    private int columnSize = 0;

    @Getter
    @DateWithYearMonthDate(nullable = true, groups = SecondCheck.class)
    private String date = null;

    @Getter
    @NotEmpty(groups = SecondCheck.class)
    private String title = null;

    // CHECKSTYLE:OFF  // マジックナンバーに対する checkstyle 抑制
    @Getter
    @Digits(integer = 6, fraction = 0, groups = SecondCheck.class)
    @Min(value = 0, groups = SecondCheck.class)
    private String count = null;
    // CHECKSTYLE:ON

    @Getter
    @IPv4Address(nullable = true, groups = SecondCheck.class)
    private String ipAddress = null;

    @Getter
    @Size(min = 0, groups = SecondCheck.class)
    private String notes = null;

    @Override
    public final int getColumnSize() {
        return this.columnSize;
    }

    @Override
    public void setLine(String[] values) {
        this.columnSize = values.length;
        try {
            // CHECKSTYLE:OFF  // マジックナンバーに対する checkstyle 抑制
            this.date = values[0];
            this.title = values[1];
            this.count = values[2];
            this.ipAddress = values[3];
            this.notes = values[4];
            // CHECKSTYLE:ON
        } catch (ArrayIndexOutOfBoundsException e) {
            // pass （受け取った行のカラム数が足りない場合に発生）
        }
    }

    @Override
    public String[] getLine() {
        return new String[] {CSVUtility.toString(this.date), CSVUtility.toString(this.title), CSVUtility.toString(this.count), CSVUtility.toString(this.ipAddress), CSVUtility.toString(this.notes)};
    }
}
