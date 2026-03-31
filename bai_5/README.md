# bai_5 - ConfigReader + da moi truong

## Output files

- src/main/resources/config-dev.properties
- src/main/resources/config-staging.properties
- src/main/java/framework/config/ConfigReader.java
- src/test/java/framework/base/BaseTest.java

## Demo command

- `mvn test` -> dung dev
- `mvn test -Denv=staging` -> dung staging

## Vi sao can nhieu moi truong?

- Cung bo test can chay tren dev va staging de xac nhan he thong hoat dong on dinh truoc khi len production.

## Vi sao khong hardcode config?

- Hardcode URL/timeout lam kho tai su dung, moi doi moi truong lai sua code.

## Loi ich cua ConfigReader

- Tap trung config mot cho, de bao tri, de mo rong, va de CI/CD truyen tham so moi truong.
