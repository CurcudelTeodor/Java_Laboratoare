package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();

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