package common;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import freemarker.template.TemplateException;

public class BaseTemplateTest {
    @Test
    @DisplayName("ê≥èÌån")
    public final void testPositive() {
        BaseTemplate test = new TestTemplate();
        try {
            String str = test.build("src/test/resources", "test.ftl");
            assertEquals("value1=1, value2=2, value3=3", str); //SUPPRESS CHECKSTYLE ignore magic number
        } catch (TemplateException | IOException e) {
            fail(e);
        }
    }

    private static final class TestTemplate extends BaseTemplate {
        @Override
        public Map<String, Object> getParameters() {
            Map<String, Object> map = new HashMap<String, Object>();
            // CHECKSTYLE:OFF
            map.put("value1", 1);
            map.put("value2", 2);
            map.put("value3", 3);
            // CHECKSTYLE:ON
            return map;
        }

    }
}
