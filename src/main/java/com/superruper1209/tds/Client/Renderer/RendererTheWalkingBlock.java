package com.superruper1209.tds.Client.Renderer;

import com.superruper1209.tds.Common.Entities.TheWalkingBlock;
import com.superruper1209.tds.TDS;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererTheWalkingBlock extends LivingRenderer<TheWalkingBlock, TheWalkingBlockModel<TheWalkingBlock>> {

    public RendererTheWalkingBlock(EntityRendererManager rendererManager, TheWalkingBlockModel<TheWalkingBlock> entityModelIn, float shadowSizeIn) {
        super(rendererManager, entityModelIn, shadowSizeIn);
    }

    public ResourceLocation getEntityTexture(TheWalkingBlock entity) {
        return new ResourceLocation( "tds", "textures/entities/walkingblock.png");
    }

    @Override
    protected boolean canRenderName(TheWalkingBlock entity) {
        return false;
    }

    @Override
    public boolean shouldRender(TheWalkingBlock livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        return super.shouldRender(livingEntityIn, camera, camX, camY, camZ);
    }
}
