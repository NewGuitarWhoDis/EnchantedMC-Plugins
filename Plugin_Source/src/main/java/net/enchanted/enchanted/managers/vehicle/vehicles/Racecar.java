package net.enchanted.enchanted.managers.vehicle.vehicles;

import net.enchanted.enchanted.Enchanted;
import net.enchanted.enchanted.managers.vehicle.VehicleInstance;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Racecar extends VehicleInstance {

    public HashMap<String, ArmorStand> parts = new HashMap<>();

    @Override
    public String getName() {
        return "Racecar";
    }

    @Override
    public Float TopSpeed() {
        return 30f;
    }

    @Override
    public Float StartAcceleration() {
        return 0.19f;
    }

    @Override
    public Float Jerk() {
        return 0.9f;
    }

    @Override
    public Float BreakForce() {
        return 0.15f;
    }

    @Override
    public Float Drag() {
        return 0.01f;
    }

    @Override
    public Float WearRate() {
        return 0.00001f;
    }

    @Override
    public void createVehicle(Player player, String args) {

        World world = Enchanted.instance.getServer().getWorld("flatroom");
        int x = 0;
        int y = 0;
        int z = 0;

        ArmorStand body = getNewArmorStand(new Location( world, x, y, z, 0, 0), false, true);

        // Creates Texture for the body
        ItemStack bodytexture = new ItemStack(Material.PAPER);
        ItemMeta bodymeta = bodytexture.getItemMeta();
        // Racecar Texture ID
        bodymeta.setCustomModelData(10267);
        bodytexture.setItemMeta(bodymeta);
        body.setHelmet(bodytexture);
        // Sets vehicle ID
        body.setCustomName("Racecar");

        // Adds body to hashmap
        parts.put("body", body);

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
