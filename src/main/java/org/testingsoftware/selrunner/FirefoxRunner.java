package org.testingsoftware.selrunner;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import fitnesse.slim.converters.ConverterRegistry;

public class FirefoxRunner extends AbstractRunner {

	public FirefoxRunner() {
		super();
		webdriver = new FirefoxDriver();
	}
	
	public FirefoxRunner(String profile_path) {
		super();
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		File file = new File(profile_path);
		FirefoxProfile fp = new FirefoxProfile(file);
		dc.setCapability(FirefoxDriver.PROFILE, fp);
		webdriver = new FirefoxDriver(dc);
		ConverterRegistry.addConverter(By.class, new ByConverter());
	}

}
