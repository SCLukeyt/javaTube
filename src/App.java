import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import commands.Scraper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;


public class App {
    public static void main(String[] args) throws Exception {
        Map<String, String> env = System.getenv();
        System.setProperty("webdriver.chrome.driver", env.get("CHROMEDRIVER_PATH"));

        JDA api = JDABuilder.createDefault(env.get("JAVACORD_TOKEN")).build();
        
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.youtube.com/results?search_query=");

        api.addEventListener(new Scraper(driver));
        
        api.awaitReady();
        Guild guild = api.getGuildById("900193901959315517");
        
        guild.upsertCommand("scrape", "Scrape YouTube with specified search query")
            .addOptions(
                new OptionData(OptionType.STRING, "query", "The query to search on YouTube"), 
                new OptionData(OptionType.BOOLEAN, "getall", "Whether or not to get all results"), 
                new OptionData(OptionType.INTEGER, "result", "The result number to get"), 
                new OptionData(OptionType.BOOLEAN, "showdata", "Whether or not to show the data of the result")
            ).queue();
        api.upsertCommand("scrape", "Scrape YouTube with specified search query")
            .addOptions(
                new OptionData(OptionType.STRING, "query", "The query to search on YouTube"), 
                new OptionData(OptionType.BOOLEAN, "getall", "Whether or not to get all results"), 
                new OptionData(OptionType.INTEGER, "result", "The result number to get"), 
                new OptionData(OptionType.BOOLEAN, "showdata", "Whether or not to show the data of the result")
            ).queue();

        
        //starting the actual bot commands

        // selenium stuff
        // WebDriver driver = new ChromeDriver();
        // driver.get("https://www.youtube.com");
        // Scanner scanner = new Scanner(System.in);

        // while (true) {
        //     // get line of text from user
        //     System.out.println("Search Query:");
        //     String line = "";
        //     //close scanner

        //     while (line.equals("")) {
        //         line = scanner.nextLine();
        //     }
            
        //     if (line.equals("exit")) {
        //         break;
        //     }

        //     //lets try this using class attributes instead of hard coding
        //     Scraper scraper = new Scraper(driver);
        //     System.out.println(scraper.searchVideo(line));
        //     System.out.println("\n" + scraper.getVideoTitles());
        //     System.out.println("\n" + scraper.getTop5());

        //     System.out.println("\n" + scraper.getMeta(1));
        // }
        
        // scanner.close();
        // driver.quit();   
    }
}
