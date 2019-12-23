package csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter {
    public static final String DEFAULT_SEPARATOR = ",";
    public static final boolean DEFAULT_INCLUDE_HEADER = true;

    public void write(final ICSVLines csvLines, final String filepath) throws IOException {
        write(csvLines, filepath, DEFAULT_SEPARATOR, DEFAULT_INCLUDE_HEADER);
    }

    public void write(final ICSVLines csvLines, final String filepath, final String separator, final boolean includeHeader) throws IOException {
        try(FileWriter fw = new FileWriter(filepath, false)){
            try(PrintWriter pw = new PrintWriter(new BufferedWriter(fw))) {
                if (includeHeader) {
                    pw.println(String.join(separator, csvLines.getHeader()));
                }
                for(ICSVLine line : csvLines) {
                    pw.println(String.join(separator, line.getLine()));
                }
            }
        }
    }
}
