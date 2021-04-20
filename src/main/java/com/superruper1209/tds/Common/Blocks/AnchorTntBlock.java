package com.superruper1209.tds.Common.Blocks;

import com.superruper1209.tds.Common.Entities.AnchorTnt;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AnchorTntBlock extends CustomTntBlock {

    public static final Block ENTRY = new AnchorTntBlock(AbstractBlock.Properties.create(Material.TNT).zeroHardnessAndResistance().sound(SoundType.CROP)).setRegistryName("tds", "anchor_tnt");

    public AnchorTntBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void catchFire(BlockState state, World world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        if (!world.isRemote) {
            com.superruper1209.tds.Common.Entities.AnchorTnt tntentity = new com.superruper1209.tds.Common.Entities.AnchorTnt(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, igniter);
            world.addEntity(tntentity);
            world.playSound((PlayerEntity)null, tntentity.getPosX(), tntentity.getPosY(), tntentity.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        if (!worldIn.isRemote && worldIn.getDimensionType().doesBedWork()) {
            AnchorTnt tntentity = new AnchorTnt(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, explosionIn.getExplosivePlacedBy());
            tntentity.setFuse((short)(worldIn.rand.nextInt(tntentity.getFuse() / 4) + tntentity.getFuse() / 8));
            worldIn.addEntity(tntentity);
        }
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        Item item = itemstack.getItem();
        if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        } else if (worldIn.getDimensionType().doesBedWork()) {
            catchFire(state, worldIn, pos, hit.getFace(), player);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            if (!player.isCreative()) {
                if (item == Items.FLINT_AND_STEEL) {
                    itemstack.damageItem(1, player, (player1) -> {
                        player1.sendBreakAnimation(handIn);
                    });
                } else {
                    itemstack.shrink(1);
                }
            }
        }
        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }

    public static BlockState getdefaultstate() {
        return ENTRY.getDefaultState();
    }
}
