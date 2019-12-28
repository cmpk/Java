package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {
    public static final char DEFAULT_DELIMITER = ',';
    public static final String DEFAULT_RECORD_SEPARATOR = "\r\n";
    public static final boolean DEFAULT_INCLUDE_HEADER = true;

    /**
     * @see CSVReader#read(String, String, String, boolean)
     *
     * @param clazz
     * @param filepath
     * @param delimiter
     * @return
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final String filepath, final char delimiter) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, filepath, delimiter, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * @see CSVReader#read(String, String, String, boolean)
     *
     * @param clazz
     * @param filepath
     * @param includeHeader
     * @return
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final String filepath, final boolean includeHeader) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, filepath, DEFAULT_DELIMITER, includeHeader);
    }

    /**
     * @see CSVReader#read(String, String, String, boolean)
     *
     * @param clazz
     * @param filepath
     * @return
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final String filepath) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, filepath, DEFAULT_DELIMITER, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * CSVを読み込み、その内容を指定したインスタンスとして取得する.
     *
     * @param clazz     CSVの内容1行分を表すクラスのFQDN.
     * @param filepath      CSVファイルのパス.
     * @param delimiter     CSVの区切り文字.
     * @param includeHeader CSVにヘッダーが含まれるか.
     * @return
     * @throws ClassNotFoundException clazz が見つからない場合に発生.
     * @throws FileNotFoundException  CSVファイルが見つからない場合に発生.
     * @throws IllegalAccessException clazz のインスタンス作成時のエラー.
     * @throws InstantiationException clazz のインスタンス作成時のエラー.
     * @throws IOException            CSVファイルのインスタンス作成、および、CSV読込み中のエラー.
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final String filepath, final char delimiter, final boolean includeHeader) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException {
        CSVFormat format = CSVFormat
                .DEFAULT
                .withIgnoreEmptyLines(true)                         // 空行を無視する
                .withDelimiter(delimiter)
                .withRecordSeparator(DEFAULT_RECORD_SEPARATOR)
                .withIgnoreSurroundingSpaces(true);                 // 値を trim して取得する
        if (includeHeader) {
            format = format.withFirstRecordAsHeader(); // 最初の行をヘッダーとして読み飛ばす
        }

        File file = new File(filepath);
        List<CSVRecord> list = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            list = format.parse(br).getRecords();
        }

        ICSVRecords records = getCSVLines(clazz);
        records.addRecords(list);

        return records;
    }

    private ICSVRecords getCSVLines(final Class<ICSVRecords> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        ICSVRecords entity = (ICSVRecords) clazz.newInstance();
        return entity;
    }
}
