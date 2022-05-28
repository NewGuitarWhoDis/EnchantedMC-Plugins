package net.enchanted.enchanted.commands.subcommand;

import net.enchanted.enchanted.Enchanted;
import net.enchanted.enchanted.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InviteCommand extends SubCommand {
    @Override
    public String getName() {
        return "invite";
    }

    @Override
    public String getDescription() {
        return "Invites players to F1 race";
    }

    @Override
    public String getSyntax() {
        return "/race invite <Player> <Race>";
    }

    @Override
    public void perform(Player player, String[] args) throws IOException {

        if(args.length == 3) {

            File tempFile = new File(Enchanted.instance.getDataFolder().getAbsolutePath(), "/races/" + args[2] + ".yml");
            boolean exists = tempFile.exists();
            if (exists == false) {
                player.sendMessage(ChatColor.RED + "A race with the name " + args[2] + " does not exists.");
                player.sendMessage(ChatColor.RED + "Create a race with the command:");
                player.sendMessage(ChatColor.RED + "/race create <race name>");
                return;
            }

            Player target = Bukkit.getPlayer(args[1]);

            File f = new File(Enchanted.instance.getDataFolder().getAbsolutePath(), "/races/" + args[2] + ".yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            ArrayList<Player> invited = new ArrayList<Player>();
            invited.add((Player) c.get(String.valueOf(invited)));
            invited.add(target);
            c.set(String.valueOf(invited), true);
            c.save(f);

            target.sendMessage("");
            target.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You Have been invited to the race: " + ChatColor.AQUA + "" + ChatColor.BOLD + args[2]);
            target.sendMessage("");

            player.sendMessage("");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Invited " + ChatColor.AQUA + "" + ChatColor.BOLD + args[1]);
            player.sendMessage("");
        } else if (args.length == 1) {
            player.sendMessage(ChatColor.RED + "Please provide a name for the player you want to invite. Usage: /race invite <player> <race>");
        } else if (args.length == 2) {
            player.sendMessage(ChatColor.RED + "Please specify a race. Usage: /race invite <player> <race>");
        } else {
            player.sendMessage(ChatColor.RED + "Invalid Arguments. Usage: /race invite <player> <race>");
        }

    }
}
