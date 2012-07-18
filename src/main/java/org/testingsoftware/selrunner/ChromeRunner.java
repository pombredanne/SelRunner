package org.testingsoftware.selrunner;

import org.openqa.selenium.chrome.ChromeDriver;

/** 
 * Use ChromeDriver as webdriver instance.
 * 
 */
public class ChromeRunner extends AbstractRunner {

    /** 
     * Creates a new ChromeRunner.
     */
	public ChromeRunner() {
		super();
		webdriver = new ChromeDriver();
	}

}
