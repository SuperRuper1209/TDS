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
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class SpoonLog extends RotatedPillarBlock {
    public static final Block ENTRY = new SpoonLog(AbstractBlock.Properties.create(Material.WOOD).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(3, 3)).setRegistryName("tds", "spoon_log");

    public SpoonLog(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        super.onBlockClicked(state, worldIn, pos, player);
        if (player.getHeldItem(Hand.MAIN_HAND).getToolTypes().contains(ToolType.AXE)) {
            if (!player.getHeldItem(Hand.MAIN_HAND).getToolTypes().contains(ToolType.PICKAXE)) {
                player.sendStatusMessage(new StringTextComponent("This wood is too hard. Try mining it with pick."), true);
            }
            else if (!player.getHeldItem(Hand.MAIN_HAND).canHarvestBlock(state)) {
                player.sendStatusMessage(new StringTextComponent("Try a better pick."), true);
            }
        }
        else if (player.getHeldItem(Hand.MAIN_HAND).getToolTypes().contains(ToolType.PICKAXE) && !player.getHeldItem(Hand.MAIN_HAND).canHarvestBlock(state)) {
            player.sendStatusMessage(new StringTextComponent("Try a better pick."), true);
        }
    }
}
