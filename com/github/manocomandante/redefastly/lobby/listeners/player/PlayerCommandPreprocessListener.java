package com.github.manocomandante.redefastly.lobby.listeners.player;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerCommandPreprocessListener implements Listener
{
    @EventHandler
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage().toLowerCase();
        if (message.startsWith("/me") || message.startsWith("/minecraft:me")) {
            event.setCancelled(true);
            player.sendMessage("§cVocê não tem acesso á este comando.");
        }
    }
}
