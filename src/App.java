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

            //lets try this using class attributes instead of hard coding
            Scraper scraper = new Scraper(driver);
            System.out.println(scraper.searchVideo(line));
            System.out.println("\n" + scraper.getVideoTitles());
            System.out.println("\n" + scraper.getTop5());

            System.out.println("\n" + scraper.getMeta(1));
        }
        
        scanner.close();
        driver.quit();   
    }
}
