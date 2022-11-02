package com.github.manocomandante.redefastly.lobby.commands;

import me.saiintbrisson.minecraft.command.*;
import org.bukkit.entity.*;

import com.github.manocomandante.redefastly.lobby.utils.*;

import me.saiintbrisson.minecraft.command.annotations.*;

public class ReplyCommand
{
    @Command(name = "responder", aliases = { "r", "reply" }, target = CommandTarget.PLAYER, usage = "responder <mensagem>")
    public void handleTellCommand(final Execution execution, final String[] args) {
        final Player target = TellCommand.getTellCache().get(execution.getPlayer());
        if (target == null) {
            execution.sendMessage("§cVocê não tem ninguém para responder.");
            return;
        }
        final StringBuilder message = new StringBuilder();
        for (final String arg : args) {
            message.append(arg).append(" ");
        }
        execution.sendMessage("§8Mensagem para " + VaultModule.getPlayerGroupPrefix(target.getName()) + "§8: §6" + (Object)message);
        target.sendMessage("§8Mensagem de " + VaultModule.getPlayerGroupPrefix(execution.getPlayer().getName()) + "§8: §6" + (Object)message);
        TellCommand.getTellCache().put(target, execution.getPlayer());
    }
}
