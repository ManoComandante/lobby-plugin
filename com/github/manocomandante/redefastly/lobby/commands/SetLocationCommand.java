package com.github.manocomandante.redefastly.lobby.commands;

import me.saiintbrisson.minecraft.command.*;
import org.bukkit.*;

import com.github.manocomandante.redefastly.lobby.*;

import me.saiintbrisson.minecraft.command.annotations.*;

public class SetLocationCommand
{
    private final LobbyPlugin plugin;
    
    public SetLocationCommand() {
        this.plugin = LobbyPlugin.getInstance();
    }
    
    @Command(name = "setlocation", permission = "fastly.administrator", usage = "setlocation <nome>", target = CommandTarget.PLAYER)
    public void handleSetWarpCommand(final Execution execution, String name) {
        final Location location = this.plugin.getLocationsManager().getLocation(name);
        if (location != null) {
            this.plugin.getLocationsManager().getLocations().remove(name);
        }
        this.plugin.getLocationsManager().getLocations().put(name, execution.getPlayer().getLocation());
        this.plugin.getLocationsManager().save();
        execution.sendMessage("§aLocalização '" + name + "' definida com sucesso.");
    }
}
