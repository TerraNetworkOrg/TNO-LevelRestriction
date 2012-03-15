package terranetworkorg.LevelRestriction.Listener;

import java.util.Set;

import terranetworkorg.LevelRestriction.LevelRestriction;
import terranetworkorg.LevelRestriction.LevelHandler.*;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class blockBreakListener implements Listener {
		
	@SuppressWarnings("unused")
	private final LevelRestriction plugin;
	 	 
	public blockBreakListener(LevelRestriction plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent event){
		
		int eventBlockID = event.getBlock().getTypeId();
		String itemString = (new Integer(eventBlockID)).toString();
    	
    	if(LevelRestriction.onBreakKeys.contains(itemString)){
    		
    		ConfigurationSection onBreakSectionIDs = LevelRestriction.event.getConfigurationSection("Event.onBreak.BlockID." + eventBlockID + ".Class");
    		Set<String> allKeys = onBreakSectionIDs.getKeys(false);
    		String[] keyArray = allKeys.toArray(new String[0]);
    		
    		for(int i=0; i<allKeys.size(); i++){
    			Player player = event.getPlayer();
    			/*player.sendMessage("Class: " + keyArray[i] + ", Exp: " + experienceHandler.getClassExperience("onBreak", eventBlockID, keyArray[i]));*/
    			playerHandler.addUserClassExperience(player, player.getName(), keyArray[i], experienceHandler.getClassExperience("onBreak", eventBlockID, keyArray[i]));
    			playerHandler.checkUserLevelUp(player, player.getName(), keyArray[i], playerHandler.getUserClassExperience(player.getName(), keyArray[i]), playerHandler.getUserClassLevel(player.getName(), keyArray[i]));
    		}
    		
    	} else{
    		return;
    	}
		
	}
	
}