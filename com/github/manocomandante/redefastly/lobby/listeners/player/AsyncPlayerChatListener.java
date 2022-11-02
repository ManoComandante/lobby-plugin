package com.github.manocomandante.redefastly.lobby.listeners.player;

import org.bukkit.event.player.*;

import com.github.manocomandante.redefastly.lobby.utils.*;

import java.util.concurrent.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class AsyncPlayerChatListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAsyncPlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        if (Cooldown.getInstance().isInCooldown(player, "chat")) {
            event.setCancelled(true);
            player.sendMessage("§cAguarde para falar no chat novamente.");
            return;
        }
        if (player.hasPermission("fastly.administrator")) {
            event.setFormat("\n§f " + VaultModule.getPlayerGroupPrefix(player.getName()) + "§8: §e" + event.getMessage().replace("%", "") + " \n §f");
        }
        else if (player.hasPermission("fastly.vip")) {
            event.setFormat(VaultModule.getPlayerGroupPrefix(player.getName()) + "§8: §f" + event.getMessage().replace("%", ""));
        }
        else {
            event.setFormat(VaultModule.getPlayerGroupPrefix(player.getName()) + "§8: §7" + event.getMessage().replace("%", ""));
        }
        if (!player.hasPermission("fastly.staff")) {
            Cooldown.getInstance().createCooldown(player, "chat", 5L, TimeUnit.SECONDS);
        }
    }
}
