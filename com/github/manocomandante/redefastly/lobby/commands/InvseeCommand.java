package com.github.manocomandante.redefastly.lobby.commands;

import me.saiintbrisson.minecraft.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import me.saiintbrisson.minecraft.command.annotations.*;

public class InvseeCommand
{
    @Command(name = "invsee", aliases = { "inventario" }, permission = "fastly.command.invsee", usage = "invsee <jogador>", target = CommandTarget.PLAYER)
    public void handleInvseeCommand(final Execution execution, final Player player) {
        execution.getPlayer().openInventory((Inventory)player.getInventory());
    }
}
