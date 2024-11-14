package dev.reptudn.minecraftDiscordBridge.Utils;

import org.bukkit.Server;

public class Logger {

	public static void Log(String message)
	{
		Server server = org.bukkit.Bukkit.getServer();
		server.getLogger().log(java.util.logging.Level.INFO, message);
	}

}

