//import java.util.concurrent.TimeUnit;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\ericm\\OneDrive\\Documents\\selenium-java\\bin\\chromedriver.exe");

        // get line of text from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search Query:");
        String line = scanner.nextLine();
        //close scanner
        scanner.close();

        WebDriver driver = new ChromeDriver();
        //Auto converts symbols to encode for URL and makes sure it is a valid URL
        driver.get("https://www.youtube.com/results?search_query=" + line);
        //WebElement titles = driver.findElement(By.className("ytd-video-renderer"));
        List<WebElement> titles = driver.findElements(By.cssSelector("yt-formatted-string.ytd-video-renderer"));
  
        //This is the title of the first video
        System.out.println(titles.get(0).getText());
        driver.quit();   
        

    }
}
