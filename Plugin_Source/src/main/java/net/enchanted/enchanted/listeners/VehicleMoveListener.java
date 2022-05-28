package net.enchanted.enchanted.listeners;


import net.enchanted.enchanted.commands.subcommand.TimeTrialCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class VehicleMoveListener implements Listener {

    TimeTrialCommand timeTrialCommand = new TimeTrialCommand();

    public void onVehicleMove(VehicleMoveEvent event) {
        Entity Vehicle = event.getVehicle();
        Location vehicleLocation = Vehicle.getLocation();
        String surface = vehicleLocation.getBlock().getRelative(BlockFace.DOWN).getType().name();
        if (!(Vehicle.getPassengers().size() > 0)) return;
        Player player = (Player) Vehicle.getPassengers().get(0);
        player.sendMessage(ChatColor.GREEN + "You are now on a " + surface + " surface.");
        if (!(player instanceof Player)) return;
        if (surface == "QUARTZ_PILLAR") {
            int lap = timeTrialCommand.getLap(player);
            player.sendMessage(ChatColor.GREEN + "You are on lap " + lap);
            if (lap == 1) {
//                        timeTrialCommand.stopTimer();
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Race Complete!");
                player.sendMessage(ChatColor.GREEN + "Your time was: " + ChatColor.AQUA + player.getLevel());
            } else {
                int newlap = timeTrialCommand.getLap(player) + 1;
                timeTrialCommand.setLap(player, newlap);
                player.sendMessage(ChatColor.GREEN + "Lap " + lap + " completed!");
            }
        }
    }
}
