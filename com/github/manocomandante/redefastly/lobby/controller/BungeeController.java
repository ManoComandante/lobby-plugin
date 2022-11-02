package com.github.manocomandante.redefastly.lobby.controller;

import org.bukkit.scheduler.*;
import org.bukkit.plugin.*;

import com.github.manocomandante.redefastly.lobby.*;
import com.github.manocomandante.redefastly.lobby.manager.*;
import com.github.manocomandante.redefastly.lobby.model.*;
import com.google.common.io.*;
import java.util.*;
import org.bukkit.entity.*;

public class BungeeController
{
    private static BungeeController instance;
    private final HashMap<String, Integer> onlineCounter;
    
    public BungeeController() {
        this.onlineCounter = new HashMap<String, Integer>();
    }
    
    public static BungeeController getInstance() {
        if (BungeeController.instance == null) {
            BungeeController.instance = new BungeeController();
        }
        return BungeeController.instance;
    }
    
    public void startRunnable() {
        new BukkitRunnable() {
            public void run() {
                final ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
                byteArrayDataOutput.writeUTF("PlayerCount");
                byteArrayDataOutput.writeUTF("ALL");
                LobbyPlugin.getInstance().getServer().sendPluginMessage((Plugin)LobbyPlugin.getInstance(), "BungeeCord", byteArrayDataOutput.toByteArray());
                for (final ServerModel server : ServerManager.getInstance().getServers()) {
                    final ByteArrayDataOutput output = ByteStreams.newDataOutput();
                    output.writeUTF("PlayerCount");
                    output.writeUTF(server.getBungeeServerName());
                    LobbyPlugin.getInstance().getServer().sendPluginMessage((Plugin)LobbyPlugin.getInstance(), "BungeeCord", output.toByteArray());
                }
            }
        }.runTaskTimer((Plugin)LobbyPlugin.getInstance(), 100L, 100L);
    }
    
    public int getCount(final String name) {
        return this.onlineCounter.getOrDefault(name, 0);
    }
    
    public void connect(final Player player, final String serverName) {
        final ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(serverName);
        player.sendPluginMessage((Plugin)LobbyPlugin.getInstance(), "BungeeCord", output.toByteArray());
    }
    
    public HashMap<String, Integer> getOnlineCounter() {
        return this.onlineCounter;
    }
}
