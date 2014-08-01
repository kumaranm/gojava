package com.mk.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtils
{

	public static ArrayList<String[]> readFromExcel(String fileName, int sheetIndex) throws IOException
	{
		// Create an ArrayList to store the data read from excel sheet.
		ArrayList<String[]> sheetData = new ArrayList<String[]>();
		FileInputStream fis = null;
		try
		{
			// Create a FileInputStream that will be use to read the excel file.
			fis = new FileInputStream(fileName);
			 org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("paypalbasic");
			// Create an excel workbook from the file system.
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			// Get the first sheet on the workbook.
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

			// When we have a sheet object in hand we can iterator on each
			// sheet's rows and on
			// each row's cells. We store the data read on an ArrayList
			int count = 0;
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext())
			{
				HSSFRow row = (HSSFRow) rows.next();
				Iterator<Cell> cells = row.cellIterator();

				if(count++ >10)
				{
					break;
				}
				
				ArrayList<String> data = new ArrayList<String>();
				while (cells.hasNext())
				{
					HSSFCell cell = (HSSFCell) cells.next();
					cell.setCellType(Cell.CELL_TYPE_STRING); //read all values as string
					data.add(cell.toString());
				}
				Object cellData[] = data.toArray();
				sheetData.add(Arrays.copyOf(cellData, cellData.length, String[].class));
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fis != null)
			{
				fis.close();
			}
		}
		// remove the first row which is header row
		sheetData.remove(0);
		return sheetData;
	}

	private static Object[][] convertArrayListOfArrayListTo2DArray(ArrayList<ArrayList<Object>> mainList)
	{
		// convert to an array of ArrayLists
		ArrayList<Object[]> tempList = new ArrayList<Object[]>();
		for (ArrayList<Object> objectList : mainList)
		{
			tempList.add((Object[]) objectList.toArray());
		}

		// Convert to array of arrays - 2D array
		Object[][] obj = (Object[][]) tempList.toArray();
		return obj;
	}

	public static int getRowCount(String fileName, int sheetIndex) throws IOException
	{
		FileInputStream fis = null;
		int rowCount = 0;
		try
		{
			// Create a FileInputStream that will be use to read the excel file.
			fis = new FileInputStream(fileName);
			 org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("paypalbasic");
			// Create an excel workbook from the file system.
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			// Get the first sheet on the workbook.
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

			rowCount = sheet.getLastRowNum() + 1;
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fis != null)
			{
				fis.close();
			}
		}
		return rowCount;
	}

	
	public static void main(String[] args) throws IOException
	{
		System.out.println(getRowCount("C:\\Kumaran\\Compliance\\3.Analysis\\nls custom list\\Copy of Copy of 基本データ20131201 - Copy.xls", 0));
		
		ArrayList<String[]> readFromExcel = readFromExcel("C:\\Kumaran\\Compliance\\3.Analysis\\nls custom list\\Copy of Copy of 基本データ20131201 - Copy.xls", 0);
        System.out.println("Country List\n"+readFromExcel);
	}
}