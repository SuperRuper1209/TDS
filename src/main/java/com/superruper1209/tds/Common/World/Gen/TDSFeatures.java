package com.superruper1209.tds.Common.World.Gen;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TDSFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, "tds");
    public static final RegistryObject<Feature<NoFeatureConfig>> SpoonFeature = FEATURES.register("spoon_tree", () -> (Feature<NoFeatureConfig>) SpoonTreeFeature.feature);
    public static ConfiguredFeature<?, ?> SpoonFeatureConfigured = null;
}
