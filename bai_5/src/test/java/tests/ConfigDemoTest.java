package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigDemoTest extends BaseTest {

    @Test
    public void shouldLoadConfigByEnvironment() {
        ConfigReader config = ConfigReader.getInstance();
        Assert.assertNotNull(config.getBaseUrl());
        Assert.assertTrue(config.getExplicitWait() > 0);
    }
}
