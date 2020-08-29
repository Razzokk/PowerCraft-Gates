package rzk.pcg.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rzk.pcg.client.render.CounterRenderer;
import rzk.pcg.gui.GuiCounter;
import rzk.pcg.gui.GuiTimer;
import rzk.pcg.tile.TileCounter;

@OnlyIn(Dist.CLIENT)
public class ClientProxy implements IProxy
{
	@Override
	public World getClientWorld()
	{
		return Minecraft.getInstance().world;
	}

	@OnlyIn(Dist.CLIENT)
	public void clientSetup(FMLClientSetupEvent event)
	{
		ClientRegistry.bindTileEntityRenderer(TileCounter.TYPE, CounterRenderer::new);
	}

	@Override
	public void openTimerGui(int delay, BlockPos pos)
	{
		Minecraft.getInstance().displayGuiScreen(new GuiTimer(delay, pos));
	}

	@Override
	public void openCounterGui(int maxCount, BlockPos pos)
	{
		Minecraft.getInstance().displayGuiScreen(new GuiCounter(maxCount, pos));
	}
}
