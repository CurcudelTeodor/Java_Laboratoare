package com_demo;

import app.resources.ResourceBundleManager;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Info {
    public static void execute() {
        Locale locale = Locale.getDefault();

        System.out.println(ResourceBundleManager.getMessage("info") + " " + locale.getDisplayName(locale));

        System.out.println(ResourceBundleManager.getMessage("country") + ": " + locale.getDisplayCountry() + " (" + locale.getDisplayCountry(locale) + ")");
        System.out.println(ResourceBundleManager.getMessage("language") + ": " + locale.getDisplayLanguage() + " (" + locale.getDisplayLanguage(locale) + ")");

        Currency currency = Currency.getInstance(locale);
        System.out.println(ResourceBundleManager.getMessage("currency") + ": " + currency.getCurrencyCode() + " (" + currency.getDisplayName(locale) + ")");

        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        System.out.println(ResourceBundleManager.getMessage("week.days") + ": " + String.join(", ", dateFormatSymbols.getWeekdays()));
        System.out.println(ResourceBundleManager.getMessage("months") + ": " + String.join(", ", dateFormatSymbols.getMonths()));

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) dateFormat;
        String currentDate = simpleDateFormat.format(new Date());
        System.out.println(ResourceBundleManager.getMessage("today") + ": " + currentDate + " (" + simpleDateFormat.toPattern() + ")");
    }
}
