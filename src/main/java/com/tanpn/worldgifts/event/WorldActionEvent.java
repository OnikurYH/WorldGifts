package com.tanpn.worldgifts.event;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.tanpn.worldgifts.WorldGifts;
import com.tanpn.worldgifts.data.AddItemToPlayer;
import com.tanpn.worldgifts.util.Msg;

public class WorldActionEvent implements Listener
{
	private final WorldGifts plugin;
	 
    public WorldActionEvent(WorldGifts plugin)
    {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event)
    {
    	if (plugin.getPlayerSaves().isPlayerAlreadyGetGift(
    			event.getPlayer().getWorld().getName(),
    			plugin.getWorldManager().getMaxGetTimes(event.getPlayer().getWorld().getName()),
    			event.getPlayer().getName()))
    	{
    		return;
    	}
    	
    	giveItemToPlayer(event.getPlayer(), event.getPlayer().getWorld().getName());
    }
    
    @EventHandler
    public void onPlayerChangedWorld (PlayerChangedWorldEvent event)
    {
    	if (plugin.getPlayerSaves().isPlayerAlreadyGetGift(
    			event.getPlayer().getWorld().getName(),
    			plugin.getWorldManager().getMaxGetTimes(event.getPlayer().getWorld().getName()),
    			event.getPlayer().getName()))
    	{
    		return;
    	}
    	
    	giveItemToPlayer(event.getPlayer(), event.getPlayer().getWorld().getName());
    }
    
    private void giveItemToPlayer (Player player, String worldName)
    {
    	List<ItemStack> items = null;
    	if ((items = plugin.getWorldManager().getItems(worldName)) != null)
    	{
    		Msg.send(player, plugin.getPhrase().getConfig().getString("GET_GIFT"));
    		
    		player.getServer().getScheduler().scheduleSyncDelayedTask(
    				plugin, 
    				new AddItemToPlayer(player, items.toArray(new ItemStack[items.size()])),
    				5);
    		plugin.getPlayerSaves().addPlayer(worldName, player.getName());
    	}
    }
}
