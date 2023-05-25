package org.example;

import org.junit.Test;

public class MyProgram {

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int add(int a, int b) {
        return a + b;
    }

    @Test
    public static void testSubtract() {
        int result = subtract(100, 77);
        assert result == 23;
        System.out.println("test scadere pass, esti SMART");
    }

    @Test
    public static void testAdd() {
        int result = add(10, 29);
        assert result == 39;
        System.out.println("test adunare pass, esti smart");
    }
}
