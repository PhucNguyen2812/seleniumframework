# Bai 1 - BasePage va BaseTest

## Files chinh

- `src/main/java/framework/base/BasePage.java`
- `src/test/java/framework/base/BaseTest.java`
- `src/main/java/framework/utils/ScreenshotUtil.java`
- `src/main/java/framework/driver/DriverFactory.java`

## Chay voi testng song song

`src/test/resources/testng.xml` dang de:

- `parallel="methods"`
- `thread-count="3"`

## Giai thich ngan

- BasePage de tap trung cac thao tac UI dung chung (click/type/wait/scroll), giup page class gon va de bao tri.
- Explicit Wait tot hon Thread.sleep vi cho den dung dieu kien can thiet, test on dinh va nhanh hon.
- ThreadLocal giu moi WebDriver rieng cho tung thread, tranh de test song song ghi de nhau.
- Screenshot khi fail giup debug nhanh vi co bang chung UI tai thoi diem loi.
