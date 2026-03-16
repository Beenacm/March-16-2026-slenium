package BeenaAcademy.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {
	WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table/tbody/tr")
	List<WebElement> allLists;

	@FindBy(css = ".tagline")
	WebElement orderMessege;
	
	

	
	

	public boolean verifyItem(String productName) {
		ordersButton();
		waitforVisibilityOfElement(allLists);
		return allLists.stream()
				.anyMatch(n -> n.findElement(By.xpath("./td[2]")).getText().equalsIgnoreCase(productName));
	}

	public String verifyOrderItem() {
		allLists.stream().map(n -> n.findElement(By.xpath("./td[2]/following-sibling::td[3]"))).findFirst()
				.ifPresent(e -> e.click());
		return orderMessege.getText();
	}

}
