package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertyStorage {
    protected final Properties properties;

    /**
     * プロパティファイルを読み込み、プロパティを保持するインスタンスを作成する.
     * @param filepath プロパティファイルのパス
     * @param charsetName プロパティファイルの文字コード
     * @throws FileNotFoundException {@see FileInputStream#FileInputStream(String)}
     * @throws IOException {@see FileInputStream#FileInputStream(String)} {@see Properties#load(java.io.InputStream)}
     */
    public PropertyStorage(final String filepath, final String charsetName) throws FileNotFoundException, IOException {
        this.properties = new Properties();
        try(InputStreamReader in = new InputStreamReader(new FileInputStream(filepath), Charset.forName(charsetName))) {
            this.properties.load(in);
        }
    }

    /**
     * プロパティ値を整数値として取得する.
     * 指定されたキーが存在しない場合は、0を返す.
     *
     * @param key
     * @return
     */
    public Integer getPropertyInt(final String key) {
        return this.getPropertyInt(key, 0);
    }


    /**
     * プロパティ値を整数値として取得する.
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public int getPropertyInt(final String key, final int defaultValue) {
        String value = this.getPropertyString(key);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        if (!StringUtils.isNumeric(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    /**
     * プロパティ値を文字列として取得する.
     * 指定されたキーが存在しない場合は、空を返す.
     *
     * @param key
     * @return
     */
    public String getPropertyString(final String key) {
        return this.getPropertyString(key, "");
    }

    /**
     * プロパティ値を文字列として取得する.
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getPropertyString(final String key, final String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }
}
