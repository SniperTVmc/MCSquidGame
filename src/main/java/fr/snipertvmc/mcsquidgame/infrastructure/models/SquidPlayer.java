package fr.snipertvmc.mcsquidgame.infrastructure.models;

import fr.snipertvmc.mcsquidgame.utilities.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SquidPlayer {


	// -------------------------------------------------- //


	private final Player player;
	private final String username;


	// -------------------------------------------------- //


	public SquidPlayer(String username) {
		this.player = Bukkit.getPlayer(username);
		this.username = username;
	}


	// -------------------------------------------------- //


	public Player getPlayer() {
		return player;
	}


	public String getUsername() {
		return username;
	}


	// -------------------------------------------------- //


	public void send(String message) {
		MessageUtils.sendMiniMessage(player, message, false);
	}


	public void send(String message, boolean legacy) {
		MessageUtils.sendMiniMessage(player, message, legacy);
	}


	// -------------------------------------------------- //
}
