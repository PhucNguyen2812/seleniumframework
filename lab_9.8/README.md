# Lab 9.8 - RetryAnalyzer

## Files

- `src/test/java/framework/utils/RetryAnalyzer.java`
- `src/test/java/framework/utils/RetryListener.java`
- `src/main/java/framework/config/ConfigReader.java`
- `src/test/resources/testng.xml`

## testng.xml config listener

Da dang ky listener trong `testng.xml`:

```xml
<listeners>
    <listener class-name="framework.utils.RetryListener"/>
</listeners>
```

Nho vay, tat ca `@Test` se tu dong dung `RetryAnalyzer`, khong can khai bao `retryAnalyzer` cho tung test.

## Cach chay

- Moi truong dev (mac dinh):

```bash
mvn test
```

- Moi truong staging (retry nhieu hon):

```bash
mvn test -Denv=staging
```

## Vi du log retry

```text
[ConfigReader] Active environment: dev
[FlakyDemoTest] Current attempt: 1
[RetryAnalyzer] Retrying test: shouldPassOnSecondAttempt | attempt 1 / 1
[FlakyDemoTest] Current attempt: 2
```

## Giai thich ngan gon

- Flaky test la test luc pass luc fail du code khong doi, thuong do mang cham, timing, UI race condition.
- Retry giup giam noise cua loi thoang qua de team tap trung vao loi that.
- Trong thuc te, retry giup pipeline on dinh hon, nhung khong duoc lam dung de che loi nghiem trong.
