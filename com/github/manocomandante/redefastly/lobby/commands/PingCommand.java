package com.github.manocomandante.redefastly.lobby.commands;

import me.saiintbrisson.minecraft.command.*;
import org.bukkit.entity.*;

import com.github.manocomandante.redefastly.lobby.utils.*;

import me.saiintbrisson.minecraft.command.argument.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;

import me.saiintbrisson.minecraft.command.annotations.*;

public class PingCommand
{
    @Command(name = "ping", aliases = { "latency", "latencia", "ms" }, usage = "ping <jogador>", target = CommandTarget.PLAYER)
    public void handlePingCommand(final Execution execution, @Argument(nullable = true) final Player player) {
        if (player == null) {
            execution.sendMessage("§e➟ Seu ping:" + ((CraftPlayer)execution.getPlayer()).getHandle().ping);
        }
        else {
            execution.sendMessage("§e➟ O ping de " + VaultModule.getPlayerGroupPrefix(player.getName()) + "§f: §6" + ((CraftPlayer)player).getHandle().ping);
        }
    }
}
