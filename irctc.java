package com.week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class irctc {

	public static void main(String[] args) {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(option);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		String parent = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()=' FLIGHTS ']")).click();
		Set<String> childs = driver.getWindowHandles();
		List<String> a =new ArrayList<String>(childs);
		 
			for (String each : a) {
				
				if(!parent.equals(a)) {
					driver.switchTo().window(each);
					
				}
				
			}
			String title = driver.getTitle();
			System.out.println(title);
			
		}
		
		
	}


