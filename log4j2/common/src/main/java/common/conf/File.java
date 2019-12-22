package common.conf;

import org.xml.sax.Attributes;

import lombok.Getter;
import lombok.Setter;

/**
 * log4j 2 File タグに値を表すクラス.
 *
 */
public class File {
    public static final String Q_NAME = "File";
    public static final String NAME = "name";
    public static final String FILE_NAME = "fileName";

    public File(final Attributes attributes) {
        this.name = attributes.getValue(NAME);
        this.fileName = attributes.getValue(FILE_NAME);
    }

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String fileName;
}
