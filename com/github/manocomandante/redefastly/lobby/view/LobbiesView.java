package com.github.manocomandante.redefastly.lobby.view;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

import com.github.manocomandante.redefastly.lobby.*;
import com.github.manocomandante.redefastly.lobby.controller.*;
import com.github.manocomandante.redefastly.lobby.utils.*;

public class LobbiesView
{
    private static final BungeeController bungeeController;
    private static final String lobbyNumber;
    
    public static void showInventory(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 27, "Lobbies");
        final ItemBuilder lobby1 = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short)10)).name("§eLobby #1").lore("§fJogadores: §7" + LobbiesView.bungeeController.getCount("lobby1") + "/200", "", LobbiesView.lobbyNumber.equals("#1") ? "§cVoc\u00ea est\u00e1 aqui." : "§eClique para conectar!");
        final ItemBuilder lobby2 = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short)10)).name("§eLobby #2").lore("§fJogadores: §7" + LobbiesView.bungeeController.getCount("lobby2") + "/200", "", LobbiesView.lobbyNumber.equals("#2") ? "§cVoc\u00ea est\u00e1 aqui." : "§eClique para conectar!");
        if (LobbiesView.lobbyNumber.equals("#1")) {
            lobby1.glow();
        }
        if (LobbiesView.lobbyNumber.equals("#2")) {
            lobby2.glow();
        }
        inventory.setItem(11, lobby1.create());
        inventory.setItem(12, lobby2.create());
        player.openInventory(inventory);
    }
    
    static {
        bungeeController = BungeeController.getInstance();
        lobbyNumber = LobbyPlugin.getInstance().getLobbyNumber();
    }
}
