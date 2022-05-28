package net.enchanted.enchanted.commands.subcommand;

import net.enchanted.enchanted.Enchanted;
import net.enchanted.enchanted.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateCommand extends SubCommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Creates a new F1 race";
    }

    @Override
    public String getSyntax() {
        return "/race create <race name>";
    }

    @Override
    public void perform(Player player, String[] args) throws IOException {

        if(args.length > 1) {

            File tempFile = new File(Enchanted.instance.getDataFolder().getAbsolutePath(), "/races/" + args[1] + ".yml");
            boolean exists = tempFile.exists();
            if (exists == true) {
                player.sendMessage(ChatColor.RED + "A race with the name " + args[1] + " already exists.");
                return;
            }

            File f = new File(Enchanted.instance.getDataFolder().getAbsolutePath(), "/races/" + args[1] + ".yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            c.set("Name", args[1]);
            ArrayList<Player> invited = new ArrayList<Player>();
            invited.add(player);
            c.set(String.valueOf(invited), true);
            c.save(f);

            player.sendMessage("");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Created New Race: " + ChatColor.AQUA + "" + ChatColor.BOLD + args[1]);
            player.sendMessage("");
            player.sendMessage(ChatColor.GREEN + "To invite players to " + args[1] + " type:");
            player.sendMessage(ChatColor.AQUA + "/race invite <player name> " + args[1]);
            player.sendMessage("");
        } else if (args.length == 1) {
            player.sendMessage(ChatColor.RED + "Please provide a name for the race. Usage: /race create <race name>");
        } else {
            player.sendMessage(ChatColor.RED + "Invalid Arguments. Usage: /race create <race name>");
        }

    }
}
