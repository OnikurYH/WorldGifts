package com.tanpn.worldgifts.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tanpn.worldgifts.WorldGifts;
import com.tanpn.worldgifts.util.Msg;

public class PlayerSaves
{
	private final String BASE_FOLDER = "data";
	private final String FILE_EXTENSION = ".worldgifts";
	private final String PRE_WORLD_BASE_PREM = "worldgifts.world.";
	
	private final WorldGifts plugin;
	private Map<String, Map<UUID, Integer>> data = new HashMap<String, Map<UUID, Integer>>();
	
	public PlayerSaves (WorldGifts plugin)
	{
		this.plugin = plugin;
	}
	
	public void addPlayer (String worldName, UUID pUUID)
	{
		if (!data.containsKey(worldName))
		{
			data.put(worldName, new HashMap<UUID, Integer>());
		}
		Map<UUID, Integer> players = data.get(worldName);
		players.put(pUUID, (players.containsKey(pUUID) ? (players.get(pUUID) + 1) : 1));
		data.put(worldName, players);
	}
	
	public void resetPlayer (CommandSender sender, String worldName, UUID pUUID)
	{
		if (!data.containsKey(worldName))
		{
			Msg.send(sender, plugin.getPhrase().getConfig().getString("World_No_Data"));
			return;
		}
		Map<UUID, Integer> players = data.get(worldName);
		players.put(pUUID, 0);
		data.put(worldName, players);
		Msg.send(sender, plugin.getPhrase().getConfig().getString("Reset_Player"));
	}
	
	public boolean isPlayerAlreadyGetGift (String worldName, int maxGetTimes, UUID pUUID)
	{
		if (!data.containsKey(worldName)) return false;
		if (maxGetTimes == -1) return false;
		if (!data.get(worldName).containsKey(pUUID)) return false;
		if (plugin.getConfig().getBoolean("Gift_Permission_Per_World"))
		{
			Player p = plugin.getServer().getPlayer(pUUID);
			if (p.hasPermission(PRE_WORLD_BASE_PREM + worldName) ||
				(plugin.getConfig().getBoolean("Gift_Permission_Per_World_Op_Override") && p.isOp()))
			{
				return (data.get(worldName).get(pUUID) >= maxGetTimes);
			}
			else
			{
				return true;
			}
		}
		
		return (data.get(worldName).get(pUUID) >= maxGetTimes);
	}
	
	public void saveAllMapData ()
	{
		File dataFolder = new File(plugin.getDataFolder(), BASE_FOLDER);
		if (!dataFolder.exists()) dataFolder.mkdir();
		
		for (Entry<String, Map<UUID, Integer>> entry : data.entrySet())
		{
			saveMapData(entry.getKey(), entry.getValue());
		}
	}
	
	public void saveMapData(String fileName, Map<UUID, Integer> mapData)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(plugin.getDataFolder() + File.separator + BASE_FOLDER + File.separator + fileName + FILE_EXTENSION));
			oos.writeObject(mapData);
			oos.flush();
			oos.close();
			//Handle I/O exceptions
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void LoadAllMapData ()
	{
		File dataFolder = new File(plugin.getDataFolder(), BASE_FOLDER);
		if (!dataFolder.exists()) return;
		
		File[] files = dataFolder.listFiles(new FilenameFilter() {
		    @Override
		    public boolean accept(File dir, String name) {
		        return name.endsWith(FILE_EXTENSION);
		    }
		});
		
		for (File dataFile : files)
		{
			data.put(getFileNameWithoutExtension(dataFile.getName()), loadMapData(dataFile.getName()));
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<UUID, Integer> loadMapData(String fileName)
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(plugin.getDataFolder() + File.separator + BASE_FOLDER + File.separator + fileName));
			Object result = ois.readObject();
			ois.close();
			return (Map<UUID, Integer>) result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String getFileNameWithoutExtension (String fileName)
	{
		String fname = fileName;
		int pos = fname.lastIndexOf(".");
		if (pos > 0)
		{
		    fname = fname.substring(0, pos);
		}
		return fname;
	}
}
