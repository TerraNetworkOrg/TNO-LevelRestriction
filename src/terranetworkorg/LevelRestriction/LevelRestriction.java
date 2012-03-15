package terranetworkorg.LevelRestriction;

import terranetworkorg.LevelRestriction.LevelHandler.*;
import terranetworkorg.LevelRestriction.Listener.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.player.SpoutPlayer;

public class LevelRestriction extends JavaPlugin {
	
	public final static Logger log = Logger.getLogger("Minecraft");
	public static final String logprefix = "[LevelRestriction 1.0.0]";
	
	public static Set<String> onBreakKeys = null;
	public static Set<String> onPlaceKeys = null;
	public static Set<String> onKillKeys = null;

	public static FileConfiguration config = null;
    public File configFile = null;
    public static FileConfiguration language = null;
	public File languageFile = null;
    public static FileConfiguration event = null;
	public File eventFile = null;
    public static FileConfiguration classes = null;
	public File classesFile = null;
    public static FileConfiguration user = null;
	public File userFile = null;
	
	public static GenericLabel expLabel = new GenericLabel("expLabel");
	public static Boolean expLabelVerifier = false;
	public Map<SpoutPlayer, GenericPopup> playerPopup = new HashMap<SpoutPlayer, GenericPopup>();
	
	public static Permission permission = null;
	public Economy economy = null;
	
	public static void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public static void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public static void LogWarning(String Message) {
		
		log.log(Level.WARNING, logprefix + " " + Message);
		
	}
	
