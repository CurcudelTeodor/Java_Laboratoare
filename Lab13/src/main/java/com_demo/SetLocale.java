package com_demo;

import app.resources.ResourceBundleManager;

import java.util.Locale;

public class SetLocale {
    public static void execute(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        Locale.setDefault(locale);
        ResourceBundleManager.loadResourceBundle(locale);
        System.out.println(ResourceBundleManager.getMessage("locale.set") + " " + locale);
    }
}

