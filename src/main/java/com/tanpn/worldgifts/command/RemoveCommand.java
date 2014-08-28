package com.tanpn.worldgifts.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tanpn.worldgifts.WorldGifts;

public class RemoveCommand extends EXTCommand
{
	public RemoveCommand()
	{
		super("remove", CommandHandler.BASE_COMMAND_PERMISSION + "remove", 2);
	}

	@Override
	public void action (CommandSender sender, String[] args) throws CommandException
	{
		if (!(sender instanceof Player)) return;
		
		Player p = (Player) sender;
		WorldGifts.getSelf().getWorldManager().removeItem(p, args[1], Integer.parseInt(args[2]));
	}
}
