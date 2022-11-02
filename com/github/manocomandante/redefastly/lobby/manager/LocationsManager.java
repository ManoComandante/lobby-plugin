package com.github.manocomandante.redefastly.lobby.manager;

import org.bukkit.*;

import com.github.manocomandante.redefastly.lobby.*;
import com.github.manocomandante.redefastly.lobby.utils.*;

import java.util.*;

public class LocationsManager
{
    private final LobbyPlugin plugin;
    private final HashMap<String, Location> locations;
    
    public LocationsManager(final LobbyPlugin plugin) {
        this.locations = new HashMap<String, Location>();
        this.plugin = plugin;
    }
    
    public Location getLocation(final String name) {
        return this.locations.get(name);
    }
    
    public void save() {
        for (final Map.Entry<String, Location> entry : this.locations.entrySet()) {
            this.plugin.getConfig().set("Locations." + entry.getKey(), (Object)LocationSerializer.serialize(entry.getValue()));
            this.plugin.saveConfig();
        }
    }
    
    public void load() {
        for (final String key : this.plugin.getConfig().getConfigurationSection("Locations").getKeys(false)) {
            this.locations.put(key, LocationSerializer.deserialize(this.plugin.getConfig().getString("Locations." + key)));
        }
    }
    
    public HashMap<String, Location> getLocations() {
        return this.locations;
    }
}
