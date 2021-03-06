package commands;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Scraper extends ListenerAdapter {
    private WebDriver driver;

    public Scraper(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("scrape")) {
            event.deferReply().queue();

            searchVideo(event.getOption("query").getAsString());

            ArrayList<String> titles;

            if (event.getOption("getall") != null && event.getOption("getall").getAsBoolean()) {
                titles = getVideoTitles();
                System.out.println(titles);
            } else {
                titles = getTop5();
            }
            String reply = "";

            if (event.getOption("result") != null) {
                int result = event.getOption("result").getAsInt() - 1;
                if (result >= titles.size()) {
                    event.getHook().editOriginal("Result number too high").queue();
                    return;
                }
                if (event.getOption("showdata") != null && event.getOption("showdata").getAsBoolean()) {
                    ArrayList<String> meta = getMeta(result);
                    reply = meta.get(0) + "\n" + meta.get(3) + "\n" + meta.get(1) + "\n" + meta.get(2);
                }
                event.getHook().editOriginal(titles.get(result)).queue();
            } else {
                for (int i = 0; i < titles.size(); i++) {
                    reply += (i + 1) + ". " + titles.get(i) + "\n";
                }
            }

            System.out.println(reply);
            event.getHook().editOriginal(reply).queue();
        }
    }

    //searches for the query in youtube
    //NOTE: Must be used before other method calls or they will not work correctly
    public boolean searchVideo(String searchQuery) {
        driver.get("https://www.youtube.com/results?search_query=" + searchQuery);
        try{        
            driver.findElement(By.cssSelector("yt-formatted-string.ytd-video-renderer"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //gets all video titles in search results
    public ArrayList<String> getVideoTitles() {
        ArrayList<String> titles = new ArrayList<String>();
        List<WebElement> elements = driver.findElements(By.cssSelector("yt-formatted-string.ytd-video-renderer"));
        
        for (int i = 0; i < elements.size(); i+=4) {
            titles.add(elements.get(i).getText());
        }
        
        return titles;
    }

    //gets titles for the top 5 search results
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

    //gets metadata for the selected video in the search results list
    public ArrayList<String> getMeta(int i){
        ArrayList<String> meta = new ArrayList<String>();
        
        //Grabs title of selected video, sends to list
        List<WebElement> elements = driver.findElements(By.cssSelector("yt-formatted-string.ytd-video-renderer"));
        meta.add(elements.get(i * 4).getText());

        //Grabs view count and release date of selected video
        elements = driver.findElements(By.cssSelector("div.text-wrapper.style-scope.ytd-video-renderer .ytd-video-meta-block"));
        meta.add(elements.get(i * 2).getText());

        //Grabs video link of selected video
        List<WebElement> links = driver.findElements(By.cssSelector("div.text-wrapper.style-scope.ytd-video-renderer a.yt-simple-endpoint.style-scope.ytd-video-renderer"));
        meta.add(links.get(i*2).getAttribute("href"));

        //Grabs channel name
        List<WebElement> channels = driver.findElements(By.cssSelector("div.text-wrapper.style-scope.ytd-video-renderer .ytd-channel-name div"));
        // print all items
        List<String> raw_channels = new ArrayList<String>();
        for (WebElement channel : channels) {
            String text = channel.getText();
            if (text != null && text.length() > 0) {
                //System.out.println(text);
                raw_channels.add(text);
            }
        }
        meta.add(raw_channels.get(i));

        return meta;
    }

 }
    

