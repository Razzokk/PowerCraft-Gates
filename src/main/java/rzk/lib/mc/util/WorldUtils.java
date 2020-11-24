package rzk.lib.mc.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;
import java.util.function.Function;

public final class WorldUtils
{
	public static <T> T getTile(World world, BlockPos pos, Class<T> clazz)
	{
		return ObjectUtils.cast(world.getTileEntity(pos), clazz);
	}

	public static <T> boolean ifTilePresent(World world, BlockPos pos, Class<T> clazz, Consumer<T> consumer)
	{
		return ObjectUtils.ifCastable(world.getTileEntity(pos), clazz, consumer);
	}

	public static <T, U> U mapTile(World world, BlockPos pos, Class<T> clazz, Function<T, U> function)
	{
		return ObjectUtils.mapIfCastable(world.getTileEntity(pos), clazz, function);
	}
}
