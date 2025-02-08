package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    // Data provider 1
    @DataProvider(name = "LoginData")
    public String [][] getData() throws IOException {
        String path = ".\\testData\\Opencart_LoginData.xlsx";

        ExcelUtility excelUtility = new ExcelUtility(path);

        int totalRows = excelUtility.getRowCount("Sayfa1");
        int totalCols = excelUtility.getCellCount("Sayfa1", 1);

        String loginData [][] = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                loginData[i-1][j] = excelUtility.getCellData("Sayfa1", i, j);
            }
        }
        return loginData;
    }

    // Data provider 2 --> farklı bir data gerekirse tanımlama yapıp name özelliğine göre kullanabiliriz.

    // Data provider 3 --> farklı bir data gerekirse tanımlama yapıp name özelliğine göre kullanabiliriz.
}
