package com.github.manocomandante.redefastly.lobby.scoreboard;

import org.bukkit.entity.*;

import com.github.manocomandante.redefastly.lobby.*;
import com.github.manocomandante.redefastly.lobby.scoreboard.util.*;

import me.clip.placeholderapi.*;
import java.util.*;

public class LobbyScoreboard extends BaseScoreboard
{
    private final LobbyPlugin plugin;
    public static Map<Player, LobbyScoreboard> scoreboardMap;
    
    public LobbyScoreboard(final Player player) {
        super(player);
        this.plugin = LobbyPlugin.getInstance();
    }
    
    public void create(final Player player) {
        this.clearLines();
        this.setDisplayName(this.plugin.getScoreboardPattern().getDisplayName());
        final List<String> lines = new ArrayList<String>();
        for (final String line : this.plugin.getScoreboardPattern().getLines()) {
            lines.add(PlaceholderAPI.setPlaceholders(player, line));
        }
        this.setLines(lines.toArray(new String[0]));
        LobbyScoreboard.scoreboardMap.put(player, this);
    }
    
    public void update(final Player player) {
        final List<String> lines = new ArrayList<String>();
        for (final String line : this.plugin.getScoreboardPattern().getLines()) {
            lines.add(PlaceholderAPI.setPlaceholders(player, line));
        }
        this.updateLines(lines.toArray(new String[0]));
    }
    
    static {
        LobbyScoreboard.scoreboardMap = new HashMap<Player, LobbyScoreboard>();
    }
}
