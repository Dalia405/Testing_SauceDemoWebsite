package tests;

import listeners.TestListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        TestListenerAdapter tla = new TestListenerAdapter();

        List<String> suites = new ArrayList<>();
        suites.add("testng.xml"); // path to your testng.xml file
       // suites.add("testngMenu.xml");


        testng.setTestSuites(suites);
        testng.addListener(tla);

        // Add custom listener
        testng.addListener(new TestListener());
        // Run tests what is important
        testng.run();

    }
}
