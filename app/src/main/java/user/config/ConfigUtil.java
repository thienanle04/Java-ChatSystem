package user.config;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public class ConfigUtil {
    private JSONObject config;

    // Constructor to load config.json from the same directory as ConfigUtil
    public ConfigUtil() throws Exception {
        String configFilePath = "src/main/java/user/config/config.json"; // Path to config.json relative to the
        // project root
        File configFile = new File(configFilePath);

        if (!configFile.exists()) {
            throw new IllegalArgumentException("File not found: " + configFilePath);
        }

        try (FileInputStream fileInputStream = new FileInputStream(configFile)) {
            // Read content from the FileInputStream
            String content = new String(fileInputStream.readAllBytes(), StandardCharsets.UTF_8);
            this.config = new JSONObject(content);
        }
    }

    // Method to get a string value
    public String getString(String key) {
        return config.getString(key);
    }

    // Method to get a nested JSON object
    public JSONObject getNestedObject(String key) {
        return config.getJSONObject(key);
    }
}
