package BeenaAcademy.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductDetailPage extends AbstractComponent {

	WebDriver driver;

	public ProductDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".card-body")
	List<WebElement> products;

	
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> productItems() {
		return products;
	}

	public boolean getProductByName(String productName) {
		boolean product = productItems().stream()
				.anyMatch(n -> n.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName));
		return product;
	}

	public void addProductItem(String productName) throws InterruptedException {
		
		WebElement prod=products.stream().filter(n->n.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		prod.findElement(addToCart).click();
		threadToSleep();

	}

	public void addToCart() {
		cartButton();
	}
	
	

}
