package com.github.manocomandante.redefastly.lobby.listeners.player;

import com.github.manocomandante.redefastly.lobby.LobbyPlugin;
import com.github.manocomandante.redefastly.lobby.manager.HideManager;
import com.github.manocomandante.redefastly.lobby.manager.ItemsManager;
import com.github.manocomandante.redefastly.lobby.manager.VanishManager;
import com.github.manocomandante.redefastly.lobby.scoreboard.LobbyScoreboard;
import com.github.manocomandante.redefastly.lobby.utils.TablistUtil;
import com.github.manocomandante.redefastly.lobby.utils.TitleAPI;
import com.nickuc.login.api.nLoginAPI;
import com.nickuc.login.api.event.bukkit.auth.LoginEvent;
import com.nickuc.login.api.event.bukkit.auth.RegisterEvent;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
   @EventHandler
   public void onPlayerJoin(PlayerJoinEvent event) {
      event.setJoinMessage((String)null);
      Player player = event.getPlayer();
      player.getInventory().clear();
      player.getInventory().setArmorContents((ItemStack[])null);
      TablistUtil.setup(player);
      LobbyScoreboard scoreboard = new LobbyScoreboard(player);
      scoreboard.create(player);
      if (nLoginAPI.getApi().isRegistered(player.getName())) {
         (new TitleAPI("§6§lFASTLY", "§f/login <senha>", 0, 99999, 0)).send(player);
      } else {
         (new TitleAPI("§6§lFASTLY", "§f/register <senha> <senha>", 0, 99999, 0)).send(player);
      }

      HideManager.getInstance().getPlayers().forEach((p) -> {
         p.hidePlayer(player);
      });
      VanishManager.getInstance().getPlayers().forEach((id) -> {
         Player playerById = Bukkit.getPlayer(id);
         if (playerById != null) {
            player.hidePlayer(playerById);
         }

      });
      player.teleport(LobbyPlugin.getInstance().getLocationsManager().getLocation("spawn"));
   }

   @EventHandler
   public void onLogin(LoginEvent event) {
      Player player = event.getPlayer();
      if (player.hasPermission("fastly.vip")) {
         player.setAllowFlight(true);
      }

      (new TitleAPI("§e§lAutenticado!", "§fAutenticado com sucesso!")).send(player);
      player.playSound(player.getLocation(), Sound.LEVEL_UP, 3.0F, 5.0F);
      ItemsManager.giveItems(player);
   }

   @EventHandler
   public void onRegister(RegisterEvent event) {
      Player player = event.getPlayer();
      if (player.hasPermission("fastly.vip")) {
         player.setAllowFlight(true);
      }

      (new TitleAPI("§e§lAutenticado!", "§fRegistrado com sucesso!")).send(player);
      player.playSound(player.getLocation(), Sound.LEVEL_UP, 3.0F, 5.0F);
      ItemsManager.giveItems(player);
   }
}