package net.cronge.crongesmod;

import net.cronge.crongesmod.block.ModBlocks;
import net.cronge.crongesmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CrongesMod implements ModInitializer {
    public static final String MOD_ID = "crongesmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        CrongesMod.LOGGER.info("Hello!");
    }
}
