# bai_3 - Data-Driven Testing voi Excel

## File output chinh

- `src/test/java/ddt/utils/ExcelReader.java`
- `src/test/java/tests/LoginDDTTest.java`
- `src/test/resources/testng.xml`
- `src/test/resources/testdata/login_data.xlsx` (duoc tao tu dong khi chay test neu chua ton tai)

## Cau truc file Excel

### Sheet 1: SmokeCases (>= 3 dong)
- username | password | expected_url | description

### Sheet 2: NegativeCases (>= 5 dong)
- username | password | expected_error | description

### Sheet 3: BoundaryCases (>= 4 dong)
- username | password | expected_error | description
- Co edge case: chuoi dai, ky tu dac biet, SQL injection, formula cell.

## Cach chay

- Chay ca smoke + regression:

```bash
mvn test
```

- Chay rieng smoke:

```bash
mvn test -Dgroups=smoke
```

- Chay rieng regression:

```bash
mvn test -Dgroups=regression
```

## Vi du report hien thi description

`LoginDDTTest` implement `ITest` va set ten case theo cot `description` tu Excel, vi vay report se hien thi ten meaningful thay vi `testLoginFromExcel[0]`.

## Giai thich ngan

- Vi sao dung Excel thay hardcode: them/sua test case khong can sua Java.
- Vi sao QA dung duoc Excel: QA khong can code van cap nhat du lieu test.
- Uu diem DDT: mo rong nhanh, giam lap code test, de bao tri khi so luong case tang.
