package testNG;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class SimSettings {
    //@FindBy(xpath="/html/body/form/div[@class=\"wrapper\"]/div[@id=\"pageCONTENT\"]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/input[@id=\"fldUserName\"]")
    @FindBy(id="fldUserName")
    private WebElement LogInName;
    @FindBy(id="fldLogin")
    private WebElement LogInPass;
    @FindBy(xpath="//*[@id=\"pageCONTENT\"]/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td/input")
    WebElement btnLogIn;
        
    //Maintenance tab
    @FindBy(xpath="//*[@id=\"menuTab\"]/ul/li[2]/a")
    private WebElement tabMaintenance;
    @FindBy(id="btnBackDoor")
    private WebElement btnBackDoor;
    @FindBy(xpath="/html/body/table/tbody/tr[2]/td/input[1]")
    private WebElement btnBackDoorConfirm;
    @FindBy(id="fldCleanupaction")
    private WebElement selectfldCleanupaction;
    @FindBy(xpath="//*[@id=\"pageCONTENT\"]/table/tbody/tr/td/table/tbody/tr[19]/td/table/tbody/tr[3]/td/input")
    private WebElement btnMaintenanceConfirm;

    //Gestion instance tab
    @FindBy(xpath="//*[@id=\"menuTab\"]/ul/li[3]/a")
    private WebElement tabGestionInstances;
    @FindBy(id="fldAction")
    private WebElement lstFldAction;
    @FindBy(xpath="//*[@id=\"pageCONTENT\"]/table/tbody/tr/td/table/tbody/tr[8]/td/input")
    private WebElement btnGestionInstancesConfirm;    
    
    protected static WebDriver driver;	
    private static Select iSelect;
    
    private void change1stLevelFrame() {
    	//switch frame to the first frame CONTENT
        driver.switchTo().defaultContent();
        driver.switchTo().frame("CONTENT");
        System.out.println("Changing target to 1st level frame.");
    }
    
    private void change2ndLevelFrame() {
    	//change to second level iframe
    	//must keep this script original since it may change
    	driver.switchTo().frame(driver.findElement(By.cssSelector("#divPopWindow > iframe")));    
    	System.out.println("Changing target to 2nd level frame");
    }
    
    private void waitWithPolling(long timeOut, long timePolling, String idName) {
    	FluentWait<WebDriver> wait = new FluentWait<>(driver);
    	wait.withTimeout(timeOut, TimeUnit.SECONDS);
    	wait.pollingEvery(timePolling, TimeUnit.SECONDS);
    	wait.ignoring(NoSuchElementException.class);        
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idName)));    	
    }
    
    protected void initializeChrome() throws InterruptedException {		
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dyan\\Documents\\Selenium WebDrivers\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("Web Chrome driver is now initilized.");
    }
     
    protected void initilizeAllElements() {
    //	initialize all @FindBy
    	PageFactory.initElements(driver, this);	    
        System.out.println("All @FindBy elements have been initilized.");
    }
  
    protected void enterCredentials(String simUser, String simPassword) throws InterruptedException {
    	change1stLevelFrame(); 
    	waitWithPolling(20000, 2000, "fldUserName");
                     
    	LogInName.sendKeys(simUser);
    	LogInPass.sendKeys(simPassword);
    	btnLogIn.click();    
    	System.out.println("UserName & Password: Success!");
    	Thread.sleep(10000);
    }
    
    protected void activate_maintenance() throws InterruptedException {
    	//change tab to Maintenance
        System.out.println("Switching tab to Maintenance...Please wait.");
    	tabMaintenance.click();
    	waitWithPolling(20000, 2000, "btnBackDoor");
    	btnBackDoor.click();
        Thread.sleep(10000);
        
        //change to 2nd level frame
        change2ndLevelFrame();
        btnBackDoorConfirm.click();
        System.out.println("Partial maintenance page has been activated...Please wait.");
        Thread.sleep(10000);
        
        //change to first level frame
        change1stLevelFrame();       
    }   
    
    protected void desActivate_maintenance() throws InterruptedException {
    	//change tab to Maintenance
        System.out.println("Switching tab to Maintenance...Please wait.");
    	tabMaintenance.click();
    	waitWithPolling(20000, 2000, "btnBackDoor");
    	btnBackDoor.click();
        Thread.sleep(10000);
        
        //change to 2nd level frame
        change2ndLevelFrame();
        btnBackDoorConfirm.click();
        System.out.println("Partial maintenance page has been desactivated...Please wait.");
        Thread.sleep(10000);
        
        //change to first level frame
        change1stLevelFrame();
        cleanDeprecatedFiles();        
    }    

    private void cleanDeprecatedFiles() throws InterruptedException {
        //clean deprecated file
        iSelect = new Select(selectfldCleanupaction);
        iSelect.selectByVisibleText("Executer deprecatedfiles.txt");
        btnMaintenanceConfirm.click();
        Thread.sleep(10000);
        
        //change to second level iframe
        change2ndLevelFrame();
        btnBackDoorConfirm.click();
        Thread.sleep(10000);
        
        //change to first level frame
        change1stLevelFrame();
        Thread.sleep(10000);
    }
    
    protected void gestionInstances() throws InterruptedException {
    	//clear cache & refresh application 
        tabGestionInstances.click();        
        waitWithPolling(20000, 2000, "fldAction");
        System.out.println("Switching tab to Gestion Instance.");
        
        iSelect = new Select(lstFldAction);
        iSelect.selectByVisibleText("Effacer la cache sécurisée");
        btnGestionInstancesConfirm.click();
        System.out.println("You are now clearing the cache...Please wait.");
        Thread.sleep(10000);
        iSelect.selectByVisibleText("Réinitialiser la mémoire applicative");        
        btnGestionInstancesConfirm.click();
        System.out.println("You are not reinitiliazing memories...Please wait.");
        Thread.sleep(10000);
    }
    
    protected void closeBrowser() {
        System.out.println("\n\nClosing browser.");
    	driver.close();
    	driver.quit();    	
    }
}