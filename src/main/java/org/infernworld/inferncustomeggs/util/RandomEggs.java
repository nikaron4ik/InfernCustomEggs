package org.infernworld.inferncustomeggs.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.infernworld.inferncustomeggs.InfernCustomEggs;

import java.util.List;
import java.util.Random;

public class RandomEggs {
    private InfernCustomEggs plugin;
    public RandomEggs(InfernCustomEggs plugin) {
        this.plugin = plugin;
    }
    //    public static ItemStack RandomEggs(String category) {
//        List<String> eggs = InfernCustomEggs.getPlugin().getConfig().getStringList("spawn-eggs." + category);
//        Random random = new Random();
//        String randomEggName = eggs.get(random.nextInt(eggs.size()));
//        return new ItemStack(Material.valueOf(randomEggName));
//    }
    public void RandomCommand(String category, Player player) {
        List<String> command = plugin.getConfig().getStringList("spawn-eggs." + category);
        Random random = new Random();
        String randomCmd = command.get(random.nextInt(command.size()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), randomCmd.replace("%player",player.getName()));
    }
}
