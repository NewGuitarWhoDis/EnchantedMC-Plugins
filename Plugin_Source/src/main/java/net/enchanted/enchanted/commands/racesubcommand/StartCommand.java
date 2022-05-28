package net.enchanted.enchanted.commands.racesubcommand;

import net.enchanted.enchanted.Enchanted;
import net.enchanted.enchanted.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StartCommand extends SubCommand {
    @Override
    public String getName() {
        return "Start";
    }

    @Override
    public String getDescription() {
        return "Starts the race";
    }

    @Override
    public String getSyntax() {
        return "/race start <race name>";
    }

    @Override
    public void perform(Player player, String[] args) throws IOException {

        if(args.length > 1) {

            File tempFile = new File(Enchanted.instance.getDataFolder().getAbsolutePath(), "/races/" + args[1] + ".yml");
            boolean exists = tempFile.exists();
            if (exists == false) {
                player.sendMessage(ChatColor.RED + "A race with the name " + args[2] + " does not exists.");
                player.sendMessage(ChatColor.RED + "Create a race with the command:");
                player.sendMessage(ChatColor.RED + "/race create <race name>");
                return;
            }

            File f = new File(Enchanted.instance.getDataFolder().getAbsolutePath(), "/races/" + args[1] + ".yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            ArrayList<Player> invited = new ArrayList<Player>();
            invited.add((Player) c.get(String.valueOf(invited)));

            for (Player i : invited) {
                Player p = (Player) i;
                p.sendMessage("Race is Starting!");
            }

        } else if (args.length == 1) {
            player.sendMessage(ChatColor.RED + "Please provide a name for the race. Usage: /race start <race name>");
        } else {
            player.sendMessage(ChatColor.RED + "Invalid Arguments. Usage: /race start <race name>");
        }

    }
}
