package com.tanpn.worldgifts.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.tanpn.worldgifts.WorldGifts;

public class FConfig
{
	private final WorldGifts plugin;
	private final String fileName;
	
	private FileConfiguration config = null;
	private File configFile = null;
	
	public FConfig (WorldGifts plugin, String fileName)
	{
		this.plugin = plugin;
		this.fileName = fileName;
	}
	
	public void reloadConfig()
	{
	    if (configFile == null)
	    {
	    	configFile = new File(plugin.getDataFolder(), fileName);
	    }
	    config = YamlConfiguration.loadConfiguration(configFile);
	 
		InputStream defConfigStream = plugin.getResource(fileName);
		if (defConfigStream != null)
		{
		    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		    config.setDefaults(defConfig);
		}
	}
	
	public FileConfiguration getConfig()
	{
	    if (config == null)
	    {
	        reloadConfig();
	    }
	    return config;
	}
	
	public void saveConfig()
	{
	    if (config == null || configFile == null)
	    {
	        return;
	    }
	    try
	    {
	        getConfig().save(configFile);
	    }
	    catch (IOException ex) {}
	}
	
	public void saveDefaultConfig()
	{
	    if (configFile == null)
	    {
	    	configFile = new File(plugin.getDataFolder(), fileName);
	    }
	    if (!configFile.exists())
	    {            
	         plugin.saveResource(fileName, false);
	     }
	}
}
