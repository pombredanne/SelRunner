package org.testingsoftware.selrunner;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeRunner extends AbstractRunner {

	public ChromeRunner() {
		super();
		webdriver = new ChromeDriver();
	}

}
