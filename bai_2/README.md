# Bai 2 - Page Object cho saucedemo.com

## Files chinh

- `src/main/java/framework/pages/LoginPage.java`
- `src/main/java/framework/pages/InventoryPage.java`
- `src/main/java/framework/pages/CartPage.java`
- `src/test/java/tests/LoginTest.java`
- `src/test/java/tests/CartTest.java`

## Chay test

```bash
mvn test
```

## Giai thich ngan

- Tai sao test khong duoc dung findElement:
  - Test chi nen mo ta nghiep vu va assert.
  - Locator nam o Page Object de de bao tri khi UI doi.

- Fluent Interface la gi:
  - Method tra ve Page Object tiep theo de chain hanh dong.
  - Vi du: `loginPage.login(...).addFirstItemToCart().goToCart()`.

- Vi sao Page Object khong chua Assert:
  - Page Object chi gom thao tac UI.
  - Assert la trach nhiem cua test de tach ro hanh vi va kiem chung.
