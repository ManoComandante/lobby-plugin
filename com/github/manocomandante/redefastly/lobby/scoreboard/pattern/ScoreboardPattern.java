package com.github.manocomandante.redefastly.lobby.scoreboard.pattern;

import org.bukkit.*;
import org.bukkit.configuration.file.*;

import com.github.manocomandante.redefastly.lobby.*;

import java.util.*;

public class ScoreboardPattern
{
    private final LobbyPlugin plugin;
    private final String displayName;
    private final List<String> lines;
    
    public ScoreboardPattern(final LobbyPlugin plugin) {
        this.lines = new ArrayList<String>();
        this.plugin = plugin;
        final FileConfiguration configuration = plugin.getConfig();
        this.displayName = ChatColor.translateAlternateColorCodes('&', configuration.getString("scoreboard.display-name"));
        for (final String line : configuration.getStringList("scoreboard.lines")) {
            this.lines.add(ChatColor.translateAlternateColorCodes('&', line));
        }
    }
    
    public LobbyPlugin getPlugin() {
        return this.plugin;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public List<String> getLines() {
        return this.lines;
    }
}
