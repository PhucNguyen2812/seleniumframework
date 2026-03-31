# bai_4 - Data-Driven Testing voi JSON + Java Faker

## Output files

- src/test/resources/testdata/users.json
- src/test/java/ddt/model/UserData.java
- src/test/java/ddt/utils/JsonReader.java
- src/test/java/ddt/factory/TestDataFactory.java
- src/test/java/tests/UserLoginTest.java
- src/test/java/tests/CheckoutTest.java

## Cach chay

mvn test

## Chung minh random data

Chay 2 lan lien tiep:
1. mvn -Dtest=tests.CheckoutTest test
2. mvn -Dtest=tests.CheckoutTest test

Gia tri FirstName, LastName, PostalCode, Email tren console se khac nhau giua 2 lan.

## Giai thich ngan

- Vi sao dung JSON thay vi Excel:
  JSON phu hop du lieu dang object, de version control, de parse tu code.

- Khi nao dung Faker:
  Dung khi can tao du lieu moi, da dang, khong lap lai de test input.

- Loi ich random data:
  Tang do bao phu testcase, phat hien loi bien/validate ma data co dinh co the bo sot.
