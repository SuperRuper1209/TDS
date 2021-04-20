package com.superruper1209.tds.Common.Entities;

import javax.annotation.Nullable;

import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public abstract class CustomTnt<T extends CustomTnt> extends Entity {
    private static final DataParameter<Integer> FUSE = null;// = EntityDataManager.createKey(CustomTnt.class, DataSerializers.VARINT);
    public static final EntityType<?> ENTRY = null;// = EntityType.Builder.create(CustomTnt::new, EntityClassification.MISC).setTrackingRange(100).size(1, 1).build("tds:").setRegistryName("tds", "");
    @Nullable
    public LivingEntity tntPlacedBy;
    public int fuse = 80;

    public CustomTnt(EntityType<?> type, World worldIn) {
        super(type, worldIn);
        this.preventEntitySpawning = true;
    }

    public CustomTnt(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
        this((EntityType<? extends CustomTnt>) T.ENTRY, worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        this.setMotion(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.tntPlacedBy = igniter;
    }

    protected void registerData() {
        this.dataManager.register(FUSE, 80);
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return !this.removed;
    }

    public void tick() {
        if (!this.hasNoGravity()) {
            this.setMotion(this.getMotion().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getMotion());
        this.setMotion(this.getMotion().scale(0.98D));
        if (this.onGround) {
            this.setMotion(this.getMotion().mul(0.7D, -0.5D, 0.7D));
        }

        --this.fuse;
        if (this.fuse <= 0) {
            this.remove();
            if (!this.world.isRemote) {
                this.explode();
            }
        } else {
            this.func_233566_aG_();
            if (this.world.isRemote) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    public void explode() {
        float f = 4.0F;
        this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), 4.0F, Explosion.Mode.BREAK);
    }

    public void writeAdditional(CompoundNBT compound) {
        compound.putShort("Fuse", (short)this.getFuse());
    }

    public void readAdditional(CompoundNBT compound) {
        this.setFuse(compound.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getTntPlacedBy() {
        return this.tntPlacedBy;
    }

    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.15F;
    }

    public void setFuse(int fuseIn) {
        this.dataManager.set(FUSE, fuseIn);
        this.fuse = fuseIn;
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (FUSE.equals(key)) {
            this.fuse = this.getFuseDataManager();
        }
    }

    public int getFuseDataManager() {
        return this.dataManager.get(FUSE);
    }

    public int getFuse() {
        return this.fuse;
    }

    public abstract IPacket<?> createSpawnPacket();
}
