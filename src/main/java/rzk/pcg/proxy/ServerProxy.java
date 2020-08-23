package rzk.pcg.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rzk.pcg.tile.TileTimer;

@OnlyIn(Dist.DEDICATED_SERVER)
public class ServerProxy implements IProxy
{
	@Override
	public World getClientWorld()
	{
		return null;
	}

	@Override
	public void openTimerGui(TileTimer tile, BlockPos pos)
	{}
}
