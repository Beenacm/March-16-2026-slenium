package BeenaAcademy.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(ErrorValidationTest.class);

	@Test(retryAnalyzer=Retry.class,groups="ErrorHandling")
	public void errorLoginValidation() throws InterruptedException {
		ProductDetailPage pr = loginPage.loginApplication("yellow@gmail.com", "Mgo!@#123");
		String errorText = loginPage.errorMessege();
		Assert.assertEquals(errorText, "Incorrect email or password.");
	}

	
	public void productErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductDetailPage pr = loginPage.loginApplication("Red@gmail.com", "Mango!@#123");
	
		pr.addProductItem(productName);
		pr.addToCart();
		CartPage cr=pr.cartButton();
		cr.verifyProductItems("ZARA COAT 33");
		Assert.assertFalse(false);
		

	}
	


}
