package com.superruper1209.tds.Common.Blocks;

import com.superruper1209.tds.Common.Entities.AnchorTnt;
import com.superruper1209.tds.Common.Entities.BedTnt;
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

public class AnchorTntBlock extends CustomTntBlock{

    public static final Block ENTRY = new AnchorTntBlock(AbstractBlock.Properties.of(Material.PLANT).instabreak().sound(SoundType.CROP)).setRegistryName("tds", "anchor_tnt");

    public AnchorTntBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void catchFire(BlockState state, World world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        if (!world.isClientSide) {
            com.superruper1209.tds.Common.Entities.AnchorTnt tntentity = new com.superruper1209.tds.Common.Entities.AnchorTnt(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, igniter);
            world.addFreshEntity(tntentity);
            world.playSound((PlayerEntity)null, tntentity.getX(), tntentity.getY(), tntentity.getZ(), SoundEvents.TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getItemInHand(handIn);
        Item item = itemstack.getItem();
        if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
            return super.use(state, worldIn, pos, player, handIn, hit);
        } else if (worldIn.dimensionType().bedWorks()) {
            catchFire(state, worldIn, pos, hit.getDirection(), player);
            worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            if (!player.isCreative()) {
                if (item == Items.FLINT_AND_STEEL) {
                    itemstack.setDamageValue(itemstack.getDamageValue()-1);
                    player.broadcastBreakEvent(handIn);
                } else {
                    itemstack.shrink(1);
                }
            }

            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public void wasExploded(World worldIn, BlockPos pos, Explosion explosionIn) {
        if (!worldIn.isClientSide && !worldIn.dimensionType().bedWorks()) {
            AnchorTnt tntentity = new AnchorTnt(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, explosionIn.getSourceMob());
            tntentity.setFuse((short)(worldIn.random.nextInt(tntentity.getFuse() / 4) + tntentity.getFuse() / 8));
            worldIn.addFreshEntity(tntentity);
        }
    }

    public static BlockState getdefaultstate() {
        return ENTRY.defaultBlockState();
    }
}
