package framework.utils;

import framework.config.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        int maxRetry = ConfigReader.getInstance().getRetryCount();

        if (retryCount < maxRetry) {
            retryCount++;
            System.out.println("[RetryAnalyzer] Retrying test: " + result.getName()
                    + " | attempt " + retryCount + " / " + maxRetry);
            return true;
        }

        return false;
    }
}
