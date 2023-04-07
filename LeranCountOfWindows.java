package com.week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LeranCountOfWindows {

	public static void main(String[] args) {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://leafground.com/window.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String parent = driver.getWindowHandle();
		System.out.println("Parent  " + parent);
		driver.findElement(By.xpath("//span[text()='Open Multiple']")).click();
		driver.findElement(By.xpath("//span[text()='Open']")).click();
		Set<String> child = driver.getWindowHandles();
		List<String> winlist = new ArrayList<String>(child);
		for (String each : winlist) {
			System.out.println("Child   " +each);
		}
		int size = winlist.size();
		System.out.println(size + " child windows opened" );
	}

}
