package com.github.manocomandante.redefastly.lobby.view.updater;

import org.bukkit.scheduler.*;

import com.github.manocomandante.redefastly.lobby.controller.*;
import com.github.manocomandante.redefastly.lobby.manager.*;
import com.github.manocomandante.redefastly.lobby.model.*;

import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.stream.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class ServersViewUpdater extends BukkitRunnable
{
    private static final ServerManager serverManager;
    private static final BungeeController bungeeController;
    
    public void run() {
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            final InventoryView inventory = onlinePlayer.getOpenInventory();
            if (inventory == null) {
                continue;
            }
            if (!inventory.getTitle().equals("Servidores")) {
                continue;
            }
            for (final ServerModel server : ServersViewUpdater.serverManager.getServers()) {
                if (server.isLobby()) {
                    continue;
                }
                final ItemStack itemStack = server.getIcon().clone();
                final ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setLore((List<String>)itemMeta.getLore().stream().map(lore -> lore.replace("{onlines}", String.valueOf(ServersViewUpdater.bungeeController.getCount(server.getBungeeServerName())))).collect(Collectors.toList()));
                itemStack.setItemMeta(itemMeta);
                inventory.setItem(server.getInventorySlot(), itemStack);
            }
        }
    }
    
    static {
        serverManager = ServerManager.getInstance();
        bungeeController = BungeeController.getInstance();
    }
}
