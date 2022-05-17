import java.util.Map;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// import commands.Ping;
// import net.dv8tion.jda.api.JDA;
// import net.dv8tion.jda.api.JDABuilder;
// import net.dv8tion.jda.api.entities.Guild;
// import net.dv8tion.jda.api.interactions.commands.OptionType;
// import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
// import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;


public class App {
    public static void main(String[] args) throws Exception {
        Map<String, String> env = System.getenv();
        System.setProperty("webdriver.chrome.driver", env.get("CHROMEDRIVER_PATH"));

        // JDA api = JDABuilder.createDefault(env.get("JAVACORD_TOKEN")).build();
        // api.addEventListener(new Ping());
        
        // api.awaitReady();
        // Guild guild = api.getGuildById("900193901959315517");
        
        // guild.upsertCommand("ping", "pongs").addSubcommands({new Command().setName("title").setDescription("Gets the title of a youtube video")});

        
        //starting the actual bot commands

        // selenium stuff
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.youtube.com");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // get line of text from user
            System.out.println("Search Query:");
            String line = "";
            //close scanner

            while (line.equals("")) {
                line = scanner.nextLine();
            }
            
            if (line.equals("exit")) {
                break;
            }
            
            //Auto converts symbols to encode for URL and makes sure it is a valid URL
            driver.get("https://www.youtube.com/results?search_query=" + line);
            
            //This is the title of the first video
            List<WebElement> titles = driver.findElements(By.cssSelector("yt-formatted-string.ytd-video-renderer"));
            
            if (titles.size() == 0) {
                System.out.println("No videos found");
                continue;
            }

            // Get video meta data
            List<WebElement> meta = driver.findElements(By.cssSelector("span.ytd-video-meta-block"));

            // Get video views
            // System.out.println(meta.get(0).getText());
            // // Get time posted
            // System.out.println(meta.get(1).getText());

            System.out.println("\nTop results");

            System.out.println("1. " + titles.get(0).getText());
            System.out.println("2. " + titles.get(4).getText());
            System.out.println("3. " + titles.get(8).getText());

            System.out.println("\nType in the number of the corresponding video");
            int videoNum = scanner.nextInt() - 1;

            System.out.println(meta.get(videoNum * 2).getText());
            System.out.println(meta.get(videoNum * 2 + 1).getText());

            
            List<WebElement> links = driver.findElements(By.cssSelector("a.yt-simple-endpoint.style-scope.ytd-video-renderer"));
            System.out.println(links.get(0).getAttribute("href"));
            System.out.println(links.get(2).getAttribute("href"));
            System.out.println(links.get(4).getAttribute("href"));
        }
        
        scanner.close();
        driver.quit();   
    }
}
