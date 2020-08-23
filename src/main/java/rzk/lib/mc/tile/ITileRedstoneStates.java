package rzk.lib.mc.tile;

import net.minecraft.util.Direction;

public interface ITileRedstoneStates
{
	byte getRedstoneStates();

	ITileRedstoneStates setRedstoneStates(byte states);

	default boolean getRedstoneState(Direction side)
	{
		return (getRedstoneStates() & 1 << side.getIndex()) >> side.getIndex() == 1;
	}

	default ITileRedstoneStates setRedstoneState(Direction side, boolean state)
	{
		byte states = getRedstoneStates();
		if (state)
			states |= 1 << side.getIndex();
		else
			states &= ~(1 << side.getIndex());
		return setRedstoneStates(states);
	}
}
