package com.superruper1209.tds;

import com.superruper1209.tds.Client.Renderer.RendererNothing;
import com.superruper1209.tds.Client.Renderer.RendererTheWalkingBlock;
import com.superruper1209.tds.Client.Renderer.TheWalkingBlockModel;
import com.superruper1209.tds.Common.Blocks.Stool;
import com.superruper1209.tds.Common.Effects.EffectList;
import com.superruper1209.tds.Common.Effects.PotionList;
import com.superruper1209.tds.Common.Entities.TheWalkingBlock;
import com.superruper1209.tds.Common.Items.SimpleItems;
import com.superruper1209.tds.Common.Miscelenaous.SitUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("tds")
public class TDS
{
    public static final Logger LOGGER = LogManager.getLogger();
    public TDS() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addGenericListener(Effect.class, this::registerEffects);
        bus.addGenericListener(Potion.class, this::registerPotions);
        bus.addGenericListener(EntityType.class, this::registerEntities);
        bus.addGenericListener(Item.class, this::registerItems);
        bus.addGenericListener(Block.class, this::registerBlocks);
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.addListener(this::EntityDismount);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> BrewingRecipeRegistry.addRecipe(PotionList.bouncerecipe));
    }

    public void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<TheWalkingBlock>) TheWalkingBlock.ENTRY, new IRenderFactory<TheWalkingBlock>() {
            @Override
            public EntityRenderer<? super TheWalkingBlock> createRenderFor(EntityRendererManager manager) {
                return new RendererTheWalkingBlock(manager, new TheWalkingBlockModel<TheWalkingBlock>(), 1);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler((EntityType<SitUtil.SitEntity>) SitUtil.SitEntity.ENTRY, new IRenderFactory<SitUtil.SitEntity>() {
            @Override
            public EntityRenderer<? super SitUtil.SitEntity> createRenderFor(EntityRendererManager manager) {
                return new RendererNothing<SitUtil.SitEntity>(manager);
            }
        });
    }

    public void registerEffects(RegistryEvent.Register<Effect> event) {
        event.getRegistry().register(EffectList.bounce);
    }

    public void registerPotions(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(PotionList.bouncepotion);
    }
    public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().register(TheWalkingBlock.ENTRY);
        GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) TheWalkingBlock.ENTRY, CreatureEntity.func_233666_p_().create());
        event.getRegistry().register(SitUtil.SitEntity.ENTRY);
    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(SimpleItems.MONSTER_EGG);
        event.getRegistry().register(SimpleItems.STOOL);
    }
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(Stool.ENTRY);
    }

    public void EntityDismount(EntityMountEvent event) {
        SitUtil.EntityDismount(event);
    }
}
