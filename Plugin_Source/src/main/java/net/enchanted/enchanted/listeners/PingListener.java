package net.enchanted.enchanted.listeners;

import net.enchanted.enchanted.Enchanted;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

public class PingListener implements Listener {
    Plugin plugin = Enchanted.getPlugin(Enchanted.class);

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        String path = plugin.getConfig().getString("MOTD");
        event.setMotd(ChatColor.translateAlternateColorCodes('&', path));
    }
}
