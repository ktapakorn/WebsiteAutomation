package sample;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Now this method does not need any arguments

public class  RegularActions {

	public static WebDriver Execute() throws Exception {
		SuperDriver sdriver;
		WebDriver driver;
		sdriver = SuperDriver.getInstance();
		driver = sdriver.openBrowser(false);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.bentoweb.com/th/login");
		LoginPage.txtbx_UserName(driver).sendKeys("k.tapakorn@gmail.com");
		LoginPage.txtbx_Password(driver).sendKeys("bossk1991");
		LoginPage.btn_LogIn(driver).click();
		return driver;
	}
	
	public static void GenerateData(WebDriver driver, HashMap<String, String> map) throws InterruptedException {
		StockPage.productName(driver).sendKeys(map.get("productName"));
		StockPage.productSKU(driver).sendKeys(map.get("productSKU"));
		StockPage.productPrice(driver).sendKeys(map.get("price"));
        System.out.println(map.get("wholesale"));
		if (map.get("wholesale") != null) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", StockPage.wholeSaleButton(driver));
			if (StockPage.wholeSaleButton(driver).getText().equalsIgnoreCase("ตั้งราคาขายส่ง") || StockPage.wholeSaleButton(driver).getText().equalsIgnoreCase("Set wholesale price") || StockPage.wholeSaleButton(driver).getText().equalsIgnoreCase("Set wholesale pricing")) {
				StockPage.wholeSaleButton(driver).click();
			}
			StockPage.rangeBox(driver).sendKeys("9");
			StockPage.rangeBox(driver).sendKeys(Keys.TAB);
			StockPage.rangeBoxStartAmount(driver).sendKeys(map.get("price"));
			StockPage.rangeBoxEndAmount(driver).sendKeys(map.get("wholesale"));
		}
        Thread.sleep(3000);
        StockPage.addOneProduct(driver).click();
	}
	
	public static void productPriceModification (WebDriver driver, String URL, String price, String subprice) throws InterruptedException {
		driver.get(URL);
		List<WebElement> iconList = driver.findElements(By.className("icon-pencil"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < iconList.size(); i++) {
			js.executeScript("arguments[0].scrollIntoView(true);", iconList.get(i));
			iconList.get(i).click();
			Thread.sleep(2000);
			StockPage.productPrice(driver).clear();
			StockPage.productPrice(driver).sendKeys(price);
			StockPage.rangeBoxStartAmount(driver).clear();
			StockPage.rangeBoxStartAmount(driver).sendKeys(price);
			if (!subprice.isEmpty()) {
				StockPage.rangeBoxEndAmount(driver).clear();
				StockPage.rangeBoxEndAmount(driver).sendKeys(subprice);
			}
			Thread.sleep(2000);
			StockPage.addOneProduct(driver).click();
			Thread.sleep(2000);
		}
	}
	public static void productAddition(WebDriver driver, String URL, String sheetName) throws Exception {
		driver.get(URL);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		XSSFSheet sheet = ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, sheetName);
		HashMap<String, String> myMap = new HashMap<String, String>();
		js.executeScript("arguments[0].scrollIntoView(true);", StockPage.addButton(driver));
		StockPage.addButton(driver).click();
		Thread.sleep(2000);
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			myMap.put("productName", "กว้าง " + ExcelUtils.getCellData(i, 3) + " ยาว " + ExcelUtils.getCellData(i, 5));
			myMap.put("productSKU", ExcelUtils.getCellData(i, 0));
			myMap.put("price", ExcelUtils.getCellData(i, 7));
			myMap.put("wholesale", ExcelUtils.getCellData(i, 1));
			System.out.println(myMap.toString());
			RegularActions.GenerateData(driver, myMap);
			Thread.sleep(2000);
		}
	}
	
	public static void productVariation(WebDriver driver, String URL, String sheetName) throws Exception {
		driver.get(URL);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		XSSFSheet sheet = ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, sheetName);
		HashMap<String, String> myMap = new HashMap<String, String>();
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("icon-pencil")));
		driver.findElement(By.className("icon-pencil")).click();
		Thread.sleep(2000);
		StockPage.productName(driver).clear();
		StockPage.productName(driver).sendKeys("กว้าง " + ExcelUtils.getCellData(0, 1) + " ยาว " + ExcelUtils.getCellData(0, 2));
		StockPage.productSKU(driver).clear();
		StockPage.productSKU(driver).sendKeys(ExcelUtils.getCellData(0, 0));
		StockPage.productPrice(driver).clear();
		StockPage.productPrice(driver).sendKeys(ExcelUtils.getCellData(0, 3));
        System.out.println(ExcelUtils.getCellData(0, 4));
        js.executeScript("arguments[0].scrollIntoView(true);", StockPage.wholeSaleButton(driver));
		if (!ExcelUtils.getCellData(0, 4).isEmpty()) {
			StockPage.wholeSaleButton(driver).click();
			StockPage.rangeBox(driver).sendKeys("9");
			StockPage.rangeBox(driver).sendKeys(Keys.TAB);
			StockPage.rangeBoxStartAmount(driver).sendKeys(ExcelUtils.getCellData(0, 3));
			StockPage.rangeBoxEndAmount(driver).sendKeys(ExcelUtils.getCellData(0, 4));
		}
        Thread.sleep(2000);
        StockPage.addOneProduct(driver).click();
		Thread.sleep(2000);
		StockPage.addButton(driver).click();
		Thread.sleep(2000);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			myMap.put("productName", "กว้าง " + ExcelUtils.getCellData(i, 1) + " ยาว " + ExcelUtils.getCellData(i, 2));
			myMap.put("productSKU", ExcelUtils.getCellData(i, 0));
			myMap.put("price", ExcelUtils.getCellData(i, 3));
			if (!ExcelUtils.getCellData(i, 4).isEmpty()) {
				myMap.put("wholesale", "");
			}
			System.out.println(myMap.toString());
			RegularActions.GenerateData(driver, myMap);
			Thread.sleep(3000);
		}

	}

}