package com.github.manocomandante.redefastly.lobby.listeners.player;

import org.bukkit.event.player.*;

import com.github.manocomandante.redefastly.lobby.manager.*;

import org.bukkit.event.*;

public class PlayerQuitListener implements Listener
{
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        event.setQuitMessage((String)null);
        HideManager.getInstance().getPlayers().remove(event.getPlayer());
        VanishManager.getInstance().getPlayers().remove(event.getPlayer().getUniqueId());
    }
}
