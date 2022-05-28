package net.enchanted.enchanted.managers.vehicle;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TireWear {

    static float TireWear = 1;
    private static HashMap<Player, Float> tireWare = new HashMap<Player, Float>();

    public void setTireWear(float tireWear, Player player) {
        tireWare.put(player, tireWear);
    }

    public static float getTireWear(Player player) {
        Float tireWear = tireWare.get(player);
        return TireWear;
    }

}
