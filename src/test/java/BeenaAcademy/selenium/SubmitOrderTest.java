package BeenaAcademy.selenium;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{

	String ProductName = "ADIDAS ORIGINAL";

	@Test(groups={"purchase"},dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

		
		
	
		ProductDetailPage pr = loginPage.loginApplication(input.get("email"),input.get("password"));
		boolean match = pr.getProductByName(ProductName);
		Assert.assertTrue(match);
		pr.addProductItem(ProductName);
		CartPage cp = pr.cartButton();
		boolean match1 = cp.verifyProductItems(ProductName);
		Assert.assertTrue(match1);
		CheckOutPage cop = cp.checkOut();
		cop.addCountry("Ind", "India");
		ConfirmatonPage cnp = cop.placeOrder();
		String messege = cnp.getMessege();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", messege);
	
	}
	
	
	

	@Test(dependsOnMethods = "submitOrder")
	public void orderHistory() throws InterruptedException {
		ProductDetailPage pr =  loginPage.loginApplication("yellow@gmail.com","Mango!@#123");
		OrderHistoryPage orderhistorypage=pr.ordersButton();
		boolean match1 = orderhistorypage.verifyItem(ProductName);
		Assert.assertTrue(match1);
		String messege = orderhistorypage.verifyOrderItem();
		Assert.assertEquals("Thank you for Shopping With Us", messege);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data=getJsonData();
		return new Object[][] {{data.get(0)},{data.get(1)}};
	

}
}
