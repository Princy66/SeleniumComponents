package Princyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Princyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	


	
	@FindBy(css=".col-lg-4")
	List<WebElement> products; 
	
	@FindBy(css=".ng-animating")
	WebElement spinner; 
	
	By productsBy = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By cartItemsVisible = By.cssSelector(".ta-results"); 
	
	
	
	public List<WebElement> getProductList()  {
	
		waitforElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)  {
		WebElement prod = getProductList().stream()
		.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL"))
		.findFirst().orElse(null);
		return prod;

	}
	
	public void addProducttoCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitforElementToAppear(toastMessage);
		waitforElementToDisappear(spinner);

		
	}
	
	

}
