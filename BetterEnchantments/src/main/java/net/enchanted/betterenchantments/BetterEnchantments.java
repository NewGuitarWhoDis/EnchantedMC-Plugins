package net.enchanted.betterenchantments;

import net.enchanted.betterenchantments.commands.armoursetCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterEnchantments extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("armourset").setExecutor(new armoursetCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
