package com.superruper1209.tds.Common.Entities;

import javax.annotation.Nullable;

import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public abstract class CustomTnt extends Entity {
    private static final DataParameter<Integer> FUSE = null;// = EntityDataManager.createKey(CustomTnt.class, DataSerializers.VARINT);
    public static final EntityType<?> ENTRY = null;// = EntityType.Builder.create(CustomTnt::new, EntityClassification.MISC).setTrackingRange(100).size(1, 1).build("tds:").setRegistryName("tds", "");
    @Nullable
    public LivingEntity tntPlacedBy;
    public int fuse = 80;

    public CustomTnt(EntityType<?> type, World worldIn) {
        super(type, worldIn);
        this.blocksBuilding = true;
    }

    public boolean canBeCollidedWith() {
        return !this.removed;
    }

    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
        }

        --this.fuse;
        if (this.fuse <= 0) {
            this.remove();
            if (!this.level.isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        if (FUSE.equals(p_184206_1_)) {
            this.fuse = this.getFuse();
        }
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 0.15F;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(FUSE, 80);
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    public boolean isPickable() {
        return !this.removed;
    }

    public void explode() {
        float f = 4.0F;
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0F, Explosion.Mode.BREAK);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        compound.putShort("Fuse", (short)this.getFuse());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        this.setFuse(compound.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.tntPlacedBy;
    }

    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.15F;
    }

    public void setFuse(int fuseIn) {
        this.entityData.set(FUSE, fuseIn);
        this.fuse = fuseIn;
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (FUSE.equals(key)) {
            this.fuse = this.getFuseDataManager();
        }
    }

    public int getFuseDataManager() {
        return this.entityData.get(FUSE);
    }

    public int getFuse() {
        return this.fuse;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return null;
    }
}
