package com.tanpn.worldgifts.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Msg
{
	private final static String PLUGIN_TAG = ChatColor.AQUA + "[WorldGifts] ";
	
	public static void send (CommandSender sender, String msg)
	{
		sender.sendMessage(formatMsg(msg));
	}
	
	public static void send (Player sender, String msg)
	{
		sender.sendMessage(formatMsg(msg));
	}
	
	public static void sendConsole (String msg)
	{
		Bukkit.getConsoleSender().sendMessage(formatMsg(msg));
	}
	
	public static String formatMsg (String msg)
	{
		return PLUGIN_TAG + ChatColor.translateAlternateColorCodes('&', msg);
	}
}
