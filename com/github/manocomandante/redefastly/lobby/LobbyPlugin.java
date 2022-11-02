package com.github.manocomandante.redefastly.lobby;

import org.bukkit.plugin.*;
import org.bukkit.plugin.messaging.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import com.github.manocomandante.redefastly.lobby.controller.*;
import com.github.manocomandante.redefastly.lobby.manager.*;
import com.github.manocomandante.redefastly.lobby.scoreboard.pattern.*;
import com.github.manocomandante.redefastly.lobby.scoreboard.updater.*;
import com.github.manocomandante.redefastly.lobby.server.*;
import com.github.manocomandante.redefastly.lobby.utils.*;
import com.github.manocomandante.redefastly.lobby.view.updater.*;
import com.google.common.io.*;

public final class LobbyPlugin extends ServerPlugin
{
    private static LobbyPlugin instance;
    private ScoreboardPattern scoreboardPattern;
    private LocationsManager locationsManager;
    private String lobbyNumber;
    private int networkCount;
    
    public LobbyPlugin() {
        this.networkCount = 0;
    }
    
    public static LobbyPlugin getInstance() {
        return LobbyPlugin.instance;
    }
    
    public void onEnable() {
        (LobbyPlugin.instance = this).saveDefaultConfig();
        this.getServer().getMessenger().registerIncomingPluginChannel((Plugin)this, "BungeeCord", (PluginMessageListener)this);
        this.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        (this.locationsManager = new LocationsManager(this)).load();
        this.lobbyNumber = this.getConfig().getString("lobby-number");
        this.scoreboardPattern = new ScoreboardPattern(this);
        VaultModule.setupVault();
        ServerManager.getInstance().load(this.getConfig());
        VanishManager.getInstance().startRunnable();
        BungeeController.getInstance().startRunnable();
        new ServersViewUpdater().runTaskTimer((Plugin)this, 40L, 40L);
        new ScoreboardUpdater().runTaskTimerAsynchronously((Plugin)this, 40L, 40L);
        try {
			this.registerListeners("com.github.manocomandante.redefastly.lobby.listeners");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			this.registerCommands("com.github.manocomandante.redefastly.lobby.commands");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        WorldManager.getInstance().loadWorld();
    }
    
    public void onPluginMessageReceived(final String channel, final Player player, final byte[] bytes) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        try {
            final ByteArrayDataInput input = ByteStreams.newDataInput(bytes);
            final String subChannel = input.readUTF();
            if (subChannel.equals("PlayerCount")) {
                final String serverName = input.readUTF();
                final int response = input.readInt();
                if (serverName.equals("ALL")) {
                    this.networkCount = response;
                }
                else {
                    final boolean contains = BungeeController.getInstance().getOnlineCounter().containsKey(serverName);
                    if (contains) {
                        BungeeController.getInstance().getOnlineCounter().replace(serverName, response);
                    }
                    else {
                        BungeeController.getInstance().getOnlineCounter().put(serverName, response);
                    }
                }
            }
        }
        catch (Exception exception) {
            Bukkit.getLogger().info("Um servidor configurado não existe no bungeecord.");
        }
    }
    
    public ScoreboardPattern getScoreboardPattern() {
        return this.scoreboardPattern;
    }
    
    public LocationsManager getLocationsManager() {
        return this.locationsManager;
    }
    
    public String getLobbyNumber() {
        return this.lobbyNumber;
    }
    
    public int getNetworkCount() {
        return this.networkCount;
    }
}
