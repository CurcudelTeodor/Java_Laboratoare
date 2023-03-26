package org.example;

import java.io.IOException;

public class SaveCommand implements Command{
    public Catalog catalog;
    public String path;

    public SaveCommand(Catalog catalog, String path) {
        this.catalog = catalog;
        this.path = path;
    }

    @Override
    public void execute() throws InvalidCommandException {
        try{
            CatalogUtil.save(catalog,path);
        } catch (IOException e) {
            throw new InvalidCommandException(e);
        }
    }
}
