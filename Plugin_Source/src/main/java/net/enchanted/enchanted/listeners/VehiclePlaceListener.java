package net.enchanted.enchanted.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class VehiclePlaceListener implements Listener {

    @EventHandler
    public void onVehiclePlace(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        final Action action = event.getAction();
        final ItemStack item = event.getItem();
        final Block clickedBlock = event.getClickedBlock();

        if (!action.equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (item == null) return;
        if (item.getItemMeta().getCustomModelData() == 10267);
    }
}
