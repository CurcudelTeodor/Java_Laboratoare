package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class MyClassLoader extends URLClassLoader {
    public MyClassLoader() {
        super(new URL[0], ClassLoader.getSystemClassLoader());
    }

    public List<Class<?>> loadClassesFromFolder(String folderPath) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("The specified path is not a valid folder: " + folderPath);
        }
        exploreFolder(folder, classes);
        return classes;
    }

    private void exploreFolder(File folder, List<Class<?>> classes) throws IOException, ClassNotFoundException {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    exploreFolder(file, classes);
                } else if (file.getName().endsWith(".class")) {
                    String className = getClassName(file.getAbsolutePath());
                    Class<?> clazz = loadClass(className);
                    classes.add(clazz);
                }
            }
        }
    }

    private String getClassName(String filePath) {
        //convertim filePath la un nume de clasa fully qualified adica ceva de genul org.example.MyProgram
        //exemplu: E:\path\to\MyClass.class -> path.to.MyClass
        String prefix = "classes\\";
        int startIndex = filePath.indexOf(prefix) + prefix.length();
        int endIndex = filePath.length() - ".class".length();
        String className = filePath.substring(startIndex, endIndex).replace("\\", ".");
        return className;
    }
}

