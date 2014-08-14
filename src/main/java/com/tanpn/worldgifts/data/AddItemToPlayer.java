package com.tanpn.worldgifts.data;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddItemToPlayer implements Runnable
{
	private Player player;
	private ItemStack[] items;
	
	public AddItemToPlayer (Player player, ItemStack[] items)
	{
		this.player = player;
		this.items = items;
	}
	
	@Override
    public void run()
	{
		player.getInventory().addItem(items);
	}
}
