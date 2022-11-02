package com.github.manocomandante.redefastly.lobby.manager;

import org.bukkit.configuration.file.*;
import org.bukkit.inventory.*;

import com.github.manocomandante.redefastly.lobby.model.*;
import com.github.manocomandante.redefastly.lobby.utils.*;

import org.bukkit.*;
import java.util.stream.*;
import java.util.*;

public class ServerManager
{
    private static ServerManager instance;
    private final Set<ServerModel> servers;
    
    public ServerManager() {
        this.servers = new HashSet<ServerModel>();
    }
    
    public static ServerManager getInstance() {
        if (ServerManager.instance == null) {
            ServerManager.instance = new ServerManager();
        }
        return ServerManager.instance;
    }
    
    public ServerModel getServerByName(final String name) {
        return this.servers.stream().filter(server -> server.getBungeeServerName().equals(name)).findFirst().orElse(null);
    }
    
    public void load(final FileConfiguration configuration) {
        for (final String key : configuration.getConfigurationSection("servers").getKeys(false)) {
            final String prefix = "servers." + key + ".";
            final String commandOnClick = configuration.getString(prefix + "commandOnClick");
            final int inventorySlot = configuration.getInt(prefix + "inventorySlot");
            final boolean lobby = configuration.getBoolean(prefix + "isLobby");
            final boolean isHead = configuration.getBoolean(prefix + "icon.head");
            ItemBuilder itemBuilder;
            if (isHead) {
                itemBuilder = new ItemBuilder(SkullCreator.itemFromUrl(configuration.getString(prefix + "icon.head-texture")));
            }
            else {
                itemBuilder = new ItemBuilder(new ItemStack(Material.getMaterial(configuration.getInt(prefix + "icon.id")), 1, Short.parseShort(configuration.getString(prefix + "icon.data"))));
            }
            itemBuilder.name(ChatColor.translateAlternateColorCodes('&', configuration.getString(prefix + "icon.displayName")));
            itemBuilder.lore((List<String>)configuration.getStringList(prefix + "icon.lore").stream().map(lore -> lore.replace("&", "§")).collect(Collectors.toList()));
            final ServerModel serverModel = new ServerModel(key, commandOnClick, inventorySlot, lobby);
            serverModel.setIcon(itemBuilder.create());
            this.servers.add(serverModel);
        }
    }
    
    public Set<ServerModel> getServers() {
        return this.servers;
    }
}
