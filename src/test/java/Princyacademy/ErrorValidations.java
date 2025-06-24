package Princyacademy;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Princyacademy.TestComponents.BaseTest;
import Princyacademy.TestComponents.Retry;
import Princyacademy.pageObjects.CartPage;
import Princyacademy.pageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class, dataProvider = "getData")
	public void loginErrorValidation(HashMap<String,String> input) throws InterruptedException {


		landingPage.loginApplication("princy@gmail.com", "jkjkjk@464k");


		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test(dataProvider = "getData")
	public void productErrorValidation(HashMap<String,String> input) throws InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));

		// Product Page
		List<WebElement> productsList = productCatalogue.getProductList();
		productCatalogue.addProducttoCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();

		Boolean match = cartPage.displayProducts(input.get("product"));
		Assert.assertTrue(match);

	}

}
