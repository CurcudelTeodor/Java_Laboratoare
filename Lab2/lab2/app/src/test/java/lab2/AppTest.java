/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lab2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
       Lab2p classUnderTest = newLab2p();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}