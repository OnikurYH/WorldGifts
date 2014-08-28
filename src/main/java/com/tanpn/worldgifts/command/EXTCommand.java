package com.tanpn.worldgifts.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public abstract class EXTCommand
{
	private final String cmd;
	private final String permission;
	private final int minargs;
	
	public EXTCommand (String cmd, String permission, int minargs)
	{
		this.cmd = cmd;
		this.permission = permission;
		this.minargs = minargs;
	}
	
	public String getCmd ()
	{
		return cmd;
	}
	
	public String getPermission ()
	{
		return permission;
	}
	
	public boolean hasPermission (CommandSender sender)
	{
		if (permission == null) return true;
		return sender.hasPermission(permission);
	}
	
	public int getMinArgs ()
	{
		return minargs + 1;
	}
	
	public abstract void action (CommandSender sender, String[] args) throws CommandException;
}
