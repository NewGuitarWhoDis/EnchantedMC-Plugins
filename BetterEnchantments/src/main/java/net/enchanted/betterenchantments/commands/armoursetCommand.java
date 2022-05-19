package net.enchanted.betterenchantments.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class armoursetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta helmet_meta = helmet.getItemMeta();
        helmet_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        helmet_meta.addEnchant(Enchantment.PROTECTION_FIRE, 4, false);
        helmet_meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, false);
        helmet_meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
        helmet_meta.addEnchant(Enchantment.MENDING, 1, false);
        helmet_meta.addEnchant(Enchantment.WATER_WORKER, 1, false);
        helmet_meta.addEnchant(Enchantment.OXYGEN, 3, false);
        helmet_meta.addEnchant(Enchantment.THORNS, 3, false);
        helmet_meta.addEnchant(Enchantment.DURABILITY, 3, false);
        helmet.setItemMeta(helmet_meta);

        ItemStack Chest = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta chest_meta = Chest.getItemMeta();
        chest_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        chest_meta.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
        chest_meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
        chest_meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        chest_meta.addEnchant(Enchantment.MENDING, 1, true);
        chest_meta.addEnchant(Enchantment.THORNS, 3, true);
        chest_meta.addEnchant(Enchantment.DURABILITY, 3, true);
        Chest.setItemMeta(chest_meta);

        ItemStack Legs = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta legs_meta = Legs.getItemMeta();
        legs_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        legs_meta.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
        legs_meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
        legs_meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        legs_meta.addEnchant(Enchantment.MENDING, 1, true);
        legs_meta.addEnchant(Enchantment.THORNS, 3, true);
        legs_meta.addEnchant(Enchantment.DURABILITY, 3, true);
        Legs.setItemMeta(legs_meta);

        ItemStack Boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta boots_meta = Boots.getItemMeta();
        boots_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        boots_meta.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
        boots_meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
        boots_meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        boots_meta.addEnchant(Enchantment.MENDING, 1, true);
        boots_meta.addEnchant(Enchantment.THORNS, 3, true);
        boots_meta.addEnchant(Enchantment.DURABILITY, 3, true);
        boots_meta.addEnchant(Enchantment.SOUL_SPEED, 3, true);
        boots_meta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
        boots_meta.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
        Boots.setItemMeta(boots_meta);



        if((player.hasPermission("betterenchantments.godarmour"))) {
            inventory.addItem(helmet);
            inventory.addItem(Chest);
            inventory.addItem(Legs);
            inventory.addItem(Boots);
        }


        return true;
    }
}
