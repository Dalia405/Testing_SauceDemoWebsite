package tests;

import org.testng.annotations.AfterSuite;
import tests.base.BaseTest;

public class TestCleanup extends BaseTest {
    @AfterSuite
    public void afterSuite() {
        BaseTest.quitDriver();
        System.out.println("✅ Browser closed successfully after suite.");
    }
}
