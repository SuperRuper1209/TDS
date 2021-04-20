package com.superruper1209.tds.Common.Entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Random;

public class TheWalkingBlock extends WaterMobEntity {
    SoundEvent weirdsounds = new SoundEvent(new ResourceLocation("tds", "weirdfishnoises"));

    static class Factory implements EntityType.IFactory {
        public Entity create(EntityType p_create_1_, World p_create_2_) {
            return new TheWalkingBlock(p_create_1_, p_create_2_);
        }
    }
    public TheWalkingBlock(EntityType<? extends WaterMobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void tick() {
        if (new Random().nextInt(100) == 2) {
            this.playSound(weirdsounds, 1, 0);
        }
        super.tick();
    }

    @Override
    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        if (damageSrc.getTrueSource() != null && damageSrc.getTrueSource() instanceof PlayerEntity) {
            this.playSound(weirdsounds, 15, 0);
            super.damageEntity(damageSrc, damageAmount);
        }
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(3, new FindWaterGoal(this));
        goalSelector.addGoal(2, new RandomSwimmingGoal(this, 50, 100));
        goalSelector.addGoal(1, new LookRandomlyGoal(this));
    }
    public static final EntityType<?> ENTRY = EntityType.Builder.create(new Factory()::create, EntityClassification.WATER_AMBIENT).setTrackingRange(100).size(8, 8).build("tds:walkingblock").setRegistryName("tds", "walkingblock");
}