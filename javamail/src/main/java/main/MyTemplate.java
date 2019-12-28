package main;

import java.util.HashMap;
import java.util.Map;

import common.BaseTemplate;

public final class MyTemplate extends BaseTemplate {
    @Override
    public Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", "‚Ù‚°ŽO˜Y");
        parameters.put("errorCount", new Integer(5));  //SUPPRESS CHECKSTYLE ignore magic number

        return parameters;
    }


}
