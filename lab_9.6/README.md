# Lab 9.6 - Data-Driven Testing (DDT)

Lab nay cung cap 3 cach DDT voi Selenium Java + TestNG:

1. DataProvider thuan Java
2. DataProvider doc Excel (.xlsx) bang Apache POI
3. DataProvider doc JSON bang Jackson

## Cau truc chinh

- `src/test/java/ddt/providers/LoginDataProviders.java`
- `src/test/java/ddt/utils/ExcelReader.java`
- `src/test/java/ddt/utils/JsonReader.java`
- `src/test/java/ddt/model/UserData.java`
- `src/test/java/ddt/tests/JavaDataProviderLoginTest.java`
- `src/test/java/ddt/tests/ExcelDataDrivenLoginTest.java`
- `src/test/java/ddt/tests/JsonDataDrivenLoginTest.java`

## Chay test

```bash
mvn test
```

Mac dinh `mvn test` se chay suite tong song song:
- `src/test/resources/testng-ddt-parallel.xml`

Chay tung suite rieng:

```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-java-ddt.xml
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-excel-ddt.xml
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-json-ddt.xml
```

Chay nhanh de demo tren lop (1-2 case):

```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-smoke.xml
```

## Tai sao phai tach data khoi code?

- De thay doi test case nhanh ma khong sua source Java.
- De nguoi khong code (QA/BA) van cap nhat du lieu duoc.
- De tranh hardcode du lieu nhay cam trong source.
- De de audit: biet du lieu nao duoc them/sua.

## Khi nao dung cach nao?

- DataProvider thuan Java:
  - Dung cho bo du lieu nho, on dinh, it thay doi.

- Excel:
  - Dung khi team QA quen lam viec tren bang tinh.
  - Hop khi du lieu test dang bang, de xem va sua hang loat.

- JSON:
  - Dung khi du lieu co cau truc phuc tap (object, nested field).
  - Hop voi API/UI can bo data mo rong theo schema.

## Uu nhuoc diem

- DataProvider Java:
  - Uu: don gian, nhanh, de debug.
  - Nhuoc: doi data phai sua code va build lai.

- Excel:
  - Uu: de nhap/sua du lieu cho non-dev.
  - Nhuoc: can thu vien doc file, can quan ly format file.

- JSON:
  - Uu: cau truc ro rang, de version control, mo rong tot.
  - Nhuoc: can POJO/map va parser.
