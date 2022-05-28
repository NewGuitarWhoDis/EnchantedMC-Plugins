package net.enchanted.enchanted.commands;

import net.enchanted.enchanted.commands.racesubcommand.*;
import net.enchanted.enchanted.commands.vehiclesubcommand.SummonCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;

public class VehicleCommand implements CommandExecutor {

    private ArrayList<SubCommand> subcommands = new ArrayList<>();

    public VehicleCommand() {
        subcommands.add(new SummonCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                for( int i = 0; i < getSubcommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                        try {
                            getSubcommands().get(i).perform(player, args);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Invalid argument type /vehicle help.");
            }

        }

        return true;
    }

    public ArrayList<SubCommand> getSubcommands() {
        return subcommands;
    }
}
