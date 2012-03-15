package terranetworkorg.LevelRestriction.LevelHandler;

import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import terranetworkorg.LevelRestriction.LevelRestriction;


public class classHandler {
	
	public static Set<String> getClassesList()
	{
		ConfigurationSection classSection = LevelRestriction.classes.getConfigurationSection("Class");
    	Set<String> classesKeys = classSection.getKeys(false);
    	
    	return classesKeys;
	}
	
	public static Set<String> getClassesLevelList(String className)
	{
		ConfigurationSection classLevelSection = LevelRestriction.classes.getConfigurationSection("Class." + className);
    	Set<String> classesLevelKeys = classLevelSection.getKeys(false);
    	
    	return classesLevelKeys;
	}
	
	public static String getClassesLevelGrant(String className, String classLevel)
	{
    	String classesLevelGrantKey = LevelRestriction.classes.getString("Class." + className + ".Level." + classLevel + ".Grant");
    	
    	return classesLevelGrantKey;
	}
	
	public static String getClassesLevelRequire(String className, String classLevel)
	{
    	String classesLevelRequireKey = LevelRestriction.classes.getString("Class." + className + ".Level." + classLevel + ".Require");
    	
    	return classesLevelRequireKey;
	}
	
}