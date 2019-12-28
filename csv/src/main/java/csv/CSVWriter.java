package csv;

import java.io.FileWriter;
import java.io.IOException;

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
     * @throws IOException
     */
    public void write(final ICSVRecords csvLines, final String filepath) throws IOException {
        write(csvLines, filepath, DEFAULT_DELIMITER, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * 指定したインスタンスの内容をCSVに出力する.
     *
     * @param csvRecords    CSVの内容を保持するインスタンス.
     * @param filepath      出力先のCSVファイルパス.
     * @param delimiter     CSVの区切り文字.
     * @param includeHeader CSVにヘッダーを出力するか.
     * @throws IOException CSVの書き込みに失敗した場合
     */
    public void write(final ICSVRecords csvRecords, final String filepath, final char delimiter, final boolean includeHeader) throws IOException {
        CSVFormat format = CSVFormat
                .DEFAULT
                .withIgnoreEmptyLines(true) // 空行を無視する
                .withDelimiter(delimiter)
                .withRecordSeparator(DEFAULT_RECORD_SEPARATOR)
                .withIgnoreSurroundingSpaces(true); // 値を trim して取得する

        try (FileWriter fw = new FileWriter(filepath, false)) {
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
