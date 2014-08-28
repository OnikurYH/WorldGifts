package com.tanpn.worldgifts.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.tanpn.worldgifts.WorldGifts;

public class SetMaxTimesCommand extends EXTCommand
{
	public SetMaxTimesCommand()
	{
		super("setmaxtimes", CommandHandler.BASE_COMMAND_PERMISSION + "setmaxtimes", 2);
	}

	@Override
	public void action (CommandSender sender, String[] args) throws CommandException
	{
		WorldGifts.getSelf().getWorldManager().setMaxGetTimes(
				sender,
				args[1],
				Integer.parseInt(args[2]));
	}
}
