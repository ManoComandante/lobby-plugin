package com.github.manocomandante.redefastly.lobby.listeners.player;

import org.bukkit.event.player.*;

import com.github.manocomandante.redefastly.lobby.*;

import org.bukkit.event.*;

public class PlayerRespawnListener implements Listener
{
    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        event.setRespawnLocation(LobbyPlugin.getInstance().getLocationsManager().getLocation("spawn"));
    }
}
