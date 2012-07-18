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
 * Use FirefoxDriver as webdriver instance.
 * 
 */
public class FirefoxRunner extends AbstractRunner {

    /** 
     * Creates a new local FirefoxRunner.
     */
	public FirefoxRunner() {
		super();
		webdriver = new FirefoxDriver();
	}
	
    /** 
     * Creates a new local FirefoxRunner.
     * 
     * @param profile_path Path to local firefox profile
     */
	public FirefoxRunner(String profile_path) {
		super();
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		File file = new File(profile_path);
		FirefoxProfile fp = new FirefoxProfile(file);
		dc.setCapability(FirefoxDriver.PROFILE, fp);
		webdriver = new FirefoxDriver(dc);
	}

}
