package week5Assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Deleteincident {

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

		//enter the incident in filter navigator
		Thread.sleep(4000);
		WebElement findElement = driver.findElement(By.id("filter"));
		findElement.sendKeys("Incidents");
		Thread.sleep(2000);
		findElement.sendKeys(Keys.ENTER);

		//get incident  first value

		driver.switchTo().frame(0);
		String num = driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).getText();
		System.out.println(num);
		WebElement findElement2 = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		findElement2.sendKeys(num);
		Thread.sleep(2000);
		findElement2.sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click(); //click incident num
		driver.switchTo().defaultContent();

		//delete the incident
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[@id='sysverb_delete_bottom']")).click();
		driver.findElement(By.xpath("//button[@id='ok_button']")).click();
		driver.switchTo().defaultContent();

		//click search box and check the delete record

		driver.switchTo().frame(0);
		Thread.sleep(5000);
		WebElement Element3 = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		Element3.sendKeys(num);
		Thread.sleep(2000);
		Element3.sendKeys(Keys.ENTER);

		// verify the deleted incident

		String text1=driver.findElement(By.xpath("//tbody[@class='list2_body']/tr/td")).getText();
		System.out.println(text1);

		String text="No records to display";
		if(text1.equals(text))
		{
			System.out.println("incident is deleted");
		}
		else

		{
			System.out.println("incident is not deleted");
		}
		Thread.sleep(3000);
		//driver.close();
	}
}