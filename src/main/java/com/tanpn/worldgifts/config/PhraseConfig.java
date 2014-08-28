package com.tanpn.worldgifts.config;

public enum PhraseConfig
{
	Command_Not_Find("&cCommand not find! Please type ''/worldgifts help'' for help."),
	No_Premission("&cYou don't have this permission!"),
	Args_Incorrect("&cPlease type \"/worldgifts help\" to see how to use this command."),
	Only_Player_Cmd("&cThis command only can player to use."),
	Plugin_Reloaded("&aPlugin reloaded!"),
	GET_GIFT("&dWelcome! Here is you gift!"),
	Max_Get_Times_Set("&aSuccessful to set the maximum get gift times!"),
	Max_Get_Times_Set_Infinite("&aSuccessful to set the maximum get gift times infinitely!"),
	Max_Get_Times_Cannot_Zero("&cCannot set the maximum get gift times is zero!"),
	Reset_Player("&aThis player gift status has been reset!"),
	Item_Added("&aItem successful to added!"),
	Item_Remove("&aItem successful to &cremove&a!"),
	Cannot_Add_Air("&cCannot add air to gift!"),
	World_No_Data("&cThis world haven't added to this plugin!"),
	World_No_Item("&cThis world haven't added items!"),
	Help__Cmd_PluginInfo("&7WorldGifts info"),
	Help__Cmd_Help("&7WorldGifts help"),
	Help__Cmd_List("&7List all world items"),
	Help__Cmd_SetMaxTimes("&7Set maximum get gift times (-1 is infinitely)"),
	Help__Cmd_Reset_Player("&7Reset a player gift status"),
	Help__Cmd_Put("&7Put a item to world"),
	Help__Cmd_Remove("&7Remove a item from world"),
	Help__Cmd_Reload("&7Reload WorldGifts");
	
	private static FConfig _conf;
	
	private final String _path;
	private final String _msg;
	
	private PhraseConfig (String msg)
	{
		this._path = name().replaceAll("__", ".");
		this._msg = msg;
	}
	
	public static void useConfig (FConfig _conf)
	{
		PhraseConfig._conf = _conf;
	}
	
	public static void reloadConfig ()
	{
		_conf.reloadConfig();
	}
	
	public String val ()
	{
		return _conf.getConfig().getString(_path, _msg);
	}
}
