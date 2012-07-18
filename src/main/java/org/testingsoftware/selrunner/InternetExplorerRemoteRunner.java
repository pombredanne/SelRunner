package org.testingsoftware.selrunner;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/** 
 * Use RemoteWebDriver as webdriver instance.
 * 
 */
public class InternetExplorerRemoteRunner extends AbstractRunner {

    /** 
     * Creates a new InternetExplorerRemoteRunner.
     * 
     * @param selenium_srv URL of the remote selenium server e.g. "http://localhost:4444/wd/hub". 
     */
    public InternetExplorerRemoteRunner(String selenium_srv) {
		super();
        DesiredCapabilities dc = DesiredCapabilities.internetExplorer();

		try {
            webdriver = new RemoteWebDriver(new URL(selenium_srv), dc);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

