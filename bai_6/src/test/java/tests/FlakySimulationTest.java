package tests;

import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlakySimulationTest extends BaseTest {

    private static int attemptCounter = 0;

    @Test(description = "Fail 2 lan dau, pass lan 3 de demo RetryAnalyzer")
    public void flakyTest() {
        attemptCounter++;
        System.out.println("Run attempt = " + attemptCounter);

        if (attemptCounter < 3) {
            Assert.fail("Gia lap flaky fail tai lan " + attemptCounter);
        }

        Assert.assertTrue(true, "Test pass o lan thu 3");
    }
}
