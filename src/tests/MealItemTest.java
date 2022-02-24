package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test(priority = 0)
	public void addMealToCart() throws InterruptedException, IOException {

		driver.navigate().to(super.baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopup();
		mealPage.addToCart("2");
		softAssertion.assertTrue(notificationSystemPage.msgText().contains("Following Errors"),
				"Error: Select Locatiom message did not appear (first row).");
		softAssertion.assertTrue(notificationSystemPage.msgText().contains("Select Location"),
				"Error: Select Locatiom message did not appear (second row).");
		softAssertion.assertAll();
		notificationSystemPage.msgDisappears();
		locationPopupPage.openPopup();
		locationPopupPage.setLocation("City Center - Albany");
		driver.navigate().refresh();
		mealPage.addToCart("2");
		Assert.assertTrue(notificationSystemPage.msgText().contains("Meal Added To Cart"),
				"Error: Meal added message did not appear.");
		notificationSystemPage.msgDisappears();
	}

	@Test(priority = 1)
	public void addMealToFavorite() throws InterruptedException, IOException {

		driver.navigate().to(super.baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopup();
		mealPage.addToFavorite();
		Assert.assertTrue(notificationSystemPage.msgText().contains("Please login first"));
		notificationSystemPage.msgDisappears();
		driver.get(super.baseURL + "/guest-user/login-form");
		loginPage.Login(super.email, super.password);
		Assert.assertTrue(notificationSystemPage.msgText().contains("Login Successfull"),
				"Error: Login message did not appear.");
		notificationSystemPage.msgDisappears();
		driver.get(super.baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		driver.navigate().refresh();
		mealPage.addToFavorite();
		Assert.assertTrue(notificationSystemPage.msgText().contains("Product has been added to your favorites"),
				"Error: Product added message did not appear.");
		notificationSystemPage.msgDisappears();
		mealPage.addToFavorite();
		notificationSystemPage.msgDisappears();
		logoutPage.LogoutUser();

	}

	@Test(priority = 2)
	public void clearCart() throws InterruptedException, IOException {
		sheet = wb.getSheet("Meals");
		driver.navigate().to(super.baseURL + "/meals");
		locationPopupPage.setLocation("City Center - Albany");
		driver.navigate().refresh();
		for (int i = 1; i < 6; i++) {
			String mealUrl = sheet.getRow(i).getCell(0).getStringCellValue();
			driver.get(mealUrl);
			mealPage.addToCart("3");
			softAssertion.assertTrue(notificationSystemPage.msgText().contains("Meal Added To Cart"),
					"Error: Meal added message did not appear.");
			notificationSystemPage.msgDisappears();
		}
		softAssertion.assertAll();
		cartSummary.clearAll();
		Assert.assertTrue(notificationSystemPage.msgText().contains("meals removed from Cart"),
				"Error: Meals removed message did not appear.");
		notificationSystemPage.msgDisappears();
	}
}
