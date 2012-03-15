package terranetworkorg.LevelRestriction.LevelHandler;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import terranetworkorg.LevelRestriction.LevelRestriction;


public class eventHandler {
	
	public static void getValueList(CommandSender sender, String showClass)
	{
		ConfigurationSection eventKeySection = null;
		Set<String> eventKeyString = null;
		String[] eventKeysArray = null;
		String[] eventKeyArray = null;
		String eventIDString = null;
		ConfigurationSection eventSection = null;
    	Set<String> eventKeys = null;
    	
    	eventSection = LevelRestriction.event.getConfigurationSection("Event");
    	eventKeys = eventSection.getKeys(false);
    	eventKeysArray = eventKeys.toArray(new String[0]);  
    	
    	for(int i=0; i<eventKeys.size(); i++){
	        if(eventKeysArray[i].equalsIgnoreCase("onBreak") || eventKeysArray[i].equalsIgnoreCase("onPlace")){
	    		eventKeySection = LevelRestriction.event.getConfigurationSection("Event." + eventKeysArray[i] + ".BlockID");
	        	eventKeyString = eventKeySection.getKeys(false);
	        	eventKeyArray = eventKeyString.toArray(new String[0]);    
	        	sender.sendMessage(ChatColor.AQUA + "--------------------------------");
	        	sender.sendMessage(ChatColor.BLUE + eventKeysArray[i]);  
	        	sender.sendMessage(ChatColor.AQUA + "--------------------------------");
        		for(int k=0; k<eventKeyString.size(); k++){
	        		eventIDString = LevelRestriction.event.getString("Event." + eventKeysArray[i] + ".BlockID." + eventKeyArray[k] + ".Class." + showClass);
	        		int materialID = new Integer(eventKeyArray[k]);
	        		Material getMaterial = Material.getMaterial(materialID);
	        		String getMaterialName = getMaterial.name();
	        		if(eventIDString != null){
	        			sender.sendMessage(ChatColor.AQUA + getMaterialName + "  -  Exp: " + ChatColor.YELLOW + eventIDString);
	        		}	        		
	        	}
        	} else if(eventKeysArray[i].equalsIgnoreCase("onKill")){
	    		eventKeySection = LevelRestriction.event.getConfigurationSection("Event." + eventKeysArray[i] + ".EnemyName");
	        	eventKeyString = eventKeySection.getKeys(false);
	        	eventKeyArray = eventKeyString.toArray(new String[0]);   
	        	sender.sendMessage(ChatColor.AQUA + "--------------------------------"); 
	        	sender.sendMessage(ChatColor.BLUE + eventKeysArray[i]);  
	        	sender.sendMessage(ChatColor.AQUA + "--------------------------------");
        		for(int k=0; k<eventKeyString.size(); k++){
	        		eventIDString = LevelRestriction.event.getString("Event." + eventKeysArray[i] + ".EnemyName." + eventKeyArray[k] + ".Class." + showClass);
	        		if(eventIDString != null){
	        			sender.sendMessage(ChatColor.AQUA + eventKeyArray[k] + "  -  Exp: " + ChatColor.YELLOW + eventIDString);
	        		}
	        	}
        	}
	        	
    	}
	}
	
}