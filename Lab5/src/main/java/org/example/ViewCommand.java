package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command{
    public String path;
    public ViewCommand(String path){
        this.path = path;
    }

    @Override
    public void execute() throws InvalidCommandException {
        File file = new File(path);
        if(!file.exists()){
            throw new InvalidCommandException("Fisierul nu exista!");
        }
        if(!Desktop.isDesktopSupported()){
            throw new InvalidCommandException("Desktop nu e suportat");
        }
        try{
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            throw new InvalidCommandException("Nu se poate deschide fisierul");
        }
    }
}
