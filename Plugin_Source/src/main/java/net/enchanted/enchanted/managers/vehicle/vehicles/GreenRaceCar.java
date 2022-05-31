package net.enchanted.enchanted.managers.vehicle.vehicles;

import net.enchanted.enchanted.Enchanted;
import net.enchanted.enchanted.managers.vehicle.VehicleInstance;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;

public class GreenRaceCar extends VehicleInstance {

    public HashMap<String, ArmorStand> parts = new HashMap<>();

    @Override
    public String getName() {
        return "GreenRaceCar";
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
    public Float TurnRate() {
        return null;
    }

    @Override
    public int FuelRate() {
        return 0;
    }

    @Override
    public Float FuelCapacity() {
        return null;
    }

    @Override
    public Boolean hasStorage() {
        return false;
    }

    @Override
    public int StorageSize() {
        return 0;
    }

    @Override
    public void createVehicle(Player player, String args) {

        int x = (int) 10139.5;
        int y = -58;
        int z = (int) 10006.5;

        Location location = new Location(Bukkit.getWorld("flatroom"), x,y,z);

        ArmorStand body = getNewArmorStand(location.clone().add(0, 0, 0), true, false);

        // Creates Texture for the body
        ItemStack bodytexture = new ItemStack(Material.PAPER);
        ItemMeta bodymeta = bodytexture.getItemMeta();
        // Racecar Texture ID
        bodymeta.setCustomModelData(10265);
        bodytexture.setItemMeta(bodymeta);
        // Sets vehicle ID
        body.setCustomName("GreenRaceCar");
        body.setRightArmPose(new EulerAngle(0, 0, 0));
        body.setItemInHand(new ItemStack(bodytexture));

        // Adds body to hashmap
        parts.put("body", body);

    }

    private ArmorStand getNewArmorStand(Location location, boolean visible, boolean mini) {
        ArmorStand as = location.getWorld().spawn(location, ArmorStand.class);

        as.setBasePlate(false);
        as.setArms(true);
        as.setVisible(visible);
        as.setInvulnerable(false);
        as.setCanPickupItems(false);
        as.setGravity(true);
        as.setSmall(mini);

        return as;
    }
}
