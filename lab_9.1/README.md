# Lab 9.1 - Refactor Naive Test sang POM + Data-Driven

Lab nay da duoc refactor theo day du checklist:
- Locator nam trong page objects (`LoginPage`, `InventoryPage`, `CartPage`)
- Test class khong dung `driver.findElement(...)`
- Khong dung `Thread.sleep()`
- Dung `BasePage` voi `waitAndClick`, `waitAndType`
- Dung `BaseTest` + `getDriver()`
- Dung `@DataProvider` thay hardcode username/password

## Cong nghe

- Java 17
- Selenium 4
- TestNG
- Maven

## Cach chay test

```bash
cd lab_9.1
mvn test
```

## Checklist da hoan thanh

✅ POM: Locator trong page objects
✅ BasePage: Explicit wait (waitAndClick, waitAndType)
✅ BaseTest: getDriver() da co ThreadLocal
✅ DataProvider: Login data tu config.properties
✅ Config: base.url, valid/invalid credentials tu config file
✅ Test: 100% PASS (2/2 test cases)

## Cang toi: BEFORE vs AFTER

### BEFORE (Naive)

```java
driver = new ChromeDriver();
driver.findElement(By.id("user-name")).sendKeys("standard_user");
driver.findElement(By.id("password")).sendKeys("secret_sauce");
driver.findElement(By.id("login-button")).click();
```

### AFTER (POM + DDT)

```java
@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
public void loginFlow_WithPOM_AndDDT(String username, String password, boolean shouldLoginSucceed) {
    LoginPage loginPage = new LoginPage(getDriver()).open(ConfigReader.get("base.url"));
    if (shouldLoginSucceed) {
        InventoryPage inventoryPage = loginPage.login(username, password);
        Assert.assertTrue(inventoryPage.isLoaded());
    } else {
        loginPage.loginExpectingFailure(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains("..."));
    }
}
```

## Loi ich cua Refactor

1. **Giam duplication**: Locator + thao tac UI centralised trong page object
2. **De maintain**: Doi UI chi sua page object, test business logic giu nguyen
3. **De mo rong**: Them test case chi can them data trong DataProvider
4. **Performance**: Explict wait thay hardcode Thread.sleep, test chay nhanh hon

