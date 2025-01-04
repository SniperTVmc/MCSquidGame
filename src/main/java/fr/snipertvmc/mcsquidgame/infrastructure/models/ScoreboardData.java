package fr.snipertvmc.mcsquidgame.infrastructure.models;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;

import java.util.List;

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


	public void updateTitle(String title) {
		fastBoard.updateTitle(title);
	}


	public void updateLines(List<String> lines) {
		fastBoard.updateLines(lines);
	}


	// ---------------------------------------- //
}
