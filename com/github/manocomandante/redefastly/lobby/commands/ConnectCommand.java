package com.github.manocomandante.redefastly.lobby.commands;

import me.saiintbrisson.minecraft.command.*;
import org.bukkit.entity.*;

import com.github.manocomandante.redefastly.lobby.controller.*;

import me.saiintbrisson.minecraft.command.annotations.*;

public class ConnectCommand
{
    private static final BungeeController CONTROLLER;
    
    @Command(name = "connect")
    public void handle(final Execution execution, final String serverName) {
        final Player player = execution.getPlayer();
        if (!player.hasPermission("server.connect." + serverName)) {
            execution.sendMessage("§cVocê não tem permissão para conectar á este servidor.");
            return;
        }
        ConnectCommand.CONTROLLER.connect(player, serverName);
    }
    
    static {
        CONTROLLER = BungeeController.getInstance();
    }
}
