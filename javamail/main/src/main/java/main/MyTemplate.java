package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import common.BaseTemplate;
import freemarker.template.TemplateException;

public class MyTemplate extends BaseTemplate {

    @Override
    public String build() {
        String str = "";
        try {
            str = super.build("./conf", "body.ftl");
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    @Override
    public Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", "‚Ù‚°ŽO˜Y");
        parameters.put("errorCount", new Integer(5));

        return parameters;
    }


}
