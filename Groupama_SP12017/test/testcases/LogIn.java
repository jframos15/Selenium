package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import settings.MainPage;

public class LogIn {
	WebDriver driver;
	MainPage login = new MainPage();
	
	@Test(priority = 0)
	public void logIn() {				
		driver = MainPage.openChromeDriver(driver);
		login.enterCredentials(driver, "holding", "0");
	}
	
	@Test(priority = 1)
	public void testChangeUser() {
		login.changeUser(driver, 10, "Jean-Paul Grangeon"); 
		login.entretienCollab(driver);
		
	}
}

