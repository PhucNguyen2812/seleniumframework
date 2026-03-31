package tests;

import framework.base.BaseTest;
import org.testng.annotations.Test;

public class BasePage_BaseTest_Demo extends BaseTest {

    @Test(description = "Demo that BaseTest provides getDriver()")
    public void demoBasePage_BaseTest() {
        assert getDriver() != null;
        getDriver().get("about:blank");
        assert getDriver().getTitle() != null;
    }
}
