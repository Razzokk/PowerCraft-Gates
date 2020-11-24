package rzk.pcg.tile;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.lib.mc.util.ObjectUtils;
import rzk.lib.mc.util.Utils;
import rzk.pcg.block.BlockCounter;
import rzk.pcg.registry.ModTiles;

import javax.annotation.Nullable;

public class TileCounter extends TileRedstoneDevice
{
	private int maxCount;
	private int counter;

	public TileCounter()
	{
		super(ModTiles.COUNTER.get());
		maxCount = 10;
		counter = 0;
	}

	public void reset()
	{
		count(-counter);
		markDirty();
	}

	public void count(int amount)
	{
		counter = Utils.constrain(counter + amount, 0, maxCount);
		ObjectUtils.ifCastable(getBlockState().getBlock(), BlockCounter.class, blockCounter ->
		{
			if (counter >= maxCount && !getBlockState().get(BlockStateProperties.POWERED))
				blockCounter.setPoweredState(getBlockState(), world, pos, true);
			else if (counter < maxCount && getBlockState().get(BlockStateProperties.POWERED))
				blockCounter.setPoweredState(getBlockState(), world, pos, false);
			else
				world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 2);
			markDirty();
		});
	}

	public int getCounter()
	{
		return counter;
	}

	public int getMaxCount()
	{
		return maxCount;
	}

	public void setMaxCount(int maxCount)
	{
		if (maxCount >= 0)
		{
			this.maxCount = maxCount;
			count(0);
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
		maxCount = compound.getInt("maxCount");
		counter = compound.getInt("counter");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		super.write(compound);
		compound.putInt("maxCount", maxCount);
		compound.putInt("counter", counter);
		return compound;
	}
}
