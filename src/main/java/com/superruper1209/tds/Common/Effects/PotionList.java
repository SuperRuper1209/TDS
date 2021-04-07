package com.superruper1209.tds.Common.Effects;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class PotionList {
    public static final Potion bouncepotion = new Potion(new EffectInstance(EffectList.bounce, 400, 0, false, true)).setRegistryName(new ResourceLocation("tds", "bounce_potion"));
    public static final BounceRecipe bouncerecipe = new BounceRecipe();

}

class BounceRecipe implements IBrewingRecipe {
    @Override
    public boolean isInput(ItemStack input) {
        return PotionUtils.getPotionFromItem(input) == Potions.SWIFTNESS;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return ingredient.getItem() == Items.WATER_BUCKET;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (isInput(input) && isIngredient(ingredient)) {
            ingredient = new ItemStack(Items.BUCKET, ingredient.getCount());
            return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionList.bouncepotion);
        }
        else {
            return ItemStack.EMPTY;
        }
    }
}