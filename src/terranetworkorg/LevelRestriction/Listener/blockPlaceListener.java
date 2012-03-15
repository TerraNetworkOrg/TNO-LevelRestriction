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
import org.bukkit.event.block.BlockPlaceEvent;

public class blockPlaceListener implements Listener {
		
	@SuppressWarnings("unused")
	private final LevelRestriction plugin;
	 	 
	public blockPlaceListener(LevelRestriction plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent event){
		
		int eventBlockID = event.getBlock().getTypeId();
		String itemString = (new Integer(eventBlockID)).toString();
    	
    	if(LevelRestriction.onPlaceKeys.contains(itemString)){
    		
    		ConfigurationSection onPlaceSectionIDs = LevelRestriction.event.getConfigurationSection("Event.onPlace.BlockID." + eventBlockID + ".Class");
    		Set<String> allKeys = onPlaceSectionIDs.getKeys(false);
    		String[] keyArray = allKeys.toArray(new String[0]);
    		
    		for(int i=0; i<allKeys.size(); i++){
    			Player player = event.getPlayer();
    			/*player.sendMessage("Class: " + keyArray[i] + ", Exp: " + experienceHandler.getClassExperience("onBreak", eventBlockID, keyArray[i]));*/
    			playerHandler.addUserClassExperience(player, player.getName(), keyArray[i], experienceHandler.getClassExperience("onPlace", eventBlockID, keyArray[i]));
    			playerHandler.checkUserLevelUp(player, player.getName(), keyArray[i], playerHandler.getUserClassExperience(player.getName(), keyArray[i]), playerHandler.getUserClassLevel(player.getName(), keyArray[i]));
    		}
    		
    	} else{
    		return;
    	}
		
	}
	
}