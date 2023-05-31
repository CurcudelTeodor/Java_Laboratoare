package com_demo;

import app.resources.ResourceBundleManager;

import java.util.Locale;


public class DisplayLocales {
    public static void execute() {
        Locale[] availableLocales = Locale.getAvailableLocales();
        System.out.println(ResourceBundleManager.getMessage("locales"));
        for (Locale locale : availableLocales) {
            System.out.println(locale);
        }
    }
}
