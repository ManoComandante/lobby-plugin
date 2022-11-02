package com.github.manocomandante.redefastly.lobby.listeners.player;

import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class PlayerDeathListener implements Listener
{
    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        event.setDeathMessage((String)null);
        event.getDrops().clear();
    }
}
