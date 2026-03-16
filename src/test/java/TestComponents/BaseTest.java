package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import BeenaAcademy.selenium.LoginPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fr = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fr);
		String browserName =System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		
		 //prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver=new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonData() throws IOException {

		File filePath = new File(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		String filePathNew = FileUtils.readFileToString(filePath, StandardCharsets.UTF_8);
		ObjectMapper obj = new ObjectMapper();
		List<HashMap<String, String>> data = obj.readValue(filePathNew,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;

	}

	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File file=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png"));
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		
		
	}

}
