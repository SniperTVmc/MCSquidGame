package fr.snipertvmc.mcsquidgame.infrastructure.models;

import fr.mrmicky.fastboard.adventure.FastBoard;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class ScoreboardData {


	// ---------------------------------------- //


	private final FastBoard fastBoard;


	// ---------------------------------------- //


	public ScoreboardData(Player player) {
		this.fastBoard = new FastBoard(player);
	}


	public FastBoard getFastBoard() {
		return fastBoard;
	}


	public void updateTitle(Component title) {
		fastBoard.updateTitle(title);
	}


	public void updateLines(Component... lines) {
		fastBoard.updateLines(lines);
	}


	// ---------------------------------------- //
}
