package com.superruper1209.tds.Common.Miscelenaous;

import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import java.util.ArrayList;
import java.util.HashMap;

public class SitUtil {
    public static HashMap<World, ArrayList<SitEntity>> SitEntities = new HashMap<>();
    public static class SitEntity extends Entity {
        public SitEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
            super(p_i48580_1_, p_i48580_2_);
            this.setNoGravity(true);
            if (!SitEntities.containsKey(this.level)) {
                SitEntities.put(this.level, new ArrayList<>());
            }
            SitEntities.get(this.level).add(this);
        }
        @Override
        public void tick()
        {
            if (this.getPassengers().isEmpty()) {
                SitEntities.get(this.level).remove(this);
                this.kill();
            }
            if (!SitEntities.containsKey(this.level)) {
                SitEntities.put(this.level, new ArrayList<>());
            }
            if (!SitEntities.get(this.level).contains(this)) {
                SitEntities.get(this.level).add(this);
            }
        }
        @Override
        protected void defineSynchedData() {}
        @Override
        protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {}
        @Override
        protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {}
        @Override
        public IPacket<?> getAddEntityPacket() {return NetworkHooks.getEntitySpawningPacket(this);}
        public static class EmptyRenderer extends EntityRenderer<SitEntity> {
            public EmptyRenderer(EntityRendererManager p_i46179_1_) {super(p_i46179_1_);}
            @Override
            public ResourceLocation getTextureLocation(SitEntity p_110775_1_) {return null;}
            @Override
            protected boolean shouldShowName(SitEntity p_177070_1_) {return false;}
            @Override
            public boolean shouldRender(SitEntity p_225626_1_, ClippingHelper p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {return false;}
        }
        public static final EntityType<?> ENTRY = EntityType.Builder.of(SitEntity::new, EntityClassification.MISC).sized(0.5f, 0.5f).build("tds:sit_entity").setRegistryName("tds", "sit_entity");
    }
    public static void sit(Entity mounting, BlockPos pos) {
        boolean found = false;
        if (SitEntities.containsKey(mounting.level)) {
            for (SitEntity entity : SitEntities.get(mounting.level)) {
                if (entity.blockPosition().equals(pos) && !entity.getPassengers().isEmpty()) {
                    found = true;
                }
            }
        }
        if (!found) {
            SitEntity sit_entity = new SitEntity(SitEntity.ENTRY,mounting.level);
            sit_entity.setPos(pos.getX()+0.5f, pos.getY(), pos.getZ()+0.5f);
            mounting.level.addFreshEntity(sit_entity);
            mounting.startRiding(sit_entity);
        }
    }
    public static void dismountEvent(EntityMountEvent event) {
        if (event.isDismounting() && event.getEntityBeingMounted().getType() == SitEntity.ENTRY) {
            if (SitEntities.containsKey(event.getEntityMounting().level)) {
                if (SitEntities.get(event.getEntityMounting().level).contains(event.getEntityBeingMounted())) {
                    SitEntities.get(event.getEntityMounting().level).remove(event.getEntityBeingMounted());
                    event.getEntityBeingMounted().kill();
                }
            }
        }
    }
}
