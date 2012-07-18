package org.testingsoftware.selrunner;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import fitnesse.slim.converters.ConverterRegistry;

/** 
 * Use RemoteWebDriver as webdriver instance.
 * 
 */
public class FirefoxRemoteRunner extends AbstractRunner {

    /** 
     * Creates a new FirefoxRemoteRunner.
     * 
     * @param selenium_srv URL of the remote selenium server e.g. "http://localhost:4444/wd/hub". 
     */
    public FirefoxRemoteRunner(String selenium_srv) {
        super();
        DesiredCapabilities dc = DesiredCapabilities.firefox();
        
        try {
            webdriver = new RemoteWebDriver(new URL(selenium_srv), dc);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
