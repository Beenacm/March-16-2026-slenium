package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BeenaAcademy.selenium.CartPage;
import BeenaAcademy.selenium.OrderHistoryPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css = "[routerlink='/dashboard/myorders']")
	WebElement orders;
	
	
	
	public void threadToSleep() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	
	public void waitforVisibilityOfElement(List<WebElement> elements) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitforVisibilityOfElement(WebElement elements) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(elements));
	}
	
	public void elementToBeClickable(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public CartPage cartButton() {
		cart.click();
		return new CartPage(driver);
	}
	
	public OrderHistoryPage ordersButton() {
		orders.click();
		return new OrderHistoryPage(driver);
		
	}
	
}
