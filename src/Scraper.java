import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Scraper {
    private WebDriver driver;

    public Scraper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean searchVideo(String searchQuery) {
        driver.get("https://www.youtube.com/results?search_query=" + searchQuery);
        try{        
            driver.findElement(By.cssSelector("yt-formatted-string.ytd-video-renderer"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<String> getVideoTitles() {
        ArrayList<String> titles = new ArrayList<String>();
        List<WebElement> elements = driver.findElements(By.cssSelector("yt-formatted-string.ytd-video-renderer"));
        for (int i = 0; i < elements.size(); i+=4) {
            titles.add(elements.get(i).getText());
        }
        return titles;
    }

    public ArrayList<String> getTop5(){
        ArrayList<String> titles = getVideoTitles();
        ArrayList<String> top5 = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            if(titles.size() > i) {
                top5.add(titles.get(i));
            } else{
                break;
            }
        }
        return top5;
    }

    public ArrayList<String> getMeta(int i){
        ArrayList<String> meta = new ArrayList<String>();

        List<WebElement> elements = driver.findElements(By.cssSelector("yt-formatted-string.ytd-video-renderer"));
        meta.add(elements.get(i * 4).getText());

        elements = driver.findElements(By.cssSelector("span.ytd-video-meta-block"));
        meta.add(elements.get(i * 2).getText());

        List<WebElement> links = driver.findElements(By.cssSelector("a.yt-simple-endpoint.style-scope.ytd-video-renderer"));
        // for(int j = 0; j < links.size(); j++) {
        //     System.out.println(links.get(j).getAttribute("href"));
        // }
        meta.add(links.get(i*2).getAttribute("href"));

        List<WebElement> channels = driver.findElements(By.cssSelector("a.yt-simple-endpoint.style-scope.yt-formatted-string"));
        meta.add(channels.get(i*2 + 1).getText());
        // for(int j = 0; j < links.size(); j++) {
        //     System.out.println(channels.get(j).getText());
        // }
        
        

        return meta;
    }

 }
    

