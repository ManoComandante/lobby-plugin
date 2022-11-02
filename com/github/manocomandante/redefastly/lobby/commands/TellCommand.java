package com.github.manocomandante.redefastly.lobby.commands;

import java.util.*;
import org.bukkit.entity.*;

import com.github.manocomandante.redefastly.lobby.utils.*;

import me.saiintbrisson.minecraft.command.*;
import me.saiintbrisson.minecraft.command.annotations.*;

public class TellCommand
{
    private static HashMap<Player, Player> tellCache;
    
    @Command(name = "tell", aliases = { "mensagem", "msg" }, target = CommandTarget.PLAYER, usage = "tell <jogador> <mensagem>")
    public void handleTellCommand(final Execution execution, Player target, final String[] args) {
        if (target == null) {
            execution.sendMessage("§cEste jogador não foi encontrado.");
            return;
        }
        final StringBuilder message = new StringBuilder();
        for (final String arg : args) {
            message.append(arg).append(" ");
        }
        execution.sendMessage("§8Mensagem para " + VaultModule.getPlayerGroupPrefix(target.getName()) + "§8: §6" + (Object)message);
        target.sendMessage("§8Mensagem de " + VaultModule.getPlayerGroupPrefix(execution.getPlayer().getName()) + "§8: §6" + (Object)message);
        TellCommand.tellCache.put(target, execution.getPlayer());
    }
    
    public static HashMap<Player, Player> getTellCache() {
        return TellCommand.tellCache;
    }
    
    static {
        TellCommand.tellCache = new HashMap<Player, Player>();
    }
}
