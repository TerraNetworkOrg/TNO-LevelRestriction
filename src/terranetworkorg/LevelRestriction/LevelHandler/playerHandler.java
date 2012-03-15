package terranetworkorg.LevelRestriction.LevelHandler;


import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import terranetworkorg.LevelRestriction.LevelRestriction;
import terranetworkorg.LevelRestriction.GUI.guiHandler;


public class playerHandler {
	
	@SuppressWarnings("unused")
	private final LevelRestriction plugin;
	 	 
	public playerHandler(LevelRestriction plugin)
	{
		this.plugin = plugin;
	}
	
	public static Set<String> getUserClasses(String userName){
		
		ConfigurationSection section = LevelRestriction.classes.getConfigurationSection("User." + userName);
    	Set<String> allKeys = section.getKeys(false);
		
		return allKeys;
		
	}
	
	public static String checkUserClassLevelUpPermission(String userName, String userClass, int userLevel){
		
		String getPermission = LevelRestriction.classes.getString("Class." + userClass + ".Level." + userLevel + ".Require");
		
		return getPermission;
	}
	
	public static String checkUserClassLevelUpGrant(String userName, String userClass, int userLevel){
		
		String getPermission = LevelRestriction.classes.getString("Class." + userClass + ".Level." + userLevel + ".Grant");
		
		return getPermission;
	}
	
	public static double getUserClassExperience(String userName, String userClass){
		
		double userClassExperience = LevelRestriction.user.getDouble("User." + userName + "." + userClass + ".Experience", 0.0);
		
		return userClassExperience;
		
	}
	
	public static int getUserClassLevel(String userName, String userClass){
		
		int userClassLevel = LevelRestriction.user.getInt("User." + userName + "." + userClass + ".Level", 1);
		
		return userClassLevel;
		
	}
	
	public static void addUserClassLevel(String userName, String userClass){
		
		LevelRestriction.user.set("User." + userName + "." + userClass + ".Level", (getUserClassLevel(userName, userClass) + 1));
		
	}
	
	public static void addUserClassExperience(Player player, String userName, String userClass, double userExperience){
				
		if(LevelRestriction.config.getBoolean("Config.allowNegativeExperience", false) == true){			
			LevelRestriction.user.set("User." + userName + "." + userClass + ".Experience", (getUserClassExperience(userName, userClass) + userExperience));			
		} else{
			if((getUserClassExperience(userName, userClass) + userExperience) < 0){				
				LevelRestriction.user.set("User." + userName + "." + userClass + ".Experience", 0);				
			} else{				
				LevelRestriction.user.set("User." + userName + "." + userClass + ".Experience", (getUserClassExperience(userName, userClass) + userExperience));				
			}
		}
		
	}
	
	public static void setUserClassExperience(String userName, String userClass, double userExperience){
				
		LevelRestriction.user.set("User." + userName + "." + userClass + ".Experience", userExperience);
		
	}
	
	public static void checkUserLevelUp(Player player, String userName, String userClass, double userExperience, int userLevel){
				
		if(userExperience < experienceHandler.getLevelEXP(userLevel)){
			
			if(LevelRestriction.config.getBoolean("Config.useSpoutLevelInfo") == true){						
				if(LevelRestriction.expLabelVerifier == false){
					SpoutPlayer sPlayer = (SpoutPlayer)player;
					guiHandler.setGUI(sPlayer, userClass, getUserClassLevel(userName, userClass), getUserClassExperience(userName, userClass), experienceHandler.getLevelEXP(getUserClassLevel(userName, userClass)));					
				} else{					
					SpoutPlayer sPlayer = (SpoutPlayer)player;
					guiHandler.updateGUI(sPlayer, userClass, getUserClassLevel(userName, userClass), getUserClassExperience(userName, userClass), experienceHandler.getLevelEXP(getUserClassLevel(userName, userClass)));
				}
			}
			
			return;
		} else{
			setUserClassExperience(userName, userClass, userExperience - experienceHandler.getLevelEXP(userLevel));
			if(checkUserClassLevelUpPermission(userName, userClass, userLevel+1) != null){
				if(LevelRestriction.permission.has(player, checkUserClassLevelUpPermission(userName, userClass, userLevel+1))){
					addUserClassLevel(userName, userClass);
					player.sendMessage("Glückwunsch zum Stufenaufstieg!");
					
					if(LevelRestriction.config.getBoolean("Config.useSpoutLevelInfo") == true){						
						if(LevelRestriction.expLabelVerifier == false){
							SpoutPlayer sPlayer = (SpoutPlayer)player;
							guiHandler.setGUI(sPlayer, userClass, getUserClassLevel(userName, userClass), getUserClassExperience(userName, userClass), experienceHandler.getLevelEXP(getUserClassLevel(userName, userClass)));					
						} else{					
							SpoutPlayer sPlayer = (SpoutPlayer)player;
							guiHandler.updateGUI(sPlayer, userClass, getUserClassLevel(userName, userClass), getUserClassExperience(userName, userClass), experienceHandler.getLevelEXP(getUserClassLevel(userName, userClass)));
						}
					}
					
					String permissionGrant = checkUserClassLevelUpGrant(userName, userClass, getUserClassLevel(userName, userClass));
					if(permissionGrant == null){
						return;
					} else{
						LevelRestriction.permission.playerAdd(player, permissionGrant);
					}
				} else{
					player.sendMessage("Du hast keine Berechtigung für den Levelaufstieg auf Stufe " + (userLevel+1) + " der Klasse " + userClass);
					
					if(LevelRestriction.config.getBoolean("Config.useSpoutLevelInfo") == true){						
						if(LevelRestriction.expLabelVerifier == false){
							SpoutPlayer sPlayer = (SpoutPlayer)player;
							guiHandler.setGUI(sPlayer, userClass, getUserClassLevel(userName, userClass), getUserClassExperience(userName, userClass), experienceHandler.getLevelEXP(getUserClassLevel(userName, userClass)));					
						} else{					
							SpoutPlayer sPlayer = (SpoutPlayer)player;
							guiHandler.updateGUI(sPlayer, userClass, getUserClassLevel(userName, userClass), getUserClassExperience(userName, userClass), experienceHandler.getLevelEXP(getUserClassLevel(userName, userClass)));
						}
					}
					
					return;
				}
			} else{
				addUserClassLevel(userName, userClass);
				player.sendMessage("Glückwunsch zum Stufenaufstieg!");
				
				if(LevelRestriction.config.getBoolean("Config.useSpoutLevelInfo") == true){						
					if(LevelRestriction.expLabelVerifier == false){
						SpoutPlayer sPlayer = (SpoutPlayer)player;
						guiHandler.setGUI(sPlayer, userClass, getUserClassLevel(userName, userClass), getUserClassExperience(userName, userClass), experienceHandler.getLevelEXP(getUserClassLevel(userName, userClass)));					
					} else{					
						SpoutPlayer sPlayer = (SpoutPlayer)player;
						guiHandler.updateGUI(sPlayer, userClass, getUserClassLevel(userName, userClass), getUserClassExperience(userName, userClass), experienceHandler.getLevelEXP(getUserClassLevel(userName, userClass)));
					}
				}
				
				String permissionGrant = checkUserClassLevelUpGrant(userName, userClass, getUserClassLevel(userName, userClass));
				if(permissionGrant == null){
					return;
				} else{
					LevelRestriction.permission.playerAdd(player, permissionGrant);
				}
				
			}
			
			
		}
		
	}
	
}