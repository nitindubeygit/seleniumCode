/**
 * 
 */
package com.vTiger.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.vTiger.testCases.BaseClass;



/**
 * @author nitin.dubey
 *
 */
public class ExcelReader extends BaseClass {

/*	//@Test
	public static void xlReader() throws IOException{

		//FileInputStream fis=new FileInputStream();		
		Workbook wbObj=getWorkbook("TestData/TestData.xlsx");
		Sheet sheetObj=wbObj.getSheet("TestData");
		Row rowObj=sheetObj.getRow(0);
		Cell cellObj=rowObj.getCell(0);
		String cellVal=cellObj.getStringCellValue();
		System.out.println(cellVal);
	}*/


	public static Workbook getWorkbook(String workbookPath) throws IOException{
		FileInputStream fis=new FileInputStream(workbookPath);	
		Workbook wbookObj=null;

		if (workbookPath.contains("xlsx")){
			wbookObj=new XSSFWorkbook(fis);
		}else {

			wbookObj=new HSSFWorkbook(fis);
		}
		return wbookObj;
	}

	public static List<String> getTestCaseData(String testcaseID, String testdataPath, String sheetName) throws IOException{
		Workbook wbObj=getWorkbook(testdataPath);
		Sheet sheetObj=wbObj.getSheet(sheetName);
		int rowCnt=sheetObj.getLastRowNum();
		Row rowObj=null;
		List<String> liData=new ArrayList<String>();
		for(int i=1;i<=rowCnt;i++){      //i=1 since our test case data starts from 2nd row, otherwise it would hv been 0
			rowObj=sheetObj.getRow(i);
			
			String tcID=getCellString(rowObj, 0);
			//Cell cellObj=rowObj.getCell(0); //fetching first cell of the test data
			//String tcid=cellObj.getStringCellValue();
			if(tcID.equalsIgnoreCase(testcaseID)){
				break;
			}
		}
		int cellCount=rowObj.getLastCellNum();
		for (int i=1; i<cellCount;i++) {         ///We need to put 2 here if our test data starts from 3rd cell, since our data starts from 2nd cell so i have put 1
			String cellData=getCellString(rowObj, i);
			liData.add(cellData);
		}
		TCData=liData;
		return liData;
	}

	private static String getCellString(Row rowObj, int cellNumber){
		MissingCellPolicy mcp=Row.CREATE_NULL_AS_BLANK;
		Cell cellObj=rowObj.getCell(cellNumber, mcp);
		int cellTypeNumber=cellObj.getCellType();
		String celldata=null;
		if(cellTypeNumber==Cell.CELL_TYPE_STRING){
			celldata=cellObj.getStringCellValue();

		}else if(cellTypeNumber==Cell.CELL_TYPE_NUMERIC){
			Double dblData=cellObj.getNumericCellValue();
			Integer intData=dblData.intValue();
			celldata=intData.toString();
		}
		return celldata;

	}
	
	


}
