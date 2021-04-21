package com.superruper1209.tds.Common.Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class SpoonLeaves extends Block implements net.minecraftforge.common.IForgeShearable  {
    public static final Block ENTRY = new SpoonLeaves(AbstractBlock.Properties.of(Material.LEAVES).requiresCorrectToolForDrops().strength(1, 1).harvestTool(ToolType.PICKAXE).harvestLevel(0)).setRegistryName("tds", "spoon_leaves");

    public SpoonLeaves(Properties properties) {
        super(properties);
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return VoxelShapes.empty();
    }
    @Override
    public void attack(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        super.attack(state, worldIn, pos, player);
        if (player.getItemInHand(Hand.MAIN_HAND).getToolTypes().contains(ToolType.AXE)) {
            if (!player.getItemInHand(Hand.MAIN_HAND).getToolTypes().contains(ToolType.PICKAXE)) {
                player.displayClientMessage(new TranslationTextComponent("block.tds.spoon_log.too_hard"), true);
            }
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState();
    }
}
