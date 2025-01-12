package org.infernworld.inferncustomeggs.gui;

import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.infernworld.inferncustomeggs.InfernCustomEggs;
import org.infernworld.inferncustomeggs.util.Color;
import org.infernworld.inferncustomeggs.util.Message;
import org.jetbrains.annotations.NotNull;


public class Menu implements InventoryHolder {
    private final Inventory inventory;
    private final InfernCustomEggs plugin;
    public Menu (InfernCustomEggs plugin) {
        Message msg = Message.getInstance();
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(null, msg.getSize(), msg.getTitle());
        val config = plugin.getMenu();
        ConfigurationSection items = config.getConfigurationSection("inventory.item");
        for (String key : items.getKeys(false)) {
            ConfigurationSection itemSection = items.getConfigurationSection(key);
            val materialName = itemSection.getString("material");
            val amount = itemSection.getInt("amount");
            val slot = itemSection.getInt("slot");
            val category = itemSection.getString("category");
            val name = Color.hex(itemSection.getString("name"));
            val lore = Color.hexList(itemSection.getStringList("lore"));

            ItemStack item = new ItemStack(Material.valueOf(materialName), amount);
            val meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);
            inventory.setItem(slot, item);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
