package Princyacademy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {

		// WebDriverManager.chromedriver().clearDriverCache().setup();;
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");

		String expectedProduct = "ADIDAS ORIGINAL";
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("princygoyal66@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("P12345678g@");
		driver.findElement(By.xpath("//input[@id='login']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

		// Get all products in the products list
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

		// Apply stream to filter one desired product
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL"))
				.findFirst().orElse(null);

		// Click Add to cart button // we are using prod to apply selector on filtered
		// product only
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// Explicit wait until toast appears and loader disappear

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// Go to Cart
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		// Locate all products in carts

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(expectedProduct));

		// Assert

		Assert.assertTrue(match);

		// Click on checkout

		driver.findElement(By.cssSelector(".totalRow button")).click();

		// Send India on dropdown

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		// select india from dropdown
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		// Place order
		driver.findElement(By.cssSelector(".action__submit")).click();

		String msg = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));

	}

}
