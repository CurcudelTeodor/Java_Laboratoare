package org.example;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti calea catre fisierul .class");
        String classFilePath = scanner.nextLine().trim();
        scanner.close();

        try {
            Class<?> clazz = Class.forName(getClassFromFilePath(classFilePath));

            System.out.println("Pachet: " + clazz.getPackage().getName());

            System.out.println("Clasa: " + clazz.getSimpleName());

            System.out.println("\n Metodele:");

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {

                System.out.println(getMethodPrototype(method));
                if (method.isAnnotationPresent(Test.class) && Modifier.isStatic(method.getModifiers())) {

                    System.out.println("\nTest: " + method.getName());
                    method.invoke(null);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Nu am gasit clasa " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Eroare " + e.getMessage());
        }
    }

    private static String getMethodPrototype(Method method) {
        StringBuilder prototype = new StringBuilder();
        prototype.append(Modifier.toString(method.getModifiers()));
        prototype.append(" ");

        prototype.append(method.getReturnType().getSimpleName());
        prototype.append(" ");

        prototype.append(method.getName());
        prototype.append("(");

        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            prototype.append(parameterTypes[i].getSimpleName());
            if (i < parameterTypes.length - 1) {
                prototype.append(", ");
            }
        }

        prototype.append(")");

        return prototype.toString();
    }

    private static String getClassFromFilePath(String filePath) {
        String[] parts = filePath.split("\\\\");
        String fileName = parts[parts.length - 1];

        String className = fileName.substring(0, fileName.lastIndexOf('.'));
        String packagePath = String.join(".", Arrays.copyOfRange(parts, parts.length - 3, parts.length - 1));

        return packagePath + "." + className;
    }

}

