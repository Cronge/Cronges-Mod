package net.cronge.crongesmod.world.feature;

import net.cronge.crongesmod.CrongesMod;
import net.cronge.crongesmod.block.ModBlocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {

    public static final List<OreFeatureConfig.Target> RUBY_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_RUBY_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> RUBY_ORE_SMALL =
            ConfiguredFeatures.register("ruby_ore", Feature.ORE, new OreFeatureConfig(RUBY_ORES, 5, 0.5f));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> RUBY_ORE_LARGE =
            ConfiguredFeatures.register("ruby_ore_large", Feature.ORE, new OreFeatureConfig(RUBY_ORES, 11, 0.7f));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> RUBY_ORE_BURIED =
            ConfiguredFeatures.register("ruby_ore_buried", Feature.ORE, new OreFeatureConfig(RUBY_ORES, 7, 1f));

    public static void registerModConfiguredFeatures() {
        CrongesMod.LOGGER.debug("Registering Mod Configured Features for " + CrongesMod.MOD_ID);
    }
}
