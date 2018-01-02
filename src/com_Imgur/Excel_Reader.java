package com_Imgur;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument;

public class Excel_Reader {
	
	public String path;
	XSSFWorkbook Workbook;
	;
	XSSFRow row;
	XSSFCell cell;
	
	public Excel_Reader(String Path)
	{
	 this.path = Path;	
	 FileInputStream FInSt;
	 try
		{
			FInSt = new FileInputStream(path);
			 Workbook = new XSSFWorkbook(FInSt);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public String getCellData(String SheetName, String Colname, int RowNum)
	{
		try
		{
			int col_Num = 0;
			int index =  Workbook.getSheetIndex(SheetName);			
			XSSFSheet sheet = Workbook.getSheetAt(index);
			XSSFRow row =sheet.getRow(0);
		
			for(int i=0; i<row.getLastCellNum();i++)
			{
				if(row.getCell(i).getStringCellValue().equals(Colname))
				{
					col_Num=i;
				}
			}
			row=sheet.getRow(RowNum-1);
			cell=row.getCell(col_Num);
			
			if(cell.getCellType() == cell.CELL_TYPE_STRING)
			{
				return cell.getStringCellValue();
			}
			else if(cell.getCellType() ==cell.CELL_TYPE_NUMERIC)
			{
				return String.valueOf(cell.getNumericCellValue());
			}
			else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN)
			{
				return String.valueOf(cell.getBooleanCellValue());
			}
			else if(cell.getCellType() == cell.CELL_TYPE_BLANK)
			{
				return "";
			}
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	//@SuppressWarnings("unused")
	
	}
	
	
/*	public static void main(String[] args)
	{
		String path = System.getProperty("user.dir")+"//src//test//resources/login.xlsx";
		Excel_Reader obj = new Excel_Reader(path);
		System.out.println(obj.getCellData("Login", "Username", 3));
		
	}*/


