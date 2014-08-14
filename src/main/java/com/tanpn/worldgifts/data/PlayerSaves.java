package com.tanpn.worldgifts.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tanpn.worldgifts.WorldGifts;

public class PlayerSaves
{
	private final String BASE_FOLDER = "data";
	private final String FILE_EXTENSION = ".worldgifts";
	
	private final WorldGifts plugin;
	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	
	public PlayerSaves (WorldGifts plugin)
	{
		this.plugin = plugin;
	}
	
	public void addPlayer (String worldName, String playerName)
	{
		if (!data.containsKey(worldName))
		{
			data.put(worldName, new ArrayList<String>());
		}
		List<String> players = data.get(worldName);
		players.add(playerName);
		data.put(worldName, players);
	}
	
	public boolean isPlayerAlreadyGetGift (String worldName, String playerName)
	{
		if (!data.containsKey(worldName)) return false;
		return data.get(worldName).contains(playerName);
	}
	
	public void saveAllMapData ()
	{
		File dataFolder = new File(plugin.getDataFolder(), BASE_FOLDER);
		if (!dataFolder.exists()) dataFolder.mkdir();
		
		for (Entry<String, List<String>> entry : data.entrySet())
		{
			saveMapData(entry.getKey(), entry.getValue());
		}
	}
	
	public void saveMapData(String fileName, List<String> mapData)
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
	public List<String> loadMapData(String fileName)
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(plugin.getDataFolder() + File.separator + BASE_FOLDER + File.separator + fileName));
			Object result = ois.readObject();
			ois.close();
			return (List<String>) result;
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
