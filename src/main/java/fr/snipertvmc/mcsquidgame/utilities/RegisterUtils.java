package fr.snipertvmc.mcsquidgame.utilities;

import fr.snipertvmc.mcsquidgame.Main;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.Listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class RegisterUtils {


	// -------------------------------------------------- //


	public static int registerCommands(String packageName) {

		int registeredCommands = 0;

		try (ScanResult scanResult = new ClassGraph()
				.enableClassInfo()
				.acceptPackages(packageName)
				.scan()) {

			List<Class<CommandExecutor>> commandClasses = scanResult
					.getClassesImplementing(CommandExecutor.class)
					.loadClasses(CommandExecutor.class);

			for (Class<? extends CommandExecutor> commandClass : commandClasses) {

				try {
					Constructor<? extends CommandExecutor> constructor = commandClass.getDeclaredConstructor();
					CommandExecutor commandExecutor = constructor.newInstance();

					String commandName = commandClass.getSimpleName().toLowerCase().replace("command", "");
					if (Main.getInstance().getCommand(commandName) != null) {
						Main.getInstance().getCommand(commandName).setExecutor(commandExecutor);

						if (commandExecutor instanceof TabExecutor) {
							Main.getInstance().getCommand(commandName).setTabCompleter((TabExecutor) commandExecutor);
						}
						registeredCommands++;

					} else {
						ConsoleLogger.error("La commande " + commandName + " n'est pas définie dans \"plugin.yml\".");
					}

				} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					ConsoleLogger.error("Échec de l'enregistrement de la classe de commande: " + commandClass.getName());
					throw new RuntimeException(e);
				}
			}
		}

		return registeredCommands;
	}


	// -------------------------------------------------- //


	public static int registerEvents(String packageName) {

		int registeredEvents = 0;

		try (ScanResult scanResult = new ClassGraph()
				.enableClassInfo()
				.acceptPackages(packageName)
				.scan()) {

			List<Class<Listener>> listenerClasses = scanResult
					.getClassesImplementing(Listener.class)
					.loadClasses(Listener.class);

			for (Class<? extends Listener> clazz : listenerClasses) {

				try {
					Listener listener = clazz.getDeclaredConstructor().newInstance();
					Bukkit.getServer().getPluginManager().registerEvents(listener, Main.getInstance());
					registeredEvents++;

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}

		return registeredEvents;
	}


	// -------------------------------------------------- //
}
