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
     * カラム数.
     *
     * @return
     */
    int getLineLength();
}
