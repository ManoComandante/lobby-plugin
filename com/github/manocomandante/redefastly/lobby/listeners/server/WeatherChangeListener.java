package com.github.manocomandante.redefastly.lobby.listeners.server;

import org.bukkit.event.weather.*;
import org.bukkit.event.*;

public class WeatherChangeListener implements Listener
{
    @EventHandler
    public void onWeatherChangeEvent(final WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
