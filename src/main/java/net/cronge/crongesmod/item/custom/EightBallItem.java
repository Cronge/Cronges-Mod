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

import java.util.ArrayList;
import java.util.List;

public class EightBallItem extends Item {
    public EightBallItem(Settings settings) {
        super(settings);
        this.instanceofRandom = Random.createLocal();
        this.instanceofJavaRandom = new java.util.Random();
    }

    private final Random instanceofRandom;
    private final java.util.Random instanceofJavaRandom;

    private final List<StatusEffect> buffs = new ArrayList<>();
    private final List<StatusEffect> debuffs = new ArrayList<>();

    private int smallAmplifierRNG;
    private int bigAmplifierRNG;

    private int eightBallRNG;
    private int buffRNG;
    private int debuffRNG;

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
            setBuffs();
            setDebuffs();

            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();

            eightBallRNG = instanceofRandom.nextInt((8 - 1) + 1) + 1;
            smallAmplifierRNG = instanceofJavaRandom.nextInt(2);
            bigAmplifierRNG = instanceofJavaRandom.nextInt((3 - 2) + 1) + 2;
            buffRNG = instanceofJavaRandom.nextInt(buffs.size());
            debuffRNG = instanceofJavaRandom.nextInt(debuffs.size());

            outputMessage(user, serverWorld, x, y, z);
            setCooldown(this, user);

            buff(user);
            debuff(user);
        }
        return super.use(world, user, hand);
    }

    private void outputMessage(PlayerEntity player, ServerWorld serverWorld, double x, double y, double z) {
        if (is8Rolled()) {
            player.sendMessage(Text.translatable(MESSAGE_ROLLED_WHEN_8, getRandomNumber()));

            spawnParticles(player, serverWorld, x, y, z);

            serverWorld.playSound(null, x, y, z, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1.5f, -1f);
            serverWorld.playSound(null, x, y, z, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 2f, 1f);
        } else {
            player.sendMessage(Text.translatable(MESSAGE_ROLLED_NUMBER, getRandomNumber()));

            serverWorld.playSound(null, x, y, z, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1.5f, 0.5f);
        }
    }

    private void spawnParticles(PlayerEntity player, ServerWorld serverWorld, double x, double y, double z) {
        serverWorld.spawnParticles(((ServerPlayerEntity) player), ParticleTypes.INSTANT_EFFECT, false, x + 0.3, y + 1, z, 5, 0, 1, 0, 0);
        serverWorld.spawnParticles(((ServerPlayerEntity) player), ParticleTypes.INSTANT_EFFECT, false, x - 0.3, y + 1, z, 5, 0, 1, 0, 0);
        serverWorld.spawnParticles(((ServerPlayerEntity) player), ParticleTypes.INSTANT_EFFECT, false, x, y + 1, z + 0.3, 5, 0, 1, 0, 0);
        serverWorld.spawnParticles(((ServerPlayerEntity) player), ParticleTypes.INSTANT_EFFECT, false, x, y + 1, z  - 0.3, 5, 0, 1, 0, 0);
    }

    private boolean is8Rolled() {
        return eightBallRNG == 8;
    }

    private void buff(PlayerEntity player) {
        if (is8Rolled()) {
            player.addStatusEffect(new StatusEffectInstance(buffs.get(getRandomBuff()), 80, getRandomBigAmplifier(), true, false, true));
        } else {
            player.addStatusEffect(new StatusEffectInstance(buffs.get(getRandomBuff()), 160, getRandomSmallAmplifier(), true, false, true));
        }
    }
    private void debuff(PlayerEntity player) {
        if (is8Rolled()) {
            player.addStatusEffect(new StatusEffectInstance(debuffs.get(getRandomDebuff()), 80, getRandomSmallAmplifier(), true, false, true));
        } else {
            player.addStatusEffect(new StatusEffectInstance(debuffs.get(getRandomDebuff()), 160, getRandomSmallAmplifier() - 1, true, false, true));
        }
    }

    private void setBuffs() {
        buffs.add(StatusEffects.SPEED);
        buffs.add(StatusEffects.HASTE);
        buffs.add(StatusEffects.STRENGTH);
        buffs.add(StatusEffects.JUMP_BOOST);
        buffs.add(StatusEffects.REGENERATION);
        buffs.add(StatusEffects.FIRE_RESISTANCE);
        buffs.add(StatusEffects.WATER_BREATHING);
        buffs.add(StatusEffects.INVISIBILITY);
        buffs.add(StatusEffects.NIGHT_VISION);
        buffs.add(StatusEffects.HEALTH_BOOST);
        buffs.add(StatusEffects.ABSORPTION);
    }
    private void setDebuffs() {
        debuffs.add(StatusEffects.SLOWNESS);
        debuffs.add(StatusEffects.MINING_FATIGUE);
        debuffs.add(StatusEffects.INSTANT_DAMAGE);
        debuffs.add(StatusEffects.NAUSEA);
        debuffs.add(StatusEffects.BLINDNESS);
        debuffs.add(StatusEffects.HUNGER);
        debuffs.add(StatusEffects.WEAKNESS);
        debuffs.add(StatusEffects.POISON);
        debuffs.add(StatusEffects.WITHER);
    }

    private void setCooldown(Item item, PlayerEntity player) {
        if (is8Rolled()) {
            player.getItemCooldownManager().set(item, 85);
        } else {
            player.getItemCooldownManager().set(item, 165);
        }
    }

    private int getRandomNumber() {
        return eightBallRNG;
    }

    private int getRandomBuff() {
        return buffRNG;
    }

    private int getRandomDebuff() {
        return debuffRNG;
    }
    private int getRandomSmallAmplifier() {
        return smallAmplifierRNG;
    }
    private int getRandomBigAmplifier() {
        return bigAmplifierRNG;
    }
}
