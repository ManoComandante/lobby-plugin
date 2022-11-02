package com.github.manocomandante.redefastly.lobby.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
   private final ItemStack itemStack;

   public ItemBuilder(ItemStack itemStack) {
      this.itemStack = itemStack;
   }

   public ItemBuilder(Material material) {
      this(new ItemStack(material));
   }

   public ItemBuilder(Material material, int amount) {
      this(new ItemStack(material, amount));
   }

   public ItemBuilder(Material material, int amount, int data) {
      this(new ItemStack(material, amount, (short)((byte)data)));
   }

   public ItemBuilder name(String name) {
      return this.applyItemMeta((im) -> {
         im.setDisplayName(name);
      });
   }

   public ItemBuilder lore(String... lore) {
      return this.applyItemMeta((im) -> {
         im.setLore(Arrays.asList(lore));
      });
   }

   public ItemBuilder lore(List<String> lore) {
      return this.applyItemMeta((im) -> {
         im.setLore(lore);
      });
   }

   public ItemBuilder skull(String name) {
      return this.applySkullMeta((im) -> {
         im.setOwner(name);
      });
   }

   public ItemBuilder hideFlags() {
      return this.applyItemMeta((im) -> {
         im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      });
   }

   public ItemBuilder addLore(String... lore) {
      return this.applyItemMeta((im) -> {
         List<String> strings = im.getLore();
         if (!im.hasLore()) {
            strings = new ArrayList<String>();
         }

         ((List<String>)strings).addAll(Arrays.asList(lore));
         im.setLore((List<String>)strings);
      });
   }

   public ItemBuilder applyItemStack(Consumer<ItemStack> consumer) {
      consumer.accept(this.itemStack);
      return this;
   }

   public ItemBuilder applyItemMeta(Consumer<ItemMeta> consumer) {
      ItemMeta itemMeta = this.itemStack.getItemMeta();
      consumer.accept(itemMeta);
      this.itemStack.setItemMeta(itemMeta);
      return this;
   }

   public ItemBuilder applySkullMeta(Consumer<SkullMeta> consumer) {
      SkullMeta skullMeta = (SkullMeta)this.itemStack.getItemMeta();
      consumer.accept(skullMeta);
      this.itemStack.setItemMeta(skullMeta);
      return this;
   }

   public ItemStack create() {
      return this.itemStack;
   }

   public ItemBuilder glow() {
      this.applyItemMeta((im) -> {
         im.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
         im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      });
      return this;
   }
}