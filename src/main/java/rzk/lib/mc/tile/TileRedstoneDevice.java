package rzk.lib.mc.tile;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileRedstoneDevice extends TileEntity implements ITileRedstoneStates
{
	public static final TileEntityType<TileRedstoneDevice> TYPE = TileEntityType.Builder.create(TileRedstoneDevice::new).build(null);

	private byte states;

	public TileRedstoneDevice()
	{
		super(TYPE);
	}

	public TileRedstoneDevice(TileEntityType<?> tileEntityType)
	{
		super(tileEntityType);
	}

	@Override
	public byte getRedstoneStates()
	{
		return states;
	}

	@Override
	public ITileRedstoneStates setRedstoneStates(byte states)
	{
		this.states = states;
		return this;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		super.write(compound);
		compound.putByte("states", states);
		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT compound)
	{
		super.read(state, compound);
		states = compound.getByte("states");
	}
}
