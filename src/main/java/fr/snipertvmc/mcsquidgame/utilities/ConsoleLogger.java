package fr.snipertvmc.mcsquidgame.utilities;

import fr.snipertvmc.mcsquidgame.Main;
import org.bukkit.Bukkit;

public class ConsoleLogger {


	// -------------------------------------------------- //


	public static void console(String message) {
		Bukkit.getConsoleSender().sendMessage(message);
	}


	public static void info(String message) {
		Bukkit.getLogger().info(message);
	}


	public static void warn(String message) {
		Bukkit.getLogger().warning(message);
	}


	public static void error(String message) {
		Bukkit.getLogger().severe(message);
	}


	// -------------------------------------------------- //
}
