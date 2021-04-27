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
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (rand.nextInt(4) == 0
        && reader.getBlockState(pos).isAir() && reader.getBlockState(pos.above()).isAir() && reader.getBlockState(pos.above().above()).isAir()
        && reader.getBlockState(pos.above().south()).isAir() && reader.getBlockState(pos.above().north()).isAir()
        && reader.getBlockState(pos.above().west()).isAir() && reader.getBlockState(pos.above().west()).isAir()
        && reader.getBlockState(pos.below()).getBlockState().getBlock().is(Blocks.STONE)
        ) {
            reader.setBlock(pos, SpoonLog.ENTRY.defaultBlockState(), 0);
            reader.setBlock(pos.above(), SpoonLog.ENTRY.defaultBlockState(), 0);
            reader.setBlock(pos.above().above(), SpoonLeaves.ENTRY.defaultBlockState(), 0);
            reader.setBlock(pos.above().south(), SpoonLeaves.ENTRY.defaultBlockState(), 0);
            reader.setBlock(pos.above().north(), SpoonLeaves.ENTRY.defaultBlockState(), 0);
            reader.setBlock(pos.above().west(), SpoonLeaves.ENTRY.defaultBlockState(), 0);
            reader.setBlock(pos.above().east(), SpoonLeaves.ENTRY.defaultBlockState(), 0);
            return true;
        }
        return false;
    }

    public static final Feature<?> feature = new SpoonTreeFeature(NoFeatureConfig.CODEC);
}
