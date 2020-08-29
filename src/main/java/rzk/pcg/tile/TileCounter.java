package rzk.pcg.tile;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntityType;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.lib.util.MathUtils;
import rzk.lib.util.ObjectUtils;
import rzk.pcg.block.BlockCounter;
import rzk.pcg.registry.ModBlocks;

import javax.annotation.Nullable;
import java.util.Optional;

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
		count(0);
		markDirty();
	}

	public void count(int amount)
	{
		counter = MathUtils.constrain(counter + amount, 0, maxCount);
		Optional<BlockCounter> blockCounter = ObjectUtils.cast(getBlockState().getBlock(), BlockCounter.class);
		if (counter >= maxCount && !getBlockState().get(BlockStateProperties.POWERED))
			blockCounter.ifPresent(block -> block.setPoweredState(getBlockState(), world, pos, true));
		else if (counter < maxCount && getBlockState().get(BlockStateProperties.POWERED))
			blockCounter.ifPresent(block -> block.setPoweredState(getBlockState(), world, pos, false));
		else
			world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 2);
		markDirty();
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
		handleUpdateTag(getBlockState(), pkt.getNbtCompound());
	}

	@Override
	public void read(BlockState state, CompoundNBT compound)
	{
		super.read(state, compound);
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
