package com.github.manocomandante.redefastly.lobby.listeners.player;

import org.bukkit.event.player.*;
import org.bukkit.event.*;

public class PlayerDropItemListener implements Listener
{
    @EventHandler
    public void onPlayerDropItemEvent(final PlayerDropItemEvent event) {
        if (!event.getPlayer().hasPermission("fastly.administrator")) {
            event.setCancelled(true);
        }
    }
}
