package BeenaAcademy.selenium;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(tagName="h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".totalRow:nth-child(3) button")
	WebElement checkOutButton;
	
	
	public boolean verifyProductItems(String productName) {
		boolean match=cartItems.stream().anyMatch(n->n.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	public CheckOutPage checkOut() {
		checkOutButton.click();
		return new CheckOutPage(driver);
	}

}
