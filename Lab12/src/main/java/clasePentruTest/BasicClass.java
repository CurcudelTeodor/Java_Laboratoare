package clasePentruTest;

import org.example.Test;

public class BasicClass {

    @Test
    public static void staticTestMethod() {
        System.out.println("Executing staticTestMethod()");
    }

    @Test
    public void nonStaticTestMethod() {
        System.out.println("Executing nonStaticTestMethod()");
    }

    @Test
    public static void staticMethodWithArguments(int number, String text) {
        System.out.println("Executing staticMethodWithArguments(" + number + ", " + text + ")");
    }

    @Test
    public void nonStaticMethodWithArguments(double value) {
        System.out.println("Executing nonStaticMethodWithArguments(" + value + ")");
    }
    @Test
    public void nonStaticMethodWithArgumentsShouldFAIL(boolean value) {
        System.out.println("Executing nonStaticMethodWithArguments(" + value + ")");
    }
    public static void nonTestMethod() {
        System.out.println("Executing nonTestMethod()");
    }
}