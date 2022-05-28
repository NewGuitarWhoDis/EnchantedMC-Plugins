package net.enchanted.enchanted.commands;

import net.enchanted.enchanted.managers.CreateGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<Material> materialList = new ArrayList<Material>(Arrays.asList( Material.COCOA, Material.ELYTRA, Material.LAVA, Material.ACACIA_LOG ) );

        new CreateGUI("Custom Items", 3, materialList);

        return true;
    }

}
