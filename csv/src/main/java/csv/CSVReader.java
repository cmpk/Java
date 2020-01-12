package csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
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
     * @param charset
     * @return
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final String filepath, final Charset charset) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, filepath, charset, DEFAULT_DELIMITER, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * @see CSVReader#read(String, String, String, boolean)
     *
     * @param clazz
     * @param inputStream
     * @param charset
     * @return
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final InputStream inputStream, final Charset charset) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, inputStream, charset, DEFAULT_DELIMITER, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * @see CSVReader#read(String, String, String, boolean)
     *
     * @param clazz
     * @param filepath
     * @param charset
     * @param delimiter
     * @return
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final String filepath, final Charset charset, final char delimiter) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, filepath, charset, delimiter, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * @see CSVReader#read(String, String, String, boolean)
     *
     * @param clazz
     * @param inputStream
     * @param charset
     * @param delimiter
     * @return
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final InputStream inputStream, final Charset charset, final char delimiter) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, inputStream, charset, delimiter, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * @see CSVReader#read(String, String, String, boolean)
     *
     * @param clazz
     * @param filepath
     * @param charset
     * @param includeHeader
     * @return
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final String filepath, final Charset charset, final boolean includeHeader) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, filepath, charset, DEFAULT_DELIMITER, includeHeader);
    }

    /**
     * @see CSVReader#read(String, String, String, boolean)
     *
     * @param clazz
     * @param inputStream
     * @param charset
     * @param includeHeader
     * @return
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final InputStream inputStream, final Charset charset, final boolean includeHeader) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, inputStream, charset, DEFAULT_DELIMITER, includeHeader);
    }

    /**
     * CSVを読み込み、その内容を指定したインスタンスとして取得する.
     *
     * @param clazz         CSVの内容1行分を表すクラスのFQDN.
     * @param filepath      CSVファイルのパス.
     * @param charset       CSVファイルの文字コード
     * @param delimiter     CSVの区切り文字.
     * @param includeHeader CSVにヘッダーが含まれるか.
     * @return
     * @throws ClassNotFoundException clazz が見つからない場合に発生.
     * @throws FileNotFoundException  CSVファイルが見つからない場合に発生.
     * @throws IllegalAccessException clazz のインスタンス作成時のエラー.
     * @throws InstantiationException clazz のインスタンス作成時のエラー.
     * @throws IOException            CSVファイルのインスタンス作成、および、CSV読込み中のエラー.
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final String filepath, final Charset charset, final char delimiter, final boolean includeHeader) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException {
        CSVFormat format = getCSVFormat(delimiter, includeHeader);

        File file = new File(filepath);
        List<CSVRecord> list = null;
        try (CSVParser parser = CSVParser.parse(file, charset, format)) {
            list = parser.getRecords();
        }

        ICSVRecords records = getCSVLines(clazz);
        records.addRecords(list);

        return records;
    }

    /**
     * CSVを読み込み、その内容を指定したインスタンスとして取得する.
     *
     * @param clazz         CSVの内容1行分を表すクラスのFQDN.
     * @param inputStream   CSVファイル.
     * @param charset       CSVファイルの文字コード
     * @param delimiter     CSVの区切り文字.
     * @param includeHeader CSVにヘッダーが含まれるか.
     * @return
     * @throws ClassNotFoundException clazz が見つからない場合に発生.
     * @throws FileNotFoundException  CSVファイルが見つからない場合に発生.
     * @throws IllegalAccessException clazz のインスタンス作成時のエラー.
     * @throws InstantiationException clazz のインスタンス作成時のエラー.
     * @throws IOException            CSVファイルのインスタンス作成、および、CSV読込み中のエラー.
     */
    public ICSVRecords read(final Class<ICSVRecords> clazz, final InputStream inputStream, final Charset charset, final char delimiter, final boolean includeHeader) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException {
        CSVFormat format = getCSVFormat(delimiter, includeHeader);

        List<CSVRecord> list = null;
        try (CSVParser parser = CSVParser.parse(inputStream, charset, format)) {
            list = parser.getRecords();
        }

        ICSVRecords records = getCSVLines(clazz);
        records.addRecords(list);

        return records;
    }

    private CSVFormat getCSVFormat(final char delimiter, final boolean includeHeader) {
        CSVFormat format = CSVFormat
            .DEFAULT
            .withIgnoreEmptyLines(true)                         // 空行を無視する
            .withDelimiter(delimiter)
            .withRecordSeparator(DEFAULT_RECORD_SEPARATOR)
            .withIgnoreSurroundingSpaces(true);                 // 値を trim して取得する
        if (includeHeader) {
            format = format.withFirstRecordAsHeader(); // 最初の行をヘッダーとして読み飛ばす
        }
        return format;
    }

    private ICSVRecords getCSVLines(final Class<ICSVRecords> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        ICSVRecords entity = (ICSVRecords) clazz.newInstance();
        return entity;
    }
}
