package com.superruper1209.tds.Client.Renderer;

import com.superruper1209.tds.Common.Blocks.AnchorTntBlock;
import com.superruper1209.tds.Common.Blocks.BedTntBlock;
import com.superruper1209.tds.Common.Entities.AnchorTnt;
import com.superruper1209.tds.Common.Entities.BedTnt;
import com.superruper1209.tds.Common.Entities.TheWalkingBlock;
import com.superruper1209.tds.Common.Miscelenaous.SitUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RenderRegistry {
    public static void register (FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<TheWalkingBlock>) TheWalkingBlock.ENTRY, manager -> new RendererTheWalkingBlock(manager, new TheWalkingBlockModel<TheWalkingBlock>(), 1));
        RenderingRegistry.registerEntityRenderingHandler((EntityType<BedTnt>) BedTnt.ENTRY, manager -> {BlockState state = BedTntBlock.getdefaultstate();return new RendererCustomTnt<BedTnt>(manager, state);});
        RenderingRegistry.registerEntityRenderingHandler((EntityType<AnchorTnt>) AnchorTnt.ENTRY, manager -> {BlockState state = AnchorTntBlock.getdefaultstate();return new RendererCustomTnt<AnchorTnt>(manager, state);});
        RenderingRegistry.registerEntityRenderingHandler((EntityType<SitUtil.SitEntity>) SitUtil.SitEntity.ENTRY, manager -> new SitUtil.SitEntity.EmptyRenderer(manager));
    }
}
