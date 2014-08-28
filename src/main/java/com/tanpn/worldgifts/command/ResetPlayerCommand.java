package com.tanpn.worldgifts.command;

import java.util.UUID;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.tanpn.worldgifts.WorldGifts;

public class ResetPlayerCommand extends EXTCommand
{
	public ResetPlayerCommand()
	{
		super("resetplayer", CommandHandler.BASE_COMMAND_PERMISSION + "resetplayer", 2);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void action (CommandSender sender, String[] args) throws CommandException
	{
		UUID targetUUID = WorldGifts.getSelf().getServer().getPlayer(args[2]).getUniqueId();
		
		WorldGifts.getSelf().getPlayerSaves().resetPlayer(
				sender,
				args[1],
				targetUUID);
	}
}
