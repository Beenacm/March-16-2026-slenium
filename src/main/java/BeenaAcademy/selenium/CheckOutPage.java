package BeenaAcademy.selenium;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".form-group")
	WebElement searchBox;

	@FindBy(css = ".ta-item")
	List<WebElement> searchItems;

	@FindBy(css = ".ng-star-inserted")
	List<WebElement> countryNames;

	@FindBy(css = ".actions a")
	WebElement placeorder;
	
	@FindBy(css=".text-validated")
	List<WebElement> boxToAppear;
	

	
	
	
	

	public List<WebElement> allSuggestedCountryNames() {
		waitforVisibilityOfElement(searchItems);
		return countryNames;
	}

	public void addCountry(String countryName, String fullName) {
		waitforVisibilityOfElement(boxToAppear);

		Actions a = new Actions(driver);
		a.moveToElement(searchBox).click().sendKeys(countryName).build().perform();
		allSuggestedCountryNames().stream().filter(n -> n.getText().equalsIgnoreCase(fullName)).findFirst().get().click();

	}

	public ConfirmatonPage placeOrder() {
		placeorder.click();
		return new ConfirmatonPage(driver);
	}

}
