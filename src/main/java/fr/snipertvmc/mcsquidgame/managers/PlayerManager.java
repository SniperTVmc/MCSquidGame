package fr.snipertvmc.mcsquidgame.managers;

import fr.snipertvmc.mcsquidgame.Main;
import fr.snipertvmc.mcsquidgame.infrastructure.models.SquidPlayer;
import org.bukkit.entity.Player;

public class PlayerManager {


	// -------------------------------------------------- //


	public SquidPlayer initializePlayer(Player player) {

		SquidPlayer squidPlayer = new SquidPlayer(player.getName());
		Main.getInstance().getSquidGame().addPlayer(squidPlayer);
		return squidPlayer;
	}


	public void savePlayer(Player player, boolean leave) {

		SquidPlayer squidPlayer = Main.getInstance().getSquidGame().getPlayer(player.getName());
		Main.getInstance().getSquidGame().removePlayer(squidPlayer);
	}


	// -------------------------------------------------- //
}
