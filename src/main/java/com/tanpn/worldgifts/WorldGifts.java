package com.tanpn.worldgifts;

import org.bukkit.plugin.java.JavaPlugin;

import com.tanpn.worldgifts.data.FConfig;
import com.tanpn.worldgifts.data.PlayerSaves;
import com.tanpn.worldgifts.data.WorldManager;
import com.tanpn.worldgifts.event.WorldActionEvent;
import com.tanpn.worldgifts.util.Msg;

public class WorldGifts extends JavaPlugin
{
	private FConfig phrase;
	private FConfig gifts;
	private WorldManager worldManager;
	private PlayerSaves playerSaves;
	
	@Override
    public void onEnable()
	{
		this.saveDefaultConfig();
		phrase = new FConfig(this, "phrase.yml", "");
		phrase.saveDefaultConfig();
		gifts = new FConfig(this, "gifts.yml", "");
		gifts.saveDefaultConfig();
		
		this.getCommand("worldgifts").setExecutor(new CommandHandler(this));
		new WorldActionEvent(this);
		
		worldManager = new WorldManager (this, gifts);
		playerSaves = new PlayerSaves(this);
		
		Msg.sendConsole("&7Loading player saved data...");
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
    
    public FConfig getPhrase ()
    {
    	return phrase;
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
