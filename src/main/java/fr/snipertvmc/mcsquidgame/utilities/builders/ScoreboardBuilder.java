package fr.snipertvmc.mcsquidgame.utilities.builders;

import fr.snipertvmc.mcsquidgame.infrastructure.models.ScoreboardData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardBuilder {


	// ---------------------------------------- //


	private final ScoreboardData scoreboardData;


	// ---------------------------------------- //


	public ScoreboardBuilder(Player player) {
		this.scoreboardData = new ScoreboardData(player);
	}


	public void hideScoreboard() {
		scoreboardData.getFastBoard().delete();
	}


	// ---------------------------------------- //


	public void updateTitle(String scoreboardTitle) {
		scoreboardData.updateTitle(MiniMessage.miniMessage().deserialize(scoreboardTitle));
	}


	public void updateLines(List<String> scoreboardLines) {
		List<Component> components = new ArrayList<>();
		for (String line : scoreboardLines) {
			components.add(MiniMessage.miniMessage().deserialize(line));
		}
		scoreboardData.updateLines(components.toArray(new Component[0]));
	}


	// ---------------------------------------- //
}
