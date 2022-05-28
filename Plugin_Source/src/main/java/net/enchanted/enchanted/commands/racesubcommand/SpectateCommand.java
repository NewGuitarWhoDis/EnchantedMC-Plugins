package net.enchanted.enchanted.commands.racesubcommand;

import net.enchanted.enchanted.Enchanted;
import net.enchanted.enchanted.commands.SubCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class SpectateCommand extends SubCommand {
    boolean IsSpectating = false;

    @Override
    public String getName() {
        return "spectate";
    }

    @Override
    public String getDescription() {
        return "Spectate a race";
    }

    @Override
    public String getSyntax() {
        return "/race spectate";
    }

    @Override
    public void perform(Player player, String[] args) throws IOException, InterruptedException {

        setSpectating(player, true);

        Enchanted.instance.savePlayerInventory(player);
        player.getInventory().clear();

        player.setFlying(true);

        Location view1 = new Location(player.getWorld(), -9940, -49, 10134);

        player.teleport(view1);

        player.getInventory().setItem(0, new ItemStack(Material.COMPASS));

    }

    public boolean isSpectating(Player player) {
        return IsSpectating;
    }

    public void setSpectating(Player player, boolean spectating) {
        IsSpectating = spectating;
    }
}
