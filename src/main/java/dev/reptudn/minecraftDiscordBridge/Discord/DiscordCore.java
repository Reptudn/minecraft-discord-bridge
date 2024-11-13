package dev.reptudn.minecraftDiscordBridge.Discord;

import dev.reptudn.minecraftDiscordBridge.Discord.Listener.MessageListener;
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
			jda = JDABuilder.createLight(DISCORD_BOT_TOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
					.addEventListeners(this)
					.build();
			jda.addEventListener(new MessageListener());
		} catch (Exception e) {
			e.printStackTrace();
		}

		changeStatus("Server is online");

	}

	public static void sendChatMessageEmbed(String message, String author) {
		EmbedBuilder embed = new EmbedBuilder();

		embed.setAuthor(author);
		embed.setDescription(message);
		embed.setColor(Color.BLUE);
		embed.setThumbnail("https://mc-heads.net/avatar/" + author + "/64");

		Objects.requireNonNull(jda.getTextChannelById("1306353692785774653")).sendMessageEmbeds(embed.build()).queue();
	}

	public static void sendPlayerJoinEmbed(String name) {
		EmbedBuilder embed = new EmbedBuilder();

		embed.setAuthor(name + " has joined the server!");
		embed.setColor(Color.GREEN);
		embed.setThumbnail("https://mc-heads.net/avatar/" + name + "/64");

		Objects.requireNonNull(jda.getTextChannelById("1306353692785774653")).sendMessageEmbeds(embed.build()).queue();

		int playerCount = plugin.getServer().getOnlinePlayers().size();
		jda.getPresence().setActivity(Activity.watching("Server with " + playerCount + " players"));
	}

	public static void sendPlayerLeaveEmbed(String name) {
		EmbedBuilder embed = new EmbedBuilder();

		embed.setAuthor(name + " has left the server!");
		embed.setColor(Color.RED);
		embed.setThumbnail("https://mc-heads.net/avatar/" + name + "/64");

		Objects.requireNonNull(jda.getTextChannelById("1306353692785774653")).sendMessageEmbeds(embed.build()).queue();

		int playerCount = plugin.getServer().getOnlinePlayers().size();
		jda.getPresence().setActivity(Activity.watching("Server with " + playerCount + " players"));
	}

	public void changeStatus(String status) {
		jda.getPresence().setActivity(Activity.watching(status));
	}

	public void shutdown() {
		changeStatus("Server is offline");
		jda.shutdown();
	}

}
