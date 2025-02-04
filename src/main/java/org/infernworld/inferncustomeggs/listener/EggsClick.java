package org.infernworld.inferncustomeggs.listener;

import lombok.val;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.infernworld.inferncustomeggs.InfernCustomEggs;
import org.infernworld.inferncustomeggs.item.Item;
import org.infernworld.inferncustomeggs.util.Color;
import org.infernworld.inferncustomeggs.util.Message;
import org.infernworld.inferncustomeggs.util.RandomEggs;
import org.infernworld.inferncustomeggs.util.SoundUtil;

public class EggsClick {
    private final InfernCustomEggs plugin;
    private final Item items;
    private final RandomEggs randomEggs;
    private final SoundUtil soundUtil;

    public EggsClick(InfernCustomEggs plugin) {
        this.plugin = plugin;
        this.items = new Item(plugin);
        this.randomEggs = new RandomEggs(plugin);
        this.soundUtil = new SoundUtil(plugin);
    }
    public void EggClick(Player player, int slot) {
        Message msg = Message.getInstance();
        val config = plugin.getMenu();
        val items = config.getConfigurationSection("inventory.item");
        String category = null;
        int price = 0;

        String inventoryTitle = player.getOpenInventory().getTitle();
            if (!inventoryTitle.equalsIgnoreCase(msg.getInventoryTitle())) {
                return;
            }

        for (String key : items.getKeys(false)) {
            ConfigurationSection itemSection = items.getConfigurationSection(key);
            if (itemSection.getInt("slot") == slot) {
                category = itemSection.getString("category");
                price = itemSection.getInt("price");
                break;
            }
        }
        if (category == null) {
            return;
        }
        int eggsAmount = getEggs(player);
        if (eggsAmount >= price) {
            EggsAmount(player, price);
            randomEggs.RandomCommand(category, player);
            player.sendMessage(msg.getGiveEggs());
            soundUtil.sound(player, "settings.sound-complete");
        } else {
            player.sendMessage(msg.getNotEggs());
            soundUtil.sound(player, "settings.sound-not-eggs");
        }
    }
    private int getEggs(Player player) {
        int totalAmount = 0;
        for (ItemStack stack : player.getInventory().getStorageContents()) {
            if (stack != null && stack.hasItemMeta()) {
                ItemMeta meta = stack.getItemMeta();
                NamespacedKey key = new NamespacedKey(plugin, "lightcustom_egg");
                if (meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    totalAmount += stack.getAmount();
                }
            }
        }
        return totalAmount;
    }



    private void EggsAmount(Player player, int price) {
        for (ItemStack item : player.getInventory().getStorageContents()) {
            if (item != null && item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                NamespacedKey key = new NamespacedKey(plugin, "lightcustom_egg");
                if(meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    int countAmount = item.getAmount();
                    if (countAmount >= price) {
                        item.setAmount(countAmount - price);
                        break;
                    } else {
                        price -= countAmount;
                        player.getInventory().setItem(player.getInventory().first(item), null);
                    }
                    if (price <= 0) {
                        break;
                    }
                }
            }
        }
    }
}
