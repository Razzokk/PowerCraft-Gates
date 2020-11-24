package rzk.pcg.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rzk.pcg.client.render.CounterRenderer;
import rzk.pcg.registry.ModTiles;

public class ClientProxy
{
	public static void clientSetup(FMLClientSetupEvent event)
	{
		ClientRegistry.bindTileEntityRenderer(ModTiles.COUNTER.get(), CounterRenderer::new);
	}
}
