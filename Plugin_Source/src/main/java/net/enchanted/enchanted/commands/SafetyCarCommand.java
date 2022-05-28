package net.enchanted.enchanted.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class SafetyCarCommand implements CommandExecutor {

    public HashMap<String, ArmorStand> parts = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(sender instanceof Player) {
            ArmorStand body = getNewArmorStand(new Location(player.getWorld(), 10139.2, -60, 10006, 0, 0), false, true);
//            ArmorStand passenger1 = getNewArmorStand(new Location(player.getWorld(), 10139.2, -60, 10006, 170, 0).add(-0.1, 0, -0.6), false, true);
//            ArmorStand passenger2 = getNewArmorStand(new Location(player.getWorld(), 10139.2, -60, 10006, 190, 0).add(0.8, 0, -0.6), false, true);
            ItemStack bodytexture = new ItemStack(Material.PAPER);
            ItemMeta bodymeta = bodytexture.getItemMeta();
            bodymeta.setCustomModelData(10268);
            bodytexture.setItemMeta(bodymeta);
            body.setHelmet(bodytexture);
            body.setCustomName(player.getUniqueId().toString());
//            passenger1.setCustomName(player.getUniqueId().toString() + "+passenger1");
//            passenger2.setCustomName(player.getUniqueId().toString() + "+passenger2");
            parts.put("body", body);
//            parts.put("passenger1", passenger1);
//            parts.put("passenger2", passenger2);
        }
        return true;
    }

    private ArmorStand getNewArmorStand(Location location, boolean visible, boolean mini) {
        ArmorStand as = location.getWorld().spawn(location, ArmorStand.class);

        as.setBasePlate(false);
        as.setArms(true);
        as.setVisible(true);
        as.setInvulnerable(false);
        as.setCanPickupItems(false);
        as.setGravity(true);
        as.setSmall(mini);

        return as;
    }
}
