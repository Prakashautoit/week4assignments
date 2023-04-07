package com.week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("demoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.partialLinkText("CRM")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		String parent = driver.getWindowHandle();
		System.out.println(parent);
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[1]")).click();
		Set<String> childs = driver.getWindowHandles();
		List<String> childslist = new ArrayList<String>(childs);
		
		for (String each : childslist) {
			if (!each.equals(parent)) {
						
				System.out.println(each);
				driver.switchTo().window(each);
				String title = driver.getTitle();
				System.out.println(title);
			}

		}

		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[text()='Find Contacts']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//button[text()='Find Contacts']")).click();
		driver.findElement(By.xpath("(//a[contains(@class,'linktext')])[1]")).click();
		// driver.switchTo().defaultContent();
		driver.switchTo().window(parent);
		String parent2 = driver.getWindowHandle();
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
		Set<String> childs2 = driver.getWindowHandles();
		List<String> childslist2 = new ArrayList<String>(childs2);
		for (String each : childslist2) {
			if (!each.equals(parent2)) {

				driver.switchTo().window(each);
			}

		}

		driver.findElement(By.xpath("(//a[contains(@class,'linktext')])[6]")).click();
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		Alert calert = driver.switchTo().alert();
		calert.getText();
		System.out.println("Alert Message is " + calert);
		calert.accept();
		String titlefinal = driver.getTitle();
		System.out.println(titlefinal);

	}

}
