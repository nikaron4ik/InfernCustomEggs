package org.infernworld.inferncustomeggs.listener;

import lombok.val;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.infernworld.inferncustomeggs.InfernCustomEggs;
import org.infernworld.inferncustomeggs.item.Item;
import org.infernworld.inferncustomeggs.util.Color;
import org.infernworld.inferncustomeggs.util.Message;

import java.util.Objects;


public class Event implements Listener {
    private final InfernCustomEggs plugin;
    private final Item items;
    private final EggsClick eggsClick;
    private final Message msg;
    public Event(InfernCustomEggs plugin) {
        this.plugin = plugin;
        this.items = new Item(plugin);
        this.eggsClick = new EggsClick(plugin);
        this.msg = Message.getInstance();
    }
    @EventHandler
    public void onClickEvent(InventoryClickEvent e) {
        String title = msg.getTitle();
        if (e.getView().getTitle().equalsIgnoreCase(title)) {
            e.setCancelled(true);
            val player = (Player) e.getWhoClicked();
            val slot = e.getSlot();
            eggsClick.EggClick(player, slot);
        }
    }
    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e) {
        if (e.getItem() != null && e.getItem().isSimilar(items.items())) {
            e.setUseInteractedBlock(org.bukkit.event.Event.Result.ALLOW);
            e.setUseItemInHand(org.bukkit.event.Event.Result.DENY);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getCurrentItem() != null && e.getInventory().getType() == InventoryType.DISPENSER) {
            if (e.getCurrentItem().isSimilar(items.items())) {
                e.setCancelled(true);
            }
        }

        if (e.getClick().isKeyboardClick()) {
            ItemStack hotbarItem = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());
            if(hotbarItem != null && hotbarItem.isSimilar(items.items()) && e.getInventory().getType() == InventoryType.DISPENSER) {
                e.setCancelled(true);
            }
        }
    }
}
