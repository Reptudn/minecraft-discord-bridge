package dev.reptudn.minecraftDiscordBridge;

import dev.reptudn.minecraftDiscordBridge.Discord.DiscordCore;
import dev.reptudn.minecraftDiscordBridge.Plugin.Listener.JoinLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	DiscordCore discordBot;

	@Override
	public void onEnable() {

		discordBot = new DiscordCore(this);

		registerCommands();

	}

	@Override
	public void onDisable() {
		discordBot.shutdown();
	}

	private void registerCommands() {
		getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
	}

}
