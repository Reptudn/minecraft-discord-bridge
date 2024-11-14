package dev.reptudn.minecraftDiscordBridge.Discord.Listener;

import dev.reptudn.minecraftDiscordBridge.Discord.DiscordCore;
import dev.reptudn.minecraftDiscordBridge.Utils.Logger;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.ChatColor;

public class MessageListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {

		if (event.getAuthor().isBot()) return;

		String message = event.getMessage().getContentRaw();
		String author = event.getAuthor().getName();

		DiscordCore.plugin.getServer().broadcastMessage(
				ChatColor.BLUE + "[DISCORD] " + ChatColor.RESET +
				ChatColor.BOLD + author + ChatColor.RESET + ": " + message
		);
		Logger.Log("Message from " + author + ": " + message);

	}
}

