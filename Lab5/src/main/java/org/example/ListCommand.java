package org.example;

public class ListCommand implements Command{

    public Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() throws InvalidCommandException {
        catalog.printDocs();
    }
}
