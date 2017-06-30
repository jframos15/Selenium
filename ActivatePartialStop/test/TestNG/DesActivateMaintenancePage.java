package TestNG;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DesActivateMaintenancePage {

    @Test(priority=0, enabled=true, description="Old National Bank Sp2 2016")
    @Parameters({ "simUser", "simPassword" })
    public void oldNationalBank_sp2_2016() throws InterruptedException {
        SimSettings ONB = new SimSettings();
        ONB.initializeChrome();
        SimSettings.driver.get("http://msrvaq11vm.technomedia.ca/sigal_60/2017sp1/sp_oldnationalbank/_sim/");
        System.out.println("Opening Old National Bank Sim...");        
        Thread.sleep(10000);
        
        ONB.enterCredentials();                
        ONB.maintenance();
        ONB.gestionInstances();
        ONB.closeBrowser();
    }
    
    @Test(priority=1, enabled=true, description="Polymont Sp2 2016")
    @Parameters({ "simUser", "simPassword" })
    public void polymont_sp2_2016() throws InterruptedException {
        SimSettings ONB = new SimSettings();
        ONB.initializeChrome();
        SimSettings.driver.get("http://msrvaq11vm.technomedia.ca/sigal_60/2017sp1/sp_polymont/_sim/");
        System.out.println("Opening Old National Bank Sim...");        
        Thread.sleep(10000);
        
        ONB.enterCredentials();                
        ONB.maintenance();
        ONB.gestionInstances();
        ONB.closeBrowser();
    }
}