package net.enchanted.enchanted.commands.racesubcommand;

import net.enchanted.enchanted.Enchanted;
import net.enchanted.enchanted.commands.SubCommand;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.HashMap;

public class TimeTrialCommand extends SubCommand {

    public HashMap<String, ArmorStand> parts = new HashMap<>();
    private HashMap<Player, Integer> lap = new HashMap<Player, Integer>();
    public Boolean CutScene = false;
    boolean raceTimer = false;

    @Override
    public String getName() {
        return "timetrial";
    }

    @Override
    public String getDescription() {
        return "do a race time trial";
    }

    @Override
    public String getSyntax() {
        return "/race timetrial";
    }

    @Override
    public void perform(Player player, String[] args) throws IOException, InterruptedException {

        CutScene = true;
        ArmorStand body = getNewArmorStand(new Location(player.getWorld(), 10133, -60, 10119.5, 90, 0), false, true);
        ItemStack bodytexture = new ItemStack(Material.PAPER);
        ItemMeta bodymeta = bodytexture.getItemMeta();
        bodymeta.setCustomModelData(10267);
        bodytexture.setItemMeta(bodymeta);
        body.setHelmet(bodytexture);
        body.setCustomName(player.getUniqueId().toString());
        parts.put("body", body);

        player.setGameMode(GameMode.SPECTATOR);
        World world = player.getWorld();
        Location track = new Location( world, 9896, -8, 10199, -141, 32);
        Location startline = new Location( world, 10047, -54, 10122, 90, 6);
        Location light1 = new Location( world, 10127, -52, 10127, 0, 0);
        Location light2 = new Location( world, 10127, -52, 10125, 0, 0);
        Location light3 = new Location( world, 10127, -52, 10123, 0, 0);
        Location light4 = new Location( world, 10127, -52, 10121, 0, 0);
        Location light5 = new Location( world, 10127, -52, 10119, 0, 0);

        light1.getBlock().setType(Material.AIR);
        light2.getBlock().setType(Material.AIR);
        light3.getBlock().setType(Material.AIR);
        light4.getBlock().setType(Material.AIR);
        light5.getBlock().setType(Material.AIR);

        player.teleport(track);
        player.sendTitle(ChatColor.GREEN + "Time Trial", ChatColor.GREEN + "Starts In...", 10, 50, 20);


        Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
            player.teleport(startline);
            player.sendTitle(ChatColor.GREEN + "5", ChatColor.GREEN + "", 10, 10, 20);
            light1.getBlock().setType(Material.REDSTONE_BLOCK);

            Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                player.sendTitle(ChatColor.GREEN + "4", ChatColor.GREEN + "", 10, 10, 20);
                light2.getBlock().setType(Material.REDSTONE_BLOCK);

                Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                    player.sendTitle(ChatColor.GREEN + "3", ChatColor.GREEN + "", 10, 10, 20);
                    light3.getBlock().setType(Material.REDSTONE_BLOCK);

                    Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                        player.sendTitle(ChatColor.GREEN + "2", ChatColor.GREEN + "", 10, 10, 20);
                        light4.getBlock().setType(Material.REDSTONE_BLOCK);

                        Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                            player.sendTitle(ChatColor.GREEN + "1", ChatColor.GREEN + "", 10, 10, 20);
                            light5.getBlock().setType(Material.REDSTONE_BLOCK);

                            Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                                player.sendTitle(ChatColor.GREEN + "GO", ChatColor.GREEN + "", 10, 10, 20);

                                light1.getBlock().setType(Material.AIR);
                                light2.getBlock().setType(Material.AIR);
                                light3.getBlock().setType(Material.AIR);
                                light4.getBlock().setType(Material.AIR);
                                light5.getBlock().setType(Material.AIR);

                                player.setGameMode(GameMode.SURVIVAL);
                                CutScene = false;
                                body.addPassenger(player);

                                int oldLevel = player.getLevel();
                                boolean raceTimer = true;
                                player.setExp(0);
                                player.setLevel(0);
                                lap.put(player, 0);

                                startTimer(player);

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

    public int getLap(Player player) {
        int lap = this.lap.get(player);
        return lap;
    }

    public void setLap(Player player, int lap) {
        this.lap.put(player, lap);
    }

    int taskID;
    public void startTimer(Player player) {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Enchanted.instance, new Runnable() {

            @Override
            public void run() {
                player.setExp((float) (player.getExp() + 0.01));
                player.setLevel(player.getLevel() + 1);
            }
        }, 0L, 20L);
    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

}
