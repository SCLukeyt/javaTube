package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        System.out.println("hello");
        if (event.getName().equals("ping")) {
            event.reply("pong!").queue();
            //get full input string
            
            
            


        }
    }
}
