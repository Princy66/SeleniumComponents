package Princyacademy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Princyacademy.TestComponents.BaseTest;
import Princyacademy.pageObjects.CartPage;
import Princyacademy.pageObjects.CheckoutPage;
import Princyacademy.pageObjects.ConfirmationPage;
import Princyacademy.pageObjects.OrderPage;
import Princyacademy.pageObjects.ProductCatalogue;

public class SubmitOrder extends BaseTest {
	String country = "India";


	@Test(dataProvider = "getData",groups = "Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));

		// Product Page
	//	List<WebElement> productsList = productCatalogue.getProductList();
		productCatalogue.addProducttoCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();

		Boolean match = cartPage.displayProducts(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(country);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"},dataProvider = "getData")
	public void orderHistory(HashMap<String,String> input) throws InterruptedException {
	ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
	OrderPage orderPage = productCatalogue.goToOrderPage();
	Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("product")));

		
	}
	


	//One way to use hash map but we want it from another file so used json
			/*HashMap<String,String> map = new HashMap<String,String>();
			map.put("email","princygoyal66@gmail.com");
			map.put("password", "P12345678g@");
			map.put("product", "ADIDAS ORIGINAL");
			
			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("email","dheeraj@gmail.com");
			map1.put("password", "P12345678gg");
			map1.put("product", "ZARA COAT 3");
			return new Object[][] {{map}, {map1}  };
			*/
			
			// return new Object[][] {{"princygoyal66@gmail.com","P12345678@","ADIDAS ORIGINAL"}, {"princygoyal66@gmail.com1","P123456178@","ZARA COAT 3"}  };
			
}
