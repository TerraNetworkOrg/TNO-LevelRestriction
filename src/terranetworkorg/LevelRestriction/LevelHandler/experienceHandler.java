package terranetworkorg.LevelRestriction.LevelHandler;


import terranetworkorg.LevelRestriction.LevelRestriction;


public class experienceHandler {
	
	public static double getClassExperience(String onEvent, int eventBlockID, String className){
		
		double getExperience = LevelRestriction.event.getDouble("Event." + onEvent + ".BlockID." + eventBlockID + ".Class." + className);
		
		return getExperience;
	}
	
	public static double getClassExperience(String onEvent, String eventKillerID, String className){
		
		double getExperience = LevelRestriction.event.getDouble("Event." + onEvent + ".EnemyName." + eventKillerID + ".Class." + className);
		
		return getExperience;
	}
	
	public static int getLevelEXP(int userLevel){
		
		int nextLevelExp = ((userLevel * LevelRestriction.config.getInt("Config.levelBaseExperience", 20)) + ((userLevel - 1) * LevelRestriction.config.getInt("Config.levelBaseExperience", 20)));
		
		return nextLevelExp;
		
	}
	
}