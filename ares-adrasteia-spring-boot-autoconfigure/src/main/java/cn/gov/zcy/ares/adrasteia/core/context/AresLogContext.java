package cn.gov.zcy.ares.adrasteia.core.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 15:22:09
 */
public class AresLogContext {
    private static final InheritableThreadLocal<Map<String, Object>> VARIABLES = new InheritableThreadLocal<>();

    static {
        Map<String, Object> map = new HashMap<>();
        VARIABLES.set(map);
    }

    public static Map<String, Object> getVariables() {
        return VARIABLES.get();
    }

    public static void putVariable(String key, Object value) {
        VARIABLES.get().put(key, value);
    }

    public static void clear() {
        if (null != VARIABLES.get()) {
            VARIABLES.get().clear();
        }
    }
}
