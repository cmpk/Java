package csv;

import java.io.Serializable;

public interface ICSVEntity extends Serializable {

    /**
     * データ1行分を本インスタンスに設定する.
     *
     * @param data データ1行分
     */
    void setData(String[] data);

    /**
     * データ1行分のカラム数.
     * データ1行分についてCSVから読み込んだ対象データのカラム数を取得する.
     * カラム数に対するバリデーションは{@see ColumnSize}にて実現される.
     * @return カラム数
     */
    int getColumnSize();
}
