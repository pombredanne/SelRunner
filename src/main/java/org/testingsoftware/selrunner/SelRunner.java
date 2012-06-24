package org.testingsoftware.selrunner;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import com.google.common.base.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testingsoftware.selrunner.exceptions.NoWebElementSelectedException;

import fitnesse.slim.converters.ConverterRegistry;

/**
 * Simple Webdriver adapter intended to be used with Fitnesse/Slim.
 *
 */
public class SelRunner {
    private static WebDriver webdriver;
	private WebElement element;
	private int timeout = 45;

    public SelRunner() {
        newDriver("Firefox");
    }

	public SelRunner(String browserType) {
		newDriver(browserType);
	}

	public SelRunner(String browserType, String profile_path) {
		if (browserType.equals("Firefox")) {
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			File file = new File(profile_path); 
			FirefoxProfile fp = new FirefoxProfile(file);
			dc.setCapability(FirefoxDriver.PROFILE, fp);
			webdriver = new FirefoxDriver(dc);
			ConverterRegistry.addConverter(By.class, new ByConverter());
		} else {
			newDriver(browserType);
		}
	}
		
	public void clear() {
		element.clear();
	}

    public void click() {
        element.click();
    }

    public void click(By by) {
        element = webdriver.findElement(by);
        click();
    }

    private Function<WebDriver, Object> condition(final String javascript) {
		// wait until Javascript snippet evaluates to true
		return new Function<WebDriver, Object>() {
			public Object apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(javascript);
			}
		};
	}

	public boolean containsText(String text) {
		if (element == null) {
			throw new NoWebElementSelectedException();
		}
		return element.getText().contains(text);
	}

	private WebDriverWait doWait() {
		return new WebDriverWait(webdriver, timeout);
	}

	private Function<WebDriver, Boolean> elementInvisible(final By by) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (driver.findElements(by).size() == 0) {
					return true;
				}
				WebElement element = driver.findElement(by);
				return !element.isDisplayed();
			}
		};
	}

	private Function<WebDriver, Boolean> elementLocatedIsNotPresent(final By by) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElements(by).size() == 0;
			}
		};
	}
	
	private Function<WebDriver, Boolean> elementLocatedIsPresent(final By by) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElements(by).size() > 0;
			}
		};
	}
	
    private Function<WebDriver, Boolean> elementVisible(final By by) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (driver.findElements(by).size() == 0) {
					return false;
				}
				WebElement element = driver.findElement(by);
				return element.isDisplayed();
			}
		};
	}

	public boolean findElement(By by) {
		if (hasElement(by)) {
			element = webdriver.findElement(by);
			return true;
		}
		return false;
	}

	public List<WebElement> findElements(By by) {
		return webdriver.findElements(by);
	}
	
	public boolean hasElement(By by) {
		return numberOfElements(by) > 0;
	}

	public boolean isDisplayed() {
		return element.isDisplayed();
	}	

	public boolean isSelected() {
		return element.isSelected();
	}

	private void newDriver(String browserType) {
    	if (browserType.equals("Chrome")) {
    		webdriver = new ChromeDriver();
    	} else if (browserType.equals("IE")) {
    		// I do not have IE available so please let me know if it works fine 
    		webdriver = new InternetExplorerDriver();
    	} else {
			webdriver = new FirefoxDriver();
    	}
        ConverterRegistry.addConverter(By.class, new ByConverter());
	}

	public int numberOfElements(By by) {
		return findElements(by).size();
	}
	
	public void open(String url) {
        webdriver.get(url);
    }

	public void selectByIndex(int index) {
		new Select(element).selectByIndex(index);
	}

	public void selectByText(String text) {
		new Select(element).selectByVisibleText(text);
	}

	public void selectByValue(String value) {
		new Select(element).selectByValue(value);
	}	

	public void sendKey(Keys key) {
		element.sendKeys(key);
	}	

	public void sendKeys(CharSequence[] keys) {
		element.sendKeys(keys);
	}

	public void setSelected() {
		if (!isSelected()) {
			toggle();
		}
	}

	public void setTimeout(int seconds) {
		timeout = seconds;
	}

	public void setUnselected() {
		if (isSelected()) {
			toggle();
		}
	}

	public void stop() {
        webdriver.close();
    }

	public String text() {
		return element.getText();
	}

	public String title() {
        return webdriver.getTitle();
    }

	public void toggle() {
		element.click();
	}

	public void type(String keys) {
		element.sendKeys(keys);
	}	

	public void wait(int milliseconds) throws Exception {
		Thread.sleep(milliseconds);
	}
	
	public void waitForCondition(String snippet) {
		doWait().until(condition("return (" + snippet + ");"));
	}

	public void waitForElementInvisible(By by) {
		doWait().until(elementInvisible(by));
	}

	public void waitForElementNotPresent(By by) {
		doWait().until(elementLocatedIsNotPresent(by));
	}

	public void waitForElementPresent(By by) {
		doWait().until(elementLocatedIsPresent(by));
	}
	
	public void waitForElementVisible(By by) {
		doWait().until(elementVisible(by));
	}
}
