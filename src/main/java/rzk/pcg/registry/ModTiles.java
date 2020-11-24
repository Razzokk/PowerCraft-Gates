package rzk.pcg.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.lib.mc.tile.TileType;
import rzk.pcg.PCGates;
import rzk.pcg.tile.TileCounter;
import rzk.pcg.tile.TileTimer;

public final class ModTiles
{
	public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, PCGates.MOD_ID);

	public static final RegistryObject<TileEntityType<TileRedstoneDevice>> REDSTONE_DEVICE = TILES.register("tile_redstone_device", () -> new TileType<>(TileRedstoneDevice::new));
	public static final RegistryObject<TileEntityType<TileTimer>> TIMER = TILES.register("tile_timer", () -> new TileType<>(TileTimer::new));
	public static final RegistryObject<TileEntityType<TileCounter>> COUNTER = TILES.register("tile_counter", () -> new TileType<>(TileCounter::new));
}
