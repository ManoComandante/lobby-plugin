package com.github.manocomandante.redefastly.lobby.utils;

import org.bukkit.entity.*;

import com.github.manocomandante.redefastly.lobby.*;

import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import java.lang.reflect.*;
import net.minecraft.server.v1_8_R3.*;

import org.bukkit.*;

public class TablistUtil
{
    private static void send(final Player player, final String header, final String footer) {
        final PacketPlayOutPlayerListHeaderFooter headerFooterPacket = new PacketPlayOutPlayerListHeaderFooter();
        final IChatBaseComponent headerComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        final IChatBaseComponent footerComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        try {
            final Field headerField = headerFooterPacket.getClass().getDeclaredField("a");
            final Field footerField = headerFooterPacket.getClass().getDeclaredField("b");
            headerField.setAccessible(true);
            footerField.setAccessible(true);
            headerField.set(headerFooterPacket, headerComp);
            footerField.set(headerFooterPacket, footerComp);
            headerField.setAccessible(false);
            footerField.setAccessible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket((Packet)headerFooterPacket);
    }
    
    public static void setup(final Player player) {
        send(player, convert(LobbyPlugin.getInstance().getConfig().getString("tablist.header")), convert(LobbyPlugin.getInstance().getConfig().getString("tablist.footer")));
    }
    
    private static String convert(final String data) {
        return ChatColor.translateAlternateColorCodes('&', data);
    }
}
