package com.github.manocomandante.redefastly.lobby.utils;

import net.milkbowl.vault.permission.*;
import net.milkbowl.vault.economy.*;
import net.milkbowl.vault.chat.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

public final class VaultModule
{
    @Deprecated
    private static Permission permission;
    private static Economy economy;
    private static Chat chat;
    
    public static String getPlayerGroupPrefix(final String player) {
        return getChat().getGroupPrefix("null", getPermission().getPrimaryGroup("null", Bukkit.getOfflinePlayer(player))).replace('&', '§') + player;
    }
    
    public static String getPlayerGroupSuffix(final String player) {
        return getChat().getGroupSuffix("null", getPermission().getPrimaryGroup("null", Bukkit.getOfflinePlayer(player))).replace('&', '§');
    }
    
    public static Permission getPermission() {
        return VaultModule.permission;
    }
    
    public static Chat getChat() {
        return VaultModule.chat;
    }
    
    public static Economy getEconomy() {
        return VaultModule.economy;
    }
    
    public static boolean hasVault() {
        return Bukkit.getPluginManager().getPlugin("Vault") != null;
    }
    
    public static boolean hasEconomy() {
        return VaultModule.economy != null;
    }
    
    public static boolean hasChat() {
        return VaultModule.chat != null;
    }
    
    public static boolean hasPermission() {
        return VaultModule.permission != null;
    }
    
    private static boolean setupChat() {
        final RegisteredServiceProvider<Chat> chatProvider = (RegisteredServiceProvider<Chat>)Bukkit.getServer().getServicesManager().getRegistration((Class)Chat.class);
        if (chatProvider != null) {
            VaultModule.chat = (Chat)chatProvider.getProvider();
        }
        return VaultModule.chat != null;
    }
    
    private static boolean setupEconomy() {
        final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>)Bukkit.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (economyProvider != null) {
            VaultModule.economy = (Economy)economyProvider.getProvider();
        }
        return VaultModule.economy != null;
    }
    
    private static boolean setupPermissions() {
        final RegisteredServiceProvider<Permission> permissionProvider = (RegisteredServiceProvider<Permission>)Bukkit.getServer().getServicesManager().getRegistration((Class)Permission.class);
        if (permissionProvider != null) {
            VaultModule.permission = (Permission)permissionProvider.getProvider();
        }
        return VaultModule.permission != null;
    }
    
    public static void setupVault() {
        setupEconomy();
        setupChat();
        setupPermissions();
    }
    
    static {
        VaultModule.permission = null;
        VaultModule.economy = null;
        VaultModule.chat = null;
    }
}
