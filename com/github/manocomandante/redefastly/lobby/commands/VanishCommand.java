package com.github.manocomandante.redefastly.lobby.commands;

import org.bukkit.event.*;

import com.github.manocomandante.redefastly.lobby.manager.*;

import me.saiintbrisson.minecraft.command.*;
import me.saiintbrisson.minecraft.command.annotations.*;

public class VanishCommand implements Listener
{
    @Command(name = "vanish", aliases = { "v" }, permission = "fastly.command.vanish", target = CommandTarget.PLAYER)
    public void handleVanishCommand(final Execution execution) {
        VanishManager.getInstance().execute(execution.getPlayer());
    }
}
