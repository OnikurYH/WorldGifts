package com.tanpn.worldgifts.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tanpn.worldgifts.WorldGifts;

public class PutCommand extends EXTCommand
{
	public PutCommand()
	{
		super("put", CommandHandler.BASE_COMMAND_PERMISSION + "put", 1);
	}

	@Override
	public void action (CommandSender sender, String[] args) throws CommandException
	{
		if (!(sender instanceof Player)) return;
		
		Player p = (Player) sender;
		WorldGifts.getSelf().getWorldManager().addItem(p, args[1], p.getItemInHand());
	}
}
