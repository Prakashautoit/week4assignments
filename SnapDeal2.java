package com.week4.day2.assessment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class SnapDeal2 {

	public static void main(String[] args) throws InterruptedException, IOException {
		String shoesbeforsort = null;
		String shoesaftersort = null;
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		Thread.sleep(10000);
		WebElement men = driver.findElement(By.xpath("//span[@class='catText']"));
		Actions act = new Actions(driver);
		act.moveToElement(men).perform();
		Thread.sleep(4000);
		WebElement footwear = driver.findElement(
				By.xpath("//span[text()='Footwear']//following::span[contains(text(),'Sports Shoes')][1]"));
		Actions acts = new Actions(driver);
		acts.click(footwear).perform();
		String totalsports = driver.findElement(By.xpath("//div[contains(@class,'sub-cat-count')]")).getText();
		System.out.println("Total Sports Shoes Count is " + totalsports);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();

		List<WebElement> findElements = driver.findElements(By.xpath("(//p[@class='product-title'])"));
		List<String> bsortlist = new ArrayList<String>();
		int size = findElements.size();
		for (int i = 0; i < size; i++) {

			String text1 = findElements.get(i).getText();
			System.out.println(text1);
			bsortlist.add(text1);

		}
		

		driver.findElement(By.xpath("//div[contains(text(),'Popularity')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//li[@class='search-li'])[1]")).click();
		Thread.sleep(5000);
		List<WebElement> findElementss = driver.findElements(By.xpath("(//p[@class='product-title'])"));
		List<String> asortlist = new ArrayList<String>();
		int size2 = findElementss.size();
		for (int i = 0; i < size2; i++) {

			String text2 = findElementss.get(i).getText();
			asortlist.add(text2);
			System.out.println(text2);

		}

		

		if (asortlist.equals(bsortlist)) {

			System.out.println("No sorting happened");
		} else {

			System.out.println("Sorting is successfull");
		}
		
		driver.findElement(By.xpath("(//input[@class='input-filter'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@class='input-filter'])[1]")).sendKeys("550");
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).sendKeys("650");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		boolean firstdisplayed = driver.findElement(By.xpath("(//span[contains(text(),'Clear')])[2]")).isDisplayed();
		if(!firstdisplayed==false) {
			System.out.println("Price Filter is applied");
			
		}
		else {
			
			System.out.println("Price Filter is not applied");
		}
		Thread.sleep(5000);
		boolean seconddisplayed = driver.findElement(By.xpath("(//span[contains(text(),'Clear')])[4]")).isDisplayed();
		
		if(!seconddisplayed==false) {
			System.out.println("Colour Filter is applied");
			
		}
		else {
			
			System.out.println("Colour Filter is not applied");
		}
		
		WebElement shoeele = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(shoeele).perform();
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		Thread.sleep(10000);
		String price = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Price is " +price);
		String discount = driver.findElement(By.xpath("//span[contains(@class,'percent-desc')]")).getText();
		System.out.println("Discount is " +discount);
		File sourcefile = driver.getScreenshotAs(OutputType.FILE);
		File target =new File("./SnapDeal/Checkout.png");
		FileUtils.copyFile(sourcefile, target);
		driver.findElement(By.xpath("//div[contains(@class,'close close1 marR1')]")).click();
		

	}

}
