package org.testingsoftware.selrunner;

import org.openqa.selenium.remote.DesiredCapabilities;
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
        DesiredCapabilities dc = DesiredCapabilities.phantomjs();
		webdriver = new PhantomJSDriver(dc);
	}

}
