package com.github.manocomandante.redefastly.lobby.server;

import org.bukkit.plugin.java.*;
import org.bukkit.plugin.messaging.*;

import com.github.manocomandante.redefastly.lobby.utils.*;

import me.saiintbrisson.minecraft.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;

import java.lang.reflect.*;

public abstract class ServerPlugin extends JavaPlugin implements PluginMessageListener
{
    private CommandFrame commandFrame;
    
    protected final void registerListeners(final Listener... listeners) {
        final PluginManager manager = this.getServer().getPluginManager();
        for (final Listener listener : listeners) {
            manager.registerEvents(listener, (Plugin)this);
        }
    }
    
    protected final void registerListeners(final String packageName) throws Throwable {
        try {
            final ClassCollector<Listener> collector = new ClassCollector<Listener>(this.getClass(), Listener.class).filterByPackage(packageName).selectInterfaces(false);
            for (final Class<Listener> clazz : collector.collect()) {
                try {
                    final Constructor<Listener> constructor = clazz.getConstructor(this.getClass());
                    this.registerListeners(constructor.newInstance(this));
                }
                catch (NoSuchMethodException e) {
                    this.registerListeners(clazz.newInstance());
                }
            }
        }
        catch (Throwable $ex) {
            throw $ex;
        }
    }
    
    protected final void registerCommands(final Object... holders) {
        this.getCommandFrame().register(holders);
    }
    
    protected final void registerCommands(final String packageName) throws Throwable {
        try {
            final ClassCollector<Object> collector = new ClassCollector<Object>(this.getClass(), Object.class).filterByPackage(packageName).selectInterfaces(false);
            for (final Class<Object> clazz : collector.collect()) {
                try {
                    final Constructor<Object> constructor = clazz.getConstructor(this.getClass());
                    this.registerCommands(constructor.newInstance(this));
                }
                catch (NoSuchMethodException e) {
                    this.registerCommands(clazz.newInstance());
                }
            }
        }
        catch (Throwable $ex) {
            throw $ex;
        }
    }
    
    public CommandFrame getCommandFrame() {
        if (this.commandFrame == null) {
            (this.commandFrame = new CommandFrame((Plugin)this)).setErrorMessage("§cUm erro ocorreu! {error}");
            this.commandFrame.setLackPermMessage("§cVocê não possui permissão para executar este comando.");
            this.commandFrame.setUsageMessage("§cUtilize /{usage}.");
            this.commandFrame.setIncorrectTargetMessage("§cVocê não pode utilizar este comando pois ele está direcionado apenas para {target}.");
        }
        return this.commandFrame;
    }
}
