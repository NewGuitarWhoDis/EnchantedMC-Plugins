package net.enchanted.enchanted.managers;

import net.enchanted.enchanted.managers.vehicle.Speed;
import net.enchanted.enchanted.managers.vehicle.TireWear;
import net.enchanted.enchanted.managers.vehicle.VehicleInstance;
import net.enchanted.enchanted.managers.vehicle.vehicles.GreenRaceCar;
import net.enchanted.enchanted.managers.vehicle.vehicles.Golfcart;
import net.enchanted.enchanted.managers.vehicle.vehicles.Racebike;
import net.enchanted.enchanted.managers.vehicle.vehicles.Racecar;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class VehicleManager {

    private ArrayList<VehicleInstance> vehicles = new ArrayList<>();

    public void move(Player player, float rotate, float TireWear, float drag, String surface, Entity vehicle) {

        net.enchanted.enchanted.managers.vehicle.TireWear tireWear = new TireWear();
        Speed speed = new Speed();

        // Forward Backwards
        Vector forwardDir = vehicle.getLocation().getDirection();
        Vector total = forwardDir.multiply( speed.getSpeed(player) / 5);
        vehicle.setVelocity(vehicle.getVelocity().add(total));

        // Speedometer
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.valueOf(ChatColor.GOLD + "" + ChatColor.BOLD + Math.round(speed.getSpeed(player) * 14) + " MPH" + " - TIRES: " + Math.round(((TireWear - 1) * 500)) + "%")));

        // Rotate
        float rotateSpeed = rotate * 3 * (1 / tireWear.getTireWear(player));
        if(speed.getSpeed(player) > 0) {
            float rotation = (vehicle.getLocation().getYaw() - rotateSpeed);
            vehicle.setRotation(rotation, 0);
        } else if(speed.getSpeed(player) < 0) {
            float rotation = (vehicle.getLocation().getYaw() + rotateSpeed);
            vehicle.setRotation(rotation, 0);
        }
    }

    // Initialize Vehicles
    public VehicleManager() {
        vehicles.add(new Racecar());
        vehicles.add(new Golfcart());
        vehicles.add(new Racebike());
        vehicles.add(new GreenRaceCar());
    }

    public void summonVehicle(Player player, String arg) {
        for( int i = 0; i < vehicles.size(); i++) {
            if (arg.equalsIgnoreCase(vehicles.get(i).getName())) {
                vehicles.get(i).createVehicle(player, arg);
            }
        }
    }
}
