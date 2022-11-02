package com.github.manocomandante.redefastly.lobby.commands;

import me.saiintbrisson.minecraft.command.*;
import me.saiintbrisson.minecraft.command.annotations.*;

import org.bukkit.*;
import org.bukkit.entity.*;

import com.github.manocomandante.redefastly.lobby.*;
import com.github.manocomandante.redefastly.lobby.utils.*;

public class TablistCommand
{
    @Command(name = "tablist", permission = "fastly.administrator")
    public void handleTablistCommand(final Execution execution) {
        execution.sendMessage("§cUtilize /tablist update.");
    }
    
    @Command(name = "tablist.update", permission = "fastly.administrator", usage = "tablist update")
    public void handleUpdateCommand(final Execution execution) {
        execution.sendMessage("§aRecarregando configuração...");
        LobbyPlugin.getInstance().reloadConfig();
        execution.sendMessage("§aAplicando tablist a todos jogadores...");
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            TablistUtil.setup(onlinePlayer);
        }
        execution.sendMessage("§aTablist atualizado com sucesso!");
    }
}
