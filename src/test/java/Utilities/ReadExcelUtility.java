package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelUtility {

	public Object[][] readData(String sheetName) throws IOException {
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/TestData/TestData.xlsx");
		
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows=sheet.getLastRowNum()+1;             // 5
		int col=sheet.getRow(0).getLastCellNum();   // 2
		
		Object[][] arr=new Object[rows-1][col];     // 4 2
		
		for(int i=0;i<rows-1;i++) {
			
			for(int j=0;j<col;j++) {
				
				arr[i][j]=sheet.getRow(i+1).getCell(j).getStringCellValue();
						
			}		
		}
		
		fis.close();
		return arr;
		
	}
	
}
