package com.superruper1209.tds;

import com.superruper1209.tds.Client.Renderer.RendererCustomTnt;
import com.superruper1209.tds.Client.Renderer.RendererNothing;
import com.superruper1209.tds.Client.Renderer.RendererTheWalkingBlock;
import com.superruper1209.tds.Client.Renderer.TheWalkingBlockModel;
import com.superruper1209.tds.Common.Blocks.*;
import com.superruper1209.tds.Common.Effects.EffectList;
import com.superruper1209.tds.Common.Effects.PotionList;
import com.superruper1209.tds.Common.Entities.AnchorTnt;
import com.superruper1209.tds.Common.Entities.BedTnt;
import com.superruper1209.tds.Common.Entities.TheWalkingBlock;
import com.superruper1209.tds.Common.Items.SimpleItems;
import com.superruper1209.tds.Common.Miscelenaous.SitUtil;
import com.superruper1209.tds.Common.World.Gen.SpoonTreeFeature;
import com.superruper1209.tds.Common.World.Gen.TDSFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
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
        bus.addListener(this::registerEntitiesAttributes);
        TDSFeatures.FEATURES.register(bus);
        MinecraftForge.EVENT_BUS.addListener(this::EntityDismountEvent);
        MinecraftForge.EVENT_BUS.addListener(this::biomeModify);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> BrewingRecipeRegistry.addRecipe(PotionList.bouncerecipe));
        TDSFeatures.SpoonFeatureConfigured = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "spoon_tree", TDSFeatures.SpoonFeature.get().withConfiguration(new NoFeatureConfig()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT));
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
        RenderingRegistry.registerEntityRenderingHandler((EntityType<BedTnt>) BedTnt.ENTRY, new IRenderFactory<BedTnt>() {
            @Override
            public EntityRenderer<? super BedTnt> createRenderFor(EntityRendererManager manager) {
                BlockState state = BedTntBlock.getdefaultstate();
                return new RendererCustomTnt<BedTnt>(manager, state);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler((EntityType<AnchorTnt>) AnchorTnt.ENTRY, new IRenderFactory<AnchorTnt>() {
            @Override
            public EntityRenderer<? super AnchorTnt> createRenderFor(EntityRendererManager manager) {
                BlockState state = AnchorTntBlock.getdefaultstate();
                return new RendererCustomTnt<AnchorTnt>(manager, state);
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
        event.getRegistry().register(SitUtil.SitEntity.ENTRY);
        event.getRegistry().register(BedTnt.ENTRY);
        event.getRegistry().register(AnchorTnt.ENTRY);
    }
    public void registerEntitiesAttributes(EntityAttributeCreationEvent event) {
        event.put((EntityType<? extends LivingEntity>) TheWalkingBlock.ENTRY, CreatureEntity.func_233666_p_().create());
    }
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(SimpleItems.MONSTER_EGG);
        event.getRegistry().register(SimpleItems.STOOL);
        event.getRegistry().register(SimpleItems.BED_TNT);
        event.getRegistry().register(SimpleItems.ANCHOR_TNT);
        event.getRegistry().register(SimpleItems.SPOON_LOG);
        event.getRegistry().register(SimpleItems.SPOON_LEAVES);
        event.getRegistry().register(SimpleItems.SPOON_PLANKS);
        event.getRegistry().register(SimpleItems.SPOON_STICK);
        event.getRegistry().register(SimpleItems.COMICALLY_LARGE_SPOON);
    }
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(Stool.ENTRY);
        event.getRegistry().register(BedTntBlock.ENTRY);
        event.getRegistry().register(AnchorTntBlock.ENTRY);
        event.getRegistry().register(SpoonLog.ENTRY);
        event.getRegistry().register(SpoonLeaves.ENTRY);
        event.getRegistry().register(SimpleBlocks.SPOON_PLANKS);
    }
    public void biomeModify(BiomeLoadingEvent event) {
        if (event.getCategory().ordinal() == Biome.Category.EXTREME_HILLS.ordinal()) {
            LOGGER.info(event.getName());
            event.getGeneration().getFeatures(GenerationStage.Decoration.TOP_LAYER_MODIFICATION).add(() -> TDSFeatures.SpoonFeatureConfigured);
        }
    }
    public void EntityDismountEvent(EntityMountEvent event) {
        SitUtil.EntityDismount(event);
    }
}
