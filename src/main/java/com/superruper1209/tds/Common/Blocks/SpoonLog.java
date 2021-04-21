package com.superruper1209.tds.Common.Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class SpoonLog extends RotatedPillarBlock {
    public static final Block ENTRY = new SpoonLog(AbstractBlock.Properties.of(Material.WOOD).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(3, 3)).setRegistryName("tds", "spoon_log");

    public SpoonLog(Properties properties) {
        super(properties);
    }



    @Override
    public void attack(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        super.attack(state, worldIn, pos, player);
        if (player.getItemInHand(Hand.MAIN_HAND).getToolTypes().contains(ToolType.AXE)) {
            if (!player.getItemInHand(Hand.MAIN_HAND).getToolTypes().contains(ToolType.PICKAXE)) {
                player.displayClientMessage(new TranslationTextComponent("block.tds.spoon_log.too_hard"), true);
            }
            else if (!player.getItemInHand(Hand.MAIN_HAND).isCorrectToolForDrops(state)) {
                player.displayClientMessage(new TranslationTextComponent("block.tds.spoon_log.better_pick"), true);
            }
        }
        else if (player.getItemInHand(Hand.MAIN_HAND).getToolTypes().contains(ToolType.PICKAXE) && !player.getItemInHand(Hand.MAIN_HAND).isCorrectToolForDrops(state)) {
            player.displayClientMessage(new TranslationTextComponent("block.tds.spoon_log.better_pick"), true);
        }
    }
}
