package net.enchanted.enchanted.listeners;

import net.enchanted.enchanted.Enchanted;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class JoinLeaveListener implements Listener {

    Plugin plugin = Enchanted.getPlugin(Enchanted.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String path = plugin.getConfig().getString("JoinMessage");
        path = path.replaceAll("%player%", event.getPlayer().getName());
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', path));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        String path = plugin.getConfig().getString("LeaveMessage");
        path = path.replaceAll("%player%", event.getPlayer().getName());
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', path));
    }

}
