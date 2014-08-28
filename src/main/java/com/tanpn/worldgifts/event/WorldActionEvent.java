package com.tanpn.worldgifts.event;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.tanpn.worldgifts.WorldGifts;
import com.tanpn.worldgifts.config.PhraseConfig;
import com.tanpn.worldgifts.data.AddItemToPlayer;
import com.tanpn.worldgifts.util.Msg;

public class WorldActionEvent implements Listener
{
    public WorldActionEvent()
    {
    	WorldGifts.getSelf().getServer().getPluginManager().registerEvents(this, WorldGifts.getSelf());
    }
    
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event)
    {
    	if (WorldGifts.getSelf().getPlayerSaves().isPlayerAlreadyGetGift(
    			event.getPlayer().getWorld().getName(),
    			WorldGifts.getSelf().getWorldManager().getMaxGetTimes(event.getPlayer().getWorld().getName()),
    			event.getPlayer().getUniqueId()))
    	{
    		return;
    	}
    	
    	giveItemToPlayer(event.getPlayer(), event.getPlayer().getWorld().getName());
    }
    
    @EventHandler
    public void onPlayerChangedWorld (PlayerChangedWorldEvent event)
    {
    	if (WorldGifts.getSelf().getPlayerSaves().isPlayerAlreadyGetGift(
    			event.getPlayer().getWorld().getName(),
    			WorldGifts.getSelf().getWorldManager().getMaxGetTimes(event.getPlayer().getWorld().getName()),
    			event.getPlayer().getUniqueId()))
    	{
    		return;
    	}
    	
    	giveItemToPlayer(event.getPlayer(), event.getPlayer().getWorld().getName());
    }
    
    private void giveItemToPlayer (Player player, String worldName)
    {
    	List<ItemStack> items = null;
    	if ((items = WorldGifts.getSelf().getWorldManager().getItems(worldName)) != null)
    	{
    		Msg.send(player, PhraseConfig.GET_GIFT.val());
    		
    		player.getServer().getScheduler().scheduleSyncDelayedTask(
    				WorldGifts.getSelf(), 
    				new AddItemToPlayer(player, items.toArray(new ItemStack[items.size()])),
    				5);
    		WorldGifts.getSelf().getPlayerSaves().addPlayer(worldName, player.getUniqueId());
    	}
    }
}
