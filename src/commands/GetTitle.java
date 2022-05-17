package commands;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GetTitle extends ListenerAdapter{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        System.out.println("hello - run two");
        if (event.getName().equals("getTitle")) {
            event.deferReply().queue();
            
            System.out.println(event.getSubcommandName());
            //WebDriver driver = new ChromeDriver();
            //Auto converts symbols to encode for URL and makes sure it is a valid URL
            //driver.get("https://www.youtube.com/results?search_query=" + line);
            //WebElement titles = driver.findElement(By.className("ytd-video-renderer"));
            //List<WebElement> titles = driver.findElements(By.cssSelector("yt-formatted-string.ytd-video-renderer"));
  
            
            
            
            
            event.reply("pong!").queue();
        }
    }
}
 