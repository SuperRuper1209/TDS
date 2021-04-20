package com.superruper1209.tds.Common.Miscelenaous;

import com.superruper1209.tds.Common.Blocks.Stool;
import com.superruper1209.tds.TDS;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.*;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.network.NetworkHooks;

public class SitUtil {
    public static class SitEntity extends Entity {
        public SitEntity(EntityType<?> entityTypeIn, World worldIn) {
            super(entityTypeIn, worldIn);
            noClip = true;
        }
        @Override
        protected void registerData() {
            this.dataManager.register(EntityDataManager.createKey(SitEntity.class, DataSerializers.BOOLEAN), true);
        }

        @Override
        public void tick() {
            super.tick();
            if (!world.isRemote) {
                if (this.getRidingEntity() != null) {
                    if (!this.getRidingEntity().getPosition().equals(this.getPosition())) {
                        BlockPos actualkey = null;
                        for (BlockPos pos : Stool.OCCUPIED.get(world.getDimensionKey().getLocation()).keySet())
                        {
                            if (pos.equals(this.getPosition())) {
                                actualkey = pos;
                            }
                        }
                        if (actualkey != null) {
                            Stool.OCCUPIED.get(world.getDimensionKey().getLocation()).remove(actualkey);
                            this.remove();
                        }
                    }
                }
            }
        }

        @Override
        protected void readAdditional(CompoundNBT compound) {

        }
        @Override
        protected void writeAdditional(CompoundNBT compound) {

        }
        @Override
        public IPacket<?> createSpawnPacket() {
            return NetworkHooks.getEntitySpawningPacket(this);
        }

        public static final EntityType<?> ENTRY = EntityType.Builder.create(SitUtil.SitEntity::new, EntityClassification.MISC).setTrackingRange(1).size(1, 1).build("tds:sit").setRegistryName("tds", "sit");;
    }
    public static SitEntity SitAt(Entity Sitter, BlockPos Pos) {
        SitEntity entity = new SitEntity(SitEntity.ENTRY, Sitter.getEntityWorld());
        entity.setPosition(Pos.getX()+0.5, Pos.getY()-0.5, Pos.getZ()+0.5);
        Sitter.getEntityWorld().addEntity(entity);
        Sitter.startRiding(entity, true);
        return entity;
    }


    public static void EntityDismount(EntityMountEvent event) {
        if (event.isDismounting()) {
            if (event.getEntityBeingMounted().getType() == SitEntity.ENTRY) {
                BlockPos actualkey = null;
                try {
                    for (BlockPos pos : Stool.OCCUPIED.get(event.getEntityMounting().world.getDimensionKey().getLocation()).keySet()) {
                        if (pos != null && pos.equals(event.getEntityBeingMounted().getPosition())) {
                            actualkey = pos;
                        }
                    }
                }
                catch (Exception e) {

                }
                if (actualkey != null) {
                    Stool.OCCUPIED.get(event.getEntityMounting().world.getDimensionKey().getLocation()).remove(actualkey);
                }
                event.getEntityBeingMounted().remove();
                event.getEntityMounting().setPosition(event.getEntityMounting().getPosition().getX(), event.getEntityMounting().getPosition().getY()+0.5, event.getEntityMounting().getPosition().getZ());
            }
            event.getEntityBeingMounted().onKillCommand();
        }
    }
}
