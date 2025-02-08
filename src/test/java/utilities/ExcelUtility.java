package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
    public String path;
    public FileInputStream fileInputStream;
    public FileOutputStream fileOutputStream;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle cellStyle;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInputStream.close();
        return rowCount-1; //İlk satırımız başlıklar olduğu için -1 değerini girdik.
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInputStream.close();
        return cellCount;
    }

    public String getCellData(String sheetName,int rownum,int colnum) throws IOException
    {
        fileInputStream=new FileInputStream(path);
        workbook=new XSSFWorkbook(fileInputStream);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try{
            data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
        }
        catch(Exception e)
        {
            data="";
        }
        workbook.close();
        fileInputStream.close();
        return data;
    }

    public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
    {
        File xlfile=new File(path);
        if(!xlfile.exists())    // If file not exists then create new file
        {
            workbook=new XSSFWorkbook();
            fileOutputStream=new FileOutputStream(path);
            workbook.write(fileOutputStream);
        }

        fileInputStream=new FileInputStream(path);
        workbook=new XSSFWorkbook(fileInputStream);

        if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
            workbook.createSheet(sheetName);
        sheet=workbook.getSheet(sheetName);

        if(sheet.getRow(rownum)==null)   // If row not exists then create new Row
            sheet.createRow(rownum);
        row=sheet.getRow(rownum);

        cell=row.createCell(colnum);
        cell.setCellValue(data);
        fileOutputStream=new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }


    public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
    {
        fileInputStream=new FileInputStream(path);
        workbook=new XSSFWorkbook(fileInputStream);
        sheet=workbook.getSheet(sheetName);

        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        cellStyle=workbook.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(cellStyle);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }


    public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
    {
        fileInputStream=new FileInputStream(path);
        workbook=new XSSFWorkbook(fileInputStream);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        cellStyle=workbook.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(cellStyle);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
