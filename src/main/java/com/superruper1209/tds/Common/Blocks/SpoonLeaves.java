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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class SpoonLeaves extends Block implements net.minecraftforge.common.IForgeShearable  {
    public static final Block ENTRY = new SpoonLeaves(AbstractBlock.Properties.create(Material.LEAVES).setRequiresTool().hardnessAndResistance(1, 1).harvestTool(ToolType.PICKAXE).harvestLevel(0)).setRegistryName("tds", "spoon_leaves");

    public SpoonLeaves(Properties properties) {
        super(properties);
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return VoxelShapes.empty();
    }

    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.isRainingAt(pos.up())) {
            if (rand.nextInt(15) == 1) {
                BlockPos blockpos = pos.down();
                BlockState blockstate = worldIn.getBlockState(blockpos);
                if (!blockstate.isSolid() || !blockstate.isSolidSide(worldIn, blockpos, Direction.UP)) {
                    double d0 = (double)pos.getX() + rand.nextDouble();
                    double d1 = (double)pos.getY() - 0.05D;
                    double d2 = (double)pos.getZ() + rand.nextDouble();
                    worldIn.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        super.onBlockClicked(state, worldIn, pos, player);
        if (player.getHeldItem(Hand.MAIN_HAND).getToolTypes().contains(ToolType.AXE)) {
            if (!player.getHeldItem(Hand.MAIN_HAND).getToolTypes().contains(ToolType.PICKAXE)) {
                player.sendStatusMessage(new StringTextComponent("This wood is too hard. Try mining it with pick."), true);
            }
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState();
    }
}
