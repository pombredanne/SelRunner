package org.testingsoftware.selrunner;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/** 
 * Use InternetExplorerDriver as webdriver instance.
 * 
 * InternetExplorerDriver only works if you set the Protected Mode settings, choose "Internet Options..."
 * from the Tools menu, and click on the Security tab. For each zone, there will be a check box at the 
 * bottom of the tab labeled "Enable Protected Mode". 
 * 
 * http://code.google.com/p/selenium/wiki/InternetExplorerDriver
 * 
 * In InternetExplorer find "Internetoptionen" > "Erweitert" > "Sicherheit" and check 
 * the option "Ausführung aktiver Inhalte in Dateien auf dem lokalen Computer zulassen"
 */
public class InternetExplorerRunner extends AbstractRunner {

    /** 
     * Creates a new InternetExplorerRunner.
     */
    public InternetExplorerRunner() {
		super();
        DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		webdriver = new InternetExplorerDriver(dc);
	}

}
