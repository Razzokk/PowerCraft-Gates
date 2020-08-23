package rzk.lib.mc.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import rzk.lib.mc.block.IItemProvider;
import rzk.pcg.registry.ModBlocks;
import rzk.pcg.registry.ModItems;
import rzk.pcg.registry.ModTiles;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public interface IModRegistry
{
	List<Block> BLOCKS = new ArrayList<>();
	List<Item> ITEMS = new ArrayList<>();
	List<TileEntityType<?>> TILES = new ArrayList<>();

	@SubscribeEvent
	static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		ModBlocks.registerBlocks(registry);
		BLOCKS.forEach(registry::register);
	}

	@SubscribeEvent
	static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> registry = event.getRegistry();
		ModItems.registerItems(registry);
		ITEMS.forEach(registry::register);
	}

	@SubscribeEvent
	static void registerTiles(RegistryEvent.Register<TileEntityType<?>> event)
	{
		IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
		ModTiles.registerTiles(registry);
		TILES.forEach(registry::register);
	}

	static Block registerBlock(String modid, Block block, String name)
	{
		BLOCKS.add(block.setRegistryName(modid, name));
		if (block instanceof IItemProvider)
		{
			BlockItem item = ((IItemProvider) block).createItem();
			if (item != IItemProvider.NO_ITEM)
				registerItem(modid, item, name);
		}
		return block;
	}

	static Item registerItem(String modid, Item item, String name)
	{
		ITEMS.add(item.setRegistryName(modid, name));
		return item;
	}

	static TileEntityType<?> registerTile(String modid, TileEntityType<?> tile, String name)
	{
		TILES.add(tile.setRegistryName(modid, name));
		return tile;
	}
}
