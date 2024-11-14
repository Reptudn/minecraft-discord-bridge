package dev.reptudn.minecraftDiscordBridge.Plugin.Listener;

import dev.reptudn.minecraftDiscordBridge.Discord.DiscordCore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.awt.*;

public class MessageListener implements Listener {

	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {

		String name = event.getPlayer().getName();
		String message = event.getMessage();

		event.setFormat(ChatColor.BLUE + name + ChatColor.RESET + ": " + ChatColor.GRAY + message);

		// TODO: Send message to Discord
		DiscordCore.sendChatMessageEmbed(message, name);

	}

}
