package net.enchanted.enchanted.commands.subcommand;

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
        ArmorStand body = getNewArmorStand(new Location(player.getWorld(), 10029, -60, 10119.5, 90, 0), false, true);
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
        Location startline = new Location( world, 10056, -54, 10122, 90, 6);

        player.teleport(track);
        player.sendTitle(ChatColor.GREEN + "Time Trial", ChatColor.GREEN + "Starts In...", 10, 50, 20);


        Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
            player.teleport(startline);
            player.sendTitle(ChatColor.GREEN + "3", ChatColor.GREEN + "", 10, 10, 20);

            Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                player.sendTitle(ChatColor.GREEN + "2", ChatColor.GREEN + "", 10, 10, 20);

                Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                    player.sendTitle(ChatColor.GREEN + "1", ChatColor.GREEN + "", 10, 10, 20);

                    Bukkit.getScheduler().runTaskLater(Enchanted.instance, () -> {
                        player.sendTitle(ChatColor.GREEN + "GO", ChatColor.GREEN + "", 10, 10, 20);

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
