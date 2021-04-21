package com.superruper1209.tds.Common.Entities;

import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class AnchorTnt extends CustomTnt {
    private static final DataParameter<Integer> FUSE = EntityDataManager.defineId(AnchorTnt.class, DataSerializers.INT);
    public static final EntityType<?> ENTRY = EntityType.Builder.of(AnchorTnt::new, EntityClassification.MISC).setTrackingRange(100).sized(1, 1).build("tds:anchor_tnt").setRegistryName("tds", "anchor_tnt");

    public AnchorTnt(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    public AnchorTnt(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
        this((EntityType<? extends CustomTnt>) AnchorTnt.ENTRY, worldIn);
        this.setPos(x, y, z);
        double d0 = worldIn.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.tntPlacedBy = igniter;
    }

    public void explode() {
        if (this.level.dimensionType().bedWorks()) {
            float f = 5.0F;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), f, Explosion.Mode.BREAK);
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
