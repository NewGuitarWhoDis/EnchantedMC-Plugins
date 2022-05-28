package net.enchanted.enchanted.managers.vehicle;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Speed {

    private HashMap<Player, Float> vehiclespeed = new HashMap<Player, Float>();

    public void setSpeed(float speed, Player player) {
        vehiclespeed.put(player, speed);
        for (Player i : vehiclespeed.keySet()) {
        }
    }
    public float getSpeed(Player player) {
        Float speed = vehiclespeed.get(player);
        return speed;
    }

}
