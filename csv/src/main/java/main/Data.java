package main;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import csv.ICSVEntity;
import lombok.Getter;
import validation.ColumnSize;
import validation.DateWithYearMonthDate;
import validation.IPv4Address;
import validation.seq.FirstCheck;
import validation.seq.SecondCheck;

public class Data implements ICSVEntity {
    private static final long serialVersionUID = 1L;

    // CHECKSTYLE:OFF  // マジックナンバーに対する checkstyle 抑制
    @ColumnSize(size = 5, groups = FirstCheck.class)
    private int columnSize = 0;
    // CHECKSTYLE:ON

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
    public final void setData(final String[] data) {
        this.columnSize = data.length;
        try {
            // CHECKSTYLE:OFF  // マジックナンバーに対する checkstyle 抑制
            this.date = data[0];
            this.title = data[1];
            this.count = data[2];
            this.ipAddress = data[3];
            this.notes = data[4];
            // CHECKSTYLE:ON
        } catch (ArrayIndexOutOfBoundsException e) {
            // pass （受け取った行のカラム数が足りない場合に発生）
        }
    }

    @Override
    public final int getColumnSize() {
        return this.columnSize;
    }
}
