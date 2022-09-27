package net.cronge.crongesmod.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EightBallItem extends Item {
    public EightBallItem(Settings settings) {
        super(settings);
        this.instanceofRandom = Random.create();
    }

    private final Random instanceofRandom;

    private final List<StatusEffect> buffs = List.of(
            StatusEffects.HASTE,
            StatusEffects.JUMP_BOOST,
            StatusEffects.FIRE_RESISTANCE,
            StatusEffects.WATER_BREATHING,
            StatusEffects.INVISIBILITY,
            StatusEffects.SATURATION,
            StatusEffects.NIGHT_VISION,
            StatusEffects.HEALTH_BOOST
    );

    private final List<StatusEffect> debuffs = List.of(
            StatusEffects.SLOWNESS,
            StatusEffects.MINING_FATIGUE,
            StatusEffects.NAUSEA,
            StatusEffects.HUNGER,
            StatusEffects.POISON
    );

    private final List<StatusEffect> strongBuffs = List.of(
            StatusEffects.STRENGTH,
            StatusEffects.SPEED,
            StatusEffects.RESISTANCE,
            StatusEffects.REGENERATION,
            StatusEffects.ABSORPTION
    );
    private final List<StatusEffect> strongDebuffs = List.of(
            StatusEffects.DARKNESS,
            StatusEffects.WITHER,
            StatusEffects.WEAKNESS,
            StatusEffects.BLINDNESS
    );

    // TODO: update lang file

    private static final String MESSAGE_ROLLED_NUMBER = "message.crongesmod.eight_ball.onUse";
    private static final String MESSAGE_ROLLED_WHEN_8 = "message.crongesmod.eight_ball.onUse_when_8";

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.crongesmod.eight_ball.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverWorld && hand == Hand.MAIN_HAND) {
            user.clearStatusEffects();

            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();

            int eightBallRNG = instanceofRandom.nextBetween(1, 8);

            int smallAmplifierRNG = instanceofRandom.nextBetween(0, 1);
            int bigAmplifierRNG = instanceofRandom.nextBetween(2, 3);

            outputMessage(user, serverWorld, x, y, z, eightBallRNG);
            user.getItemCooldownManager().set(this, 20);

            debuff(user, smallAmplifierRNG, eightBallRNG);
            buff(user, smallAmplifierRNG, bigAmplifierRNG, eightBallRNG);
        }
        return super.use(world, user, hand);
    }

    private void outputMessage(PlayerEntity player, ServerWorld serverWorld, double x, double y, double z, int rolled) {
        if (is8Rolled(rolled)) {
            player.sendMessage(Text.translatable(MESSAGE_ROLLED_WHEN_8, rolled));

            spawnParticles(player, serverWorld, x, y, z);

            serverWorld.playSound(null, x, y, z, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.AMBIENT, 1.5f, -1f);
            serverWorld.playSound(null, x, y, z, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.AMBIENT, 2f, 1f);
        } else {
            player.sendMessage(Text.translatable(MESSAGE_ROLLED_NUMBER, rolled));

            serverWorld.playSound(null, x, y, z, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.AMBIENT, 1.5f, 0.5f);
        }
    }

    private void spawnParticles(PlayerEntity player, ServerWorld serverWorld, double x, double y, double z) {
        // TODO: make a colored particle
        serverWorld.spawnParticles(((ServerPlayerEntity) player), ParticleTypes.INSTANT_EFFECT, false, x + 0.3, y + 1, z, 5, 0, 1, 0, 0);
        serverWorld.spawnParticles(((ServerPlayerEntity) player), ParticleTypes.INSTANT_EFFECT, false, x - 0.3, y + 1, z, 5, 0, 1, 0, 0);
        serverWorld.spawnParticles(((ServerPlayerEntity) player), ParticleTypes.INSTANT_EFFECT, false, x, y + 1, z + 0.3, 5, 0, 1, 0, 0);
        serverWorld.spawnParticles(((ServerPlayerEntity) player), ParticleTypes.INSTANT_EFFECT, false, x, y + 1, z - 0.3, 5, 0, 1, 0, 0);
    }

    private boolean is8Rolled(int rolled) {
        return rolled == 8;
    }

    private void buff(PlayerEntity player, int smallAmplifier, int bigAmplifier, int rolled) {
        if (is8Rolled(rolled)) {
            player.addStatusEffect(new StatusEffectInstance(getRandomIndex(strongBuffs), 120, bigAmplifier, true, false, true));
        } else {
            player.addStatusEffect(new StatusEffectInstance(getRandomIndex(buffs), 160, smallAmplifier, true, false, true));
            player.addStatusEffect(new StatusEffectInstance(getRandomIndex(buffs), 160, smallAmplifier, true, false, true));
        }
    }

    private void debuff(PlayerEntity player, int smallAmplifier, int rolled) {
        if (is8Rolled(rolled)) {
            player.addStatusEffect(new StatusEffectInstance(getRandomIndex(strongDebuffs), 40, smallAmplifier, true, false, true));
        } else {
            player.addStatusEffect(new StatusEffectInstance(getRandomIndex(debuffs), 160, smallAmplifier, true, false, true));
        }
    }

    private StatusEffect getRandomIndex(List<StatusEffect> list) {
        return list.get(instanceofRandom.nextInt(list.size()));
    }
}
