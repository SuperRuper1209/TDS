package com.superruper1209.tds.Common.Blocks;

import com.superruper1209.tds.Common.Miscelenaous.SitUtil;
import com.superruper1209.tds.TDS;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.HashMap;

public class Stool extends Block {
    public static final HashMap<ResourceLocation, HashMap<BlockPos, PlayerEntity>> OCCUPIED = new HashMap<>();
    public Stool(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return makeCuboidShape( 0, 0, 0, 16, 8, 16);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            if (OCCUPIED.containsKey(worldIn.getDimensionKey().getLocation()) == false) {
                HashMap<BlockPos, PlayerEntity> key = new HashMap<BlockPos, PlayerEntity>();
                key.put(pos, player);
                OCCUPIED.put(worldIn.getDimensionKey().getLocation(), key);
                TDS.LOGGER.info("put");
                SitUtil.SitAt(player, pos);
                TDS.LOGGER.info("sit");
                return ActionResultType.SUCCESS;
            }
            BlockPos actualkey = null;
            for (BlockPos pos2 : Stool.OCCUPIED.get(worldIn.getDimensionKey().getLocation()).keySet())
            {
                if (pos2.equals(pos)) {
                    actualkey = pos;
                }
            }
            if (OCCUPIED.containsKey(worldIn.getDimensionKey().getLocation()) == true) {
                HashMap<BlockPos, PlayerEntity> key = new HashMap<BlockPos, PlayerEntity>();
                if (actualkey == null) {
                    key.put(pos, player);
                }
                else {
                    key.put(actualkey, player);
                }
                OCCUPIED.put(worldIn.getDimensionKey().getLocation(), key);
                TDS.LOGGER.info("put");
                SitUtil.SitAt(player, pos);
                TDS.LOGGER.info("sit");
                return ActionResultType.SUCCESS;
            }
            TDS.LOGGER.info("bruh");
            return ActionResultType.FAIL;
        }
        return ActionResultType.SUCCESS;
    }

    public static final Block ENTRY = new Stool(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)).setRegistryName("tds", "stool");
}
