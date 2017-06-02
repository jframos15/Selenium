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
    @FindBy(css = "#linkmenu-92261")
    WebElement menuEntretiens;
    @FindBy(css = "#linkmenu-92269")
    WebElement subMenuEntretiensMesCollabs;
	
    public static WebDriver openChromeDriver(WebDriver driver) {
        if(driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("http://msrvaq10vm.technomedia.ca/sigal_60/2017sp1/sp_groupama/");
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        driver.switchTo().frame(0);
        
        /*use reg exp
        String sch_userModal = "searchpopupSCH_USER_";
        Pattern p = Pattern.compile("[a-zA-Z]{14}\\p{Punct}[A-Z]{4}\\p{Punct}\\d{13}");
        Matcher m;*/
        
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"SCH_USER_1496362181527_loadMore\"]/div[1]/a[1]"))));
        //select first user child of the list
        //driver.findElement(By.xpath("//*[@id=\"SCH_USER_1496362181527_loadMore\"]/div[1]/a[1]")).click();
        List<WebElement> links = driver.findElements(By.tagName("a"));        
        System.out.println(links.size());
        
        for(int i = 0; i < links.size(); i++) {
            System.out.println(links.get(i).getText());
        }       
        driver.findElement(By.cssSelector("#SCH_USER_1496260363650 > div.col-md-3.employee-datas > div.data > div.menu > div > a.list-group-item.subsubmenu.connect")).click();
        wait.until(ExpectedConditions.visibilityOf(hamburgerMenu));		
    }

    public void entretienCollab() {
        if(checkManagerUserType()) {
            hamburgerMenu.click();
            menuEntretiens.click();
            subMenuEntretiensMesCollabs.click();					
        }
    }

    private Boolean checkManagerUserType() {
        if(!userType.equals("Manager")) {
            return false;
        } else {
            return true;
        }
    }
}
