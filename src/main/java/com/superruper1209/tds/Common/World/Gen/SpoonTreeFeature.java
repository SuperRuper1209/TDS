package com.superruper1209.tds.Common.World.Gen;

import com.mojang.serialization.Codec;
import com.superruper1209.tds.Common.Blocks.SpoonLeaves;
import com.superruper1209.tds.Common.Blocks.SpoonLog;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class SpoonTreeFeature extends Feature<NoFeatureConfig> {
    public SpoonTreeFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (rand.nextInt(4) == 0
        && reader.getBlockState(pos).isAir() && reader.getBlockState(pos.up()).isAir() && reader.getBlockState(pos.up().up()).isAir()
        && reader.getBlockState(pos.up().south()).isAir() && reader.getBlockState(pos.up().north()).isAir()
        && reader.getBlockState(pos.up().west()).isAir() && reader.getBlockState(pos.up().west()).isAir()
        && reader.getBlockState(pos.down()).getBlockState().getBlock().matchesBlock(Blocks.STONE)
        ) {
            reader.setBlockState(pos, SpoonLog.ENTRY.getDefaultState(), 0);
            reader.setBlockState(pos.up(), SpoonLog.ENTRY.getDefaultState(), 0);
            reader.setBlockState(pos.up().up(), SpoonLeaves.ENTRY.getDefaultState(), 0);
            reader.setBlockState(pos.up().south(), SpoonLeaves.ENTRY.getDefaultState(), 0);
            reader.setBlockState(pos.up().north(), SpoonLeaves.ENTRY.getDefaultState(), 0);
            reader.setBlockState(pos.up().west(), SpoonLeaves.ENTRY.getDefaultState(), 0);
            reader.setBlockState(pos.up().east(), SpoonLeaves.ENTRY.getDefaultState(), 0);
            return true;
        }
        return false;
    }

    public static final Feature<?> feature = new SpoonTreeFeature(NoFeatureConfig.field_236558_a_);
}
