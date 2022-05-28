package net.enchanted.enchanted.managers;

import net.enchanted.enchanted.commands.VehicleCreateCommand;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class VehicleManager {

    static float TireWear = 1;
    private HashMap<Player, Float> vehiclespeed = new HashMap<Player, Float>();
    private static HashMap<Player, Float> tireWare = new HashMap<Player, Float>();

    public void setSpeed(float speed, Player player) {
        vehiclespeed.put(player, speed);
        for (Player i : vehiclespeed.keySet()) {
        }
    }
    public float getSpeed(Player player) {
        Float speed = vehiclespeed.get(player);
        return speed;
    }

    public void setTireWear(float tireWear, Player player) {
        tireWare.put(player, tireWear);
    }

    public static float getTireWear(Player player) {
        Float tireWear = tireWare.get(player);
        return TireWear;
    }

    public void move(Player player, float rotate, float TireWear, float drag, String surface, Entity vehicle) {

        // Forward Backwards
        Vector forwardDir = vehicle.getLocation().getDirection();
        Vector total = forwardDir.multiply( getSpeed(player) / 5);
        vehicle.setVelocity(vehicle.getVelocity().add(total));
//        player.sendMessage(String.valueOf(getSpeed(player) + " Tires: " + TireWear) + " Drag: " + drag);

        // Speedometer (Action Bar)
//        if (surface == "GRAY_CONCRETE") {
//            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "Sneak to change tires."));
//
//        } else {
//            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.valueOf(ChatColor.GOLD + "" + ChatColor.BOLD + Math.round(getSpeed(player) * 14) + " MPH" + " - TIRES: " + Math.round((TireWear * 100) - 100) + "%")));
//        }
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.valueOf(ChatColor.GOLD + "" + ChatColor.BOLD + Math.round(getSpeed(player) * 14) + " MPH" + " - TIRES: " + Math.round(((TireWear - 1) * 500)) + "%")));

        // Rotate
        float rotateSpeed = rotate * 3 * (1 / getTireWear(player));
        if(getSpeed(player) > 0) {
            float rotation = (vehicle.getLocation().getYaw() - rotateSpeed);
            vehicle.setRotation(rotation, 0);
        } else if(getSpeed(player) < 0) {
            float rotation = (vehicle.getLocation().getYaw() + rotateSpeed);
            vehicle.setRotation(rotation, 0);
        }
    }
 }
