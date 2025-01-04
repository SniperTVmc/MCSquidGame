package fr.snipertvmc.mcsquidgame.managers;

import fr.snipertvmc.mcsquidgame.Main;
import fr.snipertvmc.mcsquidgame.infrastructure.models.SquidPlayer;
import fr.snipertvmc.mcsquidgame.utilities.builders.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreboardManager {


	// -------------------------------------------------- //


	private final Map<Player, ScoreboardBuilder> playerScoreboards = new HashMap<>();
	private final String scoreboardTitle = "<color:#ed1b76>M</color><color:#f44786>C</color><color:#ffffff>S</color><color:#249f9c>q</color><color:#037a76>u</color><color:#ed1b76>i</color><color:#f44786>d</color><color:#ffffff>G</color><color:#249f9c>a</color><color:#037a76>m</color><color:#ed1b76>e</color>";


	// -------------------------------------------------- //


	public void initializeScoreboardTask() {

		Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {

			updateScoreboards();

		}, 0, 10);
	}


	public void updateScoreboard(SquidPlayer squidPlayer) {
		ScoreboardBuilder scoreboardBuilder = playerScoreboards.computeIfAbsent(squidPlayer.getPlayer(), ScoreboardBuilder::new);

		scoreboardBuilder.updateTitle(scoreboardTitle);

		List<String> newLines = getScoreboardLines(squidPlayer);
		scoreboardBuilder.updateLines(newLines);
	}


	// -------------------------------------------------- //


	private void updateScoreboards() {
		for (SquidPlayer squidPlayer : Main.getInstance().getSquidGame().getPlayers()) {
			updateScoreboard(squidPlayer);
		}
	}


	private List<String> getScoreboardLines(SquidPlayer squidPlayer) {

		List<String> scoreboardLines;

		scoreboardLines = new ArrayList<>() {{
			add("<reset>");
			add("➲ Partie");
			add("  Phase: Repas (Midi)");
			add("  Épreuve: 2/8");
			add("  Joueurs restants: 12/50");
			add("<reset>");
		}};

		return scoreboardLines;
	}


	// -------------------------------------------------- //
}
