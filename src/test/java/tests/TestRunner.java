package tests;

import listeners.TestListener;
import org.testng.TestNG;
import tests.checkout.CheckOutInfoTest;
import tests.home.*;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        // Add test classes to run
        testng.setTestClasses(new Class[]{
                LoginTest.class,
               // FooterTest.class,
                AddToCart.class,
               // MenuTest.class,
                //FiltrationTest.class
               // Navigation.class

                CheckOutInfoTest.class
        });
        // Add custom listener
        testng.addListener(new TestListener());
        // Run tests what is important
        testng.run();

    }
}
