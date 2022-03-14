package week5.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Service {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://dev91762.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.switchTo().frame(0);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Servicenow@123");
		driver.findElement(By.id("sysverb_login")).click();
		driver.switchTo().defaultContent();

		//search incident in filter navigator
		Thread.sleep(3000);
		WebElement findElement = driver.findElement(By.id("filter"));
		findElement.sendKeys("Incidents");
		Thread.sleep(2000);
		findElement.sendKeys(Keys.ENTER);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[text()='New']")).click();



		//get incident number
		WebElement find=driver.findElement(By.xpath("//input[@id='incident.number']"));
		String text=find.getAttribute("value");
		System.out.println(text);

		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Testleaf");
		// click submit
		driver.findElement(By.xpath("(//button[text()='Submit'])[1]")).click();
		driver.switchTo().defaultContent();

		//copy the incident number in search box 
		driver.switchTo().frame(0);
		WebElement ele1=driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		ele1.sendKeys(text);
		ele1.sendKeys(Keys.ENTER);
		
		WebElement ele2=driver.findElement(By.xpath("//td[@class='vt']//a"));
		String text1=ele2.getText();
		System.out.println(text1);
		driver.switchTo().defaultContent();

		//verify instance is created or not
		if(text.equals(text1))
		{
			System.out.println("instance is created");
		}
		else
		{
			System.out.println("instance is not created");
		}
		driver.close();

	}
}