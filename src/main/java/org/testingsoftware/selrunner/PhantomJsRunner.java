package org.testingsoftware.selrunner;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.CapabilityType;
import java.util.logging.Level;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

/** 
 * Use PhantomJSDriver as webdriver instance.
 * 
 */
public class PhantomJsRunner extends AbstractRunner {

    /** 
     * Creates a new ChromeRunner.
     */
	public PhantomJsRunner() {
		super();

		LoggingPreferences logs = new LoggingPreferences();
	    logs.enable(LogType.DRIVER, Level.OFF);

        DesiredCapabilities dc = DesiredCapabilities.phantomjs();
	    dc.setCapability(CapabilityType.LOGGING_PREFS, logs);

		webdriver = new PhantomJSDriver(dc);
	}

}
