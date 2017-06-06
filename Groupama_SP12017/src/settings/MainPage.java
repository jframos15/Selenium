package settings;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    @FindBy(id = "fldUserName") 
    WebElement userName;
    
    @FindBy(id = "fldPassword") 
    WebElement password;
    
    @FindBy(id = "btnLogin")
    WebElement btnLogIn;
    
    @FindBy(id = "headerMenuIcon")
    WebElement hamburgerMenu;
    
    @FindBy(xpath = "//*[@id=\"linkmenu-91874\"]/ul/li[1]/form/div/input")
    WebElement inputUser;
    
    @FindBy(xpath = "//*[@id=\"linkmenu-91874\"]/ul/li[1]/form/div/a")
    WebElement inputSubmit;
    
    @FindBy(xpath = "//*[@id=\"profile-header-dropdown\"]/span[1]/span[3]")
    WebElement userType;
    
    @FindBy(id = "linkmenu-92261")
    WebElement menuEntretiens;
    
    @FindBy(id = "linkmenu-92269")
    WebElement subMenuEntretiensMesCollabs;
	
    public static WebDriver openChromeDriver(WebDriver driver) {
        if(driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("");
            System.out.println("Opening Google Chrome");
        }
        return driver;
    }	

    public void enterCredentials(WebDriver driver, String user, String pass) {	
        PageFactory.initElements(driver, this);				
        System.out.println("Entering credentials...");

        userName.sendKeys(user); 
        password.sendKeys(pass);		
        btnLogIn.click();
    }

    public void changeUser(WebDriver driver, long seconds, String userName) {
        hamburgerMenu.click();
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        inputUser.sendKeys(userName);
        inputSubmit.click(); 
        
        driver.findElement(By.xpath("//*[@class='loadMore results']/div[1]/a[1]")).click();
        driver.findElement(By.xpath("//*[@class=\"row\"]/div[2]/div[2]/div[2]/div/a[1]")).click(); 	
        
        WebDriverWait wait = new WebDriverWait(driver, 10);   
        wait.until(ExpectedConditions.visibilityOf(hamburgerMenu));
    }

    public void entretienCollab(WebDriver driver) {
        System.out.println("I am there");
        System.out.println(checkManagerUserType());
        WebDriverWait wait = new WebDriverWait(driver, 10);  
        wait.until(ExpectedConditions.visibilityOf(hamburgerMenu));
        
        if(checkManagerUserType()) {    
            hamburgerMenu.click();
            menuEntretiens.click();
            subMenuEntretiensMesCollabs.click();					
        }
    }

    private Boolean checkManagerUserType() {       
        if(userType.getText().matches("Manager")) {
            return false;
        } else {
            return true;
        }
    }
}
