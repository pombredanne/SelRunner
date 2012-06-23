package org.testingsoftware.selrunner;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testingsoftware.selrunner.exceptions.NoWebElementSelectedException;

import com.google.common.base.Function;

import fitnesse.slim.converters.ConverterRegistry;
//import fitnesse.slim.Slim;

/**
 * Simple Webdriver adapter intended to be used with Fitnesse/Slim.
 *
 */
public class SelRunner {
    private static WebDriver webdriver;
    //private static Wait<WebDriver> wait;
	private WebElement element;
	private int timeout = 45;

    public SelRunner() {
        webdriver = new FirefoxDriver();
        //wait = new WebDriverWait(webdriver, 30);
        ConverterRegistry.addConverter(By.class, new ByConverter());
    }

    public SelRunner(String browserType) {
        webdriver = setBrowser(browserType);
        //wait = new WebDriverWait(webdriver, 30);
    }

    private FirefoxDriver setBrowser(String browserType) {
        if (StringUtils.isEmpty(browserType)) {
        }
        return new FirefoxDriver();
    }

    public void open(String url) {
        //System.out.println("url: " + url);

        webdriver.get(url);
    }

    public void click() {
        element.click();
    }

    public void click(By by) {
        element = webdriver.findElement(by);
        click();
    }

	public boolean containsText(String text) {
		if (element == null) {
			throw new NoWebElementSelectedException();
		}
		return element.getText().contains(text);
	}

	public boolean findElement(By by) {
		if (hasElement(by)) {
			element = webdriver.findElement(by);
			return true;
		}
		return false;
	}

	public boolean hasElement(By by) {
		return numberOfElements(by) > 0;
	}

	public int numberOfElements(By by) {
		return findElements(by).size();
	}
	
	public List<WebElement> findElements(By by) {
		return webdriver.findElements(by);
	}
	
    public String title() {
        return webdriver.getTitle();
    }

	public void selectByValue(String value) {
		new Select(element).selectByValue(value);
	}

	public void selectByText(String text) {
		new Select(element).selectByVisibleText(text);
	}
	
	public void selectByIndex(int index) {
		new Select(element).selectByIndex(index);
	}

	public void clear() {
		element.clear();
	}	

	public void type(String keys) {
		element.sendKeys(keys);
	}

	public void sendKeys(CharSequence[] keys) {
		element.sendKeys(keys);
	}

	public void sendKey(Keys key) {
		element.sendKeys(key);
	}
	
	public void toggle() {
		element.click();
	}

	public String text() {
		return element.getText();
	}

	public boolean isSelected() {
		return element.isSelected();
	}

	public void setSelected() {
		if (!isSelected()) {
			toggle();
		}
	}	

	public void setUnselected() {
		if (isSelected()) {
			toggle();
		}
	}	

	public void setTimeout(int seconds) {
		timeout = seconds;
	}

	public void wait(int milliseconds) throws Exception {
		Thread.sleep(milliseconds);
	}

	public void waitForElementVisible(By by) {
		doWait().until(elementVisible(by));
	}

	public void waitForElementInvisible(By by) {
		doWait().until(elementInvisible(by));
	}

	public void waitForElementPresent(By by) {
		doWait().until(elementLocatedIsPresent(by));
	}

	public void waitForElementNotPresent(By by) {
		doWait().until(elementLocatedIsNotPresent(by));
	}

	public void waitForCondition(String javascript) {
		doWait().until(condition("return (" + javascript + ");"));
	}

	private WebDriverWait doWait() {
		return new WebDriverWait(webdriver, timeout);
	}

	private Function<WebDriver, Object> condition(final String javascript) {
		// wait until Javascript snippet evaluates to true
		return new Function<WebDriver, Object>() {
			public Object apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(javascript);
			}
		};
	}	

	public boolean isDisplayed() {
		return element.isDisplayed();
	}
	
	private Function<WebDriver, Boolean> elementLocatedIsPresent(final By by) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElements(by).size() > 0;
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
	
	public void stop() {
        webdriver.close();
    }
}
