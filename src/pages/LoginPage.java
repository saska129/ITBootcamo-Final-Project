package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		}
	public WebElement getEmailInput() {
		return driver.findElement(By.name("username"));	
	}
	public WebElement getPasswordInput() {
		return driver.findElement(By.name("password"));	
	}
	public WebElement getRememberMeInput() {
		return driver.findElement(By.name("remember_me"));	
	}
	public WebElement getForgotPasswordLink() {
		return driver.findElement(By.xpath("//*[@id='frm_fat_id_frmLogin']/p/a"));	
	}
	public WebElement getLoginBtn() {
		return driver.findElement(By.name("btn_submit"));	
	}
	
	public void Login(String email, String password) {
		this.getEmailInput().clear();
		this.getEmailInput().sendKeys(email);
		this.getPasswordInput().clear();
		this.getPasswordInput().sendKeys(password);
		this.getLoginBtn().click();
	}
	
}
