# WorldGifts
> Let your bukkit auto give player a gift on join.

This plugin can let your server auto to give gifts to player when they are join.

## Features
* Easy to install and use
* Multi world support
* Set the gift limitation per world
* Set gift give permission per world
* You can custome the plugin massage (Edit phrase.yml)
* Support writing books
* Work with some lore edit plugins (e.g. Lore, CrackShot)

## Commands
```
    /worldgifts - About WorldGifts plugin
    /worldgifts help - WorldGifts Help
    /worldgifts list <world> - List all gift on this world
    /worldgifts setmaxtimes <world> <max get times> - Set maximum get gift times (-1 is infinitely)
    /worldgifts resetplayer <world> <player name> - Reset a player gift status
    /worldgifts put <world> - Put your hand item to the world
    /worldgifts remove <world> <item_index> - Remove a item from world
    /worldgifts reload - Reload this plugin
``` 
## Permission
```
    worldgifts.* - Gives access to all WorldGifts commands
    worldgifts.plugininfo - Allows you to see this plugin info
    worldgifts.help - Allows you to see this plugin help
    worldgifts.list - Allows you to see world items
    worldgifts.setmaxtimes - Allows you to set maximum get gift times
    worldgifts.resetplayer - Allows you to reset a player gift status
    worldgifts.put - Allows you to add item to world
    worldgifts.remove - Allows you to remove item from world
    worldgifts.reload - Allows you to reload this plugin
```
### World Premission
```
    worldgifts.world.<world name>
```
## Examples
You have 2 world in the server -- mainworld and activity.

When player first join the server in mainworld, they will have 3 diamonds.

Also, when a player get in to activity, the will receive 1 iron sword and renamed to &aNewbie Sword.

1. Put 3 diamonds on your hand
2. Type /worldgifts put mainworld
3. Put 1 iron sword on your hand
4. Rename the sword (If you are use Lore Plugin, type /lore name &aNewbie Sword)
5. Type /worldgifts put activity

## Developmant
Project Developer : OnikurChan (TsnYikHei)

We are use Eclipse [https://www.eclipse.org](https://www.eclipse.org "Title") and

m2e Maven [https://www.eclipse.org/m2e/](https://www.eclipse.org/m2e/ "Title") for development.

## Download
* Bukkit Dev (English) [http://dev.bukkit.org/bukkit-plugins/worldgifts/](http://dev.bukkit.org/bukkit-plugins/worldgifts/ "Title")
* TANPN Resources (Chinese) [https://rsrc.tanpn.com/resources/worldgifts.4/](https://rsrc.tanpn.com/resources/worldgifts.4/ "Title")

- - -
WorldGifts is open source

Available under the GNU General Public License v2.
