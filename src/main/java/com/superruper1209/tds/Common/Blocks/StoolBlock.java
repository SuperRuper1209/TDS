package com.superruper1209.tds.Common.Blocks;

import com.superruper1209.tds.Common.Miscelenaous.SitUtil;
import com.superruper1209.tds.TDS;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class StoolBlock extends Block {
    public static final Block ENTRY = new StoolBlock(AbstractBlock.Properties.of(Material.WOOD)).setRegistryName("tds", "stool");

    public StoolBlock(Properties p_i48440_1_) {super(p_i48440_1_);}

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    }

    @Override
    public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if (!p_225533_2_.isClientSide && p_225533_4_.isOnGround() && !p_225533_4_.isShiftKeyDown()) {
            SitUtil.sit(p_225533_4_, p_225533_3_);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
