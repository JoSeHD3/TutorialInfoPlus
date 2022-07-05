package io.github.josehd3.tutorialinfoplus;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class CommandTutorial implements CommandExecutor{
	
	private TutorialInfoPlus main;
	
	public CommandTutorial(TutorialInfoPlus main) {
		this.main = main;
	}
	

	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) { 
				ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
				
				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("set")) {
						Player player = (Player) sender;
						if(!player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK)) return false;
						
						try {
							book = player.getInventory().getItemInMainHand();
							
							main.getBookConfig().set("tutorial.book", book);
							
							main.save();
							Bukkit.getLogger().info("[TutorialInfo]: Tutorial Book has been created");
							sender.sendMessage("You've created a Tutorial Book.");
						} catch (CommandException e) {
							e.printStackTrace();
							Bukkit.getLogger().info("[TutorialInfo] Failed at creating a Tutorial Book.");
						}
						return true;
					}
				}
				
				Player player = (Player) sender;
				try{
					book = main.getBookConfig().getItemStack("tutorial.book");
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setTitle(ChatColor.GREEN + "Tutorial Book");
					meta.setAuthor(ChatColor.DARK_RED + "Server");
					player.openBook(book);
					return true;
				} catch(CommandException | NullPointerException e) {
					e.getStackTrace();
					Bukkit.getLogger().info("[TutorialInfo]: You haven't created a Tutorial Book yet.");
					sender.sendMessage("You haven't created a Tutorial Book yet.");
				} 
			
		} else {
			sender.sendMessage("You ran a TutorialInfo command!");
			return false;
		}
		
		
		return false;
	}
	

	
}
