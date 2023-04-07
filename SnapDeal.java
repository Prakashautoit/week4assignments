package com.week4.day2.assessment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class SnapDeal {
	
	

	public static void main(String[] args) throws InterruptedException {
		String shoesbeforsort =null;
		String shoesaftersort =null;
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		Thread.sleep(10000);
		//WebElement menfashion = driver.findElement(By.xpath("(//div[contains(text(),'Top Categories')]//following::span[contains(text(),'Men')])[1]"));c
		WebElement men = driver.findElement(By.xpath("//span[@class='catText']"));
		Actions act =new Actions(driver);
		act.moveToElement(men).perform();
		Thread.sleep(4000);
		WebElement footwear = driver.findElement(By.xpath("//span[text()='Footwear']//following::span[contains(text(),'Sports Shoes')][1]"));
		Actions acts =new Actions(driver);
		acts.click(footwear).perform();
		String totalsports = driver.findElement(By.xpath("//div[contains(@class,'sub-cat-count')]")).getText();
		System.out.println("Total Sports Shoes Count is " +totalsports);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		//List<WebElement> tshoes = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		//driver.findElement(By.xpath("//span[@class='lfloat product-price']")).getText();
		
		
		String p1 = driver.findElement(By.xpath("(//p[@class='product-title'])[1]")).getText();
		String p2 = driver.findElement(By.xpath("(//p[@class='product-title'])[2]")).getText();
		String p3 = driver.findElement(By.xpath("(//p[@class='product-title'])[3]")).getText();
		String p4 = driver.findElement(By.xpath("(//p[@class='product-title'])[4]")).getText();
		String p5 = driver.findElement(By.xpath("(//p[@class='product-title'])[5]")).getText();
		List<String> bsortlist =new ArrayList<String>();
		bsortlist.add(p1);
		bsortlist.add(p2);
		bsortlist.add(p3);
		bsortlist.add(p4);
		bsortlist.add(p5);
		
		
//		Set<String> bsortset =new LinkedHashSet<String>();
//		bsortset.add(p1);
//		bsortset.add(p2);
//		bsortset.add(p3);
//		bsortset.add(p4);
//		bsortset.add(p5);
		System.out.println(bsortlist);		
		driver.findElement(By.xpath("//div[contains(text(),'Popularity')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//li[@class='search-li'])[1]")).click();
		Thread.sleep(5000);
		String p6 = driver.findElement(By.xpath("//p[text()='ASIAN Black Training Shoes']")).getText();
		String p7 = driver.findElement(By.xpath("//p[text()='ASIAN White Training Shoes']")).getText();
		String p8 = driver.findElement(By.xpath("//p[text()='Figor Stylish/Comfortable Multi Color Training Shoes']")).getText();
		String p9 = driver.findElement(By.xpath("//p[text()='Columbus Rider-DGreyAqua Gray Training Shoes']")).getText();
		String p10 = driver.findElement(By.xpath("//p[text()='Columbus Navy Training Shoes']")).getText();
		List<String> asortlist =new ArrayList<String>();
		asortlist.add(p6);
		asortlist.add(p7);
		asortlist.add(p8);
		asortlist.add(p9);
		asortlist.add(p10);
		System.out.println(asortlist);
		
//		Set<String> asortset =new LinkedHashSet<String>();
//		asortset.add(p6);
//		asortset.add(p7);
//		asortset.add(p8);
//		asortset.add(p9);
//		asortset.add(p10);
		//System.out.println(asortset);
		
		if(asortlist.equals(bsortlist)) {
			
			System.out.println("No sorting happened");
		}
		else {
			
			System.out.println("Sorting is successfull");
		}

	}

}
