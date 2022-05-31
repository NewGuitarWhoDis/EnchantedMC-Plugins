package net.enchanted.enchanted;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
import net.enchanted.enchanted.commands.*;
import net.enchanted.enchanted.commands.racesubcommand.TimeTrialCommand;
import net.enchanted.enchanted.listeners.*;
import net.enchanted.enchanted.managers.VehicleManager;
import net.enchanted.enchanted.managers.vehicle.Speed;
import net.enchanted.enchanted.managers.vehicle.TireWear;
import net.enchanted.enchanted.managers.vehicle.VehiclePerameters;
import net.minecraft.server.level.ServerPlayer;
import org.apache.commons.lang.Validate;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.abs;

public final class Enchanted extends JavaPlugin {

    public static Enchanted instance;

    //Used to keep our NPCs to be accessed in other classes
    private List<ServerPlayer> npcs = new ArrayList<>();
    private static Enchanted plugin;

    VehicleManager vehicleManager = new VehicleManager();
    TimeTrialCommand timeTrialCommand = new TimeTrialCommand();
    Speed speed = new Speed();
    TireWear tireWear = new TireWear();
    VehiclePerameters vehiclePerameters = new VehiclePerameters();

    @Override
    public void onEnable() {

        instance = this;
        plugin = this;
        HashMap<Player, Float> forwardVelocity = new HashMap<Player, Float>();
        final HashMap<Player, Float>[] Wear = new HashMap[]{new HashMap<Player, Float>()};

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        this.getCommand("createvehicle").setExecutor(new VehicleCreateCommand());
        this.getCommand("createnpc").setExecutor(new CreateNPCCommand());
        this.getCommand("resettires").setExecutor(new ResetTireWearCommand());
        this.getCommand("race").setExecutor(new RaceCommand());
        this.getCommand("safetycar").setExecutor(new SafetyCarCommand());
        this.getCommand("vehicle").setExecutor(new VehicleCommand());

        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new PingListener(), this);
        getServer().getPluginManager().registerEvents(new VehicleClickListener(), this);
        getServer().getPluginManager().registerEvents(new MovementListener(), this);

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Client.STEER_VEHICLE) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                Player player = event.getPlayer();
                PacketContainer packet = event.getPacket();

                if (player.getVehicle() == null) return;
                final Entity vehicle = player.getVehicle();

                if (!(vehicle instanceof ArmorStand)) return;
                if (vehicle.getCustomName().contains("passenger")) return;

                if (forwardVelocity.get(player) == null) {
                    forwardVelocity.put(player, 0F);
                }
                if (Wear[0].get(player) == null) {
                    Wear[0].put(player, 1F);
                }

                Location vehicleLocation = vehicle.getLocation();
                String surface = vehicleLocation.getBlock().getRelative(BlockFace.DOWN).getType().name();
                String front = vehicleLocation.getBlock().getRelative(BlockFace.SELF).getType().name();

                //Get Packets
                float rotate, forwardpacket; // so we can access them outside the scope of our try-catch block
                try {
                    rotate = event.getPacket().getFloat().read(0);
                    forwardpacket = event.getPacket().getFloat().read(1);

                } catch (FieldAccessException ex) {
                    ex.printStackTrace();
                    return;
                }

                String name = vehicle.getCustomName();

                vehiclePerameters = new VehiclePerameters();
                float TopSpeed = vehiclePerameters.topSpeed(name);
                float StartAcceleration = vehiclePerameters.startAcceleration(name);
                float Jerk = vehiclePerameters.jerk(name);
                float BreakForce = vehiclePerameters.breakForce(name);
                float Drag = vehiclePerameters.Drag(name);
                float WearRate = vehiclePerameters.WearRate(name);

                if (!(rotate == 0)) {

                    Wear[0].put(player, Wear[0].get(player) + (WearRate * (speed.getSpeed(player) / 3)));
                    tireWear.setTireWear(Wear[0].get(player), player);
                    Drag = (float) (Drag + (0.01));
                }

                if (surface == "GRAVEL") {
                    Drag = 0.12F;
                    Wear[0].put(player, (float) (Wear[0].get(player) + ((WearRate + 0.0002f) * (Math.pow(speed.getSpeed(player), 2) / 10))));
                    tireWear.setTireWear(Wear[0].get(player), player);
                    Drag = (float) (Drag + 0.06);
                }
                if (surface == "GRAY_CONCRETE") {
                    Wear[0].put(player, tireWear.getTireWear(player));
                    if (Wear[0].get(player) > 1) {
                        Wear[0].put(player, Wear[0].get(player) - 0.01F);
                        tireWear.setTireWear(Wear[0].get(player), player);
                    } else if (Wear[0].get(player) <= 1) {
                        tireWear.setTireWear(1, player);
                    }
                    Drag = 0.11F;
                }

                if (surface == "GRASS_BLOCK") {
                    Drag = 0.11F;
                }

                if (forwardpacket == 0.0) {
                    if (forwardVelocity.get(player) < 0) {
                        forwardVelocity.put(player, (float) (forwardVelocity.get(player) + Drag));
                        speed.setSpeed(forwardVelocity.get(player), player);
                    } else if (forwardVelocity.get(player) > 0) {
                        forwardVelocity.put(player, (float) (forwardVelocity.get(player) - Drag));
                        speed.setSpeed(forwardVelocity.get(player), player);
                    }
                    if (forwardVelocity.get(player) < 0.02 && forwardVelocity.get(player) > 0) {
                        forwardVelocity.put(player, 0F);
                        speed.setSpeed(forwardVelocity.get(player), player);
                    } else if (forwardVelocity.get(player) > 0.02 && forwardVelocity.get(player) < 0) {
                        forwardVelocity.put(player, 0F);
                        speed.setSpeed(forwardVelocity.get(player), player);
                    }
                } else if (forwardpacket > 0) {
                    // Top Speed
                    if (!(forwardVelocity.get(player) >= TopSpeed)) {
                        // Acceleration
                        forwardVelocity.put(player, (float) (forwardVelocity.get(player) + StartAcceleration / Wear[0].get(player) * Math.pow((Wear[0].get(player) / Jerk), -forwardVelocity.get(player))));
                        forwardVelocity.put(player, (float) (forwardVelocity.get(player) - Drag * forwardVelocity.get(player)));
                        speed.setSpeed(forwardVelocity.get(player), player);
                    }
                } else if (forwardpacket < 0) {
                    if (forwardVelocity.get(player) > 0) {
                        forwardVelocity.put(player, (float) (forwardVelocity.get(player) - BreakForce));
                        speed.setSpeed(forwardVelocity.get(player), player);
                    } else {
                        if (!(forwardVelocity.get(player) <= -0.2)) {
                            forwardVelocity.put(player, (float) (forwardVelocity.get(player) - 0.005));
                            speed.setSpeed(forwardVelocity.get(player), player);
                        }
                    }
                }

                vehicleManager.move(player, rotate, Wear[0].get(player), Drag, surface, vehicle);
