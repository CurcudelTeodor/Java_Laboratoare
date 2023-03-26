package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private Catalog catalog;

    public ReportCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setClassForTemplateLoading(ReportCommand.class, "/");
        try {
            Template template = configuration.getTemplate("report.ftl");
            Map<String, Object> data = new HashMap<>();

            data.put("catalogName", catalog.name);
            data.put("documents", catalog.docs);

            File reportFile = new File("report.html");
            FileWriter fileWriter = new FileWriter(reportFile);

            template.process(data, fileWriter);
            fileWriter.close();
            System.out.println("Raportul a fost generat aici:  " + reportFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului template report.ftl: " + e.getMessage());
        } catch (TemplateException e) {
            System.err.println("Eroare la procesarea(citire template + inlocuire variabile ${...} cu valori) --- parsare, evaluare expresii, generare HTML" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Eroare neasteptata/ neanticipata" + e.getMessage());
        }
    }
}































//package org.example;
//
//import java.awt.Desktop;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import freemarker.template.TemplateException;
//import org.example.Catalog;
//import org.example.Command;
//import org.example.Document;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateExceptionHandler;
//import org.example.InvalidCommandException;
//
//public class ReportCommand implements Command {
//
//    private Catalog catalog;
//
//    public ReportCommand(Catalog catalog) {
//        this.catalog = catalog;
//    }
//
//    @Override
//    public void execute() throws InvalidCommandException {
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
//        cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "/");
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//
//        Template template;
//        try {
//            template = cfg.getTemplate("report.ftl");
//        } catch (IOException e) {
//            throw new InvalidCommandException("nu am putut initializa template-ul");
//        }
//
//        Map<String, Object> data = new HashMap<>();
//        List<Document> documents = catalog.docs;
//        data.put("documents", documents);
//
//        File reportFile = new File("report.html");
//
//        try{
//            Writer out = new FileWriter(reportFile);
//            template.process(data, out);
//            out.close();
//        } catch (TemplateException e) {
//            throw new InvalidCommandException("Nu am putut procesa datele");
//        } catch (IOException e) {
//            throw new InvalidCommandException("Nu am putut inchide Writer-ul");
//        }
//
//
//        try {
//            Desktop.getDesktop().open(reportFile);
//        } catch (IOException e) {
//            throw new InvalidCommandException("Nu am putut deschide fisierul html");
//        }
//    }
//}
