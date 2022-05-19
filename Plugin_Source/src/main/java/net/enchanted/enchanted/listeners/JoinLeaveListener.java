package net.enchanted.enchanted.listeners;

import net.enchanted.enchanted.Enchanted;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class JoinLeaveListener implements Listener {

    Plugin plugin = Enchanted.getPlugin(Enchanted.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        String joinMSG = plugin.getConfig().getString("JoinMessage");

        event.setJoinMessage(joinMSG);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        String leaveMSG = plugin.getConfig().getString("LeaveMessage");

        event.setQuitMessage(leaveMSG);
    }

}
