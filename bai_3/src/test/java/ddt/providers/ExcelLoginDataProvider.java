package ddt.providers;

import ddt.utils.ExcelReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public final class ExcelLoginDataProvider {

    private static final String FILE_PATH = "src/test/resources/testdata/login_data.xlsx";

    private ExcelLoginDataProvider() {
    }

    @DataProvider(name = "excelLoginData")
    public static Object[][] excelLoginData(ITestContext context) {
        Path excelPath = Paths.get(FILE_PATH);
        if (!Files.exists(excelPath)) {
            throw new IllegalStateException("Missing test data file: " + FILE_PATH);
        }

        String runGroup = context.getCurrentXmlTest().getParameter("runGroup");
        if (runGroup == null || runGroup.isBlank()) {
            runGroup = "regression";
        }

        if ("smoke".equalsIgnoreCase(runGroup)) {
            return ExcelReader.getData(FILE_PATH, "SmokeCases");
        }

        List<Object[]> allRows = new ArrayList<>();
        allRows.addAll(Arrays.asList(ExcelReader.getData(FILE_PATH, "SmokeCases")));
        allRows.addAll(Arrays.asList(ExcelReader.getData(FILE_PATH, "NegativeCases")));
        allRows.addAll(Arrays.asList(ExcelReader.getData(FILE_PATH, "BoundaryCases")));
        return allRows.toArray(new Object[0][]);
    }
}
