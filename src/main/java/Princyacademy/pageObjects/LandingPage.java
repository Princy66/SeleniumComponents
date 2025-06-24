package Princyacademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Princyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement userPassword;
	
	@FindBy(xpath="//input[@id='login']")
	WebElement submit;
	
	@FindBy(css= ".ngx-toastr")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApplication(String email, String password)  {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
	    ProductCatalogue productCatalogue = new ProductCatalogue(driver);
	    return productCatalogue;

	}
	
	public String getErrorMessage() {
		
		waitforWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
		//div[@class='ng-tns-c4-8 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
	}
	
	

	public void goTO() {
		driver.get("https://rahulshettyacademy.com/client");

	}

}
