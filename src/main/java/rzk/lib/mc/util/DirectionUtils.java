package rzk.lib.mc.util;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public final class DirectionUtils
{
	public static Direction getFromBlockPos(BlockPos origin, BlockPos end)
	{
		return Direction.getFacingFromVector(end.getX() - origin.getX(), end.getY() - origin.getY(), end.getZ() - origin.getZ());
	}
}
