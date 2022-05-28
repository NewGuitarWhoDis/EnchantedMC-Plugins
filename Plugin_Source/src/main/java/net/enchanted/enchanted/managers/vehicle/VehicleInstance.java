package net.enchanted.enchanted.managers.vehicle;

import org.bukkit.entity.Player;

public abstract class VehicleInstance {

   public abstract String getName();
   public abstract Float TopSpeed();
   public abstract Float StartAcceleration();
   public abstract Float Jerk();
   public abstract Float BreakForce();
   public abstract Float Drag();
   public abstract Float WearRate();


   public abstract void createVehicle(Player player, String args);

}
