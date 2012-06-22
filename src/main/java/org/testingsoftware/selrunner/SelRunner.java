package org.testingsoftware.selrunner;

import org.apache.commons.lang.StringUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import fitnesse.slim.converters.ConverterRegistry;
//import fitnesse.slim.Slim;

/**
 * Search Google example.
 *
 */
public class SelRunner {
    static WebDriver webdriver;
    static Wait<WebDriver> wait;

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
        System.out.println("url: " + url);

        webdriver.get(url);
    }

    public void click(By by) {
        // click link
        //webdriver.findElement(By.linkText("SelRunner")).click();
        webdriver.findElement(by).click();
    }

    public boolean verifyTextPresent(String text) {
        return webdriver.findElement(By.tagName("body")).getText().contains(
            "The Hitchhickers Guide");
    }

    public String title() {
        return getTitle();
    }

    public String getTitle() {
        return webdriver.getTitle();
    }

    public void stop() {
        webdriver.close();
    }
}
