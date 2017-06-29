package TestNG;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class sp2_2016 {
    //@FindBy(xpath="/html/body/form/div[@class=\"wrapper\"]/div[@id=\"pageCONTENT\"]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/input[@id=\"fldUserName\"]")
    @FindBy(id="fldUserName")
    WebElement LogInName;
    @FindBy(id="fldLogin")
    WebElement LogInPass;
    @FindBy(xpath="//*[@id=\"pageCONTENT\"]/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td/input")
    WebElement btnLogIn;
    @FindBy(xpath="//*[@id=\"menuTab\"]/ul/li[2]/a")
    WebElement tabMaintenance;
    @FindBy(id="btnBackDoor")
    WebElement btnBackDoor;
    @FindBy(xpath="/html/body/table/tbody/tr[2]/td/input[1]")
    WebElement btnBackDoorConfirm;
    @FindBy(id="fldCleanupaction")
    WebElement selectfldCleanupaction;
    @FindBy(xpath="//*[@id=\"pageCONTENT\"]/table/tbody/tr/td/table/tbody/tr[19]/td/table/tbody/tr[3]/td/input")
    WebElement btnMaintenanceConfirm;
    @FindBy(xpath="//*[@id=\"menuTab\"]/ul/li[3]/a")
    WebElement tabGestionInstances;
    @FindBy(id="fldAction")
    WebElement lstFldAction;
    @FindBy(xpath="//*[@id=\"pageCONTENT\"]/table/tbody/tr/td/table/tbody/tr[8]/td/input")
    WebElement btnGestionInstancesConfirm;
    
    
    
    WebDriver driver;	

    String simUser = "dyan";
    String simPass = "Girkey89";
    @Test(priority=0, enabled=true, description="Old National Bank")
    @Parameters({ "simUser", "simPassword" })

    public void openChrome() throws InterruptedException {		
    System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("http://msrvaq11vm.technomedia.ca/sigal_60/2017sp1/sp_polymont/_sim/");
    System.out.println("Opening Old National Bank");

    //switch frame to the first frame CONTENT
    driver.switchTo().defaultContent();
    driver.switchTo().frame("CONTENT");
    //initialize all @FindBy
    PageFactory.initElements(driver, this);	           

    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
    wait.withTimeout(20000, TimeUnit.SECONDS);
    wait.pollingEvery(2000, TimeUnit.SECONDS);
    wait.ignoring(NoSuchElementException.class);        
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fldUserName")));

    LogInName.sendKeys(simUser);       
    LogInPass.sendKeys(simPass);
    btnLogIn.click();    
    System.out.println("UserName & Password: OK!");
    Thread.sleep(10000);

    //change tab to Maintenance
    tabMaintenance.click();
    wait.withTimeout(30000, TimeUnit.SECONDS);
    wait.pollingEvery(2000, TimeUnit.SECONDS);
    wait.ignoring(NoSuchElementException.class);        
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnBackDoor")));
    btnBackDoor.click();
    Thread.sleep(5000);
    
    //change to second level iframe
    driver.switchTo().defaultContent();
    driver.switchTo().frame(driver.findElement(By.cssSelector("#divPopWindow > iframe")));
    
    btnBackDoorConfirm.click();
    Thread.sleep(5000);
    
    
    //clean deprecated file
    Select iSelect = new Select(selectfldCleanupaction);
    iSelect.selectByVisibleText("Executer deprecatedfiles.txt");
    btnMaintenanceConfirm.click();
    Thread.sleep(10000);
    
    //clear cache & refresh application 
    tabGestionInstances.click();
    wait.withTimeout(20000, TimeUnit.SECONDS);
    wait.pollingEvery(2000, TimeUnit.SECONDS);
    wait.ignoring(NoSuchElementException.class);        
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fldAction")));
    iSelect = new Select(lstFldAction);
    iSelect.selectByVisibleText("Effacer la cache sécurisée");
    btnGestionInstancesConfirm.click();
    Thread.sleep(10000);
    
    iSelect.selectByVisibleText("Réinitialiser la mémoire applicative");
    btnGestionInstancesConfirm.click();
    Thread.sleep(10000);
    }

    @Test(priority=1, enabled=true, description="Old National Bank")
    public void f() {


    }
}