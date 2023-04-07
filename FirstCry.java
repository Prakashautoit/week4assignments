package com.week4.day2.assessment;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class FirstCry {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.firstcry.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'FASHION')]"));
		Actions act =new Actions(driver);
		act.moveToElement(ele).perform();
		Thread.sleep(2000);
		WebElement party = driver.findElement(By.xpath("(//a[text()='Party Wear'])[1]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", party);
		Thread.sleep(5000);
		act.scrollByAmount(0, 300000).perform();
		Thread.sleep(10000);
		WebElement dress = driver.findElement(By.xpath("//img[contains(@alt,'Little Kangaroos Half Sleeves T-Shirt')]"));
		Thread.sleep(10000);
		//act.scrollToElement(dress);
		act.moveToElement(dress).perform();
		String parent = driver.getWindowHandle();
		driver.findElement(By.xpath("(//a[contains(text(),'12 - 18M')])[20]")).click();
		Thread.sleep(5000);
		Set<String> c2 = driver.getWindowHandles();
		for (String each2 : c2) {

			if (!each2.equals(parent)) {
				driver.switchTo().window(each2);

			}
			else {
				driver.switchTo().window(parent);
			}

		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//span[@class='step1 M16_white'])[1]")).click();

	}

}
