package com.tanpn.worldgifts.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.tanpn.worldgifts.WorldGifts;
import com.tanpn.worldgifts.config.PhraseConfig;
import com.tanpn.worldgifts.util.Msg;

public class ReloadCommand extends EXTCommand
{
	public ReloadCommand()
	{
		super("reload", CommandHandler.BASE_COMMAND_PERMISSION + "reload", 0);
	}

	@Override
	public void action (CommandSender sender, String[] args) throws CommandException
	{
		WorldGifts.getSelf().reloadConfig();
		PhraseConfig.reloadConfig();
		
		Msg.send(sender, PhraseConfig.Plugin_Reloaded.val());
	}
}
