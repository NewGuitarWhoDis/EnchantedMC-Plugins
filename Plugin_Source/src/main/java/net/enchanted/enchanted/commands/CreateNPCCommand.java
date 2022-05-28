package net.enchanted.enchanted.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import net.enchanted.enchanted.Enchanted;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class CreateNPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p){

            CraftPlayer craftPlayer = (CraftPlayer) p;

            //NMS representation of the MC server
            MinecraftServer server = craftPlayer.getHandle().getServer();
            //NMS representation of the MC world
            ServerLevel level = craftPlayer.getHandle().getLevel();

            //Create a new NPC named Billy Bob, with a new GameProfile to uniquely identify them
            ServerPlayer npc = new ServerPlayer(server, level, new GameProfile(UUID.randomUUID(), "Salesman"));
            //Set their position. They will be here when we call the packets below to spawn them
            npc.setPos(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());

            //set the npc skin
            /* Retrieving skins:
             *  - First thing to understand is that skins have to be "signed" by Mojang. Each skin has two parts: texture and signature
             *  - You can retrieve this data from:
             *    https://sessionserver.mojang.com/session/minecraft/profile/UUID?unsigned=false
             *  - UUID = the uuid of a player without the dashes
             *  - UUID data can only be retrieved every 60 seconds (if same uuid)
             */
            String signature = "so2Y3BlhntbkPSZQphqDuWbQcTst9Q7JYcOZMBQkPC0e3T6UvDRP2mHMtfIfEruaoPtOVIiAo2T6myy9WoXt2w4QIeqNkoVygMiTywc7a81cpHWHrEz2dZ8XM24YClRvKzO7DDTmZFR6IiYf1bUHX0aNrg/sphxkwpLGOMeQ2OoDBQnQ5N9/YQeq3Zm62QLFL5amEj1KglfDQU7Ye/BJrBSWckqKVeD8I7bsvsQnSZHnHwid0QlmAAZUrXmCwDQ42XbJRT//GSOenPR8DvZ5OaPcZEVUvdc8MEbL/5O0bwlod+scsSmfoW4kRdtxNJ6+jf0HenqsC0lCmp4VCvuS3TkZenUhpQsjiuMEnRoUscr7VQfCCQ8IRo4OEHgKanxa8+q9GEsMTGEdV9KqG731e2NOHrjiZtqDpwvCiEHBh+kdmQEdZ0Pr5tPKkrRbm6RO3YzO1+5c+5TC61l9/EZTtbrZTqadD8u0dLdl06JrggNXrzgRLoSf4ocJzjL1QzQmM/J4H7fa5JpNgszUJdOTgKfyVkDrkupfspCL3zaR0b4Lrgww0cFjLx1iyG3z3Giv+VJl/xlC56sm8h4x/mTkZF6pz+e0sJsjCu1N3GbKmIz/j3aJpjc0bkpPk6uWi9aJgf76Li2xl4gEzJG0Q8VwTB1kGVmwCLOMZVbnaNyVtXU=";
            String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY1MzQ5MTc5NzI2NiwKICAicHJvZmlsZUlkIiA6ICJiNjhlODc4ZWMzZjk0OTFhODM4NjU3OTcyMzg4N2I5ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJfVGF6bzEzMzciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzc1MjdkMzA3ODkwYjRhNjJjODNjZTVkNmY2YjI4NTBiZjM1ZjIyMmQ3NGZiMjdmZTNkMDY5YjAxYzIwY2FlNiIKICAgIH0KICB9Cn0=";
            npc.getGameProfile().getProperties().put("textures", new Property("textures", texture, signature));

            //Send the packets to artificially spawn this entity, only the client we are sending the packet to will know of it's existence
            ServerGamePacketListenerImpl ps = craftPlayer.getHandle().connection;
            //Spawn Player packet
            ps.send(new ClientboundAddPlayerPacket(npc));

            //add it to the list of NPCs so we can access it in our movement listener
            Enchanted.getPlugin().getNpcs().add(npc);

            p.sendMessage("Spawning a NPC");
        }

        return true;
    }
}
