package net.cronge.crongesmod.event;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEntityHandler implements AttackEntityCallback {

    private static final String MESSAGE_ATTACK_ANIMAL_ENTITY = "message.crongesmod.hit_animal_entity";

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {

        if (!world.isClient && hand == Hand.MAIN_HAND && entity instanceof AnimalEntity) {
            player.sendMessage(Text.translatable(MESSAGE_ATTACK_ANIMAL_ENTITY, getPlayerName(player)));
        }
        return ActionResult.PASS;
    }

    private static String getPlayerName(PlayerEntity player) {
        return player.getName().getString();
    }
}
