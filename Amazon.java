package com.week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println("Price of the product is   " +price);
		String rating = driver.findElement(By.xpath("(//span[@class='a-size-base'])[1]")).getText();
		System.out.println("Rating  of the product is   " +rating);
		String parent = driver.getWindowHandle();
		//System.out.println(parent);
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		//Thread.sleep(10000);
		//String child = driver.getWindowHandle();
		//System.out.println(child);
		//driver.switchTo().window(child);
		Thread.sleep(10000);
		Set<String> childs = driver.getWindowHandles();
		List<String> winlist = new ArrayList<String>(childs);
		int size = childs.size();
		//System.out.println(size);	
		
		for (String each : winlist) {
			
			if (!each.equals(parent)) {
				driver.switchTo().window(each);
			}
			
		}
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination =new File("./amazon/oneplus.jpg");
		FileUtils.copyFile(source, destination);
		Thread.sleep(3000);
		//driver.switchTo().window(child);
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(4000);
		String subtotal = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();	
		System.out.println("Subtotal  of the product is   " +subtotal);
		//check with mentor why sub total is not get printed ?
		
	}

	
}
