package fr.snipertvmc.mcsquidgame.utilities;

import fr.snipertvmc.mcsquidgame.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class MessageUtils {


	// -------------------------------------------------- //


	public static void sendMiniMessage(Player player, String message, boolean legacy) {

		if (legacy) {
			player.sendMessage(message);
			return;
		}

		Component parsedMessage = MiniMessage.miniMessage().deserialize(message);
		Main.getInstance().getAdventure().player(player).sendMessage(parsedMessage);
	}


	// -------------------------------------------------- //
}
