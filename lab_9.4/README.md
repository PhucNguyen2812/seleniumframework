# Lab 9.4 - Page Object Model (POM)

Project nay mo phong cau truc POM theo huong du an thuc te voi `LoginPage` va `InventoryPage`.

## Cau truc

- `src/main/java/framework/base/BasePage.java`
- `src/main/java/framework/pages/LoginPage.java`
- `src/main/java/framework/pages/InventoryPage.java`
- `src/main/java/framework/pages/CartPage.java`
- `src/main/java/framework/config/ConfigReader.java`
- `src/main/resources/config.properties`
- `src/test/java/framework/base/BaseTest.java`
- `src/test/java/tests/LoginTest.java`

## Cach chay

```bash
mvn test
```

## Giai thich ngan gon

- Vi sao dung `@FindBy` thay cho `findElement`:
  - Locator duoc khai bao tap trung trong page class, de sua va de bao tri.
  - Code doc de hon va giam lap lai.

- Fluent Interface la gi:
  - Method tra ve `Page Object` tiep theo (hoac chinh no) de chain hanh dong theo luong nguoi dung.
  - Vi du: `login(...)->InventoryPage`, `addFirstItemToCart()->InventoryPage`.

- Vi sao Page Object khong chua assert:
  - Page Object chi mo ta thao tac UI.
  - Assertion la trach nhiem cua test class de tach ro "hanh vi" va "kiem chung".
