package week5Assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignincident {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
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
		findElement.sendKeys("Incident");
		Thread.sleep(2000);
		findElement.sendKeys(Keys.ENTER);

		//To get an Existing incident ID
		driver.switchTo().frame(0);
		WebElement Incnum = driver.findElement(By.xpath("(//td[@class='vt']/a)[1]"));
		String text = Incnum.getText();
		System.out.println(text);
		driver.switchTo().defaultContent();

		//click on open and Search for the existing incident and click on  the incident
		driver.findElement(By.xpath("(//div[text()='Open'])[1]")).click();
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		WebElement send = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		send.sendKeys(text);
		Thread.sleep(2000);
		send.sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("(//td[@class='vt'])[1]")).click();
		//click assignment group search button
		driver.findElement(By.xpath("(//button[@class='btn btn-default'])[5]")).click();

		driver.switchTo().defaultContent();

		//Assign the incident to  Software group

		//window handling
		String parentWindow = driver.getWindowHandle();
		//System.out.println(parentWindow);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		//System.out.println(driver.getWindowHandle());
		WebElement search = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		search.sendKeys("software");
		Thread.sleep(2000);
		search.sendKeys(Keys.ENTER);
		WebElement soft = driver.findElement(By.xpath("//a[text()='Software']"));
		String text1=soft.getText();
		System.out.println(text1);
		soft.click();
		driver.switchTo().window(parentWindow);


		//assigned to don good life

				driver.switchTo().frame(0);
				driver.findElement(By.xpath("//button[@id='lookup.incident.assigned_to']")).click();
				Set<String> windowHandles1 = driver.getWindowHandles();
				List<String> window1 = new ArrayList<String>(windowHandles1);
				driver.switchTo().window(window1.get(1));
				//System.out.println(driver.getWindowHandle());
				WebElement don = driver.findElement(By.xpath("//tr//td//a[text()='ITIL User']"));
				String text2 =don.getText();
				System.out.println(text2);
				don.click();
				driver.switchTo().window(parentWindow);

		//update the incident with work notes
		driver.switchTo().frame(0);
		WebElement findElement2 = driver.findElement(By.xpath("((//div[@class='sn-stream-textarea-container'])[1]//following::textarea)[1]"));
		findElement2.sendKeys("hi testleaf");

		//click update
		driver.findElement(By.xpath("//button[@id='sysverb_update_bottom']")).click();

		//verify the assignment group
		String text3=driver.findElement(By.xpath("//a[text()='Software']")).getText();
		System.out.println(text3);
		String text4=driver.findElement(By.xpath("//a[text()='ITIL User']")).getText();
		System.out.println(text4);

		if(text1.equals(text3))
		{
			System.out.println("Software group Instance is updated");
		}
		else
		{
			System.out.println("Software group Instance in not updated");
		}
				if(text2.equals(text4))
			{
					System.out.println("Assigned to Instance is updated");
				}
				else
			{
					System.out.println("Assigned to Instance in not updated");
				}


	}
}
