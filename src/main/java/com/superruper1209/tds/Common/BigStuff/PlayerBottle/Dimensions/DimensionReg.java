package com.superruper1209.tds.Common.BigStuff.PlayerBottle.Dimensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.superruper1209.tds.Common.Blocks.SpoonLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;

public class DimensionReg {
    public static final RegistryKey<DimensionType> BOTTLE_TYPE = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation("tds", "bottle_type"));
    public static final RegistryKey<World> BOTTLE = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("tds", "bottle"));

    public static class BottleGenerator extends ChunkGenerator {
        public static final Codec<BottleGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(BottleGenerator::getBiomeRegistry)).apply(instance, BottleGenerator::new));
        public BottleGenerator(Registry<Biome> registry) {super(new BottleBiome(registry), new DimensionStructuresSettings(false));}
        @Override
        protected Codec<? extends ChunkGenerator> codec() {return CODEC;}
        @Override
        public ChunkGenerator withSeed(long p_230349_1_) {return new BottleGenerator(getBiomeRegistry());}
        @Override
        public void buildSurfaceAndBedrock(WorldGenRegion p_225551_1_, IChunk p_225551_2_) {
            for (int x=6;x<10;x++) {
                p_225551_2_.setBlockState(new BlockPos(x, 0, 7), Blocks.GLASS.defaultBlockState(), false);
                p_225551_2_.setBlockState(new BlockPos(x, 0, 8), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int x=6;x<10;x++) {
                p_225551_2_.setBlockState(new BlockPos(x, 3, 7), Blocks.GLASS.defaultBlockState(), false);
                p_225551_2_.setBlockState(new BlockPos(x, 3, 8), Blocks.GLASS.defaultBlockState(), false);
            }


            for (int y=0;y<4;y++) {
                p_225551_2_.setBlockState(new BlockPos(7, y, 6), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int y=0;y<4;y++) {
                p_225551_2_.setBlockState(new BlockPos(8, y, 6), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int y=0;y<4;y++) {
                p_225551_2_.setBlockState(new BlockPos(7, y, 9), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int y=0;y<4;y++) {
                p_225551_2_.setBlockState(new BlockPos(8, y, 9), Blocks.GLASS.defaultBlockState(), false);
            }


            for (int y=0;y<4;y++) {
                p_225551_2_.setBlockState(new BlockPos(6, y, 7), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int y=0;y<4;y++) {
                p_225551_2_.setBlockState(new BlockPos(6, y, 8), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int y=0;y<4;y++) {
                p_225551_2_.setBlockState(new BlockPos(9, y, 7), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int y=0;y<4;y++) {
                p_225551_2_.setBlockState(new BlockPos(9, y, 8), Blocks.GLASS.defaultBlockState(), false);
            }


            for (int y=1;y<3;y++) {
                p_225551_2_.setBlockState(new BlockPos(6, y, 6), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int y=1;y<3;y++) {
                p_225551_2_.setBlockState(new BlockPos(9, y, 9), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int y=1;y<3;y++) {
                p_225551_2_.setBlockState(new BlockPos(9, y, 6), Blocks.GLASS.defaultBlockState(), false);
            }
            for (int y=1;y<3;y++) {
                p_225551_2_.setBlockState(new BlockPos(6, y, 9), Blocks.GLASS.defaultBlockState(), false);
            }

            Block[] planks = {Blocks.ACACIA_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BIRCH_PLANKS, Blocks.CRIMSON_PLANKS, Blocks.WARPED_PLANKS, SpoonLog.ENTRY};
            Block selPlanks = planks[p_225551_1_.getRandom().nextInt(planks.length)];

            for (int x=7;x<9;x++) {
                for (int z=7;z<9;z++) {
                    for (int y=4;y<7;y++) {
                        p_225551_2_.setBlockState(new BlockPos(x, y, z), selPlanks.defaultBlockState(), false);
                    }
                }
            }

            for (int x=6;x<10;x++) {
                for (int z=6;z<10;z++) {
                    p_225551_2_.setBlockState(new BlockPos(x, 5, z), selPlanks.defaultBlockState(), false);
                }
            }
        }
        @Override
        public void createStructures(DynamicRegistries p_242707_1_, StructureManager p_242707_2_, IChunk p_242707_3_, TemplateManager p_242707_4_, long p_242707_5_) {}
        @Override
        public boolean hasStronghold(ChunkPos p_235952_1_) {return false;}
        @Override
        public void fillFromNoise(IWorld p_230352_1_, StructureManager p_230352_2_, IChunk p_230352_3_) {}
        @Override
        public int getBaseHeight(int p_222529_1_, int p_222529_2_, Heightmap.Type p_222529_3_) {return 0;}
        @Override
        public IBlockReader getBaseColumn(int p_230348_1_, int p_230348_2_) {return new Blockreader(new BlockState[0]);}
        public Registry<Biome> getBiomeRegistry() {return ((BottleBiome)biomeSource).getBiomeRegistry();}
    }
}
