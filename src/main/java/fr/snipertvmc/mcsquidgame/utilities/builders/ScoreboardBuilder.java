package fr.snipertvmc.mcsquidgame.utilities.builders;

import fr.snipertvmc.mcsquidgame.infrastructure.models.ScoreboardData;
import org.bukkit.entity.Player;

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
		scoreboardData.updateTitle(scoreboardTitle);
	}


	public void updateLines(List<String> scoreboardLines) {
		scoreboardData.updateLines(scoreboardLines);
	}


	// ---------------------------------------- //
}
