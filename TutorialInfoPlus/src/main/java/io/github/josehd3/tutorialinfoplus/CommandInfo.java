package io.github.josehd3.tutorialinfoplus;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInfo implements CommandExecutor {

	private String pluginAuthors, pluginVersion, pluginName;
	
	CommandInfo(String pluginName, String pluginVersion, String pluginAuthors){
		this.pluginName = pluginName;
		this.pluginVersion = pluginVersion;
		this.pluginAuthors = pluginAuthors;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.GOLD + "Plugin: " + ChatColor.RED + pluginName);
			player.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.RED + pluginVersion);
			player.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.RED + pluginAuthors);
		} else {
			sender.sendMessage("Plugin: " + pluginName);
			sender.sendMessage("Version: " + pluginVersion);
			sender.sendMessage("Author: " + pluginAuthors);
		}
		
		return true;
	}
}