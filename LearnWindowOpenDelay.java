package com.week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LearnWindowOpenDelay {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://leafground.com/window.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String parent = driver.getWindowHandle();
		System.out.println("Parent" + parent);
		driver.findElement(By.xpath("//span[text()='Open with delay']")).click();
		Thread.sleep(10000);
		Set<String> child = driver.getWindowHandles();
		List<String> winlist = new ArrayList<String>(child);
		for (String each : winlist) {

			if (!each.equals(parent)) {
				System.out.println("Childs " + each);
				System.out.println("New windows opened");
				//driver.switchTo().window(each).close();
			}

		}
		
		driver.switchTo().defaultContent();
		//driver.switchTo().window(parent);
		String parent2 = driver.getWindowHandle();
		System.out.println(parent2);
		System.out.println(driver.getTitle()); 

		
	}

}
