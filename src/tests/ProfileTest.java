package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import pages.CartSummary;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;


public class ProfileTest extends BasicTest {

	@Test (priority=0)
	public void editProfile() throws InterruptedException, IOException {
		driver.navigate().to(super.baseURL + "/guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.Login(super.email, super.password);
		Assert.assertTrue(notificationSystemPage.msgText().contains("Login Successfull"), "Error: Login message did not appear.");
		notificationSystemPage.msgDisappears();
		driver.navigate().to(super.baseURL + "/member/profile");
		profilePage.changeGeneralInfo("Mark", "Marks", "271 Spring", "465465464", "7788", "United States", "Texas", "Houston");
		Assert.assertTrue(notificationSystemPage.msgText().contains("Setup"), "Error: Setup message did not appear.");
		notificationSystemPage.msgDisappears();
		logoutPage.LogoutUser();
		Assert.assertTrue(notificationSystemPage.msgText().contains("Logout Successfull"), "Error: Logout message did not appear.");
		notificationSystemPage.msgDisappears();
	}	
	
	@Test (priority=1)
	public void changeProfileImg () throws IOException, InterruptedException  {
		driver.navigate().to(super.baseURL + "/guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.Login(super.email, super.password);
		Assert.assertTrue(notificationSystemPage.msgText().contains("Login Successfull"), "Error: Login message did not appear.");
		notificationSystemPage.msgDisappears();
		driver.navigate().to(super.baseURL + "/member/profile");
		String imgPath = new File("img/ProfilePicture.png").getCanonicalPath();
		profilePage.changeProfilePicture(imgPath);
		Assert.assertTrue(notificationSystemPage.msgText().contains("Profile Image Uploaded Successfully"), "Error: Upload message did not appear.");
		notificationSystemPage.msgDisappears();
		profilePage.deleteProfilePicture();
		Assert.assertTrue(notificationSystemPage.msgText().contains("Profile Image Deleted Successfully"), "Error: Delete message did not appear.");
		notificationSystemPage.msgDisappears();
		logoutPage.LogoutUser();
		Assert.assertTrue(notificationSystemPage.msgText().contains("Logout Successfull"), "Error: Logout message did not appear.");
		notificationSystemPage.msgDisappears();
	}
	
	
}
