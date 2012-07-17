package org.testingsoftware.selrunner;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class InterneExplorerRunner extends AbstractRunner {

	public InterneExplorerRunner() {
		super();
		webdriver = new InternetExplorerDriver();
	}

}
