package rzk.pcg.proxy;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.DEDICATED_SERVER)
public class ServerProxy implements IProxy
{
	@Override
	public World getClientWorld()
	{
		return null;
	}

	@Override
	public void clientSetup(FMLClientSetupEvent event) {}

	@Override
	public void openTimerGui(int delay, BlockPos pos) {}

	@Override
	public void openCounterGui(int maxCount, BlockPos pos) {}
}
