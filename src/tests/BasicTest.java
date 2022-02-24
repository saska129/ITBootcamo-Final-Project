package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pages.CartSummary;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public abstract class BasicTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected LogoutPage logoutPage;
	protected MealPage mealPage;
	protected CartSummary cartSummary;
	protected String baseURL = "http://demo.yo-meals.com";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";
	protected SoftAssert softAssertion;
	protected File file;
	protected FileInputStream fis;
	protected XSSFWorkbook wb;
	protected XSSFSheet sheet;
	

	@BeforeMethod
	public void beforeMethod() throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		js = (JavascriptExecutor) driver;
		softAssertion = new SoftAssert();
		file = new File("data/Data.xlsx");
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		
		locationPopupPage = new LocationPopupPage(driver, wait, js);
		loginPage = new LoginPage(driver, wait, js);
		notificationSystemPage = new NotificationSystemPage(driver, wait, js);
		profilePage = new ProfilePage(driver, wait, js);
		logoutPage = new LogoutPage(driver, wait, js);
		mealPage = new MealPage(driver, wait, js);
		cartSummary = new CartSummary(driver, wait, js);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-h-mm-ssa");
		String formattedDate = sdf.format(date);

		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
				File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(sourceFile, new File("./screenshots/" + formattedDate + ".png"));
				System.out.println("Screenshot taken!");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage() + "!");
			}
		}

		driver.quit();

	}
}
