package rzk.pcg.proxy;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rzk.pcg.tile.TileTimer;

public interface IProxy
{
	World getClientWorld();

	void openTimerGui(TileTimer tile, BlockPos pos);
}
