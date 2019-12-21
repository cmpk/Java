package common;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *
 * テンプレート共通実装.
 *
 */
public abstract class BaseTemplate {
    protected final Configuration conf;

    public BaseTemplate() {
        this.conf = new Configuration(Configuration.VERSION_2_3_29);
    }

    /**
     * テンプレートファイルに値をセットした文字列を構築する.
     *
     * @param directory
     * @param filename
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    protected String build(final String directory, final String filename) throws TemplateException, IOException {
        this.conf.setDirectoryForTemplateLoading(new File(directory));
        Template template = this.conf.getTemplate(filename);

        Map<String, Object> parameters = getParameters();

        Writer out = new StringWriter();
        template.process(parameters, out);

        return out.toString();
    }

    public abstract String build();
    public abstract Map<String, Object> getParameters();
}
