package common.conf;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.Getter;

/**
 * log4j 2 の設定ファイルの値を取得するクラス.
 */
public final class Log4j2Attributes extends DefaultHandler {
    public static Log4j2Attributes newInstance(final String filepath) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Log4j2Attributes handler = new Log4j2Attributes();
        parser.parse(Paths.get(filepath).toFile(), handler);

        return handler;
    }

    @Getter
    private final List<File> fileList;

    private Log4j2Attributes() {
        this.fileList = new ArrayList<File>();
    }

    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        if (File.Q_NAME.equals(qName)) {
            File file = new File(attributes);
            this.fileList.add(file);
        }
    }
}
