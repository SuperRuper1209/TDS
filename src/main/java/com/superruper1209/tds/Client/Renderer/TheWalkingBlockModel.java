package com.superruper1209.tds.Client.Renderer;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.function.Consumer;

public class TheWalkingBlockModel<T extends Entity> extends EntityModel<T> {
    @Override
    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) { }

    @Override
    public Consumer<ModelRenderer> andThen(Consumer<? super ModelRenderer> after) { return null; }

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

    @Override
    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) { }
}
