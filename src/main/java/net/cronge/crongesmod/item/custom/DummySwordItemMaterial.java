package net.cronge.crongesmod.item.custom;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class DummySwordItemMaterial implements ToolMaterial {

    public static final DummySwordItemMaterial INSTANCE = new DummySwordItemMaterial();

    @Override
    public int getDurability() {
        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0.1f;
    }

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.STRIPPED_OAK_WOOD,
                Items.STRIPPED_SPRUCE_WOOD, Items.STRIPPED_BIRCH_WOOD,
                Items.STRIPPED_JUNGLE_WOOD, Items.STRIPPED_ACACIA_WOOD, Items.STRIPPED_DARK_OAK_WOOD,
                Items.STRIPPED_MANGROVE_WOOD, Items.STRIPPED_CRIMSON_HYPHAE, Items.STRIPPED_WARPED_HYPHAE);
    }
}
