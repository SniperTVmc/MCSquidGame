package fr.snipertvmc.mcsquidgame.events.player;

import fr.snipertvmc.mcsquidgame.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {


	// -------------------------------------------------- //


	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {

		Player player = event.getPlayer();
		event.setQuitMessage(null);

		Main.getInstance().getPlayerManager().savePlayer(player, true);
	}


	// -------------------------------------------------- //
}
