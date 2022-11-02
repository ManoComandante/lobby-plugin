package com.github.manocomandante.redefastly.lobby.manager;

import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class HideManager
{
    private static HideManager instance;
    private final List<Player> players;
    
    public HideManager() {
        this.players = new ArrayList<Player>();
    }
    
    public static HideManager getInstance() {
        if (HideManager.instance == null) {
            HideManager.instance = new HideManager();
        }
        return HideManager.instance;
    }
    
    public void hide(final Player player) {
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(onlinePlayer);
        }
        this.players.add(player);
    }
    
    public void show(final Player player) {
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            player.showPlayer(onlinePlayer);
        }
        this.players.remove(player);
    }
    
    public List<Player> getPlayers() {
        return this.players;
    }
}
