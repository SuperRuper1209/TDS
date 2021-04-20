package com.superruper1209.tds.Client.Renderer;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class TheWalkingBlockModel<T extends Entity> extends SegmentedModel<T> {
    private final ModelRenderer slimeBodies;
    public TheWalkingBlockModel() {
        this.slimeBodies = new ModelRenderer(this, 0, 0);
        this.slimeBodies.addBox(-50.0F, -60.0F, -50.0F, 100.0F, 100.0F, 100.0F);
    }
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.slimeBodies);
    }
}
