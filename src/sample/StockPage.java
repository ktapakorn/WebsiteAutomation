package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StockPage {
	private static WebElement element = null;
	
	public static WebElement addButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@data-action='product-variant-add-open']"));
		return element;
	}
	
	public static WebElement productName(WebDriver driver) {
		element = driver.findElement(By.id("name"));
		return element;
	}
	
	public static WebElement productSKU(WebDriver driver) {
		element = driver.findElement(By.id("sku"));
		return element;
	}
	
	public static WebElement productPrice(WebDriver driver) {
		element = driver.findElement(By.id("price"));
		return element;
	}
	
	public static WebElement addOneProduct(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@type='submit']"));
		return element;
	}
	public static WebElement wholeSaleButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@data-action='wholesale-add-open']"));
		return element;
	}
	
	public static WebElement rangeBox(WebDriver driver) {
		element = driver.findElement(By.id("range_qty_range_start"));
		return element;
	}
	
	public static WebElement rangeBoxStartAmount(WebDriver driver) {
		element = driver.findElement(By.id("range_qty_amount_start"));
		return element;
	}
	public static WebElement rangeBoxEndAmount(WebDriver driver) {
		element = driver.findElement(By.id("range_qty_amount_end"));
		return element;
	}
	public static WebElement rangeQtyEnd(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='range_qty_range_end']"));
		return element;
	}
}
