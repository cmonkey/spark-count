package com.sparkcount.test;

/**
 * Created by cmonkey on 1/5/17.
 */
/*
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
*/


public class FirstTest {

    public static void main(String [] args){

        System.setProperty("webdriver.gecko.driver","/home/cmonkey/Downloads/geckodriver");
        /*
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        WebDriver driver = new FirefoxDriver(capabilities);
        String url = "http://www.amazon.in/";
        driver.get(url);
        System.out.println("Successfully opened the website www.amazon.com");
        try {
            Thread.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String title = driver.getTitle();
        int titleLength = driver.getTitle().length();
        System.out.println("Title of the page is : " + title);
        System.out.println("Length of the title is : "+ titleLength);
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals(url)){
            System.out.println("Verification Successful - The correct Url is opened.");
        }else{
            System.out.println("Verification Failed - An incorrect Url is opened.");
            System.out.println("Actual URL is : " + actualUrl);
            System.out.println("Expected URL is : " + url);
        }
        String pageSource = driver.getPageSource();
        int pageSourceLength = pageSource.length();
        System.out.println("Total length of the Page Source is : " + pageSourceLength);
        driver.close();
        */
    }
}