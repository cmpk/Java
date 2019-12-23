package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static final String DEFAULT_SEPARATOR = ",";
    public static final boolean DEFAULT_INCLUDE_HEADER = true;

    /**
     * {@see CSVReader#read(String, String, String, boolean)}.
     *
     * @param classpath
     * @param filepath
     * @param separator
     * @return
     * @throws FileNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ICSVLines read(final Class<ICSVLines> clazz, final String filepath, final String separator) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, filepath, separator, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * {@see CSVReader#read(String, String, String, boolean)}.
     *
     * @param classpath
     * @param filepath
     * @param includeHeader
     * @return
     * @throws FileNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ICSVLines read(final Class<ICSVLines> clazz, final String filepath, final boolean includeHeader) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, filepath, DEFAULT_SEPARATOR, includeHeader);
    }

    /**
     * {@see CSVReader#read(String, String, String, boolean)}.
     *
     * @param classpath
     * @param filepath
     * @return
     * @throws FileNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ICSVLines read(final Class<ICSVLines> clazz, final String filepath) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        return read(clazz, filepath, DEFAULT_SEPARATOR, DEFAULT_INCLUDE_HEADER);
    }

    /**
     * CSVを読み込み内容をインスタンス化する.
     *
     * @param classpath     CSVの内容1行分を表すクラスのFQDN. {@see CSVEntity}でなければならない.
     * @param filepath      CSVファイルのパス.
     * @param separator     CSVの区切り文字.
     * @param includeHeader CSVにヘッダーが含まれるか.
     * @return
     * @throws FileNotFoundException  CSVファイルが見つからない場合に発生.
     *                                詳細は{@see FileNotFoundException}を参照のこと.
     * @throws IOException            CSVファイルのインスタンス作成、および、CSV読込み中のエラー.
     *                                詳細は{@see IOException}を参照のこと.
     * @throws InstantiationException classpath のインスタンス作成時のエラー.
     *                                詳細は{@see InstantiationException}を参照のこと.
     * @throws IllegalAccessException classpath のインスタンス作成時のエラー.
     *                                詳細は{@see IllegalAccessException}を参照のこと.
     * @throws ClassNotFoundException classpath が見つからない場合に発生.
     *                                詳細は{@see ClassNotFoundException}を参照のこと.
     */
    public ICSVLines read(final Class<ICSVLines> clazz, final String filepath, final String separator, final boolean includeHeader) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ICSVLines lines = getCSVLines(clazz);

        File file = new File(filepath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if (includeHeader) {
                br.readLine(); // skip header
            }

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(separator, 0);


                lines.addLine(values);
            }
        }
        System.out.println(this.getClass().getName() + " : csv has " + lines.size() + " lines as data.");

        return lines;
    }

    private ICSVLines getCSVLines(final Class<ICSVLines> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        ICSVLines entity = (ICSVLines) clazz.newInstance();
        return entity;
    }
}
