package com.github.manocomandante.redefastly.lobby.scoreboard.updater;

import org.bukkit.scheduler.*;

import com.github.manocomandante.redefastly.lobby.scoreboard.*;

import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class ScoreboardUpdater extends BukkitRunnable
{
    private final Map<Player, LobbyScoreboard> scoreboardMap;
    
    public ScoreboardUpdater() {
        this.scoreboardMap = LobbyScoreboard.scoreboardMap;
    }
    
    public void run() {
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            final LobbyScoreboard scoreboard = this.scoreboardMap.get(onlinePlayer);
            if (scoreboard == null) {
                continue;
            }
            scoreboard.update(onlinePlayer);
        }
    }
}
