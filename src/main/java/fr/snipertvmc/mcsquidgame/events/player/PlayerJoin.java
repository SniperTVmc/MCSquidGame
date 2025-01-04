package fr.snipertvmc.mcsquidgame.events.player;

import fr.snipertvmc.mcsquidgame.Main;
import fr.snipertvmc.mcsquidgame.infrastructure.models.SquidPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {


	// -------------------------------------------------- //


	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		event.setJoinMessage(null);

		new SquidPlayer(player).send(Main.getInstance().getGeneralPrefix() + "<gray>Bienvenue à toi</gray> <white>" + player.getName() + "</white> <gray>!</gray>");
	}


	// -------------------------------------------------- //
}
