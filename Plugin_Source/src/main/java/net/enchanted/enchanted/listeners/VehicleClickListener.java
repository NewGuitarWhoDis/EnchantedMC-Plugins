package net.enchanted.enchanted.listeners;

import net.enchanted.enchanted.Enchanted;
import net.enchanted.enchanted.managers.VehicleManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;

public class VehicleClickListener implements Listener {

    private Inventory storage;
    private Inventory vehicleSettings;
    private Entity entity;

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent event) throws IOException {
        Player player = (Player) event.getPlayer();

        if(!(event.getRightClicked().getType() == EntityType.ARMOR_STAND)) return;
        event.setCancelled(true);
        entity = event.getRightClicked();

        // Key (Vehicle Options)
//        if (player.getItemInHand().getType() == Material.STICK) {
//
//            player.sendMessage(entity.getCustomName() + " " + player.getUniqueId().toString());
//            if(entity.getCustomName().toString() == player.getUniqueId().toString()) {
//                vehicleSettings = Bukkit.createInventory(null, 9, "Edit Vehicle");
//
//                initItem(8, Material.BARRIER, "§c§lClose");
//
//                initItem(0, Material.PAPER, "§c§lLock Vehicle");
//                initItem(1, Material.PAPER, "§c§lUnlock Vehicle");
//
//                player.openInventory(vehicleSettings);
//            } else {
//                player.sendMessage("§c§lYou do not have access to this vehicle!");
//            }
//            return;
//        }

        //Vehicle Storage
//        if(player.isSneaking() == true) {
//            File tmpDir = new File(Enchanted.instance.getDataFolder().getAbsolutePath() + "\\" + player.getUniqueId() + ".yml");
//            boolean exists = tmpDir.exists();
//
//            storage = Bukkit.createInventory(null, 9, "Storage");
//            if (exists == true) {
//                Enchanted.instance.restoreInventory(entity, storage);
//            }
//            player.openInventory(storage);
//            return;
//        }

        //Enter Vehicle
        if(entity.isEmpty()) {
            if (entity.getCustomName() == null) return;
            entity.addPassenger(player);
            double x = entity.getVelocity().getX();
            entity.setVelocity(entity.getVelocity().setX(x * 10));
            VehicleManager vehicleManager = new VehicleManager();
            vehicleManager.setSpeed(0, player);
            vehicleManager.setTireWear(1, player);

        } else {

        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) throws IOException {
        if(!(event.getView().getTitle() == "Storage")) return;
        Enchanted.instance.saveVehicleInventory(entity, storage);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getView().getTitle() == "Edit Vehicle") {
            event.setCancelled(true);
        }
    }

    private void initItem(Integer Slot, Material material, String name) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }


}
