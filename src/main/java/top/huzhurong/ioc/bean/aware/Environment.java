package top.huzhurong.ioc.bean.aware;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author luobo.cs@raycloud.com
 * @since 2018/9/17
 */
@Slf4j
public class Environment {
    private static Map<String, Object> context = new HashMap<>();

    static {
        try {
            InputStream resource = Environment.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(resource);
            Set<String> set = properties.stringPropertyNames();
            set.forEach(key -> context.put(key, properties.getProperty(key)));
            String profile = (String) context.get("profiles.active");
            if (profile != null && profile.length() != 0) {
                Environment.class.getClassLoader().getResourceAsStream("application-" + profile + ".properties");
                Properties pp = new Properties();
                pp.stringPropertyNames().forEach(key -> context.put(key, properties.getProperty(key)));
            }
        } catch (Exception e) {
            log.error("load application.properties file occur exception : " + e);
        }
    }

    public String getString(String key) {
        return (String) Environment.context.get(key);
    }

    public Object getStringOrDef(String key, Object defualt) {
        return Environment.context.getOrDefault(key, defualt);
    }


    public Integer getInteger(String key) {
        return (Integer) Environment.context.get(key);
    }
}