//                drawLine(new Location(Bukkit.getWorld("flatroom"), 10433, -59.8, 10128), new Location(Bukkit.getWorld("flatroom"), 9975, -59.8, 10117), 0.1);
//                drawLine(new Location(Bukkit.getWorld("flatroom"), 9975, -59.8, 10117), new Location(Bukkit.getWorld("flatroom"), 9973, -59.8, 10118), new Location(Bukkit.getWorld("flatroom"), 9973, -59.8, 10118), 0.1);
            }
        });
    }

    @Override
    public void onDisable() {
    }

    public List<ServerPlayer> getNpcs() {
        return npcs;
    }

    public static Enchanted getPlugin() {
        return plugin;
    }

    public void saveVehicleInventory(Entity vehicle, Inventory inv) throws IOException {
        File f = new File(this.getDataFolder().getAbsolutePath(), vehicle.getCustomName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("inventory.content", inv.getContents());
        c.save(f);
    }
    public void savePlayerInventory(Player player) throws IOException {
        File f = new File(this.getDataFolder().getAbsolutePath(), player.getDisplayName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        Inventory inv = player.getInventory();
        c.set("inventory.content", inv.getContents());
        c.save(f);
    }

    @SuppressWarnings("unchecked")
    public void restoreInventory(Entity vehicle, Inventory inv) throws IOException {
        File f = new File(this.getDataFolder().getAbsolutePath(), vehicle.getCustomName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        inv.setContents(content);
    }

    public void drawLine(Location point1, Location point2, Location point3, double space) {
        //gets World
        World world = point1.getWorld();
        //gets point 2 and checks if it is in the same world
        Validate.isTrue(point2.getWorld().equals(world), "Lines cannot be in different worlds!");

        //gets distance
        double distance = point1.distance(point2);
        //point 1
        Vector p1 = point1.toVector();
        //point 2
        Vector p2 = point2.toVector();

        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double length = 0;
        for (; length < distance; p1.add(vector)) {
            world.spawnParticle(Particle.CURRENT_DOWN, p1.getX(), p1.getY(), p1.getZ(), 1);
            length += space;
        }
    }

}
