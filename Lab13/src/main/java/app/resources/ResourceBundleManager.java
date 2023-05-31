package app.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    private static ResourceBundle resourceBundle;

    static {
        loadResourceBundle(Locale.getDefault());
    }

    public static void loadResourceBundle(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("res.Message", locale);
    }

    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}