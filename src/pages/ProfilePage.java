package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {
	
	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);

	}
	public WebElement getFirstNameInput() {
		return driver.findElement(By.name("user_first_name"));	
	}
	public WebElement getLastNameInput() {
		return driver.findElement(By.name("user_last_name"));
	}
	public WebElement getAddressInput() {
		return driver.findElement(By.name("user_address"));
	}
	public WebElement getPhoneInput() {
		return driver.findElement(By.name("user_phone"));
	}
	public WebElement getZipCode() {
		return driver.findElement(By.name("user_zip"));
	}
	public Select getCountry() {
		Select select = new Select (driver.findElement(By.id("user_country_id")));
		return select;
	}
	public Select getState() {
		Select select = new Select (driver.findElement(By.id("user_state_id")));
		return select;
	}
	public Select getCity() {
		Select select = new Select (driver.findElement(By.id("user_city")));
		return select;
	}
	public WebElement getSaveGeneralInfoBtn() {
		return driver.findElement(By.xpath("//div[@class='col-lg-12 col-md-12 col-sm-12 col-lg-12 align--right']//input[@name='btn_submit']"));
	}
	public void changeGeneralInfo(String firstName, String lastName, String address, String phoneNumber,
			String zipCode, String country, String state, String city) throws InterruptedException {
		
		this.getFirstNameInput().clear();
		this.getFirstNameInput().sendKeys(firstName);
		this.getLastNameInput().clear();
		this.getLastNameInput().sendKeys(lastName);
		this.getAddressInput().clear();
		this.getAddressInput().sendKeys(address);
		this.getPhoneInput().clear();
		this.getPhoneInput().sendKeys(phoneNumber);
		this.getZipCode().clear();
		this.getZipCode().sendKeys(zipCode);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(1000);
		this.getState().selectByVisibleText(state);
		this.getCity().selectByVisibleText(city);
		js.executeScript("arguments[0].click();", this.getSaveGeneralInfoBtn());

	}
	
	public WebElement getUploadBtn() {
		return driver.findElement(By.xpath("//a[@class='upload uploadFile-Js']/i"));
	}
	public WebElement getRemoveBtn() {
		return driver.findElement(By.xpath("//a[@class='remove']/i"));
	}
	public void changeProfilePicture (String imgPath) {
		js.executeScript("arguments[0].click();", this.getUploadBtn());
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgPath);
	}

	public void deleteProfilePicture () {
		js.executeScript("arguments[0].click();", this.getRemoveBtn());
	}
	
}
