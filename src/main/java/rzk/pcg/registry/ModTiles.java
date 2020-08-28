package rzk.pcg.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rzk.lib.mc.registry.ModRegistry;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.pcg.PCGates;
import rzk.pcg.tile.TileCounter;
import rzk.pcg.tile.TileTimer;

import java.util.ArrayList;
import java.util.List;

public final class ModTiles
{
	public static final List<TileEntityType<?>> TILES = new ArrayList<>();

	@SubscribeEvent
	public static void registerTiles(RegistryEvent.Register<TileEntityType<?>> event)
	{
		registerTile(TileRedstoneDevice.TYPE, "tile_redstone_device");
		registerTile(TileTimer.TYPE, "tile_timer");
		registerTile(TileCounter.TYPE, "tile_counter");

		TILES.forEach(event.getRegistry()::register);
	}

	public static TileEntityType<?> registerTile(TileEntityType<?> tile, String name)
	{
		return ModRegistry.registerTile(TILES, PCGates.MODID, tile, name);
	}
}
