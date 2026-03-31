package ddt.utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelReader {

    private ExcelReader() {
    }

    // Read all rows except header and convert them to Object[][].
    public static Object[][] getData(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Cannot find sheet: " + sheetName);
            }

            int lastRow = sheet.getLastRowNum();
            int lastCol = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[lastRow][lastCol];

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int r = 1; r <= lastRow; r++) {
                Row row = sheet.getRow(r);
                for (int c = 0; c < lastCol; c++) {
                    Cell cell = row == null ? null : row.getCell(c);
                    data[r - 1][c] = getCellValue(cell, evaluator);
                }
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + filePath, e);
        }
    }

    // Handle STRING, NUMERIC, BOOLEAN, and FORMULA cell types.
    private static String getCellValue(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                double number = cell.getNumericCellValue();
                long whole = (long) number;
                return number == whole ? String.valueOf(whole) : String.valueOf(number);
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return getFormulaValue(cell, evaluator);
            case BLANK:
            default:
                return "";
        }
    }

    private static String getFormulaValue(Cell cell, FormulaEvaluator evaluator) {
        CellType evaluatedType = evaluator.evaluateFormulaCell(cell);
        switch (evaluatedType) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                double number = cell.getNumericCellValue();
                long whole = (long) number;
                return number == whole ? String.valueOf(whole) : String.valueOf(number);
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
