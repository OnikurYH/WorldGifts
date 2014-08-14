package com.tanpn.worldgifts.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tanpn.worldgifts.WorldGifts;
import com.tanpn.worldgifts.util.Msg;

public class WorldManager
{
	private final WorldGifts plugin;
	private final FileConfiguration gifts;
	
	public WorldManager (WorldGifts plugin, FConfig gifts)
	{
		this.plugin = plugin;
		this.gifts = plugin.getGifts().getConfig();
	}
	
	public boolean isWorldHaveData (String worldName)
	{
		worldName = worldName.toLowerCase();
		
		if (!gifts.contains("Worlds." + worldName))
		{
			return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public void addItem (Player player, String worldName, ItemStack item)
	{
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			gifts.set("Worlds." + worldName, new ArrayList<ItemStack>());
		}
		
		List<ItemStack> items = (List<ItemStack>) gifts.getList("Worlds." + worldName);
		items.add(item);
		gifts.set("Worlds." + worldName, items);
		
		Msg.send(player, plugin.getPhrase().getConfig().getString("Item_Added"));
	}
	
	@SuppressWarnings("unchecked")
	public void removeItem (Player player, String worldName, int itemIndex)
	{
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			Msg.send(player, plugin.getPhrase().getConfig().getString("World_No_Data"));
			return;
		}
		if (((List<ItemStack>) gifts.getList("Worlds." + worldName)).size() < (itemIndex-1))
		{
			return;
		}
		
		List<ItemStack> items = (List<ItemStack>) gifts.getList("Worlds." + worldName);
		items.remove(itemIndex-1);
		gifts.set("Worlds." + worldName, items);
		
		Msg.send(player, plugin.getPhrase().getConfig().getString("Item_Remove"));
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemStack> getItems (String worldName)
	{
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			return null;
		}
		if (((List<ItemStack>) gifts.getList("Worlds." + worldName)).size() <= 0)
		{
			return null;
		}
		
		return (List<ItemStack>) gifts.getList("Worlds." + worldName);
	}
	
	@SuppressWarnings("unchecked")
	public void printItemList (CommandSender sender, String worldName)
	{
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			Msg.send(sender, plugin.getPhrase().getConfig().getString("World_No_Data"));
			return;
		}
		if (((List<ItemStack>) gifts.getList("Worlds." + worldName)).size() <= 0)
		{
			Msg.send(sender, plugin.getPhrase().getConfig().getString("World_No_Item"));
			return;
		}
		
		Msg.send(sender, "&b\u2592\u2592\u2592 &a" + worldName + " &b\u2592\u2592\u2592");
		Msg.send(sender, "&7Item Index | Item");
		
		List<ItemStack> items = (List<ItemStack>) gifts.getList("Worlds." + worldName);
		for (int i = 0; i < items.size(); i++)
		{
			ItemStack item = items.get(i);
			
			Msg.send(sender, "- &f" + (i + 1) + " " +
			(item.hasItemMeta() ?
					(item.getItemMeta().hasDisplayName() ?
							item.getItemMeta().getDisplayName() : item.getType().toString()) : item.getType().toString()) +
							":" +  item.getDurability() +
							"x" + item.getAmount());
		}
	}
}
