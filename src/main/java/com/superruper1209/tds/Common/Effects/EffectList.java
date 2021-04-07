package com.superruper1209.tds.Common.Effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class EffectList {
    public static final Effect bounce = new Bounce(EffectType.NEUTRAL, 0xc3f7c9).setRegistryName(new ResourceLocation("tds", "bounce"));
}
