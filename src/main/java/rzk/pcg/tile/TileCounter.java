package rzk.pcg.tile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntityType;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.lib.util.MathUtils;
import rzk.pcg.registry.ModBlocks;

import javax.annotation.Nullable;

public class TileCounter extends TileRedstoneDevice
{
	public static final TileEntityType<TileCounter> TYPE = TileEntityType.Builder.create(TileCounter::new, ModBlocks.COUNTER).build(null);

	private int maxCount;
	private int counter;

	public TileCounter()
	{
		super(TYPE);
		maxCount = 10;
		counter = 0;
	}

	public void reset()
	{
		counter = 0;
		if (getBlockState().get(BlockStateProperties.POWERED))
			world.setBlockState(pos, getBlockState().with(BlockStateProperties.POWERED, false));
		else
			world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 2);
		markDirty();
	}

	public void count(int amount)
	{
		counter = MathUtils.constrain(counter + amount, 0, maxCount);
		if (counter >= maxCount && !getBlockState().get(BlockStateProperties.POWERED))
			world.setBlockState(pos, getBlockState().with(BlockStateProperties.POWERED, true));
		else if (counter < maxCount && getBlockState().get(BlockStateProperties.POWERED))
			world.setBlockState(pos, getBlockState().with(BlockStateProperties.POWERED, false));
		else
			world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 2);
		markDirty();
	}

	public void setMaxCount(int maxCount)
	{
		if (maxCount >= 0)
		{
			this.maxCount = maxCount;
			count(0);
		}
	}

	public int getCounter()
	{
		return counter;
	}

	public int getMaxCount()
	{
		return maxCount;
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
