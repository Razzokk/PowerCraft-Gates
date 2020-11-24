package rzk.pcg.tile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.lib.mc.util.ObjectUtils;
import rzk.pcg.block.BlockTimer;
import rzk.pcg.registry.ModTiles;

import javax.annotation.Nullable;

public class TileTimer extends TileRedstoneDevice implements ITickableTileEntity
{
	private int neededTicks;
	private int currentTicks;
	private boolean enabled;

	public TileTimer()
	{
		super(ModTiles.TIMER.get());
		neededTicks = 20;
		currentTicks = 0;
		enabled = false;
	}

	public int getDelay()
	{
		return neededTicks;
	}

	public void setDelay(int delay)
	{
		neededTicks = delay;
		world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
	}

	public void setEnabled(boolean state)
	{
		enabled = state;
		currentTicks = 0;
		markDirty();
	}

	private void setTimerState()
	{
		Block block = getBlockState().getBlock();
		if (block instanceof BlockTimer.Delay)
		{
			setEnabled(false);
			ObjectUtils.ifCastable(getBlockState().getBlock(), BlockTimer.Delay.class, blockTimer -> blockTimer.setPoweredState(getBlockState(), world, pos, blockTimer.isOnTimer()));
		}
		else
		{
			ObjectUtils.ifCastable(getBlockState().getBlock(), BlockTimer.class, blockTimer ->
			{
				blockTimer.setPoweredState(getBlockState(), world, pos, true);
				blockTimer.scheduleTickIfNotScheduled(world, pos, 2);
			});
		}
	}

	@Override
	public void tick()
	{
		if (!world.isRemote && enabled)
		{
			if (currentTicks >= neededTicks - 1)
			{
				setTimerState();
				currentTicks = 0;
			}
			else
			{
				currentTicks++;
			}
			markDirty();
		}
	}

	@Override
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		return new SUpdateTileEntityPacket(this.pos, 3, getUpdateTag());
	}

	@Override
	public CompoundNBT getUpdateTag()
	{
		return write(new CompoundNBT());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
	{
		super.onDataPacket(net, pkt);
		handleUpdateTag(getBlockState(), pkt.getNbtCompound());
	}

	@Override
	public void read(BlockState state, CompoundNBT compound)
	{
		super.read(state, compound);
		neededTicks = compound.getInt("neededTicks");
		currentTicks = compound.getInt("currentTicks");
		enabled = compound.getBoolean("enabled");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		super.write(compound);
		compound.putInt("neededTicks", neededTicks);
		compound.putInt("currentTicks", currentTicks);
		compound.putBoolean("enabled", enabled);
		return compound;
	}
}
