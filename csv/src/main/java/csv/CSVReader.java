package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public List<CSVEntity> read(final Class<CSVEntity> clazz, final String filepath, final String separator) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
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
    public List<CSVEntity> read(final Class<CSVEntity> clazz, final String filepath, final boolean includeHeader) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
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
    public List<CSVEntity> read(final Class<CSVEntity> clazz, final String filepath) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
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
    public List<CSVEntity> read(final Class<CSVEntity> clazz, final String filepath, final String separator, final boolean includeHeader) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<CSVEntity> list = new ArrayList<CSVEntity>();

        File file = new File(filepath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if (includeHeader) {
                br.readLine(); // skip header
            }

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator, 0);

                CSVEntity entity = getCSVEntity(clazz, data);
                list.add(entity);
            }
        }
        System.out.println(this.getClass().getName() + " : csv has " + list.size() + " lines as data.");

        return list;
    }

    private CSVEntity getCSVEntity(final Class<CSVEntity> clazz, final String[] data) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        CSVEntity entity = (CSVEntity) clazz.newInstance();
        entity.setData(data);
        return entity;
    }
}
