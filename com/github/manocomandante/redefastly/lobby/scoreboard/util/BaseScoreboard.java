package com.github.manocomandante.redefastly.lobby.scoreboard.util;

import org.bukkit.entity.*;
import org.bukkit.scoreboard.*;
import org.bukkit.*;

public class BaseScoreboard
{
    protected Player owner;
    protected Scoreboard scoreboard;
    protected Objective objective;
    
    public BaseScoreboard(final Player owner) {
        this.owner = owner;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.owner.setScoreboard(this.scoreboard);
        (this.objective = this.scoreboard.registerNewObjective("lobby-board", "dummy")).setDisplaySlot(DisplaySlot.SIDEBAR);
    }
    
    public void setDisplayName(final String displayName) {
        if (this.objective.getDisplayName().equals(displayName)) {
            return;
        }
        this.objective.setDisplayName(displayName);
    }
    
    public void setLine(final int i, final String prefix, final String suffix, final boolean creating) {
        final Team team = this.getTeam(i);
        final String entry = this.getEntry(i);
        if (team.getPrefix() == null || !team.getPrefix().equals(prefix)) {
            team.setPrefix(prefix);
        }
        if (team.getSuffix() == null || !team.getSuffix().equals(suffix)) {
            team.setSuffix(suffix);
        }
        if (creating) {
            final Score score = this.objective.getScore(entry);
            if (score.getScore() != i) {
                score.setScore(i);
            }
        }
    }
    
    public void setLines(final String[] lines) {
        int score = lines.length;
        for (final String s : lines) {
            String prefix = "";
            String suffix = "";
            if (s.length() <= 16) {
                prefix = s;
            }
            else {
                prefix = s.substring(0, 16);
                suffix = s.substring(16);
                if (!suffix.startsWith("§") || ChatColor.getByChar(suffix.charAt(1)).isFormat()) {
                    suffix = String.valueOf(ChatColor.getLastColors(prefix)) + suffix;
                }
            }
            this.setLine(score--, prefix, suffix, true);
        }
    }
    
    public void updateLines(final String[] lines) {
        int score = lines.length;
        for (final String s : lines) {
            if (s == null) {
                --score;
            }
            else {
                String prefix = "";
                String suffix = "";
                if (s.length() <= 16) {
                    prefix = s;
                }
                else {
                    prefix = s.substring(0, 16);
                    suffix = s.substring(16);
                    if (!suffix.startsWith("§") || ChatColor.getByChar(suffix.charAt(1)).isFormat()) {
                        suffix = String.valueOf(ChatColor.getLastColors(prefix)) + suffix;
                    }
                }
                this.setLine(score--, prefix, suffix, false);
            }
        }
    }
    
    public void clearLine(final int i) {
        this.scoreboard.resetScores(this.getEntry(i));
    }
    
    public void clearLines() {
        for (final String entry : this.scoreboard.getEntries()) {
            this.scoreboard.resetScores(entry);
        }
    }
    
    private String getEntry(final int line) {
        return ChatColor.values()[line] + "§r";
    }
    
    public Team getTeam(final int line) {
        Team t = this.scoreboard.getTeam("team" + line);
        if (t == null) {
            t = this.scoreboard.registerNewTeam("team" + line);
            t.addEntry(this.getEntry(line));
        }
        return t;
    }
}
