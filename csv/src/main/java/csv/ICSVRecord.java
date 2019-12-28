package csv;

import java.io.Serializable;

import org.apache.commons.csv.CSVRecord;

public interface ICSVRecord extends Serializable {

    /**
     * CSVレコードを本インスタンスに設定する.
     *
     * @param record CSVレコード
     */
    void setRecord(CSVRecord record);

    /**
     * CSVレコードを取得する.
     * @return CSVレコード
     */
    String[] getRecord();

    /**
     * インスタンスが所持しているCSVレコードのカラム数.
     * カラム数に対するバリデーションは{@see ColumnSize}にて実現される.
     * @return カラム数
     */
    int getColumnSize();
}
