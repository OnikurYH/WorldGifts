package com.tanpn.worldgifts.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.tanpn.worldgifts.WorldGifts;

public class ListCommand extends EXTCommand
{
	public ListCommand()
	{
		super("list", CommandHandler.BASE_COMMAND_PERMISSION + "list", 1);
	}

	@Override
	public void action (CommandSender sender, String[] args) throws CommandException
	{
		WorldGifts.getSelf().getWorldManager().printItemList(sender, args[1]);
	}
}
