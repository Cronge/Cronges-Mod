package net.cronge.crongesmod.item;

import net.cronge.crongesmod.CrongesMod;
import net.cronge.crongesmod.item.custom.DummySwordItem;
import net.cronge.crongesmod.item.custom.DummySwordItemMaterial;
import net.cronge.crongesmod.item.custom.EightBallItem;
import net.cronge.crongesmod.item.custom.TooltipStickItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item EIGHT_BALL = registerItem("eight_ball", new EightBallItem(new FabricItemSettings().group(ModItemGroup.CUSTOM).maxCount(1)));

    public static final Item TOOLTIP_STICK = registerItem("tooltip_stick", new TooltipStickItem(new FabricItemSettings().group(ModItemGroup.CUSTOM).maxCount(1)));
    public static final Item RUBY_GEMSTONE = registerItem("ruby_gemstone", new Item(new FabricItemSettings().group(ModItemGroup.GEMSTONES)));

    public static final Item RAW_RUBY_GEMSTONE = registerItem("raw_ruby_gemstone", new Item(new FabricItemSettings().group(ModItemGroup.GEMSTONES)));

    public static final Item DUMMY_SWORD = registerItem("dummy_sword",
            new DummySwordItem(DummySwordItemMaterial.INSTANCE, -1, -2.4f, new FabricItemSettings().group(ItemGroup.COMBAT).fireproof()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(CrongesMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CrongesMod.LOGGER.debug("Registering Mod Items for " + CrongesMod.MOD_ID);
    }
}
