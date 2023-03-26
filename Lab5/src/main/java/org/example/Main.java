package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException, InvalidCommandException {
        Main app = new Main();
        //app.testCreateSave();
        //app.testLoadView();
        app.run();
    }

    private void run() throws IOException, InvalidCatalogException, InvalidCommandException {

        Catalog catalog = new Catalog("MyDocuments");
        var book = new Document("book1","Alchimistul", "e:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\Alchimistul.txt" );
        var article = new Document("article1", "Moara cu noroc", "e:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\Moara cu noroc.txt" );
        var documentPdf = new Document("documentPdf1", "Manual Yala EM", "e:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\user-manual-ys810-ys800.pdf" );
        var pozaDulap = new Document("pozaDulap1", "Poza Dulap","E:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\4.jpg");
        //catalog.add(book);
        //catalog.add(article);

        Command addCommand1 = new AddCommand(catalog, book);
        Command addCommand2 = new AddCommand(catalog, article);
        Command addCommand3 = new AddCommand(catalog, documentPdf);
        Command addCommand4 = new AddCommand(catalog, pozaDulap);

        addCommand1.execute();
        addCommand2.execute();
        addCommand3.execute();
        addCommand4.execute();

        Command listCommand = new ListCommand(catalog);
        listCommand.execute();

        Command saveCommand = new SaveCommand(catalog, "E:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\catalog.json");
        saveCommand.execute();

        Command loadCommand = new LoadCommand("E:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\catalog.json");
        loadCommand.execute();

        //Command view1 = new ViewCommand("E:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\user-manual-ys810-ys800.pdf");
        //view1.execute();
        //Command view2 = new ViewCommand("E:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\4.jpg");
        //view2.execute();

        Command report = new ReportCommand(catalog);
        report.execute();

    }

        private void testCreateSave() throws IOException, FileNotFoundException {
        Catalog catalog = new Catalog("MyDocuments");

        var book = new Document("book1","Alchimistul", "e:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\Alchimistul.txt" );
        var article = new Document("article1", "Moara cu noroc", "e:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\Moara cu noroc.txt" );
        catalog.add(book);
        catalog.add(article);

        CatalogUtil.save(catalog, "E:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\catalog.json");
    }

    private void testLoadView() throws InvalidCatalogException, IOException {
        try{
            Catalog catalog = CatalogUtil.load("E:\\Facultate\\Anul 2 Sem 2\\Java\\carti_lab5\\catalog.json");
            CatalogUtil.view(catalog.findById("article1"));
            System.out.println(catalog);
        } catch(InvalidCatalogException | IOException e){
            System.out.println("Eroare la incarcarea catalogului: " + e.getMessage());
        }
    }

}