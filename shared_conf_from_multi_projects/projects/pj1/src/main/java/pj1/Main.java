package pj1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.Library;

public class Main {
    public static final String CONF_FILE = "../../conf/common.properties";
    public static final String CONF_CHARSET = "SJIS";
    public static final String LOG4J_CONF = "../../conf/log4j2.xml";

    public static void main(String[] args) {
        System.out.println("package: " + Main.class.getPackage().getName());
        Library.outputCurrentDirectory();

        Properties properties = new Properties();
        try(InputStreamReader in = new InputStreamReader(new FileInputStream(CONF_FILE), Charset.forName(CONF_CHARSET))) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(properties.getProperty("key"));

        System.setProperty("log4j.configurationFile", LOG4J_CONF);
        System.setProperty("log4j.configuratorClass", "org.apache.log4j.xml.DOMConfigurator");
        Logger logger = LogManager.getLogger();
        logger.info("テスト");
    }

}
