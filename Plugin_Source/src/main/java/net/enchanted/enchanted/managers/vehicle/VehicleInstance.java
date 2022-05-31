package net.enchanted.enchanted.managers.vehicle;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class VehicleInstance {

   public abstract String getName();
   public abstract Float TopSpeed();
   public abstract Float StartAcceleration();
   public abstract Float Jerk();
   public abstract Float BreakForce();
   public abstract Float Drag();
   public abstract Float WearRate();
   public abstract Float TurnRate();
   public abstract int FuelRate();
   public abstract Float FuelCapacity();
   public abstract Boolean hasStorage();
   public abstract int StorageSize();


   public abstract void createVehicle(Player player, String args);

}
