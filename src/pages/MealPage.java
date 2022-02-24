package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {
	
	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	
	}
	public WebElement getQuantityInput() {
		return driver.findElement(By.name("product_qty"));	
	}
	public WebElement getAddToCartBtn() {
		return driver.findElement(By.xpath("//a[contains(@class, 'btn--large')]"));	
	}
	public WebElement getFavoriteBtn() {
		return driver.findElement(By.className("svg-icn"));	
	}
	public void addToFavorite() {
//		wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.elementToBeClickable(this.getFavoriteBtn()));
		js.executeScript("arguments[0].click();", this.getFavoriteBtn());
//		this.getFavoriteBtn().click();

	}
	public void addToCart(String numberOfProduct) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("product_qty")));
		this.getQuantityInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		this.getQuantityInput().sendKeys(numberOfProduct);
		this.getAddToCartBtn().click();
	}
	

}
