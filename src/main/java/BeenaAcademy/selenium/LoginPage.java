package BeenaAcademy.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement useremail;

	@FindBy(id = "userPassword")
	WebElement Password;

	@FindBy(id = "login")
	WebElement Login;
	
	
	@FindBy(css="[class*='flyInOut']")
	WebElement toastMessege;

	public ProductDetailPage loginApplication(String username,String password) {
		useremail.sendKeys(username);
		Password.sendKeys(password);
		Login.click();
		
		return new ProductDetailPage(driver);
	}
	
	public String errorMessege() {
		
		waitforVisibilityOfElement(toastMessege);
		return toastMessege.getText();
	}
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
	}

}
