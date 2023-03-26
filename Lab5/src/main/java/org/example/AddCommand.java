package org.example;

public class AddCommand implements Command{

    public Catalog catalog;
    public Document document;

    public AddCommand(Catalog catalog, Document document) {
        this.catalog = catalog;
        this.document = document;
    }

    @Override
    public void execute() throws InvalidCommandException {
        this.catalog.add(document);
    }
}
