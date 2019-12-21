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

public class Data implements ICSVEntity {
    public static final int COLUMN_LENGTH = 5;
    private static final long serialVersionUID = 1L;

    @ColumnSize(size = COLUMN_LENGTH)
    private int lineLength = 0;

    @Getter @DateWithYearMonthDate(nullable = true)
    private String date = null;

    @Getter @NotEmpty
    private String title = null;

    @Getter @Digits(integer = 6, fraction = 0) @Min(0)
    private String count = null;

    @Getter @IPv4Address(nullable = true)
    private String ipAddress = null;

    @Getter @Size(min = 0)
    private String notes = null;

    @Override
    public final void setData(final String[] data) {
        this.lineLength = data.length;
        try {
            this.date = data[0];
            this.title = data[1];
            this.count = data[2];
            this.ipAddress = data[3];
            this.notes = data[4];
        } catch (ArrayIndexOutOfBoundsException e) {
            //pass
        }
    }

    @Override
    public final int getLineLength() {
        return this.lineLength;
    }
}
