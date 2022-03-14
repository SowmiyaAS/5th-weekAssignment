package week5Assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Updateincident {

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

		//search for the existing incident and click on the incident
		driver.switchTo().frame(0);
		String incnum = driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).getText();
		System.out.println(incnum);
		WebElement findElement2 = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		findElement2.sendKeys(incnum);
		Thread.sleep(2000);
		findElement2.sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("//a[@class='linked formlink']")).click(); //click incident number
		driver.switchTo().defaultContent();

		//Update the incidents with Urgency as High and State as In Progress

		//		driver.switchTo().frame(0);
		//		  WebElement dropdown = driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		//		Select high=new Select(dropdown);
		//		high.selectByIndex(0);
		//	
		//		WebElement dropdown1=driver.findElement(By.xpath("//select[@id='incident.state']"));
		//		Select high1=new Select( dropdown1);
		//		high1.selectByVisibleText("In Progress");
		//		//verify the priority and state

		driver.switchTo().frame(0);


		WebElement dropdown3 = driver.findElement(By.xpath("(//select[@id='incident.urgency']/option)[3]"));
		dropdown3.click();
		String text2 = dropdown3.getText();
		System.out.println(text2);
		System.out.println("urgency is verified"+" "+dropdown3.isSelected());

		WebElement dropdown2 = driver.findElement(By.xpath("(//select[@id='incident.state']/option)[2]"));
		dropdown2.click();
		String text = dropdown2.getText();
		System.out.println(text);
		System.out.println("state is verified"+" "+dropdown2.isSelected());
		//click update
		driver.findElement(By.xpath("//button[@id='sysverb_update_bottom']")).click(); 


	}

}
