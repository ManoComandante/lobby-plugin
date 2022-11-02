package com.github.manocomandante.redefastly.lobby.commands;

import me.saiintbrisson.minecraft.command.*;
import org.bukkit.entity.*;
import me.saiintbrisson.minecraft.command.argument.*;
import me.saiintbrisson.minecraft.command.annotations.*;

public class SpeedCommand
{
    @Command(name = "speed", aliases = { "velocidade" }, target = CommandTarget.PLAYER, permission = "fastly.command.speed", usage = "speed <level>")
    public void handleSpeedCommand(final Execution execution, final int level, @Argument(nullable = true) final Player player) {
        if (level > 10) {
            execution.sendMessage("§cO nível inserido é inválido.");
            return;
        }
        if (player == null) {
            execution.getPlayer().setWalkSpeed(this.getSpeedLevel(level));
            execution.getPlayer().setFlySpeed(this.getSpeedLevel(level));
            execution.sendMessage("§aSua velocidade foi alterada para o nível §f" + level + "§a.");
        }
        else {
            player.setWalkSpeed(this.getSpeedLevel(level));
            player.setFlySpeed(this.getSpeedLevel(level));
            execution.sendMessage("§aA velocidade de " + player.getName() + " foi alterada para o nível §f" + level + "§a.");
        }
    }
    
    private float getSpeedLevel(final int level) {
        if (level == 10) {
            return 1.0f;
        }
        return Float.parseFloat("0." + level);
    }
}
