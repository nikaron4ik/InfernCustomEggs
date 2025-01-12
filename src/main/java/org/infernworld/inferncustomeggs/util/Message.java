package org.infernworld.inferncustomeggs.util;

import lombok.Getter;
import lombok.val;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Message {
    @Getter private static Message instance;
    private FileConfiguration config;
    private FileConfiguration menuConfig;
    @Getter private final String title;
    @Getter private final int size;
    @Getter private final String giveEggs;
    @Getter private final String notEggs;
    @Getter private final String material;
    @Getter private final String itemName;
    @Getter private final List<String> itemLore;

    public Message(FileConfiguration cfg, FileConfiguration menuCfg) {
        instance = this;
        this.config = cfg;
        this.menuConfig = menuCfg;
        String inv = "inventory.";
        String item = "item.";
        this.title = Color.hex(menuConfig.getString(inv + "title"));
        this.size = menuConfig.getInt(inv + "size");
        this.giveEggs = Color.hex(config.getString("message.give-eggs"));
        this.notEggs = Color.hex(config.getString("message.not-eggs"));
        this.itemLore = new ArrayList<>(Color.hexList(config.getStringList(item + "lore")));
        this.itemName = Color.hex(config.getString(item + "name"));
        this.material = config.getString(item + "material");
    }
}
