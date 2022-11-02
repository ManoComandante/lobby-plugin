package com.github.manocomandante.redefastly.lobby.commands;

import me.saiintbrisson.minecraft.command.*;
import org.bukkit.entity.*;
import me.saiintbrisson.minecraft.command.argument.*;
import org.bukkit.*;
import me.saiintbrisson.minecraft.command.annotations.*;

public class GamemodeCommand
{
    @Command(name = "gamemode", usage = "gamemode <modo> <jogador>", aliases = { "gm" }, permission = "fastly.command.gamemode")
    public void handleGamemodeCommand(final Execution execution, final Integer mode, @Argument(nullable = true) final Player player) {
        final GameMode gameMode = GameMode.getByValue((int)mode);
        if (gameMode == null) {
            execution.sendMessage("§cO modo de jogo inserido é inválido.");
            return;
        }
        if (player == null) {
            execution.getPlayer().setGameMode(gameMode);
            execution.sendMessage("§aModo de jogo atualizado para §f" + gameMode.name() + "§a.");
        }
        else {
            player.setGameMode(gameMode);
            execution.sendMessage("§aModo de jogo de §f" + player.getName() + "§a atualizado para §f" + gameMode.name() + "§a.");
        }
    }
}
