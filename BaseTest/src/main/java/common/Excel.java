package common;

//import Library.GlobalVariables.GlobalVariables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel
{
	public String CurrentDataFilePath;
	public String TestDataSheetName;
	public String CurrentTestCaseName;
	public Excel(String CurrentDataFilePath,String TestDataSheetName,String CurrentTestCaseName){
		this.CurrentDataFilePath=CurrentDataFilePath;
		this.TestDataSheetName=TestDataSheetName;
		this.CurrentTestCaseName=CurrentTestCaseName;
	}
	
  public String getData(String ColumnName)
    throws IOException
  {
	  
	  /*CurrentTestCaseName ="TC001";
		TestDataSheetName = "TestData";
		CurrentModuleName = "TestScripts";
		ModuleName="TestScripts";
		CurrentDataFilePath ="D:\\FRAMEWORK_Selenium\\TEST\\main\\TestData\\TestScripts.xlsx";
	  */
/*    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    String CompleteClassPath = stackTraceElements[2].getClassName();
    String[] arrnames = CompleteClassPath.split("\\.");
    String ClassName = arrnames[2];*/
    //CurrentTestCaseName = ClassName;
   // String ModuleName = arrnames[1];
    //GlobalVariables.CurrentModuleName = ModuleName;
    //CurrentDataFilePath = GlobalVariables.CurrentDirectory + "\\TestData\\" + ModuleName + ".xlsx";
     

	  
	  
    String TestData = "";
    File f1 = new File(CurrentDataFilePath);
    
    FileInputStream fis = new FileInputStream(f1);
    XSSFWorkbook wb1 = new XSSFWorkbook(fis);
    XSSFSheet ws1 = wb1.getSheet(TestDataSheetName);
    int rowcount = ws1.getLastRowNum();
    int colCount = ws1.getRow(0).getLastCellNum();
    
    System.out.println(rowcount+ " "+colCount);
    
    int foundCol = -1;
    for (int j = 0; j < colCount; j++)
    {
      Cell cell = ws1.getRow(0).getCell(j);
      String ExcelColumnName = "";
      switch (cell.getCellType())
      {
      case 1: 
        ExcelColumnName = cell.getStringCellValue();
      }
      if (ExcelColumnName.toLowerCase().trim().equalsIgnoreCase(ColumnName.toLowerCase().trim()))
      {
        foundCol = j;
        break;
      }
    }
    if (foundCol != -1)
    {
      int foundrow = -1;
      for (int j = 0; j < rowcount; j++)
      {
        Cell cell = ws1.getRow(j).getCell(0);
        String ExcelTestCaseName = "";
        switch (cell.getCellType())
        {
        case 1: 
          ExcelTestCaseName = cell.getStringCellValue();
        }
        if (ExcelTestCaseName.toLowerCase().trim().equalsIgnoreCase(CurrentTestCaseName.toLowerCase().trim()))
        {
          foundrow = j;
          break;
        }
      }
      if (foundrow != -1)
      {
        Cell cell = ws1.getRow(foundrow).getCell(foundCol);
        switch (cell.getCellType())
        {
        case 1: 
          TestData = cell.getStringCellValue();
          break;
        case 4: 
          TestData = String.valueOf(cell.getBooleanCellValue());
          break;
        case 0: 
          TestData = String.valueOf(cell.getNumericCellValue());
          break;
        case 3: 
          TestData = String.valueOf(cell.getStringCellValue());
        }
      }
      else
      {
        System.out.println("TestCase" + CurrentTestCaseName + " row not found in datatable");
      }
    }
    else
    {
      System.out.println("Column Not Found in DataTable");
    }
    wb1.close();
    fis.close();
    return TestData;
  }
  


public void putData(String ColumnName, String Value)
    throws IOException
  {
    /*StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    String CompleteClassPath = stackTraceElements[2].getClassName();
    String[] arrnames = CompleteClassPath.split("\\.");
    String ClassName = arrnames[2];
    CurrentTestCaseName = ClassName;
    String ModuleName = arrnames[1];
    GlobalVariables.CurrentModuleName = ModuleName;
    GlobalVariables.CurrentDataFilePath = GlobalVariables.CurrentDirectory + "\\TestData\\" + ModuleName + ".xlsx";
    */
    File f1 = new File(CurrentDataFilePath);
    
    FileInputStream fis = new FileInputStream(f1);
    XSSFWorkbook wb1 = new XSSFWorkbook(fis);
    XSSFSheet ws1 = wb1.getSheet(TestDataSheetName);
    int rowcount = ws1.getLastRowNum();
    int colCount = ws1.getRow(0).getLastCellNum();
    
    int foundCol = -1;
    for (int j = 0; j < colCount; j++)
    {
      Cell cell = ws1.getRow(0).getCell(j);
      String ExcelColumnName = "";
      switch (cell.getCellType())
      {
      case 1: 
        ExcelColumnName = cell.getStringCellValue();
      }
      if (ExcelColumnName.toLowerCase().trim().equalsIgnoreCase(ColumnName.toLowerCase().trim()))
      {
        foundCol = j;
        break;
      }
    }
    if (foundCol != -1)
    {
      int foundrow = -1;
      for (int j = 0; j < rowcount; j++)
      {
        Cell cell = ws1.getRow(j).getCell(0);
        String ExcelTestCaseName = "";
        switch (cell.getCellType())
        {
        case 1: 
          ExcelTestCaseName = cell.getStringCellValue();
        }
        if (ExcelTestCaseName.toLowerCase().trim().equalsIgnoreCase(CurrentTestCaseName.toLowerCase().trim()))
        {
          foundrow = j;
          break;
        }
      }
      if (foundrow != -1)
      {
        Cell cell = ws1.getRow(foundrow).getCell(foundCol);
        cell.setCellValue(Value);
      }
      else
      {
        System.out.println("TestCase" + CurrentTestCaseName + " row not found in datatable");
      }
    }
    else
    {
      System.out.println("Column Not Found in DataTable");
    }
    fis.close();
    
    FileOutputStream outFile = new FileOutputStream(f1);
    wb1.write(outFile);
    outFile.close();
    wb1.close();
  }
}
