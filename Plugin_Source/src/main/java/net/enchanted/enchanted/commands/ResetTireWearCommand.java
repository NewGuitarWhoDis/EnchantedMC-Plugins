package net.enchanted.enchanted.commands;

import net.enchanted.enchanted.managers.VehicleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetTireWearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        VehicleManager vehicleManager = new VehicleManager();
        vehicleManager.setTireWear(1, player);

        return true;
    }
}
