package com.github.manocomandante.redefastly.lobby.manager;

import org.bukkit.*;

public class WorldManager
{
    private static WorldManager instance;
    private final World world;
    
    public WorldManager() {
        this.world = Bukkit.getWorld("hub");
    }
    
    public static WorldManager getInstance() {
        if (WorldManager.instance == null) {
            WorldManager.instance = new WorldManager();
        }
        return WorldManager.instance;
    }
    
    public void loadWorld() {
        this.world.setTime(0L);
        this.world.setAutoSave(false);
        this.world.setGameRuleValue("doDaylightCycle", "false");
        this.world.setGameRuleValue("doMobSpawning", "false");
        this.world.setGameRuleValue("doMobLoot", "false");
        this.world.setGameRuleValue("doFireTick", "false");
    }
    
    public World getWorld() {
        return this.world;
    }
}
