package app;

import app.resources.ResourceBundleManager;
import com_demo.DisplayLocales;
import com_demo.Info;
import com_demo.SetLocale;

import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.print(ResourceBundleManager.getMessage("prompt"));
            command = scanner.nextLine();

            switch (command) {
                case "display-locales":
                    DisplayLocales.execute();
                    break;
                case "set-locale":
                    System.out.print(ResourceBundleManager.getMessage("locales"));
                    String languageTag = scanner.nextLine();
                    SetLocale.execute(languageTag);
                    break;
                case "info":
                    Info.execute();
                    break;
                case "exit":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println(ResourceBundleManager.getMessage("invalid"));
            }

            System.out.println();
        } while (!command.equals("exit"));

        scanner.close();
    }
}