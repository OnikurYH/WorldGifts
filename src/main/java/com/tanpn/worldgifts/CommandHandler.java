package com.tanpn.worldgifts;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tanpn.worldgifts.config.PhraseConfig;
import com.tanpn.worldgifts.util.Msg;

public class CommandHandler implements CommandExecutor
{
	private final WorldGifts plugin;
	
	public CommandHandler(WorldGifts plugin)
	{
		this.plugin = plugin;
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
		}
		else
		{
			Player player = null;
			
			switch (args[0].toLowerCase())
			{
				case "help":
				case "?":
					if (!sender.hasPermission("worldgifts.help"))
					{
						Msg.send(sender, PhraseConfig.No_Premission.val());
						return true;
					}
					
					cmd_help(sender);
					break;
			
				case "list":
					if (!sender.hasPermission("worldgifts.list"))
					{
						Msg.send(sender, PhraseConfig.No_Premission.val());
						return true;
					}
					if (args.length < 2)
					{
						Msg.send(sender, PhraseConfig.Args_Incorrect.val());
						return true;
					}
					
					cmd_list (sender, args[1]);
					break;
				
				case "setmaxtimes":
					if (!sender.hasPermission("worldgifts.setmaxtimes"))
					{
						Msg.send(sender, PhraseConfig.No_Premission.val());
						return true;
					}
					if (args.length < 3)
					{
						Msg.send(sender, PhraseConfig.Args_Incorrect.val());
						return true;
					}
					
					cmd_setmaxtimes (sender, args[1], Integer.parseInt(args[2]));
					break;
				
				case "resetplayer":
					if (!sender.hasPermission("worldgifts.resetplayer"))
					{
						Msg.send(sender, PhraseConfig.No_Premission.val());
						return true;
					}
					if (args.length < 3)
					{
						Msg.send(sender, PhraseConfig.Args_Incorrect.val());
						return true;
					}
					
					player = (Player) sender;
					cmd_resetplayer (player, args[1], args[2]);
					break;
					
				case "put":
					if (!(sender instanceof Player))
					{
						Msg.send(sender, PhraseConfig.Only_Player_Cmd.val());
						return true;
					}
					if (!sender.hasPermission("worldgifts.put"))
					{
						Msg.send(sender, PhraseConfig.No_Premission.val());
						return true;
					}
					if (args.length < 2)
					{
						Msg.send(sender, PhraseConfig.Args_Incorrect.val());
						return true;
					}
					
					player = (Player) sender;
					cmd_put (player, args[1]);
					break;
					
				case "remove":
					if (!(sender instanceof Player))
					{
						Msg.send(sender, PhraseConfig.Only_Player_Cmd.val());
						return true;
					}
					if (!sender.hasPermission("worldgifts.remove"))
					{
						Msg.send(sender, PhraseConfig.No_Premission.val());
						return true;
					}
					if (args.length < 3)
					{
						Msg.send(sender, PhraseConfig.Args_Incorrect.val());
						return true;
					}
					
					player = (Player) sender;
					cmd_remove (player, args[1], Integer.parseInt(args[2]));
					break;
					
				case "reload":
					if (!sender.hasPermission("worldgifts.reload"))
					{
						Msg.send(sender, PhraseConfig.No_Premission.val());
						return true;
					}
					
					cmd_reload(sender);
					break;
					
				default:
					Msg.send(sender, PhraseConfig.Command_Not_Find.val());
					break;
			}
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
	
	private void cmd_help (CommandSender sender)
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
	
	private void cmd_list (CommandSender sender, String worldName)
	{
		plugin.getWorldManager().printItemList(sender, worldName);
	}
	
	private void cmd_setmaxtimes (CommandSender sender, String worldName, int maxGetTimes)
	{
		plugin.getWorldManager().setMaxGetTimes(sender, worldName, maxGetTimes);
	}
	
	@SuppressWarnings("deprecation")
	private void cmd_resetplayer (CommandSender sender, String worldName, String playerName)
	{
		plugin.getPlayerSaves().resetPlayer(sender , worldName, plugin.getServer().getPlayer(playerName).getUniqueId());
	}
	
	private void cmd_put (Player player, String worldName)
	{
		plugin.getWorldManager().addItem(player, worldName, player.getItemInHand());
	}
	
	private void cmd_remove (Player player, String worldName, int itemIndex)
	{
		plugin.getWorldManager().removeItem(player, worldName, itemIndex);
	}
	
	private void cmd_reload (CommandSender sender)
	{
		plugin.reloadConfig();
		PhraseConfig.reloadConfig();
		
		Msg.send(sender, PhraseConfig.Plugin_Reloaded.val());
	}
}
