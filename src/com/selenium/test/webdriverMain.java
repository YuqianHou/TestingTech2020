package com.selenium.test;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class webdriverMain {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "/Users/yuqianhou/eclipse-workspace/TestingTech2020/webdriver/geckodriver");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://103.120.226.190/selenium-demo/git-repo");
		
		// 数据存入二维数组
//		String[][] arr_testdata = {
//				{"3017218053", "https://gitee.com/chenkuochih/software_test.git"},
//				{"3017218054", "https://gitee.com/roshan0/software_test.git"},
//				{"3017218055", "https://gitee.com/dai88X/software_test.git"}
//				
//		};
		File f = new File("/Users/yuqianhou/eclipse-workspace/TestingTech2020/data/Selenium+Lab2020.xlsx");
		FileInputStream fis = null;
		Workbook wb = null;
		int rowCount = 20;
		String[][] arr_testdata = null;
		
		try {
			fis = new FileInputStream(f);
			wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheet("Sheet1");
			
			// 计算表格行数
//			rowCount = sheet.getLastRowNum();
			
			// 通过行数来初始化二维数组
			arr_testdata = new String[rowCount][2];
			
			// 读取文件数据存入arr_testdata
			for (int i = 0; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				
				for(int j = 0; j < 2; j++) {
					arr_testdata[i][j] = row.getCell(j).getStringCellValue();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// 关闭文件流
			try {
				wb.close();
				fis.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		// 读取测试源，执行test case并断言
		System.out.println("开始执行"
				+ Thread.currentThread().getStackTrace()[1].getClassName()
				+ "模块中" + Thread.currentThread().getStackTrace()[1].getMethodName()
				+ ": ");
		
		for(int i = 0; i < arr_testdata.length; i++) {
			// 执行结果
			String TEST_RESULT = "Test Fail";
			
			// arr_testdata[0]取出的是number，arr_testdata[1]取出的是password
			String number = arr_testdata[i][0];
			String password = arr_testdata[i][1];
			System.out.println("第" + i + "次执行：" + "学号：" + number + "，密码：" + password);
			WebElement elementNum = driver.findElement(By.name("user_number"));
			elementNum.sendKeys(number);
			WebElement elementPass = driver.findElement(By.name("password"));
			elementPass.sendKeys(password);
			WebElement btnQuery = driver.findElement(By.cssSelector(".btn"));
			btnQuery.click();
			
			// 断言1: 提示语是否与密码相同
			WebElement elementTip = driver.findElements(By.className("mb-2")).get(1);
			String tipText = elementTip.getAttribute("textContent").trim();
//			str.trim();
			System.out.println("获得提示语：" + tipText);
			if(tipText.equals(password)) {
				// 断言正确
				TEST_RESULT = "Test Pass";
			}
			System.out.println("执行结果：" + TEST_RESULT);
		}
		
//		driver.quit();	

	}
}
