package csv;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

/**
 * CSVの内容を表すクラス.
 */
public interface ICSVRecords extends Iterable<ICSVRecord>, Iterator<ICSVRecord> {
    /**
     * ヘッダを取得する.
     * @return
     */
    String[] getHeader();

    /**
     * CSVレコードを纏めて追加する.
     * @param recordList 1行分のCSVデータ
     */
    void addRecords(List<CSVRecord> recordList);

    /**
     * CSVレコードの行数を取得する.
     * @return
     */
    int size();
}
