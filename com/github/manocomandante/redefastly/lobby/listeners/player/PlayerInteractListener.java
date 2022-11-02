package com.github.manocomandante.redefastly.lobby.listeners.player;

import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

import com.github.manocomandante.redefastly.lobby.manager.*;
import com.github.manocomandante.redefastly.lobby.utils.*;
import com.github.manocomandante.redefastly.lobby.view.*;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerInteractListener implements Listener
{
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        if (item.getType() == Material.AIR) {
            return;
        }
        if (item.getType() == Material.INK_SACK) {
            if (item.getData().getData() == 10) {
                player.getInventory().setItem(2, new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short)8)).name("§fJogadores: §cOFF").create());
                player.sendMessage("§aAgora os jogadores estáo invisíveis.");
                HideManager.getInstance().hide(player);
            }
            else if (item.getData().getData() == 8) {
                player.getInventory().setItem(2, new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short)10)).name("§fJogadores: §aON").create());
                player.sendMessage("§aAgora os jogadores estão vísiveis novamente.");
                HideManager.getInstance().show(player);
            }
        }
        else if (item.getType() == Material.COMPASS) {
            event.setCancelled(true);
            ServersView.showInventory(player);
        }
        else if (item.getType() == Material.NETHER_STAR) {
            LobbiesView.showInventory(player);
        }
    }
}
