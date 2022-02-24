package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage extends BasicPage {
	
	public LogoutPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	public WebElement getUserTrigger() {
		return driver.findElement(By.xpath("//li[@class='filled ']/a" ));
	}
	public WebElement getLogout() {
		return driver.findElement(By.xpath("//div[@class='my-account-dropdown']//li[2]"));
	}
	public void LogoutUser() throws InterruptedException {
		this.getUserTrigger().click();
		wait.until(ExpectedConditions.elementToBeClickable(this.getLogout()));
		this.getLogout().click();
		
	}
}
