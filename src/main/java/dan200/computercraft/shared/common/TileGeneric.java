/*
 * This file is part of ComputerCraft - http://www.computercraft.info
 * Copyright Daniel Ratcliffe, 2011-2021. Do not distribute without permission.
 * Send enquiries to dratcliffe@gmail.com
 */

package dan200.computercraft.shared.common;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;

public abstract class TileGeneric extends BlockEntity implements BlockEntityClientSerializable
{
    public TileGeneric( BlockEntityType<? extends TileGeneric> type, BlockPos pos, BlockState state )
    {
        super( type, pos, state );
    }

    public void destroy()
    {
    }

    public void onChunkUnloaded()
    {
    }

    public final void updateBlock()
    {
        setChanged();
        BlockPos pos = getBlockPos();
        BlockState state = getBlockState();
        getLevel().sendBlockUpdated( pos, state, state, 3 );
    }

    @Nonnull
    public InteractionResult onActivate( Player player, InteractionHand hand, BlockHitResult hit )
    {
        return InteractionResult.PASS;
    }

    public void onNeighbourChange( @Nonnull BlockPos neighbour )
    {
    }

    public void onNeighbourTileEntityChange( @Nonnull BlockPos neighbour )
    {
    }

    protected void blockTick()
    {
    }

    public boolean isUsable( Player player, boolean ignoreRange )
    {
        if( player == null || !player.isAlive() || getLevel().getBlockEntity( getBlockPos() ) != this )
        {
            return false;
        }
        if( ignoreRange )
        {
            return true;
        }

        double range = getInteractRange( player );
        BlockPos pos = getBlockPos();
        return player.getCommandSenderWorld() == getLevel() && player.distanceToSqr( pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5 ) <= range * range;
    }

    protected double getInteractRange( Player player )
    {
        return 8.0;
    }

    @Override
    public void fromClientTag( CompoundTag compoundTag )
    {
        readDescription( compoundTag );
    }

    protected void readDescription( @Nonnull CompoundTag nbt )
    {
    }

    @Override
    public CompoundTag toClientTag( CompoundTag compoundTag )
    {
        writeDescription( compoundTag );
        return compoundTag;
    }

    protected void writeDescription( @Nonnull CompoundTag nbt )
    {
    }
}
