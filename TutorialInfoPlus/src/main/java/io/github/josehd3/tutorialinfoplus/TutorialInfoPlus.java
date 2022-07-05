package io.github.josehd3.tutorialinfoplus;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class TutorialInfoPlus extends JavaPlugin {

	private PluginDescriptionFile pluginInfo = getDescription();
	private String pluginName = pluginInfo.getName();
	private String pluginVersion = pluginInfo.getVersion();
	private String pluginAuthors = pluginInfo.getAuthors().toString();
	private static File file;
	private static FileConfiguration fc;
	
	public File getFile() {
		return file;
	}

	@Override
	public void onEnable() {
		
		createCustomConfig();

		
		this.getCommand("tutorial").setExecutor(new CommandTutorial(this));
		this.getCommand("tutorialinfo").setExecutor(new CommandInfo(getPluginName(), getPluginVersion(), getPluginAuthors()));
		this.getCommand("changelog").setExecutor(new ChangelogInfo(this));
	}
	
	@Override
	public void onDisable() {
		save();
	}
	
	public String getPluginName() {
		return pluginName;
	}
	
	public String getPluginVersion() {
		return pluginVersion;
	}
	
	public String getPluginAuthors() {
		return pluginAuthors;
	}
	
	private void createCustomConfig() {
		file = new File(getDataFolder(), "book.yml");
		
		if(!file.exists()) {
			try {
				file.mkdirs();
				file.createNewFile();
			} catch(Exception e) {
				e.printStackTrace();
				getLogger().info("[TutorialInfo] Failed creating a new book.yml file.");
			}
		}
		load();
	}
	
	public void reload() {
		try {
			fc.save(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		fc = YamlConfiguration.loadConfiguration(file);
	}
	
	public void save() {
		try {
			fc.save(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		fc = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration getBookConfig() {
		// TODO Auto-generated method stub
		return fc;
	}
	
}
