package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		
		String path = "D:\\pavan\\opencartv1.0\\testData\\LoginData.xlsx";
		ExcelUtility ExcelUtil = new ExcelUtility(path);
		
		int totalrows=ExcelUtil.getRowCount("Sheet1");
		int totalcols=ExcelUtil.getCellCount("Sheet1", 1);
		
		String loginData[][]= new String [totalrows][totalcols];
		
		for(int i=1; i<totalrows; i++) {
			
			for(int j=0; j<totalcols;j++) {
				loginData[i-1][j] = ExcelUtil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
		
		
	}	
	
}
