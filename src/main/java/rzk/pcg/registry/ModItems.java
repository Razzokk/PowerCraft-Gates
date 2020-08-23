package rzk.pcg.registry;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import rzk.lib.mc.registry.IModRegistry;
import rzk.pcg.PCGates;
import rzk.pcg.item.ItemUtil;

public final class ModItems implements IModRegistry
{
	// Util
	public static final Item BASE_PLATE = registerItem(new ItemUtil(), "base_plate");

	public static void registerItems(IForgeRegistry<Item> registry) {}

	public static Item registerItem(Item item, String name)
	{
		return IModRegistry.registerItem(PCGates.MODID, item, name);
	}
}
