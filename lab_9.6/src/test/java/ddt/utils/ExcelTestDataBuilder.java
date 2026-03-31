package ddt.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelTestDataBuilder {

    private ExcelTestDataBuilder() {
    }

    // Create a sample .xlsx file once so DataProvider can read real Excel data.
    public static void ensureFile(String filePath) {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            return;
        }

        try {
            Files.createDirectories(path.getParent());
            try (Workbook workbook = new XSSFWorkbook();
                    FileOutputStream fos = new FileOutputStream(filePath)) {

                Sheet sheet = workbook.createSheet("LoginCases");
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("username");
                header.createCell(1).setCellValue("password");
                header.createCell(2).setCellValue("expectedResult");
                header.createCell(3).setCellValue("description");

                Row row1 = sheet.createRow(1);
                row1.createCell(0).setCellValue("standard_user");
                row1.createCell(1).setCellValue("secret_sauce");
                row1.createCell(2).setCellValue("SUCCESS");
                row1.createCell(3).setCellValue("valid_login");

                Row row2 = sheet.createRow(2);
                row2.createCell(0).setCellValue("locked_out_user");
                row2.createCell(1).setCellValue("secret_sauce");
                row2.createCell(2).setCellValue("FAIL");
                row2.createCell(3).setCellValue("locked_user");

                Row row3 = sheet.createRow(3);
                row3.createCell(0).setCellValue("standard_user");
                row3.createCell(1).setCellValue("wrongpass");
                row3.createCell(2).setCellValue("FAIL");
                row3.createCell(3).setCellValue("wrong_password");

                workbook.write(fos);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot create test Excel file: " + filePath, e);
        }
    }
}
