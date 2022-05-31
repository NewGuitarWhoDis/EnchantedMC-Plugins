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

public class Golfcart extends VehicleInstance {

    public HashMap<String, ArmorStand> parts = new HashMap<>();

    @Override
    public String getName() {
        return "Golfcart";
    }

    @Override
    public Float TopSpeed() {
        return 2f;
    }

    @Override
    public Float StartAcceleration() {
        return 0.1f;
    }

    @Override
    public Float Jerk() {
        return 0.6f;
    }

    @Override
    public Float BreakForce() {
        return 0.1f;
    }

    @Override
    public Float Drag() {
        return 0.02f;
    }

    @Override
    public Float WearRate() {
        return 0f;
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
        return true;
    }

    @Override
    public int StorageSize() {
        return 9;
    }


    @Override
    public void createVehicle(Player player, String args) {

        World world = Enchanted.instance.getServer().getWorld("flatroom");
        int x = (int) 10139.5;
        int y = -59;
        int z = (int) 10006.5;

        ArmorStand body = getNewArmorStand(new Location( world, x, y, z, 0, 0), false, true);

        // Creates Texture for the body
        ItemStack bodytexture = new ItemStack(Material.PAPER);
        ItemMeta bodymeta = bodytexture.getItemMeta();
        // Racecar Texture ID
        bodymeta.setCustomModelData(10268);
        bodytexture.setItemMeta(bodymeta);
        body.setHelmet(bodytexture);
        // Sets vehicle ID
        body.setCustomName("Golfcart");

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