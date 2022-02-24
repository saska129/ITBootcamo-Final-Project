package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NotificationSystemPage extends BasicPage {
	

	public NotificationSystemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	public WebElement getMsgElement() {
		return driver.findElement(By.xpath("//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));	
	}
	public String msgText () {
		return this.getMsgElement().getText();
	}
	
	public void msgDisappears() {
		wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"), "style", "display: none;"));

	}


	
}
