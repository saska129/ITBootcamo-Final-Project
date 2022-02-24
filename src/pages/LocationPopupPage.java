package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage{
	
	public LocationPopupPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);

	}
	public WebElement getLocationFromHeder() {
		return driver.findElement(By.xpath("//div[@class = 'location-selector']/a"));	
	}
	public WebElement getClosePopupBtn() {
		return driver.findElement(By.xpath("//a[@class = 'close-btn close-btn-white']"));
	}
	public WebElement getKeyword() {
		return driver.findElement(By.id("locality_keyword"));
	}
	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	public WebElement getLocationInput() {
		return driver.findElement(By.id("location_id"));
	}
	public WebElement getSubmitBtn() {
		return driver.findElement(By.name("btn_submit"));
	}
	public void openPopup() {
		this.getLocationFromHeder().click();
	}
	public void setLocation(String locationName) {
		this.getKeyword().click();
		js.executeScript("arguments[0].value=arguments[1];", this.getLocationInput(), this.getLocationItem(locationName).getAttribute("data-value"));
		js.executeScript("arguments[0].click();", this.getSubmitBtn());
	}
		
	public void closePopup() {
		js.executeScript("arguments[0].click();", this.getClosePopupBtn());
	}
	
}
