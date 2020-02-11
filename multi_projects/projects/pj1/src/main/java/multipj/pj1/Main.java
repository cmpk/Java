package multipj.pj1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import multipj.common.Common;
import multipj.pj2.PJ2;

public class Main {
    public static final String CONF_FILE = "../../conf/common.properties";
    public static final String CONF_CHARSET = "SJIS";

    public static void main(String[] args) {
        useDependency();
        readProperties();
    }

    /**
     * 依存プロジェクトを使用する実験.
     */
    private static void useDependency() {
        Common common = new Common();
        common.useCommonsLang3();

        PJ2 pj2 = new PJ2();
        pj2.useCommonsIO();
    }

    /**
     * プロジェクトの外にあるファイルを読み込む実験.
     */
    private static void readProperties() {
        Properties properties = new Properties();
        try(InputStreamReader in = new InputStreamReader(new FileInputStream(CONF_FILE), Charset.forName(CONF_CHARSET))) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(Common.getClassName() + "#" + Common.getMethodName() + ": key = " + properties.getProperty("key"));
    }

}
