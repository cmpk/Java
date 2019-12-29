package csv;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVWriter {
    public static final char DEFAULT_DELIMITER = ',';
    public static final String DEFAULT_RECORD_SEPARATOR = "\r\n";
    public static final boolean DEFAULT_INCLUDE_HEADER = true;

    /**
     * @see CSVWriter#write(ICSVRecords, String, char, boolean)
     *
     * @param csvLines
     * @param filepath
     * @param charset
     * @throws IOException
     */
    public void write(final ICSVRecords csvLines, final String filepath, final Charset charset) throws IOException {
        write(csvLines, filepath, charset, DEFAULT_DELIMITER, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * 指定したインスタンスの内容をCSVに出力する.
     *
     * @param csvRecords    CSVの内容を保持するインスタンス.
     * @param filepath      出力先のCSVファイルパス.
     * @param charset       出力に利用する文字コード
     * @param delimiter     CSVの区切り文字.
     * @param includeHeader CSVにヘッダーを出力するか.
     * @throws IOException CSVの書き込みに失敗した場合
     */
    public void write(final ICSVRecords csvRecords, final String filepath, final Charset charset, final char delimiter, final boolean includeHeader) throws IOException {
        CSVFormat format = CSVFormat
                .DEFAULT
                .withIgnoreEmptyLines(true) // 空行を無視する
                .withDelimiter(delimiter)
                .withRecordSeparator(DEFAULT_RECORD_SEPARATOR)
                .withIgnoreSurroundingSpaces(true); // 値を trim して取得する

        try (OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(filepath, false), charset)) {
            try (CSVPrinter printer = new CSVPrinter(fw, format)) {
                if (includeHeader) {
                    printer.printRecord((Object[]) csvRecords.getHeader());
                }
                for (ICSVRecord line : csvRecords) {
                    printer.printRecord((Object[]) line.getRecord());
                }
            }
        }
    }
}
