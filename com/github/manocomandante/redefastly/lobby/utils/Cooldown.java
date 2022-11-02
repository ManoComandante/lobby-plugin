package com.github.manocomandante.redefastly.lobby.utils;

import java.util.*;
import org.bukkit.entity.*;
import java.util.concurrent.*;

public class Cooldown
{
    private static Cooldown instance;
    private final Map<String, Long> rawMap;
    
    public Cooldown() {
        this.rawMap = new WeakHashMap<String, Long>();
    }
    
    public static Cooldown getInstance() {
        if (Cooldown.instance == null) {
            Cooldown.instance = new Cooldown();
        }
        return Cooldown.instance;
    }
    
    public void createCooldown(final Player player, final String name, final long time, final TimeUnit unit) {
        this.createCooldown(player.getUniqueId() + ";" + name, time, unit);
    }
    
    public void createCooldown(final String name, final long time, final TimeUnit unit) {
        this.rawMap.put(name, System.currentTimeMillis() + unit.toMillis(time));
    }
    
    public boolean isInCooldown(final Player player, final String name) {
        return this.isInCooldown(player.getUniqueId() + ";" + name);
    }
    
    public boolean isInCooldown(final String name) {
        return System.currentTimeMillis() < this.rawMap.getOrDefault(name, 0L);
    }
    
    public long getCooldownTime(final Player player, final String name) {
        return this.rawMap.get(player.getUniqueId() + ";" + name) - System.currentTimeMillis();
    }
    
    public long getCooldownTime(final Player player, final String name, final TimeUnit unit) {
        return unit.convert(this.getCooldownTime(player, name), TimeUnit.MILLISECONDS);
    }
}
