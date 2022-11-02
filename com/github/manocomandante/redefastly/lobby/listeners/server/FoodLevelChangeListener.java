package com.github.manocomandante.redefastly.lobby.listeners.server;

import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class FoodLevelChangeListener implements Listener
{
    @EventHandler
    public void onFoodLevelChangeEvent(final FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
