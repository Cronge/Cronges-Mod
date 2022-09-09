package net.cronge.crongesmod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Very Important Comment
public class CrongesMod implements ModInitializer {
    public static final String MOD_ID = "crongesmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        CrongesMod.LOGGER.info("Hello Fabric World!");
    }
}
