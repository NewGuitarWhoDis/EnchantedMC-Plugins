package net.enchanted.enchanted.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CreateVehicle {

    private HashMap<String, ArmorStand> parts = new HashMap<>();

    public CreateVehicle(double x, double y, double z) {
        Location location = new Location(Bukkit.getWorld("world"), x,y,z);

        ArmorStand body = getNewArmorStand(location, false, true);

        body.setHelmet(new ItemStack(Material.GOLD_BLOCK));

        parts.put("body", body);
    }

    private ArmorStand getNewArmorStand(Location location, boolean visible, boolean mini) {
        ArmorStand as = location.getWorld().spawn(location, ArmorStand.class);

        as.setBasePlate(false);
        as.setArms(true);
        as.setVisible(visible);
        as.setInvulnerable(true);
        as.setCanPickupItems(false);
        as.setGravity(false);
        as.setSmall(mini);

        return as;
    }

}
