package rzk.lib.mc.util;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rzk.lib.util.ObjectUtils;

import java.util.Optional;
import java.util.function.Consumer;

public final class Utils
{
	public static Direction getFromBlockPos(BlockPos origin, BlockPos end)
	{
		return Direction.getFacingFromVector(end.getX() - origin.getX(), end.getY() - origin.getY(), end.getZ() - origin.getZ());
	}

	public static <T> Optional<T> getTile(World world, BlockPos pos, Class<T> clazz)
	{
		return ObjectUtils.cast(world.getTileEntity(pos), clazz);
	}

	public static <T> void getTileAndDo(World world, BlockPos pos, Class<T> clazz, Consumer<? super T> consumer)
	{
		getTile(world, pos, clazz).ifPresent(consumer);
	}
}
