package rzk.pcg.proxy;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rzk.pcg.tile.TileTimer;

public interface IProxy
{
	World getClientWorld();

	void clientSetup(FMLClientSetupEvent event);

	void openTimerGui(int delay, BlockPos pos);

	void openCounterGui(int maxCount, BlockPos pos);
}
