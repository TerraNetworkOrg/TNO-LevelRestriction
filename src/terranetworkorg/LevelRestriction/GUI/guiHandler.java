package terranetworkorg.LevelRestriction.GUI;

import java.util.HashMap;
import terranetworkorg.LevelRestriction.LevelRestriction;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;

public class guiHandler{
		
	private static LevelRestriction plugin;
	protected static HashMap<Player, Widget> screens = new HashMap<Player, Widget>();
	 	 
	public guiHandler(LevelRestriction plugin)
	{
		guiHandler.plugin = plugin;
	}	
	
    public static void setGUI(SpoutPlayer player, String userClass, int userLevel, double playerExp, double levelExp)
    {
    	/*LevelRestriction.expLabel.setAlign(WidgetAnchor.TOP_LEFT);
    	LevelRestriction.expLabel.setAnchor(WidgetAnchor.CENTER_CENTER);*/
    	LevelRestriction.expLabel.setWidth(100).setHeight(20);
    	LevelRestriction.expLabel.setX(5).setY(5);
    	LevelRestriction.expLabel.setText(userClass + " lvl " + userLevel + " : " + playerExp + "/" + levelExp);
	    player.getMainScreen().attachWidget(plugin, LevelRestriction.expLabel);
	    LevelRestriction.expLabelVerifier = true;
	}
    
	public static void updateGUI(SpoutPlayer player, String userClass, int userLevel, double playerExp, double levelExp)
	{
		LevelRestriction.expLabel.setText(userClass + " lvl " + userLevel + " : " + playerExp + "/" + levelExp);
		LevelRestriction.expLabel.updateSize();
	}
	
}
