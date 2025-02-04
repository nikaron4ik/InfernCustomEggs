package org.infernworld.inferncustomeggs.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CmdTabCompleter implements TabCompleter {

    private final List<String> numbers = new ArrayList<>();

    public CmdTabCompleter() {
        for (int i = 1; i <= 64; i++) {
            numbers.add(Integer.toString(i));
        }
    }
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length > 3 || !sender.hasPermission("lightcustomeggs.give")) {
            return List.of();
        }
        if (args.length == 1) {
            return List.of("give");
        }
        if (!args[0].equalsIgnoreCase("give")) {
            return List.of();
        }
        if (args.length == 2) {
            final String input = args[1].toLowerCase();
            final List<String> completins = new ArrayList<>();
            for (final Player payer : Bukkit.getOnlinePlayers()) {
                if (this.startsWithIgnoreCase(input, payer.getName())) {
                    completins.add(payer.getName());
                }
            }
            return completins;
        }
        return numbers;
    }
    public static boolean startsWithIgnoreCase(final String input, final String completion) {
        if (completion == null || input == null) {
            return false;
        }
        return completion.regionMatches(true, 0, input, 0, input.length());
    }
}