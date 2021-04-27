package com.superruper1209.tds.Common.Effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.vector.Vector3d;

public class Bounce extends Effect {

    public Bounce(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.isOnGround()) {
            entityLivingBaseIn.setDeltaMovement(new Vector3d(0, 0.5, 0).add(entityLivingBaseIn.getDeltaMovement()));
        }
    }
}
