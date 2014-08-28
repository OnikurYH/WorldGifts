package com.tanpn.worldgifts.command;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tanpn.worldgifts.WorldGifts;
import com.tanpn.worldgifts.config.PhraseConfig;
import com.tanpn.worldgifts.util.Msg;

public class CommandHandler implements CommandExecutor
{
	public final static String BASE_COMMAND_PERMISSION = "worldgifts.";
	
	private final WorldGifts plugin;
	private Map<String, EXTCommand> commands;
	
	@SuppressWarnings("serial")
	public CommandHandler(WorldGifts plugin)
	{
		this.plugin = plugin;
		this.commands =
				new HashMap<String, EXTCommand>()
				{{
					put ("help", new HelpCommand());
					put ("list", new ListCommand());
					put ("setmaxtimes", new SetMaxTimesCommand());
					put ("resetplayer", new ResetPlayerCommand());
					put ("put", new PutCommand());
					put ("remove", new RemoveCommand());
					put ("reload", new ReloadCommand());
				}};
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length == 0)
		{
			if (!sender.hasPermission("worldgifts.plugininfo"))
			{
				Msg.send(sender, PhraseConfig.No_Premission.val());
				return true;
			}
			
			cmd_info(sender);
			return true;
		}
		
		String subCmd = args[0].toLowerCase();
		if (commands.containsKey(subCmd))
		{
			EXTCommand excmd = commands.get(subCmd);
			
			if (!excmd.hasPermission(sender))
			{
				Msg.send(sender, PhraseConfig.No_Premission.val());
				return true;
			}
			
			if (args.length < excmd.getMinArgs())
			{
				Msg.send(sender, PhraseConfig.Args_Incorrect.val());
				return true;
			}
			
			excmd.action(sender, args);
		}
		else
		{
			Msg.send(sender, PhraseConfig.Command_Not_Find.val());
		}
		
		return true;
	}
	
	private void cmd_info (CommandSender sender)
	{
		Msg.send(sender, "&b\u2592\u2592\u2592\u2592\u2592 WorldGifts \u2592\u2592\u2592\u2592\u2592");
		Msg.send(sender, "&fVersion : " + plugin.getDescription().getVersion());
		Msg.send(sender, "&fProject Developer : TsnYikHei");
		Msg.send(sender, "&aType ''/worldgifts help'' for help.");
		Msg.send(sender, "&b\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592");
	}
}
