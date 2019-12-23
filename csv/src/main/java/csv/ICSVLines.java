package csv;

import java.util.Iterator;

/**
 * CSVの内容を表すクラス.
 */
public interface ICSVLines extends Iterable<ICSVLine>, Iterator<ICSVLine> {
    /**
     * ヘッダーを取得する.
     * @return
     */
    String[] getHeader();

    /**
     * CSVデータ1行分を追加する.
     * @param values 1行分のCSVデータ
     */
    void addLine(String[] values);

    /**
     * CSVデータの行数を取得する.
     * @return
     */
    int size();
}
