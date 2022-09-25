package net.cronge.crongesmod;

import net.cronge.crongesmod.block.ModBlocks;
import net.cronge.crongesmod.event.AttackEntityHandler;
import net.cronge.crongesmod.item.ModItems;
import net.cronge.crongesmod.world.feature.ModConfiguredFeatures;
import net.cronge.crongesmod.world.gen.ModOreGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrongesMod implements ModInitializer {
    public static final String MOD_ID = "crongesmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModConfiguredFeatures.registerModConfiguredFeatures();
                                                    
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        ModOreGeneration.generateOres();
        AttackEntityCallback.EVENT.register(new AttackEntityHandler());
    }
}
