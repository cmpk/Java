package csv;

import java.io.Serializable;

public interface ICSVLine extends Serializable {

    /**
     * データ1行分を本インスタンスに設定する.
     *
     * @param values データ1行分
     */
    void setLine(String[] values);

    /**
     * データ1行分を取得する.
     * @return データ1行分
     */
    String[] getLine();

    /**
     * データ1行分のカラム数.
     * データ1行分についてCSVから読み込んだ対象データのカラム数を取得する.
     * カラム数に対するバリデーションは{@see ColumnSize}にて実現される.
     * @return カラム数
     */
    int getColumnSize();
}
