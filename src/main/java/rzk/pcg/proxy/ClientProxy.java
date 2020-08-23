package rzk.pcg.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rzk.pcg.gui.TimerGui;
import rzk.pcg.tile.TileTimer;

@OnlyIn(Dist.CLIENT)
public class ClientProxy implements IProxy
{
	@Override
	public World getClientWorld()
	{
		return Minecraft.getInstance().world;
	}

	@Override
	public void openTimerGui(TileTimer tile, BlockPos pos)
	{
		Minecraft.getInstance().displayGuiScreen(new TimerGui(tile.getDelay(), pos));
	}
}
