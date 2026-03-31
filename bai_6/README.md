# Bai 6 - RetryAnalyzer and Flaky Test Simulation

## Muc tieu
- Tao co che retry cho TestNG khi test bi fail.
- Gia lap mot test flaky: fail 2 lan dau, pass lan 3.
- Cau hinh so lan retry theo moi truong:
  - dev: retry.count=2
  - staging: retry.count=0

## Cac file chinh
- src/test/java/framework/retry/RetryAnalyzer.java
- src/test/java/framework/retry/RetryListener.java
- src/test/java/tests/FlakySimulationTest.java
- src/test/resources/testng.xml

## Chay test
Chay suite (bao gom dev va staging):

```bash
mvn test
```

Chay rieng theo moi truong:

```bash
mvn test -Denv=dev
mvn test -Denv=staging
```

## Ky vong ket qua
- Moi truong dev (retry.count=2):
  - flakyTest fail 2 lan dau
  - RetryAnalyzer retry 2 lan
  - flakyTest pass o lan thu 3

- Moi truong staging (retry.count=0):
  - flakyTest fail ngay lan dau
  - khong retry

## Luu y
- Khong dung Thread.sleep.
- Khong truy cap element trong test (test nay chi mo phong flaky logic).
- Retry duoc gan tu dong qua RetryListener.
