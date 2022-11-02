package com.github.manocomandante.redefastly.lobby.view;

import org.bukkit.entity.*;
import org.bukkit.*;

import java.util.stream.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import com.github.manocomandante.redefastly.lobby.controller.*;
import com.github.manocomandante.redefastly.lobby.manager.*;
import com.github.manocomandante.redefastly.lobby.model.*;

public class ServersView
{
    private static final ServerManager serverManager;
    
    public static void showInventory(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 27, "Servidores");
        for (final ServerModel server : ServersView.serverManager.getServers()) {
            if (!server.isLobby()) {
                final ItemStack itemStack = server.getIcon().clone();
                final ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setLore((List<String>)itemMeta.getLore().stream().map(lore -> lore.replace("{onlines}", String.valueOf(BungeeController.getInstance().getCount(server.getBungeeServerName())))).collect(Collectors.toList()));
                itemStack.setItemMeta(itemMeta);
                inventory.setItem(server.getInventorySlot(), itemStack);
            }
        }
        player.openInventory(inventory);
    }
    
    static {
        serverManager = ServerManager.getInstance();
    }
}
