package rzk.pcg.registry;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import rzk.lib.mc.registry.ModRegistry;
import rzk.pcg.PCGates;
import rzk.pcg.item.ItemUtil;

import java.util.ArrayList;
import java.util.List;

public final class ModItems
{
	public static final List<Item> ITEMS = new ArrayList<>();

	// Util
	public static final Item BASE_PLATE = registerItem(new ItemUtil(), "base_plate");

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		ITEMS.forEach(event.getRegistry()::register);
	}

	public static Item registerItem(Item item, String name)
	{
		return ModRegistry.registerItem(ITEMS, PCGates.MODID, item, name);
	}
}
