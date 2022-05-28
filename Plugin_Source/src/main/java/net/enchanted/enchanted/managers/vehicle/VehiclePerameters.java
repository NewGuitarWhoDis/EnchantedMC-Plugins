package net.enchanted.enchanted.managers.vehicle;

import net.enchanted.enchanted.managers.vehicle.vehicles.Golfcart;
import net.enchanted.enchanted.managers.vehicle.vehicles.Racebike;
import net.enchanted.enchanted.managers.vehicle.vehicles.Racecar;

import java.util.ArrayList;

public class VehiclePerameters {

    public ArrayList<VehicleInstance> vehicles = new ArrayList<>();

    public VehiclePerameters() {
        vehicles.add(new Racecar());
        vehicles.add(new Golfcart());
        vehicles.add(new Racebike());
    }

    public float topSpeed(String vehicleName) {
        Float TopSpeed = 0f;
        for(int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                TopSpeed = vehicles.get(i).TopSpeed();
            }
        }
        return TopSpeed;
    }

    public float startAcceleration(String vehicleName) {
        float StartAcceleration = 0f;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                StartAcceleration = vehicles.get(i).StartAcceleration();
            }
        }
        return StartAcceleration;
    }

    public float jerk(String vehicleName) {
        Float Jerk = 0f;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                Jerk = vehicles.get(i).Jerk();
            }
        }
        return Jerk;
    }

    public float breakForce(String vehicleName) {
        Float BreakForce = 0f;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                BreakForce = vehicles.get(i).BreakForce();
            }
        }
        return BreakForce;
    }

    public float Drag(String vehicleName) {
        Float drag = 0f;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                drag = vehicles.get(i).Drag();
            }
        }
        return drag;
    }

    public float WearRate(String vehicleName) {
        Float wearRate = 0f;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                wearRate = vehicles.get(i).WearRate();
            }
        }
        return wearRate;
    }

}
