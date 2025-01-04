package fr.snipertvmc.mcsquidgame.infrastructure.models;

import java.util.ArrayList;
import java.util.List;

public class SquidGame {


	// -------------------------------------------------- //


	private List<SquidPlayer> players;


	// -------------------------------------------------- //


	public SquidGame() {
		this.players = new ArrayList<>();
	}


	// -------------------------------------------------- //


	public SquidPlayer getPlayer(String username) {
		return players.stream()
				.filter(p -> p.getUsername().equals(username))
				.findFirst()
				.orElse(new SquidPlayer(username));
	}


	public List<SquidPlayer> getPlayers() {
		return players;
	}


	public void setPlayers(List<SquidPlayer> players) {
		this.players = players;
	}


	// -------------------------------------------------- //
}
