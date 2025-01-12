package org.infernworld.inferncustomeggs.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.infernworld.inferncustomeggs.InfernCustomEggs;

public class SoundUtil {
    private final InfernCustomEggs plugin;
    public SoundUtil(InfernCustomEggs plugin){
        this.plugin = plugin;
    }
    public void sound(Player player, String key) {
        String name = plugin.getConfig().getString(key);
        if (name != null) {
            Sound sound = Sound.valueOf(name);
            player.playSound(player.getLocation(), sound, 1f, 1f);
        }
    }
}
