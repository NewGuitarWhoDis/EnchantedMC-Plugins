package net.enchanted.enchanted.commands.vehiclesubcommand;

import net.enchanted.enchanted.commands.SubCommand;
import net.enchanted.enchanted.managers.VehicleManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SummonCommand extends SubCommand {

    VehicleManager vehicleManager = new VehicleManager();
    @Override
    public String getName() {
        return "summon";
    }

    @Override
    public String getDescription() {
        return "Summons a vehicle.";
    }

    @Override
    public String getSyntax() {
        return "/vehicle summon <vehicle>";
    }

    @Override
    public void perform(Player player, String[] args) throws IOException, InterruptedException {

        if (args.length == 2) {
            new VehicleManager().summonVehicle(player, args[1]);
            player.sendMessage(ChatColor.GREEN + args[1] + " summoned at the dealership");
        } else {
            player.sendMessage(ChatColor.RED + "Invalid argument. Usage: /vehicle summon <vehicle>");
        }

    }
}
