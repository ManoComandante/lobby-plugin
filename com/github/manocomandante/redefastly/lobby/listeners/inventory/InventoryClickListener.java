package com.github.manocomandante.redefastly.lobby.listeners.inventory;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

import com.github.manocomandante.redefastly.lobby.controller.*;
import com.github.manocomandante.redefastly.lobby.manager.*;
import com.github.manocomandante.redefastly.lobby.model.*;

import java.util.*;
import org.bukkit.event.*;

public class InventoryClickListener implements Listener
{
    private static final ServerManager serverManager;
    
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            final Player whoClicked = (Player)event.getWhoClicked();
            if (event.getClickedInventory() == null) {
                return;
            }
            if (event.getClickedInventory().getTitle().equals("Servidores")) {
                event.setCancelled(true);
                final int slot = event.getRawSlot();
                for (final ServerModel server : InventoryClickListener.serverManager.getServers()) {
                    if (server.getInventorySlot() == slot) {
                        whoClicked.performCommand(server.getCommandOnClick());
                    }
                }
            }
            else if (event.getClickedInventory().getTitle().equals("Lobbies")) {
                event.setCancelled(true);
                final ItemStack currentItem = event.getCurrentItem();
                if (currentItem == null || currentItem.getType() == Material.AIR) {
                    return;
                }
                if (!currentItem.hasItemMeta() || !currentItem.getItemMeta().hasLore()) {
                    return;
                }
                final List<String> lore = (List<String>)currentItem.getItemMeta().getLore();
                if (lore.get(2) != null && lore.get(2).equals("§cVocê está aqui.")) {
                    whoClicked.sendMessage("§cVocê já está conectado á este lobby.");
                    return;
                }
                final int slot2 = event.getRawSlot();
                if (slot2 == 11) {
                    BungeeController.getInstance().connect(whoClicked, "lobby1");
                }
                else if (slot2 == 12) {
                    BungeeController.getInstance().connect(whoClicked, "lobby2");
                }
                else if (slot2 == 13) {
                    BungeeController.getInstance().connect(whoClicked, "lobby3");
                }
            }
        }
    }
    
    static {
        serverManager = ServerManager.getInstance();
    }
}
