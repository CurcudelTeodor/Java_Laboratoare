package org.example;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyTestFramework {

    //    public static void main(String[] args) throws Exception {
//        //path-ul catre fisierul .class nici nu conteaza decat ultima parte cu org.example.MyProgram.class
//        String classFilePath = "E:\\Facultate\\Anul 2 Sem 2\\Java\\Java_Laboratoare\\Lab12\\target\\classes\\org.example.MyProgram.class";
//
//        //luam numele clasei
//        String className = extractClassName(classFilePath);
//
//        //incarcam clasa (la runtime pentru ca? idk)
//        Class<?> clazz = Class.forName(className);
//
//        //analizam clasa (numele clasei, pachetului si metodelor)
//        analyzeClass(clazz);
//
//        ///facem testele pentru metodele adnotate cu @Test
//        performTests(clazz);
//    }
//
//    private static String extractClassName(String classFilePath) {
//        //extragem numele clasei din classFilePath
//        String fileName = classFilePath.substring(classFilePath.lastIndexOf("\\") + 1);
//        return fileName.substring(0, fileName.lastIndexOf('.'));
//    }
//
    private static void analyzeClass(Class<?> clazz) {
        System.out.println("Class name: " + clazz.getName());
        System.out.println("Package name: " + clazz.getPackage().getName());
        System.out.println("Modifiers: " + Modifier.toString(clazz.getModifiers()));
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            System.out.println("Superclass: " + superclass.getName());
        } else {
            System.out.println("Superclass: There is no superclass!! (maybe an annotation -> they don't inherit from anybody)");
            System.out.println("[Explanation - Annotation] : Annotation types are a special construct in Java that differ from " +
                    "regular classes.");
            System.out.println("They are not part of the regular class hierarchy and do not have a superclass");
        }

        System.out.println("Implemented interfaces:");
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> iface : interfaces) {
            System.out.println(" - " + iface.getName());
        }

        System.out.println("Fields:");
        Arrays.stream(clazz.getDeclaredFields())
                .forEach(field -> System.out.println(" - " + field));

        System.out.println("Constructors:");
        Arrays.stream(clazz.getDeclaredConstructors())
                .forEach(constructor -> System.out.println(" - " + constructor));

        System.out.println("Methods:");
        Arrays.stream(clazz.getDeclaredMethods())
                .forEach(method -> System.out.println(" - " + method));
    }

    //
    private static void performTests(Class<?> clazz) {
        System.out.println("Running tests...");
        int passed = 0;
        int failed = 0;

        for (Method method : clazz.getDeclaredMethods()) {
            //metoda cu @Test cu parametrii
            if (method.isAnnotationPresent(Test.class) && method.getParameterCount() > 0) {
                try {
                    if (Modifier.isStatic(method.getModifiers())) {
                        //invoc metoda statica cu parametrii
                        Object[] args = generateMockArguments(method.getParameterTypes());
                        method.invoke(null, args);
                    } else {
                        //invoc metoda non-statica cu parametrii pe o instanta
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        Object[] args = generateMockArguments(method.getParameterTypes());
                        method.invoke(instance, args);
                    }
                    System.out.println("Test passed: " + method.getName());
                    passed++;
                } catch (Exception e) {
                    System.out.println("Test failed: " + method.getName());
                    e.printStackTrace();
                    failed++;
                }
            }

            //metoda cu @Test dar fara parametrii
            else if (method.isAnnotationPresent(Test.class) && method.getParameterCount() == 0) {
                try {
                    if (Modifier.isStatic(method.getModifiers())) {
                        //invoc metoda statica fara parametrii
                        method.invoke(null);
                    } else {
                        //invoc metoda non-statica fara parametrii pe o instanta
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        method.invoke(instance);
                    }
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


    private static Object[] generateMockArguments(Class<?>[] parameterTypes) {
        Object[] args = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == int.class) {
                args[i] = i;
            } else if (parameterTypes[i] == double.class) {
                args[i] = 3.14;
            } else {
                args[i] = null;
            }
        }
        return args;
    }

    public static void main(String[] args) {
        String folderPath = "E:\\Facultate\\Anul 2 Sem 2\\Java\\Java_Laboratoare\\Lab12\\target\\classes";
        MyClassLoader classLoader = new MyClassLoader();
        try {
            List<Class<?>> classes = classLoader.loadClassesFromFolder(folderPath);
            for (Class<?> clazz : classes) {
                // Process the class and print its details
                analyzeClass(clazz);
                performTests(clazz);
                System.out.println();
                System.out.println("-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-");
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
