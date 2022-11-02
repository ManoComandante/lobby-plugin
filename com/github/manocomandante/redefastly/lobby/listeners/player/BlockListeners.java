package com.github.manocomandante.redefastly.lobby.listeners.player;

import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockListeners implements Listener
{
    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPermission("fastly.administrator")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPermission("fastly.administrator")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        final Entity entity = event.getEntity();
        if ((entity instanceof ItemFrame || entity instanceof EntityItemFrame) && event.getDamager() instanceof Player) {
            final Player player = (Player)event.getDamager();
            if (!player.hasPermission("fastly.administrator")) {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            final Block block = event.getClickedBlock();
            if (block == null) {
                return;
            }
            if (block.getType() == Material.AIR) {
                return;
            }
            if (block.getType() == Material.TRAP_DOOR && !player.hasPermission("fastly.administrator")) {
                event.setCancelled(true);
            }
        }
    }
}
