package com.tanpn.worldgifts.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.tanpn.worldgifts.config.PhraseConfig;
import com.tanpn.worldgifts.util.Msg;

public class HelpCommand extends EXTCommand
{
	public HelpCommand()
	{
		super("help", CommandHandler.BASE_COMMAND_PERMISSION + "help", 0);
	}

	@Override
	public void action (CommandSender sender, String[] args) throws CommandException
	{
		Msg.send(sender, "&b\u2592\u2592\u2592\u2592\u2592 WorldGifts Help \u2592\u2592\u2592\u2592\u2592");
		Msg.send(sender, "&f/worldgifts &7" + PhraseConfig.Help__Cmd_PluginInfo.val());
		Msg.send(sender, "&f/worldgifts help &7" + PhraseConfig.Help__Cmd_Help.val());
		Msg.send(sender, "&f/worldgifts list <world> &7" + PhraseConfig.Help__Cmd_List.val());
		Msg.send(sender, "&f/worldgifts setmaxtimes <world> <max get times> &7" + PhraseConfig.Help__Cmd_SetMaxTimes.val());
		Msg.send(sender, "&f/worldgifts resetplayer <world> <player name> &7" + PhraseConfig.Help__Cmd_Reset_Player.val());
		Msg.send(sender, "&f/worldgifts put <world> &7" + PhraseConfig.Help__Cmd_Put.val());
		Msg.send(sender, "&f/worldgifts remove <world> <item index> &7" + PhraseConfig.Help__Cmd_Remove.val());
		Msg.send(sender, "&f/worldgifts reload &7" + PhraseConfig.Help__Cmd_Reload.val());
		Msg.send(sender, "&b\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592");
	}
}
