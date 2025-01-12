package org.infernworld.inferncustomeggs;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;
import org.infernworld.inferncustomeggs.command.CmdTabCompleter;
import org.infernworld.inferncustomeggs.command.Commands;
import org.infernworld.inferncustomeggs.item.Item;
import org.infernworld.inferncustomeggs.listener.Event;
import org.infernworld.inferncustomeggs.util.Message;

import java.io.File;
import java.io.IOException;

public final class InfernCustomEggs extends JavaPlugin {

    private File menuFile;
    @Getter
    private FileConfiguration menu;
    @Getter private FileConfiguration config;
    private File cfgFile;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        cfgFile= new File(getDataFolder(),"config.yml");
        menuFile = new File(getDataFolder(), "menu.yml");
        if (!menuFile.exists()) {
            saveResource("menu.yml", false);
        }
        menu = YamlConfiguration.loadConfiguration(menuFile);
        config = YamlConfiguration.loadConfiguration(cfgFile);
        if (!cfgFile.exists()) {
            try {
                cfgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new Message(config,menu);
        Bukkit.getPluginCommand("eggs").setExecutor(new Commands(this));
        Bukkit.getPluginCommand("eggs").setTabCompleter(new CmdTabCompleter());
        Bukkit.getPluginManager().registerEvents(new Event(this), this);
    }
}
