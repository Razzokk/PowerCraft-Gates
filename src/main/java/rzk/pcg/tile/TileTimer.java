package rzk.pcg.tile;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.lib.util.ObjectUtils;
import rzk.pcg.block.BlockTimer;
import rzk.pcg.registry.ModBlocks;

import javax.annotation.Nullable;

public class TileTimer extends TileRedstoneDevice implements ITickableTileEntity
{
	public static final TileEntityType<TileTimer> TYPE = TileEntityType.Builder.create(TileTimer::new, ModBlocks.TIMER, ModBlocks.TIMER_ON_DELAY, ModBlocks.TIMER_OFF_DELAY).build(null);

	private int neededTicks;
	private int currentTicks;
	private boolean enabled;

	public TileTimer()
	{
		super(TYPE);
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
			ObjectUtils.cast(getBlockState().getBlock(), BlockTimer.Delay.class).ifPresent(blockTimer -> blockTimer.setPoweredState(getBlockState(), world, pos, blockTimer.isOnTimer()));
		}
		else
		{
			ObjectUtils.cast(getBlockState().getBlock(), BlockTimer.class).ifPresent(blockTimer ->
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
		handleUpdateTag(pkt.getNbtCompound());
	}

	@Override
	public void read(CompoundNBT compound)
	{
		super.read(compound);
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
