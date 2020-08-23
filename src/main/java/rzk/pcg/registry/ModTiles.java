package rzk.pcg.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.IForgeRegistry;
import rzk.lib.mc.registry.IModRegistry;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.pcg.PCGates;
import rzk.pcg.tile.TileTimer;

public class ModTiles implements IModRegistry
{
	public static void registerTiles(IForgeRegistry<TileEntityType<?>> registry)
	{
		registerTile(TileRedstoneDevice.TYPE, "tile_redstone_device");
		registerTile(TileTimer.TYPE, "tile_timer");
	}

	public static TileEntityType<?> registerTile(TileEntityType<?> tile, String name)
	{
		return IModRegistry.registerTile(PCGates.MODID, tile, name);
	}
}
