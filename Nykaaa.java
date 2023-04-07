package com.week4.day2.assessment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

/*1) Go to https://www.nykaa.com/
2) Mouseover on Brands and Search L'Oreal Paris
3) Click L'Oreal Paris
4) Check the title contains L'Oreal Paris(Hint-GetTitle)
5) Click sort By and select customer top rated
6) Click Category and click Hair->Click haircare->Shampoo
7) Click->Concern->Color Protection
8)check whether the Filter is applied with Shampoo
9) Click on L'Oreal Paris Colour Protect Shampoo
10) GO to the new window and select size as 175ml
11) Print the MRP of the product
12) Click on ADD to BAG
13) Go to Shopping Bag 
14) Print the Grand Total amount
15) Click Proceed
16) Click on Continue as Guest
17) Check if this grand total is the same in step 14
18) Close all windows*/


public class Nykaaa {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions act =new Actions(driver);
		act.moveToElement(brands).perform();
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L'Oreal Paris");
		driver.findElement(By.xpath("//div[@class='SearchIcon']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='brandSearchBox']/following::a")).click();
		String title = driver.getTitle();
		System.out.println(title);
		if(title.equalsIgnoreCase("L'Oreal Paris: Buy L'Oreal Paris Products Online at Best Price in India")) {
			
			System.out.println("Expected Title is printed");
		}
		else {
			
			System.out.println("Not Expected Title");
		}
		
		driver.findElement(By.xpath("//span[text()='Sort By : popularity']")).click();
		driver.findElement(By.xpath("(//div[contains(@class,'control-indicator radio ')])[4]")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//span[text()='Hair Care'])[2]")).click();
		driver.findElement(By.xpath("(//div[contains(@class,'control-indicator checkbox ')])[2]")).click();
		WebElement concern = driver.findElement(By.xpath("//span[text()='Concern']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", concern);
		driver.findElement(By.xpath("(//span[text()='Concern']//following::div[contains(@class,'control-indicator checkbox ')])[11]")).click();
		boolean selected = driver.findElement(By.xpath("(//span[text()='Filters Applied']//following::span[text()='Color Protection'])[1]")).isDisplayed();
		if(selected==true) {
			
			System.out.println("Filters are applied");
		}else {
			
			System.out.println("Filters were not applied");
		}
		String parent = driver.getWindowHandle();
		WebElement allproducts = driver.findElement(By.xpath("//div[text()='All Products']/following::div[@class='css-xrzmfa']"));
		JavascriptExecutor executor3 = (JavascriptExecutor)driver;
		executor3.executeScript("arguments[0].click();", allproducts);
		Set<String> childs = driver.getWindowHandles();
		List<String> winlist = new ArrayList<String>(childs);
		int size = childs.size();
		//System.out.println(size);	
		
		for (String each : winlist) {
			
			if (!each.equals(parent)) {
				driver.switchTo().window(each);
			}
			
		}
		driver.findElement(By.xpath("//span[text()='180ml']")).click();
		Thread.sleep(5000);
		String mrp = driver.findElement(By.xpath("(//span[text()='₹209'])[1]")).getText();
		System.out.println("MRP of the product is " +mrp);
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		Thread.sleep(10000);
		WebElement cart = driver.findElement(By.xpath("(//*[name()='svg' and @height='24' and @width='24' and @fill='none']//*[local-name()='path'])[10]"));
		Actions builder =new Actions(driver);
		builder.click(cart).perform();
		driver.switchTo().frame(0);
		String grandtotal = driver.findElement(By.xpath("//p[text()='Price Details']//following::span[contains(text(),'220')]")).getText();
		System.out.println("Grand Total is " +grandtotal);
		String parent2 = driver.getWindowHandle();
		System.out.println("Parent2 window   " +parent2);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		Set<String> child2 = driver.getWindowHandles();
		List<String> winlists2 = new ArrayList<String>(child2);
		int size2 = child2.size();
		//System.out.println(size);	
		
		for (String each : winlists2) {
			System.out.println("Child2 windows   " +each);
			
			if (!each.equals(parent2)) {
				driver.switchTo().window(each);
			}
			else {
				driver.switchTo().window(parent2);
			}
			
		}
		
		
		
		driver.findElement(By.xpath("//button[text()='Continue as guest']")).click();
		driver.findElement(By.xpath("(//img[@src='https://adn-static1.nykaa.com/media/wysiwyg/Payments/Clear.svg'])[1]")).click();
		String FinalTotal = driver.findElement(By.xpath("//p[text()='₹220']")).getText();
		System.out.println("FinalTotal is " +FinalTotal);
		
		if(grandtotal.equals(FinalTotal)) {
			
			System.out.println("Final Total and Grand Total Prices are same");
		}
		else {
			
			System.out.println("Prices are different");
		}

	}

}
