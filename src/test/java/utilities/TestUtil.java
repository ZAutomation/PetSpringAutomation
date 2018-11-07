package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testbase.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	static Class noparams[] = {};
	public static String INPUT_SHEET_PATH = TestUtil.resourceFolder + "/inputData/" + "InputDataSheet.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,
				new File(TestUtil.resourceFolder + "/shotsAndImages/" + +System.currentTimeMillis() + ".png"));
	}

	/* Method to write in Excel */

	public static void createwriteExcel(List<?> instances, String fileName) throws IOException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Workbook workbook = new XSSFWorkbook();

		Field[] fields = instances.get(0).getClass().getDeclaredFields();

		// Create a Sheet
		Sheet sheet = workbook.createSheet(instances.get(0).getClass().getName());

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		Row headerRow = sheet.createRow(0);
		// Create cells
		for (int i = 0; i < fields.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(fields[i].getName());
			cell.setCellStyle(headerCellStyle);
		}

		// Create Other rows and cells with data
		int rowNum = 1;
		for (Object instance : instances) {
			Row row = sheet.createRow(rowNum++);
			for (int i = 0; i < fields.length; i++) {
				String varName = fields[i].getName();
				String correctMethodNAme = varName.substring(0, 1).toUpperCase() + varName.substring(1);
				Method m = instance.getClass().getMethod("get" + correctMethodNAme, noparams);
				Object valInvoked = m.invoke(instance);
				row.createCell(i).setCellValue(valInvoked.toString());
			}

		}

		// Resize all columns to fit the content size
		for (int i = 0; i < fields.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(TestUtil.resourceFolder + "/dataSheets/" + fileName + ".xlsx");
		workbook.write(fileOut);
		fileOut.close();
		

		// Closing the workbook

	}

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(INPUT_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static List<Map<String, String>> getPetInfotoAdd(String s) {
		List<Map<String, String>> petInfo = new LinkedList<>();
		Map<String, String> pet = new HashMap<>();

		String[] split = s.split("\n");
		for (String s1 : split) {
			String[] split2 = s1.split("\\|");

			pet.put("petName", split2[0]);
			pet.put("petBirthDate", split2[1]);
			pet.put("typePet", split2[2]);

			petInfo.add(pet);
			pet = new HashMap<>();
		}
		return petInfo;
	}

	public static void deleteFile(String fName) {
		  
		    { 
		        File file = new File(fName); 
		          
		        if(file.delete()) 
		        { 
		            System.out.println("File deleted successfully"); 
		        } 
		        else
		        { 
		            System.out.println("Failed to delete the file"); 
		        } 
		    }
		    
	}
	
}
