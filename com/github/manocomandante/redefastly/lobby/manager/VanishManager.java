package com.github.manocomandante.redefastly.lobby.manager;

import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.scheduler.*;

import com.github.manocomandante.redefastly.lobby.*;
import com.github.manocomandante.redefastly.lobby.utils.*;

import org.bukkit.plugin.*;

public class VanishManager
{
    private static VanishManager instance;
    private final List<UUID> players;
    
    public VanishManager() {
        this.players = new ArrayList<UUID>();
    }
    
    public static VanishManager getInstance() {
        if (VanishManager.instance == null) {
            VanishManager.instance = new VanishManager();
        }
        return VanishManager.instance;
    }
    
    public void execute(final Player player) {
        if (this.players.contains(player.getUniqueId())) {
            this.players.remove(player.getUniqueId());
            for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (onlinePlayer == player) {
                    continue;
                }
                onlinePlayer.showPlayer(player);
            }
            player.sendMessage("§aVanish desabilitado com sucesso!");
        }
        else {
            this.players.add(player.getUniqueId());
            for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (onlinePlayer == player) {
                    continue;
                }
                onlinePlayer.hidePlayer(player);
            }
            player.sendMessage("§aVanish habilitado com sucesso!");
        }
    }
    
    public void startRunnable() {
        new BukkitRunnable() {
            public void run() {
                for (final UUID id : VanishManager.this.players) {
                    final Player playerById = Bukkit.getPlayer(id);
                    if (playerById == null) {
                        continue;
                    }
                    ActionBar.send(playerById, "§aVocê está invisível!");
                }
            }
        }.runTaskTimer((Plugin)LobbyPlugin.getInstance(), 20L, 40L);
    }
    
    public List<UUID> getPlayers() {
        return this.players;
    }
}
