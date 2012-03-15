package terranetworkorg.LevelRestriction.Listener;

import java.util.Set;

import terranetworkorg.LevelRestriction.LevelRestriction;
import terranetworkorg.LevelRestriction.LevelHandler.experienceHandler;
import terranetworkorg.LevelRestriction.LevelHandler.playerHandler;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class entityDeathListener implements Listener {
		
	@SuppressWarnings("unused")
	private final LevelRestriction plugin;
	 	 
	public entityDeathListener(LevelRestriction plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeath(EntityDeathEvent event){
		
		if(event.getEntity().getKiller() instanceof Player){
			String eventKillerID = event.getEntity().getClass().getSimpleName().replace("SpoutCraft", "").replace("Craft", "");
			String itemString = eventKillerID;
			
			Player yplayer = event.getEntity().getKiller();
			yplayer.sendMessage(itemString);
	    	
	    	if(LevelRestriction.onKillKeys.contains(itemString)){
	    		
	    		ConfigurationSection onKillSectionIDs = LevelRestriction.event.getConfigurationSection("Event.onKill.EnemyName." + eventKillerID + ".Class");
	    		Set<String> allKeys = onKillSectionIDs.getKeys(false);
	    		String[] keyArray = allKeys.toArray(new String[0]);
	    		
	    		for(int i=0; i<allKeys.size(); i++){
	    			Player player = event.getEntity().getKiller();
	    			/*player.sendMessage("Class: " + keyArray[i] + ", Exp: " + experienceHandler.getClassExperience("onBreak", eventBlockID, keyArray[i]));*/
	    			playerHandler.addUserClassExperience(player, player.getName(), keyArray[i], experienceHandler.getClassExperience("onKill", eventKillerID, keyArray[i]));
	    			playerHandler.checkUserLevelUp(player, player.getName(), keyArray[i], playerHandler.getUserClassExperience(player.getName(), keyArray[i]), playerHandler.getUserClassLevel(player.getName(), keyArray[i]));
	    		}
	    		
	    	} else{
	    		return;
	    	}
		} else{
			return;
		}
		
	}
	
}