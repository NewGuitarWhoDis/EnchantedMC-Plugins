package net.enchanted.enchanted.commands.racesubcommand;

import net.enchanted.enchanted.Enchanted;
import net.enchanted.enchanted.commands.SubCommand;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class StartCommand extends SubCommand {

    public HashMap<String, ArmorStand> parts = new HashMap<>();

    int loopCounter1 = 1;


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

            Collection<? extends Player> allPlayers = Bukkit.getOnlinePlayers();

            for (Player p : allPlayers) {
                player.sendMessage("started looping " + p.getName());
                Player p1 = (Player) p;
                    player.sendMessage("added " + p1.getName());
                    p1.sendMessage(ChatColor.GREEN + "The race is starting in...");
                    startRacing(p1);
                }

        } else if (args.length == 1) {
            player.sendMessage(ChatColor.RED + "Please provide a name for the race. Usage: /race start <race name>");
        } else {
            player.sendMessage(ChatColor.RED + "Invalid Arguments. Usage: /race start <race name>");
        }

    }

    private void startRacing(Player p) {

        Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
            p.sendTitle(ChatColor.GREEN + "5", ChatColor.GREEN + "", 10, 10, 20);

            Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                p.sendTitle(ChatColor.GREEN + "4", ChatColor.GREEN + "", 10, 10, 20);

                Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                    p.sendTitle(ChatColor.GREEN + "3", ChatColor.GREEN + "", 10, 10, 20);

                    Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                        p.sendTitle(ChatColor.GREEN + "2", ChatColor.GREEN + "", 10, 10, 20);

                        Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                            p.sendTitle(ChatColor.GREEN + "1", ChatColor.GREEN + "", 10, 10, 20);

                            Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                                p.sendTitle(ChatColor.GREEN + "GO", ChatColor.GREEN + "", 10, 10, 20);

                                p.setGameMode(GameMode.SURVIVAL);
                                World world = p.getWorld();
                                Location trackposition1 = new Location( world, 10134, -60, 10119, 90, 0);
                                Location trackposition2 = new Location( world, 10141, -60, 10126, 90, 0);
                                Location trackposition3 = new Location( world, 10148, -60, 10119, 90, 0);
                                Location trackposition4 = new Location( world, 10155, -60, 10126, 90, 0);
                                Location trackposition5 = new Location( world, 10162, -60, 10119, 90, 0);
                                Location trackposition6 = new Location( world, 10169, -60, 10126, 90, 0);
                                Location trackposition7 = new Location( world, 10176, -60, 10119, 90, 0);
                                Location trackposition8 = new Location( world, 10183, -60, 10126, 90, 0);
                                Location trackposition9 = new Location( world, 10190, -60, 10119, 90, 0);
                                Location trackposition10 = new Location( world, 10197, -60, 10126, 90, 0);

                                if (loopCounter1 == 1) {
                                    ArmorStand body = getNewArmorStand(trackposition1, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                } else if (loopCounter1 == 2) {
                                    ArmorStand body = getNewArmorStand(trackposition2, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                } else if (loopCounter1 == 3) {
                                    ArmorStand body = getNewArmorStand(trackposition3, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                } else if (loopCounter1 == 4) {
                                    ArmorStand body = getNewArmorStand(trackposition4, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                } else if (loopCounter1 == 5) {
                                    ArmorStand body = getNewArmorStand(trackposition5, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                } else if (loopCounter1 == 6) {
                                    ArmorStand body = getNewArmorStand(trackposition6, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                } else if (loopCounter1 == 7) {
                                    ArmorStand body = getNewArmorStand(trackposition7, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                } else if (loopCounter1 == 8) {
                                    ArmorStand body = getNewArmorStand(trackposition8, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                } else if (loopCounter1 == 9) {
                                    ArmorStand body = getNewArmorStand(trackposition9, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                } else if (loopCounter1 == 10) {
                                    ArmorStand body = getNewArmorStand(trackposition10, false, true);
                                    ItemStack bodytexture = new ItemStack(Material.PAPER);
                                    ItemMeta bodymeta = bodytexture.getItemMeta();
                                    bodymeta.setCustomModelData(10265);
                                    bodytexture.setItemMeta(bodymeta);
                                    body.setHelmet(bodytexture);
                                    body.setCustomName(p.getUniqueId().toString());
                                    parts.put("body", body);
                                    body.addPassenger(p);
                                }

                                loopCounter1++;


                            }, 20L);
                        }, 20L);
                    }, 20L);

                }, 20L);
            }, 20L);
        }, 100L);

    }

    private ArmorStand getNewArmorStand(Location location, boolean visible, boolean mini) {
        ArmorStand as = location.getWorld().spawn(location, ArmorStand.class);

        as.setBasePlate(false);
        as.setArms(true);
        as.setVisible(true);
        as.setInvulnerable(false);
        as.setCanPickupItems(false);
        as.setGravity(true);
        as.setSmall(mini);

        return as;
    }

}

