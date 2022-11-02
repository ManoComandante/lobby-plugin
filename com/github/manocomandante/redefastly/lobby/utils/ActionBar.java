package com.github.manocomandante.redefastly.lobby.utils;

import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import net.minecraft.server.v1_8_R3.*;

public class ActionBar
{
    public static void send(final Player player, final String text) {
        final PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
    }
}
