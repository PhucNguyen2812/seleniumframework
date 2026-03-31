package tests;

import java.util.concurrent.atomic.AtomicInteger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlakyDemoTest {

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    @Test
    public void shouldPassOnSecondAttempt() {
        int attempt = COUNTER.incrementAndGet();
        System.out.println("[FlakyDemoTest] Current attempt: " + attempt);

        // Simulate a flaky test: first run fails, second run passes.
        Assert.assertTrue(attempt >= 2, "Simulated flaky failure on first attempt");
    }
}
