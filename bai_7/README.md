# Bai 7 - Refactor sang POM + Data-Driven

## 1. GIT (gia lap commit)

```bash
git add .
git commit -m "before-refactor-naive-test"

# Refactor code sang POM + DDT

git add .
git commit -m "after-refactor-pom-ddt"

# So sanh toan bo thay doi
git diff before-refactor-naive-test..after-refactor-pom-ddt
```

## 2. Chuyen sang POM

Locators da duoc dua het vao page objects:
- `framework.pages.LoginPage`
- `framework.pages.InventoryPage`
- `framework.pages.CartPage`

Test class `tests.LoginRefactorTest` khong dung `driver.findElement(...)`.

## 3. BasePage thay Thread.sleep

Khong su dung `Thread.sleep()`.
Dung explicit wait trong `framework.base.BasePage`:
- `waitAndClick`
- `waitAndType`

## 4. BaseTest thay driver khoi tao truc tiep

Khong khoi tao `new ChromeDriver()` trong test.
Test dung `getDriver()` tu `framework.base.BaseTest`.

## 5. Data-Driven

Khong hardcode username/password trong test.
Du lieu duoc cap qua `@DataProvider` tai `data.LoginDataProvider` va lay tu `config.properties`.

## 6. Test Suite

Chay toan bo test:

```bash
cd bai_7
mvn test
```

Do khong dung `Thread.sleep()`, bo test nhanh va on dinh hon.

## 7. OUTPUT - BEFORE vs AFTER

### BEFORE (Naive)

```java
WebDriver driver = new ChromeDriver();
driver.findElement(By.id("user-name")).sendKeys("standard_user");
driver.findElement(By.id("password")).sendKeys("secret_sauce");
driver.findElement(By.id("login-button")).click();
```

File mau before: `docs/BeforeNaiveLoginTest.java`

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
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }
}
```

## 8. Giai thich ngan

Refactor la viec to chuc lai code de sach va de bao tri hon, nhung khong thay doi hanh vi nghiep vu.

Loi ich:
- Giam duplication
- De maintain
- De mo rong (them test case moi chi can them data)
