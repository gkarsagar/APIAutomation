package com.APITest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import util.ExcelUtils;

public class LoginTest extends ExcelUtils {
	@org.testng.annotations.DataProvider(name = "LoginData")
    public Object[][] getData() throws Exception {
        String path = "C:\\Users\\sagar\\OneDrive\\Desktop\\testFile.xlsx";
        return ExcelUtils.getExcelData(path, "LoginData");
    }

    @org.testng.annotations.Test(dataProvider = "LoginData")
    public void loginTest(String Username, String Passwords) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.demo.guru99.com/V4/");
        driver.findElement(By.xpath("//a[text()='Bank Project']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Username);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Passwords);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // Add assertion or verification step here

        driver.quit();
    }
}

