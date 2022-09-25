package net.cronge.crongesmod.world.gen;

import net.cronge.crongesmod.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.RAW_GENERATION, ModPlacedFeatures.RUBY_ORE_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.RAW_GENERATION, ModPlacedFeatures.RUBY_ORE_LARGE_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.RAW_GENERATION, ModPlacedFeatures.RUBY_ORE_BURIED_PLACED.getKey().get());
    }
}
