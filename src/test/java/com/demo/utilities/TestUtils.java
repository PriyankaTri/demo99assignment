package com.demo.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtils {

    public FileInputStream fis;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;

    public XSSFRow row;

    public XSSFCell cell;
    String path = null;

    DataFormatter formatter = new DataFormatter();

    String data;

    public TestUtils(String path){
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {

        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        wb.close();
        fis.close();
        return rowCount;

    }

    public int getCellCount(String sheetName, int rowNum) throws IOException{
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fis.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException{
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(cellNum);

        data = formatter.formatCellValue(cell);
        wb.close();
        fis.close();
        return data;
    }
}
