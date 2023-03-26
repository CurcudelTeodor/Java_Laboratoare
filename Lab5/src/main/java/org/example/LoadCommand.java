package org.example;

import java.io.IOException;

public class LoadCommand implements Command{
    private String path;

    public LoadCommand(String path) {
        this.path = path;
    }

    @Override
    public void execute() throws InvalidCommandException {
        try{
            CatalogUtil.load(this.path);
        } catch (InvalidCatalogException e) {
            throw new InvalidCommandException(e);
        } catch (IOException e) {
            throw new InvalidCommandException(e);
        }

    }
}
