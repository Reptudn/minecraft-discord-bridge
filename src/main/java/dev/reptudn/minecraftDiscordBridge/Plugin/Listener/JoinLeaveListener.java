package dev.reptudn.minecraftDiscordBridge.Plugin.Listener;

import dev.reptudn.minecraftDiscordBridge.Discord.DiscordCore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		String name = event.getPlayer().getName();

		if (!event.getPlayer().hasPlayedBefore()) {
			event.setJoinMessage(ChatColor.GREEN + ">> " + ChatColor.GRAY + name + ChatColor.GRAY + " has joined for the first time!");
			return;
		}

		if (event.getPlayer().getUniqueId() == event.getPlayer().getServer().getOfflinePlayer("Reptudn").getUniqueId()) {
			event.setJoinMessage(ChatColor.GREEN + ">> " +  ChatColor.RESET +
					ChatColor.YELLOW + ChatColor.UNDERLINE + ChatColor.BOLD + name + ChatColor.RESET +
					ChatColor.GRAY + " has joined the server!");
		} else
			event.setJoinMessage(ChatColor.GREEN + ">> " + ChatColor.GRAY + name);

		DiscordCore.sendPlayerJoinEmbed(name);

	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {

		String name = event.getPlayer().getName();

		event.setQuitMessage(ChatColor.RED + "<< " + ChatColor.GRAY + name);
		DiscordCore.sendPlayerLeaveEmbed(name);
	}

}

