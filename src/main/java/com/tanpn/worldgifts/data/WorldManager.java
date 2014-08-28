package com.tanpn.worldgifts.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tanpn.worldgifts.WorldGifts;
import com.tanpn.worldgifts.config.FConfig;
import com.tanpn.worldgifts.config.PhraseConfig;
import com.tanpn.worldgifts.util.Msg;

public class WorldManager
{
	private final FileConfiguration gifts;
	
	public WorldManager (FConfig gifts)
	{
		this.gifts = WorldGifts.getSelf().getGifts().getConfig();
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
	
	public void setMaxGetTimes (CommandSender sender, String worldName, int maxGetTimes)
	{
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			return;
		}
		
		if (maxGetTimes <= -1)
		{
			maxGetTimes = -1;
			Msg.send(sender, PhraseConfig.Max_Get_Times_Set_Infinite.val());
		}
		else if (maxGetTimes == 0)
		{
			Msg.send(sender, PhraseConfig.Max_Get_Times_Cannot_Zero.val());
			return;
		}
		else
		{
			Msg.send(sender, PhraseConfig.Max_Get_Times_Set.val());
		}
		
		gifts.set("Worlds." + worldName + ".Max_Get_Times", maxGetTimes);
	}
	
	public int getMaxGetTimes (String worldName)
	{
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			return -1;
		}
		if (!gifts.contains("Worlds." + worldName + ".Max_Get_Times"))
		{
			return -1;
		}
		
		return gifts.getInt("Worlds." + worldName + ".Max_Get_Times");
	}
	
	@SuppressWarnings("unchecked")
	public void addItem (Player player, String worldName, ItemStack item)
	{
		if (item.getType() == Material.AIR)
		{
			Msg.send(player, PhraseConfig.Cannot_Add_Air.val());
			return;
		}
		
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			gifts.set("Worlds." + worldName + ".Max_Get_Times", 1);
			gifts.set("Worlds." + worldName + ".Gifts", new ArrayList<ItemStack>());
		}
		
		List<ItemStack> items = (List<ItemStack>) gifts.getList("Worlds." + worldName + ".Gifts");
		items.add(item);
		gifts.set("Worlds." + worldName + ".Gifts", items);
		
		Msg.send(player, PhraseConfig.Item_Added.val());
	}
	
	@SuppressWarnings("unchecked")
	public void removeItem (Player player, String worldName, int itemIndex)
	{
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			Msg.send(player, PhraseConfig.World_No_Data.val());
			return;
		}
		if (((List<ItemStack>) gifts.getList("Worlds." + worldName + ".Gifts")).size() < (itemIndex-1))
		{
			return;
		}
		
		List<ItemStack> items = (List<ItemStack>) gifts.getList("Worlds." + worldName + ".Gifts");
		items.remove(itemIndex-1);
		gifts.set("Worlds." + worldName + ".Gifts", items);
		
		Msg.send(player, PhraseConfig.Item_Remove.val());
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemStack> getItems (String worldName)
	{
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			return null;
		}
		if (((List<ItemStack>) gifts.getList("Worlds." + worldName + ".Gifts")).size() <= 0)
		{
			return null;
		}
		
		return (List<ItemStack>) gifts.getList("Worlds." + worldName + ".Gifts");
	}
	
	@SuppressWarnings("unchecked")
	public void printItemList (CommandSender sender, String worldName)
	{
		worldName = worldName.toLowerCase();
		
		if (!isWorldHaveData(worldName))
		{
			Msg.send(sender, PhraseConfig.World_No_Data.val());
			return;
		}
		if (((List<ItemStack>) gifts.getList("Worlds." + worldName + ".Gifts")).size() <= 0)
		{
			Msg.send(sender, PhraseConfig.World_No_Item.val());
			return;
		}
		
		Msg.send(sender, "&b\u2592\u2592\u2592 &a" + worldName + " &b\u2592\u2592\u2592");
		int maxGetTimes = gifts.getInt("Worlds." + worldName + ".Max_Get_Times");
		Msg.send(sender, "&7Get items per player : " + (maxGetTimes == -1 ? "\u221E Infinity" : maxGetTimes));
		Msg.send(sender, "&7Item Index | Item");
		
		List<ItemStack> items = (List<ItemStack>) gifts.getList("Worlds." + worldName + ".Gifts");
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
