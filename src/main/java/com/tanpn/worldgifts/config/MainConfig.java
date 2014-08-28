package com.tanpn.worldgifts.config;

import org.bukkit.configuration.file.FileConfiguration;

import com.tanpn.worldgifts.WorldGifts;

public enum MainConfig
{
	Gift_Permission_Per_World (false),
	Gift_Permission_Per_World_Op_Override (true);
	
	private final String _path;
	private final Object _val;
	
	private MainConfig (Object defval)
	{
		this._path = name().replaceAll("__", ".");
		this._val = WorldGifts.getSelf().getConfig().get(_path, defval);
	}
	
	public String getPath ()
	{
		return _path;
	}
	
	public Object getObject ()
	{
		return _val;
	}
	
	public String getString ()
	{
		return (String) _val;
	}
	
	public boolean getBool ()
	{
		return (boolean) _val;
	}
	
	public static void forceSave ()
	{
        FileConfiguration config = WorldGifts.getSelf().getConfig();
        for (MainConfig set : MainConfig.values())
        {
            if (!config.contains(set.getPath()))
            {
                config.set(set.getPath(), set.getObject());
            }
        }
        WorldGifts.getSelf().saveConfig();
    }
}