	private Boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
            LogInfo("succesfully connected permissions support with Vault");
        }
        return (permission != null);
    }
	
	public void reloadConfig() {
	    if (configFile == null) {
	    configFile = new File(getDataFolder(), "config.yml");
	    }
	    config = YamlConfiguration.loadConfiguration(configFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = getResource("config.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        config.setDefaults(defConfig);
	    }
	}

	public FileConfiguration getConfig() {
	    if (config == null) {
	        reloadConfig();
	    }
	    return config;
	}
	
	public void saveConfig() {
	    if (config == null || configFile == null) {
	    return;
	    }
	    try {
	        config.save(configFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + configFile, ex);
	    }
	}
	
	public void reloadLanguage() {
	    if (languageFile == null) {
	    	languageFile = new File(getDataFolder(), "language.yml");
	    }
	    language = YamlConfiguration.loadConfiguration(languageFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = getResource("language.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        language.setDefaults(defConfig);
	    }
	}

	public FileConfiguration getLanguage() {
	    if (language == null) {
	        reloadLanguage();
	    }
	    return language;
	}
	
	public void saveLanguage() {
	    if (language == null || languageFile == null) {
	    return;
	    }
	    try {
	    	language.save(languageFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + languageFile, ex);
	    }
	}
	
	public void reloadEvent() {
	    if (eventFile == null) {
	    	eventFile = new File(getDataFolder(), "event.yml");
	    }
	    event = YamlConfiguration.loadConfiguration(eventFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = getResource("event.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        event.setDefaults(defConfig);
	    }
	}

	public FileConfiguration getEvent() {
	    if (event == null) {
	        reloadEvent();
	    }
	    return event;
	}
	
	public void saveEvent() {
	    if (event == null || eventFile == null) {
	    return;
	    }
	    try {
	    	event.save(eventFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + eventFile, ex);
	    }
	}
	
	public void reloadClasses() {
	    if (classesFile == null) {
	    	classesFile = new File(getDataFolder(), "classes.yml");
	    }
	    classes = YamlConfiguration.loadConfiguration(classesFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = getResource("classes.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        classes.setDefaults(defConfig);
	    }
	}

	public FileConfiguration getClasses() {
	    if (classes == null) {
	        reloadClasses();
	    }
	    return classes;
	}
	
	public void saveClasses() {
	    if (classes == null || classesFile == null) {
	    return;
	    }
	    try {
	    	classes.save(classesFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + classesFile, ex);
	    }
	}
	
	public void reloadUser() {
	    if (userFile == null) {
	    	userFile = new File(getDataFolder(), "user.yml");
	    }
	    user = YamlConfiguration.loadConfiguration(userFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = getResource("user.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        user.setDefaults(defConfig);
	    }
	}

	public FileConfiguration getUser() {
	    if (user == null) {
	        reloadUser();
	    }
	    return user;
	}
	
	public void saveUser() {
	    if (user == null || userFile == null) {
	    return;
	    }
	    try {
	    	user.save(userFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + userFile, ex);
	    }
	}
	

	
	private Boolean loadConfig()
	{
		reloadConfig();
		getConfig();
		reloadLanguage();
		getLanguage();
		reloadEvent();
		getEvent();
		reloadClasses();
		getClasses();
		reloadUser();
		getUser();

		if(config.getString("Config.levelBaseExperience") == null)
        	config.set("Config.levelBaseExperience", 20);
		if(config.getString("Config.allowNegativeExperience") == null)
        	config.set("Config.allowNegativeExperience", false);
		if(config.getString("Config.setSaveTimerInMinutes") == null)
        	config.set("Config.setSaveTimerInMinutes", 1.0);
		if(config.getString("Config.useSpoutLevelInfo") == null)
        	config.set("Config.useSpoutLevelInfo", true);
		if(config.getString("Config.copyExampleFiles") == null)
        	config.set("Config.copyExampleFiles", true);
        
        saveConfig();

        
        
        saveLanguage();
        
        /*if(event.getString("Event.onBreak.BlockID") == null)
        	event.set("Event.onBreak.BlockID", "true");
        if(event.getString("Event.onPlace.BlockID") == null)
        	event.set("Event.onPlace.BlockID", "true");
        if(event.getString("Event.onKill.EnemyName") == null)
        	event.set("Event.onKill.EnemyName", "true");*/
        
        if(config.getBoolean("Config.copyExampleFiles") == true){
        	getEvent().options().copyDefaults(true);
        }
        
        saveEvent();
        
        /*if(classes.getString("Class.Stonebreaker.Level.1.Grant") == null)
        	classes.set("Class.Stonebreaker.Level.1.Grant", "give.me.a.permission.node");
        if(classes.getString("Class.Stonebreaker.Level.1.Require") == null)
        	classes.set("Class.Stonebreaker.Level.1.Require", "you.need.this.permission.node");*/
        
        if(config.getBoolean("Config.copyExampleFiles") == true){
        	getClasses().options().copyDefaults(true);
        }
        saveClasses();
        
        saveUser();
        
        if(config.getBoolean("Config.copyExampleFiles") == true){
        	config.set("Config.copyExampleFiles", false);
        	saveConfig();
        }
		      
		return true;
	}
	
	public void onEnable() {
		
		loadConfig();
		
		LogInfo("=============================");
		if (!setupPermissions()) {
			System.out.println("No permissions plugin found.");
	        //use these if you require econ
	        getServer().getPluginManager().disablePlugin(this);
	        return;
	    }
		
		ConfigurationSection section = getEvent().getConfigurationSection("Event");
    	Set<String> allKeys = section.getKeys(false);
		if(allKeys.contains("onBreak")){
			getServer().getPluginManager().registerEvents(new blockBreakListener(this), this);
			
			ConfigurationSection onBreakSectionIDs = getEvent().getConfigurationSection("Event.onBreak.BlockID");
			onBreakKeys = onBreakSectionIDs.getKeys(false);
			
			LogInfo("using blockBreak-Event");
		}
		if(allKeys.contains("onPlace")){
			getServer().getPluginManager().registerEvents(new blockPlaceListener(this), this);
			
			ConfigurationSection onPlaceSectionIDs = getEvent().getConfigurationSection("Event.onPlace.BlockID");
			onPlaceKeys = onPlaceSectionIDs.getKeys(false);
    	
			LogInfo("using blockPlace-Event");
		}
		if(allKeys.contains("onKill")){
			getServer().getPluginManager().registerEvents(new entityDeathListener(this), this);
			
			ConfigurationSection onKillSectionIDs = getEvent().getConfigurationSection("Event.onKill.EnemyName");
			onKillKeys = onKillSectionIDs.getKeys(false);
			
			LogInfo("using entityKill-Event");
		}	
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new updateDB(), config.getInt("Config.setSaveTimerInMinutes", 2) * 1200L, config.getInt("Config.setSaveTimerInMinutes", 2) * 1200L);
		
		LogInfo("was successfully initiated.");
		LogInfo("=============================");
		
	}
	
	public void onDisable() {
		
		LogInfo("Plugin Disabled");
		
	}
	
	public class updateDB implements Runnable{

		public void run() {
			//Stats.printCon("Flushing to database.");
			saveUser();
			LogInfo("successfully saved user.yml");
			//Stats.printCon("flush completed");
		}

	}
	
	private void showPluginInfo(CommandSender sender){
		sender.sendMessage(ChatColor.GREEN + logprefix);
	}
	
	private void configReload(CommandSender sender){
		reloadConfig();
		sender.sendMessage(ChatColor.BLUE+ "Config successfully reloaded.");
	}
	
	private void languageReload(CommandSender sender){
		reloadLanguage();
		sender.sendMessage(ChatColor.BLUE+ "Language successfully reloaded.");
	}
	
	private void eventReload(CommandSender sender){
		reloadEvent();
		sender.sendMessage(ChatColor.BLUE+ "Events successfully reloaded.");
	}
	
	private void classesReload(CommandSender sender){
		reloadClasses();
		sender.sendMessage(ChatColor.BLUE+ "Classes successfully reloaded.");
	}
	
	private void userReload(CommandSender sender){
		reloadUser();
		sender.sendMessage(ChatColor.BLUE+ "Users successfully reloaded.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	
		if(sender instanceof Player){
			Player player = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("levelrestriction")){
				if(args.length==0){
	                   showPluginInfo(sender);
	                   return true;
				} else if(args.length==1){
					if(args[0].equalsIgnoreCase("list")){						
						Set<String> classesList = classHandler.getClassesList();
						String[] classes = classesList.toArray(new String[0]);
						sender.sendMessage("List of available classes:");
						sender.sendMessage("--------------------------");
						for(int i=0; i<classesList.size(); i++){
							sender.sendMessage(classes[i]);
						}
						return true;
					} else{
						sender.sendMessage(ChatColor.BLUE+"Use a valid parameter: list");
						return false;
					}
				} else if(args.length==2){
					if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("config") && permission.has(player, "levelrestriction.admin.reload")){						
						configReload(sender);
						return true;
					} else if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("language") && permission.has(player, "levelrestriction.admin.reload")){
						languageReload(sender);
						return true;						
					} else if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("event") && permission.has(player, "levelrestriction.admin.reload")){
						eventReload(sender);
						return true;
					} else if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("classes") && permission.has(player, "levelrestriction.admin.reload")){
						classesReload(sender);
						return true;
					} else if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("user") && permission.has(player, "levelrestriction.admin.reload")){
						userReload(sender);
						return true;
					} else if(args[0].equalsIgnoreCase("show")){
						eventHandler.getValueList(sender, args[1]);
						return true;
					} else {
						sender.sendMessage(ChatColor.BLUE+"Use a valid parameter: reload [config/language/event/classes/user]");
						return true;						
					}
				} else {
					sender.sendMessage(ChatColor.BLUE+"Use a valid parameter: reload [config/language/event/classes/user]");
					return true;
				}
			}
			return false;
		} else{
			if(cmd.getName().equalsIgnoreCase("levelrestriction")){
				if(args.length==0){
	                   showPluginInfo(sender);
	                   return true;
				} else if(args.length==1){
					if(args[0].equalsIgnoreCase("list")){						
						Set<String> classesList = classHandler.getClassesList();
						String[] classes = classesList.toArray(new String[0]);
						sender.sendMessage("List of available classes:");
						sender.sendMessage("--------------------------");
						for(int i=0; i<classesList.size(); i++){
							sender.sendMessage(classes[i]);
						}
						return true;
					} else{
						sender.sendMessage(ChatColor.BLUE+"Use a valid parameter: list");
						return false;
					}
				} else if(args.length==2){
					if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("config")){						
						configReload(sender);
						return true;
					} else if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("language")){
						languageReload(sender);
						return true;						
					} else if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("event")){
						eventReload(sender);
						return true;
					} else if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("classes")){
						classesReload(sender);
						return true;
					} else if(args[0].equalsIgnoreCase("reload") && args[1].equalsIgnoreCase("user")){
						userReload(sender);
						return true;
					} else if(args[0].equalsIgnoreCase("show")){
						eventHandler.getValueList(sender, args[1]);
						return true;
					} else {
						sender.sendMessage(ChatColor.BLUE+"Use a valid parameter: reload [config/language/event/classes/user]");
						return true;						
					}
				} else {
					sender.sendMessage(ChatColor.BLUE+"Use a valid parameter: reload [config/language/event/classes/user]");
					return true;
				}
			}
			return false;
		}		
	}
}