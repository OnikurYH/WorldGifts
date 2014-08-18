package com.tanpn.worldgifts;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
				Msg.send(sender, plugin.getPhrase().getConfig().getString("No_Premission"));
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
						Msg.send(sender, plugin.getPhrase().getConfig().getString("No_Premission"));
						return true;
					}
					
					cmd_help(sender);
					break;
			
				case "list":
					if (!sender.hasPermission("worldgifts.list"))
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("No_Premission"));
						return true;
					}
					if (args.length < 2)
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("Args_Incorrect"));
						return true;
					}
					
					cmd_list (sender, args[1]);
					break;
				
				case "setmaxtimes":
					if (!sender.hasPermission("worldgifts.setmaxtimes"))
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("No_Premission"));
						return true;
					}
					if (args.length < 3)
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("Args_Incorrect"));
						return true;
					}
					
					cmd_setmaxtimes (sender, args[1], Integer.parseInt(args[2]));
					break;
					
				case "put":
					if (!(sender instanceof Player))
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("Only_Player_Cmd"));
						return true;
					}
					if (!sender.hasPermission("worldgifts.put"))
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("No_Premission"));
						return true;
					}
					if (args.length < 2)
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("Args_Incorrect"));
						return true;
					}
					
					player = (Player) sender;
					cmd_put (player, args[1]);
					break;
					
				case "remove":
					if (!(sender instanceof Player))
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("Only_Player_Cmd"));
						return true;
					}
					if (!sender.hasPermission("worldgifts.remove"))
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("No_Premission"));
						return true;
					}
					if (args.length < 3)
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("Args_Incorrect"));
						return true;
					}
					
					player = (Player) sender;
					cmd_remove (player, args[1], Integer.parseInt(args[2]));
					break;
					
				case "reload":
					if (!sender.hasPermission("worldgifts.reload"))
					{
						Msg.send(sender, plugin.getPhrase().getConfig().getString("No_Premission"));
						return true;
					}
					
					cmd_reload(sender);
					break;
					
				default:
					Msg.send(sender, plugin.getPhrase().getConfig().getString("Command_Not_Find"));
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
		Msg.send(sender, "&f/worldgifts &7" + plugin.getPhrase().getConfig().getString("Help.Cmd_PluginInfo"));
		Msg.send(sender, "&f/worldgifts help &7" + plugin.getPhrase().getConfig().getString("Help.Cmd_Help"));
		Msg.send(sender, "&f/worldgifts list <world> &7" + plugin.getPhrase().getConfig().getString("Help.Cmd_List"));
		Msg.send(sender, "&f/worldgifts setmaxtimes <world> <max get times> &7" + plugin.getPhrase().getConfig().getString("Help.Cmd_SetMaxTimes"));
		Msg.send(sender, "&f/worldgifts put <world> &7" + plugin.getPhrase().getConfig().getString("Help.Cmd_Put"));
		Msg.send(sender, "&f/worldgifts remove <world> <item index> &7" + plugin.getPhrase().getConfig().getString("Help.Cmd_Remove"));
		Msg.send(sender, "&f/worldgifts reload &7" + plugin.getPhrase().getConfig().getString("Help.Cmd_Reload"));
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
		plugin.getPhrase().reloadConfig();
		
		Msg.send(sender, plugin.getPhrase().getConfig().getString("Plugin_Reloaded"));
	}
}
