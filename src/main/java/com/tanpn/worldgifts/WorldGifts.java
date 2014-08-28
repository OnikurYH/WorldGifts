package com.tanpn.worldgifts;

import org.bukkit.plugin.java.JavaPlugin;

import com.tanpn.worldgifts.config.FConfig;
import com.tanpn.worldgifts.config.MainConfig;
import com.tanpn.worldgifts.config.PhraseConfig;
import com.tanpn.worldgifts.data.PlayerSaves;
import com.tanpn.worldgifts.data.WorldManager;
import com.tanpn.worldgifts.event.WorldActionEvent;
import com.tanpn.worldgifts.util.Msg;

public final class WorldGifts extends JavaPlugin
{
	private static WorldGifts self;
	
	private FConfig phrase;
	private FConfig gifts;
	private WorldManager worldManager;
	private PlayerSaves playerSaves;
	
	@Override
    public void onEnable()
	{
		WorldGifts.self = this;
		
		initConfig ();
		
		this.getCommand("worldgifts").setExecutor(new CommandHandler(this));
		new WorldActionEvent();
		
		worldManager = new WorldManager (gifts);
		
		Msg.sendConsole("&7Loading player saved data...");
		playerSaves = new PlayerSaves();
		playerSaves.LoadAllMapData ();
		
        Msg.sendConsole("&aStart !");
    }
 
    @Override
    public void onDisable()
    {
    	gifts.saveConfig();
    	playerSaves.saveAllMapData ();
    	this.getConfig().options().header("WorldGift Config File");
    	this.saveConfig();
    	Msg.sendConsole("&cStop !");
    }
    
    private void initConfig ()
    {
    	this.saveDefaultConfig();
		MainConfig.forceSave();
		
		phrase = new FConfig(this, "phrase.yml", "");
		phrase.saveDefaultConfig();
		PhraseConfig.useConfig(phrase);
		
		gifts = new FConfig(this, "gifts.yml", "");
		gifts.saveDefaultConfig();
    }
    
    public static WorldGifts getSelf ()
    {
    	return WorldGifts.self;
    }
    
    public FConfig getGifts ()
    {
    	return gifts;
    }
    
    public WorldManager getWorldManager ()
    {
    	return worldManager;
    }
    
    public PlayerSaves getPlayerSaves ()
    {
    	return playerSaves;
    }
}
