package com.superruper1209.tds.Common.Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class CustomTntBlock extends Block {
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

    public CustomTntBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(UNSTABLE, Boolean.valueOf(false)));
    }

    public void catchFire(BlockState state, World world, BlockPos pos, @Nullable net.minecraft.util.Direction face, @Nullable LivingEntity igniter) {

    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.isIn(state.getBlock())) {
            if (worldIn.isBlockPowered(pos)) {
                catchFire(state, worldIn, pos, null, null);
                worldIn.removeBlock(pos, false);
            }

        }
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.isBlockPowered(pos)) {
            catchFire(state, worldIn, pos, null, null);
            worldIn.removeBlock(pos, false);
        }

    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote() && !player.isCreative() && state.get(UNSTABLE)) {
            catchFire(state, worldIn, pos, null, null);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {

    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        Item item = itemstack.getItem();
        if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        } else {
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

            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }
    }

    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        if (!worldIn.isRemote) {
            Entity entity = projectile.func_234616_v_();
            if (projectile.isBurning()) {
                BlockPos blockpos = hit.getPos();
                catchFire(state, worldIn, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                worldIn.removeBlock(blockpos, false);
            }
        }

    }

    public boolean canDropFromExplosion(Explosion explosionIn) {
        return false;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(UNSTABLE);
    }
}
