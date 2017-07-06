package testNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DesActivateMaintenancePage {
	
	@Test(priority=0, enabled=true, description="desactivate all partial down")
	@Parameters({ "simUser", "simPassword" })
	public void desActivate(String simUser, String simPassword) {
		
		List<String> urlList = new ArrayList<>(Arrays.asList(
				"https://hraccess-us.technomedia.com/accudyne/_sim"
				,"https://hraccess-us.technomedia.com/uhospital/_sim"
				,"https://careers.ohio.aaa.com/aaaohio/_sim"
				,"http://hraccess-us.technomedia.com/express/_sim"
				,"https://hraccess-us.technomedia.com/saramed/_sim"
				,"http://hraccess-us.technomedia.com/sony/_sim"
				,"https://hraccess-us.technomedia.com/littleton/_sim"
				,"https://hraccess-us.technomedia.com/amsted/_sim"
				,"http://hraccess-us.technomedia.com/rowanuniversity/_sim"
				));

		
		SimSettings ONB = new SimSettings();
		
		urlList.forEach(list -> {		
			System.out.println("\n\n" + list);	
			
	        try {
				ONB.initializeChrome();			
		        SimSettings.driver.get("\n\n" + list);
		        System.out.println("Opening... " + list + "PROD Sp2 2016 Sim...\n\n");        
		        Thread.sleep(10000);
		        
		        ONB.initilizeAllElements();
		        ONB.enterCredentials(simUser, simPassword);                
		        ONB.desActivate_maintenance();
		        ONB.gestionInstances();
		        ONB.closeBrowser();         
	        } catch (InterruptedException e) {				
				e.printStackTrace();
			}
		});	
	}
	  
	
        
    /*************DO NOT USER THIS METHOD for testing purpose ONLY *************************/
    @Test(priority=4, enabled=false, description="Polymont Sp2 2016")
    @Parameters({ "simUser", "simPassword" })    
    public void polymont_sp2_2016(String simUser, String simPassword) throws InterruptedException {
        SimSettings ONB = new SimSettings();
        ONB.initializeChrome();
        SimSettings.driver.get("http://msrvaq11vm.technomedia.ca/sigal_60/2017sp1/sp_polymont/_sim/");
        System.out.println("Opening Old National Bank Sim...");        
        Thread.sleep(10000);
        
        ONB.initilizeAllElements();
        ONB.enterCredentials(simUser, simPassword);                
        ONB.desActivate_maintenance();
        ONB.gestionInstances();
        ONB.closeBrowser();
    }        
}