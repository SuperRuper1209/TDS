package com.superruper1209.tds.Common.BigStuff.PlayerBottle.Dimensions;

import com.superruper1209.tds.TDS;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.function.Function;

public class BottleItem extends Item {
    public static final Item ENTRY = new BottleItem(new Item.Properties().tab(ItemGroup.TAB_MISC)).setRegistryName("tds", "teleport_bottle");
    public BottleItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    public static void TeleportEntity(PlayerInteractEvent.EntityInteract event) {
        Entity p_77659_2_ = event.getTarget();
        if (p_77659_2_.level.getServer() != null && p_77659_2_.level.getServer().getLevel(DimensionReg.BOTTLE) != null && p_77659_2_.level.dimension() != DimensionReg.BOTTLE && p_77659_2_ instanceof CreatureEntity && event.getPlayer().getMainHandItem().getItem().equals(BottleItem.ENTRY)) {
            p_77659_2_.changeDimension(p_77659_2_.level.getServer().getLevel(DimensionReg.BOTTLE), new ITeleporter() {
                @Override
                public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                    entity = repositionEntity.apply(false);
                    if (entity instanceof MobEntity) {
                        MobEntity ent2 = (MobEntity) entity;
                        ent2.setPersistenceRequired();
                    }
                    entity.teleportTo(16.0f*random.nextInt(10)-8.5f, 1.0f, 16f*random.nextInt(10)-8.5f);
                    TDS.LOGGER.info(String.valueOf(entity.getX())+", "+String.valueOf(entity.getY())+", "+String.valueOf(entity.getZ()));
                    return entity;
                }
            });
            event.getPlayer().displayClientMessage(new TranslationTextComponent("item.tds.teleport_bottle.entity_discomfort"), true);
        }
    }
}
