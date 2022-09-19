package net.cronge.crongesmod.item;

import net.cronge.crongesmod.CrongesMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup GEMSTONES = FabricItemGroupBuilder.build(
            new Identifier(CrongesMod.MOD_ID, "gemstones"), () -> new ItemStack(ModItems.RUBY_GEMSTONE));

    public static final ItemGroup CUSTOM = FabricItemGroupBuilder.build(
            new Identifier(CrongesMod.MOD_ID, "custom"), () -> new ItemStack(ModItems.EIGHT_BALL));

}
