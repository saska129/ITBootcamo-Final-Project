package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartSummary extends BasicPage{


	
	public CartSummary(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	public WebElement getClearAll() {
		return driver.findElement(By.xpath("//div[@class='cart-head']/a[contains(@class, 'btn--third')]"));	
}
	public void clearAll() {
		this.getClearAll().click();
	}
}