package dev.reptudn.minecraftDiscordBridge.Discord;

import dev.reptudn.minecraftDiscordBridge.Discord.Listener.MessageListener;
import dev.reptudn.minecraftDiscordBridge.Utils.Logger;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.Objects;

public class DiscordCore {

	public static final String DISCORD_BOT_TOKEN = "CRINGE";
	public static JDA jda;
	public static JavaPlugin plugin;

	public DiscordCore(JavaPlugin plugin) {

		DiscordCore.plugin = plugin;

		try {
			Logger.Log("Starting discord bot");
			jda = JDABuilder.createDefault(DISCORD_BOT_TOKEN)
					.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
					.addEventListeners(new MessageListener())
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.Log("Failed to start discord bot");
			return;
		}

		Logger.Log("Discord Bot started");
		changeStatus("Server");

	}

	public static void sendChatMessageEmbed(String message, String author) {
		EmbedBuilder embed = new EmbedBuilder();

		embed.setTitle(author);
		embed.setDescription(message);
		embed.setColor(Color.BLUE);
		embed.setThumbnail("https://mc-heads.net/avatar/" + author + "/20.jpg");

		Objects.requireNonNull(jda.getTextChannelById("1306353692785774653")).sendMessageEmbeds(embed.build()).queue();
	}

	public static void sendPlayerJoinEmbed(String name) {
		EmbedBuilder embed = new EmbedBuilder();

		embed.setTitle(name + " has joined the server!");
		embed.setColor(Color.GREEN);
		embed.setThumbnail("https://mc-heads.net/avatar/" + name + "/20.jpg");

		Objects.requireNonNull(jda.getTextChannelById("1306353692785774653")).sendMessageEmbeds(embed.build()).queue();

		int playerCount = plugin.getServer().getOnlinePlayers().size();
		jda.getPresence().setActivity(Activity.watching("Server with " + playerCount + " players"));
	}

	public static void sendPlayerLeaveEmbed(String name) {
		EmbedBuilder embed = new EmbedBuilder();

		embed.setTitle(name + " has left the server!");
		embed.setColor(Color.RED);
		embed.setThumbnail("https://mc-heads.net/avatar/" + name + "/20.jpg");

		Objects.requireNonNull(jda.getTextChannelById("1306353692785774653")).sendMessageEmbeds(embed.build()).queue();

		int playerCount = plugin.getServer().getOnlinePlayers().size();
		if (playerCount > 0)
			jda.getPresence().setActivity(Activity.watching("Server with " + playerCount + " players"));
		else
			jda.getPresence().setActivity(Activity.watching("Server"));
	}

	public void changeStatus(String status) {
		if (jda != null)
			jda.getPresence().setActivity(Activity.watching(status));
		else Logger.Log("JDA IS NULL");
	}

	public void shutdown() {
		changeStatus("Server is offline");
		if (jda != null)
			jda.shutdownNow(); // TODO: fix weird exception when shutting down

	}

}
