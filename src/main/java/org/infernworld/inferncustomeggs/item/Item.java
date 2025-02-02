package org.infernworld.inferncustomeggs.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.infernworld.inferncustomeggs.InfernCustomEggs;
import org.infernworld.inferncustomeggs.util.Color;
import org.infernworld.inferncustomeggs.util.Message;

import java.util.List;

public class Item {
    private final InfernCustomEggs plugin;
    private final Message msg;
    public Item(InfernCustomEggs plugin) {
        this.plugin = plugin;
        this.msg = Message.getInstance();
    }
    public ItemStack items() {
        String material = msg.getMaterial();
        ItemStack item = new ItemStack(Material.valueOf(material));
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(msg.getItemName());

        List<String> lore = msg.getItemLore();
        meta.setLore(lore);

        NamespacedKey key = new NamespacedKey(plugin, "inferncustom_egg");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");

        item.setItemMeta(meta);
        return item;
    }
}