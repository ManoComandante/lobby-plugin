package com.github.manocomandante.redefastly.lobby.manager;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

import com.github.manocomandante.redefastly.lobby.utils.*;

public class ItemsManager
{
    public static void giveItems(final Player player) {
        if (HideManager.getInstance().getPlayers().contains(player)) {
            player.getInventory().setItem(2, new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short)8)).name("§fJogadores: §cOFF").create());
        }
        else {
            player.getInventory().setItem(2, new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short)10)).name("§fJogadores: §aON").create());
        }
        player.getInventory().setItem(4, new ItemBuilder(Material.COMPASS).name("§aModos de jogo").create());
        player.getInventory().setItem(6, new ItemBuilder(Material.NETHER_STAR).name("§aLobbies").create());
    }
}
