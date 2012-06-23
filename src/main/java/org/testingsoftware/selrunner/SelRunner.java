package org.testingsoftware.selrunner;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import fitnesse.slim.converters.ConverterRegistry;
//import fitnesse.slim.Slim;

/**
 * Simple Webdriver adapter intended for Slim use.
 *
 */
public class SelRunner {
    private static WebDriver webdriver;
    private static Wait<WebDriver> wait;
	private WebElement element;

    public SelRunner() {
        webdriver = new FirefoxDriver();
        wait = new WebDriverWait(webdriver, 30);
        ConverterRegistry.addConverter(By.class, new ByConverter());
    }

    public SelRunner(String browserType) {
        webdriver = setBrowser(browserType);
        wait = new WebDriverWait(webdriver, 30);
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
        // click link
        //webdriver.findElement(By.linkText("SelRunner")).click();
        element.click();
    }

    public void click(By by) {
        webdriver.findElement(by).click();
    }

	public boolean containsText(String text) {
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

	public void stop() {
        webdriver.close();
    }
}
