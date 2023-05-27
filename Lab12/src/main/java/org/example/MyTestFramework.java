package org.example;

import java.lang.reflect.*;
public class MyTestFramework {

    public static void main(String[] args) throws Exception {
        //path-ul catre fisierul .class nici nu conteaza decat ultima parte cu org.example.MyProgram.class
        String classFilePath = "E:\\Facultate\\Anul 2 Sem 2\\Java\\Java_Laboratoare\\Lab12\\target\\classes\\org.example.MyProgram.class";

        //luam numele clasei
        String className = extractClassName(classFilePath);

        //incarcam clasa (la runtime pentru ca? idk)
        Class<?> clazz = Class.forName(className);

        //analizam clasa (numele clasei, pachetului si metodelor)
        analyzeClass(clazz);

        ///facem testele pentru metodele adnotate cu @Test
        performTests(clazz);
    }

    private static String extractClassName(String classFilePath) {
        //extragem numele clasei din classFilePath
        String fileName = classFilePath.substring(classFilePath.lastIndexOf("\\") + 1);
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    private static void analyzeClass(Class<?> clazz) {
        System.out.println("Class name: " + clazz.getName());
        System.out.println("Package name: " + clazz.getPackage().getName());
        System.out.println("Methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(" - " + method.toString());
        }
    }

    private static void performTests(Class<?> clazz) {
        System.out.println("Running tests...");
        int passed = 0;
        int failed = 0;
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class) && method.getParameterCount() == 0) {
                try {
                    method.invoke(null);
                    System.out.println("Test passed: " + method.getName());
                    passed++;
                } catch (Exception e) {
                    System.out.println("Test failed: " + method.getName());
                    failed++;
                }
            }
        }
        System.out.println("Test summary: Passed=" + passed + ", Failed=" + failed);
    }
}