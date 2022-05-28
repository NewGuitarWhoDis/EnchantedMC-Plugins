package net.enchanted.enchanted.managers.vehicle;

import net.enchanted.enchanted.managers.VehicleManager;
import net.enchanted.enchanted.managers.vehicle.vehicles.Racecar;

import java.util.ArrayList;

public class VehiclePerameters {

    public ArrayList<VehicleInstance> vehicles = new ArrayList<>();

    public VehiclePerameters() {
        vehicles.add(new Racecar());
    }

    public void topSpeed(String vehicleName) {
        for( int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                vehicles.get(i).TopSpeed();
            }
        }
    }

    public void startAcceleration(String vehicleName) {
        for( int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                vehicles.get(i).StartAcceleration();
            }
        }
    }

    public void jerk(String vehicleName) {
        for( int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                vehicles.get(i).Jerk();
            }
        }
    }

    public void breakForce(String vehicleName) {
        for( int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                vehicles.get(i).BreakForce();
            }
        }
    }

    public void Drag(String vehicleName) {
        for( int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                vehicles.get(i).Drag();
            }
        }
    }

    public void WearRate(String vehicleName) {
        for( int i = 0; i < vehicles.size(); i++) {
            if (vehicleName.equalsIgnoreCase(vehicles.get(i).getName())) {
                vehicles.get(i).WearRate();
            }
        }
    }

}
