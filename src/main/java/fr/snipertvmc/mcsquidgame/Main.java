package fr.snipertvmc.mcsquidgame;

import fr.snipertvmc.mcsquidgame.infrastructure.models.SquidGame;
import fr.snipertvmc.mcsquidgame.managers.PlayerManager;
import fr.snipertvmc.mcsquidgame.managers.ScoreboardManager;
import fr.snipertvmc.mcsquidgame.utilities.ConsoleLogger;
import fr.snipertvmc.mcsquidgame.utilities.RegisterUtils;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


	// -------------------------------------------------- //


	private static Main instance;


	private final String generalPrefix = "<color:#ed1b76>M</color><color:#f44786>C</color><color:#ffffff>S</color><color:#249f9c>q</color><color:#037a76>u</color><color:#ed1b76>i</color><color:#f44786>d</color><color:#ffffff>G</color><color:#249f9c>a</color><color:#037a76>m</color><color:#ed1b76>e</color> <white><bold>┃</bold></white> <gray>";
	private final String consolePrefix = "§dMCSquidGame §f§l┃ §7";

	private BukkitAudiences adventure;

	private PlayerManager playerManager;
	private ScoreboardManager scoreboardManager;

	private SquidGame squidGame;


	// -------------------------------------------------- //


	@Override
	public void onEnable() {


		// CHARGEMENT DU PLUGIN
		long startTime = System.currentTimeMillis();

		ConsoleLogger.console("");
		ConsoleLogger.console("\t" + consolePrefix + "Chargement du plugin...");


		// INITIALISATION DES VARIABLES GLOBALES
		instance = this;

		adventure = BukkitAudiences.create(this);

		playerManager = new PlayerManager();
		scoreboardManager = new ScoreboardManager();

		squidGame = new SquidGame();


		// INITIALISATION DES DONNÉES GLOBALES
		ConsoleLogger.console("\t" + consolePrefix + "Initialisation des données globales...");
		for (Player player : Bukkit.getOnlinePlayers()) {
			squidGame.addPlayer(Main.getInstance().getPlayerManager().initializePlayer(player));
		}
		ConsoleLogger.console("\t" + consolePrefix + "Initialisation des données globales §fterminée§7.");


		// INITIALISATION DES TÂCHES
		ConsoleLogger.console("\t" + consolePrefix + "Initialisation des tâches...");
		scoreboardManager.initializeScoreboardTask();
		ConsoleLogger.console("\t" + consolePrefix + "Initialisation des tâches §fterminée§7.");


		// ENREGISTREMENT DES COMMANDES
		ConsoleLogger.console("\t" + consolePrefix + "Enregistrement des commandes...");
		int registeredCommands = RegisterUtils.registerCommands("fr.snipertvmc.mcsquidgame.commands");
		ConsoleLogger.console("\t" + consolePrefix + "Enregistrement des commandes §fterminé§7. §f(" + registeredCommands + " commandes enregistrées)");


		// ENREGISTREMENT DES ÉVÉNEMENTS
		ConsoleLogger.console("\t" + consolePrefix + "Enregistrement des événements...");
		int registeredEvents = RegisterUtils.registerEvents("fr.snipertvmc.mcsquidgame.events");
		ConsoleLogger.console("\t" + consolePrefix + "Enregistrement des événements §fterminé§7. §f(" + registeredEvents + " événements enregistrés)");


		// CHARGEMENT TERMINÉ DU PLUGIN
		long endTime = System.currentTimeMillis();
		long loadingTime = endTime - startTime;

		ConsoleLogger.console("\t" + consolePrefix + "Le plugin s'est §fchargé §7correctement en §f" + loadingTime + "ms§7.");
		ConsoleLogger.console("");
	}


	// -------------------------------------------------- //


	@Override
	public void onDisable() {


		// DÉCHARGEMENT DU PLUGIN
		long startTime = System.currentTimeMillis();

		ConsoleLogger.console("\t" + consolePrefix + "Déchargement du plugin...");
		ConsoleLogger.console("");


		// ENREGISTREMENT DES DONNÉES
		ConsoleLogger.console("\t" + consolePrefix + "Enregistrement des données...");
		if (adventure != null) {
			adventure.close();
		}
		ConsoleLogger.console("\t" + consolePrefix + "Enregistrement des données §fterminé§7.");


		// DÉCHARGEMENT TERMINÉ DU PLUGIN
		long endTime = System.currentTimeMillis();
		long unloadingTime = endTime - startTime;

		ConsoleLogger.console("\t" + consolePrefix + "Le plugin s'est §fdéchargé §7correctement en §f" + unloadingTime + "ms§7.");
		ConsoleLogger.console("");
	}


	// -------------------------------------------------- //


	public static Main getInstance() {
		return instance;
	}


	public String getGeneralPrefix() {
		return generalPrefix;
	}
	public String getConsolePrefix() {
		return consolePrefix;
	}

	public BukkitAudiences getAdventure() {
		return adventure;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	public ScoreboardManager getScoreboardManager() {
		return scoreboardManager;
	}

	public SquidGame getSquidGame() {
		return squidGame;
	}


	// -------------------------------------------------- //
}